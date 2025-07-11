package Public;

import org.testng.annotations.Test; import org.testng.Assert;
import org.testng.annotations.Test; import org.testng.Assert;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.annotations.Test; import org.testng.Assert;

import com.NexustAPIAutomation.java.CommonMethods;

import Private.BaseClass;
import io.restassured.response.ValidatableResponse;

public class consumptionHistoryControllerV3  extends BaseClass {

	public static ValidatableResponse jsonPathEvaluator;

	@Test(priority = 1, groups = "ConsumptionHistoryController" )
	public void getconsumptionHistoryController() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/consumptionHistory/getConsumptionHistory";
		String ver = "3.0";
		String jpath = "./\\TestData\\consumptionHist.json";
				
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "LOCATION008");
		params.put("CustomerId", "CUSTOMER009");
		params.put("ConnectionSequence", "0");
		params.put("UserDate", "2000-04-01");
		params.put("NumberOfYears", "20");
		
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
		
	}
	
	
		
	

}