package com.api.reporting;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.api.utils.Entity;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class Setup implements ITestListener {

    private static ExtentReports extinstance;

    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    static {
        String reportPath = "API Report/extent-report.html";
        extinstance = ExtentReportManager.createInstance(reportPath);
    }

    @Override
    public void onTestStart(ITestResult result) {

        String testName = result.getTestClass().getName();
     // Split the package/class name and get only the last value
        String[] parts = testName.split("\\.");
        testName = parts[parts.length - 1];
        // Include the DataProvider test-case name, if available
        if (result.getParameters() != null
                && result.getParameters().length > 0
                && result.getParameters()[0] instanceof Entity) {

            Entity entity = (Entity) result.getParameters()[0];
            testName += "<br>TestCase: " + entity.getTestCaseName();
        }

        ExtentTest test = extinstance.createTest(testName);

        // Store the same ExtentTest against this TestNG invocation
        result.setAttribute("extentTest", test);
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        ExtentTest test = (ExtentTest) result.getAttribute("extentTest");

        if (test != null) {
            test.pass("Test Case Passed");
        }

        extentTest.remove();
    }

    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTest test = (ExtentTest) result.getAttribute("extentTest");

        if (test != null) {
            Throwable error = result.getThrowable();

            if (error != null) {
                test.fail(error);
            } else {
                test.fail("Test Case Failed");
            }
        }

        extentTest.remove();
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        ExtentTest test = (ExtentTest) result.getAttribute("extentTest");

        if (test != null) {
            Throwable error = result.getThrowable();

            if (error != null) {
                test.skip(error);
            } else {
                test.skip("Test Case Skipped");
            }
        }

        extentTest.remove();
    }

    @Override
    public void onFinish(ITestContext context) {

        if (extinstance != null) {
            extinstance.flush();
        }
    }

    public static ExtentReports getExtentInstance() {
        return extinstance;
    }
}