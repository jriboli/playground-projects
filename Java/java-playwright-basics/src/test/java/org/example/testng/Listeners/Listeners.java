package org.example.testng.Listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {

    public void onTestStart(ITestResult result) {

    }

    public void onTestSuccess(ITestResult result) {

        System.out.println("Passed: TestCase - " + result.getTestName());
    }

    public void onTestFailure(ITestResult result) {

        System.out.println("Capturing Screenshot: For TestCase - " + result.getTestName());
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
