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

}