package ru.ifmo.se.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

public class FlightsPage extends Page {
    private static final String TICKETS_XPATH = "//div[@data-ti=\"offer-card-item\"]";

    public static final String ROUTE_TO_XPATH = "//div[@data-ti=\"route-0\"]";
    public static final String ROUTE_FROM_XPATH = "//div[@data-ti=\"route-1\"]";

    public static final String CITY_FROM_XPATH = "//div[@data-ti=\"departure\"]/span[@data-ti=\"city\"]";
    public static final String CITY_TO_XPATH = "//div[@data-ti=\"arrival\"]/span[@data-ti=\"city\"]";

    public static final String DEPARTURE_DATE_XPATH = "//div[@data-ti=\"departure\"]/span[@data-ti=\"date\"]";

    public FlightsPage(
            WebDriver driver,
            FlightsSearchPage searchPage,
            String cityFrom,
            String cityTo,
            LocalDate dateFrom,
            LocalDate dateBack
    ) {
        super(driver);
        searchPage.findFlights(cityFrom, cityTo, dateFrom, dateBack);
    }

    private Optional<WebElement> findTicketByIndex(int idx) {
        return findOptionalElement(By.xpath("(%s)[%s]".formatted(TICKETS_XPATH, idx + 1)));
    }

    public Ticket getFirstTicket() {
        Optional<WebElement> ticketElementOpt = findTicketByIndex(0);
        if (ticketElementOpt.isEmpty()) {
            return null;
        }
        WebElement ticketElement = ticketElementOpt.get();
        WebElement routeFromElement = ticketElement.findElement(By.xpath(ROUTE_FROM_XPATH));
        WebElement routeToElement = ticketElement.findElement(By.xpath(ROUTE_TO_XPATH));

        String cityFrom = routeFromElement.findElement(By.xpath(CITY_FROM_XPATH)).getText();
        String cityTo = routeFromElement.findElement(By.xpath(CITY_TO_XPATH)).getText();

        String dateFrom = routeFromElement.findElement(By.xpath(DEPARTURE_DATE_XPATH)).getText();
        String dateBack = routeToElement.findElement(By.xpath(DEPARTURE_DATE_XPATH)).getText();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM, E", new Locale("ru"));
        return new Ticket(
                cityFrom,
                cityTo,
                LocalDate.parse(dateFrom, formatter),
                LocalDate.parse(dateBack, formatter)
        );
    }
}
