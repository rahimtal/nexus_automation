package Private;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;

public class locationControllerv4 {

	@Test(priority = 3, groups = "locationController")
	public static void dellocationv4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/location/locdeltest";
		String ver = "4.0";
		String expected = "{\"Location\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"locdeltest Deleted Successfully.\",\"Level\":1}]}}";
		CommonMethods.deleteMethodvoid(uri, ver, expected);

	}

}
