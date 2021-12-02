package pt.codemaster.adt.activity;

import pt.codemaster.adt.Solution;

import java.util.HashSet;
import java.util.Set;

public class EvaluationActivity extends Activity {
    private Set<Solution> solution = new HashSet<>();
    private double score;

    public Set<Solution> getSolution() {
        return solution;
    }

    public void setSolution(Set<Solution> solution) {
        this.solution = solution;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
