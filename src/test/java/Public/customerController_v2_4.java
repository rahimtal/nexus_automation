package Public;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.annotations.Test; import org.testng.Assert;

import com.NexusAPI.Tests.BaseClass;
import com.NexustAPIAutomation.java.CommonMethods;

import io.restassured.response.ValidatableResponse;

public class customerController_v2_4  extends BaseClass{

	public static ValidatableResponse jsonPathEvaluator;

	//@Test(priority = 6, groups = "CustomerController")
	public void getlocationsByCustomerv_2_4()
	
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		CommonMethods.Bug("22857");
		String uri = "/customers/AUTO1001/locationsByCustomer";
		String ver = "2.4";
		String jpath = "./\\TestData\\locationsByCustomerv2_4.json";

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "LOCATION008");
		params.put("NumPerPage", "50");
		params.put("OrderBy", "status, locationId");
		params.put("PageNum", "1");

		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);

	}

}