package gamelogic;


import boatpuzzle.Position;

public class Individual {
    private int id;
    private boolean hasCrossed;
    private IndividualType type;
    private Position position;

    public Individual(Individual individual) {
        this.id = individual.id;
        this.type = individual.GetType();
        this.hasCrossed = individual.GetHasCrossed();
        this.position = individual.GetPosition();
    }

    public Individual(int id, IndividualType type) {
        this.id = id;
        this.type = type;
        this.hasCrossed = false;
        this.position = Position.L;
    }

    public int GetId() { return this.id; }

    public void ChangePosition(){
        this.hasCrossed = !this.hasCrossed;
        this.position = (this.hasCrossed) ? Position.R : Position.L;
    }

    public Position GetPosition() { return this.position; }

    public boolean GetHasCrossed() { return this.hasCrossed; }

    public IndividualType GetType() { return this.type;}

    @Override
    public String toString() {
        return "Identity{" +
                "id=" + id +
                ", state=" + hasCrossed +
                ", type=" + type +
                '}';
    }
}
