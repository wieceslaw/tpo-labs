package ru.ifmo.se.page;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetailedSearchFilter {
    private SearchFilter searchFilter;
    private String departureFromAirport;
    private String departureBackAirport;
    private String arrivalFromAirport;
    private String arrivalBackAirport;

}
