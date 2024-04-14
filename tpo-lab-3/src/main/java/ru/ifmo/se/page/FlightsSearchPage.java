package ru.ifmo.se.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class FlightsSearchPage extends Page {
    public static final String NO_CITY_ERROR_DEPARTURE_XPATH = "//div[@class='qtip-content' and text()='Пожалуйста, укажите город вылета']";
    public static final String NO_CITY_ERROR_ARRIVAL_XPATH = "//div[@class='qtip-content' and text()='Пожалуйста, укажите город прилета']";
    public static final String SAME_CITY_ERROR_XPATH = "//div[@class='qtip-content' and contains(text(), 'Город прилета совпадает')]";
    public static final String INCORRECT_DEPARTURE_DATE_ERROR_XPATH = "//div[@class='qtip-content' and contains(text(), 'Некорректная дата вылета.')]";
    public static final String CITY_TO_BUTTON_XPATH = "//div[@class='input_wrp forward j-city_container j-city_container_to']//span[@class='name_city']";
    public static final String CITY_FROM_BUTTON_XPATH = "//div[@class='input_wrp j-city_container j-city_container_from']//span[@class='name_city']";
    @FindBy(xpath = "//input[@name='city_from']")
    private WebElement cityFromField;

    @FindBy(xpath = "//input[@name='city_to']")
    public WebElement cityToField;

    @FindBy(xpath = "//input[@name='date_from']")
    public WebElement dateFromField;

    @FindBy(xpath = "//input[@name='date_back']")
    public WebElement dateBackField;

    @FindBy(xpath = "//span[contains(@style, 'top:')]")
    public WebElement findButton;

    public FlightsSearchPage(WebDriver driver) {
        super(driver);
    }

    public void findFlights(SearchFilter searchFilter) {
        cityFromField.sendKeys(searchFilter.getCityFrom());
        cityFromField.click();
        Optional<WebElement> cityFromButton = findOptionalElement(By.xpath(CITY_FROM_BUTTON_XPATH));
        cityFromButton.ifPresent(WebElement::click);
//        cityFromButton.ifPresent(it -> {
//            (new WebDriverWait(getDriver(), getWaitTimeout()))
//                    .until(ExpectedConditions.elementToBeClickable(it)).click();
//        });
        cityToField.sendKeys(searchFilter.getCityTo());
        cityToField.click();
        Optional<WebElement> cityToButton = findOptionalElement(By.xpath(CITY_TO_BUTTON_XPATH));
//        cityToButton.ifPresent(it -> {
//            (new WebDriverWait(getDriver(), getWaitTimeout()))
//                    .until(ExpectedConditions.elementToBeClickable(it)).click();
//        });
        cityToButton.ifPresent(WebElement::click);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        dateFromField.sendKeys(searchFilter.getDateFrom().format(formatter));
        dateBackField.sendKeys(searchFilter.getDateBack().format(formatter));
        findButton.click();
    }


    public boolean hasNoCityDepartureError() {
        return findOptionalElement(By.xpath(NO_CITY_ERROR_DEPARTURE_XPATH)).isPresent();
    }

    public boolean hasNoCityArrivalError() {
        return findOptionalElement(By.xpath(NO_CITY_ERROR_ARRIVAL_XPATH)).isPresent();
    }

    public boolean hasSameCityError() {
        return findOptionalElement(By.xpath(SAME_CITY_ERROR_XPATH)).isPresent();
    }

    public boolean hasIncorrectDepartureDateError() {
        return findOptionalElement(By.xpath(INCORRECT_DEPARTURE_DATE_ERROR_XPATH)).isPresent();
    }
}
