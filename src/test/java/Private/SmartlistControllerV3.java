package Private;

import org.testng.annotations.Test; import org.testng.Assert;
import org.testng.Assert;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;


import org.testng.annotations.Test; import org.testng.Assert;

import com.NexustAPIAutomation.java.CommonMethods;


import io.restassured.path.json.JsonPath;

public class SmartlistControllerV3 {
private boolean  TestAll=true;

	@Test(priority = 2, groups = "SmartList" )
	public void getsmartList_v_3() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		//Still a Bug (2025)
		CommonMethods.Bug("CPDEV-14084");
		String uri = "/smartlist";
		String ver = "3.0";
		String jpath = "./\\TestData\\getsmartlistv3.json";
		HashMap<String, String> params = new HashMap<String, String>();
		//String result = CommonMethods.getMethod(uri, ver, params, jpath);
		String result = CommonMethods.getMethodContains(uri, ver, params, jpath);
		System.out.println(result);

	}

	@Test(priority = 1, groups = "SmartList" )
	public void postsmartlistfavorite_v_3()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		//Still a Bug (2025)
		CommonMethods.Bug("CPDEV-14084");
		JsonPath jsonPathEvaluator;
		
		String uri = "/smartlist/favorite";
		String ver = "3.0";
		String payload = "./\\TestData\\smartlistaddfav_v3.json";
		jsonPathEvaluator = CommonMethods.postMethod(payload, uri, ver);
		Boolean Result = jsonPathEvaluator.get("SmartlistFavorite.Success");
		System.out.println(jsonPathEvaluator.prettyPrint());
		String info = jsonPathEvaluator.get("SmartlistFavorite.Messages[0].Info");
		System.out.println(jsonPathEvaluator.prettyPrint());
		if (Result != true) {
			System.out.print("CPDEV-14084");
			Assert.fail(jsonPathEvaluator.prettyPrint());
		}
		if(!info.contains("( 2 ) of the ( 2 ) smartlist favorites have been save."))
		{
			Assert.fail();
		}
		
	}
	

	


}