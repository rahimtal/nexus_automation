package com.NexusAPI.Tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.NexustAPIAutomation.java.CommonMethods;


public class Private_ServiceOrderController_Test  extends BaseClass{

	private static final Pattern SORD_PATTERN = Pattern.compile("SORD\\d{11}");

	/**
	 * Creates a fresh, deletable service order and returns its document number.
	 * Baseline service orders (e.g. SORD00000004258) now carry Transfer Tasks and
	 * cannot be deleted, so delete tests create their own throwaway order instead.
	 */
	private static String createDeletableServiceOrder()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/serviceOrder";
		String ver = "4.0";
		String payload = "{\r\n" + "    \"ServiceOrder\": [\r\n" + "        {\r\n"
				+ "            \"LocationId\": \"ELECWAT001\",\r\n"
				+ "            \"CustomerId\": \"CUSTOMER007\",\r\n"
				+ "            \"MoveInCustomerId\": \"CUSTOMER006\",\r\n"
				+ "            \"RequestId\": \"REQ-INSTALL-E\",\r\n"
				+ "            \"Description\": \"Self-contained delete test SO\",\r\n"
				+ "            \"RequestedDateTime\": \"2019-04-08T10:45:00\",\r\n"
				+ "            \"ScheduledDateTime\": \"\",\r\n"
				+ "            \"EquipmentId\": \"WATEREQUIP006\",\r\n"
				+ "            \"CommentLine\": [{ \"Id\": 2, \"Description\": \"Line 2\" }],\r\n"
				+ "            \"Udf\": [],\r\n"
				+ "            \"ShowDrillBack\": 0,\r\n"
				+ "            \"UseScheduleDateForSODetail\": false\r\n"
				+ "        }\r\n" + "    ]\r\n" + "}";
		String createResp = CommonMethods.postMethodStringPayloadString(payload, uri, ver);
		System.out.println("Create response: " + createResp);
		Assert.assertTrue(createResp.contains("\"Success\":true"),
				"Service order creation should succeed. Response: " + createResp);
		Matcher m = SORD_PATTERN.matcher(createResp);
		Assert.assertTrue(m.find(), "Create response should contain a SORD document number: " + createResp);
		return m.group();
	}

	@Test(priority = 1, groups = "ServiceOrder")
	public void delBatv4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// Known backend/DB defect: csmApi_spServiceOrderDelete fails with
		// "INSERT failed because the following SET options have incorrect settings:
		// 'ANSI_NULLS, QUOTED_IDENTIFIER'". Service order DELETE is broken at the SQL level
		// on the TWO baseline (same root cause as csmApi_spSOTransferWrapper / postTransferv4).
		// The create-then-delete flow below is correct and will pass once the DB defect is fixed.
		CommonMethods.Bug("CPDEV-27123 - csmApi_spServiceOrderDelete SET ANSI_NULLS/QUOTED_IDENTIFIER - service order DELETE broken");
		// Self-contained: create a fresh service order then delete it (happy path).
		String sord = createDeletableServiceOrder();
		String ver = "4.0";
		String result = CommonMethods.deleteMethodasString("/serviceOrder/" + sord, ver);
		System.out.println(result);
		Assert.assertTrue(result.contains("\"Success\":true"), result);
		Assert.assertTrue(result.contains("Deleted Successfully"), result);
		Assert.assertTrue(result.contains(sord), result);
	}

	@Test(priority = 2, groups = "ServiceOrder")
	public void delBatv4Error() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// Self-contained negative: deleting a non-existent service order returns an error.
		String ver = "4.0";
		String result = CommonMethods.deleteMethodasString("/serviceOrder/SORD00000099999", ver);
		System.out.println(result);
		Assert.assertTrue(result.contains("\"Success\":false"), result);
		Assert.assertTrue(result.contains("Invalid document number (SORD00000099999)."), result);
	}

	@Test(priority = 3, groups = "ServiceOrder")
	public void putServiceOrderControllerev4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		//CommonMethods.Bugs("CPDEV-20965");
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
	public void delServiceOrderv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// Known backend/DB defect: csmApi_spServiceOrderDelete fails with
		// "INSERT failed because the following SET options have incorrect settings:
		// 'ANSI_NULLS, QUOTED_IDENTIFIER'". Service order DELETE is broken at the SQL level
		// on the TWO baseline (same root cause as csmApi_spSOTransferWrapper / postTransferv4).
		// The create-then-delete flow below is correct and will pass once the DB defect is fixed.
		CommonMethods.Bug("CPDEV-27123 - csmApi_spServiceOrderDelete SET ANSI_NULLS/QUOTED_IDENTIFIER - service order DELETE broken");
		// Self-contained: create a fresh service order then delete it (happy path).
		String sord = createDeletableServiceOrder();
		String ver = "4.0";
		String result = CommonMethods.deleteMethodasString("/serviceOrder/" + sord, ver);
		System.out.println(result);
		Assert.assertTrue(result.contains("\"Success\":true"), result);
		Assert.assertTrue(result.contains("Deleted Successfully"), result);
		Assert.assertTrue(result.contains(sord), result);
	}

}
