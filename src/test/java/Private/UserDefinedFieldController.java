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

	@Test(priority = 1, groups = "void")
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
}