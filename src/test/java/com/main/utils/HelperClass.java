package com.main.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class HelperClass {

    public static Properties testData;

    public static void takeScreenshot(WebDriver driver, String screenshotName) {
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
    }

    public static void loadData(String path) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(path);
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
            FileUtils.cleanDirectory(file);
            FileUtils.forceDelete(file);
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
}
