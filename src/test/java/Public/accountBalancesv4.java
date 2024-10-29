package Public;

import org.testng.annotations.Test;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;
import com.NexustAPIAutomation.java.Retry;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class accountBalancesv4 {

	public static JsonPath jsonPathEvaluator;

	@Test(priority = 1, groups = "AccountBalances", retryAnalyzer = Retry.class)
	public void getAccountBalancesV4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

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