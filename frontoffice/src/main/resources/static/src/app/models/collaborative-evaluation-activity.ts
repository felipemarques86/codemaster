import {ActivityUnitTest, EvaluationActivity} from "./evaluation-activity";

export interface CollaborativeEvaluationActivity extends EvaluationActivity{
  activityUnitTestList: ActivityUnitTest[];
}
