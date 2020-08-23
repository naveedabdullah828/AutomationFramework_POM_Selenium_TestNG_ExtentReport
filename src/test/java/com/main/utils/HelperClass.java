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
        String userDirectory = getUserDirectory();
        String fileSeparator = getFileSeparator();

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String destination = userDirectory + fileSeparator + "Screenshots" + fileSeparator + screenshotName +".png";
        File destinationFile = new File(destination);
        try {
            FileUtils.copyFile(source, destinationFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("File Copy Failed " + e.getMessage());
        }

        Path path = Paths.get(userDirectory);
        String imagePath = fileSeparator + path.getFileName() + fileSeparator + "Screenshots" + fileSeparator + screenshotName + ".png";
        return imagePath;
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
        File file = new File(getUserDirectory() + getFileSeparator() + "Screenshots");
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
        return System.getProperty("file.separator");
    }

    public static String getUserDirectory() {
        return System.getProperty("user.dir");
    }

    public static boolean isParallelTest(ITestContext iTestContext) {
        if(ParallelMode.TESTS.equals(iTestContext.getCurrentXmlTest().getParallel()))
            return true;
        else
            return false;
    }
}
