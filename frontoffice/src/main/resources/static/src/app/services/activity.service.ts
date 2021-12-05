import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ActivityInstance} from "../models/activity-instance";
import {Activity} from "../models/evaluation-activity";

@Injectable({
  providedIn: 'root'
})
export class ActivityService {

  constructor(private http: HttpClient) {
  }

  getActivityInstance(id: number, userId: number): Observable<ActivityInstance<any>> {
    return this.http.get<ActivityInstance<any>>(
      `http://localhost/v1/api/cea/${id}/${userId}`
    );
  }

  saveActivity(activity: Activity) {
    return this.http.post<Activity>(
      `http://localhost/v1/api/activity/`,
      activity
    );
  }


}
