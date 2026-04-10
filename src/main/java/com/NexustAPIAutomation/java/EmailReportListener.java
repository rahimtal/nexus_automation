package com.NexustAPIAutomation.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import jakarta.mail.MessagingException;

public class EmailReportListener implements ISuiteListener {

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("✅ EmailReportListener.onFinish() triggered");

		// Run AFTER the JVM is shutting down, when TestNG is done writing files
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			System.out.println("🚨 Shutdown hook started. Waiting for report file...");

			try {
				// Wait for report file to appear
				Path reportPath = Paths.get(System.getProperty("user.dir"), "test-output", "emailable-report.html");
				int retries = 0;
				while (!Files.exists(reportPath) && retries < 60) {
					System.out.println("⏳ Still waiting for emailable-report.html...");
					Thread.sleep(1000);
					retries++;
				}

				if (!Files.exists(reportPath)) {
					System.err.println("❌ Report not found after waiting.");
					return;
				}

				// Summarize results
				int passedTests = suite.getResults().values().stream()
						.mapToInt(result -> result.getTestContext().getPassedTests().size()).sum();
				int failedTests = suite.getResults().values().stream()
						.mapToInt(result -> result.getTestContext().getFailedTests().size()).sum();
				int skippedTests = suite.getResults().values().stream()
						.mapToInt(result -> result.getTestContext().getSkippedTests().size()).sum();

				String formattedNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
				String subject = "TestNG Execution Report @ " + formattedNow;
				String body = String.format(
						"TestNG Execution Summary for Nexus API Latest Build:\nPassed: %d\nFailed: %d\nSkipped: %d",
						passedTests, failedTests, skippedTests);

				// Send emails
				String[] recipients = {
						"trahim@cogsdale.com",
						"RThurairasa@cogsdale.com",
						// "MCausevic@cogsdale.com",
						"MAkhlaq@Cogsdale.com"
				};

				for (String to : recipients) {
					try {
						EmailSender.sendEmail("cogsauto@gmail.com", to, subject, body);
						System.out.println("✅ Email sent to " + to);
					} catch (MessagingException | IOException e) {
						e.printStackTrace();
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}));
	}

}
