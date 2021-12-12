import {Deliverable} from "./activity-instance";

export interface ITestAnalytics {

  updatePassedTests(deliverable: Deliverable, passedTests: any[]): void;
  updateFailedTests(deliverable: Deliverable, failedTests: any[]): void;

}
