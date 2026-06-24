package com.NexusAPI.Tests;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;

public class Private_servicesByLocation_Test_V4 extends BaseClass {

	@Test(priority = 1, groups = "servicesByLocation")
	public void getServicesByLocation_v4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/servicesByLocation/getServicesByLocation";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<>();
		params.put("CustomerId", "0012200");
		params.put("LocationId", "000000000523000");
		params.put("TransferDate", "1900-01-01");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertTrue(result.contains("LocationID"), "Expected services list for valid location");
		Assert.assertTrue(result.contains("IsEligibleForDeletion"),
				"Expected IsEligibleForDeletion field in response");
	}

	@Test(priority = 2, groups = "servicesByLocation")
	public void getServicesByLocation_v4_IsEligibleForDeletionFalse()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/servicesByLocation/getServicesByLocation";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<>();
		params.put("CustomerId", "0012200");
		params.put("LocationId", "000000000523000");
		params.put("TransferDate", "1900-01-01");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertTrue(result.contains("\"IsEligibleForDeletion\":false"),
				"Expected IsEligibleForDeletion to be false for active service location");
	}

	@Test(priority = 3, groups = "servicesByLocation")
	public void getServicesByLocation_v4_InvalidCustomerId()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/servicesByLocation/getServicesByLocation";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<>();
		params.put("CustomerId", "INVALID_CUSTOMER");
		params.put("LocationId", "000000000523000");
		params.put("TransferDate", "1900-01-01");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertFalse(result.contains("\"LocationID\""), "Expected no services for invalid CustomerId");
	}

	@Test(priority = 4, groups = "servicesByLocation")
	public void getServicesByLocation_v4_MissingLocationId()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/servicesByLocation/getServicesByLocation";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<>();
		params.put("CustomerId", "0012200");
		params.put("TransferDate", "1900-01-01");
		// Missing LocationId
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertFalse(result.contains("\"LocationID\""), "Expected no services when LocationId is missing");
	}

	@Test(priority = 5, groups = "servicesByLocation")
	public void getServicesByLocation_v4_EmptyParams()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/servicesByLocation/getServicesByLocation";
		String ver = "3.0";
		HashMap<String, String> params = new HashMap<>();
		// No parameters provided
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertFalse(result.contains("\"LocationID\""), "Expected no services when no parameters are provided");
	}

}