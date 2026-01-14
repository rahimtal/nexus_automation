package com.NexusAPI.Tests;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.testng.SkipException;
import org.testng.TestNG;
import org.testng.annotations.BeforeSuite;

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

    @BeforeSuite
    public void checkApiHealth() {
        String apiUrl = "http://your-api-endpoint.com/health"; // Replace with your API health check URL

        try {
            System.out.println("Checking API health at: " + apiUrl);
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000); // 5 seconds timeout
            connection.setReadTimeout(5000);

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                System.out.println("API is running. Response code: " + responseCode);
            } else {
                System.err.println("API is not running. Response code: " + responseCode);
                throw new SkipException("Skipping tests as API is not running.");
            }
        } catch (IOException e) {
            System.err.println("Failed to connect to the API: " + e.getMessage());
            throw new SkipException("Skipping tests as API is not reachable.");
        }
    }
}
