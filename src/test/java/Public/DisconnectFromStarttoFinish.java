package Public;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;
import com.NexustAPIAutomation.java.Retry;

import io.restassured.path.json.JsonPath;

public class DisconnectFromStarttoFinish {

	public static JsonPath jsonPathEvaluator;
	public static String ServiceOrderNumber;

	@Test(priority = 1, groups = "DisconnectFromStarttoFinish", retryAnalyzer = Retry.class)
	public static void PostServiceOrder()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/serviceOrder";
		String ver = "4.0";
		String payload = "./\\TestData\\createDcserviceOrderv4.json";
		String exResponse = "{\"ServiceOrder\":[{\"DocumentNumber\":\"SORD000000";
		CommonMethods.postcallcontains(uri, payload, ver, exResponse);

	}

	@Test(priority = 2, groups = "DisconnectFromStarttoFinish", retryAnalyzer = Retry.class)
	public static void PostMeterReading()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/meterReading";
		String ver = "4.0";
		String payload = "./\\TestData\\PostmeterReadingV4.json";
		String exResponse = "{\"MeterReading\":[{\"DocumentNumber\":\"READ0000000";
		CommonMethods.postcallcontains(uri, payload, ver, exResponse);

	}

	@Test(priority = 3, groups = "DisconnectFromStarttoFinish", retryAnalyzer = Retry.class)
	public static void putMeterDisconnect()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/connection/meter";
		String ver = "4.0";
		String exResponse = "./\\TestData\\PutmeterDisconnectV4.json";
		String payload = "{\n" + "	\"Connection\": {\n" + "        \"LocationId\": \"WATER003\",\n"
				+ "		\"ConnectionSequence\": 1,\n" + "		\"EquipmentId\": \"WATEREQUIP001\",\n"
				+ "        \"RouteId\": \"ROUTEW001\",\n" + "        \"DiscountRate\": \"\",\n"
				+ "        \"SequenceNumber\": 0,\n" + "        \"ConnectionDate\": \"2023-01-02\",\n"
				+ "        \"DisconnectionDate\": \"2024-01-01\",\n" + "        \"InstallationDate\": \"2023-01-01\",\n"
				+ "        \"Status\": 5,\n" + "        \"TaxDiscountPercent\": 1.00000,\n"
				+ "        \"Multiplier\": {\n" + "            \"Rate\": 0,\n" + "            \"Fixed\": 0,\n"
				+ "            \"Loss\": 0,\n" + "            \"Consumption\": 1,\n"
				+ "            \"RangeAndMinimum\": 1\n" + "        },\n" + "       \"Rate\": [\n" + "            {\n"
				+ "                \"Consumption\": \"WATERMETERED\",\n" + "                \"KVA\": \"\",\n"
				+ "                \"KW\": \"\",\n" + "                \"NetMeterReceived\": \"\",\n"
				+ "                \"PeriodIndex\": 1\n" + "            }         \n" + "        ],\n"
				+ "        \"Confirm\": {\n" + "            \"IgnoreDisconnectMeterReadValidation\": true,\n"
				+ "            \"IgnoreDisconnectToActiveValidation\": false,\n"
				+ "            \"IgnoreEquipmentReinstallValidation\": false\n" + "        },\n"
				+ "        \"ServiceOrder\":{\n" + "            \"Id\": \"SORD00000009002\",\n"
				+ "            \"Task\":{\n" + "                \"Sequence\": 1000\n" + "            }\n"
				+ "        }\n" + "	}\n" + "}";

		CommonMethods.putMethod(uri, ver, payload, exResponse);

	}

}
