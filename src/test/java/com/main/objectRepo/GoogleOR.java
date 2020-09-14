package com.main.objectRepo;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import com.main.baseSetup.TestBase;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class GoogleOR {
    WebDriver driver;
    WebDriverWait wait;
    ITestContext iTestContext;
    Actions actions;

    public GoogleOR(ITestContext iTestContext) {
        this.iTestContext = iTestContext;
        this.driver = TestBase.getDriver();
        this.wait = TestBase.getWebDriverWait();
        actions = new Actions(driver);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(factory,this);
        //PageFactory.initElements(driver,this);
//        FluentWait fluentWait = new FluentWait(driver);
//        fluentWait.ignoring(NoSuchElementException.class).pollingEvery(Duration.ofSeconds(5)).withTimeout(Duration.ofSeconds(30))
//                .until(ExpectedConditions.visibilityOfElementLocated(By.name("")));
    }

    @FindBy(name = "q")
    WebElement searchInput;

//    @FindBy(css = "input.gNO89b[value='Google Search']")
//    WebElement btnClick;

    @FindBy(name = "btnK")
    List<WebElement> btnClick;

    @FindBy(css = "button.Tg7LZd[type='submit']")
    WebElement btnClickSecondScreen;

    public void launchGoogle(String url) {
        driver.get(url);
    }

    public void enterText(String input){
        //wait.until(ExpectedConditions.visibilityOf(searchInput));
        searchInput.clear();
        searchInput.sendKeys(input);
        //actions.moveToElement(searchInput).sendKeys(Keys.ESCAPE).build().perform();
    }

    public void clickSearch() {
        //wait.until(ExpectedConditions.visibilityOf(btnClick));
        System.out.println("size " + btnClick.size());
        System.out.println("bttn val " + btnClick.get(btnClick.size()-1).getText());
        System.out.println("bttn v " + btnClick.get(btnClick.size()-1).getAttribute("value"));
        //wait.until(ExpectedConditions.elementToBeClickable(btnClick));
        btnClick.get(btnClick.size()-1).click();
    }

    public void clickSecondButton() {
        //wait.until(ExpectedConditions.visibilityOf(btnClickSecondScreen));
        btnClickSecondScreen.click();
    }
}
