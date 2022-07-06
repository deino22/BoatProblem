package agent;

import searchmethods.Node;

import java.util.LinkedList;
import java.util.List;

public class Solution {
    private final Problem problem;
    private final LinkedList<Action> actions;

    public Solution(Problem problem, Node goalNode) {
        this.problem = problem;
        Node node = goalNode;

        actions = new LinkedList<>();

        while (node.GetParent() != null) {
            actions.addFirst(node.GetState().GetAction());
            node = node.GetParent();
        }
    }

    public double GetCost(){
        return problem.ComputePathCost(actions);
    }

    public List<Action> GetActions() { return actions; }
}
