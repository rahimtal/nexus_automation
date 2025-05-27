package Public;

import org.testng.annotations.Test; import org.testng.Assert;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test; import org.testng.Assert;

import com.NexustAPIAutomation.java.CommonMethods;



import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class billingController extends BaseClass {

	@Test(priority = 1, groups = "Billing" )
	public static void billingprintStatementv4true()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/billing/statements/CUSTOMER010/ELECWAT003";
		String ver = "4.0";
		String expected = "{\"Statements\":{\"Success\":true,\"Data\":{\"CustomerId\":\"CUSTOMER010\",\"Location\":[{\"Id\":\"ELECWAT003\",\"Statement\":[{\"Number\":82,\"Date\":\"1997-08-31T00:00:00\",\"DueDate\":\"1997-09-30T00:00:00\",\"Amount\":163.92},{\"Number\":85,\"Date\":\"1997-10-31T00:00:00\",\"DueDate\":\"1997-12-01T00:00:00\",\"Amount\":280.27},{\"Number\":88,\"Date\":\"1997-12-31T00:00:00\",\"DueDate\":\"1998-01-30T00:00:00\",\"Amount\":132.39},{\"Number\":91,\"Date\":\"1998-02-28T00:00:00\",\"DueDate\":\"1998-03-30T00:00:00\",\"Amount\":449.31},{\"Number\":94,\"Date\":\"1998-04-30T00:00:00\",\"DueDate\":\"1998-06-01T00:00:00\",\"Amount\":64.38},{\"Number\":97,\"Date\":\"1998-06-30T00:00:00\",\"DueDate\":\"1998-07-30T00:00:00\",\"Amount\":100.46},{\"Number\":100,\"Date\":\"1998-08-31T00:00:00\",\"DueDate\":\"1998-09-30T00:00:00\",\"Amount\":87.63},{\"Number\":103,\"Date\":\"1998-10-31T00:00:00\",\"DueDate\":\"1998-11-30T00:00:00\",\"Amount\":70.48},{\"Number\":106,\"Date\":\"1998-12-31T00:00:00\",\"DueDate\":\"1999-01-30T00:00:00\",\"Amount\":64.99},{\"Number\":319,\"Date\":\"1999-01-31T00:00:00\",\"DueDate\":\"1999-03-01T00:00:00\",\"Amount\":172.57},{\"Number\":322,\"Date\":\"1999-02-28T00:00:00\",\"DueDate\":\"1999-03-30T00:00:00\",\"Amount\":315.15},{\"Number\":325,\"Date\":\"1999-03-31T00:00:00\",\"DueDate\":\"1999-04-30T00:00:00\",\"Amount\":160.67},{\"Number\":328,\"Date\":\"1999-04-30T00:00:00\",\"DueDate\":\"1999-05-30T00:00:00\",\"Amount\":148.24},{\"Number\":331,\"Date\":\"1999-05-30T00:00:00\",\"DueDate\":\"1999-06-29T00:00:00\",\"Amount\":298.07},{\"Number\":334,\"Date\":\"1999-06-30T00:00:00\",\"DueDate\":\"1999-07-30T00:00:00\",\"Amount\":138.9},{\"Number\":337,\"Date\":\"1999-07-31T00:00:00\",\"DueDate\":\"1999-08-30T00:00:00\",\"Amount\":157.9},{\"Number\":340,\"Date\":\"1999-08-30T00:00:00\",\"DueDate\":\"1999-09-29T00:00:00\",\"Amount\":165.66},{\"Number\":343,\"Date\":\"1999-09-30T00:00:00\",\"DueDate\":\"1999-10-30T00:00:00\",\"Amount\":137.57},{\"Number\":346,\"Date\":\"1999-10-30T00:00:00\",\"DueDate\":\"1999-11-29T00:00:00\",\"Amount\":122.93},{\"Number\":349,\"Date\":\"1999-11-30T00:00:00\",\"DueDate\":\"1999-12-30T00:00:00\",\"Amount\":137.28},{\"Number\":352,\"Date\":\"1999-12-31T00:00:00\",\"DueDate\":\"2000-01-31T00:00:00\",\"Amount\":127.32},{\"Number\":355,\"Date\":\"2000-01-31T00:00:00\",\"DueDate\":\"2000-02-29T00:00:00\",\"Amount\":146.85},{\"Number\":358,\"Date\":\"2000-02-29T00:00:00\",\"DueDate\":\"2000-03-30T00:00:00\",\"Amount\":154.64}]}]},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("ExcludeInWork", "true");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(expected, result);
		System.out.println(result);
	}

	@Test(priority = 2, groups = "Billing" )
	public static void billingprintStatementv4false()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/billing/statements/CUSTOMER010/ELECWAT003";
		String ver = "4.0";
		String expected = "{\"Statements\":{\"Success\":true,\"Data\":{\"CustomerId\":\"CUSTOMER010\",\"Location\":[{\"Id\":\"ELECWAT003\",\"Statement\":[{\"Number\":82,\"Date\":\"1997-08-31T00:00:00\",\"DueDate\":\"1997-09-30T00:00:00\",\"Amount\":163.92},{\"Number\":85,\"Date\":\"1997-10-31T00:00:00\",\"DueDate\":\"1997-12-01T00:00:00\",\"Amount\":280.27},{\"Number\":88,\"Date\":\"1997-12-31T00:00:00\",\"DueDate\":\"1998-01-30T00:00:00\",\"Amount\":132.39},{\"Number\":91,\"Date\":\"1998-02-28T00:00:00\",\"DueDate\":\"1998-03-30T00:00:00\",\"Amount\":449.31},{\"Number\":94,\"Date\":\"1998-04-30T00:00:00\",\"DueDate\":\"1998-06-01T00:00:00\",\"Amount\":64.38},{\"Number\":97,\"Date\":\"1998-06-30T00:00:00\",\"DueDate\":\"1998-07-30T00:00:00\",\"Amount\":100.46},{\"Number\":100,\"Date\":\"1998-08-31T00:00:00\",\"DueDate\":\"1998-09-30T00:00:00\",\"Amount\":87.63},{\"Number\":103,\"Date\":\"1998-10-31T00:00:00\",\"DueDate\":\"1998-11-30T00:00:00\",\"Amount\":70.48},{\"Number\":106,\"Date\":\"1998-12-31T00:00:00\",\"DueDate\":\"1999-01-30T00:00:00\",\"Amount\":64.99},{\"Number\":319,\"Date\":\"1999-01-31T00:00:00\",\"DueDate\":\"1999-03-01T00:00:00\",\"Amount\":172.57},{\"Number\":322,\"Date\":\"1999-02-28T00:00:00\",\"DueDate\":\"1999-03-30T00:00:00\",\"Amount\":315.15},{\"Number\":325,\"Date\":\"1999-03-31T00:00:00\",\"DueDate\":\"1999-04-30T00:00:00\",\"Amount\":160.67},{\"Number\":328,\"Date\":\"1999-04-30T00:00:00\",\"DueDate\":\"1999-05-30T00:00:00\",\"Amount\":148.24},{\"Number\":331,\"Date\":\"1999-05-30T00:00:00\",\"DueDate\":\"1999-06-29T00:00:00\",\"Amount\":298.07},{\"Number\":334,\"Date\":\"1999-06-30T00:00:00\",\"DueDate\":\"1999-07-30T00:00:00\",\"Amount\":138.9},{\"Number\":337,\"Date\":\"1999-07-31T00:00:00\",\"DueDate\":\"1999-08-30T00:00:00\",\"Amount\":157.9},{\"Number\":340,\"Date\":\"1999-08-30T00:00:00\",\"DueDate\":\"1999-09-29T00:00:00\",\"Amount\":165.66},{\"Number\":343,\"Date\":\"1999-09-30T00:00:00\",\"DueDate\":\"1999-10-30T00:00:00\",\"Amount\":137.57},{\"Number\":346,\"Date\":\"1999-10-30T00:00:00\",\"DueDate\":\"1999-11-29T00:00:00\",\"Amount\":122.93},{\"Number\":349,\"Date\":\"1999-11-30T00:00:00\",\"DueDate\":\"1999-12-30T00:00:00\",\"Amount\":137.28},{\"Number\":352,\"Date\":\"1999-12-31T00:00:00\",\"DueDate\":\"2000-01-31T00:00:00\",\"Amount\":127.32},{\"Number\":355,\"Date\":\"2000-01-31T00:00:00\",\"DueDate\":\"2000-02-29T00:00:00\",\"Amount\":146.85},{\"Number\":358,\"Date\":\"2000-02-29T00:00:00\",\"DueDate\":\"2000-03-30T00:00:00\",\"Amount\":154.64}]}]},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("ExcludeInWork", "false");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(expected, result);
		System.out.println(result);
	}

	@Test(priority = 3, groups = "Billing" )
	public static void billingprintStatementv3true()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/billing/statements/CUSTOMER010/ELECWAT003";
		String ver = "3.0";
		String expected = "{\"Statements\":{\"Success\":true,\"Data\":{\"CustomerId\":\"CUSTOMER010\",\"Location\":[{\"Id\":\"ELECWAT003\",\"Statement\":[{\"Number\":82,\"Date\":\"1997-08-31T00:00:00\",\"DueDate\":\"1997-09-30T00:00:00\",\"Amount\":163.92},{\"Number\":85,\"Date\":\"1997-10-31T00:00:00\",\"DueDate\":\"1997-12-01T00:00:00\",\"Amount\":280.27},{\"Number\":88,\"Date\":\"1997-12-31T00:00:00\",\"DueDate\":\"1998-01-30T00:00:00\",\"Amount\":132.39},{\"Number\":91,\"Date\":\"1998-02-28T00:00:00\",\"DueDate\":\"1998-03-30T00:00:00\",\"Amount\":449.31},{\"Number\":94,\"Date\":\"1998-04-30T00:00:00\",\"DueDate\":\"1998-06-01T00:00:00\",\"Amount\":64.38},{\"Number\":97,\"Date\":\"1998-06-30T00:00:00\",\"DueDate\":\"1998-07-30T00:00:00\",\"Amount\":100.46},{\"Number\":100,\"Date\":\"1998-08-31T00:00:00\",\"DueDate\":\"1998-09-30T00:00:00\",\"Amount\":87.63},{\"Number\":103,\"Date\":\"1998-10-31T00:00:00\",\"DueDate\":\"1998-11-30T00:00:00\",\"Amount\":70.48},{\"Number\":106,\"Date\":\"1998-12-31T00:00:00\",\"DueDate\":\"1999-01-30T00:00:00\",\"Amount\":64.99},{\"Number\":319,\"Date\":\"1999-01-31T00:00:00\",\"DueDate\":\"1999-03-01T00:00:00\",\"Amount\":172.57},{\"Number\":322,\"Date\":\"1999-02-28T00:00:00\",\"DueDate\":\"1999-03-30T00:00:00\",\"Amount\":315.15},{\"Number\":325,\"Date\":\"1999-03-31T00:00:00\",\"DueDate\":\"1999-04-30T00:00:00\",\"Amount\":160.67},{\"Number\":328,\"Date\":\"1999-04-30T00:00:00\",\"DueDate\":\"1999-05-30T00:00:00\",\"Amount\":148.24},{\"Number\":331,\"Date\":\"1999-05-30T00:00:00\",\"DueDate\":\"1999-06-29T00:00:00\",\"Amount\":298.07},{\"Number\":334,\"Date\":\"1999-06-30T00:00:00\",\"DueDate\":\"1999-07-30T00:00:00\",\"Amount\":138.9},{\"Number\":337,\"Date\":\"1999-07-31T00:00:00\",\"DueDate\":\"1999-08-30T00:00:00\",\"Amount\":157.9},{\"Number\":340,\"Date\":\"1999-08-30T00:00:00\",\"DueDate\":\"1999-09-29T00:00:00\",\"Amount\":165.66},{\"Number\":343,\"Date\":\"1999-09-30T00:00:00\",\"DueDate\":\"1999-10-30T00:00:00\",\"Amount\":137.57},{\"Number\":346,\"Date\":\"1999-10-30T00:00:00\",\"DueDate\":\"1999-11-29T00:00:00\",\"Amount\":122.93},{\"Number\":349,\"Date\":\"1999-11-30T00:00:00\",\"DueDate\":\"1999-12-30T00:00:00\",\"Amount\":137.28},{\"Number\":352,\"Date\":\"1999-12-31T00:00:00\",\"DueDate\":\"2000-01-31T00:00:00\",\"Amount\":127.32},{\"Number\":355,\"Date\":\"2000-01-31T00:00:00\",\"DueDate\":\"2000-02-29T00:00:00\",\"Amount\":146.85},{\"Number\":358,\"Date\":\"2000-02-29T00:00:00\",\"DueDate\":\"2000-03-30T00:00:00\",\"Amount\":154.64}]}]},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("ExcludeInWork", "true");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(expected, result);
		System.out.println(result);
	}

	@Test(priority = 4, groups = "Billing" )
	public static void billingprintStatementv3false()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/billing/statements/CUSTOMER010/ELECWAT003";
		String ver = "3.0";
		String expected = "{\"Statements\":{\"Success\":true,\"Data\":{\"CustomerId\":\"CUSTOMER010\",\"Location\":[{\"Id\":\"ELECWAT003\",\"Statement\":[{\"Number\":82,\"Date\":\"1997-08-31T00:00:00\",\"DueDate\":\"1997-09-30T00:00:00\",\"Amount\":163.92},{\"Number\":85,\"Date\":\"1997-10-31T00:00:00\",\"DueDate\":\"1997-12-01T00:00:00\",\"Amount\":280.27},{\"Number\":88,\"Date\":\"1997-12-31T00:00:00\",\"DueDate\":\"1998-01-30T00:00:00\",\"Amount\":132.39},{\"Number\":91,\"Date\":\"1998-02-28T00:00:00\",\"DueDate\":\"1998-03-30T00:00:00\",\"Amount\":449.31},{\"Number\":94,\"Date\":\"1998-04-30T00:00:00\",\"DueDate\":\"1998-06-01T00:00:00\",\"Amount\":64.38},{\"Number\":97,\"Date\":\"1998-06-30T00:00:00\",\"DueDate\":\"1998-07-30T00:00:00\",\"Amount\":100.46},{\"Number\":100,\"Date\":\"1998-08-31T00:00:00\",\"DueDate\":\"1998-09-30T00:00:00\",\"Amount\":87.63},{\"Number\":103,\"Date\":\"1998-10-31T00:00:00\",\"DueDate\":\"1998-11-30T00:00:00\",\"Amount\":70.48},{\"Number\":106,\"Date\":\"1998-12-31T00:00:00\",\"DueDate\":\"1999-01-30T00:00:00\",\"Amount\":64.99},{\"Number\":319,\"Date\":\"1999-01-31T00:00:00\",\"DueDate\":\"1999-03-01T00:00:00\",\"Amount\":172.57},{\"Number\":322,\"Date\":\"1999-02-28T00:00:00\",\"DueDate\":\"1999-03-30T00:00:00\",\"Amount\":315.15},{\"Number\":325,\"Date\":\"1999-03-31T00:00:00\",\"DueDate\":\"1999-04-30T00:00:00\",\"Amount\":160.67},{\"Number\":328,\"Date\":\"1999-04-30T00:00:00\",\"DueDate\":\"1999-05-30T00:00:00\",\"Amount\":148.24},{\"Number\":331,\"Date\":\"1999-05-30T00:00:00\",\"DueDate\":\"1999-06-29T00:00:00\",\"Amount\":298.07},{\"Number\":334,\"Date\":\"1999-06-30T00:00:00\",\"DueDate\":\"1999-07-30T00:00:00\",\"Amount\":138.9},{\"Number\":337,\"Date\":\"1999-07-31T00:00:00\",\"DueDate\":\"1999-08-30T00:00:00\",\"Amount\":157.9},{\"Number\":340,\"Date\":\"1999-08-30T00:00:00\",\"DueDate\":\"1999-09-29T00:00:00\",\"Amount\":165.66},{\"Number\":343,\"Date\":\"1999-09-30T00:00:00\",\"DueDate\":\"1999-10-30T00:00:00\",\"Amount\":137.57},{\"Number\":346,\"Date\":\"1999-10-30T00:00:00\",\"DueDate\":\"1999-11-29T00:00:00\",\"Amount\":122.93},{\"Number\":349,\"Date\":\"1999-11-30T00:00:00\",\"DueDate\":\"1999-12-30T00:00:00\",\"Amount\":137.28},{\"Number\":352,\"Date\":\"1999-12-31T00:00:00\",\"DueDate\":\"2000-01-31T00:00:00\",\"Amount\":127.32},{\"Number\":355,\"Date\":\"2000-01-31T00:00:00\",\"DueDate\":\"2000-02-29T00:00:00\",\"Amount\":146.85},{\"Number\":358,\"Date\":\"2000-02-29T00:00:00\",\"DueDate\":\"2000-03-30T00:00:00\",\"Amount\":154.64}]}]},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("ExcludeInWork", "false");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(expected, result);
		System.out.println(result);
	}

	@Test(priority = 5, groups = "Billing" )
	public static void billingbillBatchStatus()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/billing/billBatchStatus/BAT10123123?ValidateBilling=Final";
		String ver = "4.0";
		// String expected =
		// "{\"BatchStatus\":{\"Success\":true,\"Data\":{\"BatchId\":\"BATCHID\",\"BatchStatus\":1,\"Route\":[],\"BatchDate\":{\"BillPreparationDate\":\"2024-11-12\",\"BillEditDate\":\"1900-01-01\",\"BillPrintDate\":\"1900-01-01\",\"BillPostDate\":\"1900-01-01\",\"BillCreatedDate\"";
		String expected = "{\"BatchStatus\":{\"Success\":true,";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("ValidateBilling", "Final");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(result);
		Assert.assertTrue(result.contains(expected));

	}

}
