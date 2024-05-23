package Private;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class MiscellaneousChargeControllerV4 {

	@Test(priority = 1, groups = "misccharge")
	public void miscellaneousChargeSimulatev4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/miscellaneous/simulate";
		String ver = "4.0";
		String payload = "./\\TestData\\/miscellaneoussimulatev4.json";
		String exResponse = "{\"MiscellaneousCharge\":{\"Success\":true,\"Data\":{\"LocationId\":\"LOCATION013\",\"CustomerId\":\"CUSTOMER017\",\"MiscChargeType\":\"INSTALLELEC\",\"MiscChargeAmount\":41.55,\"TaxScheduleId\":\"ONT GST\\/PST\",\"ServiceDetail\":[{\"ServiceType\":\"ELECTRIC\",\"ChargeAmount\":41.55,\"TaxAmount\":6.24,\"TotalAmount\":47.79,\"OutstandingAmount\":47.79}],\"ServiceTotal\":{\"ChargeAmount\":41.55,\"TaxAmount\":6.24,\"TotalAmount\":47.79,\"OutstandingAmount\":47.79},\"DistributionDetail\":[{\"TypeId\":3,\"Type\":\"RECV\",\"Description\":\"Customer Accounts Receivable - Electric\",\"Index\":506,\"Number\":\"900-1410-00\",\"DebitAmount\":47.79,\"CreditAmount\":0.00},{\"TypeId\":9,\"Type\":\"SALES\",\"Description\":\"Metered Sales to Residential - Electric\",\"Index\":515,\"Number\":\"900-4611-00\",\"DebitAmount\":0.00,\"CreditAmount\":41.55},{\"TypeId\":13,\"Type\":\"TAXES\",\"Description\":\"Federal Income Tax Payable\",\"Index\":96,\"Number\":\"000-2600-00\",\"DebitAmount\":0.00,\"CreditAmount\":6.24}],\"DistributionTotal\":{\"DebitAmount\":47.79,\"CreditAmount\":47.79},\"TaxDetail\":[{\"TaxDetailId\":\"GST\",\"TransactionAmount\":41.55,\"TaxAmount\":2.91,\"TaxAccountIndex\":96,\"ServiceType\":\"\",\"ServiceTypeReceivableAccountIndex\":0},{\"TaxDetailId\":\"PST\",\"TransactionAmount\":41.55,\"TaxAmount\":3.33,\"TaxAccountIndex\":96,\"ServiceType\":\"\",\"ServiceTypeReceivableAccountIndex\":0}]},\"Messages\":[]}}";
		CommonMethods.postcall(uri, payload, ver, exResponse);
	}

	@Test(priority = 2, groups = "misccharge")
	public void deleteMiscellaneousChargeErr1v4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/miscellaneous/MISC00000000001";
		String ver = "4.0";
		String expected = "{\"MiscellaneousCharge\":{\"Success\":false,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Cannot delete miscellaneous charge document (MISC00000000001) in open\\/history.\",\"Level\":3}]}}";
		String result = CommonMethods.deleteMethodasString(uri, ver);
		if (!result.contains(expected)) {
			Assert.fail();
		}
		System.out.println(result);
	}

	@Test(priority = 3, groups = "misccharge")
	public void deleteMiscellaneousChargeErrv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/miscellaneous/MISC00000000004";
		String ver = "4.0";
		String expected = "{\"MiscellaneousCharge\":{\"Success\":false,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Cannot delete miscellaneous charge document (MISC00000000004) in open\\/history.\",\"Level\":3}]}}";
		String result = CommonMethods.deleteMethodasString(uri, ver);
		if (!result.contains(expected)) {
			Assert.fail();
		}
		System.out.println(result);
	}

//	@Test(priority = 4, groups = "misccharge")
	public void deleteMiscellaneousChargev4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/miscellaneous/MISC00000000350";
		String ver = "4.0";
		String expected = "{\"MiscellaneousCharge\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Miscellaneous charge deleted.\",\"Level\":1}]}}";
		String result = CommonMethods.deleteMethodasString(uri, ver);
		if (!result.contains(expected)) {
			Assert.fail();
		}
		System.out.println(result);
	}

	// @Test(priority = 5, groups = "misccharge")
	public void deleteMiscellaneousChargeE2v4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/miscellaneous/MISC00000000350";
		String ver = "4.0";
		String expected = "{\"MiscellaneousCharge\":{\"Success\":false,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Invalid document number (MISC00000000350).\",\"Level\":3}]}}";
		String result = CommonMethods.deleteMethodasString(uri, ver);
		if (!result.contains(expected)) {
			Assert.fail();
		}
		System.out.println(result);
	}

	@Test(priority = 6, groups = "misccharge")
	public void miscellaneousChargepostv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/miscellaneous/post";
		String ver = "4.0";
		String payload = "./\\TestData\\//miscellaneouspostv4.json";
		String exResponse = "{\"MiscellaneousCharge\":{\"Success\":true,\"Data\":{\"UserId\":\"sa\",\"VersionNumber\":\"1.0.0\",\"PostDate\":\"2022-09-30\",\"BatchId\":\"NADMC2022093001\",\"MiscCharge\":{\"Document\":[{\"DocType\":\"MISC\",\"DocumentNumber\":\"MISC00000000351\",\"CustomerId\":\"03332301204\",\"LocationId\":\"SPALOCATION1\",\"DocumentDate\":\"2022-09-30\",\"TransactionAmount\":10.00,\"TaxAmount\":0.00,\"TotalChargeAmount\":10.00,\"ServiceType\":\"WATER\",\"OutstandingAmount\":10.00,\"MiscChargeType\":\"SERVICE WATER\",\"TransactionDescription\":\"Charge for water service\",\"TaxSchedule\":\"EXEMPT\"}]},\"MiscChargeDistribution\":{\"DocumentDistribution\":[{\"DocType\":\"MISC\",\"DocumentNumber\":\"MISC00000000351\",\"DistributionType\":9,\"DistributionIndex\":401,\"OriginalDebitAmount\":0.00,\"OriginalCreditAmount\":10.00},{\"DocType\":\"MISC\",\"DocumentNumber\":\"MISC00000000351\",\"DistributionType\":3,\"DistributionIndex\":508,\"OriginalDebitAmount\":10.00,\"OriginalCreditAmount\":0.00}]},\"Payment\":null,\"PaymentDistribution\":null,\"Bill\":null,\"BillDistribution\":null,\"HasPostingError\":false,\"HasPostingReport\":true,\"Document\":[{\"DocumentNumber\":\"MISC00000000351\"}],\"ApplyDocument\":[],\"PostedDistribution\":[{\"DocumentNumber\":\"MISC00000000351\",\"LineSequence\":1,\"DistributionIndex\":401,\"TransactionAmount\":10.00,\"LocationId\":\"\"},{\"DocumentNumber\":\"MISC00000000351\",\"LineSequence\":2,\"DistributionIndex\":508,\"TransactionAmount\":-10.00,\"LocationId\":\"\"}],\"ReportList\":[{\"Name\":\"Post Misc Charge Edit List\",\"PrintOrder\":1,\"DisplayName\":\"Post Misc Charge Edit List\",\"PrintEnabled\":true},{\"Name\":\"Post Misc Charge Dist Breakdown Summary\",\"PrintOrder\":2,\"DisplayName\":\"Post Misc Charge Dist Breakdown Summary\",\"PrintEnabled\":true},{\"Name\":\"Post Misc Charge Payment Dist Breakdown Detail\",\"PrintOrder\":3,\"DisplayName\":\"Post Misc Charge Payment Dist Breakdown Detail\",\"PrintEnabled\":false},{\"Name\":\"Post Misc Charge Payment Dist Breakdown Summary\",\"PrintOrder\":4,\"DisplayName\":\"Post Misc Charge Payment Dist Breakdown Summary\",\"PrintEnabled\":false}],\"ReportErrorList\":[{\"Name\":\"Post Misc Charge Error List\",\"PrintOrder\":1,\"DisplayName\":\"Post Misc Charge Error List\",\"PrintEnabled\":true}]},\"Messages\":[]}}";
		CommonMethods.postcall(uri, payload, ver, exResponse);
	}

	@Test(priority = 7, groups = "misccharge")
	public void putmiscellaneousChargeSimulatev4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/miscellaneous";
		String ver = "4.0";
		String payload = "{\n" + "   \"DocumentNumber\":\"MISC00000000350\",\n"
				+ "   \"BatchId\":\"NADMC2022093001\",\n" + "  \"LocationId\": \"SPALOCATION1\",\n"
				+ "	\"CustomerId\": \"03332301204\",\n" + "	\"MiscCharge\":{\n"
				+ "        \"TypeId\":\"SERVICEELEC\",\n" + "        \"Amount\": 80.00,\n"
				+ "        \"Description\":\"Charge for electric service\",\n" + "        \"Date\":\"2022-04-17\",\n"
				+ "        \"DueDate\":\"\"\n" + "    },\n" + "    \"TaxScheduleId\": \"ONT GST/PST\",\n"
				+ "    \"ServiceDetail\": [\n" + "        {\n" + "            \"ServiceType\": \"ELECTRIC\",\n"
				+ "            \"ChargeAmount\": 80.00,\n" + "            \"TaxAmount\": 12.00,\n"
				+ "            \"TotalAmount\": 92.00,\n" + "            \"OutstandingAmount\": 92.00\n" + "        }\n"
				+ "    ],\n" + "    \"ServiceTotal\": {\n" + "        \"ChargeAmount\": 80.00,\n"
				+ "        \"TaxAmount\": 12.00,\n" + "        \"TotalAmount\": 92.00,\n"
				+ "        \"OutstandingAmount\": 92.00\n" + "    },\n" + "    \"DistributionDetail\": [\n"
				+ "        {\n" + "            \"TypeId\": 3,\n" + "            \"Type\": \"RECV\",\n"
				+ "            \"Description\": \"Customer Accounts Receivable - Electric\",\n"
				+ "            \"Index\": 506,\n" + "            \"Number\": \"900-1410-00\",\n"
				+ "            \"DebitAmount\": 92.00,\n" + "            \"CreditAmount\": 0.00\n" + "        },\n"
				+ "        {\n" + "            \"TypeId\": 9,\n" + "            \"Type\": \"SALES\",\n"
				+ "            \"Description\": \"Metered Sales to Residential - Electric\",\n"
				+ "            \"Index\": 515,\n" + "            \"Number\": \"900-4611-00\",\n"
				+ "            \"DebitAmount\": 0.00,\n" + "            \"CreditAmount\": 80.00\n" + "        },\n"
				+ "        {\n" + "            \"TypeId\": 13,\n" + "            \"Type\": \"TAXES\",\n"
				+ "            \"Description\": \"Federal Income Tax Payable\",\n" + "            \"Index\": 96,\n"
				+ "            \"Number\": \"000-2600-00\",\n" + "            \"DebitAmount\": 0.00,\n"
				+ "            \"CreditAmount\": 5.60\n" + "        },\n" + "        {\n"
				+ "            \"TypeId\": 13,\n" + "            \"Type\": \"TAXES\",\n"
				+ "            \"Description\": \"IL Income Tax Payable\",\n" + "            \"Index\": 97,\n"
				+ "            \"Number\": \"000-2610-00\",\n" + "            \"DebitAmount\": 0.00,\n"
				+ "            \"CreditAmount\": 6.40\n" + "        }\n" + "    ],\n" + "    \"DistributionTotal\": {\n"
				+ "        \"DebitAmount\": 92.00,\n" + "        \"CreditAmount\": 92.00\n" + "    },\n"
				+ "    \"TaxDetail\": [\n" + "        {\n" + "            \"TaxDetailId\": \"GST\",\n"
				+ "            \"TransactionAmount\": 80.00,\n" + "            \"TaxAmount\": 5.60000,\n"
				+ "            \"TaxAccountIndex\": 96,\n" + "            \"ServiceType\": \"\",\n"
				+ "            \"ServiceTypeReceivableAccountIndex\": 0\n" + "        },\n" + "        {\n"
				+ "            \"TaxDetailId\": \"PST\",\n" + "            \"TransactionAmount\": 80.00,\n"
				+ "            \"TaxAmount\": 6.40000,\n" + "            \"TaxAccountIndex\": 97,\n"
				+ "            \"ServiceType\": \"\",\n" + "            \"ServiceTypeReceivableAccountIndex\": 0\n"
				+ "        }\n" + "    ]\n" + "}";
		String exResponse = "./\\TestData\\/exputmiscellaneoussimulatev4.json";
		// String exResponse =
		// "{\"MiscellaneousCharge\":{\"Success\":true,\"Data\":{\"DocumentNumber\":\"MISC00000000350\",\"BatchId\":\"NADMC2022093001\",\"LocationId\":\"SPALOCATION1\",\"CustomerId\":\"03332301204\"},\"Messages\":[{\"Enabled\":1,\"Info\":\"Updated\",\"Level\":1}]}}";
		CommonMethods.putMethod(uri, ver, payload, exResponse);
	}

	@Test(priority = 8, groups = "misccharge")
	public void miscellaneousChargepostv4err()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/miscellaneous/post";
		String ver = "4.0";
		String payload = "./\\TestData\\//miscellaneouspostv4.json";
		String exResponse = "{\"MiscellaneousCharge\":{\"Success\":false,\"Data\":{\"UserId\":\"\",\"VersionNumber\":\"1.0.0\",\"PostDate\":\"2022-09-30\",\"BatchId\":\"NADMC2022093001\",\"MiscCharge\":{\"Document\":null},\"MiscChargeDistribution\":{\"DocumentDistribution\":[{\"DocType\":\"MISC\",\"DocumentNumber\":\"MISC00000000351\",\"DistributionType\":9,\"DistributionIndex\":401,\"OriginalDebitAmount\":0.00,\"OriginalCreditAmount\":10.00},{\"DocType\":\"MISC\",\"DocumentNumber\":\"MISC00000000351\",\"DistributionType\":3,\"DistributionIndex\":508,\"OriginalDebitAmount\":10.00,\"OriginalCreditAmount\":0.00}]},\"Payment\":null,\"PaymentDistribution\":null,\"Bill\":null,\"BillDistribution\":null,\"HasPostingError\":false,\"HasPostingReport\":true,\"Document\":[{\"DocumentNumber\":\"MISC00000000351\"}],\"ApplyDocument\":[],\"PostedDistribution\":[],\"ReportList\":[{\"Name\":\"Post Misc Charge Edit List\",\"PrintOrder\":1,\"DisplayName\":\"Post Misc Charge Edit List\",\"PrintEnabled\":true},{\"Name\":\"Post Misc Charge Dist Breakdown Summary\",\"PrintOrder\":2,\"DisplayName\":\"Post Misc Charge Dist Breakdown Summary\",\"PrintEnabled\":true},{\"Name\":\"Post Misc Charge Payment Dist Breakdown Detail\",\"PrintOrder\":3,\"DisplayName\":\"Post Misc Charge Payment Dist Breakdown Detail\",\"PrintEnabled\":false},{\"Name\":\"Post Misc Charge Payment Dist Breakdown Summary\",\"PrintOrder\":4,\"DisplayName\":\"Post Misc Charge Payment Dist Breakdown Summary\",\"PrintEnabled\":false}],\"ReportErrorList\":[{\"Name\":\"Post Misc Charge Error List\",\"PrintOrder\":1,\"DisplayName\":\"Post Misc Charge Error List\",\"PrintEnabled\":true}]},\"Messages\":[{\"Enabled\":1,\"Info\":\"csmApi_spCsmPostToGL - No distribution found in audit (UM40009) table\",\"Level\":2}]}}";
		CommonMethods.postcall(uri, payload, ver, exResponse);
	}

	@Test(priority = 9, groups = "misccharge")
	public void postMiscellaneousv4() throws ClassNotFoundException, SQLException, InterruptedException {
		String uri = "/miscellaneous";
		String ver = "4.0";
		String payload = "./\\TestData\\miscellaneousv4.json";
		String exptected = "{\"MiscellaneousCharge\":{\"Success\":true,\"Data\":{\"DocumentNumber\":\"MISC00000000383\",\"BatchId\":\"auto\",\"LocationId\":\"TRANSACTION001\",\"CustomerId\":\"TRS0001\"},\"Messages\":[{\"Enabled\":1,\"Info\":\"Miscellaneous Charge Saved Successfully\",\"Level\":1}]}}";
		Response result = CommonMethods.postMethodResponseasString(payload, uri, ver);
		String actualResult = result.print();
		Assert.assertEquals(actualResult, exptected);

	}

}