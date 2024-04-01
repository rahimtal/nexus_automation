package Private;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

public class connectionControllerV4 {

	@Test(priority = 1, groups = "connectionController")
	public void postconnectionmeterv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		//JsonPath jsonPathEvaluator;
		CommonMethods.Bug("CPDEV-17054");
		String uri = "/connection/meter";
		String ver = "4.0";
		String payload = "./\\TestData\\/postconnectionv4.json";
		String exResponse = "{\"Connection\":{\"Success\":true,\"Data\":{\"LocationId\":\"LOCATION001\",\"ConnectionSequence\":3},\"Messages\":[{\"Enabled\":1,\"Info\":\"Created\",\"Level\":1}]}}";
		CommonMethods.postcall(uri, payload, ver, exResponse);
	
		
	}


	@Test(priority = 2, groups = "connectionController")
	public void postconnectionmeterv4Error()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		//JsonPath jsonPathEvaluator;
		String uri = "/connection/meter";
		String ver = "4.0";
		String payload = "./\\TestData\\/postconnectionv4.json";
		String exResponse = "{\"Connection\":{\"Success\":false,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Connection.Confirm is required\",\"Level\":3}]}}";
		CommonMethods.postcall(uri, payload, ver, exResponse);
		
	}


}