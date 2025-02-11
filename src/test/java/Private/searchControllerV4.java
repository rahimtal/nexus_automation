package Private;

import org.testng.annotations.Test;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;
import com.NexustAPIAutomation.java.Retry;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;

public class searchControllerV4 {

	// This will create elastic search index if not already
	@Test(priority = 1, groups = "Search" )
	public void elascticsearchcreateindex_v_4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/search/load?index=accounts";
		String ver = "4.0";

		JsonPath result = CommonMethods.getMethod(uri, ver);
		System.out.println(result.prettyPrint());

	}

	@Test(priority = 2, groups = "Search" , dependsOnMethods = "elascticsearchcreateindex_v_4")
	public void searchMatchPhrasePrefixv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/search";
		String ver = "4.0";
		String expected = "{\"Search\":{\"Success\":true,\"Data\":";
		String expected_2 = "\"timed_out\":false,\"_shards\":{\"total\":1,\"successful\":1,\"skipped\":0,\"failed\":0},\"hits\":{\"total\":{\"value\":2,\"relation\":\"eq\"},\"max_score\":null,\"hits\":[{\"_index\":\"accounts\",\"_type\":\"_doc\",\"_id\":\"MASTER001-MASTER001\",\"_score\":null,\"_source\":{\"AccountNumber\":\"MASTER001-MASTER001\",\"LocationId\":\"MASTER001\",\"CustomerId\":\"MASTER001\",\"ServiceAddressLine1\":\"100 Water\",\"ServiceAddressCity\":\"NEW YORK\",\"ServiceAddressState\":\"NY\",\"ServiceAddressZipCode\":\"57481\",\"ServiceAddressCountry\":\"USA\",\"FreeFormServiceAddressLine1\":\"\",\"FreeFormServiceAddressLine2\":\"\",\"FreeFormServiceAddressLine3\":\"\",\"CustomerName\":\"Dunning, Arthur K\",\"CustomerNameFL\":\"Arthur, Dunning K\",\"Status\":1,\"CustomerStatus\":\"Current\",\"CustomerType\":\"Individual\",\"EmailAddress\":\"auto@cogs.com\",\"Phone1\":\"9025545600\",\"Phone1Ext\":\"\",\"Phone2\":\"\",\"Phone2Ext\":\"\",\"Phone3\":\"\",\"Phone3Ext\":\"\",\"SsnSin\":\"56756\",\"DateOfBirth\":\"06/30/1965\",\"OwnerName\":\"Dunning Arthur K TR\",\"TenantName\":\"\",\"LandlordName\":\"\",\"PrimaryCustomerID\":\"MASTER001\",\"AlternateId\":\"12000\",\"SecondaryCustomer\":null,\"Connection\":null,\"AccumTypeDescription\":\"Master Location\",\"AccumType\":2},\"sort\":[\"current\",\"MASTER001-MASTER001\"]},{\"_index\":\"accounts\",\"_type\":\"_doc\",\"_id\":\"MASTERLOC01-MASTERCUS01\",\"_score\":null,\"_source\":{\"AccountNumber\":\"MASTERLOC01-MASTERCUS01\",\"LocationId\":\"MASTERLOC01\",\"CustomerId\":\"MASTERCUS01\",\"ServiceAddressLine1\":\"3123 123 Brackley ALY 32\",\"ServiceAddressCity\":\"Troy\",\"ServiceAddressState\":\"NY\",\"ServiceAddressZipCode\":\"23432\",\"ServiceAddressCountry\":\"USA\",\"FreeFormServiceAddressLine1\":\"\",\"FreeFormServiceAddressLine2\":\"\",\"FreeFormServiceAddressLine3\":\"\",\"CustomerName\":\"Customer, Master\",\"CustomerNameFL\":\"Master, Customer\",\"Status\":1,\"CustomerStatus\":\"Current\",\"CustomerType\":\"Individual\",\"EmailAddress\":\"\",\"Phone1\":\"0000000000\",\"Phone1Ext\":\"0000\",\"Phone2\":\"0000000000\",\"Phone2Ext\":\"0000\",\"Phone3\":\"0000000000\",\"Phone3Ext\":\"0000\",\"SsnSin\":\"\",\"DateOfBirth\":\"01/01/1900\",\"OwnerName\":\"\",\"TenantName\":\"\",\"LandlordName\":\"\",\"PrimaryCustomerID\":\"MASTERCUS01\",\"AlternateId\":\"\",\"SecondaryCustomer\":null,\"Connection\":null,\"AccumTypeDescription\":\"Master Location\",\"AccumType\":2},\"sort\":[\"current\",\"MASTERLOC01-MASTERCUS01\"]}]}},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("index", "accounts");
		params.put("searchQuery", "{\"LocationId\":\"Master\"},{\"CustomerId\":\"Master\"}");
		params.put("searchType", "MatchPhrasePrefix");
		CommonMethods.getMethodContainsString(uri, ver, params, expected);
		CommonMethods.getMethodContainsString(uri, ver, params, expected_2);

	}

	@Test(priority = 3, groups = "Search" , dependsOnMethods = "searchMatchPhrasePrefixv4")
	public void part_searchMatchPhrasePrefixv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/search";
		String ver = "4.0";

		String expected = "\"Search\":{\"Success\":true,\"Data\"";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("index", "accounts");
		params.put("searchQuery", "{\"LocationId\":\"Mas\"},{\"CustomerId\":\"Mas\"}");
		params.put("searchType", "MatchPhrasePrefix");
		String expected2 = "MASTER001-MASTER001";
		String expected3 = "MASTERLOC01-MASTERCUS01";
		CommonMethods.getMethodContainsString(uri, ver, params, expected);// ains(uri, ver, params, jpath);
		CommonMethods.getMethodContainsString(uri, ver, params, expected2);// ains(uri, ver, params, jpath);
		CommonMethods.getMethodContainsString(uri, ver, params, expected3);// ains(uri, ver, params, jpath);

	}

	@Test(priority = 4, groups = "Search" , dependsOnMethods = "part_searchMatchPhrasePrefixv4")
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

	@Test(priority = 4, groups = "Search" , dependsOnMethods = "part4_searchMatchPhrasePrefixv4")
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