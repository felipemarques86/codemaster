import {Activity} from "./evaluation-activity";

export interface IActivityConfiguration {
  saveActivity(activity: Activity): void;

  getActivity(): Activity;

  validate(activity: Activity): any;
}
