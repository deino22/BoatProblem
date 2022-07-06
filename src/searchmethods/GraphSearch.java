package searchmethods;

import agent.Problem;
import agent.Solution;
import agent.State;
import utils.NodeCollection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class GraphSearch<L extends NodeCollection> implements SearchMethod {
    protected L frontier;
    protected Statistics statistics = new Statistics();
    protected Set<State> explored = new HashSet<>();
    protected boolean stopped;

    protected Solution execute(Problem problem) {
        // Clear Previous data
        explored.clear();
        frontier.clear();

        // Add Starting Nodes
        frontier.add(new Node(problem.GetInitialState()));

        while (!frontier.isEmpty() && !stopped) {
            Node currNode = frontier.poll(); // Get First In queue

            if(problem.IsGoal(currNode.GetState())) return new Solution(problem, currNode);

            explored.add(currNode.GetState());

            List<State> successors = problem.ExecuteActions(currNode.GetState());
            this.AddSuccessorsToFrontier(successors, currNode);

            // Update UI
            ComputeStatistics(successors.size());
        }

        return null;
    }

    protected void ComputeStatistics(int successorsSize) {
        this.statistics.numExpandedNodes++;
        this.statistics.numGeneratedNodes += successorsSize;
        this.statistics.maxFrontierSize = Math.max(statistics.maxFrontierSize, frontier.size());
    }

    @Override
    public Statistics GetStatistics() { return this.statistics; }

    @Override
    public Solution Search(Problem problem) {
        statistics.Reset();
        return execute(problem);
    }

    @Override
    public void Stop(){
        this.stopped = true;
    }

    @Override
    public boolean HasBeenStopped() {
        return stopped;
    }

    public abstract void AddSuccessorsToFrontier(List<State> successors, Node parent);
}
