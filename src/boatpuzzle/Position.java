package boatpuzzle;

public enum Position {
    L("Left"),
    R("Right");

    private String description;

    Position(String description) {
        this.description = description;
    }
}
