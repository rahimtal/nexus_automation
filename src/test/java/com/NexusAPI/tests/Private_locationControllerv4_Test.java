package com.NexusAPI.Tests;

import org.testng.annotations.Test; import org.testng.Assert;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;


public class Private_locationControllerv4_Test  extends BaseClass{

	@Test(priority = 1, groups = "locationController" )
	public void dellocationv4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/location/locdeltest";
		String ver = "4.0";
		String expected = "{\"Location\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"locdeltest Deleted Successfully.\",\"Level\":1}]}}";
		CommonMethods.deleteMethodvoid(uri, ver, expected);

	}

	@Test(priority = 2, groups = "locationController" )
	public void getlocationClassv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/location/class/NONCUST-LOC";
		String ver = "4.0";
		String expected = "{\"LocationClass\":{\"Success\":true,\"Data\":{\"ClassId\":\"NONCUST-LOC\",\"Description\":\"Non customer Location\",\"CompanyId\":\"\",\"Zone\":{\"Id\":\"\",\"Description\":\"\"},\"LocationType\":{\"Id\":3,\"Description\":\"Noncustomer Location\"},\"CustomerType\":{\"Id\":3,\"Description\":\"Third Party\"},\"CollectionType\":{\"CurrentCustomer\":\"\",\"FormerCustomer\":\"\",\"BudgetCustomer\":\"\",\"SPACustomer\":\"\"},\"CheckbookId\":\"\",\"ConnectionOnSameRoute\":false,\"AllowSPATransfer\":true,\"AllowTransferWithoutFinalBill\":false,\"AllowMultiServiceDiscount\":false,\"MultiServiceDiscountId\":\"\"},\"Messages\":[]}}";
		// CommonMethods.deleteMethodvoid(uri, ver, expected);
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationClassId", "NONCUST-LOC");
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(actual, expected);

	}

	@Test(priority = 3, groups = "locationController" )
	public void postbillingOptionv4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// JsonPath jsonPathEvaluator;

		String uri = "/location/billingOption/validate";
		String ver = "4.0";
		String payload = "{\r\n" + "    \"LocationId\":\"WATER005\",\r\n" + "    \"Billing\":{\r\n"
				+ "        \"AccumulatedBilling\":{\r\n" + "            \"Type\":1,\r\n"
				+ "            \"MasterId\":\"\"\r\n" + "        }\r\n" + "    },\r\n" + "    \"ServiceOptions\":{\r\n"
				+ "        \"Service\":[\r\n" + "            {\r\n" + "                \"CategoryId\":1,\r\n"
				+ "                \"AccountReceivableIndex\":1\r\n" + "            },\r\n" + "            {\r\n"
				+ "                \"CategoryId\":2,\r\n" + "                \"AccountReceivableIndex\":1\r\n"
				+ "            },\r\n" + "            {\r\n" + "                \"CategoryId\":3,\r\n"
				+ "                \"AccountReceivableIndex\":1\r\n" + "            },\r\n" + "                {\r\n"
				+ "                \"CategoryId\":4,\r\n" + "                \"AccountReceivableIndex\":1\r\n"
				+ "            },\r\n" + "            {\r\n" + "                \"CategoryId\":5,\r\n"
				+ "                \"AccountReceivableIndex\":1\r\n" + "            },\r\n" + "            {\r\n"
				+ "                \"CategoryId\":6,\r\n" + "                \"AccountReceivableIndex\":1\r\n"
				+ "            }\r\n" + "        ]\r\n" + "    }\r\n" + "}";
		String exResponse = "{\"Location\":{\"Success\":true,\"Data\":{\"InvalidAccumType\":false,\"InvalidMasterId\":false,\"InvalidServiceAccountIndex\":false,\"MessageIncludeDocument\":true},\"Messages\":[{\"Enabled\":1,\"Info\":\"There are documents for the location\\/customer that are not included in the customer statement. Do you want the documents to be included in the Master Location statement?\",\"Level\":1}]}}";
		CommonMethods.postMethodString(payload, uri, ver, exResponse);

	}

	@Test(priority = 4, groups = "locationController" )
	public void lookuptransferThirdPartyDepositv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// JsonPath jsonPathEvaluator;

		String uri = "/lookup/transferThirdPartyDeposit";
		// CommonMethods.CompanyDBRestore();
		String ver = "4.0";
		String expected = "{\"TransferThirdPartyDeposit\":[{\"Id\":2,\"Description\":\"Refund of Difference\"},{\"Id\":3,\"Description\":\"Full Refund\"}]}";
		// CommonMethods.deleteMethodvoid(uri, ver, expected);
		HashMap<String, String> params = new HashMap<String, String>();
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(actual, expected);
	}

	// CPDEV-27210 : Fetch Latitude/Longitude from SmartyStreets (360 Degree Account Panel)
	@Test(priority = 5, groups = "locationController")
	public void getlocationCoordinatesv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String expected = "{\"LocationCoordinates\":{\"Success\":true,\"Data\":{\"Latitude\":42.69252,\"Longitude\":-73.67305},\"Messages\":[]}}";
		Assert.assertEquals(getCoordinates("100001"), expected);
	}

	@Test(priority = 6, groups = "locationController")
	public void getlocationCoordinatesSecondLocationv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String expected = "{\"LocationCoordinates\":{\"Success\":true,\"Data\":{\"Latitude\":42.75115,\"Longitude\":-73.68035},\"Messages\":[]}}";
		Assert.assertEquals(getCoordinates("100003"), expected);
	}

	// Location with an address SmartyStreets cannot resolve returns null coordinates
	@Test(priority = 7, groups = "locationController")
	public void getlocationCoordinatesInvalidAddressv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String expected = "{\"LocationCoordinates\":{\"Success\":true,\"Data\":{\"Latitude\":null,\"Longitude\":null},\"Messages\":[]}}";
		Assert.assertEquals(getCoordinates("100002"), expected);
	}

	// TWO.bak predates CPDEV-27210, so a restored DB has no csmApi_spLocationCoordinatesGet.
	private static String getCoordinates(String locationId) throws InterruptedException, IOException {
		String actual = CommonMethods.getMethodasString("/location/" + locationId + "/coordinates", "4.0",
				new HashMap<String, String>());
		if (actual != null && actual.contains("csmApi_spLocationCoordinatesGet")) {
			throw new SkipException("GET /location/:LocationId/coordinates is not deployed on this environment - "
					+ "csmApi_spLocationCoordinatesGet is missing. Response: " + actual);
		}
		return actual;
	}

}
