package ru.ifmo.se.page;

import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TicketPageTest extends PageTestBase {
    TicketPage ticketPage;
    FlightsSearchPage flightsSearchPage;

    @TestWithAllDrivers
    void testGetFirstTicket(WebDriver driver) {
        SearchFilter searchFilter = new SearchFilter(
                "Санкт-Петербург (Россия)",
                "Москва (Россия)",
                LocalDate.of(2024, 5, 5),
                LocalDate.of(2024, 5, 10));
        ticketPage = new TicketPage(driver, flightsSearchPage,
                DetailedSearchFilter.builder().
                        searchFilter(searchFilter).build()
        );
        Ticket firstTicket = ticketPage.getTicketPair().get(0);
        assertEquals("Санкт-Петербург", firstTicket.cityFrom());
    }

    @TestWithAllDrivers
    void applyFilters(WebDriver driver) {
        SearchFilter searchFilter = new SearchFilter(
                "Санкт-Петербург (Россия)",
                "Москва (Россия)",
                LocalDate.of(2024, 5, 5),
                LocalDate.of(2024, 5, 10));
        ticketPage = new TicketPage(driver, flightsSearchPage,
                DetailedSearchFilter.builder().
                        searchFilter(searchFilter)
                        .arrivalFromAirport("Домодедово").build()
        );
        List<Ticket> ticketPair = ticketPage.getTicketPair();
        assertEquals("Домодедово", ticketPair.get(0).arrivalAirport());
    }

    @Override
    protected void preparePages(WebDriver driver) {
        flightsSearchPage = new FlightsSearchPage(driver);
    }
}
