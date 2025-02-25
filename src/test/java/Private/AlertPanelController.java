package Private;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class AlertPanelController extends BaseClass {

    @Test(priority = 1, groups = "AlertPanelController")
    public void getalert_v4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
        ExtentTest test = extent.createTest("getalert_v4");
        test.log(Status.INFO, "Starting test: getalert_v4");

        String uri = "/alert/CUSTOMER002/SEWER003";
        String ver = "4.0";
        test.log(Status.INFO, "URI: " + uri + ", Version: " + ver);

        String expected = "{\"Alert\":[{\"Messgage\":[],\"Success\":true,\"Messages\":[{\"Enabled\":0,\"Info\":\"\",\"Level\":0}]}]}";
        test.log(Status.INFO, "Expected: " + expected);

        // In this test, no parameters are provided.
        HashMap<String, String> params = new HashMap<>();

        String result = CommonMethods.getMethodasString(uri, ver, params);
        test.log(Status.INFO, "Response: " + result);

        try {
            Assert.assertEquals(expected, result);
            test.log(Status.PASS, "Response matched expected result.");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Response did not match expected result: " + e.getMessage());
            throw e;
        }
    }

    @Test(priority = 2, groups = "AlertPanelController")
    public void getalertaccountAttributes_v4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
        ExtentTest test = extent.createTest("getalertaccountAttributes_v4");
        test.log(Status.INFO, "Starting test: getalertaccountAttributes_v4");

        String uri = "/alert/accountAttributes/500001/100001";
        String ver = "4.0";
        test.log(Status.INFO, "URI: " + uri + ", Version: " + ver);

        String expected = "{\"result\":[{\"AttributeType\":\"deposits\",\"DrillbackLink\":\"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=Deposits&CustomerID=500001&LocationID=100001&Deposits=1&CogsDrillback=1\"}]}";
        test.log(Status.INFO, "Expected: " + expected);

        // In this test, no parameters are provided.
        HashMap<String, String> params = new HashMap<>();

        String result = CommonMethods.getMethodasString(uri, ver, params);
        test.log(Status.INFO, "Response: " + result);

        try {
            Assert.assertEquals(expected, result);
            test.log(Status.PASS, "Response matched expected result.");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Response did not match expected result: " + e.getMessage());
            throw e;
        }
    }
}
