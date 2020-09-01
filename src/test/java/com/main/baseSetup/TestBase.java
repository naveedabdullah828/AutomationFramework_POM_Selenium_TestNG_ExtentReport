package com.main.baseSetup;

import com.aventstack.extentreports.ExtentReports;
import com.main.listener.ReportListener;
import com.main.listener.RetryFailedTest;
import com.main.objectRepo.FacebookOR;
import com.main.objectRepo.GoogleOR;
import com.main.objectRepo.SampleOR;
import com.main.utils.HelperClass;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.*;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.concurrent.atomic.AtomicBoolean;

public class TestBase {
    public RemoteWebDriver driver;
    public WebDriverWait wait;
    public DriverFactory driverFactory;

    public GoogleOR googleOR;
    public FacebookOR facebookOR;
    public SampleOR sampleOR;
    public RetryFailedTest retryFailedTest;
    public ITestContext testBaseTestContext;

    public static ThreadLocal<RemoteWebDriver> webDriverThreadLocal = new ThreadLocal<>();
    public static ThreadLocal<WebDriverWait> webDriverWaitThreadLocal = new ThreadLocal<>();
    public static ThreadLocal<AtomicBoolean> retryStatusThread = new ThreadLocal<>();

    @BeforeSuite(alwaysRun = true)
    public void setup(ITestContext iTestContext) {
        HelperClass.setDriverPathForWindows();
        HelperClass.deleteAndCreateDirectory();
        HelperClass.loadData();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown(ITestContext iTestContext) {
    }

    @BeforeTest(alwaysRun = true)
    @Parameters({"automationType", "browser", "os", "deviceId"})
    public void beforeTest(final ITestContext iTestContext, String automationType, String browser, String osValue, String deviceId) throws MalformedURLException {
        driverFactory = new DriverFactory();
        if(automationType.equalsIgnoreCase("grid"))
            driver = driverFactory.createDriverGrid(browser, osValue, deviceId);
        else
            driver = driverFactory.createDriver(browser);
        if(browser.equalsIgnoreCase("headless"))
            iTestContext.setAttribute("headless", driver.hashCode());
        else
            iTestContext.setAttribute("headless", 0);

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
        wait = new WebDriverWait(driver, 10);
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

    public static RemoteWebDriver getDriver() {
        return webDriverThreadLocal.get();
    }

    public static WebDriverWait getWebDriverWait() {
        return webDriverWaitThreadLocal.get();
    }
}
