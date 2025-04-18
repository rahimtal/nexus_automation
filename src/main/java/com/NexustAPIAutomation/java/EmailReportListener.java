package com.NexustAPIAutomation.java;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.NexustAPIAutomation.java.EmailSender;

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

		String subject = "TestNG Execution Report";
		String body = String.format("TestNG Execution Summary:\nPassed: %d\nFailed: %d\nSkipped: %d", passedTests,
				failedTests, skippedTests);

		EmailSender.sendEmail("cogsauto@gmail.com", "trahim@cogsdale.com", subject, body);
	}

	// Other listener methods (optional to override)
}
