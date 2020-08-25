package com.main.objectRepo;

import com.main.baseSetup.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;

public class FacebookOR {
    WebDriver driver;
    WebDriverWait wait;
    ITestContext iTestContext;

    public FacebookOR(ITestContext iTestContext) {
        this.iTestContext = iTestContext;
        this.driver = TestBase.getDriver();
        this.wait = TestBase.getWebDriverWait();
        PageFactory.initElements(driver, this);
    }

    @FindBy(partialLinkText = "Create New Account")
    WebElement createAccountBtn;

    @FindBy(css = "._8idr.img")
    WebElement closeBtn;

    public void launchFacebook(String url) {
        driver.get(url);
    }

    public void createNewAccount() {
        createAccountBtn.click();
    }

    public void closeSignUpForm() {
        wait.until(ExpectedConditions.visibilityOf(closeBtn));
        closeBtn.click();
    }
}
