package ru.ifmo.se.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends Page {
    @FindBy(how = How.XPATH, using = "//div[@data-content=\"avia\"]")
    private WebElement webElement;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String clickHome() {
        webElement.click();
        return webElement.getText();
    }
}
