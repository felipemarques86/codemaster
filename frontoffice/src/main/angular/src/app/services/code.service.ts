import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Activity, Code} from "../models/evaluation-activity";

@Injectable({
  providedIn: 'root'
})
export class CodeService {

  constructor(private http: HttpClient) {
  }

  saveCode(code: Code) {
    return this.http.post<Code>(
      `http://localhost/v1/api/code/`,
      code
    );
  }

  getCode(id?: number) {
    return this.http.get<Code>(
      `http://localhost/v1/api/code/${id}`
    );
  }

}
