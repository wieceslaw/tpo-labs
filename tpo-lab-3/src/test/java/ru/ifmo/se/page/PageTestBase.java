package ru.ifmo.se.page;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import ru.ifmo.se.CustomParameterResolver;
import ru.ifmo.se.driver.SeleniumDriver;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.stream.Stream;

@ExtendWith(CustomParameterResolver.class)
public abstract class PageTestBase {

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    @Retention(RetentionPolicy.RUNTIME)
    protected @interface TestWithAllDrivers {
    }

    static Stream<Arguments> allDrivers() {
        return SeleniumDriver.getDrivers().stream().map(SeleniumDriver::getDriver).map(Arguments::of);
    }

    @BeforeEach
    public void prepareContext(WebDriver driver) {
        driver.get("https://www.tutu.ru");
        preparePages(driver);
    }

    protected abstract void preparePages(WebDriver driver);

    @AfterEach
    public void quitDriver(WebDriver driver) {
        driver.quit();
    }

}
