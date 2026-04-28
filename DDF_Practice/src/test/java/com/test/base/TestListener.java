package com.test.base;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;

import com.test.utilities.ScreenshotUtil;

public class TestListener implements ITestListener {

    public void onStart(ITestContext context) {
        System.out.println("=== Test Suite Started ===");
    }

    public void onFinish(ITestContext context) {
        System.out.println("=== Test Suite Finished ===");
    }

    public void onTestStart(ITestResult result) {
        System.out.println("STARTED: " + result.getName());
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("PASSED: " + result.getName());
    }

    public void onTestFailure(ITestResult result) {

        System.out.println("FAILED: " + result.getName());

        // Get driver from test class
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).driver;

        ScreenshotUtil.takeScreenshot(driver, result.getName());
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("SKIPPED: " + result.getName());
    }
}