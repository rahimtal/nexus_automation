package Public;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;


import io.restassured.path.json.JsonPath;

public class GetAccountBalancesV3 {

	public static JsonPath jsonPathEvaluator;

	@Test(priority = 1, groups = "Account Balances" )
	public void getAccountBalancesV3() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/accountBalance/getAccountBalances";
		String ver = "3.0";
		String jpath = "./TestData\\accountBalancev3.json";

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "LOCATION008");
		params.put("CustomerId", "CUSTOMER009");
		params.put("UserDate", "2027-04-12");
		String result = CommonMethods.getMethodContains(uri, ver, params, jpath);
		System.out.println(result);

	}

	@Test(priority = 3, groups = "Account Balances" )
	public void getAccountBalancesPortalv3()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/AccountBalance/portal";
		String ver = "3.0";
		String jpath = "./TestData\\accountBalancePortal3.json";

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "LOCATION008");
		params.put("CustomerId", "CUSTOMER009");
		String result = CommonMethods.getMethodContains(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 4, groups = "Account Balances" )
	public void getAccountBalancesPortalv3Paymentatwork()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/accountBalance/getAccountBalances";
		String ver = "3.0";
		String jpath = "./TestData\\accountBalancev3OpenWork.json";

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "ELECWAT001");
		params.put("CustomerId", "CUSTOMER007");
		String result = CommonMethods.getMethodContains(uri, ver, params, jpath);
		System.out.println(result);
	}

}