package pt.codemaster.adt.activity;

import java.util.Set;

public class CollaborativeEvaluationActivity extends EvaluationActivity {
    private Set<ActivityUnitTest> activityUnitTestList;

    public Set<ActivityUnitTest> getActivityUnitTestList() {
        return activityUnitTestList;
    }

    public void setActivityUnitTestList(Set<ActivityUnitTest> activityUnitTestList) {
        this.activityUnitTestList = activityUnitTestList;
    }
}
