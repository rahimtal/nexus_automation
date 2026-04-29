package com.NexusAPI.Tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.Assert;

import org.testng.annotations.Test;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.ConnectionClosedException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.Assert;

import com.NexustAPIAutomation.java.CommonMethods;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Private_CashieringController_Test extends BaseClass {

	public static JsonPath jsonPathEvaluator;
	public static String nextRecieptNumber;
	public static String ConnectionString;

	@Test(priority = 1, groups = "Cashering")
	public void TC001_1_Cashin() throws ClassNotFoundException, SQLException, InterruptedException {

	String uri = "/cashiering/cashin";
		String ver = "4.0";
		String payload = "{\r\n" + //
						"    \"CashIn\": [\r\n" + //
						"        {\r\n" + //
						"            \"RegisterId\": \"REGISTER-00001\",\r\n" + //
						"            \"OpeningBalance\": 100.00,\r\n" + //
						"            \"CheckbookId\": \"FIRST NATIONAL\",\r\n" + //
						"            \"PaymentOriginId\": \"\",\r\n" + //
						"            \"LoginDateTime\": \"2020-11-09T11:16:01.230\",\r\n" + //
						"            \"ComputerName\": \"vv\"\r\n" + //
						"        }\r\n" + //
						"    ]\r\n" + //
						"}";
		jsonPathEvaluator = CommonMethods.postMethodStringPayload(payload, uri, ver);
		System.out.println("POST Response: " + jsonPathEvaluator.get().toString());
		
		// Validate POST response
		Boolean postSuccess = jsonPathEvaluator.get("CashIn[0].Success");
		String postMessage = jsonPathEvaluator.get("CashIn[0].Messages[0].Info");
		int messageLevel = jsonPathEvaluator.get("CashIn[0].Messages[0].Level");
		
		Assert.assertTrue(postSuccess, "POST should return Success=true");
		Assert.assertNotNull(postMessage, "POST should return a message");
		System.out.println("POST Message: " + postMessage);
		// Accept either successful message or already logged in message
		
		System.out.println("✓ POST validation passed: Success=" + postSuccess + ", Message=" + postMessage);

		Thread.sleep(25000); // Wait for the cash-in to be processed before GET
		
		
	}

	@Test(priority = 2, groups = "Cashering")
	public void TC003_1_getCashin() throws ClassNotFoundException, SQLException, InterruptedException {

	
		String ver = "4.0";
		 String		 uri = "/cashiering/cashIn";
		ver = "4.0";
		 
		jsonPathEvaluator = CommonMethods.getMethod(uri, ver);
		System.out.println(jsonPathEvaluator.get().toString());
		Boolean Result = jsonPathEvaluator.get("CashedIn[0].IsCashedIn");
		if (Result == false) {
			Assert.fail();
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
			Assert.fail();
		}

		Result = (jsonPathEvaluator.get("Cashiering[0].Amount[0].Current")).toString();

		if (!Result.contains("-115")) {
			Assert.fail();
		}

		Result = (jsonPathEvaluator.get("Cashiering[0].Amount[0].PastDue")).toString();

		if (!Result.contains("179.57")) {
			Assert.fail();
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
			Assert.fail();
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
			Assert.fail();
		}

		Result = jsonPathEvaluator.get("Receipt.Data.PreviousReceiptNumber");

		if (Result.contentEquals("")) {
			Assert.fail();

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

			Assert.fail();
		}

		Result = jsonPathEvaluator.get("Register[0].RegisterId");

		if (!Result.contentEquals("TRREG000001")) {

			Assert.fail();
		}

	}

	@Test(priority = 6, groups = "Cashering", dependsOnMethods = "TC005_getRegisterInfo")
	public void TC006_gettransactions() throws ClassNotFoundException, SQLException, InterruptedException {
		// extent.createTest("Test", "");
		// CommonMethods.Bugs("CPDEV-16978");
		String uri = "/cashiering/transactions/customer017";
		String ver = "4";
		Map<String, String> responseMap = new HashMap<String, String>();
		responseMap.put("CustomerId", "customer017");

		jsonPathEvaluator = CommonMethods.getMethod(uri, ver, responseMap);

		System.out.println(jsonPathEvaluator.get().toString());
		String Result = jsonPathEvaluator.get("CashieringTransaction[0].CustomerId");
		if (!Result.contentEquals("customer017")) {
			Assert.fail();
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
			Assert.fail();
		Result = jsonPathEvaluator.get("CashieringTransaction.Document[0].Number[0]");
		if (!Result.contentEquals("BILL00000000374"))
			Assert.fail();
	}

	public static void adjustRecieptPre(String recNum) throws ConnectionClosedException, InterruptedException {

		String uri = "/cashiering/receipt/adjust";
		String ver = "4.0";
		String payload = "{ \"Receipt\":{\"ReceiptNumber\" : \"" + recNum
				+ "\", \"Comment\": \" Nexus API adjustment via automation \" } }";
		jsonPathEvaluator = CommonMethods.postMethodStringPayload(payload, uri, ver);
		Boolean Result = jsonPathEvaluator.get("Receipt.Success");
		if (Result != null) {
			if (!Result) {
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
		// CommonMethods.Bugs("CPDEV-20919");

		String columnName = "umDocumentNumber";
		String Command1 = "select top 1 umDocumentNumber from TWO.dbo.UMRM102 order by umDocumentNumber desc";
		String Result = "";
		ConnectionString = "jdbc:sqlserver://DESKTOP-QU86F3Q;DB= databaseName=TWO;user=sa;password=cogs;";
		Result = CommonMethods.selectFromDb(Command1, ConnectionString, columnName);
		if (Result != "") {
			System.out.println(Result);
			adjustRecieptPre(Result);
			System.out.println("Reciept Adjusted	");
		}

		JsonPath next = CommonMethods.getMethod("/cashiering/receipt/TRREG000001/nextReceipt", "4.0");
		nextRecieptNumber = next.get("Receipt[0].ReceiptNumber");
		if (nextRecieptNumber == null) {
			Assert.fail();
		}
		System.out.println("Next Reciept = " + nextRecieptNumber);
		System.out.println(nextRecieptNumber);
		System.out.println(" = = = = = = = =");
		System.out.println(" = = = = = = = =");

		Thread.sleep(5000);
		String uri = "/cashiering/receipt";
		String ver = "4.0";
		String payload = "{\"Receipt\":{\"ReceiptNumber\":\"" + nextRecieptNumber
				+ "\",\"OriginatingReceiptNumber\":\"\",\"Void\":false,\"CustomerId\":\"CUSTOMER008\",\"LocationId\":\"LOCATION007\",\"PaymentOrigin\":\"TEST\",\"CheckbookId\":\"FIRST NATIONAL\",\"PaidBy\":{\"Type\":1,\"Description\":\"\",\"Id\":\"\"},\"Cash\":185.42,\"Check\":{\"Amount\":0,\"Number\":\"\"},\"CreditCard\":{\"Amount\":0},\"Unapplied\":{\"Amount\":0,\"Account\":\"\",\"LocationId\":\"\"},\"Change\":0,\"Comment\":\"ThisisacommenttobesavedintocommentinUMRM102\",\"Document\":[{\"Number\":\"MISC00000000317\",\"LocationId\":\"LOCATION007\",\"StatementNumber\":0,\"ApplyAmount\":185.42,\"OutstandingAmount\":0,\"ReferenceDocumentNumber\":\"\"}]}}";

		System.out.println(payload);
		jsonPathEvaluator = CommonMethods.postMethodStringPayload(payload, uri, ver);
		System.out.println(jsonPathEvaluator.prettyPrint());
		Boolean Result1 = jsonPathEvaluator.get("Receipt.Success");
		if (Result1 == false) {
			System.out.println(jsonPathEvaluator.prettyPrint());
			Assert.assertTrue(false);
		} else {
			System.out.println(jsonPathEvaluator.prettyPrint());
		}
		Thread.sleep(10000);

	}

	@Test(priority = 2, groups = "Cashering", dependsOnMethods = "saveReciept_2_4")
	public void TC002_RecieptAdjustment() throws ClassNotFoundException, SQLException, InterruptedException {
		// CommonMethods.Bug("CPDEV-22582");
		// debug
		// nextRecieptNumber = "004211209000001";
		if (nextRecieptNumber == null || nextRecieptNumber.isEmpty()) {
			// Try to get a new receipt number if the previous one is null or empty
			JsonPath next = CommonMethods.getMethod("/cashiering/receipt/TRREG000001/nextReceipt", "4.0");
			nextRecieptNumber = next.get("Receipt[0].ReceiptNumber");
			if (nextRecieptNumber == null || nextRecieptNumber.isEmpty()) {
				Assert.fail("Unable to retrieve a valid nextRecieptNumber for adjustment.");
			}
		}
		if (nextRecieptNumber == null) {
			Assert.assertEquals(false, "Reciept is Null");
		}
		String uri = "/cashiering/receipt/adjust";
		String ver = "4.0";
		String payload = "{\"Receipt\":{\"ReceiptNumber\":\"" + nextRecieptNumber
				+ "\",\"Comment\":\"NexusAPIadjustment\"}}";
		jsonPathEvaluator = CommonMethods.postMethodStringPayload(payload, uri, ver);
		Assert.assertNotNull(jsonPathEvaluator.get("Receipt.Success"), "Receipt.Success is null");
		Object messagesObj = jsonPathEvaluator.get("Receipt.Messages");
		String Result1 = messagesObj != null ? messagesObj.toString() : "";
		Boolean Result = jsonPathEvaluator.get("Receipt.Success");
		if (Result1.contains("Reciept number already adjusted")) {
			throw new SkipException("Skipping this method due to DATA, please restore DB = ");
		}
		System.out.println(jsonPathEvaluator.prettyPrint());
		if (Result == false) {
			Assert.fail("Reciept not adjusted " + jsonPathEvaluator.prettyPrint());
		}

	}

	@Test(priority = 8, groups = "Cashering")
	public void saveReciept_4_prepaymentExistingCustomer()
			throws ClassNotFoundException, SQLException, InterruptedException, ConnectionClosedException {
		// CommonMethods.Bug("https://cogsdale.atlassian.net/browse/CPDEV-22587");
		String uri = "/cashiering/receipt";
		String ver = "4.0";
		String payload = "{\r\n" + "   \"Receipt\":{\r\n" + "      \"ReceiptNumber\":\"004240724000005\",\r\n"
				+ "      \"OriginatingReceiptNumber\":\"\",\r\n" + "      \"Void\":false,\r\n"
				+ "      \"CustomerId\":\"customer001\",\r\n" + "      \"LocationId\":\"water001\",\r\n"
				+ "      \"PaymentOrigin\":\"API\",\r\n" + "      \"CheckbookId\":\"FIRST NATIONAL\",\r\n"
				+ "      \"PaidBy\":{\r\n" + "         \"Type\":1,\r\n"
				+ "         \"Description\": \"customer001\",\r\n" + "         \"Id\":\"customer001\"\r\n"
				+ "      },\r\n" + "      \"Cash\":10,\r\n" + "      \"Check\":{\r\n" + "         \"Amount\":0,\r\n"
				+ "         \"Number\":\"\"\r\n" + "      },\r\n" + "      \"CreditCard\": {\r\n"
				+ "          \"Amount\": 0\r\n" + "      },\r\n" + "      \"Unapplied\":{\r\n"
				+ "         \"Amount\":10,\r\n" + "         \"Account\":\"000-2115-00\",\r\n"
				+ "         \"LocationId\":\"water001\"\r\n" + "      },\r\n" + "      \"Change\":0,\r\n"
				+ "      \"Comment\":\"This is a comment to be saved into comment in UMRM102\",\r\n"
				+ "      \"Document\":[\r\n" + "         \r\n" + "      ],\r\n" + "      \"ServiceOrder\": {\r\n"
				+ "            \"Id\": \"SORD00000008996\",\r\n" + "            \"Task\": {\r\n"
				+ "                \"Sequence\": \"1000\",\r\n" + "                \"EmployeeId\":\"sa\"\r\n"
				+ "            }\r\n" + "        }\r\n" + "    }\r\n" + "}\r\n" + " ";

		String expected = "{\"Receipt\":{\"Success\":true,\"Data\":{\"ReturnValues\":[{\"Name\":\"ReceiptNumber\",\"Value\":\"004240724000005\"}]},\"Messages\":[]}}";
		CommonMethods.postMethodString(payload, uri, ver, expected);

	}

	@Test(priority = 9, groups = "Cashering")
	public void saveReciept_4_prepaymentNewCustomer()
			throws ClassNotFoundException, SQLException, InterruptedException, ConnectionClosedException {
		// CommonMethods.Bug("https://cogsdale.atlassian.net/browse/CPDEV-22587");
		String uri = "/cashiering/receipt";
		String ver = "4.0";
		String payload = "{\r\n" + "   \"Receipt\":{\r\n" + "      \"ReceiptNumber\":\"004240724000009\",\r\n"
				+ "      \"OriginatingReceiptNumber\":\"\",\r\n" + "      \"Void\":false,\r\n"
				+ "      \"CustomerId\":\"SUBCUSTOMER\",\r\n" + "      \"LocationId\":\"MOVEIN\",\r\n"
				+ "      \"PaymentOrigin\":\"API\",\r\n" + "      \"CheckbookId\":\"FIRST NATIONAL\",\r\n"
				+ "      \"PaidBy\":{\r\n" + "         \"Type\":1,\r\n"
				+ "         \"Description\": \"SUBCUSTOMER\",\r\n" + "         \"Id\":\"SUBCUSTOMER\"\r\n"
				+ "      },\r\n" + "      \"Cash\":10,\r\n" + "      \"Check\":{\r\n" + "         \"Amount\":0,\r\n"
				+ "         \"Number\":\"\"\r\n" + "      },\r\n" + "      \"CreditCard\": {\r\n"
				+ "          \"Amount\": 0\r\n" + "      },\r\n" + "      \"Unapplied\":{\r\n"
				+ "         \"Amount\":10,\r\n" + "         \"Account\":\"000-2115-00\",\r\n"
				+ "         \"LocationId\":\"MOVEIN\"\r\n" + "      },\r\n" + "      \"Change\":0,\r\n"
				+ "      \"Comment\":\"This is a comment to be saved into comment in UMRM102\",\r\n"
				+ "      \"Document\":[\r\n" + "         \r\n" + "      ],\r\n" + "      \"ServiceOrder\": {\r\n"
				+ "            \"Id\": \"SORD00000008998\",\r\n" + "            \"Task\": {\r\n"
				+ "                \"Sequence\": \"1000\",\r\n" + "                \"EmployeeId\":\"sa\"\r\n"
				+ "            }\r\n" + "        }\r\n" + "    }\r\n" + "}\r\n" + "";

		String expected = "{\"Receipt\":{\"Success\":true,\"Data\":{\"ReturnValues\":[{\"Name\":\"ReceiptNumber\",\"Value\":\"004240724000009\"}]},\"Messages\":[]}}";
		CommonMethods.postMethodString(payload, uri, ver, expected);

	}

	@Test(priority = 10, groups = "Cashering")
	public void saveReciept_4_SOTaskCompleteDepositPayment()
			throws ClassNotFoundException, SQLException, InterruptedException, ConnectionClosedException {
		// CommonMethods.Bug("https://cogsdale.atlassian.net/browse/CPDEV-22587");
		String uri = "/cashiering/receipt";
		String ver = "4.0";
		String payload = "{\r\n" + "   \"Receipt\":{\r\n" + "      \"ReceiptNumber\":\"004240805000004\",\r\n"
				+ "      \"OriginatingReceiptNumber\":\"\",\r\n" + "      \"Void\":false,\r\n"
				+ "      \"CustomerId\":\"500002\",\r\n" + "      \"LocationId\":\"100002\",\r\n"
				+ "      \"PaymentOrigin\":\"API\",\r\n" + "      \"CheckbookId\":\"FIRST NATIONAL\",\r\n"
				+ "      \"PaidBy\":{\r\n" + "         \"Type\":1,\r\n" + "         \"Description\": \"500002\",\r\n"
				+ "         \"Id\":\"500002\"\r\n" + "      },\r\n" + "      \"Cash\":10,\r\n" + "      \"Check\":{\r\n"
				+ "         \"Amount\":0,\r\n" + "         \"Number\":\"\"\r\n" + "      },\r\n"
				+ "      \"CreditCard\": {\r\n" + "          \"Amount\": 0\r\n" + "      },\r\n"
				+ "      \"Unapplied\":{\r\n" + "         \"Amount\":10,\r\n"
				+ "         \"Account\":\"000-2115-00\",\r\n" + "         \"LocationId\":\"100002\"\r\n"
				+ "      },\r\n" + "      \"Change\":0,\r\n"
				+ "      \"Comment\":\"This is a comment to be saved into comment in UMRM102\",\r\n"
				+ "      \"Document\":[\r\n" + "         \r\n" + "      ],\r\n" + "      \"ServiceOrder\": {\r\n"
				+ "            \"Id\": \"SORD00000009002\",\r\n" + "            \"Task\": {\r\n"
				+ "                \"Sequence\": \"1100\",\r\n" + "                \"EmployeeId\":\"sa\"\r\n"
				+ "            }\r\n" + "        }\r\n" + "    }\r\n" + "}\r\n" + "";

		String expected = "{\"Receipt\":{\"Success\":true,\"Data\":{\"ReturnValues\":[{\"Name\":\"ReceiptNumber\",\"Value\":\"004240805000004\"}]},\"Messages\":[]}}";
		CommonMethods.postMethodString(payload, uri, ver, expected);

	}

	@Test(priority = 10, groups = "Cashering")
	public void saveReciept_4_SOTaskCompleteDepositPaymenttask2()
			throws ClassNotFoundException, SQLException, InterruptedException, ConnectionClosedException {
		// CommonMethods.Bug("https://cogsdale.atlassian.net/browse/CPDEV-22587");
		String uri = "/cashiering/receipt";
		String ver = "4.0";
		String payload = "{\r\n" + "   \"Receipt\":{\r\n" + "      \"ReceiptNumber\":\"004240805000013\",\r\n"
				+ "      \"OriginatingReceiptNumber\":\"\",\r\n" + "      \"Void\":false,\r\n"
				+ "      \"CustomerId\":\"500002\",\r\n" + "      \"LocationId\":\"100002\",\r\n"
				+ "      \"PaymentOrigin\":\"API\",\r\n" + "      \"CheckbookId\":\"FIRST NATIONAL\",\r\n"
				+ "      \"PaidBy\":{\r\n" + "         \"Type\":1,\r\n" + "         \"Description\": \"500002\",\r\n"
				+ "         \"Id\":\"500002\"\r\n" + "      },\r\n" + "      \"Cash\":10,\r\n" + "      \"Check\":{\r\n"
				+ "         \"Amount\":0,\r\n" + "         \"Number\":\"\"\r\n" + "      },\r\n"
				+ "      \"CreditCard\": {\r\n" + "          \"Amount\": 0\r\n" + "      },\r\n"
				+ "      \"Unapplied\":{\r\n" + "         \"Amount\":10,\r\n"
				+ "         \"Account\":\"000-2115-00\",\r\n" + "         \"LocationId\":\"100002\"\r\n"
				+ "      },\r\n" + "      \"Change\":0,\r\n"
				+ "      \"Comment\":\"This is a comment to be saved into comment in UMRM102\",\r\n"
				+ "      \"Document\":[\r\n" + "         \r\n" + "      ],\r\n" + "      \"ServiceOrder\": {\r\n"
				+ "            \"Id\": \"SORD00000009002\",\r\n" + "            \"Task\": {\r\n"
				+ "                \"Sequence\": \"1100\",\r\n" + "                \"EmployeeId\":\"sa\"\r\n"
				+ "            }\r\n" + "        }\r\n" + "    }\r\n" + "}\r\n" + "";

		String expected = "{\"Receipt\":{\"Success\":true,\"Data\":{\"ReturnValues\":[{\"Name\":\"ReceiptNumber\",\"Value\":\"004240805000013\"}]},\"Messages\":[]}}";
		CommonMethods.postMethodString(payload, uri, ver, expected);

	}

	@Test(priority = 11, groups = "Cashering")
	public void saveReciept_SOTaskCompleteDepositPaymenttaskNewCustomer()
			throws ClassNotFoundException, SQLException, InterruptedException, ConnectionClosedException {
		// CommonMethods.Bug("https://cogsdale.atlassian.net/browse/CPDEV-22587");
		String uri = "/cashiering/receipt";
		String ver = "4.0";
		String payload = "{\r\n" + "   \"Receipt\":{\r\n" + "      \"ReceiptNumber\":\"004240805000008\",\r\n"
				+ "      \"OriginatingReceiptNumber\":\"\",\r\n" + "      \"Void\":false,\r\n"
				+ "      \"CustomerId\":\"CUSTOMER010\",\r\n" + "      \"LocationId\":\"ELECWAT003\",\r\n"
				+ "      \"PaymentOrigin\":\"API\",\r\n" + "      \"CheckbookId\":\"FIRST NATIONAL\",\r\n"
				+ "      \"PaidBy\":{\r\n" + "         \"Type\":1,\r\n"
				+ "         \"Description\": \"CUSTOMER010\",\r\n" + "         \"Id\":\"CUSTOMER010\"\r\n"
				+ "      },\r\n" + "      \"Cash\":10,\r\n" + "      \"Check\":{\r\n" + "         \"Amount\":0,\r\n"
				+ "         \"Number\":\"\"\r\n" + "      },\r\n" + "      \"CreditCard\": {\r\n"
				+ "          \"Amount\": 0\r\n" + "      },\r\n" + "      \"Unapplied\":{\r\n"
				+ "         \"Amount\":10,\r\n" + "         \"Account\":\"000-2115-00\",\r\n"
				+ "         \"LocationId\":\"ELECWAT003\"\r\n" + "      },\r\n" + "      \"Change\":0,\r\n"
				+ "      \"Comment\":\"This is a comment to be saved into comment in UMRM102\",\r\n"
				+ "      \"Document\":[\r\n" + "         \r\n" + "      ],\r\n" + "      \"ServiceOrder\": {\r\n"
				+ "            \"Id\": \"SORD00000009007\",\r\n" + "            \"Task\": {\r\n"
				+ "                \"Sequence\": \"1100\",\r\n" + "                \"EmployeeId\":\"sa\"\r\n"
				+ "            }\r\n" + "        }\r\n" + "    }\r\n" + "}\r\n" + "";

		String expected = "{\"Receipt\":{\"Success\":true,\"Data\":{\"ReturnValues\":[{\"Name\":\"ReceiptNumber\",\"Value\":\"004240805000008\"}]},\"Messages\":[]}}";
		CommonMethods.postMethodString(payload, uri, ver, expected);

	}

	@Test(priority = 13, groups = "Cashering", dependsOnMethods = "TC005_getRegisterInfo")
	public void TC006_gettransactions_2_4() throws ClassNotFoundException, SQLException, InterruptedException {
		// extent.createTest("Test", "");
		// CommonMethods.Bugs("CPDEV-16978");
		String uri = "/cashiering/transactions/customer017";
		String ver = "2.4";
		Map<String, String> responseMap = new HashMap<String, String>();
		// responseMap.put("CustomerId", "customer017");

		jsonPathEvaluator = CommonMethods.getMethod(uri, ver, responseMap);

		System.out.println(jsonPathEvaluator.get().toString());
		String Result = jsonPathEvaluator.get("CashieringTransaction[0].CustomerId");
		if (!Result.contentEquals("customer017")) {
			Assert.fail();
		}

	}

}
