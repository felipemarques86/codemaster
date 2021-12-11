import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Code, EndUser} from "../models/evaluation-activity";

@Injectable({
  providedIn: 'root'
})
export class EndUserService {

  constructor(private http: HttpClient) {
  }

  saveEndUser(endUser: EndUser) {
    console.log('Save user', endUser);
    return this.http.post<EndUser>(
      `http://localhost/v1/api/user/`,
      endUser
    );
  }
}
