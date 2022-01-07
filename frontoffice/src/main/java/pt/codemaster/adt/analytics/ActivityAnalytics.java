package pt.codemaster.adt.analytics;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pt.codemaster.adt.Deliverable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@JsonSerialize(as=INameValuePair.class)
@Entity
public class ActivityAnalytics implements INameValuePair {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Deliverable deliverable;
    private String name;
    private String value;

    public ActivityAnalytics(String name, String value) {
        this.name = name;
        this.value = value;
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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ActivityAnalytics{" +
                "id=" + id +
                ", deliverable=" + deliverable +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
