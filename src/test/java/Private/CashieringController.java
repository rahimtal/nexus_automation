package Private;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.ConnectionClosedException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;
import com.aventstack.extentreports.Status;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CashieringController extends BaseClass {

	public static JsonPath jsonPathEvaluator;
	public static String nextRecieptNumber;
	public static String ConnectionString;

	@Test(priority = 1, groups = "Cashering")
	public void TC003_1_getCashin() throws ClassNotFoundException, SQLException, InterruptedException {

		String uri = "/cashiering/cashIn";
		String ver = "4.0";
		String payload = "";
		jsonPathEvaluator = CommonMethods.getMethod(uri, ver);
		System.out.println(jsonPathEvaluator.get().toString());
		Boolean Result = jsonPathEvaluator.get("CashedIn[0].IsCashedIn");
		if (Result == false) {
			AssertJUnit.fail();
		}

	}

	@Test(priority = 2, groups = "Cashering", dependsOnMethods = "TC003_1_getCashin")
	public void TC004_balances() throws ClassNotFoundException, SQLException, InterruptedException {

		String uri = "/cashiering/balances/customer006/1999-03-24";
		String ver = "4";

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

	@Test(priority = 3, groups = "Cashering", dependsOnMethods = "TC004_balances")
	public void TC003_getnextReceipt() throws ClassNotFoundException, SQLException, InterruptedException {
		// extent.createTest("Test", "");
		String uri = "/cashiering/receipt/TRREG000001/nextReceipt";
		String ver = "4";
		String payload = "";
		jsonPathEvaluator = CommonMethods.getMethod(uri, ver);
		System.out.println(jsonPathEvaluator.get().toString());
		Boolean Result = jsonPathEvaluator.get("Receipt[0].Success");

		if (!Result == true) {
			AssertJUnit.fail();
			// testStatus(false);
		}

	}

	@Test(priority = 4, groups = "Cashering", dependsOnMethods = "TC003_getnextReceipt")
	public void TC004_getReceipt() throws ClassNotFoundException, SQLException, InterruptedException {
		String uri = "/cashiering/receipt/004220929000004";
		String ver = "4";
		String payload = "";
		jsonPathEvaluator = CommonMethods.getMethod(uri, ver);
		System.out.println(jsonPathEvaluator.get().toString());
		String Result = jsonPathEvaluator.get("Receipt.Data.ReceiptNumber");

		if (!Result.contentEquals("004220929000004")) {
			AssertJUnit.fail();
		}

		Result = jsonPathEvaluator.get("Receipt.Data.PreviousReceiptNumber");

		if (Result.contentEquals("")) {
			AssertJUnit.fail();

		}

	}

	@Test(priority = 5, groups = "Cashering", dependsOnMethods = "TC004_getReceipt")
	public void TC005_getRegisterInfo() throws ClassNotFoundException, SQLException, InterruptedException {
		// extent.createTest("Test", "");
		String uri = "/cashiering/register/TRREG000001/info";
		String ver = "4";
		jsonPathEvaluator = CommonMethods.getMethod(uri, ver);
		System.out.println(jsonPathEvaluator.get().toString());
		String Result = jsonPathEvaluator.get("Register[0].RegId");

		if (!Result.contentEquals("4")) {

			AssertJUnit.fail();
		}

		Result = jsonPathEvaluator.get("Register[0].RegisterId");

		if (!Result.contentEquals("TRREG000001")) {

			AssertJUnit.fail();
		}

	}

	@Test(priority = 6, groups = "Cashering", dependsOnMethods = "TC005_getRegisterInfo")
	public void TC006_gettransactions() throws ClassNotFoundException, SQLException, InterruptedException {
		// extent.createTest("Test", "");
		CommonMethods.Bug("CPDEV-16978");
		String uri = "/cashiering/transactions/customer017";
		String ver = "4";
		Map<String, String> responseMap = new HashMap<String, String>();
		responseMap.put("CustomerId", "customer017");

		jsonPathEvaluator = CommonMethods.getMethod(uri, ver, responseMap);

		System.out.println(jsonPathEvaluator.get().toString());
		String Result = jsonPathEvaluator.get("CashieringTransaction[0].CustomerId");
		if (!Result.contentEquals("customer017")) {
			AssertJUnit.fail();
		}

	}

	@Test(priority = 7, groups = "Cashering", dependsOnMethods = "TC006_gettransactions")
	public void TC007_getAutoApply() throws ClassNotFoundException, SQLException, InterruptedException {
		// extent.createTest("Test", "");
		String uri = "/cashiering/autoApply";
		String ver = "4";
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
			AssertJUnit.fail();
		Result = jsonPathEvaluator.get("CashieringTransaction.Document[0].Number[0]");
		if (!Result.contentEquals("BILL00000000374"))
			AssertJUnit.fail();
	}

	public static void adjustRecieptPre(String recNum) throws ConnectionClosedException, InterruptedException {

		String uri = "/cashiering/receipt/adjust";
		String ver = "4.0";
		String payload = "{ \"Receipt\":{\"ReceiptNumber\" : \"" + recNum
				+ "\", \"Comment\": \" Nexus API adjustment via automation \" } }";
		jsonPathEvaluator = CommonMethods.postMethodStringPayload(payload, uri, ver);
		Boolean Result = jsonPathEvaluator.get("Receipt.Success");
		if (Result != null) {
			if (Result == false) {
				System.out.println("Unable to adjust or reciept already adjust, check data");

			}
			System.out.println("Reciept Adjusted =" + recNum);
			System.out.println(jsonPathEvaluator.prettyPrint());
		}

	}

	@Test(priority = 1, groups = "Cashering")
	public void saveReciept_2_4()
			throws ClassNotFoundException, SQLException, InterruptedException, ConnectionClosedException {
		// CommonMethods.CompanyDBRestore();

		String columnName = "umDocumentNumber";
		String Command1 = "select top 1 umDocumentNumber from TWO.dbo.UMRM102 order by umDocumentNumber desc";
		String Result = "";
		ConnectionString = "jdbc:sqlserver://DESKTOP-QU86F3Q;DB= databaseName=TWO;user=sa;password=cogs;";
		Result = CommonMethods.selectFromDb(Command1, ConnectionString, columnName);
		if (Result != "") {
			System.out.println(Result);
			adjustRecieptPre(Result);
		}

		JsonPath next = CommonMethods.getMethod("/cashiering/receipt/TRREG000001/nextReceipt", "4.0");
		nextRecieptNumber = next.get("Receipt[0].ReceiptNumber");
		Thread.sleep(5000);
		String uri = "/cashiering/receipt";
		String ver = "4.0";
		String payload = "{\"Receipt\":{\"ReceiptNumber\":\"" + nextRecieptNumber
				+ "\",\"OriginatingReceiptNumber\":\"\",\"Void\":false,\"CustomerId\":\"CUSTOMER008\",\"LocationId\":\"LOCATION007\",\"PaymentOrigin\":\"TEST\",\"CheckbookId\":\"FIRST NATIONAL\",\"PaidBy\":{\"Type\":1,\"Description\":\"\",\"Id\":\"\"},\"Cash\":185.42,\"Check\":{\"Amount\":0,\"Number\":\"\"},\"CreditCard\":{\"Amount\":0},\"Unapplied\":{\"Amount\":0,\"Account\":\"\",\"LocationId\":\"\"},\"Change\":0,\"Comment\":\"ThisisacommenttobesavedintocommentinUMRM102\",\"Document\":[{\"Number\":\"MISC00000000317\",\"LocationId\":\"LOCATION007\",\"StatementNumber\":0,\"ApplyAmount\":185.42,\"OutstandingAmount\":0,\"ReferenceDocumentNumber\":\"\"}]}}";
		jsonPathEvaluator = CommonMethods.postMethodStringPayload(payload, uri, ver);
		System.out.println(jsonPathEvaluator.prettyPrint());
		Boolean Result1 = jsonPathEvaluator.get("Receipt.Success");
		if (Result1 == false) {
			Assert.fail();
		} else {
			System.out.println(jsonPathEvaluator.toString());
		}

	}

	@Test(priority = 2, groups = "Cashering", dependsOnMethods = "saveReciept_2_4")
	public void TC002_RecieptAdjustment() throws ClassNotFoundException, SQLException, InterruptedException {
		String uri = "/cashiering/receipt/adjust";
		String ver = "4.0";
		String payload = "{\"Receipt\":{\"ReceiptNumber\":\"" + nextRecieptNumber
				+ "\",\"Comment\":\"NexusAPIadjustment\"}}";
		jsonPathEvaluator = CommonMethods.postMethodStringPayload(payload, uri, ver);
		Boolean Result = jsonPathEvaluator.get("Receipt.Success");
		System.out.println(jsonPathEvaluator.toString());
		if (Result == false) {
			AssertJUnit.fail();
		}

	}
	
	
	@Test(priority = 8, groups = "Cashering")
	public void saveReciept_4_prepaymentExistingCustomer()
			throws ClassNotFoundException, SQLException, InterruptedException, ConnectionClosedException {
		
		String uri = "/cashiering/receipt";
		String ver = "4.0";
		String payload = "{\r\n" + 
				"   \"Receipt\":{\r\n" + 
				"      \"ReceiptNumber\":\"004240724000005\",\r\n" + 
				"      \"OriginatingReceiptNumber\":\"\",\r\n" + 
				"      \"Void\":false,\r\n" + 
				"      \"CustomerId\":\"customer001\",\r\n" + 
				"      \"LocationId\":\"water001\",\r\n" + 
				"      \"PaymentOrigin\":\"API\",\r\n" + 
				"      \"CheckbookId\":\"FIRST NATIONAL\",\r\n" + 
				"      \"PaidBy\":{\r\n" + 
				"         \"Type\":1,\r\n" + 
				"         \"Description\": \"customer001\",\r\n" + 
				"         \"Id\":\"customer001\"\r\n" + 
				"      },\r\n" + 
				"      \"Cash\":10,\r\n" + 
				"      \"Check\":{\r\n" + 
				"         \"Amount\":0,\r\n" + 
				"         \"Number\":\"\"\r\n" + 
				"      },\r\n" + 
				"      \"CreditCard\": {\r\n" + 
				"          \"Amount\": 0\r\n" + 
				"      },\r\n" + 
				"      \"Unapplied\":{\r\n" + 
				"         \"Amount\":10,\r\n" + 
				"         \"Account\":\"000-2115-00\",\r\n" + 
				"         \"LocationId\":\"water001\"\r\n" + 
				"      },\r\n" + 
				"      \"Change\":0,\r\n" + 
				"      \"Comment\":\"This is a comment to be saved into comment in UMRM102\",\r\n" + 
				"      \"Document\":[\r\n" + 
				"         \r\n" + 
				"      ],\r\n" + 
				"      \"ServiceOrder\": {\r\n" + 
				"            \"Id\": \"SORD00000008996\",\r\n" + 
				"            \"Task\": {\r\n" + 
				"                \"Sequence\": \"1000\",\r\n" + 
				"                \"EmployeeId\":\"sa\"\r\n" + 
				"            }\r\n" + 
				"        }\r\n" + 
				"    }\r\n" + 
				"}\r\n" + 
				" ";
		
		String expected = "{\"Receipt\":{\"Success\":true,\"Data\":{\"ReturnValues\":[{\"Name\":\"ReceiptNumber\",\"Value\":\"004240724000005\"}]},\"Messages\":[]}}";
		CommonMethods.postMethodString(payload, uri, ver, expected);
		
	}
	
	
	@Test(priority = 9, groups = "Cashering")
	public void saveReciept_4_prepaymentNewCustomer()
			throws ClassNotFoundException, SQLException, InterruptedException, ConnectionClosedException {
		
		String uri = "/cashiering/receipt";
		String ver = "4.0";
		String payload = "{\r\n" + 
				"   \"Receipt\":{\r\n" + 
				"      \"ReceiptNumber\":\"004240724000009\",\r\n" + 
				"      \"OriginatingReceiptNumber\":\"\",\r\n" + 
				"      \"Void\":false,\r\n" + 
				"      \"CustomerId\":\"SUBCUSTOMER\",\r\n" + 
				"      \"LocationId\":\"MOVEIN\",\r\n" + 
				"      \"PaymentOrigin\":\"API\",\r\n" + 
				"      \"CheckbookId\":\"FIRST NATIONAL\",\r\n" + 
				"      \"PaidBy\":{\r\n" + 
				"         \"Type\":1,\r\n" + 
				"         \"Description\": \"SUBCUSTOMER\",\r\n" + 
				"         \"Id\":\"SUBCUSTOMER\"\r\n" + 
				"      },\r\n" + 
				"      \"Cash\":10,\r\n" + 
				"      \"Check\":{\r\n" + 
				"         \"Amount\":0,\r\n" + 
				"         \"Number\":\"\"\r\n" + 
				"      },\r\n" + 
				"      \"CreditCard\": {\r\n" + 
				"          \"Amount\": 0\r\n" + 
				"      },\r\n" + 
				"      \"Unapplied\":{\r\n" + 
				"         \"Amount\":10,\r\n" + 
				"         \"Account\":\"000-2115-00\",\r\n" + 
				"         \"LocationId\":\"MOVEIN\"\r\n" + 
				"      },\r\n" + 
				"      \"Change\":0,\r\n" + 
				"      \"Comment\":\"This is a comment to be saved into comment in UMRM102\",\r\n" + 
				"      \"Document\":[\r\n" + 
				"         \r\n" + 
				"      ],\r\n" + 
				"      \"ServiceOrder\": {\r\n" + 
				"            \"Id\": \"SORD00000008998\",\r\n" + 
				"            \"Task\": {\r\n" + 
				"                \"Sequence\": \"1000\",\r\n" + 
				"                \"EmployeeId\":\"sa\"\r\n" + 
				"            }\r\n" + 
				"        }\r\n" + 
				"    }\r\n" + 
				"}\r\n" + 
				"";
		
		String expected = "{\"Receipt\":{\"Success\":true,\"Data\":{\"ReturnValues\":[{\"Name\":\"ReceiptNumber\",\"Value\":\"004240724000009\"}]},\"Messages\":[]}}";
		CommonMethods.postMethodString(payload, uri, ver, expected);
		
	}


}
