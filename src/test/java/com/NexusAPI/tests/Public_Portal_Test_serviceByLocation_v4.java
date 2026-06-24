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

public class Public_Portal_Test_serviceByLocation_v4  extends BaseClass{

	@Test(priority = 1, groups = "ServiceByLocation")
	public void getPortalServiceByLocation_ResponseStructure()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/portal/ServiceByLocation";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("CustomerId", "CUSTOMER014");
		params.put("LocationId", "WATER002");
		params.put("TransferDate", "1900-01-01");
		params.put("ResponseVersion", "1");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		JsonPath json = JsonPath.from(result);
		Assert.assertNotNull(json.get("result"), "Expected result array in response");
		Assert.assertTrue(json.getList("result").size() > 0, "Expected services for the location");
	}

	@Test(priority = 2, groups = "ServiceByLocation")
	public void getPortalServiceByLocation_WaterService()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/portal/ServiceByLocation";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("CustomerId", "CUSTOMER014");
		params.put("LocationId", "WATER002");
		params.put("TransferDate", "1900-01-01");
		params.put("ResponseVersion", "1");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		JsonPath json = JsonPath.from(result);
		Assert.assertEquals(json.getString("result.find { it.ServiceType == 'WATER' }.LocationID"), "WATER002",
				"Expected Water record LocationID WATER002");
		Assert.assertEquals(json.getString("result.find { it.ServiceType == 'WATER' }.ServiceCategoryDescription"),
				"Water", "Expected the matched record to be a Water service");
		Assert.assertEquals(json.getString("result.find { it.ServiceType == 'WATER' }.ServiceType"), "WATER",
				"Expected ServiceType WATER");
		Assert.assertEquals(json.getString("result.find { it.ServiceType == 'WATER' }.RateID"), "WATERMETERED",
				"Expected RateID WATERMETERED");
	}

	@Test(priority = 3, groups = "ServiceByLocation")
	public void getPortalServiceByLocation_ConnectionDetails()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/portal/ServiceByLocation";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("CustomerId", "CUSTOMER014");
		params.put("LocationId", "WATER002");
		params.put("TransferDate", "1900-01-01");
		params.put("ResponseVersion", "1");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		JsonPath json = JsonPath.from(result);
		Assert.assertEquals(json.getString("result.find { it.ServiceType == 'WATER' }.Connection"), "1",
				"Expected Water record Connection 1");
		Assert.assertEquals(json.getString("result.find { it.ServiceType == 'WATER' }.EquipmentID"), "WATEREQUIP002",
				"Expected Water record EquipmentID WATEREQUIP002");
		Assert.assertEquals(json.getString("result.find { it.ServiceType == 'WATER' }.RouteID"), "ROUTEW001",
				"Expected Water record RouteID ROUTEW001");
		Assert.assertEquals(json.getString("result.find { it.ServiceType == 'WATER' }.CycleID"), "BIMONTHLY",
				"Expected Water record CycleID BIMONTHLY");
		Assert.assertEquals(json.getString("result.find { it.ServiceType == 'WATER' }.ConnectionDate"), "1997-07-16",
				"Expected Water record ConnectionDate 1997-07-16");
	}

	@Test(priority = 4, groups = "ServiceByLocation")
	public void getPortalServiceByLocation_StatusActive()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/portal/ServiceByLocation";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("CustomerId", "CUSTOMER014");
		params.put("LocationId", "WATER002");
		params.put("TransferDate", "1900-01-01");
		params.put("ResponseVersion", "1");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		JsonPath json = JsonPath.from(result);
		Assert.assertEquals(json.getString("result[0].ConnectionStatusDescription"), "Active",
				"Expected first record ConnectionStatusDescription Active");
		Assert.assertEquals(json.getString("result[0].ConnectionStatus"), "2",
				"Expected first record ConnectionStatus 2");
	}

	@Test(priority = 5, groups = "ServiceByLocation")
	public void getPortalServiceByLocation_ReadingValues()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/portal/ServiceByLocation";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("CustomerId", "CUSTOMER014");
		params.put("LocationId", "WATER002");
		params.put("TransferDate", "1900-01-01");
		params.put("ResponseVersion", "1");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		JsonPath json = JsonPath.from(result);
		Assert.assertEquals(json.getString("result.find { it.ServiceType == 'WATER' }.ReadingDate"), "2000-02-29",
				"Expected Water record ReadingDate 2000-02-29");
		Assert.assertEquals(json.getFloat("result.find { it.ServiceType == 'WATER' }.Reading"), 43640.0f,
				"Expected Water record Reading 43640.00000");
		Assert.assertEquals(json.getFloat("result.find { it.ServiceType == 'WATER' }.Consumption"), 10.0f,
				"Expected Water record Consumption 10.00000");
		Assert.assertEquals(json.getString("result.find { it.ServiceType == 'WATER' }.SequenceNumber"), "401",
				"Expected Water record SequenceNumber 401");
	}

	@Test(priority = 6, groups = "ServiceByLocation")
	public void getPortalServiceByLocation_InvalidCustomerId()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/portal/ServiceByLocation";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("CustomerId", "INVALID_CUSTOMER");
		params.put("LocationId", "WATER002");
		params.put("TransferDate", "1900-01-01");
		params.put("ResponseVersion", "1");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		JsonPath json = JsonPath.from(result);
		Object records = json.get("result");
		boolean hasServices = (records instanceof java.util.List) && !((java.util.List<?>) records).isEmpty();
		Assert.assertFalse(hasServices, "Expected no services for an invalid CustomerId");
	}

}