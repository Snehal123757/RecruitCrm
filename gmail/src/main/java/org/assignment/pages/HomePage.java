package org.assignment.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Set;

public class HomePage extends BasePage {

    Actions actions;

    @FindBy(xpath = "//input[@placeholder='Search mail']")
    WebElement searchBox;

    @FindBy(xpath = "(//table[@aria-readonly='true'])[4]//tr")
    List<WebElement> mailGrid;

    @FindBy(xpath = "//span[text()='Has attachment']")
    WebElement hasAttachment;

    @FindBy(xpath = "//input[@type='password']")
    WebElement password;

    @FindBy(xpath = "//div[@class='ha']//h2")
    WebElement emailSubject;

    @FindBy(xpath = "//div[text()='Attachments area']/parent::div//div[@data-tooltip='Download']")
    WebElement downloadAttachmentBtn;

    @FindBy(xpath = "//div[text()='Attachments area']/parent::div//a")
    WebElement downloadAttachmentLink;


    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(super.driver, this);
    }

    public void searchEmail(String searchedString) throws InterruptedException {
        waitForElementToVisible(searchBox);
        searchBox.sendKeys(searchedString);
        searchBox.sendKeys(Keys.ENTER);
        Thread.sleep(8000);
    }

    public int getCountOfMailWithAttachment() throws InterruptedException {
        waitForElementToVisible(hasAttachment);
        hasAttachment.click();
        Thread.sleep(3000);
        return mailGrid.size();
    }

    public String selectEmailAndReturnSubject(int elementIndex) {
        System.out.println(mailGrid.size());
        for (int i = 0; i < mailGrid.size(); i++) {
            if (i == elementIndex) {
                waitForElementToVisible(mailGrid.get(i));
                mailGrid.get(elementIndex).click();
                break;
            }
        }
        return emailSubject.getText();
    }

    public void clickHasAttachment() {
        actions = new Actions(driver);
        waitForElementToVisible(hasAttachment);
        actions.moveToElement(hasAttachment).click().build().perform();
    }

    public void downloadAttachment() {
        actions = new Actions(driver);
        waitForElementToVisible(downloadAttachmentLink);
        actions.moveToElement(downloadAttachmentLink).moveToElement(downloadAttachmentBtn).click().build().perform();
    }

}
