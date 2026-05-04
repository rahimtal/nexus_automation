package com.NexusAPI.Tests;

import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.NexustAPIAutomation.java.EmailSender;
import com.NexustAPIAutomation.java.QuickDBRestore;
import com.NexustAPIAutomation.java.FilteredPrintStream;

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
		// Suppress FreeMarker DEBUG logs from ExtentReports at both stderr and stdout
		System.setErr(new FilteredPrintStream(System.err));
		System.setOut(new FilteredPrintStream(System.out));
		
		// System.out.println("Deleting folder");
		// Runtime.getRuntime().exec("cmd /c rd /s /q
		// C:\\Users\\Admin\\Documents\\GitHub\\nexus_automation\\test-output\\");
		// Runtime.getRuntime().exec("cmd /c rd /s /q
		// C:\\Users\\Admin\\Documents\\GitHub\\nexus_automation\\target\\test-output\\");
		//disabled for now 
		System.out.println("\n========== BEFORE SUITE: Starting Database Restore ==========");
		try {
			QuickDBRestore.restoreDatabase();
			System.out.println("========== BEFORE SUITE: Database Restore COMPLETED ==========\n");
		} catch (Exception e) {
			System.out.println("========== BEFORE SUITE: Database Restore FAILED ==========");
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("Database restore failed in @BeforeSuite", e);
		}

	}

	@AfterSuite
	public void sendReportByEmail() throws Exception {
		// Delay added just to be safe
		Thread.sleep(3000);

		EmailSender.sendEmail("cogsauto@gmail.com", "trahim@cogsdale.com", "Test", "Test");

	}

}
