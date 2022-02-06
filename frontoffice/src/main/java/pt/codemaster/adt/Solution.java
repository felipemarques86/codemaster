package pt.codemaster.adt;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import pt.codemaster.adt.activity.ActivityUnitTest;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Solution {
    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ActivityUnitTest> testsToPass = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Code code;

    public Solution() {
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public boolean isEmpty() {
        return testsToPass.isEmpty();
    }

    public boolean contains(Object o) {
        return testsToPass.contains(o);
    }

    public boolean add(ActivityUnitTest activityUnitTest) {
        return testsToPass.add(activityUnitTest);
    }

    public List<ActivityUnitTest> getTestsToPass() {
        return testsToPass;
    }

    public void setTestsToPass(List<ActivityUnitTest> testsToPass) {
        this.testsToPass = testsToPass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "id=" + id +
                ", testsToPass=" + testsToPass +
                ", code=" + code +
                '}';
    }
}
