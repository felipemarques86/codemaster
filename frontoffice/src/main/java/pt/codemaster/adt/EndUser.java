package pt.codemaster.adt;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EndUser {
    @Id
    @GeneratedValue
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
}
