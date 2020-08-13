package com.main.listener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTest implements IRetryAnalyzer {
    int maxRetryCount = 2;
    int retryCount = 0;
    public static boolean retry = false;
    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            retry = true;
            return true;
        }
        retry = false;
        return false;
    }
}