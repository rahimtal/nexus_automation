package com.NexusAPI.Tests;

import org.testng.annotations.Test; 
import org.testng.Assert;

import java.io.IOException;
import java.sql.SQLException;
import org.testng.Assert;
import org.testng.annotations.Test; import org.testng.Assert;
import com.NexustAPIAutomation.java.CommonMethods;



//import freemarker.core.BugException;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Private_BatchControllerv4_Test extends BaseClass {

	private void runTest(String testName, String uri, String ver, String payload, String expected,
			boolean containsCheck, String type)
			throws IOException, SQLException, ClassNotFoundException, InterruptedException {
		//ExtentTest test = extent.createTest(testName);
	//test.log(Status.INFO, "Starting test: " + testName);

	//test.log(Status.INFO, "URI: " + uri + ", Version: " + ver);
	//test.log(Status.INFO, "Payload: " + payload);
	//test.log(Status.INFO, "Expected: " + expected);

		switch (type) {

		case "post":
			Assert.assertEquals(CommonMethods.postMethodStringPayloadString(payload, uri, ver), expected);
			break;

		case "pur":
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
				"{\"BatchId\": \"Test Batch 2025\", \"BatchType\": 3, \"OriginId\": \"\", \"CheckbookId\": \"FIRST NATIONAL\", \"Comment\": \"Example Comment\"}",
				"{\"Batch\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Created\",\"Level\":1}]}}",
				true, "post");
	}

	@Test(priority = 2, groups = "batch")
	public void postBatchtv4_err() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		//CommonMethods.Bugs(" CPDEV-21246");
		runTest("postBatchtv4_err", "/batch", "4.0",
				"{\"BatchId\": \"Test Batch 2025\", \"BatchType\": 3, \"OriginId\": \"\", \"CheckbookId\": \"FIRST NATIONAL\", \"Comment\": \"Example Comment\"}",
				"{\"Batch\":{\"Success\":false,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Batch Id ( Test Batch 2025 ) already exist.\",\"Level\":3}]}}",
				true, "post");
	}

	@Test(priority = 3, groups = "batch")
	public void putBatchtv4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		runTest("putBatchtv4", "/batch", "4.0",
				"{\"BatchId\": \"Test Batch 2025\", \"OriginId\": \"\", \"CheckbookId\": \"FIRST NATIONAL\", \"Comment\": \"Updated Example Comments\"}",
				"{\"Batch\":{\"Success\":true,\"Messages\":[{\"Enabled\":1,\"Info\":\"Updated\",\"Level\":1}]}}", false,
				"put");
	}
}