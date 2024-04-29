package Private;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;

public class ServiceOrderController {
	
	
	@Test(priority = 1, groups = "ServiceOrder")
	public static void delBatv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/serviceOrder/SORD00000004258";
		String ver = "4.0";
		String jpath = "./\\TestData\\ServiceOrderv4.json";
		String result = CommonMethods.deleteMethod(uri, ver, jpath);
		System.out.println(result.toString());

	}
	
	@Test(priority = 2, groups = "ServiceOrder")
	public static void delBatv4Error()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/serviceOrder/SORD00000004258";
		String ver = "4.0";
		String jpath = "./\\TestData\\ServiceOrdervErr4.json";
		String result = CommonMethods.deleteMethod(uri, ver, jpath);
		System.out.println(result.toString());

	}

}
