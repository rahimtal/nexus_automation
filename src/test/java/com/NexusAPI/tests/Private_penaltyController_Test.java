package com.NexusAPI.Tests;

import org.testng.annotations.Test;
import org.testng.Assert;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import org.testng.Assert;

import com.NexustAPIAutomation.java.CommonMethods;
import com.NexustAPIAutomation.java.DataBackupRestore;
import com.NexustAPIAutomation.java.ReadProjectProperties;

import io.restassured.response.ValidatableResponse;

public class Private_penaltyController_Test {
//Moved to global properties
	// @Test(priority = 1, groups = "Penalty")
	public void putpenaltySetup_v_4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/penalty/setup";
		String ver = "4.0";
		String body = "{\r\n" + "  \"PenaltyId\": \"5%\",\r\n" + "  \"PenaltyProcessing\": 1,\r\n"
				+ "  \"CompoundPenalties\": true\r\n" + "}";
		String expected = "{\"PenaltySetup\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Penalty Setup Updated.\",\"Level\":1}]}}";
		String result = CommonMethods.putMethodstring(uri, ver, body, expected);

	}

	// Moved to global properties
	// @Test(priority = 2, groups = "Penalty")
	public void getpenaltySetup_v_4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/penalty/setup";
		String ver = "4.0";
		String expected = "{\"PenaltySetup\":{\"Success\":true,\"Data\":{\"PenaltyId\":\"5%\",\"PenaltyProcessing\":1,\"CompoundPenalties\":true},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		CommonMethods.getMethodContainsString(uri, ver, params, expected);
	}

	@Test(priority = 3, groups = "Penalty")
	public void postpenaltyDocuments_v_4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/penalty/documentPost";
		String ver = "4.0";
		String payload = "{\r\n" + "    \"BatchId\": \"PENALTY\",\r\n" + "    \"PostDate\": \"2025-09-18\",\r\n"
				+ "    \"Document\": [  \r\n" + "     {  \r\n" + "        \"Number\": \"PNLT00000000063\"  \r\n"
				+ "      }  \r\n" + " ]\r\n" + "}";
		String expected = "{\"Penalty\":{\"Success\":true,\"Data\":{\"UserId\":\"sa\",\"VersionNumber\":\"1.0.0\",\"PostDate\":\"2025-09-18\",\"BatchId\":\"PENALTY\",\"MiscCharge\":{\"Document\":[{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000063\",\"CustomerId\":\"0000011111\",\"LocationId\":\"LOC@0001\",\"DocumentDate\":\"2000-05-01\",\"TransactionAmount\":5.00,\"TaxAmount\":0.00,\"TotalChargeAmount\":5.00,\"ServiceType\":\"ELECTRIC\",\"OutstandingAmount\":5.00,\"MiscChargeType\":\"RES-OVERDUE\",\"TransactionDescription\":\"Penalty for overdue\",\"TaxSchedule\":\"\"}]},\"MiscChargeDistribution\":{\"DocumentDistribution\":[{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000063\",\"DistributionType\":3,\"DistributionIndex\":611,\"OriginalDebitAmount\":5.00,\"OriginalCreditAmount\":0.00},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000063\",\"DistributionType\":9,\"DistributionIndex\":612,\"OriginalDebitAmount\":0.00,\"OriginalCreditAmount\":5.00}]},\"Payment\":null,\"PaymentDistribution\":null,\"Bill\":null,\"BillDistribution\":null,\"HasPostingError\":false,\"HasPostingReport\":true,\"Document\":[{\"DocumentNumber\":\"PNLT00000000063\"}],\"ApplyDocument\":[],\"PostedDistribution\":[{\"DocumentNumber\":\"PNLT00000000063\",\"LineSequence\":1,\"DistributionIndex\":611,\"TransactionAmount\":-5.00,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000063\",\"LineSequence\":2,\"DistributionIndex\":612,\"TransactionAmount\":5.00,\"LocationId\":\"\"}],\"ReportList\":[{\"Name\":\"Post Misc Charge Edit List\",\"PrintOrder\":1,\"DisplayName\":\"Post Misc Charge Edit List\",\"PrintEnabled\":true},{\"Name\":\"Post Misc Charge Dist Breakdown Summary\",\"PrintOrder\":2,\"DisplayName\":\"Post Misc Charge Dist Breakdown Summary\",\"PrintEnabled\":true},{\"Name\":\"Post Misc Charge Payment Dist Breakdown Detail\",\"PrintOrder\":3,\"DisplayName\":\"Post Misc Charge Payment Dist Breakdown Detail\",\"PrintEnabled\":false},{\"Name\":\"Post Misc Charge Payment Dist Breakdown Summary\",\"PrintOrder\":4,\"DisplayName\":\"Post Misc Charge Payment Dist Breakdown Summary\",\"PrintEnabled\":false}],\"ReportErrorList\":[{\"Name\":\"Post Misc Charge Error List\",\"PrintOrder\":1,\"DisplayName\":\"Post Misc Charge Error List\",\"PrintEnabled\":true}]},\"Messages\":[]}}";
		String Result = CommonMethods.postMethodResponseAsString(payload, uri, ver);
		Assert.assertEquals(Result, expected);
	}

	@Test(priority = 4, groups = "Penalty")
	public void postpenaltycalculate_v_4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/penalty/calculate";
		String ver = "4.0";
		String payload = "{\r\n" + "  \"CalculateBy\": 0,\r\n" + "  \"BatchId\": \"PENALTY2\",\r\n"
				+ "  \"PenaltyDocumentDate\": \"2025-01-01\",\r\n" + "  \"PenaltyDueDate\": \"2025-09-15\",\r\n"
				+ "  \"ExcludeFormerCustomers\": false,\r\n" + "  \"IncludeOnlyPrintedDocuments\": false,\r\n"
				+ "  \"IncludeFormerCustomerstWithLoanBalance\": false,\r\n" + "  \"Route\": [\r\n"
				+ "      \"001\",\r\n" + "      \"002\"\r\n" + "  ],\r\n"
				+ "  \"IncludeDocumentsWithDueDate\": \"2025-09-15\",\r\n"
				+ "  \"IncludeDocumentWithPenaltyProcessingDate\": \"2025-09-15\"\r\n" + "}\r\n" + "";
		String expected = "{\"PenaltyCalculate\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Penalty calculation has been done successfully. 1 penalty document\\/s has been created.\",\"Level\":1}]}}";
		String Result = CommonMethods.postMethodResponseAsString(payload, uri, ver);
		Assert.assertEquals(Result, expected);
	}

	@Test(priority = 5, groups = "Penalty")
	public void postpenaltyDocumentsRoute_v_4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/penalty/documentPost";
		String ver = "4.0";
		String payload = "{\r\n" + "    \"BatchId\": \"ROUTE\",\r\n" + "    \"PostDate\": \"2025-09-30\"\r\n" + "}";
		String expected = "{\"Penalty\":{\"Success\":true,\"Data\":{\"UserId\":\"sa\",\"VersionNumber\":\"1.0.0\",\"PostDate\":\"2025-09-30\",\"BatchId\":\"ROUTE\",\"MiscCharge\":{\"Document\":[{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000064\",\"CustomerId\":\"500001\",\"LocationId\":\"100001\",\"DocumentDate\":\"2000-05-01\",\"TransactionAmount\":2.79,\"TaxAmount\":0.00,\"TotalChargeAmount\":2.79,\"ServiceType\":\"WR-A\",\"OutstandingAmount\":2.79,\"MiscChargeType\":\"DEFAULTPYMT\",\"TransactionDescription\":\"Penalty for late payment\",\"TaxSchedule\":\"\"},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000065\",\"CustomerId\":\"500001\",\"LocationId\":\"100001\",\"DocumentDate\":\"2000-05-01\",\"TransactionAmount\":1.62,\"TaxAmount\":0.00,\"TotalChargeAmount\":1.62,\"ServiceType\":\"IR-A\",\"OutstandingAmount\":1.62,\"MiscChargeType\":\"REFUSE\",\"TransactionDescription\":\"Refuse Late Charges\",\"TaxSchedule\":\"\"},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000066\",\"CustomerId\":\"500002\",\"LocationId\":\"100002\",\"DocumentDate\":\"2000-05-01\",\"TransactionAmount\":2.67,\"TaxAmount\":0.00,\"TotalChargeAmount\":2.67,\"ServiceType\":\"WR-A\",\"OutstandingAmount\":2.67,\"MiscChargeType\":\"DEFAULTPYMT\",\"TransactionDescription\":\"Penalty for late payment\",\"TaxSchedule\":\"\"},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000067\",\"CustomerId\":\"500002\",\"LocationId\":\"100002\",\"DocumentDate\":\"2000-05-01\",\"TransactionAmount\":1.55,\"TaxAmount\":0.00,\"TotalChargeAmount\":1.55,\"ServiceType\":\"IR-A\",\"OutstandingAmount\":1.55,\"MiscChargeType\":\"REFUSE\",\"TransactionDescription\":\"Refuse Late Charges\",\"TaxSchedule\":\"\"},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000068\",\"CustomerId\":\"500300\",\"LocationId\":\"WATER005\",\"DocumentDate\":\"2000-05-01\",\"TransactionAmount\":2.99,\"TaxAmount\":0.00,\"TotalChargeAmount\":2.99,\"ServiceType\":\"WATER\",\"OutstandingAmount\":2.99,\"MiscChargeType\":\"DEFAULTPYMT\",\"TransactionDescription\":\"Penalty for late payment\",\"TaxSchedule\":\"\"}]},\"MiscChargeDistribution\":{\"DocumentDistribution\":[{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000064\",\"DistributionType\":9,\"DistributionIndex\":615,\"OriginalDebitAmount\":0.00,\"OriginalCreditAmount\":2.79},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000064\",\"DistributionType\":3,\"DistributionIndex\":618,\"OriginalDebitAmount\":2.79,\"OriginalCreditAmount\":0.00},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000065\",\"DistributionType\":3,\"DistributionIndex\":613,\"OriginalDebitAmount\":1.62,\"OriginalCreditAmount\":0.00},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000065\",\"DistributionType\":9,\"DistributionIndex\":616,\"OriginalDebitAmount\":0.00,\"OriginalCreditAmount\":1.62},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000066\",\"DistributionType\":9,\"DistributionIndex\":615,\"OriginalDebitAmount\":0.00,\"OriginalCreditAmount\":2.67},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000066\",\"DistributionType\":3,\"DistributionIndex\":618,\"OriginalDebitAmount\":2.67,\"OriginalCreditAmount\":0.00},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000067\",\"DistributionType\":3,\"DistributionIndex\":613,\"OriginalDebitAmount\":1.55,\"OriginalCreditAmount\":0.00},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000067\",\"DistributionType\":9,\"DistributionIndex\":616,\"OriginalDebitAmount\":0.00,\"OriginalCreditAmount\":1.55},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000068\",\"DistributionType\":9,\"DistributionIndex\":615,\"OriginalDebitAmount\":0.00,\"OriginalCreditAmount\":2.99},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000068\",\"DistributionType\":3,\"DistributionIndex\":618,\"OriginalDebitAmount\":2.99,\"OriginalCreditAmount\":0.00}]},\"Payment\":null,\"PaymentDistribution\":null,\"Bill\":null,\"BillDistribution\":null,\"HasPostingError\":false,\"HasPostingReport\":true,\"Document\":[{\"DocumentNumber\":\"PNLT00000000064\"},{\"DocumentNumber\":\"PNLT00000000065\"},{\"DocumentNumber\":\"PNLT00000000066\"},{\"DocumentNumber\":\"PNLT00000000067\"},{\"DocumentNumber\":\"PNLT00000000068\"}],\"ApplyDocument\":[],\"PostedDistribution\":[{\"DocumentNumber\":\"PNLT00000000064\",\"LineSequence\":1,\"DistributionIndex\":615,\"TransactionAmount\":2.79,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000064\",\"LineSequence\":2,\"DistributionIndex\":618,\"TransactionAmount\":-2.79,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000065\",\"LineSequence\":1,\"DistributionIndex\":613,\"TransactionAmount\":-1.62,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000065\",\"LineSequence\":2,\"DistributionIndex\":616,\"TransactionAmount\":1.62,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000066\",\"LineSequence\":1,\"DistributionIndex\":615,\"TransactionAmount\":2.67,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000066\",\"LineSequence\":2,\"DistributionIndex\":618,\"TransactionAmount\":-2.67,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000067\",\"LineSequence\":1,\"DistributionIndex\":613,\"TransactionAmount\":-1.55,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000067\",\"LineSequence\":2,\"DistributionIndex\":616,\"TransactionAmount\":1.55,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000068\",\"LineSequence\":1,\"DistributionIndex\":615,\"TransactionAmount\":2.99,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000068\",\"LineSequence\":2,\"DistributionIndex\":618,\"TransactionAmount\":-2.99,\"LocationId\":\"\"}],\"ReportList\":[{\"Name\":\"Post Misc Charge Edit List\",\"PrintOrder\":1,\"DisplayName\":\"Post Misc Charge Edit List\",\"PrintEnabled\":true},{\"Name\":\"Post Misc Charge Dist Breakdown Summary\",\"PrintOrder\":2,\"DisplayName\":\"Post Misc Charge Dist Breakdown Summary\",\"PrintEnabled\":true},{\"Name\":\"Post Misc Charge Payment Dist Breakdown Detail\",\"PrintOrder\":3,\"DisplayName\":\"Post Misc Charge Payment Dist Breakdown Detail\",\"PrintEnabled\":false},{\"Name\":\"Post Misc Charge Payment Dist Breakdown Summary\",\"PrintOrder\":4,\"DisplayName\":\"Post Misc Charge Payment Dist Breakdown Summary\",\"PrintEnabled\":false}],\"ReportErrorList\":[{\"Name\":\"Post Misc Charge Error List\",\"PrintOrder\":1,\"DisplayName\":\"Post Misc Charge Error List\",\"PrintEnabled\":true}]},\"Messages\":[]}}";
		String Result = CommonMethods.postMethodResponseAsString(payload, uri, ver);
		Assert.assertEquals(Result, expected);
	}

	@Test(priority = 6, groups = "Penalty")
	public void postpenaltyDocumentsZones_v_4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/penalty/documentPost";
		String ver = "4.0";
		String payload = "{\r\n" + "    \"BatchId\": \"ZONES\",\r\n" + "    \"PostDate\": \"2025-09-18\"\r\n" + "}";
		String expected = "{\"Penalty\":{\"Success\":true,\"Data\":{\"UserId\":\"sa\",\"VersionNumber\":\"1.0.0\",\"PostDate\":\"2025-09-18\",\"BatchId\":\"ZONES\",\"MiscCharge\":{\"Document\":[{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000069\",\"CustomerId\":\"CUSTOMER007\",\"LocationId\":\"ELECWAT001\",\"DocumentDate\":\"2020-01-01\",\"TransactionAmount\":5.50,\"TaxAmount\":0.00,\"TotalChargeAmount\":5.50,\"ServiceType\":\"ELECTRIC\",\"OutstandingAmount\":5.50,\"MiscChargeType\":\"5%\",\"TransactionDescription\":\"Late Payment Charge\",\"TaxSchedule\":\"\"},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000070\",\"CustomerId\":\"CUSTOMER007\",\"LocationId\":\"ELECWAT001\",\"DocumentDate\":\"2020-01-01\",\"TransactionAmount\":4.49,\"TaxAmount\":0.00,\"TotalChargeAmount\":4.49,\"ServiceType\":\"\",\"OutstandingAmount\":4.49,\"MiscChargeType\":\"DEFAULTPYMT\",\"TransactionDescription\":\"Penalty for late payment\",\"TaxSchedule\":\"\"},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000071\",\"CustomerId\":\"CUSTOMER007\",\"LocationId\":\"ELECWAT001\",\"DocumentDate\":\"2020-01-01\",\"TransactionAmount\":2.40,\"TaxAmount\":0.00,\"TotalChargeAmount\":2.40,\"ServiceType\":\"REFUSE\",\"OutstandingAmount\":2.40,\"MiscChargeType\":\"REFUSE\",\"TransactionDescription\":\"Refuse Late Charges\",\"TaxSchedule\":\"\"},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000072\",\"CustomerId\":\"CUSTOMER007\",\"LocationId\":\"ELECWAT001\",\"DocumentDate\":\"2020-01-01\",\"TransactionAmount\":305.35,\"TaxAmount\":0.00,\"TotalChargeAmount\":305.35,\"ServiceType\":\"\",\"OutstandingAmount\":305.35,\"MiscChargeType\":\"RES-OVERDUE\",\"TransactionDescription\":\"Penalty for overdue\",\"TaxSchedule\":\"\"},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000073\",\"CustomerId\":\"CUSTOMER007\",\"LocationId\":\"LOCATION006\",\"DocumentDate\":\"2020-01-01\",\"TransactionAmount\":60.80,\"TaxAmount\":0.00,\"TotalChargeAmount\":60.80,\"ServiceType\":\"\",\"OutstandingAmount\":60.80,\"MiscChargeType\":\"RES-OVERDUE\",\"TransactionDescription\":\"Penalty for overdue\",\"TaxSchedule\":\"\"},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000074\",\"CustomerId\":\"CUSTOMER008\",\"LocationId\":\"LOCATION007\",\"DocumentDate\":\"2020-01-01\",\"TransactionAmount\":136.00,\"TaxAmount\":0.00,\"TotalChargeAmount\":136.00,\"ServiceType\":\"\",\"OutstandingAmount\":136.00,\"MiscChargeType\":\"RES-OVERDUE\",\"TransactionDescription\":\"Penalty for overdue\",\"TaxSchedule\":\"\"},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000075\",\"CustomerId\":\"CUSTOMER009\",\"LocationId\":\"SEWER002\",\"DocumentDate\":\"2020-01-01\",\"TransactionAmount\":3.00,\"TaxAmount\":0.00,\"TotalChargeAmount\":3.00,\"ServiceType\":\"\",\"OutstandingAmount\":3.00,\"MiscChargeType\":\"RES-OVERDUE\",\"TransactionDescription\":\"Penalty for overdue\",\"TaxSchedule\":\"\"},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000076\",\"CustomerId\":\"CUSTOMER010\",\"LocationId\":\"LOCATION009\",\"DocumentDate\":\"2020-01-01\",\"TransactionAmount\":1.60,\"TaxAmount\":0.00,\"TotalChargeAmount\":1.60,\"ServiceType\":\"ELECTRIC\",\"OutstandingAmount\":1.60,\"MiscChargeType\":\"5%\",\"TransactionDescription\":\"Late Payment Charge\",\"TaxSchedule\":\"\"},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000077\",\"CustomerId\":\"MASTER001\",\"LocationId\":\"LOCATION002\",\"DocumentDate\":\"2020-01-01\",\"TransactionAmount\":62.80,\"TaxAmount\":0.00,\"TotalChargeAmount\":62.80,\"ServiceType\":\"\",\"OutstandingAmount\":62.80,\"MiscChargeType\":\"RES-OVERDUE\",\"TransactionDescription\":\"Penalty for overdue\",\"TaxSchedule\":\"\"},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000078\",\"CustomerId\":\"MASTER001\",\"LocationId\":\"LOCATION003\",\"DocumentDate\":\"2020-01-01\",\"TransactionAmount\":800.00,\"TaxAmount\":0.00,\"TotalChargeAmount\":800.00,\"ServiceType\":\"\",\"OutstandingAmount\":800.00,\"MiscChargeType\":\"RES-OVERDUE\",\"TransactionDescription\":\"Penalty for overdue\",\"TaxSchedule\":\"\"},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000079\",\"CustomerId\":\"MASTER001\",\"LocationId\":\"LOCATION004\",\"DocumentDate\":\"2020-01-01\",\"TransactionAmount\":41.77,\"TaxAmount\":0.00,\"TotalChargeAmount\":41.77,\"ServiceType\":\"\",\"OutstandingAmount\":41.77,\"MiscChargeType\":\"RES-OVERDUE\",\"TransactionDescription\":\"Penalty for overdue\",\"TaxSchedule\":\"\"},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000080\",\"CustomerId\":\"CUSTOMER014\",\"LocationId\":\"WATER002\",\"DocumentDate\":\"2020-01-01\",\"TransactionAmount\":5.06,\"TaxAmount\":0.00,\"TotalChargeAmount\":5.06,\"ServiceType\":\"\",\"OutstandingAmount\":5.06,\"MiscChargeType\":\"DEFAULTPYMT\",\"TransactionDescription\":\"Penalty for late payment\",\"TaxSchedule\":\"\"},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000081\",\"CustomerId\":\"CUSTOMER014\",\"LocationId\":\"WATER002\",\"DocumentDate\":\"2020-01-01\",\"TransactionAmount\":2.40,\"TaxAmount\":0.00,\"TotalChargeAmount\":2.40,\"ServiceType\":\"REFUSE\",\"OutstandingAmount\":2.40,\"MiscChargeType\":\"REFUSE\",\"TransactionDescription\":\"Refuse Late Charges\",\"TaxSchedule\":\"\"},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000082\",\"CustomerId\":\"CUSTOMER014\",\"LocationId\":\"WATER002\",\"DocumentDate\":\"2020-01-01\",\"TransactionAmount\":124.80,\"TaxAmount\":0.00,\"TotalChargeAmount\":124.80,\"ServiceType\":\"WATER\",\"OutstandingAmount\":124.80,\"MiscChargeType\":\"RES-OVERDUE\",\"TransactionDescription\":\"Penalty for overdue\",\"TaxSchedule\":\"\"},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000083\",\"CustomerId\":\"0000011111\",\"LocationId\":\"LOC@0001\",\"DocumentDate\":\"2020-01-01\",\"TransactionAmount\":48.53,\"TaxAmount\":0.00,\"TotalChargeAmount\":48.53,\"ServiceType\":\"ELECTRIC\",\"OutstandingAmount\":48.53,\"MiscChargeType\":\"RES-OVERDUE\",\"TransactionDescription\":\"Penalty for overdue\",\"TaxSchedule\":\"\"},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000084\",\"CustomerId\":\"0012200\",\"LocationId\":\"000000000523000\",\"DocumentDate\":\"2020-01-01\",\"TransactionAmount\":4.82,\"TaxAmount\":0.00,\"TotalChargeAmount\":4.82,\"ServiceType\":\"\",\"OutstandingAmount\":4.82,\"MiscChargeType\":\"DEFAULTPYMT\",\"TransactionDescription\":\"Penalty for late payment\",\"TaxSchedule\":\"\"},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000085\",\"CustomerId\":\"0012200\",\"LocationId\":\"000000000523000\",\"DocumentDate\":\"2020-01-01\",\"TransactionAmount\":3.72,\"TaxAmount\":0.00,\"TotalChargeAmount\":3.72,\"ServiceType\":\"PC\",\"OutstandingAmount\":3.72,\"MiscChargeType\":\"REFUSE\",\"TransactionDescription\":\"Refuse Late Charges\",\"TaxSchedule\":\"\"},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000086\",\"CustomerId\":\"0012200\",\"LocationId\":\"000000000523000\",\"DocumentDate\":\"2020-01-01\",\"TransactionAmount\":150.30,\"TaxAmount\":0.00,\"TotalChargeAmount\":150.30,\"ServiceType\":\"WR\",\"OutstandingAmount\":150.30,\"MiscChargeType\":\"RES-OVERDUE\",\"TransactionDescription\":\"Penalty for overdue\",\"TaxSchedule\":\"\"}]},\"MiscChargeDistribution\":{\"DocumentDistribution\":[{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000069\",\"DistributionType\":9,\"DistributionIndex\":1,\"OriginalDebitAmount\":0.00,\"OriginalCreditAmount\":5.50},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000069\",\"DistributionType\":3,\"DistributionIndex\":180,\"OriginalDebitAmount\":5.50,\"OriginalCreditAmount\":0.00},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000070\",\"DistributionType\":9,\"DistributionIndex\":615,\"OriginalDebitAmount\":0.00,\"OriginalCreditAmount\":4.49},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000070\",\"DistributionType\":3,\"DistributionIndex\":618,\"OriginalDebitAmount\":4.49,\"OriginalCreditAmount\":0.00},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000071\",\"DistributionType\":3,\"DistributionIndex\":613,\"OriginalDebitAmount\":2.40,\"OriginalCreditAmount\":0.00},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000071\",\"DistributionType\":9,\"DistributionIndex\":616,\"OriginalDebitAmount\":0.00,\"OriginalCreditAmount\":2.40},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000072\",\"DistributionType\":3,\"DistributionIndex\":611,\"OriginalDebitAmount\":305.35,\"OriginalCreditAmount\":0.00},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000072\",\"DistributionType\":9,\"DistributionIndex\":612,\"OriginalDebitAmount\":0.00,\"OriginalCreditAmount\":305.35},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000073\",\"DistributionType\":3,\"DistributionIndex\":611,\"OriginalDebitAmount\":60.80,\"OriginalCreditAmount\":0.00},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000073\",\"DistributionType\":9,\"DistributionIndex\":612,\"OriginalDebitAmount\":0.00,\"OriginalCreditAmount\":60.80},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000074\",\"DistributionType\":3,\"DistributionIndex\":611,\"OriginalDebitAmount\":136.00,\"OriginalCreditAmount\":0.00},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000074\",\"DistributionType\":9,\"DistributionIndex\":612,\"OriginalDebitAmount\":0.00,\"OriginalCreditAmount\":136.00},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000075\",\"DistributionType\":3,\"DistributionIndex\":611,\"OriginalDebitAmount\":3.00,\"OriginalCreditAmount\":0.00},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000075\",\"DistributionType\":9,\"DistributionIndex\":612,\"OriginalDebitAmount\":0.00,\"OriginalCreditAmount\":3.00},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000076\",\"DistributionType\":9,\"DistributionIndex\":1,\"OriginalDebitAmount\":0.00,\"OriginalCreditAmount\":1.60},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000076\",\"DistributionType\":3,\"DistributionIndex\":180,\"OriginalDebitAmount\":1.60,\"OriginalCreditAmount\":0.00},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000077\",\"DistributionType\":3,\"DistributionIndex\":611,\"OriginalDebitAmount\":62.80,\"OriginalCreditAmount\":0.00},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000077\",\"DistributionType\":9,\"DistributionIndex\":612,\"OriginalDebitAmount\":0.00,\"OriginalCreditAmount\":62.80},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000078\",\"DistributionType\":3,\"DistributionIndex\":611,\"OriginalDebitAmount\":800.00,\"OriginalCreditAmount\":0.00},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000078\",\"DistributionType\":9,\"DistributionIndex\":612,\"OriginalDebitAmount\":0.00,\"OriginalCreditAmount\":800.00},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000079\",\"DistributionType\":3,\"DistributionIndex\":611,\"OriginalDebitAmount\":41.77,\"OriginalCreditAmount\":0.00},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000079\",\"DistributionType\":9,\"DistributionIndex\":612,\"OriginalDebitAmount\":0.00,\"OriginalCreditAmount\":41.77},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000080\",\"DistributionType\":9,\"DistributionIndex\":615,\"OriginalDebitAmount\":0.00,\"OriginalCreditAmount\":5.06},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000080\",\"DistributionType\":3,\"DistributionIndex\":618,\"OriginalDebitAmount\":5.06,\"OriginalCreditAmount\":0.00},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000081\",\"DistributionType\":3,\"DistributionIndex\":613,\"OriginalDebitAmount\":2.40,\"OriginalCreditAmount\":0.00},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000081\",\"DistributionType\":9,\"DistributionIndex\":616,\"OriginalDebitAmount\":0.00,\"OriginalCreditAmount\":2.40},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000082\",\"DistributionType\":3,\"DistributionIndex\":611,\"OriginalDebitAmount\":124.80,\"OriginalCreditAmount\":0.00},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000082\",\"DistributionType\":9,\"DistributionIndex\":612,\"OriginalDebitAmount\":0.00,\"OriginalCreditAmount\":124.80},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000083\",\"DistributionType\":3,\"DistributionIndex\":611,\"OriginalDebitAmount\":48.53,\"OriginalCreditAmount\":0.00},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000083\",\"DistributionType\":9,\"DistributionIndex\":612,\"OriginalDebitAmount\":0.00,\"OriginalCreditAmount\":48.53},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000084\",\"DistributionType\":9,\"DistributionIndex\":615,\"OriginalDebitAmount\":0.00,\"OriginalCreditAmount\":4.82},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000084\",\"DistributionType\":3,\"DistributionIndex\":618,\"OriginalDebitAmount\":4.82,\"OriginalCreditAmount\":0.00},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000085\",\"DistributionType\":3,\"DistributionIndex\":613,\"OriginalDebitAmount\":3.72,\"OriginalCreditAmount\":0.00},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000085\",\"DistributionType\":9,\"DistributionIndex\":616,\"OriginalDebitAmount\":0.00,\"OriginalCreditAmount\":3.72},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000086\",\"DistributionType\":3,\"DistributionIndex\":611,\"OriginalDebitAmount\":150.30,\"OriginalCreditAmount\":0.00},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000086\",\"DistributionType\":9,\"DistributionIndex\":612,\"OriginalDebitAmount\":0.00,\"OriginalCreditAmount\":150.30}]},\"Payment\":null,\"PaymentDistribution\":null,\"Bill\":null,\"BillDistribution\":null,\"HasPostingError\":false,\"HasPostingReport\":true,\"Document\":[{\"DocumentNumber\":\"PNLT00000000069\"},{\"DocumentNumber\":\"PNLT00000000070\"},{\"DocumentNumber\":\"PNLT00000000071\"},{\"DocumentNumber\":\"PNLT00000000072\"},{\"DocumentNumber\":\"PNLT00000000073\"},{\"DocumentNumber\":\"PNLT00000000074\"},{\"DocumentNumber\":\"PNLT00000000075\"},{\"DocumentNumber\":\"PNLT00000000076\"},{\"DocumentNumber\":\"PNLT00000000077\"},{\"DocumentNumber\":\"PNLT00000000078\"},{\"DocumentNumber\":\"PNLT00000000079\"},{\"DocumentNumber\":\"PNLT00000000080\"},{\"DocumentNumber\":\"PNLT00000000081\"},{\"DocumentNumber\":\"PNLT00000000082\"},{\"DocumentNumber\":\"PNLT00000000083\"},{\"DocumentNumber\":\"PNLT00000000084\"},{\"DocumentNumber\":\"PNLT00000000085\"},{\"DocumentNumber\":\"PNLT00000000086\"}],\"ApplyDocument\":[],\"PostedDistribution\":[{\"DocumentNumber\":\"PNLT00000000069\",\"LineSequence\":1,\"DistributionIndex\":1,\"TransactionAmount\":5.50,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000069\",\"LineSequence\":2,\"DistributionIndex\":180,\"TransactionAmount\":-5.50,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000070\",\"LineSequence\":1,\"DistributionIndex\":615,\"TransactionAmount\":4.49,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000070\",\"LineSequence\":2,\"DistributionIndex\":618,\"TransactionAmount\":-4.49,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000071\",\"LineSequence\":1,\"DistributionIndex\":613,\"TransactionAmount\":-2.40,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000071\",\"LineSequence\":2,\"DistributionIndex\":616,\"TransactionAmount\":2.40,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000072\",\"LineSequence\":1,\"DistributionIndex\":611,\"TransactionAmount\":-305.35,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000072\",\"LineSequence\":2,\"DistributionIndex\":612,\"TransactionAmount\":305.35,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000073\",\"LineSequence\":1,\"DistributionIndex\":611,\"TransactionAmount\":-60.80,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000073\",\"LineSequence\":2,\"DistributionIndex\":612,\"TransactionAmount\":60.80,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000074\",\"LineSequence\":1,\"DistributionIndex\":611,\"TransactionAmount\":-136.00,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000074\",\"LineSequence\":2,\"DistributionIndex\":612,\"TransactionAmount\":136.00,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000075\",\"LineSequence\":1,\"DistributionIndex\":611,\"TransactionAmount\":-3.00,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000075\",\"LineSequence\":2,\"DistributionIndex\":612,\"TransactionAmount\":3.00,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000076\",\"LineSequence\":1,\"DistributionIndex\":1,\"TransactionAmount\":1.60,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000076\",\"LineSequence\":2,\"DistributionIndex\":180,\"TransactionAmount\":-1.60,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000077\",\"LineSequence\":1,\"DistributionIndex\":611,\"TransactionAmount\":-62.80,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000077\",\"LineSequence\":2,\"DistributionIndex\":612,\"TransactionAmount\":62.80,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000078\",\"LineSequence\":1,\"DistributionIndex\":611,\"TransactionAmount\":-800.00,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000078\",\"LineSequence\":2,\"DistributionIndex\":612,\"TransactionAmount\":800.00,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000079\",\"LineSequence\":1,\"DistributionIndex\":611,\"TransactionAmount\":-41.77,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000079\",\"LineSequence\":2,\"DistributionIndex\":612,\"TransactionAmount\":41.77,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000080\",\"LineSequence\":1,\"DistributionIndex\":615,\"TransactionAmount\":5.06,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000080\",\"LineSequence\":2,\"DistributionIndex\":618,\"TransactionAmount\":-5.06,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000081\",\"LineSequence\":1,\"DistributionIndex\":613,\"TransactionAmount\":-2.40,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000081\",\"LineSequence\":2,\"DistributionIndex\":616,\"TransactionAmount\":2.40,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000082\",\"LineSequence\":1,\"DistributionIndex\":611,\"TransactionAmount\":-124.80,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000082\",\"LineSequence\":2,\"DistributionIndex\":612,\"TransactionAmount\":124.80,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000083\",\"LineSequence\":1,\"DistributionIndex\":611,\"TransactionAmount\":-48.53,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000083\",\"LineSequence\":2,\"DistributionIndex\":612,\"TransactionAmount\":48.53,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000084\",\"LineSequence\":1,\"DistributionIndex\":615,\"TransactionAmount\":4.82,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000084\",\"LineSequence\":2,\"DistributionIndex\":618,\"TransactionAmount\":-4.82,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000085\",\"LineSequence\":1,\"DistributionIndex\":613,\"TransactionAmount\":-3.72,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000085\",\"LineSequence\":2,\"DistributionIndex\":616,\"TransactionAmount\":3.72,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000086\",\"LineSequence\":1,\"DistributionIndex\":611,\"TransactionAmount\":-150.30,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000086\",\"LineSequence\":2,\"DistributionIndex\":612,\"TransactionAmount\":150.30,\"LocationId\":\"\"}],\"ReportList\":[{\"Name\":\"Post Misc Charge Edit List\",\"PrintOrder\":1,\"DisplayName\":\"Post Misc Charge Edit List\",\"PrintEnabled\":true},{\"Name\":\"Post Misc Charge Dist Breakdown Summary\",\"PrintOrder\":2,\"DisplayName\":\"Post Misc Charge Dist Breakdown Summary\",\"PrintEnabled\":true},{\"Name\":\"Post Misc Charge Payment Dist Breakdown Detail\",\"PrintOrder\":3,\"DisplayName\":\"Post Misc Charge Payment Dist Breakdown Detail\",\"PrintEnabled\":false},{\"Name\":\"Post Misc Charge Payment Dist Breakdown Summary\",\"PrintOrder\":4,\"DisplayName\":\"Post Misc Charge Payment Dist Breakdown Summary\",\"PrintEnabled\":false}],\"ReportErrorList\":[{\"Name\":\"Post Misc Charge Error List\",\"PrintOrder\":1,\"DisplayName\":\"Post Misc Charge Error List\",\"PrintEnabled\":true}]},\"Messages\":[]}}";
		String Result = CommonMethods.postMethodResponseAsString(payload, uri, ver);
		Assert.assertEquals(Result, expected);
	}

	@Test(priority = 7, groups = "Penalty")
	public void getcalculatedDocuments_v4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/penalty/calculatedDocuments/ABC1213";
		String ver = "4.0";
		String expected = "{\"Penalty\":{\"Success\":true,\"Data\":{\"BatchId\":\"ABC1213\",\"Document\":[{\"PenaltyDocument\":\"PNLT00000000059\",\"SourceDocument\":\"BILL00000000574\",\"LocationId\":\"TRANSACTION001\",\"CustomerId\":\"TRS0001\",\"ServiceType\":\"ELECTRIC\",\"PenaltyId\":\"5%\",\"TaxAmount\":1.40,\"OriginalBalance\":21.40,\"OutstandingAmount\":21.40,\"PenaltyAmount\":1.07},{\"PenaltyDocument\":\"PNLT00000000060\",\"SourceDocument\":\"BILL00000000575\",\"LocationId\":\"TRANSACTION001\",\"CustomerId\":\"TRS0001\",\"ServiceType\":\"GAS\",\"PenaltyId\":\"DEFAULTPYMT\",\"TaxAmount\":0.00,\"OriginalBalance\":10.00,\"OutstandingAmount\":10.00,\"PenaltyAmount\":0.50},{\"PenaltyDocument\":\"PNLT00000000061\",\"SourceDocument\":\"BILL00000000578\",\"LocationId\":\"TRANSACTION001\",\"CustomerId\":\"TRS0001\",\"ServiceType\":\"SEWER\",\"PenaltyId\":\"DEFAULTPYMT\",\"TaxAmount\":0.00,\"OriginalBalance\":15.00,\"OutstandingAmount\":15.00,\"PenaltyAmount\":0.75},{\"PenaltyDocument\":\"PNLT00000000062\",\"SourceDocument\":\"BILL00000000577\",\"LocationId\":\"TRANSACTION001\",\"CustomerId\":\"TRS0001\",\"ServiceType\":\"WATER\",\"PenaltyId\":\"DEFAULTPYMT\",\"TaxAmount\":0.00,\"OriginalBalance\":30.00,\"OutstandingAmount\":30.00,\"PenaltyAmount\":1.50}]},\"Messages\":[]}}";
		HashMap<String, String> map = new HashMap<String, String>();
		String Result = CommonMethods.getMethodasString(uri, ver, map);
		assertEquals(Result, expected);

	}

	@Test(priority = 8, groups = "Penalty", dependsOnMethods = "getcalculatedDocuments_v4")
	public void deletecalculatedDocuments_v4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/penalty/documentDelete";
		String ver = "4.0";
		String expected = "{\"PenaltyDelete\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Penalties successfully deleted!\",\"Level\":1}]}}";
		// HashMap<String, String> map = new HashMap<String, String>();
		String body = "{\r\n" + "    \"BatchId\": \"ABC1213\",\r\n" + "    \"Document\": [\r\n" + "        { \r\n"
				+ "             \"Number\": \"PNLT00000000059\"\r\n" + "        },\r\n" + "        { \r\n"
				+ "             \"Number\": \"PNLT00000000060\"\r\n" + "        }\r\n" + "    ]\r\n" + "}";
		String Result = CommonMethods.postMethodStringPayloadString(body, uri, ver);
		assertEquals(Result, expected);

	}

	@Test(priority = 9, groups = "Penalty", dependsOnMethods = "deletecalculatedDocuments_v4")
	public void verifyDeletedtcalculatedDocuments_v4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/penalty/calculatedDocuments/ABC1213";
		String ver = "4.0";
		String expected = "{\"Penalty\":{\"Success\":true,\"Data\":{\"BatchId\":\"ABC1213\",\"Document\":[{\"PenaltyDocument\":\"PNLT00000000061\",\"SourceDocument\":\"BILL00000000578\",\"LocationId\":\"TRANSACTION001\",\"CustomerId\":\"TRS0001\",\"ServiceType\":\"SEWER\",\"PenaltyId\":\"DEFAULTPYMT\",\"TaxAmount\":0.00,\"OriginalBalance\":15.00,\"OutstandingAmount\":15.00,\"PenaltyAmount\":0.75},{\"PenaltyDocument\":\"PNLT00000000062\",\"SourceDocument\":\"BILL00000000577\",\"LocationId\":\"TRANSACTION001\",\"CustomerId\":\"TRS0001\",\"ServiceType\":\"WATER\",\"PenaltyId\":\"DEFAULTPYMT\",\"TaxAmount\":0.00,\"OriginalBalance\":30.00,\"OutstandingAmount\":30.00,\"PenaltyAmount\":1.50}]},\"Messages\":[]}}";
		HashMap<String, String> map = new HashMap<String, String>();
		String Result = CommonMethods.getMethodasString(uri, ver, map);
		assertEquals(Result, expected);

	}

	@Test(priority = 10, groups = "Penalty")
	public void postpenaltydocumentPrint_v4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/penalty/documentPrint";
		String ver = "4.0";
		String payload = "{\r\n" + "    \"BatchId\": \"ABC1213\",\r\n" + "    \"Documents\": [\r\n" + "        {\r\n"
				+ "            \"Number\": \"PNLT00000000061\"\r\n" + "        },\r\n" + "        {\r\n"
				+ "            \"Number\": \"PNLT00000000062\"\r\n" + "        }\r\n" + "    ]\r\n" + "}";
		String expected = "{\"Penalty\":{\"Success\":true,\"Data\":{\"ReportList\":[{\"Name\":\"PenaltyPreparationDetail\",\"DisplayName\":\"Penalty Preparation Detail\",\"PrintOrder\":1}]},\"Messages\":[]}}";
		String Result = CommonMethods.postMethodResponseAsString(payload, uri, ver);
		Assert.assertEquals(Result, expected);
		
		
		ReadProjectProperties Read = new ReadProjectProperties();
		String ConnectionString = Read.ReadFile("ConnectionStringServTWO");
		String columnName = "DocumentNumber";
		String Command1 = "select * from csmApi_vwReportPenaltyPreparationDetail";
		Result = "";
		Result = CommonMethods.selectFromDb(Command1, ConnectionString, columnName);

		if (Result == null) {
		
			Assert.fail("csmApi_vwReportPenaltyPreparationDetail is Not empty " + Result);

		}

	}

	@Test(priority = 11, groups = "Penalty", dependsOnMethods = "postpenaltydocumentPrint_v4")
	public void postpenaltydocumentPrintErr_v4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/penalty/documentPrint";
		String ver = "4.0";
		String payload = "{\r\n" + "    \"BatchId\": \"ABC1213\",\r\n" + "    \"Documents\": [\r\n" + "        {\r\n"
				+ "            \"Number\": \"PNLT00000000059\"\r\n" + "        },\r\n" + "        {\r\n"
				+ "            \"Number\": \"PNLT00000000060\"\r\n" + "        }\r\n" + "    ]\r\n" + "}";
		String expected = "{\"Penalty\":{\"Success\":false,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Invalid Penalty Document\\/s PNLT00000000059, PNLT00000000060 in Batch Id ABC1213.\",\"Level\":3}]}}";
		String Result = CommonMethods.postMethodResponseAsString(payload, uri, ver);
		Assert.assertEquals(Result, expected);
	}

	@Test(priority = 11, groups = "Penalty", dependsOnMethods = "postpenaltydocumentPrintErr_v4")
	public void getpenaltyHeader_v_4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/penalty/header/PENALTY2";
		String ver = "4.0";
		String expected = "{\"Penalty\":{\"Success\":true,\"Data\":{\"CalculateBy\":\"0\",\"BatchId\":\"PENALTY2\",\"PenaltyDocumentDate\":\"2025-01-01\",\"PenaltyDueDate\":\"2025-09-15\",\"ExcludeFormerCustomers\":\"false\",\"IncludeOnlyPrintedDocuments\":\"false\",\"IncludeFormerCustomerstWithLoanBalance\":\"false\",\"Route\":[\"001\",\"002\"],\"IncludeDocumentsWithDueDate\":\"2025-09-15\",\"IncludeDocumentWithPenaltyProcessingDate\":\"2025-09-15\",\"UserId\":\"sa\",\"Zone\":[],\"Cycle\":[],\"LocationId\":\"\"},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		CommonMethods.getMethodContainsString(uri, ver, params, expected);
		
		//Verify Table
		ReadProjectProperties Read = new ReadProjectProperties();
		String ConnectionString = Read.ReadFile("ConnectionStringServTWO");
		
		
		String columnName = "BatchId";
		String Command1 = "select * from Two.dbo.csmApi_PenaltyPreparationHistory ";
		String Result = "";
		Result = CommonMethods.selectFromDb(Command1, ConnectionString, columnName);
		
		if (Result == "") {
			Assert.fail("csmApi_PenaltyPreparationHistory is empty " + Result);

		}
	
		
	}

	@Test(priority = 12, groups = "Penalty")
	public void postCreatemiscellaneousv_4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/miscellaneous";
		String ver = "4.0";
		String payload = "{\r\n" + "   \"BatchId\":\"PENALTY1\",\r\n" + "   \"LocationId\":\"100001\",\r\n"
				+ "   \"CustomerId\":\"500001\",\r\n" + "   \"MiscCharge\":{\r\n"
				+ "      \"TypeId\":\"SERVICEELEC\",\r\n" + "      \"Amount\": 80,\r\n"
				+ "      \"Description\":\"Charge for electric service\",\r\n" + "      \"Date\":\"2025-04-17\",\r\n"
				+ "      \"DueDate\":\"\"\r\n" + "   },\r\n" + "   \"TaxScheduleId\":\"ONT GST/PST\"\r\n" + "}";
		String expected = "{\"MiscellaneousCharge\":{\"Success\":false,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"The Batch ID PENALTY1 contains Penalty documents. Select a different Batch ID or create a new Batch ID.\",\"Level\":3}]}}";
		String Result = CommonMethods.postMethodResponseAsString(payload, uri, ver);
		Assert.assertEquals(Result, expected);
	}
	
	@Test(priority = 13, groups = "Penalty")
	public void postpenaltyDocumentsPENALTY2_v_4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/penalty/documentPost";
		String ver = "4.0";
		String payload = "{\r\n" + "    \"BatchId\": \"PENALTY2\",\r\n" + "    \"PostDate\": \"2025-09-18\"\r\n" + "}";
		String expected = "{\"Penalty\":{\"Success\":true,\"Data\":{\"UserId\":\"sa\",\"VersionNumber\":\"1.0.0\",\"PostDate\":\"2025-09-18\",\"BatchId\":\"PENALTY2\",\"MiscCharge\":{\"Document\":[{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000098\",\"CustomerId\":\"0000011111\",\"LocationId\":\"LOC@0001\",\"DocumentDate\":\"2025-01-01\",\"TransactionAmount\":5.00,\"TaxAmount\":0.00,\"TotalChargeAmount\":5.00,\"ServiceType\":\"ELECTRIC\",\"OutstandingAmount\":5.00,\"MiscChargeType\":\"RES-OVERDUE\",\"TransactionDescription\":\"Penalty for overdue\",\"TaxSchedule\":\"\"}]},\"MiscChargeDistribution\":{\"DocumentDistribution\":[{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000098\",\"DistributionType\":3,\"DistributionIndex\":611,\"OriginalDebitAmount\":5.00,\"OriginalCreditAmount\":0.00},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000098\",\"DistributionType\":9,\"DistributionIndex\":612,\"OriginalDebitAmount\":0.00,\"OriginalCreditAmount\":5.00}]},\"Payment\":null,\"PaymentDistribution\":null,\"Bill\":null,\"BillDistribution\":null,\"HasPostingError\":false,\"HasPostingReport\":true,\"Document\":[{\"DocumentNumber\":\"PNLT00000000098\"}],\"ApplyDocument\":[],\"PostedDistribution\":[{\"DocumentNumber\":\"PNLT00000000098\",\"LineSequence\":1,\"DistributionIndex\":611,\"TransactionAmount\":-5.00,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000098\",\"LineSequence\":2,\"DistributionIndex\":612,\"TransactionAmount\":5.00,\"LocationId\":\"\"}],\"ReportList\":[{\"Name\":\"Post Misc Charge Edit List\",\"PrintOrder\":1,\"DisplayName\":\"Post Misc Charge Edit List\",\"PrintEnabled\":true},{\"Name\":\"Post Misc Charge Dist Breakdown Summary\",\"PrintOrder\":2,\"DisplayName\":\"Post Misc Charge Dist Breakdown Summary\",\"PrintEnabled\":true},{\"Name\":\"Post Misc Charge Payment Dist Breakdown Detail\",\"PrintOrder\":3,\"DisplayName\":\"Post Misc Charge Payment Dist Breakdown Detail\",\"PrintEnabled\":false},{\"Name\":\"Post Misc Charge Payment Dist Breakdown Summary\",\"PrintOrder\":4,\"DisplayName\":\"Post Misc Charge Payment Dist Breakdown Summary\",\"PrintEnabled\":false}],\"ReportErrorList\":[{\"Name\":\"Post Misc Charge Error List\",\"PrintOrder\":1,\"DisplayName\":\"Post Misc Charge Error List\",\"PrintEnabled\":true}]},\"Messages\":[]}}";
		String Result = CommonMethods.postMethodResponseAsString(payload, uri, ver);
		Assert.assertEquals(Result, expected);
		
		
		ReadProjectProperties Read = new ReadProjectProperties();
		String ConnectionString = Read.ReadFile("ConnectionStringServTWO");
		String columnName = "Batchid";
		String Command1 = "select * from Two.dbo.csmApi_PenaltyPreparationHistory where Batchid ='PENALTY2'";
		Result = "";
		Result = CommonMethods.selectFromDb(Command1, ConnectionString, columnName);

		if (Result == null) {
			System.out.println(false);
		}
		else{
			Assert.fail("csmApi_PenaltyPreparationHistory is Not empty " + Result);

		}

	}
	
	
	
	

}