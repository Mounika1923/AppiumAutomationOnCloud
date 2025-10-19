package utils;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumDriver;

public class Listeners extends AppiumCommonUtils implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    AppiumDriver driver;
    
    @Override
    public void onStart(ITestContext context) {
        // Initialize ExtentReports once when suite starts
        System.out.println("Test Suite started: " + context.getName());
        if (extent == null) {
            extent = ExtentReportsConfiguration.getExtentReports();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Create test for each method (thread-safe)
        System.out.println("Test started: " + result.getMethod().getMethodName());
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test); // Store in ThreadLocal
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + result.getMethod().getMethodName());
        extentTest.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getMethod().getMethodName());
        extentTest.get().log(Status.FAIL, "Test Failed");
        extentTest.get().fail(result.getThrowable()); // Log the exception
        
        try {
			driver =(AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			extentTest.get().addScreenCaptureFromPath(getScreenShotPath(result.getMethod().getMethodName(),driver),result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + result.getMethod().getMethodName());
        extentTest.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Optional: Handle tests with success percentage
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        // Handle timeout failures specifically if needed
        onTestFailure(result);
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Suite finished: " + context.getName());
        // Flush the report
        if (extent != null) {
            extent.flush();
        }
    }
}