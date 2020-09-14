package com.main.test;

import com.main.baseSetup.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

@Test(groups = "System")
public class MobileTest extends TestBase {
    @Test
    public void TC_Mob_001() {
        System.out.println("In mobile");
//
        System.out.println("current activity " + driverMobile.currentActivity());
        waitMobile.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.sec.android.app.samsungapps:id/lbl_heading")));
        WebElement elem = driverMobile.findElement(By.id("com.sec.android.app.samsungapps:id/lbl_heading"));

        String content = elem.getText();
        System.out.println("x " + elem.getLocation().getX());
        System.out.println("Content is " + content);
    }
}
