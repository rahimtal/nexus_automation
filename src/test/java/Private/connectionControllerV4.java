package Private;

import org.testng.annotations.Test;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;
import com.NexustAPIAutomation.java.Retry;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

public class connectionControllerV4 {

	// @Test(priority = 1, groups = "connectionController", retryAnalyzer = Retry.class)
	public void postconnectionmeter1v4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// JsonPath jsonPathEvaluator;
		CommonMethods.Bug("CPDEV-17054");
		String uri = "/connection/meter";
		String ver = "4.0";
		String payload = "./\\TestData\\/postconnectionv4.json";
		String exResponse = "{\"Connection\":{\"Success\":true,\"Data\":{\"LocationId\":\"LOCATION001\",\"ConnectionSequence\":3},\"Messages\":[{\"Enabled\":1,\"Info\":\"Created\",\"Level\":1}]}}";
		CommonMethods.postcall(uri, payload, ver, exResponse);

	}

	// @Test(priority = 2, groups = "connectionController", retryAnalyzer = Retry.class)
	public void postconnectionmeterv4Error()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// JsonPath jsonPathEvaluator;
		String uri = "/connection/meter";
		String ver = "4.0";
		String payload = "./\\TestData\\/postconnectionv4.json";
		String exResponse = "{\"Connection\":{\"Success\":false,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Connection.Confirm is required\",\"Level\":3}]}}";
		CommonMethods.postcall(uri, payload, ver, exResponse);

	}

	@Test(priority = 3, groups = "connectionController", retryAnalyzer = Retry.class)
	public void getcollectioncriteriav4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/connection/LOCATION011/";
		String version = "4.0";
		String expected = "{\"Connection\":{\"Success\":true,\"Data\":{\"LocationId\":\"LOCATION011\",\"Description\":\"\",\"LocationClass\":\"\",\"CurrentCustomerId\":\"CUSTOMER012\",\"ZoneId\":\"2\",\"RouteId\":\"\",\"CycleId\":\"\",\"Service\":[{\"CustomerId\":\"CUSTOMER012\",\"Type\":{\"Id\":\"ELECTRIC\",\"Description\":\"Residential electrical accounts\"},\"Category\":{\"Id\":1,\"Description\":\"Electric\"},\"ConnectionSequence\":1,\"AlternateConnection\":false,\"SubtractConnection\":false,\"BaseConnectionSequence\":0,\"EquipmentId\":\"EQUIPMENT013\",\"DiscountRate\":\"\",\"FlatRate\":\"\",\"TaxDiscountPercent\":0.00000,\"NetMeter\":{\"Description\":\"None\",\"DeliveredMeterId\":\"\"},\"Multiplier\":{\"Rate\":1.00000,\"Fixed\":1.00000,\"Loss\":1.00000,\"Consumption\":1.00000,\"RangeAndMinimum\":1.00000,\"Total\":1.00000},\"Route\":{\"Id\":\"ROUTEE001\",\"SequenceNumber\":901},\"MeterGroup\":{\"Id\":\"\",\"Type\":{\"Id\":0,\"Description\":\"\"},\"MultiplierId\":\"\"},\"CycleId\":\"TESTCYCLEQUART\",\"Rate\":[{\"Period\":{\"Index\":1,\"Name\":\"ON PEAK\",\"Description\":\"On Peak Consumption\",\"Consumption\":\"\",\"KW\":\"GS-PK DEMAND\",\"KVA\":\"\",\"NetMeterReceived\":\"\"}},{\"Period\":{\"Index\":2,\"Name\":\"OFFPEAK\",\"Description\":\"Off Peak Consumption\",\"Consumption\":\"\",\"KW\":\"GS-OFFPKDEMAND\",\"KVA\":\"\",\"NetMeterReceived\":\"\"}}],\"ConnectionDate\":\"1997-01-01\",\"DisconnectionDate\":\"1900-01-01\",\"InstallationDate\":\"1997-01-01\",\"Status\":{\"Id\":2,\"Description\":\"Active\"},\"CustomStatus\":{\"Id\":2,\"Description\":\"Active\"},\"Attributes\":{\"Transponder\":\"\"},\"Alternate\":[],\"Subtract\":[]}]},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("ConnectionSequence", "1");
		// params.put("LocationId", "LOCATION011");
		String actual = CommonMethods.getMethodasString(uri, version, params);
		Assert.assertEquals(expected, actual);
	}

	@Test(priority = 4, groups = "connectionController", retryAnalyzer = Retry.class)
	public void postconnectionFlatv4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// JsonPath jsonPathEvaluator;

		String uri = "/connection/flat";
		String ver = "4.0";
		String payload = "{\"Connection\": {\r\n" + "        \"LocationId\": \"000000000523000\",\r\n"
				+ "		\"Service\": {\r\n" + "			\"TypeId\": \"water\",\r\n"
				+ "			\"RateId\": \"WATERFLAT\",\r\n" + "            \"TaxDiscountPercent\": 1.00000,\r\n"
				+ "			\"Multiplier\": {\r\n" + "                \"Rate\": 1,\r\n"
				+ "                \"Fixed\": 1,\r\n" + "                \"Loss\": 1,\r\n"
				+ "                \"Consumption\": 1,\r\n" + "                \"RangeAndMinimum\": 1\r\n"
				+ "            },\r\n" + "            \"RouteId\": \"002\",\r\n"
				+ "			\"ConnectionDate\": \"1900-01-01\",\r\n" + "			\"Status\": 1\r\n" + "}\r\n"
				+ "}\r\n" + "}";
		String exResponse = "{\"Connection\":{\"Success\":true,\"Data\":{\"LocationId\":\"000000000523000\",\"ConnectionSequence\":7},\"Messages\":[{\"Enabled\":1,\"Info\":\"Created\",\"Level\":1}]}}";
		CommonMethods.postMethodString(payload, uri, ver, exResponse);

	}

	@Test(priority = 5, groups = "connectionController", retryAnalyzer = Retry.class)
	public void postconnectionalternatev4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// JsonPath jsonPathEvaluator;

		String uri = "/connection/alternate";
		String ver = "4.0";
		String payload = "{\r\n" + "    \"Connection\": {\r\n" + "        \"LocationId\": \"AUTO1001\",\r\n"
				+ "        \"BaseSequenceNumber\": 1,\r\n" + "        \"ServiceTypeId\": \"ELECTRIC\",\r\n"
				+ "        \"RouteId\": \"001\",\r\n" + "        \"DiscountRate\": \"\",\r\n"
				+ "        \"ConnectionDate\": \"1900-01-01\",\r\n" + "        \"Status\": 1,\r\n"
				+ "        \"DisconnectionDate\":\"1900-01-01\",\r\n" + "        \"TaxDiscountPercent\": 1.00000,\r\n"
				+ "        \"Multiplier\": {\r\n" + "            \"Rate\": 1,\r\n" + "            \"Fixed\": 1,\r\n"
				+ "            \"Loss\": 1,\r\n" + "            \"Consumption\": 1,\r\n"
				+ "            \"RangeAndMinimum\": 1\r\n" + "        },\r\n" + "        \"Rate\": [\r\n"
				+ "            {\r\n" + "                \"PeriodIndex\": 1,\r\n"
				+ "                \"Consumption\": \"RATE001-FIXED\",\r\n" + "                \"KW\": \"\",\r\n"
				+ "                \"KVA\": \"\",\r\n" + "                \"NetMeterReceived\": \"\"\r\n"
				+ "            }          \r\n" + "        ]\r\n" + "    }\r\n" + "}";
		String exResponse = "{\"Connection\":{\"Success\":true,\"Data\":{\"LocationId\":\"AUTO1001\",\"AlternateConnectionSequence\":2},\"Messages\":[{\"Enabled\":1,\"Info\":\"Created\",\"Level\":1}]}}";
		CommonMethods.postMethodString(payload, uri, ver, exResponse);

	}

	@Test(priority = 6, groups = "connectionController", retryAnalyzer = Retry.class)
	public void postconnectionmeterv4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// JsonPath jsonPathEvaluator;

		String uri = "/connection/meter";
		String ver = "4.0";
		String payload = "{\r\n" + "    \"Connection\": {\r\n" + "        \"ConnectionDate\": \"1900-01-01\",\r\n"
				+ "        \"DisconnectionDate\": \"1900-01-01\",\r\n" + "        \"DiscountRate\": \"\",\r\n"
				+ "        \"EquipmentId\": \"EQUIPMENT006\",\r\n" + "        \"InstallationDate\": \"2023-01-01\",\r\n"
				+ "        \"IsSubtractConnection\": false,\r\n" + "        \"LocationId\": \"AUTO1001\",\r\n"
				+ "        \"Multiplier\": {\r\n" + "            \"Rate\": 1,\r\n" + "            \"Fixed\": 1,\r\n"
				+ "            \"Loss\": 1,\r\n" + "            \"Consumption\": 1,\r\n"
				+ "            \"RangeAndMinimum\": 1\r\n" + "        },\r\n"
				+ "        \"NetMeterDeliveryMeter\": \"\",\r\n" + "        \"Rate\": [\r\n" + "            {\r\n"
				+ "                \"Consumption\": \"RATE001-FIXED\",\r\n" + "                \"KVA\": \"\",\r\n"
				+ "                \"KW\": \"\",\r\n" + "                \"NetMeterReceived\": \"\",\r\n"
				+ "                \"PeriodIndex\": 1\r\n" + "            }\r\n" + "        ],\r\n"
				+ "        \"RouteId\": \"001\",\r\n" + "        \"SequenceNumber\": 0,\r\n"
				+ "        \"ServiceTypeId\": \"ELECTRIC\",\r\n" + "        \"Status\": \"1\",\r\n"
				+ "        \"SubtractBaseConnectionSequence\": 0,\r\n" + "        \"TaxDiscountPercent\": 1.00000,\r\n"
				+ "        \"Confirm\": {\r\n" + "            \"IgnoreEquipmentReinstallValidation\": false,\r\n"
				+ "            \"EquipmentReinstall\": false\r\n" + "        }\r\n" + "    }\r\n" + "}";
		String exResponse = "{\"Connection\":{\"Success\":true,\"Data\":{\"LocationId\":\"AUTO1001\",\"ConnectionSequence\":3},\"Messages\":[{\"Enabled\":1,\"Info\":\"Created\",\"Level\":1}]}}";
		CommonMethods.postMethodString(payload, uri, ver, exResponse);

	}

	@Test(priority = 7, groups = "connectionController", retryAnalyzer = Retry.class)
	public void putconnectionmeterv4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// JsonPath jsonPathEvaluator;

		String uri = "/connection/meter";
		String ver = "4.0";
		String payload = "{\r\n" + 
				"	\"Connection\": {\r\n" + 
				"        \"LocationId\": \"AUTO1001\",\r\n" + 
				"		\"ConnectionSequence\": 1,\r\n" + 
				"		\"EquipmentId\": \"EQUIPMENT021\",\r\n" + 
				"        \"RouteId\": \"001\",\r\n" + 
				"        \"DiscountRate\": \"\",\r\n" + 
				"        \"SequenceNumber\": 0,\r\n" + 
				"        \"ConnectionDate\": \"2023-01-02\",\r\n" + 
				"        \"DisconnectionDate\": \"1900-01-01\",\r\n" + 
				"        \"InstallationDate\": \"2023-01-01\",\r\n" + 
				"        \"Status\": 2,\r\n" + 
				"        \"TaxDiscountPercent\": 1.00000,\r\n" + 
				"        \"Multiplier\": {\r\n" + 
				"            \"Rate\": 1,\r\n" + 
				"            \"Fixed\": 1,\r\n" + 
				"            \"Loss\": 1,\r\n" + 
				"            \"Consumption\": 1,\r\n" + 
				"            \"RangeAndMinimum\": 1\r\n" + 
				"        },\r\n" + 
				"       \"Rate\": [\r\n" + 
				"            {\r\n" + 
				"                \"Consumption\": \"GS-OFFPKENERGY\",\r\n" + 
				"                \"KVA\": \"\",\r\n" + 
				"                \"KW\": \"\",\r\n" + 
				"                \"NetMeterReceived\": \"\",\r\n" + 
				"                \"PeriodIndex\": 1\r\n" + 
				"            }         \r\n" + 
				"        ],\r\n" + 
				"        \"Confirm\": {\r\n" + 
				"            \"IgnoreDisconnectMeterReadValidation\": false,\r\n" + 
				"            \"IgnoreDisconnectToActiveValidation\": false,\r\n" + 
				"            \"IgnoreEquipmentReinstallValidation\": false\r\n" + 
				"        }\r\n" + 
				"	}\r\n" + 
				"}";
		String exResponse = "{\"Connection\":{\"Success\":true,\"Data\":{\"LocationId\":\"AUTO1001\",\"BaseConnection\":{\"ConnectionSequence\":1,\"NewConnectionSequence\":0},\"AlternateConnection\":[{\"AlternateConnectionSequence\":2,\"NewAlternateConnectionSequence\":0}],\"SubtractConnection\":\"[]\",\"Confirm\":{\"DisconnectToActiveValidation\":false,\"EquipmentReinstallValidation\":false,\"DisconnectMeterReadValidation\":false}},\"Messages\":[{\"Enabled\":1,\"Info\":\"Updated\",\"Level\":1}]}}";
		//CommonMethods.putMethodstring(payload, uri, ver, exResponse);
		CommonMethods.putMethodstring(uri, ver, payload, exResponse);

	}

	@Test(priority = 8, groups = "connectionController", retryAnalyzer = Retry.class)
	public void deleteconnectionmetergrpv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// JsonPath jsonPathEvaluator;

		String uri = "/connection/meterGroup/MTGR00000000001";
		String version = "4.0";
		String exResponse = "{\"Connection\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Deleted\",\"Level\":1}]}}";
		Assert.assertEquals(CommonMethods.deleteMethodasString(uri, version), exResponse);

	}

	@Test(priority = 9, groups = "connectionController", retryAnalyzer = Retry.class, dependsOnMethods = "deleteconnectionmetergrpv4")
	public void postconnectionmetergrpv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// JsonPath jsonPathEvaluator;

		String uri = "/connection/meterGroup";
		String version = "4.0";
		String payload = "{\r\n" + "    \"Connection\": {\r\n" + "        \"LocationId\": \"LOCATION008\",\r\n"
				+ "        \"FixedChargeId\": 1,\r\n" + "        \"MinimumCalculationId\": 3,\r\n"
				+ "        \"DemandHandlingId\": 1,\r\n" + "        \"Equipment\": [\r\n" + "            {\r\n"
				+ "                \"Id\": \"EQUIPMENT008\",\r\n" + "                \"ConnectionSequence\": 2\r\n"
				+ "            },\r\n" + "            {\r\n" + "                \"Id\": \"EQUIPMENT006\",\r\n"
				+ "                \"ConnectionSequence\": 3 \r\n" + "            }\r\n" + "        ]\r\n" + "    }\r\n"
				+ "}";
		String exResponse = "{\"Connection\":{\"Success\":true,\"Data\":{\"MeterGroupId\":\"MTGR00000000003\"},\"Messages\":[{\"Enabled\":1,\"Info\":\"Created\",\"Level\":1}]}}";
		CommonMethods.postMethodString(payload, uri, version, exResponse);

	}

	@Test(priority = 10, groups = "connectionController", retryAnalyzer = Retry.class)
	public void putconnectionmeterAlternatev4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// JsonPath jsonPathEvaluator;

		String uri = "/connection/alternate";
		String ver = "4.0";
		String payload = "{\r\n" + "    \"Connection\": {\r\n" + "        \"LocationId\": \"AUTO1001\",\r\n"
				+ "        \"AlternateSequenceNumber\": 2,\r\n" + "        \"ServiceTypeId\": \"ELECTRIC\",\r\n"
				+ "        \"RouteId\": \"001\",\r\n" + "        \"DiscountRate\": \"res-elec\",\r\n"
				+ "        \"ConnectionDate\": \"1900-01-01\",\r\n" + "        \"Status\": 1,\r\n"
				+ "        \"DisconnectionDate\":\"1900-01-01\",\r\n" + "        \"TaxDiscountPercent\": 1.00000,\r\n"
				+ "        \"Multiplier\": {\r\n" + "            \"Rate\": 1,\r\n" + "            \"Fixed\": 1,\r\n"
				+ "            \"Loss\": 1,\r\n" + "            \"Consumption\": 1,\r\n"
				+ "            \"RangeAndMinimum\": 1\r\n" + "        },\r\n" + "        \"Rate\": [\r\n"
				+ "            {\r\n" + "                \"PeriodIndex\": 1,\r\n"
				+ "                \"Consumption\": \"RATE001-FIXED\",\r\n" + "                \"KW\": \"\",\r\n"
				+ "                \"KVA\": \"\",\r\n" + "                \"NetMeterReceived\": \"\"\r\n"
				+ "            }\r\n" + "        ]\r\n" + "    }\r\n" + "}";
		String exResponse = "{\"Connection\":{\"Success\":true,\"Data\":{\"LocationId\":\"AUTO1001\",\"AlternateConnectionSequence\":2},\"Messages\":[{\"Enabled\":1,\"Info\":\"Updated\",\"Level\":1}]}}";
		CommonMethods.putMethodstring(uri, ver, payload, exResponse);

	}

	@Test(priority = 11, groups = "connectionController", retryAnalyzer = Retry.class)
	public void postconnectionMeterInstallMeter() 		throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// JsonPath jsonPathEvaluator;
		String uri = "/connection/meter";
		String version = "4.0";
		String payload = "{\r\n" + 
				"    \"Connection\": {\r\n" + 
				"        \"ConnectionDate\": \"2023-01-01\",\r\n" + 
				"        \"DisconnectionDate\": \"1900-01-01\",\r\n" + 
				"        \"DiscountRate\": \"\",\r\n" + 
				"        \"EquipmentId\": \"KVARMETER01\",\r\n" + 
				"        \"InstallationDate\": \"2034-01-01\",\r\n" + 
				"        \"IsSubtractConnection\": false,\r\n" + 
				"        \"LocationId\": \"LOCATION003\",\r\n" + 
				"        \"Multiplier\": {\r\n" + 
				"            \"Rate\": 0,\r\n" + 
				"            \"Fixed\": 0,\r\n" + 
				"            \"Loss\": 0,\r\n" + 
				"            \"Consumption\": 0,\r\n" + 
				"            \"RangeAndMinimum\": 0\r\n" + 
				"        },\r\n" + 
				"        \"NetMeterDeliveryMeter\": \"\",\r\n" + 
				"        \"Rate\": [\r\n" + 
				"            {\r\n" + 
				"                \"Consumption\": \"\",\r\n" + 
				"                \"KVA\": \"res-elec\",\r\n" + 
				"                \"KW\": \"\",\r\n" + 
				"                \"NetMeterReceived\": \"\",\r\n" + 
				"                \"PeriodIndex\": 1\r\n" + 
				"            }\r\n" + 
				"        ],\r\n" + 
				"        \r\n" + 
				"        \"RouteId\": \"ROUTEE001\",\r\n" + 
				"        \"SequenceNumber\": 0,\r\n" + 
				"        \"ServiceTypeId\": \"ELECTRIC\",\r\n" + 
				"        \"Status\": \"2\",\r\n" + 
				"        \"SubtractBaseConnectionSequence\": 0,\r\n" + 
				"        \"TaxDiscountPercent\": 10.00000,\r\n" + 
				"        \"Confirm\": {\r\n" + 
				"            \"IgnoreEquipmentReinstallValidation\": false,\r\n" + 
				"            \"EquipmentReinstall\": false\r\n" + 
				"        },\r\n" + 
				"     \"ServiceOrder\": {\r\n" + 
				"         \"Id\": \"SORD00000009016\",\r\n" + 
				"         \"EmployeeId\": \"sa\",\r\n" + 
				"         \"OverrideServiceType\": true,\r\n" + 
				"         \"Task\": {\r\n" + 
				"             \"Sequence\": 1000\r\n" + 
				"         }\r\n" + 
				"     }\r\n" + 
				"    }\r\n" + 
				"}\r\n" + 
				"";
		String exResponse = "{\"Connection\":{\"Success\":true,\"Data\":{\"LocationId\":\"LOCATION003\",\"ConnectionSequence\":2},\"Messages\":[{\"Enabled\":1,\"Info\":\"Created\",\"Level\":1}]}}";
		CommonMethods.postMethodString(payload, uri, version, exResponse);

	}
	
}