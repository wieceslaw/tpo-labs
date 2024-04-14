package ru.ifmo.se.page;

public record Ticket(
        String cityFrom,
        String cityTo,
        String dateFrom,
        String dateBack,
        String departureAirport,
        String arrivalAirport
) {
}
