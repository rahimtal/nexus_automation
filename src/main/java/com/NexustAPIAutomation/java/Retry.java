package com.NexustAPIAutomation.java;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	private int count = 0;
	private static int maxTry = 3; // set no.of retry @Override

	public boolean retry(ITestResult iTestResult) {
		if (!iTestResult.isSuccess()) {
			// Check if test not succeed
			if (count < maxTry) {
				count++;
				iTestResult.setStatus(ITestResult.FAILURE);

				return true;
			} else {

				iTestResult.setStatus(ITestResult.FAILURE);
				// If maxCount reached,test marked as failed
			}

		} else {
			iTestResult.setStatus(ITestResult.SUCCESS);
		}

		return false;
	}
}