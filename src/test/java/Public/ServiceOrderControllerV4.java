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
		String expected = "{\"ServiceOrderDetail\":{\"Success\":true,\"Data\":[{\"ServiceOrder\":{\"Id\":\"SORD00000000043\",\"Description\":\"\",\"LocationId\":\"LOCATION002\",\"RequestedDateTime\":\"2000-03-10T15:58:27\",\"ScheduledDateTime\":\"2000-04-17T00:00:00\",\"CompletedDateTime\":\"1900-01-01T00:00:00\",\"RescheduledDateTime\":\"1900-01-01T00:00:00\",\"EquipmentId\":\"\",\"Status\":{\"Id\":2,\"Description\":\"Pending\"},\"CancelReasonCode\":\"\",\"Origin\":{\"Id\":1,\"Description\":\"Internal\"}},\"Customer\":{\"Id\":\"MASTER001\",\"Name\":\"Mr. Arthur K Dunning TR\",\"Type\":\"Individual\",\"Status\":\"Current\"},\"Request\":{\"Id\":\"REQ-SWITCH-E\",\"Description\":\"Meter switch - electric\"},\"RequestedBy\":{\"Id\":\"MASTER001\",\"Name\":\"Mr. Arthur K Dunning TR\",\"Type\":1},\"MoveInCustomer\":{\"Id\":\"\",\"Name\":\"\",\"Type\":\"\",\"Status\":\"\"},\"CommentLine\":[{\"Id\":1,\"Description\":\"\"},{\"Id\":2,\"Description\":\"\"},{\"Id\":3,\"Description\":\"\"},{\"Id\":4,\"Description\":\"\"},{\"Id\":5,\"Description\":\"\"},{\"Id\":6,\"Description\":\"\"}],\"Task\":[{\"Id\":\"TASK015\",\"Description\":\"Meter switch-electric\",\"Sequence\":1100,\"Print\":1,\"Ordered\":0,\"Completed\":0,\"ScheduledDateTime\":\"1900-01-01T00:00:00\",\"StartedDateTime\":\"1900-01-01T00:00:00\",\"CompletedDateTime\":\"1900-01-01T00:00:00\",\"DispatchDateTime\":\"1900-01-01T00:00:00\",\"Employee\":null,\"EquipmentId\":\"\",\"Action\":{\"Id\":7,\"Description\":\"Meter Switch\",\"ConnectionStatus\":{\"Id\":0,\"Description\":\"\"},\"CreditType\":{\"Id\":0,\"Description\":\"\"},\"ChargeType\":{\"Id\":\"\",\"Description\":\"\"}},\"ChargeAmount\":0,\"CrossReference\":\"\",\"DocumentNumber\":\"\",\"EmployeeRequired\":false,\"DepositId\":\"\",\"ServiceCategory\":{\"Id\":1,\"Description\":\"Electric\"},\"ServiceType\":{\"Id\":\"\",\"Description\":\"\"}}],\"Udf\":[],\"ServiceOrderDrillbackLink\":\"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=ServiceOrder&LocationID=LOCATION002&ServiceOrderNumber=SORD00000000043&CogsDrillback=1\",\"MoveInDrillbackLink\":null}],\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("ShowDrillBack", "true");
		params.put("ServiceOrderNumber", "SORD00000000043");

		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result, expected);

	}

	// If set to true, the service order tasks schedule date will default to
	// schedule date else service order tasks schedule date will be set to requested
	// date.
	@Test(priority = 2, groups = "ServiceOrder", retryAnalyzer = Retry.class)
	public void postCreateServiceOrderv4UseScheduleDateForSODetailfalse()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// JsonPath jsonPathEvaluator;
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
		String exResponse = "{\"ServiceOrder\":[{\"DocumentNumber\":\"SORD";
		String response = CommonMethods.postMethodStringPayloadString(payload, uri, version);
		Assert.assertTrue(response.contains(exResponse));

	}

	@Test(priority = 3, groups = "ServiceOrder", retryAnalyzer = Retry.class)
	public void postCreateServiceOrderv4UseScheduleDateForSODetailtrue()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// JsonPath jsonPathEvaluator;
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
		String exResponse = "{\"ServiceOrder\":[{\"DocumentNumber\":\"SORD";
		String response = CommonMethods.postMethodStringPayloadString(payload, uri, version);
		Assert.assertTrue(response.contains(exResponse));

	}

}
