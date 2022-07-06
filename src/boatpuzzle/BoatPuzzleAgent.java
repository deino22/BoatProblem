package boatpuzzle;

import agent.Agent;

import java.util.ArrayList;

public class BoatPuzzleAgent extends Agent<BoatPuzzleState> {
    protected BoatPuzzleState initialEnvironment;

    public BoatPuzzleAgent(BoatPuzzleState puzzleState) {
        super(puzzleState);
        this.initialEnvironment = (BoatPuzzleState) environment.clone();
        this.heuristics = new ArrayList<>();
        this.heuristics.add(new HeuristicIndividualsNotCrossed());
        this.heuristic = this.heuristics.get(0);
    }
}
