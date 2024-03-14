package ru.ifmo.se.task3;

/**
 * @author amifideles
 */

public class Location {
    private final String name;
    private int noiseLevel;
    private int availableSeats;

    public Location(String name, int availableSeats) {
        if (name == null){
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.name = name;
        if (availableSeats < 0){
            throw new IllegalArgumentException("Number of seats cannot be negative");
        }
        this.availableSeats = availableSeats;
        noiseLevel = 0;
    }

    public void increaseNoiseLevel() {
        noiseLevel++;
    }

    public void decreaseNoiseLevel() {
        noiseLevel--;
    }

    public boolean occupySeat() {
        if (availableSeats > 0) {
            availableSeats--;
            return true;
        } else {
            return false;
        }
    }

    public void freeSeat() {
        availableSeats++;
    }

    public int getNoiseLevel() {
        return noiseLevel;
    }

    public String getName() {
        return name;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }
}
