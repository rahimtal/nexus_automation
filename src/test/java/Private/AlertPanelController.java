package Private;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;

public class AlertPanelController {

	@Test(priority = 1, groups = "AlertPanelController")
	public void getalert_v4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/alert/CUSTOMER002/SEWER003";
		String ver = "4.0";
		String expected = "{\"Alert\":[{\"Messgage\":[],\"Success\":true,\"Messages\":[{\"Enabled\":0,\"Info\":\"\",\"Level\":0}]}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		// params.put("CustomerId", "CUSTOMER002");
		// params.put("LocationId", "SEWER003");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(expected, result);
	}
	

	@Test(priority = 2, groups = "AlertPanelController")
	public void getalertaccountAttributes_v4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/alert/accountAttributes/CUSTOMER002/SEWER003";
		String ver = "4.0";
		String expected = "{\"Alert\":[{\"Messgage\":[],\"Success\":true,\"Messages\":[{\"Enabled\":0,\"Info\":\"\",\"Level\":0}]}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		// params.put("CustomerId", "CUSTOMER002");
		// params.put("LocationId", "SEWER003");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(expected, result);
	}


}
