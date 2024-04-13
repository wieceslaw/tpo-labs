package ru.ifmo.se.page;

import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginPageTest extends PageTestBase {
    HomePage homePage;
    LoginOverlayPage loginPage;

    static String EMAIL = "rututu591@gmail.com";
    static String PASSWORD = "testtuturu2024";

    @TestWithAllDrivers
    void testCorrectCredentials(WebDriver driver) {
        LoggedInHomePage loggedInHomePage = new LoggedInHomePage(driver, loginPage, EMAIL, PASSWORD);

        assertEquals(loggedInHomePage.getCurrentUsername(), EMAIL);
    }

    @TestWithAllDrivers
    void testWrongCredentials(WebDriver driver) {
        loginPage.login("123@mail.ru", "123");

        assertTrue(loginPage.hasWrongCredentialsError());
    }

    @TestWithAllDrivers
    void testNoPassword(WebDriver driver) {
        loginPage.login("123@mail.ru", "");

        assertTrue(loginPage.hasNoPasswordError());
    }

    @TestWithAllDrivers
    void testNoEmail(WebDriver driver) {
        loginPage.login("", "123");

        assertTrue(loginPage.hasNoEmailError());
    }

    @TestWithAllDrivers
    void testWrongEmail(WebDriver driver) {
        loginPage.login("123", "123");

        assertTrue(loginPage.hasWrongEmailError());
    }

    @Override
    protected void preparePages(WebDriver driver) {
        homePage = new HomePage(driver);
        loginPage = new LoginOverlayPage(driver, homePage);
    }
}
