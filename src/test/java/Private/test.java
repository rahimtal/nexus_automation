package Private;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.http.ConnectionClosedException;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class test {
	@Test(priority = 1, groups = "Smarty_Street")
	public static boolean verifySmartyStreetService() throws ConnectionClosedException, InterruptedException {
		// CUSTOMER001
		char q = '"';
		RestAssured.baseURI = "http://localhost:3000/api/v4/customers/validateAddresses/";
		String rawbody = "{" + q + "ValidateAddresses " + q
				+ ":[{\"+q+\"Address\":[{\"AddressId\":\"\",\"AddressCode\":\"Primary\",\"ContactPerson\":\"\",\"AddressLabel\":{\"Line1\":\"11985 SW 15th St Bldg 182\",\"City\":\"Pembroke Pines\",\"State\":\"FL\",\"ZipCode\":\"330255785\",\"Country\":\"USA\"},\"Override\":{\"Override\":\"0\",\"AddressCode\":\"\",\"FromDate\":\"1900-01-01\",\"ToDate\":\"1900-01-01\",\"Recurring\":\"0\"},\"PhoneNumber\":[{\"Number\":\"9056150000\",\"TypeId\":\"1\",\"Description\":\"Phone 1\"},{\"Number\":\"9056150002\",\"TypeId\":\"2\",\"Description\":\"Phone 2\"},{\"Number\":\"9056150003\",\"TypeId\":\"3\",\"Description\":\"Phone 3\"}],\"FaxNumber\":{\"Number\":\"50688943210000\"},\"SmartyStreetsMailingAddressCandidate\":{\"AddressId\":\"\",\"DeliveryLine1\":\"TEST FOR WHEN SMARTY STREETS DISABLED\",\"DeliveryLine2\":\"\",\"LastLine\":\"\",\"StreetNumber\":\"\",\"StreetNumberLng\":0,\"StrNumberSuffix\":\"\",\"StreetName\":\"\",\"StreetType\":\"\",\"StreetDirectionP\":\"\",\"StreetDirection\":\"\",\"AptNumber\":\"\",\"AptDescription\":\"\",\"UnitDesignation\":\"\",\"AptNumberPlus\":\"\",\"CityName\":\"\",\"State\":\"FL\",\"ZipCode\":\"\",\"Country\":\"\",\"Success\":false,\"MailingAddressMessages\":{\"Messages\":[{\"Enabled\":1,\"Info\":\"\",\"AddressCode\":\"\"}]}}}],\"Acknowledge\":0,\"AddressConfirm\":0,\"Success\":false,\"Messages\":[{\"Enabled\":1,\"Info\":\"\",\"AddressCode\":\"\"}]}]}";

		RequestSpecification httpRequest = RestAssured.given()
				.headers("Authorization", "Bearer " + getToken(), "Content-Type", ContentType.JSON, "Accept", "*/*",
						"Connection", "keep-alive", "Accept-Encoding", "gzip, deflate, br")
				.body(rawbody);

		Response response = httpRequest.put();

		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();

		// Then simply query the JsonPath object to get a String value of the node
		// specified by JsonPath: City (Note: You should not put $. in the Java code)
		Boolean result = jsonPathEvaluator.get("SPACancel[0].Success");
		// System.out.println(getToken());
		if (result == false) {
			System.out.println("smarty sreet is down");
			return false;
		}

		System.out.println("Smarty street is working");
		return result;

	}
	
	public static String getToken() throws InterruptedException {


		String keycloakurl = "http://localhost:8080";
		String apiserverurl = "http://localhost:3000";
		String userName = "sa";// Read.ReadFile("username");
		String Password = "cogs1";// Read.ReadFile("PassWord");
		
		
		String url = keycloakurl + "/auth/realms/nexus-portal/protocol/openid-connect/token";

		Response response = RestAssured.given().auth().preemptive().basic("nexus-portal", url)
				.contentType("application/x-www-form-urlencoded").log().all().formParam("grant_type", "password")
				.formParam("username", userName).formParam("password", Password).when().post(url); // authorization_token

		if (response.path("access_token").toString() == "") {
			AssertJUnit.fail("Austhorisation failed");
		}
		// null - has a value
		// The auth token could then be set to a string variable

		String auth_token = response.path("access_token").toString();
		return auth_token;

	}
}
