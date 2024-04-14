package ru.ifmo.se.page;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SearchFilter {
    private final String cityFrom;
    private final String cityTo;
    private final LocalDate dateFrom;
    private final LocalDate dateBack;

}
