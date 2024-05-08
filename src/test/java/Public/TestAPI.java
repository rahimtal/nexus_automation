package Public;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

public class TestAPI {

	public static JsonPath jsonPathEvaluator;

	@Test(priority = 2, groups = "Deposits")
	public void getdepositpaymentPlan_v_3()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/deposit/DEPS00000000026/paymentPlan";
		String ver = "3.0";
		String jpath = "./\\TestData\\depositsPaymentPlanv3.json";
		HashMap<String, String> params = new HashMap<String, String>();
		//params.put("DocumentNumber", "DEPS00000000026");
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);

	}

	public static int retrunIndex(int[] arr1) {
		
			int arrayL = arr1.length;
			for (int i = 0; i < arrayL; i++) {
				int rightindexsum = 0;
				int leftindexsum = 0;
				for (int j = i; j < arrayL; j++) {
					leftindexsum += arr1[j];
					System.out.println("Hello Ibrahim leftindexsum =" + leftindexsum + " value of j ="+j);
				}
				for (int k = i - 1; k >= 0; k--) {
					rightindexsum += arr1[k];
					System.out.println("Hellow Affan rightindexsum = " + rightindexsum + " value of i ="+i);
				}
				if (rightindexsum == leftindexsum) {
					return i;
				}

			}

		
		return -1;

	}
	
	//@Test(priority = 2, groups = "Deposits")
	public static void postsmartlistfavorite_v_3()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		JsonPath jsonPathEvaluator;
		CommonMethods.Bug("CPDEV-14084");
		String uri = "/smartlist/favorite";
		String ver = "3.0";
		String payload = "./\\TestData\\smartlistaddfav_v3.json";
		jsonPathEvaluator = CommonMethods.postMethod(payload, uri, ver);
		Boolean Result = jsonPathEvaluator.get("SmartlistFavorite.Success");
		System.out.println(jsonPathEvaluator.prettyPrint());
		String info = jsonPathEvaluator.get("SmartlistFavorite.Messages[0].Info");
		System.out.println(jsonPathEvaluator.prettyPrint());
		if (Result != true) {
			Assert.fail(jsonPathEvaluator.prettyPrint());
		}
		if(!info.contains("( 2 ) of the ( 2 ) smartlist favorites have been save."))
		{
			Assert.fail();
		}
		

	}

	public static void main(String args[]) throws ClassNotFoundException, SQLException, InterruptedException, IOException {

//		postsmartlistfavorite_v_3();

	}

}
