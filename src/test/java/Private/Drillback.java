package Private;

import org.testng.annotations.Test; import org.testng.Assert;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.annotations.Test; import org.testng.Assert;

import com.NexustAPIAutomation.java.CommonMethods;


public class Drillback {

	@Test(priority = 1, groups = "Drillback" )
	public void getdrillbackv4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/drillback";
		String ver = "4.0";
		String expected = "{\"Drillback\":{\"Success\":true,\"Data\":{\"HasActivity\":null,\"Link\":\"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=Cash_Out&=&CogsDrillback=1\"},\"Messages\":\"[]\"}}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("Id", "69");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(expected, result);
	}

	public void getdrillbackv4_1() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/drillback";
		String ver = "4.0";
		String expected = "{\"Drillback\":{\"Success\":true,\"Data\":{\"HasActivity\":null,\"Link\":\"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=MeterReadAdjust&=&CogsDrillback=1\"},\"Messages\":\"[]\"}}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("Id", "70");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(expected, result);
	}

	public void getdrillbackv4_2() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/drillback";
		String ver = "4.0";
		String expected = "{\"Drillback\":{\"Success\":true,\"Data\":{\"HasActivity\":null,\"Link\":\"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=MiscChargeImport&=&CogsDrillback=1\"},\"Messages\":\"[]\"}}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("Id", "71");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(expected, result);
	}

	public void getdrillbackv4_3() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/drillback";
		String ver = "4.0";
		String expected = "{\"Drillback\":{\"Success\":true,\"Data\":{\"HasActivity\":null,\"Link\":\"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=PaymentApply&=&CogsDrillback=1\"},\"Messages\":\"[]\"}}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("Id", "72");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(expected, result);
	}

}
