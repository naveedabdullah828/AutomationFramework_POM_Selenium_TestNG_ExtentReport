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
    @Parameters("node")
    public void beforeTest(String value) throws MalformedURLException
    {
//        DesiredCapabilities ds=DesiredCapabilities.chrome();
//        ds.setPlatform(Platform.ANY);
        //value = "http://192.168.0.113:4444/wd/hub"; // server
        //value = "http://192.168.0.113:5566/wd/hub";

        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.merge(ds);

        driver=new RemoteWebDriver(new URL(value),chromeOptions);
        driver.manage().window().maximize();
        driver.get("http://demowebshop.tricentis.com/login");
    }
    @AfterTest
    public void afterTest()
    {
        //driver.close();
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