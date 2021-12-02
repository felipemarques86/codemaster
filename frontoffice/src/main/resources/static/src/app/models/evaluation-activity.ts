import {Activity} from "./activity";

export interface EvaluationActivity extends Activity {
  solution: Solution[];
  score: number;
}

export interface  Solution {
  id: number;
  testsToPass: ActivityUnitTest[];
  code: Code;
  checkOutput: boolean;
}

export enum LanguageEnum {
  JAVASCRIPT= <any>'JAVASCRIPT',  HTML = <any>'HTML'
}

export interface Code {
  language: LanguageEnum;
  code: string;
  commentList: Comment[];
  score: number;
}

export interface Comment {
  id: number;
  author: EndUser;
  date: Date;
  content: string;
  replies: Comment[];
}

export interface EndUser {
  id: number;
  name: string;
}

export interface ActivityUnitTest {
  id: number;
  code: Code;
  expectedResult: string;
  performance: boolean;
  score: number;
}
