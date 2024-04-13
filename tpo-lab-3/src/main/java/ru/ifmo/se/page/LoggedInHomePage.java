package ru.ifmo.se.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoggedInHomePage extends Page {
    @FindBy(xpath = "//a[@data-ti='user_name_link']")
    private WebElement username;

    public LoggedInHomePage(WebDriver driver, LoginOverlayPage loginOverlayPage, String email, String password) {
        super(driver);
        loginOverlayPage.login(email, password);
    }

    public String getCurrentUsername() {
        return username.getText();
    }
}
