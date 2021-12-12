export interface Activity {
  id: number;
  name: string;
  description: string;
  activityUnitTestList: ActivityUnitTest[];
  solution: Solution[];
  bibliographicReferenceList: BibliographicReference[];
}

export interface  Solution {
  id?: number;
  testsToPass: ActivityUnitTest[];
  code: Code;
  checkOutput: boolean;
}

export enum LanguageEnum {
  JAVASCRIPT= <any>'JAVASCRIPT',  HTML = <any>'HTML'
}



export interface Comment {
  id?: number;
  author: EndUser;
  date: Date;
  content: string;
  replies: Comment[];
}

export interface EndUser {
  id: number;
  name: string;
}

export interface Code {
  id?: number;
  language: LanguageEnum;
  code: string;
  commentList: Comment[];
  score: number;
  author?: EndUser;
}

export interface ActivityUnitTest {
  id?: number;
  code: Code;
  expectedResult: string;
  performance: boolean;
  score: number;
  /*Additional transient fields*/
  passed?: boolean;
  message: string;
  global: boolean;
}

export interface BibliographicReference {
  id?: number;
  name: string;
  url: string;
}

