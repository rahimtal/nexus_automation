package Private;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.annotations.Test;
import org.testng.Assert;

import com.NexustAPIAutomation.java.CommonMethods;

public class UserDefinedFieldController extends BaseClass {

	@Test(priority = 1, groups = "udf")
	public void GetCustomerUdfs() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/udf/customer/500001";
		String ver = "4.0";
		String expected = "{\"UserDefinedField\":{\"Success\":true,\"Data\":{\"CustomerId\":\"500001\",\"UDF\":null},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(expected, result);

	}

	@Test(priority = 2, groups = "void")
	public void GetCustomerUdfs_InvalidCustomer()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/udf/customer/999999";
		String ver = "4.0";
		String expected = "{\"UserDefinedField\":{\"Success\":false,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Invalid Customer Id. 999999\",\"Level\":3}]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result, expected);
	}

	@Test(priority = 3, groups = "void")
	public void GetCustomerUdfs_WithParams()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/udf/customer/500001";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("includeInactive", "true");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertTrue(result.contains("\"CustomerId\":\"500001\""));
		Assert.assertTrue(result.contains("\"Success\":true"));
	}

	@Test(priority = 5, groups = "void")
	public void GetCustomerUdfs_EmptyParams()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/udf/customer/500001";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertTrue(result.contains("\"CustomerId\":\"500001\""));
	}

	@Test(priority = 6, groups = "void")
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

	@Test(priority = 7, groups = "udf")
	public void getCustomerUdfs()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/udf/location/100001";
		String ver = "4.0";
		String params = "";
		String expected = "{\"UserDefinedField\":{\"Success\":true,\"Data\":{\"LocationId\":\"100001\",\"Udf\":[{\"Order\":\"1\",\"Label\":\"Location UDF\",\"Value\":\"1000\",\"Description\":\"\",\"Type\":\"String\",\"Tooltip\":\"Data Type = String, Length = 100\"},{\"Order\":\"2\",\"Label\":\"Location UDF 1\",\"Value\":\"2000\",\"Description\":\"\",\"Type\":\"String\",\"Tooltip\":\"Data Type = String, Length = 100\"},{\"Order\":\"3\",\"Label\":\"Location UDF 2\",\"Value\":\"3000\",\"Description\":\"\",\"Type\":\"String\",\"Tooltip\":\"Data Type = String, Length = 100\"}]},\"Messages\":[]}}";
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result, expected);

	}

	@Test(priority = 8, groups = "udf")
	public void putEquipmentUdfs()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/udf/equipment";
		String ver = "4.0";
		String payload = "{\r\n" + //
				"  \"EquipmentId\": \"EQUIPMENT001\",\r\n" + //
				"  \"Udf\": [\r\n" + //
				"    {\r\n" + //
				"      \"Label\": \"TESTLABEL\",\r\n" + //
				"      \"Value\": \"String value\"\r\n" + //
				"    } ]\r\n" + //
				"}";
		String expected = "{\"UserDefinedField\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"UDF successfully created.\",\"Level\":1}]}}";
		CommonMethods.putMethodstring(uri, ver, payload, expected);

	}

	@Test(priority = 9, groups = "udf")
	public void putLocationUdfs()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/udf/location";
		String ver = "4.0";
		String payload = "{\r\n" + //
				"  \"LocationId\": \"WATER100\",\r\n" + //
				"  \"Udf\": [\r\n" + //
				"    {\r\n" + //
				"      \"Label\": \"TEST124\",\r\n" + //
				"      \"Value\": \"String value\"\r\n" + //
				"    },\r\n" + //
				"    {\r\n" + //
				"      \"Label\": \"TEST125\",\r\n" + //
				"      \"Value\": \"Picklist value\"\r\n" + //
				"    }\r\n" + //
				"  ]\r\n" + //
				"}";
		String expected = "{\"UserDefinedField\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"UDF successfully created.\",\"Level\":1}]}}";
		CommonMethods.putMethodstring(uri, ver, payload, expected);

	}

	@Test(priority = 10, groups = "udf")
	public void getequipmentUdfs()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/udf/equipment/EQUIPMENT008";
		String ver = "4.0";
		String params = "";
		String expected = "{\"UserDefinedField\":{\"Success\":true,\"Data\":{\"EquipmentId\":\"EQUIPMENT008\",\"Udf\":[{\"Order\":\"1\",\"Label\":\"TESTLABEL\",\"Value\":\"\",\"Description\":\"\",\"Type\":\"String\",\"Tooltip\":\"Data Type = String, Length = 100\"},{\"Order\":\"2\",\"Label\":\"TESTLABEL1\",\"Value\":\"\",\"Description\":\"\",\"Type\":\"String\",\"Tooltip\":\"Data Type = String, Length = 100\"},{\"Order\":\"3\",\"Label\":\"TESTLABE2\",\"Value\":\"\",\"Description\":\"\",\"Type\":\"String\",\"Tooltip\":\"Data Type = String, Length = 100\"}]},\"Messages\":[]}}";
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result, expected);

	}

}