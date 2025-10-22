package listeners;

import objectManager.ObjectManager;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class FailedTestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getName();
        String className = result.getInstanceName();
        String timestamp = String.valueOf(System.currentTimeMillis());

        try {
            ObjectManager objectManager = (ObjectManager) result.getInstance();
        } catch (Exception e) {
            System.err.println("Failed");
        }
    }
}
