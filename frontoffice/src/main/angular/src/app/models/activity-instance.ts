import {Activity, ActivityUnitTest, Code, EndUser, Solution} from "./evaluation-activity";


export interface ActivityInstance {
  activity: Activity;
  id: number;
  startDate: Date;
  endDate: Date;
  deliverable: Deliverable[];
}

export interface Deliverable {
  id?: number;
  author: EndUser;
  content: string;
  code: Code;
  result: UnitTestResult[];
  solution: Solution;
  submitted: boolean;
  readOnly: boolean;
  /* Temp */
  output: string;


}

export interface UnitTestResult {
  unitTest: ActivityUnitTest;
  result: string;
  passed: boolean;
}
