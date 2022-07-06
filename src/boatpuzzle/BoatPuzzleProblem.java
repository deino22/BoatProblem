package boatpuzzle;

import agent.Action;
import agent.Problem;
import gamelogic.Individual;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BoatPuzzleProblem extends Problem<BoatPuzzleState> {

    public BoatPuzzleProblem(BoatPuzzleState initialState) {
        super(initialState);

        this.possibleActions = new LinkedList<Action<BoatPuzzleState>>() {{
            add(new ActionMixMonster());
            add(new ActionOneMonster());
            add(new ActionTwoMonster());
            add(new ActionOnePerson());
            add(new ActionTwoPerson());
        }};
    }

    @Override
    public List<BoatPuzzleState> ExecuteActions(BoatPuzzleState state) {
        List<BoatPuzzleState> successors = new LinkedList<>();

        for (Action<BoatPuzzleState> action : this.possibleActions) {
            if(action.IsValid(state)) {
                BoatPuzzleState successor = (BoatPuzzleState) state.clone();

                action.Execute(successor);
                successors.add(successor);
            }
        }

        return successors;
    }

    @Override
    public boolean IsGoal(BoatPuzzleState state) {
       for (Individual individual : state.GetIdentities()) {
            if(!individual.GetHasCrossed()) return false;
        }

        return true;
    }

    @Override
    public double ComputePathCost(List<Action<BoatPuzzleState>> path) { return path.size(); }
}
