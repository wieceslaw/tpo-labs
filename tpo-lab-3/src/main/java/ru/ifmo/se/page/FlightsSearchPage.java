package ru.ifmo.se.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FlightsSearchPage extends Page {
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

    @FindBy(xpath = "//div[@data-id='75']")
    public WebElement cityFromButton;

    @FindBy(xpath = "//div[@data-id='491']")
    public WebElement cityToButton;
    
    public FlightsSearchPage(WebDriver driver) {
        super(driver);
    }

    public void findFlights(String cityFrom, String cityTo, LocalDate dateFrom, LocalDate dateBack) {
        cityFromField.sendKeys(cityFrom);
        cityFromField.click();
        cityFromButton.click();

        cityToField.sendKeys(cityTo);
        cityToField.click();
        cityToButton.click();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        dateFromField.sendKeys(dateFrom.format(formatter));
        dateBackField.sendKeys(dateBack.format(formatter));
        findButton.click();
    }
}
