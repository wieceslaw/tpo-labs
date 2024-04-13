package ru.ifmo.se.page;

import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HomePageTest extends PageTestBase {
    HomePage homePage;

    @TestWithAllDrivers
    void name2(WebDriver driver) {
        assertEquals("Авиабилеты", homePage.clickHome());
    }

    @Override
    protected void preparePages(WebDriver driver) {
        this.homePage = new HomePage(driver);
    }
}