package com.NexustAPIAutomation.java;

import java.util.Collections;

import org.testng.TestNG;

public class RunFailedTests {
    public static void main(String[] args) {
        TestNG testng = new TestNG();
        // Specify the path to the testng-failed.xml file
        String failedTestPath = System.getProperty("user.dir") + "/test-output/testng-failed.xml";
        System.out.println("Running failed tests from: " + failedTestPath);
        testng.setTestSuites(Collections.singletonList(failedTestPath));
        testng.run();
    }
}