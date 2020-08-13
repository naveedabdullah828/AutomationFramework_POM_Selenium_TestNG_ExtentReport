package com.main.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.main.utils.HelperClass;
import java.io.File;

public class ExtentManager {
    private static ExtentReports extentReports;
    private static String reportFileName = "Test-Automation-Report.html";
    private static String fileSeparator = HelperClass.getFileSeparator();
    private static String userDirectory = HelperClass.getUserDirectory();
    private static String reportFilePath = userDirectory + fileSeparator + "TestReport";
    private static String reportFileLocation = reportFilePath + fileSeparator + reportFileName;

    public static ExtentReports getInstance() {
        if(null == extentReports)
            extentReports = createInstance();

        return extentReports;
    }

    private static ExtentReports createInstance() {
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(reportFileLocation);
        extentReports = new ExtentReports();

        extentSparkReporter.config().setReportName("Automation Report");
        extentSparkReporter.config().setDocumentTitle("Automation Test Result");

        extentReports.setSystemInfo("OS", "Mac");
        extentReports.setSystemInfo("Environment", "Prod");

        extentReports.attachReporter(extentSparkReporter);
        return extentReports;
    }
}
