package com.NexustAPIAutomation.java;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import jakarta.mail.MessagingException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EmailReportListener implements ISuiteListener {

    @Override
    public void onFinish(ISuite suite) {
        // Get test results from suite
        int passedTests = suite.getResults().values().stream()
                .mapToInt(result -> result.getTestContext().getPassedTests().size())
                .sum();
        int failedTests = suite.getResults().values().stream()
                .mapToInt(result -> result.getTestContext().getFailedTests().size())
                .sum();
        int skippedTests = suite.getResults().values().stream()
                .mapToInt(result -> result.getTestContext().getSkippedTests().size())
                .sum();

        // Current timestamp
        LocalDateTime now = LocalDateTime.now();
        String formattedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // Subject and body
        String subject = "TestNG Execution Report @ " + formattedNow;
        String body = String.format(
                "TestNG Execution Summary for Nexus API Latest Build:\nPassed: %d\nFailed: %d\nSkipped: %d",
                passedTests, failedTests, skippedTests
        );

        // Wait for report generation (optional)
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Send email to multiple recipients
        String[] recipients = {
            "trahim@cogsdale.com",
            "RThurairasa@cogsdale.com",
            "MCausevic@cogsdale.com"
        };

        for (String to : recipients) {
            try {
                EmailSender.sendEmail("cogsauto@gmail.com", to, subject, body);
            } catch (MessagingException | IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
