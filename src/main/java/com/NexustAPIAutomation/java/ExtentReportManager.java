package com.NexustAPIAutomation.java;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static final String REPORT_PATH = "test-output/ExtentReport.html";

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(REPORT_PATH);
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setDocumentTitle("Nexus API Automation Report");
            sparkReporter.config().setReportName("API Test Results");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Tester", "Automation");
        }
        return extent;
    }

    public static ExtentTest createTest(String testName) {
        ExtentTest extentTest = getInstance().createTest(testName);
        test.set(extentTest);
        return extentTest;
    }

    public static ExtentTest createTest(String testName, String description) {
        ExtentTest extentTest = getInstance().createTest(testName, description);
        test.set(extentTest);
        return extentTest;
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static String getReportPath() {
        return System.getProperty("user.dir") + File.separator + REPORT_PATH.replace("/", File.separator);
    }

    public static void logRequest(String method, String uri, String version, String body) {
        ExtentTest currentTest = getTest();
        if (currentTest != null) {
            currentTest.info("<b>Request:</b> [" + method + "] Version: " + version + " | URI: " + uri);
            if (body != null && !body.isEmpty()) {
                currentTest.info("<b>Request Body/Params:</b><br><pre>" + escapeHtml(body) + "</pre>");
            }
        } else {
            System.out.println("[ExtentReport] No active test. Request: " + method + " " + uri);
        }
    }

    public static void logResponse(int statusCode, String responseBody) {
        ExtentTest currentTest = getTest();
        if (currentTest != null) {
            String escapedBody = escapeHtml(truncate(responseBody));
            if (statusCode >= 200 && statusCode < 300) {
                currentTest.info("<b>Response [" + statusCode + "]:</b><br><pre>" + escapedBody + "</pre>");
            } else {
                currentTest.warning("<b>Response [" + statusCode + "]:</b><br><pre>" + escapedBody + "</pre>");
            }
        } else {
            System.out.println("[ExtentReport] No active test. Response: " + statusCode);
        }
    }

    public static void logValidation(String expected, String actual, boolean passed) {
        ExtentTest currentTest = getTest();
        if (currentTest != null) {
            String escapedExpected = escapeHtml(truncate(expected));
            String escapedActual = escapeHtml(truncate(actual));
            if (passed) {
                currentTest.pass("<b>Validation PASSED</b><br>"
                        + "<b>Expected:</b><br><pre>" + escapedExpected + "</pre><br>"
                        + "<b>Actual:</b><br><pre>" + escapedActual + "</pre>");
            } else {
                currentTest.fail("<b>Validation FAILED</b><br>"
                        + "<b>Expected:</b><br><pre>" + escapedExpected + "</pre><br>"
                        + "<b>Actual:</b><br><pre>" + escapedActual + "</pre>");
            }
        } else {
            System.out.println("[ExtentReport] No active test. Validation passed=" + passed);
        }
    }

    public static void logPass(String message) {
        ExtentTest currentTest = getTest();
        if (currentTest != null) {
            currentTest.pass(message);
        } else {
            System.out.println("[ExtentReport-PASS] " + message);
        }
    }

    public static void logFail(String message) {
        ExtentTest currentTest = getTest();
        if (currentTest != null) {
            currentTest.fail(message);
        } else {
            System.out.println("[ExtentReport-FAIL] " + message);
        }
    }

    public static void logFail(String message, Throwable throwable) {
        ExtentTest currentTest = getTest();
        if (currentTest != null) {
            currentTest.fail(message);
            currentTest.fail(throwable);
        } else {
            System.out.println("[ExtentReport-FAIL] " + message);
            if (throwable != null) {
                throwable.printStackTrace();
            }
        }
    }

    public static void logWarning(String message) {
        ExtentTest currentTest = getTest();
        if (currentTest != null) {
            currentTest.warning(message);
        } else {
            System.out.println("[ExtentReport-WARNING] " + message);
        }
    }

    public static void logInfo(String message) {
        ExtentTest currentTest = getTest();
        if (currentTest != null) {
            currentTest.info(message);
        } else {
            System.out.println("[ExtentReport-INFO] " + message);
        }
    }

    public static void logSkip(String message) {
        ExtentTest currentTest = getTest();
        if (currentTest != null) {
            currentTest.skip(message);
        } else {
            System.out.println("[ExtentReport-SKIP] " + message);
        }
    }

    public static void logSkip(String message, Throwable throwable) {
        ExtentTest currentTest = getTest();
        if (currentTest != null) {
            currentTest.skip(message);
            if (throwable != null) {
                currentTest.skip(throwable);
            }
        } else {
            System.out.println("[ExtentReport-SKIP] " + message);
        }
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }

    public static void removeTest() {
        test.remove();
    }

    private static String truncate(String text) {
        if (text == null)
            return "null";
        int maxLength = 5000;
        if (text.length() > maxLength) {
            return text.substring(0, maxLength) + "\n... [TRUNCATED - " + text.length() + " chars total]";
        }
        return text;
    }

    private static String escapeHtml(String text) {
        if (text == null)
            return "null";
        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}