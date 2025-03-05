package org.example.testng.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class Listeners implements ITestListener {

    public void onTestStart(ITestResult result) {

    }

    public void onTestSuccess(ITestResult result) {

        System.out.println("Passed: TestCase - " + result.getName());
    }

    public void onTestFailure(ITestResult result) {

        System.out.println("Capturing Screenshot: For TestCase - " + result.getName());
        Reporter.log("Screenshot link");
    }

    public void onTestSkipped(ITestResult result) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    public void onTestFailedWithTimeout(ITestResult result) {

        this.onTestFailure(result);
    }

    public void onStart(ITestContext context) {

    }

    public void onFinish(ITestContext context) {

    }
}
