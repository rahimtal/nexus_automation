package com.NexusAPI.Tests;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;

public class Public_Test_UserDefinedFieldController extends BaseClass {

	@Test(priority = 1, groups = "udf")
	public void GetCustomerUdfs() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/udf/connection";
		String ver = "4.0";
		String expected = "{\"UserDefinedField\":[{\"LocationId\":\"100001\",\"Connection\":[{\"Sequence\":1,\"Udf\":[{\"Label\":\"TESTCONLABELUDF1\",\"Value\":\"TESTCONLABELUDFvalue1\",\"Description\":\"\",\"Type\":\"String\"},{\"Label\":\"TESTCONLABELUDF2\",\"Value\":\"TESTCONLABELUDFvalue2\",\"Description\":\"\",\"Type\":\"String\"},{\"Label\":\"TESTCONLABELUDF3\",\"Value\":\"TESTCONLABELUDFvalue3\",\"Description\":\"\",\"Type\":\"String\"},{\"Label\":\"TESTCONLABELUDF4\",\"Value\":\"TESTCONLABELUDFvalue4\",\"Description\":\"\",\"Type\":\"String\"}]}]}]}";
		HashMap<String, String> params = new HashMap<String, String>();

		params.put("LocationId", "100001");
		params.put("ConnectionSeq", "1");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(expected, result);

	}

	// @Test(priority = 6, groups = "void")
	public void putCustomerUdfs()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/udf/customer";
		String ver = "4.0";
		String payload = "{\r\n" + //
				"  \"CustomerId\": \"CUSTOMER007\",\r\n" + //
				"  \"Udf\": [\r\n" + //
				"    {\r\n" + //
				"      \"Label\": \"Customer UDF - String\",\r\n" + //
				"      \"Value\": \"customer\"\r\n" + //
				"    }\r\n" + //
				"  ]\r\n" + //
				"}";
		String expected = "{\"UserDefinedField\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"UDF successfully created.\",\"Level\":1}]}}";
		String result = CommonMethods.putMethodstring(uri, ver, payload, expected);

	}

}