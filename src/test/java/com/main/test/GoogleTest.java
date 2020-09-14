package com.main.test;

import com.aventstack.extentreports.Status;
import com.main.baseSetup.TestBase;
import com.main.listener.ReportListener;
import com.main.utils.HelperClass;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

@Test(groups = "System")
public class GoogleTest extends TestBase {
    @Test(description = "Launch Google", groups = "Regression", alwaysRun = true)
    public void TC_001_launchGoogle(){
        googleOR.launchGoogle(HelperClass.testData.getProperty("inputUrl"));
        System.out.println("Title " + driver.getTitle());
        Assert.fail();
    }

//    @Test(description = "Enter word to search", groups = "Regression", dependsOnMethods = "TC_001_launchGoogle", alwaysRun = true)
//    public void TC_002_enterText() {
//        googleOR.enterText(HelperClass.testData.getProperty("searchText"));
//        ReportListener.extentTestThreadLocal.get().log(Status.INFO, "Step Info " + testBaseTestContext.getAttribute("testName"));
//        googleOR.clickSearch();
//    }
//
//    @Test(description = "Click on Search", dependsOnMethods = "TC_003_failTest", alwaysRun = true)
//    public void TC_004_clickTest() {
//        ReportListener.extentTestThreadLocal.get().log(Status.INFO, "Step Info " + testBaseTestContext.getAttribute("testName"));
//        throw new SkipException("Skipping 4");
//    }
//
//    @Test(description = "Click on Search", dependsOnMethods = "TC_004_clickTest", alwaysRun = true)
//    public void TC_005_clickTest() {
//        ReportListener.extentTestThreadLocal.get().log(Status.INFO, "Step Info " + testBaseTestContext.getAttribute("testName"));
//        if(testBaseTestContext.getAttribute("testName").toString().equalsIgnoreCase("Regression")) {
//            //Assert.fail();
//        }
//    }
//
//    @Test(description = "Fail it", dependsOnMethods = "TC_002_enterText", alwaysRun = true)
//    public void TC_003_failTest() {
//        googleOR.enterText(HelperClass.testData.getProperty("searchText"));
//        googleOR.enterText("Naveed");
//        ReportListener.extentTestThreadLocal.get().log(Status.INFO, "Step Info " + testBaseTestContext.getAttribute("testName"));
//        googleOR.clickSecondButton();
//        //Assert.assertTrue(false);
//    }
}
