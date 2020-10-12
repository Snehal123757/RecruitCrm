package org.assignment.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    private static final int TIMEOUT = 25;
    private static final int POLLING = 100;

    public WebDriver driver;
    public WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, TIMEOUT);
    }

    public void waitForElementToVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

}
