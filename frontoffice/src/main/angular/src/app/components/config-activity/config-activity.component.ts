import {Component, OnInit} from '@angular/core';
import {ActivityService} from "../../services/activity.service";
import {
  Activity,
  ActivityUnitTest,
  BibliographicReference,
  LanguageEnum,
  Solution
} from "../../models/evaluation-activity";
import {FormControl} from "@angular/forms";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-config-activity',
  templateUrl: './config-activity.component.html',
  styleUrls: ['./config-activity.component.css']
})
export class ConfigActivityComponent implements OnInit {

  private activity!: Activity;
  name = new FormControl('');
  description = new FormControl('');
  solutions: Solution[] = [];
  id = 0;
  step = 1;
  selectedSolution = new FormControl('');
  bibliographicReferenceList: BibliographicReference[] = [];
  activityTests: ActivityUnitTest[] = [];
  nameBr = new FormControl('');
  urlBr = new FormControl('');
  ASSERT_FUNC = "function assert(cond){ try{ if(!eval(cond)) { throw 'Assertion ' + cond + ' is FALSE'} } catch(e) { throw e; } }";

  constructor(private activityService: ActivityService, private modalService: NgbModal, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
      this.activity = this.activityService.getActivity();
      this.patchValues();
  }

  saveActivity() {
    this.mergeValues();
    console.log(JSON.stringify(this.activityService.getActivity()));
  }

  private mergeValues() {

    this.activity = {
        name: this.name.value,
        activityUnitTestList: this.activityTests,
        description: this.description.value,
        solution: this.solutions,
        bibliographicReferenceList: this.bibliographicReferenceList
    }

    this.activityService.saveActivity(this.activity);
  }

  private patchValues() {
    this.name.setValue(this.activity.name);
    this.description.setValue(this.activity.description);
    this.activityTests = this.activity.activityUnitTestList;
    this.solutions = this.activity.solution;
    this.bibliographicReferenceList = this.activity.bibliographicReferenceList;
  }

  addSolution() {

    const solution: Solution = {
      testsToPass: [],
      code: {code: '', commentList: [], language: LanguageEnum.JAVASCRIPT}
    };

    this.solutions.push(solution);
  }

  codeChanged() {

  }

  nextStep() {
    this.saveActivity();
    this.step = this.step > 3 ? 4: this.step + 1;
  }

  previousStep() {
    this.saveActivity();
    this.step = this.step > 1 ? this.step - 1 : 1;
  }

  addSolutionTest() {
    const test: ActivityUnitTest = {
      code: {code: '', language: LanguageEnum.JAVASCRIPT, commentList: []},
      expectedResult: '',
      score: 0,
      performance: false,
      passed: undefined,
      message: '',
      global: false
    };

    if (!this.selectedSolution.value.testsToPass) {
      this.selectedSolution.value.testsToPass = [];
    }

    this.selectedSolution.value.testsToPass.push(test);
  }

  addGlobalTest() {
    const test: ActivityUnitTest = {
      id: undefined,
      code: {code: '', language: LanguageEnum.JAVASCRIPT, commentList: []},
      expectedResult: '',
      score: 0,
      performance: false,
      passed: undefined,
      message: '',
      global: true
    };

    this.activityTests.push(test);
  }

  validate(test: ActivityUnitTest) {
    const timeCodeStart = test.performance ? "const startTime = new Date();\r\n" : "";
    const endTime = test.performance ? "const RUN_TIME = (new Date()) - startTime; console.log(RUN_TIME);\r\n" :"";
    try {
      eval(timeCodeStart + this.selectedSolution.value.code.code + endTime + '\r\n{' + this.ASSERT_FUNC + ';\r\n' + test.code.code + '}');
      test.passed = true;
    } catch (e: any) {
      console.error(e);
      test.passed = false;
      test.message = e.toString();
    }
  }

  validateGlobalTest(test: ActivityUnitTest) {
    const c = this.solutions.map(s => s.code.code).reduce((s1, s2) => s1 + ";" + s2 + ";");
    try {
      eval(c + '\r\n{' + this.ASSERT_FUNC + ';\r\n' + test.code.code + '}');
      test.passed = true;
    } catch (e: any) {
      console.error(e);
      test.passed = false;
      test.message = e.toString();
    }
  }

  addOrEditBr(content: any, element?: BibliographicReference) {

    if (element) {
      this.nameBr.setValue(element.name);
      this.urlBr.setValue(element.url);
    }

    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {

      const ref: BibliographicReference = {
        id: element ? element.id : undefined,
        name: this.nameBr.value,
        url: this.urlBr.value
      };

      if (!element) {
        this.bibliographicReferenceList.push(ref);
      }

      this.nameBr.setValue('');
      this.urlBr.setValue('');

    }, (reason) => {
      this.nameBr.setValue('');
      this.urlBr.setValue('');
    });
  }

  getTests(): ActivityUnitTest[] {
    const tests = [];
    if (this.selectedSolution.value.testsToPass) {
      tests.push(this.selectedSolution.value.testsToPass);
    }
    if (this.activityTests) {
      tests.push(this.activityTests);
    }
    return tests;
  }
}
