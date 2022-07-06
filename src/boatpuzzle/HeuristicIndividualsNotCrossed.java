package boatpuzzle;

import agent.Heuristic;

public class HeuristicIndividualsNotCrossed extends Heuristic<BoatPuzzleProblem, BoatPuzzleState> {

    @Override
    public double Compute(BoatPuzzleState state) {
        return state.GetTotalIndividualsRemaining();
    }
}
