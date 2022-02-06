package pt.codemaster.adt;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class EndUser implements ISubscriber {
    @Id
    private String id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Notification> notifications = new ArrayList<>();

    public EndUser(String id) {
        this.id = id;
    }

    public EndUser() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    @Override
    public String toString() {
        return "EndUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public void update(NotificationEvent event) {
        Notification notification = new Notification();
        notification.setContent(event.getMessage());
        notifications.add(notification);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EndUser endUser = (EndUser) o;
        return Objects.equals(id, endUser.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
