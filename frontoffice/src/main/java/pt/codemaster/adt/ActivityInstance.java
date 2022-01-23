package pt.codemaster.adt;

import pt.codemaster.adt.activity.Activity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class ActivityInstance implements IPublisher {
    @Id
    @GeneratedValue
    private Long id;
    private Date startDate;
    private Date endDate;
    @ManyToOne
    private Activity activity;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Deliverable> deliverable = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL)
    private List<EndUser> subscribers = new ArrayList<>();

    public ActivityInstance() {
    }

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

    public List<Deliverable> getDeliverable() {
        return deliverable;
    }

    public void setDeliverable(List<Deliverable> deliverable) {
        this.deliverable = deliverable;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public List<EndUser> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<EndUser> subscribers) {
        this.subscribers = subscribers;
    }

    @Override
    public String toString() {
        return "ActivityInstance{" +
                "id=" + id +
                ", activity=" + activity +
                '}';
    }

    @Override
    public void notifySubscribers(NotificationEvent event) {
         subscribers.forEach(endUser -> endUser.update(event));
    }

    @Override
    public void subscribe(EndUser user) {
        if(!subscribers.contains(user))
            subscribers.add(user);
    }
}
