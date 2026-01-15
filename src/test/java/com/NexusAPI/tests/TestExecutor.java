package com.NexusAPI.Tests;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class TestExecutor {
    private static final int MAX_RETRIES = 2;
    private static final String PROJECT_ROOT = System.getProperty("user.dir");
    private static final String MAIN_SUITE_PATH = PROJECT_ROOT + File.separator + "testng.xml";

    public static void main(String[] args) {
        runTestNGSuitesWithRetries(MAIN_SUITE_PATH, MAX_RETRIES);
    }

    public static void runTestNGSuitesWithRetries(String mainSuite, int maxRetries) {
        System.out.println("==================== Started Execution  ====================");
        TestNG testng = new TestNG();
        List<String> suites = new ArrayList<>();
        suites.add(mainSuite);
        testng.setTestSuites(suites);

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            System.out.println("Attempt " + attempt + " of " + maxRetries);
            testng.run();

            if (!testng.hasFailure()) {
                System.out.println("All tests passed on attempt " + attempt);
                break;
            }

            if (attempt == maxRetries) {
                System.out.println("Tests failed after " + maxRetries + " attempts.");
            }
        }

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
