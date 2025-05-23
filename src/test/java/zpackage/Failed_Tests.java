package zpackage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;

public class Failed_Tests {
	
	@Test(priority = 1, groups = "Transaction")
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
