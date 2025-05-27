package Public;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;


import io.restassured.path.json.JsonPath;

public class PaymentExtensionV3 {

	public static JsonPath jsonPathEvaluator;
	
	@Test(priority = 1, groups = "Cashering" )
	public void postPaymentv3() throws ClassNotFoundException, SQLException, InterruptedException {
		//CommonMethods.CompanyDBRestore();
		String uri = "/payment";
		String ver = "3.0";
		String payload = "./\\TestData\\PostPaymentv3.json";
		jsonPathEvaluator = CommonMethods.postMethod(payload, uri, ver);
		Boolean Result = jsonPathEvaluator.get("result[0].Success");
		if (Result == false) {
			System.out.println(jsonPathEvaluator.toString());
			AssertJUnit.fail(jsonPathEvaluator.prettyPrint());
		}
		System.out.println(jsonPathEvaluator.toString());
	}

	@Test(priority = 2, groups = "Cashering" )
	public void paymentExtensionv3() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
	//	CommonMethods.CompanyDBRestore();
		String uri = "/paymentextension";
		String ver = "3.0";
		String payload = "./\\TestData\\PayementExtension.json";
		jsonPathEvaluator = CommonMethods.postMethod(payload, uri, ver);
		System.out.println(jsonPathEvaluator.get().toString());
		String Result = jsonPathEvaluator.get("PaymentExtension.Messages[0].Info");
		
		if (Result == "Payment extensions are not allowed. A payment extension already exist or invalid date condition.") {
			AssertJUnit.fail(jsonPathEvaluator.prettyPrint());
		}
		else {
			System.out.println(jsonPathEvaluator.toString());
		}

	}

	
	
	
}
