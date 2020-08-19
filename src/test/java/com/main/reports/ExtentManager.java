package com.main.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.main.utils.HelperClass;

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
        ExtentReports individualTestReports = new ExtentReports();

        extentSparkReporter.config().setReportName("Automation Report");
        extentSparkReporter.config().setDocumentTitle("Automation Test Result");

        individualTestReports.setSystemInfo("OS", "Mac");
        individualTestReports.setSystemInfo("Environment", "Prod");

        individualTestReports.attachReporter(extentSparkReporter);
        return individualTestReports;
    }

    public static ExtentReports createInstance(String fileName) {
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(reportFilePath + fileSeparator + fileName + ".html");
        ExtentReports extentIndividualReports = new ExtentReports();

        extentSparkReporter.config().setReportName("Automation Report " + fileName);
        extentSparkReporter.config().setDocumentTitle("Automation Test Result " + fileName);

        extentIndividualReports.setSystemInfo("OS", "Mac");
        extentIndividualReports.setSystemInfo("Environment", "Prod");

        extentIndividualReports.attachReporter(extentSparkReporter);
        return extentIndividualReports;
    }
}
