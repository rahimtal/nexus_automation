package Private;

import org.testng.annotations.Test; import org.testng.Assert;

import org.testng.Assert;
import org.testng.annotations.Test; import org.testng.Assert;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.annotations.Test; import org.testng.Assert;

import com.NexustAPIAutomation.java.CommonMethods;


import io.restassured.response.ValidatableResponse;

public class transactionsControllerv3  extends BaseClass{

	@Test(priority = 1, groups = "Transaction" )
	public void getTransactions_v_3() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/transactions/getTransactions";
		String ver = "3.0";

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "LOCATION002");
		params.put("CustomerId", "MASTER001");
		params.put("Payment", "true");
		params.put("History", "true");
		params.put("CSM", "true");
		/*
		 * params.put("MeterReads", "true"); params.put("MiscCharges", "true");
		 * params.put("Penalty", "true");
		 * 
		 * params.put("Check", "true"); params.put("Ticket", "true");
		 * params.put("License", "true"); params.put("Rental", "true");
		 * params.put("Permit", "true"); params.put("SmallItem", "true");
		 * params.put("Invoice", "true"); params.put("Work", "true"); params.put("Open",
		 * "true");
		 * 
		 * params.put("Void", "true"); params.put("LocalGov", "true");
		 * params.put("Electric", "true"); params.put("Water", "true");
		 * params.put("Sewer", "false"); params.put("Gas", "false"); params.put("Phone",
		 * "true"); params.put("OtherCharge", "true"); params.put("Other", "true");
		 */
		String actual = CommonMethods.getMethodasString(uri, ver, params);// (uri, ver, params, expected);
		String expected = "{\"result\":[{\"DocumentNumber\":\"PYMT00000000247\",\"DocumentType\":\"Payment\",\"DocumentStatus\":\"History\",\"ConnectionSequence\":\"0\",\"DocumentDate\":\"2000-02-19T19:00:00.000Z\",\"DocVoided\":\"0\",\"DueDate\":\"1899-12-31T19:31:48.000Z\",\"Amount\":\"104.18\",\"OutstandingAmount\":\"0.00\",\"ServiceCategory\":\"0\",\"ServiceCategoryDescription\":\"\",\"ServiceType\":\"\",\"EquipmentID\":\"\",\"Description\":\"Check\",\"DrillbackLink\":\"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=TransactionPymtOrCreditMemoInquiry&DocumentNumber=PYMT00000000247&CogsDrillback=1\",\"ReferenceDocumentNumber\":\"\",\"ReferenceDocumentDate\":\"1899-12-31T19:31:48.000Z\",\"PaymentOrigin\":\"\",\"ChargeDescription\":\"\"},{\"DocumentNumber\":\"PYMT00000000232\",\"DocumentType\":\"Payment\",\"DocumentStatus\":\"History\",\"ConnectionSequence\":\"0\",\"DocumentDate\":\"1999-10-19T19:00:00.000Z\",\"DocVoided\":\"0\",\"DueDate\":\"1899-12-31T19:31:48.000Z\",\"Amount\":\"51.98\",\"OutstandingAmount\":\"0.00\",\"ServiceCategory\":\"0\",\"ServiceCategoryDescription\":\"\",\"ServiceType\":\"\",\"EquipmentID\":\"\",\"Description\":\"Check\",\"DrillbackLink\":\"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=TransactionPymtOrCreditMemoInquiry&DocumentNumber=PYMT00000000232&CogsDrillback=1\",\"ReferenceDocumentNumber\":\"\",\"ReferenceDocumentDate\":\"1899-12-31T19:31:48.000Z\",\"PaymentOrigin\":\"\",\"ChargeDescription\":\"\"},{\"DocumentNumber\":\"PYMT00000000217\",\"DocumentType\":\"Payment\",\"DocumentStatus\":\"History\",\"ConnectionSequence\":\"0\",\"DocumentDate\":\"1999-07-09T19:00:00.000Z\",\"DocVoided\":\"0\",\"DueDate\":\"1899-12-31T19:31:48.000Z\",\"Amount\":\"36.70\",\"OutstandingAmount\":\"0.00\",\"ServiceCategory\":\"0\",\"ServiceCategoryDescription\":\"\",\"ServiceType\":\"\",\"EquipmentID\":\"\",\"Description\":\"Check\",\"DrillbackLink\":\"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=TransactionPymtOrCreditMemoInquiry&DocumentNumber=PYMT00000000217&CogsDrillback=1\",\"ReferenceDocumentNumber\":\"\",\"ReferenceDocumentDate\":\"1899-12-31T19:31:48.000Z\",\"PaymentOrigin\":\"\",\"ChargeDescription\":\"\"},{\"DocumentNumber\":\"PYMT00000000197\",\"DocumentType\":\"Payment\",\"DocumentStatus\":\"History\",\"ConnectionSequence\":\"0\",\"DocumentDate\":\"1999-04-11T19:00:00.000Z\",\"DocVoided\":\"0\",\"DueDate\":\"1899-12-31T19:31:48.000Z\",\"Amount\":\"6.56\",\"OutstandingAmount\":\"0.00\",\"ServiceCategory\":\"0\",\"ServiceCategoryDescription\":\"\",\"ServiceType\":\"\",\"EquipmentID\":\"\",\"Description\":\"Check\",\"DrillbackLink\":\"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=TransactionPymtOrCreditMemoInquiry&DocumentNumber=PYMT00000000197&CogsDrillback=1\",\"ReferenceDocumentNumber\":\"\",\"ReferenceDocumentDate\":\"1899-12-31T19:31:48.000Z\",\"PaymentOrigin\":\"\",\"ChargeDescription\":\"\"},{\"DocumentNumber\":\"PYMT00000000184\",\"DocumentType\":\"Payment\",\"DocumentStatus\":\"History\",\"ConnectionSequence\":\"0\",\"DocumentDate\":\"1999-01-09T19:00:00.000Z\",\"DocVoided\":\"0\",\"DueDate\":\"1899-12-31T19:31:48.000Z\",\"Amount\":\"17.85\",\"OutstandingAmount\":\"0.00\",\"ServiceCategory\":\"0\",\"ServiceCategoryDescription\":\"\",\"ServiceType\":\"\",\"EquipmentID\":\"\",\"Description\":\"Cash\",\"DrillbackLink\":\"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=TransactionPymtOrCreditMemoInquiry&DocumentNumber=PYMT00000000184&CogsDrillback=1\",\"ReferenceDocumentNumber\":\"\",\"ReferenceDocumentDate\":\"1899-12-31T19:31:48.000Z\",\"PaymentOrigin\":\"\",\"ChargeDescription\":\"\"},{\"DocumentNumber\":\"PYMT00000000171\",\"DocumentType\":\"Payment\",\"DocumentStatus\":\"History\",\"ConnectionSequence\":\"0\",\"DocumentDate\":\"1998-07-09T19:00:00.000Z\",\"DocVoided\":\"0\",\"DueDate\":\"1899-12-31T19:31:48.000Z\",\"Amount\":\"162.03\",\"OutstandingAmount\":\"0.00\",\"ServiceCategory\":\"0\",\"ServiceCategoryDescription\":\"\",\"ServiceType\":\"\",\"EquipmentID\":\"\",\"Description\":\"Cash\",\"DrillbackLink\":\"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=TransactionPymtOrCreditMemoInquiry&DocumentNumber=PYMT00000000171&CogsDrillback=1\",\"ReferenceDocumentNumber\":\"\",\"ReferenceDocumentDate\":\"1899-12-31T19:31:48.000Z\",\"PaymentOrigin\":\"\",\"ChargeDescription\":\"\"}]}";
		Assert.assertEquals(actual, expected);

	}

	@Test(priority = 1, groups = "Transaction" )
	public void getrecentTransactions_v_3()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/transactions/getRecentTransactions";
		String ver = "3.0";
		String jpath = "./\\TestData\\getrecentTransactionsv2.json";

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("CustomerId", "CUSTOMER012");
		params.put("LocationId", "LOCATION011");
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);

	}

	@Test(priority = 1, groups = "Transaction" )
	public void getBillInquiry_v_3() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/transaction/bill/BILL00000000001";
		String ver = "3.0";
		String jpath = "./\\TestData\\getBillInquiryv3.json";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("CustomerId", "CUSTOMER012");
		params.put("LocationId", "LOCATION011");
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);

	}

	@Test(priority = 1, groups = "Transaction" )
	public void getBillInquirychargeSummary_v_3()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/transaction/bill/BILL00000000001/chargeSummary";
		String ver = "3.0";
		String jpath = "./\\TestData\\getBillInquirychargeSummaryv3.json";

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("CustomerId", "CUSTOMER012");
		params.put("LocationId", "LOCATION011");
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 1, groups = "Transaction" )
	public void getBillInquirydistribution_v_3()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/transaction/bill/BILL00000000366/distribution";
		String ver = "3.0";
		String jpath = "./\\TestData\\getBillInquirydistributionv3.json";

		HashMap<String, String> params = new HashMap<String, String>();
		// params.put("CustomerId", "CUSTOMER012");
		// params.put("LocationId", "LOCATION011");
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

}