package searchmethods;

import agent.State;
import utils.NodePriorityQueue;

import java.util.List;

public class UniformCostSearch extends GraphSearch<NodePriorityQueue>{

    public UniformCostSearch(){
        frontier = new NodePriorityQueue();
    }

    @Override
    public void AddSuccessorsToFrontier(List<State> successors, Node parent) {
        for (State s : successors)
        {
            double cost = parent.GetCost() + s.GetAction().GetCost();

            if(!frontier.ContainsState(s))
            {
                if(!explored.contains(s))
                    frontier.add(new Node(s, parent, cost, cost));
            }
            else if (frontier.getNode(s).GetCost() > cost)
            {
                frontier.removeNode(s);
                frontier.add(new Node(s, parent, cost, cost));
            }
        }
    }

    @Override
    public String toString() {
        return "UniformCostSearch{" +
                "frontier=" + frontier +
                ", statistics=" + statistics +
                ", explored=" + explored +
                ", stopped=" + stopped +
                '}';
    }
}
