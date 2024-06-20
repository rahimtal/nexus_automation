package Public;

import java.sql.SQLException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;



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
