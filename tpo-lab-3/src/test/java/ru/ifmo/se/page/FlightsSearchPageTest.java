package ru.ifmo.se.page;

import org.openqa.selenium.WebDriver;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FlightsSearchPageTest extends PageTestBase {
    FlightsSearchPage flightsSearchPage;

    @TestWithAllDrivers
    void testFindFlights(WebDriver driver) {
        flightsSearchPage.findFlights(
                new SearchFilter("Санкт-Петербург",
                        "Москва",
                        LocalDate.of(2024, 5, 5),
                        LocalDate.of(2024, 5, 10))
        );
    }

    @TestWithAllDrivers
    void testFindFlightsWithEmptyCity(WebDriver driver) {
        flightsSearchPage.findFlights(
                new SearchFilter(
                        "", // Пустой город
                        "Москва",
                        LocalDate.of(2024, 5, 5),
                        LocalDate.of(2024, 5, 10))
        );
        assertTrue(flightsSearchPage.hasNoCityDepartureError());
        // Здесь добавьте проверки на то, что система должна предупредить об ошибке ввода.
    }

    @TestWithAllDrivers
    void testFindFlightsWithNonexistentCity(WebDriver driver) {
        flightsSearchPage.findFlights(
                new SearchFilter(
                        "Санкт-Петербург",
                        "Несуществующий город", // Несуществующий город
                        LocalDate.of(2024, 5, 5),
                        LocalDate.of(2024, 5, 10))
        );
        assertTrue(flightsSearchPage.hasNoCityArrivalError());
        // Здесь добавьте проверки на то, что система должна предупредить об ошибке ввода.
    }

    @TestWithAllDrivers
    void testFindFlightsWithIncorrectDate(WebDriver driver) {
        flightsSearchPage.findFlights(
                new SearchFilter(
                        "Санкт-Петербург",
                        "Москва",
                        LocalDate.of(2024, 5, 5),
                        LocalDate.of(2024, 4, 10))// Неправильная дата
        );
        assertTrue(flightsSearchPage.hasIncorrectDepartureDateError());
        // Здесь добавьте проверки на то, что система должна предупредить об ошибке ввода.
    }

    @TestWithAllDrivers
    void testFindFlightsWithSameCities(WebDriver driver) {
        flightsSearchPage.findFlights(
                new SearchFilter(
                        "Москва", // Города совпадают
                        "Москва",
                        LocalDate.of(2024, 5, 5),
                        LocalDate.of(2024, 5, 10)
                )
        );
        assertTrue(flightsSearchPage.hasSameCityError());
        // Здесь добавьте проверки на то, что система должна предупредить об ошибке ввода.
    }

    @Override
    protected void preparePages(WebDriver driver) {
        flightsSearchPage = new FlightsSearchPage(driver);
    }
}
