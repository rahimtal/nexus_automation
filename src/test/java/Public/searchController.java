package Public;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;
import com.NexustAPIAutomation.java.Retry;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class searchController {

	// This will create elastic search index if not already
	@Test(priority = 1, groups = "Search", retryAnalyzer = Retry.class)
	public void elascticsearchcreateindex_v_2()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/search/load?index=accounts";
		String ver = "2";

		JsonPath result = CommonMethods.getMethod(uri, ver);
		System.out.println(result.prettyPrint());

	}

	// This will create elastic search index if not already
	//Depreciated
	//@Test(priority = 1, groups = "Search", retryAnalyzer = Retry.class)
	public void getAccounts_v_2() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		AssertJUnit.fail("Bug Reported 8700");
		String uri = "/search/getAccounts";
		String ver = "2";

		String jpath = "./\\TestData\\getAccountsv2.json";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("SearchQuery", "sally");
		params.put("DisplayAll", "false");
		params.put("UserId", "sa");

		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);

	}

	// {{urlv2}}/search/getAccountsAdvanced?SearchCustomerName=sally&SearchAccountNumber=&SearchServiceAddress=&SearchPhoneNumber=&SearchEmailAddress=&SortOrder=1&SortDescending=0&ShowAll=0
	// This will create elastic search index if not already
	//Depreciated
	//@Test(priority = 1, groups = "Search", retryAnalyzer = Retry.class)
	public void getAccountsAdvanced_v_2()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		AssertJUnit.fail("Bug Reported 8700");
		String uri = "/search/getAccountsAdvanced";
		String ver = "2";

		String jpath = "./\\TestData\\getAccountsAdvancedv2.json";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("SearchCustomerName", "sally");
		params.put("SearchAccountNumber", "");
		params.put("SearchPhoneNumber", "");
		params.put("SearchEmailAddress", "");
		params.put("SortOrder", "1");
		params.put("SortDescending", "0");
		params.put("ShowAll", "0");

		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);

	}

	// {{urlv2}}/search/getCustomer?SearchQuery=Sally

	@Test(priority = 1, groups = "Search", retryAnalyzer = Retry.class)
	public void getCustomer_v2() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/search/getCustomer";
		String ver = "2";

		String jpath = "./\\TestData\\getCustomerv2.json";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("SearchQuery", "sally");

		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);

	}

	// {{urlv2}}/search?index=accounts&searchQuery=success&pageNum=1&numPerPage=10

	@Test(priority = 1, groups = "Search", retryAnalyzer = Retry.class)
	public void getindex_v2() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/search";
		String ver = "2";

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("index", "accounts");
		params.put("searchQuery", "success");
		params.put("pageNum", "1");
		params.put("numPerPage", "10");

		Response result = CommonMethods.getMethod(uri, ver, params);
		AssertJUnit.assertEquals(result.statusCode(), 200);
		System.out.println(result);

	}

}