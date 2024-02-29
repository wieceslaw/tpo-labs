package ru.ifmo.se.task3;

/**
 * @author amifideles
 */

public class Location {
    private final String name;
    private int noiseLevel;
    private int availableSeats;
    public Location(String name) {
        this.name = name;
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

}
