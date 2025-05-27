package Public;

import org.testng.annotations.Test; import org.testng.Assert;

import org.testng.annotations.Test; import org.testng.Assert;


import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test; import org.testng.Assert;

import com.NexustAPIAutomation.java.CommonMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

public class customerControllerV4 {

	@Test(priority = 1, groups = "CustomerController" )
	public static void getlocationsByCustomerv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/customers/CUSTOMER014/locationsByCustomer";
		String ver = "4.0";
		String expected = "{\"LocationsByCustomer\":[{\"PageNumber\":1,\"TotalPages\":1,\"Customer\":[{\"Id\":\"CUSTOMER014\",\"Type\":\"Primary\",\"Name\":\"Test Name\",\"Title\":\"\",\"FirstName\":\"Test\",\"MiddleName\":\"\",\"LastName\":\"Name\"}],\"Location\":[{\"Id\":\"WATER002\",\"Description\":\"\",\"STATUS\":\"Current\",\"Class\":\"\",\"ServiceAddress\":[{\"Line1\":\"100 Water\",\"DetailLine1\":\"100 Water\",\"DetailLine2\":\"\",\"City\":\"NEW YORK\",\"State\":\"NY\",\"ZipCode\":\"32541\",\"Country\":\"USA\",\"Id\":24}],\"PrimaryCustomerId\":\"CUSTOMER014\",\"FinanciallyResponsible\":true,\"AccountBalance\":0,\"MasterIncluded\":\"1\",\"MoveInDate\":\"\",\"MoveOutDate\":\"\",\"ZoneId\":\"3\",\"RouteId\":\"ROUTEW001\",\"CycleId\":\"BIMONTHLY\",\"Udl\":[{\"Label\":\"Location001\",\"value\":\"\"},{\"Label\":\"Location002\",\"value\":\"\"}],\"RelatedCustomers\":[{\"Label\":\"Owner ID                      \",\"CustomerId\":\"CUSTOMER005\",\"CustomerName\":\"Mr. Stewart D Brian\"},{\"Label\":\"Tenant                        \",\"CustomerId\":\"\",\"CustomerName\":\"\"},{\"Label\":\"Landlord                      \",\"CustomerId\":\"\",\"CustomerName\":\"\"}]}]}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "WATER002");
		params.put("NumPerPage", "50");
		params.put("OrderBy", "status, locationId");
		params.put("IncludeBalance", "false");
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(actual, expected);

	}

}