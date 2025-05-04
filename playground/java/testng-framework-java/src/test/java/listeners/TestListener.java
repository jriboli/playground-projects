package listeners;

import com.microsoft.playwright.Page;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.nio.file.Paths;

/*
    -- ITestListener --
    Purpose: Fires for each test method.
    Use Cases:
    - Screenshots on failure
    - Logging test status
    - Integrating with test reporting tools
 */

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: " + result.getName());
        // Take a screenshot using Playwright
        Page page = (Page) result.getAttribute("page");
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("resources/screenshots/" + result.getName() + ".png")));
    }

    // More: onTestSkipped, onFinish, etc.
}
