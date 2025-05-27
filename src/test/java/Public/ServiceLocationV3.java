package Public;

import org.testng.annotations.Test;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;


public class ServiceLocationV3{

	
	@Test(priority = 1, groups = "ServiceLocation" )
	public void getServicesByLocation_v_3() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/servicesByLocation/getServicesByLocation";
		String ver = "3.0";
		String jpath = "./\\TestData\\servicesbylocationv3.json";
				
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("CustomerId", "CUSTOMER014");
		params.put("LocationId", "WATER002");
		params.put("TransferDate", "1900-01-01");
				
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	
	}
	

	
	
	
	
	

}