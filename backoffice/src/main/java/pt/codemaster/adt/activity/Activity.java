package pt.codemaster.adt.activity;

import pt.codemaster.adt.Solution;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Activity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    @OneToMany(cascade = CascadeType.ALL)
    private List<BibliographicReference> referenceSet = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    private List<Solution> solution = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    private List<ActivityUnitTest> activityUnitTestList = new ArrayList<>();
    private double score;

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

    public List<BibliographicReference> getReferenceSet() {
        return referenceSet;
    }

    public void setReferenceSet(List<BibliographicReference> referenceSet) {
        this.referenceSet = referenceSet;
    }

    public List<Solution> getSolution() {
        return solution;
    }

    public void setSolution(List<Solution> solution) {
        this.solution = solution;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public List<ActivityUnitTest> getActivityUnitTestList() {
        return activityUnitTestList;
    }

    public void setActivityUnitTestList(List<ActivityUnitTest> activityUnitTestList) {
        this.activityUnitTestList = activityUnitTestList;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", referenceSet=" + referenceSet +
                ", solution=" + solution +
                ", activityUnitTestList=" + activityUnitTestList +
                ", score=" + score +
                '}';
    }
}
