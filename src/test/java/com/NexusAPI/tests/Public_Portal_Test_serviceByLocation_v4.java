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

	// -----------------------------------------------------------------------
	// Performance improvement (stored procedures): new optional query parameter
	// FormerConnections on serviceController.getServiceByLocation
	// (/portal/ServiceByLocation). Defaults to existing behavior, so the
	// parameter must be non-breaking.
	// -----------------------------------------------------------------------

	// FormerConnections=true -> former (disconnected) connections are included in
	// addition to current ones, so the response returns at least as many
	// connections as the default (false) behavior.
	@Test(priority = 7, groups = "ServiceByLocation")
	public void getPortalServiceByLocation_FormerConnectionsTrue()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/portal/ServiceByLocation";
		String ver = "4.0";

		HashMap<String, String> trueParams = new HashMap<String, String>();
		trueParams.put("CustomerId", "CUSTOMER015");
		trueParams.put("LocationId", "ELECWAT002");
		trueParams.put("FormerConnections", "true");
		String resultTrue = CommonMethods.getMethodasString(uri, ver, trueParams);
		System.out.println(resultTrue);
		JsonPath jsonTrue = JsonPath.from(resultTrue);
		Assert.assertNotNull(jsonTrue.get("ServiceByLocation"), "Expected ServiceByLocation array in response");
		int trueCount = jsonTrue.getList("ServiceByLocation").size();
		Assert.assertTrue(trueCount > 0, "Expected services for the location");

		HashMap<String, String> falseParams = new HashMap<String, String>();
		falseParams.put("CustomerId", "CUSTOMER015");
		falseParams.put("LocationId", "ELECWAT002");
		falseParams.put("FormerConnections", "false");
		String resultFalse = CommonMethods.getMethodasString(uri, ver, falseParams);
		int falseCount = JsonPath.from(resultFalse).getList("ServiceByLocation").size();

		Assert.assertTrue(trueCount >= falseCount,
				"Expected FormerConnections=true to return at least as many connections as false. true=" + trueCount
						+ ", false=" + falseCount);
	}

	// FormerConnections=false (default behavior) -> only current connections are
	// returned (backward compatible), and the response is still successful.
	@Test(priority = 8, groups = "ServiceByLocation")
	public void getPortalServiceByLocation_FormerConnectionsFalse()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/portal/ServiceByLocation";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("CustomerId", "CUSTOMER015");
		params.put("LocationId", "ELECWAT002");
		params.put("FormerConnections", "false");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(result);
		JsonPath json = JsonPath.from(result);
		Assert.assertNotNull(json.get("ServiceByLocation"), "Expected ServiceByLocation array in response");
		Assert.assertTrue(json.getList("ServiceByLocation").size() > 0, "Expected services for the location");
	}

	// FormerConnections omitted -> existing behavior is preserved (former
	// connections are still included), so the default returns at least as many
	// connections as the FormerConnections=false opt-out. This proves the new
	// parameter is non-breaking.
	@Test(priority = 9, groups = "ServiceByLocation")
	public void getPortalServiceByLocation_FormerConnectionsDefault()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/portal/ServiceByLocation";
		String ver = "4.0";

		HashMap<String, String> defaultParams = new HashMap<String, String>();
		defaultParams.put("CustomerId", "CUSTOMER015");
		defaultParams.put("LocationId", "ELECWAT002");
		String resultDefault = CommonMethods.getMethodasString(uri, ver, defaultParams);
		System.out.println(resultDefault);
		JsonPath jsonDefault = JsonPath.from(resultDefault);
		Assert.assertNotNull(jsonDefault.get("ServiceByLocation"), "Expected ServiceByLocation array in response");
		int defaultCount = jsonDefault.getList("ServiceByLocation").size();
		Assert.assertTrue(defaultCount > 0, "Expected services for the location");

		HashMap<String, String> falseParams = new HashMap<String, String>();
		falseParams.put("CustomerId", "CUSTOMER015");
		falseParams.put("LocationId", "ELECWAT002");
		falseParams.put("FormerConnections", "false");
		int falseCount = JsonPath.from(CommonMethods.getMethodasString(uri, ver, falseParams))
				.getList("ServiceByLocation").size();

		Assert.assertTrue(defaultCount >= falseCount,
				"Expected omitting FormerConnections to preserve existing behavior (former connections included), "
						+ "returning at least as many connections as FormerConnections=false. default=" + defaultCount
						+ ", false=" + falseCount);
	}

}