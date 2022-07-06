package agent;

import java.util.List;

public abstract class Problem<S extends State> {
    protected S initialState;
    protected List<Action<S>> possibleActions;
    protected Heuristic<Problem<S>, S> heuristic;

    public Problem(S initialState) {
        this.initialState = initialState;
    }

    public abstract List<S> ExecuteActions(S state);

    public abstract boolean IsGoal(S state);

    public double ComputePathCost(List<Action<S>> actions) {
        double cost = 0;

        for(Action<S> action : actions) {
            cost += action.GetCost();
        }

        return cost;
    }

    public State GetInitialState() { return initialState; }

    private void SetInitialState(S initialState) { this.initialState = initialState; }

    public Heuristic GetHeuristic() { return heuristic; }

    public void SetHeuristic(Heuristic heuristic) { this.heuristic = heuristic; }
}
