package Public;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.sql.SQLException;

import org.apache.http.ConnectionClosedException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;

import io.restassured.path.json.JsonPath;

public class CashieringPostV3 {

	public static JsonPath jsonPathEvaluator;
	public static String nextRecieptNumber;
	public static String ConnectionString;
	// public static String ConnectionString =
	// "jdbc:sqlserver://DESKTOP-QU86F3Q\\MSSQLSERVER2019;DB=
	// databaseName=TWO;user=auto;password=password123;";

	public static void adjustRecieptPre(String recNum) throws ConnectionClosedException, InterruptedException {

		String uri = "/cashiering/receipt/adjust";
		String ver = "3.0";
		String payload = "{ \"Receipt\":{\"ReceiptNumber\" : \"" + recNum
				+ "\", \"Comment\": \" Nexus API adjustment via automation \" } }";
		jsonPathEvaluator = CommonMethods.postMethodStringPayload(payload, uri, ver);
		Boolean Result = jsonPathEvaluator.get("Receipt.Success");
		if (Result != null) {
			if (Result == false) {
				System.out.println("Unable to adjust or reciept already adjust, check data");

			}
			System.out.println("Reciept Adjusted =" + recNum);
			System.out.println(jsonPathEvaluator.prettyPrint());
		}

	}

	@Test(priority = 1, groups = "Cashering")
	public void saveReciept_v_3()
			throws ClassNotFoundException, SQLException, InterruptedException, ConnectionClosedException {
		// CommonMethods.CompanyDBRestore();

		String columnName = "umDocumentNumber";
		String Command1 = "select top 1 umDocumentNumber from TWO.dbo.UMRM102 order by umDocumentNumber desc";
		String Result = "";
		ConnectionString = "jdbc:sqlserver://DESKTOP-QU86F3Q\\MSSQLSERVER2019;DB= databaseName=TWO;user=sa;password=Admin2019;";
		Result = CommonMethods.selectFromDb(Command1, ConnectionString, columnName);
		if (Result != "") {
			adjustRecieptPre(Result);
		}

		JsonPath next = CommonMethods.getMethod("/cashiering/receipt/TRREG000001/nextReceipt", "3.0");
		nextRecieptNumber = next.get("Receipt[0].ReceiptNumber");
		Thread.sleep(5000);
		String uri = "/cashiering/receipt";
		String ver = "3.0";
		String payload = "{\"Receipt\":{\"ReceiptNumber\":\"" + nextRecieptNumber
				+ "\",\"OriginatingReceiptNumber\":\"\",\"Void\":false,\"CustomerId\":\"CUSTOMER008\",\"LocationId\":\"LOCATION007\",\"PaymentOrigin\":\"TEST\",\"CheckbookId\":\"FIRST NATIONAL\",\"PaidBy\":{\"Type\":1,\"Description\":\"\",\"Id\":\"\"},\"Cash\":185.42,\"Check\":{\"Amount\":0,\"Number\":\"\"},\"CreditCard\":{\"Amount\":0},\"Unapplied\":{\"Amount\":0,\"Account\":\"\",\"LocationId\":\"\"},\"Change\":0,\"Comment\":\"ThisisacommenttobesavedintocommentinUMRM102\",\"Document\":[{\"Number\":\"MISC00000000317\",\"LocationId\":\"LOCATION007\",\"StatementNumber\":0,\"ApplyAmount\":185.42,\"OutstandingAmount\":0,\"ReferenceDocumentNumber\":\"\"}]}}";
		jsonPathEvaluator = CommonMethods.postMethodStringPayload(payload, uri, ver);
		System.out.println(jsonPathEvaluator.prettyPrint());
		Boolean Result1 = jsonPathEvaluator.get("Receipt.Success");
		if (Result1 == false) {
			AssertJUnit.fail();
		} else {
			System.out.println(jsonPathEvaluator.toString());
		}

	}

	@Test(priority = 2, groups = "Cashering", dependsOnMethods = "saveReciept_v_3")
	public void TC002_RecieptAdjustment() throws ClassNotFoundException, SQLException, InterruptedException {
		String uri = "/cashiering/receipt/adjust";
		String ver = "3.0";
		String payload = "{\"Receipt\":{\"ReceiptNumber\":\"" + nextRecieptNumber
				+ "\",\"Comment\":\"NexusAPIadjustment\"}}";
		jsonPathEvaluator = CommonMethods.postMethodStringPayload(payload, uri, ver);
		Boolean Result = jsonPathEvaluator.get("Receipt.Success");
		System.out.println(jsonPathEvaluator.toString());
		if (Result == false) {
			AssertJUnit.fail();
		}

	}

}
