package com.NexusAPI.Tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.Assert;

import com.NexustAPIAutomation.java.CommonMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

public class Private_customerControllerV4_Test extends BaseClass {

	@Test(priority = 1, groups = "CustomerController")
	public static void getCustomerBasicInfo4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		// CommonMethods.Bugs("CPDEV-21251");
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
		// Still a Bug (2025)
		CommonMethods.Bug("CPDEV-20931");
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
	public static void getSecondaryCustomerv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/customers/getSecondaryCustomer";
		String ver = "4.0";
		String expected = "{\"result\":[{\"CustomerID\":\"03332301204\",\"CustomerStatus\":\"Current\",\"CustomerType\":\"Individual\",\"CustomerTitle\":\"Mr.\",\"CustomerFirstName\":\"Talha\",\"CustomerMiddleName\":\"\",\"CustomerLastName\":\"Rahim\",\"CustomerName\":\"Mr. Talha Rahim\",\"ssn\":\"\",\"EffectiveFromDate\":\"May 01, 2019\",\"EffectiveToDate\":\"\",\"RelationShip\":\"\",\"RelationShipSsn\":\"\",\"FinancialResponsible\":\"Responsible\",\"DrillbackLink\":\"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=SecondaryCustomer&LocationID=ELECWAT001&CustomerID=CUSTOMER007&Address=12 A GRAFTON HILLS n APT 4 NEW YORK NY 12123&CustName=Miss Sally E MacKenzie&CogsDrillback=1\"}]}";

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("CustomerId", "CUSTOMER007");
		params.put("LocationId", "ELECWAT001");

		CommonMethods.getMethodContainsString(uri, ver, params, expected);

	}

	@Test(priority = 3, groups = "CustomerController")
	public static void getSecondaryCustomerv4All()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/customers/getSecondaryCustomer";
		String ver = "4.0";
		String expected = "{\"result\":[{\"CustomerID\":\"03332301204\",\"CustomerStatus\":\"Current\",\"CustomerType\":\"Individual\",\"CustomerTitle\":\"Mr.\",\"CustomerFirstName\":\"Talha\",\"CustomerMiddleName\":\"\",\"CustomerLastName\":\"Rahim\",\"CustomerName\":\"Mr. Talha Rahim\",\"ssn\":\"\",\"EffectiveFromDate\":\"May 01, 2019\",\"EffectiveToDate\":\"\",\"RelationShip\":\"\",\"RelationShipSsn\":\"\",\"FinancialResponsible\":\"Responsible\",\"DrillbackLink\":\"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=SecondaryCustomer&LocationID=ELECWAT001&CustomerID=CUSTOMER007&Address=12 A GRAFTON HILLS n APT 4 NEW YORK NY 12123&CustName=Miss Sally E MacKenzie&CogsDrillback=1\"}]}";

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("CustomerId", "CUSTOMER007");
		params.put("SecondaryCustomerId", "03332301204");
		params.put("LocationId", "ELECWAT001");

		CommonMethods.getMethodContainsString(uri, ver, params, expected);

	}

	@Test(priority = 4, groups = "CustomerController")
	public void getSecondaryr4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

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
	public void putupdateCustomerCard() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.Bugs("CPDEV-20936");
		CommonMethods.Bug("CPDEV-25665");
		String uri = "/customer/updateCustomerCard";
		String ver = "4.0";
		String jpath = "{\r\n" + "	\"CustomerId\": \"CUSTOMER003\",\r\n" + "	\"AddressLine1\": \"6 JIM BLVD\",\r\n"
				+ "	\"AddressCity\": \"NEW YORK\",\r\n" + "	\"AddressState\": \"NY\",\r\n"
				+ "	\"AddressZipCode\": \"65422\",\r\n" + "	\"AddressCountry\": \"USA\",\r\n"
				+ "	\"EmailAddress\": \"cmacdonald@gmail.com\",\r\n" + "	\"PhoneNumber\": [\r\n" + "		{\r\n"
				+ "			\"Number\": \"9028888888\",\r\n" + "			\"TypeID\":\"1\",\r\n"
				+ "			\"Description\": \"Phone 1\"\r\n" + "		},\r\n" + "		{\r\n"
				+ "			\"Number\": \"\",\r\n" + "			\"TypeID\":\"2\",\r\n"
				+ "			\"Description\": \"Phone 2\"\r\n" + "		},\r\n" + "		{\r\n"
				+ "			\"Number\": \"\",\r\n" + "			\"TypeID\":\"3\",\r\n"
				+ "			\"Description\": \"Phone 3\"\r\n" + "		}\r\n" + "	]\r\n" + "}\r\n" + "";
		String response = "{\"result\":[{\"success\":true,\"message\":\"Customer Card Updated.\"}]}";
		CommonMethods.putMethodstring(uri, ver, jpath, response);

	}

	// @Test(priority = 6, groups = "CustomerController" )
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

		// CommonMethods.Bugs("CPDEV-20941");
		CommonMethods.Bug("CPDEV-25670");
		String uri = "/customers/info";
		String ver = "4.0";
		String jpath = "{\r\n" + "    \"Customer\": [\r\n" + "        {\r\n"
				+ "            \"CustomerId\": \"CUSTOMER009\",\r\n" + "            \"CustomerType\": {\r\n"
				+ "                \"Id\": 1,\r\n" + "                \"Description\": \"Individual\"\r\n"
				+ "            },\r\n" + "            \"Inactive\":true,\r\n"
				+ "               \"CustomerClass\": {\r\n" + "               \"Id\": \"RES-METERED\",\r\n"
				+ "                \"Description\": \"Residental Metered\"\r\n" + "            },\r\n"
				+ "            \"Name\": {\r\n" + "                \"Title\": {\r\n"
				+ "                    \"Id\": \"2\",\r\n" + "                    \"Description\": \"Mr.\"\r\n"
				+ "                },\r\n" + "                \"FirstName\": \"Anthony\",\r\n"
				+ "                \"MiddleName\": \"W\",\r\n" + "                \"LastName\": \"Bendetto\",\r\n"
				+ "                \"NameSuffix\": \"\",\r\n" + "                \"MaidenName\": \"\"\r\n"
				+ "            },\r\n" + "            \"PlaceOfWork\": {\r\n" + "                \"Value\": \"\",\r\n"
				+ "                \"IsDirty\": false\r\n" + "            },\r\n"
				+ "            \"PrimaryLanguage\": \"\",\r\n"
				+ "            \"EmailAddress\": \"uCustomer009@cogsdale.com\",\r\n" + "            \"SSN\": {\r\n"
				+ "                \"Value\": \"765765\",\r\n" + "                \"IsDirty\": false\r\n"
				+ "            },\r\n" + "            \"DateOfBirth\": {\r\n"
				+ "                \"Value\": \"2031-04-11\",\r\n" + "                \"IsDirty\": false\r\n"
				+ "            },\r\n" + "            \"AlternateId\": {\r\n" + "                \"Value\": \"\",\r\n"
				+ "                \"IssuedBy\": \"\",\r\n" + "                \"Country\": \"USA\",\r\n"
				+ "                \"IsDirty\": false\r\n" + "            },\r\n"
				+ "            \"BusinessAs\": \"\",\r\n" + "            \"Confirm\": 0,\r\n"
				+ "            \"Success\": true,\r\n" + "            \"Messages\": [\r\n" + "                {\r\n"
				+ "                    \"Enabled\": 0,\r\n" + "                    \"Info\": \"\"\r\n"
				+ "                }\r\n" + "            ]\r\n" + "        }\r\n" + "    ]\r\n" + "}";
		String response = "{\"Customer\":[{\"Acknowledge\":0,\"Success\":false,\"Messages\":[{\"Enabled\":1,\"Info\":\"Customer is the current customer for one or more locations and cannot be marked inactive.\"}]}]}";
		CommonMethods.putMethodstring(uri, ver, jpath, response);

	}

	@Test(priority = 8, groups = "CustomerController")
	public void putupdateCustomersInfov4Pos()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		// verified //CommonMethods.Bugs("CPDEV-20941");
		CommonMethods.Bug("CPDEV-25675");

		String uri = "/customers/info";
		String ver = "4.0";
		String jpath = "{\r\n" + "    \"Customer\": [\r\n" + "        {\r\n"
				+ "            \"CustomerId\": \"TESTCUSTOMER1\",\r\n" + "            \"CustomerType\": {\r\n"
				+ "                \"Id\": 1,\r\n" + "                \"Description\": \"Individual\"\r\n"
				+ "            },\r\n" + "            \"Inactive\":true,\r\n"
				+ "               \"CustomerClass\": {\r\n" + "               \"Id\": \"RES-METERED\",\r\n"
				+ "                \"Description\": \"Residental Metered\"\r\n" + "            },\r\n"
				+ "            \"Name\": {\r\n" + "                \"Title\": {\r\n"
				+ "                    \"Id\": \"2\",\r\n" + "                    \"Description\": \"Mr.\"\r\n"
				+ "                },\r\n" + "                \"FirstName\": \"Anthony\",\r\n"
				+ "                \"MiddleName\": \"W\",\r\n" + "                \"LastName\": \"Bendetto\",\r\n"
				+ "                \"NameSuffix\": \"\",\r\n" + "                \"MaidenName\": \"\"\r\n"
				+ "            },\r\n" + "            \"PlaceOfWork\": {\r\n" + "                \"Value\": \"\",\r\n"
				+ "                \"IsDirty\": false\r\n" + "            },\r\n"
				+ "            \"PrimaryLanguage\": \"\",\r\n"
				+ "            \"EmailAddress\": \"uCustomer009@cogsdale.com\",\r\n" + "            \"SSN\": {\r\n"
				+ "                \"Value\": \"765765\",\r\n" + "                \"IsDirty\": false\r\n"
				+ "            },\r\n" + "            \"DateOfBirth\": {\r\n"
				+ "                \"Value\": \"2031-04-11\",\r\n" + "                \"IsDirty\": false\r\n"
				+ "            },\r\n" + "            \"AlternateId\": {\r\n" + "                \"Value\": \"\",\r\n"
				+ "                \"IssuedBy\": \"\",\r\n" + "                \"Country\": \"USA\",\r\n"
				+ "                \"IsDirty\": false\r\n" + "            },\r\n"
				+ "            \"BusinessAs\": \"\",\r\n" + "            \"Confirm\": 0,\r\n"
				+ "            \"Success\": true,\r\n" + "            \"Messages\": [\r\n" + "                {\r\n"
				+ "                    \"Enabled\": 0,\r\n" + "                    \"Info\": \"\"\r\n"
				+ "                }\r\n" + "            ]\r\n" + "        }\r\n" + "    ]\r\n" + "}";
		String response = "{\"Customer\":[{\"Acknowledge\":0,\"Success\":true,\"Messages\":[{\"Enabled\":1,\"Info\":\"Customer updated.\"}]}]}";
		CommonMethods.putMethodstring(uri, ver, jpath, response);

	}

	@Test(priority = 9, groups = "CustomerController")
	public void putpreauthorizedPaymentPlanv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/customers/preauthorizedPaymentPlan";
		String ver = "4.0";
		String jpath = "{\r\n" + //
				"      \"CustomerId\":\"50000202\",\r\n" + //
				"      \"PaymentPlan\": [\r\n" + //
				"        {\r\n" + //
				"            \"LocationId\": \"LOC@0009\",\r\n" + //
				"            \"BankBranchTransit\": \"01236\",\r\n" + //
				"            \"BankAccountNumber\": \"0123505456\",\r\n" + //
				"            \"InstId\": \"041\"\r\n" + //
				"        }\r\n" + //
				"      ]\r\n" + //
				"}";
		String response = "{\"PreauthorizedPaymentPlan\":{\"Success\":true,\"Data\":null,\"Messages\":[]}}";
		CommonMethods.putMethodstring(uri, ver, jpath, response);

	}

	@Test(priority = 10, groups = "CustomerController")
	public void getCustomerAddress()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/customers/500001/address";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);

		// Validate using JsonPath to avoid order-dependent comparison
		JsonPath jsonPath = new JsonPath(result);
		Assert.assertEquals(jsonPath.getString("AddressInformation[0].CustomerId"), "500001");
		Assert.assertTrue(jsonPath.getBoolean("AddressInformation[0].Success"));
		Assert.assertEquals(jsonPath.getInt("AddressInformation[0].Acknowledge"), 0);
		Assert.assertEquals(jsonPath.getInt("AddressInformation[0].AddressConfirm"), 0);

		// Verify all 3 addresses are present regardless of order
		int addressCount = jsonPath.getList("AddressInformation[0].Address").size();
		Assert.assertEquals(addressCount, 3);

		// Verify each address exists by AddressId
		java.util.List<String> addressIds = jsonPath.getList("AddressInformation[0].Address.AddressId");
		Assert.assertTrue(addressIds.contains("83"), "PRIMARY address (Id 83) should be present");
		Assert.assertTrue(addressIds.contains("159"), "Third address (Id 159) should be present");
		Assert.assertTrue(addressIds.contains("160"), "Fourth address (Id 160) should be present");
	}

	@Test(priority = 11, groups = "CustomerController")
	public void getCustomerAddressVerifyPrimary()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/customers/500001/address";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);

		JsonPath jsonPath = new JsonPath(result);
		java.util.List<java.util.Map<String, Object>> addresses = jsonPath.getList("AddressInformation[0].Address");

		// Find PRIMARY address and validate its fields
		java.util.Map<String, Object> primaryAddress = null;
		for (java.util.Map<String, Object> addr : addresses) {
			if ("PRIMARY".equals(addr.get("AddressCode"))) {
				primaryAddress = addr;
				break;
			}
		}
		Assert.assertNotNull(primaryAddress, "PRIMARY address should exist");
		Assert.assertEquals(primaryAddress.get("AddressId"), "83");
		Assert.assertEquals(primaryAddress.get("ContactPerson"), "Mr. Gregg  Lammon");
		Assert.assertEquals(primaryAddress.get("BillToAddress"), "0");
	}

	@Test(priority = 12, groups = "CustomerController")
	public void getCustomerAddressVerifyThird()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/customers/500001/address";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);

		JsonPath jsonPath = new JsonPath(result);
		java.util.List<java.util.Map<String, Object>> addresses = jsonPath.getList("AddressInformation[0].Address");

		// Find Third address and validate BillToAddress is "1"
		java.util.Map<String, Object> thirdAddress = null;
		for (java.util.Map<String, Object> addr : addresses) {
			if ("Third".equals(addr.get("AddressCode"))) {
				thirdAddress = addr;
				break;
			}
		}
		Assert.assertNotNull(thirdAddress, "Third address should exist");
		Assert.assertEquals(thirdAddress.get("AddressId"), "159");
		Assert.assertEquals(thirdAddress.get("BillToAddress"), "1");
		Assert.assertEquals(thirdAddress.get("ContactPerson"), "Mr. Gregg  Lammon");
	}

	@Test(priority = 13, groups = "CustomerController")
	public void getCustomerAddressVerifyFourth()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/customers/500001/address";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);

		JsonPath jsonPath = new JsonPath(result);
		java.util.List<java.util.Map<String, Object>> addresses = jsonPath.getList("AddressInformation[0].Address");

		// Find Fourth address and validate Canada country
		java.util.Map<String, Object> fourthAddress = null;
		for (java.util.Map<String, Object> addr : addresses) {
			if ("Fourth".equals(addr.get("AddressCode"))) {
				fourthAddress = addr;
				break;
			}
		}
		Assert.assertNotNull(fourthAddress, "Fourth address should exist");
		Assert.assertEquals(fourthAddress.get("AddressId"), "160");
		Assert.assertEquals(fourthAddress.get("ContactPerson"), "Andy Dwyer");
		Assert.assertEquals(fourthAddress.get("BillToAddress"), "0");
	}

	@Test(priority = 14, groups = "CustomerController")
	public void getCustomerAddressVerifyOverride()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/customers/500001/address";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);

		JsonPath jsonPath = new JsonPath(result);
		java.util.List<java.util.Map<String, Object>> addresses = jsonPath.getList("AddressInformation[0].Address");

		// Verify PRIMARY has Override enabled, others do not
		for (java.util.Map<String, Object> addr : addresses) {
			java.util.Map<String, Object> override = (java.util.Map<String, Object>) addr.get("Override");
			if ("PRIMARY".equals(addr.get("AddressCode"))) {
				Assert.assertEquals(override.get("Override"), "1");
				Assert.assertEquals(override.get("AddressCode"), "PRIMARY");
				Assert.assertEquals(override.get("FromDate"), "2020-04-01");
				Assert.assertEquals(override.get("ToDate"), "2020-04-02");
			} else {
				Assert.assertEquals(override.get("Override"), "0");
			}
		}
	}

	@Test(priority = 15, groups = "CustomerController")
	public void getCustomerAddressVerifyPhoneNumbers()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/customers/500001/address";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);

		JsonPath jsonPath = new JsonPath(result);
		java.util.List<java.util.Map<String, Object>> addresses = jsonPath.getList("AddressInformation[0].Address");

		for (java.util.Map<String, Object> addr : addresses) {
			java.util.List<java.util.Map<String, Object>> phones = (java.util.List<java.util.Map<String, Object>>) addr
					.get("PhoneNumber");
			Assert.assertEquals(phones.size(), 3, "Each address should have 3 phone numbers");

			if ("Third".equals(addr.get("AddressCode")) || "Fourth".equals(addr.get("AddressCode"))) {
				Assert.assertEquals(phones.get(0).get("Number"), "90563011980000");
				Assert.assertEquals(phones.get(1).get("Number"), "90255512340000");
				Assert.assertEquals(phones.get(2).get("Number"), "50678912340000");
			} else if ("PRIMARY".equals(addr.get("AddressCode"))) {
				// PRIMARY has empty phone numbers
				Assert.assertEquals(phones.get(0).get("Number"), "");
				Assert.assertEquals(phones.get(1).get("Number"), "");
				Assert.assertEquals(phones.get(2).get("Number"), "");
			}
		}
	}

	@Test(priority = 16, groups = "CustomerController")
	public void getCustomerAddressVerifySmartyStreets()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/customers/500001/address";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);

		JsonPath jsonPath = new JsonPath(result);
		java.util.List<java.util.Map<String, Object>> addresses = jsonPath.getList("AddressInformation[0].Address");

		for (java.util.Map<String, Object> addr : addresses) {
			java.util.Map<String, Object> smarty = (java.util.Map<String, Object>) addr
					.get("SmartyStreetsMailingAddressCandidate");
			Assert.assertTrue((Boolean) smarty.get("Success"),
					"SmartyStreets Success should be true for address " + addr.get("AddressCode"));

			if ("PRIMARY".equals(addr.get("AddressCode"))) {
				Assert.assertEquals(smarty.get("StreetName"), "ORCHARD");
				Assert.assertEquals(smarty.get("StreetType"), "AVE");
				Assert.assertEquals(smarty.get("CityName"), "TROY");
				Assert.assertEquals(smarty.get("StreetNumber"), "8");
			} else if ("Fourth".equals(addr.get("AddressCode"))) {
				Assert.assertEquals(smarty.get("CityName"), "Stratford");
				Assert.assertEquals(smarty.get("State"), "PE");
				Assert.assertEquals(smarty.get("Country"), "Canada");
			}
		}
	}

}