package com.NexusAPI.Tests;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;

public class Private_Test_rateController extends BaseClass {

	@Test(priority = 1, groups = "rate")
	public void geteffectiveDates() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/EMP-1/effectiveDates";
		String ver = "4.0";
		String expected = "{\"Rate\":{\"Success\":true,\"Data\":{\"EffectiveDate\":[{\"StartDate\":\"1998-01-01\",\"EndDate\":\"1900-01-01\"}]},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result, expected);

	}

	@Test(priority = 2, groups = "rate")
	public void geteffectiveDatesInvalidURI()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/INVALID/effectiveDates";
		String ver = "4.0";
		String expected = "{\"Rate\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Rate INVALID does not exist.\",\"Level\":2}]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result, expected);

	}

}