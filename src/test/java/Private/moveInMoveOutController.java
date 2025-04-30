package Private;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;
import com.NexustAPIAutomation.java.Retry;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class moveInMoveOutController {

	@Test(priority = 1, groups = "moveInMoveOutController")
	public void postTransferv4() throws ClassNotFoundException, SQLException, InterruptedException {

		String uri = "/transfer";
		String ver = "4.0";
		String payload = " {\r\n"
				+ "     \"_Comment\": \"NOTE: DELETE THIS LINE AND ALL COMMENT LINES FOR THIS POSTMAN REQUEST TO WORK\",\r\n"
				+ "     \r\n" + "     \"TransferMoveFrom\": \"TRANSFER\", \r\n"
				+ "     \"_Comment\": \"//Optional if not provided in the Request then will be pulled from Config.  Example TRANSFER.  Epmty strings will be used as a valid request parameter \",\r\n"
				+ "     \r\n" + "     \"TransferMoveTo\": \"TRANSFER\",  \r\n"
				+ "     \"_Comment\": \"//Optional if not provided in the Request then will be pulled from Config.  Example TRANSFER.  Epmty strings will be used as a valid request parameter\",\r\n"
				+ "     \r\n" + "     \"TransferCustomerId\": \"MASTER001\",\r\n"
				+ "     \"MoveOutLocationId\": \"LOCATION001\",\r\n" + "     \r\n"
				+ "     \"CurrentLocationMoveInCustomerId\": \"VACANT\",  \r\n"
				+ "     \"_Comment\": \"//Optional if not provided in the Request then will be pulled from Config.  Example VACANT.  Epmty strings will be used as a valid request parameter\",\r\n"
				+ "     \r\n" + "     \"MoveOutRequestedDateTime\": \"2018-10-10T13:30:00Z\",\r\n"
				+ "     \"MoveOutScheduledDateTime\": \"1900-01-01T00:00:00Z\", \r\n"
				+ "     \"_Comment\": \"//Optional if not provided then pulled from config. Example '1900-01-01T00:00:00Z' Epmty strings are not valid. and will return validation error.\",\r\n"
				+ "     \r\n" + "     \"MoveInLocationId\": \"LOCATION002\",\r\n" + "     \r\n"
				+ "     \"MoveInRequestedDateTime\": \"2018-12-10T13:30:00Z\",\r\n"
				+ "     \"MoveInScheduledDateTime\": \"1900-01-01T00:00:00Z\",  \r\n"
				+ "     \"_Comment\": \"//Optional if not provided then pulled from whatever value is supplied to the MoveOutScheduledDateTime\",\r\n"
				+ "     \r\n" + "     \"MoveOutDescription\": \"\", \r\n"
				+ "     \"_Comment\": \"//Optional if not provided then empty string will be used.\",\r\n" + "     \r\n"
				+ "     \"MoveInDescription\": \"\", \r\n"
				+ "     \"_Comment\": \"//Optional if not provided then empty string will be used.\"\r\n" + "     \r\n"
				+ " }";
		//String expected = "{\"ServiceOrder\":{\"Success\":true,\"Data\":{\"MoveOutDocumentNumber\":\"SORD00000009047\",\"MoveInDocumentNumber\":\"SORD00000009048\",\"MoveOutDrillbackLink\":\"cogsDrillback:\\/\\/DGPB\\/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=ServiceOrder&LocationID=LOCATION001&ServiceOrderNumber=SORD00000009047&CogsDrillback=1\",\"MoveInDrillbackLink\":\"cogsDrillback:\\/\\/DGPB\\/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=ServiceOrder&CustomerID=MASTER001&LocationID=LOCATION002&ServiceOrderNumber=SORD00000009048&CogsDrillback=1\"},\"Messages\":[{\"Enabled\":1,\"Info\":\"Successfully completed transfer process.\",\"Level\":1}]}}";

		String exp1 = "{\"ServiceOrder\":{\"Success\":true,\"Data\":{\"MoveOutDocumentNumber\":\"SORD000000090";
		String exp2 = "\",\"MoveInDocumentNumber\":\"SORD000000090";
		String exp3 = "\",\"MoveOutDrillbackLink\":\"cogsDrillback:\\/\\/DGPB\\/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=ServiceOrder&LocationID=LOCATION001&ServiceOrderNumber=SORD000000090";
		String exp4 = "&CogsDrillback=1\",\"MoveInDrillbackLink\":\"cogsDrillback:\\/\\/DGPB\\/?Db=&Srv=DESKTOP-QU86F3Q&Cmp=TWO&Prod=229&Act=OPEN&Func=ServiceOrder&CustomerID=MASTER001&LocationID=LOCATION002&ServiceOrderNumber=SORD000000090";
		String exp5 = "&CogsDrillback=1\"},\"Messages\":[{\"Enabled\":1,\"Info\":\"Successfully completed transfer process.\",\"Level\":1}]}}";
		//System.out.println(expected);
		//CommonMethods.postMethodString(payload, uri, ver, expected);
		String Result = CommonMethods.postMethodResponseAsString(payload, uri, ver);
		Assert.assertTrue(Result.contains(exp1));
		Assert.assertTrue(Result.contains(exp2));
		Assert.assertTrue(Result.contains(exp3));
		Assert.assertTrue(Result.contains(exp4));
		Assert.assertTrue(Result.contains(exp5));
		
		

	}

}
