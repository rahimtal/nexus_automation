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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

	// =====================================================================
	// CPDEV-27145 - GET /api/v4/transaction/read/:DocumentNumber
	// Meter Read Inquiry header. Sources documents from Work (UM10300),
	// Open (UM20300) and History (UM30300) branches and asserts the header
	// contract shown in the Meter Reading Inquiry window.
	// =====================================================================

	private static final String HISTORY_DOCUMENT = "READ00000000002";
	private static final String NEVER_ADJUSTED = "1900-01-01";

	/** umNetMeterType value that gates UM00300.umDeliveredEquipmentID. */
	private static final int NET_METER_TYPE_DELIVERED = 4;

	/** Header fields the spec requires on every successful inquiry. */
	private static final String[] INQUIRY_FIELDS = { "DocumentNumber", "PrevDocumentNumber", "NextDocumentNumber",
			"Description", "EquipmentId", "ReadingType", "LocationId", "NetMeterType", "MeterReader", "ReasonCodeId",
			"CreatedBy", "NumberOfDays", "Components", "MeterGroup", "SequenceNumber", "RouteId", "Status",
			"TotalMultiplier", "ConnectionSequence", "ServiceTypeId", "ReadingDateTime", "PreviousReadingDate",
			"CreateDate", "DateAdjusted", "Customer" };

	private static Map<String, String> inquiryResults;

	@Test(priority = 40, groups = "MeterRead")
	public void getMeterReadInquiry_ReturnsAllSpecFields()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		Map.Entry<String, String> entry = firstInquiry();
		Map<String, Object> data = inquiryData(entry.getValue());
		for (String field : INQUIRY_FIELDS) {
			Assert.assertTrue(data.containsKey(field),
					"Header field '" + field + "' is missing for " + entry.getKey() + ". Response: " + entry.getValue());
		}
		Assert.assertEquals(data.get("DocumentNumber"), entry.getKey(),
				"The inquiry must echo the requested document. Response: " + entry.getValue());
	}

	@Test(priority = 41, groups = "MeterRead")
	public void getMeterReadInquiry_ReadingTypeAndNetMeterTypeAreResolvedLookups()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		for (Map.Entry<String, String> entry : inquiryScan().entrySet()) {
			JsonPath json = new JsonPath(entry.getValue());
			// UM40620 / umNetMeterType are expanded into Id + Description pairs.
			Assert.assertNotNull(json.get("Read.Data.ReadingType.Id"),
					"ReadingType.Id missing for " + entry.getKey() + ". Response: " + entry.getValue());
			Assert.assertTrue(notBlank(json.getString("Read.Data.ReadingType.Description")),
					"ReadingType must be described for " + entry.getKey() + ". Response: " + entry.getValue());
			Assert.assertNotNull(json.get("Read.Data.NetMeterType.Id"),
					"NetMeterType.Id missing for " + entry.getKey() + ". Response: " + entry.getValue());
			Assert.assertTrue(notBlank(json.getString("Read.Data.NetMeterType.Description")),
					"NetMeterType must be described for " + entry.getKey() + ". Response: " + entry.getValue());
		}
	}

	@Test(priority = 41, groups = "MeterRead")
	public void getMeterReadInquiry_DeliveredMeterIsAlwaysAString()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// UM00300.umDeliveredEquipmentID (UM00300H for History). Uniformly a string so
		// the UI needs no null handling, even though the ticket wording says NULL.
		requireDeliveredMeterDeployed();
		for (Map.Entry<String, String> entry : inquiryScan().entrySet()) {
			Assert.assertNotNull(new JsonPath(entry.getValue()).getString("Read.Data.NetMeterType.DeliveredMeter"),
					"DeliveredMeter must never be null for " + entry.getKey() + ". Response: " + entry.getValue());
		}
	}

	@Test(priority = 41, groups = "MeterRead")
	public void getMeterReadInquiry_DeliveredMeterIsEmptyWhenNetMeterTypeIsNot4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		requireDeliveredMeterDeployed();
		boolean checked = false;
		for (Map.Entry<String, String> entry : inquiryScan().entrySet()) {
			JsonPath json = new JsonPath(entry.getValue());
			if (json.getInt("Read.Data.NetMeterType.Id") == NET_METER_TYPE_DELIVERED) {
				continue;
			}
			Assert.assertEquals(json.getString("Read.Data.NetMeterType.DeliveredMeter"), "",
					"DeliveredMeter is gated on NetMeterType = 4 for " + entry.getKey() + ". Response: "
							+ entry.getValue());
			checked = true;
		}
		Assert.assertTrue(checked, "No scanned read has a NetMeterType other than 4");
	}

	@Test(priority = 41, groups = "MeterRead")
	public void getMeterReadInquiry_DeliveredMeterIsResolvedWhenNetMeterTypeIs4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		requireDeliveredMeterDeployed();
		Map.Entry<String, String> hit = null;
		for (Map.Entry<String, String> entry : inquiryScan().entrySet()) {
			JsonPath json = new JsonPath(entry.getValue());
			if (json.getInt("Read.Data.NetMeterType.Id") == NET_METER_TYPE_DELIVERED
					&& notBlank(json.getString("Read.Data.NetMeterType.DeliveredMeter"))) {
				hit = entry;
				break;
			}
		}
		if (hit == null) {
			throw new SkipException("No scanned read has NetMeterType 4 with a delivered meter assigned");
		}
		JsonPath json = new JsonPath(hit.getValue());
		Assert.assertNotEquals(json.getString("Read.Data.NetMeterType.DeliveredMeter"),
				json.getString("Read.Data.EquipmentId"),
				"The delivered meter must be a different meter than the one being read (" + hit.getKey()
						+ "). Response: " + hit.getValue());
	}

	@Test(priority = 42, groups = "MeterRead")
	public void getMeterReadInquiry_StatusMatchesTheSourceBranch()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		for (Map<String, String> row : allMeterReads()) {
			String expected = row.get("Status");
			String document = row.get("DocumentNumber");
			String actual = inquiryScan().get(document);
			if (actual == null) {
				continue;
			}
			Assert.assertEquals(new JsonPath(actual).getString("Read.Data.Status"), expected,
					"Status must reflect the branch the document lives in (" + document + "). Response: " + actual);
		}
	}

	@Test(priority = 43, groups = "MeterRead")
	public void getMeterReadInquiry_HistoryDocumentIsReadFromHistoryBranch()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String actual = readInquiry(HISTORY_DOCUMENT, false, false, false);
		JsonPath json = new JsonPath(actual);
		if (!"History".equals(json.getString("Read.Data.Status"))) {
			throw new SkipException(HISTORY_DOCUMENT + " is not a History read on this data set");
		}
		Assert.assertEquals(json.getBoolean("Read.Success"), Boolean.TRUE, "Response: " + actual);
		Assert.assertEquals(json.getString("Read.Data.DocumentNumber"), HISTORY_DOCUMENT, "Response: " + actual);
		// UM30300 rows are billed, so the history header carries the bill it produced.
		Assert.assertTrue(inquiryData(actual).containsKey("BillNumber"),
				"A History read should expose BillNumber. Response: " + actual);
		// History reads resolve the delivered meter from UM00300H, which has no UM00306H.
		requireDeliveredMeterDeployed();
		Assert.assertNotNull(json.getString("Read.Data.NetMeterType.DeliveredMeter"),
				"DeliveredMeter must never be null on a History read. Response: " + actual);
	}

	@Test(priority = 44, groups = "MeterRead")
	public void getMeterReadInquiry_DateAdjustedIsSetWhenTheReadWasAdjusted()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		Map.Entry<String, String> hit = firstInquiryWhere("Read.Data.DateAdjusted", NEVER_ADJUSTED, false);
		if (hit == null) {
			throw new SkipException("No scanned read has been adjusted on this data set");
		}
		String dateAdjusted = new JsonPath(hit.getValue()).getString("Read.Data.DateAdjusted");
		Assert.assertNotEquals(dateAdjusted, NEVER_ADJUSTED, "Response: " + hit.getValue());
		Assert.assertTrue(dateAdjusted.matches("\\d{4}-\\d{2}-\\d{2}"),
				"DateAdjusted must be a plain date for " + hit.getKey() + ". Response: " + hit.getValue());
	}

	@Test(priority = 45, groups = "MeterRead")
	public void getMeterReadInquiry_DateAdjustedDefaultsWhenNeverAdjusted()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		Map.Entry<String, String> hit = firstInquiryWhere("Read.Data.DateAdjusted", NEVER_ADJUSTED, true);
		if (hit == null) {
			throw new SkipException("Every scanned read has already been adjusted on this data set");
		}
		Assert.assertEquals(new JsonPath(hit.getValue()).getString("Read.Data.DateAdjusted"), NEVER_ADJUSTED,
				"An unadjusted read must default DateAdjusted instead of returning null. Response: " + hit.getValue());
	}

	@Test(priority = 46, groups = "MeterRead")
	public void getMeterReadInquiry_ReasonCodeIsReturned()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		for (Map.Entry<String, String> entry : inquiryScan().entrySet()) {
			Assert.assertNotNull(new JsonPath(entry.getValue()).getString("Read.Data.ReasonCodeId"),
					"ReasonCodeId must be an empty string rather than null for " + entry.getKey() + ". Response: "
							+ entry.getValue());
		}
		Map.Entry<String, String> hit = firstInquiryWithNonBlank("Read.Data.ReasonCodeId");
		if (hit == null) {
			throw new SkipException("No scanned read carries a reason code on this data set");
		}
		Assert.assertTrue(notBlank(new JsonPath(hit.getValue()).getString("Read.Data.ReasonCodeId")),
				"Response: " + hit.getValue());
	}

	@Test(priority = 47, groups = "MeterRead")
	public void getMeterReadInquiry_MeterReaderAndDescriptionAreReturned()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		for (Map.Entry<String, String> entry : inquiryScan().entrySet()) {
			JsonPath json = new JsonPath(entry.getValue());
			Assert.assertNotNull(json.getString("Read.Data.MeterReader"),
					"MeterReader must be present for " + entry.getKey() + ". Response: " + entry.getValue());
			String description = json.getString("Read.Data.Description");
			Assert.assertNotNull(description,
					"Description must be present for " + entry.getKey() + ". Response: " + entry.getValue());
			// UM10302.umDescr64
			Assert.assertTrue(description.length() <= 64,
					"Description exceeds 64 characters for " + entry.getKey() + ". Response: " + entry.getValue());
		}
		if (firstInquiryWithNonBlank("Read.Data.MeterReader") == null
				&& firstInquiryWithNonBlank("Read.Data.Description") == null) {
			throw new SkipException("No scanned read carries a meter reader or a description on this data set");
		}
	}

	@Test(priority = 48, groups = "MeterRead")
	public void getMeterReadInquiry_PrevNextFlagsOffReturnNoSiblings()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		for (Map.Entry<String, String> entry : inquiryScan().entrySet()) {
			JsonPath json = new JsonPath(entry.getValue());
			Assert.assertEquals(json.getString("Read.Data.PrevDocumentNumber"), "",
					"PrevDocumentNumber must stay empty when PrevNextIn* are false (" + entry.getKey() + "). Response: "
							+ entry.getValue());
			Assert.assertEquals(json.getString("Read.Data.NextDocumentNumber"), "",
					"NextDocumentNumber must stay empty when PrevNextIn* are false (" + entry.getKey() + "). Response: "
							+ entry.getValue());
		}
	}

	@Test(priority = 49, groups = "MeterRead")
	public void getMeterReadInquiry_PrevNextFlagsOnResolveSiblings()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String sibling = null;
		String owner = null;
		String response = null;
		for (String document : inquiryScan().keySet()) {
			response = readInquiry(document, true, true, true);
			JsonPath json = new JsonPath(response);
			String prev = json.getString("Read.Data.PrevDocumentNumber");
			String next = json.getString("Read.Data.NextDocumentNumber");
			Assert.assertNotEquals(prev, document, "A document cannot be its own previous. Response: " + response);
			Assert.assertNotEquals(next, document, "A document cannot be its own next. Response: " + response);
			if (sibling == null) {
				sibling = notBlank(prev) ? prev : (notBlank(next) ? next : null);
				owner = document;
			}
		}
		if (sibling == null) {
			throw new SkipException("No scanned read has a previous or next document on this data set");
		}
		// The resolved sibling must itself be a valid meter read document.
		String siblingResponse = readInquiry(sibling, false, false, false);
		Assert.assertEquals(new JsonPath(siblingResponse).getBoolean("Read.Success"), Boolean.TRUE,
				sibling + " was returned as a sibling of " + owner + " but cannot be loaded. Response: "
						+ siblingResponse);
		Assert.assertEquals(new JsonPath(siblingResponse).getString("Read.Data.DocumentNumber"), sibling,
				"Response: " + siblingResponse);
	}

	@Test(priority = 50, groups = "MeterRead")
	public void getMeterReadInquiry_CustomerIsIndividualOrBusinessNotBoth()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		for (Map.Entry<String, String> entry : inquiryScan().entrySet()) {
			JsonPath json = new JsonPath(entry.getValue());
			String type = json.getString("Read.Data.Customer.Type");
			Object individual = json.get("Read.Data.Customer.Individual");
			Object business = json.get("Read.Data.Customer.Business");
			Assert.assertTrue(notBlank(json.getString("Read.Data.Customer.Id")),
					"The bill-to customer must be linked for " + entry.getKey() + ". Response: " + entry.getValue());
			if ("Individual".equals(type)) {
				Assert.assertNotNull(individual, "Response: " + entry.getValue());
				Assert.assertNull(business,
						"Business must be null for an individual customer. Response: " + entry.getValue());
				Assert.assertTrue(notBlank(json.getString("Read.Data.Customer.Individual.FullName")),
						"Response: " + entry.getValue());
			} else {
				Assert.assertNotNull(business, "Response: " + entry.getValue());
				Assert.assertNull(individual,
						"Individual must be null for a business customer. Response: " + entry.getValue());
			}
		}
	}

	@Test(priority = 51, groups = "MeterRead")
	public void getMeterReadInquiry_NumberOfDaysMatchesTheReadingWindow()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		for (Map.Entry<String, String> entry : inquiryScan().entrySet()) {
			JsonPath json = new JsonPath(entry.getValue());
			String previous = json.getString("Read.Data.PreviousReadingDate");
			String reading = json.getString("Read.Data.ReadingDateTime");
			Assert.assertTrue(reading != null && reading.contains("T"),
					"ReadingDateTime must keep its time component for " + entry.getKey() + ". Response: "
							+ entry.getValue());
			long expected = ChronoUnit.DAYS.between(LocalDate.parse(previous),
					LocalDate.parse(reading.substring(0, 10)));
			long actual = json.getLong("Read.Data.NumberOfDays");
			// The SP counts with DATEDIFF over the stored datetimes, so a single day of
			// drift against the date-only window is expected.
			Assert.assertTrue(actual >= 0 && Math.abs(actual - expected) <= 1,
					"NumberOfDays must span PreviousReadingDate -> ReadingDateTime (expected ~" + expected + ") for "
							+ entry.getKey() + ". Response: " + entry.getValue());
		}
	}

	@Test(priority = 52, groups = "MeterRead")
	public void getMeterReadInquiry_ComponentsCountIsNeverNegative()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		for (Map.Entry<String, String> entry : inquiryScan().entrySet()) {
			JsonPath json = new JsonPath(entry.getValue());
			// UM30303 component count and UM00300 connection sequence.
			Assert.assertTrue(json.getInt("Read.Data.Components") >= 0,
					"Components cannot be negative for " + entry.getKey() + ". Response: " + entry.getValue());
			Assert.assertTrue(json.getInt("Read.Data.ConnectionSequence") > 0,
					"ConnectionSequence must identify a connection for " + entry.getKey() + ". Response: "
							+ entry.getValue());
			Assert.assertTrue(notBlank(json.getString("Read.Data.ServiceTypeId")),
					"ServiceTypeId must come from the master connection for " + entry.getKey() + ". Response: "
							+ entry.getValue());
		}
	}

	@Test(priority = 53, groups = "MeterRead")
	public void getMeterReadInquiry_InvalidDocumentNumber()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String document = "READNOTAREAD01";
		String actual = readInquiry(document, false, false, false);
		JsonPath json = new JsonPath(actual);
		Assert.assertEquals(json.getBoolean("Read.Success"), Boolean.FALSE,
				"An unknown document must not resolve. Response: " + actual);
		Assert.assertNull(json.get("Read.Data"), "Response: " + actual);
		Assert.assertTrue(actual.toLowerCase().contains(MSG_INVALID_DOCUMENT),
				"The failure should name the invalid document. Response: " + actual);
	}

	@Test(priority = 54, groups = "MeterRead")
	public void getMeterReadInquiry_FailureStillReturnsHttp200()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		Response response = CommonMethods.getMethod(READ_INQUIRY_URI + "READNOTAREAD01", VER,
				new HashMap<String, String>());
		Assert.assertEquals(response.getStatusCode(), 200,
				"A failed inquiry must still return HTTP 200. Body: " + response.asString());
	}

	// ---------------------------------------------------------------------
	// Read inquiry helpers
	// ---------------------------------------------------------------------

	private static String readInquiry(String documentNumber, boolean prevNextInWork, boolean prevNextInOpen,
			boolean prevNextInHistory) throws IOException, InterruptedException {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("PrevNextInWork", String.valueOf(prevNextInWork));
		params.put("PrevNextInOpen", String.valueOf(prevNextInOpen));
		params.put("PrevNextInHistory", String.valueOf(prevNextInHistory));
		return CommonMethods.getMethodasString(READ_INQUIRY_URI + documentNumber, VER, params);
	}

	/** Loads up to {@link #SCAN_LIMIT} documents with the Prev/Next flags off. */
	private static Map<String, String> inquiryScan() throws IOException, InterruptedException {
		if (inquiryResults == null) {
			Map<String, String> results = new LinkedHashMap<String, String>();
			for (Map<String, String> row : allMeterReads()) {
				if (results.size() >= SCAN_LIMIT) {
					break;
				}
				String document = row.get("DocumentNumber");
				String actual = readInquiry(document, false, false, false);
				if (new JsonPath(actual).getBoolean("Read.Success")) {
					results.put(document, actual);
				}
			}
			Assert.assertFalse(results.isEmpty(), "No meter read document could be loaded through " + READ_INQUIRY_URI);
			inquiryResults = results;
		}
		return inquiryResults;
	}

	@SuppressWarnings("unchecked")
	private static Map<String, Object> inquiryData(String json) {
		Map<String, Object> data = (Map<String, Object>) new JsonPath(json).get("Read.Data");
		Assert.assertNotNull(data, "Expected a populated Read.Data. Response: " + json);
		return data;
	}

	private static Map.Entry<String, String> firstInquiry() throws IOException, InterruptedException {
		return inquiryScan().entrySet().iterator().next();
	}

	private static Map.Entry<String, String> firstInquiryWhere(String path, String value, boolean shouldEqual)
			throws IOException, InterruptedException {
		for (Map.Entry<String, String> entry : inquiryScan().entrySet()) {
			if (value.equals(new JsonPath(entry.getValue()).getString(path)) == shouldEqual) {
				return entry;
			}
		}
		return null;
	}

	private static Map.Entry<String, String> firstInquiryWithNonBlank(String path)
			throws IOException, InterruptedException {
		for (Map.Entry<String, String> entry : inquiryScan().entrySet()) {
			if (notBlank(new JsonPath(entry.getValue()).getString(path))) {
				return entry;
			}
		}
		return null;
	}

	private static boolean notBlank(String value) {
		return value != null && !value.trim().isEmpty();
	}

	/** DeliveredMeter is only emitted by builds that carry the CPDEV-27145 U01.16 SP. */
	private static void requireDeliveredMeterDeployed() throws IOException, InterruptedException {
		for (Map.Entry<String, String> entry : inquiryScan().entrySet()) {
			if (new JsonPath(entry.getValue()).get("Read.Data.NetMeterType.DeliveredMeter") != null) {
				return;
			}
		}
		throw new SkipException("This build does not return NetMeterType.DeliveredMeter - "
				+ "csmApi_spMeterReadInquiryGet does not select umDeliveredEquipmentID");
	}

}
