package pt.codemaster.adt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import pt.codemaster.adt.activity.UnitTestResult;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Deliverable implements IPublisher {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private EndUser author;
    private String content;
    @ManyToOne(cascade = CascadeType.ALL)
    private Code code;
    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<UnitTestResult> result = new ArrayList<>();
    @ManyToOne
    private Solution solution;
    private boolean submitted;
    private boolean readOnly;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date submissionDate;
    @ManyToOne
    @JsonIgnore
    private ActivityInstance activityInstance;

    public Deliverable(Solution solution) {
        this.solution = solution;
    }

    public Deliverable() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public ActivityInstance getActivityInstance() {
        return activityInstance;
    }

    public void setActivityInstance(ActivityInstance activityInstance) {
        this.activityInstance = activityInstance;
    }

    @Override
    public String toString() {
        return "Deliverable{" +
                "id=" + id +
                ", author=" + author +
                ", content='" + content + '\'' +
                ", code=" + code +
                ", result=" + result +
                ", solution=" + solution +
                ", submitted=" + submitted +
                ", readOnly=" + readOnly +
                ", submissionDate=" + submissionDate +
                '}';
    }

    @Override
    public void notifySubscribers(NotificationEvent event) {
        this.activityInstance.notifySubscribers(event);
    }

    @Override
    public void subscribe(EndUser user) {
        activityInstance.subscribe(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deliverable that = (Deliverable) o;
        return submitted == that.submitted && readOnly == that.readOnly && Objects.equals(id, that.id) && Objects.equals(author, that.author) && Objects.equals(content, that.content) && Objects.equals(code, that.code) && Objects.equals(result, that.result) && Objects.equals(solution, that.solution) && Objects.equals(submissionDate, that.submissionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, content, code, result, solution, submitted, readOnly, submissionDate);
    }
}
