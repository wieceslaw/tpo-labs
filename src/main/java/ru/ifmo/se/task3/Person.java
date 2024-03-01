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
    private Location currentLocation;
    private final List<Relationship> relationships;
    private boolean alive;

    public Person(String name, Sex gender) {
        if (name == null){
            throw new IllegalArgumentException("Name cannot be null");
        }
        if (gender == null){
            throw new IllegalArgumentException("Gender cannot be null");
        }
        this.name = name;
        this.gender = gender;
        this.mood = Mood.NORMAL;
        this.relationships = new ArrayList<>();
        this.alive = true;
    }

    public void move(Location newLocation) throws NoAvailableSeatsException {
        if (newLocation == null){
            throw new IllegalArgumentException("Location cannot be null");
        }
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
        if (this == otherPerson){
            throw new IllegalArgumentException("There can be no connection between a person and himself");
        }
        if (type == null){
            throw new IllegalArgumentException("RelationshipType cannot be null");
        }
        Relationship relationship = new Relationship(this, otherPerson, type);
        relationships.add(relationship);

        // Установим взаимные отношения
        otherPerson.relationships.add(new Relationship(otherPerson, this, type));
    }

    public void breakAllRelationships(Person otherPerson) {
        boolean removed = relationships.removeIf(relationship -> relationship.getPerson2() == otherPerson);

        if (removed) {
            // Remove the relationship from the other person
            otherPerson.relationships.removeIf(relationship -> relationship.getPerson2() == this);
        } else {
            throw new IllegalArgumentException("No relationship with the specified person to break.");
        }
    }

    public Relationship getRelationshipWithPerson(Person otherPerson) {
        return relationships.stream()
                .filter(relationship -> relationship.getPerson2() == otherPerson)
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

    public void vaporize(){
        this.alive = false;
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

    public boolean isAlive() {
        return alive;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

}
