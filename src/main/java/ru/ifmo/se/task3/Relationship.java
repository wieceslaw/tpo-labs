package ru.ifmo.se.task3;

/**
 * @author amifideles
 */
public class Relationship {
    private final Person person1;
    private final Person person2;
    private RelationshipType type;

    public Relationship(Person person1, Person person2, RelationshipType type) {
        this.person1 = person1;
        this.person2 = person2;
        this.type = type;
    }

    public Person getPerson1() {
        return person1;
    }

    public Person getPerson2() {
        return person2;
    }

    public RelationshipType getType() {
        return type;
    }

    public void setType(RelationshipType type) {
        this.type = type;
    }
}
