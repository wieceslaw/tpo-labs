package ru.ifmo.se.task3.exceptions;

/**
 * @author amifideles
 */
public class NoAvailableSeatsException extends Exception {
    public NoAvailableSeatsException(String message) {
        super(message);
    }
}