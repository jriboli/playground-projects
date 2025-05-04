package listeners;

import org.testng.IExecutionListener;

/*
    -- IExecutionListener --
    Purpose: Runs before and after entire test run, even before suite.
    Use Cases:
    - Timer for the full run
    - Resource init before TestNG spins up anything
 */

public class ExecutionListener implements IExecutionListener {

    @Override
    public void onExecutionStart() {
        System.out.println("=== TEST RUN STARTED ===");
    }

    @Override
    public void onExecutionFinish() {
        System.out.println("=== TEST RUN FINISHED ===");
    }
}
