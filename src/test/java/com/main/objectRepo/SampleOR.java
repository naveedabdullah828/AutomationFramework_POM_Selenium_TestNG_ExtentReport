package com.main.objectRepo;

import com.main.baseSetup.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;

public class SampleOR {
    ITestContext iTestContext;
    WebDriver driver;
    WebDriverWait wait;

    public SampleOR(ITestContext iTestContext) {
        this.iTestContext = iTestContext;
        this.driver = TestBase.getDriver();
        this.wait = TestBase.getWebDriverWait();
        PageFactory.initElements(driver,this);
    }

    @FindBy(name = "uid")
    WebElement username;

    @FindBy(name = "password")
    WebElement password;

    public void launchURL(String url) {
        driver.get(url);
    }

    public void enterUsername(String usernameData) {
        username.sendKeys(usernameData);
    }

    public void enterPassword(String passwordData) {
        password.sendKeys(passwordData);
    }
}
