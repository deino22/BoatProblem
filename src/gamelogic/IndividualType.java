package gamelogic;

public enum IndividualType {
    M("Monster"),
    P("Person");

    private String description;

    IndividualType(String description) {
    this.description = description;
    }
}
