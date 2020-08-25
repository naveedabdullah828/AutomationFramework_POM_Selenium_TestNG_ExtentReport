package com.main.baseSetup;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {
    WebDriver driver;
    public WebDriver createDriverGrid(String browser, String osValue) {
        DesiredCapabilities capabilities;
        switch (browser.toLowerCase()) {
            case "chrome" :
                capabilities = DesiredCapabilities.chrome();
                break;

            case "firefox":
                capabilities = DesiredCapabilities.firefox();
                break;
            default :
                capabilities = null;
        }

        switch (osValue.toLowerCase()) {
            case "mac" :
                capabilities.setPlatform(Platform.MAC);
                break;
            case "android" :
                capabilities = DesiredCapabilities.android();
                capabilities.setCapability("platformName", "Android");
                capabilities.setCapability("deviceName", "RZ8M22QC4MW");
                capabilities.setCapability("automationName", "UiAutomator2");
                capabilities.setCapability("platformVersion","10");
                capabilities.setCapability("browserName", "Chrome");
                capabilities.setCapability("chromedriverExecutable", System.getProperty("webdriver.chrome.driver"));
                System.out.println("In Android " + System.getProperty("webdriver.chrome.driver"));
                break;

            default:
                capabilities.setPlatform(Platform.ANY);
        }

        String url = "http://192.168.0.113:4444/wd/hub"; // server
        //value = "http://192.168.0.113:5566/wd/hub";

        try {
            driver = new RemoteWebDriver(new URL(url),capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    public WebDriver createDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "chrome" :
                driver = new ChromeDriver();
                break;
            default:
                driver = null;
        }
        return driver;
    }
}
