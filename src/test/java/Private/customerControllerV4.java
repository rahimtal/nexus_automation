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
		String uri = "/customer/updateCustomerCard";
		String ver = "4.0";
		String jpath = "./\\TestData\\putcustomerupdateCustomerCardv4.json";
		String response = "{\"result\":[{\"success\":true,\"message\":\"Customer Card Updated.\"}]}";
		CommonMethods.putMethodstring(uri, ver, jpath, response);

	}

	@Test(priority = 6, groups = "CustomerController")
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
		String uri = "/customers/info";
		String ver = "4.0";
		String jpath = "./\\TestData\\putcustomersinfov4.json";
		String response = "{\"Customer\":[{\"Acknowledge\":0,\"Success\":true,\"Messages\":[{\"Enabled\":1,\"Info\":\"Customer updated.\"}]}]}";
		CommonMethods.putMethodstring(uri, ver, jpath, response);

	}

}