package com.main.listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.main.reports.ExtentManager;
import com.main.baseSetup.TestBase;
import com.main.utils.HelperClass;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

public class ReportListener implements ITestListener {
    public ExtentReports extentReports;
    public ExtentTest extentTest;
    public static ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

    public static ThreadLocal<ExtentReports> extentIndividualReportsThreadLocal = new ThreadLocal<>();
    public static ThreadLocal<ExtentTest> extentIndividualTestThreadLocal = new ThreadLocal<>();

    public static ITestContext context;

    @Override
    public void onTestStart(ITestResult iTestResult) {
        context = iTestResult.getTestContext();
        if(null != TestBase.retryStatusThread && TestBase.retryStatusThread.get().get()) {
            extentReports.removeTest(extentTestThreadLocal.get());
        }
        extentTest = extentReports.createTest(context.getAttribute("testName").toString() + " " +iTestResult.getMethod().getMethodName());
        extentTestThreadLocal.set(extentTest);
        extentTestThreadLocal.get().log(Status.INFO, iTestResult.getMethod().getDescription());

        if(HelperClass.isParallelTest(context)) {
            ExtentReports individualTestReports= extentIndividualReportsThreadLocal.get();
            ExtentTest individualExtentTest = individualTestReports.createTest(context.getAttribute("testName").toString() + " " +iTestResult.getMethod().getMethodName());
            extentIndividualTestThreadLocal.set(individualExtentTest);
            extentIndividualTestThreadLocal.get().log(Status.INFO, iTestResult.getMethod().getDescription());
        }
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        extentTestThreadLocal.get().log(Status.PASS, iTestResult.getName() + " Passed");

        if(HelperClass.isParallelTest(context)) {
            extentIndividualTestThreadLocal.get().log(Status.PASS, iTestResult.getName() + " Passed");
        }
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        WebDriver driver = TestBase.getDriver();
        String imagePath = null;
        if(!HelperClass.isHeadless(context,driver)){
            imagePath = HelperClass.takeScreenshot(driver,context.getAttribute("testName").toString() + "_" + iTestResult.getMethod().getMethodName());
            extentTestThreadLocal.get().addScreenCaptureFromPath(imagePath);
        }

        extentTestThreadLocal.get().log(Status.FAIL,iTestResult.getName() + " Failed " + iTestResult.getThrowable());

        if(HelperClass.isParallelTest(context)) {
            if(!HelperClass.isHeadless(context,driver)){
                extentIndividualTestThreadLocal.get().addScreenCaptureFromPath(imagePath);
            }
            extentIndividualTestThreadLocal.get().log(Status.FAIL,iTestResult.getName() + " Failed " + iTestResult.getThrowable());

        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        extentTestThreadLocal.get().log(Status.SKIP, iTestResult.getName() + " Skipped");
        if(HelperClass.isParallelTest(context)) {
            extentIndividualTestThreadLocal.get().log(Status.SKIP, iTestResult.getName() + " Skipped");
        }
    }

    @Override
    public void onStart(ITestContext context) {
        XmlSuite suiteName = context.getCurrentXmlTest().getSuite();
        String xmlFilePath = suiteName.getFileName();
        String[] paths = xmlFilePath.split(HelperClass.getFileSeparator());
        String xmlFileName = paths[paths.length - 1];

        context.setAttribute("testName", context.getName().replace("(failed)", "").trim());
        extentReports = ExtentManager.getInstance(xmlFileName);

        if(HelperClass.isParallelTest(context)) {
            ExtentReports individualTestReports = ExtentManager.createInstance(context.getAttribute("testName").toString(), xmlFileName);
            extentIndividualReportsThreadLocal.set(individualTestReports);
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }
}
