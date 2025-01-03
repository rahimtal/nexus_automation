package Private;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;

public class csmSetup {

	@Test(priority = 1, groups = "csmSetup")
	public void getserviceCategoryv4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/csmSetup/serviceCategory";
		String ver = "4.0";
		String expected = "{\"ServiceCategory\":{\"Success\":true,\"Data\":{\"Category\":[{\"Id\":1,\"Description\":\"Electric\",\"Enabled\":true,\"ReceivableAccountIndex\":0,\"ReceivableAccountNumber\":\"\",\"PenaltyId\":\"\",\"TaxScheduleId\":\"\",\"MultiCompanyId\":\"\",\"CheckbookId\":\"\",\"UnappliedAccountIndex\":0,\"UnappliedAccountNumber\":\"\",\"EstimateId\":5,\"EstimateDescription\":\"Last Reading\"},{\"Id\":2,\"Description\":\"Water\",\"Enabled\":true,\"ReceivableAccountIndex\":0,\"ReceivableAccountNumber\":\"\",\"PenaltyId\":\"\",\"TaxScheduleId\":\"\",\"MultiCompanyId\":\"\",\"CheckbookId\":\"\",\"UnappliedAccountIndex\":0,\"UnappliedAccountNumber\":\"\",\"EstimateId\":5,\"EstimateDescription\":\"Last Reading\"},{\"Id\":3,\"Description\":\"Sewer\",\"Enabled\":true,\"ReceivableAccountIndex\":0,\"ReceivableAccountNumber\":\"\",\"PenaltyId\":\"\",\"TaxScheduleId\":\"\",\"MultiCompanyId\":\"\",\"CheckbookId\":\"\",\"UnappliedAccountIndex\":0,\"UnappliedAccountNumber\":\"\",\"EstimateId\":5,\"EstimateDescription\":\"Last Reading\"},{\"Id\":4,\"Description\":\"Gas\",\"Enabled\":true,\"ReceivableAccountIndex\":0,\"ReceivableAccountNumber\":\"\",\"PenaltyId\":\"\",\"TaxScheduleId\":\"\",\"MultiCompanyId\":\"\",\"CheckbookId\":\"\",\"UnappliedAccountIndex\":0,\"UnappliedAccountNumber\":\"\",\"EstimateId\":5,\"EstimateDescription\":\"Last Reading\"},{\"Id\":5,\"Description\":\"Phone\",\"Enabled\":true,\"ReceivableAccountIndex\":0,\"ReceivableAccountNumber\":\"\",\"PenaltyId\":\"\",\"TaxScheduleId\":\"\",\"MultiCompanyId\":\"\",\"CheckbookId\":\"\",\"UnappliedAccountIndex\":0,\"UnappliedAccountNumber\":\"\",\"EstimateId\":1,\"EstimateDescription\":\"Estimate Not Allowed\"},{\"Id\":6,\"Description\":\"Refuse\",\"Enabled\":true,\"ReceivableAccountIndex\":0,\"ReceivableAccountNumber\":\"\",\"PenaltyId\":\"\",\"TaxScheduleId\":\"\",\"MultiCompanyId\":\"\",\"CheckbookId\":\"\",\"UnappliedAccountIndex\":0,\"UnappliedAccountNumber\":\"\",\"EstimateId\":5,\"EstimateDescription\":\"Last Reading\"},{\"Id\":7,\"Description\":\"Property Tax\",\"Enabled\":false,\"ReceivableAccountIndex\":0,\"ReceivableAccountNumber\":\"\",\"PenaltyId\":\"\",\"TaxScheduleId\":\"\",\"MultiCompanyId\":\"\",\"CheckbookId\":\"\",\"UnappliedAccountIndex\":0,\"UnappliedAccountNumber\":\"\",\"EstimateId\":1,\"EstimateDescription\":\"Estimate Not Allowed\"},{\"Id\":8,\"Description\":\"Cable\",\"Enabled\":false,\"ReceivableAccountIndex\":0,\"ReceivableAccountNumber\":\"\",\"PenaltyId\":\"\",\"TaxScheduleId\":\"\",\"MultiCompanyId\":\"\",\"CheckbookId\":\"\",\"UnappliedAccountIndex\":0,\"UnappliedAccountNumber\":\"\",\"EstimateId\":1,\"EstimateDescription\":\"Estimate Not Allowed\"},{\"Id\":9,\"Description\":\"Internet\",\"Enabled\":false,\"ReceivableAccountIndex\":0,\"ReceivableAccountNumber\":\"\",\"PenaltyId\":\"\",\"TaxScheduleId\":\"\",\"MultiCompanyId\":\"\",\"CheckbookId\":\"\",\"UnappliedAccountIndex\":0,\"UnappliedAccountNumber\":\"\",\"EstimateId\":1,\"EstimateDescription\":\"Estimate Not Allowed\"},{\"Id\":10,\"Description\":\"Fire Protection\",\"Enabled\":false,\"ReceivableAccountIndex\":0,\"ReceivableAccountNumber\":\"\",\"PenaltyId\":\"\",\"TaxScheduleId\":\"\",\"MultiCompanyId\":\"\",\"CheckbookId\":\"\",\"UnappliedAccountIndex\":0,\"UnappliedAccountNumber\":\"\",\"EstimateId\":1,\"EstimateDescription\":\"Estimate Not Allowed\"},{\"Id\":11,\"Description\":\"Sanitation\",\"Enabled\":false,\"ReceivableAccountIndex\":0,\"ReceivableAccountNumber\":\"\",\"PenaltyId\":\"\",\"TaxScheduleId\":\"\",\"MultiCompanyId\":\"\",\"CheckbookId\":\"\",\"UnappliedAccountIndex\":0,\"UnappliedAccountNumber\":\"\",\"EstimateId\":1,\"EstimateDescription\":\"Estimate Not Allowed\"},{\"Id\":12,\"Description\":\"Propane\",\"Enabled\":false,\"ReceivableAccountIndex\":0,\"ReceivableAccountNumber\":\"\",\"PenaltyId\":\"\",\"TaxScheduleId\":\"\",\"MultiCompanyId\":\"\",\"CheckbookId\":\"\",\"UnappliedAccountIndex\":0,\"UnappliedAccountNumber\":\"\",\"EstimateId\":7,\"EstimateDescription\":\"Generic\"}]},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(actual, expected);

	}

	@Test(priority = 2, groups = "csmSetup")
	public void getcsmSetupserviceOrderv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/csmSetup/serviceOrder";
		String ver = "4.0";
		String expected = "{\"ServiceOrder\":{\"Success\":true,\"Data\":{\"MoveInDefault\":2},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(actual, expected);

	}

	@Test(priority = 3, groups = "csmSetup")
	public void getcsmDefaultCheckbookIdv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/csmSetup/checkbookId";
		String ver = "4.0";
		String expected = "{\"Setup\":{\"Success\":true,\"Data\":{\"CheckbookId\":\"FIRST NATIONAL\"},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(actual, expected);

	}

}
