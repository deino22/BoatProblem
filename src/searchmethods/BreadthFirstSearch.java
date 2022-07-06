package searchmethods;

import agent.Problem;
import agent.Solution;
import agent.State;
import utils.NodeLinkedList;

import java.util.List;

public class BreadthFirstSearch extends GraphSearch<NodeLinkedList> {

    public BreadthFirstSearch() { frontier = new NodeLinkedList(); }

    @Override
    public void AddSuccessorsToFrontier(List<State> successors, Node parent) {
        for (State successor : successors) {
            if(!frontier.ContainsState(successor) && !explored.contains(successor)) frontier.addLast(new Node(successor, parent));
        }
    }
}
