package com.main.test;

import com.main.listener.RetryFailedTest;
import com.main.utils.HelperClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GoogleTest extends TestBase {
    @Test(description = "Launch Google")
    public void TC_001_launchGoogle(){
        googleOR.launchGoogle(HelperClass.testData.getProperty("inputUrl"));
    }

    @Test(description = "Enter word to search")
    public void TC_002_enterText() {
        googleOR.enterText(HelperClass.testData.getProperty("searchText"));
    }

    @Test(description = "Click on Search")
    public void TC_004_clickTest() {
        googleOR.clickSearch();
        if (RetryFailedTest.retry)
            Assert.fail();
        else
            Assert.assertTrue(true);
    }

    @Test(description = "Fail it")
    public void TC_003_failTest() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(false);
        googleOR.enterText(HelperClass.testData.getProperty("naveed"));
        if(!RetryFailedTest.retry) {
            softAssert.assertAll();
        }
        if (RetryFailedTest.retry) {
            RetryFailedTest.retry = false;
        }
    }
}
