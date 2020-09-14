package com.main.test;

import com.main.baseSetup.TestBase;
import org.junit.rules.Timeout;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.time.LocalTime;
import java.util.List;

public class SampleTest extends TestBase {

    @Test(groups = "System")
    public void rightClickTest() throws InterruptedException, AWTException {

        driver.manage().window().maximize();

        Actions actions = new Actions(driver);
        driver.get("http://www.google.com");

        /**
         Scroll WebSite
         1. Till element => js.executeScript("arguments[0].scrollIntoView();",element);
         2. X,Y => js.executeScript("window.scrollBy(0, 2000)");
         3. Scroll to bottom of page => js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
         **/

        //driver.get("https://www.guru99.com/scroll-up-down-selenium-webdriver.html");
        //driver.get("https://onepagelove.com/tag/horizontal-scroll");
        //WebElement element = driver.findElement(By.cssSelector("i[class='fa fa-book']"));
//        List<WebElement> element;
//        JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("arguments[0].scrollIntoView();",element);
        //js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//        int co = 0;
//        while (co < 35) {
//            js.executeScript("window.scrollBy(0, 2000)");
//            co++;
//            Thread.sleep(750);
//            System.out.println("Count " + co);
//            element = driver.findElements(By.xpath("//a[@title='NETWiSE Strategies']"));
//            if(element.size()>0)
//                break;
//        }

        /**
            Upload Image
         **/
//        driver.get("http://www.simpleimageresizer.com/");
//        driver.findElement(By.xpath("//input[@id='fileInput']")).sendKeys("/Users/naveedabdullah/Documents/20190128_162202.jpg");

        /**
         Copy Paste using
         1. Action and
         2. Robot

         Some Mouse Events using Robot
         **/

//        driver.get("http://www.google.com");
//        WebElement element = driver.findElement(By.name("q"));
//        element.sendKeys("Hello how are you");
//        actions.keyDown(element,Keys.COMMAND).sendKeys("a").sendKeys("x").keyUp(Keys.COMMAND).build().perform();
//        actions.keyDown(element,Keys.COMMAND).sendKeys("v").keyUp(Keys.COMMAND).build().perform();
//        actions.moveToElement(element).click().sendKeys(Keys.ARROW_DOWN).build().perform();
//        actions.moveToElement(element).click().sendKeys(Keys.ARROW_DOWN).build().perform();
        //Thread.sleep(1000);
//        Robot robot = new Robot();
//        robot.mouseMove(600, 400);
//        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
//
//        robot.keyPress(KeyEvent.VK_DOWN);
//        robot.keyRelease(KeyEvent.VK_DOWN);
//        Thread.sleep(1000);
//        robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
//        robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
//        Thread.sleep(1000);
//        robot.keyPress(KeyEvent.VK_DOWN);
//        robot.keyRelease(KeyEvent.VK_DOWN);
//
//        robot.keyPress(KeyEvent.VK_DOWN);
//        robot.keyRelease(KeyEvent.VK_DOWN);
//        robot.keyPress(KeyEvent.VK_DOWN);
//        robot.keyRelease(KeyEvent.VK_DOWN);
//
//        robot.keyPress(KeyEvent.VK_DOWN);
//        robot.keyRelease(KeyEvent.VK_DOWN);
//
//        robot.keyPress(KeyEvent.VK_ENTER);

        /**
         Handle ALerts
         **/

//        driver.switchTo().window(driver.getWindowHandle());
        sampleOR.iterateLinks();
//        WebElement element = driver.findElement(By.name("q"));
//        element.click();

//        driver.get("http://demo.guru99.com/test/delete_customer.php");
//        driver.findElement(By.name("cusid")).sendKeys("111111");
//        driver.findElement(By.name("submit")).click();
//        Alert aleert = wait.until(ExpectedConditions.alertIsPresent());
//        if (aleert == null)
//            System.out.println("No alert");
//        else {
//            System.out.println("Alert present");
//            System.out.println(aleert.getText());
//            aleert.accept();
//            aleert = wait.until(ExpectedConditions.alertIsPresent());
//            System.out.println(aleert.getText());
//            aleert.accept();
//        }
//
//        driver.findElement(By.name("cusid")).sendKeys("111111");
//        driver.findElement(By.name("submit")).click();

        /**
         Select a value from Drop Down
         **/

//        driver.get("http://demo.guru99.com/test/newtours/register.php");
//        Select country = new Select(driver.findElement(By.name("country")));
//        country.selectByVisibleText("ZIMBABWE");
//        country.selectByIndex(0);
//        List<WebElement> countries = country.getOptions();
//        System.out.println(countries.size());
//        for(WebElement countr : countries) {
//            System.out.println(countr.getText());
//        }
//
//        System.out.println("First " + country.getFirstSelectedOption().getText());
//
//        List<WebElement> selectedCountry = country.getAllSelectedOptions();
//        System.out.println(selectedCountry.size());

        /**
         Radio Button CheckBoxes
         **/

//        driver.get("http://demo.guru99.com/test/radio.html");
//        List<WebElement> radioGroup = driver.findElements(By.xpath("//input[@type='radio']"));
//        System.out.println(radioGroup.size());
//        for(WebElement radio : radioGroup) {
//            System.out.println("valu " + radio.getAttribute("value"));
//        }

        /**
         Custom Waits
         **/

//        driver.get("https://maven.iais.fraunhofer.de/artifactory/remote-repos/com/github/PeelTechnologies/android-amplitude-sampler/1.0.2/android-amplitude-sampler-1.0.2.jar");
//        Thread.sleep(2000);
//        System.out.println(driver.switchTo().alert().getText());
//        int count = 0;
//        boolean found = false;
//        driver.get("https://www.softwaretestingmaterial.com/handle-authentication-popup-window/");
//        while (count < 4) {
//            System.out.println("Count " + count + " time " + LocalTime.now());
//            count++;
//            try {
//                WebElement element = wait
//                        .ignoring(NoSuchElementException.class)
//                        .until(ExpectedConditions.visibilityOfElementLocated(By.id("onesignal-slidedown-cancel-button")));
//                if(element.isDisplayed())
//                    break;
//
//            } catch (TimeoutException e) {
//                System.out.println("In Timeout");
//            }
//        }
//        driver.findElement(By.id("onesignal-slidedown-cancel-button")).click();



        //driver.switchTo().alert();

        //actions.contextClick(element).build().perform();
        //actions.sendKeys(Keys.ARROW_DOWN).build().perform();
        //actions.sendKeys(Keys.ARROW_DOWN).build().perform();


    }
}