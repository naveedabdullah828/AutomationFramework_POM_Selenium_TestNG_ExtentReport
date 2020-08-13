package com.main.test;

import com.main.utils.HelperClass;
import org.testng.annotations.Test;

public class FacebookTest extends Parent {

    @Test(description = "Launch FB")
    public void TC_101_launchFB() {
        facebookOR.launchFacebook(HelperClass.testData.getProperty("facebookUrl"));
    }

    @Test(description = "Will CLick on Create New Account")
    public void TC_102_clickButton() {
        facebookOR.createNewAccount();
    }

    @Test(description = "Close the Sign Up Form Button")
    public void TC_103_closeSignUpForm() {
        facebookOR.closeSignUpForm();
    }
}
