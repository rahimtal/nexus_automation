package Private;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ActionsMenuController_ExtentReports extends BaseClass {

    @Test(priority = 1, groups = "ActionsMenu")
    public void getActionsMenu_v4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
        ExtentTest test = extent.createTest("getActionsMenu_v4");
        test.log(Status.INFO, "Starting test: getActionsMenu_v4");

        String uri = "/actionsMenu/getActionsMenu";
        String ver = "4.0";
        test.log(Status.INFO, "URI: " + uri + ", Version: " + ver);

        String expected = "{\"result\":[{\"MenuID\":1,\"MenuLevel\":1,\"MenuName\":\"Transactions\",\"MenuDisplayName\":\"Transactions\",\"ParentMenuID\":1,\"CSMDrillback\":false,\"URL\":\"\",\"MouseOverText\":\"\"},"
                + "{\"MenuID\":23,\"MenuLevel\":2,\"MenuName\":\"Budget\",\"MenuDisplayName\":\"Budget\",\"ParentMenuID\":1,\"CSMDrillback\":false,\"URL\":\"BudgetHandler\",\"MouseOverText\":\"\"},"
                + "{\"MenuID\":13,\"MenuLevel\":2,\"MenuName\":\"Cashiering\",\"MenuDisplayName\":\"Cashiering\",\"ParentMenuID\":1,\"CSMDrillback\":false,\"URL\":\"CashieringHandler\",\"MouseOverText\":\"\"},"
                + "{\"MenuID\":2,\"MenuLevel\":2,\"MenuName\":\"CreditMemo\",\"MenuDisplayName\":\"Credit Memo\",\"ParentMenuID\":1,\"CSMDrillback\":false,\"URL\":\"CreditMemoHandler\",\"MouseOverText\":\"\"},"
                + "{\"MenuID\":3,\"MenuLevel\":2,\"MenuName\":\"Deposit\",\"MenuDisplayName\":\"Deposit\",\"ParentMenuID\":1,\"CSMDrillback\":false,\"URL\":\"DepositHandler\",\"MouseOverText\":\"\"},"
                + "{\"MenuID\":5,\"MenuLevel\":2,\"MenuName\":\"Misc. Charge\",\"MenuDisplayName\":\"Miscellaneous Charge\",\"ParentMenuID\":1,\"CSMDrillback\":false,\"URL\":\"MiscChargeHandler\",\"MouseOverText\":\"\"},"
                + "{\"MenuID\":6,\"MenuLevel\":2,\"MenuName\":\"Payment\",\"MenuDisplayName\":\"Payment\",\"ParentMenuID\":1,\"CSMDrillback\":false,\"URL\":\"PaymentHandler\",\"MouseOverText\":\"\"},"
                + "{\"MenuID\":10,\"MenuLevel\":2,\"MenuName\":\"Payment Arrangement\",\"MenuDisplayName\":\"Payment Arrangement\",\"ParentMenuID\":1,\"CSMDrillback\":false,\"URL\":\"PaymentArrangementHandler\",\"MouseOverText\":\"\"},"
                + "{\"MenuID\":7,\"MenuLevel\":2,\"MenuName\":\"PaymentExtension\",\"MenuDisplayName\":\"Payment Extension\",\"ParentMenuID\":1,\"CSMDrillback\":true,\"URL\":\"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=PaymentExtensions&CustomerID=CUSTOMER002&LocationID=SEWER003&CogsDrillback=1\",\"MouseOverText\":\"\"},"
                + "{\"MenuID\":11,\"MenuLevel\":2,\"MenuName\":\"Void Entry\",\"MenuDisplayName\":\"Void Entry\",\"ParentMenuID\":1,\"CSMDrillback\":false,\"URL\":\"VoidEntryHandler\",\"MouseOverText\":\"\"},"
                + "{\"MenuID\":4,\"MenuLevel\":2,\"MenuName\":\"Meter Reading Entry\",\"MenuDisplayName\":\"Meter Reading Entry\",\"ParentMenuID\":1,\"CSMDrillback\":false,\"URL\":\"MeterReadingEntryHandler\",\"MouseOverText\":\"Meter Reading Entry\"},"
                + "{\"MenuID\":24,\"MenuLevel\":2,\"MenuName\":\"Check Entry\",\"MenuDisplayName\":\"Check Entry\",\"ParentMenuID\":1,\"CSMDrillback\":false,\"URL\":\"CheckEntryHandler\",\"MouseOverText\":\"Check Entry\"},"
                + "{\"MenuID\":12,\"MenuLevel\":1,\"MenuName\":\"Service\",\"MenuDisplayName\":\"Service\",\"ParentMenuID\":12,\"CSMDrillback\":false,\"URL\":\"\",\"MouseOverText\":\"\"},"
                + "{\"MenuID\":8,\"MenuLevel\":2,\"MenuName\":\"Contactlog\",\"MenuDisplayName\":\"Contact Log\",\"ParentMenuID\":12,\"CSMDrillback\":false,\"URL\":\"CustomerContactLogHandler\",\"MouseOverText\":\"\"},"
                + "{\"MenuID\":9,\"MenuLevel\":2,\"MenuName\":\"ServiceOrder\",\"MenuDisplayName\":\"Service Order\",\"ParentMenuID\":12,\"CSMDrillback\":false,\"URL\":\"ServiceOrderHandler\",\"MouseOverText\":\"\"},"
                + "{\"MenuID\":22,\"MenuLevel\":2,\"MenuName\":\"Transfer Service\",\"MenuDisplayName\":\"Transfer Service\",\"ParentMenuID\":12,\"CSMDrillback\":false,\"URL\":\"TransferServiceHandler\",\"MouseOverText\":\"\"},"
                + "{\"MenuID\":30,\"MenuLevel\":1,\"MenuName\":\"Utilities\",\"MenuDisplayName\":\"Utilities\",\"ParentMenuID\":12,\"CSMDrillback\":false,\"URL\":\"\",\"MouseOverText\":\"\"},"
                + "{\"MenuID\":31,\"MenuLevel\":3,\"MenuName\":\"Rates\",\"MenuDisplayName\":\"Rates\",\"ParentMenuID\":30,\"CSMDrillback\":true,\"URL\":\"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=RateSetup&CustomerID=CUSTOMER002&LocationID=SEWER003&CogsDrillback=1\",\"MouseOverText\":\"Rate Setup\"},"
                + "{\"MenuID\":32,\"MenuLevel\":3,\"MenuName\":\"Electronic Meter Reading\",\"MenuDisplayName\":\"Electronic Meter Reading\",\"ParentMenuID\":30,\"CSMDrillback\":true,\"URL\":\"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=ElectronicMeterReading&CustomerID=CUSTOMER002&LocationID=SEWER003&CogsDrillback=1\",\"MouseOverText\":\"Electronic Meter Reading\"},"
                + "{\"MenuID\":33,\"MenuLevel\":3,\"MenuName\":\"Notice Processing\",\"MenuDisplayName\":\"Backflow Processing\",\"ParentMenuID\":30,\"CSMDrillback\":true,\"URL\":\"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=NoticeProcessing&CustomerID=CUSTOMER002&LocationID=SEWER003&CogsDrillback=1\",\"MouseOverText\":\"Notice Processing\"},"
                + "{\"MenuID\":34,\"MenuLevel\":3,\"MenuName\":\"Summer Sewer Averaging Zone\",\"MenuDisplayName\":\"Summer Sewer\",\"ParentMenuID\":30,\"CSMDrillback\":true,\"URL\":\"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=SummerSewerAveragingZone&CustomerID=CUSTOMER002&LocationID=SEWER003&CogsDrillback=1\",\"MouseOverText\":\"Summer Sewer Averaging Zone\"},"
                + "{\"MenuID\":35,\"MenuLevel\":3,\"MenuName\":\"Payment Import\",\"MenuDisplayName\":\"Payment Import\",\"ParentMenuID\":30,\"CSMDrillback\":true,\"URL\":\"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=PaymentImport&CustomerID=CUSTOMER002&LocationID=SEWER003&CogsDrillback=1\",\"MouseOverText\":\"Payment Import\"},"
                + "{\"MenuID\":36,\"MenuLevel\":3,\"MenuName\":\"Penalties\",\"MenuDisplayName\":\"Penalties\",\"ParentMenuID\":30,\"CSMDrillback\":true,\"URL\":\"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=PenaltyBatchDuePreparationCalculate&CustomerID=CUSTOMER002&LocationID=SEWER003&CogsDrillback=1\",\"MouseOverText\":\"Penalties\"},"
                + "{\"MenuID\":37,\"MenuLevel\":3,\"MenuName\":\"Rebilling\",\"MenuDisplayName\":\"Rebilling\",\"ParentMenuID\":30,\"CSMDrillback\":true,\"URL\":\"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=Re_Bill&CustomerID=CUSTOMER002&LocationID=SEWER003&CogsDrillback=1\",\"MouseOverText\":\"Rebilling\"},"
                + "{\"MenuID\":38,\"MenuLevel\":3,\"MenuName\":\"Cash Out\",\"MenuDisplayName\":\"Cash Out\",\"ParentMenuID\":30,\"CSMDrillback\":true,\"URL\":\"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=Cash_Out&CustomerID=CUSTOMER002&LocationID=SEWER003&CogsDrillback=1\",\"MouseOverText\":\"Cash Out\"},"
                + "{\"MenuID\":39,\"MenuLevel\":3,\"MenuName\":\"Miscellaneous Charge Import\",\"MenuDisplayName\":\"Miscellaneous Charge Import\",\"ParentMenuID\":30,\"CSMDrillback\":true,\"URL\":\"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=MiscChargeImport&CustomerID=CUSTOMER002&LocationID=SEWER003&CogsDrillback=1\",\"MouseOverText\":\"Miscellaneous Charge Import\"},"
                + "{\"MenuID\":40,\"MenuLevel\":3,\"MenuName\":\"Meter Reading Adjustment\",\"MenuDisplayName\":\"Meter Reading Adjustment\",\"ParentMenuID\":30,\"CSMDrillback\":true,\"URL\":\"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=MeterReadAdjust&CustomerID=CUSTOMER002&LocationID=SEWER003&CogsDrillback=1\",\"MouseOverText\":\"Meter Reading Adjustment\"},"
                + "{\"MenuID\":41,\"MenuLevel\":3,\"MenuName\":\"Payment Apply\",\"MenuDisplayName\":\"Payment Apply\",\"ParentMenuID\":30,\"CSMDrillback\":true,\"URL\":\"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=PaymentApply&CustomerID=CUSTOMER002&LocationID=SEWER003&CogsDrillback=1\",\"MouseOverText\":\"Payment Apply\"}]}";
        
        HashMap<String, String> params = new HashMap<>();
        params.put("CustomerId", "CUSTOMER002");
        params.put("LocationId", "SEWER003");
        test.log(Status.INFO, "Parameters: " + params.toString());

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

    @Test(priority = 2, groups = "ActionsMenu")
    public void getDrillbackLinkCreditInquiry_v4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
        ExtentTest test = extent.createTest("getDrillbackLinkCreditInquiry_v4");
        test.log(Status.INFO, "Starting test: getDrillbackLinkCreditInquiry_v4");

        String uri = "/actionsMenu/getDrillbackLinkCreditInquiry";
        String ver = "4.0";
        test.log(Status.INFO, "URI: " + uri + ", Version: " + ver);

        String expected = "{\"result\":[{\"DrillbackLink\":\"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=CreditInquiry&CustomerID=500001&LocationID=100001&CogsDrillback=1\"}]}";
        HashMap<String, String> params = new HashMap<>();
        params.put("CustomerId", "500001");
        params.put("LocationId", "100001");
        test.log(Status.INFO, "Parameters: " + params.toString());

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

    @Test(priority = 3, groups = "ActionsMenu")
    public void getDrillbackLinkCustomerMaintenance_v4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
        ExtentTest test = extent.createTest("getDrillbackLinkCustomerMaintenance_v4");
        test.log(Status.INFO, "Starting test: getDrillbackLinkCustomerMaintenance_v4");

        String uri = "/actionsMenu/getDrillbackLinkCustomerMaintenance";
        String ver = "4.0";
        test.log(Status.INFO, "URI: " + uri + ", Version: " + ver);

        String expected = "{\"result\":[{\"DrillbackLink\":\"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=CustomerMaintenance&CustomerID=500001&LocationID=100001&CogsDrillback=1\"}]}";
        HashMap<String, String> params = new HashMap<>();
        params.put("CustomerId", "500001");
        params.put("LocationId", "100001");
        test.log(Status.INFO, "Parameters: " + params.toString());

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

    @Test(priority = 4, groups = "ActionsMenu")
    public void getDrillbackLink_v4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
        ExtentTest test = extent.createTest("getDrillbackLink_v4");
        test.log(Status.INFO, "Starting test: getDrillbackLink_v4");

        String uri = "/actionsMenu/getDrillbackLinkAccountServices";
        String ver = "4.0";
        test.log(Status.INFO, "URI: " + uri + ", Version: " + ver);

        String expected = "{\"result\":[{\"DrillbackLink\":\"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=LocationAccountServices&CustomerID=500001&LocationID=100001&CogsDrillback=1\"}]}";
        HashMap<String, String> params = new HashMap<>();
        params.put("CustomerId", "500001");
        params.put("LocationId", "100001");
        test.log(Status.INFO, "Parameters: " + params.toString());

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
