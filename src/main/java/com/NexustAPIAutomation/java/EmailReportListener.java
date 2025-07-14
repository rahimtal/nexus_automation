package com.NexustAPIAutomation.java;
import org.testng.ISuite;
import org.testng.ISuiteListener;

import jakarta.mail.MessagingException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EmailReportListener implements ISuiteListener {

    @Override
    public void onFinish(ISuite suite) {
        // Use new thread to wait and send email AFTER TestNG reports are flushed
        new Thread(() -> {
            try {
                // Wait 5 seconds to ensure report generation is complete
                Thread.sleep(5000);

                // Generate summary
                int passedTests = suite.getResults().values().stream()
                        .mapToInt(result -> result.getTestContext().getPassedTests().size()).sum();
                int failedTests = suite.getResults().values().stream()
                        .mapToInt(result -> result.getTestContext().getFailedTests().size()).sum();
                int skippedTests = suite.getResults().values().stream()
                        .mapToInt(result -> result.getTestContext().getSkippedTests().size()).sum();

                LocalDateTime now = LocalDateTime.now();
                String formattedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                String subject = "TestNG Execution Report @ " + formattedNow;
                String body = String.format(
                        "TestNG Execution Summary for Nexus API Latest Build:\nPassed: %d\nFailed: %d\nSkipped: %d",
                        passedTests, failedTests, skippedTests
                );

                // Check if report file exists before sending
                Path reportPath = Paths.get(System.getProperty("user.dir"), "test-output", "emailable-report.html");
                int retries = 0;
                while (!Files.exists(reportPath) && retries < 10) {
                    System.out.println("Waiting for emailable-report.html...");
                    Thread.sleep(1000);
                    retries++;
                }

                if (!Files.exists(reportPath)) {
                    System.err.println("Report not found after waiting: " + reportPath);
                    return;
                }

                // Send to recipients
                String[] recipients = {
                    "trahim@cogsdale.com",
                    "RThurairasa@cogsdale.com",
                    "MCausevic@cogsdale.com"
                };

                for (String to : recipients) {
                    try {
                        EmailSender.sendEmail("cogsauto@gmail.com", to, subject, body);
                    } catch (MessagingException | IOException e) {
                        e.printStackTrace();
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
