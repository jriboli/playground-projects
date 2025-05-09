package customAnnotation;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.internal.ConstructorOrMethod;

public class CustomAnnotationListener implements IInvokedMethodListener {

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        ConstructorOrMethod consOrMethod = method.getTestMethod().getConstructorOrMethod();
        CustomSetup disable = consOrMethod.getMethod().getDeclaringClass().getAnnotation(CustomSetup.class);
        if (disable != null) {
            // Custom setup logic before the test
            System.out.println("Custom Setup Executed...");
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        // You can also handle logic after the test runs
    }
}