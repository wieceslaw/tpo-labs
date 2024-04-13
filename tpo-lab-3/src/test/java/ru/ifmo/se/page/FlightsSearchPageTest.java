package ru.ifmo.se.page;

import org.openqa.selenium.WebDriver;

import java.time.LocalDate;

class FlightsSearchPageTest extends PageTestBase {
    FlightsSearchPage flightsSearchPage;

    @TestWithAllDrivers
    void testFindFlights(WebDriver driver) {
        flightsSearchPage.findFlights(
                "Санкт-Петербург",
                "Москва",
                LocalDate.of(2024, 5, 5),
                LocalDate.of(2024, 5, 10)
        );
    }

    @Override
    protected void preparePages(WebDriver driver) {
        flightsSearchPage = new FlightsSearchPage(driver);
    }
}
