package Public;

import org.testng.annotations.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class connectionControllerv4 {

	public static ValidatableResponse jsonPathEvaluator;

	@Test(priority = 1, groups = "ConnectionController")
	public void putconnectionmeterGroupV4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/connection/meterGroup";
		String ver = "4.0";
		String jpath = "./\\TestData\\putmeterGroup_v4.json";
		String params = new String(Files.readAllBytes(Paths.get(jpath)));
		String expected = "./\\TestData\\putmeterGroupexpected_v4.json";
		Response result = CommonMethods.putMethod(uri, ver, params, expected);
		

	}

	@Test(priority = 2, groups = "ConnectionController")
	public void getconnection_v4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/connection/ELECWAT003";
		String ver = "4.0";
		String jpath = "./\\TestData\\getConnection_v4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("ConnectionSequence", "1");
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);

	}

	
	

}