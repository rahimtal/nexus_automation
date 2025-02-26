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
		String expected_2 = "\"timed_out\":false,\"_shards\":{\"total\":1,\"successful\":1,\"skipped\":0,\"failed\":0},\"hits\":{\"total\":{\"value\":4,\"relation\":\"eq\"},\"max_score\":null,\"hits\":[{\"_index\":\"accounts\",\"_type\":\"_doc\",\"_id\":\"ELECWAT001-CUSTOMER007\",\"_score\":null,\"_source\":{\"AccountNumber\":\"ELECWAT001-CUSTOMER007\",\"LocationId\":\"ELECWAT001\",\"AccumType\":1,\"AccumTypeDescription\":\"Not Applicable\",\"CustomerId\":\"CUSTOMER007\",\"ServiceAddressLine1\":\"12 A GRAFTON HILLS n APT 4\",\"ServiceAddressCity\":\"NEW YORK\",\"ServiceAddressState\":\"NY\",\"ServiceAddressZipCode\":\"12123\",\"ServiceAddressCountry\":\"USA\",\"FreeFormServiceAddressLine1\":\"\",\"FreeFormServiceAddressLine2\":\"\",\"FreeFormServiceAddressLine3\":\"\",\"CustomerName\":\"MacKenzie, Sally E\",\"CustomerNameFL\":\"Sally, MacKenzie E\",\"Status\":1,\"CustomerStatus\":\"Current\",\"CustomerType\":\"Individual\",\"EmailAddress\":\"\",\"Phone1\":\"9028948138\",\"Phone1Ext\":\"0000\",\"Phone2\":\"\",\"Phone2Ext\":\"\",\"Phone3\":\"\",\"Phone3Ext\":\"\",\"SsnSin\":\"111123123\",\"DateOfBirth\":\"11/27/1960\",\"OwnerName\":\"MacKenzie Sally E\",\"TenantName\":\"Name Test\",\"LandlordName\":\"\",\"PrimaryCustomerID\":\"CUSTOMER007\",\"AlternateId\":\"\",\"SecondaryCustomer\":[{\"Id\":\"03332301204\",\"Name\":\"Rahim, Talha\"}],\"Connection\":[{\"EquipmentId\":\"EQUIP-GAS-1\",\"RemoteId\":\"\"},{\"EquipmentId\":\"EQUIPMENT011\",\"RemoteId\":\"\"}]},\"sort\":[\"current\",\"ELECWAT001-CUSTOMER007\"]}]}},\"Messages\":[]}}";
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