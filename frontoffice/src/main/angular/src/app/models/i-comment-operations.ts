import {Code, Comment, EndUser} from "./evaluation-activity";
import {Observable} from "rxjs";

export interface ICommentOperations {
  addComment(code: Code, comment: Comment, line: number, userId: number): Observable<Code>;
  replyComment(comment: Comment, commentText: string, currentUser: EndUser): Observable<Comment>;


}
