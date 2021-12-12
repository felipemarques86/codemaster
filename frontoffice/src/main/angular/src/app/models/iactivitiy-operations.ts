import {Observable} from "rxjs";
import {ActivityInstance} from "./activity-instance";
import {Activity} from "./evaluation-activity";

export interface IActivitiyOperations {
  getActivityInstance(id: number): Observable<ActivityInstance>;

  createInstance(id: number, userId: number): Observable<ActivityInstance>;


}
