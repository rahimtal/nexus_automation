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

public class Public_Test_consumptionHistoryController_v4  extends BaseClass{

	public static ValidatableResponse jsonPathEvaluator;

	
		@Test(priority = 1, groups = "ConsumptionHistoryController" )
	public void getconsumptionHistoryController() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
	//	CommonMethods.Bug("https://cogsdale.atlassian.net/browse/CPDEV-23531");
		String uri = "/consumptionHistory/getConsumptionHistory";
		String ver = "3.0";
		String jpath = "./\\TestData\\consumptionHist.json";
				
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "LOCATION008");
		params.put("CustomerId", "CUSTOMER009");
		params.put("ConnectionSequence", "0");
		params.put("UserDate", "2000-04-01");
		params.put("NumberOfYears", "20");
		
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
		
	}

	@Test(priority = 2, groups = "ConsumptionHistoryController")
	public void getConsumptionHistory_ResponseStructure()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/consumptionHistory/getConsumptionHistory";
		String ver = "3.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "LOCATION008");
		params.put("CustomerId", "CUSTOMER009");
		params.put("ConnectionSequence", "0");
		params.put("UserDate", "2000-04-01");
		params.put("NumberOfYears", "20");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		JsonPath json = JsonPath.from(result);
		Assert.assertNotNull(json.get("ConsumptionHistory"), "Expected ConsumptionHistory array in response");
		Assert.assertTrue(json.getList("ConsumptionHistory").size() > 0,
				"Expected consumption history records for the location");
	}

	@Test(priority = 3, groups = "ConsumptionHistoryController")
	public void getConsumptionHistory_ServiceCategoryElectric()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/consumptionHistory/getConsumptionHistory";
		String ver = "3.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "LOCATION008");
		params.put("CustomerId", "CUSTOMER009");
		params.put("ConnectionSequence", "0");
		params.put("UserDate", "2000-04-01");
		params.put("NumberOfYears", "20");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		JsonPath json = JsonPath.from(result);
		Assert.assertEquals(json.getString("ConsumptionHistory[0].ServiceCategory"), "Electric",
				"Expected first record ServiceCategory to be Electric");
	}

	@Test(priority = 4, groups = "ConsumptionHistoryController")
	public void getConsumptionHistory_FirstReadingValues()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/consumptionHistory/getConsumptionHistory";
		String ver = "3.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "LOCATION008");
		params.put("CustomerId", "CUSTOMER009");
		params.put("ConnectionSequence", "0");
		params.put("UserDate", "2000-04-01");
		params.put("NumberOfYears", "20");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		JsonPath json = JsonPath.from(result);
		Assert.assertEquals(json.getString("ConsumptionHistory[0].ReadingDate"), "1997-03-29",
				"Expected first record ReadingDate 1997-03-29");
		Assert.assertEquals(json.getString("ConsumptionHistory[0].ConsumptionPeriodEnd"), "1997-03-01",
				"Expected first record ConsumptionPeriodEnd 1997-03-01");
		Assert.assertEquals(json.getString("ConsumptionHistory[0].Consumption"), "820",
				"Expected first record Consumption 820");
		Assert.assertEquals(json.getInt("ConsumptionHistory[0].CurrentReading"), 620,
				"Expected first record CurrentReading 620");
		Assert.assertEquals(json.getInt("ConsumptionHistory[0].PreviousReading"), 0,
				"Expected first record PreviousReading 0");
	}

	@Test(priority = 5, groups = "ConsumptionHistoryController")
	public void getConsumptionHistory_MeterEquipments()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/consumptionHistory/getConsumptionHistory";
		String ver = "3.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "LOCATION008");
		params.put("CustomerId", "CUSTOMER009");
		params.put("ConnectionSequence", "0");
		params.put("UserDate", "2000-04-01");
		params.put("NumberOfYears", "20");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		JsonPath json = JsonPath.from(result);
		String meterEquipments = json.getString("ConsumptionHistory[0].MeterEquipments");
		Assert.assertTrue(meterEquipments.contains("EQUIPMENT007"),
				"Expected MeterEquipments to include EQUIPMENT007");
		Assert.assertTrue(meterEquipments.contains("EQUIPMENT008"),
				"Expected MeterEquipments to include EQUIPMENT008");
	}

	@Test(priority = 6, groups = "ConsumptionHistoryController")
	public void getConsumptionHistoryAdvanced_ResponseStructure()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/ConsumptionHistory/getConsumptionHistoryAdvanced";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "LOCATION008");
		params.put("CustomerId", "CUSTOMER009");
		params.put("UserDate", "2000-04-01");
		params.put("Electric", "true");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		JsonPath json = JsonPath.from(result);
		Assert.assertNotNull(json.get("ConsumptionHistoryAdvanced"),
				"Expected ConsumptionHistoryAdvanced array in response");
		Assert.assertEquals(json.getList("ConsumptionHistoryAdvanced").size(), 13,
				"Expected 13 advanced consumption history records");
	}

	@Test(priority = 7, groups = "ConsumptionHistoryController")
	public void getConsumptionHistoryAdvanced_AllElectric()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/ConsumptionHistory/getConsumptionHistoryAdvanced";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "LOCATION008");
		params.put("CustomerId", "CUSTOMER009");
		params.put("UserDate", "2000-04-01");
		params.put("Electric", "true");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		JsonPath json = JsonPath.from(result);
		java.util.List<String> categories = json.getList("ConsumptionHistoryAdvanced.ServiceCategory");
		for (String category : categories) {
			Assert.assertEquals(category, "Electric", "All advanced records should be Electric");
		}
	}

	@Test(priority = 8, groups = "ConsumptionHistoryController")
	public void getConsumptionHistoryAdvanced_FirstRecordValues()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/ConsumptionHistory/getConsumptionHistoryAdvanced";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "LOCATION008");
		params.put("CustomerId", "CUSTOMER009");
		params.put("UserDate", "2000-04-01");
		params.put("Electric", "true");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		JsonPath json = JsonPath.from(result);
		Assert.assertEquals(json.getString("ConsumptionHistoryAdvanced[0].ReadingDate"), "1997-03-29",
				"Expected first record ReadingDate 1997-03-29");
		Assert.assertEquals(json.getString("ConsumptionHistoryAdvanced[0].ConsumptionPeriodEnd"), "1997-03-01",
				"Expected first record ConsumptionPeriodEnd 1997-03-01");
		Assert.assertEquals(json.getString("ConsumptionHistoryAdvanced[0].Consumption"), "0",
				"Expected first record Consumption 0");
		Assert.assertEquals(json.getString("ConsumptionHistoryAdvanced[0].NumberOfDays"), "88",
				"Expected first record NumberOfDays 88");
		Assert.assertEquals(json.getString("ConsumptionHistoryAdvanced[0].UnitsPerDay"), "0.00",
				"Expected first record UnitsPerDay 0.00");
	}

	@Test(priority = 9, groups = "ConsumptionHistoryController")
	public void getConsumptionHistoryAdvanced_ConsumptionValueByReadingDate()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/ConsumptionHistory/getConsumptionHistoryAdvanced";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "LOCATION008");
		params.put("CustomerId", "CUSTOMER009");
		params.put("UserDate", "2000-04-01");
		params.put("Electric", "true");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		JsonPath json = JsonPath.from(result);
		Assert.assertEquals(
				json.getString("ConsumptionHistoryAdvanced.find { it.ReadingDate == '1998-06-30' }.Consumption"),
				"101", "Expected Consumption 101 for ReadingDate 1998-06-30");
		Assert.assertEquals(
				json.getString("ConsumptionHistoryAdvanced.find { it.ReadingDate == '1998-06-30' }.UnitsPerDay"),
				"1.11", "Expected UnitsPerDay 1.11 for ReadingDate 1998-06-30");
	}

	@Test(priority = 10, groups = "ConsumptionHistoryController")
	public void getConsumptionHistoryAdvanced_MeterEquipments()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/ConsumptionHistory/getConsumptionHistoryAdvanced";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "LOCATION008");
		params.put("CustomerId", "CUSTOMER009");
		params.put("UserDate", "2000-04-01");
		params.put("Electric", "true");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		JsonPath json = JsonPath.from(result);
		String meterEquipments = json
				.getString("ConsumptionHistoryAdvanced.find { it.ReadingDate == '1998-03-31' }.MeterEquipments");
		Assert.assertTrue(meterEquipments.contains("EQUIPMENT006"),
				"Expected MeterEquipments to include EQUIPMENT006");
		Assert.assertTrue(meterEquipments.contains("EQUIPMENT007"),
				"Expected MeterEquipments to include EQUIPMENT007");
		Assert.assertTrue(meterEquipments.contains("EQUIPMENT008"),
				"Expected MeterEquipments to include EQUIPMENT008");
	}

	@Test(priority = 11, groups = "ConsumptionHistoryController")
	public void getConsumptionHistoryAdvanced_AllReadingsZero()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/ConsumptionHistory/getConsumptionHistoryAdvanced";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "LOCATION008");
		params.put("CustomerId", "CUSTOMER009");
		params.put("UserDate", "2000-04-01");
		params.put("Electric", "true");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		JsonPath json = JsonPath.from(result);
		java.util.List<Integer> currentReadings = json.getList("ConsumptionHistoryAdvanced.CurrentReading");
		for (Integer reading : currentReadings) {
			Assert.assertEquals(reading.intValue(), 0, "All advanced records should have CurrentReading 0");
		}
	}

}