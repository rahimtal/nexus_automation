package Private;

import org.testng.annotations.Test;
import org.testng.Assert;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;
import com.NexustAPIAutomation.java.Retry;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class BatchControllerv4 {


	@Test(priority = 1, groups = "batch", retryAnalyzer = Retry.class)
	public static void postBatchtv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/batch";
		String ver = "4.0";
		String payload = "{\r\n" + 
				"    \"BatchId\": \"Test Batch 2025\",\r\n" + 
				"    \"BatchType\": 3,\r\n" + 
				"    \"OriginId\": \"\",\r\n" + 
				"    \"CheckbookId\": \"FIRST NATIONAL\",\r\n" + 
				"    \"Comment\": \"Example Comment\"\r\n" + 
				"}";
		String expected = "{\"Batch\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Created\",\"Level\":1}]}}";
		String actual = CommonMethods.postMethodStringPayloadString(payload, uri, ver);
		Assert.assertTrue(actual.contains(expected));

	}
	
	@Test(priority = 2, groups = "batch", retryAnalyzer = Retry.class)
	public static void postBatchtv4_err()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/batch";
		String ver = "4.0";
		String payload = "{\r\n" + 
				"    \"BatchId\": \"Test Batch 2025\",\r\n" + 
				"    \"BatchType\": 3,\r\n" + 
				"    \"OriginId\": \"\",\r\n" + 
				"    \"CheckbookId\": \"FIRST NATIONAL\",\r\n" + 
				"    \"Comment\": \"Example Comment\"\r\n" + 
				"}";
		String expected = "{\"Batch\":{\"Success\":false,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Batch Id ( Test Batch 2025 ) already exist.\",\"Level\":3}]}}";
		String actual = CommonMethods.postMethodStringPayloadString(payload, uri, ver);
		Assert.assertTrue(actual.contains(expected));

	}


	
	
}
