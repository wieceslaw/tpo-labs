package ru.ifmo.se.page;

import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class Page {
    protected WebDriver driver;

    public Page(WebDriver driver) {
        this.driver = driver;
    }

//    protected final void driverGet(PageUrl pageUrl) {
//        driver.get(pageUrl.getUrl());
//    }

    public final String driverGetUrl() {
        return driver.getCurrentUrl();
    }

    public final Duration getWaitTimeout() {
        return driver.manage().timeouts().getImplicitWaitTimeout();
    }

    public final void refreshPage() {
        driver.get(driver.getCurrentUrl());
    }

    public final WebDriver getDriver(){
        return driver;
    }
}
