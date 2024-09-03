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

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;

public class ServiceOrderControllerV4 {

	public static JsonPath jsonPathEvaluator;
	public static String ServiceOrderNumber;

	@Test
	public static void getServiceOrderdetails_v4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		//CommonMethods.Bug("CPDEV-17883");
		String uri = "/serviceOrder/detail";
		String ver = "4.0";
		String jpath = "./\\TestData\\getserviceOrderDetailsv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("ShowDrillBack", "true");
		params.put("ServiceOrderNumber", "SORD00000000043");
					
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	

}
