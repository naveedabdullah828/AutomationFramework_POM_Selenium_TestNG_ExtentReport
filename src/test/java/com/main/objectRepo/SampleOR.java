package com.main.objectRepo;

import com.main.baseSetup.TestBase;
import okhttp3.HttpUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

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

    @FindBy(xpath = "//div[@class='gb_oe gb_i gb_Ng gb_Dg']/div")
    List<WebElement> links;

    public void iterateLinks() {
        System.out.println("Len " + links.size());
        HttpURLConnection huc = null;
        for (int i = 0; i < links.size(); i++) {
            WebElement link = links.get(i);
            System.out.println(link.getText());
            //wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("a")));

            WebElement ll = link.findElement(By.tagName("a"));
            String url = ll.getAttribute("href");
            try {
                huc = (HttpURLConnection) new URL(url).openConnection();
                huc.setRequestMethod("HEAD");
                huc.connect();
                int resCode = huc.getResponseCode();
                System.out.println("Resp code " + resCode);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //link.findElement(By.tagName("a")).click();
//            driver.switchTo().window(mainWindow);
//            WebElement linkSub = link.findElement(By.tagName("a"));
//            actions.keyDown(Keys.COMMAND)
//                    .click(linkSub)
//                    .keyUp(Keys.COMMAND)
//                    .build().perform();
//
//            try {
//                Thread.sleep(1000);
//                driver.navigate().back();
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            links = driver.findElements(By.xpath("//div[@class='gb_oe gb_i gb_Ng gb_Dg']/div"));
//            Set<String> windows = driver.getWindowHandles();
//            System.out.println("Size of windows " + windows.size());
//            String childHanle = null;
//            for(String window : windows) {
//                System.out.println("Window handle " + window);
//                if(!window.equalsIgnoreCase(mainWindow))
//                    childHanle = window;
//            }
//            driver.switchTo().window(childHanle);
//            Thread.sleep(1000);
//            driver.close();
        }
    }

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
