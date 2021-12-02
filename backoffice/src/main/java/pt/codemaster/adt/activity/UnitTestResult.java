package pt.codemaster.adt.activity;

public class UnitTestResult {
    private ActivityUnitTest unitTest;
    private String result;
    private boolean passed;

    public ActivityUnitTest getUnitTest() {
        return unitTest;
    }

    public void setUnitTest(ActivityUnitTest unitTest) {
        this.unitTest = unitTest;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}
