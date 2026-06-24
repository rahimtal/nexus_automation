package com.NexusAPI.Tests;

import org.testng.annotations.Test; import org.testng.Assert;
import org.testng.annotations.Test; import org.testng.Assert;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.annotations.Test; import org.testng.Assert;

import com.NexustAPIAutomation.java.CommonMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

public class Public_Test_consumptionHistoryControllerV3  extends BaseClass {

	public static ValidatableResponse jsonPathEvaluator;


	@Test(priority = 6, groups = "ConsumptionHistoryController")
	public void getConsumptionHistory_InvalidCustomerId()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/consumptionHistory/getConsumptionHistory";
		String ver = "3.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "LOCATION008");
		params.put("CustomerId", "INVALID_CUSTOMER");
		params.put("ConnectionSequence", "0");
		params.put("UserDate", "2000-04-01");
		params.put("NumberOfYears", "20");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		JsonPath json = JsonPath.from(result);
		java.util.List<Object> records = json.getList("ConsumptionHistory");
		Assert.assertTrue(records == null || records.isEmpty(),
				"Expected no consumption history for an invalid CustomerId");
	}

}