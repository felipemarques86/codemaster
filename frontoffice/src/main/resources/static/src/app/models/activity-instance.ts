import {Activity} from "./activity";
import {ActivityUnitTest, Code, EndUser, Solution} from "./evaluation-activity";


export interface ActivityInstance<T extends Activity> {
  activity: T;
  id: number;
  startDate: Date;
  endDate: Date;
  deliverable: Deliverable[];
}


export interface Deliverable {
  author: EndUser;
  content: string;
  code: Code;
  result: UnitTestResult[];
  solution: Solution;
  submitted: boolean;
  readOnly: boolean;
}

export interface UnitTestResult {
  unitTest: ActivityUnitTest;
  result: string;
  passed: boolean;
}
