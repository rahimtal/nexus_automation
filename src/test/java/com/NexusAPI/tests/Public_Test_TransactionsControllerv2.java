package com.NexusAPI.Tests;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.hamcrest.Matchers;
import org.testng.annotations.Test; import org.testng.Assert;

import com.NexustAPIAutomation.java.CommonMethods;


import io.restassured.response.ValidatableResponse;

public class Public_Test_TransactionsControllerv2 {

	//@Test(priority = 1, groups = "SPA")
	public void getTransactions_v2()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
	//	CommonMethods.CompanyDBRestore();

		String uri = "/transactions/getTransactions";
		String ver = "2";
		String jpath = "./\\TestData\\getTransactionsv2.json";

		HashMap<String, String> params = new HashMap<String, String>();
		 params.put("CustomerId", "500001"); 
		 params.put("LocationId", "100001"); 
		 params.put("MeterReads", "true");
		 params.put("MiscCharges", "true");
		 params.put("Payment", "true");
		 params.put("Bill", "true");
		 params.put("MiscCharges", "true");
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
		 params.put("OtherCharge", "true");
		 String result = CommonMethods.getMethod(uri, ver, params, jpath);
         System.out.println(result);

	}

	
}