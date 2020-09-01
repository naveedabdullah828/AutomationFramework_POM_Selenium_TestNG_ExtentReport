package com.main.baseSetup;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Platform;
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
    RemoteWebDriver driver;
    public RemoteWebDriver createDriverGrid(String browser, String osValue, String deviceId) throws MalformedURLException {
        System.out.println("Browser " + browser + " os " + osValue);
        DesiredCapabilities capabilities = new DesiredCapabilities();;
        ChromeOptions chromeOptions = null;
        FirefoxOptions firefoxOptions = null;

        String remoteHost =System.getProperty("remoteHost");
        String url;
        if(remoteHost == null)
            url = "http://192.168.0.113:4444/wd/hub"; // server
        else {
            url = "http://"+remoteHost+":4444/wd/hub";
        }

        switch (osValue.toLowerCase()) {
            case "mac" :
                capabilities.setPlatform(Platform.MAC);
                if (browser.toLowerCase().equalsIgnoreCase("chrome")) {
                    capabilities.setBrowserName(BrowserType.CHROME);
                } else if (browser.toLowerCase().equalsIgnoreCase("firefox")) {
                    capabilities.setBrowserName(BrowserType.FIREFOX);
                } else {
                    System.err.println("Enter a valid browser");
                }
                driver = new RemoteWebDriver(new URL(url),capabilities);
                return driver;

            case "windows":
                capabilities.setCapability("platformName","WIN10");
                capabilities.setCapability("platform","WIN10");
                if (browser.toLowerCase().equalsIgnoreCase("chrome")) {
                    chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--whitelisted-ips=''");
                    capabilities.setBrowserName(BrowserType.CHROME);
                    chromeOptions.merge(capabilities);
                    driver = new RemoteWebDriver(new URL(url),chromeOptions);
                    return driver;
                } else if (browser.toLowerCase().equalsIgnoreCase("firefox")) {
                    firefoxOptions = new FirefoxOptions();
                    capabilities.setBrowserName(BrowserType.FIREFOX);
                    firefoxOptions.merge(capabilities);
                    driver = new RemoteWebDriver(new URL(url), firefoxOptions);
                    return driver;
                } else {
                    System.err.println("Enter a valid browser");
                }
                break;

            case "linux" :
                break;

            case "android" :
                if (!deviceId.equalsIgnoreCase("") && browser.equalsIgnoreCase("")) {
                    capabilities.setCapability("platformName", "Android");
                    capabilities.setCapability("deviceName", "Samsung");
                    capabilities.setCapability("udid", deviceId);
                    capabilities.setCapability("automationName", "Appium");
                    capabilities.setCapability("platformVersion","10");
                    //capabilities.setCapability("appPackage","tv.peel.app");
                    //capabilities.setCapability("appActivity","com.peel.main.Main");
                    capabilities.setCapability("appPackage","com.android.chrome");
                    capabilities.setCapability("appActivity","com.google.android.apps.chrome.Main");

                    capabilities.setCapability("noReset", false);
                    //capabilities.setCapability("appWaitActivity","com.peel.setup.CountrySetupSplashActivity");
                    AppiumDriver driver = new AppiumDriver<>(new URL(url), capabilities);
                    return driver;
                } else {
                    capabilities.setCapability("platformName", "Android");
                    capabilities.setCapability("deviceName", "Samsung");
                    capabilities.setCapability("udid", deviceId);
                    capabilities.setCapability("automationName", "Appium");
                    capabilities.setCapability("platformVersion","10");
                    if (browser.toLowerCase().equalsIgnoreCase("chrome")) {
                        System.out.println("In android else if ");
                        chromeOptions = new ChromeOptions();
                        chromeOptions.addArguments("--whitelisted-ips=''");
                        capabilities.setCapability("browserName", "chrome");
                        capabilities.setCapability("chromedriverExecutable", System.getProperty("webdriver.chrome.driver"));
                        chromeOptions.merge(capabilities);
                        driver = new RemoteWebDriver(new URL(url),chromeOptions);
                        return driver;
                    } else if (browser.toLowerCase().equalsIgnoreCase("firefox")) {
                        firefoxOptions = new FirefoxOptions();
                        capabilities.setCapability("browserName", "firefox");
                        capabilities.setCapability("firefoxdriverExecutable", System.getProperty("webdriver.gecko.driver"));
                        firefoxOptions.merge(capabilities);
                        driver = new RemoteWebDriver(new URL(url), firefoxOptions);
                        return driver;
                    }
                }
        }
        return null;
    }

    public RemoteWebDriver createDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "chrome" :
                driver = new ChromeDriver();
                break;
            case "headless" :
                ChromeOptions firefoxOptions = new ChromeOptions();
                firefoxOptions.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors","--silent");
                //driver = new HtmlUnitDriver();
                driver = new ChromeDriver(firefoxOptions);
                break;
            default:
                driver = null;
        }
        return driver;
    }
}