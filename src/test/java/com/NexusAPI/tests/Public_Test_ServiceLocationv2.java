package com.NexusAPI.Tests;

import org.testng.annotations.Test; import org.testng.Assert;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.annotations.Test; import org.testng.Assert;

import com.NexustAPIAutomation.java.CommonMethods;

import io.restassured.response.ValidatableResponse;

public class Public_Test_ServiceLocationv2{

	
	//@Test(priority = 1, groups = "ServiceLocation")
	public void getServicesByLocation_v_2() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/servicesByLocation/getServicesByLocation";
		String ver = "2";
		String jpath = "./\\TestData\\servicesbylocationv2.json";
				
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("CustomerId", "CUSTOMER014");
		params.put("LocationId", "WATER002");
		params.put("TransferDate", "1900-01-01");
				
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	
	}
	

	
	
	
	
	

}