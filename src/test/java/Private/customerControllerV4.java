package Private;

import org.testng.annotations.Test;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

public class customerControllerV4 {

	@Test(priority = 1, groups = "CustomerController")
	public static void getCustomerBasicInfo4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/customers/AUTO1001/info";
		String ver = "4.0";
		String jpath = "./\\TestData\\getCustomerBasicInfo4.json";

		HashMap<String, String> params = new HashMap<String, String>();
		/*
		 * params.put("LocationId", "LOCATION008"); params.put("NumPerPage", "50");
		 * params.put("OrderBy", "status, locationId"); params.put("PageNum", "1");
		 */

		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);

	}

	@Test(priority = 2, groups = "CustomerController")
	public static void getCustomerAddressInfo()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/customers/AUTO1001/address";
		String ver = "4.0";
		String jpath = "./\\TestData\\getCustomerAddressInfov4.json";

		HashMap<String, String> params = new HashMap<String, String>();
		/*
		 * params.put("LocationId", "TESTLOCATION03"); params.put("NumPerPage", "50");
		 * params.put("OrderBy", "status, locationId"); params.put("PageNum", "1");
		 * params.put("ExcludeFormerLocationsWithZeroBalance", "1");
		 */

		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);

	}

	@Test(priority = 3, groups = "CustomerController")
	public static void getSecondaryCustomer()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/customers/getSecondaryCustomer";
		String ver = "4.0";
		String jpath = "./\\TestData\\getSecondaryCustomerv4.json";

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("CustomerId", "CUSTOMER007");
		params.put("LocationId", "ELECWAT001");

		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);

	}

	@Test(priority = 4, groups = "CustomerController")
	public void getSecondaryCustomer2() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/customers/Secondary";
		String ver = "4.0";
		String jpath = "./\\TestData\\getSecondaryCustomer2v4.json";

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("PrimaryCustomerId", "CUSTOMER007");
		params.put("SecondaryCustomerId", "03332301204");

		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);

	}

	@Test(priority = 5, groups = "CustomerController")
	public void putupdateCustomerCard()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		//CommonMethods.Bug("CPDEV-18811");
		String uri = "/customer/updateCustomerCard";
		String ver = "4.0";
		String jpath = "{\r\n" + 
				"	\"CustomerId\": \"CUSTOMER003\",\r\n" + 
				"	\"AddressLine1\": \"6 JIM BLVD\",\r\n" + 
				"	\"AddressCity\": \"NEW YORK\",\r\n" + 
				"	\"AddressState\": \"NY\",\r\n" + 
				"	\"AddressZipCode\": \"65422\",\r\n" + 
				"	\"AddressCountry\": \"USA\",\r\n" + 
				"	\"EmailAddress\": \"cmacdonald@gmail.com\",\r\n" + 
				"	\"PhoneNumber\": [\r\n" + 
				"		{\r\n" + 
				"			\"Number\": \"9028888888\",\r\n" + 
				"			\"TypeID\":\"1\",\r\n" + 
				"			\"Description\": \"Phone 1\"\r\n" + 
				"		},\r\n" + 
				"		{\r\n" + 
				"			\"Number\": \"\",\r\n" + 
				"			\"TypeID\":\"2\",\r\n" + 
				"			\"Description\": \"Phone 2\"\r\n" + 
				"		},\r\n" + 
				"		{\r\n" + 
				"			\"Number\": \"\",\r\n" + 
				"			\"TypeID\":\"3\",\r\n" + 
				"			\"Description\": \"Phone 3\"\r\n" + 
				"		}\r\n" + 
				"	]\r\n" + 
				"}\r\n" + 
				"";
		String response = "{\"result\":[{\"success\":true,\"message\":\"Customer Card Updated.\"}]}";
		CommonMethods.putMethodstring(uri, ver, jpath, response);

	}

//	@Test(priority = 6, groups = "CustomerController")
	public void putupdateCustomersCard()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/customers/updateCustomerCard";
		String ver = "4.0";
		String jpath = "./\\TestData\\putcustomerupdateCustomerCardv4.json";
		String response = "{\"result\":[{\"success\":true,\"message\":\"Customer Card Updated.\"}]}";
		CommonMethods.putMethodstring(uri, ver, jpath, response);

	}

	@Test(priority = 7, groups = "CustomerController")
	public void putupdateCustomersInfov4()
				throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		
		//CommonMethods.Bug("CPDEV-18816");
		String uri = "/customers/info";
		String ver = "4.0";
		String jpath = "{\r\n" + 
				"    \"Customer\": [\r\n" + 
				"        {\r\n" + 
				"            \"CustomerId\": \"CUSTOMER009\",\r\n" + 
				"            \"CustomerType\": {\r\n" + 
				"                \"Id\": 1,\r\n" + 
				"                \"Description\": \"Individual\"\r\n" + 
				"            },\r\n" + 
				"            \"Inactive\":true,\r\n" + 
				"               \"CustomerClass\": {\r\n" + 
				"               \"Id\": \"RES-METERED\",\r\n" + 
				"                \"Description\": \"Residental Metered\"\r\n" + 
				"            },\r\n" + 
				"            \"Name\": {\r\n" + 
				"                \"Title\": {\r\n" + 
				"                    \"Id\": \"2\",\r\n" + 
				"                    \"Description\": \"Mr.\"\r\n" + 
				"                },\r\n" + 
				"                \"FirstName\": \"Anthony\",\r\n" + 
				"                \"MiddleName\": \"W\",\r\n" + 
				"                \"LastName\": \"Bendetto\",\r\n" + 
				"                \"NameSuffix\": \"\",\r\n" + 
				"                \"MaidenName\": \"\"\r\n" + 
				"            },\r\n" + 
				"            \"PlaceOfWork\": {\r\n" + 
				"                \"Value\": \"\",\r\n" + 
				"                \"IsDirty\": false\r\n" + 
				"            },\r\n" + 
				"            \"PrimaryLanguage\": \"\",\r\n" + 
				"            \"EmailAddress\": \"uCustomer009@cogsdale.com\",\r\n" + 
				"            \"SSN\": {\r\n" + 
				"                \"Value\": \"765765\",\r\n" + 
				"                \"IsDirty\": false\r\n" + 
				"            },\r\n" + 
				"            \"DateOfBirth\": {\r\n" + 
				"                \"Value\": \"2031-04-11\",\r\n" + 
				"                \"IsDirty\": false\r\n" + 
				"            },\r\n" + 
				"            \"AlternateId\": {\r\n" + 
				"                \"Value\": \"\",\r\n" + 
				"                \"IssuedBy\": \"\",\r\n" + 
				"                \"Country\": \"USA\",\r\n" + 
				"                \"IsDirty\": false\r\n" + 
				"            },\r\n" + 
				"            \"BusinessAs\": \"\",\r\n" + 
				"            \"Confirm\": 0,\r\n" + 
				"            \"Success\": true,\r\n" + 
				"            \"Messages\": [\r\n" + 
				"                {\r\n" + 
				"                    \"Enabled\": 0,\r\n" + 
				"                    \"Info\": \"\"\r\n" + 
				"                }\r\n" + 
				"            ]\r\n" + 
				"        }\r\n" + 
				"    ]\r\n" + 
				"}";
		String response = "{\"Customer\":[{\"Acknowledge\":0,\"Success\":false,\"Messages\":[{\"Enabled\":1,\"Info\":\"Customer is the current customer for one or more locations and cannot be marked inactive.\"}]}]}";
		CommonMethods.putMethodstring(uri, ver, jpath, response);

	}
	
	@Test(priority = 8, groups = "CustomerController")
	public void putupdateCustomersInfov4Pos()
				throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		
		//CommonMethods.Bug("CPDEV-18816");
		String uri = "/customers/info";
		String ver = "4.0";
		String jpath = "{\r\n" + 
				"    \"Customer\": [\r\n" + 
				"        {\r\n" + 
				"            \"CustomerId\": \"TESTCUSTOMER1\",\r\n" + 
				"            \"CustomerType\": {\r\n" + 
				"                \"Id\": 1,\r\n" + 
				"                \"Description\": \"Individual\"\r\n" + 
				"            },\r\n" + 
				"            \"Inactive\":true,\r\n" + 
				"               \"CustomerClass\": {\r\n" + 
				"               \"Id\": \"RES-METERED\",\r\n" + 
				"                \"Description\": \"Residental Metered\"\r\n" + 
				"            },\r\n" + 
				"            \"Name\": {\r\n" + 
				"                \"Title\": {\r\n" + 
				"                    \"Id\": \"2\",\r\n" + 
				"                    \"Description\": \"Mr.\"\r\n" + 
				"                },\r\n" + 
				"                \"FirstName\": \"Anthony\",\r\n" + 
				"                \"MiddleName\": \"W\",\r\n" + 
				"                \"LastName\": \"Bendetto\",\r\n" + 
				"                \"NameSuffix\": \"\",\r\n" + 
				"                \"MaidenName\": \"\"\r\n" + 
				"            },\r\n" + 
				"            \"PlaceOfWork\": {\r\n" + 
				"                \"Value\": \"\",\r\n" + 
				"                \"IsDirty\": false\r\n" + 
				"            },\r\n" + 
				"            \"PrimaryLanguage\": \"\",\r\n" + 
				"            \"EmailAddress\": \"uCustomer009@cogsdale.com\",\r\n" + 
				"            \"SSN\": {\r\n" + 
				"                \"Value\": \"765765\",\r\n" + 
				"                \"IsDirty\": false\r\n" + 
				"            },\r\n" + 
				"            \"DateOfBirth\": {\r\n" + 
				"                \"Value\": \"2031-04-11\",\r\n" + 
				"                \"IsDirty\": false\r\n" + 
				"            },\r\n" + 
				"            \"AlternateId\": {\r\n" + 
				"                \"Value\": \"\",\r\n" + 
				"                \"IssuedBy\": \"\",\r\n" + 
				"                \"Country\": \"USA\",\r\n" + 
				"                \"IsDirty\": false\r\n" + 
				"            },\r\n" + 
				"            \"BusinessAs\": \"\",\r\n" + 
				"            \"Confirm\": 0,\r\n" + 
				"            \"Success\": true,\r\n" + 
				"            \"Messages\": [\r\n" + 
				"                {\r\n" + 
				"                    \"Enabled\": 0,\r\n" + 
				"                    \"Info\": \"\"\r\n" + 
				"                }\r\n" + 
				"            ]\r\n" + 
				"        }\r\n" + 
				"    ]\r\n" + 
				"}";
		String response = "{\"Customer\":[{\"Acknowledge\":0,\"Success\":true,\"Messages\":[{\"Enabled\":1,\"Info\":\"Customer updated.\"}]}]}";
		CommonMethods.putMethodstring(uri, ver, jpath, response);

	}


}