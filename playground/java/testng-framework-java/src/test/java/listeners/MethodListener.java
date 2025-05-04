package listeners;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

/*
    -- IInvokeMethodListener --
    Purpose: Hooks before and after every method call, including @Before/@After methods.
    Use Cases:
    - Track execution time
    - Log all method names
    - Conditional test skipping
 */

public class MethodListener implements IInvokedMethodListener {

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult result) {
        System.out.println("About to execute: " + method.getTestMethod().getMethodName());
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult result) {
        System.out.println("Finished executing: " + method.getTestMethod().getMethodName());
    }
}
