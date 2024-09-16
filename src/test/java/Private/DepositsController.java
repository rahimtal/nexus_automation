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
		String expected = "{\"Deposit\":{\"Success\":true,\"Data\":[{\"Customer\":{\"Id\":\"03332301204\",\"Name\":\"Mr. Talha Rahim\"},\"Location\":{\"Id\":\"SPALOCATION1\",\"Address\":\"BSMT 8000 8000  Cartersville, GA 09007-8623\"},\"DepositRequest\":[{\"DocumentNumber\":\"DEPS00000000024\",\"MasterDepositNumber\":\"DEPS00000000024\",\"DepositId\":\"ELECTRIC\",\"ServiceCategory\":\"Electric\",\"CustomerId\":\"03332301204\",\"CustomerName\":\"Mr. Talha Rahim\",\"RequestedDate\":\"2027-04-12\",\"AmountRequested\":210,\"CurrentBalance\":210,\"AmountReceived\":210},{\"DocumentNumber\":\"DEPS00000000026\",\"MasterDepositNumber\":\"DEPS00000000026\",\"DepositId\":\"ELECTRIC\",\"ServiceCategory\":\"Electric\",\"CustomerId\":\"03332301204\",\"CustomerName\":\"Mr. Talha Rahim\",\"RequestedDate\":\"2022-09-12\",\"AmountRequested\":210,\"CurrentBalance\":0,\"AmountReceived\":0},{\"DocumentNumber\":\"DEPS00000000028\",\"MasterDepositNumber\":\"DEPS00000000028\",\"DepositId\":\"ELECTRIC\",\"ServiceCategory\":\"Electric\",\"CustomerId\":\"03332301204\",\"CustomerName\":\"Mr. Talha Rahim\",\"RequestedDate\":\"2022-11-01\",\"AmountRequested\":110,\"CurrentBalance\":0,\"AmountReceived\":0},{\"DocumentNumber\":\"DEPS00000000029\",\"MasterDepositNumber\":\"DEPS00000000029\",\"DepositId\":\"ELECTRIC\",\"ServiceCategory\":\"Electric\",\"CustomerId\":\"03332301204\",\"CustomerName\":\"Mr. Talha Rahim\",\"RequestedDate\":\"2022-11-01\",\"AmountRequested\":110,\"CurrentBalance\":0,\"AmountReceived\":0},{\"DocumentNumber\":\"DEPS00000000030\",\"MasterDepositNumber\":\"DEPS00000000030\",\"DepositId\":\"ELECTRIC\",\"ServiceCategory\":\"Electric\",\"CustomerId\":\"03332301204\",\"CustomerName\":\"Mr. Talha Rahim\",\"RequestedDate\":\"2022-11-01\",\"AmountRequested\":110,\"CurrentBalance\":0,\"AmountReceived\":0},{\"DocumentNumber\":\"DEPS00000000031\",\"MasterDepositNumber\":\"DEPS00000000031\",\"DepositId\":\"ELECTRIC\",\"ServiceCategory\":\"Electric\",\"CustomerId\":\"03332301204\",\"CustomerName\":\"Mr. Talha Rahim\",\"RequestedDate\":\"2027-04-12\",\"AmountRequested\":200,\"CurrentBalance\":0,\"AmountReceived\":0},{\"DocumentNumber\":\"DEPS00000000023\",\"MasterDepositNumber\":\"DEPS00000000023\",\"DepositId\":\"WATER\",\"ServiceCategory\":\"Water\",\"CustomerId\":\"03332301204\",\"CustomerName\":\"Mr. Talha Rahim\",\"RequestedDate\":\"2027-04-12\",\"AmountRequested\":110,\"CurrentBalance\":0,\"AmountReceived\":0},{\"DocumentNumber\":\"DEPS00000000025\",\"MasterDepositNumber\":\"DEPS00000000025\",\"DepositId\":\"WATER\",\"ServiceCategory\":\"Water\",\"CustomerId\":\"03332301204\",\"CustomerName\":\"Mr. Talha Rahim\",\"RequestedDate\":\"2027-04-12\",\"AmountRequested\":120,\"CurrentBalance\":0,\"AmountReceived\":0},{\"DocumentNumber\":\"DEPS00000000027\",\"MasterDepositNumber\":\"DEPS00000000027\",\"DepositId\":\"WATER\",\"ServiceCategory\":\"Water\",\"CustomerId\":\"03332301204\",\"CustomerName\":\"Mr. Talha Rahim\",\"RequestedDate\":\"2027-04-12\",\"AmountRequested\":100,\"CurrentBalance\":0,\"AmountReceived\":0}]}],\"Messages\":[]}}";
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
		String exptected = "{\"Deposit\":{\"Success\":true,\"Data\":{\"DocumentNumber\":\"DEPS00000000034\",\"IsThirdPartyCustomer\":false},\"Messages\":[]}}";
		Response result = CommonMethods.postMethodResponseasString(payload, uri, ver);
		String actualResult = result.print();
		AssertJUnit.assertEquals(exptected, actualResult);

	}
	
	

}