package Private;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;
import com.NexustAPIAutomation.java.Retry;

import io.restassured.response.Response;

public class contactLogController {

	@Test(priority = 1, groups = "contactLogController" )
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

	@Test(priority = 2, groups = "contactLogController" )
	public void postcontactLogControllerv4datevaldiation()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/contactLog";
		String ver = "4.0";
		String payload = "./\\TestData\\/PostcontactLog2v4.json";
		String exResponse = "{\"ContactLog\":{\"Success\":false,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Next contact time";
		CommonMethods.postcallcontains(uri, payload, ver, exResponse);

	}

	@Test(priority = 3, groups = "contactLogController" )
	public void postcontactLogControllerv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/contactLog";
		String ver = "4.0";
		String payload = "./\\TestData\\/PostcontactLog3v4.json";
		String exResponse = "{\"ContactLog\":{\"Success\":true,\"Data\":{\"ServiceOrderNumber\":\"SORD";
		CommonMethods.postcallcontains(uri, payload, ver, exResponse);

	}

	@Test(priority = 4, groups = "contactLogController" )
	public void getContactLogDetail() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.Bug("CPDEV-18571");
		String uri = "/contactLog/getContactLogDetail";
		String ver = "4.0";
		String expected = "{\"result\":[{\"Description\":\"Visit Customer\",\"AdditionalText\":\"Test\",\"ContactMethod\":\"Email\",\"ActionCode\":\"2\",\"UserID\":\"Admin\",\"UserName\":\"Admin\",\"ContactDate\":\"2020-01-14T02:48:51.000Z\",\"NextDate\":\"2020-01-21\",\"IsCompleted\":\"Outstanding\",\"Resolution\":\"\",\"ServiceOrderNumber\":\"\",\"ServiceOrderStatusDescription\":\"\",\"RequestID\":\"\",\"RequestDescription\":\"\",\"DrillbackLink\":\"\"}]}";
		//String exp2 = "\"IsCompleted\":\"Completed\",\"Resolution\":\"\",\"ServiceOrderNumber\":\"SORD00000009044\",\"ServiceOrderStatusDescription\":\"Pending\",\"RequestID\":\"DISCONNECT\",\"RequestDescription\":\"Disconnected connection\",\"DrillbackLink\":\"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=ServiceOrder&LocationID=LOCATION008&ServiceOrderNumber=SORD00000009044&CogsDrillback=1\"}]}";

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "ELECWAT001");
		params.put("CustomerId", "CUSTOMER007");
		params.put("CsmApiDatabase", "NEXUS_API");
		CommonMethods.getMethodContainsString(uri, ver, params, expected);
		//CommonMethods.getMethodContainsString(uri, ver, params, exp2);

	}

	@Test(priority = 5, groups = "contactLogController" )
	public void getContactLogDetail2() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/contactLog";
		String version = "4.0";
		String expected = "{\"ContactLog\":{\"Success\":true,\"Data\":{\"Detail\":[{\"ActionCode\":\"2\",\"AdditionalText\":\"Test\",\"ContactMethod\":3,\"Description\":\"Visit Customer\",\"FirstContactDateTime\":\"2020-01-14T07:48:51\",\"NextContactDateTime\":\"2020-01-21T00:00:00\",\"CompletedDateTime\":\"1900-01-01T00:00:00\",\"IsCompleted\":false,\"ResolutionText\":\"\",\"ServiceOrder\":null,\"FollowUpEmployee\":null,\"User\":null}]},\"Messages\":[]}}";
		//String expected2 = "\"CompletedDateTime\":\"2022-04-09T23:01:05\",\"IsCompleted\":true,\"ResolutionText\":\"\",\"ServiceOrder\":{\"Number\":\"SORD00000009044\",\"StatusDescription\":\"Pending\",\"RequestId\":\"DISCONNECT\",\"RequestDescription\":\"Disconnected connection\"},\"FollowUpEmployee\":{\"Id\":\"ZAID0001\",\"FirstName\":\"Syed\",\"MiddleName\":\"M.\",\"LastName\":\"Zaidi\"},\"User\":{\"Id\":\"sa\",\"FirstName\":\"Sam\",\"MiddleName\":\"M.\",\"LastName\":\"Arsenault\"}}]},\"Messages\":[]}}";

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "ELECWAT001");
		params.put("CustomerId", "CUSTOMER007");
		CommonMethods.getMethodContainsString(uri, version, params, expected);
		//CommonMethods.getMethodContainsString(uri, version, params, expected2);

	}

	@Test(priority = 7, groups = "contactLogController" )
	public void contactLoggetContactLogActionCodes()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/contactLog/getContactLogActionCodes";
		String ver = "4.0";
		String jpath = "./\\TestData\\getContactLogActionCodesv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);

	}

	@Test(priority = 6, groups = "contactLogController" )
	public void getContactLogMethods() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/contactLog/getContactLogMethods";
		String ver = "4.0";
		String jpath = "./\\TestData\\getContactLogMethodsv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);

	}

	@Test(priority = 8, groups = "contactLogController" )
	public void getContactLogServiceOrderRequest()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/contactLog/getContactLogServiceOrderRequest";
		String version = "4.0";
		String expected = "{\"result\":[{\"RequestID\":\"TRANSFER2\",\"ServiceOrderDescription\":\"\"},{\"RequestID\":\"REQ-NSF\",\"ServiceOrderDescription\":\"Collection for NSF cheque\"},{\"RequestID\":\"REQ-COLL\",\"ServiceOrderDescription\":\"Collection request\"},{\"RequestID\":\"DEP-REQ-NEW\",\"ServiceOrderDescription\":\"deposit for new customer\"},{\"RequestID\":\"REQ-DEP-WATER\",\"ServiceOrderDescription\":\"Deposit request\"},{\"RequestID\":\"REQ-DISCON-ELE\",\"ServiceOrderDescription\":\"Disaconnection-electric meter\"},{\"RequestID\":\"DISCONNECT\",\"ServiceOrderDescription\":\"Disconnected connection\"},{\"RequestID\":\"REQ-DISCON-S\",\"ServiceOrderDescription\":\"Disconnection-sewer meter\"},{\"RequestID\":\"REQ-DISCON-W\",\"ServiceOrderDescription\":\"Disconnection-water meter\"},{\"RequestID\":\"REQ-INSTALL-E\",\"ServiceOrderDescription\":\"Installation of a new mwter - electric\"},{\"RequestID\":\"REQ-EST-ELE\",\"ServiceOrderDescription\":\"Meter reading for estimate electric\"},{\"RequestID\":\"REQ-EST-GAS\",\"ServiceOrderDescription\":\"Meter reading for estimate gas\"},{\"RequestID\":\"REQ-EST-SEWER\",\"ServiceOrderDescription\":\"Meter reading for estimate sewer\"},{\"RequestID\":\"REQ-EST-WATER\",\"ServiceOrderDescription\":\"meter reading for estimate water\"},{\"RequestID\":\"REQ-SWITCH-E\",\"ServiceOrderDescription\":\"Meter switch - electric\"},{\"RequestID\":\"MOVEIN\",\"ServiceOrderDescription\":\"Move In Customer\"},{\"RequestID\":\"REQ-EST-PH\",\"ServiceOrderDescription\":\"Phone estimate\"},{\"RequestID\":\"REQ-EST-INT\",\"ServiceOrderDescription\":\"Request for internet service\"},{\"RequestID\":\"TRANSFER\",\"ServiceOrderDescription\":\"Transfer location\"}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, version, params);
		Assert.assertEquals(expected, result);

	}

	@Test(priority = 9, groups = "contactLogController" )
	public static void putcontactLogv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.Bug("CPDEV-18576");
		String uri = "/contactLog";
		String ver = "4.0";
		String jpath = "./\\TestData\\putContactLog.json";
		String params = new String(Files.readAllBytes(Paths.get(jpath)));
		String expected = "./\\TestData\\putCheckexpectedsendtoApi_v4.json";
		CommonMethods.putMethod(uri, ver, params, expected);

	}

}