import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ActivityInstance} from "../models/activity-instance";
import {Activity, Code, Comment, EndUser} from "../models/evaluation-activity";

@Injectable({
  providedIn: 'root'
})
export class ActivityService {

  constructor(private http: HttpClient) {
  }

  getActivityInstance(id: number): Observable<ActivityInstance<any>> {
    return this.http.get<ActivityInstance<any>>(
      `http://localhost/v1/api/cea/${id}`
    );
  }

   createInstance(id: number, userId: number): Observable<ActivityInstance<any>> {
    return this.http.get<ActivityInstance<any>>(
      `http://localhost/v1/api/cea/${id}/${userId}`
    );
  }

  saveActivity(activity: Activity) {
    const w:any = window;
    w.activityName = activity.name;
    w.activityUnitTestList = activity.activityUnitTestList;
    w.description = activity.description;
    w.solutions = activity.solution;
    w.bibliographicReferenceList = activity.bibliographicReferenceList;
  }


  getActivity(): Activity {
    const w:any = window;
    const activity: Activity = {
      name: w.activityName ? w.activityName : null,
      activityUnitTestList: w.activityUnitTestList ? w.activityUnitTestList : [],
      description: w.description ? w.description : null,
      solution: w.solutions ? w.solutions : [],
      bibliographicReferenceList: w.bibliographicReferenceList ? w.bibliographicReferenceList : []
    }
   return activity;
  }

  addComment(code: Code, comment: Comment, line: number, userId: number) {
    return this.http.post<Code>(
      `http://localhost/v1/api/code/${code.id}/user/${userId}/comment/${line}`,
      comment
    );
  }

  replyComment(comment: Comment, commentText: string, currentUser: EndUser) : Observable<Comment> {
    return this.http.post<Comment>(
      `http://localhost/v1/api/comment/${comment.id}/user/${currentUser.id}/reply`,
      {content: commentText}
    );

  }
}
