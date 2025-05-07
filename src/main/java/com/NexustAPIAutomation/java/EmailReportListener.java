package com.NexustAPIAutomation.java;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.NexustAPIAutomation.java.EmailSender;

import jakarta.mail.MessagingException;

public class EmailReportListener implements ITestListener {

	@Override
	public void onFinish(ITestContext context) {
		// Collect results and send email after all tests
		sendEmailReport(context);
	}

	private void sendEmailReport(ITestContext context) {
		int failedTests = context.getFailedTests().size();
		int passedTests = context.getPassedTests().size();
		int skippedTests = context.getSkippedTests().size();
		// Get the current date and time
		LocalDateTime now = LocalDateTime.now();
		// Define the format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		// Format the current date and time
		String formattedNow = now.format(formatter);

		String subject = "TestNG Execution Report @" + formattedNow;
		String body = String.format(
				"TestNG Execution Summary for Nexus API Latest Build :\nPassed: %d\nFailed: %d\nSkipped: %d",
				passedTests, failedTests, skippedTests);

		try {
			EmailSender.sendEmail("cogsauto@gmail.com", "trahim@cogsdale.com", subject, body);
		} catch (MessagingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			EmailSender.sendEmail("cogsauto@gmail.com", "RThurairasa@cogsdale.com", subject, body);
		} catch (MessagingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			EmailSender.sendEmail("cogsauto@gmail.com", "MCausevic@cogsdale.com", subject, body);
		} catch (MessagingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Other listener methods (optional to override)
}
