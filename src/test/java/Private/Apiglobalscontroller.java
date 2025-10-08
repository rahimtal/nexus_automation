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
		/*String params = "{\r\n"
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
				+ "}";*/
		
		String params = "{\r\n"
				+ "\r\n"
				+ "            \"Section\": [\r\n"
				+ "                {\r\n"
				+ "                    \"Id\": 1,\r\n"
				+ "                    \"Name\": \"Defaults\",\r\n"
				+ "                    \"SubSection\": [\r\n"
				+ "                        {\r\n"
				+ "                            \"Id\": 2011,\r\n"
				+ "                            \"Name\": \"Penalty\",\r\n"
				+ "                            \"Setting\": [\r\n"
				+ "                                {\r\n"
				+ "                                    \"Id\": 4087,\r\n"
				+ "                                    \"Name\": \"PenaltyNoOfDays\",\r\n"
				+ "                                    \"Label\": \"Penalty No. of days\",\r\n"
				+ "                                    \"Description\": \"Calculate penalty number of days after the document due date.\",\r\n"
				+ "                                    \"FieldType\": {\r\n"
				+ "                                        \"Id\": 5,\r\n"
				+ "                                        \"Value\": \"Int\"\r\n"
				+ "                                    },\r\n"
				+ "                                    \"FieldTypeModel\": {\r\n"
				+ "                                        \"Id\": 1,\r\n"
				+ "                                        \"Value\": \"Request\"\r\n"
				+ "                                    },\r\n"
				+ "                                    \"EndPoint\": \"\",\r\n"
				+ "                                    \"Required\": false,\r\n"
				+ "                                    \"CustomValue\": false,\r\n"
				+ "                                    \"Display\": {\r\n"
				+ "                                        \"Id\": 1,\r\n"
				+ "                                        \"Value\": \"Enabled\"\r\n"
				+ "                                    },\r\n"
				+ "                                    \"Value\": \"99\"\r\n"
				+ "                                },\r\n"
				+ "                                {\r\n"
				+ "                                    \"Id\": 1,\r\n"
				+ "                                    \"Name\": \"PenaltyProcessing\",\r\n"
				+ "                                    \"Label\": \"Penalty Processing Options\",\r\n"
				+ "                                    \"Description\": \"0 - One Time, 1 - Periodic\",\r\n"
				+ "                                    \"FieldType\": {\r\n"
				+ "                                        \"Id\": 5,\r\n"
				+ "                                        \"Value\": \"Int\"\r\n"
				+ "                                    },\r\n"
				+ "                                    \"FieldTypeModel\": {\r\n"
				+ "                                        \"Id\": 1,\r\n"
				+ "                                        \"Value\": \"Request\"\r\n"
				+ "                                    },\r\n"
				+ "                                    \"EndPoint\": \"\",\r\n"
				+ "                                    \"Required\": false,\r\n"
				+ "                                    \"CustomValue\": false,\r\n"
				+ "                                    \"Display\": {\r\n"
				+ "                                        \"Id\": 1,\r\n"
				+ "                                        \"Value\": \"Enabled\"\r\n"
				+ "                                    },\r\n"
				+ "                                    \"Value\": \"1\"\r\n"
				+ "                                },\r\n"
				+ "                                {\r\n"
				+ "                                    \"Id\": 2,\r\n"
				+ "                                    \"Name\": \"CompoundPenalties\",\r\n"
				+ "                                    \"Label\": \"Compound Penalty\",\r\n"
				+ "                                    \"Description\": \"\",\r\n"
				+ "                                    \"FieldType\": {\r\n"
				+ "                                        \"Id\": 4,\r\n"
				+ "                                        \"Value\": \"Boolean\"\r\n"
				+ "                                    },\r\n"
				+ "                                    \"FieldTypeModel\": {\r\n"
				+ "                                        \"Id\": 1,\r\n"
				+ "                                        \"Value\": \"Request\"\r\n"
				+ "                                    },\r\n"
				+ "                                    \"EndPoint\": \"\",\r\n"
				+ "                                    \"Required\": false,\r\n"
				+ "                                    \"CustomValue\": false,\r\n"
				+ "                                    \"Display\": {\r\n"
				+ "                                        \"Id\": 1,\r\n"
				+ "                                        \"Value\": \"Enabled\"\r\n"
				+ "                                    },\r\n"
				+ "                                    \"Value\": \"true\"\r\n"
				+ "                                },\r\n"
				+ "                                {\r\n"
				+ "                                    \"Id\": 3,\r\n"
				+ "                                    \"Name\": \"PenaltyId\",\r\n"
				+ "                                    \"Label\": \"Penalty Id\",\r\n"
				+ "                                    \"Description\": \"\",\r\n"
				+ "                                    \"FieldType\": {\r\n"
				+ "                                        \"Id\": 1,\r\n"
				+ "                                        \"Value\": \"Lookup\"\r\n"
				+ "                                    },\r\n"
				+ "                                    \"FieldTypeModel\": {\r\n"
				+ "                                        \"Id\": 1,\r\n"
				+ "                                        \"Value\": \"Request\"\r\n"
				+ "                                    },\r\n"
				+ "                                    \"EndPoint\": \"/api/v4/lookupPenalty\",\r\n"
				+ "                                    \"Required\": false,\r\n"
				+ "                                    \"CustomValue\": false,\r\n"
				+ "                                    \"Display\": {\r\n"
				+ "                                        \"Id\": 1,\r\n"
				+ "                                        \"Value\": \"Enabled\"\r\n"
				+ "                                    },\r\n"
				+ "                                    \"Value\": \"5%\"\r\n"
				+ "                                }\r\n"
				+ "                            ]\r\n"
				+ "                        }\r\n"
				+ "                    ]\r\n"
				+ "                }\r\n"
				+ "            ]\r\n"
				+ "        \r\n"
				+ "    \r\n"
				+ "}";
		String expected = "{\"ApiGlobal\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"API Global Settings Updated.\",\"Level\":1}]}}";
		 CommonMethods.putMethodstring(uri, ver, params, expected);

	}
	
	
	
	@Test(priority = 2, groups = "GlobalSettings")
	public void getapiGlobalSettingsv4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/apiGlobalSettings/Defaults/Penalties";
		String ver = "4.0";
		String expected = "{\"ApiGlobalSettings\":[{\"Section\":[{\"Id\":1,\"Name\":\"Defaults\",\"SubSection\":[{\"Id\":2011,\"Name\":\"Penalty\",\"Setting\":[{\"Id\":4087,\"Name\":\"PenaltyNoOfDays\",\"Label\":\"Penalty No. of days\",\"Description\":\"Calculate penalty number of days after the document due date.\",\"FieldType\":{\"Id\":5,\"Value\":\"Int\"},\"FieldTypeModel\":{\"Id\":1,\"Value\":\"Request\"},\"EndPoint\":\"\",\"Required\":false,\"CustomValue\":false,\"Display\":{\"Id\":1,\"Value\":\"Enabled\"},\"Value\":\"99\"},{\"Id\":1,\"Name\":\"PenaltyProcessing\",\"Label\":\"Penalty Processing Options\",\"Description\":\"0 - One Time, 1 - Periodic\",\"FieldType\":{\"Id\":5,\"Value\":\"Int\"},\"FieldTypeModel\":{\"Id\":1,\"Value\":\"Request\"},\"EndPoint\":\"\",\"Required\":false,\"CustomValue\":false,\"Display\":{\"Id\":1,\"Value\":\"Enabled\"},\"Value\":\"1\"},{\"Id\":2,\"Name\":\"CompoundPenalties\",\"Label\":\"Compound Penalty\",\"Description\":\"\",\"FieldType\":{\"Id\":4,\"Value\":\"Boolean\"},\"FieldTypeModel\":{\"Id\":1,\"Value\":\"Request\"},\"EndPoint\":\"\",\"Required\":false,\"CustomValue\":false,\"Display\":{\"Id\":1,\"Value\":\"Enabled\"},\"Value\":\"true\"},{\"Id\":3,\"Name\":\"PenaltyId\",\"Label\":\"Penalty Id\",\"Description\":\"\",\"FieldType\":{\"Id\":1,\"Value\":\"Lookup\"},\"FieldTypeModel\":{\"Id\":1,\"Value\":\"Request\"},\"EndPoint\":\"/api/v4/lookupPenalty\",\"Required\":false,\"CustomValue\":false,\"Display\":{\"Id\":1,\"Value\":\"Enabled\"},\"Value\":\"5%\"}]}]}]}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result, expected);
		

	}
	
	
	@Test(priority = 3, groups = "GlobalSettings")
	public void putapiGlobalSettingsV4Error() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		
		String uri = "/apiGlobalSettings";
		String ver = "4.0";
		String params = "{\r\n"
				+ "    \"Section\": [\r\n"
				+ "        {\r\n"
				+ "            \"Id\": 1,\r\n"
				+ "            \"Name\": \"default\",\r\n"
				+ "            \"SubSection\": [\r\n"
				+ "                {\r\n"
				+ "                            \"Id\": 2011,\r\n"
				+ "                            \"Name\": \"Penalty\",\r\n"
				+ "                            \"Setting\": [\r\n"
				+ "                                {\r\n"
				+ "                                    \"Id\": 4087,\r\n"
				+ "                                    \"Name\": \"PenaltyNoOfDays\",\r\n"
				+ "                                    \"Label\": \"Penalty No. of days\",\r\n"
				+ "                                    \"Description\": \"Calculate penalty number of days after the document due date.\",\r\n"
				+ "                                    \"FieldType\": {\r\n"
				+ "                                        \"Id\": 5,\r\n"
				+ "                                        \"Value\": \"Int\"\r\n"
				+ "                                    },\r\n"
				+ "                                    \"FieldTypeModel\": {\r\n"
				+ "                                        \"Id\": 1,\r\n"
				+ "                                        \"Value\": \"Request\"\r\n"
				+ "                                    },\r\n"
				+ "                                    \"EndPoint\": \"\",\r\n"
				+ "                                    \"Required\": false,\r\n"
				+ "                                    \"CustomValue\": false,\r\n"
				+ "                                    \"Display\": {\r\n"
				+ "                                        \"Id\": 1,\r\n"
				+ "                                        \"Value\": \"Enabled\"\r\n"
				+ "                                    },\r\n"
				+ "                                    \"Value\": \"-368\"\r\n"
				+ "                                }\r\n"
				+ "                            ]\r\n"
				+ "                        }\r\n"
				+ "            ]\r\n"
				+ "        }\r\n"
				+ "    ]\r\n"
				+ "}";
		//String params = new String(Files.readAllBytes(Paths.get(jpath)));
		String expected = "{\"ApiGlobal\":{\"Success\":false,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Penalty number of days should be numeric value between 0 and 365.\",\"Level\":3}]}}";
		 CommonMethods.putMethodstring(uri, ver, params, expected);

	}
	
	
	@Test(priority = 4, groups = "GlobalSettings")
	public void putapiGlobalSettingsV4Err() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		
		String uri = "/apiGlobalSettings";
		String ver = "4.0";
		/*String params = "{\r\n"
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
				+ "}";*/
		
		String params = "{\r\n"
				+ "\r\n"
				+ "            \"Section\": [\r\n"
				+ "                {\r\n"
				+ "                    \"Id\": 1,\r\n"
				+ "                    \"Name\": \"Defaults\",\r\n"
				+ "                    \"SubSection\": [\r\n"
				+ "                        {\r\n"
				+ "                            \"Id\": 2011,\r\n"
				+ "                            \"Name\": \"Penalty\",\r\n"
				+ "                            \"Setting\": [\r\n"
				+ "                                {\r\n"
				+ "                                    \"Id\": 4087,\r\n"
				+ "                                    \"Name\": \"PenaltyNoOfDays\",\r\n"
				+ "                                    \"Label\": \"Penalty No. of days\",\r\n"
				+ "                                    \"Description\": \"Calculate penalty number of days after the document due date.\",\r\n"
				+ "                                    \"FieldType\": {\r\n"
				+ "                                        \"Id\": 5,\r\n"
				+ "                                        \"Value\": \"Int\"\r\n"
				+ "                                    },\r\n"
				+ "                                    \"FieldTypeModel\": {\r\n"
				+ "                                        \"Id\": 1,\r\n"
				+ "                                        \"Value\": \"Request\"\r\n"
				+ "                                    },\r\n"
				+ "                                    \"EndPoint\": \"\",\r\n"
				+ "                                    \"Required\": false,\r\n"
				+ "                                    \"CustomValue\": false,\r\n"
				+ "                                    \"Display\": {\r\n"
				+ "                                        \"Id\": 1,\r\n"
				+ "                                        \"Value\": \"Enabled\"\r\n"
				+ "                                    },\r\n"
				+ "                                    \"Value\": \"499\"\r\n"
				+ "                                },\r\n"
				+ "                                {\r\n"
				+ "                                    \"Id\": 1,\r\n"
				+ "                                    \"Name\": \"PenaltyProcessing\",\r\n"
				+ "                                    \"Label\": \"Penalty Processing Options\",\r\n"
				+ "                                    \"Description\": \"0 - One Time, 1 - Periodic\",\r\n"
				+ "                                    \"FieldType\": {\r\n"
				+ "                                        \"Id\": 5,\r\n"
				+ "                                        \"Value\": \"Int\"\r\n"
				+ "                                    },\r\n"
				+ "                                    \"FieldTypeModel\": {\r\n"
				+ "                                        \"Id\": 1,\r\n"
				+ "                                        \"Value\": \"Request\"\r\n"
				+ "                                    },\r\n"
				+ "                                    \"EndPoint\": \"\",\r\n"
				+ "                                    \"Required\": false,\r\n"
				+ "                                    \"CustomValue\": false,\r\n"
				+ "                                    \"Display\": {\r\n"
				+ "                                        \"Id\": 1,\r\n"
				+ "                                        \"Value\": \"Enabled\"\r\n"
				+ "                                    },\r\n"
				+ "                                    \"Value\": \"1\"\r\n"
				+ "                                },\r\n"
				+ "                                {\r\n"
				+ "                                    \"Id\": 2,\r\n"
				+ "                                    \"Name\": \"CompoundPenalties\",\r\n"
				+ "                                    \"Label\": \"Compound Penalty\",\r\n"
				+ "                                    \"Description\": \"\",\r\n"
				+ "                                    \"FieldType\": {\r\n"
				+ "                                        \"Id\": 4,\r\n"
				+ "                                        \"Value\": \"Boolean\"\r\n"
				+ "                                    },\r\n"
				+ "                                    \"FieldTypeModel\": {\r\n"
				+ "                                        \"Id\": 1,\r\n"
				+ "                                        \"Value\": \"Request\"\r\n"
				+ "                                    },\r\n"
				+ "                                    \"EndPoint\": \"\",\r\n"
				+ "                                    \"Required\": false,\r\n"
				+ "                                    \"CustomValue\": false,\r\n"
				+ "                                    \"Display\": {\r\n"
				+ "                                        \"Id\": 1,\r\n"
				+ "                                        \"Value\": \"Enabled\"\r\n"
				+ "                                    },\r\n"
				+ "                                    \"Value\": \"true\"\r\n"
				+ "                                },\r\n"
				+ "                                {\r\n"
				+ "                                    \"Id\": 3,\r\n"
				+ "                                    \"Name\": \"PenaltyId\",\r\n"
				+ "                                    \"Label\": \"Penalty Id\",\r\n"
				+ "                                    \"Description\": \"\",\r\n"
				+ "                                    \"FieldType\": {\r\n"
				+ "                                        \"Id\": 1,\r\n"
				+ "                                        \"Value\": \"Lookup\"\r\n"
				+ "                                    },\r\n"
				+ "                                    \"FieldTypeModel\": {\r\n"
				+ "                                        \"Id\": 1,\r\n"
				+ "                                        \"Value\": \"Request\"\r\n"
				+ "                                    },\r\n"
				+ "                                    \"EndPoint\": \"/api/v4/lookupPenalty\",\r\n"
				+ "                                    \"Required\": false,\r\n"
				+ "                                    \"CustomValue\": false,\r\n"
				+ "                                    \"Display\": {\r\n"
				+ "                                        \"Id\": 1,\r\n"
				+ "                                        \"Value\": \"Enabled\"\r\n"
				+ "                                    },\r\n"
				+ "                                    \"Value\": \"5%\"\r\n"
				+ "                                }\r\n"
				+ "                            ]\r\n"
				+ "                        }\r\n"
				+ "                    ]\r\n"
				+ "                }\r\n"
				+ "            ]\r\n"
				+ "        \r\n"
				+ "    \r\n"
				+ "}";
		String expected = "{\"ApiGlobal\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"API Global Settings Updated.\",\"Level\":1}]}}";
		 CommonMethods.putMethodstring(uri, ver, params, expected);

	}
	




   }
