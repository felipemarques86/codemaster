package pt.codemaster.adt;

import pt.codemaster.adt.activity.ActivityUnitTest;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    private Long id;
    private Set<ActivityUnitTest> testsToPass = new HashSet<>();
    private Code code;
    private boolean checkOutput;

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public boolean isEmpty() {
        return testsToPass.isEmpty();
    }

    public boolean contains(Object o) {
        return testsToPass.contains(o);
    }

    public boolean add(ActivityUnitTest activityUnitTest) {
        return testsToPass.add(activityUnitTest);
    }

    public Set<ActivityUnitTest> getTestsToPass() {
        return testsToPass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isCheckOutput() {
        return checkOutput;
    }

    public void setCheckOutput(boolean checkOutput) {
        this.checkOutput = checkOutput;
    }
}
