package Private;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;

import io.restassured.path.json.JsonPath;

public class BillingControllerv4 extends BaseClass {

	// Test 4: Post Billing Calculate
	@Test(priority = 1, groups = "billing")
	public static void PostBillingcalculatev4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// ExtentTest test = extent.createTest("PostBillingcalculatev4");
		// test.log(Status.INFO, "Starting test: PostBillingcalculatev4");

		// CommonMethods.Bug("CPDEV-22133");
		String uri = "/billing/calculate";
		String ver = "4.0";
		String payload = "{\n" + "    \"Billing\": {\n" + "        \"BatchId\": \"BT1231\",\n"
				+ "        \"BillingType\": 1,\n" + "        \"PrepareType\": 1,\n" + "        \"PrepareValue\": [\n"
				+ "            \"LOCATION002\"\n" + "        ],\n" + "        \"PeriodStartDate\": \"2000-06-01\",\n"
				+ "        \"PeriodEndDate\": \"2000-06-30\",\n" + "        \"ReadingDate\": \"2000-06-30\",\n"
				+ "        \"BillingDate\": \"2000-07-01\",\n" + "        \"PowerFactor\": 0,\n"
				+ "        \"BtuPgaFactorDate\": \"2000-01-01\",\n" + "        \"Cycle\": {\n"
				+ "            \"Id\": \"\",\n" + "            \"BillingPeriod\": 0\n" + "        }\n" + "    }\n"
				+ "}";
		String filepath = "./\\TestData\\PostBillingcalculatev4.json";
		// test.log(Status.INFO, "URI: " + uri + ", Version: " + ver);
		// test.log(Status.INFO, "Payload written to file: " + filepath);

		FileWriter file = new FileWriter(filepath);
		file.write(payload);
		file.close();

		// Calling first time for cold start
		CommonMethods.postMethodResponseasString(filepath, uri, ver);
		CommonMethods.Delay(5000);
		// second call
		JsonPath jsonPathEvaluator = CommonMethods.postMethod(filepath, uri, ver);

		Boolean resultFlag = jsonPathEvaluator.get("Billing.Success");
		// test.log(Status.INFO, "Billing.Success: " + resultFlag);
		System.out.println(resultFlag);
		if (!resultFlag) {
			// test.log(Status.FAIL, "Bill Calculation Failed");
			Assert.fail("Bill Calculation Failed");
		} else {
			// test.log(Status.PASS, "Bill Calculation succeeded.");
		}
	}

	// Test 2: Post Generate Edit Report
	@Test(priority = 2, groups = "billing", dependsOnMethods = "PostBillingcalculatev4")
	public static void PostgenerateEditReportv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// ExtentTest test = extent.createTest("PostgenerateEditReportv4");
		// test.log(Status.INFO, "Starting test: PostgenerateEditReportv4");

		String uri = "/billing/generateEditReport";
		String ver = "4.0";
		String payload = "{\n" + "    \"Billing\": {\n" + "        \"BatchId\": \"BT1231\"\n" + "    }\n" + "}";
		String filepath = "./\\TestData\\PostgenerateEditReportv4.json";
		// test.log(Status.INFO, "URI: " + uri + ", Version: " + ver);
		// test.log(Status.INFO, "Payload written to file: " + filepath);

		FileWriter file = new FileWriter(filepath);
		file.write(payload);
		file.close();

		JsonPath jsonPathEvaluator = CommonMethods.postMethod(filepath, uri, ver);
		Boolean resultFlag = jsonPathEvaluator.get("Billing.Success");
		// test.log(Status.INFO, "Billing.Success: " + resultFlag);
		System.out.println(resultFlag);
		if (!resultFlag) {
			// test.log(Status.FAIL, "Bill Calculation Failed");
			Assert.fail("Bill Calculation Failed");
		} else {
			// test.log(Status.PASS, "Bill Calculation succeeded.");
		}
	}

	// Test 3: Post Create Statement
	@Test(priority = 3, groups = "billing", dependsOnMethods = "PostgenerateEditReportv4")
	public static void postcreateStatementv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// ExtentTest test = extent.createTest("postcreateStatementv4");
		// test.log(Status.INFO, "Starting test: postcreateStatementv4");

		String uri = "/billing/createStatement";
		String ver = "4.0";
		String payload = "{\n" + "    \"Billing\":{\n" + "        \"BatchId\":\"BT1231\",\n"
				+ "        \"Confirm\": {\n" + "            \"IgnoreMiscChargeOrCreditValidation\": false\n"
				+ "        }\n" + "    }\n" + "}";
		String filepath = "./\\TestData\\postBillingStatementv4.json";
		// test.log(Status.INFO, "URI: " + uri + ", Version: " + ver);
		// test.log(Status.INFO, "Payload written to file: " + filepath);

		FileWriter file = new FileWriter(filepath);
		file.write(payload);
		file.close();

		JsonPath jsonPathEvaluator = CommonMethods.postMethod(filepath, uri, ver);
		Boolean resultFlag = jsonPathEvaluator.get("Billing.Success");
		// test.log(Status.INFO, "Billing.Success: " + resultFlag);
		System.out.println(resultFlag);
		if (!resultFlag) {
			// test.log(Status.FAIL, "Bill Posting Failed: " +
			// jsonPathEvaluator.prettyPrint());
			Assert.fail("Bill Posting Failed \n" + jsonPathEvaluator.prettyPrint());
		} else {
			// test.log(Status.PASS, "Bill Posting succeeded.");
		}
	}

	// Test 3: Billing Print Statement
	@Test(priority = 4, groups = "billing")
	public static void billingprintStatementv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// ExtentTest test = extent.createTest("billingprintStatementv4");
		// test.log(Status.INFO, "Starting test: billingprintStatementv4");

		// CommonMethods.Bugs("CPDEV-16682");
		// CommonMethods.Bug("CPDEV-21966");
		String uri = "/billing/printStatement";
		String ver = "4.0";
		String payload = "{\n" + "    \"Billing\":{\n" + "        \"ExportToCSV\": true,\n"
				+ "        \"IncludeEbills\": true,\n" + "        \"PrintAction\": 1,\n"
				+ "        \"BatchId\": \"BT1231  \",\n" + "        \"Confirm\": {\n"
				+ "            \"RefreshBillPrintData\": true\n" + "        }\n" + "    }\n" + "}";
		String filepath = "./\\TestData\\billingprintStatement.json";
		// test.log(Status.INFO, "URI: " + uri + ", Version: " + ver);
		// test.log(Status.INFO, "Payload written to file: " + filepath);

		FileWriter file = new FileWriter(filepath);
		file.write(payload);
		file.close();

		JsonPath jsonPathEvaluator = CommonMethods.postMethod(filepath, uri, ver);
		Boolean resultFlag = jsonPathEvaluator.get("Billing.Success");
		// test.log(Status.INFO, "Response: " + jsonPathEvaluator.prettyPrint());
		System.out.println(jsonPathEvaluator.prettyPrint());
		if (!resultFlag) {
			// test.log(Status.FAIL, "Bill Posting Failed: " +
			// jsonPathEvaluator.prettyPrint());
			Assert.fail("Bill Posting Failed \n" + jsonPathEvaluator.prettyPrint());
		} else {
			// test.log(Status.PASS, "Bill Posting succeeded.");
		}
	}

	// Test 5: Post Billing
	@Test(priority = 5, groups = "billing", dependsOnMethods = "billingprintStatementv4")
	public static void postBillingv4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// ExtentTest test = extent.createTest("postBillingv4");
		// test.log(Status.INFO, "Starting test: postBillingv4");

		String uri = "/billing/postingBill";
		String ver = "4.0";
		String payload = "{\n" + "    \"Billing\": {\n" + "        \"BatchId\": \"BT1231\",\n"
				+ "        \"Document\": []\n" + "    }\n" + "}";
		String filepath = "./\\TestData\\postingBillv4.json";
		// test.log(Status.INFO, "URI: " + uri + ", Version: " + ver);
		// test.log(Status.INFO, "Payload written to file: " + filepath);

		FileWriter file = new FileWriter(filepath);
		file.write(payload);
		file.close();

		JsonPath jsonPathEvaluator = CommonMethods.postMethod(filepath, uri, ver);
		Boolean resultFlag = jsonPathEvaluator.get("Billing.Success");
		// test.log(Status.INFO, "Response: " + jsonPathEvaluator.prettyPrint());
		System.out.println(jsonPathEvaluator.prettyPrint());
		if (!resultFlag) {
			// test.log(Status.FAIL, "Billing Post Failed: " +
			// jsonPathEvaluator.prettyPrint());
			Assert.fail(jsonPathEvaluator.prettyPrint());
		} else {
			// test.log(Status.PASS, "Billing Post succeeded.");
		}
		Thread.sleep(5000);
	}
	
	// Test 13: Post Create Statement (Is Final)
		@Test(priority = 7, groups = "billing", dependsOnMethods = "PostgenerateEditReportv4")
		public static void postcreateStatementv4_isfinal()
				throws ClassNotFoundException, SQLException, InterruptedException, IOException {
			// ExtentTest test = extent.createTest("postcreateStatementv4_isfinal");
			// test.log(Status.INFO, "Starting test: postcreateStatementv4_isfinal");

			String uri = "/billing/createStatement";
			String ver = "4.0";
			String payload = "{\r\n" + "    \"Billing\":{\r\n" + "        \"BatchId\":\"BT1231\",\r\n"
					+ "        \"IsFinal\": true,\r\n" + "        \"Confirm\": {\r\n"
					+ "            \"IgnoreMiscChargeOrCreditValidation\": false\r\n" + "        }\r\n" + "    }\r\n" + "}";
			String expected = "{\"Billing\":{\"Success\":true,\"Data\":[{\"BatchId\":\"BT1231\",\"UserDateTime\":";
			// test.log(Status.INFO, "URI: " + uri + ", Version: " + ver);
			// test.log(Status.INFO, "Payload: " + payload);
			// test.log(Status.INFO, "Expected contains: " + expected);

			String actual = CommonMethods.postMethodStringPayloadString(payload, uri, ver);
			// test.log(Status.INFO, "Actual: " + actual);
			Assert.assertTrue(actual.contains(expected));
			// test.log(Status.PASS, "Response contains the expected value.");
		}


	// Test 1: Delete Billing
	@Test(priority = 8, groups = "billing")
	public void delBatv4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// ExtentTest test = extent.createTest("delBatv4");
		// test.log(Status.INFO, "Starting test: delBatv4");

		String uri = "/billing/delete/TEST109";
		String ver = "4.0";
		String jpath = "./\\TestData\\delBatv4.json";
		// test.log(Status.INFO, "URI: " + uri + ", Version: " + ver);
		// test.log(Status.INFO, "JSON Path file: " + jpath);

		try {
			String result = CommonMethods.deleteMethod(uri, ver, jpath);
			// test.log(Status.INFO, "Response: " + result);
			System.out.println(result);
		} catch (Exception e) {
			// test.log(Status.ERROR, "Error occurred: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Test 2: Delete Billing Error
	@Test(priority = 9, groups = "billing")
	public static void delBatv4Err() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// ExtentTest test = extent.createTest("delBatv4Err");
		// test.log(Status.INFO, "Starting test: delBatv4Err");

		String uri = "/billing/delete/BT12312";
		String ver = "4.0";
		String jpath = "./\\TestData\\delBatv4Err.json";
		// test.log(Status.INFO, "URI: " + uri + ", Version: " + ver);
		// test.log(Status.INFO, "JSON Path file: " + jpath);

		try {
			String result = CommonMethods.deleteMethod(uri, ver, jpath);
			// test.log(Status.INFO, "Response: " + result);
			System.out.println(result);
		} catch (Exception e) {
			// test.log(Status.ERROR, "Error occurred: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Test 3: Billing Print Statement --- Look Later
	@Test(priority = 10, groups = "billing")
	public static void billingprintStatementv4_Error()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// ExtentTest test = extent.createTest("billingprintStatementv4");
		// test.log(Status.INFO, "Starting test: billingprintStatementv4");

		// CommonMethods.Bugs("CPDEV-16682");
		// CommonMethods.Bug("CPDEV-21966");
		String uri = "/billing/printStatement";
		String ver = "4.0";
		String payload = "{\n" + "    \"Billing\":{\n" + "        \"ExportToCSV\": true,\n"
				+ "        \"IncludeEbills\": true,\n" + "        \"PrintAction\": 1,\n"
				+ "        \"BatchId\": \"10001  \",\n" + "        \"Confirm\": {\n"
				+ "            \"RefreshBillPrintData\": true\n" + "        }\n" + "    }\n" + "}";
		String filepath = "./\\TestData\\billingprintStatement.json";
		// test.log(Status.INFO, "URI: " + uri + ", Version: " + ver);
		// test.log(Status.INFO, "Payload written to file: " + filepath);

		FileWriter file = new FileWriter(filepath);
		file.write(payload);
		file.close();

		JsonPath jsonPathEvaluator = CommonMethods.postMethod(filepath, uri, ver);
		Boolean resultFlag = jsonPathEvaluator.get("Billing.Success");
		// test.log(Status.INFO, "Response: " + jsonPathEvaluator.prettyPrint());
		System.out.println(jsonPathEvaluator.prettyPrint());
		if (!resultFlag) {
			// test.log(Status.FAIL, "Bill Posting Failed: " +
			// jsonPathEvaluator.prettyPrint());
			Assert.fail("Bill Posting Failed \n" + jsonPathEvaluator.prettyPrint());
		} else {
			// test.log(Status.PASS, "Bill Posting succeeded.");
		}
	}

	/*
	 * // Test 5: Post Billing
	 * 
	 * @Test(priority = 5, groups = "billing", dependsOnMethods =
	 * "billingprintStatementv4") public static void postBillingv4() throws
	 * ClassNotFoundException, SQLException, InterruptedException, IOException { //
	 * ExtentTest test = extent.createTest("postBillingv4"); //
	 * test.log(Status.INFO, "Starting test: postBillingv4");
	 * 
	 * String uri = "/billing/postingBill"; String ver = "4.0"; String payload =
	 * "{\n" + "    \"Billing\": {\n" + "        \"BatchId\": \"BT1231\",\n" +
	 * "        \"Document\": []\n" + "    }\n" + "}"; String filepath =
	 * "./\\TestData\\postingBillv4.json"; // test.log(Status.INFO, "URI: " + uri +
	 * ", Version: " + ver); // test.log(Status.INFO, "Payload written to file: " +
	 * filepath);
	 * 
	 * FileWriter file = new FileWriter(filepath); file.write(payload);
	 * file.close();
	 * 
	 * JsonPath jsonPathEvaluator = CommonMethods.postMethod(filepath, uri, ver);
	 * Boolean resultFlag = jsonPathEvaluator.get("Billing.Success"); //
	 * test.log(Status.INFO, "Response: " + jsonPathEvaluator.prettyPrint());
	 * System.out.println(jsonPathEvaluator.prettyPrint()); if (!resultFlag) { //
	 * test.log(Status.FAIL, "Billing Post Failed: " + //
	 * jsonPathEvaluator.prettyPrint());
	 * Assert.fail(jsonPathEvaluator.prettyPrint()); } else { //
	 * test.log(Status.PASS, "Billing Post succeeded."); } Thread.sleep(5000); }
	 */

	// Shared JsonPath variable
	public static JsonPath jsonPathEvaluator;

	// Test 8: Get Bill Print Template Path
	@Test(priority = 11, groups = "billing")
	public void getbillprintTemplatePath()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// ExtentTest test = extent.createTest("getbillprintTemplatePath");
		// test.log(Status.INFO, "Starting test: getbillprintTemplatePath");

		String uri = "/billing/printTemplatePath";
		String ver = "4.0";
		String jpath = "./\\TestData\\printTemplatePathv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		// test.log(Status.INFO, "URI: " + uri + ", Version: " + ver);
		// test.log(Status.INFO, "JSON Path file: " + jpath);

		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		// test.log(Status.INFO, "Response: " + result);
		System.out.println(result);
	}

	// Test 9: Print CSV Billing Statements
	@Test(priority = 13, groups = "billing", dependsOnMethods = "postBillingv4")
	public void printcsvbillingStatements()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// ExtentTest test = extent.createTest("printcsvbillingStatements");
		// test.log(Status.INFO, "Starting test: printcsvbillingStatements");

		String uri = "/print/csv/billingStatements";
		String ver = "4.0";
		String jpath = "./\\TestData\\printcsvbillingStatementsv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		// test.log(Status.INFO, "URI: " + uri + ", Version: " + ver);
		// test.log(Status.INFO, "JSON Path file: " + jpath);

		String result = CommonMethods.getMethodContains(uri, ver, params, jpath);
		// test.log(Status.INFO, "Response: " + result);
		System.out.println(result);
	}

	// Test 10: TC001 Get Utility Setup
	@Test(priority = 14, groups = "billing")
	public void TC001_getutilitySetup() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// ExtentTest test = extent.createTest("TC001_getutilitySetup");
		// test.log(Status.INFO, "Starting test: TC001_getutilitySetup");

		String uri = "/billing/utilitySetup";
		String ver = "4.0";
		String jpath = "./\\TestData\\getutilitySetup_v4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("ConnectionSequence", "1");
		// test.log(Status.INFO, "URI: " + uri + ", Version: " + ver);
		// test.log(Status.INFO, "JSON Path file: " + jpath);

		String result = CommonMethods.getMethodContains(uri, ver, params, jpath);
		// test.log(Status.INFO, "Response: " + result);
		System.out.println(result);
	}

	// Test 11: TC002 Get Bill Batch Status
	@Test(priority = 15, groups = "billing")
	public void TC002_getbillBatchStatus()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.Bugs("CPDEV-21608");
		// ExtentTest test = extent.createTest("TC002_getbillBatchStatus");
		// test.log(Status.INFO, "Starting test: TC002_getbillBatchStatus");

		String uri = "/billing/billBatchStatus/BAT10123123";
		String ver = "4.0";
		String jpath = "./\\TestData\\billBatchStatus_v4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		// test.log(Status.INFO, "URI: " + uri + ", Version: " + ver);
		// test.log(Status.INFO, "JSON Path file: " + jpath);

		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		// test.log(Status.INFO, "Response: " + result);
		System.out.println(result);
	}

	// Test 12: Get Billing Transfer Progress
	@Test(priority = 16, groups = "billing")
	public void getbillingtransferProgress()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		// CommonMethods.Bugs("CPDEV-21613");
		// ExtentTest test = extent.createTest("getbillingtransferProgress");
		// test.log(Status.INFO, "Starting test: getbillingtransferProgress");

		String uri = "/billing/transfer/progress";
		String ver = "4.0";
		// String expected =
		// "{\"Billing\":{\"Success\":true,\"Data\":{\"BatchId\":\"\",\"Summary\":{\"BillingStartDate\":\"2000-04-17\",\"BillingEndDate\":\"2000-04-17\",\"PeriodStartDate\":\"2000-04-01\",\"PeriodEndDate\":\"2000-04-17\",\"TransferStartDate\":\"2000-04-17\",\"TransferEndDate\":\"2000-04-17\"},\"Detail\":[{\"ServiceOrder\":{\"Number\":\"SORD00000000044\",\"BillingDate\":\"2000-04-17\",\"PeriodStartDate\":\"2000-04-01\",\"PeriodEndDate\":\"2000-04-17\",\"LocationId\":\"LOCATION009\",\"CustomerId\":\"CUSTOMER010\",\"TransferDate\":\"2000-04-17\",\"EmployeeId\":\"CARN0001\",\"BillToCustomerDeposit\":{\"Id\":2,\"Description\":\"Refund
		// of
		// Difference\"},\"ThirdPartyDeposit\":{\"Id\":1,\"Description\":\"Transfer\"}}}]},\"Messages\":[]}}";
		String expected = "{\"Billing\":{\"Success\":true,\"Data\":{\"BatchId\":\"\",\"Summary\":{\"BillingStartDate\":\"2000-04-17\",\"BillingEndDate\":\"2000-04-17\",\"PeriodStartDate\":\"2000-04-01\",\"PeriodEndDate\":\"2000-04-17\",\"TransferStartDate\":\"2000-04-17\",\"TransferEndDate\":\"2000-04-17\"},\"Detail\":[{\"ServiceOrder\":{\"Number\":\"SORD00000000044\",\"BillingDate\":\"2000-04-17\",\"PeriodStartDate\":\"2000-04-01\",\"PeriodEndDate\":\"2000-04-17\",\"LocationId\":\"LOCATION009\",\"CustomerId\":\"CUSTOMER010\",\"TransferDate\":\"2000-04-17\",\"EmployeeId\":\"CARN0001\",\"BillToCustomerDeposit\":{\"Id\":2,\"Description\":\"Refund of Difference\"},\"ThirdPartyDeposit\":{\"Id\":1,\"Description\":\"Transfer\"}},\"AllowTransferWithoutBill\":false}]},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("ServiceOrderNumber", "SORD00000000044");
		// test.log(Status.INFO, "URI: " + uri + ", Version: " + ver);
		// test.log(Status.INFO, "Expected: " + expected);

		String actual = CommonMethods.getMethodasString(uri, ver, params);
		// test.log(Status.INFO, "Actual: " + actual);
		Assert.assertEquals(actual, expected);
	}

	
	// Test 14: Billing Final Calculate
	@Test(priority = 17, groups = "billing")
	public static void billingfinalcalculatev4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// ExtentTest test = extent.createTest("billingfinalcalculatev4");
		// test.log(Status.INFO, "Starting test: billingfinalcalculatev4");

		String uri = "/billing/final/calculate";
		String ver = "4.0";
		String payload = "{\r\n" + "    \"BatchId\": \"BATCHID\",\r\n" + "    \"CheckBatchId\": \"CHECKBATCHID\",\r\n"
				+ "    \"SingleOption\": {\r\n" + "        \"ServiceOrderNumber\": \"SORD00000009024\",\r\n"
				+ "        \"Task\": {\r\n" + "            \"Sequence\": 1000,\r\n"
				+ "            \"EmployeeId\": \"cogsuser\"\r\n" + "        },\r\n"
				+ "        \"PeriodStartDate\": \"2024-11-01\",\r\n" + "        \"PeriodEndDate\": \"2024-12-01\",\r\n"
				+ "        \"BillingDate\": \"2024-12-02\",\r\n" + "        \"BillToCustomerDeposit\": 2,\r\n"
				+ "        \"ThirdPartyDeposit\": 2\r\n" + "    }\r\n" + "}";
		// test.log(Status.INFO, "URI: " + uri + ", Version: " + ver);
		// test.log(Status.INFO, "Payload: " + payload);

		String actual = CommonMethods.postMethodStringPayloadString(payload, uri, ver);
		// test.log(Status.INFO, "Actual: " + actual);
		String expected = "{\"Billing\":{\"Success\":true,\"Data\":{\"BatchId\":\"BATCHID\",\"NumberOfValidTransaction\":1,\"HasTransferErrorInReport\":false,\"List\":[{\"LocationId\":\"TESTLOC018\",\"CustomerId\":\"50000201\",\"ServiceOrderNumber\":\"SORD00000009024\"}],\"TransferErrorList\":[],\"ReportErrorList\":[]},\"Messages\":[]}}";
		Assert.assertEquals(actual, expected);
		// test.log(Status.PASS, "Billing Final Calculation succeeded.");
	}

	// Test 15: Get Batch ID Validate (Invalid)
	@Test(priority = 18, groups = "billing")
	public void getBatchIdValidate() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// ExtentTest test = extent.createTest("getBatchIdValidate");
		// test.log(Status.INFO, "Starting test: getBatchIdValidate");

		String uri = "/billing/batchId/BAT1/validate";
		String ver = "4.0";
		String expected = "{\"Billing\":{\"Success\":false,\"Data\":{\"BatchId\":\"BAT1\",\"isBatchIdValid\":false},\"Messages\":[{\"Enabled\":1,\"Info\":\"The Batch Source for this Batch Id is not BILLING. Select a differenct Batch Id or create a new Batch Id.\",\"Level\":3}]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		// test.log(Status.INFO, "URI: " + uri + ", Version: " + ver);
		// test.log(Status.INFO, "Expected: " + expected);

		String actual = CommonMethods.getMethodasString(uri, ver, params);
		// test.log(Status.INFO, "Actual: " + actual);
		Assert.assertEquals(actual, expected);
		// test.log(Status.PASS, "Batch ID validation returned expected result.");
	}

	// Test 16: Get Batch ID Validate (Valid)
	@Test(priority = 19, groups = "billing")
	public void getBatchIdValidatetrue()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// ExtentTest test = extent.createTest("getBatchIdValidatetrue");
		// test.log(Status.INFO, "Starting test: getBatchIdValidatetrue");

		String uri = "/billing/batchId/BAT10123123/validate";
		String ver = "4.0";
		String expected = "{\"Billing\":{\"Success\":true,\"Data\":{\"BatchId\":\"BAT10123123\",\"isBatchIdValid\":false},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		// test.log(Status.INFO, "URI: " + uri + ", Version: " + ver);
		// test.log(Status.INFO, "Expected: " + expected);

		String actual = CommonMethods.getMethodasString(uri, ver, params);
		// test.log(Status.INFO, "Actual: " + actual);
		Assert.assertEquals(actual, expected);
		// test.log(Status.PASS, "Batch ID validation (true) returned expected
		// result.");
	}

	// Test 17: Delete Billing (Edit List Print)
	@Test(priority = 20, groups = "billing")
	public static void delBatv4_EditListPrint()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// ExtentTest test = extent.createTest("delBatv4_EditListPrint");
		// test.log(Status.INFO, "Starting test: delBatv4_EditListPrint");

		String uri = "/billing/delete/BT1231";
		String ver = "4.0";
		String jpath = "./\\TestData\\delBatv4.json";
		// test.log(Status.INFO, "URI: " + uri + ", Version: " + ver);
		// test.log(Status.INFO, "JSON Path file: " + jpath);

		String result = CommonMethods.deleteMethod(uri, ver, jpath);
		// test.log(Status.INFO, "Response: " + result);
		System.out.println(result);
	}

	// Test 18: Delete Billing (Calculating)
	@Test(priority = 21, groups = "billing")
	public static void delBatv4_calculating()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// ExtentTest test = extent.createTest("delBatv4_calculating");
		// test.log(Status.INFO, "Starting test: delBatv4_calculating");

		String uri = "/billing/delete/BATCHID";
		String ver = "4.0";
		String jpath = "./\\TestData\\delBatv4.json";
		// test.log(Status.INFO, "URI: " + uri + ", Version: " + ver);
		// test.log(Status.INFO, "JSON Path file: " + jpath);

		String result = CommonMethods.deleteMethod(uri, ver, jpath);
		// test.log(Status.INFO, "Response: " + result);
		System.out.println(result);
	}

	// Test 15: Get Batch ID Validate (Invalid)
	@Test(priority = 22, groups = "billing")
	public void billBatchStatus() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// ExtentTest test = extent.createTest("billBatchStatus");
		// test.log(Status.INFO, "Starting test: billBatchStatus");

		String uri = "/billing/billBatchStatus/FINALBILL";
		String ver = "4.0";
		String expected = "{\"BatchStatus\":{\"Success\":true,\"Data\":{\"BatchId\":\"FINALBILL\",\"BatchStatus\":1,\"Route\":[],\"BatchDate\":{\"BillPreparationDate\":\"2027-04-12\",\"BillEditDate\":\"1900-01-01\",\"BillPrintDate\":\"1900-01-01\",\"BillPostDate\":\"1900-01-01\",\"BillCreatedDate\":\"2027-04-12\",\"BillModifiedDate\":\"2027-04-12\",\"BillPeriodStartDate\":\"1900-01-01\",\"BillPeriodEndDate\":\"1900-01-01\",\"BillDate\":\"1900-01-01\",\"ReadingDate\":\"1900-01-01\",\"StatementDate\":\"1900-01-01\",\"BTUDate\":\"1900-01-01\"},\"BatchDescription\":\"\",\"BatchTotal\":66.00000,\"NumberOfTransactions\":1,\"TotalOnHoldOrWithError\":0,\"CycleId\":\"\",\"PrepUserId\":\"sa\",\"EditListUserId\":\"\",\"PrintUserId\":\"\",\"PostUserId\":\"\",\"CheckBookId\":\"\",\"BillType\":0,\"LocationId\":\"\",\"PowerFactor\":0.00000,\"VersionNumber\":0,\"TransferCheckBatchId\":\"CHK041227sa01\"},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		// test.log(Status.INFO, "URI: " + uri + ", Version: " + ver);
		// test.log(Status.INFO, "Expected: " + expected);

		String actual = CommonMethods.getMethodasString(uri, ver, params);
		// test.log(Status.INFO, "Actual: " + actual);
		Assert.assertEquals(actual, expected);
		// test.log(Status.PASS, "Batch ID validation returned expected result.");
	}

	@Test(priority = 23, groups = "billing")
	public void getBilltransferProgress()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// ExtentTest test = extent.createTest("gettransfer");
		// test.log(Status.INFO, "Starting test: gettransfer");

		String uri = "/billing/transfer/progress";
		String ver = "4.0";
		String expected = "{\"Billing\":{\"Success\":true,\"Data\":{\"BatchId\":\"FINALBILL\",\"Summary\":{\"BillingStartDate\":\"2027-04-12\",\"BillingEndDate\":\"2027-04-12\",\"PeriodStartDate\":\"2000-03-01\",\"PeriodEndDate\":\"2027-04-12\",\"TransferStartDate\":\"2027-04-12\",\"TransferEndDate\":\"2027-04-12\"},\"Detail\":[{\"ServiceOrder\":{\"Number\":\"SORD00000009044\",\"BillingDate\":\"2027-04-12\",\"PeriodStartDate\":\"2000-03-01\",\"PeriodEndDate\":\"2027-04-12\",\"LocationId\":\"WATER002\",\"CustomerId\":\"CUSTOMER014\",\"TransferDate\":\"2027-04-12\",\"EmployeeId\":\"ALVA0001\",\"BillToCustomerDeposit\":{\"Id\":2,\"Description\":\"Refund of Difference\"},\"ThirdPartyDeposit\":{\"Id\":0,\"Description\":\"\"}},\"AllowTransferWithoutBill\":true}]},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("ServiceOrderNumber", "SORD00000009044");
		// test.log(Status.INFO, "URI: " + uri + ", Version: " + ver);
		// test.log(Status.INFO, "Expected: " + expected);

		String actual = CommonMethods.getMethodasString(uri, ver, params);
		// test.log(Status.INFO, "Actual: " + actual);
		Assert.assertEquals(actual, expected);
		// test.log(Status.PASS, "Batch ID validation returned expected result.");
	}

	// Test 14: Billing Final posting Final BillTransfer
	@Test(priority = 24, groups = "billing")
	public static void postingFinalBillTransferv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/billing/postingFinalBillTransfer";
		String ver = "4.0";
		String payload = "{\r\n" + "    \"BatchId\": \"BATCH2025\",\r\n" + "    \"ServiceOrderNumber\": \"\",\r\n"
				+ "    \"CheckBatchId\": \"CHK043025sa01\",\r\n" + "    \"SingleOrBatch\": \"BATCH\"\r\n" + "}";
		String actual = CommonMethods.postMethodStringPayloadString(payload, uri, ver);

		String expected = "{\"Billing\":{\"Success\":true,\"Data\":{\"BatchId\":\"BATCH2025\",\"ServiceOrderNumber\":\"\",\"HasPostingError\":false},\"Messages\":[]}}";
		Assert.assertEquals(actual, expected);

	}

	// Test 14: Billing Final posting Final BillTransfer
	@Test(priority = 25, groups = "billing")
	public static void postinggenerateBillingTransferReportv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		// DataBackupRestore.CompanyDBRestore();
		// CommonMethods.Bug(" CPDEV-22127");
		String uri = "/billing/generateBillingTransferReport";
		String ver = "4.0";
		String payload = "{\r\n" + "\r\n" + "    \"BatchId\": \"BATCH2025\",\r\n"
				+ "    \"ServiceOrderNumber\": \"\"\r\n" + "}";
		String actual = CommonMethods.postMethodStringPayloadString(payload, uri, ver);

		String expected = "{\"Billing\":{\"Success\":true,\"Data\":{\"BillReportList\":[{\"ReportName\":\"Bill Post Batch Summary List\",\"ReportDisplayName\":\"Batch Summary List\",\"PrintOrder\":1,\"printEnabled\":false},{\"ReportName\":\"Bill Post Distribution Breakdown Detail\",\"ReportDisplayName\":\"Bill Distribution Breakdown Detail\",\"PrintOrder\":2,\"printEnabled\":false},{\"ReportName\":\"Bill Post Distribution Breakdown Summary\",\"ReportDisplayName\":\"Bill Distribution Breakdown Summary\",\"PrintOrder\":3,\"printEnabled\":false},{\"ReportName\":\"Bill Post and Pymt Distribution Breakdown Summary\",\"ReportDisplayName\":\"Bill & Payment Distribution Breakdown Summary\",\"PrintOrder\":4,\"printEnabled\":false},{\"ReportName\":\"Bill Post-Payment Distribution Breakdown Detail\",\"ReportDisplayName\":\"Payment Distribution Breakdown Detail\",\"PrintOrder\":5,\"printEnabled\":false},{\"ReportName\":\"Bill Post-Payment Distribution Breakdown Summary\",\"ReportDisplayName\":\"Payment Distribution Breakdown Summary\",\"PrintOrder\":6,\"printEnabled\":false},{\"ReportName\":\"Bill Post Distribution Breakdown Account Detail\",\"ReportDisplayName\":\"Bill Distribution Breakdown Account Detail\",\"PrintOrder\":7,\"printEnabled\":false},{\"ReportName\":\"Bill Post Statement Summary\",\"ReportDisplayName\":\"Bill Statement Summary\",\"PrintOrder\":8,\"printEnabled\":false},{\"ReportName\":\"Bill Post Statement Summary Electronic\",\"ReportDisplayName\":\"Bill Statement Summary Electronic\",\"PrintOrder\":9,\"printEnabled\":false}],\"BillErrorReportList\":[{\"ReportName\":\"Bill Post Distribution Error Post List\",\"ReportDisplayName\":\"Bill Post Distribution Error Post List\",\"PrintOrder\":1,\"printEnabled\":false}],\"ErrorReportList\":[{\"ReportName\":\"Billing Error List\",\"ReportDisplayName\":\"Billing Error List\",\"PrintOrder\":1,\"printEnabled\":true}],\"PaymentReportList\":[{\"ReportName\":\"PaymentPostEditList\",\"ReportDisplayName\":\"Payment Edit List\",\"PrintOrder\":1,\"printEnabled\":true},{\"ReportName\":\"PaymentPostDistributionBreakdownSummary\",\"ReportDisplayName\":\"Payment Distribution Breakdown Summary\",\"PrintOrder\":2,\"printEnabled\":true}],\"PaymentReportErrorList\":[{\"ReportName\":\"PaymentPostErrorList\",\"ReportDisplayName\":\"Payment Error List\",\"PrintOrder\":1,\"printEnabled\":true}],\"CheckReportList\":[{\"ReportName\":\"Post Check Refund Edit List\",\"ReportDisplayName\":\"Post Check Refund Edit List\",\"PrintOrder\":1,\"printEnabled\":true}],\"CheckErrorReportList\":[{\"ReportName\":\"Check Refund Error List\",\"ReportDisplayName\":\"Check Refund Error List\",\"PrintOrder\":1,\"printEnabled\":true}]},\"Messages\":[]}}";
		Assert.assertEquals(actual, expected);

	}

}
