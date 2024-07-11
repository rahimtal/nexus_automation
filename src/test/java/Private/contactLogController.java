package Private;

import org.testng.annotations.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;

import io.restassured.response.Response;

public class contactLogController {

	@Test(priority = 1, groups = "contactLogController")
	public void postcontactLogSimplev4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// JsonPath jsonPathEvaluator;
		// CommonMethods.Bug("CPDEV-17054");
		String uri = "/contactLogSimple";
		String ver = "4.0";
		String payload = "./\\TestData\\/PostcontactLogv4.json";
		String exResponse = "{\"result\":[{\"success\":true,\"message\":\"Contact log created succesfully.\"}]}";
		CommonMethods.postcall(uri, payload, ver, exResponse);

	}

	@Test(priority = 2, groups = "contactLogController")
	public void postcontactLogControllerv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// JsonPath jsonPathEvaluator;
		// CommonMethods.Bug("CPDEV-17054");
		String uri = "/contactLog";
		String ver = "4.0";
		String payload = "./\\TestData\\/PostcontactLog2v4.json";
		String exResponse = "{\"ContactLog\":{\"Success\":true,\"Data\":{\"ServiceOrderNumber\":\"SORD000000";
		CommonMethods.postcallcontains(uri, payload, ver, exResponse);

	}

	@Test(priority = 3, groups = "contactLogController")
	public static void putcontactLogv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		CommonMethods.Bug("CPDEV-18576");
		String uri = "/contactLog";
		String ver = "4.0";
		String jpath = "./\\TestData\\putContactLog.json";
		String params = new String(Files.readAllBytes(Paths.get(jpath)));
		String expected = "./\\TestData\\putCheckexpectedsendtoApi_v4.json";
		CommonMethods.putMethod(uri, ver, params, expected);

	}

	@Test(priority = 4, groups = "contactLogController")
	public void getContactLogDetail() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		CommonMethods.Bug("CPDEV-18571");
		String uri = "/contactLog/getContactLogDetail";
		String ver = "4.0";
		String jpath = "./\\TestData\\ContactLogDetailv4.json";

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "LOCATION008");
		params.put("CustomerId", "CUSTOMER009");
		params.put("CsmApiDatabase", "NEXUS_API");
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);

	}

	@Test(priority = 5, groups = "contactLogController")
	public void getgetContactLogDetail2()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/contactLog";
		String ver = "4.0";
		String jpath = "./\\TestData\\getContactLogv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "LOCATION008");
		params.put("CustomerId", "CUSTOMER009");
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);

	}

	@Test(priority = 7, groups = "contactLogController")
	public void contactLoggetContactLogActionCodes()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/contactLog/getContactLogActionCodes";
		String ver = "4.0";
		String jpath = "./\\TestData\\getContactLogActionCodesv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);

	}

	@Test(priority = 6, groups = "contactLogController")
	public void getContactLogMethods() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/contactLog/getContactLogMethods";
		String ver = "4.0";
		String jpath = "./\\TestData\\getContactLogMethodsv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);

	}

	@Test(priority = 8, groups = "contactLogController")
	public void getContactLogServiceOrderRequest()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/contactLog/getContactLogServiceOrderRequest";
		String ver = "4.0";
		String jpath = "./\\TestData\\getContactLogServiceOrderRequest.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);

	}

}