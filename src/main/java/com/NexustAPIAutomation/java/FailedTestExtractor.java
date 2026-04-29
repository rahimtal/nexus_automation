package com.NexustAPIAutomation.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class to extract failed tests from TestSuite.txt output
 * and generate a testng-failed.xml file to re-run only the failed tests.
 * 
 * Usage: java FailedTestExtractor <input_file> [output_file]
 * Example: java FailedTestExtractor target/test-output/TestSuite.txt testng-failed.xml
 */
public class FailedTestExtractor {

    private static final String FAILURE_PATTERN = "<<< FAILURE!";
    private static final String TEST_PATTERN = "^(com\\.NexusAPI\\.Tests\\.[^\\s]+)\\s+Time elapsed:";
    private static final String PACKAGE_PATTERN = "^(com\\.NexusAPI\\.Tests\\.[^.]+)";

    /**
     * Extract failed tests from TestSuite.txt
     * @param testSuiteFile Path to TestSuite.txt file
     * @return Set of failed test class names
     */
    public static Set<String> extractFailedTests(String testSuiteFile) throws IOException {
        Set<String> failedTests = new HashSet<>();
        Pattern testPattern = Pattern.compile(TEST_PATTERN);

        try (BufferedReader reader = new BufferedReader(new FileReader(testSuiteFile))) {
            String line;
            String currentTest = null;

            while ((line = reader.readLine()) != null) {
                Matcher matcher = testPattern.matcher(line);
                if (matcher.find()) {
                    currentTest = matcher.group(1);
                }

                if (line.contains(FAILURE_PATTERN) && currentTest != null) {
                    failedTests.add(currentTest);
                }
            }
        }

        return failedTests;
    }

    /**
     * Generate testng.xml with only failed tests
     * @param failedTests Set of failed test names
     * @param outputFile Path to output testng-failed.xml
     */
    public static void generateFailedTestsXml(Set<String> failedTests, String outputFile) throws IOException {
        // Group tests by class
        Set<String> failedClasses = new HashSet<>();
        for (String test : failedTests) {
            // Extract class name (e.g., "com.NexusAPI.Tests.Private_ActionsMenuController_Test")
            failedClasses.add(test.substring(0, test.lastIndexOf('.')));
        }

        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.append("<!DOCTYPE suite SYSTEM \"https://testng.org/testng-1.0.dtd\">\n");
        xml.append("<suite name=\"Failed Tests Suite\" verbose=\"2\" preserve-order=\"true\" parallel=\"false\" thread-count=\"1\">\n");
        xml.append("    <test name=\"Re-run Failed Tests\">\n");
        xml.append("        <classes>\n");

        for (String testClass : failedClasses) {
            xml.append("            <class name=\"").append(testClass).append("\"/>\n");
        }

        xml.append("        </classes>\n");
        xml.append("    </test>\n");
        xml.append("</suite>\n");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(xml.toString());
        }

        System.out.println("✓ Generated " + outputFile);
        System.out.println("✓ Total failed test classes: " + failedClasses.size());
        System.out.println("✓ Total failed tests: " + failedTests.size());
    }

    /**
     * Print summary of failed tests
     * @param failedTests Set of failed test names
     */
    public static void printSummary(Set<String> failedTests) {
        List<String> sortedTests = new ArrayList<>(failedTests);
        sortedTests.sort(String::compareTo);

        System.out.println("\n========== FAILED TESTS SUMMARY ==========\n");
        for (String test : sortedTests) {
            System.out.println("  ✗ " + test);
        }
        System.out.println("\n==========================================");
        System.out.println("Total failed tests: " + failedTests.size());
        System.out.println("==========================================\n");
    }

    /**
     * Main method to run the extractor
     * Usage: java FailedTestExtractor target/test-output/TestSuite.txt [testng-failed.xml]
     */
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Usage: java FailedTestExtractor <testSuiteFile> [outputXmlFile]");
            System.err.println("Example: java FailedTestExtractor target/test-output/TestSuite.txt testng-failed.xml");
            System.exit(1);
        }

        String inputFile = args[0];
        String outputFile = args.length > 1 ? args[1] : "testng-failed.xml";

        File input = new File(inputFile);
        if (!input.exists()) {
            System.err.println("✗ File not found: " + inputFile);
            System.exit(1);
        }

        System.out.println("Extracting failed tests from: " + inputFile);
        Set<String> failedTests = extractFailedTests(inputFile);

        if (failedTests.isEmpty()) {
            System.out.println("✓ No failed tests found! All tests passed.");
            System.exit(0);
        }

        printSummary(failedTests);
        generateFailedTestsXml(failedTests, outputFile);

        System.out.println("\nTo run failed tests, use:");
        System.out.println("  mvn clean test -Dsuite=" + outputFile);
    }
}
