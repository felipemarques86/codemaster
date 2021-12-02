package pt.codemaster.adt;

import pt.codemaster.adt.activity.Activity;

import java.util.*;

public class ActivityInstance<A extends Activity> {
    private Long id;
    private Date startDate;
    private Date endDate;
    private A activity;
    private List<Deliverable> deliverable = new ArrayList<>();

    public ActivityInstance(A activity) {
        this.activity = activity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public A getActivity() {
        return activity;
    }

    public List<Deliverable> getDeliverable() {
        return deliverable;
    }

    public void setDeliverable(List<Deliverable> deliverable) {
        this.deliverable = deliverable;
    }
}
