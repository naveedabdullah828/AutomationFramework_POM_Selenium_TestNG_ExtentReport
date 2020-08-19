package com.main.listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.main.reports.ExtentManager;
import com.main.test.TestBase;
import com.main.utils.HelperClass;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

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
        extentTest = extentReports.createTest(context.getName() + " " +iTestResult.getMethod().getMethodName());
        extentTestThreadLocal.set(extentTest);
        extentTestThreadLocal.get().log(Status.INFO, iTestResult.getMethod().getDescription());

        if("tests".equals(context.getCurrentXmlTest().getParallel().toString())) {
            ExtentReports individualTestReports= extentIndividualReportsThreadLocal.get();
            ExtentTest individualExtentTest = individualTestReports.createTest(context.getName() + " " +iTestResult.getMethod().getMethodName());
            extentIndividualTestThreadLocal.set(individualExtentTest);
            extentIndividualTestThreadLocal.get().log(Status.INFO, iTestResult.getMethod().getDescription());
        }
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        extentTestThreadLocal.get().log(Status.PASS, iTestResult.getName() + " Passed");
        if("tests".equals(context.getCurrentXmlTest().getParallel().toString())) {
            extentIndividualTestThreadLocal.get().log(Status.PASS, iTestResult.getName() + " Passed");
        }
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        String imagePath = HelperClass.takeScreenshot(TestBase.getDriver(),context.getName() + "_" + iTestResult.getMethod().getMethodName());
        extentTestThreadLocal.get().log(Status.FAIL,iTestResult.getName() + " Failed " + iTestResult.getThrowable());
        extentTestThreadLocal.get().addScreenCaptureFromPath(imagePath);

        if("tests".equals(context.getCurrentXmlTest().getParallel().toString())) {
            extentIndividualTestThreadLocal.get().log(Status.FAIL,iTestResult.getName() + " Failed " + iTestResult.getThrowable());
            extentIndividualTestThreadLocal.get().addScreenCaptureFromPath(imagePath);
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        extentTestThreadLocal.get().log(Status.SKIP, iTestResult.getName() + " Skipped");
        if("tests".equals(context.getCurrentXmlTest().getParallel().toString())) {
            extentIndividualTestThreadLocal.get().log(Status.SKIP, iTestResult.getName() + " Skipped");
        }
    }

    @Override
    public void onStart(ITestContext context) {
        String parallel = context.getCurrentXmlTest().getParallel().toString();
        extentReports = ExtentManager.getInstance();

        if("tests".equals(parallel)) {
            ExtentReports individualTestReports = ExtentManager.createInstance(context.getName());
            extentIndividualReportsThreadLocal.set(individualTestReports);
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }
}
