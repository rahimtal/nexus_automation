package Private;

import org.testng.annotations.Test; import org.testng.Assert;
import org.testng.Assert;
import org.testng.annotations.Test; import org.testng.Assert;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test; import org.testng.Assert;

import com.NexustAPIAutomation.java.CommonMethods;


import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

public class equipmentControllerV4 {

	@Test(priority = 1, groups = "equipmentController" )
	public void postequipmentControllerv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		JsonPath jsonPathEvaluator;
		String uri = "/Equipment";
		String ver = "4.0";
		String payload = "./\\TestData\\/postEquipmentv4.json";
		jsonPathEvaluator = CommonMethods.postMethod(payload, uri, ver);
		Boolean Result = jsonPathEvaluator.get("Equipment.Success");
		System.out.println(jsonPathEvaluator.prettyPrint());
		if (Result != true) {
			Assert.fail(jsonPathEvaluator.prettyPrint());
		}

	}

	@Test(priority = 2, groups = "equipmentController" )
	public void postequipmentControllerv4Error()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		JsonPath jsonPathEvaluator;
		String uri = "/Equipment";
		String ver = "4.0";
		String payload = "./\\TestData\\/postEquipmentv4.json";
		jsonPathEvaluator = CommonMethods.postMethod(payload, uri, ver);
		Boolean Result = jsonPathEvaluator.get("Equipment.Success");
		System.out.println(jsonPathEvaluator.prettyPrint());
		if (Result == true) {
			Assert.fail(jsonPathEvaluator.prettyPrint());
		}

	}

	@Test(priority = 3, groups = "equipmentController" )
	public void getequipmentControllerClassv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/equipment/class/EQUIPCLASS002";
		String ver = "4.0";
		String expresult = "./\\TestData\\/getEquipmentclassv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, expresult);
		System.out.println(result);

	}

	@Test(priority = 4, groups = "equipmentController" )
	public void getequipmentControllerEquipmentDetailv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/equipment";
		String ver = "4.0";
		String expresult = "./\\TestData\\/getEquipmentdetailsv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("EquipmentClass", "EQUIPCLASS001");
		params.put("EquipmentId", "16358960");
		String result = CommonMethods.getMethod(uri, ver, params, expresult);
		System.out.println(result);

	}

}