package Public;

import org.testng.annotations.Test;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;


import io.restassured.response.ValidatableResponse;

public class spaController {

	@Test(priority = 1, groups = "SPA" )
	public void getoutstandingDocuments_v_2()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
	//	CommonMethods.CompanyDBRestore();

		String uri = "/spa/CUSTOMER001/outstandingDocuments";
		String ver = "2";
		String jpath = "./\\TestData\\spagetoutstandingdocumnetsv2.json";

		HashMap<String, String> params = new HashMap<String, String>();
		/*
		 * params.put("CustomerId", "CUSTOMER014"); params.put("LocationId",
		 * "WATER002"); params.put("TransferDate", "1900-01-01");
		 */

		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);

	}

	@Test(priority = 2, groups = "SPA" )
	public void getheaderInfo_v_2() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/spa/CUSTOMER002";
		String ver = "2";
		String jpath = "./\\TestData\\spaheaderinfov2.json";

		HashMap<String, String> params = new HashMap<String, String>();
		/*
		 * params.put("CustomerId", "CUSTOMER014"); params.put("LocationId",
		 * "WATER002"); params.put("TransferDate", "1900-01-01");
		 */

		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);

	}

	@Test(priority = 3, groups = "SPA" )
	public void getspadetails_v_2() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/spa/CUSTOMER002/details/1";
		String ver = "2";
		String jpath = "./\\TestData\\getspadetailsv2.json";

		HashMap<String, String> params = new HashMap<String, String>();
		/*
		 * params.put("CustomerId", "CUSTOMER014"); params.put("LocationId",
		 * "WATER002"); params.put("TransferDate", "1900-01-01");
		 */

		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);

	}

	@Test(priority = 4, groups = "SPA" )
	public void getspadocuments_v_2() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/spa/CUSTOMER002/documents/1/1";
		String ver = "2";
		String jpath = "./\\TestData\\getspadocumentsv2.json";

		HashMap<String, String> params = new HashMap<String, String>();
		

		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);

	}

	@Test(priority = 5, groups = "SPA" )
	public void putcalculatedocuments_v_2()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/spa/calculate";
		String ver = "2";
		String jpath = "./\\TestData\\calculateddocumentsv2.json";

		ValidatableResponse result = CommonMethods.putMethod(uri, ver, jpath);
		result.assertThat().body(Matchers.containsString("true"));
		result.assertThat().body(Matchers.containsString("SpaCalculated"));
		result.assertThat().body(Matchers.containsString("Messages"));
		System.out.println(result.extract().asString());

	}

	@Test(priority = 6, groups = "SPA" , dependsOnMethods = "putcalculatedocuments_v_2")
	public void getcalculatedocuments_v_2()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/spa/CUSTOMER002/documents/1/1";
		String ver = "2";
		String jpath = "./\\TestData\\getcalculateddocumentsv2.json";

		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);

	}

	@Test(priority = 7, groups = "SPA" )
	public void getspaAccountBalances_v_2()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/spa/CUSTOMER001/getAccountBalances/2000-04-30";
		String ver = "2";
		String jpath = "./\\TestData\\getspagetaccountbalancesv2.json";

		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);

	}

}