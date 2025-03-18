package Public;

import org.testng.annotations.Test;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;
import com.NexustAPIAutomation.java.Retry;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;

public class ServiceAddressControllerV4 {

	public static JsonPath jsonPathEvaluator;
	public static String ServiceOrderNumber;

	@Test(priority = 1, groups = "ServiceOrder")
	public static void getServiceOrderdetails_v4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		//CommonMethods.Bug("https://cogsdale.atlassian.net/browse/CPDEV-20975");
		String uri = "/search/load";
		String ver = "4.0";
		String expected = "{\"Search\":{\"Success\":true,\"Data\":null,\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("ShowDrillBack", "true");
		params.put("ServiceOrderNumber", "SORD00000000043");

		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result, expected);

	}



}
