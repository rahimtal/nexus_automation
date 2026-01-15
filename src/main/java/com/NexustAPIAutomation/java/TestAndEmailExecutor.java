package com.NexustAPIAutomation.java;

import java.io.IOException;

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
        } catch (IOException e) {
            System.err.println("I/O error occurred: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Thread was interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }
}