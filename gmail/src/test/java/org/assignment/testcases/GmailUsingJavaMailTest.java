package org.assignment.testcases;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.assignment.pages.HomePage;
import org.assignment.pages.LoginPage;
import org.assignment.utility.CommonUtils;
import org.assignment.utility.ExcelUtils;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class GmailUsingJavaMailTest extends BaseTest {

    LoginPage loginPage;
    HomePage homePage;
    String projectDir = System.getProperty("user.dir");

    @BeforeMethod
    public void setUp() throws InterruptedException {
        initDriver();
        loginPage = new LoginPage(getDriver());
        homePage = new HomePage(getDriver());

    }

    @Test(enabled = true)
    public void testGmailSearchFunctionality() throws InterruptedException {
        //Creating test for extent report
        test = reports.createTest("testGmailSearchFunctionality");
        //1. Log in your Gmail account
        loginPage.openNewTab("https://auth.udacity.com/sign-in?next=https://classroom.udacity.com/authenticated", parentWindow);
        loginPage.loginGmail("katte.snehal32@gmail.com", "snehal123");
        loginPage.switchToParent(parentWindow);
        //2. Search a particular mail in Gmail search box which has an attachment
        homePage.searchEmail("ATTACHMENT MAIL TEST");
        String actualSelectedMailSubject = homePage.selectEmailAndReturnSubject(0);
        System.out.println(actualSelectedMailSubject);
        Assert.assertEquals("ATTACHMENT MAIL TEST", actualSelectedMailSubject);
        Thread.sleep(4000);
    }

    @Test(enabled = true)
    public void testGmailSearchFunctionalityUsingExcel() throws InterruptedException, IOException {
        test = reports.createTest("testGmailSearchFunctionalityUsingExcel");
        //1. Log in your Gmail account
        loginPage.openNewTab("https://auth.udacity.com/sign-in?next=https://classroom.udacity.com/authenticated", parentWindow);
        loginPage.loginGmail("katte.snehal32@gmail.com", "snehal123");
        loginPage.switchToParent(parentWindow);
        // Get the searched text from Excel file
        String excelData = ExcelUtils.readExcelData("ATTACHMENT MAIL TEST");
        //2. Search a particular mail in Gmail search box using data from excel
        homePage.searchEmail(excelData);
        String actualSelectedMailSubject = homePage.selectEmailAndReturnSubject(0);
        System.out.println(actualSelectedMailSubject);
        Assert.assertEquals("ATTACHMENT MAIL TEST", actualSelectedMailSubject);
        Thread.sleep(4000);
    }

    @Test(enabled = true)
    public void testGettingLatestEmailWithAttachmentFunctionality() throws InterruptedException {
        test = reports.createTest("testGettingLatestEmailWithAttachmentFunctionality");
        //1. Log in your Gmail account
        loginPage.openNewTab("https://auth.udacity.com/sign-in?next=https://classroom.udacity.com/authenticated", parentWindow);
        loginPage.loginGmail("katte.snehal32@gmail.com", "snehal123");
        loginPage.switchToParent(parentWindow);
        //2. Search a particular mail in Gmail search box which has an attachment
        homePage.searchEmail("to");
        int count = homePage.getCountOfMailWithAttachment();
        System.out.println("The total count of emails with attachment " + count);
        // Get the latest mail with attachment
        String actualSelectedMailSubject = homePage.selectEmailAndReturnSubject(0);
        System.out.println("The subject of latest mail with attachment " + actualSelectedMailSubject);
        Thread.sleep(3000);
        homePage.downloadAttachment();
        Thread.sleep(4000);
    }

    @Test(enabled = true)
    public void testDownloadAttachmentAndRenamedFunctionality() throws InterruptedException, IOException {
        test = reports.createTest("testDownloadAttachmentAndRenamedFunctionality");
        //1. Log in your Gmail account
        loginPage.openNewTab("https://auth.udacity.com/sign-in?next=https://classroom.udacity.com/authenticated", parentWindow);
        loginPage.loginGmail("katte.snehal32@gmail.com", "snehal123");
        loginPage.switchToParent(parentWindow);
        //2. Search a particular mail in Gmail search box which has an attachment
        homePage.searchEmail("to");
        int count = homePage.getCountOfMailWithAttachment();
        System.out.println("The total count of emails with attachment " + count);
        // Get the latest mail with attachment
        String actualSelectedMailSubject = homePage.selectEmailAndReturnSubject(0);
        System.out.println("The subject of latest mail with attachment " + actualSelectedMailSubject);
        Thread.sleep(3000);
        homePage.downloadAttachment();
        Thread.sleep(5000);
        String data = ExcelUtils.readExcelData("RENAMED.pdf");
        System.out.println(data);
        CommonUtils.renameFileInSameDirectory("attachment.pdf", data);
        CommonUtils.moveFileInDifferentDirectory("RENAMED.pdf", "IdeaProjects/gmail/downloads");
        Thread.sleep(4000);
    }

    @AfterMethod
    public void getResult(ITestResult result) throws Exception {
        if (result.getStatus() == ITestResult.FAILURE) {
            //MarkupHelper is used to display the output in different colors
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
        }
        tearDown();
    }

}
