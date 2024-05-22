package Public;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;

public class DisconnectFromStarttoFinish {

	public static JsonPath jsonPathEvaluator;
	public static String ServiceOrderNumber;

	@Test(priority = 1, groups = "DisconnectFromStarttoFinish")
	public static void PostServiceOrder()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/serviceOrder";
		String ver = "4.0";
		String payload = "./\\TestData\\createDcserviceOrderv4.json";
		String exResponse = "{\"ServiceOrder\":[{\"DocumentNumber\":\"SORD00000008996\",\"DrillbackLink\":\"\",\"Success\":true,\"Messages\":[{\"Enabled\":1,\"Info\":\"UDF Label 'SORequestUDF' not valid for Service Order SORD00000008989.\",\"Level\":1},{\"Enabled\":1,\"Info\":\"UDF Label 'SOReqUDF-2' not valid for Service Order SORD00000008989.\",\"Level\":1}]}]}";
		CommonMethods.postcall(uri, payload, ver, exResponse);

	}

	@Test(priority = 2, groups = "DisconnectFromStarttoFinish")
	public static void PostMeterReading()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/meterReading";
		String ver = "4.0";
		String payload = "./\\TestData\\PostmeterReadingV4.json";
		String exResponse = "{\"MeterReading\":[{\"DocumentNumber\":\"READ00000000922\",\"MiscChargeDocumentNumber\":\"\",\"PostingReport\":false,\"PostingError\":false,\"ReportList\":[],\"ReportErrorList\":[],\"Success\":true,\"Messages\":[{\"Enabled\":1,\"Info\":\"Meter Reading successfully Saved!\",\"Level\":1}]}]}";
		CommonMethods.postcall(uri, payload, ver, exResponse);

	}

	@Test(priority = 3, groups = "DisconnectFromStarttoFinish")
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
				+ "        \"ServiceOrder\":{\n" + "            \"Id\": \"SORD00000008989\",\n"
				+ "            \"Task\":{\n" + "                \"Sequence\": 1100\n" + "            }\n"
				+ "        }\n" + "	}\n" + "}";

		CommonMethods.putMethod(uri, ver, payload, exResponse);

	}

}
