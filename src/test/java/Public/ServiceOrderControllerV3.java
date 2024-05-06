package Public;

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

public class ServiceOrderControllerV3 {

	public static JsonPath jsonPathEvaluator;
	public static String ServiceOrderNumber;

	@Test(priority = 1, groups = "ServiceOrder")
	public static void postcreateServiceOrder_v3()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/serviceOrder";
		String ver = "3.0";
		String payload = "./\\TestData\\serviceOrderv3.json";
		jsonPathEvaluator = CommonMethods.postMethod(payload, uri, ver);
		String ServiceOrderNumber = jsonPathEvaluator.get("ServiceOrder[0].DocumentNumber");
		System.out.println(ServiceOrderNumber);
		if(ServiceOrderNumber==null)
		{
			Assert.fail("Service Order not created");
		
		}
		else {
			getServiceOrderdetails_v3(ServiceOrderNumber);	
		}

		
	}

	public static void getServiceOrderdetails_v3(String param)
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		System.out.println(param);
		String uri = "/serviceOrder/detail";
		String ver = "3.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("ServiceOrderNumber", param);
		params.put("ShowDrillBack", "true");
		Response response = CommonMethods.getMethod(uri, ver, params);

		// To check for sub string presence get the Response body as a String.
		// Do a String.contains
		String bodyAsString = response.asString();
		System.out.println(bodyAsString);

		if (!bodyAsString.contains(param)) {
			Assert.fail();
		}
		System.out.println(response.prettyPrint());

	}

	@Test(priority = 2, groups = "ServiceOrder", dependsOnMethods = "postcreateServiceOrder_v3")
	public static void getServiceOrder_v3()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		// rue&ShowStatus_FieldComplete=true&OrderBy=ScheduledDateTime ASC,
		// ServiceOrderId DESC
		String uri = "/serviceOrder/getServiceOrders";
		String ver = "3.0";
		String jpath = "./\\TestData\\getserviceordersv3.json";

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "WATER002");
		params.put("ShowStatus_Preparing", "true");
		params.put("ShowStatus_Pending", "true");
		params.put("ShowStatus_Preparing", "true");
		params.put("ShowStatus_InProgress", "true");
		params.put("ShowStatus_OnHold", "true");
		params.put("ShowStatus_Delayed", "true");
		params.put("Showtatus_Billed", "true");
		params.put("ShowStatus_Canceled", "true");
		params.put("ShowStatus_Completed", "true");
		params.put("ShowStatus_WebGenerated", "true");
		params.put("ShowStatus_FieldInProgress", "true");
		params.put("ScheduledDateTime", "ScheduledDateTime ASC, ServiceOrderId DESC");
		params.put("ShowStatus_FieldComplete", "true");

		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);

	}

	@Test(priority = 3, groups = "ServiceOrder")
	public static void getServiceOrderRequestDetails_v3()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		// ?RequestID=transfer&ShowOnlyTransfers=true
		String uri = "/serviceOrderRequestIdDetail/getServiceOrderRequestIdDetail";
		String ver = "3.0";
		String jpath = "./\\TestData\\serviceorderrequestdetailsv2.json";

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("RequestID", "transfer");
		params.put("ShowOnlyTransfers", "true");

		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);

	}

	@Test(priority = 4, groups = "ServiceOrder")
	public static void getServiceOrderRequestDetailsbyOptional_v3()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		// ?RequestID=transfer&ShowOnlyTransfers=true
		String uri = "/serviceOrder/detail";
		String ver = "3.0";
		String jpath = "./\\TestData\\serviceorderrequestdetailsOptionalv3.json";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("ServiceOrderNumber", "SORD00000000002");
		params.put("ShowDrillBack", "true");
		params.put("IncludeHistory", "true");
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);

	}

	@Test(priority = 5, groups = "ServiceOrder")
	public static void putaddMeterReading_v_3()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/serviceOrder/addMeterReading";
		String ver = "3.0";
		String jpath = "./\\TestData\\addMeterReading_v3.json";
		// String fresponse = "./\\TestData\\addMeterReadingresp_v2.json";
		// ValidatableResponse result = CommonMethods.putMethodvalidate(uri, ver, jpath,fresponse);
		ValidatableResponse result = CommonMethods.putMethod(uri, ver, jpath);
		result.assertThat().body(Matchers.containsString("true"));
		result.assertThat().body(Matchers.containsString("READ"));
		result.assertThat().body(Matchers.containsString("Created"));
		//System.out.println(result.extract().asString());
		System.out.println(result.toString());

	}

	@Test(priority = 6, groups = "ServiceOrder")
	public static void putupdatecomment_v_3()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/serviceOrder/updateSOComment";
		String ver = "3.0";
		String jpath = "./\\TestData\\putupdatecommentsv2.json";

		ValidatableResponse result = CommonMethods.putMethod(uri, ver, jpath);
		result.assertThat().body(Matchers.containsString("true"));
		result.assertThat().body(Matchers.containsString("Comment updated"));
		System.out.println(result.extract().asString());

	}

	@Test(priority = 7, groups = "ServiceOrder")
	public static void putaddMiscCharge_v_3()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/serviceOrder/addMiscCharge";
		String ver = "3.0";
		String jpath = "./\\TestData\\putaddmiscChargev2.json";

		ValidatableResponse result = CommonMethods.putMethod(uri, ver, jpath);
		result.assertThat().body(Matchers.containsString("Batch posted. Misc charge created"));
		result.assertThat().body(Matchers.containsString("MISC"));
		result.assertThat().body(Matchers.containsString("true"));

		System.out.println(result.extract().asString());
	}

	@Test(priority = 8, groups = "ServiceOrder")
	public static void putswitchMeter_v_3()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/serviceOrder/switchMeter";
		String ver = "3.0";
		String jpath = "./\\TestData\\meterchangev2.json";
		ValidatableResponse result = CommonMethods.putMethod(uri, ver, jpath);
		result.assertThat().body(Matchers.containsString("Meter updated"));
		result.assertThat().body(Matchers.containsString("true"));
		System.out.println(result.extract().asString());
	}
//DEPRECATED use "Complete service order Task"
//	@Test(priority = 8, groups = "ServiceOrder")
	public static void putTaskComplete_v_3()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/serviceOrder/putTaskComplete";
		String ver = "3.0";
		String jpath = "./\\TestData\\putaskcompletev3.json";
		ValidatableResponse result = CommonMethods.putMethod(uri, ver, jpath);
		result.assertThat().body(Matchers.containsString("Task updated"));
		result.assertThat().body(Matchers.containsString("true"));

		System.out.println(result.extract().asString());
	}

	@Test(priority = 9, groups = "ServiceOrder")
	public static void putTaskCompleteNocharge_v3()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/serviceOrder/taskCompleteOtherNoCharge";
		String ver = "3.0";
		String jpath = "./\\TestData\\putaskcompletenochargev2_3.json";

		ValidatableResponse result = CommonMethods.putMethod(uri, ver, jpath);
		result.assertThat().body(Matchers.containsString(
				"Service Order SORD00000000044 task TASK003 for sequence 1000 has already been completed."));
		result.assertThat().body(Matchers.containsString("false"));
		System.out.println(result.extract().asString());
	}

		

	
	public static void main(String args[])
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		//putaddMeterReading_v_3();
		
		postcreateServiceOrder_v3();
		// putTaskComplete_v_3_4();
	}

}
