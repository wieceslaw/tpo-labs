package ru.ifmo.se.task3;

import ru.ifmo.se.task3.exceptions.NoAvailableSeatsException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author amifideles
 */
public class Person {
    private final String name;
    private final Sex gender;
    private Position position;
    private Mood mood;
    private Location currentLocation; // текущая локация
    private final List<Relationship> relationships;

    public Person(String name, Sex gender) {
        this.name = name;
        this.gender = gender;
        this.mood = Mood.NORMAL;
        this.relationships = new ArrayList<>();
    }

    public void move(Location newLocation) throws NoAvailableSeatsException {
        if (newLocation.occupySeat()) {
            if (currentLocation != null) {
                currentLocation.freeSeat(); // Освобождаем место в текущей локации
            }
            this.currentLocation = newLocation;
        } else {
            throw new NoAvailableSeatsException("Нет свободных мест в локации " + newLocation.getName());
        }
    }

    public void createRelationship(Person otherPerson, RelationshipType type) {
        Relationship relationship = new Relationship(this, otherPerson, type);
        relationships.add(relationship);

        // Установим взаимные отношения
        otherPerson.relationships.add(new Relationship(otherPerson, this, type));
    }

    // TODO Может быть это сойдет за "испарение" ?
    public void breakRelationship(Person otherPerson) {
        if (relationships.removeIf(relationship -> relationship.getPerson2() == otherPerson)) {
            // Удалим взаимные отношения
            otherPerson.relationships.removeIf(relationship -> relationship.getPerson2() == this);
        }
    }

    public RelationshipType getRelationshipTypeWithPerson(Person otherPerson) {
        return relationships.stream()
                .filter(relationship -> relationship.getPerson2() == otherPerson)
                .map(Relationship::getType)
                .findFirst()
                .orElse(null);
    }

    public void laugh() {
        mood = Mood.HAPPY;
        currentLocation.increaseNoiseLevel();
    }

    public void sit() {
        position = Position.SITTING;
    }

    public void stand() {
        position = Position.STANDING;
    }

    public String getName() {
        return name;
    }

    public Sex getGender() {
        return gender;
    }

    public Position getPosition() {
        return position;
    }

    public Mood getMood() {
        return mood;
    }
}
