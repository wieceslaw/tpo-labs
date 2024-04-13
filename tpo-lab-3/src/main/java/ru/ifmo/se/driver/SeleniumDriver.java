package ru.ifmo.se.driver;

import org.openqa.selenium.WebDriver;

import java.util.List;

public abstract class SeleniumDriver {

    protected WebDriver driver;

    public final WebDriver getDriver() {
        return driver;
    }

    public abstract void setup();

    public abstract void close();

    public static List<SeleniumDriver> getDrivers() {
        return List.of(new SeleniumFirefoxDriver(), new SeleniumChromeDriver());
    }

}
