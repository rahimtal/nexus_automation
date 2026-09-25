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

public class Public_Portal_Test_consumptionHistoryController_v4  extends BaseClass{

	@Test(priority = 1, groups = "ConsumptionHistoryController")
	public void getPortalConsumptionHistory_ResponseStructure()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/portal/ConsumptionHistory";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "LOCATION008");
		params.put("CustomerId", "CUSTOMER009");
		params.put("UserDate", "2000-04-01");
		params.put("ConnectionSequence", "0");
		params.put("NumberOfYears", "20");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		JsonPath json = JsonPath.from(result);
		Assert.assertNotNull(json.get("ConsumptionHistory"), "Expected ConsumptionHistory array in response");
		Assert.assertTrue(json.getList("ConsumptionHistory").size() > 0,
				"Expected consumption history records for the location");
	}

	@Test(priority = 2, groups = "ConsumptionHistoryController")
	public void getPortalConsumptionHistory_ServiceCategoryElectric()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/portal/ConsumptionHistory";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "LOCATION008");
		params.put("CustomerId", "CUSTOMER009");
		params.put("UserDate", "2000-04-01");
		params.put("ConnectionSequence", "0");
		params.put("NumberOfYears", "20");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		JsonPath json = JsonPath.from(result);
		Assert.assertEquals(json.getString("ConsumptionHistory[0].ServiceCategory"), "Electric",
				"Expected first record ServiceCategory to be Electric");
	}

	@Test(priority = 3, groups = "ConsumptionHistoryController")
	public void getPortalConsumptionHistory_FirstRecordValues()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/portal/ConsumptionHistory";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "LOCATION008");
		params.put("CustomerId", "CUSTOMER009");
		params.put("UserDate", "2000-04-01");
		params.put("ConnectionSequence", "0");
		params.put("NumberOfYears", "20");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		JsonPath json = JsonPath.from(result);
		Assert.assertEquals(json.getString("ConsumptionHistory[0].ReadingDate"), "1997-03-31",
				"Expected first record ReadingDate 1997-03-31");
		Assert.assertEquals(json.getString("ConsumptionHistory[0].ConsumptionPeriodEnd"), "1997-03-01",
				"Expected first record ConsumptionPeriodEnd 1997-03-01");
		Assert.assertEquals(json.getString("ConsumptionHistory[0].Consumption"), "820.00",
				"Expected first record Consumption 820.00");
		Assert.assertEquals(json.getString("ConsumptionHistory[0].NumberOfDays"), "88",
				"Expected first record NumberOfDays 88");
		Assert.assertEquals(json.getString("ConsumptionHistory[0].UnitsPerDay"), "9.32",
				"Expected first record UnitsPerDay 9.32");
		Assert.assertEquals(json.getInt("ConsumptionHistory[0].CurrentReading"), 620,
				"Expected first record CurrentReading 620");
		Assert.assertEquals(json.getInt("ConsumptionHistory[0].PreviousReading"), 0,
				"Expected first record PreviousReading 0");
		Assert.assertEquals(json.getFloat("ConsumptionHistory[0].BilledAmount"), 73.37f,
				"Expected first record BilledAmount 73.37");
	}

	@Test(priority = 4, groups = "ConsumptionHistoryController")
	public void getPortalConsumptionHistory_SecondRecordValues()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/portal/ConsumptionHistory";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "LOCATION008");
		params.put("CustomerId", "CUSTOMER009");
		params.put("UserDate", "2000-04-01");
		params.put("ConnectionSequence", "0");
		params.put("NumberOfYears", "20");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		JsonPath json = JsonPath.from(result);
		Assert.assertEquals(json.getString("ConsumptionHistory[1].ReadingDate"), "1997-06-30",
				"Expected second record ReadingDate 1997-06-30");
		Assert.assertEquals(json.getString("ConsumptionHistory[1].Consumption"), "1200.00",
				"Expected second record Consumption 1200.00");
		Assert.assertEquals(json.getString("ConsumptionHistory[1].UnitsPerDay"), "13.19",
				"Expected second record UnitsPerDay 13.19");
		Assert.assertEquals(json.getInt("ConsumptionHistory[1].CurrentReading"), 1220,
				"Expected second record CurrentReading 1220");
		Assert.assertEquals(json.getInt("ConsumptionHistory[1].PreviousReading"), 620,
				"Expected second record PreviousReading 620");
	}

	@Test(priority = 5, groups = "ConsumptionHistoryController")
	public void getPortalConsumptionHistory_MeterEquipments()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/portal/ConsumptionHistory";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "LOCATION008");
		params.put("CustomerId", "CUSTOMER009");
		params.put("UserDate", "2000-04-01");
		params.put("ConnectionSequence", "0");
		params.put("NumberOfYears", "20");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		JsonPath json = JsonPath.from(result);
		Assert.assertTrue(json.getString("ConsumptionHistory[0].MeterEquipments").contains("MTG00000000003"),
				"Expected MeterEquipments to include MTG00000000003");
	}

	@Test(priority = 6, groups = "ConsumptionHistoryController")
	public void getPortalConsumptionHistory_InvalidCustomerId()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/portal/ConsumptionHistory";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "LOCATION008");
		params.put("CustomerId", "INVALID_CUSTOMER");
		params.put("UserDate", "2000-04-01");
		params.put("ConnectionSequence", "0");
		params.put("NumberOfYears", "20");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		JsonPath json = JsonPath.from(result);
		java.util.List<Object> records = json.getList("ConsumptionHistory");
		Assert.assertTrue(records == null || records.isEmpty(),
				"Expected no consumption history for an invalid CustomerId");
	}

	// CPDEV-27430 : Include billed meter with zero billing in portal consumption history
	@Test(priority = 7, groups = "ConsumptionHistoryController")
	public void getPortalConsumptionHistory_ZeroBilledMeterIncluded()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		JsonPath json = JsonPath.from(getZeroBillLocationHistory("4.0"));
		Assert.assertEquals(json.getList("ConsumptionHistory.findAll { it.BilledAmount == 0.00 }.ReadingDate"),
				java.util.Arrays.asList(ZERO_BILL_READING_DATES),
				"Expected both zero-billed readings of " + ZERO_BILL_LOCATION + " to be returned");
	}

	@Test(priority = 8, groups = "ConsumptionHistoryController")
	public void getPortalConsumptionHistory_ZeroBilledMeterValues()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		JsonPath json = JsonPath.from(getZeroBillLocationHistory("4.0"));
		String record = "ConsumptionHistory.find { it.ReadingDate == '1998-08-31' }.";
		Assert.assertEquals(json.getFloat(record + "BilledAmount"), 0.00f,
				"Expected BilledAmount 0.00 on the 1998-08-31 reading");
		Assert.assertEquals(json.getFloat(record + "SewerBilledAmount"), 0.00f,
				"Expected SewerBilledAmount 0.00 on the 1998-08-31 reading");
		Assert.assertEquals(Float.parseFloat(json.getString(record + "Consumption")), 50.0f,
				"Expected Consumption 50 on the 1998-08-31 reading");
		Assert.assertEquals(json.getString(record + "NumberOfDays"), "61",
				"Expected NumberOfDays 61 on the 1998-08-31 reading");
		Assert.assertEquals(json.getString(record + "UnitsPerDay"), "0.82",
				"Expected UnitsPerDay 0.82 on the 1998-08-31 reading");
		Assert.assertEquals(json.getInt(record + "CurrentReading"), 1240,
				"Expected CurrentReading 1240 on the 1998-08-31 reading");
		Assert.assertEquals(json.getInt(record + "PreviousReading"), 1190,
				"Expected PreviousReading 1190 on the 1998-08-31 reading");
	}

	// The zero-billed row must still carry its meter/service details, otherwise the
	// portal renders an unattributed consumption line.
	@Test(priority = 9, groups = "ConsumptionHistoryController")
	public void getPortalConsumptionHistory_ZeroBilledMeterKeepsEquipmentDetails()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		JsonPath json = JsonPath.from(getZeroBillLocationHistory("4.0"));
		String record = "ConsumptionHistory.find { it.ReadingDate == '1998-06-30' }.";
		Assert.assertEquals(json.getFloat(record + "BilledAmount"), 0.00f,
				"Expected BilledAmount 0.00 on the 1998-06-30 reading");
		Assert.assertEquals(json.getString(record + "ServiceCategory"), "Electric",
				"Expected ServiceCategory Electric on the zero-billed reading");
		Assert.assertEquals(json.getString(record + "MeterEquipments"), ZERO_BILL_EQUIPMENT,
				"Expected the zero-billed reading to keep its meter equipment");
		Assert.assertEquals(json.getString(record + "ConsumptionPeriodEnd"), "1998-06-01",
				"Expected ConsumptionPeriodEnd 1998-06-01 on the zero-billed reading");
	}

	// ELECWAT001 connection 1 has two billed readings whose charges net to zero.
	static final String ZERO_BILL_LOCATION = "ELECWAT001";
	static final String ZERO_BILL_CUSTOMER = "CUSTOMER007";
	static final String ZERO_BILL_EQUIPMENT = "EQUIPMENT011";
	static final String[] ZERO_BILL_READING_DATES = { "1998-06-30", "1998-08-31" };

	static String getZeroBillLocationHistory(String ver) throws InterruptedException, IOException {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", ZERO_BILL_LOCATION);
		params.put("CustomerId", ZERO_BILL_CUSTOMER);
		params.put("UserDate", "2000-04-01");
		params.put("ConnectionSequence", "1");
		params.put("NumberOfYears", "20");
		String result = CommonMethods.getMethodasString("/portal/ConsumptionHistory", ver, params);
		JsonPath json = JsonPath.from(result);
		java.util.List<Object> records = json.getList("ConsumptionHistory");
		if (records == null || records.isEmpty() || Boolean.FALSE.equals(json.get("ConsumptionHistory[0].Success"))) {
			throw new org.testng.SkipException("No consumption history for " + ZERO_BILL_LOCATION + "/"
					+ ZERO_BILL_CUSTOMER + " on this environment. Response: " + result);
		}
		return result;
	}

}