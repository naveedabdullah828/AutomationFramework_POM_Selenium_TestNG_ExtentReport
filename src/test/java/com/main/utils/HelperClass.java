package com.main.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.xml.XmlSuite.ParallelMode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.*;

public class HelperClass {

    public static Properties testData;
    public static List<ITestNGMethod> retriedTest = new LinkedList<>();

    public static String takeScreenshot(WebDriver driver, String screenshotName) {
        String fileSeparator = getFileSeparator();

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String destination = "." + fileSeparator + "TestReport" + fileSeparator + "Screenshots" + fileSeparator + screenshotName +".png";
        File destinationFile = new File(destination);
        try {
            FileUtils.copyFile(source, destinationFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("File Copy Failed " + e.getMessage());
        }

        destination = "."+ fileSeparator + "Screenshots" + fileSeparator + screenshotName + ".png";
        return destination;
    }

    public static void loadData() {
        String userDirectory = HelperClass.getUserDirectory();
        String fileSeparator = HelperClass.getFileSeparator();
        String testDataPath = userDirectory + fileSeparator + "TestData" + fileSeparator + "TestData.properties";
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(testDataPath);
            testData = new Properties();
            testData.load(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAndCreateDirectory() {
        File file = new File(getUserDirectory() + getFileSeparator() +"TestReport" + getFileSeparator() + "Screenshots");
        try {
            if (file.exists()) {
                FileUtils.cleanDirectory(file);
                FileUtils.forceDelete(file);
            }
            FileUtils.forceMkdir(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFileSeparator() {
        if(getOS().contains("Windows")){
            return "\\\\";
        }
        return System.getProperty("file.separator");
    }

    private static String getOS() {
        return System.getProperty("os.name");
    }

    public static String getUserDirectory() {
        return System.getProperty("user.dir");
    }

    public static boolean isParallelTest(ITestContext iTestContext) {
        return ParallelMode.TESTS.equals(iTestContext.getCurrentXmlTest().getParallel());
    }

    public static boolean isHeadless(ITestContext iTestContext, WebDriver driver) {
        return null != iTestContext.getAttribute("headless") && iTestContext.getAttribute("headless").equals(driver.hashCode());
    }

    public static void setDriverPathForWindows() {
        if(getOS().toLowerCase().contains("windows")) {
            System.setProperty("webdriver.chrome.driver",getUserDirectory() + "/Driver/chromedriver.exe");
            System.setProperty("webdriver.gecko.driver",getUserDirectory() + "/Driver/geckodriver.exe");
        } else if (getOS().toLowerCase().contains("mac os")) {
            System.setProperty("webdriver.chrome.driver",getUserDirectory() + "/Driver/chromedriver");
            System.setProperty("webdriver.gecko.driver",getUserDirectory() + "/Driver/geckodriver");
        }
    }
}
