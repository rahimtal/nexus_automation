package Private;

import org.testng.annotations.Test;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;

public class searchControllerV4 {

	// This will create elastic search index if not already
	@Test(priority = 1, groups = "Search")
	public void elascticsearchcreateindex_v_4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/search/load?index=accounts";
		String ver = "4.0";

		JsonPath result = CommonMethods.getMethod(uri, ver);
		System.out.println(result.prettyPrint());

	}

	@Test(priority = 2, groups = "Search", dependsOnMethods = "elascticsearchcreateindex_v_4")
	public void searchMatchPhrasePrefixv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/search";
		String ver = "4.0";

		String jpath = "./\\TestData\\searchMatchPhrasePrefixv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("index", "accounts");
		params.put("searchQuery", "{\"LocationId\":\"Master\"},{\"CustomerId\":\"Master\"}");
		params.put("searchType", "MatchPhrasePrefix");
		String result = CommonMethods.getMethodContains(uri, ver, params, jpath);
		System.out.println(result);

	}

	@Test(priority = 3, groups = "Search", dependsOnMethods = "searchMatchPhrasePrefixv4")
	public void part_searchMatchPhrasePrefixv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/search";
		String ver = "4.0";

		String jpath = "./\\TestData\\searchMatchPhrasePrefixv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("index", "accounts");
		params.put("searchQuery", "{\"LocationId\":\"Mas\"},{\"CustomerId\":\"Mas\"}");
		params.put("searchType", "MatchPhrasePrefix");
		String result = CommonMethods.getMethodContains(uri, ver, params, jpath);
		System.out.println(result);

	}

	@Test(priority = 4, groups = "Search", dependsOnMethods = "part_searchMatchPhrasePrefixv4")
	public void part4_searchMatchPhrasePrefixv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/search";
		String ver = "4.0";
		String jpath = "./\\TestData\\searchMatchPhrasePrefixv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("index", "accounts");
		params.put("searchQuery", "{\"LocationId\":\"Mas\"},{\"CustomerId\":\"Mas\"}");
		params.put("searchType", "MatchPhrasePrefix");
		String result = CommonMethods.getMethodContains(uri, ver, params, jpath);
		System.out.println(result);

	}

	@Test(priority = 4, groups = "Search", dependsOnMethods = "part4_searchMatchPhrasePrefixv4")
	public void part2_searchMatchPhrasePrefixv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/search";
		String ver = "4.0";
		String jpath = "./\\TestData\\2searchMatchPhrasePrefixv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("index", "accounts");
		params.put("searchQuery", "{\"LocationId\":\"loc\"},{\"CustomerId\":\"mas\"}");
		params.put("searchType", "MatchPhrasePrefix");
		String result = CommonMethods.getMethodContains(uri, ver, params, jpath);
		System.out.println(result);

	}

}