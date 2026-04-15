package com.NexusAPI.Tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.NexustAPIAutomation.java.CommonMethods;
import com.NexustAPIAutomation.java.ExtentReportManager;
import com.NexustAPIAutomation.java.PerformanceMetrics;
import com.NexustAPIAutomation.java.PerformanceTestHelper;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Private_ProcessControllerV4_Test extends BaseClass {

	private static final String PROCESS_STATUS_URI = "/process/status";
	private static final String API_VERSION = "4.0";
	private static final String VALID_HANDLE_ID = "bbf3c02607856eb7b23d24f040a032e6";
	private static final String BATCH_ID = "default1";
	private static final String FILE_NAME = "PaymentImport_pay.csv";

	// ─── Positive Test: Full Field-Level Validation ─────────────────────

	@Test(priority = 1, groups = { "Search", "ProcessController",
			"Smoke" }, description = "Verify GET /process/status returns correct completed import status with all fields")
	public void getProcessStatusv4_FullValidation()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		HashMap<String, String> params = new HashMap<>();
		params.put("HandleId", VALID_HANDLE_ID);

		String response = CommonMethods.getMethodasString(PROCESS_STATUS_URI, API_VERSION, params);
		Assert.assertNotNull(response, "Response should not be null");
		Assert.assertFalse(response.isEmpty(), "Response should not be empty");

		JsonPath json = new JsonPath(response);

		// ── Top-level structure ──
		Assert.assertTrue(json.getBoolean("Import.Success"), "Import.Success should be true");
		Assert.assertNotNull(json.get("Import.Data"), "Import.Data should not be null");
		Assert.assertNotNull(json.get("Import.Messages"), "Import.Messages should not be null");

		ExtentReportManager.logInfo("<b>Top-level structure:</b> Import.Success=true, Data and Messages present");

		// ── Data fields ──
		Map<String, Object> data = json.getMap("Import.Data");

		Assert.assertEquals(data.get("HandleId"), VALID_HANDLE_ID,
				"HandleId mismatch");
		Assert.assertEquals(data.get("BatchId"), BATCH_ID,
				"BatchId mismatch");
		Assert.assertEquals(data.get("FileName"), FILE_NAME,
				"FileName mismatch");

		ExtentReportManager.logInfo("<b>Identifiers verified:</b> HandleId, BatchId, FileName match expected values");

		// ── Status fields ──
		float statusId = json.getFloat("Import.Data.StatusId");
		String statusName = json.getString("Import.Data.StatusName");
		Assert.assertEquals(statusId, 1000.00f, "StatusId should be 1000.00 (Completed)");
		Assert.assertEquals(statusName, "Completed", "StatusName should be 'Completed'");

		ExtentReportManager.logPass("<b>Status verified:</b> StatusId=1000.00, StatusName=Completed");

		// ── Timestamp fields (format and logical order) ──
		String startTime = json.getString("Import.Data.StartTime");
		String endTime = json.getString("Import.Data.EndTime");
		String createdAt = json.getString("Import.Data.CreatedAt");

		Assert.assertNotNull(startTime, "StartTime should not be null");
		Assert.assertNotNull(endTime, "EndTime should not be null");
		Assert.assertNotNull(createdAt, "CreatedAt should not be null");

		// Validate ISO 8601 format: yyyy-MM-ddTHH:mm:ss.SSS
		String timestampPattern = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}";
		Assert.assertTrue(startTime.matches(timestampPattern),
				"StartTime format invalid: " + startTime);
		Assert.assertTrue(endTime.matches(timestampPattern),
				"EndTime format invalid: " + endTime);
		Assert.assertTrue(createdAt.matches(timestampPattern),
				"CreatedAt format invalid: " + createdAt);

		// EndTime should be after StartTime
		Assert.assertTrue(endTime.compareTo(startTime) >= 0,
				"EndTime (" + endTime + ") should be >= StartTime (" + startTime + ")");

		ExtentReportManager.logPass("<b>Timestamps verified:</b> Valid ISO format, EndTime >= StartTime");

		// ── Message field ──
		String message = json.getString("Import.Data.Message");
		Assert.assertNotNull(message, "Message should not be null");
		Assert.assertTrue(message.contains("BatchId: " + BATCH_ID),
				"Message should contain BatchId");
		Assert.assertTrue(message.contains("FileName: " + FILE_NAME),
				"Message should contain FileName");
		Assert.assertTrue(message.contains("Records completed: 7/7"),
				"Message should indicate 7/7 records completed");

		ExtentReportManager.logPass("<b>Message verified:</b> Contains BatchId, FileName, and 7/7 completion");

		// ── History array ──
		List<Map<String, Object>> history = json.getList("Import.Data.History");
		Assert.assertNotNull(history, "History should not be null");
		Assert.assertEquals(history.size(), 2, "History should have exactly 2 entries");

		// History[0] — Started status
		Map<String, Object> historyStarted = history.get(0);
		Assert.assertEquals(((Number) historyStarted.get("StatusId")).floatValue(), 30.00f,
				"History[0].StatusId should be 30.00 (Started)");
		Assert.assertEquals(historyStarted.get("StatusName"), "Started",
				"History[0].StatusName should be 'Started'");
		Assert.assertTrue(historyStarted.get("Message").toString().contains("Total Records Import: 8"),
				"History[0].Message should contain total records import count");

		// History[1] — Processing status
		Map<String, Object> historyProcessing = history.get(1);
		Assert.assertEquals(((Number) historyProcessing.get("StatusId")).floatValue(), 40.00f,
				"History[1].StatusId should be 40.00 (Processing)");
		Assert.assertEquals(historyProcessing.get("StatusName"), "Processing",
				"History[1].StatusName should be 'Processing'");
		Assert.assertTrue(historyProcessing.get("Message").toString().contains("Total valid records to process: 7"),
				"History[1].Message should contain valid record count");

		// History chronological order
		String historyStartTime = historyStarted.get("StartTime").toString();
		String historyProcessTime = historyProcessing.get("StartTime").toString();
		Assert.assertTrue(historyProcessTime.compareTo(historyStartTime) >= 0,
				"Processing StartTime should be >= Started StartTime");

		ExtentReportManager
				.logPass("<b>History verified:</b> 2 entries — Started(30) → Processing(40), chronological order");

		// ── Messages array should be empty ──
		List<Object> messages = json.getList("Import.Messages");
		Assert.assertTrue(messages.isEmpty(), "Messages array should be empty for successful import");

		ExtentReportManager.logPass("<b>All field-level validations passed for getProcessStatus v4</b>");
	}

	// ─── Negative Test: Invalid HandleId ────────────────────────────────

	@Test(priority = 2, groups = { "Search", "ProcessController",
			"Negative" }, description = "Verify GET /process/status with invalid HandleId returns proper error")
	public void getProcessStatusv4_InvalidHandleId()
			throws InterruptedException, IOException {

		HashMap<String, String> params = new HashMap<>();
		params.put("HandleId", "INVALID_HANDLE_ID_000000000000");

		Response response = CommonMethods.getMethod(PROCESS_STATUS_URI, API_VERSION, params);
		String responseBody = response.asString();

		Assert.assertNotNull(responseBody, "Response should not be null");

		JsonPath json = new JsonPath(responseBody);

		// Should either return error or empty data
		boolean hasError = responseBody.contains("error") || responseBody.contains("Error")
				|| responseBody.contains("not found") || responseBody.contains("Not Found");
		boolean hasNullData = json.get("Import.Data") == null;

		Assert.assertTrue(hasError || hasNullData,
				"Invalid HandleId should return error or null data, got: " + responseBody);

		ExtentReportManager.logPass("<b>Invalid HandleId:</b> Correctly returned error/null data");
	}

	// ─── Negative Test: Missing HandleId Parameter ──────────────────────

	@Test(priority = 3, groups = { "Search", "ProcessController",
			"Negative" }, description = "Verify GET /process/status without HandleId returns proper error")
	public void getProcessStatusv4_MissingHandleId()
			throws InterruptedException, IOException {

		HashMap<String, String> params = new HashMap<>();
		// Intentionally not adding HandleId

		Response response = CommonMethods.getMethod(PROCESS_STATUS_URI, API_VERSION, params);
		String responseBody = response.asString();

		Assert.assertNotNull(responseBody, "Response should not be null");

		// Should return 400 Bad Request or error message
		int statusCode = response.getStatusCode();
		boolean isClientError = statusCode == 400 || statusCode == 422;
		boolean hasErrorMessage = responseBody.contains("HandleId") || responseBody.contains("required")
				|| responseBody.contains("error") || responseBody.contains("Error");

		Assert.assertTrue(isClientError || hasErrorMessage,
				"Missing HandleId should return 400/422 or error message. Status: "
						+ statusCode + ", Body: " + responseBody);

		ExtentReportManager.logPass("<b>Missing HandleId:</b> Status=" + statusCode + " — Correctly rejected");
	}

	// ─── Negative Test: Empty HandleId ──────────────────────────────────

	@Test(priority = 4, groups = { "Search", "ProcessController",
			"Negative" }, description = "Verify GET /process/status with empty HandleId returns proper error")
	public void getProcessStatusv4_EmptyHandleId()
			throws InterruptedException, IOException {

		HashMap<String, String> params = new HashMap<>();
		params.put("HandleId", "");

		Response response = CommonMethods.getMethod(PROCESS_STATUS_URI, API_VERSION, params);

		int statusCode = response.getStatusCode();
		String responseBody = response.asString();

		Assert.assertEquals(responseBody,
				"{\"ProcessStatus\":{\"Success\":false,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"HandleId is not allowed to be empty\",\"Level\":3}]}}",
				"Empty HandleId should return expected error response");

		ExtentReportManager.logPass("<b>Empty HandleId:</b> Status=" + statusCode + " — Correctly rejected");
	}

	// ─── Boundary Test: Special Characters in HandleId ──────────────────

	@Test(priority = 5, groups = { "Search", "ProcessController",
			"Boundary" }, description = "Verify GET /process/status with special characters in HandleId is handled safely")
	public void getProcessStatusv4_SpecialCharsHandleId()
			throws InterruptedException, IOException {

		String[] specialInputs = {
				"<script>alert('xss')</script>",
				"'; DROP TABLE Process; --",
				"../../../etc/passwd",
				"handleId=abc&extraParam=inject"
		};

		for (String maliciousInput : specialInputs) {
			HashMap<String, String> params = new HashMap<>();
			params.put("HandleId", maliciousInput);

			Response response = CommonMethods.getMethod(PROCESS_STATUS_URI, API_VERSION, params);
			int statusCode = response.getStatusCode();

			// Should NOT return 500 (server error)
			Assert.assertNotEquals(statusCode, 500,
					"Special chars '" + maliciousInput + "' caused server error 500");

			ExtentReportManager.logPass("<b>Security:</b> Input '" + maliciousInput
					+ "' handled safely. Status=" + statusCode);
		}
	}

	// ─── Performance Test: Single Request ───────────────────────────────

	@Test(priority = 6, groups = { "Search", "ProcessController",
			"performance" }, description = "Verify GET /process/status responds within 3 seconds")
	public void getProcessStatusv4_Performance()
			throws InterruptedException {

		HashMap<String, String> params = new HashMap<>();
		params.put("HandleId", VALID_HANDLE_ID);

		PerformanceMetrics metrics = PerformanceTestHelper.measureGet(
				PROCESS_STATUS_URI, API_VERSION, params,
				PerformanceTestHelper.THRESHOLD_NORMAL);

		Assert.assertTrue(metrics.isPassed(),
				"Response time " + metrics.getResponseTimeMs() + "ms exceeded "
						+ metrics.getThresholdMs() + "ms threshold");
	}

	// ─── Performance Test: Load (10 Concurrent) ─────────────────────────

	@Test(priority = 7, groups = { "Search", "ProcessController", "performance",
			"load" }, description = "Load test: 10 concurrent users hitting /process/status")
	public void getProcessStatusv4_Load10Users()
			throws InterruptedException {

		HashMap<String, String> params = new HashMap<>();
		params.put("HandleId", VALID_HANDLE_ID);

		List<PerformanceMetrics> results = PerformanceTestHelper.loadTestGet(
				PROCESS_STATUS_URI, API_VERSION, params,
				10, PerformanceTestHelper.THRESHOLD_SLOW);

		long failCount = results.stream().filter(m -> !m.isPassed()).count();
		Assert.assertEquals(failCount, 0,
				failCount + " out of " + results.size() + " requests exceeded the "
						+ PerformanceTestHelper.THRESHOLD_SLOW + "ms threshold");
	}

	// ─── Idempotency Test: Same Request Returns Same Response ───────────

	@Test(priority = 8, groups = { "Search", "ProcessController",
			"Stability" }, description = "Verify repeated calls to /process/status return consistent results")
	public void getProcessStatusv4_Idempotency()
			throws InterruptedException, IOException {

		HashMap<String, String> params = new HashMap<>();
		params.put("HandleId", VALID_HANDLE_ID);

		String firstResponse = CommonMethods.getMethodasString(PROCESS_STATUS_URI, API_VERSION, params);
		String secondResponse = CommonMethods.getMethodasString(PROCESS_STATUS_URI, API_VERSION, params);
		String thirdResponse = CommonMethods.getMethodasString(PROCESS_STATUS_URI, API_VERSION, params);

		Assert.assertEquals(firstResponse, secondResponse,
				"First and second responses should be identical");
		Assert.assertEquals(secondResponse, thirdResponse,
				"Second and third responses should be identical");

		ExtentReportManager.logPass("<b>Idempotency verified:</b> 3 identical calls returned identical responses");
	}

	// ─── Data Type Validation ───────────────────────────────────────────

	@Test(priority = 9, groups = { "Search", "ProcessController",
			"Schema" }, description = "Verify all field data types in /process/status response")
	public void getProcessStatusv4_DataTypeValidation()
			throws InterruptedException, IOException {

		HashMap<String, String> params = new HashMap<>();
		params.put("HandleId", VALID_HANDLE_ID);

		String response = CommonMethods.getMethodasString(PROCESS_STATUS_URI, API_VERSION, params);
		JsonPath json = new JsonPath(response);

		// Boolean
		Object success = json.get("Import.Success");
		Assert.assertTrue(success instanceof Boolean,
				"Import.Success should be Boolean, got: " + success.getClass().getSimpleName());

		// String fields
		String[] stringFields = {
				"Import.Data.HandleId", "Import.Data.BatchId", "Import.Data.FileName",
				"Import.Data.StatusName", "Import.Data.StartTime", "Import.Data.EndTime",
				"Import.Data.CreatedAt", "Import.Data.Message"
		};
		for (String field : stringFields) {
			Object val = json.get(field);
			Assert.assertTrue(val instanceof String,
					field + " should be String, got: " + (val != null ? val.getClass().getSimpleName() : "null"));
		}

		// Numeric field
		Object statusId = json.get("Import.Data.StatusId");
		Assert.assertTrue(statusId instanceof Number,
				"Import.Data.StatusId should be Number, got: "
						+ (statusId != null ? statusId.getClass().getSimpleName() : "null"));

		// Array fields
		Object history = json.get("Import.Data.History");
		Assert.assertTrue(history instanceof List,
				"Import.Data.History should be List, got: "
						+ (history != null ? history.getClass().getSimpleName() : "null"));

		Object messagesArr = json.get("Import.Messages");
		Assert.assertTrue(messagesArr instanceof List,
				"Import.Messages should be List, got: "
						+ (messagesArr != null ? messagesArr.getClass().getSimpleName() : "null"));

		ExtentReportManager
				.logPass("<b>Schema/Data type validation passed:</b> Boolean, String, Number, List types correct");
	}
}