package com.main.test;

import com.main.utils.HelperClass;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups = "System")
public class FacebookTest extends TestBase {

    @Test(description = "Launch FB", groups = "Regression")
    public void TC_101_launchFB() {
        facebookOR.launchFacebook(HelperClass.testData.getProperty("facebookUrl"));
    }

    @Test(description = "Will CLick on Create New Account", groups = "Regression")
    public void TC_102_clickButton() {
        facebookOR.createNewAccount();
    }

    @Test(description = "Close the Sign Up Form Button")
    public void TC_103_closeSignUpForm() {
        facebookOR.closeSignUpForm();
        Assert.assertTrue(false);
    }
}
