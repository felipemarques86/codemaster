package pt.codemaster.adt.activity;

import pt.codemaster.adt.Solution;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Activity {
    @Id
    private Long id;
    private String name;
    private String description;
    @OneToMany(cascade = CascadeType.ALL)
    private List<BibliographicReference> bibliographicReferenceList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    private List<Solution> solution = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<BibliographicReference> getBibliographicReferenceList() {
        return bibliographicReferenceList;
    }

    public void setBibliographicReferenceList(List<BibliographicReference> referenceSet) {
        this.bibliographicReferenceList = referenceSet;
    }

    public List<Solution> getSolution() {
        return solution;
    }

    public void setSolution(List<Solution> solution) {
        this.solution = solution;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", referenceSet=" + bibliographicReferenceList +
                ", solution=" + solution +
                '}';
    }
}
