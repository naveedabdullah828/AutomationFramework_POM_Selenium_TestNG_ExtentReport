package com.main.test;

import com.main.utils.HelperClass;
import org.testng.annotations.*;

@Test(groups = "System")
public class SampleTest extends TestBase{
    @Test(description = "Launch Site", groups = "Regression")
    public void TC_201_launchSite(){
        sampleOR.launchURL(HelperClass.testData.getProperty("sampleSiteUrl"));
    }

    @Test(description = "Enter username", groups = "Regression")
    public void TC_202_enterUserName(){
        sampleOR.enterUsername(HelperClass.testData.getProperty("username"));
    }

    @Test(description = "Enter Password")
    public void TC_203_enterPassword(){
        sampleOR.enterPassword(HelperClass.testData.getProperty("password"));
    }
}