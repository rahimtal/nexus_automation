package com.NexustAPIAutomation.java;

public class TestAndEmailExecutor {
    public static void main(String[] args) {
        try {
            // Step 1: Run Maven test command
            System.out.println("Running Maven tests...");
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "mvn test");
            processBuilder.inheritIO(); // Redirect output to console
            Process process = processBuilder.start();

            // Wait for the Maven test process to complete
            int exitCode = process.waitFor();
            System.out.println("Maven tests completed with exit code: " + exitCode);

            // Step 2: Send email after tests complete
            if (exitCode == 0) {
                System.out.println("Sending email...");
                EmailSender.main(null); // Call EmailSender to send the email
            } else {
                System.err.println("Maven tests failed");
                EmailSender.main(null); // Call EmailSender to send the email
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}