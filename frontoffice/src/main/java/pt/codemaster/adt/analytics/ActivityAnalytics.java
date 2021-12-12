package pt.codemaster.adt.analytics;

import pt.codemaster.adt.Deliverable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ActivityAnalytics extends AnalyticsNameValuePair {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Deliverable deliverable;

    public ActivityAnalytics(String name, String value) {
        super(name, value);
    }

    public ActivityAnalytics() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Deliverable getDeliverable() {
        return deliverable;
    }

    public void setDeliverable(Deliverable instance) {
        this.deliverable = instance;
    }
}
