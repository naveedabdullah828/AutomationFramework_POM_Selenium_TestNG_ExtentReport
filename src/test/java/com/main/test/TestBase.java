package com.main.test;

import com.main.listener.ReportListener;
import com.main.objectRepo.FacebookOR;
import com.main.objectRepo.GoogleOR;
import com.main.objectRepo.SampleOR;
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
    SampleOR sampleOR;

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
    }

    @BeforeTest
    public void beforeTest(final ITestContext iTestContext) {
        if(null == iTestContext.getAttribute(Thread.currentThread().getId() + "_driver")) {
            driver = new FirefoxDriver();
            iTestContext.setAttribute(Thread.currentThread().getId() + "_driver", driver);
        }
    }

    @AfterTest
    public void afterTest(final ITestContext iTestContext) {
        driver.quit();
    }

    @BeforeClass
    public void beforeClass(ITestContext iTestContext){
        driver = (WebDriver) iTestContext.getAttribute(Thread.currentThread().getId() + "_driver");
        wait = new WebDriverWait(driver, 5);

        iTestContext.setAttribute(Thread.currentThread().getId()+"_wait",wait);
        googleOR = new GoogleOR(iTestContext);
        facebookOR = new FacebookOR(iTestContext);
        sampleOR = new SampleOR(iTestContext);
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
