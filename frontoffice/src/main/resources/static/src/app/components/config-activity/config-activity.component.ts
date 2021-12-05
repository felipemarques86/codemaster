import {Component, OnInit} from '@angular/core';
import {ActivityService} from "../../services/activity.service";
import {Activity, LanguageEnum, Solution} from "../../models/evaluation-activity";
import {FormControl} from "@angular/forms";

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

  constructor(private activityService: ActivityService) { }

  ngOnInit(): void {
  }

  saveActivity() {
    this.mergeValues();
    this.activityService.saveActivity(this.activity);
  }

  private mergeValues() {

    if(!this.activity) {
      this.activity = {
        name: this.name.value,
        id: this.id,
        activityUnitTestList: [],
        score: 0,
        description: this.description.value,
        solution: this.solutions
      };

      console.log('Save Activity', this.activity);

      this.activityService.saveActivity(this.activity).subscribe( (act: Activity) => {

        this.activity = act;
        console.log('Activity saved:', this.activity);
        this.patchValues();

      }, error => {
        console.error(error);
        alert("Error while saving");
      })

    }
  }

  private patchValues() {
    this.name.setValue(this.activity.name);
    this.description.setValue(this.activity.description);
    this.id =  this.activity.id;
  }

  addSolution() {

    const solution: Solution = {
      id: undefined,
      testsToPass: [],
      checkOutput: false,
      code: {code: '', commentList: [], score: 0, language: LanguageEnum.JAVASCRIPT}
    };

    this.solutions.push(solution)

  }

  codeChanged() {

  }
}
