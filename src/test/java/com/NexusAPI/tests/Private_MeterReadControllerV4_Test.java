package com.NexusAPI.Tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.Assert;

import org.testng.annotations.Test;
import org.testng.Assert;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.SkipException;

import com.NexustAPIAutomation.java.CommonMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class Private_MeterReadControllerV4_Test extends BaseClass {

	@Test(priority = 1, groups = "MeterRead", dependsOnMethods = "putMeterReadinginWorkV4")
	public void deletemeterReadingvalidv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/meterReading/READ00000000915";
		String ver = "4.0";
		String expected = "{\"MeterReading\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Meter Reading successfully Deleted!\",\"Level\":1}]}}";
		String result = CommonMethods.deleteMethodasString(uri, ver);
		Assert.assertEquals(expected, result);

	}

	@Test(priority = 2, groups = "MeterRead", dependsOnMethods = "deletemeterReadingvalidv4")
	public void deletemeterReadingErrorv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/meterReading/READ00000000915";
		String ver = "4.0";
		String expected = "{\"MeterReading\":{\"Success\":false,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Invalid document number (READ00000000915).\",\"Level\":3},{\"Enabled\":1,\"Info\":\"Cannot delete meter reading as the document (READ00000000915) is not in work\\/open.\",\"Level\":3}]}}";
		String result = CommonMethods.deleteMethodasString(uri, ver);

		if (!result.contains(expected)) {
			Assert.fail();
		}
		System.out.println(result);
		System.out.println(result);

	}

	@Test(priority = 3, groups = "MeterRead")
	public void getmeterReadingnextv4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/meterReading/next";
		String ver = "4.0";
		String expected = "{\"MeterReading\":{\"Success\":true,\"Data\":{\"PreviousDocumentNumber\":\"\",\"NextDocumentNumber\":\"READREAD000000";
		String expected2 = "\"},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();

		String result = CommonMethods.getMethodasString(uri, ver, params);
		if (!result.contains(expected) && !result.contains(expected2)) {
			Assert.fail();
		}
		System.out.println(result);
		System.out.println(result);

	}

	@Test(priority = 99, groups = "MeterRead")
	public static void PostMeterReadv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		// CommonMethods.Bugs("CPDEV-20946");
		String uri = "/meterReading";
		String ver = "4.0";

		String payload = "{\r\n" + "    \"MeterReading\": [\r\n" + "        {\r\n"
				+ "            \"LocationId\": \"Z100036\",\r\n" + "            \"Connection\": 1,\r\n"
				+ "            \"EquipmentId\": \"EE1\",\r\n" + "            \"RemoteId\": \"\",\r\n"
				+ "            \"BatchId\": \"NewBatch\",\r\n" + "            \"ReadDocumentLocation\": 2,\r\n"
				+ "            \"ServiceOrder\": \r\n" + "                {\r\n"
				+ "                    \"Id\": \"\",\r\n" + "                    \"Task\": {\r\n"
				+ "                            \"Sequence\": 0\r\n" + "                    }\r\n"
				+ "                },\r\n" + "            \"MeterReadInfo\": \r\n" + "                {\r\n"
				+ "                    \"EmployeeId\": \"BANK0001\",\r\n"
				+ "                    \"Description\": \"Meter Read from street\",\r\n"
				+ "                    \"ReadingType\": 1,\r\n"
				+ "                    \"ReadingDateTime\": \"2022-05-31T10:11:23\",\r\n"
				+ "                    \"ReasonCode\": \"\",\r\n" + "                    \"Periods\": \r\n"
				+ "                        [\r\n" + "                            {\r\n"
				+ "                                \"Index\": 1,\r\n"
				+ "                                \"ConsumptionOverride\": \"true\",\r\n"
				+ "                                \"Rollover\": 0,\r\n"
				+ "                                \"NetRollover\": 0,\r\n"
				+ "                                \"ConsumptionReading\": 3.3,\r\n"
				+ "                                \"Consumption\": 2.2,\r\n"
				+ "                                \"KW\": 0,\r\n" + "                                \"KVA\": 0,\r\n"
				+ "                                \"NetMeterReceived\": 0,\r\n"
				+ "                                \"NetMeterPreviousReceived\": 0,\r\n"
				+ "                                \"PowerFactor\": 0,\r\n"
				+ "                                \"LoadFactor\": 0\r\n" + "                            }\r\n"
				+ "                        ]\r\n" + "                }\r\n" + "            \r\n" + "        }\r\n"
				+ "    ]\r\n" + "}\r\n" + "";
		String filepath = "./\\TestData\\PostMeterReadv4.json";
		FileWriter file = new FileWriter(filepath);
		file.write(payload);
		file.close();
		JsonPath jsonPathEvaluator = CommonMethods.postMethod(filepath, uri, ver);
		Boolean Result = jsonPathEvaluator.get("MeterReading[0].Success");
		if (Result == true) {

			Assert.fail("Meter Reading posting should not be done ");

		} else {

			System.out.print(jsonPathEvaluator.prettyPrint());
		}
	}

	@Test(priority = 5, groups = "MeterRead")
	public void putMeterReadinginWorkV4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// Still a Bug (2025)
		// CommonMethods.Bug("CPDEV-21835");

		String uri = "/meterReading";
		String ver = "4.0";
		String params = "{\r\n" + //
				"    \"MeterReading\": {\r\n" + //
				"        \"DocumentNumber\": \"READ00000000913\",\r\n" + //
				"        \"BatchId\": \"NAPIMR~20230815\",\r\n" + //
				"        \"EmployeeId\": \"BANK0001\",\r\n" + //
				"        \"Description\": \"Meter Read from street\",\r\n" + //
				"        \"AdjustedDate\": \"2028-03-13T10:50:42\",\r\n" + //
				"        \"ReadingType\": 1,\r\n" + //
				"        \"ReadingDateTime\": \"2028-02-14T10:11:23\",\r\n" + //
				"        \"ReasonCode\": \"ELECTRICREAD\",\r\n" + //
				"        \"Periods\": [\r\n" + //
				"            {\r\n" + //
				"                \"Index\": 1,\r\n" + //
				"                \"ConsumptionOverride\": 1,\r\n" + //
				"                \"Rollover\": 0,\r\n" + //
				"                \"ConsumptionReading\": 0,\r\n" + //
				"                \"Consumption\": 0,\r\n" + //
				"                \"KW\": 0,\r\n" + //
				"                \"KVA\": 0,\r\n" + //
				"                \"NetRollover\": 0,\r\n" + //
				"                \"NetMeterReceived\": 8.000,\r\n" + //
				"                \"NetMeterPreviousReceived\": 0,\r\n" + //
				"                \"PowerFactor\": 0,\r\n" + //
				"                \"LoadFactor\": 0\r\n" + //
				"            },\r\n" + //
				"            {\r\n" + //
				"                \"Index\": 2,\r\n" + //
				"                \"ConsumptionOverride\": 0,\r\n" + //
				"                \"Rollover\": 0,\r\n" + //
				"                \"ConsumptionReading\": 80.00000,\r\n" + //
				"                \"Consumption\": 0,\r\n" + //
				"                \"KW\": 0,\r\n" + //
				"                \"KVA\": 0,\r\n" + //
				"                \"NetRollover\": 0,\r\n" + //
				"                \"NetMeterReceived\": 0,\r\n" + //
				"                \"NetMeterPreviousReceived\": 0,\r\n" + //
				"                \"PowerFactor\": 0,\r\n" + //
				"                \"LoadFactor\": 0\r\n" + //
				"            },\r\n" + //
				"            {\r\n" + //
				"                \"Index\": 3,\r\n" + //
				"                \"ConsumptionOverride\": 0,\r\n" + //
				"                \"Rollover\": 0,\r\n" + //
				"                \"ConsumptionReading\": 80,\r\n" + //
				"                \"Consumption\": 0,\r\n" + //
				"                \"KW\": 0,\r\n" + //
				"                \"KVA\": 0,\r\n" + //
				"                \"NetRollover\": 0,\r\n" + //
				"                \"NetMeterReceived\": 0,\r\n" + //
				"                \"NetMeterPreviousReceived\": 0,\r\n" + //
				"                \"PowerFactor\": 0,\r\n" + //
				"                \"LoadFactor\": 0\r\n" + //
				"            }\r\n" + //
				"        ]\r\n" + //
				"    }\r\n" + //
				"}";
		// String params = new String(Files.readAllBytes(Paths.get(jpath)));
		String expected = "{\"MeterReading\":{\"Success\":false,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Location has bill in work. No adjustment can be made until bill is posted or deleted.\",\"Level\":3}]}}";
		String result = CommonMethods.putMethodstring(uri, ver, params, expected);

	}

	@Test(priority = 6, groups = "MeterRead")
	public void putMeterReadingNetMeterV4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/meterReading";
		String ver = "4.0";
		String jpath = "./\\TestData\\putMeterReadingNetMeterV4.json";
		String params = new String(Files.readAllBytes(Paths.get(jpath)));
		String expected = "./\\TestData\\putMeterReadingexpectedNetMeter_v4.json";
		Response result = CommonMethods.putMethod(uri, ver, params, expected);

	}

	@Test(priority = 7, groups = "MeterRead")
	public void putMeterReadingnottheLatestreadingV4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/meterReading";
		String ver = "4.0";
		String jpath = "./\\TestData\\putMeterReadingnottheLatestreadingV4.json";
		String params = new String(Files.readAllBytes(Paths.get(jpath)));
		String expected = "./\\TestData\\putMeterReadingnottheLatestreadingexpectedNetMeter_v4.json";
		Response result = CommonMethods.putMethod(uri, ver, params, expected);

	}

	@Test(priority = 8, groups = "MeterRead")
	public void getmeterlastDocumentv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/meterReading/lastDocument/EQUIPMENT015";
		String ver = "4.0";
		String expected = "{\"MeterReading\":{\"Success\":true,\"Data\":{\"LastDocumentNumber\":\"";
		String expected2 = "\"},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("ConnectionSequence", "1");
		params.put("LocationId", "ELECWAT003");

		String result = CommonMethods.getMethodasString(uri, ver, params);
		if (!result.contains(expected) && !result.contains(expected2)) {
			Assert.fail("actual" + result);
		}

		System.out.println(result);

	}

	@Test(priority = 9, groups = "MeterRead")
	public static void postMeterReadPostInvalidv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/meterReading/post";
		String ver = "4.0";

		String payload = "{\r\n" + "    \"MeterReading\": \r\n" + "        {\r\n"
				+ "            \"DocumentNumber\": \"READ00000000418\",\r\n" + "            \"BatchId\": \"MIKEA\",\r\n"
				+ "            \"UserId\": \"sa\",\r\n" + "            \"MeterReadInfo\":[ \r\n"
				+ "                {\r\n" + "                    \"EmployeeId\": \"sa\",\r\n"
				+ "                    \"Description\": \"Test meter reading\",\r\n"
				+ "                    \"ReadingType\": 1,\r\n"
				+ "                    \"ReadingDateTime\": \"2027-04-12 23:56:25.000\",\r\n"
				+ "                    \"ReasonCode\": \"WATERREAD\" \r\n" + "                }\r\n"
				+ "            ]\r\n" + "        }\r\n" + "}";
		String filepath = "./\\TestData\\PostMeterReadPost_invalidv4.json";
		FileWriter file = new FileWriter(filepath);
		file.write(payload);
		file.close();
		JsonPath jsonPathEvaluator = CommonMethods.postMethod(filepath, uri, ver);
		Boolean Result = jsonPathEvaluator.get("MeterReading.Success");
		if (Result == true) {

			Assert.fail("Meter Reading posting should not be done.Meter Reading in open or history ");

		} else {

			System.out.print(jsonPathEvaluator.prettyPrint());
		}

		String info = jsonPathEvaluator.get("MeterReading.Messages[0].Info");

		if (!info.contentEquals("Meter Reading in open or history.  Unable to post Meter Reading.")) {

			Assert.fail("Meter Reading posting should not be done.Meter Reading in open or history ");

		} else {

			System.out.print(jsonPathEvaluator.prettyPrint());
		}
	}

	@Test(priority = 100, groups = "MeterRead", dependsOnMethods = "PostMeterReadv4")
	public static void postmoveOpenToHistoryv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/meterReading/moveOpenToHistory";
		String ver = "4.0";
		String payload = "./\\TestData\\PostmoveOpenToHistoryv4.json";
		// changes String exResponse
		// ="{\"MeterReading\":{\"Success\":true,\"Data\":{\"Data\":[{}]},\"Messages\":[{\"Enabled\":1,\"Info\":\"Meter
		// Reading successfully moved.\",\"Level\":1}]}}";
		String exResponse = "{\"MeterReading\":{\"Success\":true,\"Data\":{\"Data\":[{\"Document\":[{\"Number\":\"READ00000000704\"},{\"Number\":\"READ00000000705\"}]}]},\"Messages\":[{\"Enabled\":1,\"Info\":\"Meter Reading successfully moved.\",\"Level\":1}]}}";
		CommonMethods.postcall(uri, payload, ver, exResponse);
	}

	// =====================================================================
	// GET /api/v4/meterReading/adjustment/:DocumentNumber - Meter Reading
	// Adjustment validation (SP csmApi_spMeterReadAdjustmentGetValidation).
	// Validation-only: always HTTP 200, Data is always null and pass/fail is
	// carried by MeterReading.Success.
	// =====================================================================

	private static final String ADJUSTMENT_URI = "/meterReading/adjustment/";
	private static final String LOOKUP_URI = "/lookupMeterRead";
	private static final String READ_INQUIRY_URI = "/transaction/read/";
	private static final String VER = "4.0";

	private static final int LEVEL_WARNING = 2;
	private static final int LEVEL_ERROR = 3;

	/** Lower-cased fragments of the SP messages, matched case-insensitively. */
	private static final String MSG_INVALID_DOCUMENT = "invalid document number";
	private static final String MSG_NOT_OPEN = "is not in open status";
	private static final String MSG_NOT_LATEST = "not the latest reading";
	private static final String MSG_READ_IN_WORK = "meter readings in work";
	private static final String MSG_BILL_IN_WORK = "location has bill in work";
	private static final String MSG_SUBTRACT_METER = "subtract meter connected to master location";

	/** Cap on how many documents the shared scan validates, to keep runtime sane. */
	private static final int SCAN_LIMIT = 25;

	private static Boolean endpointDeployed;
	private static List<Map<String, String>> meterReadRows;
	private static Map<String, String> scanResults;

	@Test(priority = 20, groups = "MeterRead")
	public void getMeterReadAdjustment_OpenLatestRead_Success()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String document = firstAdjustableDocument();
		String actual = scan().get(document);
		assertAdjustmentEnvelope(actual, document);
		Assert.assertTrue(isAdjustmentSuccess(actual), "An adjustable open read should validate. Response: " + actual);
		Assert.assertNull(findMessage(actual, LEVEL_ERROR),
				"A successful validation must not carry a Level 3 message. Response: " + actual);
	}

	@Test(priority = 21, groups = "MeterRead")
	public void getMeterReadAdjustment_SuccessReturnsNoData()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String document = firstAdjustableDocument();
		String actual = scan().get(document);
		Assert.assertNull(new JsonPath(actual).get("MeterReading.Data"),
				"This is a validation-only endpoint - Data must stay null even on success. Response: " + actual);
	}

	@Test(priority = 22, groups = "MeterRead")
	public void getMeterReadAdjustment_FollowOnReadInquiryLoadsTheWindow()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// The UI calls GET /transaction/read/:DocumentNumber once validation passes.
		String document = firstAdjustableDocument();
		String actual = CommonMethods.getMethodasString(READ_INQUIRY_URI + document, VER,
				new HashMap<String, String>());
		JsonPath json = new JsonPath(actual);
		Assert.assertEquals(json.getBoolean("Read.Success"), Boolean.TRUE,
				"Read inquiry must succeed for a validated document. Response: " + actual);
		Assert.assertEquals(json.getString("Read.Data.DocumentNumber"), document,
				"Read inquiry must return the requested document. Response: " + actual);
		Assert.assertEquals(json.getString("Read.Data.Status"), "Open",
				"A validated document must still be Open. Response: " + actual);
		// DateAdjusted is defaulted by the API when null in the DB, so the window
		// always has a value to display.
		String dateAdjusted = json.getString("Read.Data.DateAdjusted");
		Assert.assertTrue(dateAdjusted != null && !dateAdjusted.isEmpty(),
				"The adjustment window needs a DateAdjusted value. Response: " + actual);
	}

	@Test(priority = 23, groups = "MeterRead")
	public void getMeterReadAdjustment_InvalidDocumentNumber_50385()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String document = "READNOTAREAD01";
		String actual = validateAdjustment(document);
		assertAdjustmentBlocked(actual, document, MSG_INVALID_DOCUMENT);
		Assert.assertTrue(actual.contains(document),
				"The message should name the rejected document. Response: " + actual);
	}

	@Test(priority = 24, groups = "MeterRead")
	public void getMeterReadAdjustment_ReadInWorkStatus_51080()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String document = firstDocumentWithStatus("Work");
		assertAdjustmentBlocked(validateAdjustment(document), document, MSG_NOT_OPEN);
	}

	@Test(priority = 25, groups = "MeterRead")
	public void getMeterReadAdjustment_ReadInHistoryStatus_51080()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// lookupMeterRead only lists Work/Open, so a history document is taken from the
		// restored TWO data set and its status re-confirmed before asserting.
		String document = "READ00000000002";
		String status = new JsonPath(CommonMethods.getMethodasString(READ_INQUIRY_URI + document, VER,
				new HashMap<String, String>())).getString("Read.Data.Status");
		if (!"History".equals(status)) {
			throw new SkipException(document + " is not a History read on this data set (Status=" + status + ")");
		}
		assertAdjustmentBlocked(validateAdjustment(document), document, MSG_NOT_OPEN);
	}

	@Test(priority = 26, groups = "MeterRead")
	public void getMeterReadAdjustment_NotTheLatestReading_51081()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String document = supersededOpenDocument();
		assertAdjustmentBlocked(validateAdjustment(document), document, MSG_NOT_LATEST);
	}

	@Test(priority = 27, groups = "MeterRead")
	public void getMeterReadAdjustment_LocationHasReadInWork_50639()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String document = latestOpenDocumentForEquipmentWithWorkRead();
		assertAdjustmentBlocked(validateAdjustment(document), document, MSG_READ_IN_WORK);
	}

	@Test(priority = 28, groups = "MeterRead")
	public void getMeterReadAdjustment_LocationHasBillInWork_50645()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		Map.Entry<String, String> hit = firstScanResultContaining(MSG_BILL_IN_WORK);
		if (hit == null) {
			throw new SkipException("No scanned open read is blocked by a bill in work (UM10100) on this data set");
		}
		assertAdjustmentBlocked(hit.getValue(), hit.getKey(), MSG_BILL_IN_WORK);
	}

	@Test(priority = 29, groups = "MeterRead")
	public void getMeterReadAdjustment_SubtractMeterWarningDoesNotBlock_51082()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		Map.Entry<String, String> hit = firstScanResultContaining(MSG_SUBTRACT_METER);
		if (hit == null) {
			throw new SkipException("No scanned open read has a subtract-meter connection (UMSC301) on this data set");
		}
		String actual = hit.getValue();
		Assert.assertNotNull(findMessage(actual, LEVEL_WARNING),
				"The subtract-meter message must be Level 2. Response: " + actual);
		Assert.assertEquals(isAdjustmentSuccess(actual), findMessage(actual, LEVEL_ERROR) == null,
				"A subtract-meter warning on its own must leave Success true. Response: " + actual);
	}

	@Test(priority = 30, groups = "MeterRead")
	public void getMeterReadAdjustment_SuccessIsFalseOnlyForLevel3()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		for (Map.Entry<String, String> entry : scan().entrySet()) {
			String actual = entry.getValue();
			Assert.assertEquals(isAdjustmentSuccess(actual), findMessage(actual, LEVEL_ERROR) == null,
					"Success must be false only when a Level 3 message is present. " + entry.getKey() + " -> "
							+ actual);
		}
	}

	@Test(priority = 31, groups = "MeterRead")
	public void getMeterReadAdjustment_AtMostOneBlockingMessage()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// Rules 1-4 short-circuit, so at most one Level 3 message can be raised.
		for (Map.Entry<String, String> entry : scan().entrySet()) {
			int errors = 0;
			for (Map<String, Object> message : messages(entry.getValue())) {
				if (level(message) == LEVEL_ERROR) {
					errors++;
				}
			}
			Assert.assertTrue(errors <= 1,
					"Expected at most one Level 3 message. " + entry.getKey() + " -> " + entry.getValue());
		}
	}

	@Test(priority = 32, groups = "MeterRead")
	public void getMeterReadAdjustment_DataIsAlwaysNull()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		for (Map.Entry<String, String> entry : scan().entrySet()) {
			Assert.assertNull(new JsonPath(entry.getValue()).get("MeterReading.Data"),
					"Data must always be null. " + entry.getKey() + " -> " + entry.getValue());
		}
	}

	@Test(priority = 33, groups = "MeterRead")
	public void getMeterReadAdjustment_FailureStillReturnsHttp200()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		requireAdjustmentEndpointDeployed();
		Response response = CommonMethods.getMethod(ADJUSTMENT_URI + "READNOTAREAD01", VER,
				new HashMap<String, String>());
		Assert.assertEquals(response.getStatusCode(), 200,
				"A failed validation must still return HTTP 200. Body: " + response.asString());
		Assert.assertFalse(isAdjustmentSuccess(response.asString()), "Body: " + response.asString());
	}

	@Test(priority = 34, groups = "MeterRead")
	public void getMeterReadAdjustment_DocumentNumberLongerThan15_Rejected()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		requireAdjustmentEndpointDeployed();
		String document = "READ000000000000"; // 16 characters
		String actual = CommonMethods.getMethodasString(ADJUSTMENT_URI + document, VER, new HashMap<String, String>());
		Assert.assertFalse(isAdjustmentSuccess(actual),
				"A DocumentNumber longer than 15 characters must be rejected. Response: " + actual);
	}

	@Test(priority = 35, groups = "MeterRead")
	public void getMeterReadAdjustment_MissingDocumentNumber_Rejected()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		requireAdjustmentEndpointDeployed();
		String actual = CommonMethods.getMethodasString("/meterReading/adjustment", VER, new HashMap<String, String>());
		Assert.assertFalse(isAdjustmentSuccess(actual),
				"A missing DocumentNumber must not validate successfully. Response: " + actual);
	}

	// ---------------------------------------------------------------------
	// Adjustment validation helpers - endpoint access
	// ---------------------------------------------------------------------

	private static void requireAdjustmentEndpointDeployed() throws IOException, InterruptedException {
		if (endpointDeployed == null) {
			String probe = CommonMethods.getMethodasString(ADJUSTMENT_URI + "READNOTAREAD01", VER,
					new HashMap<String, String>());
			endpointDeployed = Boolean.valueOf(probe != null && probe.contains("\"MeterReading\""));
		}
		if (!endpointDeployed.booleanValue()) {
			throw new SkipException("GET " + ADJUSTMENT_URI + ":DocumentNumber is not deployed on this environment - "
					+ "csmApi_spMeterReadAdjustmentGetValidation may be missing");
		}
	}

	private static String validateAdjustment(String documentNumber) throws IOException, InterruptedException {
		requireAdjustmentEndpointDeployed();
		return CommonMethods.getMethodasString(ADJUSTMENT_URI + documentNumber, VER, new HashMap<String, String>());
	}

	/** Validates the latest open read of each equipment, up to {@link #SCAN_LIMIT}. */
	private static Map<String, String> scan() throws IOException, InterruptedException {
		if (scanResults == null) {
			requireAdjustmentEndpointDeployed();
			Map<String, String> results = new LinkedHashMap<String, String>();
			for (Map<String, String> row : latestOpenReadPerEquipment().values()) {
				if (results.size() >= SCAN_LIMIT) {
					break;
				}
				String document = row.get("DocumentNumber");
				results.put(document, validateAdjustment(document));
			}
			Assert.assertFalse(results.isEmpty(), "lookupMeterRead returned no open reads to validate");
			scanResults = results;
		}
		return scanResults;
	}

	// ---------------------------------------------------------------------
	// Adjustment validation helpers - response parsing
	// ---------------------------------------------------------------------

	private static boolean isAdjustmentSuccess(String json) {
		return json != null && json.contains("\"Success\":true");
	}

	private static List<Map<String, Object>> messages(String json) {
		List<Map<String, Object>> found = new JsonPath(json).getList("MeterReading.Messages");
		return found == null ? new ArrayList<Map<String, Object>>() : found;
	}

	private static int level(Map<String, Object> message) {
		Object value = message.get("Level");
		return value == null ? 0 : Integer.parseInt(String.valueOf(value));
	}

	private static String info(Map<String, Object> message) {
		Object value = message.get("Info");
		return value == null ? "" : String.valueOf(value);
	}

	private static Map<String, Object> findMessage(String json, int expectedLevel) {
		for (Map<String, Object> message : messages(json)) {
			if (level(message) == expectedLevel) {
				return message;
			}
		}
		return null;
	}

	private static Map<String, Object> findMessage(String json, String lowerCaseFragment) {
		for (Map<String, Object> message : messages(json)) {
			if (info(message).toLowerCase().contains(lowerCaseFragment)) {
				return message;
			}
		}
		return null;
	}

	private static Map.Entry<String, String> firstScanResultContaining(String lowerCaseFragment)
			throws IOException, InterruptedException {
		for (Map.Entry<String, String> entry : scan().entrySet()) {
			if (findMessage(entry.getValue(), lowerCaseFragment) != null) {
				return entry;
			}
		}
		return null;
	}

	private static void assertAdjustmentEnvelope(String json, String document) {
		Assert.assertTrue(json != null && json.contains("\"MeterReading\""),
				"Expected the MeterReading wrapper for " + document + ". Response: " + json);
		Assert.assertNull(new JsonPath(json).get("MeterReading.Data"),
				"Data must be null for " + document + ". Response: " + json);
	}

	private static void assertAdjustmentBlocked(String json, String document, String lowerCaseFragment) {
		assertAdjustmentEnvelope(json, document);
		Assert.assertFalse(isAdjustmentSuccess(json), document + " should fail validation. Response: " + json);
		Map<String, Object> message = findMessage(json, lowerCaseFragment);
		Assert.assertNotNull(message, document + " should report '" + lowerCaseFragment + "'. Response: " + json);
		Assert.assertEquals(level(message), LEVEL_ERROR, "A blocking rule must be Level 3. Response: " + json);
	}

	// ---------------------------------------------------------------------
	// Adjustment validation helpers - test data from GET /lookupMeterRead
	// ---------------------------------------------------------------------

	private static List<Map<String, String>> allMeterReads() throws IOException, InterruptedException {
		if (meterReadRows == null) {
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("PageNum", "1");
			params.put("NumPerPage", "32000");
			String json = CommonMethods.getMethodasString(LOOKUP_URI, VER, params);
			List<Map<String, String>> rows = new JsonPath(json).getList("MeterReading");
			List<Map<String, String>> usable = new ArrayList<Map<String, String>>();
			if (rows != null) {
				for (Map<String, String> row : rows) {
					// The SP returns a single all-empty row when nothing matches.
					if (row.get("DocumentNumber") != null && !"".equals(row.get("DocumentNumber"))) {
						usable.add(row);
					}
				}
			}
			Assert.assertFalse(usable.isEmpty(), "lookupMeterRead returned no meter reads: " + json);
			meterReadRows = usable;
		}
		return meterReadRows;
	}

	private static Map<String, Map<String, String>> latestOpenReadPerEquipment()
			throws IOException, InterruptedException {
		Map<String, Map<String, String>> latest = new LinkedHashMap<String, Map<String, String>>();
		for (Map<String, String> row : allMeterReads()) {
			if (!"Open".equals(row.get("Status"))) {
				continue;
			}
			String equipmentId = row.get("EquipmentId");
			Map<String, String> current = latest.get(equipmentId);
			// ReadingDate is yyyy-MM-dd, so lexical comparison is chronological.
			if (current == null || row.get("ReadingDate").compareTo(current.get("ReadingDate")) > 0) {
				latest.put(equipmentId, row);
			}
		}
		return latest;
	}

	private static String firstAdjustableDocument() throws IOException, InterruptedException {
		for (Map.Entry<String, String> entry : scan().entrySet()) {
			if (isAdjustmentSuccess(entry.getValue())) {
				return entry.getKey();
			}
		}
		throw new SkipException("None of the " + SCAN_LIMIT + " scanned open reads is adjustable on this data set");
	}

	private static String firstDocumentWithStatus(String status) throws IOException, InterruptedException {
		for (Map<String, String> row : allMeterReads()) {
			if (status.equals(row.get("Status"))) {
				return row.get("DocumentNumber");
			}
		}
		throw new SkipException("lookupMeterRead returned no meter read in " + status + " status");
	}

	/** An open read that is not the latest open read of its equipment. */
	private static String supersededOpenDocument() throws IOException, InterruptedException {
		Map<String, Map<String, String>> latest = latestOpenReadPerEquipment();
		for (Map<String, String> row : allMeterReads()) {
			if (!"Open".equals(row.get("Status"))) {
				continue;
			}
			Map<String, String> newest = latest.get(row.get("EquipmentId"));
			if (row.get("ReadingDate").compareTo(newest.get("ReadingDate")) < 0) {
				return row.get("DocumentNumber");
			}
		}
		throw new SkipException("No equipment has more than one open read on this data set");
	}

	/** The latest open read of an equipment that also has a read in Work. */
	private static String latestOpenDocumentForEquipmentWithWorkRead() throws IOException, InterruptedException {
		Map<String, Map<String, String>> latest = latestOpenReadPerEquipment();
		for (Map<String, String> row : allMeterReads()) {
			if (!"Work".equals(row.get("Status"))) {
				continue;
			}
			Map<String, String> openRead = latest.get(row.get("EquipmentId"));
			if (openRead != null) {
				return openRead.get("DocumentNumber");
			}
		}
		throw new SkipException("No equipment has both an open read and a read in Work on this data set");
	}

}
