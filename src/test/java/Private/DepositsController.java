package Private;

import org.testng.Assert;
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
		String expected = "{\"Deposit\":{\"Success\":true,\"Data\":{\"LocationId\":\"WATER001\",\"CustomerId\":\"CUSTOMER001\",\"DocumentNumber\":\"DEPS00000000032\",\"TotalAmount\":100,\"InitialInstallmentAmount\":25,\"StartDate\":\"2022-01-01\",\"NumberOfInstallments\":4,\"NumberOfDays\":30,\"Installment\":[{\"BillDate\":\"2022-01-01\",\"DueDate\":\"2022-01-01\",\"Amount\":25,\"OutstandingAmount\":25,\"AmountReceived\":0,\"ChargeDocument\":\"\",\"Posted\":false},{\"BillDate\":\"2022-01-01\",\"DueDate\":\"2022-01-02\",\"Amount\":25,\"OutstandingAmount\":25,\"AmountReceived\":0,\"ChargeDocument\":\"\",\"Posted\":false},{\"BillDate\":\"2022-02-01\",\"DueDate\":\"2022-02-01\",\"Amount\":25,\"OutstandingAmount\":25,\"AmountReceived\":0,\"ChargeDocument\":\"\",\"Posted\":false},{\"BillDate\":\"2022-03-01\",\"DueDate\":\"2022-03-01\",\"Amount\":25,\"OutstandingAmount\":25,\"AmountReceived\":0,\"ChargeDocument\":\"\",\"Posted\":false}]},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "SPALOCATION1");
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(actual, expected);

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