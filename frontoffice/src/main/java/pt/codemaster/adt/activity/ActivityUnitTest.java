package pt.codemaster.adt.activity;

import pt.codemaster.adt.Code;

import javax.persistence.*;

@Entity
public class ActivityUnitTest {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Code code;
    private boolean performance;
    private double score;

    public ActivityUnitTest() {
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public boolean isPerformance() {
        return performance;
    }

    public void setPerformance(boolean performance) {
        this.performance = performance;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ActivityUnitTest{" +
                "id=" + id +
                ", code=" + code +
                ", performance=" + performance +
                ", score=" + score +
                '}';
    }
}
