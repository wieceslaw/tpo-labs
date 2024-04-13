package ru.ifmo.se.page;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.stqa.selenium.factory.WebDriverPool;

import java.time.Duration;
import java.util.List;

public class Configuration {

    public List<WebDriver> getDrivers() {
        DesiredCapabilities firefoxCapabilities = new DesiredCapabilities();
        firefoxCapabilities.setCapability("driverKey", "webdriver.gecko.driver");
        firefoxCapabilities.setCapability("browserName", "firefox");
        firefoxCapabilities.setCapability("nativeEvents", "false");
        firefoxCapabilities.setCapability("webdriver.firefox.bin", "drivers/geckodriver");

        WebDriver firefoxDriver = WebDriverPool.DEFAULT.getDriver(firefoxCapabilities);
        firefoxDriver.manage().window().setSize(new Dimension(1024, 768));
        firefoxDriver.manage().window().setPosition(new Point(0, 0));
        firefoxDriver.get("https://www.tutu.ru/");
        firefoxDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        firefoxDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        DesiredCapabilities chromeCapabilities = new DesiredCapabilities();
        chromeCapabilities.setCapability("driverKey", "webdriver.chrome.driver");
        chromeCapabilities.setCapability("browserName", "chrome");
        chromeCapabilities.setCapability("webdriver.chrome.bin", "drivers/chromedriver");

        WebDriver chromeDriver = WebDriverPool.DEFAULT.getDriver(chromeCapabilities);
        chromeDriver.manage().window().setSize(new Dimension(1024, 768));
        chromeDriver.manage().window().setPosition(new Point(0, 0));
        chromeDriver.get("https://www.tutu.ru/");
        chromeDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return List.of(firefoxDriver, chromeDriver);
    }
}
