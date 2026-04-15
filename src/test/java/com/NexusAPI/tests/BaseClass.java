package com.NexusAPI.Tests;

import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.NexustAPIAutomation.java.EmailSender;
import com.NexustAPIAutomation.java.QuickDBRestore;

//import com.aventstack.extentreports.ExtentReports;

//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.annotations.Listeners;
import com.NexustAPIAutomation.java.ExtentTestNGListener;

@Listeners(ExtentTestNGListener.class)
public class BaseClass {

	// public static ExtentReports extent;
	// public static //ExtentTest test;
	// public static ExtentHtmlReporter htmlReporter;

	@BeforeSuite
	public void setupReport() throws IOException, InterruptedException {
		// System.out.println("Deleting folder");
		// Runtime.getRuntime().exec("cmd /c rd /s /q
		// C:\\Users\\Admin\\Documents\\GitHub\\nexus_automation\\test-output\\");
		// Runtime.getRuntime().exec("cmd /c rd /s /q
		// C:\\Users\\Admin\\Documents\\GitHub\\nexus_automation\\target\\test-output\\");
		QuickDBRestore.restoreDatabase();

	}

	@AfterSuite
	public void sendReportByEmail() throws Exception {
		// Delay added just to be safe
		Thread.sleep(3000);

		EmailSender.sendEmail("cogsauto@gmail.com", "trahim@cogsdale.com", "Test", "Test");

	}

}
