package Private;

import org.testng.annotations.Test;
import java.io.IOException;
import java.sql.SQLException;

import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;
import com.NexustAPIAutomation.java.Retry;

public class ServiceOrderController {

	@Test(priority = 1, groups = "ServiceOrder")
	public static void delBatv4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/serviceOrder/SORD00000004258";
		String ver = "4.0";
		String jpath = "./\\TestData\\ServiceOrderv4.json";
		String result = CommonMethods.deleteMethod(uri, ver, jpath);
		System.out.println(result.toString());

	}

	@Test(priority = 2, groups = "ServiceOrder")
	public static void delBatv4Error() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/serviceOrder/SORD00000004258";
		String ver = "4.0";
		String jpath = "./\\TestData\\ServiceOrdervErr4.json";
		String result = CommonMethods.deleteMethod(uri, ver, jpath);
		System.out.println(result.toString());

	}

	@Test(priority = 3, groups = "ServiceOrder")
	public void putServiceOrderControllerev4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		//CommonMethods.Bug("CPDEV-20965");
		String uri = "/serviceOrder";
		String ver = "4.0";
		String payload = "{\n" + "    \"Number\": \"SORD00000000043\",\n"
				+ "    \"Description\": \"description of service order 71\",\n"
				+ "    \"RequestedDateTime\": \"2024-05-02T09:28:24Z\",\n"
				+ "    \"ScheduledDateTime\": \"1900-01-01T00:00:00Z\",\n"
				+ "    \"RescheduledDateTime\": \"1900-01-01T00:00:00Z\",\n" + "    \"EquipmentId\": \"\",\n"
				+ "    \"StatusId\": 2,\n" + "    \"CancelReasonCode\": \"\",\n" + "    \"OriginId\": 2,\n"
				+ "    \"RequestedBy\": {\n" + "        \"Type\": 1,\n" + "        \"Id\": \"\"\n" + "    },\n"
				+ "    \"Task\": [\n" + "        {\n" + "        \"Id\": \"TASK003\",\n"
				+ "        \"SequenceNumber\": 1000,\n" + "        \"OldSequenceNumber\": 1000,\n"
				+ "        \"Ordered\": false,\n" + "        \"Completed\": false,\n"
				+ "        \"ScheduledDateTime\": \"1900-01-01T00:00:00Z\",\n"
				+ "        \"StartDateTime\": \"1900-01-01T00:00:00Z\",\n"
				+ "        \"EndDateTime\": \"1900-01-01T00:00:00Z\",\n" + "        \"EmployeeId\": \"BURN0001\",\n"
				+ "        \"EquipmentId\": \"\",\n" + "        \"ChargeAmount\": 0,\n"
				+ "        \"DocumentNumber\": \"\",\n" + "        \"CrossReferenceNumber\": \"\",\n"
				+ "        \"Delete\": false\n" + "        },\n" + "                {\n"
				+ "        \"Id\": \"TASK003\",\n" + "        \"SequenceNumber\": 1100,\n"
				+ "        \"OldSequenceNumber\": 1100,\n" + "        \"Ordered\": false,\n"
				+ "        \"Completed\": false,\n" + "        \"ScheduledDateTime\": \"1900-01-01T00:00:00Z\",\n"
				+ "        \"StartDateTime\": \"1900-01-01T00:00:00Z\",\n"
				+ "        \"EndDateTime\": \"1900-01-01T00:00:00Z\",\n" + "        \"EmployeeId\": \"sa\",\n"
				+ "        \"EquipmentId\": \"\",\n" + "        \"ChargeAmount\": 0,\n"
				+ "        \"DocumentNumber\": \"\",\n" + "        \"CrossReferenceNumber\": \"\",\n"
				+ "        \"Delete\": false\n" + "        }\n" + "    ]\n" + "}";
		String exResponse = "./\\TestData\\/ServiceOrderPUTv4.json";
		CommonMethods.putMethod(uri, ver, payload, exResponse);
	}

	@Test(priority = 4, groups = "ServiceOrder")
	public static void delServiceOrderv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/serviceOrder/SORD00000000161";
		String ver = "4.0";
		String jpath = "./\\TestData\\delServiceOrderv4.json";
		String result = CommonMethods.deleteMethod(uri, ver, jpath);
		System.out.println(result.toString());

	}

}
