package Private;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;
import com.NexustAPIAutomation.java.Retry;

public class locationControllerv4 {

	@Test(priority = 1, groups = "locationController", retryAnalyzer = Retry.class)
	public static void dellocationv4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/location/locdeltest";
		String ver = "4.0";
		String expected = "{\"Location\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"locdeltest Deleted Successfully.\",\"Level\":1}]}}";
		CommonMethods.deleteMethodvoid(uri, ver, expected);

	}

	@Test(priority = 2, groups = "locationController", retryAnalyzer = Retry.class)
	public static void getlocationClassv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/location/class/NONCUST-LOC";
		String ver = "4.0";
		String expected = "{\"LocationClass\":{\"Success\":true,\"Data\":{\"ClassId\":\"NONCUST-LOC\",\"Description\":\"Non customer Location\",\"CompanyId\":\"\",\"Zone\":{\"Id\":\"\",\"Description\":\"\"},\"LocationType\":{\"Id\":3,\"Description\":\"Noncustomer Location\"},\"CustomerType\":{\"Id\":3,\"Description\":\"Third Party\"},\"CollectionType\":{\"CurrentCustomer\":\"\",\"FormerCustomer\":\"\",\"BudgetCustomer\":\"\",\"SPACustomer\":\"\"},\"CheckbookId\":\"\",\"ConnectionOnSameRoute\":false,\"AllowSPATransfer\":true,\"AllowTransferWithoutFinalBill\":false,\"AllowMultiServiceDiscount\":false,\"MultiServiceDiscountId\":\"\"},\"Messages\":[]}}";
		// CommonMethods.deleteMethodvoid(uri, ver, expected);
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationClassId", "NONCUST-LOC");
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(actual, expected);

	}

	@Test(priority = 3, groups = "locationController", retryAnalyzer = Retry.class)
	public void postbillingOptionv4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// JsonPath jsonPathEvaluator;

		String uri = "/location/billingOption/validate";
		String ver = "4.0";
		String payload = "{\r\n" + "    \"LocationId\":\"WATER005\",\r\n" + "    \"Billing\":{\r\n"
				+ "        \"AccumulatedBilling\":{\r\n" + "            \"Type\":1,\r\n"
				+ "            \"MasterId\":\"\"\r\n" + "        }\r\n" + "    },\r\n" + "    \"ServiceOptions\":{\r\n"
				+ "        \"Service\":[\r\n" + "            {\r\n" + "                \"CategoryId\":1,\r\n"
				+ "                \"AccountReceivableIndex\":1\r\n" + "            },\r\n" + "            {\r\n"
				+ "                \"CategoryId\":2,\r\n" + "                \"AccountReceivableIndex\":1\r\n"
				+ "            },\r\n" + "            {\r\n" + "                \"CategoryId\":3,\r\n"
				+ "                \"AccountReceivableIndex\":1\r\n" + "            },\r\n" + "                {\r\n"
				+ "                \"CategoryId\":4,\r\n" + "                \"AccountReceivableIndex\":1\r\n"
				+ "            },\r\n" + "            {\r\n" + "                \"CategoryId\":5,\r\n"
				+ "                \"AccountReceivableIndex\":1\r\n" + "            },\r\n" + "            {\r\n"
				+ "                \"CategoryId\":6,\r\n" + "                \"AccountReceivableIndex\":1\r\n"
				+ "            }\r\n" + "        ]\r\n" + "    }\r\n" + "}";
		String exResponse = "{\"Location\":{\"Success\":true,\"Data\":{\"InvalidAccumType\":false,\"InvalidMasterId\":false,\"InvalidServiceAccountIndex\":false,\"MessageIncludeDocument\":true},\"Messages\":[{\"Enabled\":1,\"Info\":\"There are documents for the location\\/customer that are not included in the customer statement. Do you want the documents to be included in the Master Location statement?\",\"Level\":1}]}}";
		CommonMethods.postMethodString(payload, uri, ver, exResponse);

	}

}
