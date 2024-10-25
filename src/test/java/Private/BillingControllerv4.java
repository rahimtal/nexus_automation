package Private;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

public class BillingControllerv4 {

	@Test(priority = 1, groups = "billing")
	public static void delBatv4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/billing/delete/BT1231";
		String ver = "4.0";
		String jpath = "./\\TestData\\delBatv4.json";
		String result = CommonMethods.deleteMethod(uri, ver, jpath);
		System.out.println(result.toString());

	}

	@Test(priority = 2, groups = "billing")
	public static void delBatv4Err() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/billing/delete/BT1231";
		String ver = "4.0";
		String jpath = "./\\TestData\\delBatv4Err.json";
		String result = CommonMethods.deleteMethod(uri, ver, jpath);
		System.out.println(result.toString());

	}

	@Test(priority = 7, groups = "Billing")
	public static void billingprintStatementv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		CommonMethods.Bug("CPDEV-16682");
		String uri = "/billing/printStatement";
		String ver = "4.0";
		String payload = "{\n" + "    \"Billing\":{\n" + "        \"ExportToCSV\": true,\n"
				+ "        \"IncludeEbills\": true,\n" + "        \"PrintAction\": 1,\n"
				+ "        \"BatchId\": \"BT1231\",\n" + "        \"Confirm\": {\n"
				+ "            \"RefreshBillPrintData\": true\n" + "        }\n" + "    }\n" + "}";
		String filepath = "./\\TestData\\billingprintStatement.json";
		FileWriter file = new FileWriter(filepath);
		file.write(payload);
		file.close();
		jsonPathEvaluator = CommonMethods.postMethod(filepath, uri, ver);
		Boolean Result = jsonPathEvaluator.get("Billing.Success");
		System.out.println(jsonPathEvaluator.prettyPrint());
		if (!Result) {

			AssertJUnit.fail("Bill Posting Failed \n" + jsonPathEvaluator.prettyPrint());

		}
	}

	@Test(priority = 3, groups = "Billing")
	public static void PostBillingcalculatev4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
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
		FileWriter file = new FileWriter(filepath);
		file.write(payload);
		file.close();
		jsonPathEvaluator = CommonMethods.postMethod(filepath, uri, ver);
		Boolean Result = jsonPathEvaluator.get("Billing.Success");
		System.out.println(Result);
		if (!Result) {

			AssertJUnit.fail("Bill Calculation Failed");

		}
	}

	@Test(priority = 8, groups = "Billing", dependsOnMethods = "billingprintStatementv4")
	public static void postBillingv4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/billing/postingBill";
		String ver = "4.0";
		String payload = "{\n" + "    \"Billing\": {\n" + "        \"BatchId\": \"BT1231\",\n"
				+ "        \"Document\": []\n" + "    }\n" + "}";
		String filepath = "./\\TestData\\postingBillv4.json";
		FileWriter file = new FileWriter(filepath);
		file.write(payload);
		file.close();
		jsonPathEvaluator = CommonMethods.postMethod(filepath, uri, ver);
		Boolean Result = jsonPathEvaluator.get("Billing.Success");
		System.out.println(jsonPathEvaluator.prettyPrint());
		if (!Result) {
			AssertJUnit.fail(jsonPathEvaluator.prettyPrint());

		}
	}

	@Test(priority = 6, groups = "Billing", dependsOnMethods = "PostgenerateEditReportv4")
	public static void postcreateStatementv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/billing/createStatement";
		String ver = "4.0";
		String payload = "{\n" + "    \"Billing\":{\n" + "        \"BatchId\":\"BT1231\",\n"
				+ "        \"Confirm\": {\n" + "            \"IgnoreMiscChargeOrCreditValidation\": false\n"
				+ "        }\n" + "    }\n" + "}";
		String filepath = "./\\TestData\\postBillingStatementv4.json";
		FileWriter file = new FileWriter(filepath);
		file.write(payload);
		file.close();
		jsonPathEvaluator = CommonMethods.postMethod(filepath, uri, ver);
		Boolean Result = jsonPathEvaluator.get("Billing.Success");
		System.out.println(Result);
		if (!Result) {

			AssertJUnit.fail("Bill Posting Failed \n" + jsonPathEvaluator.prettyPrint());

		}
	}

	@Test(priority = 4, groups = "Billing", dependsOnMethods = "PostBillingcalculatev4")
	public static void PostgenerateEditReportv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/billing/generateEditReport";
		String ver = "4.0";
		String payload = "{\n" + "    \"Billing\": {\n" + "        \"BatchId\": \"BT1231\"\n" + "    }\n" + "}";
		String filepath = "./\\TestData\\PostgenerateEditReportv4.json";
		FileWriter file = new FileWriter(filepath);
		file.write(payload);
		file.close();
		jsonPathEvaluator = CommonMethods.postMethod(filepath, uri, ver);
		Boolean Result = jsonPathEvaluator.get("Billing.Success");
		System.out.println(Result);
		if (!Result) {

			AssertJUnit.fail("Bill Calculation Failed");

		}
	}

	public static JsonPath jsonPathEvaluator;

	@Test(priority = 5, groups = "Billing")
	public void getbillprintTemplatePath()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// extent.createTest("Test", "");
		String uri = "/billing/printTemplatePath";
		String ver = "4.0";
		String jpath = "./\\TestData\\printTemplatePathv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		// params.put("ConnectionSequence", "1");
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 10, groups = "Billing", dependsOnMethods = "postBillingv4")
	public void printcsvbillingStatements()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// extent.createTest("Test", "");
		String uri = "/print/csv/billingStatements";
		String ver = "4.0";
		String jpath = "./\\TestData\\printcsvbillingStatementsv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		// params.put("ConnectionSequence", "1");
		String result = CommonMethods.getMethodContains(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 1, groups = "Billing")
	public void TC001_getutilitySetup() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// extent.createTest("Test", "");
		String uri = "/billing/utilitySetup";
		String ver = "4.0";
		String jpath = "./\\TestData\\getutilitySetup_v4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("ConnectionSequence", "1");
		String result = CommonMethods.getMethodContains(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 2, groups = "Billing")
	public void TC002_getbillBatchStatus()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// extent.createTest("Test", "");
		String uri = "/billing/billBatchStatus/BAT012301203";
		String ver = "4.0";
		String jpath = "./\\TestData\\billBatchStatus_v4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		// params.put("ConnectionSequence", "1");
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 11, groups = "Billing")
	public void getbillingtransferProgress()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// extent.createTest("Test", "");
		String uri = "/billing/transfer/progress";
		String ver = "4.0";
		String expected = "{\"Billing\":{\"Success\":true,\"Data\":{\"BatchId\":\"\",\"Summary\":{\"BillingStartDate\":\"2000-04-17\",\"BillingEndDate\":\"2000-04-17\",\"PeriodStartDate\":\"2000-04-01\",\"PeriodEndDate\":\"2000-04-17\",\"TransferStartDate\":\"2000-04-17\",\"TransferEndDate\":\"2000-04-17\"},\"Detail\":[{\"ServiceOrder\":{\"Number\":\"SORD00000000044\",\"BillingDate\":\"2000-04-17\",\"PeriodStartDate\":\"2000-04-01\",\"PeriodEndDate\":\"2000-04-17\",\"LocationId\":\"LOCATION009\",\"CustomerId\":\"CUSTOMER010\",\"TransferDate\":\"2000-04-17\",\"EmployeeId\":\"CARN0001\",\"BillToCustomerDeposit\":{\"Id\":2,\"Description\":\"Refund of Difference\"},\"ThirdPartyDeposit\":{\"Id\":1,\"Description\":\"Transfer\"}}}]},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("ServiceOrderNumber", "SORD00000000044");
		String actual = CommonMethods.getMethodasString(uri, ver, params);// (uri, ver, params, jpath);
		Assert.assertEquals(actual, expected);
	}

}
