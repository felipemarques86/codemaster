import {Component, OnInit, QueryList, ViewChild, ViewChildren} from '@angular/core';
import {ActivityInstance, Deliverable, UnitTestResult} from "../../models/activity-instance";
import {ActivityService} from "../../services/activity.service";
import {ActivatedRoute} from "@angular/router";
import {Activity, ActivityUnitTest, Code, LanguageEnum} from "../../models/evaluation-activity";
import {CodemirrorComponent} from "@ctrl/ngx-codemirror";
import {LineHandle, Position} from "codemirror";

@Component({
  selector: 'app-cea',
  templateUrl: './cea.component.html',
  styleUrls: ['./cea.component.css']
})
export class CeaComponent implements OnInit {

  public activityInstance!: ActivityInstance<Activity>;
  public deliverable!: Deliverable;
  public passedTests: ActivityUnitTest[] = [];
  public failedTests: any[] = [];
  @ViewChild('codemirrorComponent') codemirrorComponent!: CodemirrorComponent;
  @ViewChildren('codemirrorComponentOthers') codemirrorComponentOthers!: QueryList<CodemirrorComponent>;
  ASSERT_FUNC = "function assert(cond){ try{ if(!eval(cond)) { throw 'Assertion ' + cond + ' is FALSE'} } catch(e) { throw e; } }";

  constructor(private activityService: ActivityService,
              private route: ActivatedRoute) { }


  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    const userId = this.route.snapshot.paramMap.get('userId');
    if(id && userId) {
      this.activityService.getActivityInstance(+id, +userId).subscribe(instance => {
        this.activityInstance = instance;
        this.activityInstance.deliverable.forEach( del =>{
            if(!del.readOnly) {
              this.deliverable = del;
            }
        });
        setTimeout(() => this.buildComments(this.codemirrorComponent, this.deliverable)  , 100);
        setTimeout(() => {
          let i = 0;
          this.codemirrorComponentOthers.forEach( cm => {
            this.buildComments(cm, this.getOthersDeliverable()[i++]);
          })
        }, 100);
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

    } else {
      this.activityInstance.deliverable.filter( d => d.readOnly).forEach( del => {
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
  }

  getOthersDeliverable() {
    return this.activityInstance.deliverable.filter( d => d.readOnly);
  }

  highlightLine(id: number) {
    let comment =  document.getElementById("comment_" + id);
    if(comment && comment.parentElement) {
      setTimeout(() => {
        if(comment && comment.parentElement)
          comment.parentElement.style.textDecoration = "underline wavy white";
      }, 50);
    }
  }

  removeHighlightLine(id: number) {
    let comment =  document.getElementById("comment_" + id);
    if(comment && comment.parentElement) {
      setTimeout( () => {
        if(comment && comment.parentElement)
          comment.parentElement.style.textDecoration = "none";
      }, 200);
    }
  }

  buildComments(codemirrorComponent: CodemirrorComponent, deliverable: Deliverable): void {
    let n = 0;
    codemirrorComponent.codeMirror?.eachLine( (line: LineHandle) => {
      if(line.text.indexOf('/**<comment>') >= 0) {
        let p1: Position = {ch: line.text.indexOf('/**<comment>'), line: n};
        let p2: Position = {ch: line.text.indexOf('**/', p1.ch)+3, line: n};

        const elm = document.createElement("span");

        let opts: any = { readOnly: true, className: 'cea-comment', atomic: true, replacedWith: elm};
        let commentId = +line.text.substring(p1.ch, p2.ch).replace("/**<comment>", "").replace("</comment>**/", "");
        let comment = deliverable.code.commentList.find( c => c.id == commentId );
        if(comment) {
          elm.id = "comment_" + comment.id;
          elm.className = "btn btn-info cea-comment";
          elm.innerHTML = "<span class='badge badge-light'>" + comment.id +"</span> Comentário de " + comment.author.name + "";
        }
        codemirrorComponent.codeMirror?.markText(p1, p2, opts);
      }
      n++;
    });
  }


  save() {

  }
}
