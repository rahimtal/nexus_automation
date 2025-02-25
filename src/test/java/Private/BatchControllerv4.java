package Private;

import java.io.IOException;
import java.sql.SQLException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.NexustAPIAutomation.java.CommonMethods;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class BatchControllerv4 extends BaseClass {

    @Test(priority = 1, groups = "batch")
    public static void postBatchtv4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
        ExtentTest test = extent.createTest("postBatchtv4");
        test.log(Status.INFO, "Starting test: postBatchtv4");

        // Setup variables
        String uri = "/batch";
        String ver = "4.0";
        String payload = "{\r\n" + 
                "    \"BatchId\": \"Test Batch 2025\",\r\n" + 
                "    \"BatchType\": 3,\r\n" +
                "    \"OriginId\": \"\",\r\n" + 
                "    \"CheckbookId\": \"FIRST NATIONAL\",\r\n" +
                "    \"Comment\": \"Example Comment\"\r\n" + 
                "}";
        String expected = "{\"Batch\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Created\",\"Level\":1}]}}";
        
        test.log(Status.INFO, "URI: " + uri + ", Version: " + ver);
        test.log(Status.INFO, "Payload: " + payload);
        test.log(Status.INFO, "Expected: " + expected);
        
        // Execute API call
        String actual = CommonMethods.postMethodStringPayloadString(payload, uri, ver);
        test.log(Status.INFO, "Actual: " + actual);
        
        // Assertion and logging
        try {
            Assert.assertTrue(actual.contains(expected));
            test.log(Status.PASS, "Response contains the expected value.");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Response did not contain expected value: " + e.getMessage());
            throw e;
        }
    }

    @Test(priority = 2, groups = "batch")
    public static void postBatchtv4_err() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
        ExtentTest test = extent.createTest("postBatchtv4_err");
        test.log(Status.INFO, "Starting test: postBatchtv4_err");

        String uri = "/batch";
        String ver = "4.0";
        String payload = "{\r\n" + 
                "    \"BatchId\": \"Test Batch 2025\",\r\n" + 
                "    \"BatchType\": 3,\r\n" +
                "    \"OriginId\": \"\",\r\n" + 
                "    \"CheckbookId\": \"FIRST NATIONAL\",\r\n" +
                "    \"Comment\": \"Example Comment\"\r\n" + 
                "}";
        String expected = "{\"Batch\":{\"Success\":false,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Batch Id ( Test Batch 2025 ) already exist.\",\"Level\":3}]}}";
        
        test.log(Status.INFO, "URI: " + uri + ", Version: " + ver);
        test.log(Status.INFO, "Payload: " + payload);
        test.log(Status.INFO, "Expected: " + expected);
        
        String actual = CommonMethods.postMethodStringPayloadString(payload, uri, ver);
        test.log(Status.INFO, "Actual: " + actual);
        
        try {
            Assert.assertTrue(actual.contains(expected));
            test.log(Status.PASS, "Response contains the expected error message.");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Response did not contain expected error message: " + e.getMessage());
            throw e;
        }
    }

    @Test(priority = 3, groups = "batch")
    public static void putBatchtv4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
        ExtentTest test = extent.createTest("putBatchtv4");
        test.log(Status.INFO, "Starting test: putBatchtv4");

        String uri = "/batch";
        String ver = "4.0";
        String payload = "{\r\n" + 
                "    \"BatchId\": \"Test Batch 2025\",\r\n" + 
                "    \"OriginId\": \"\",\r\n" + 
                "    \"CheckbookId\": \"FIRST NATIONAL\",\r\n" + 
                "    \"Comment\": \"Updated Example Comments\"\r\n" + 
                "}";
        String expected = "{\"Batch\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Updated\",\"Level\":1}]}}";
        
        test.log(Status.INFO, "URI: " + uri + ", Version: " + ver);
        test.log(Status.INFO, "Payload: " + payload);
        test.log(Status.INFO, "Expected: " + expected);
        
        String actual = CommonMethods.putMethodstring(uri, ver, payload, expected);
        test.log(Status.INFO, "Actual: " + actual);
        
        try {
            Assert.assertEquals(actual, expected);
            test.log(Status.PASS, "Response matched expected value.");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Response did not match expected value: " + e.getMessage());
            throw e;
        }
    }
}
