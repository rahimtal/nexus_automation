package com.NexusAPI.Tests;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class Private_Test_ImportController extends BaseClass {

	@Test(priority = 1, groups = "import")
	public void importPayment() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/import/payment";
		String ver = "4.0";

		String payload = "{\r\n" +
				"  \"FileName\": \"PymtImport-FixedDefault.txt\",\r\n" +
				"  \"BatchId\": \"FIXDEFAULT\",\r\n" +
				"  \"DefaultCustomerId\": \"\",\r\n" +
				"  \"DefaultLocationId\": \"\",\r\n" +
				"  \"Payment\": [\r\n" +
				"    {\r\n" +
				"      \"PaymentAmount\": \"00090.00\",\r\n" +
				"      \"CustomerId\": \"500001\",\r\n" +
				"      \"LocationId\": \"100001\",\r\n" +
				"      \"PaymentDate\": \"2000-05-11\",\r\n" +
				"      \"Type\": 0\r\n" +
				"    },\r\n" +
				"    {\r\n" +
				"      \"PaymentAmount\": \"000008.75\",\r\n" +
				"      \"CustomerId\": \"500002\",\r\n" +
				"      \"LocationId\": \"100004\",\r\n" +
				"      \"PaymentDate\": \"2000-05-11\",\r\n" +
				"      \"Type\": 0\r\n" +
				"    },\r\n" +
				"    {\r\n" +
				"      \"PaymentAmount\": \"0000005.25\",\r\n" +
				"      \"CustomerId\": \"500100\",\r\n" +
				"      \"LocationId\": \"100003\",\r\n" +
				"      \"PaymentDate\": \"2000-05-11\",\r\n" +
				"      \"Type\": 0\r\n" +
				"    },\r\n" +
				"    {\r\n" +
				"      \"PaymentAmount\": \"00000070.00\",\r\n" +
				"      \"CustomerId\": \"CUSTOMER021\",\r\n" +
				"      \"LocationId\": \"WATER001\",\r\n" +
				"      \"PaymentDate\": \"2000-05-11\",\r\n" +
				"      \"Type\": 0\r\n" +
				"    },\r\n" +
				"    {\r\n" +
				"      \"PaymentAmount\": \"000006.75\",\r\n" +
				"      \"CustomerId\": \"CUSTOMER014\",\r\n" +
				"      \"LocationId\": \"WATER002\",\r\n" +
				"      \"PaymentDate\": \"2000-05-11\",\r\n" +
				"      \"Type\": 1\r\n" +
				"    },\r\n" +
				"    {\r\n" +
				"      \"PaymentAmount\": \"1000\",\r\n" +
				"      \"CustomerId\": \"CUSTOMER023\",\r\n" +
				"      \"LocationId\": \"WATER023\",\r\n" +
				"      \"PaymentDate\": \"2000-05-11\",\r\n" +
				"      \"Type\": 0\r\n" +
				"    }\r\n" +
				"  ]\r\n" +
				"}";

		HashMap<String, String> params = new HashMap<>();

		System.out.println("POST URI: http://localhost:3000/api/v4" + uri);
		System.out.println("Payload: " + payload);

		// Build and send the request
		Response response = given()
				.headers("Authorization", "Bearer " + CommonMethods.getToken(),
						"Content-Type", "application/json",
						"Connection", "keep-alive",
						"Accept-Encoding", "gzip, deflate, br")
				.body(payload)
				.post("http://localhost:3000/api/v4" + uri);

		String actualResponse = response.asString();
		System.out.println("Actual Response: " + actualResponse);
		System.out.println("Status Code: " + response.getStatusCode());

		// Assert response with JSON path assertions (handles dynamic ProcessHandleId)
		response.then()
				.assertThat()
				.statusCode(200)
				.body("ImportPayment.Success", equalTo(true))
				.body("ImportPayment.Data.TotalRecords", equalTo(6))
				.body("ImportPayment.Data.RecordsWithError", equalTo(4))
				.body("ImportPayment.Data.RecordsWithWarning", equalTo(5))
				.body("ImportPayment.Data.ProcessHandleId", notNullValue()) // Validates it exists but allows any UUID
				.body("ImportPayment.Messages[0].Enabled", equalTo(1))
				.body("ImportPayment.Messages[0].Info", containsString("Import payment validated"))
				.body("ImportPayment.Messages[0].Level", equalTo(1));

		System.out.println("Test Passed!");
	}

}