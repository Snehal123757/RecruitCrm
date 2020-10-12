package org.assignment.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Set;

public class LoginPage extends BasePage {


    @FindBy(xpath = "//a[@ga-event-action='sign in']")
    WebElement signIn;

    @FindBy(xpath = "//input[@type='email']")
    WebElement email;

    @FindBy(xpath = "//button[@jsname='LgbsSe']")
    WebElement emailNext;

    @FindBy(xpath = "//input[@type='password']")
    WebElement password;

    @FindBy(xpath = "(//button[@jsname='LgbsSe'])[1]")
    WebElement passwordNext;

    @FindBy(xpath = "//button//div[text()='Sign in with Google']/parent::button")
    WebElement udacityLoginWithGmailBtn;


    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(super.driver, this);
    }

    public void clickSignIn() {
        waitForElementToVisible(signIn);
        signIn.click();
    }

    public void loginGmail(String username, String pwd) {
        waitForElementToVisible(udacityLoginWithGmailBtn);
        udacityLoginWithGmailBtn.click();
        waitForElementToVisible(email);
        email.sendKeys(username);
        emailNext.click();
        waitForElementToVisible(password);
        password.sendKeys(pwd);
        passwordNext.click();
    }

    public void openNewTab(String url, String parent){
        ((JavascriptExecutor) driver).executeScript("window.open('"+url+"','_blank');");
        Set<String> windows = driver.getWindowHandles();
        for(String child: windows){
            if(!child.equals(parent)){
                driver.switchTo().window(child);
            }
        }
    }

    public void switchToParent(String parentWindow) throws InterruptedException {
        driver.switchTo().window(parentWindow);
        Thread.sleep(3000);
    }


}
