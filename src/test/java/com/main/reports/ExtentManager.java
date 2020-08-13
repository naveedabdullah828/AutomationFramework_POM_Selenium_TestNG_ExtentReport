package com.main.reports;

import com.main.utils.HelperClass;
import com.relevantcodes.extentreports.ExtentReports;
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
        extentReports = new ExtentReports(reportFileLocation, true);
        extentReports.loadConfig(new File(userDirectory + fileSeparator + "extent-config.xml"));
        extentReports.addSystemInfo("OS", "Mac");
        extentReports.addSystemInfo("Environment", "Production");

        return extentReports;
    }
}
