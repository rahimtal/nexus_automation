package Public;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;
import com.NexustAPIAutomation.java.Retry;

import io.restassured.path.json.JsonPath;

public class PaymentControllerV4 {

	public static JsonPath jsonPathEvaluator;

	@Test(priority = 1, groups = "Payment" )
	public void postPaymentv4() throws ClassNotFoundException, SQLException, InterruptedException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/payment";
		String ver = "4.0";
		String payload = "./\\TestData\\PostPaymentv4.json";
		//String exresult = "{\"Payment\":{\"Success\":true,\"Data\":{\"BatchId\":\"API20240505001\",\"DocumentNumber\":\"PYMT00000000529\"},\"Messages\":[]}}";
		String exresult = "{\"Payment\":{\"Success\":true,\"Data\":{\"BatchId\":\"";
		CommonMethods.postcallcontains(uri, payload, ver, exresult);
		}

	@Test(priority = 2, groups = "Payment" )
	public void postPaymentBatchv4() throws ClassNotFoundException, SQLException, InterruptedException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/payment/paymentBatch";
		String ver = "4.0";
		String payload = "./\\TestData\\paymentBatchv4.json";
		jsonPathEvaluator = CommonMethods.postMethod(payload, uri, ver);
		Boolean Result = jsonPathEvaluator.get("Payment.Success");
		if (Result == false) {
			System.out.println(jsonPathEvaluator.prettyPrint());
			Assert.fail(jsonPathEvaluator.prettyPrint());
		}
		// System.out.println(jsonPathEvaluator.toString());
	}

	//@Test(priority = 3, groups = "Payment" )
	public void gettPaymentNextv4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/payment/next";
		String ver = "4.0";
		String expected = "./\\TestData\\gettPaymentNextv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		// params.put("ConnectionSequence", "1");
		String result = CommonMethods.getMethodContains(uri, ver, params, expected);
		System.out.println(result);

	}

	@Test(priority = 4, groups = "Payment" )
	public void postPaymentBatchtruev4() throws ClassNotFoundException, SQLException, InterruptedException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/payment/paymentBatch";
		String ver = "4.0";
		String payload = "./\\TestData\\paymentBatchtruev4.json";
		jsonPathEvaluator = CommonMethods.postMethod(payload, uri, ver);
		Boolean Result = jsonPathEvaluator.get("Payment.Success");
		if (Result == false) {
			System.out.println(jsonPathEvaluator.prettyPrint());
			AssertJUnit.fail(jsonPathEvaluator.prettyPrint());
		}
		// System.out.println(jsonPathEvaluator.toString());
	}
	
	
	@Test(priority = 5, groups = "Payment" )
	public void postPaymentMiscv4() throws ClassNotFoundException, SQLException, InterruptedException {
		// CommonMethods.CompanyDBRestore();
	//	CommonMethods.Bug("CPDEV-18766");
		String uri = "/payment";
		String ver = "4.0";
		String payload = "./\\TestData\\PostPaymentMiscv4.json";
		//String exresult = "{\"Payment\":{\"Success\":true,\"Data\":{\"BatchId\":\"API20240505001\",\"DocumentNumber\":\"PYMT00000000529\"},\"Messages\":[]}}";
		String exresult = "{\"Payment\":{\"Success\":true,\"Data\":{\"BatchId\":\"___api_CR\",\"DocumentNumber\":\"PYMT00000000";
		CommonMethods.postcallcontains(uri, payload, ver, exresult);
		}

}
