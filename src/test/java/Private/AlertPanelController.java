package Private;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;



public class AlertPanelController extends BaseClass {

    private void runTest(String testName, String uri, String ver, String expected) throws IOException, ClassNotFoundException, SQLException, InterruptedException {
      // //ExtentTest test = extent.createTest(testName);
       //test.log(Status.INFO, "Starting test: " + testName);
       //test.log(Status.INFO, "URI: " + uri + ", Version: " + ver);
       //test.log(Status.INFO, "Expected: " + expected);
        
        HashMap<String, String> params = new HashMap<>();
        String result = CommonMethods.getMethodasString(uri, ver, params);
       //test.log(Status.INFO, "Response: " + result);
        
        try {
            Assert.assertEquals(expected, result);
           //test.log(Status.PASS, "Response matched expected result.");
        } catch (AssertionError e) {
           //test.log(Status.FAIL, "Response did not match expected result: " + e.getMessage());
            throw e;
        }
    }

    @Test(priority = 1, groups = "AlertPanelController")
    public void getalert_v4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
        runTest("getalert_v4", "/alert/CUSTOMER002/SEWER003", "4.0", 
                "{\"Alert\":[{\"Messgage\":[],\"Success\":true,\"Messages\":[{\"Enabled\":0,\"Info\":\"\",\"Level\":0}]}]}");
    }

    @Test(priority = 2, groups = "AlertPanelController")
    public void getalertaccountAttributes_v4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
        runTest("getalertaccountAttributes_v4", "/alert/accountAttributes/500001/100001", "4.0", 
                "{\"result\":[{\"AttributeType\":\"deposits\",\"DrillbackLink\":\"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=Deposits&CustomerID=500001&LocationID=100001&Deposits=1&CogsDrillback=1\"}]}");
    }
}
