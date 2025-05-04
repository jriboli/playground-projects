package listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;

/*
    -- ISuiteListener --
    Purpose: Runs once per test suite (before/after all tests).
    Use Cases:
    - Suite-level reporting
    - Global setup/teardown (DB connections, environment setup)
 */

public class SuiteListener implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        System.out.println("SUITE STARTED: " + suite.getName());
    }

    @Override
    public void onFinish(ISuite suite) {
        System.out.println("SUITE FINISHED: " + suite.getName());
    }
}
