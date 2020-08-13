# What is it ?
It is an Automation Framework designed using 
```
 • Page Object Model using Page Factory pattern
```
and built on 
``` 
 • Selenium WebDriver tool, 
 • TestNG testing framework,
 • Extent Report and
 • Maven Build Automation Tool
```
Which can be used to create and run Test Scripts. Using Extent Reports it provides reports including Screenshots for failed test cases. 

## Driver Support
• Currently it has Firefox driver support. For other Drivers, replace the FirefoxDriver with your preferred driver and the System Property.

## Directory Structure
```
• ObjectRepo => components and locators of each webpage
• Test       => actual test classes that rely on page objects
• Listener   => classes that implement the TestNG listeners interface.
• Reports    => Extent Report config class.
• TestData   => Test data used in Automation testing
• Utils      => Helper classes that contain methods used across the framework.
```

# How to use ?
Clone the project, Setup the driver and run. And you are done.

Sample Test Scripts for Google Search and Facebook Create New User is added. Replace them with your test.


## Please Note
Add only the TestNG Test annotations (@Test) in the test classes, the other TestNG Test annotations (@Before, @After) should be updated/added in the file called "Parent.java", so that all Test Class can benefit from it.
