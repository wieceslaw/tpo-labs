package ru.ifmo.se.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Optional;

public class LoginOverlayPage extends Page {
    public static final String PASSWORD_ERROR_XPATH = "//span[@data-ti-error='password']";
    public static final String EMAIL_ERROR_XPATH = "//span[@data-ti-error='email']";
    public static final String CREDENTIALS_ERROR_XPATH = "//span[@data-ti-error='authApi']";
    public static final String NO_PASSWORD_ERROR = "Без пароля никак.";
    public static final String NO_EMAIL_ERROR = "Без адреса электронной почты никак.";
    public static final String WRONG_EMAIL_ERROR = "Проверьте правильность написания адреса эл. почты.";
    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@variant='primary']")
    private WebElement loginButton;

    public LoginOverlayPage(WebDriver driver, HomePage homePage) {
        super(driver);
        homePage.openLoginPage();
    }

    public void fillCredentials(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void login(String email, String password) {
        fillCredentials(email, password);
        clickLoginButton();
    }

    public boolean hasNoPasswordError() {
        Optional<WebElement> passwordError =
                findOptionalElement(By.xpath(PASSWORD_ERROR_XPATH));
        return passwordError.map(webElement -> webElement.getText().strip().equals(NO_PASSWORD_ERROR)).orElse(false);
    }

    public boolean hasNoEmailError() {
        Optional<WebElement> passwordError =
                findOptionalElement(By.xpath(EMAIL_ERROR_XPATH));
        return passwordError.map(webElement -> webElement.getText().strip().equals(NO_EMAIL_ERROR)).orElse(false);
    }

    public boolean hasWrongEmailError() {
        Optional<WebElement> passwordError =
                findOptionalElement(By.xpath(EMAIL_ERROR_XPATH));
        return passwordError.map(webElement -> webElement.getText().strip().equals(WRONG_EMAIL_ERROR)).orElse(false);
    }

    public boolean hasWrongCredentialsError() {
        Optional<WebElement> passwordError = findOptionalElement(By.xpath(CREDENTIALS_ERROR_XPATH));
        return passwordError.isPresent();
    }
}
