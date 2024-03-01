package ru.ifmo.se.task3;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ifmo.se.task3.exceptions.NoAvailableSeatsException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author amifideles
 */
class PersonTest {
    private Person person1;
    private Person person2;
    private Location locationWithSeats;
    private Location locationWithoutSeats;

    @BeforeEach
    void setUp() {
        person1 = new Person("Alice", Sex.FEMALE);
        person2 = new Person("Bob", Sex.MALE);
        locationWithSeats = new Location("Cafe", 2);
        locationWithoutSeats = new Location("Library", 0);
    }

    @Test
    void testNullName() {
        assertThrows(IllegalArgumentException.class, () -> new Person(null, Sex.MALE));
    }


    @Test
    void testNullGender() {
        assertThrows(IllegalArgumentException.class, () -> new Person("jon", null));
    }

    @Test
    void testSetUp() {
        assertEquals("Alice", person1.getName());
        assertEquals(Sex.FEMALE, person1.getGender());
        assertEquals("Bob", person2.getName());
        assertEquals(Sex.MALE, person2.getGender());
    }

    @Test
    void moveTest() throws NoAvailableSeatsException {
        person1.move(locationWithSeats);
        assertEquals(locationWithSeats, person1.getCurrentLocation());
    }

    @Test
    void moveNullLocationTest() throws NoAvailableSeatsException {
        assertThrows(IllegalArgumentException.class, () -> person1.move(null));
    }


    @Test
    void moveSamePlaceTest() throws NoAvailableSeatsException {
        person1.move(locationWithSeats);
        person1.move(locationWithSeats);
        assertEquals(locationWithSeats, person1.getCurrentLocation());
    }

    @Test
    void moveNoAvailableSeatsTest() {
        assertThrows(NoAvailableSeatsException.class, () -> person1.move(locationWithoutSeats));
        assertNull(person1.getCurrentLocation());
    }

    @Test
    void createRelationshipTest() {
        person1.createRelationship(person2, RelationshipType.FRIEND);
        Relationship relationship = person1.getRelationshipWithPerson(person2);
        assertEquals(person1, relationship.getPerson1());
        assertEquals(person2, relationship.getPerson2());
        assertEquals(RelationshipType.FRIEND, relationship.getType());
    }

    @Test
    void createSelfRelationshipTest() {
        assertThrows(IllegalArgumentException.class, () -> person1.createRelationship(person1, RelationshipType.FRIEND));
    }

    @Test
    void createNullRelationshipTypeTest() {
        assertThrows(IllegalArgumentException.class, () -> person1.createRelationship(person2, null));
    }

    @Test
    void breakRelationshipTest() {
        // Ensure that no relationship exists initially
        assertNull(person1.getRelationshipWithPerson(person2));
        assertNull(person2.getRelationshipWithPerson(person1));

        // Attempt to break a non-existent relationship
        assertThrows(IllegalArgumentException.class, () -> person1.breakAllRelationships(person2));

        // Create a relationship and then break it
        person1.createRelationship(person2, RelationshipType.FRIEND);
        person1.breakAllRelationships(person2);

        // Ensure that the relationship is successfully broken
        assertNull(person1.getRelationshipWithPerson(person2));
        assertNull(person2.getRelationshipWithPerson(person1));
    }

    @Test
    void breakRelationshipTest2() {
        // Ensure that no relationship exists initially
        Person jon = new Person("Jon", Sex.MALE);
        assertNull(person1.getRelationshipWithPerson(person2));
        assertNull(person2.getRelationshipWithPerson(person1));

        // Attempt to break a non-existent relationship
        assertThrows(IllegalArgumentException.class, () -> person1.breakAllRelationships(person2));

        // Create a relationship and then break it
        person1.createRelationship(person2, RelationshipType.FRIEND);
        person1.createRelationship(jon, RelationshipType.FRIEND);
        person2.createRelationship(jon, RelationshipType.COLLEAGUE);
        person1.breakAllRelationships(person2);

        // Ensure that the relationship is successfully broken
        assertNull(person1.getRelationshipWithPerson(person2));
        assertNull(person2.getRelationshipWithPerson(person1));
        assertEquals(RelationshipType.FRIEND, person1.getRelationshipWithPerson(jon).getType());
    }

    @Test
    void breakRelationshipWithMultipleRelationshipsTest() {
        // Ensure that no relationship exists initially
        assertNull(person1.getRelationshipWithPerson(person2));
        assertNull(person2.getRelationshipWithPerson(person1));

        // Create multiple relationships
        person1.createRelationship(person2, RelationshipType.FRIEND);
        person1.createRelationship(person2, RelationshipType.COLLEAGUE);

        // Break one of the relationships
        person1.breakAllRelationships(person2);

        // Ensure that the broken relationship is removed
        assertNull(person1.getRelationshipWithPerson(person2));
        assertNull(person2.getRelationshipWithPerson(person1));
    }

    @Test
    void getRelationshipTypeWithPersonTest() {
        person1.createRelationship(person2, RelationshipType.FRIEND);
        assertEquals(RelationshipType.FRIEND, person1.getRelationshipWithPerson(person2).getType());
    }

    @SneakyThrows
    @Test
    void laughTest() {
        person1.move(locationWithSeats);
        person1.laugh();
        assertEquals(Mood.HAPPY, person1.getMood());
        assertEquals(1, locationWithSeats.getNoiseLevel());
    }

    @Test
    void sitTest() {
        person1.sit();
        assertEquals(Position.SITTING, person1.getPosition());
    }

    @Test
    void standTest() {
        person1.stand();
        assertEquals(Position.STANDING, person1.getPosition());
    }

    @Test
    void vaporizeTest() {
        person1.vaporize();
        assertFalse(person1.isAlive());
    }
}