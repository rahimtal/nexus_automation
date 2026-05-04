package com.NexusAPI.Tests;

import org.testng.annotations.Test; import org.testng.Assert;

import org.testng.annotations.Test; import org.testng.Assert;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test; import org.testng.Assert;

import com.NexustAPIAutomation.java.CommonMethods;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;

public class Public_Test_ServiceOrderControllerV4 {

	public static JsonPath jsonPathEvaluator;
	public static String ServiceOrderNumber;

	@Test(priority = 1, groups = "ServiceOrder")
	public static void getServiceOrderdetails_v4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		//CommonMethods.Bugs("https://cogsdale.atlassian.net/browse/CPDEV-20975");
		String uri = "/serviceOrder/detail";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("ShowDrillBack", "true");
		params.put("ServiceOrderNumber", "SORD00000000043");

		String result = CommonMethods.getMethodasString(uri, ver, params);
		
		// Validate response structure instead of exact match to avoid hostname differences
		Assert.assertTrue(result.contains("\"ServiceOrderDetail\""), "Response should contain ServiceOrderDetail");
		Assert.assertTrue(result.contains("\"Success\":true"), "Response should indicate success");
		Assert.assertTrue(result.contains("\"SORD00000000043\""), "Response should contain correct Service Order ID");
		Assert.assertTrue(result.contains("\"LOCATION002\""), "Response should contain correct Location");
		Assert.assertTrue(result.contains("\"MASTER001\""), "Response should contain correct Customer");
		Assert.assertTrue(result.contains("cogsDrillback://DGPB"), "Response should contain drillback link");

	}

	// If set to true, the service order tasks schedule date will default to
	// schedule date else service order tasks schedule date will be set to requested
	// date.
	@Test(priority = 2, groups = "ServiceOrder")
	public void postCreateServiceOrderv4UseScheduleDateForSODetailfalse()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// JsonPath jsonPathEvaluator;
		//CommonMethods.Bugs("CPDEV-20960");
		String uri = "/serviceOrder";
		String version = "4.0";
		String payload = "{\r\n" + "    \"ServiceOrder\": [\r\n" + "        {\r\n"
				+ "            \"LocationId\": \"ELECWAT001\",\r\n" + "            \"CustomerId\": \"CUSTOMER007\",\r\n"
				+ "            \"MoveInCustomerId\": \"CUSTOMER006\",\r\n"
				+ "            \"RequestId\": \"REQ-INSTALL-E\",\r\n"
				+ "            \"Description\": \"UseScheduleDateForSODetail FALSE \",\r\n"
				+ "            \"RequestedDateTime\": \"2019-04-08T10:45:00\",\r\n"
				+ "            \"ScheduledDateTime\": \"\",\r\n" + "            \"EquipmentId\": \"WATEREQUIP006\",\r\n"
				+ "            \"CommentLine\": [\r\n" + "                {\r\n" + "                    \"Id\": 2,\r\n"
				+ "                    \"Description\": \"Line 2\"\r\n" + "                },\r\n"
				+ "                {\r\n" + "                    \"Id\": 4,\r\n"
				+ "                    \"Description\": \"Line 4\"\r\n" + "                },\r\n"
				+ "                {\r\n" + "                    \"Id\": 6,\r\n"
				+ "                    \"Description\": \"Line 6\"\r\n" + "                }\r\n" + "            ],\r\n"
				+ "            \"Udf\": [\r\n" + "                {\r\n"
				+ "                    \"Label\": \"SORequestUDF\",\r\n"
				+ "                    \"Value\": \"udfval1ue1\"\r\n" + "                },\r\n"
				+ "                {\r\n" + "                    \"Label\": \"SOReqUDF-2\",\r\n"
				+ "                    \"Value\": \"udfValue2\"\r\n" + "                }\r\n" + "            ],\r\n"
				+ "            \"ShowDrillBack\": 0,\r\n" + "            \"UseScheduleDateForSODetail\": false\r\n"
				+ "        }\r\n" + "    ]\r\n" + "}";
		String exResponse = ",\"DrillbackLink\":\"\",\"ServiceOrder\":{\"Success\":true,\"Messages\":[{\"Enabled\":1,\"Info\":\"UDF Label 'SORequestUDF' not valid for Service Order SORD0000000";
		String response = CommonMethods.postMethodStringPayloadString(payload, uri, version);
		Assert.assertTrue(response.contains(exResponse));

	}

	@Test(priority = 3, groups = "ServiceOrder")
	public void postCreateServiceOrderv4UseScheduleDateForSODetailtrue()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// JsonPath jsonPathEvaluator;

		//CommonMethods.Bugs("CPDEV-20960");
		String uri = "/serviceOrder";
		String version = "4.0";
		String payload = "{\r\n" + "    \"ServiceOrder\": [\r\n" + "        {\r\n"
				+ "            \"LocationId\": \"ELECWAT001\",\r\n" + "            \"CustomerId\": \"CUSTOMER007\",\r\n"
				+ "            \"MoveInCustomerId\": \"CUSTOMER006\",\r\n"
				+ "            \"RequestId\": \"REQ-INSTALL-E\",\r\n"
				+ "            \"Description\": \"UseScheduleDateForSODetail TRUE\",\r\n"
				+ "            \"RequestedDateTime\": \"2019-04-08T10:45:00\",\r\n"
				+ "            \"ScheduledDateTime\": \"\",\r\n" + "            \"EquipmentId\": \"WATEREQUIP006\",\r\n"
				+ "            \"CommentLine\": [\r\n" + "                {\r\n" + "                    \"Id\": 2,\r\n"
				+ "                    \"Description\": \"Line 2\"\r\n" + "                },\r\n"
				+ "                {\r\n" + "                    \"Id\": 4,\r\n"
				+ "                    \"Description\": \"Line 4\"\r\n" + "                },\r\n"
				+ "                {\r\n" + "                    \"Id\": 6,\r\n"
				+ "                    \"Description\": \"Line 6\"\r\n" + "                }\r\n" + "            ],\r\n"
				+ "            \"Udf\": [\r\n" + "                {\r\n"
				+ "                    \"Label\": \"SORequestUDF\",\r\n"
				+ "                    \"Value\": \"udfval1ue1\"\r\n" + "                },\r\n"
				+ "                {\r\n" + "                    \"Label\": \"SOReqUDF-2\",\r\n"
				+ "                    \"Value\": \"udfValue2\"\r\n" + "                }\r\n" + "            ],\r\n"
				+ "            \"ShowDrillBack\": 0,\r\n" + "            \"UseScheduleDateForSODetail\": true\r\n"
				+ "        }\r\n" + "    ]\r\n" + "}";
		String exResponse = ",\"DrillbackLink\":\"\",\"ServiceOrder\":{\"Success\":true,\"Messages\":[{\"Enabled\":1,\"Info\":\"UDF Label 'SORequestUDF' not valid for Service Order SORD000000";
		String response = CommonMethods.postMethodStringPayloadString(payload, uri, version);
		Assert.assertTrue(response.contains(exResponse));

	}

}
