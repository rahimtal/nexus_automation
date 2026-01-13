package com.NexustAPIAutomation.java;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.testng.TestNG;

public class TestExecutor {
    private static final int MAX_RETRIES = 2;
    private static final String projectRoot = System.getProperty("user.dir");
    private static final String MAIN_SUITE_PATH = projectRoot + File.separator + "testng.xml";
    public static void main(String[] args) {
        runTestNGSuitesWithRetries(MAIN_SUITE_PATH, MAX_RETRIES);
    }

    public static void runTestNGSuitesWithRetries(String mainSuite, int maxRetries) {
        System.out.println("==================== Started Execution  ====================");
        TestNG testng = new TestNG();
        List<String> suites = new ArrayList<>();
        suites.add(mainSuite);
        testng.setTestSuites(suites);
        testng.run();
        // Debug logs
        System.out.println("➡ hasFailure(): " + testng.hasFailure());
        System.out.println("➡ hasSkip(): " + testng.hasSkip());
        // Wait to ensure testng-failed.xml is written
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // Check if we should retry
        System.out.println("==================== Test Execution Finished ====================");
    }
}
