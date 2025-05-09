package analyzers;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int attempt = 1;
    private final int maxRetry = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (attempt <= maxRetry) {
            // To allow tests to pass but flagging them gives team observability without breaking CI/CD.
            result.setAttribute("wasRetried", true);
            // Need to make a change to the TestListener to report properly
            attempt++;
            return true; // Retry test
        }
        return false; // Fail test
    }
}
