# What is it ?
It is an Automation Framework designed using 
```
 • Page Object Model using Page Factory pattern
```
and built on 
``` 
 • Selenium WebDriver tool,
 • Selenium Grid
 • Appium
 • TestNG testing framework,
 • Extent Report and
 • Maven Build Automation Tool
```
It uses Java Programming Language and using this framework you can create and run Test Scripts. 
The Extent Reports include Screenshots for failed test cases. 

## Features
```
 • Rerun Failed Test
 • Screenshot Capture for failed Test which is present in Extent Reports.
 • Grid support to run test in distributed environment and parallel mode using testNG
 ```

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
Clone the project, Add Tests and run. And you are done.

Sample Test Scripts for Google Search and Facebook Create New User is added. Replace them with your test.


## Please Note
Add only the TestNG Test annotations (@Test) in the test classes, the other TestNG Test annotations (@Before, @After) should be updated/added in the file called "TestBase.java", so that all Test Class can benefit from it.

Configure the parameters in TestNG.xml file correctly to run the gird setup or parallel setup.
