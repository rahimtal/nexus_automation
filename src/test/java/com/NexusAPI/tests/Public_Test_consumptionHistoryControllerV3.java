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

	// CPDEV-27430 : Include billed meter with zero billing in portal consumption history
	@Test(priority = 7, groups = "ConsumptionHistoryController")
	public void getPortalConsumptionHistory_ZeroBilledMeterIncluded_v3()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		JsonPath json = JsonPath
				.from(Public_Portal_Test_consumptionHistoryController_v4.getZeroBillLocationHistory("3.0"));
		Assert.assertEquals(json.getList("ConsumptionHistory.findAll { it.BilledAmount == 0.00 }.ReadingDate"),
				java.util.Arrays.asList(Public_Portal_Test_consumptionHistoryController_v4.ZERO_BILL_READING_DATES),
				"Expected v3 to return both zero-billed readings of "
						+ Public_Portal_Test_consumptionHistoryController_v4.ZERO_BILL_LOCATION);
	}

	@Test(priority = 8, groups = "ConsumptionHistoryController")
	public void getPortalConsumptionHistory_ZeroBilledMeterKeepsEquipmentDetails_v3()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		JsonPath json = JsonPath
				.from(Public_Portal_Test_consumptionHistoryController_v4.getZeroBillLocationHistory("3.0"));
		String record = "ConsumptionHistory.find { it.ReadingDate == '1998-06-30' }.";
		Assert.assertEquals(json.getFloat(record + "BilledAmount"), 0.00f,
				"Expected BilledAmount 0.00 on the 1998-06-30 reading");
		Assert.assertEquals(json.getString(record + "ServiceCategory"), "Electric",
				"Expected ServiceCategory Electric on the zero-billed reading");
		Assert.assertEquals(json.getString(record + "MeterEquipments"),
				Public_Portal_Test_consumptionHistoryController_v4.ZERO_BILL_EQUIPMENT,
				"Expected the zero-billed reading to keep its meter equipment");
	}

}