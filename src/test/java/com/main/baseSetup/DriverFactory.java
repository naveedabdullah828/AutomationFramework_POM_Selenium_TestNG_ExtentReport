package com.main.baseSetup;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {
    WebDriver driver;
    public WebDriver createDriverGrid(String browser, String osValue) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        ChromeOptions chromeOptions = null;
        FirefoxOptions firefoxOptions = null;
        switch (browser.toLowerCase()) {
            case "chrome" :
                chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--whitelisted-ips=''");
                capabilities.setBrowserName(BrowserType.CHROME);
                chromeOptions.merge(capabilities);
                break;

            case "firefox":
                firefoxOptions = new FirefoxOptions();
                capabilities.setBrowserName(BrowserType.FIREFOX);
                firefoxOptions.merge(capabilities);
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

            case "windows":
                System.out.println("In windows");
                capabilities.setCapability("platformName","WIN10");
                capabilities.setCapability("platform","WIN10");
                //capabilities.setPlatform(Platform.WIN10);
                break;
        }

        String url = "http://192.168.0.113:4444/wd/hub"; // serve
        //url = "http://192.168.0.116:5568/wd/hub";

        try {
            if(browser.equalsIgnoreCase("chrome") && !osValue.equalsIgnoreCase("mac")) {
                driver = new RemoteWebDriver(new URL(url),chromeOptions);
            } else if (browser.equalsIgnoreCase("firefox") && !osValue.equalsIgnoreCase("mac")) {
                driver = new RemoteWebDriver(new URL(url), firefoxOptions);
            } else {
                driver = new RemoteWebDriver(new URL(url),capabilities);
            }

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
