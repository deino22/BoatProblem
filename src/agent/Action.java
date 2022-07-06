package agent;

import gamelogic.Individual;
import gamelogic.IndividualType;

public abstract class Action<S extends State> {
    protected final IndividualType[] passengers;
    private final double cost;

    public Action(double cost, IndividualType[] passengers) {
        this.cost = cost;
        this.passengers = passengers;

    }

    public abstract void Execute(S state);
    public abstract boolean IsValid(S state);

    public IndividualType[] GetPassengers() { return passengers; }
    public double GetCost() { return cost; }
}
