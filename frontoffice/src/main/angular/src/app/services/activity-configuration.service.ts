import { Injectable } from '@angular/core';
import {Activity} from "../models/evaluation-activity";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {IActivityConfiguration} from "../models/iactivity-configuration";

@Injectable({
  providedIn: 'root'
})
export class ActivityConfigurationService implements IActivityConfiguration{

  constructor(private http: HttpClient) {
  }

  saveActivity(activity: Activity) {
    const w: any = window;
    w.activityName = activity.name;
    w.activityUnitTestList = activity.activityUnitTestList;
    w.description = activity.description;
    w.solutions = activity.solution;
    w.bibliographicReferenceList = activity.bibliographicReferenceList;
  }


  getActivity(): Activity {
    const w: any = window;
    const activity: Activity = {
      name: w.activityName ? w.activityName : null,
      activityUnitTestList: w.activityUnitTestList ? w.activityUnitTestList : [],
      description: w.description ? w.description : null,
      solution: w.solutions ? w.solutions : [],
      bibliographicReferenceList: w.bibliographicReferenceList ? w.bibliographicReferenceList : []
    }
    return activity;
  }

  validate(activity: Activity) {
    return this.http.post<Activity>(
      `${environment.base}/v1/api/activity/validate`,
      activity
    );
  }
}
