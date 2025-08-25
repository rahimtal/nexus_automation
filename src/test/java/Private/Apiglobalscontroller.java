package Private;

import org.testng.Assert;
import org.testng.annotations.Test; import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.annotations.Test; import org.testng.Assert;

import com.NexustAPIAutomation.java.CommonMethods;

import io.restassured.response.Response;



public class Apiglobalscontroller extends BaseClass {

	
	@Test(priority = 1, groups = "GlobalSettings")
	public void putapiGlobalSettingsV4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		
		String uri = "/apiGlobalSettings";
		String ver = "4.0";
		String params = "{\r\n"
				+ "    \"Section\": [\r\n"
				+ "        {\r\n"
				+ "            \"Id\": 1,\r\n"
				+ "            \"Name\": \"default\",\r\n"
				+ "            \"SubSection\": [\r\n"
				+ "                {\r\n"
				+ "                    \"Id\": 1007,\r\n"
				+ "                    \"Name\": \"Penalty\",\r\n"
				+ "                    \"Setting\": [\r\n"
				+ "                        {\r\n"
				+ "                            \"Id\": 1069,\r\n"
				+ "                            \"Name\": \"PenaltyNoOfDays\",\r\n"
				+ "                            \"Label\": \"Penalty No. of days\",\r\n"
				+ "                            \"Description\": \"Calculate penalty number of days after the document due date.\",\r\n"
				+ "                            \"FieldType\": {\r\n"
				+ "                                \"Id\": 5,\r\n"
				+ "                                \"Value\": \"int\"\r\n"
				+ "                            },\r\n"
				+ "                            \"FieldTypeModel\": {\r\n"
				+ "                                \"Id\": 1,\r\n"
				+ "                                \"Value\": \"Request\"\r\n"
				+ "                            },\r\n"
				+ "                            \"EndPoint\": \"\",\r\n"
				+ "                            \"Required\": false,\r\n"
				+ "                            \"CustomValue\": false,\r\n"
				+ "                            \"Display\": {\r\n"
				+ "                                \"Id\": 1,\r\n"
				+ "                                \"Value\": \"Enabled\"\r\n"
				+ "                            },\r\n"
				+ "                            \"Value\": \"200\"\r\n"
				+ "                        }\r\n"
				+ "                    ]\r\n"
				+ "                }\r\n"
				+ "            ]\r\n"
				+ "        }\r\n"
				+ "    ]\r\n"
				+ "}";
		//String params = new String(Files.readAllBytes(Paths.get(jpath)));
		String expected = "{\"result\":[{\"success\":true,\"message\":\"API Global Settings Updated.\"}]}";
		 CommonMethods.putMethodstring(uri, ver, params, expected);

	}
	
	
	@Test(priority = 2, groups = "GlobalSettings")
	public void getapiGlobalSettingsv4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/payment/next";
		String ver = "4.0";
		String expected = "./\\TestData\\gettPaymentNextv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("PrevInWork", "true");
		String result = CommonMethods.getMethodContains(uri, ver, params, expected);
		System.out.println(result);

	}


   }
