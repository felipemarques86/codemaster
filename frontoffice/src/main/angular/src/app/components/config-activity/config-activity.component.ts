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

    const id = this.route.snapshot.paramMap.get('id');

    if (id) {
      this.activityService.getActivity(+id).subscribe(act => {
        this.activity = act;
        this.patchValues();
      });
    }
  }

  saveActivity() {
    this.mergeValues();
    //this.activityService.saveActivity(this.activity);
  }

  private mergeValues() {

    if (!this.activity) {
      this.activity = {
        name: this.name.value,
        id: this.id,
        activityUnitTestList: this.activityTests,
        score: 0,
        description: this.description.value,
        solution: this.solutions,
        bibliographicReferenceList: this.bibliographicReferenceList
      };
    }

    console.log('Save Activity', this.activity);
    this.activityService.saveActivity(this.activity).subscribe((act: Activity) => {
      this.activity = act;
      console.log('Activity saved:', this.activity);
      this.patchValues();

    }, error => {
      console.error(error);
      alert("Error while saving");
    });
  }

  private patchValues() {
    this.name.setValue(this.activity.name);
    this.description.setValue(this.activity.description);
    this.id = this.activity.id;
    this.activityTests = this.activity.activityUnitTestList;
    this.solutions = this.activity.solution;
    this.bibliographicReferenceList = this.activity.bibliographicReferenceList;
  }

  addSolution() {

    const solution: Solution = {
      id: undefined,
      testsToPass: [],
      checkOutput: false,
      code: {code: '', commentList: [], score: 0, language: LanguageEnum.JAVASCRIPT}
    };

    this.solutions.push(solution);
  }

  codeChanged() {

  }

  nextStep() {
    this.step++;
  }

  previousStep() {
    this.step = this.step > 1 ? this.step - 1 : 1;
  }

  addSolutionTest() {
    const test: ActivityUnitTest = {
      id: undefined,
      code: {code: '', id: undefined, language: LanguageEnum.JAVASCRIPT, score: 0, commentList: []},
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
      code: {code: '', id: undefined, language: LanguageEnum.JAVASCRIPT, score: 0, commentList: []},
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


    try {
      eval(this.selectedSolution.value.code.code + '\r\n{' + this.ASSERT_FUNC + ';\r\n' + test.code.code + '}');
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
