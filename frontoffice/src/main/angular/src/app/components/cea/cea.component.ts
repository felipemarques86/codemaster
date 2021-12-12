import {Component, OnInit, QueryList, ViewChild, ViewChildren} from '@angular/core';
import {ActivityInstance, Deliverable, UnitTestResult} from "../../models/activity-instance";
import {ActivityService} from "../../services/activity.service";
import {ActivatedRoute} from "@angular/router";
import {ActivityUnitTest, Code, Comment, LanguageEnum} from "../../models/evaluation-activity";
import {CodemirrorComponent} from "@ctrl/ngx-codemirror";
import {EndUserService} from "../../services/end-user.service";
import {AnalyticsService} from "../../services/analytics.service";

@Component({
  selector: 'app-cea',
  templateUrl: './cea.component.html',
  styleUrls: ['./cea.component.css']
})
export class CeaComponent implements OnInit {

  public activityInstance!: ActivityInstance;
  public deliverable!: Deliverable;
  public passedTests: ActivityUnitTest[] = [];
  public failedTests: any[] = [];
  @ViewChild('codemirrorComponent') codemirrorComponent!: CodemirrorComponent;
  @ViewChildren('codemirrorComponentOthers') codemirrorComponentOthers!: QueryList<CodemirrorComponent>;
  ASSERT_FUNC = "function assert(cond){ try{ if(!eval(cond)) { throw 'Assertion ' + cond + ' is FALSE'} } catch(e) { throw e; } }";
  codeOutput!: string;

  constructor(private activityService: ActivityService,
              private endUserService: EndUserService,
              private analyticsService: AnalyticsService,
              private route: ActivatedRoute) { }


  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    const userId = this.route.snapshot.paramMap.get('userId');
    if(id && userId) {
      this.activityService.getActivityInstance(+id).subscribe(instance => {
        if(!instance) {
          alert("Esta atividade não está disponível.");
          return;
        }

        this.activityInstance = instance;
        this.activityInstance.deliverable.forEach( del =>{
            if(del.author && del.author.id == +userId) {
              this.deliverable = del;
              if(!this.deliverable.author.name) {
                let authorName = null;
                do {
                  authorName = prompt('Qual o seu nome completo?');
                } while(!authorName);
                this.deliverable.author.name = authorName;
                this.endUserService.saveEndUser(this.deliverable.author).subscribe( endUser => {
                  console.log(endUser);
                })
              }
            }
        });
      });
    } else {
      throw "ID and UserID required";
    }
  }

  runTests(runOthers: boolean) {
    if(!runOthers) {
      this.passedTests = [];
      this.failedTests = [];

      this.deliverable.solution.testsToPass.forEach(test => {
        this.runTest(test, this.deliverable.code, this.failedTests, this.passedTests);
      });

      this.analyticsService.updatePassedTests(this.deliverable, this.passedTests);
      this.analyticsService.updateFailedTests(this.deliverable, this.failedTests);

    } else {
      this.activityInstance.deliverable.filter( d => d.id != this.deliverable.id).forEach( del => {
        del.result = [];
        del.solution.testsToPass.forEach( test => {

          let failed: any[] = [], passed: any[] = [];
          this.runTest(test, del.code, failed, passed);
          if(failed.length > 0) {
            let rest:UnitTestResult =  {
              unitTest: test,
              result: failed[0].error,
              passed: false
            };
            del.result.push(rest);
          }
          if(passed.length > 0) {
            let rest:UnitTestResult =  {
              unitTest: test,
              result: "",
              passed: true
            };
            del.result.push(rest);
          }
        });
      });
    }
  }

  private checkTests(): number {
    let failedTests: any = [], passedTests: any = [];
    for(let i in this.deliverable.solution.testsToPass) {
      const test = this.deliverable.solution.testsToPass[i];
      this.runTest(test, this.deliverable.code, failedTests, passedTests);
    }

    this.deliverable.result = [];
    failedTests.forEach( (t: {error: string, test: ActivityUnitTest}) => {
      const testResult: UnitTestResult = {
          result: t.error,
          passed: false,
          unitTest: t.test
      };
      this.deliverable.result.push(testResult);
    });

    passedTests.forEach( (t:ActivityUnitTest) => {
      const testResult: UnitTestResult = {
        result: '',
        passed: true,
        unitTest: t
      };
      this.deliverable.result.push(testResult);
    });


    return failedTests.length;
  }

  private runTest(test: ActivityUnitTest, code: Code, failedTests: any[], passedTests: any[]) {
    switch (test.code.language) {
      case LanguageEnum.JAVASCRIPT:
        try {
          eval("{" + this.ASSERT_FUNC + code.code + ";\r\n" + test.code.code + "}");
          passedTests.push(test);
        } catch (e: any) {
          console.log(test);
          failedTests.push({test: test, error: e});
        }
        break;
      default:
        alert("Language not supported: " + test.code.language);
    }

    return failedTests.length == 0;
  }

  getOthersDeliverable() {
    return this.activityInstance.deliverable.filter( (d: Deliverable) => d.id != this.deliverable.id);
  }

  run(deliverable: Deliverable) {
    let output = '';

    const consoleCode = '{\n' +
    '  const log = console.log.bind(console)\n' +
    '  console.log = (...args) => {\n' +
    "    output += JSON.stringify(args) + '<br />';\n" +
    '  }';

    const consoleCodeEnd = 'console.log = log;';

    const fullCode = consoleCode + '\r\n' + deliverable.code.code + consoleCodeEnd +'}';
    eval(fullCode);

    deliverable.output = output;
  }

  addComment(code: Code) {
      let line = prompt('Linha: ');
      line = line ? line : '1';
      const commentText = prompt('Comentário: ');
      if(commentText) {
        const comment: Comment = {
            id: undefined,
            content: commentText,
            date: new Date(),
            author: this.deliverable.author,
            replies: []
        };

        this.activityService.addComment(code, comment, +line, this.deliverable.author.id).subscribe( code => {
            code.code = code.code;
            code.commentList = code.commentList;
        });
      }
  }

  get currentUser() {
    return this.deliverable.author;
  }

  codeChangeEvent(code: Code) {
    this.deliverable.code.code = code.code;
  }

  replyCommentEvent($event: any) {
    //console.log('replyComment', $event);
  }

  submit() {
    let submitFlag = true;
    let failedTestsCount = this.checkTests();
    if(failedTestsCount > 0) {
      submitFlag = confirm(`Existem ${failedTestsCount} testes que estão a falhar. Tem a certeza que quer submeter?`);
    }
    if(submitFlag) {
      if(confirm('Após a submissão não poderá alterar o código que escreveu. No entanto poderá fazer comentários no seu código ou nos códigos dos seus colegas. Tem a certeza que quer continuar?')) {
        this.activityService.submit(this.deliverable).subscribe( del => {
          this.deliverable = del;
        });
      }
    }
  }
}
