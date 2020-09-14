package com.main.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.main.utils.HelperClass;

public class ExtentManager {
    private static ExtentReports extentReports;
    private static String reportFileName = "Test-Automation-Report.html";
    private static final String fileSeparator = HelperClass.getFileSeparator();
    private static final String userDirectory = HelperClass.getUserDirectory();
    private static final String reportFilePath = userDirectory + fileSeparator + "TestReport";

    public static ExtentReports getInstance(String xmlFileName) {
        if (!xmlFileName.equalsIgnoreCase("testng.xml")) {
            reportFileName = "Test-Automation-Failed-Rerun-Report.html";
        }
        if(null == extentReports)
            extentReports = createInstance();
        return extentReports;
    }

    private static ExtentReports createInstance() {
        String reportFileLocation = reportFilePath + fileSeparator + reportFileName;
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(reportFileLocation);
        ExtentReports individualTestReports = new ExtentReports();

        extentSparkReporter.config().setReportName("Automation Report");
        extentSparkReporter.config().setDocumentTitle("Automation Test Result");

        individualTestReports.setSystemInfo("OS", "Mac");
        individualTestReports.setSystemInfo("Environment", "Prod");

        individualTestReports.attachReporter(extentSparkReporter);
        return individualTestReports;
    }

    public static ExtentReports createInstance(String testName, String xmlFileName) {
        if(xmlFileName.equalsIgnoreCase("testng-failed.xml")) {
            testName = testName.replace("(failed)", "").trim();
            testName = testName + "-Failed";
        }
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(reportFilePath + fileSeparator + testName + ".html");
        ExtentReports extentIndividualReports = new ExtentReports();

        extentSparkReporter.config().setReportName("Automation Report " + testName);
        extentSparkReporter.config().setDocumentTitle("Automation Test Result " + testName);

        extentIndividualReports.setSystemInfo("OS", "Mac");
        extentIndividualReports.setSystemInfo("Environment", "Prod");

        extentIndividualReports.attachReporter(extentSparkReporter);
        return extentIndividualReports;
    }
}
