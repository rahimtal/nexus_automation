package Private;

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
	public void tearDownReport() {
		// extent.flush();
	}

}
