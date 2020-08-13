package com.main.listener;

import com.main.reports.ExtentManager;
import com.main.utils.HelperClass;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ReportListener implements ITestListener {
//    ExtentTest extentTest;
//    ExtentReports extentReports;
    @Override
    public void onTestStart(ITestResult result) {
//        extentReports = ExtentManager.getInstance();
//        extentTest = extentReports.startTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        //extentTest.log(LogStatus.PASS, result.getName() + " Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        //extentTest.log(LogStatus.FAIL, result.getName() + " Failed \n" + result.getThrowable());
        HelperClass.takeScreenshot((WebDriver) result.getTestContext().getAttribute("driver"),result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        //extentTest.log(LogStatus.SKIP, result.getName() + " Skipped");
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
//        extentReports.endTest(extentTest);
//        extentReports.flush();
    }
}
