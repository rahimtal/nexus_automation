package apackage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;

public class FailedTests {
	
	
	@Test(priority = 2, groups = "lookup")
	public void getapplyByService_v4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookupBatch";
		String ver = "4.0";
	
		//String expected = "{\"Batch\":[{\"Id\":\"___api_CR\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"10001\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"1001\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"100111\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"10111\",\"Description\":\"\",\"HasTransaction\":false},{\"Id\":\"109090ABC\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"12312312\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"12345\",\"Description\":\"\",\"HasTransaction\":false},{\"Id\":\"ABC10001\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"ABC1213\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"API 20190430\",\"Description\":\"Payments from Web Service - API\",\"HasTransaction\":true},{\"Id\":\"API 20190503\",\"Description\":\"Payments from Web Service - API\",\"HasTransaction\":true},{\"Id\":\"API20250525001\",\"Description\":\"Payments from Nexus Api - API\",\"HasTransaction\":true},{\"Id\":\"API5262025\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"auto\",\"Description\":\"API Misc Charge\",\"HasTransaction\":true},{\"Id\":\"BAT012301203\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"BAT1\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"BAT10123123\",\"Description\":\"\",\"HasTransaction\":false},{\"Id\":\"BATCHPOSTTRANS\",\"Description\":\"\",\"HasTransaction\":false},{\"Id\":\"BATCHTEST01\",\"Description\":\"\",\"HasTransaction\":false},{\"Id\":\"CHEQ1\",\"Description\":\"\",\"HasTransaction\":false},{\"Id\":\"CHK041227sa01\",\"Description\":\"CHEQUE\",\"HasTransaction\":false},{\"Id\":\"DPP041227sa01\",\"Description\":\"PYMT\",\"HasTransaction\":true},{\"Id\":\"FINALBILL\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"INT4\\/12\\/2027\",\"Description\":\"\",\"HasTransaction\":false},{\"Id\":\"INT4\\/30\\/2025\",\"Description\":\"\",\"HasTransaction\":false},{\"Id\":\"MG2024\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"MG2024DM001\",\"Description\":\"MISC\",\"HasTransaction\":true},{\"Id\":\"MISC10001\",\"Description\":\"\",\"HasTransaction\":false},{\"Id\":\"MR052625sa01\",\"Description\":\"API Meter Read\",\"HasTransaction\":true},{\"Id\":\"NADMC2022093001\",\"Description\":\"API Deposit Misc Charge\",\"HasTransaction\":true},{\"Id\":\"RM(3)120427\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"Test Batch\",\"Description\":\"\",\"HasTransaction\":false},{\"Id\":\"Test Batch 2025\",\"Description\":\"Example Comment\",\"HasTransaction\":false},{\"Id\":\"WO101619CRP001\",\"Description\":\"Write Off - sa\",\"HasTransaction\":true},{\"Id\":\"WRITEOFF01\",\"Description\":\"\",\"HasTransaction\":true}]}";
		String expected = "{\"Batch\":[{\"Id\":\"10001\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"1001\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"100111\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"10111\",\"Description\":\"\",\"HasTransaction\":false},{\"Id\":\"109090ABC\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"12312312\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"12345\",\"Description\":\"\",\"HasTransaction\":false},{\"Id\":\"ABC10001\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"ABC1213\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"API 20190430\",\"Description\":\"Payments from Web Service - API\",\"HasTransaction\":true},{\"Id\":\"API 20190503\",\"Description\":\"Payments from Web Service - API\",\"HasTransaction\":true},{\"Id\":\"API20220908001\",\"Description\":\"Payments from Nexus Api - API\",\"HasTransaction\":true},{\"Id\":\"API20220929001\",\"Description\":\"Payments from Nexus Api - API\",\"HasTransaction\":true},{\"Id\":\"BAT012301203\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"BAT1\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"BAT10123123\",\"Description\":\"\",\"HasTransaction\":false},{\"Id\":\"BATCH2025\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"BATCHPOSTTRANS\",\"Description\":\"\",\"HasTransaction\":false},{\"Id\":\"BATCHTEST01\",\"Description\":\"\",\"HasTransaction\":false},{\"Id\":\"CHEQ1\",\"Description\":\"\",\"HasTransaction\":false},{\"Id\":\"CHK041227sa01\",\"Description\":\"CHEQUE\",\"HasTransaction\":false},{\"Id\":\"CHK043025sa01\",\"Description\":\"CHEQUE\",\"HasTransaction\":false},{\"Id\":\"DPP041227sa01\",\"Description\":\"PYMT\",\"HasTransaction\":true},{\"Id\":\"FINALBILL\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"INT4\\/12\\/2027\",\"Description\":\"\",\"HasTransaction\":false},{\"Id\":\"INT4\\/30\\/2025\",\"Description\":\"\",\"HasTransaction\":false},{\"Id\":\"MG2024\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"MG2024DM001\",\"Description\":\"MISC\",\"HasTransaction\":true},{\"Id\":\"MISC10001\",\"Description\":\"\",\"HasTransaction\":false},{\"Id\":\"NADMC2022093001\",\"Description\":\"API Deposit Misc Charge\",\"HasTransaction\":true},{\"Id\":\"RM(3)120427\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"TEST109\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"WO101619CRP001\",\"Description\":\"Write Off - sa\",\"HasTransaction\":true},{\"Id\":\"WRITEOFF01\",\"Description\":\"\",\"HasTransaction\":true}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertEquals(actual, expected);
		// Assert.assertTrue(actual.contains(expected1));
		//Assert.assertEquals(actual, expected);
	}
	
	@Test(priority = 3, groups = "lookup")
	public void lookupPaymentDocuments()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		//Still a Bug (2025)
		//CommonMethods.Bugs("https://cogsdale.atlassian.net/browse/CPDEV-18805");
		String uri = "/lookup/paymentDocuments";
		String ver = "4.0";
		String jpath = "./\\TestData\\lookuppaymentDocumentsv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("DocumentSource", "work");
		params.put("PaymentType", "creditmemo");

		//String expected = "{\"Payment\":[{\"DocumentNumber\":\"PYMT00000000505\",\"BatchId\":\"WO101619CRP001\",\"LocationId\":\"100002\",\"CustomerId\":\"500002\",\"PaymentDate\":\"2027-04-12T00:00:00\",\"PaymentTypeDesc\":\"CreditMemo\"},{\"DocumentNumber\":\"PYMT00000000529\",\"BatchId\":\"WRITEOFF01\",\"LocationId\":\"LOCATION004\",\"CustomerId\":\"MASTER001\",\"PaymentDate\":\"2027-04-12T00:00:00\",\"PaymentTypeDesc\":\"CreditMemo\"},{\"DocumentNumber\":\"PYMT00000000532\",\"BatchId\":\"WRITEOFF01\",\"LocationId\":\"LOCATION004\",\"CustomerId\":\"MASTER001\",\"PaymentDate\":\"2027-04-12T00:00:00\",\"PaymentTypeDesc\":\"CreditMemo\"},{\"DocumentNumber\":\"PYMT00000000534\",\"BatchId\":\"WRITEOFF01\",\"LocationId\":\"LOCATION004\",\"CustomerId\":\"MASTER001\",\"PaymentDate\":\"2027-04-12T00:00:00\",\"PaymentTypeDesc\":\"CreditMemo\"},{\"DocumentNumber\":\"PYMT00000000535\",\"BatchId\":\"WRITEOFF01\",\"LocationId\":\"LOCATION001\",\"CustomerId\":\"MASTER001\",\"PaymentDate\":\"2027-04-12T00:00:00\",\"PaymentTypeDesc\":\"CreditMemo\"},{\"DocumentNumber\":\"PYMT00000000537\",\"BatchId\":\"WRITEOFF01\",\"LocationId\":\"LOCATION004\",\"CustomerId\":\"MASTER001\",\"PaymentDate\":\"2027-04-12T00:00:00\",\"PaymentTypeDesc\":\"CreditMemo\"},{\"DocumentNumber\":\"PYMT00000000541\",\"BatchId\":\"___api_CR\",\"LocationId\":\"BUDGETLOC02\",\"CustomerId\":\"03332301204\",\"PaymentDate\":\"2020-01-15T00:00:00\",\"PaymentTypeDesc\":\"CreditMemo\"}]}";
		  String expected ="{\"Payment\":[{\"DocumentNumber\":\"PYMT00000000505\",\"BatchId\":\"WO101619CRP001\",\"LocationId\":\"100002\",\"CustomerId\":\"500002\",\"PaymentDate\":\"2027-04-12T00:00:00\",\"PaymentTypeDesc\":\"CreditMemo\"},{\"DocumentNumber\":\"PYMT00000000529\",\"BatchId\":\"WRITEOFF01\",\"LocationId\":\"LOCATION004\",\"CustomerId\":\"MASTER001\",\"PaymentDate\":\"2027-04-12T00:00:00\",\"PaymentTypeDesc\":\"CreditMemo\"},{\"DocumentNumber\":\"PYMT00000000532\",\"BatchId\":\"WRITEOFF01\",\"LocationId\":\"LOCATION004\",\"CustomerId\":\"MASTER001\",\"PaymentDate\":\"2027-04-12T00:00:00\",\"PaymentTypeDesc\":\"CreditMemo\"},{\"DocumentNumber\":\"PYMT00000000534\",\"BatchId\":\"WRITEOFF01\",\"LocationId\":\"LOCATION004\",\"CustomerId\":\"MASTER001\",\"PaymentDate\":\"2027-04-12T00:00:00\",\"PaymentTypeDesc\":\"CreditMemo\"},{\"DocumentNumber\":\"PYMT00000000535\",\"BatchId\":\"WRITEOFF01\",\"LocationId\":\"LOCATION001\",\"CustomerId\":\"MASTER001\",\"PaymentDate\":\"2027-04-12T00:00:00\",\"PaymentTypeDesc\":\"CreditMemo\"},{\"DocumentNumber\":\"PYMT00000000537\",\"BatchId\":\"WRITEOFF01\",\"LocationId\":\"LOCATION004\",\"CustomerId\":\"MASTER001\",\"PaymentDate\":\"2027-04-12T00:00:00\",\"PaymentTypeDesc\":\"CreditMemo\"}]}";
		String actual = CommonMethods.getMethodasString(uri, ver, params);//(uri, ver, params, jpath);
		Assert.assertEquals(actual, expected);
		
	}

}
