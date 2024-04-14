package ru.ifmo.se.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.Optional;

public class Page {
    private final WebDriver driver;

    public Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected final WebDriver getDriver() {
        return driver;
    }

    protected final void goToUrl(String url) {
        driver.get(url);
    }

    protected final String currentUrl() {
        return driver.getCurrentUrl();
    }

    protected final Duration getWaitTimeout() {
        return driver.manage().timeouts().getImplicitWaitTimeout();
    }

    protected final void refreshPage() {
        driver.get(driver.getCurrentUrl());
    }

    protected final Optional<WebElement> findOptionalElement(By by) {
        try {
            return Optional.of(driver.findElement(by));
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }

}
