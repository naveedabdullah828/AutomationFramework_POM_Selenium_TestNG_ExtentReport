package com.main.test;

import com.aventstack.extentreports.ExtentReports;
import com.main.listener.ReportListener;
import com.main.listener.RetryFailedTest;
import com.main.objectRepo.FacebookOR;
import com.main.objectRepo.GoogleOR;
import com.main.objectRepo.SampleOR;
import com.main.utils.HelperClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.*;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicBoolean;

public class TestBase {
    WebDriver driver;
    WebDriverWait wait;

    GoogleOR googleOR;
    FacebookOR facebookOR;
    SampleOR sampleOR;
    RetryFailedTest retryFailedTest;
    ITestContext testBaseTestContext;

    public static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();
    public static ThreadLocal<WebDriverWait> webDriverWaitThreadLocal = new ThreadLocal<>();
    public static ThreadLocal<AtomicBoolean> retryStatusThread = new ThreadLocal<>();

    @BeforeSuite(alwaysRun = true)
    public void setup(ITestContext iTestContext) {
        HelperClass.deleteAndCreateDirectory();
        HelperClass.loadData();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown(ITestContext iTestContext) {
    }

    @BeforeTest(alwaysRun = true)
    @Parameters("browser")
    public void beforeTest(final ITestContext iTestContext, String browser) {
        if ("firefox".equals(browser)) {
            driver = new FirefoxDriver();
        } else if ("chrome".equals(browser)) {
            driver = new ChromeDriver();
        } else {
            driver = new FirefoxDriver();
        }
        webDriverThreadLocal.set(driver);
        retryStatusThread.set(new AtomicBoolean(false));
    }

    @AfterTest(alwaysRun = true)
    public void afterTest(final ITestContext iTestContext) {
        if(HelperClass.isParallelTest(iTestContext)) {
            ExtentReports individualTestReports = ReportListener.extentIndividualReportsThreadLocal.get();
            individualTestReports.flush();
        }
        (webDriverThreadLocal.get()).quit();
    }

    @BeforeClass(alwaysRun = true)
    public void beforeClass(ITestContext iTestContext){
        driver = webDriverThreadLocal.get();
        wait = new WebDriverWait(driver, 5);
        webDriverWaitThreadLocal.set(wait);

        retryFailedTest = new RetryFailedTest();
        googleOR = new GoogleOR(iTestContext);
        facebookOR = new FacebookOR(iTestContext);
        sampleOR = new SampleOR(iTestContext);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method, ITestResult iTestResult, ITestContext iTestContext) {
        testBaseTestContext = iTestContext;
        if(retryStatusThread.get().get()) {
            HelperClass.retriedTest.add(iTestResult.getMethod());
        }
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult iTestResult) {
        if(iTestResult.getStatus() == ITestResult.SUCCESS) {
            retryStatusThread.set(new AtomicBoolean(false));
        }
    }

    public static WebDriver getDriver() {
        return webDriverThreadLocal.get();
    }

    public static WebDriverWait getWebDriverWait() {
        return webDriverWaitThreadLocal.get();
    }
}
