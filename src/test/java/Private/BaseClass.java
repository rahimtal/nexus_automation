package Private;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.NexustAPIAutomation.java.CommonMethods;
import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;



public class BaseClass {

	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentHtmlReporter htmlReporter;
	
	@BeforeSuite
	void BeforeTest() throws ClassNotFoundException, SQLException, InterruptedException {
	
	}
	@AfterSuite
	void flush()
	{
		
	}
	
	

}
