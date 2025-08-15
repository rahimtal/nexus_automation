package Private;

import org.testng.annotations.Test; import org.testng.Assert;

import org.testng.annotations.Test; import org.testng.Assert;
import org.testng.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.annotations.Test; import org.testng.Assert;

import com.NexustAPIAutomation.java.CommonMethods;


//import freemarker.core.BugException;
import io.restassured.response.ValidatableResponse;

public class transactionsControllerv4  extends BaseClass{

	@Test(priority = 1, groups = "Transaction")
	public void getapplyByService_v4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/transaction/payment/PYMT00000000405/MISC00000000311/applyByService";
		String ver = "4.0";
		String jpath = "./\\TestData\\getapplyByService_v4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		// params.put("CustomerId", "CUSTOMER012");
		// params.put("LocationId", "LOCATION011");
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 2, groups = "Transaction")
	public void getMeterReadInquiryWork_v4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/transaction/read/READ00000000913";
		String ver = "4.0";
		String expected = "{\"Read\":{\"Success\":true,\"Data\":{\"DocumentNumber\":\"READ00000000913\",\"PrevDocumentNumber\":\"\",\"NextDocumentNumber\":\"\",\"BatchId\":\"TEST100\",\"Description\":\"\",\"LocationId\":\"WATER100\",\"EquipmentId\":\"ELECT\",\"ReadingType\":{\"Id\":1,\"Description\":\"Actual\"},\"NetMeterType\":{\"Id\":1,\"Description\":\"None\"},\"MeterReader\":\"\",\"ReasonCodeId\":\"\",\"CreatedBy\":\"sa\",\"NumberOfDays\":32,\"Components\":0,\"MeterGroup\":\"\",\"SequenceNumber\":0,\"RouteId\":\"001\",\"Status\":\"Open\",\"TotalMultiplier\":1.00000,\"ConnectionSequence\":1,\"ServiceTypeId\":\"ELECTRIC\",\"ReadingDateTime\":\"2020-02-10T08:14:31\",\"PreviousReadingDate\":\"2020-01-10\",\"CreateDate\":\"2027-04-12\",\"DateAdjusted\":\"1900-01-01\",\"Customer\":{\"Id\":\"CUSTOMER001\",\"Type\":\"Individual\",\"Individual\":{\"FullName\":\"Mr. Joe P MacDonald\",\"Name\":{\"Title\":\"Mr.\",\"First\":\"Joe\",\"Middle\":\"P\",\"Last\":\"MacDonald\"}},\"Business\":null}},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		if (!result.contentEquals(expected)) {
			Assert.fail();
		}
		System.out.println(result);
	}

	@Test(priority = 3, groups = "Transaction")
	public void getMeterReadInquiryOpen_v4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/transaction/read/READ00000000418";
		String ver = "4.0";
		String expected = "{\"Read\":{\"Success\":true,\"Data\":{\"DocumentNumber\":\"READ00000000418\",\"PrevDocumentNumber\":\"\",\"NextDocumentNumber\":\"\",\"BatchId\":\"READ55\",\"Description\":\"\",\"LocationId\":\"LOCEMP-1\",\"EquipmentId\":\"EQUIPELEC021\",\"ReadingType\":{\"Id\":1,\"Description\":\"Actual\"},\"NetMeterType\":{\"Id\":1,\"Description\":\"None\"},\"MeterReader\":\"\",\"ReasonCodeId\":\"\",\"CreatedBy\":\"\",\"NumberOfDays\":91,\"Components\":0,\"MeterGroup\":\"\",\"SequenceNumber\":101,\"RouteId\":\"ROUTEE001\",\"Status\":\"Open\",\"TotalMultiplier\":1.00000,\"ConnectionSequence\":1,\"ServiceTypeId\":\"ELECTRIC\",\"ReadingDateTime\":\"2000-06-30T11:49:04\",\"PreviousReadingDate\":\"2000-03-31\",\"CreateDate\":\"2000-06-30\",\"DateAdjusted\":\"1900-01-01\",\"Customer\":{\"Id\":\"CUSTOMER003\",\"Type\":\"Individual\",\"Individual\":{\"FullName\":\"Miss Jeannie K Bernard\",\"Name\":{\"Title\":\"Miss\",\"First\":\"Jeannie\",\"Middle\":\"K\",\"Last\":\"Bernard\"}},\"Business\":null}},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		if (!result.contentEquals(expected)) {
			Assert.fail();
		}
		System.out.println(result);
	}

	@Test(priority = 4, groups = "Transaction")
	public void getMeterReadInquiryHistory_v4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/transaction/read/READ00000000002";
		String ver = "4.0";
		String expected = "{\"Read\":{\"Success\":true,\"Data\":{\"DocumentNumber\":\"READ00000000002\",\"PrevDocumentNumber\":\"\",\"NextDocumentNumber\":\"\",\"BillNumber\":\"BILL00000000001\",\"Description\":\"\",\"EquipmentId\":\"EQUIPMENT011\",\"ReadingType\":{\"Id\":1,\"Description\":\"Actual\"},\"LocationId\":\"ELECWAT001\",\"NetMeterType\":{\"Id\":1,\"Description\":\"None\"},\"MeterReader\":\"\",\"ReasonCodeId\":\"\",\"CreatedBy\":\"\",\"NumberOfDays\":1,\"Components\":0,\"MeterGroup\":\"\",\"SequenceNumber\":0,\"RouteId\":\"ROUTEEW001\",\"Status\":\"History\",\"TotalMultiplier\":1.00000,\"ConnectionSequence\":1,\"ServiceTypeId\":\"ELECTRIC\",\"ReadingDateTime\":\"1997-01-01T10:27:00\",\"PreviousReadingDate\":\"1997-01-01\",\"CreateDate\":\"1997-01-01\",\"DateAdjusted\":\"1900-01-01\",\"Customer\":{\"Id\":\"CUSTOMER014\",\"Type\":\"Individual\",\"Individual\":{\"FullName\":\"Test Name\",\"Name\":{\"Title\":\"\",\"First\":\"Test\",\"Middle\":\"\",\"Last\":\"Name\"}},\"Business\":null}},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		if (!result.contentEquals(expected)) {
			Assert.fail();
		}
		System.out.println(result);
	}

	@Test(priority = 5, groups = "Transaction")
	public void gettransactionbatch_v4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/transaction/batch/NADMC2022093001";
		String ver = "4.0";
		String expected = "{\"Batch\":{\"Success\":true,\"Data\":{\"Approved\":false,\"ApprovedDate\":\"1900-01-01\",\"ApprovedUser\":\"\",\"BatchDescription\":\"API Deposit Misc Charge\",\"BatchId\":\"NADMC2022093001\",\"source\":\"MISC CHARGES\",\"Status\":0,\"Frequency\":1,\"FrequencyDescription\":\"Single Use\",\"BatchTotal\":92.00000,\"ControlTotal\":0.00000,\"ControlNumber\":0,\"CreatedDate\":\"1900-01-01\",\"ModifiedDate\":\"1900-01-01\",\"PostedDate\":\"1900-01-01\",\"Marked\":false,\"NumberOfTransactions\":1,\"PostUserId\":\"\",\"PostDate\":\"2022-09-30\",\"RecurringPost\":0,\"RecurringLastDate\":\"1900-01-01\",\"NumberOfPosting\":0,\"BatchDaysToIncrement\":0,\"CurrencyId\":\"\",\"CheckBookId\":\"FIRST NATIONAL\",\"PaymentoriginId\":\"\"},\"Messages\":[]}}";

		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result, expected);

		uri = "/transaction/batch/ABC1213";
		ver = "4.0";
		expected = "{\"Batch\":{\"Success\":true,\"Data\":{\"Approved\":false,\"ApprovedDate\":\"1900-01-01\",\"ApprovedUser\":\"\",\"BatchDescription\":\"\",\"BatchId\":\"ABC1213\",\"source\":\"MISC CHARGES\",\"Status\":0,\"Frequency\":0,\"FrequencyDescription\":\"\",\"BatchTotal\":3.82000,\"ControlTotal\":0.00000,\"ControlNumber\":0,\"CreatedDate\":\"1900-01-01\",\"ModifiedDate\":\"1900-01-01\",\"PostedDate\":\"1900-01-01\",\"Marked\":false,\"NumberOfTransactions\":4,\"PostUserId\":\"sa\",\"PostDate\":\"2019-07-31\",\"RecurringPost\":0,\"RecurringLastDate\":\"1900-01-01\",\"NumberOfPosting\":0,\"BatchDaysToIncrement\":0,\"CurrencyId\":\"\",\"CheckBookId\":\"FIRST NATIONAL\",\"PaymentoriginId\":\"\"},\"Messages\":[]}}";
		params = new HashMap<String, String>();
		result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result, expected);
	}

	@Test(priority = 6, groups = "Transaction")
	public void gettransactionpayment_v4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/transaction/payment/PYMT00000000500";
		String ver = "4.0";
		// String expected =
		// "{\"Payment\":{\"Success\":false,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Payment
		// not previously created as a Credit Memo. Cannot display selected
		// payment\",\"Level\":3}]}}";
		String expected = "{\"Payment\":{\"Success\":false,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Payment not previously created as a Credit Memo. Cannot display selected payment\",\"Level\":3}]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("HandleCreditMemoMessaging", "true");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result, expected);
		System.out.println(result);
	}

	@Test(priority = 7, groups = "Transaction")
	public void gettransactionpaymentInv_v4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/transaction/payment/PYMT00000000501";
		String ver = "4.0";
		String expected = "{\"Payment\":{\"Success\":false,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Invalid document number (PYMT00000000501).\",\"Level\":3}]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("HandleCreditMemoMessaging", "true");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result, expected);
		System.out.println(result);
	}

	@Test(priority = 8, groups = "Transaction")
	public void gettransactionpaymentInvCred_v4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/transaction/payment/PYMT00000000001";
		String ver = "4.0";
		String expected = "{\"Payment\":{\"Success\":false,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Payment not previously created as a Credit Memo. Cannot display selected payment\",\"Level\":3}]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("HandleCreditMemoMessaging", "true");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result, expected);
		System.out.println(result);
	}

	

	@Test(priority = 10, groups = "Transaction")
	public void gettransactionpayment_v4_false()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.Bugs("CPDEV-21448");
		String uri = "/transaction/payment/PYMT00000000500";
		String ver = "4.0";
		String expected = "{\"Payment\":{\"Success\":true,\"Data\":{\"DocumentNumber\":\"PYMT00000000500\",\"BatchId\":\"109090ABC\",\"PaidById\":\"CUSTOMER016\",\"PaidBy\":{\"Id\":1,\"Description\":\"Bill To Customer\"},\"PaymentOrigin\":null,\"Comment\":\"\",\"Status\":\"Work\",\"CreateDateTime\":\"2019-08-05T09:01:03\",\"CreatedBy\":\"sa\",\"IsAppliedToDeposit\":false,\"PayDetail\":{\"Type\":\"Check\",\"OtherType\":null,\"TaxSchduleId\":null,\"TaxDescription\":null,\"CreditNoteId\":null,\"Date\":\"2019-08-05\",\"SubTotal\":161.43,\"TotalTaxAmount\":0.00,\"Amount\":161.43,\"UnappliedToBill\":0.00,\"CheckbookId\":\"FIRST NATIONAL\",\"CreditCard\":null,\"CheckNumber\":\"\",\"CreditNoteReasonCode\":\"\",\"TaxDetail\":[]},\"IsVoided\":false,\"Void\":null,\"LocationId\":\"SEWER001\",\"ServiceAddress\":{\"Line1\":\"4 Water st UNIT 145\",\"City\":\"NEW YORK\",\"State\":\"NY\",\"ZipCode\":\"65342\",\"Country\":\"USA\"},\"Customer\":{\"Id\":\"CUSTOMER016\",\"Type\":\"Individual\",\"Individual\":{\"FullName\":\"Mrs. Elizabeth R Hunter\",\"Name\":{\"Title\":\"Mrs.\",\"First\":\"Elizabeth\",\"Middle\":\"R\",\"Last\":\"Hunter\"}},\"Business\":null},\"Prepayment\":[]},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("HandleCreditMemoMessaging", "false");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result, expected);
		System.out.println(result);
	}

	@Test(priority = 11, groups = "Transaction")
	public void gettransactionpayment_v3_false()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.Bugs("CPDEV-21448");
		String uri = "/transaction/payment/PYMT00000000500";
		String ver = "3.0";
		String expected = "{\"Payment\":{\"Success\":true,\"Data\":{\"DocumentNumber\":\"PYMT00000000500\",\"BatchId\":\"109090ABC\",\"PaidById\":\"CUSTOMER016\",\"PaidBy\":{\"Id\":1,\"Description\":\"Bill To Customer\"},\"PaymentOrigin\":null,\"Comment\":\"\",\"Status\":\"Work\",\"CreateDateTime\":\"2019-08-05T09:01:03\",\"CreatedBy\":\"sa\",\"IsAppliedToDeposit\":false,\"PayDetail\":{\"Type\":\"Check\",\"OtherType\":null,\"TaxSchduleId\":null,\"TaxDescription\":null,\"CreditNoteId\":null,\"Date\":\"2019-08-05\",\"SubTotal\":161.43,\"TotalTaxAmount\":0.00,\"Amount\":161.43,\"UnappliedToBill\":0.00,\"CheckbookId\":\"FIRST NATIONAL\",\"CreditCard\":null,\"CheckNumber\":\"\",\"CreditNoteReasonCode\":\"\",\"TaxDetail\":[]},\"IsVoided\":false,\"Void\":null,\"LocationId\":\"SEWER001\",\"ServiceAddress\":{\"Line1\":\"4 Water st UNIT 145\",\"City\":\"NEW YORK\",\"State\":\"NY\",\"ZipCode\":\"65342\",\"Country\":\"USA\"},\"Customer\":{\"Id\":\"CUSTOMER016\",\"Type\":\"Individual\",\"Individual\":{\"FullName\":\"Mrs. Elizabeth R Hunter\",\"Name\":{\"Title\":\"Mrs.\",\"First\":\"Elizabeth\",\"Middle\":\"R\",\"Last\":\"Hunter\"}},\"Business\":null},\"Prepayment\":[]},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("HandleCreditMemoMessaging", "false");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result, expected);
		System.out.println(result);
	}
	
	@Test(priority = 12, groups = "Transaction")
	public void gettransactionpayment_v4_AuthCode()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.Bugs("CPDEV-21448");
		String uri = "/transaction/payment/PYMT00000000539";
		String ver = "4.0";
		String expected = "{\"Payment\":{\"Success\":true,\"Data\":{\"DocumentNumber\":\"PYMT00000000539\",\"BatchId\":\"PY081525sa\",\"PaidById\":\"500003\",\"PaidBy\":{\"Id\":1,\"Description\":\"Bill To Customer\"},\"PaymentOrigin\":null,\"Comment\":\"\",\"Status\":\"Work\",\"CreateDateTime\":\"2025-12-07T00:00:00\",\"CreatedBy\":\"sa\",\"IsAppliedToDeposit\":false,\"PayDetail\":{\"Type\":\"Cash\",\"OtherType\":null,\"TaxSchduleId\":null,\"TaxDescription\":null,\"CreditNoteId\":null,\"Date\":\"2025-12-07\",\"SubTotal\":100.00,\"TotalTaxAmount\":0.00,\"Amount\":100.00,\"UnappliedToBill\":14.84,\"CheckbookId\":\"FIRST NATIONAL\",\"CreditCard\":null,\"CheckNumber\":\"\",\"CreditNoteReasonCode\":\"\",\"TaxDetail\":[]},\"IsVoided\":false,\"Void\":null,\"LocationId\":\"100003\",\"ServiceAddress\":{\"Line1\":\"3243 7th AVE\",\"City\":\"TROY\",\"State\":\"NY\",\"ZipCode\":\"121801325\",\"Country\":\"USA\"},\"Customer\":{\"Id\":\"500003\",\"Type\":\"Individual\",\"Individual\":{\"FullName\":\"Mr. George Thomas\",\"Name\":{\"Title\":\"Mr.\",\"First\":\"George\",\"Middle\":\"\",\"Last\":\"Thomas\"}},\"Business\":null},\"Prepayment\":[]},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("HandleCreditMemoMessaging", "false");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result, expected);
		System.out.println(result);
	}
	
	
	//@Test(priority = 12, groups = "Transaction")
	public void gettransactionsWriteoff_v4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		
		//CommonMethods.Bugs("CPDEV-21830");
		String uri = "/transactions/getTransactions";
		String ver = "4.0";
		String expected = "{\"result\":[{\"DocumentNumber\":\"PYMT00000000532\",\"DocumentType\":\"Payment\",\"DocumentStatus\":\"Unposted\",\"ConnectionSequence\":\"0\",\"DocumentDate\":\"2027-04-11T19:00:00.000Z\",\"DocVoided\":\"0\",\"DueDate\":\"1899-12-31T19:31:48.000Z\",\"Amount\":\"10.00\",\"OutstandingAmount\":\"10.00\",\"ServiceCategory\":\"0\",\"ServiceCategoryDescription\":\"\",\"ServiceType\":\"\",\"EquipmentID\":\"\",\"Description\":\"WRITEOFF\",\"DrillbackLink\":\"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=TransactionPymtOrCreditMemoInquiry&DocumentNumber=PYMT00000000532&CogsDrillback=1\",\"ReferenceDocumentNumber\":\"\",\"ReferenceDocumentDate\":\"1899-12-31T19:31:48.000Z\",\"PaymentOrigin\":\"\",\"ChargeDescription\":\"\"},{\"DocumentNumber\":\"PYMT00000000531\",\"DocumentType\":\"Payment\",\"DocumentStatus\":\"Open\",\"ConnectionSequence\":\"0\",\"DocumentDate\":\"2027-04-11T19:00:00.000Z\",\"DocVoided\":\"0\",\"DueDate\":\"1899-12-31T19:31:48.000Z\",\"Amount\":\"10.00\",\"OutstandingAmount\":\"10.00\",\"ServiceCategory\":\"0\",\"ServiceCategoryDescription\":\"\",\"ServiceType\":\"\",\"EquipmentID\":\"\",\"Description\":\"WRITEOFF\",\"DrillbackLink\":\"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=TransactionPymtOrCreditMemoInquiry&DocumentNumber=PYMT00000000531&CogsDrillback=1\",\"ReferenceDocumentNumber\":\"\",\"ReferenceDocumentDate\":\"1899-12-31T19:31:48.000Z\",\"PaymentOrigin\":\"\",\"ChargeDescription\":\"\"},{\"DocumentNumber\":\"PYMT00000000529\",\"DocumentType\":\"Payment\",\"DocumentStatus\":\"Unposted\",\"ConnectionSequence\":\"0\",\"DocumentDate\":\"2024-04-19T19:00:00.000Z\",\"DocVoided\":\"0\",\"DueDate\":\"1899-12-31T19:31:48.000Z\",\"Amount\":\"10.00\",\"OutstandingAmount\":\"10.00\",\"ServiceCategory\":\"0\",\"ServiceCategoryDescription\":\"\",\"ServiceType\":\"\",\"EquipmentID\":\"\",\"Description\":\"RMGT-OTH-000002\",\"DrillbackLink\":\"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=TransactionPymtOrCreditMemoInquiry&DocumentNumber=PYMT00000000529&CogsDrillback=1\",\"ReferenceDocumentNumber\":\"\",\"ReferenceDocumentDate\":\"1899-12-31T19:31:48.000Z\",\"PaymentOrigin\":\"TEST\",\"ChargeDescription\":\"\"},{\"DocumentNumber\":\"READ00000000423\",\"DocumentType\":\"Meter Reading\",\"DocumentStatus\":\"Open\",\"ConnectionSequence\":\"1\",\"DocumentDate\":\"2000-06-29T19:00:00.000Z\",\"DocVoided\":\"0\",\"DueDate\":\"1899-12-31T19:31:48.000Z\",\"Amount\":\"100\",\"OutstandingAmount\":\"0.00\",\"ServiceCategory\":\"1\",\"ServiceCategoryDescription\":\"Electric\",\"ServiceType\":\"ELECTRIC\",\"EquipmentID\":\"EQUIPMENT005\",\"Description\":\"Actual\",\"DrillbackLink\":\"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=TransactionReadInquiry&DocumentNumber=READ00000000423&DocumentLocation=2&CogsDrillback=1\",\"ReferenceDocumentNumber\":\"\",\"ReferenceDocumentDate\":\"1899-12-31T19:31:48.000Z\",\"PaymentOrigin\":\"\",\"ChargeDescription\":\"\"},{\"DocumentNumber\":\"READ00000000409\",\"DocumentType\":\"Meter Reading\",\"DocumentStatus\":\"History\",\"ConnectionSequence\":\"1\",\"DocumentDate\":\"2000-03-30T19:00:00.000Z\",\"DocVoided\":\"0\",\"DueDate\":\"1899-12-31T19:31:48.000Z\",\"Amount\":\"410\",\"OutstandingAmount\":\"0.00\",\"ServiceCategory\":\"1\",\"ServiceCategoryDescription\":\"Electric\",\"ServiceType\":\"ELECTRIC\",\"EquipmentID\":\"EQUIPMENT005\",\"Description\":\"Actual\",\"DrillbackLink\":\"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=TransactionReadInquiry&DocumentNumber=READ00000000409&DocumentLocation=3&CogsDrillback=1\",\"ReferenceDocumentNumber\":\"BILL00000000369\",\"ReferenceDocumentDate\":\"2000-03-29T19:00:00.000Z\",\"PaymentOrigin\":\"\",\"ChargeDescription\":\"\"}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("ExcludeDescription", "WRITE OFF");
		params.put("LocationId", "LOCATION004");
		params.put("CustomerId", "MASTER001");
		params.put("StartDate", "2000-01-05");
		params.put("EndDate", "1900-01-01");
		params.put("CSM", "true");
		params.put("Payment", "true");
		params.put("History", "true");
		params.put("MeterReads", "true");
		params.put("MiscReads", "true");
		params.put("MiscCharges", "true");
		params.put("Work", "true");
		params.put("Open", "true");
		params.put("Void", "true");
		params.put("LocalGov", "true");
		params.put("Electric", "true");
		params.put("Water", "true");
		params.put("Sewer", "false");
		params.put("Gas", "false");
		params.put("Phone", "true");
		params.put("OtherCharge", "true");

		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result, expected);
		System.out.println(result);
	}

}