package com.NexustAPIAutomation.java;

import org.testng.annotations.Test;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;

import org.testng.annotations.Test;
import org.testng.Assert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.security.sasl.SaslException;

import org.apache.http.ConnectionClosedException;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
//import org.mortbay.util.ajax.JSON;
import org.testng.Assert;
import org.testng.SkipException;

import com.google.gson.Gson;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class CommonMethods {

	public static ReadProjectProperties Read = new ReadProjectProperties();
	public static String userName = Read.ReadFile("username");
	public static String Password = Read.ReadFile("password");
	public static String keycloakurl = Read.ReadFile("keycloakurl");
	public static String urlv1 = Read.ReadFile("urlv1");
	public static String urlv2 = Read.ReadFile("urlv2");
	public static String urlv210 = Read.ReadFile("urlv210");
	public static String urlv220 = Read.ReadFile("urlv220");
	public static String urlv230 = Read.ReadFile("urlv230");
	public static String urlv231 = Read.ReadFile("urlv231");
	public static String urlv240 = Read.ReadFile("urlv240");
	public static String urlv3 = Read.ReadFile("urlv3");
	public static String urlv4 = Read.ReadFile("urlv4");
	public static String urle = Read.ReadFile("urle");

	public static String getToken() throws InterruptedException {
		String url = keycloakurl + "/auth/realms/nexus-portal/protocol/openid-connect/token";
		Response response = RestAssured.given().auth().preemptive().basic("nexus-portal", url)
				.contentType("application/x-www-form-urlencoded").formParam("grant_type", "password")
				.formParam("username", userName).formParam("password", Password).when().post(url); // authorization_token
		CommonMethods.Delay(100); // value is not
		// System.out.println(response.path("error").toString());
		try {
			boolean f = (response.path("error").toString()).contains("invalid_grant");
			if (f == true) {
				// Comment Following to Test Authorization
				Assert.fail("Authorization failed/Invalid Token/Check User Name");
			}
		} catch (NullPointerException e) {

		}
		// The auth token could then be set to a string variable
		String auth_token = response.path("access_token").toString();
		// System.out.println(auth_token);
		return auth_token;

	}

	public static void Delay(int i) throws InterruptedException {
		// TODO Auto-generated method stub
		Thread.sleep(i);

	}

	public static boolean CompanyDBRestore() {

		try {
			CommonMethods.Delay(10000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			PowerShell powerShell = PowerShell.openSession();
			// Execute a command in PowerShell session
			PowerShellResponse response;
			Map<String, String> config = new HashMap<String, String>();
			config.put("maxWait", "200000");
			response = powerShell.configuration(config).executeScript("./\\Configuration\\DBOnlyrestore.ps1");
			System.out.println("Script output:" + response.getCommandOutput());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Scripts got error while rinning DB Scripts, please see logs");

			System.exit(1);
		}

		return true;

	}

	@Test
	void test() throws ConnectionClosedException, InterruptedException {

		String uri = "/cashiering/receipt/adjust";
		String ver = "2.4";
		String payload = "./\\TestData\\recieptAdjust.json";

		// getToken();
		postMethod(payload, uri, ver);

	}

	public static JsonPath postMethod(String fielpath, String uri, String version) throws InterruptedException {

		switch (version) {
			case "1":
				RestAssured.baseURI = urlv1;
				break;
			case "2":
				RestAssured.baseURI = urlv2;
				break;
			case "2.1":
				RestAssured.baseURI = urlv210;
				break;
			case "2.2":
				RestAssured.baseURI = urlv220;
				break;
			case "2.3":
				RestAssured.baseURI = urlv230;
				break;
			case "2.3.1":
				RestAssured.baseURI = urlv231;
				break;
			case "2.4":
				RestAssured.baseURI = urlv240;
				break;
			case "3.0":
				RestAssured.baseURI = urlv3;
				break;
			case "4.0":
				RestAssured.baseURI = urlv4;
				break;
			default:
				Assert.fail("Invalid version");
				version = "Invalid version";
				break;
		}
		File jsonDataInFile = new File(fielpath);
		JSONObject bodycontent = null;
		try (FileReader reader = new FileReader(jsonDataInFile)) {
			// Read JSON file
			JSONParser jsonParser = new JSONParser();
			Object obj = jsonParser.parse(reader);
			bodycontent = (JSONObject) obj;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (ClassCastException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RestAssured.config = RestAssuredConfig.config()
				.httpClient(HttpClientConfig.httpClientConfig().setParam("http.connection.timeout", 30000)
						.setParam("http.socket.timeout", 30000).setParam("http.connection-manager.timeout", 30000L));
		Response response;
		JsonPath jsonPathEvaluator;
		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println("Posting uri  = " + version + "   " + uri);
		RequestSpecification httpRequest = RestAssured.given()
				.headers("Authorization", "Bearer " + getToken(), "Content-Type", ContentType.JSON, "Accept", "*/*",
						"Connection", "keep-alive", "Accept-Encoding", "gzip, deflate, br")
				.body(jsonDataInFile);

		System.out.println("Posting call Body :" + bodycontent.toString());
		response = httpRequest.post();
		System.out.println("Posting call Response :" + response.asString());
		jsonPathEvaluator = response.jsonPath();
		Thread.sleep(1000);
		return jsonPathEvaluator;

	}

	public static Response postMethodResponseasString(String payload, String uri, String version)
			throws InterruptedException {

		switch (version) {
			case "1":
				RestAssured.baseURI = urlv1;
				break;
			case "2":
				RestAssured.baseURI = urlv2;
				break;
			case "2.1":
				RestAssured.baseURI = urlv210;
				break;
			case "2.2":
				RestAssured.baseURI = urlv220;
				break;
			case "2.3":
				RestAssured.baseURI = urlv230;
				break;
			case "2.3.1":
				RestAssured.baseURI = urlv231;
				break;
			case "2.4":
				RestAssured.baseURI = urlv240;
				break;
			case "3.0":
				RestAssured.baseURI = urlv3;
				break;
			case "4.0":
				RestAssured.baseURI = urlv4;
				break;
			default:
				Assert.fail("Invalid version");
				version = "Invalid version";
				break;
		}
		File jsonDataInFile = new File(payload);

		Response response;
		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println("Posting uri  = " + version + "   " + uri);
		RequestSpecification httpRequest = RestAssured.given()
				.headers("Authorization", "Bearer " + getToken(), "Content-Type", ContentType.JSON, "Accept", "*/*",
						"Connection", "keep-alive", "Accept-Encoding", "gzip, deflate, br")
				.body(jsonDataInFile);

		response = httpRequest.post();
		System.out.println("Response :" + response.asString());
		return response;

	}

	public static String postMethodResponseAsString(String payload, String uri, String version)
			throws InterruptedException {

		switch (version) {
			case "1":
				RestAssured.baseURI = urlv1;
				break;
			case "2":
				RestAssured.baseURI = urlv2;
				break;
			case "2.1":
				RestAssured.baseURI = urlv210;
				break;
			case "2.2":
				RestAssured.baseURI = urlv220;
				break;
			case "2.3":
				RestAssured.baseURI = urlv230;
				break;
			case "2.3.1":
				RestAssured.baseURI = urlv231;
				break;
			case "2.4":
				RestAssured.baseURI = urlv240;
				break;
			case "3.0":
				RestAssured.baseURI = urlv3;
				break;
			case "4.0":
				RestAssured.baseURI = urlv4;
				break;
			default:
				Assert.fail("Invalid version");
				version = "Invalid version";
				break;
		}

		Response response;
		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println("Posting uri  = " + version + "   " + uri);
		RequestSpecification httpRequest = RestAssured.given()
				.headers("Authorization", "Bearer " + getToken(), "Content-Type", ContentType.JSON, "Accept", "*/*",
						"Connection", "keep-alive", "Accept-Encoding", "gzip, deflate, br")
				.body(payload);

		response = httpRequest.post();
		System.out.println("Response :" + response.asString());
		return response.asString();

	}

	public static JsonPath postMethodStringPayload(String payload, String uri, String version)
			throws InterruptedException {

		switch (version) {
			case "1":
				RestAssured.baseURI = urlv1;
				break;
			case "2":
				RestAssured.baseURI = urlv2;
				break;
			case "2.1":
				RestAssured.baseURI = urlv210;
				break;
			case "2.2":
				RestAssured.baseURI = urlv220;
				break;
			case "2.3":
				RestAssured.baseURI = urlv230;
				break;
			case "2.3.1":
				RestAssured.baseURI = urlv231;
				break;
			case "2.4":
				RestAssured.baseURI = urlv240;
				break;
			case "3.0":
				RestAssured.baseURI = urlv3;
				break;
			case "4.0":
				RestAssured.baseURI = urlv4;
				break;
			default:
				version = "Invalid version";
				break;
		}
		// File jsonDataInFile = new File(payload);
		System.out.println(payload);
		Response response;
		JsonPath jsonPathEvaluator;
		// CharSequence i="\\";
		// payload.replace(i,"");
		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println("Posting uri  = " + version + "   " + uri);
		RequestSpecification httpRequest = RestAssured.given()
				.headers("Authorization", "Bearer " + getToken(), "Content-Type", ContentType.JSON, "Accept", "*/*",
						"Connection", "keep-alive", "Accept-Encoding", "gzip, deflate, br")
				.body(payload);

		response = httpRequest.post();
		System.out.println(response.asString());
		jsonPathEvaluator = response.jsonPath();

		return jsonPathEvaluator;

	}

	public static String postMethodStringPayloadString(String payload, String uri, String version)
			throws InterruptedException {

		switch (version) {
			case "1":
				RestAssured.baseURI = urlv1;
				break;
			case "2":
				RestAssured.baseURI = urlv2;
				break;
			case "2.1":
				RestAssured.baseURI = urlv210;
				break;
			case "2.2":
				RestAssured.baseURI = urlv220;
				break;
			case "2.3":
				RestAssured.baseURI = urlv230;
				break;
			case "2.3.1":
				RestAssured.baseURI = urlv231;
				break;
			case "2.4":
				RestAssured.baseURI = urlv240;
				break;
			case "3.0":
				RestAssured.baseURI = urlv3;
				break;
			case "4.0":
				RestAssured.baseURI = urlv4;
				break;
			default:
				version = "Invalid version";
				break;
		}
		// File jsonDataInFile = new File(payload);

		Response response;
		JsonPath jsonPathEvaluator;
		// CharSequence i="\\";
		// payload.replace(i,"");
		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println("Posting uri  = " + version + "   " + uri);
		RequestSpecification httpRequest = RestAssured.given()
				.headers("Authorization", "Bearer " + getToken(), "Content-Type", ContentType.JSON, "Accept", "*/*",
						"Connection", "keep-alive", "Accept-Encoding", "gzip, deflate, br")
				.body(payload);

		// System.out.println("Uri =" + RestAssured.baseURI.toString());
		System.out.println("Uri Payload =" + payload);
		response = httpRequest.post();
		System.out.println("Uri Response =" + response.asString());
		jsonPathEvaluator = response.jsonPath();

		Thread.sleep(10000);
		return response.asString();

	}

	public static void postMethodString(String payload, String uri, String version, String expected)
			throws InterruptedException {

		switch (version) {
			case "1":
				RestAssured.baseURI = urlv1;
				break;
			case "2":
				RestAssured.baseURI = urlv2;
				break;
			case "2.1":
				RestAssured.baseURI = urlv210;
				break;
			case "2.2":
				RestAssured.baseURI = urlv220;
				break;
			case "2.3":
				RestAssured.baseURI = urlv230;
				break;
			case "2.3.1":
				RestAssured.baseURI = urlv231;
				break;
			case "2.4":
				RestAssured.baseURI = urlv240;
				break;
			case "3.0":
				RestAssured.baseURI = urlv3;
				break;
			case "4.0":
				RestAssured.baseURI = urlv4;
				break;
			default:
				version = "Invalid version";
				break;
		}

		System.out.println("Payload = " + payload);
		Response response;
		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println("Posting uri  = " + version + "   " + uri);
		RequestSpecification httpRequest = RestAssured.given()
				.headers("Authorization", "Bearer " + getToken(), "Content-Type", ContentType.JSON, "Accept", "*/*",
						"Connection", "keep-alive", "Accept-Encoding", "gzip, deflate, br")
				.body(payload);

		response = httpRequest.post();
		System.out.println("Actual Response =" + response.asString());
		System.out.println("Expected Response =" + expected);
		Thread.sleep(10000);
		Assert.assertEquals(response.asString(), expected);

	}

	public static JsonPath getMethod(String uri, String version) throws InterruptedException {
		switch (version) {
			case "1":
				RestAssured.baseURI = urlv1;
				break;
			case "1.0":
				RestAssured.baseURI = urlv1;
				break;
			case "2.0":
				RestAssured.baseURI = urlv2;
				break;
			case "2":
				RestAssured.baseURI = urlv2;
				break;
			case "2.1":
				RestAssured.baseURI = urlv210;
				break;
			case "2.2":
				RestAssured.baseURI = urlv220;
				break;
			case "2.3":
				RestAssured.baseURI = urlv230;
				break;
			case "2.3.1":
				RestAssured.baseURI = urlv231;
				break;
			case "2.4":
				RestAssured.baseURI = urlv240;
				break;
			case "3.0":
				RestAssured.baseURI = urlv3;
				break;
			case "4.0":
				RestAssured.baseURI = urlv4;
			case "4":
				RestAssured.baseURI = urlv4;
				break;
			default:
				version = "Invalid version";
				break;
		}
		// File jsonDataInFile = new File(payload);
		System.out.println(RestAssured.baseURI);
		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println("Get URI :" + RestAssured.baseURI.toString());
		RequestSpecification httpRequest = RestAssured.given().headers("Authorization", "bearer " + getToken(),
				"Content-Type", ContentType.JSON, "Accept", "*/*", "Connection", "keep-alive", "Accept-Encoding",
				"gzip, deflate, br", "Cache-Control", "no-cache", "urlEncodingEnabled", "false");

		Response response = httpRequest.get();
		JsonPath jsonPathEvaluator = response.jsonPath();
		return jsonPathEvaluator;

	}

	public static JsonPath getMethod(String uri, String version, Map<String, String> responseMap)
			throws InterruptedException {
		switch (version) {
			case "1":
				RestAssured.baseURI = urlv1;
				break;
			case "2":
				RestAssured.baseURI = urlv2;
				break;
			case "2.1":
				RestAssured.baseURI = urlv210;
				break;
			case "2.2":
				RestAssured.baseURI = urlv220;
				break;
			case "2.3":
				RestAssured.baseURI = urlv230;
				break;
			case "2.3.1":
				RestAssured.baseURI = urlv231;
				break;
			case "2.4":
				RestAssured.baseURI = urlv240;
				break;
			case "3.0":
				RestAssured.baseURI = urlv3;
				break;
			case "4.0":
				RestAssured.baseURI = urlv4;
				break;
			case "4":
				RestAssured.baseURI = urlv4;
				break;

			default:
				version = "Invalid version";
				break;
		}
		// File jsonDataInFile = new File(payload);
		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println(RestAssured.baseURI.toString());
		RequestSpecification httpRequest = RestAssured.given().headers("Authorization", "Bearer " + getToken(),
				"Content-Type", ContentType.JSON, "Accept", "*/*", "Connection", "keep-alive", "Accept-Encoding",
				"gzip, deflate, br");

		Iterator<Entry<String, String>> it = responseMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			System.out.println(pair.getKey() + " = " + pair.getValue());
			httpRequest.queryParam(pair.getKey().toString(), pair.getValue().toString());
		}

		Response response = null;
		JsonPath jsonPathEvaluator;
		try {
			response = httpRequest.get();
		} catch (NullPointerException e) {

		}
		jsonPathEvaluator = response.jsonPath();
		System.out.print(response.prettyPrint());
		return jsonPathEvaluator;

	}

	public static Response getMethod(String uri, String version, HashMap<String, String> params)
			throws InterruptedException {
		switch (version) {
			case "1":
				RestAssured.baseURI = urlv1;
				break;
			case "2":
				RestAssured.baseURI = urlv2;
				break;
			case "2.1":
				RestAssured.baseURI = urlv210;
				break;
			case "2.2":
				RestAssured.baseURI = urlv220;
				break;
			case "2.3":
				RestAssured.baseURI = urlv230;
				break;
			case "2.3.1":
				RestAssured.baseURI = urlv231;
				break;
			case "2.4":
				RestAssured.baseURI = urlv240;
				break;
			case "3.0":
				RestAssured.baseURI = urlv3;
				break;
			case "4.0":
				RestAssured.baseURI = urlv4;
				break;

			default:
				version = "Invalid version";
				break;
		}
		// File jsonDataInFile = new File(payload);
		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println(RestAssured.baseURI.toString());
		RequestSpecification httpRequest = RestAssured.given().headers("Authorization", "Bearer " + getToken(),
				"Content-Type", ContentType.JSON, "Connection", "keep-alive", "Accept-Encoding", "gzip, deflate, br")
				.queryParams(params);

		Response response = httpRequest.get();

		return response;

	}

	public static String getMethod(String uri, String version, HashMap<String, String> params, String jpath)
			throws InterruptedException, IOException {

		switch (version) {
			case "1":
				RestAssured.baseURI = urlv1;
				break;
			case "2":
				RestAssured.baseURI = urlv2;
				break;
			case "2.1":
				RestAssured.baseURI = urlv210;
				break;
			case "2.2":
				RestAssured.baseURI = urlv220;
				break;
			case "2.3":
				RestAssured.baseURI = urlv230;
				break;
			case "2.3.1":
				RestAssured.baseURI = urlv231;
				break;
			case "2.4":
				RestAssured.baseURI = urlv240;
				break;
			case "3.0":
				RestAssured.baseURI = urlv3;
				break;
			case "4.0":
				RestAssured.baseURI = urlv4;
				break;
			default:
				version = "Invalid version";
				Assert.fail("Invalid version");
				break;
		}
		String expe = new String(Files.readAllBytes(Paths.get(jpath)));
		System.out.println("Expected Response as in file : " + jpath + " = " + expe);
		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println("Tesing URI:" + RestAssured.baseURI.toString());
		RequestSpecification httpRequest = RestAssured.given().headers("Authorization", "Bearer " + getToken(),
				"Content-Type", ContentType.JSON, "Connection", "keep-alive", "Accept-Encoding", "gzip, deflate, br")
				.queryParams(params);

		Response response = httpRequest.get();
		System.out.print(response.asString());
		Assert.assertEquals(response.asString(), new String(Files.readAllBytes(Paths.get(jpath))));
		return response.asString();
	}

	public static String putMethod(String uri, String version, HashMap<String, String> params, String payload,
			String responseFile) throws InterruptedException, IOException {

		switch (version) {
			case "1":
				RestAssured.baseURI = urlv1;
				break;
			case "2":
				RestAssured.baseURI = urlv2;
				break;
			case "2.1":
				RestAssured.baseURI = urlv210;
				break;
			case "2.2":
				RestAssured.baseURI = urlv220;
				break;
			case "2.3":
				RestAssured.baseURI = urlv230;
				break;
			case "2.3.1":
				RestAssured.baseURI = urlv231;
				break;
			case "2.4":
				RestAssured.baseURI = urlv240;
				break;
			case "3.0":
				RestAssured.baseURI = urlv3;
				break;
			case "4.0":
				RestAssured.baseURI = urlv4;
				break;

			default:
				version = "Invalid version";
				break;
		}

		File jsonDataInFile = new File(payload);
		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println(RestAssured.baseURI.toString());
		RequestSpecification httpRequest = RestAssured
				.given().headers("Authorization", "Bearer " + getToken(), "Content-Type", ContentType.JSON,
						"Connection", "keep-alive", "Accept-Encoding", "gzip, deflate, br")
				.queryParams(params).body(jsonDataInFile);
		String expe = new String(Files.readAllBytes(Paths.get(responseFile)));
		System.out.println("Expected Response as in file : " + expe);
		Response responseTest = httpRequest.put();
		System.out.println(responseTest.asString());
		ValidatableResponse response = httpRequest.put().then().assertThat().statusCode(200)
				.body(Matchers.equalTo(new String(Files.readAllBytes(Paths.get(responseFile)))));
		;
		System.out.println(response.extract().asString());
		System.out.println(response.log());

		return response.extract().asString();

	}

	public static ValidatableResponse putMethod(String uri, String version, String payload)
			throws InterruptedException, IOException {

		switch (version) {
			case "1":
				RestAssured.baseURI = urlv1;
				break;
			case "2":
				RestAssured.baseURI = urlv2;
				break;
			case "2.1":
				RestAssured.baseURI = urlv210;
				break;
			case "2.2":
				RestAssured.baseURI = urlv220;
				break;
			case "2.3":
				RestAssured.baseURI = urlv230;
				break;
			case "2.3.1":
				RestAssured.baseURI = urlv231;
				break;
			case "2.4":
				RestAssured.baseURI = urlv240;
				break;
			case "3.0":
				RestAssured.baseURI = urlv3;
				break;

			default:
				version = "Invalid version";
				break;
		}

		File jsonDataInFile = new File(payload);
		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println(RestAssured.baseURI.toString());
		RequestSpecification httpRequest = RestAssured.given().headers("Authorization", "Bearer " + getToken(),
				"Content-Type", ContentType.JSON, "Connection", "keep-alive", "Accept-Encoding", "gzip, deflate, br")
				.body(jsonDataInFile);
		System.out.println(httpRequest.toString());
		ValidatableResponse response = httpRequest.put().then().log().all();
		return response;

	}

	public static ValidatableResponse putMethodvalidate(String uri, String version, String payload, String fresponse)
			throws InterruptedException, IOException {

		switch (version) {
			case "1":
				RestAssured.baseURI = urlv1;
				break;
			case "2":
				RestAssured.baseURI = urlv2;
				break;
			case "2.1":
				RestAssured.baseURI = urlv210;
				break;
			case "2.2":
				RestAssured.baseURI = urlv220;
				break;
			case "2.3":
				RestAssured.baseURI = urlv230;
				break;
			case "2.3.1":
				RestAssured.baseURI = urlv231;
				break;
			case "2.4":
				RestAssured.baseURI = urlv240;
				break;
			case "3.0":
				RestAssured.baseURI = urlv3;
				break;
			case "4.0":
				RestAssured.baseURI = urlv4;
				break;

			default:
				version = "Invalid version";
				break;
		}

		File jsonDataInFile = new File(payload);
		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println(RestAssured.baseURI.toString());
		RequestSpecification httpRequest = RestAssured.given().headers("Authorization", "Bearer " + getToken(),
				"Content-Type", ContentType.JSON, "Connection", "keep-alive", "Accept-Encoding", "gzip, deflate, br")
				.body(jsonDataInFile);
		String expe = new String(Files.readAllBytes(Paths.get(fresponse)));
		System.out.println("Expected Response as in file : " + expe);
		// System.out.println(httpRequest.put().prettyPrint());
		ValidatableResponse response = httpRequest.put().then().assertThat()
				.body(Matchers.equalTo(new String(Files.readAllBytes(Paths.get(fresponse))))).assertThat()
				.statusCode(200);
		return response;

	}

	public static Response putMethod(String uri, String version, String payload, String jsonDataInFile)
			throws InterruptedException, IOException {

		switch (version) {
			case "1":
				RestAssured.baseURI = urlv1;
				break;
			case "2":
				RestAssured.baseURI = urlv2;
				break;
			case "2.1":
				RestAssured.baseURI = urlv210;
				break;
			case "2.2":
				RestAssured.baseURI = urlv220;
				break;
			case "2.3":
				RestAssured.baseURI = urlv230;
				break;
			case "2.3.1":
				RestAssured.baseURI = urlv231;
				break;
			case "2.4":
				RestAssured.baseURI = urlv240;
				break;
			case "3.0":
				RestAssured.baseURI = urlv3;
				break;
			case "4.0":
				RestAssured.baseURI = urlv4;
				break;

			default:
				version = "Invalid version";
				break;
		}
		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println(RestAssured.baseURI.toString());
		RequestSpecification httpRequest = RestAssured.given().headers("Authorization", "Bearer " + getToken(),
				"Content-Type", ContentType.JSON, "Connection", "keep-alive", "Accept-Encoding", "gzip, deflate, br")
				.body(payload);
		System.out.println("** PUT call uri ** " + RestAssured.baseURI);
		System.out.println("** PUT call payload ** " + payload);
		Response response = httpRequest.put();
		Assert.assertEquals(response.getBody().asString(), new String(Files.readAllBytes(Paths.get(jsonDataInFile))));
		System.out.println("** PUT call Response ** " + response.asString());

		Thread.sleep(10000);
		return response;

	}

	public static String putMethodstring(String uri, String version, String payload, String expected)
			throws InterruptedException, IOException {

		switch (version) {
			case "1":
				RestAssured.baseURI = urlv1;
				break;
			case "2":
				RestAssured.baseURI = urlv2;
				break;
			case "2.1":
				RestAssured.baseURI = urlv210;
				break;
			case "2.2":
				RestAssured.baseURI = urlv220;
				break;
			case "2.3":
				RestAssured.baseURI = urlv230;
				break;
			case "2.3.1":
				RestAssured.baseURI = urlv231;
				break;
			case "2.4":
				RestAssured.baseURI = urlv240;
				break;
			case "3.0":
				RestAssured.baseURI = urlv3;
				break;
			case "4.0":
				RestAssured.baseURI = urlv4;
				break;
			case "4":
				RestAssured.baseURI = urlv4;
				break;

			default:
				version = "Invalid version";
				break;
		}

		// File body = new File(payload);
		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println(RestAssured.baseURI.toString());
		RequestSpecification httpRequest = RestAssured.given().headers("Authorization", "Bearer " + getToken(),
				"Content-Type", ContentType.JSON, "Connection", "keep-alive", "Accept-Encoding", "gzip, deflate, br")
				.body(payload);
		System.out.println("** PUT call Body **" + payload);
		Response response = httpRequest.put();
		System.out.println("** PUT call Response **");
		System.out.println(response.getBody().asString());
		Assert.assertEquals(response.getBody().asString(), expected);
		return response.getBody().asString();

	}

	public static ValidatableResponse putMethodNofile(String uri, String version, String payload, String jsonDataInFile)
			throws InterruptedException, IOException {

		switch (version) {
			case "1":
				RestAssured.baseURI = urlv1;
				break;
			case "2":
				RestAssured.baseURI = urlv2;
				break;
			case "2.1":
				RestAssured.baseURI = urlv210;
				break;
			case "2.2":
				RestAssured.baseURI = urlv220;
				break;
			case "2.3":
				RestAssured.baseURI = urlv230;
				break;
			case "2.3.1":
				RestAssured.baseURI = urlv231;
				break;
			case "2.4":
				RestAssured.baseURI = urlv240;
				break;
			case "3.0":
				RestAssured.baseURI = urlv3;
				break;
			case "4.0":
				RestAssured.baseURI = urlv4;
				break;

			default:
				version = "Invalid version";
				break;
		}

		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println(RestAssured.baseURI.toString());
		RequestSpecification httpRequest = RestAssured.given().headers("Authorization", "Bearer " + getToken(),
				"Content-Type", ContentType.JSON, "Connection", "keep-alive", "Accept-Encoding", "gzip, deflate, br")
				.body(payload);

		ValidatableResponse response = httpRequest.put().then().assertThat()
				.body(Matchers.equalTo(new String(Files.readAllBytes(Paths.get(jsonDataInFile)))));

		System.out.println("** PUT call Response **");
		System.out.println(response.extract().asString());
		return response;

	}

	public static JsonPath getMethodTest() throws InterruptedException, IOException {

		String version = "2.4";
		switch (version) {
			case "1":
				RestAssured.baseURI = urlv1;
				break;
			case "2":
				RestAssured.baseURI = urlv2;
				break;
			case "2.1":
				RestAssured.baseURI = urlv210;
				break;
			case "2.2":
				RestAssured.baseURI = urlv220;
				break;
			case "2.3":
				RestAssured.baseURI = urlv230;
				break;
			case "2.3.1":
				RestAssured.baseURI = urlv231;
				break;
			case "2.4":
				RestAssured.baseURI = urlv240;
				break;
			case "3.0":
				RestAssured.baseURI = urlv3;
				break;

			default:
				version = "Invalid version";
				break;
		}
		// File jsonDataInFile = new File(payload);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "loc@0001");
		params.put("CustomerId", "0000011111");
		params.put("UserDate", "2027-04-12");
		/*
		 * Iterator<Entry<String, String>> it = responseMap.entrySet().iterator(); while
		 * (it.hasNext()) { Map.Entry pair = (Map.Entry) it.next();
		 * System.out.println(pair.getKey() + " = " + pair.getValue());
		 * httpRequest.queryParam(pair.getKey().toString(), pair.getValue().toString());
		 * 
		 * resp = RestAssured.given() .headers(headers) .queryParameters(params)
		 * .post(apiURL).andReturn() }
		 */

		String uri = "/accountBalance/getAccountBalances";
		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println(RestAssured.baseURI.toString());
		RequestSpecification httpRequest = RestAssured.given().headers("Authorization", "Bearer " + getToken(),
				"Content-Type", ContentType.JSON, "Connection", "keep-alive", "Accept-Encoding", "gzip, deflate, br")
				.queryParams(params);

		ValidatableResponse response = httpRequest.get().then().assertThat()
				.body(Matchers.equalTo(new String(Files.readAllBytes(Paths.get("./TestData\\accountBalance.json")))));
		// JsonPath jsonPathEvaluator = ((ResponseBodyExtractionOptions)
		// response).jsonPath();
		JsonPath jsonPathEvaluator = null;
		System.out.println(response.extract().asString());
		// ValidatableResponse response =
		// httpRequest.get().then().assertThat().statusCode(200).body(Matchers.equalTo(new
		// String(Files.readAllBytes(jsonDataInFile))));

		return jsonPathEvaluator;

	}

	public static ValidatableResponse getMethod(String uri, String version, String pathToResponse)
			throws InterruptedException, IOException {
		switch (version) {
			case "1":
				RestAssured.baseURI = urlv1;
				break;
			case "2":
				RestAssured.baseURI = urlv2;
				break;
			case "2.1":
				RestAssured.baseURI = urlv210;
				break;
			case "2.2":
				RestAssured.baseURI = urlv220;
				break;
			case "2.3":
				RestAssured.baseURI = urlv230;
				break;
			case "2.3.1":
				RestAssured.baseURI = urlv231;
				break;
			case "2.4":
				RestAssured.baseURI = urlv240;
				break;
			case "3.0":
				RestAssured.baseURI = urlv3;
				break;
			case "4.0":
				RestAssured.baseURI = urlv4;
				break;

			default:
				version = "Invalid version";
				break;
		}
		Path jsonDataInFile = Paths.get(pathToResponse);

		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println("Posting uri  = " + version + "   " + uri);
		String expe = new String(Files.readAllBytes(Paths.get(pathToResponse)));
		System.out.println("Expected Response as in file : " + expe);
		RestAssured.baseURI = RestAssured.baseURI + uri;
		RequestSpecification httpRequest = RestAssured.given().headers("Authorization", "Bearer " + getToken(),
				"Content-Type", ContentType.JSON, "Connection", "keep-alive", "Accept-Encoding", "gzip, deflate, br");

		ValidatableResponse response = httpRequest.get().then().assertThat().statusCode(200);
		// .and() .body(Matchers.equalTo(new
		// String(Files.readAllBytes(jsonDataInFile))));
		System.out.println(response.toString());
		return response;
		// JsonPath jsonPathEvaluator = response.jsonPath();

	}

	public static String getSPAIndex(String customerId) throws ClassNotFoundException, SQLException, SaslException {
		String columnName = "umSPAIndex";
		String Command1 = "select * from Two.dbo.UMCO102 where CUSTNMBR ='" + customerId + "'";
		String Result = "";
		String ConnectionString = Read.ReadFile("ConnectionStringServTWO");
		Result = selectFromDb(Command1, ConnectionString, columnName);

		if (Result != "") {
			log("Open spa found Order verified = " + Result);

		}

		return Result;

	}

	public static String selectFromDb(String Command, String ConnectionString, String columnName)
			throws ClassNotFoundException, SQLException {
		String Result = "";
		Connection con = DriverManager.getConnection(ConnectionString);
		try {
			// Load the SQL Server JDBC driver
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			// Execute the SQL query
			Statement stmt = con.createStatement();
			System.out.println("Executing query: " + Command); // Debugging
			ResultSet rs = stmt.executeQuery(Command);

			if (!rs.next()) {
				System.out.println("No results found for query: " + Command);
				return null;
			}

			// Print all the columns for the first row to check
			System.out.println("Column names:");
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				System.out.println("Column " + i + ": " + rsmd.getColumnName(i));
			}

			// Fetch and print the BatchId column (or handle as needed)
			do {
				Result = rs.getString(columnName);
				System.out.println("Fetched " + columnName + ": " + Result); // Debugging
			} while (rs.next());

		} catch (SQLDataException e) {
			e.printStackTrace();
			Assert.fail("Record not found, check query.");
		} finally {
			con.close();
		}

		return Result;
	}

	private static void log(String string) {

		System.out.println(string);
		// TODO Auto-generated method stub

	}

	public static boolean cancelSpa(String spaIndexfromdb, String Customer)
			throws ConnectionClosedException, InterruptedException {
		// CUSTOMER001
		char q = '"';
		RestAssured.baseURI = "http://localhost:3000/api/v2/spa/cancel";
		String rawbody = "{ " + q + "SpaCancel" + q + " :[{ " + q + "CustomerId" + q + ":" + q + Customer + q + "," + q
				+ "SpaIndex" + q + ":" + q + spaIndexfromdb + q + "," + q + "CancelDate" + q + ":" + q + "2020-06-08"
				+ q + "," + q + "CancelReason" + q + ":" + q + "Test" + q + "," + q + "ReasonCode" + q + ":" + q + " "
				+ q + "}]}";

		RequestSpecification httpRequest = RestAssured.given()
				.headers("Authorization", "Bearer " + getToken(), "Content-Type", ContentType.JSON, "Accept", "*/*",
						"Connection", "keep-alive", "Accept-Encoding", "gzip, deflate, br")
				.body(rawbody);

		Response response = httpRequest.put();
		JsonPath jsonPathEvaluator = response.jsonPath();
		Boolean result = true;
		try {
			result = jsonPathEvaluator.get("SPACancel[0].Success");
		} catch (IllegalArgumentException e) {

		}
		// System.out.println(getToken());
		if (result == false) {
			System.out.println(
					"Active (Open) special payment arrangement does not exist for customer at " + spaIndexfromdb);
			return false;
		}

		System.out.println("Active (Open) special payment arrangement cancelled for customer at " + spaIndexfromdb);
		return result;

	}

	public static String getMethodContains(String uri, String version, HashMap<String, String> params, String jpath)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		switch (version) {
			case "1":
				RestAssured.baseURI = urlv1;
				break;
			case "2":
				RestAssured.baseURI = urlv2;
				break;
			case "2.1":
				RestAssured.baseURI = urlv210;
				break;
			case "2.2":
				RestAssured.baseURI = urlv220;
				break;
			case "2.3":
				RestAssured.baseURI = urlv230;
				break;
			case "2.3.1":
				RestAssured.baseURI = urlv231;
				break;
			case "2.4":
				RestAssured.baseURI = urlv240;
				break;
			case "3.0":
				RestAssured.baseURI = urlv3;
				break;
			case "4.0":
				RestAssured.baseURI = urlv4;
				break;

			default:
				version = "Invalid version";
				Assert.fail("Invalid version");
				break;
		}

		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println(RestAssured.baseURI.toString());
		RequestSpecification httpRequest = RestAssured.given().headers("Authorization", "Bearer " + getToken(),
				"Content-Type", ContentType.JSON, "Connection", "keep-alive", "Accept-Encoding", "gzip, deflate, br")
				.queryParams(params);

		// ValidatableResponse response;
		// Response response;
		String validate = new String(Files.readAllBytes(Paths.get(jpath)));
		System.out.println("Veriying String =" + validate);

		// response =
		// httpRequest.get().then().assertThat().body(Matchers.containsString(validate));

		Response response = httpRequest.get();
		System.out.println("Response Body: " + response.getBody().asString());

		// Now validate
		response.then().assertThat().body(containsString(validate));
		// System.out.println(response.extract().asString());
		// Assert.assertEquals(response.asString(), validate);
		return response.asString();
	}

	public static void getMethodContainsString(String uri, String version, HashMap<String, String> params,
			String expected) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		switch (version) {
			case "1":
				RestAssured.baseURI = urlv1;
				break;
			case "2":
				RestAssured.baseURI = urlv2;
				break;
			case "2.1":
				RestAssured.baseURI = urlv210;
				break;
			case "2.2":
				RestAssured.baseURI = urlv220;
				break;
			case "2.3":
				RestAssured.baseURI = urlv230;
				break;
			case "2.3.1":
				RestAssured.baseURI = urlv231;
				break;
			case "2.4":
				RestAssured.baseURI = urlv240;
				break;
			case "3.0":
				RestAssured.baseURI = urlv3;
				break;
			case "4.0":
				RestAssured.baseURI = urlv4;
				break;

			default:
				version = "Invalid version";
				Assert.fail("Invalid version");
				break;
		}

		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println(RestAssured.baseURI.toString());
		RequestSpecification httpRequest = RestAssured.given().headers("Authorization", "Bearer " + getToken(),
				"Content-Type", ContentType.JSON, "Connection", "keep-alive", "Accept-Encoding", "gzip, deflate, br")
				.queryParams(params);

		ValidatableResponse response;

		System.out.println("Veriying String =" + expected);
		response = httpRequest.get().then().assertThat().body(Matchers.containsString(expected));
		System.out.println("Response  =" + response);

	}

	public static String getMethodasString(String uri, String version, HashMap<String, String> params)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		switch (version) {
			case "1":
				RestAssured.baseURI = urlv1;
				break;
			case "2":
				RestAssured.baseURI = urlv2;
				break;
			case "2.1":
				RestAssured.baseURI = urlv210;
				break;
			case "2.2":
				RestAssured.baseURI = urlv220;
				break;
			case "2.3":
				RestAssured.baseURI = urlv230;
				break;
			case "2.3.1":
				RestAssured.baseURI = urlv231;
				break;
			case "2.4":
				RestAssured.baseURI = urlv240;
				break;
			case "3.0":
				RestAssured.baseURI = urlv3;
				break;
			case "4.0":
				RestAssured.baseURI = urlv4;
				break;
			case "e":
				RestAssured.baseURI = urle;
				break;

			default:
				version = "Invalid version";
				Assert.fail("Invalid version");
				break;
		}

		System.out.println("===============================================");
		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println(RestAssured.baseURI.toString());
		RequestSpecification httpRequest = RestAssured.given().headers("Authorization", "Bearer " + getToken(),
				"Content-Type", ContentType.JSON, "Connection", "keep-alive", "Accept-Encoding", "gzip, deflate, br")
				.queryParams(params);

		String response;
		System.out.println("===============================================");
		System.out.println("===============================================");
		response = httpRequest.get().asString();
		System.out.println("URI :" + RestAssured.baseURI.toString());
		System.out.println("Response :" + response);
		return response;
	}

	public static String getMethodasString(String uri, String version, String rawbody)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		switch (version) {
			case "1":
				RestAssured.baseURI = urlv1;
				break;
			case "2":
				RestAssured.baseURI = urlv2;
				break;
			case "2.1":
				RestAssured.baseURI = urlv210;
				break;
			case "2.2":
				RestAssured.baseURI = urlv220;
				break;
			case "2.3":
				RestAssured.baseURI = urlv230;
				break;
			case "2.3.1":
				RestAssured.baseURI = urlv231;
				break;
			case "2.4":
				RestAssured.baseURI = urlv240;
				break;
			case "3.0":
				RestAssured.baseURI = urlv3;
				break;
			case "4.0":
				RestAssured.baseURI = urlv4;
				break;
			case "e":
				RestAssured.baseURI = urle;
				break;

			default:
				version = "Invalid version";
				Assert.fail("Invalid version");
				break;
		}

		RestAssured.baseURI = RestAssured.baseURI + uri;
		RequestSpecification httpRequest = RestAssured.given().headers("Authorization", "Bearer " + getToken(),
				"Content-Type", ContentType.JSON, "Connection", "keep-alive", "Accept-Encoding", "gzip, deflate, br")
				.body(rawbody);

		String response;
		response = httpRequest.get().asString();
		System.out.println("URI :" + RestAssured.baseURI.toString());
		System.out.println("Response :" + response);
		return response;
	}

	/**
	 * Returns a minimal set of characters that have to be removed from (or added
	 * to) the respective strings to make the strings equal.
	 */
	// public static Pair<String> diff(String a, String b) {
	// return diffHelper(a, b, new HashMap<>());
	// }

	/**
	 * Recursively compute a minimal set of characters while remembering already
	 * computed substrings. Runs in O(n^2).
	 */
	/*
	 * private static Pair<String> diffHelper(String a, String b, Map<Long,
	 * Pair<String>> lookup) { long key = ((long) a.length()) << 32 | b.length(); if
	 * (!lookup.containsKey(key)) { Pair<String> value; if (a.isEmpty() ||
	 * b.isEmpty()) { value = new Pair<>(a, b); } else if (a.charAt(0) ==
	 * b.charAt(0)) { value = diffHelper(a.substring(1), b.substring(1), lookup); }
	 * else { Pair<String> aa = diffHelper(a.substring(1), b, lookup); Pair<String>
	 * bb = diffHelper(a, b.substring(1), lookup); if (aa.first.length() +
	 * aa.second.length() < bb.first.length() + bb.second.length()) { value = new
	 * Pair<>(a.charAt(0) + aa.first, aa.second); } else { value = new
	 * Pair<>(bb.first, b.charAt(0) + bb.second); } } lookup.put(key, value); }
	 * return lookup.get(key); }
	 * 
	 * public static class Pair<T> { public Pair(T first, T second) { this.first =
	 * first; this.second = second; }
	 * 
	 * public final T first, second;
	 * 
	 * public String toString() { return "(" + first + "," + second + ")"; } }
	 */
	public static String deleteMethod(String uri, String version, String jpath)
			throws InterruptedException, IOException {
		switch (version) {
			case "1":
				RestAssured.baseURI = urlv1;
				break;
			case "2":
				RestAssured.baseURI = urlv2;
				break;
			case "2.1":
				RestAssured.baseURI = urlv210;
				break;
			case "2.2":
				RestAssured.baseURI = urlv220;
				break;
			case "2.3":
				RestAssured.baseURI = urlv230;
				break;
			case "2.3.1":
				RestAssured.baseURI = urlv231;
				break;
			case "2.4":
				RestAssured.baseURI = urlv240;
				break;
			case "3.0":
				RestAssured.baseURI = urlv3;
				break;
			case "4.0":
				RestAssured.baseURI = urlv4;
				break;
			default:
				version = "Invalid version";
				break;
		}
		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println(RestAssured.baseURI.toString());
		RequestSpecification httpRequest = RestAssured.given().headers("Authorization", "Bearer " + getToken(),
				"Content-Type", ContentType.JSON, "Connection", "keep-alive", "Accept-Encoding", "gzip, deflate, br");

		ValidatableResponse response;
		String validate = new String(Files.readAllBytes(Paths.get(jpath)));
		System.out.println("Veriying String =" + validate);
		response = httpRequest.delete().then().assertThat().body(Matchers.containsString(validate));
		System.out.println(response.extract().asString());
		return response.extract().asString();

	}

	public static void deleteMethodvoid(String uri, String version, String expected)
			throws InterruptedException, IOException {
		switch (version) {
			case "1":
				RestAssured.baseURI = urlv1;
				break;
			case "2":
				RestAssured.baseURI = urlv2;
				break;
			case "2.1":
				RestAssured.baseURI = urlv210;
				break;
			case "2.2":
				RestAssured.baseURI = urlv220;
				break;
			case "2.3":
				RestAssured.baseURI = urlv230;
				break;
			case "2.3.1":
				RestAssured.baseURI = urlv231;
				break;
			case "2.4":
				RestAssured.baseURI = urlv240;
				break;
			case "3.0":
				RestAssured.baseURI = urlv3;
				break;
			case "4.0":
				RestAssured.baseURI = urlv4;
				break;
			default:
				version = "Invalid version";
				break;
		}
		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println(RestAssured.baseURI.toString());
		RequestSpecification httpRequest = RestAssured.given().headers("Authorization", "Bearer " + getToken(),
				"Content-Type", ContentType.JSON, "Connection", "keep-alive", "Accept-Encoding", "gzip, deflate, br");

		Response response;

		response = httpRequest.delete();
		System.out.println(response.asString());
		Assert.assertEquals(response.asString(), expected);

	}

	public static String deleteMethodasString(String uri, String version) throws InterruptedException, IOException {
		switch (version) {
			case "1":
				RestAssured.baseURI = urlv1;
				break;
			case "2":
				RestAssured.baseURI = urlv2;
				break;
			case "2.1":
				RestAssured.baseURI = urlv210;
				break;
			case "2.2":
				RestAssured.baseURI = urlv220;
				break;
			case "2.3":
				RestAssured.baseURI = urlv230;
				break;
			case "2.3.1":
				RestAssured.baseURI = urlv231;
				break;
			case "2.4":
				RestAssured.baseURI = urlv240;
				break;
			case "3.0":
				RestAssured.baseURI = urlv3;
				break;
			case "4.0":
				RestAssured.baseURI = urlv4;
				break;
			default:
				version = "Invalid version";
				break;
		}
		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println(RestAssured.baseURI.toString());
		RequestSpecification httpRequest = RestAssured.given().headers("Authorization", "Bearer " + getToken(),
				"Content-Type", ContentType.JSON, "Connection", "keep-alive", "Accept-Encoding", "gzip, deflate, br");

		String response = httpRequest.delete().asString();
		System.out.println(response);
		return response;
	}

	public static void postcall(String uri, String payload, String ver, String exResult) throws InterruptedException {
		Response jsonPathResponse;
		jsonPathResponse = CommonMethods.postMethodResponseasString(payload, uri, ver);
		Assert.assertEquals(jsonPathResponse.asString(), exResult);

	}

	public static void postcallcontains(String uri, String payload, String ver, String exResult)
			throws InterruptedException {
		Response jsonPathResponse;
		jsonPathResponse = CommonMethods.postMethodResponseasString(payload, uri, ver);
		System.out.println("Response :" + jsonPathResponse.asString());
		Assert.assertTrue(jsonPathResponse.asString().contains(exResult));

	}

	public static void Match(String ss, String tomatch) throws Exception {
		// String[] ss = { "aabb", "aa", "cc", "aac" };
		Pattern p = Pattern.compile(tomatch);
		Matcher m = p.matcher("");

		if (m.matches()) {
			System.out.printf("%-4s : match%n", ss);
		} else {
			System.out.printf("%-4s : no match%n", ss);
		}

	}

	public static void RegexMatcher(String expectedPattern, String result) {
		Pattern pattern = Pattern.compile(expectedPattern);
		Matcher matcher = pattern.matcher(result);
		Assert.assertTrue(matcher.find(), "Result does not match expected pattern.");

	}

	public static void main(String args[]) {

	}

	public static void Bug(String str1) {

		throw new SkipException("Skipping this method due to bug = " + str1);
	}

}
