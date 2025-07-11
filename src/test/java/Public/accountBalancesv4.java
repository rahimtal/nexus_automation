package Public;

import org.testng.annotations.Test; import org.testng.Assert;
import org.testng.annotations.Test; import org.testng.Assert;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.Assert;
import org.testng.annotations.Test; import org.testng.Assert;

import com.NexustAPIAutomation.java.CommonMethods;

import Private.BaseClass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class accountBalancesv4  extends BaseClass {

	public static JsonPath jsonPathEvaluator;

	@Test(priority = 1, groups = "AccountBalances" )
	public void getAccountBalancesV4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		//Still a Bug (2025)	
		CommonMethods.Bug("CPDEV-21481");
		String uri = "/accountBalance/getAccountBalances";
		String ver = "4.0";
		String jpath = "./TestData\\accountBalancev4.json";

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "LOCATION008");
		params.put("CustomerId", "CUSTOMER009");
		params.put("UserDate", "2027-04-12");
		String result = CommonMethods.getMethodContains(uri, ver, params, jpath);
		System.out.println(result);

	}

	

}