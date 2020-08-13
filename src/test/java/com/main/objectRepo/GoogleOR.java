package com.main.objectRepo;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleOR {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(name = "q")
    WebElement searchInput;

    @FindBy(name = "btnK")
    WebElement btnClick;

    public GoogleOR(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver,this);
    }

    public void launchGoogle(String url) {
        driver.get(url);
    }

    public void enterText(String input){
        searchInput.clear();
        searchInput.sendKeys(input);
        searchInput.sendKeys(Keys.ESCAPE);
    }

    public void clickSearch() {
        try {
            btnClick.click();
        } catch (ElementNotInteractableException exception) {
            if(searchInput.isDisplayed())
                searchInput.sendKeys(Keys.ENTER);
        }
    }
}
