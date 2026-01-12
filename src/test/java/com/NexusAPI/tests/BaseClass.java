package com.NexusAPI.Tests;

import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

//import com.aventstack.extentreports.ExtentReports;

//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BaseClass {

	// public static ExtentReports extent;
	// public static //ExtentTest test;
	// public static ExtentHtmlReporter htmlReporter;
	
	@BeforeSuite
	public void setupReport() throws IOException {
		System.out.println("Deleting folder");
		Runtime.getRuntime().exec("cmd /c rd /s /q C:\\Users\\Admin\\Documents\\GitHub\\nexus_automation\\test-output");
		}

	 @AfterSuite
	    public void sendReportByEmail() throws Exception {
	        // Delay added just to be safe
	        Thread.sleep(3000);

	        String from = "cogsauto@gmail.com";
	        String to = "recipient@example.com";
	        String subject = "TestNG API Test Report";
	        String body = "Please find the attached report.";


	    }

	   

}
