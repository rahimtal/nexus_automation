package Private;

import org.testng.annotations.Test;
import org.testng.Assert;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import org.testng.Assert;

import com.NexustAPIAutomation.java.CommonMethods;

import io.restassured.response.ValidatableResponse;

public class penaltyController {

	@Test(priority = 1, groups = "Penalty")
	public void putpenaltySetup_v_4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/penalty/setup";
		String ver = "4.0";
		String body = "{\r\n" + "  \"PenaltyId\": \"5%\",\r\n" + "  \"PenaltyProcessing\": 1,\r\n"
				+ "  \"CompoundPenalties\": true\r\n" + "}";
		String expected = "{\"PenaltySetup\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Penalty Setup Updated.\",\"Level\":1}]}}";
		String result = CommonMethods.putMethodstring(uri, ver, body, expected);

	}

}