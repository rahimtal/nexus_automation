package Public;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.Assert;
import java.io.IOException;
import java.sql.SQLException;

import org.testng.annotations.Test;
import org.testng.Assert;

import com.NexustAPIAutomation.java.CommonMethods;

import io.restassured.path.json.JsonPath;

public class PaymentExtension {

	public static JsonPath jsonPathEvaluator;

	@Test(priority = 1, groups = "Cashering")
	public void postPaymentv2() throws ClassNotFoundException, SQLException, InterruptedException {
		// Still a Bug (2025)
		// CommonMethods.Bug("17867");
		String ver = "2";
		String uri = "/payment";

		String payload = "{\r\n" + "        \"CustomerID\" : \"CUSTOMER007\",\r\n"
				+ "          \"LocationID\": \"ELECWAT001\",\r\n" + "          \"Type\": 2,\r\n"
				+ "          \"PaymentDate\": \"2020-01-15\",\r\n" + "          \"PaymentAmount\": 90.99,\r\n"
				+ "          \"OriginID\": \"NexusApi\",\r\n" + "          \"PaidBy\": 1,\r\n"
				+ "          \"PaidByID\": \"CUSTOMER013\",\r\n"
				+ "          \"AuthorizationCode\": \"AUTH: 11145\"\r\n" + "}";
		String response = CommonMethods.postMethodStringPayloadString(payload, uri, ver);

		Assert.assertTrue(response.contains("{\"result\":[{\"Success\":true,\"Message\":\"PYMT"));

	}

	@Test(priority = 2, groups = "Cashering")
	public void paymentExtensionv2() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/paymentextension";
		String ver = "2";
		String payload = "./\\TestData\\PayementExtension.json";
		jsonPathEvaluator = CommonMethods.postMethod(payload, uri, ver);
		System.out.println(jsonPathEvaluator.get().toString());
		String Result = jsonPathEvaluator.get("PaymentExtension.Messages[0].Info");

		if (Result == "Payment extensions are not allowed. A payment extension already exist or invalid date condition.") {
			Assert.fail();
		} else {
			System.out.println(jsonPathEvaluator.toString());
		}

	}

	// @Test(priority = 3, groups = "Cashering")
	public void postPaymentv2_2() throws ClassNotFoundException, SQLException, InterruptedException {
		// Still a Bug (2025)
		// CommonMethods.Bug("CPDEV-17867");
		String uri = "/payment";
		String ver = "2";
		// String payload = "./\\TestData\\PostPayment2_1.json";
		String payload = "{\r\n" + "        \"CustomerID\" : \"CUSTOMER007\",\r\n"
				+ "          \"LocationID\": \"ELECWAT001\",\r\n" + "          \"Type\": 2,\r\n"
				+ "          \"PaymentDate\": \"2020-01-15\",\r\n" + "          \"PaymentAmount\": 90.99,\r\n"
				+ "          \"OriginID\": \"NexusApi\",\r\n" + "          \"PaidBy\": 1,\r\n"
				+ "          \"PaidByID\": \"CUSTOMER013\",\r\n"
				+ "          \"AuthorizationCode\": \"AUTH: 12354\"\r\n" + "}";
		JsonPath response = CommonMethods.postMethodStringPayload(payload, uri, ver);
		String result = response.toString();
		System.out.println(result);
		Assert.assertTrue(result.contains("{\"result\":[{\"Success\":true,\"Message\":\"PYMT"));

	}

}
