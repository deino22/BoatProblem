package boatpuzzle;

import java.util.EventObject;

public class BoatPuzzleEvent extends EventObject {
    public BoatPuzzleEvent(BoatPuzzleState source) { super(source); }
}
