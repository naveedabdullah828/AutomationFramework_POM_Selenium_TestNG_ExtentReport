package com.main.objectRepo;

import com.main.baseSetup.TestBase;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;

public class GoogleOR {
    WebDriver driver;
    WebDriverWait wait;
    ITestContext iTestContext;

    public GoogleOR(ITestContext iTestContext) {
        this.iTestContext = iTestContext;
        this.driver = TestBase.getDriver();
        this.wait = TestBase.getWebDriverWait();
        PageFactory.initElements(driver,this);
    }

    @FindBy(name = "q")
    WebElement searchInput;

    @FindBy(name = "btnK")
    WebElement btnClick;

    @FindBy(css = "button.Tg7LZd[type='submit']")
    WebElement btnClickSecondScreen;

    public void launchGoogle(String url) {
        driver.get(url);
    }

    public void enterText(String input){
        wait.until(ExpectedConditions.visibilityOf(searchInput));
        searchInput.clear();
        searchInput.sendKeys(input);
        searchInput.sendKeys(Keys.ESCAPE);
    }

    public void clickSearch() {
        wait.until(ExpectedConditions.visibilityOf(btnClick));
        btnClick.click();
    }

    public void clickSecondButton() {
        wait.until(ExpectedConditions.visibilityOf(btnClickSecondScreen));
        btnClickSecondScreen.click();
    }
}
