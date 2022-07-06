package agent;

public abstract class State {
    protected Action action; // Action that generated this state

    public State() {}

    public abstract void ExecuteAction(Action action);

    public Action GetAction() { return action; }

    public void SetAction(Action action) { this.action = action; }

    @Override
    public abstract int hashCode();

    @Override
    public abstract boolean equals(Object obj);
}
