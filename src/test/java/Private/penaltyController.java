package Private;

import org.testng.annotations.Test;
import org.testng.Assert;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import org.testng.Assert;

import com.NexustAPIAutomation.java.CommonMethods;

import io.restassured.response.ValidatableResponse;

public class penaltyController {

	@Test(priority = 1, groups = "Penalty")
	public void putpenaltySetup_v_4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/penalty/setup";
		String ver = "4.0";
		String body = "{\r\n" + "  \"PenaltyId\": \"5%\",\r\n" + "  \"PenaltyProcessing\": 1,\r\n"
				+ "  \"CompoundPenalties\": true\r\n" + "}";
		String expected = "{\"PenaltySetup\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Penalty Setup Updated.\",\"Level\":1}]}}";
		String result = CommonMethods.putMethodstring(uri, ver, body, expected);

	}
	
	
	@Test(priority = 2, groups = "Penalty")
	public void getpenaltySetup_v_4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/penalty/setup";
		String ver = "4.0";
		String expected = "{\"PenaltySetup\":{\"Success\":true,\"Data\":{\"PenaltyId\":\"5%\",\"PenaltyProcessing\":1,\"CompoundPenalties\":true},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		CommonMethods.getMethodContainsString(uri, ver, params, expected);
	}
	
	
	@Test(priority = 3, groups = "Penalty")
	public void postpenaltyDocuments_v_4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/penalty/documentPost";
		String ver = "4.0";
		String payload ="{\r\n"
				+ "    \"BatchId\": \"PENALTY\",\r\n"
				+ "    \"PostDate\": \"2025-09-18\",\r\n"
				+ "    \"Document\": [  \r\n"
				+ "     {  \r\n"
				+ "        \"Number\": \"PNLT00000000063\"  \r\n"
				+ "      }  \r\n"
				+ " ]\r\n"
				+ "}";
		String expected = "{\"Penalty\":{\"Success\":true,\"Data\":{\"UserId\":\"sa\",\"VersionNumber\":\"1.0.0\",\"PostDate\":\"2025-09-18\",\"BatchId\":\"PENALTY\",\"MiscCharge\":{\"Document\":[{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000063\",\"CustomerId\":\"0000011111\",\"LocationId\":\"LOC@0001\",\"DocumentDate\":\"2000-05-01\",\"TransactionAmount\":5.00,\"TaxAmount\":0.00,\"TotalChargeAmount\":5.00,\"ServiceType\":\"ELECTRIC\",\"OutstandingAmount\":5.00,\"MiscChargeType\":\"RES-OVERDUE\",\"TransactionDescription\":\"Penalty for overdue\",\"TaxSchedule\":\"\"}]},\"MiscChargeDistribution\":{\"DocumentDistribution\":[{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000063\",\"DistributionType\":3,\"DistributionIndex\":611,\"OriginalDebitAmount\":5.00,\"OriginalCreditAmount\":0.00},{\"DocType\":\"PNLT\",\"DocumentNumber\":\"PNLT00000000063\",\"DistributionType\":9,\"DistributionIndex\":612,\"OriginalDebitAmount\":0.00,\"OriginalCreditAmount\":5.00}]},\"Payment\":null,\"PaymentDistribution\":null,\"Bill\":null,\"BillDistribution\":null,\"HasPostingError\":false,\"HasPostingReport\":true,\"Document\":[{\"DocumentNumber\":\"PNLT00000000063\"}],\"ApplyDocument\":[],\"PostedDistribution\":[{\"DocumentNumber\":\"PNLT00000000063\",\"LineSequence\":1,\"DistributionIndex\":611,\"TransactionAmount\":-5.00,\"LocationId\":\"\"},{\"DocumentNumber\":\"PNLT00000000063\",\"LineSequence\":2,\"DistributionIndex\":612,\"TransactionAmount\":5.00,\"LocationId\":\"\"}],\"ReportList\":[{\"Name\":\"Post Misc Charge Edit List\",\"PrintOrder\":1,\"DisplayName\":\"Post Misc Charge Edit List\",\"PrintEnabled\":true},{\"Name\":\"Post Misc Charge Dist Breakdown Summary\",\"PrintOrder\":2,\"DisplayName\":\"Post Misc Charge Dist Breakdown Summary\",\"PrintEnabled\":true},{\"Name\":\"Post Misc Charge Payment Dist Breakdown Detail\",\"PrintOrder\":3,\"DisplayName\":\"Post Misc Charge Payment Dist Breakdown Detail\",\"PrintEnabled\":false},{\"Name\":\"Post Misc Charge Payment Dist Breakdown Summary\",\"PrintOrder\":4,\"DisplayName\":\"Post Misc Charge Payment Dist Breakdown Summary\",\"PrintEnabled\":false}],\"ReportErrorList\":[{\"Name\":\"Post Misc Charge Error List\",\"PrintOrder\":1,\"DisplayName\":\"Post Misc Charge Error List\",\"PrintEnabled\":true}]},\"Messages\":[]}}";
		String Result = CommonMethods.postMethodResponseAsString(payload, uri, ver);
		Assert.assertEquals(Result, expected);
	}


}