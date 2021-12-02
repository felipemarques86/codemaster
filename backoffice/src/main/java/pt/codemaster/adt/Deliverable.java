package pt.codemaster.adt;

import pt.codemaster.adt.activity.UnitTestResult;

import java.util.ArrayList;
import java.util.List;

public class Deliverable {
    private EndUser author;
    private String content;
    private Code code;
    private List<UnitTestResult> result = new ArrayList<>();
    private Solution solution;
    private boolean submitted;
    private boolean readOnly;

    public Deliverable(Solution solution) {
        this.solution = solution;
    }

    public EndUser getAuthor() {
        return author;
    }

    public void setAuthor(EndUser author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public List<UnitTestResult> getResult() {
        return result;
    }

    public void setResult(List<UnitTestResult> result) {
        this.result = result;
    }

    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }



}
