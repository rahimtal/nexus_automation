package Public;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;
import com.NexustAPIAutomation.java.Retry;

public class portalconsumptionControllerV4 {

	@Test(priority = 1, groups = "portalConsumption" )
	public void getconsumptionHistoryController()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

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

	@Test(priority = 2, groups = "portalConsumption" )
	public void getserviceByLocation() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/portal/serviceByLocation";
		String ver = "4.0";
		String jpath = "./\\TestData\\portalserviceByLocation.json";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "METERGRP01");
		params.put("CustomerId", "500024119");
		params.put("ResponseVersion", "2");
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);

	}

}