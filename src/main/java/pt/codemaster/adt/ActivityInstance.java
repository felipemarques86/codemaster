package pt.codemaster.adt;

import pt.codemaster.adt.Deliverable;
import pt.codemaster.adt.activity.Activity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ActivityInstance {
    private Long id;
    private Date startDate;
    private Date endDate;
    private Activity activity;
    private Set<Deliverable> deliverable = new HashSet<>();

    public ActivityInstance(Activity activity) {
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

    public Activity getActivity() {
        return activity;
    }

    public Set<Deliverable> getDeliverable() {
        return deliverable;
    }

    public void setDeliverable(Set<Deliverable> deliverable) {
        this.deliverable = deliverable;
    }
}
