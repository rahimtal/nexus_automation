package com.NexusAPI.tests;

import org.testng.annotations.Test; import org.testng.Assert;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.annotations.Test; import org.testng.Assert;

import com.NexustAPIAutomation.java.CommonMethods;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;

public class Private_searchControllerV4_Test  extends BaseClass{

	// This will create elastic search index if not already
//	@Test(priority = 1, groups = "Search")
	public void elascticsearchcreateindex_v_4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/search/load?index=accounts";
		String ver = "4.0";
		String payload = "{\r\n" + "    \"Index\": \"accounts\",\r\n" + "    \"RefreshModifiedOnly\": false\r\n"
				+ "}\r\n" + "";

		JsonPath result = CommonMethods.postMethodStringPayload(payload, uri, ver);
		System.out.println(result.prettyPrint());

	}

//	@Test(priority = 2, groups = "Search", dependsOnMethods = "elascticsearchcreateindex_v_4")
	public void searchMatchPhrasePrefixv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/search";
		String ver = "4.0";
		String expected = "{\"Search\":{\"Success\":true,\"Data\":";
		String expected_2 = "\"timed_out\":false,\"_shards\":{\"total\":1,\"successful\":1,\"skipped\":0,\"failed\":0},\"hits\":{\"total\":{\"value\":2,\"relation\":\"eq\"},\"max_score\":null,\"hits\":[{\"_index\":\"accounts\",\"_type\":\"_doc\",\"_id\":\"MASTER001-MASTER001\",\"_score\":null,\"_source\":{\"AccountNumber\":\"MASTER001-MASTER001\",\"LocationId\":\"MASTER001\",\"AccumType\":2,\"AccumTypeDescription\":\"Master Location\",\"CustomerId\":\"MASTER001\",\"ServiceAddressLine1\":\"100 Water\",\"ServiceAddressCity\":\"NEW YORK\",\"ServiceAddressState\":\"NY\",\"ServiceAddressZipCode\":\"57481\",\"ServiceAddressCountry\":\"USA\",\"FreeFormServiceAddressLine1\":\"\",\"FreeFormServiceAddressLine2\":\"\",\"FreeFormServiceAddressLine3\":\"\",\"CustomerName\":\"Dunning, Arthur K\",\"CustomerNameFL\":\"Arthur, Dunning K\",\"Status\":1,\"CustomerStatus\":\"Current\",\"CustomerType\":\"Individual\",\"EmailAddress\":\"auto@cogs.com\",\"Phone1\":\"9025545600\",\"Phone1Ext\":\"\",\"Phone2\":\"\",\"Phone2Ext\":\"\",\"Phone3\":\"\",\"Phone3Ext\":\"\",\"SsnSin\":\"56756\",\"DateOfBirth\":\"06/30/1965\",\"OwnerName\":\"Dunning Arthur K TR\",\"TenantName\":\"\",\"LandlordName\":\"\",\"PrimaryCustomerID\":\"MASTER001\",\"AlternateId\":\"12000\",\"SecondaryCustomer\":null,\"Connection\":null},\"sort\":[\"current\",\"MASTER001-MASTER001\"]},{\"_index\":\"accounts\",\"_type\":\"_doc\",\"_id\":\"MASTERLOC01-MASTERCUS01\",\"_score\":null,\"_source\":{\"AccountNumber\":\"MASTERLOC01-MASTERCUS01\",\"LocationId\":\"MASTERLOC01\",\"AccumType\":2,\"AccumTypeDescription\":\"Master Location\",\"CustomerId\":\"MASTERCUS01\",\"ServiceAddressLine1\":\"3123 123 Brackley ALY 32\",\"ServiceAddressCity\":\"Troy\",\"ServiceAddressState\":\"NY\",\"ServiceAddressZipCode\":\"23432\",\"ServiceAddressCountry\":\"USA\",\"FreeFormServiceAddressLine1\":\"\",\"FreeFormServiceAddressLine2\":\"\",\"FreeFormServiceAddressLine3\":\"\",\"CustomerName\":\"Customer, Master\",\"CustomerNameFL\":\"Master, Customer\",\"Status\":1,\"CustomerStatus\":\"Current\",\"CustomerType\":\"Individual\",\"EmailAddress\":\"\",\"Phone1\":\"0000000000\",\"Phone1Ext\":\"0000\",\"Phone2\":\"0000000000\",\"Phone2Ext\":\"0000\",\"Phone3\":\"0000000000\",\"Phone3Ext\":\"0000\",\"SsnSin\":\"\",\"DateOfBirth\":\"01/01/1900\",\"OwnerName\":\"\",\"TenantName\":\"\",\"LandlordName\":\"\",\"PrimaryCustomerID\":\"MASTERCUS01\",\"AlternateId\":\"\",\"SecondaryCustomer\":null,\"Connection\":null},\"sort\":[\"current\",\"MASTERLOC01-MASTERCUS01\"]}]}},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("index", "accounts");
		params.put("searchQuery", "{\"LocationId\":\"Master\"},{\"CustomerId\":\"Master\"}");
		params.put("searchType", "MatchPhrasePrefix");
		CommonMethods.getMethodContainsString(uri, ver, params, expected);
		CommonMethods.getMethodContainsString(uri, ver, params, expected_2);

	}

}