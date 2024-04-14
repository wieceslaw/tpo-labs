package ru.ifmo.se.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Optional;

public class TicketPage extends Page {
    private static final String TICKETS_XPATH = "//div[@data-ti=\"offer-card-item\"]";
    private static final String ROUTE_FROM_XPATH = "//div[@data-ti=\"route-0\"]";
    private static final String ROUTE_BACK_XPATH = "//div[@data-ti=\"route-1\"]";
    private static final String CITY_FROM_XPATH = "//div[@data-ti=\"departure\"]/span[@data-ti=\"city\"]";
    private static final String CITY_TO_XPATH = "//div[@data-ti=\"arrival\"]/span[@data-ti=\"city\"]";
    private static final String AIRPORT_DEPARTURE_XPATH = "//div[@data-ti=\"departure\"]/span[@data-ti=\"place\"]";
    private static final String AIRPORT_ARRIVAL_XPATH = "//div[@data-ti=\"arrival\"]/span[@data-ti=\"place\"]";
    private static final String DEPARTURE_DATE_XPATH = "//div[@data-ti=\"departure\"]/span[@data-ti=\"date\"]";
    private static final String ARRIVAL_DATE_XPATH = "//div[@data-ti=\"arrival\"]/span[@data-ti=\"date\"]";
    private static final String DEPARTURE_FROM_AIRPORT_XPATH = "//span[@data-ti=\"order-popper-title\"]/parent::div/parent::div/following-sibling::div[1]/div[3]/div[1]";
    private static final String ARRIVAL_FROM_AIRPORT_XPATH = "//span[@data-ti=\"order-popper-title\"]/parent::div/parent::div/following-sibling::div[1]/div[3]/div[2]";
    private static final String DEPARTURE_BACK_AIRPORT_XPATH = "//span[@data-ti=\"order-popper-title\"]/parent::div/parent::div/following-sibling::div[1]/div[4]/div[1]";
    private static final String ARRIVAL_BACK_AIRPORT_XPATH = "//span[@data-ti=\"order-popper-title\"]/parent::div/parent::div/following-sibling::div[1]/div[4]/div[2]";
    private static final String AIRPORT_LABELS_XPATH = "div[2]/div[@data-ti=\"filter\"]/div/label";
    private static final String AIRPORT_NAME_XPATH = "div[1]";
    private static final String SEARCH_BUTTON_XPATH = "//div[@class='o-popper-footer']//span[contains(text(), 'Показать')]/../..";
    @FindBy(xpath = "//button[@data-ti='order-button'][1]")
    WebElement filterButton;

    public TicketPage(
            WebDriver driver,
            FlightsSearchPage searchPage,
            DetailedSearchFilter detailedSearchFilter
    ) {
        super(driver);
        searchPage.findFlights(detailedSearchFilter.getSearchFilter());
        applyFilters(detailedSearchFilter);
    }

    private Optional<WebElement> findTicketByIndex(int idx) {
        return findOptionalElement(By.xpath("(%s)[%s]".formatted(TICKETS_XPATH, idx + 1)));
    }

    public List<Ticket> getTicketPair() {
        return List.of(getTicket(ROUTE_FROM_XPATH), getTicket(ROUTE_BACK_XPATH));
    }

    public Ticket getTicket(String ROUTE_XPATH) {
        Optional<WebElement> ticketElementOpt = findTicketByIndex(0);
        if (ticketElementOpt.isEmpty()) {
            return null;
        }
        WebElement ticketElement = ticketElementOpt.get();
        WebElement routeFromElement = ticketElement.findElement(By.xpath(ROUTE_XPATH));

        String cityFrom = routeFromElement.findElement(By.xpath(CITY_FROM_XPATH)).getText();
        String cityTo = routeFromElement.findElement(By.xpath(CITY_TO_XPATH)).getText();

        String dateFrom = routeFromElement.findElement(By.xpath(DEPARTURE_DATE_XPATH)).getText();
        String dateBack = routeFromElement.findElement(By.xpath(ARRIVAL_DATE_XPATH)).getText();

        String departureAirport = routeFromElement.findElement(By.xpath(AIRPORT_DEPARTURE_XPATH)).getText();
        String arrivalAirport = routeFromElement.findElement(By.xpath(AIRPORT_ARRIVAL_XPATH)).getText();

        return new Ticket(
                cityFrom,
                cityTo,
                dateFrom,
                dateBack,
                departureAirport,
                arrivalAirport
        );
    }


    private void applyFilters(DetailedSearchFilter detailedSearchFilter) {
        filterButton.click();
        selectAirports(detailedSearchFilter);
        getDriver().findElement(By.xpath(SEARCH_BUTTON_XPATH)).click();
    }

    private void selectAirports(DetailedSearchFilter detailedSearchFilter) {
        if (detailedSearchFilter.getDepartureFromAirport() != null) {
            selectAirport(detailedSearchFilter.getDepartureFromAirport(), DEPARTURE_FROM_AIRPORT_XPATH);
        }
        if (detailedSearchFilter.getDepartureBackAirport() != null) {
            selectAirport(detailedSearchFilter.getDepartureBackAirport(), DEPARTURE_BACK_AIRPORT_XPATH);
        }
        if (detailedSearchFilter.getArrivalFromAirport() != null) {
            selectAirport(detailedSearchFilter.getArrivalFromAirport(), ARRIVAL_FROM_AIRPORT_XPATH);
        }
        if (detailedSearchFilter.getArrivalBackAirport() != null) {
            selectAirport(detailedSearchFilter.getArrivalBackAirport(), ARRIVAL_BACK_AIRPORT_XPATH);
        }
    }

    private void selectAirport(String airportName, String xpath) {
        List<WebElement> elements = getDriver().findElement(By.xpath(xpath)).findElements(By.xpath(AIRPORT_LABELS_XPATH));
        elements.forEach(it -> {
            WebElement element = it.findElement(By.xpath(AIRPORT_NAME_XPATH));
            if (airportName.equals(element.getText())) {
                it.click();
            }
        });
    }
}
