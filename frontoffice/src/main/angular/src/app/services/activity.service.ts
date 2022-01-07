import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ActivityInstance, Deliverable} from "../models/activity-instance";
import {Code, Comment, EndUser} from "../models/evaluation-activity";
import {environment} from "../../environments/environment";
import {ICommentOperations} from "../models/i-comment-operations";
import {IActivitiyOperations} from "../models/iactivitiy-operations";

@Injectable({
  providedIn: 'root'
})
export class ActivityService implements ICommentOperations, IActivitiyOperations{

  constructor(private http: HttpClient) {
  }

  getActivityInstance(id: number): Observable<ActivityInstance> {
    return this.http.get<ActivityInstance>(
      `${environment.base}/v1/api/cea/${id}`
    );
  }

  createInstance(id: number, userId: number): Observable<ActivityInstance> {
    return this.http.get<ActivityInstance>(
      `${environment.base}/v1/api/cea/${id}/${userId}`
    );
  }

  addComment(code: Code, comment: Comment, line: number, userId: number): Observable<Code> {
    return this.http.post<Code>(
      `${environment.base}/v1/api/code/${code.id}/user/${userId}/comment/${line}`,
      comment
    );
  }

  replyComment(comment: Comment, commentText: string, currentUser: EndUser): Observable<Comment> {
    return this.http.post<Comment>(
      `${environment.base}/v1/api/comment/${comment.id}/user/${currentUser.id}/reply`,
      {content: commentText}
    );
  }

  submit(instanceId: number, deliverable: Deliverable) : Observable<Deliverable> {
    return this.http.post<Deliverable>(
      `${environment.base}/v1/api/cea/${instanceId}/deliverable/submit`,
      deliverable
    );
  }
}
