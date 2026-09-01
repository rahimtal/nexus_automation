package com.NexusAPI.Tests;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;

import io.restassured.path.json.JsonPath;

/**
 * CPDEV-27360 — retest of PUT /check/sendToAP (reworded messages 50623 / 50624)
 * and GET /check/setup (Vendor Integration validation removed).
 */
public class Private_checkSendToAPSetupv4_Test extends BaseClass {

	private static final String VER = "4.0";
	private static final String SETUP_URI = "/check/setup";
	private static final String SENDTOAP_URI = "/check/sendToAP";
	private static final String CHECK_DOC = "CHEQ00000000009";

	private static final String MSG_50623 = "A check file path has not been configured. Please configure a valid file path before sending the cheque to AP.";
	private static final String MSG_50624 = "The configured check file path is invalid or does not exist. Please update the file path and try again.";
	private static final String MSG_50591 = "Vendor Integration has to be set to Send to AP.";
	private static final String MSG_50615 = "Missing check setup";
	private static final String MSG_50621 = "Check already sent to AP.";

	/** Wording that must never reach an end user in messages 50623 / 50624. */
	private static final String[] TECHNICAL_TOKENS = { "ApiCheckFilePath", "putSendToAP", "checkController" };

	private static final String UM42901_COLUMNS = "umSetupKey,umMiscChargeType,umChkBook,umChequeRepeatDupli,"
			+ "umMinAutoRefund,umVendorIntegration,umCheckClearAccount,umVendorClass,umVoucherDescr,umFilePath,umFileSequence";

	private static final String TWO_DB = CommonMethods.Read.ReadFile("ConnectionStringServTWO");
	private static final String API_DB = CommonMethods.Read.ReadFile("ConnectionStringApi");

	private static final String API_PATH = CommonMethods.Read.ReadFile("sendToApPathApi");
	private static final String LOCAL_PATH = CommonMethods.Read.ReadFile("sendToApPathLocal");
	private static final String INVALID_PATH = CommonMethods.Read.ReadFile("sendToApPathInvalid");
	private static final String FILE_CHECK_COMMAND = CommonMethods.Read.ReadFile("sendToApFileCheck");

	private static String originalApiCheckFilePath;
	private static String originalCsmFilePath;
	private static String originalVendorIntegration;

	@BeforeClass(alwaysRun = true)
	public void captureOriginalConfiguration() throws ClassNotFoundException, SQLException {
		originalApiCheckFilePath = readApiCheckFilePath();
		originalCsmFilePath = CommonMethods.selectFromDb("SELECT RTRIM(umFilePath) AS umFilePath FROM UM42901", TWO_DB,
				"umFilePath");
		originalVendorIntegration = CommonMethods
				.selectFromDb("SELECT umVendorIntegration FROM UM42901", TWO_DB, "umVendorIntegration");

		Assert.assertNotNull(originalApiCheckFilePath,
				"CSM_ParameterConfig row checkController/putSendToAP/ApiCheckFilePath is missing");
		Assert.assertNotNull(originalVendorIntegration, "UM42901 (Cheque Setup) has no row to test against");
	}

	@AfterMethod(alwaysRun = true)
	public void restoreConfiguration() throws ClassNotFoundException, SQLException {
		setApiCheckFilePath(originalApiCheckFilePath);
		setCsmFilePath(originalCsmFilePath);
		setVendorIntegration(originalVendorIntegration);
	}

	@AfterClass(alwaysRun = true)
	public void resetCheckDocument() throws ClassNotFoundException, SQLException {
		markCheckUnsent();
	}

	// =====================================================================
	// GET /api/v4/check/setup
	// =====================================================================

	@Test(priority = 1, groups = "check")
	public void getCheckSetup_HappyPath() throws IOException, InterruptedException {
		JsonPath json = JsonPath.from(getSetup());

		Assert.assertTrue(json.getBoolean("Check.Success"), "GET /check/setup must succeed");
		Assert.assertEquals(json.getList("Check.Messages").size(), 0, "GET /check/setup must return no messages");

		for (String field : new String[] { "ChargeType", "CheckBook", "Comment", "RepeatChecks", "MinAutoRefund",
				"VendorIntegration.Id", "VendorIntegration.Description", "APVoucher.Id", "APVoucher.Description",
				"FilePath", "FileSequence", "BatchId" }) {
			Assert.assertNotNull(json.get("Check.Data." + field), "Missing setup field " + field);
		}
	}

	@Test(priority = 2, groups = "check")
	public void getCheckSetup_BatchIdFormat() throws IOException, InterruptedException {
		JsonPath json = JsonPath.from(getSetup());

		// UM_spBatch_CreateByPrefix appends its own sequence when the batch already exists.
		String expectedPrefix = "CH" + LocalDate.now().format(DateTimeFormatter.ofPattern("MMddyy"))
				+ userIdPrefix(CommonMethods.userName);

		Assert.assertTrue(json.getString("Check.Data.BatchId").startsWith(expectedPrefix),
				"BatchId must start with CH + MMDDYY + first 5 characters of the user id, got "
						+ json.getString("Check.Data.BatchId"));
	}

	@Test(priority = 3, groups = "check")
	public void getCheckSetup_IgnoresUnexpectedQueryParameter() throws IOException, InterruptedException {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("VendorIntegration", "3");

		JsonPath json = JsonPath.from(CommonMethods.getMethodasString(SETUP_URI, VER, params));

		Assert.assertTrue(json.getBoolean("Check.Success"),
				"GET /check/setup takes no parameters and must ignore anything the caller sends");
		Assert.assertNotNull(json.get("Check.Data.BatchId"), "Setup data must still be returned");
	}

	/**
	 * CPDEV-27360: 50591 was removed from csmApi_spCheckSetupGet, so the setup data
	 * must come back even when Vendor Integration is not "Send to AP".
	 */
	@Test(priority = 4, groups = "check")
	public void getCheckSetup_VendorIntegrationNot3_NoLonger50591()
			throws IOException, InterruptedException, ClassNotFoundException, SQLException {
		setVendorIntegration("1");

		JsonPath json = JsonPath.from(getSetup());

		Assert.assertTrue(json.getBoolean("Check.Success"),
				"GET /check/setup must still succeed when Vendor Integration is not Send to AP");
		Assert.assertEquals(json.getInt("Check.Data.VendorIntegration.Id"), 1,
				"Setup must report the configured Vendor Integration");
		Assert.assertNotNull(json.getString("Check.Data.BatchId"), "Batch must still be created");
		Assert.assertFalse(json.getString("Check").contains(MSG_50591),
				"Message 50591 must no longer be returned by GET /check/setup");
	}

	// =====================================================================
	// PUT /api/v4/check/sendToAP
	// =====================================================================

	@Test(priority = 10, groups = "check")
	public void sendToAP_SchemaValidation() throws IOException, InterruptedException {
		String missing = sendToAP("{}");
		Assert.assertTrue(missing.contains("DocumentNumber") && missing.contains("required"),
				"DocumentNumber is required. Response: " + missing);

		String unknown = sendToAP("{\"DocumentNumber\":\"" + CHECK_DOC + "\",\"VendorIntegration\":3}");
		Assert.assertTrue(unknown.contains("is not allowed"),
				"Unknown properties must be rejected by the strict schema. Response: " + unknown);

		String tooLong = sendToAP("{\"DocumentNumber\":\"CHEQ000000000099\"}");
		Assert.assertTrue(tooLong.contains("15"),
				"DocumentNumber is limited to 15 characters. Response: " + tooLong);
	}

	/**
	 * CPDEV-27360: reworded message 50623.
	 */
	@Test(priority = 11, groups = "check")
	public void sendToAP_50623_NoFilePathConfigured()
			throws IOException, InterruptedException, ClassNotFoundException, SQLException {
		// Both sources must be blank - a blank API parameter alone is auto-filled from the CSM setup.
		setApiCheckFilePath("");
		setCsmFilePath("");
		markCheckUnsent();

		JsonPath json = JsonPath.from(sendToAP(payloadFor(CHECK_DOC)));

		Assert.assertFalse(json.getBoolean("Check.Success"), "Send to AP must fail with no file path configured");
		assertMessage(json, MSG_50623);
		assertNoTechnicalWording(json.getString("Check.Messages"));
	}

	/**
	 * CPDEV-27360: reworded message 50624.
	 */
	@Test(priority = 12, groups = "check")
	public void sendToAP_50624_InvalidFilePath()
			throws IOException, InterruptedException, ClassNotFoundException, SQLException {
		setApiCheckFilePath(INVALID_PATH);
		markCheckUnsent();

		JsonPath json = JsonPath.from(sendToAP(payloadFor(CHECK_DOC)));

		Assert.assertFalse(json.getBoolean("Check.Success"), "Send to AP must fail with an unreachable file path");
		assertMessage(json, MSG_50624);
		assertNoTechnicalWording(json.getString("Check.Messages"));
	}

	/**
	 * CPDEV-27360 regression: 50591 was only removed from the Setup GET, the send
	 * action must still enforce it.
	 */
	@Test(priority = 13, groups = "check")
	public void sendToAP_50591_VendorIntegrationNot3()
			throws IOException, InterruptedException, ClassNotFoundException, SQLException {
		setApiCheckFilePath(API_PATH);
		setCsmFilePath(API_PATH);
		setVendorIntegration("1");
		markCheckUnsent();

		JsonPath json = JsonPath.from(sendToAP(payloadFor(CHECK_DOC)));

		Assert.assertFalse(json.getBoolean("Check.Success"),
				"Send to AP must still fail when Vendor Integration is not Send to AP");
		assertMessage(json, MSG_50591);
	}

	@Test(priority = 14, groups = "check")
	public void sendToAP_50615_MissingCheckSetup()
			throws IOException, InterruptedException, ClassNotFoundException, SQLException {
		setApiCheckFilePath(API_PATH);
		markCheckUnsent();
		backupAndClearCheckSetup();

		try {
			JsonPath json = JsonPath.from(sendToAP(payloadFor(CHECK_DOC)));

			Assert.assertFalse(json.getBoolean("Check.Success"), "Send to AP must fail without a cheque setup row");
			assertMessage(json, MSG_50615);
		} finally {
			restoreCheckSetup();
		}
	}

	@Test(priority = 15, groups = "check")
	public void sendToAP_50612_UnknownDocument()
			throws IOException, InterruptedException, ClassNotFoundException, SQLException {
		setApiCheckFilePath(API_PATH);
		setCsmFilePath(API_PATH);

		JsonPath json = JsonPath.from(sendToAP(payloadFor("CHEQ00000099999")));

		Assert.assertFalse(json.getBoolean("Check.Success"), "An unknown document number must fail");
		assertMessage(json, "CHEQ00000099999");
	}

	@Test(priority = 20, groups = "check")
	public void sendToAP_HappyPath()
			throws IOException, InterruptedException, ClassNotFoundException, SQLException {
		setApiCheckFilePath(API_PATH);
		setCsmFilePath(API_PATH);
		markCheckUnsent();
		int sequenceBefore = readFileSequence();

		JsonPath json = JsonPath.from(sendToAP(payloadFor(CHECK_DOC)));

		Assert.assertTrue(json.getBoolean("Check.Success"), "Send to AP happy path must succeed");

		String fileName = json.getString("Check.Data.FileName");
		Assert.assertNotNull(fileName, "Data.FileName must contain the full path of the created file");
		Assert.assertTrue(fileName.startsWith(API_PATH), "FileName must sit under the configured path: " + fileName);
		assertFileCreated(fileName);

		Assert.assertEquals(readSentToAPFlag(), "1", "The SendToAP flag must be set on the check");
		Assert.assertEquals(readFileSequence(), sequenceBefore + 1, "The file sequence number must be incremented");
	}

	/**
	 * 50625 is a level 2 warning - the file is still created and Success stays true.
	 */
	@Test(priority = 21, groups = "check")
	public void sendToAP_50625_EmptyCsmFilePath_WarningOnly()
			throws IOException, InterruptedException, ClassNotFoundException, SQLException {
		setApiCheckFilePath(API_PATH);
		setCsmFilePath("");
		markCheckUnsent();

		JsonPath json = JsonPath.from(sendToAP(payloadFor(CHECK_DOC)));

		Assert.assertTrue(json.getBoolean("Check.Success"), "An empty CSM file path is a warning, not a failure");
		assertWarningLevel(json);
		assertFileCreated(json.getString("Check.Data.FileName"));
	}

	/**
	 * 50626 is a level 2 warning - the file is still created and Success stays true.
	 */
	@Test(priority = 22, groups = "check")
	public void sendToAP_50626_InvalidCsmFilePath_WarningOnly()
			throws IOException, InterruptedException, ClassNotFoundException, SQLException {
		setApiCheckFilePath(API_PATH);
		setCsmFilePath(INVALID_PATH);
		markCheckUnsent();

		JsonPath json = JsonPath.from(sendToAP(payloadFor(CHECK_DOC)));

		Assert.assertTrue(json.getBoolean("Check.Success"), "An invalid CSM file path is a warning, not a failure");
		assertWarningLevel(json);
		assertFileCreated(json.getString("Check.Data.FileName"));
	}

	@Test(priority = 23, groups = "check")
	public void sendToAP_50621_AlreadySentToAP()
			throws IOException, InterruptedException, ClassNotFoundException, SQLException {
		setApiCheckFilePath(API_PATH);
		setCsmFilePath(API_PATH);
		markCheckSent();

		JsonPath json = JsonPath.from(sendToAP(payloadFor(CHECK_DOC)));

		Assert.assertFalse(json.getBoolean("Check.Success"), "A check already sent to AP must not be sent again");
		assertMessage(json, MSG_50621);
	}

	// =====================================================================
	// Helpers
	// =====================================================================

	private String getSetup() throws IOException, InterruptedException {
		return CommonMethods.getMethodasString(SETUP_URI, VER, new HashMap<String, String>());
	}

	private String sendToAP(String payload) throws IOException, InterruptedException {
		return CommonMethods.putMethodasString(SENDTOAP_URI, VER, payload);
	}

	private static String payloadFor(String documentNumber) {
		return "{\"DocumentNumber\":\"" + documentNumber + "\"}";
	}

	private static String userIdPrefix(String userId) {
		return userId.length() > 5 ? userId.substring(0, 5) : userId;
	}

	private static void assertMessage(JsonPath json, String expected) {
		String messages = json.getString("Check.Messages");
		Assert.assertTrue(messages != null && messages.contains(expected),
				"Expected message [" + expected + "] but got: " + messages);
	}

	private static void assertNoTechnicalWording(String messages) {
		for (String token : TECHNICAL_TOKENS) {
			Assert.assertFalse(messages.contains(token),
					"End user message must not expose the internal name '" + token + "': " + messages);
		}
	}

	private static void assertWarningLevel(JsonPath json) {
		Assert.assertTrue(json.getList("Check.Messages.findAll { it.Level == 2 }").size() > 0,
				"Expected a level 2 warning but got: " + json.getString("Check.Messages"));
	}

	/**
	 * Verifies the check file on the API host, either through a shared path or - when
	 * the API does not share a filesystem with the runner - through a configured command.
	 */
	private static void assertFileCreated(String apiFileName) throws IOException, InterruptedException {
		Assert.assertNotNull(apiFileName, "Data.FileName must contain the full path of the created file");

		if (LOCAL_PATH != null && !LOCAL_PATH.trim().isEmpty()) {
			String local = LOCAL_PATH + apiFileName.substring(API_PATH.length()).replace('/', File.separatorChar);
			Assert.assertTrue(new File(local).exists(), "The check file was not created on disk: " + local);
			return;
		}

		Assert.assertTrue(FILE_CHECK_COMMAND != null && !FILE_CHECK_COMMAND.trim().isEmpty(),
				"Configure sendToApPathLocal or sendToApFileCheck so the created check file can be verified");

		String[] command = FILE_CHECK_COMMAND.replace("{0}", apiFileName).trim().split("\\s+");
		Process process = new ProcessBuilder(command).redirectErrorStream(true).start();
		int exitCode = process.waitFor();
		Assert.assertEquals(exitCode, 0, "The check file was not created on the API host: " + apiFileName);
	}

	private static String readApiCheckFilePath() throws ClassNotFoundException, SQLException {
		return CommonMethods.selectFromDb(
				"SELECT ParameterValue FROM CSM_ParameterConfig WHERE ControllerName = 'checkController'"
						+ " AND MethodName = 'putSendToAP' AND ParameterName = 'ApiCheckFilePath'",
				API_DB, "ParameterValue");
	}

	private static void setApiCheckFilePath(String value) throws ClassNotFoundException, SQLException {
		CommonMethods.executeUpdateDb(
				"UPDATE CSM_ParameterConfig SET ParameterValue = '" + escape(value)
						+ "' WHERE ControllerName = 'checkController' AND MethodName = 'putSendToAP'"
						+ " AND ParameterName = 'ApiCheckFilePath'",
				API_DB);
	}

	private static void setCsmFilePath(String value) throws ClassNotFoundException, SQLException {
		CommonMethods.executeUpdateDb("UPDATE UM42901 SET umFilePath = '" + escape(value) + "'", TWO_DB);
	}

	private static void setVendorIntegration(String value) throws ClassNotFoundException, SQLException {
		CommonMethods.executeUpdateDb("UPDATE UM42901 SET umVendorIntegration = " + Integer.parseInt(value), TWO_DB);
	}

	private static void backupAndClearCheckSetup() throws ClassNotFoundException, SQLException {
		CommonMethods.executeUpdateDb("IF OBJECT_ID('UM42901_BKP_CPDEV27360') IS NOT NULL"
				+ " DROP TABLE UM42901_BKP_CPDEV27360", TWO_DB);
		CommonMethods.executeUpdateDb(
				"SELECT " + UM42901_COLUMNS + " INTO UM42901_BKP_CPDEV27360 FROM UM42901", TWO_DB);
		CommonMethods.executeUpdateDb("DELETE FROM UM42901", TWO_DB);
	}

	private static void restoreCheckSetup() throws ClassNotFoundException, SQLException {
		CommonMethods.executeUpdateDb("INSERT INTO UM42901 (" + UM42901_COLUMNS + ") SELECT " + UM42901_COLUMNS
				+ " FROM UM42901_BKP_CPDEV27360", TWO_DB);
		CommonMethods.executeUpdateDb("DROP TABLE UM42901_BKP_CPDEV27360", TWO_DB);
	}

	private static void markCheckUnsent() throws ClassNotFoundException, SQLException {
		CommonMethods.executeUpdateDb(
				"UPDATE UM12906 SET umSentToAP = 0 WHERE umDocumentNumber = '" + CHECK_DOC + "'", TWO_DB);
	}

	private static void markCheckSent() throws ClassNotFoundException, SQLException {
		CommonMethods.executeUpdateDb(
				"UPDATE UM12906 SET umSentToAP = 1 WHERE umDocumentNumber = '" + CHECK_DOC + "'", TWO_DB);
	}

	private static String readSentToAPFlag() throws ClassNotFoundException, SQLException {
		return CommonMethods.selectFromDb(
				"SELECT umSentToAP FROM UM12906 WHERE umDocumentNumber = '" + CHECK_DOC + "'", TWO_DB, "umSentToAP");
	}

	private static int readFileSequence() throws ClassNotFoundException, SQLException {
		return Integer.parseInt(CommonMethods.selectFromDb("SELECT umFileSequence FROM UM42901", TWO_DB,
				"umFileSequence").trim());
	}

	private static String escape(String value) {
		return value == null ? "" : value.replace("'", "''");
	}
}
