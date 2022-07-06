package boatpuzzle;

import agent.Action;
import agent.State;
import gamelogic.Individual;
import gamelogic.IndividualType;

import java.util.ArrayList;
import java.util.Arrays;

public class BoatPuzzleState extends State implements Cloneable {
    private static final int _MAX_PASSENGERS = 2;
    private Individual[] individuals;
    private int individualsCrossed;
    private Position boatPosition;

    public BoatPuzzleState(Individual[] vector) {
        this(vector, 0, Position.L);
    }

    public BoatPuzzleState(Individual[] ind, int individualsCrossed, Position boatPosition) {
        this.individuals = new Individual[ind.length];
        for (int i = 0; i < ind.length; i++) {
            this.individuals[i] = new Individual(ind[i]);
        }

//        this.individuals = Arrays.copyOf(ind, ind.length);
        this.individualsCrossed = individualsCrossed;
        this.boatPosition = boatPosition;
    }

    public void MoveIdentity(Action action){
        ArrayList<Individual> passengers = new ArrayList<>();

        for (IndividualType type : action.GetPassengers()) {
            for (Individual ind : individuals) {
                if(ind.GetPosition() != boatPosition) continue;

                if(!passengers.contains(ind) && ind.GetType() == type) {
                    ind.ChangePosition();
                    passengers.add(ind);
                    break;
                }
            }

        }

        this.boatPosition = (boatPosition == Position.R) ? Position.L : Position.R;
        this.ComputeIndividualsCrossed();
    }

    public boolean CheckRule(IndividualType[] types) {
        int totalMonstersDestination = 0, totalPersonsDestination = 0;
        int totalRemainingPersons = 0, totalRemainingMonsters = 0;

        int totalIndividualsDestination = 0, totalIndividualsRemaining = 0;

        for (Individual individual : individuals) {
            if(individual.GetPosition() != boatPosition) {
                switch (individual.GetType()) {
                    case M -> totalMonstersDestination++;
                    case P -> totalPersonsDestination++;
                }
            }
            else {
                switch (individual.GetType()) {
                    case M -> totalRemainingMonsters++;
                    case P -> totalRemainingPersons++;
                }
            }

        }

        int monstersOnBoat = 0, personsOnBoat = 0;
        for (IndividualType type : types){
            switch (type) {
                case M -> monstersOnBoat++;
                case P -> personsOnBoat++;
            }
        }

        return totalPersonsDestination + personsOnBoat == 0
                || totalRemainingPersons - personsOnBoat == 0
                || (totalRemainingPersons - personsOnBoat >= totalRemainingMonsters - monstersOnBoat
                        && totalPersonsDestination + personsOnBoat >= totalMonstersDestination + monstersOnBoat);

//                || (totalPersonsDestination > 0 &&
//                        && (totalRemainingPersons - personsOnBoat >= totalRemainingMonsters - monstersOnBoat));


//        int totalIdentities = this.individuals.length;
//        int totalMonstersRight = 0, totalPersonsRight = 0, totalPersonsLeft = 0, totalMonstersLeft = 0;
//
//        for (Individual individual : individuals) {
//            if(individual.GetType().equals(IndividualType.M) && individual.GetHasCrossed()) totalMonstersRight++;
//            if(individual.GetType().equals(IndividualType.P) && individual.GetHasCrossed()) totalPersonsRight++;
//        }
//
//        totalPersonsLeft = totalIdentities - totalPersonsRight;
//        totalMonstersLeft = totalIdentities - totalMonstersRight;
//
//        return (totalPersonsLeft >= totalMonstersLeft && totalPersonsLeft > 0) ||
//                (totalPersonsRight >= totalMonstersRight && totalPersonsRight > 0);
    }

    public boolean CheckNumberOfPassengers(int totalPassengers, IndividualType[] types) {
        if(totalPassengers > _MAX_PASSENGERS || totalPassengers <= 0) return false;
        ArrayList<Individual> passengers = new ArrayList<>();

        for (int i = 0; i < totalPassengers; i++) {
            IndividualType type = types[i];

            for (Individual ind : this.individuals) {
                if(ind.GetPosition() != boatPosition) continue;

                if(!passengers.contains(ind) && ind.GetType() == type) {
                    passengers.add(ind);
                    break;
                }
            }
        }

        return (passengers.size() == totalPassengers);
    }

    @Override
    public void ExecuteAction(Action action) {
        action.Execute(this);
        this.ComputeIndividualsCrossed();
        this.FirePuzzleChanged(null);
    }

    @Override
    public int hashCode() {
        return 97 * 7 + Arrays.deepHashCode(this.individuals) + boatPosition.hashCode() + individualsCrossed;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof BoatPuzzleState)) {
            return false;
        }

        BoatPuzzleState o = (BoatPuzzleState) other;
        if (individuals.length != o.individuals.length) {
            return false;
        }

        return Arrays.deepEquals(individuals, o.individuals);
    }

    @Override
    public Object clone() {
        return new BoatPuzzleState(individuals, individualsCrossed, boatPosition);
    }

    // Gets & Sets
    public Individual GetIdentityAt(int index){
        return individuals[index];
    }

    public Individual[] GetIdentities() { return this.individuals; }

    public int ComputeIndividualsCrossed() {
        this.individualsCrossed = 0;
        for (Individual i : individuals) if(i.GetHasCrossed()) this.individualsCrossed++;
        return this.individualsCrossed;
    }

    public int GetTotalIndividuals() { return this.individuals.length; }

    public int GetTotalIndividualsCrossed() { return this.individualsCrossed; }

    public int GetTotalIndividualsRemaining() { return this.GetTotalIndividuals() - this.GetTotalIndividualsCrossed(); }

    // Listeners
    private transient ArrayList<BoatPuzzleListener> listeners = new ArrayList<>(3);

    public synchronized void RemoveListener(BoatPuzzleListener l) {
        if(listeners != null) listeners.remove(l);
    }

    public synchronized void AddListener(BoatPuzzleListener l) {
        if(!listeners.contains(l)) listeners.add(l);
    }

    public void FirePuzzleChanged(BoatPuzzleEvent pe) {
        for (BoatPuzzleListener listener : listeners) listener.PuzzleChanged(null);
    }
}
