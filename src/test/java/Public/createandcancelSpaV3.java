package Public;

import java.io.IOException;
import java.sql.SQLException;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.NexusAPI.tests.BaseClass;
import com.NexustAPIAutomation.java.CommonMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

public class createandcancelSpaV3 extends BaseClass {

	public static JsonPath jsonPathEvaluator;

	@Test(priority = 1, groups = "SPA")
	public static void cancelSPA_v_3() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String customerId = "500300";
		String spaIndexfromdb = CommonMethods.getSPAIndex(customerId);

		Boolean res = CommonMethods.cancelSpa(spaIndexfromdb, customerId);
		if (!res) {
			Assert.fail("Not cancelled");
		}
		Thread.sleep(5000);

		System.out.println(res);
	}

	@Test(priority = 2, groups = "SPA")
	public void createSPA_v_3() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.Bugs("CPDEV-20924");
		String uri = "/spa/create";
		String ver = "3.0";
		String payload = "./\\TestData\\spacreatev2.json";

		jsonPathEvaluator = CommonMethods.postMethod(payload, uri, ver);
		Boolean Result = jsonPathEvaluator.get("SpaCreate[0].Success");

		if (Result == false) {
			Assert.fail("SPA not created");
		}

	}

	@Test(priority = 3, groups = "SPA")
	public void putspaCalculate_v_3() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/spa/calculate";
		String ver = "3.0";
		String jpath = "./\\TestData\\putspacalculatev2.json";

		ValidatableResponse result = CommonMethods.putMethod(uri, ver, jpath);
		result.assertThat().body(Matchers.containsString(
				"{\"SpaCalculated\":[{\"Success\":true,\"Messages\":[{\"Enabled\":0,\"Info\":\"\"}]}]}"));
		System.out.println(result.extract().asString());

	}

	@Test(priority = 4, groups = "SPA") // dependsOnMethods = "putspaCalculate_v_3")
	public void recancelSPA_v_3() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String customerId = "500300";
		String spaIndexfromdb = CommonMethods.getSPAIndex(customerId);
		System.out.println("SPA Index from DB =" + spaIndexfromdb);
		Boolean res = CommonMethods.cancelSpa(spaIndexfromdb, customerId);

		String uri = "/spa/calculate";
		String ver = "3.0";
		String jpath = "./\\TestData\\calculateddocumentsv2.json";
		ValidatableResponse result = CommonMethods.putMethod(uri, ver, jpath);
		result.assertThat().body(Matchers.containsString("SpaCalculated"));
		result.assertThat().body(Matchers.containsString("Success"));
		result.assertThat().body(Matchers.containsString("true"));
		System.out.println(result.extract().asString());

		System.out.println(res);
	}

	public static void main(String args[])
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		cancelSPA_v_3();
	}

	@Test(priority = 5, groups = "SPA")
	public void createSPA_v_4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.Bugs("CPDEV-20924");
		String uri = "/spa/create";
		String ver = "4.0";
		String payload = "./\\TestData\\spacreatev2.json";

		jsonPathEvaluator = CommonMethods.postMethod(payload, uri, ver);
		Boolean Result = jsonPathEvaluator.get("SpaCreate[0].Success");

		if (Result == false) {
			CommonMethods.postMethod(payload, uri, ver);
			if (Result == true) {
				return;
			}
			Assert.fail("SPA not created");
		}

	}

}