package Public;

import org.testng.annotations.Test;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;

import io.restassured.response.ValidatableResponse;

public class portalconsumptionControllerV4 {

	public static ValidatableResponse jsonPathEvaluator;

	@Test(priority = 1, groups = "portalConsumptionHistory")
	public void getconsumptionHistoryController() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/portal/consumptionHistory";
		String ver = "4.0";
		String jpath = "./\\TestData\\portalconsumptionHistory_v4.json";
				
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "METERGRP01");
		params.put("CustomerId", "500024119");
		params.put("UserDate", "2025-01-01");
				
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
		
	}
	
	
		
	

}