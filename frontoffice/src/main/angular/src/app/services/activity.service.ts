import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ActivityInstance} from "../models/activity-instance";
import {Activity, Code, Comment} from "../models/evaluation-activity";

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


  getActivity(id: number): Observable<Activity> {
    return this.http.get<Activity>(
      `http://localhost/v1/api/activity/${id}/`
    );
  }

  addComment(code: Code, comment: Comment, line: number, userId: number) {
    return this.http.post<Code>(
      `http://localhost/v1/api/code/${code.id}/user/${userId}/comment/${line}`,
      comment
    );
  }
}
