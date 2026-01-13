package com.NexusAPI.Tests;

import org.testng.annotations.Test; import org.testng.Assert;
import org.testng.Assert;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.hamcrest.Matchers;
import org.testng.Assert;

import org.testng.annotations.Test; import org.testng.Assert;

import com.NexustAPIAutomation.java.CommonMethods;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class Public_Test_ServiceOrderControllerV3 {

	public static JsonPath jsonPathEvaluator;
	public static String ServiceOrderNumber;

	@Test(priority = 1, groups = "ServiceOrder")
	public static void postcreateServiceOrder_v3()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.Bugs("CPDEV-20960");
		String uri = "/serviceOrder";
		String ver = "3.0";
		String payload = "{\r\n" + "    \"ServiceOrder\": [\r\n" + "        {\r\n"
				+ "            \"LocationId\": \"ELECWAT001\",\r\n" + "            \"CustomerId\": \"CUSTOMER007\",\r\n"
				+ "            \"RequestId\": \"REQ-INSTALL-E\",\r\n" + "            \"Description\": \"\",\r\n"
				+ "            \"RequestedDateTime\": \"2021-04-08T11:45:00\",\r\n"
				+ "            \"ScheduledDateTime\": \"2021-04-08T11:45:00\",\r\n"
				+ "            \"EquipmentId\": \"WATEREQUIP006\",\r\n" + "            \"CommentLine\": [\r\n"
				+ "                {\r\n" + "                    \"Id\": 2,\r\n"
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
				+ "            \"ShowDrillBack\": 0\r\n" + "        }\r\n" + "    ]\r\n" + "}";

		// String expected
		// ="{\"DocumentNumber\":\"SORD00000009045\",\"DrillbackLink\":\"\",\"ServiceOrder\":{\"Success\":true,\"Messages\":[{\"Enabled\":1,\"Info\":\"UDF
		// Label 'SORequestUDF' not valid for Service Order
		// SORD00000009045.\",\"Level\":1},{\"Enabled\":1,\"Info\":\"UDF Label
		// 'SOReqUDF-2' not valid for Service Order
		// SORD00000009045.\",\"Level\":1},{\"Enabled\":1,\"Info\":\"SORD00000009045
		// created\",\"Level\":1}]}}";
		// CommonMethods.postMethodString(payload, uri, ver, expected);
		String result = CommonMethods.postMethodResponseAsString(payload, uri, ver);
		String exp1 = "{\"DocumentNumber\":\"SORD000000";
		String exp2 = ",\"DrillbackLink\":\"\",\"ServiceOrder\":{\"Success\":true,\"Messages\":[{\"Enabled\":1,\"Info\":\"UDF Label 'SORequestUDF' not valid for Service Order";
		String exp3 = ".\",\"Level\":1},{\"Enabled\":1,\"Info\":\"UDF Label 'SOReqUDF-2' not valid for Service Order SORD00000";
		String exp4 = ".\",\"Level\":1},{\"Enabled\":1,\"Info\":\"SORD0000";
		String exp5 = "created\",\"Level\":1}]}}";
		Assert.assertTrue(result.contains(exp1));
		Assert.assertTrue(result.contains(exp2));
		Assert.assertTrue(result.contains(exp3));
		Assert.assertTrue(result.contains(exp4));
		Assert.assertTrue(result.contains(exp5));

	}

	public static void getServiceOrderdetails_v3(String param)
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		System.out.println(param);
		String uri = "/serviceOrder/detail";
		String ver = "3.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("ServiceOrderNumber", param);
		params.put("ShowDrillBack", "true");
		Response response = CommonMethods.getMethod(uri, ver, params);

		// To check for sub string presence get the Response body as a String.
		// Do a String.contains
		String bodyAsString = response.asString();
		System.out.println(bodyAsString);

		if (!bodyAsString.contains(param)) {
			Assert.fail();
		}
		System.out.println(response.prettyPrint());

	}

	@Test(priority = 2, groups = "ServiceOrder", dependsOnMethods = "postcreateServiceOrder_v3")
	public static void getServiceOrder_v3()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		// rue&ShowStatus_FieldComplete=true&OrderBy=ScheduledDateTime ASC,
		// ServiceOrderId DESC
		String uri = "/serviceOrder/getServiceOrders";
		String ver = "3.0";
		String jpath = "./\\TestData\\getserviceordersv3.json";

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "WATER002");
		params.put("ShowStatus_Preparing", "true");
		params.put("ShowStatus_Pending", "true");
		params.put("ShowStatus_Preparing", "true");
		params.put("ShowStatus_InProgress", "true");
		params.put("ShowStatus_OnHold", "true");
		params.put("ShowStatus_Delayed", "true");
		params.put("Showtatus_Billed", "true");
		params.put("ShowStatus_Canceled", "true");
		params.put("ShowStatus_Completed", "true");
		params.put("ShowStatus_WebGenerated", "true");
		params.put("ShowStatus_FieldInProgress", "true");
		params.put("ScheduledDateTime", "ScheduledDateTime ASC, ServiceOrderId DESC");
		params.put("ShowStatus_FieldComplete", "true");

		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);

	}

	@Test(priority = 3, groups = "ServiceOrder")
	public static void getServiceOrderRequestDetails_v3()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		// ?RequestID=transfer&ShowOnlyTransfers=true
		String uri = "/serviceOrderRequestIdDetail/getServiceOrderRequestIdDetail";
		String ver = "3.0";
		String jpath = "./\\TestData\\serviceorderrequestdetailsv2.json";

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("RequestID", "transfer");
		params.put("ShowOnlyTransfers", "true");

		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);

	}

	@Test(priority = 4, groups = "ServiceOrder")
	public static void getServiceOrderRequestDetailsbyOptional_v3()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		//CommonMethods.Bugs("DEV-20975");
		// ?RequestID=transfer&ShowOnlyTransfers=true
		String uri = "/serviceOrder/detail";
		String ver = "3.0";
		String jpath = "./\\TestData\\serviceorderrequestdetailsOptionalv3.json";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("ServiceOrderNumber", "SORD00000000002");
		params.put("ShowDrillBack", "true");
		params.put("IncludeHistory", "true");
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);

	}

	@Test(priority = 5, groups = "ServiceOrder")
	public static void putaddMeterReading_v_3()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/serviceOrder/addMeterReading";
		String ver = "3.0";
		String jpath = "./\\TestData\\addMeterReading_v3.json";
		// String fresponse = "./\\TestData\\addMeterReadingresp_v2.json";
		// ValidatableResponse result = CommonMethods.putMethodvalidate(uri, ver,
		// jpath,fresponse);
		ValidatableResponse result = CommonMethods.putMethod(uri, ver, jpath);
		result.assertThat().body(Matchers.containsString("true"));
		result.assertThat().body(Matchers.containsString("READ"));
		result.assertThat().body(Matchers.containsString("Created"));
		// System.out.println(result.extract().asString());
		System.out.println(result.toString());

	}

	@Test(priority = 6, groups = "ServiceOrder")
	public static void putupdatecomment_v_3()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/serviceOrder/updateSOComment";
		String ver = "3.0";
		String jpath = "./\\TestData\\putupdatecommentsv2.json";

		ValidatableResponse result = CommonMethods.putMethod(uri, ver, jpath);
		result.assertThat().body(Matchers.containsString("true"));
		result.assertThat().body(Matchers.containsString("Comment updated"));
		System.out.println(result.extract().asString());

	}

	@Test(priority = 7, groups = "ServiceOrder")
	public static void putaddMiscCharge_v_3()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/serviceOrder/addMiscCharge";
		String ver = "3.0";
		String jpath = "./\\TestData\\putaddmiscChargev2.json";

		ValidatableResponse result = CommonMethods.putMethod(uri, ver, jpath);
		result.assertThat().body(Matchers.containsString("Batch posted. Misc charge created"));
		result.assertThat().body(Matchers.containsString("MISC"));
		result.assertThat().body(Matchers.containsString("true"));

		System.out.println(result.extract().asString());
	}

	@Test(priority = 8, groups = "ServiceOrder")
	public static void putswitchMeter_v_3()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/serviceOrder/switchMeter";
		String ver = "3.0";
		String jpath = "./\\TestData\\meterchangev2.json";
		ValidatableResponse result = CommonMethods.putMethod(uri, ver, jpath);
		result.assertThat().body(Matchers.containsString("Meter updated"));
		result.assertThat().body(Matchers.containsString("true"));
		System.out.println(result.extract().asString());
	}

//DEPRECATED use "Complete service order Task"
//	@Test(priority = 8, groups = "ServiceOrder" )
	public static void putTaskComplete_v_3()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/serviceOrder/putTaskComplete";
		String ver = "3.0";
		String jpath = "./\\TestData\\putaskcompletev3.json";
		ValidatableResponse result = CommonMethods.putMethod(uri, ver, jpath);
		result.assertThat().body(Matchers.containsString("Task updated"));
		result.assertThat().body(Matchers.containsString("true"));

		System.out.println(result.extract().asString());
	}

	@Test(priority = 9, groups = "ServiceOrder")
	public static void putTaskCompleteNocharge_v3()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/serviceOrder/taskCompleteOtherNoCharge";
		String ver = "3.0";
		String jpath = "./\\TestData\\putaskcompletenochargev2_3.json";

		ValidatableResponse result = CommonMethods.putMethod(uri, ver, jpath);
		result.assertThat().body(Matchers.containsString(
				"Service Order SORD00000000044 task TASK003 for sequence 1000 has already been completed."));
		result.assertThat().body(Matchers.containsString("false"));
		System.out.println(result.extract().asString());
	}

	public static void main(String args[])
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// putaddMeterReading_v_3();

		postcreateServiceOrder_v3();
		// putTaskComplete_v_3_4();
	}

}
