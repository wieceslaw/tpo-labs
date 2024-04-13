package ru.ifmo.se.page;

import java.time.LocalDate;

public record Ticket(
        String cityFrom,
        String cityTo,
        LocalDate dateFrom,
        LocalDate dateBack
) {
}
