package com.NexustAPIAutomation.java;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentTestNGListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        System.out.println("======================================================");
        System.out.println("  Test Suite Started: " + context.getName());
        System.out.println("======================================================");
    }

    @Override
    public void onTestStart(ITestResult result) {
        String className = result.getTestClass().getRealClass().getSimpleName();
        String methodName = result.getMethod().getMethodName();
        String testName = className + " :: " + methodName;
        String description = result.getMethod().getDescription();

        ExtentTest test;
        if (description != null && !description.isEmpty()) {
            test = ExtentReportManager.createTest(testName, description);
        } else {
            test = ExtentReportManager.createTest(testName);
        }

        // Assign groups as categories
        String[] groups = result.getMethod().getGroups();
        if (groups.length > 0) {
            test.assignCategory(groups);
        }

        // Log priority
        int priority = result.getMethod().getPriority();
        ExtentReportManager.logInfo("Priority: " + priority);
        ExtentReportManager.logInfo("Test Class: " + className);

        System.out.println("  Starting: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        long duration = result.getEndMillis() - result.getStartMillis();

        ExtentReportManager.logPass(MarkupHelper.createLabel(
                testName + " PASSED (" + duration + "ms)", ExtentColor.GREEN).getMarkup());

        System.out.println("  PASSED:  " + testName + " (" + duration + "ms)");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        long duration = result.getEndMillis() - result.getStartMillis();
        Throwable throwable = result.getThrowable();

        ExtentReportManager.logFail(MarkupHelper.createLabel(
                testName + " FAILED (" + duration + "ms)", ExtentColor.RED).getMarkup());

        if (throwable != null) {
            ExtentReportManager.logFail("<b>Error Message:</b> " + throwable.getMessage());

            // Log stack trace (limited to 15 lines)
            StringBuilder sb = new StringBuilder();
            sb.append(throwable.toString()).append("\n");
            StackTraceElement[] stackTrace = throwable.getStackTrace();
            int limit = Math.min(stackTrace.length, 15);
            for (int i = 0; i < limit; i++) {
                sb.append("    at ").append(stackTrace[i].toString()).append("\n");
            }
            if (stackTrace.length > 15) {
                sb.append("    ... ").append(stackTrace.length - 15).append(" more\n");
            }
            ExtentReportManager.logFail("<b>Stack Trace:</b> <pre>" + sb.toString() + "</pre>");
        }

        System.out.println("  FAILED:  " + testName + " (" + duration + "ms)");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        Throwable throwable = result.getThrowable();

        ExtentReportManager.logSkip(MarkupHelper.createLabel(
                testName + " SKIPPED", ExtentColor.ORANGE).getMarkup());

        if (throwable != null) {
            ExtentReportManager.logSkip("<b>Reason:</b> " + throwable.getMessage());
        }

        System.out.println("  SKIPPED: " + testName);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not commonly used
    }

    @Override
    public void onFinish(ITestContext context) {
        // Flush report
        ExtentReportManager.flush();

        // Print summary to console
        int passed = context.getPassedTests().size();
        int failed = context.getFailedTests().size();
        int skipped = context.getSkippedTests().size();
        int total = passed + failed + skipped;
        double passPercent = total > 0 ? (passed * 100.0 / total) : 0;

        System.out.println("======================================================");
        System.out.println("  Test Suite Finished: " + context.getName());
        System.out.println("------------------------------------------------------");
        System.out.println("  TOTAL:     " + total);
        System.out.println("  PASSED:    " + passed);
        System.out.println("  FAILED:    " + failed);
        System.out.println("  SKIPPED:   " + skipped);
        System.out.println("  PASS RATE: " + String.format("%.1f", passPercent) + "%");
        System.out.println("------------------------------------------------------");
        System.out.println("  Report: " + ExtentReportManager.getReportPath());
        System.out.println("======================================================");
    }
}