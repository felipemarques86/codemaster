import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ActivityInstance} from "../models/activity-instance";

@Injectable({
  providedIn: 'root'
})
export class ActivityService {

  constructor(private http: HttpClient) { }

   getActivityInstance(id: number, userId: number): Observable<ActivityInstance<any>>  {
    return this.http.get<ActivityInstance<any>> (
      `http://localhost/v1/api/cea/${id}/${userId}`
    );
  }




}
