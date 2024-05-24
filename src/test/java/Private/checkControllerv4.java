package Private;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.hc.core5.http.nio.ssl.SecurePortStrategy;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class checkControllerv4 {
	public static String chq;

	public static JsonPath jsonPathEvaluator;

	@Test(priority = 1, groups = "check")
	public static void PostCheckv4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/check";
		String ver = "4.0";
		String nextCheck = getNextCheckv4();
		if (nextCheck != null) {
			String payload = "{\"Check\":{\"DocumentNumber\": \"" + nextCheck
					+ "\",\"CheckDate\":\"2023-07-13\",\"CreatedDate\":\"2023-07-13\",\"CheckAmount\":120.50,\"LocationId\":\"WATER005\",\"CustomerId\":\"500300\",\"IssuedToCustomerId\":\"500200\",\"CheckbookId\":\"FIRST NATIONAL\",\"MiscChargeId\":\"CHEQUE\",\"BatchId\":\"Test Batch\",\"Comment\":\"Example Comment\"}}";
			String filepath = "./\\TestData\\PostCheckv4.json";
			FileWriter file = new FileWriter(filepath);
			file.write(payload);
			file.close();
			jsonPathEvaluator = CommonMethods.postMethod(filepath, uri, ver);
			String Result = jsonPathEvaluator.get("Check.Data.DocumentNumber");
			System.out.println(Result);
			if (Result == null) {

				Assert.fail("Check Posting Failed");

			} else {
				getCheckv4(nextCheck);
			}
		}

	}

	// If an error is thrown then you might need to restore database

	// @Test(priority = 2, groups = "check", dependsOnMethods = "PostCheckv4")
	public static void getCheckv4(String str)
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/check/".concat(str);
		String ver = "4.0";
		String jpath = "./\\TestData\\getCheckv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		// params.put("SearchQuery", "sally");
		String result = CommonMethods.getMethodContains(uri, ver, params, jpath);
		System.out.println("get Check API RESPONSE " + result);
		putCheckv4(str);
		delCheckv4(str);

	}

	// @Test(priority = 3, groups = "check")
	public static void delCheckv4(String str)
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/check/".concat(str);
		String ver = "4.0";
		String jpath = "./\\TestData\\delCheckv4.json";
		String result = CommonMethods.deleteMethod(uri, ver, jpath);
		System.out.println(result.toString());

	}

//	@Test(priority = 4, groups = "check")
	public void getCheckSetupv4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/check/setup";
		String ver = "4.0";
		jsonPathEvaluator = CommonMethods.getMethod(uri, ver);
		System.out.println(jsonPathEvaluator.prettyPrint());
		Boolean Result = jsonPathEvaluator.get("Check.Success");
		if (Result.equals(false)) {
			Assert.fail(null);
		}
	}

	@Test(priority = 5, groups = "check")
	public static String getNextCheckv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/check/nextCheck";
		String ver = "4.0";
		JsonPath result = CommonMethods.getMethod(uri, ver);
		System.out.println(result.getString("Check"));
		String j = result.getString("Check");
		if (!j.contains("CHEQ000000000")) {
			Assert.fail("next Check API is failed");
		}
		if (!j.contains("Success:true")) {
			Assert.fail("next Check API is failed");
		}
		System.out.println(result.getString("Check.Data.NextDocumentNumber"));
		return result.getString("Check.Data.NextDocumentNumber");
	}

	// @Test(priority = 6, groups = "check")
	public static void putCheckv4(String str)
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/check";
		String ver = "4.0";
		// String jpath = "./\\TestData\\putCheckv4.json";
		String params = "{\"Check\": {\"DocumentNumber\": \"" + str
				+ "\",\"CheckDate\": \"2023-07-13\",\"CreatedDate\": \"2023-07-13\",\"CheckAmount\": 120.50,\"CheckbookId\": \"FIRST NATIONAL\",    \"Comment\": \"Example Comment Example\" }}";
		String expected = "{\"Check\":{\"Success\":true,\"Data\":{\"DocumentNumber\":\"" + str
				+ "\"},\"Messages\":[{\"Enabled\":1,\"Info\":\"Updated\",\"Level\":1}]}}";
		String filepath = "./\\TestData\\PUTCheckv4.json";
		FileWriter file = new FileWriter(filepath);
		file.write(expected);
		file.close();
		Response result = CommonMethods.putMethod(uri, ver, params, filepath);

	}

	@Test(priority = 7, groups = "check")
	public static void putChecksendtoAPv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/check/sendtoAP";
		String ver = "4.0";
		String jpath = "./\\TestData\\putChecksendtoAPv4.json";
		String params = new String(Files.readAllBytes(Paths.get(jpath)));
		String expected = "./\\TestData\\putCheckexpectedsendtoApi_v4.json";
		Response result = CommonMethods.putMethod(uri, ver, params, expected);

	}

	@Test(priority = 8, groups = "check")
	public static void postingReceivable4Error()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/check/postingReceivable";
		String ver = "4.0";
		String nextCheck = "CHEQ00000000009";
		if (nextCheck != null) {
			// String payload = "{\"Check\":{\"DocumentNumber\":
			// \""+nextCheck+"\",\"CheckDate\":\"2023-07-13\",\"CreatedDate\":\"2023-07-13\",\"CheckAmount\":120.50,\"LocationId\":\"WATER005\",\"CustomerId\":\"500300\",\"IssuedToCustomerId\":\"500200\",\"CheckbookId\":\"FIRST
			// NATIONAL\",\"MiscChargeId\":\"CHEQUE\",\"BatchId\":\"Test
			// Batch\",\"Comment\":\"Example Comment\"}}";
			String payload = "{\"Check\":{\"DocumentNumber\": \"CHEQ00000000009\"}}";
			String filepath = "./\\TestData\\postingReceivablev4.json";
			FileWriter file = new FileWriter(filepath);
			file.write(payload);
			file.close();
			// jsonPathEvaluator = CommonMethods.postMethodResponseasString(filepath, uri,
			// ver);
			Response response = CommonMethods.postMethodResponseasString(filepath, uri, ver);
			String result = response.asString();
			// result.replaceAll("\\s", "");
			System.out.println("Response " + result);
			String expected = "Cannot insert the value NULL into column 'umTotalTaxes', table 'TWO.dbo.UTX'; column does not allow nulls. INSERT fails";

			if (!result.contains(expected)) {
				Assert.fail();
			}

			expected = "Success\":false,\"Data\":{\"DocumentNumber\":\"CHEQ00000000009\""; // ,"Receivable":[{"ChargeDocument":"MISC00000000391","Posted":false}],"PostingReport":true,"PostingError":true,"ReportList":[{"Name":"Post
																							// Check Refund Edit
																							// List","PrintOrder":1}],"ReportErrorList":[{"Name":"Post
																							// Check Refund Error
																							// List","PrintOrder":1}]},"Messages":[{"Enabled":1,"Info":"Posting
																							// validation warning found.
																							// Refer to posting error
																							// report.","Level":2},{"Enabled":1,"Info":"csmApi_spCheckPostingReceivableCreate
																							// - Cannot insert the value
																							// NULL into column
																							// 'umTotalTaxes', table
																							// 'TWO.dbo.UTX'; column
																							// does not allow nulls.
																							// INSERT
																							// fails.","Level":3}]}}

			if (!result.contains(expected)) {
				Assert.fail();
			}

		}

	}

	@Test(priority = 8, groups = "check")
	public static void postingReceivable4RefundError()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/check/postingReceivable";
		String ver = "4.0";
		String nextCheck = getNextCheckv4();
		if (nextCheck != null) {
			// String payload = "{\"Check\":{\"DocumentNumber\":
			// \""+nextCheck+"\",\"CheckDate\":\"2023-07-13\",\"CreatedDate\":\"2023-07-13\",\"CheckAmount\":120.50,\"LocationId\":\"WATER005\",\"CustomerId\":\"500300\",\"IssuedToCustomerId\":\"500200\",\"CheckbookId\":\"FIRST
			// NATIONAL\",\"MiscChargeId\":\"CHEQUE\",\"BatchId\":\"Test
			// Batch\",\"Comment\":\"Example Comment\"}}";
			String payload = "{\"Check\":{\"DocumentNumber\": \"" + nextCheck + "\"}}";
			String filepath = "./\\TestData\\postingReceivablev4.json";
			FileWriter file = new FileWriter(filepath);
			file.write(payload);
			file.close();
			// jsonPathEvaluator = CommonMethods.postMethodResponseasString(filepath, uri,
			// ver);
			Response response = CommonMethods.postMethodResponseasString(filepath, uri, ver);
			String result = response.asString();
			result.replaceAll("\\s", "");
			System.out.println(result);
			String expected = "{\"Check\":{\"Success\":false,\"Data\":{\"DocumentNumber\":\"CHEQ00000000013\",\"Receivable\":null,\"PostingReport\":false,\"PostingError\":true,\"ReportList\":[],\"ReportErrorList\":[{\"Name\":\"Post Check Refund Error List\",\"PrintOrder\":1}]},\"Messages\":[{\"Enabled\":1,\"Info\":\"Posting validation error found. Refer to posting error report.\",\"Level\":3}]}}";
			System.out.println(expected);
			if (!result.trim().contentEquals(expected.trim())) {
				Assert.fail(result);
			}
		}

	}

	public static void main(String args[])
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		// PostCheckv4();
		postingReceivable4RefundError();

	}

}
