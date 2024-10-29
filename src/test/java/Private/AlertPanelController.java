package Private;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;
import com.NexustAPIAutomation.java.Retry;

public class AlertPanelController {

	@Test(priority = 1, groups = "AlertPanelController", retryAnalyzer = Retry.class)
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
	

	@Test(priority = 2, groups = "AlertPanelController", retryAnalyzer = Retry.class)
	public void getalertaccountAttributes_v4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/alert/accountAttributes/500001/100001";
		String ver = "4.0";
		String expected = "{\"result\":[{\"AttributeType\":\"deposits\",\"DrillbackLink\":\"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=Deposits&CustomerID=500001&LocationID=100001&Deposits=1&CogsDrillback=1\"}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		// params.put("CustomerId", "CUSTOMER002");
		// params.put("LocationId", "SEWER003");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(expected, result);
	}


}
