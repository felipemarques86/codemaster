package pt.codemaster.adt;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EndUser {
    @Id
    private Long id;
    private String name;

    public EndUser(Long id) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "EndUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
