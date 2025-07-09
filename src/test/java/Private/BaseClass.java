package Private;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.filter.Filter;

//import com.aventstack.extentreports.ExtentReports;

//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BaseClass {

	// public static ExtentReports extent;
	// public static //ExtentTest test;
	// public static ExtentHtmlReporter htmlReporter;
	
	@BeforeSuite
	public void setupReport() throws IOException {
		System.out.print("Deleting folder");
		Runtime.getRuntime().exec("cmd /c rd /s /q C:\\Users\\Admin\\Documents\\GitHub\\nexus_automation\\test-output");
		// extent = new ExtentReports();
		// extent.attachReporter(htmlReporter);

	}

	@AfterSuite
	public void tearDownReport() {
		// extent.flush();
	}

}
