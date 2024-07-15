package Private;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class DepositsController {

	@Test(priority = 1, groups = "Deposits")
	public void getdeposit() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/deposit";
		String ver = "4.0";
		String jpath = "./\\TestData\\depositsv3.json";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "SPALOCATION1");
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);

	}

	@Test(priority = 4, groups = "Deposits" , dependsOnMethods = "postdepositpaymentPlanv4")
	public void getdepositpaymentPlan()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/deposit/DEPS00000000032/paymentPlan";
		String ver = "4.0";
		String jpath = "./\\TestData\\depositsPaymentPlanv3.json";
		HashMap<String, String> params = new HashMap<String, String>();
		//params.put("DocumentNumber", "DEPS00000000026");
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);

	}

	@Test(priority = 3, groups = "Deposits")
	public void lookupDepositId() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/lookupDepositId";
		String ver = "4.0";
		String jpath = "./\\TestData\\lookupDepositIdV2.json";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("OrderBy", "description");
		params.put("PageNum", "1");
		params.put("NumPerPage", "32000");
		params.put("ServiceCategory", "electric");
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);

	}
	
	
	@Test(priority = 1, groups = "Deposits")
	public void postdepositpaymentPlanv4() throws ClassNotFoundException, SQLException, InterruptedException {

		
		String uri = "/deposit/paymentPlan";
		String ver = "4.0";
		String payload = "./\\TestData\\postdepositpaymentPlanv4.json";
		String exptected = "{\"Deposit\":{\"Success\":true,\"Data\":{\"DocumentNumber\":\"DEPS00000000032\",\"IsThirdPartyCustomer\":false},\"Messages\":[]}}";
		Response result = CommonMethods.postMethodResponseasString(payload, uri, ver);
		String actualResult = result.print();
		AssertJUnit.assertEquals(exptected, actualResult);

	}
	
	

}