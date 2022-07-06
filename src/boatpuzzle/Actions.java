package boatpuzzle;

import agent.Action;
import gamelogic.Individual;
import gamelogic.IndividualType;


abstract class ActionBoatPuzzle extends Action<BoatPuzzleState> {

    public ActionBoatPuzzle(double cost, IndividualType[] passengers) {
        super(cost, passengers);
    }

    @Override
    public void Execute(BoatPuzzleState state) {
        state.MoveIdentity(this);
        state.SetAction(this);
    }

    @Override
    public boolean IsValid(BoatPuzzleState state) {
        return state.CheckNumberOfPassengers(this.passengers.length, this.passengers) && state.CheckRule(this.passengers);
    }
}



class ActionMixMonster extends ActionBoatPuzzle {
    private static final IndividualType[] _PASSENGERS_TYPE = new IndividualType[]{
            IndividualType.M,
            IndividualType.P
    };
    //    private Individual individual;
    public ActionMixMonster() { super(1, _PASSENGERS_TYPE); };

    @Override
    public int hashCode() { return 1; }
}

class ActionOneMonster extends ActionBoatPuzzle {
    private static final IndividualType[] _PASSENGERS_TYPE = new IndividualType[]{
            IndividualType.M
    };
//    private Individual individual;
    public ActionOneMonster() { super(1, _PASSENGERS_TYPE); };

    @Override
    public int hashCode() { return 2; }
}

class ActionTwoMonster extends ActionBoatPuzzle {
    private static final IndividualType[] _PASSENGERS_TYPE = new IndividualType[]{
            IndividualType.M,
            IndividualType.M
    };
    private Individual Individual;

    public ActionTwoMonster() {super(1, _PASSENGERS_TYPE); };

    @Override
    public int hashCode() { return 3; }
}

class ActionOnePerson extends ActionBoatPuzzle {
    private static final IndividualType[] _PASSENGERS_TYPE = new IndividualType[]{
            IndividualType.P
    };
    private Individual Individual;

    public ActionOnePerson() { super(1, _PASSENGERS_TYPE); };

    @Override
    public int hashCode() { return 4; }
}

class ActionTwoPerson extends ActionBoatPuzzle {
    private static final IndividualType[] _PASSENGERS_TYPE = new IndividualType[]{
            IndividualType.P,
            IndividualType.P
    };
    private Individual Individual;

    public ActionTwoPerson() { super(1, _PASSENGERS_TYPE); };

    @Override
    public int hashCode() { return 5; }

}
