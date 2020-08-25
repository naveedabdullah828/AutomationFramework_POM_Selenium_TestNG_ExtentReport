package com.main.listener;

import com.main.baseSetup.TestBase;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import java.util.concurrent.atomic.AtomicBoolean;

public class RetryFailedTest implements IRetryAnalyzer {
    int maxRetryCount = 1;
    int retryCount = 0;
    @Override
    synchronized public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            TestBase.retryStatusThread.set(new AtomicBoolean(true));
            return true;
        }
        TestBase.retryStatusThread.set(new AtomicBoolean(false));
        return false;
    }
}
