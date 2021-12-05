package pt.codemaster.adt.activity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UnitTestResult {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private ActivityUnitTest unitTest;
    private String result;
    private boolean passed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
