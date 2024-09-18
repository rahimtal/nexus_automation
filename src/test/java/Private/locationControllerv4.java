package Private;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;

public class locationControllerv4 {

	@Test(priority = 1, groups = "locationController")
	public static void dellocationv4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/location/locdeltest";
		String ver = "4.0";
		String expected = "{\"Location\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"locdeltest Deleted Successfully.\",\"Level\":1}]}}";
		CommonMethods.deleteMethodvoid(uri, ver, expected);

	}

	@Test(priority = 2, groups = "locationController")
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

}
