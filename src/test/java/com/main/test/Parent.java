package com.main.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.main.objectRepo.FacebookOR;
import com.main.objectRepo.GoogleOR;
import com.main.reports.ExtentManager;
import com.main.utils.HelperClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.*;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Parent {
    static WebDriver driver;
    static WebDriverWait wait;

    ExtentReports extentReports;
    ExtentTest extentTest;

    static String fileSeparator;
    static String userDirectory;

    static GoogleOR googleOR;
    static FacebookOR facebookOR;

    @BeforeSuite
    public void setup(ITestContext iTestContext) {
        userDirectory = HelperClass.getUserDirectory();
        fileSeparator = HelperClass.getFileSeparator();
        String testDataPath = userDirectory + fileSeparator + "src" + fileSeparator + "test" + fileSeparator + "java" + fileSeparator + "com" + fileSeparator + "main" + fileSeparator + "testdata" + fileSeparator + "TestData.properties";
        HelperClass.loadData(testDataPath);
        HelperClass.deleteAndCreateDirectory();

        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);

        googleOR = new GoogleOR(driver, wait);
        facebookOR = new FacebookOR(driver, wait);

        iTestContext.setAttribute("driver", driver);
    }

    @AfterSuite
    public void tearDown(ITestContext iTestContext) {
        driver.quit();

        extentReports.removeTest(extentTest);
        extentReports.flush();
    }

    @BeforeTest
    public void beforeTest(final ITestContext iTestContext) {
    }

    @AfterTest
    public void afterTest(final ITestContext iTestContext) {

    }

    @BeforeClass
    public void beforeClass(){
    }

    @AfterClass
    public void afterClass() {
    }

    @BeforeMethod
    public void beforeMethod(Method method, ITestResult iTestResult, ITestContext iTestContext) {
        extentReports = ExtentManager.getInstance();
        extentTest = extentReports.createTest(method.getName());
        extentTest.log(Status.INFO, iTestResult.getMethod().getDescription());
    }

    @AfterMethod
    public void afterMethod(ITestResult iTestResult) {
        if(ITestResult.SUCCESS == iTestResult.getStatus()) {
            extentTest.log(Status.PASS, iTestResult.getName() + " Passed");
        } else if(ITestResult.SKIP == iTestResult.getStatus()) {
            extentTest.log(Status.SKIP, iTestResult.getName() + " Skipped");
        } else if(ITestResult.FAILURE == iTestResult.getStatus()) {
            Path path = Paths.get(userDirectory);
            String imagePath = fileSeparator + path.getFileName() + fileSeparator + "Screenshots" + fileSeparator + iTestResult.getName() + ".png";

            extentTest.log(Status.FAIL,iTestResult.getName() + " Failed \n " + iTestResult.getThrowable());
            extentTest.addScreenCaptureFromPath(imagePath);
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
