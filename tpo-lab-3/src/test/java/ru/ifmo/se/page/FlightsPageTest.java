package ru.ifmo.se.page;

import org.openqa.selenium.WebDriver;

import java.time.LocalDate;

class FlightsPageTest extends PageTestBase {
    FlightsPage flightsPage;
    FlightsSearchPage flightsSearchPage;

    @TestWithAllDrivers
    void testGetFirstTicket(WebDriver driver) {
        Ticket firstTicket = flightsPage.getFirstTicket();
        assert firstTicket.cityFrom().equals("Санкт-Петербург");
    }

    @Override
    protected void preparePages(WebDriver driver) {
        flightsSearchPage = new FlightsSearchPage(driver);
        flightsPage = new FlightsPage(driver, flightsSearchPage,
                "Санкт-Петербург (Россия)",
                "Москва (Россия)",
                LocalDate.of(2024, 5, 5),
                LocalDate.of(2024, 5, 10)
        );
    }
}
