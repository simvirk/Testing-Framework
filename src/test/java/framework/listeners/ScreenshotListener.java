package framework.listeners;

import framework.drivermanager.DriverManager;
import framework.utils.Utils;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result){
       // Utils.captureScreenshot(DriverManager.getDriver(), result.getName(), true);
    }

    @Override
    public void onTestFailure(ITestResult result){
        Utils.captureScreenshot(DriverManager.getDriver(), result.getName(), false);
    }

}
