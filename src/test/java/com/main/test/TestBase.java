package com.main.test;

import com.main.listener.ReportListener;
import com.main.objectRepo.FacebookOR;
import com.main.objectRepo.GoogleOR;
import com.main.utils.HelperClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.*;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class TestBase extends ReportListener {
    WebDriver driver;
    WebDriverWait wait;

    static String fileSeparator;
    static String userDirectory;

    GoogleOR googleOR;
    FacebookOR facebookOR;

    @BeforeSuite
    public void setup(ITestContext iTestContext) {
        userDirectory = HelperClass.getUserDirectory();
        fileSeparator = HelperClass.getFileSeparator();
        String testDataPath = userDirectory + fileSeparator + "TestData" + fileSeparator + "TestData.properties";
        HelperClass.loadData(testDataPath);
        HelperClass.deleteAndCreateDirectory();
    }

    @AfterSuite
    public void tearDown(ITestContext iTestContext) {
        driver.quit();
    }

    @BeforeTest
    public void beforeTest(final ITestContext iTestContext) {
    }

    @AfterTest
    public void afterTest(final ITestContext iTestContext) {

    }

    @BeforeClass
    public void beforeClass(ITestContext iTestContext){
        if(null == iTestContext.getAttribute("driver")) {
            driver = new FirefoxDriver();
            iTestContext.setAttribute("driver", driver);
        } else {
            driver = (WebDriver) iTestContext.getAttribute("driver");
        }

        wait = new WebDriverWait(driver, 5);

        googleOR = new GoogleOR(driver, wait);
        facebookOR = new FacebookOR(driver, wait);
    }

    @AfterClass
    public void afterClass() {
    }

    @BeforeMethod
    public void beforeMethod(Method method, ITestResult iTestResult, ITestContext iTestContext) {
    }

    @AfterMethod
    public void afterMethod(ITestResult iTestResult) {
    }

    public WebDriver getDriver() {
        return driver;
    }
}
