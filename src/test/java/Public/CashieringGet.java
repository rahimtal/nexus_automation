package Public;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;
import com.NexustAPIAutomation.java.Retry;
import com.aventstack.extentreports.Status;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CashieringGet extends BaseClass {

	public static JsonPath jsonPathEvaluator;

	@Test(priority = 1, groups = "Cashering", retryAnalyzer = Retry.class)
	public void TC003_getCashin() throws ClassNotFoundException, SQLException, InterruptedException {
		// extent.createTest("Test", "");
		String uri = "/cashiering/cashIn";
		String ver = "2.4";
		String payload = "";
		// extent.createTest("Test", "");
		jsonPathEvaluator = CommonMethods.getMethod(uri, ver);
		System.out.println(jsonPathEvaluator.get().toString());
		Boolean Result = jsonPathEvaluator.get("CashedIn[0].IsCashedIn");
		if (Result == false) {
			AssertJUnit.fail();
		}

	}

	@Test(priority = 1, groups = "Cashering", retryAnalyzer = Retry.class)
	public void TC003_1_getCashin() throws ClassNotFoundException, SQLException, InterruptedException {

		String uri = "/cashiering/cashIn";
		String ver = "2.4";
		String payload = "";
		jsonPathEvaluator = CommonMethods.getMethod(uri, ver);
		System.out.println(jsonPathEvaluator.get().toString());
		Boolean Result = jsonPathEvaluator.get("CashedIn[0].IsCashedIn");
		if (Result == false) {
			AssertJUnit.fail();
		}

	}

	@Test(priority = 2, groups = "Cashering", retryAnalyzer = Retry.class, dependsOnMethods = "TC003_getCashin")
	public void TC004_balances() throws ClassNotFoundException, SQLException, InterruptedException {

		String uri = "/cashiering/balances/customer006/1999-03-24";
		String ver = "2.4";

		jsonPathEvaluator = CommonMethods.getMethod(uri, ver);
		System.out.println(jsonPathEvaluator.get().toString());
		String Result = (jsonPathEvaluator.get("Cashiering[0].Amount[0].TotalBalanceDue")).toString();

		if (!Result.contains("64.57")) {
			AssertJUnit.fail();
		}

		Result = (jsonPathEvaluator.get("Cashiering[0].Amount[0].Current")).toString();

		if (!Result.contains("-115")) {
			AssertJUnit.fail();
		}

		Result = (jsonPathEvaluator.get("Cashiering[0].Amount[0].PastDue")).toString();

		if (!Result.contains("179.57")) {
			AssertJUnit.fail();
		}

	}

	@Test(priority = 3, groups = "Cashering", retryAnalyzer = Retry.class, dependsOnMethods = "TC004_balances")
	public void TC003_getnextReceipt() throws ClassNotFoundException, SQLException, InterruptedException {
		// extent.createTest("Test", "");
		String uri = "/cashiering/receipt/TRREG000001/nextReceipt";
		String ver = "2.4";
		String payload = "";
		jsonPathEvaluator = CommonMethods.getMethod(uri, ver);
		System.out.println(jsonPathEvaluator.get().toString());
		Boolean Result = jsonPathEvaluator.get("Receipt[0].Success");

		if (!Result == true) {
			AssertJUnit.fail();
			// testStatus(false);
		}

	}

	@Test(priority = 4, groups = "Cashering", retryAnalyzer = Retry.class, dependsOnMethods = "TC003_getnextReceipt")
	public void TC004_getReceipt() throws ClassNotFoundException, SQLException, InterruptedException {
		String uri = "/cashiering/receipt/004270412000001";
		String ver = "2.4";
		String payload = "";
		jsonPathEvaluator = CommonMethods.getMethod(uri, ver);
		System.out.println(jsonPathEvaluator.get().toString());
		String Result = jsonPathEvaluator.get("Receipt.ReceiptNumber");

		if (!Result.contentEquals("004270412000001")) {
			testStatus(false);
		}

		Result = jsonPathEvaluator.get("Receipt.PreviousReceiptNumber");

		if (Result.contentEquals("")) {
			testStatus(false);

		}

	}

	@Test(priority = 5, groups = "Cashering", retryAnalyzer = Retry.class, dependsOnMethods = "TC004_getReceipt")
	public void TC005_getRegisterInfo() throws ClassNotFoundException, SQLException, InterruptedException {
		// extent.createTest("Test", "");
		String uri = "/cashiering/register/TRREG000001/info";
		String ver = "2.4";
		jsonPathEvaluator = CommonMethods.getMethod(uri, ver);
		System.out.println(jsonPathEvaluator.get().toString());
		String Result = jsonPathEvaluator.get("Register[0].RegId");

		if (!Result.contentEquals("4")) {
			testStatus(false);
			AssertJUnit.fail();
		}

		Result = jsonPathEvaluator.get("Register[0].RegisterId");

		if (!Result.contentEquals("TRREG000001")) {
			testStatus(false);
			AssertJUnit.fail();
		}

	}

	@Test(priority = 6, groups = "Cashering", retryAnalyzer = Retry.class, dependsOnMethods = "TC005_getRegisterInfo")
	public void TC006_gettransactions() throws ClassNotFoundException, SQLException, InterruptedException {
		// extent.createTest("Test", "");
		CommonMethods.Bug("CPDEV-16978");
		String uri = "/cashiering/transactions/customer017";
		String ver = "2.4";
		Map<String, String> responseMap = new HashMap<String, String>();
		responseMap.put("CustomerId", "customer017");

		jsonPathEvaluator = CommonMethods.getMethod(uri, ver, responseMap);

		System.out.println(jsonPathEvaluator.get().toString());
		String Result = jsonPathEvaluator.get("CashieringTransaction[0].CustomerId");
		if (!Result.contentEquals("customer017")) {
			testStatus(false);
		}

	}

	@Test(priority = 7, groups = "Cashering", retryAnalyzer = Retry.class, dependsOnMethods = "TC006_gettransactions")
	public void TC007_getAutoApply() throws ClassNotFoundException, SQLException, InterruptedException {
		// extent.createTest("Test", "");
		String uri = "/cashiering/autoApply";
		String ver = "2.4";
		Map<String, String> responseMap = new HashMap<String, String>();
		responseMap.put("CustomerId", "CUSTOMER010");
		responseMap.put("LocationId", "LOCATION009");
		responseMap.put("ReceiptNumber", "0123555555");
		responseMap.put("ApplyAmount", "50.00");
		responseMap.put("PaymentOrigin", "TEST");
		jsonPathEvaluator = CommonMethods.getMethod(uri, ver, responseMap);
		System.out.println(jsonPathEvaluator.get().toString());
		// String Result = jsonPathEvaluator.get("CashieringTransaction.CustomerId");
		String Result = jsonPathEvaluator.getJsonObject("CashieringTransaction.CustomerId[0]");

		if (!Result.contentEquals("CUSTOMER010"))
			testStatus(false);
		Result = jsonPathEvaluator.get("CashieringTransaction.Document[0].Number[0]");
		if (!Result.contentEquals("BILL00000000374"))
			testStatus(false);
	}

	//@Test
	void testStatus(boolean Result) {
		if (Result == false) {
			test.log(Status.FAIL, "Test Failed");
			AssertJUnit.fail();
		}
		test.log(Status.PASS, "Test Passed");
	}

}
