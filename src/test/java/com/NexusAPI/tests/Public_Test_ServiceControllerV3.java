package com.NexusAPI.Tests;

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

public class Public_Test_ServiceControllerV3 {

	public static JsonPath jsonPathEvaluator;
	public static String ServiceOrderNumber;

	//@Test(priority = 9, groups = "connectionController", dependsOnMethods = "deleteconnectionmetergrpv4")
	public void postServicemeterinstallv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// JsonPath jsonPathEvaluator;
		String uri = "/service/meterinstall";
		String version = "4.0";
		String payload = "{\r\n" + "    \"Connection\": {\r\n" + "        \"LocationId\": \"LOCATION008\",\r\n"
				+ "        \"FixedChargeId\": 1,\r\n" + "        \"MinimumCalculationId\": 3,\r\n"
				+ "        \"DemandHandlingId\": 1,\r\n" + "        \"Equipment\": [\r\n" + "            {\r\n"
				+ "                \"Id\": \"EQUIPMENT008\",\r\n" + "                \"ConnectionSequence\": 2\r\n"
				+ "            },\r\n" + "            {\r\n" + "                \"Id\": \"EQUIPMENT006\",\r\n"
				+ "                \"ConnectionSequence\": 3 \r\n" + "            }\r\n" + "        ]\r\n" + "    }\r\n"
				+ "}";
		String exResponse = "{\"Connection\":{\"Success\":true,\"Data\":{\"MeterGroupId\":\"MTGR00000000001\"},\"Messages\":[{\"Enabled\":1,\"Info\":\"Created\",\"Level\":1}]}}";
		CommonMethods.postMethodString(payload, uri, version, exResponse);

	}

}
