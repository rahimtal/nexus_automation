package Public;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

public class depositControllerv4 {

	public static JsonPath jsonPathEvaluator;

	//@Test(priority = 1)
	public void createdepositpaymentPlanV4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/deposit/paymentPlan";
		String ver = "4.0";

		String payload = "{\r\n" + "    \"LocationId\": \"SPALOCATION1\",\r\n"
				+ "    \"CustomerId\": \"03332301204\",\r\n" + "    \"DepositId\": \"ELECTRIC\",\r\n"
				+ "    \"TotalAmount\": 100,\r\n" + "    \"InitialInstallmentAmount\": 25,\r\n"
				+ "    \"StartDate\": \"2022-01-01\",\r\n" + "    \"NumberOfInstallments\": 4,\r\n"
				+ "    \"NumberOfDays\": 30,\r\n" + "    \"Installment\": [\r\n" + "        {\r\n"
				+ "            \"BillingDate\": \"2022-01-01\",\r\n" + "            \"DueDate\": \"2022-01-02\",\r\n"
				+ "            \"Amount\": 25\r\n" + "        },\r\n" + "        {\r\n"
				+ "            \"BillingDate\": \"2022-01-01\",\r\n" + "            \"DueDate\": \"2022-01-01\",\r\n"
				+ "            \"Amount\": 25\r\n" + "        },\r\n" + "        {\r\n"
				+ "            \"BillingDate\": \"2022-02-01\",\r\n" + "            \"DueDate\": \"2022-02-01\",\r\n"
				+ "            \"Amount\": 25\r\n" + "        },\r\n" + "        {\r\n"
				+ "            \"BillingDate\": \"2022-03-01\",\r\n" + "            \"DueDate\": \"2022-03-01\",\r\n"
				+ "            \"Amount\": 25\r\n" + "        }\r\n" + "    ],\r\n" + "    \"ServiceOrder\": {\r\n"
				+ "        \"Id\": \"DEPS00000000031\",\r\n" + "        \"Task\": {\r\n"
				+ "            \"Sequence\": \"1000\"\r\n" + "        }\r\n" + "    }   \r\n" + "}\r\n" + "";
		String filepath = "./\\TestData\\depositpaymentPlanV4.json";
		FileWriter file = new FileWriter(filepath);
		file.write(payload);
		file.close();
		jsonPathEvaluator = CommonMethods.postMethod(filepath, uri, ver);
		Boolean Result = jsonPathEvaluator.get("Deposit.Success");
		System.out.println(Result);
		if (!Result) {

			Assert.fail("Deposit Failed");

		}
	}

}