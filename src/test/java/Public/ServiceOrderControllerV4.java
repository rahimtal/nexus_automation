package Public;

import org.testng.annotations.Test;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;
import com.NexustAPIAutomation.java.Retry;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;

public class ServiceOrderControllerV4 {

	public static JsonPath jsonPathEvaluator;
	public static String ServiceOrderNumber;

	@Test(priority = 1, groups = "ServiceOrder", retryAnalyzer = Retry.class)
	public static void getServiceOrderdetails_v4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.Bug("CPDEV-17883");
		String uri = "/serviceOrder/detail";
		String ver = "4.0";
		String jpath = "./\\TestData\\getserviceOrderDetailsv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("ShowDrillBack", "true");
		params.put("ServiceOrderNumber", "SORD00000000043");

		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 2, groups = "ServiceOrder", retryAnalyzer = Retry.class)
	public void postCreateServiceOrderv4UseScheduleDateForSODetailtrue()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// JsonPath jsonPathEvaluator;
		String uri = "/serviceOrder";
		String version = "4.0";
		String payload = "{\r\n" + "    \"ServiceOrder\": [\r\n" + "        {\r\n"
				+ "            \"LocationId\": \"ELECWAT001\",\r\n" + "            \"CustomerId\": \"CUSTOMER007\",\r\n"
				+ "            \"MoveInCustomerId\": \"CUSTOMER006\",\r\n"
				+ "            \"RequestId\": \"REQ-INSTALL-E\",\r\n" + "            \"Description\": \"\",\r\n"
				+ "            \"RequestedDateTime\": \"2019-04-08T10:45:00\",\r\n"
				+ "            \"ScheduledDateTime\": \"2019-10-08T10:45:00\",\r\n"
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
				+ "            \"ShowDrillBack\": 0,\r\n" + "            \"UseScheduleDateForSODetail\": false\r\n"
				+ "        }\r\n" + "    ]\r\n" + "}";
		String exResponse = "{\"ServiceOrder\":[{\"DocumentNumber\":\"SORD";
		String response = CommonMethods.postMethodStringPayloadString(payload, uri, version);
		Assert.assertTrue(response.contains(exResponse));

	}
	
	@Test(priority = 3, groups = "ServiceOrder", retryAnalyzer = Retry.class)
	public void postCreateServiceOrderv4UseScheduleDateForSODetailfalse()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// JsonPath jsonPathEvaluator;
		String uri = "/serviceOrder";
		String version = "4.0";
		String payload = "{\r\n" + "    \"ServiceOrder\": [\r\n" + "        {\r\n"
				+ "            \"LocationId\": \"ELECWAT001\",\r\n" + "            \"CustomerId\": \"CUSTOMER007\",\r\n"
				+ "            \"MoveInCustomerId\": \"CUSTOMER006\",\r\n"
				+ "            \"RequestId\": \"REQ-INSTALL-E\",\r\n" + "            \"Description\": \"\",\r\n"
				+ "            \"RequestedDateTime\": \"2019-04-08T10:45:00\",\r\n"
				+ "            \"ScheduledDateTime\": \"2019-10-08T10:45:00\",\r\n"
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
				+ "            \"ShowDrillBack\": 0,\r\n" + "            \"UseScheduleDateForSODetail\": true\r\n"
				+ "        }\r\n" + "    ]\r\n" + "}";
		String exResponse = "{\"ServiceOrder\":[{\"DocumentNumber\":\"SORD";
		String response = CommonMethods.postMethodStringPayloadString(payload, uri, version);
		Assert.assertTrue(response.contains(exResponse));

	}

}
