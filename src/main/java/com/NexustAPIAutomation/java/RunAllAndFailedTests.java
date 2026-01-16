package com.NexustAPIAutomation.java;

public class RunAllAndFailedTests {
    public static void main(String[] args) {
        try {
            // Step 1: Run all tests
            System.out.println("Running all tests...");
            ProcessBuilder allTestsBuilder = new ProcessBuilder("cmd.exe", "/c", "mvn test");
            allTestsBuilder.inheritIO();
            Process allTestsProcess = allTestsBuilder.start();
            int allTestsExitCode = allTestsProcess.waitFor();
            System.out.println("All tests completed with exit code: " + allTestsExitCode);

            // Step 2: Send email with the report
            if (allTestsExitCode == 0) {
                System.out.println("Sending email with the test report...");
                EmailSender.main(null);
            } else {
                System.err.println("Some tests failed. Sending email with the test report...");
                EmailSender.main(null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}