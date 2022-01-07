import {Injectable} from '@angular/core';
import {ITestAnalytics} from "../models/itest-analytics";
import {ICodeEditingEvents} from "../models/icode-editing-events";
import {Deliverable} from "../models/activity-instance";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AnalyticsService implements ITestAnalytics, ICodeEditingEvents{

  constructor(private http: HttpClient) { }

  updatePassedTests(deliverable: Deliverable, passedTests: any[]) {
    this.http.post(`${environment.base}/v1/api/analytics/deliverable/${deliverable.id}`, {name: 'Testes que passaram', value: passedTests.length + '' })
      .subscribe(_=> {}, error => console.error(error));
  }

  updateFailedTests(deliverable: Deliverable, failedTests: any[]) {
    this.http.post(`${environment.base}/v1/api/analytics/deliverable/${deliverable.id}`, {name: 'Testes de falharam', value: failedTests.length + '' })
      .subscribe(_=> {}, error => console.error(error));
  }

  putAnalytics(deliverable: Deliverable, name: string, value: string | null) {
    if(value) {
      this.http.post(`${environment.base}/v1/api/analytics/deliverable/${deliverable.id}`, {name: name, value: value})
        .subscribe(_ => {
        }, error => console.error(error));
    }
  }
}
