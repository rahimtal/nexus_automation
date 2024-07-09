package Private;

import org.testng.annotations.Test;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;

import io.restassured.response.ValidatableResponse;

public class transactionsControllerv3 {

	

	@Test(priority = 1, groups = "Transaction")
	public void getTransactions_v_3() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/transactions/getTransactions";
		String ver = "3.0";
		String expected = "./\\TestData\\getTransactionsv2.json";
	
		HashMap<String, String> params = new HashMap<String, String>();
		 params.put("LocationId", "LOCATION002"); 
		 params.put("CustomerId", "MASTER001"); 
		 params.put("MeterReads", "true");
		 params.put("MiscCharges", "true");
		 params.put("Penalty", "true");
		 params.put("Payment", "true");
		 params.put("Check", "true");
		 params.put("Ticket", "true");
		 params.put("License", "true");
		 params.put("Rental", "true");
		 params.put("Permit", "true");
		 params.put("SmallItem", "true");
		 params.put("Invoice", "true");
		 params.put("Work", "true");
		 params.put("Open", "true");
		 params.put("History", "true");
		 params.put("CSM", "true");
		 params.put("Void", "true");
		 params.put("LocalGov", "true");
		 params.put("Electric", "true");
		 params.put("Water", "true");
		 params.put("Sewer", "false");
		 params.put("Gas", "false");
		 params.put("Phone", "true");
		 params.put("OtherCharge", "true");
		 params.put("Other", "true");
		 String result = CommonMethods.getMethod(uri, ver, params, expected);
		 System.out.println(result);

	
		
	}
	
	@Test(priority = 1, groups = "Transaction")
	public void getrecentTransactions_v_3() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/transactions/getRecentTransactions";
		String ver = "3.0";
		String jpath = "./\\TestData\\getrecentTransactionsv2.json";

	 	 HashMap<String, String> params = new HashMap<String, String>();
		 params.put("CustomerId", "CUSTOMER012"); 
		 params.put("LocationId", "LOCATION011"); 
		 String result = CommonMethods.getMethod(uri, ver, params, jpath);
		 System.out.println(result);

		
	}
	
	@Test(priority = 1, groups = "Transaction")
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
	
	
	@Test(priority = 1, groups = "Transaction")
	public void getBillInquirychargeSummary_v_3() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/transaction/bill/BILL00000000001/chargeSummary";
		String ver = "3.0";
		String jpath = "./\\TestData\\getBillInquirychargeSummaryv3.json";
		
	     HashMap<String, String> params = new HashMap<String, String>();
		 params.put("CustomerId", "CUSTOMER012"); 
		 params.put("LocationId", "LOCATION011"); 
		 String result = CommonMethods.getMethod(uri, ver, params, jpath);
		 System.out.println(result);
	}
	

	@Test(priority = 1, groups = "Transaction")
	public void getBillInquirydistribution_v_3() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/transaction/bill/BILL00000000366/distribution";
		String ver = "3.0";
		String jpath = "./\\TestData\\getBillInquirydistributionv3.json";
		
	     HashMap<String, String> params = new HashMap<String, String>();
		// params.put("CustomerId", "CUSTOMER012"); 
		 //params.put("LocationId", "LOCATION011"); 
		 String result = CommonMethods.getMethod(uri, ver, params, jpath);
		 System.out.println(result);
	}
	
	


}