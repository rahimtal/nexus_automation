package com.NexustAPIAutomation.java;

import org.testng.annotations.Test;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
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
import org.testng.SkipException;

import com.google.gson.Gson;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import com.NexustAPIAutomation.java.ExtentReportManager;

public class CommonMethods {

	public static ReadProjectProperties Read = new ReadProjectProperties();
	public static String userName = Read.ReadFile("username");
	public static String Password = Read.ReadFile("password");
	public static String keycloakurl = Read.ReadFile("keycloakurl");
	
	// Token caching variables
	private static String cachedToken = null;
	private static long tokenExpirationTime = 0;
	private static final long TOKEN_BUFFER = 30000; // 30 second buffer before expiration
	
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

	// Helper method to resolve base URI from version
	private static String resolveBaseUri(String version) {
		switch (version) {
			case "1":
			case "1.0":
				return urlv1;
			case "2":
			case "2.0":
				return urlv2;
			case "2.1":
				return urlv210;
			case "2.2":
				return urlv220;
			case "2.3":
				return urlv230;
			case "2.3.1":
				return urlv231;
			case "2.4":
				return urlv240;
			case "3.0":
				return urlv3;
			case "4.0":
			case "4":
				return urlv4;
			case "e":
				return urle;
			default:
				return null;
		}
	}

	public static String getToken() {

		// Check if cached token is still valid
		long currentTime = System.currentTimeMillis();
		if (cachedToken != null && currentTime < (tokenExpirationTime - TOKEN_BUFFER)) {
			System.out.println("✅ Using cached token (valid for " + ((tokenExpirationTime - currentTime) / 1000) + " seconds)");
			return cachedToken;
		}

		String auth_token = null;
		java.nio.file.Path propertiesPath = java.nio.file.Paths.get("Configuration", "Project.properties");
		if (!java.nio.file.Files.exists(propertiesPath)) {
			System.out.println("Sorry, unable to find Project.properties");
			Assert.fail("Project.properties file not found in Configuration folder.");
			return null;
		}

		java.util.Properties properties = new java.util.Properties();

		ReadProjectProperties readProps = new ReadProjectProperties();
		String PKCE = readProps.ReadFile("PKCE");

		if (PKCE == "true" || PKCE.equalsIgnoreCase("true")) {
			// Use KeycloakStep1And2And3WithCookies for PKCE flow
			String kcBase = keycloakurl + "/realms/nexus";
			String clientId = "nexus-portal";
			String redirectUri = "https://oauth.usebruno.com/callback";
			
			System.out.println("\n========== PKCE Flow Started ==========");
			System.out.println("Keycloak Base: " + kcBase);
			System.out.println("Client ID: " + clientId);
			System.out.println("Username: " + userName);
			
			// Step 1: Auth GET
			Map<String, Object> context = KeycloakStep1And2And3WithCookies.stage1Auth(kcBase, clientId, redirectUri);
			System.out.println("Step 1 - Flow Step: " + context.get("flow_step"));
			System.out.println("Step 1 - Auth Code: " + context.getOrDefault("auth_code", "NOT FOUND"));

			// Step 2: Login POST (if needed)
			if ("need_login".equals(context.get("flow_step"))) {
				System.out.println("Step 2 - Performing login...");
				context.putAll(KeycloakStep1And2And3WithCookies.step2Login((String) context.get("kc_login_action"),
						userName, Password, "on",
						(Map<String, String>) context.get("cookies")));
				System.out.println("Step 2 - Flow Step After Login: " + context.get("flow_step"));
				System.out.println("Step 2 - Auth Code After Login: " + context.getOrDefault("auth_code", "NOT FOUND"));
			} else if ("got_code".equals(context.get("flow_step"))) {
				System.out.println("Step 2 - Skipped (already have auth code from Step 1)");
			}

			// Step 3: Token Exchange
			String authCodeForExchange = (String) context.getOrDefault("auth_code", "");
			System.out.println("Step 3 - Auth Code to exchange: " + (authCodeForExchange.isEmpty() ? "EMPTY!" : authCodeForExchange));
			System.out.println("Step 3 - PKCE Verifier: " + context.get("pkce_verifier"));
			
			Map<String, Object> tokenContext = KeycloakStep1And2And3WithCookies.step3TokenExchange(
					kcBase, clientId, redirectUri,
					authCodeForExchange,
					(String) context.get("pkce_verifier"),
					(Map<String, String>) context.get("cookies")
			);

			System.out.println("\n========== Token Exchange Result ==========");
			System.out.println("Access Token: " + tokenContext.get("access_token"));
			System.out.println("Refresh Token: " + tokenContext.get("refresh_token"));
			auth_token = (String) tokenContext.get("access_token");
			
			if (auth_token == null || auth_token.isEmpty()) {
				System.out.println("❌ PKCE Flow Failed - No access token received");
				System.out.println("Full Token Context: " + tokenContext);
				Assert.fail("Authorization failed/Invalid Token/Check User Name");
			}
			
			// Cache the token with expiration time (300 seconds = 5 minutes)
			cachedToken = auth_token;
			tokenExpirationTime = System.currentTimeMillis() + 300000;
			System.out.println("✅ Token cached, expires in 300 seconds");

		} else {

			String url = keycloakurl + "/auth/realms/nexus-portal/protocol/openid-connect/token";
			Response response = RestAssured.given().auth().preemptive().basic("nexus-portal", url)
					.contentType("application/x-www-form-urlencoded").formParam("grant_type", "password")
					.formParam("username", userName).formParam("password", Password).when().post(url);


			try {
				boolean f = (response.path("error").toString()).contains("invalid_grant");
				if (f == true || response.path("access_token") == null) {
					Assert.fail("Authorization failed/Invalid Token/Check User Name");
				}
			} catch (NullPointerException e) {

			}

			auth_token = response.path("access_token").toString();
			
			// Cache the token with expiration time (from response or default 300 seconds)
			Integer expiresIn = response.path("expires_in");
			long expirationMs = (expiresIn != null && expiresIn > 0) ? expiresIn * 1000 : 300000;
			cachedToken = auth_token;
			tokenExpirationTime = System.currentTimeMillis() + expirationMs;
			System.out.println("✅ Token cached, expires in " + expirationMs / 1000 + " seconds");

		}
		return auth_token;

	}

	public static void Delay(int i) throws InterruptedException {
		Thread.sleep(i);
	}

	public static boolean CompanyDBRestore() {

		try {
			CommonMethods.Delay(10000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		try {
			PowerShell powerShell = PowerShell.openSession();
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

		postMethod(payload, uri, ver);

	}

	public static JsonPath postMethod(String fielpath, String uri, String version) throws InterruptedException {

		String baseUri = resolveBaseUri(version);
		if (baseUri == null) {
			Assert.fail("Invalid version: " + version);
			return null;
		}
		RestAssured.baseURI = baseUri;

		File jsonDataInFile = new File(fielpath);
		JSONObject bodycontent = null;
		try (FileReader reader = new FileReader(jsonDataInFile)) {
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
			e.printStackTrace();
		}

		RestAssured.config = RestAssuredConfig.config()
				.httpClient(HttpClientConfig.httpClientConfig().setParam("http.connection.timeout", 30000)
						.setParam("http.socket.timeout", 30000).setParam("http.connection-manager.timeout", 30000L));
		Response response;
		JsonPath jsonPathEvaluator;
		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println("Posting uri  = " + version + "   " + uri);

		ExtentReportManager.logRequest("POST", uri, version, bodycontent != null ? bodycontent.toString() : "N/A");

		RequestSpecification httpRequest = RestAssured.given()
				.headers("Authorization", "Bearer " + getToken(), "Content-Type", ContentType.JSON, "Accept", "*/*",
						"Connection", "keep-alive")
				.body(jsonDataInFile);

		System.out.println("Posting call Body :" + bodycontent.toString());
		response = httpRequest.post();
		System.out.println("Posting call Response :" + response.asString());

		ExtentReportManager.logResponse(response.getStatusCode(), response.asString());

		jsonPathEvaluator = response.jsonPath();
		Thread.sleep(1000);
		return jsonPathEvaluator;

	}

	public static Response postMethodResponseasString(String payload, String uri, String version)
			throws InterruptedException {

		String baseUri = resolveBaseUri(version);
		if (baseUri == null) {
			Assert.fail("Invalid version: " + version);
			return null;
		}
		RestAssured.baseURI = baseUri;

		File jsonDataInFile = new File(payload);

		Response response;
		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println("Posting uri  = " + version + "   " + uri);

		ExtentReportManager.logRequest("POST", uri, version, "Payload file: " + payload);

		RequestSpecification httpRequest = RestAssured.given()
				.headers("Authorization", "Bearer " + getToken(), "Content-Type", ContentType.JSON, "Accept", "*/*",
					"Connection", "keep-alive")
				.body(jsonDataInFile);
		response = httpRequest.post();
		System.out.println("Response :" + response.asString());

		ExtentReportManager.logResponse(response.getStatusCode(), response.asString());

		return response;

	}

	public static String postMethodResponseAsString(String payload, String uri, String version)
			throws InterruptedException {

		String baseUri = resolveBaseUri(version);
		if (baseUri == null) {
			Assert.fail("Invalid version: " + version);
			return null;
		}
		RestAssured.baseURI = baseUri;

		Response response;
		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println("Posting uri  = " + version + "   " + uri);

		ExtentReportManager.logRequest("POST", uri, version, payload);

		RequestSpecification httpRequest = RestAssured.given()
				.headers("Authorization", "Bearer " + getToken(), "Content-Type", ContentType.JSON, "Accept", "*/*",
						"Connection", "keep-alive")
				.body(payload);

		response = httpRequest.post();
		System.out.println("Response :" + response.asString());

		ExtentReportManager.logResponse(response.getStatusCode(), response.asString());

		return response.asString();

	}

	public static JsonPath postMethodStringPayload(String payload, String uri, String version)
			throws InterruptedException {

		String baseUri = resolveBaseUri(version);
		if (baseUri == null) {
			version = "Invalid version";
			RestAssured.baseURI = "";
		} else {
			RestAssured.baseURI = baseUri;
		}

		System.out.println(payload);
		Response response;
		JsonPath jsonPathEvaluator;
		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println("Posting uri  = " + version + "   " + uri);

		ExtentReportManager.logRequest("POST", uri, version, payload);

		RequestSpecification httpRequest = RestAssured.given()
				.headers("Authorization", "Bearer " + getToken(), "Content-Type", ContentType.JSON, "Accept", "*/*",
						"Connection", "keep-alive")
				.body(payload);

		response = httpRequest.post();
		System.out.println(response.asString());

		ExtentReportManager.logResponse(response.getStatusCode(), response.asString());

		jsonPathEvaluator = response.jsonPath();

		return jsonPathEvaluator;

	}

	public static String postMethodStringPayloadString(String payload, String uri, String version)
			throws InterruptedException {

		String baseUri = resolveBaseUri(version);
		if (baseUri == null) {
			version = "Invalid version";
			RestAssured.baseURI = "";
		} else {
			RestAssured.baseURI = baseUri;
		}

		Response response;
		JsonPath jsonPathEvaluator;
		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println("Posting uri  = " + version + "   " + uri);

		ExtentReportManager.logRequest("POST", uri, version, payload);

		RequestSpecification httpRequest = RestAssured.given()
				.headers("Authorization", "Bearer " + getToken(), "Content-Type", ContentType.JSON, "Accept", "*/*",
						"Connection", "keep-alive")
				.body(payload);

		System.out.println("Uri Payload =" + payload);
		response = httpRequest.post();
		System.out.println("Uri Response =" + response.asString());

		ExtentReportManager.logResponse(response.getStatusCode(), response.asString());

		jsonPathEvaluator = response.jsonPath();

		Thread.sleep(10000);
		return response.asString();

	}

	public static void postMethodString(String payload, String uri, String version, String expected)
			throws InterruptedException {

		String baseUri = resolveBaseUri(version);
		if (baseUri == null) {
			version = "Invalid version";
			RestAssured.baseURI = "";
		} else {
			RestAssured.baseURI = baseUri;
		}

		System.out.println("Payload = " + payload);
		Response response;
		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println("Posting uri  = " + version + "   " + uri);

		ExtentReportManager.logRequest("POST", uri, version, payload);

		RequestSpecification httpRequest = RestAssured.given()
				.headers("Authorization", "Bearer " + getToken(), "Content-Type", ContentType.JSON, "Accept", "*/*",
						"Connection", "keep-alive")
				.body(payload);

		response = httpRequest.post();
		System.out.println("Actual Response =" + response.asString());
		System.out.println("Expected Response =" + expected);

		ExtentReportManager.logResponse(response.getStatusCode(), response.asString());

		Thread.sleep(10000);

		try {
			Assert.assertEquals(response.asString(), expected);
			ExtentReportManager.logValidation(expected, response.asString(), true);
		} catch (AssertionError e) {
			ExtentReportManager.logValidation(expected, response.asString(), false);
			throw e;
		}

	}

	public static JsonPath getMethod(String uri, String version) throws InterruptedException {
		String baseUri = resolveBaseUri(version);
		if (baseUri == null) {
			version = "Invalid version";
			RestAssured.baseURI = "";
		} else {
			RestAssured.baseURI = baseUri;
		}

		System.out.println(RestAssured.baseURI);
		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println("Get URI :" + RestAssured.baseURI.toString());

		ExtentReportManager.logRequest("GET", uri, version, "");

		RequestSpecification httpRequest = RestAssured.given().headers("Authorization", "bearer " + getToken(),
				"Content-Type", ContentType.JSON, "Accept", "*/*", "Connection", "keep-alive", "Cache-Control", "no-cache", "urlEncodingEnabled", "false");

		Response response = httpRequest.get();

		ExtentReportManager.logResponse(response.getStatusCode(), response.asString());

		JsonPath jsonPathEvaluator = response.jsonPath();
		return jsonPathEvaluator;

	}

	public static JsonPath getMethod(String uri, String version, Map<String, String> responseMap)
			throws InterruptedException {
		String baseUri = resolveBaseUri(version);
		if (baseUri == null) {
			version = "Invalid version";
			RestAssured.baseURI = "";
		} else {
			RestAssured.baseURI = baseUri;
		}

		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println(RestAssured.baseURI.toString());

		ExtentReportManager.logRequest("GET", uri, version, responseMap.toString());

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

		ExtentReportManager.logResponse(response.getStatusCode(), response.asString());

		jsonPathEvaluator = response.jsonPath();
		System.out.print(response.prettyPrint());
		return jsonPathEvaluator;

	}

	public static Response getMethod(String uri, String version, HashMap<String, String> params)
			throws InterruptedException {
		String baseUri = resolveBaseUri(version);
		if (baseUri == null) {
			version = "Invalid version";
			RestAssured.baseURI = "";
		} else {
			RestAssured.baseURI = baseUri;
		}

		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println(RestAssured.baseURI.toString());

		ExtentReportManager.logRequest("GET", uri, version, params.toString());

		RequestSpecification httpRequest = RestAssured.given().headers("Authorization", "Bearer " + getToken(),
				"Content-Type", ContentType.JSON, "Connection", "keep-alive")
				.queryParams(params);

		Response response = httpRequest.get();

		ExtentReportManager.logResponse(response.getStatusCode(), response.asString());

		return response;

	}

	public static String getMethod(String uri, String version, HashMap<String, String> params, String jpath)
			throws InterruptedException, IOException {

		String baseUri = resolveBaseUri(version);
		if (baseUri == null) {
			Assert.fail("Invalid version: " + version);
			return null;
		}
		RestAssured.baseURI = baseUri;

		String expe = new String(Files.readAllBytes(Paths.get(jpath)));
		System.out.println("Expected Response as in file : " + jpath + " = " + expe);
		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println("Tesing URI:" + RestAssured.baseURI.toString());

		ExtentReportManager.logRequest("GET", uri, version, params.toString());

		RequestSpecification httpRequest = RestAssured.given().headers("Authorization", "Bearer " + getToken(),
				"Content-Type", ContentType.JSON, "Connection", "keep-alive")
				.queryParams(params);

		Response response = httpRequest.get();
		System.out.print(response.asString());

		ExtentReportManager.logResponse(response.getStatusCode(), response.asString());

		try {
			Assert.assertEquals(response.asString(), expe);
			ExtentReportManager.logValidation(expe, response.asString(), true);
		} catch (AssertionError e) {
			ExtentReportManager.logValidation(expe, response.asString(), false);
			throw e;
		}

		return response.asString();
	}

	public static String putMethod(String uri, String version, HashMap<String, String> params, String payload,
			String responseFile) throws InterruptedException, IOException {

		String baseUri = resolveBaseUri(version);
		if (baseUri == null) {
			version = "Invalid version";
			RestAssured.baseURI = "";
		} else {
			RestAssured.baseURI = baseUri;
		}

		File jsonDataInFile = new File(payload);
		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println(RestAssured.baseURI.toString());

		ExtentReportManager.logRequest("PUT", uri, version,
				"Params: " + params.toString() + " | Payload file: " + payload);

		RequestSpecification httpRequest = RestAssured
				.given().headers("Authorization", "Bearer " + getToken(), "Content-Type", ContentType.JSON,
						"Connection", "keep-alive")
				.queryParams(params).body(jsonDataInFile);
		String expe = new String(Files.readAllBytes(Paths.get(responseFile)));
		System.out.println("Expected Response as in file : " + expe);
		Response responseTest = httpRequest.put();
		System.out.println(responseTest.asString());

		ExtentReportManager.logResponse(responseTest.getStatusCode(), responseTest.asString());

		ValidatableResponse response = httpRequest.put().then().assertThat().statusCode(200)
				.body(Matchers.equalTo(new String(Files.readAllBytes(Paths.get(responseFile)))));

		ExtentReportManager.logValidation(expe, response.extract().asString(), true);

		System.out.println(response.extract().asString());
		System.out.println(response.log());

		return response.extract().asString();

	}

	public static ValidatableResponse putMethod(String uri, String version, String payload)
			throws InterruptedException, IOException {

		String baseUri = resolveBaseUri(version);
		if (baseUri == null) {
			version = "Invalid version";
			RestAssured.baseURI = "";
		} else {
			RestAssured.baseURI = baseUri;
		}

		File jsonDataInFile = new File(payload);
		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println(RestAssured.baseURI.toString());

		ExtentReportManager.logRequest("PUT", uri, version, "Payload file: " + payload);

		RequestSpecification httpRequest = RestAssured.given().headers("Authorization", "Bearer " + getToken(),
				"Content-Type", ContentType.JSON, "Connection", "keep-alive")
				.body(jsonDataInFile);
		System.out.println(httpRequest.toString());
		ValidatableResponse response = httpRequest.put().then().log().all();

		ExtentReportManager.logResponse(200, response.extract().asString());

		return response;

	}

	public static String putMethodString(String uri, String version, HashMap<String, String> params, String payload,
			String expectedResponse) throws InterruptedException, IOException {

		String baseUri = resolveBaseUri(version);
		if (baseUri == null) {
			Assert.fail("Invalid version provided: " + version);
			return null;
		}
		RestAssured.baseURI = baseUri;

		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println("PUT URI: " + RestAssured.baseURI.toString());
		System.out.println("Payload: " + payload);
		System.out.println("Expected Response: " + expectedResponse);

		ExtentReportManager.logRequest("PUT", uri, version, "Params: " + params.toString() + " | Payload: " + payload);

		RequestSpecification httpRequest = RestAssured.given()
				.headers("Authorization", "Bearer " + getToken(),
						"Content-Type", ContentType.JSON,
						"Connection", "keep-alive",
						"Accept-Encoding", "gzip, deflate, br")
				.queryParams(params)
				.body(payload);

		Response response = httpRequest.put();
		String actualResponse = response.asString();

		System.out.println("Actual Response: " + actualResponse);
		System.out.println("Status Code: " + response.getStatusCode());

		ExtentReportManager.logResponse(response.getStatusCode(), actualResponse);

		try {
			ValidatableResponse validatableResponse = response.then()
					.assertThat()
					.statusCode(200)
					.body(Matchers.equalTo(expectedResponse));

			System.out.println("Response Validation Passed");
			ExtentReportManager.logValidation(expectedResponse, actualResponse, true);

			return validatableResponse.extract().asString();
		} catch (AssertionError e) {
			ExtentReportManager.logValidation(expectedResponse, actualResponse, false);
			throw e;
		}
	}

	public static String putMethodString(String uri, String version, String payload,
			String expectedResponse) throws InterruptedException, IOException {

		String baseUri = resolveBaseUri(version);
		if (baseUri == null) {
			Assert.fail("Invalid version provided: " + version);
			return null;
		}
		RestAssured.baseURI = baseUri;

		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println("PUT URI: " + RestAssured.baseURI.toString());
		System.out.println("Payload: " + payload);
		System.out.println("Expected Response: " + expectedResponse);

		ExtentReportManager.logRequest("PUT", uri, version, payload);

		RequestSpecification httpRequest = RestAssured.given()
				.headers("Authorization", "Bearer " + getToken(),
						"Content-Type", ContentType.JSON,
						"Connection", "keep-alive",
						"Accept-Encoding", "gzip, deflate, br")
				.body(payload);

		Response response = httpRequest.put();
		String actualResponse = response.asString();

		System.out.println("Actual Response: " + actualResponse);
		System.out.println("Status Code: " + response.getStatusCode());

		ExtentReportManager.logResponse(response.getStatusCode(), actualResponse);

		try {
			ValidatableResponse validatableResponse = response.then()
					.assertThat()
					.statusCode(200)
					.body(Matchers.equalTo(expectedResponse));

			System.out.println("Response Validation Passed");
			ExtentReportManager.logValidation(expectedResponse, actualResponse, true);

			return validatableResponse.extract().asString();
		} catch (AssertionError e) {
			ExtentReportManager.logValidation(expectedResponse, actualResponse, false);
			throw e;
		}
	}

	public static ValidatableResponse putMethodvalidate(String uri, String version, String payload, String fresponse)
			throws InterruptedException, IOException {

		String baseUri = resolveBaseUri(version);
		if (baseUri == null) {
			version = "Invalid version";
			RestAssured.baseURI = "";
		} else {
			RestAssured.baseURI = baseUri;
		}

		File jsonDataInFile = new File(payload);
		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println(RestAssured.baseURI.toString());

		ExtentReportManager.logRequest("PUT", uri, version, "Payload file: " + payload);

		RequestSpecification httpRequest = RestAssured.given().headers("Authorization", "Bearer " + getToken(),
				"Content-Type", ContentType.JSON, "Connection", "keep-alive")
				.body(jsonDataInFile);
		String expe = new String(Files.readAllBytes(Paths.get(fresponse)));
		System.out.println("Expected Response as in file : " + expe);

		try {
			ValidatableResponse response = httpRequest.put().then().assertThat()
					.body(Matchers.equalTo(new String(Files.readAllBytes(Paths.get(fresponse))))).assertThat()
					.statusCode(200);

			ExtentReportManager.logResponse(200, response.extract().asString());
			ExtentReportManager.logValidation(expe, response.extract().asString(), true);

			return response;
		} catch (AssertionError e) {
			ExtentReportManager.logFail("<b>Validation FAILED</b> — Expected response from file: " + fresponse);
			throw e;
		}

	}

	public static Response putMethod(String uri, String version, String payloadfile, String jsonDataInFile)
			throws InterruptedException, IOException {

		String baseUri = resolveBaseUri(version);
		if (baseUri == null) {
			version = "Invalid version";
			RestAssured.baseURI = "";
		} else {
			RestAssured.baseURI = baseUri;
		}

		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println(RestAssured.baseURI.toString());

		ExtentReportManager.logRequest("PUT", uri, version, payloadfile);

		RequestSpecification httpRequest = RestAssured.given().headers("Authorization", "Bearer " + getToken(),
				"Content-Type", ContentType.JSON, "Connection", "keep-alive")
				.body(payloadfile);
		System.out.println("** PUT call uri ** " + RestAssured.baseURI);
		System.out.println("** PUT call payload ** " + payloadfile);
		Response response = httpRequest.put();

		ExtentReportManager.logResponse(response.getStatusCode(), response.asString());

		String expected = new String(Files.readAllBytes(Paths.get(jsonDataInFile)));
		try {
			Assert.assertEquals(response.getBody().asString(), expected);
			ExtentReportManager.logValidation(expected, response.getBody().asString(), true);
		} catch (AssertionError e) {
			ExtentReportManager.logValidation(expected, response.getBody().asString(), false);
			throw e;
		}

		System.out.println("** PUT call Response ** " + response.asString());

		Thread.sleep(10000);
		return response;

	}

	public static String putMethodstring(String uri, String version, String payload, String expected)
			throws InterruptedException, IOException {

		String baseUri = resolveBaseUri(version);
		if (baseUri == null) {
			version = "Invalid version";
			RestAssured.baseURI = "";
		} else {
			RestAssured.baseURI = baseUri;
		}

		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println(RestAssured.baseURI.toString());

		ExtentReportManager.logRequest("PUT", uri, version, payload);

		RequestSpecification httpRequest = RestAssured.given().headers("Authorization", "Bearer " + getToken(),
				"Content-Type", ContentType.JSON, "Connection", "keep-alive")
				.body(payload);
		System.out.println("** PUT call Body **" + payload);
		Response response = httpRequest.put();
		System.out.println("** PUT call Response **");
		System.out.println(response.getBody().asString());

		ExtentReportManager.logResponse(response.getStatusCode(), response.getBody().asString());

		try {
			Assert.assertEquals(response.getBody().asString(), expected);
			ExtentReportManager.logPass("Response matches expected output");
		} catch (AssertionError e) {
			ExtentReportManager.logFail("Response does NOT match expected output");
			throw e;
		}

		return response.getBody().asString();

	}

	public static ValidatableResponse putMethodNofile(String uri, String version, String payload, String jsonDataInFile)
			throws InterruptedException, IOException {

		String baseUri = resolveBaseUri(version);
		if (baseUri == null) {
			version = "Invalid version";
			RestAssured.baseURI = "";
		} else {
			RestAssured.baseURI = baseUri;
		}

		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println(RestAssured.baseURI.toString());

		ExtentReportManager.logRequest("PUT", uri, version, payload);

		RequestSpecification httpRequest = RestAssured.given().headers("Authorization", "Bearer " + getToken(),
				"Content-Type", ContentType.JSON, "Connection", "keep-alive")
				.body(payload);

		String expected = new String(Files.readAllBytes(Paths.get(jsonDataInFile)));

		try {
			ValidatableResponse response = httpRequest.put().then().assertThat()
					.body(Matchers.equalTo(expected));

			System.out.println("** PUT call Response **");
			System.out.println(response.extract().asString());

			ExtentReportManager.logResponse(200, response.extract().asString());
			ExtentReportManager.logValidation(expected, response.extract().asString(), true);

			return response;
		} catch (AssertionError e) {
			ExtentReportManager.logValidation(expected, "Assertion failed", false);
			throw e;
		}

	}

	public static JsonPath getMethodTest() throws InterruptedException, IOException {

		String version = "2.4";
		String baseUri = resolveBaseUri(version);
		if (baseUri != null) {
			RestAssured.baseURI = baseUri;
		}

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "loc@0001");
		params.put("CustomerId", "0000011111");
		params.put("UserDate", "2027-04-12");

		String uri = "/accountBalance/getAccountBalances";
		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println(RestAssured.baseURI.toString());

		ExtentReportManager.logRequest("GET", uri, version, params.toString());

		RequestSpecification httpRequest = RestAssured.given().headers("Authorization", "Bearer " + getToken(),
				"Content-Type", ContentType.JSON, "Connection", "keep-alive")
				.queryParams(params);

		ValidatableResponse response = httpRequest.get().then().assertThat()
				.body(Matchers.equalTo(new String(Files.readAllBytes(Paths.get("./TestData\\accountBalance.json")))));
		JsonPath jsonPathEvaluator = null;
		System.out.println(response.extract().asString());

		ExtentReportManager.logResponse(200, response.extract().asString());

		return jsonPathEvaluator;

	}

	public static ValidatableResponse getMethod(String uri, String version, String pathToResponse)
			throws InterruptedException, IOException {
		String baseUri = resolveBaseUri(version);
		if (baseUri == null) {
			version = "Invalid version";
			RestAssured.baseURI = "";
		} else {
			RestAssured.baseURI = baseUri;
		}

		Path jsonDataInFile = Paths.get(pathToResponse);

		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println("Posting uri  = " + version + "   " + uri);
		String expe = new String(Files.readAllBytes(Paths.get(pathToResponse)));
		System.out.println("Expected Response as in file : " + expe);

		ExtentReportManager.logRequest("GET", uri, version, "Expected from file: " + pathToResponse);

		RestAssured.baseURI = RestAssured.baseURI + uri;
		RequestSpecification httpRequest = RestAssured.given().headers("Authorization", "Bearer " + getToken(),
				"Content-Type", ContentType.JSON, "Connection", "keep-alive");

		ValidatableResponse response = httpRequest.get().then().assertThat().statusCode(200);

		ExtentReportManager.logResponse(200, response.extract().asString());

		System.out.println(response.toString());
		return response;

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
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			Statement stmt = con.createStatement();
			System.out.println("Executing query: " + Command);
			ResultSet rs = stmt.executeQuery(Command);

			if (!rs.next()) {
				System.out.println("No results found for query: " + Command);
				return null;
			}

			System.out.println("Column names:");
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				System.out.println("Column " + i + ": " + rsmd.getColumnName(i));
			}

			do {
				Result = rs.getString(columnName);
				System.out.println("Fetched " + columnName + ": " + Result);
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
	}

	public static boolean cancelSpa(String spaIndexfromdb, String Customer)
			throws ConnectionClosedException, InterruptedException {
		char q = '"';
		RestAssured.baseURI = "http://localhost:3000/api/v2/spa/cancel";
		String rawbody = "{ " + q + "SpaCancel" + q + " :[{ " + q + "CustomerId" + q + ":" + q + Customer + q + "," + q
				+ "SpaIndex" + q + ":" + q + spaIndexfromdb + q + "," + q + "CancelDate" + q + ":" + q + "2020-06-08"
				+ q + "," + q + "CancelReason" + q + ":" + q + "Test" + q + "," + q + "ReasonCode" + q + ":" + q + " "
				+ q + "}]}";

		ExtentReportManager.logRequest("PUT", "/spa/cancel", "v2", rawbody);

		RequestSpecification httpRequest = RestAssured.given()
				.headers("Authorization", "Bearer " + getToken(), "Content-Type", ContentType.JSON, "Accept", "*/*",
						"Connection", "keep-alive")
				.body(rawbody);

		Response response = httpRequest.put();

		ExtentReportManager.logResponse(response.getStatusCode(), response.asString());

		JsonPath jsonPathEvaluator = response.jsonPath();
		Boolean result = true;
		try {
			result = jsonPathEvaluator.get("SPACancel[0].Success");
		} catch (IllegalArgumentException e) {

		}
		if (result == false) {
			System.out.println(
					"Active (Open) special payment arrangement does not exist for customer at " + spaIndexfromdb);
			ExtentReportManager.logWarning("No active SPA found for customer at index " + spaIndexfromdb);
			return false;
		}

		System.out.println("Active (Open) special payment arrangement cancelled for customer at " + spaIndexfromdb);
		ExtentReportManager.logPass("SPA cancelled for customer at index " + spaIndexfromdb);
		return result;

	}

	public static String getMethodContains(String uri, String version, HashMap<String, String> params, String jpath)
			throws IOException, InterruptedException {

		String baseUri = resolveBaseUri(version);
		if (baseUri == null) {
			Assert.fail("Invalid version: " + version);
			return null;
		}
		RestAssured.baseURI = baseUri;

		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println(RestAssured.baseURI.toString());

		ExtentReportManager.logRequest("GET", uri, version, params.toString());

		RequestSpecification httpRequest = RestAssured.given().headers("Authorization", "Bearer " + getToken(),
				"Content-Type", ContentType.JSON, "Connection", "keep-alive")
				.queryParams(params);

		String validate = new String(Files.readAllBytes(Paths.get(jpath)));
		System.out.println("Veriying String =" + validate);

		Response response = httpRequest.get();
		System.out.println("Response Body: " + response.getBody().asString());

		ExtentReportManager.logResponse(response.getStatusCode(), response.asString());

		try {
			response.then().assertThat().body(containsString(validate));
			ExtentReportManager.logPass("Response contains expected string from file: " + jpath);
		} catch (AssertionError e) {
			ExtentReportManager.logFail("Response does NOT contain expected string from file: " + jpath);
			throw e;
		}

		return response.asString();
	}

	public static void getMethodContainsString(String uri, String version, HashMap<String, String> params,
			String expected) throws IOException, InterruptedException {

		String baseUri = resolveBaseUri(version);
		if (baseUri == null) {
			Assert.fail("Invalid version: " + version);
			return;
		}
		RestAssured.baseURI = baseUri;

		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println(RestAssured.baseURI.toString());

		ExtentReportManager.logRequest("GET", uri, version, params.toString());

		RequestSpecification httpRequest = RestAssured.given().headers("Authorization", "Bearer " + getToken(),
				"Content-Type", ContentType.JSON, "Connection", "keep-alive")
				.queryParams(params);

		System.out.println("Veriying String =" + expected);

		Response rawResponse = httpRequest.get();

		ExtentReportManager.logResponse(rawResponse.getStatusCode(), rawResponse.asString());

		try {
			rawResponse.then().assertThat().body(Matchers.containsString(expected));
			ExtentReportManager.logPass("Response contains expected string");
		} catch (AssertionError e) {
			ExtentReportManager.logFail("Response does NOT contain expected string");
			ExtentReportManager.logValidation(expected, rawResponse.asString(), false);
			throw e;
		}

		System.out.println("Response  = validated successfully");

	}

	public static String getMethodasString(String uri, String version, HashMap<String, String> params)
			throws IOException, InterruptedException {

		String baseUri = resolveBaseUri(version);
		if (baseUri == null) {
			Assert.fail("Invalid version: " + version);
			return null;
		}
		RestAssured.baseURI = baseUri;

		System.out.println("===============================================");
		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println(RestAssured.baseURI.toString());

		ExtentReportManager.logRequest("GET", uri, version, params.toString());

		RequestSpecification httpRequest = RestAssured.given().headers("Authorization", "Bearer " + getToken(),
				"Content-Type", ContentType.JSON, "Connection", "keep-alive")
				.queryParams(params);

		String response;
		System.out.println("===============================================");
		System.out.println("===============================================");
		response = httpRequest.get().asString();
		System.out.println("URI :" + RestAssured.baseURI.toString());
		System.out.println("Response :" + response);

		ExtentReportManager.logResponse(200, response);

		return response;
	}

	public static String getMethodasString(String uri, String version, String rawbody)
			throws IOException, InterruptedException {

		String baseUri = resolveBaseUri(version);
		if (baseUri == null) {
			Assert.fail("Invalid version: " + version);
			return null;
		}
		RestAssured.baseURI = baseUri;

		RestAssured.baseURI = RestAssured.baseURI + uri;

		ExtentReportManager.logRequest("GET", uri, version, rawbody);

		RequestSpecification httpRequest = RestAssured.given().headers("Authorization", "Bearer " + getToken(),
				"Content-Type", ContentType.JSON, "Connection", "keep-alive")
				.body(rawbody);

		String response;
		response = httpRequest.get().asString();
		System.out.println("URI :" + RestAssured.baseURI.toString());
		System.out.println("Response :" + response);

		ExtentReportManager.logResponse(200, response);

		return response;
	}

	public static String deleteMethod(String uri, String version, String jpath)
			throws InterruptedException, IOException {

		String baseUri = resolveBaseUri(version);
		if (baseUri == null) {
			version = "Invalid version";
			RestAssured.baseURI = "";
		} else {
			RestAssured.baseURI = baseUri;
		}

		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println(RestAssured.baseURI.toString());

		ExtentReportManager.logRequest("DELETE", uri, version, "Expected from file: " + jpath);

		RequestSpecification httpRequest = RestAssured.given().headers("Authorization", "Bearer " + getToken(),
				"Content-Type", ContentType.JSON, "Connection", "keep-alive");

		ValidatableResponse response;
		String validate = new String(Files.readAllBytes(Paths.get(jpath)));
		System.out.println("Veriying String =" + validate);

		try {
			response = httpRequest.delete().then().assertThat().body(Matchers.containsString(validate));
			System.out.println(response.extract().asString());

			ExtentReportManager.logResponse(200, response.extract().asString());
			ExtentReportManager.logPass("DELETE response contains expected string");

			return response.extract().asString();
		} catch (AssertionError e) {
			ExtentReportManager.logFail("DELETE response does NOT contain expected string from: " + jpath);
			throw e;
		}

	}

	public static void deleteMethodvoid(String uri, String version, String expected)
			throws InterruptedException, IOException {

		String baseUri = resolveBaseUri(version);
		if (baseUri == null) {
			version = "Invalid version";
			RestAssured.baseURI = "";
		} else {
			RestAssured.baseURI = baseUri;
		}

		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println(RestAssured.baseURI.toString());

		ExtentReportManager.logRequest("DELETE", uri, version, "");

		RequestSpecification httpRequest = RestAssured.given().headers("Authorization", "Bearer " + getToken(),
				"Content-Type", ContentType.JSON, "Connection", "keep-alive");

		Response response;

		response = httpRequest.delete();
		System.out.println(response.asString());

		ExtentReportManager.logResponse(response.getStatusCode(), response.asString());

		try {
			Assert.assertEquals(response.asString(), expected);
			ExtentReportManager.logValidation(expected, response.asString(), true);
		} catch (AssertionError e) {
			ExtentReportManager.logValidation(expected, response.asString(), false);
			throw e;
		}

	}

	public static String deleteMethodasString(String uri, String version) throws InterruptedException, IOException {

		String baseUri = resolveBaseUri(version);
		if (baseUri == null) {
			version = "Invalid version";
			RestAssured.baseURI = "";
		} else {
			RestAssured.baseURI = baseUri;
		}

		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println(RestAssured.baseURI.toString());

		ExtentReportManager.logRequest("DELETE", uri, version, "");

		RequestSpecification httpRequest = RestAssured.given().headers("Authorization", "Bearer " + getToken(),
				"Content-Type", ContentType.JSON, "Connection", "keep-alive");

		String response = httpRequest.delete().asString();
		System.out.println(response);

		ExtentReportManager.logResponse(200, response);

		return response;
	}

	public static String deleteMethodasString(String uri, String version, HashMap<String, String> params)
			throws InterruptedException, IOException {

		String baseUri = resolveBaseUri(version);
		if (baseUri == null) {
			version = "Invalid version";
			RestAssured.baseURI = "";
		} else {
			RestAssured.baseURI = baseUri;
		}

		RestAssured.baseURI = RestAssured.baseURI + uri;
		System.out.println(RestAssured.baseURI.toString());

		ExtentReportManager.logRequest("DELETE", uri, version, params.toString());

		RequestSpecification httpRequest = RestAssured.given().headers("Authorization", "Bearer " + getToken(),
				"Content-Type", ContentType.JSON, "Connection", "keep-alive");
		httpRequest.queryParams(params);
		String response = httpRequest.delete().asString();
		System.out.println(response);

		ExtentReportManager.logResponse(200, response);

		return response;
	}

	public static void postcall(String uri, String payload, String ver, String exResult) throws InterruptedException {
		Response jsonPathResponse;
		jsonPathResponse = CommonMethods.postMethodResponseasString(payload, uri, ver);

		try {
			Assert.assertEquals(jsonPathResponse.asString(), exResult);
			ExtentReportManager.logValidation(exResult, jsonPathResponse.asString(), true);
		} catch (AssertionError e) {
			ExtentReportManager.logValidation(exResult, jsonPathResponse.asString(), false);
			throw e;
		}

	}

	public static void postcallcontains(String uri, String payload, String ver, String exResult)
			throws InterruptedException {
		Response jsonPathResponse;
		jsonPathResponse = CommonMethods.postMethodResponseasString(payload, uri, ver);
		System.out.println("Response :" + jsonPathResponse.asString());

		try {
			Assert.assertTrue(jsonPathResponse.asString().contains(exResult));
			ExtentReportManager.logPass("Response contains expected string");
		} catch (AssertionError e) {
			ExtentReportManager.logFail("Response does NOT contain expected string: " + exResult);
			throw e;
		}

	}

	public static void Match(String ss, String tomatch) throws Exception {
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
		ExtentReportManager.logWarning("Known Bug: " + str1 + " — Test skipped");
		throw new SkipException("Skipping this method due to bug = " + str1);
	}

	public static String getToken3step() throws InterruptedException {
		String kcBase = "http://localhost:8080/realms/nexus";
		String clientId = "nexus-portal";
		String redirectUri = "https://oauth.pstmn.io/v1/callback";
		String username = "cogsuser";
		String password = "password";

		Map<String, Object> context = KeycloakStep1And2And3WithCookies.stage1Auth(kcBase, clientId, redirectUri);

		if ("need_login".equals(context.get("flow_step"))) {
			context.putAll(KeycloakStep1And2And3WithCookies.step2Login(
					(String) context.get("kc_login_action"),
					username,
					password,
					"on",
					(Map<String, String>) context.get("cookies")));
		}

		Map<String, Object> tokens = KeycloakStep1And2And3WithCookies.step3TokenExchange(
				kcBase,
				clientId,
				redirectUri,
				(String) context.getOrDefault("auth_code", ""),
				(String) context.get("pkce_verifier"),
				(Map<String, String>) context.get("cookies"));

		return (String) tokens.get("access_token");

	}

}
