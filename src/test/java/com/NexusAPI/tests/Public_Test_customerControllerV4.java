package com.NexusAPI.Tests;

import org.testng.annotations.Test; import org.testng.Assert;

import org.testng.annotations.Test; import org.testng.Assert;


import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test; import org.testng.Assert;

import com.NexustAPIAutomation.java.CommonMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

public class Public_Test_customerControllerV4  extends BaseClass{

	@Test(priority = 1, groups = "CustomerController" )
	public static void getlocationsByCustomerv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		CommonMethods.Bug("22857");
		String uri = "/customers/CUSTOMER014/locationsByCustomer";
		String ver = "4.0";
		String expected = "{\"LocationsByCustomer\":[{\"PageNumber\":1,\"TotalPages\":1,\"Customer\":[{\"Id\":\"CUSTOMER014\",\"Type\":\"Primary\",\"Name\":\"Test Name\",\"Title\":\"\",\"FirstName\":\"Test\",\"MiddleName\":\"\",\"LastName\":\"Name\"}],\"Location\":[{\"Id\":\"WATER002\",\"Description\":\"\",\"STATUS\":\"Current\",\"Class\":\"\",\"ServiceAddress\":[{\"Line1\":\"100 Water\",\"DetailLine1\":\"100 Water\",\"DetailLine2\":\"\",\"City\":\"NEW YORK\",\"State\":\"NY\",\"ZipCode\":\"32541\",\"Country\":\"USA\",\"Id\":24}],\"PrimaryCustomerId\":\"CUSTOMER014\",\"FinanciallyResponsible\":true,\"AccountBalance\":0,\"MasterIncluded\":\"1\",\"MoveInDate\":\"\",\"MoveOutDate\":\"\",\"ZoneId\":\"3\",\"RouteId\":\"ROUTEW001\",\"CycleId\":\"BIMONTHLY\",\"Udl\":[{\"Label\":\"Location001\",\"value\":\"\"},{\"Label\":\"Location002\",\"value\":\"\"}],\"RelatedCustomers\":[{\"Label\":\"Owner ID                      \",\"CustomerId\":\"CUSTOMER005\",\"CustomerName\":\"Mr. Stewart D Brian\"},{\"Label\":\"Tenant                        \",\"CustomerId\":\"\",\"CustomerName\":\"\"},{\"Label\":\"Landlord                      \",\"CustomerId\":\"\",\"CustomerName\":\"\"}]}]}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "WATER002");
		params.put("NumPerPage", "50");
		params.put("OrderBy", "status, locationId");
		params.put("IncludeBalance", "false");
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(actual, expected);

	}

	// -----------------------------------------------------------------------
	// Performance improvement (stored procedures): new optional query parameter
	// IncludeDrillback on customerController.getCustomerBillingOptions
	// (/customers/{CustomerId}/billingoptions). Defaults to existing behavior,
	// so the parameter must be non-breaking.
	// -----------------------------------------------------------------------

	// IncludeDrillback=true -> drillback links are generated for the billing
	// options (response contains a cogsDrillback link).
	@Test(priority = 10, groups = "CustomerController")
	public void getCustomerBillingOptionsIncludeDrillbackTruev4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/customers/500001/billingoptions";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("IncludeDrillback", "true");

		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertTrue(actual.contains("\"Success\":true"));
		Assert.assertTrue(actual.contains("cogsDrillback"),
				"Expected drillback links to be generated when IncludeDrillback=true. Actual: " + actual);
	}

	// IncludeDrillback=false (default behavior) -> drillback links are NOT
	// generated (backward compatible), so the Drillback fields are empty.
	@Test(priority = 11, groups = "CustomerController")
	public void getCustomerBillingOptionsIncludeDrillbackFalsev4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/customers/500001/billingoptions";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("IncludeDrillback", "false");

		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertTrue(actual.contains("\"Success\":true"));
		Assert.assertFalse(actual.contains("cogsDrillback"),
				"Expected no drillback links when IncludeDrillback=false. Actual: " + actual);
	}

}