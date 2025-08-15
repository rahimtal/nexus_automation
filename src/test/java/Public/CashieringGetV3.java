package Public;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.Assert;

import com.NexustAPIAutomation.java.CommonMethods;

import Private.BaseClass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CashieringGetV3 extends BaseClass {

	public static JsonPath jsonPathEvaluator;

	@Test(priority = 1, groups = "Cashering")
	public void TC003_getCashin() throws ClassNotFoundException, SQLException, InterruptedException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/cashiering/cashIn";
		String ver = "3.0";
		String payload = "";
		jsonPathEvaluator = CommonMethods.getMethod(uri, ver);
		System.out.println(jsonPathEvaluator.get().toString());
		Boolean Result = jsonPathEvaluator.get("CashedIn[0].IsCashedIn");

		if (Result == false) {
			Assert.fail();
		}

	}

	@Test(priority = 2, groups = "Cashering", dependsOnMethods = "TC003_getCashin")
	public void TC004_balances() throws ClassNotFoundException, SQLException, InterruptedException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/cashiering/balances/customer006/1999-03-24";
		String ver = "3.0";

		jsonPathEvaluator = CommonMethods.getMethod(uri, ver);
		System.out.println(jsonPathEvaluator.get().toString());
		String Result = (jsonPathEvaluator.get("Cashiering[0].Amount[0].TotalBalanceDue")).toString();

		if (!Result.contains("64.57")) {
			Assert.fail(Result.toString());
		}

		Result = (jsonPathEvaluator.get("Cashiering[0].Amount[0].Current")).toString();

		if (!Result.contains("-115")) {
			Assert.fail(Result.toString());
		}

		Result = (jsonPathEvaluator.get("Cashiering[0].Amount[0].PastDue")).toString();

		if (!Result.contains("179.57")) {
			Assert.fail(Result.toString());
		}

	}

	@Test(priority = 3, groups = "Cashering", dependsOnMethods = "TC004_balances")
	public void TC003_getnextReceipt() throws ClassNotFoundException, SQLException, InterruptedException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/cashiering/receipt/TRREG000001/nextReceipt";
		String ver = "3.0";
		String payload = "";
		jsonPathEvaluator = CommonMethods.getMethod(uri, ver);
		System.out.println(jsonPathEvaluator.get().toString());
		Boolean Result = jsonPathEvaluator.get("Receipt[0].Success");

		if (!Result == true) {
			Assert.fail();
		}

	}

	@Test(priority = 4, groups = "Cashering", dependsOnMethods = "TC003_getnextReceipt")
	public void TC004_getReceipt() throws ClassNotFoundException, SQLException, InterruptedException {
		// CommonMethods.CompanyDBRestore();

		// CommonMethods.Bugs("CPDEV-20951");
		String uri = "/cashiering/receipt/004270412000001";
		String ver = "3.0";
		String payload = "";
		jsonPathEvaluator = CommonMethods.getMethod(uri, ver);
		System.out.println(jsonPathEvaluator.get().toString());
		String Result = jsonPathEvaluator.get("Receipt.ReceiptNumber");

		if (!Result.contentEquals("004270412000001")) {
			Assert.fail(Result);
		}

		Result = jsonPathEvaluator.get("Receipt.PreviousReceiptNumber");

		if (Result.contentEquals("")) {
			Assert.fail(Result);
		}

	}

	@Test(priority = 5, groups = "Cashering", dependsOnMethods = "TC004_getReceipt")
	public void TC005_getRegisterInfo() throws ClassNotFoundException, SQLException, InterruptedException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/cashiering/register/TRREG000001/info";
		String ver = "3.0";
		jsonPathEvaluator = CommonMethods.getMethod(uri, ver);
		System.out.println(jsonPathEvaluator.get().toString());
		String Result = jsonPathEvaluator.get("Register[0].RegId");

		if (!Result.contentEquals("4")) {
			Assert.fail();
		}

		Result = jsonPathEvaluator.get("Register[0].RegisterId");

		if (!Result.contentEquals("TRREG000001")) {
			Assert.fail();
		}

	}

	@Test(priority = 6, groups = "Cashering", dependsOnMethods = "TC005_getRegisterInfo")
	public void TC006_gettransactions() throws ClassNotFoundException, SQLException, InterruptedException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/cashiering/transactions/customer017";
		String ver = "3.0";
		jsonPathEvaluator = CommonMethods.getMethod(uri, ver);
		System.out.println(jsonPathEvaluator.get().toString());
		String Result = jsonPathEvaluator.get("CashieringTransaction[0].CustomerId");

		if (!Result.contentEquals("customer017")) {
			Assert.fail();
		}

	}

	@Test(priority = 7, groups = "Cashering", dependsOnMethods = "TC006_gettransactions")
	public static void TC007_getAutoApply() throws ClassNotFoundException, SQLException, InterruptedException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/cashiering/autoApply";
		String ver = "3.0";
		Map<String, String> responseMap = new HashMap<String, String>();
		responseMap.put("CustomerId", "CUSTOMER010");
		responseMap.put("LocationId", "LOCATION009");
		responseMap.put("ReceiptNumber", "0123555555");
		responseMap.put("ApplyAmount", "50.00");
		responseMap.put("PaymentOrigin", "TEST");
		jsonPathEvaluator = CommonMethods.getMethod(uri, ver, responseMap);

		String Result = jsonPathEvaluator.getJsonObject("CashieringTransaction.CustomerId[0]");

		if (!Result.contentEquals("CUSTOMER010"))
			Assert.fail(Result);
		Result = jsonPathEvaluator.get("CashieringTransaction.Document[0].Number[0]");
		if (!Result.contentEquals("BILL00000000374"))
			Assert.fail(Result);
	}

	@Test(priority = 8, groups = "Cashering")
	public static void getReceiptAuthCode() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/cashiering/receipt/004250815000001";
		String ver = "3.0";

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("ReceiptNumber", "004250815000001");
		String Resp = CommonMethods.getMethodasString(uri, ver, params);
		String Result = "{\"Receipt\":{\"PreviousReceiptNumber\":\"004240724000001\",\"ReceiptNumber\":\"004250815000001\",\"NextReceiptNumber\":\"004250815000002\",\"OriginatingReceiptNumber\":\"004250815000002\",\"Void\":true,\"CustomerId\":\"CUSTOMER014\",\"LocationId\":\"WATER002\",\"PaymentOrigin\":\"TEST\",\"CheckbookId\":\"FIRST NATIONAL\",\"PaidBy\":{\"Type\":1,\"Description\":\"Bill To Customer\",\"Id\":\"\"},\"Cash\":0,\"Check\":{\"Amount\":0,\"Number\":\"\"},\"CreditCard\":{\"Amount\":1342.92,\"IsAuthorized\":false},\"Unapplied\":{\"Amount\":0,\"Account\":\"\",\"LocationId\":\"\"},\"Change\":0,\"Comment\":\"APPROVED 316626\",\"Document\":[{\"Number\":\"BILL00000000412\",\"LocationId\":\"WATER002       \",\"LocationAddress\":\"100 Water\",\"StatementNumber\":300,\"Type\":[{\"Id\":5,\"Description\":\"Regular Bill\"}],\"Date\":\"1999-10-30\",\"DueDate\":\"1999-11-29\",\"Amount\":254.58,\"OutstandingAmount\":94.92,\"ApplyAmount\":94.92,\"Sequence\":1,\"ReferenceDocumentNumber\":\"BUDG00000001500\",\"ReferenceDocumentDate\":\"\",\"ServiceCategory\":[{\"Id\":2,\"Description\":\"Water\"}],\"Attribute\":[{\"ChargeType\":\"\",\"ChargeDescription\":\"\",\"PaymentOrigin\":\"\",\"SupportAuthorization\":0,\"Service\":[{\"Category\":0,\"Description\":\"\",\"Type\":\"WATER\",\"Amount\":254.58,\"OutstandingAmount\":94.92,\"ApplyAmount\":0}]}]},{\"Number\":\"BUDG00000002301\",\"LocationId\":\"WATER002       \",\"LocationAddress\":\"100 Water\",\"StatementNumber\":0,\"Type\":[{\"Id\":7,\"Description\":\"Budget Document\"}],\"Date\":\"2000-02-28\",\"DueDate\":\"2000-02-28\",\"Amount\":208,\"OutstandingAmount\":208,\"ApplyAmount\":208,\"Sequence\":2,\"ReferenceDocumentNumber\":\"BUDG00000002300\",\"ReferenceDocumentDate\":\"2000-01-01\",\"ServiceCategory\":[{\"Id\":2,\"Description\":\"Water\"}],\"Attribute\":[{\"ChargeType\":\"\",\"ChargeDescription\":\"\",\"PaymentOrigin\":\"\",\"SupportAuthorization\":0,\"Service\":[{\"Category\":0,\"Description\":\"\",\"Type\":\"\",\"Amount\":0,\"OutstandingAmount\":0,\"ApplyAmount\":0}]}]},{\"Number\":\"BUDG00000002302\",\"LocationId\":\"WATER002       \",\"LocationAddress\":\"100 Water\",\"StatementNumber\":0,\"Type\":[{\"Id\":7,\"Description\":\"Budget Document\"}],\"Date\":\"2000-04-28\",\"DueDate\":\"2000-04-28\",\"Amount\":208,\"OutstandingAmount\":208,\"ApplyAmount\":208,\"Sequence\":3,\"ReferenceDocumentNumber\":\"BUDG00000002300\",\"ReferenceDocumentDate\":\"2000-01-01\",\"ServiceCategory\":[{\"Id\":2,\"Description\":\"Water\"}],\"Attribute\":[{\"ChargeType\":\"\",\"ChargeDescription\":\"\",\"PaymentOrigin\":\"\",\"SupportAuthorization\":0,\"Service\":[{\"Category\":0,\"Description\":\"\",\"Type\":\"\",\"Amount\":0,\"OutstandingAmount\":0,\"ApplyAmount\":0}]}]},{\"Number\":\"BUDG00000002303\",\"LocationId\":\"WATER002       \",\"LocationAddress\":\"100 Water\",\"StatementNumber\":0,\"Type\":[{\"Id\":7,\"Description\":\"Budget Document\"}],\"Date\":\"2000-06-27\",\"DueDate\":\"2000-06-27\",\"Amount\":208,\"OutstandingAmount\":208,\"ApplyAmount\":208,\"Sequence\":4,\"ReferenceDocumentNumber\":\"BUDG00000002300\",\"ReferenceDocumentDate\":\"2000-01-01\",\"ServiceCategory\":[{\"Id\":2,\"Description\":\"Water\"}],\"Attribute\":[{\"ChargeType\":\"\",\"ChargeDescription\":\"\",\"PaymentOrigin\":\"\",\"SupportAuthorization\":0,\"Service\":[{\"Category\":0,\"Description\":\"\",\"Type\":\"\",\"Amount\":0,\"OutstandingAmount\":0,\"ApplyAmount\":0}]}]},{\"Number\":\"BUDG00000002304\",\"LocationId\":\"WATER002       \",\"LocationAddress\":\"100 Water\",\"StatementNumber\":0,\"Type\":[{\"Id\":7,\"Description\":\"Budget Document\"}],\"Date\":\"2000-08-28\",\"DueDate\":\"2000-08-28\",\"Amount\":208,\"OutstandingAmount\":208,\"ApplyAmount\":208,\"Sequence\":5,\"ReferenceDocumentNumber\":\"BUDG00000002300\",\"ReferenceDocumentDate\":\"2000-01-01\",\"ServiceCategory\":[{\"Id\":2,\"Description\":\"Water\"}],\"Attribute\":[{\"ChargeType\":\"\",\"ChargeDescription\":\"\",\"PaymentOrigin\":\"\",\"SupportAuthorization\":0,\"Service\":[{\"Category\":0,\"Description\":\"\",\"Type\":\"\",\"Amount\":0,\"OutstandingAmount\":0,\"ApplyAmount\":0}]}]},{\"Number\":\"BUDG00000002305\",\"LocationId\":\"WATER002       \",\"LocationAddress\":\"100 Water\",\"StatementNumber\":0,\"Type\":[{\"Id\":7,\"Description\":\"Budget Document\"}],\"Date\":\"2000-10-25\",\"DueDate\":\"2000-10-25\",\"Amount\":208,\"OutstandingAmount\":208,\"ApplyAmount\":208,\"Sequence\":6,\"ReferenceDocumentNumber\":\"BUDG00000002300\",\"ReferenceDocumentDate\":\"2000-01-01\",\"ServiceCategory\":[{\"Id\":2,\"Description\":\"Water\"}],\"Attribute\":[{\"ChargeType\":\"\",\"ChargeDescription\":\"\",\"PaymentOrigin\":\"\",\"SupportAuthorization\":0,\"Service\":[{\"Category\":0,\"Description\":\"\",\"Type\":\"\",\"Amount\":0,\"OutstandingAmount\":0,\"ApplyAmount\":0}]}]},{\"Number\":\"BUDG00000002306\",\"LocationId\":\"WATER002       \",\"LocationAddress\":\"100 Water\",\"StatementNumber\":0,\"Type\":[{\"Id\":7,\"Description\":\"Budget Document\"}],\"Date\":\"2000-12-25\",\"DueDate\":\"2000-12-25\",\"Amount\":208,\"OutstandingAmount\":208,\"ApplyAmount\":208,\"Sequence\":7,\"ReferenceDocumentNumber\":\"BUDG00000002300\",\"ReferenceDocumentDate\":\"2000-01-01\",\"ServiceCategory\":[{\"Id\":2,\"Description\":\"Water\"}],\"Attribute\":[{\"ChargeType\":\"\",\"ChargeDescription\":\"\",\"PaymentOrigin\":\"\",\"SupportAuthorization\":0,\"Service\":[{\"Category\":0,\"Description\":\"\",\"Type\":\"\",\"Amount\":0,\"OutstandingAmount\":0,\"ApplyAmount\":0}]}]}],\"Success\":true,\"Messages\":[{\"Enabled\":0,\"Info\":\"\",\"Level\":0}]}}";

		if (!Result.contentEquals(Resp)) {
			Assert.fail(Result);
		}

	}

	public static void main(String args[]) throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		getReceiptAuthCode();
	}

}
