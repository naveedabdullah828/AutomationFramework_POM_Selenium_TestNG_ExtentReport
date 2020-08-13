package com.main.objectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FacebookOR {
    WebDriver driver;
    WebDriverWait wait;

            @FindBy(partialLinkText = "Create New Account")
    WebElement createAccountBtn;

    @FindBy(css = "._8idr.img")
    WebElement closeBtn;


    public FacebookOR(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

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
