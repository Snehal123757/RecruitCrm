package org.assignment.testcases;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.Set;

public class BaseTest {

    private WebDriver driver;
    String parentWindow;
    String projectDir = System.getProperty("user.dir");
    ExtentHtmlReporter extentHtmlReporter;
    ExtentReports reports;
    ExtentTest test;

    @BeforeTest
    public void initExtentReport(){
        extentHtmlReporter = new ExtentHtmlReporter(projectDir+"/reports/ExtentReport.html");
        // Create an object of Extent Reports
        reports = new ExtentReports();
        reports.attachReporter(extentHtmlReporter);
        reports.setSystemInfo("Host Name", "Localhost");
        reports.setSystemInfo("Environment", "QA");
        reports.setSystemInfo("User Name", "TESTER");
        extentHtmlReporter.config().setDocumentTitle("Automation Test Execution Report");
        // Setting name of the report
        extentHtmlReporter.config().setReportName("Gmail Test Report");
        // Setting Dark Theme
        extentHtmlReporter.config().setTheme(Theme.DARK);
    }

    public void initDriver() throws InterruptedException {
        // To get project directory
        String projectDir = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", projectDir + "/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.gmail.com");
        parentWindow = driver.getWindowHandle();
    }

    public WebDriver getDriver(){
        return this.driver;
    }

    public void tearDown(){
        if(driver != null){
            driver.close();
            driver.quit();
        }
    }

    @AfterTest
    public void endReport(){
        reports.flush();
    }

}
