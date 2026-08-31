package com.NexusAPI.Tests;

import org.testng.annotations.Test;
import org.testng.Assert;

import java.io.IOException;
import java.sql.SQLException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.Assert;
import com.NexustAPIAutomation.java.CommonMethods;
import com.NexustAPIAutomation.java.ReadProjectProperties;

//import freemarker.core.BugException;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Private_BatchControllerv4_Test extends BaseClass {

	private void runTest(String testName, String uri, String ver, String payload, String expected,
			boolean containsCheck, String type)
			throws IOException, SQLException, ClassNotFoundException, InterruptedException {
		// ExtentTest test = extent.createTest(testName);
		// test.log(Status.INFO, "Starting test: " + testName);

		// test.log(Status.INFO, "URI: " + uri + ", Version: " + ver);
		// test.log(Status.INFO, "Payload: " + payload);
		// test.log(Status.INFO, "Expected: " + expected);

		switch (type) {

			case "post":
				Assert.assertEquals(CommonMethods.postMethodStringPayloadString(payload, uri, ver), expected);
				break;

			case "put":
				CommonMethods.putMethodstring(uri, ver, payload, expected);
				break;
			default:
				ver = "Invalid version";
				break;
		}

	}

	@Test(priority = 1, groups = "batch")
	public void postBatchtv4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		runTest("postBatchtv4", "/batch", "4.0",
				"{\"BatchId\": \"Test Batch 2026\", \"BatchType\": 3, \"OriginId\": \"\", \"CheckbookId\": \"FIRST NATIONAL\", \"Comment\": \"Example Comment\"}",
				"{\"Batch\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Created\",\"Level\":1}]}}",
				true, "post");
	}

	@Test(priority = 2, groups = "batch")
	public void postBatchtv4_err() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.Bugs(" CPDEV-21246");
		runTest("postBatchtv4_err", "/batch", "4.0",
				"{\"BatchId\": \"Test Batch 2025\", \"BatchType\": 3, \"OriginId\": \"\", \"CheckbookId\": \"FIRST NATIONAL\", \"Comment\": \"Example Comment\"}",
				"{\"Batch\":{\"Success\":false,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Batch Id ( Test Batch 2025 ) already exist.\",\"Level\":3}]}}",
				true, "post");
	}

	@Test(priority = 3, groups = "batch")
	public void putBatchtv4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		runTest("putBatchtv4", "/batch", "4.0",
				"{\"BatchId\": \"Test Batch 2025\", \"OriginId\": \"\", \"CheckbookId\": \"FIRST NATIONAL\", \"Comment\": \"Updated Example Comments\"}",
				"{\"Batch\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Updated\",\"Level\":1}]}}",
				false,
				"put");
	}

	// CPDEV-27466 : PostDate added to POST/PUT /batch, stored in um00100.umGLPOST
	private static final String POSTDATE_BATCH_ID = "PDATE27466";

	private String getBatchPostDateFromDb(String batchId) throws ClassNotFoundException, SQLException {
		String connectionString = new ReadProjectProperties().ReadFile("ConnectionStringServTWO");
		String command = "select CONVERT(varchar(10), umGLPOST, 23) as umGLPOST from Two.dbo.um00100 where umBatchID = '"
				+ batchId + "'";
		return CommonMethods.selectFromDb(command, connectionString, "umGLPOST");
	}

	@Test(priority = 4, groups = "batch")
	public void postBatchtv4_PostDate() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		runTest("postBatchtv4_PostDate", "/batch", "4.0",
				"{\"BatchId\": \"" + POSTDATE_BATCH_ID
						+ "\", \"BatchType\": 1, \"OriginId\": \"\", \"CheckbookId\": \"FIRST NATIONAL\", \"Comment\": \"Example Comment\", \"PostDate\": \"2026-08-21\"}",
				"{\"Batch\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Created\",\"Level\":1}]}}",
				true, "post");

		Assert.assertEquals(getBatchPostDateFromDb(POSTDATE_BATCH_ID), "2026-08-21",
				"umGLPOST was not saved with the PostDate sent on POST /batch");
	}

	@Test(priority = 5, groups = "batch", dependsOnMethods = "postBatchtv4_PostDate")
	public void putBatchtv4_PostDate() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		runTest("putBatchtv4_PostDate", "/batch", "4.0",
				"{\"BatchId\": \"" + POSTDATE_BATCH_ID
						+ "\", \"OriginId\": \"\", \"CheckbookId\": \"FIRST NATIONAL\", \"Comment\": \"Example Comment\", \"PostDate\": \"2026-08-27\"}",
				"{\"Batch\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Updated\",\"Level\":1}]}}",
				false, "put");

		Assert.assertEquals(getBatchPostDateFromDb(POSTDATE_BATCH_ID), "2026-08-27",
				"umGLPOST was not updated with the changed PostDate sent on PUT /batch");
	}
}