package com.test;

import com.main.objectRepo.GoogleOR;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.awt.event.KeyEvent;
import java.util.List;

import static com.main.utils.HelperClass.getUserDirectory;

public class StaleElementException {
    @Test
    void start() {
        System.setProperty("webdriver.chrome.driver",getUserDirectory() + "/Driver/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://www.google.com/");
        //driver.get("https://www.google.com/");
        WebElement searchTitle = driver.findElement(By.name("q"));
        searchTitle.sendKeys("Hello");
//        searchTitle.sendKeys(Keys.TAB);
//        WebElement dropDownList = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/form[1]/div[2]/div[1]/div[2]/div[2]"));
//        System.out.println("Is dropdown " + dropDownList.isDisplayed());
//        driver.findElements(By.name("btnK")).get(1).click();
        GoogleOR googleOR = new GoogleOR(driver);

//        solves Stale Element exception

//        List<WebElement> hyperLinks = driver.findElements(By.tagName("a"));
//        System.out.println("Before " + hyperLinks.size());
//        //hyperLinks.removeIf(val -> val.getText().isEmpty());
//        System.out.println("After " + hyperLinks.size());
//
//        hyperLinks.forEach(a -> System.out.println("All Text " + a.getText()));

//        for(int i = 0; i <= hyperLinks.size() - 1; i++) {
//            hyperLinks = driver.findElements(By.tagName("a"));
//            hyperLinks.removeIf(val -> val.getText().isEmpty());
//            WebElement link = hyperLinks.get(i);
//            System.out.println("Clicking " + link.getText());
//            link.click();
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            driver.navigate().back();
//        }

        //Creates staleElement
//        hyperLinks.forEach(b -> {
//            b.click();
//            driver.navigate().back();
//        });

        googleOR.traverseLinks();

//        hyperLinks.forEach(b -> System.out.println("Name " + b.getText()));
//        hyperLinks.removeIf(val -> !val.getText().isEmpty());


    }
}
