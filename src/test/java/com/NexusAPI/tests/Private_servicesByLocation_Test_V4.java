package com.NexusAPI.Tests;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;

import io.restassured.path.json.JsonPath;

public class Private_servicesByLocation_Test_V4 extends BaseClass {

	@Test(priority = 1, groups = "servicesByLocation")
	public void getServicesByLocation_v4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/servicesByLocation/getServicesByLocation";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<>();
		params.put("CustomerId", "0012200");
		params.put("LocationId", "000000000523000");
		params.put("TransferDate", "1900-01-01");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertTrue(result.contains("LocationID"), "Expected services list for valid location");
		Assert.assertTrue(result.contains("IsEligibleForDeletion"),
				"Expected IsEligibleForDeletion field in response");
	}

	@Test(priority = 2, groups = "servicesByLocation")
	public void getServicesByLocation_v4_IsEligibleForDeletionFalse()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/servicesByLocation/getServicesByLocation";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<>();
		params.put("CustomerId", "0012200");
		params.put("LocationId", "000000000523000");
		params.put("TransferDate", "1900-01-01");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertTrue(result.contains("\"IsEligibleForDeletion\":false"),
				"Expected IsEligibleForDeletion to be false for active service location");
	}

	@Test(priority = 3, groups = "servicesByLocation")
	public void getServicesByLocation_v4_InvalidCustomerId()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/servicesByLocation/getServicesByLocation";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<>();
		params.put("CustomerId", "INVALID_CUSTOMER");
		params.put("LocationId", "000000000523000");
		params.put("TransferDate", "1900-01-01");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertFalse(result.contains("\"LocationID\""), "Expected no services for invalid CustomerId");
	}

	@Test(priority = 4, groups = "servicesByLocation")
	public void getServicesByLocation_v4_MissingLocationId()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/servicesByLocation/getServicesByLocation";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<>();
		params.put("CustomerId", "0012200");
		params.put("TransferDate", "1900-01-01");
		// Missing LocationId
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertFalse(result.contains("\"LocationID\""), "Expected no services when LocationId is missing");
	}

	@Test(priority = 5, groups = "servicesByLocation")
	public void getServicesByLocation_v4_EmptyParams()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/servicesByLocation/getServicesByLocation";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<>();
		// No parameters provided
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertFalse(result.contains("\"LocationID\""), "Expected no services when no parameters are provided");
	}

	@Test(priority = 6, groups = "servicesByLocation")
	public void getServicesByLocation_v4_ResponseStructure()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/servicesByLocation/getServicesByLocation";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<>();
		params.put("CustomerId", "0012200");
		params.put("LocationId", "000000000523000");
		params.put("TransferDate", "1900-01-01");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		JsonPath json = JsonPath.from(result);
		Assert.assertNotNull(json.get("ServiceByLocation"), "Expected ServiceByLocation array in response");
		int count = json.getList("ServiceByLocation").size();
		Assert.assertEquals(count, 6, "Expected 6 services for the location");
	}

	@Test(priority = 7, groups = "servicesByLocation")
	public void getServicesByLocation_v4_WaterService()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/servicesByLocation/getServicesByLocation";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<>();
		params.put("CustomerId", "0012200");
		params.put("LocationId", "000000000523000");
		params.put("TransferDate", "1900-01-01");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		JsonPath json = JsonPath.from(result);
		Assert.assertEquals(json.getString("ServiceByLocation.find { it.Connection == '1' }.ServiceCategoryDescription"),
				"Water", "Connection 1 should be a Water service");
		Assert.assertEquals(json.getString("ServiceByLocation.find { it.Connection == '1' }.ServiceType"), "WR",
				"Connection 1 should have ServiceType WR");
		Assert.assertEquals(json.getString("ServiceByLocation.find { it.Connection == '1' }.RateId"), "WR_0.62_CITY",
				"Connection 1 should have RateId WR_0.62_CITY");
		Assert.assertEquals(json.getInt("ServiceByLocation.find { it.Connection == '1' }.Consumption"), 24,
				"Connection 1 should have Consumption 24");
	}

	@Test(priority = 8, groups = "servicesByLocation")
	public void getServicesByLocation_v4_ElectricServiceWithKwh()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/servicesByLocation/getServicesByLocation";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<>();
		params.put("CustomerId", "0012200");
		params.put("LocationId", "000000000523000");
		params.put("TransferDate", "1900-01-01");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		JsonPath json = JsonPath.from(result);
		Assert.assertEquals(json.getString("ServiceByLocation.find { it.Connection == '2' }.ServiceCategoryDescription"),
				"Electric", "Connection 2 should be an Electric service");
		Assert.assertEquals(json.getString("ServiceByLocation.find { it.Connection == '2' }.ServiceType"), "RE_MR",
				"Connection 2 should have ServiceType RE_MR");
		Assert.assertEquals(json.getString("ServiceByLocation.find { it.Connection == '2' }.Equipment.UnitOfMeasure"),
				"Kwh", "Connection 2 Equipment should have UnitOfMeasure Kwh");
	}

	@Test(priority = 9, groups = "servicesByLocation")
	public void getServicesByLocation_v4_SewerServiceAlternate()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/servicesByLocation/getServicesByLocation";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<>();
		params.put("CustomerId", "0012200");
		params.put("LocationId", "000000000523000");
		params.put("TransferDate", "1900-01-01");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		JsonPath json = JsonPath.from(result);
		Assert.assertEquals(json.getString("ServiceByLocation.find { it.Connection == '5' }.ServiceCategoryDescription"),
				"Sewer", "Connection 5 should be a Sewer service");
		Assert.assertEquals(json.getString("ServiceByLocation.find { it.Connection == '5' }.ServiceType"), "PC",
				"Connection 5 should have ServiceType PC");
		Assert.assertEquals(json.getInt("ServiceByLocation.find { it.Connection == '5' }.Alternate"), 1,
				"Connection 5 should be flagged as Alternate");
	}

	@Test(priority = 10, groups = "servicesByLocation")
	public void getServicesByLocation_v4_AllConnectionsActive()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/servicesByLocation/getServicesByLocation";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<>();
		params.put("CustomerId", "0012200");
		params.put("LocationId", "000000000523000");
		params.put("TransferDate", "1900-01-01");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		JsonPath json = JsonPath.from(result);
		java.util.List<String> statuses = json.getList("ServiceByLocation.ConnectionStatusDescription");
		for (String status : statuses) {
			Assert.assertEquals(status, "Active", "All connections should be Active");
		}
	}

	@Test(priority = 11, groups = "servicesByLocation")
	public void getServicesByLocation_v4_DrillBackUrl()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/servicesByLocation/getServicesByLocation";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<>();
		params.put("CustomerId", "0012200");
		params.put("LocationId", "000000000523000");
		params.put("TransferDate", "1900-01-01");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		JsonPath json = JsonPath.from(result);
		String drillBackUrl = json.getString("ServiceByLocation.find { it.Connection == '1' }.drillBackURL");
		Assert.assertTrue(drillBackUrl.contains("Func=ConnectionDetail"),
				"drillBackURL should reference ConnectionDetail");
		Assert.assertTrue(drillBackUrl.contains("LocationID=000000000523000"),
				"drillBackURL should reference the requested LocationID");
	}

	@Test(priority = 12, groups = "servicesByLocation")
	public void getServicesByLocation_v4_NoneEligibleForDeletion()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/servicesByLocation/getServicesByLocation";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<>();
		params.put("CustomerId", "0012200");
		params.put("LocationId", "000000000523000");
		params.put("TransferDate", "1900-01-01");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		JsonPath json = JsonPath.from(result);
		java.util.List<Boolean> eligibility = json.getList("ServiceByLocation.IsEligibleForDeletion");
		for (Boolean eligible : eligibility) {
			Assert.assertFalse(eligible, "Active services should not be eligible for deletion");
		}
	}

}