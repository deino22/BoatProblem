package searchmethods;

import agent.State;

public class Node implements Comparable<Node> {
    private Node parent;
    private State state;
    private double cost, f;
    private int depth;

    public Node(State state, Node parent, double cost, double f) {
        this.state = state;
        this.cost = cost;
        this.f = f;
        this.parent = parent;
        this.depth = parent.depth + 1;
    }

    public Node(State state, Node parent) { this(state, parent, 0, 0); }

    public Node(State state) { this.state = state; }

    public Node GetParent() {
        return parent;
    }

    public State GetState() { return this.state; }

    public double GetCost() { return this.cost; }

    public double GetF() { return this.f; }

    /**
     * Checks whether the State already exists within the path
    */
    public boolean isCycle(State state) {
        Node auxNode = this;
        do {
            if(state.equals(auxNode.GetState())) return true;

            auxNode = auxNode.GetParent();
        }while (auxNode != null);

        return false;
    }

    @Override
    public int compareTo(Node other) {
        return Double.compare(f, other.f);
    }
}
