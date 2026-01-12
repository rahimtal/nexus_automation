package Public;

import org.testng.annotations.Test; import org.testng.Assert;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.Assert;
import org.testng.annotations.Test; import org.testng.Assert;

import com.NexusAPI.Tests.BaseClass;
import com.NexustAPIAutomation.java.CommonMethods;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAccountBalances  extends BaseClass{

	public static JsonPath jsonPathEvaluator;

	//@Test(priority = 1, groups = "Account Balances")
	public void getAccountBalancesV2() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/accountBalance/getAccountBalances";
		String ver = "2";
		String jpath = "./TestData\\accountBalancev2.json";

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "loc@0001");
		params.put("CustomerId", "0000011111");
		params.put("UserDate", "2027-04-12");
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);

	}

	//@Test(priority = 2, groups = "Account Balances")
	public void getAccountBalancesV24() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/accountBalance/getAccountBalances";
		String ver = "2.4";
		String jpath = "./TestData\\accountBalance.json";

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "loc@0001");
		params.put("CustomerId", "0000011111");
		params.put("UserDate", "2027-04-12");

		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}
	
//	@Test(priority = 3, groups = "Account Balances")
	public void getAccountBalancesPortalv2_4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/AccountBalance/portal";
		String ver = "2.4";
		String jpath = "./TestData\\accountBalancePortal.json";
		
		

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "LOCATION008");
		params.put("CustomerId", "CUSTOMER009");
		String result = CommonMethods.getMethodContains(uri, ver, params, jpath);
				
		System.out.println(result);
	}
	

}