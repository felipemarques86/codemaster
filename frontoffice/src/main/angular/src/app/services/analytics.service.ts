import { Injectable } from '@angular/core';
import {ITestAnalytics} from "../models/itest-analytics";
import {ICodeEditingEvents} from "../models/icode-editing-events";

@Injectable({
  providedIn: 'root'
})
export class AnalyticsService implements ITestAnalytics, ICodeEditingEvents{

  constructor() { }
}
