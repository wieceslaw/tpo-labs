package ru.ifmo.se.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author amifideles
 */
class LocationTest {
    private Location location;

    @BeforeEach
    public void setUp() {
        location = new Location("TestLocation", 10);
    }

    @Test
    public void increaseNoiseLevelTest() {
        location.increaseNoiseLevel();
        assertEquals(1, location.getNoiseLevel());
    }

    @Test
    public void decreaseNoiseLevelTest() {
        location.decreaseNoiseLevel();
        assertEquals(-1, location.getNoiseLevel());
    }

    @Test
    public void occupySeatTest() {
        assertTrue(location.occupySeat());
        assertEquals(9, location.getAvailableSeats());
    }

    @Test
    public void occupySeatNoAvailableSeatsTest() {
        // Occupy all available seats
        for (int i = 0; i < 10; i++) {
            location.occupySeat();
        }

        assertFalse(location.occupySeat());
        assertEquals(0, location.getAvailableSeats());
    }

    @Test
    public void freeSeatTest() {
        // Occupy a seat first
        location.occupySeat();
        location.freeSeat();
        assertEquals(10, location.getAvailableSeats());
    }

    @Test
    public void getNoiseLevelTest() {
        assertEquals(0, location.getNoiseLevel());
    }

    @Test
    public void getNameTest() {
        assertEquals("TestLocation", location.getName());
    }

    @Test
    public void createLocationWithNonNullNameAndNegativeSeatsTest() {
        assertThrows(IllegalArgumentException.class, () -> new Location("TestLocation", -5));
    }

    @Test
    public void createLocationWithValidParametersTest() {
        assertDoesNotThrow(() -> new Location("TestLocation", 10));
    }

    @Test
    public void createLocationWithNullNameTest() {
        assertThrows(IllegalArgumentException.class, () -> new Location(null, 10));
    }

    @Test
    public void createLocationWithNullNameAndSeatsTest() {
        assertThrows(IllegalArgumentException.class, () -> new Location(null, 0));
    }

    @Test
    public void createLocationWithNullNameAndNegativeSeatsTest() {
        assertThrows(IllegalArgumentException.class, () -> new Location(null, -5));
    }

}