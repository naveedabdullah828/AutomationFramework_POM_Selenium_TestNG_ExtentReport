package com.main.test;

import java.net.MalformedURLException;
import java.net.URL;

import com.main.utils.ReadExcel;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
public class HubNodeTest {
    WebDriver driver;
    @BeforeTest
    @Parameters({"node", "browser", "os"})
    public void beforeTest(String value, String browser, String osValue) throws MalformedURLException
    {
        System.out.println("Values " + value + " " + browser + " " + osValue);
        DesiredCapabilities ds;
        if(browser.equals("firefox")) {
            ds = DesiredCapabilities.firefox();
        } else if (browser.equals("chrome")) {
            ds = DesiredCapabilities.chrome();
        } else {
            ds = DesiredCapabilities.firefox();
        }

        if(osValue.equals("MAC")) {
            ds.setPlatform(Platform.MAC);
        } else {
            ds.setPlatform(Platform.ANY);
        }

        //value = "http://192.168.0.113:4444/wd/hub"; // server
        //value = "http://192.168.0.113:5566/wd/hub";

        driver=new RemoteWebDriver(new URL(value),ds);
        driver.get("http://demowebshop.tricentis.com/login");
        driver.manage().window().maximize();
    }
    @AfterTest
    public void afterTest()
    {
        driver.close();
    }
    @Test(dataProvider="dp1")
    public void testValidUsersDemoWebShop(String username,String password)
    {
        driver.findElement(By.id("Email")).sendKeys(username);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value='Log in']")).click();
        driver.findElement(By.linkText("Log out")).click();
        driver.findElement(By.linkText("Log in")).click();
    }
    @DataProvider(name="dp1")
    public Object[][] provideData()
    {
// to connect to excel & convert to object 2d array
        return ReadExcel.testExtractDataExcel();
    }
}