package pt.codemaster.adt.activity;

import java.util.HashSet;
import java.util.Set;

public abstract class Activity {
    private Long id;
    private String name;
    private String description;
    private Set<BibliographicReference> referenceSet = new HashSet<>();

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

    public Set<BibliographicReference> getReferenceSet() {
        return referenceSet;
    }

    public void setReferenceSet(Set<BibliographicReference> referenceSet) {
        this.referenceSet = referenceSet;
    }
}
