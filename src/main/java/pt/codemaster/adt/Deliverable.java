package pt.codemaster.adt;

import pt.codemaster.adt.activity.UnitTestResult;

public class Deliverable {
    private EndUser author;
    private String content;
    private Code code;
    private UnitTestResult result;
    private Solution solution;
    private boolean submitted;

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

    public UnitTestResult getResult() {
        return result;
    }

    public void setResult(UnitTestResult result) {
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
}
