package agent;

public abstract class Heuristic<P extends Problem, S extends State> {
    protected P problem;

    public Heuristic(){}

    public Heuristic(P problem) {
        this.problem = problem;
    }

    public abstract double Compute(S state);

    public P GetProblem() {
        return this.problem;
    }

    public void SetProblem(P problem) {
        this.problem = problem;
    }
}
