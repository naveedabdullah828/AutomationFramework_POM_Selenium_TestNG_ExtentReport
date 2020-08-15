package com.main.listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.main.reports.ExtentManager;
import com.main.utils.HelperClass;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ReportListener implements ITestListener {
    public ExtentReports extentReports;
    public static ExtentTest extentTest;

    @Override
    public void onTestStart(ITestResult iTestResult) {
        extentReports = ExtentManager.getInstance();
        extentTest = extentReports.createTest(Thread.currentThread().getId() + " " +iTestResult.getMethod().getMethodName());
        extentTest.log(Status.INFO, iTestResult.getMethod().getDescription());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        extentTest.log(Status.PASS, iTestResult.getName() + " Passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        String imagePath = HelperClass.takeScreenshot((WebDriver) iTestResult.getTestContext().getAttribute(Thread.currentThread().getId() + "_driver"),Thread.currentThread().getId() + "_" + iTestResult.getMethod().getMethodName());
        extentTest.log(Status.FAIL,iTestResult.getName() + " Failed \n " + iTestResult.getThrowable());
        extentTest.addScreenCaptureFromPath(imagePath);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        extentTest.log(Status.SKIP, iTestResult.getName() + " Skipped");
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }
}
