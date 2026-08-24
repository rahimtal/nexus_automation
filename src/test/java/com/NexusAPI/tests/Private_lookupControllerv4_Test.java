package com.NexusAPI.Tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.Assert;
import org.testng.SkipException;

import com.NexustAPIAutomation.java.CommonMethods;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.NexustAPIAutomation.java.CommonMethods;
import com.NexustAPIAutomation.java.DataBackupRestore;

import io.restassured.path.json.JsonPath;

public class Private_lookupControllerv4_Test extends BaseClass {

	@Test(priority = 1, groups = "lookup")
	public void getlookupBatch_v4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		DataBackupRestore.CompanyDBRestore();
		String uri = "/lookupBatch";
		String ver = "4.0";
		String string1 = "{\"Id\":\"109090ABC\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"12312312\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"12345\",\"Description\":\"\",\"HasTransaction\":false},{\"Id\":\"ABC10001\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"ABC1213\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"API 20190430\",\"Description\":\"Payments from Web Service - API\",\"HasTransaction\":true},{\"Id\":\"API 20190503\",\"Description\":\"Payments from Web Service - API\",\"HasTransaction\":true},{\"Id\":\"API20220908001\",\"Description\":\"Payments from Nexus Api - API\",\"HasTransaction\":true},{\"Id\":\"API20220929001\",\"Description\":\"Payments from Nexus Api - ";
		HashMap<String, String> params = new HashMap<String, String>();
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertTrue(actual.contains(string1));

	}

	@Test(priority = 2, groups = "lookup")
	public void getapplyByService_Paymentsv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookupBatch";
		String version = "4.0";
		String expected = "{\"Batch\":[{\"Id\":\"10001\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"1001\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"100111\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"10111\",\"Description\":\"\",\"HasTransaction\":false},{\"Id\":\"109090ABC\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"12345\",\"Description\":\"\",\"HasTransaction\":false},{\"Id\":\"API 20190430\",\"Description\":\"Payments from Web Service - API\",\"HasTransaction\":true},{\"Id\":\"API 20190503\",\"Description\":\"Payments from Web Service - API\",\"HasTransaction\":true},{\"Id\":\"API20220908001\",\"Description\":\"Payments from Nexus Api - API\",\"HasTransaction\":true},{\"Id\":\"API20220929001\",\"Description\":\"Payments from Nexus Api - API\",\"HasTransaction\":true},{\"Id\":\"BAT1\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"CREDITNOTE\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"default1\",\"Description\":\"Import Payment\",\"HasTransaction\":true},{\"Id\":\"DPP041227sa01\",\"Description\":\"PYMT\",\"HasTransaction\":true},{\"Id\":\"INT4\\/12\\/2027\",\"Description\":\"\",\"HasTransaction\":false},{\"Id\":\"INT4\\/30\\/2025\",\"Description\":\"\",\"HasTransaction\":false},{\"Id\":\"PY081525sa\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"RM(3)120427\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"WO101619CRP001\",\"Description\":\"Write Off - sa\",\"HasTransaction\":false},{\"Id\":\"WRITEOFF01\",\"Description\":\"\",\"HasTransaction\":true}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("BatchSource", "PAYMENTS");
		String actual = CommonMethods.getMethodasString(uri, version, params);
		System.out.println(actual);
		Assert.assertTrue(actual.contains(expected));
		// Assert.assertEquals(actual, expected);
	}

	@Test(priority = 3, groups = "lookup")
	public void getapplyByService_nonev4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri3 = "/lookupBatch";
		String ver = "4.0";
		String jpath = "./\\TestData\\lookupBatchNone_v4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("Batchsource", "NONE");
		// params.put("LocationId", "LOCATION011");
		String result = CommonMethods.getMethod(uri3, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 4, groups = "lookup")
	public void lookupMetergroup4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookupMeterGroup";
		String ver = "4.0";
		String expected = "{\"MeterGroup\":[{\"Id\":\"MTGR00000000001\"},{\"Id\":\"MTGR00000000002\"}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		// params.put("Batchsource", "NONE"); //params.put("LocationId", "LOCATION011");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result, expected);
		System.out.println(result);
	}

	@Test(priority = 5, groups = "lookup")
	public void lookupCheckBookv4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri3 = "/lookupCheckBook";
		String ver = "4.0";
		String jpath = "./\\TestData\\lookupCheckBookv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		// params.put("Batchsource", "NONE"); //params.put("LocationId", "LOCATION011");
		String result = CommonMethods.getMethod(uri3, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 6, groups = "lookup")
	public void lookupReadingTypev4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri3 = "/lookupReadingType";
		String ver = "4.0";
		String jpath = "./\\TestData\\lookupReadingTypev4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		// params.put("Batchsource", "NONE"); //params.put("LocationId", "LOCATION011");
		String result = CommonMethods.getMethod(uri3, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 7, groups = "lookup")
	public void lookupNsfReasonCodev4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri3 = "/lookupNsfReasonCode";
		String ver = "4.0";
		String jpath = "./\\TestData\\lookupNsfReasonCodev4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		// params.put("Batchsource", "NONE"); //params.put("LocationId", "LOCATION011");
		String result = CommonMethods.getMethod(uri3, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 8, groups = "lookup")
	public void lookupMeterReadv4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// Still a Bug (2025)
		// CommonMethods.Bug("CPDEV-20970");
		String uri = "/lookupMeterRead";
		String version = "4.0";
		String expected = ":\"BILL\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000871\",\"ReadingDate\":\"2019-07-31\",\"LocationId\":\"SPALOCATION1\",\"EquipmentId\":\"AUTOGAS\",\"BatchId\":\"BATGAS1\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000830\",\"ReadingDate\":\"2019-07-31\",\"LocationId\":\"TRANSACTION001\",\"EquipmentId\":\"EQUIPMENT007\",\"BatchId\":\"AC1001\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000890\",\"ReadingDate\":\"2019-07-01\",\"LocationId\":\"BILLGRAPH\",\"EquipmentId\":\"ELECMETER\",\"BatchId\":\"VOID\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000882\",\"ReadingDate\":\"2019-06-01\",\"LocationId\":\"BILLGRAPH\",\"EquipmentId\":\"ELECMETER\",\"BatchId\":\"VOID\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000803\",\"ReadingDate\":\"2019-04-12\",\"LocationId\":\"ELECWAT003\",\"EquipmentId\":\"EQUIPMENT015\",\"BatchId\":\"1\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000805\",\"ReadingDate\":\"2019-04-12\",\"LocationId\":\"ELECWAT002\",\"EquipmentId\":\"EQUIPMENT016\",\"BatchId\":\"001\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000907\",\"ReadingDate\":\"2019-02-28\",\"LocationId\":\"STATEMENTTEST01\",\"EquipmentId\":\"ELEC0001\",\"BatchId\":\"VOID\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000905\",\"ReadingDate\":\"2019-01-30\",\"LocationId\":\"STATEMENTTEST01\",\"EquipmentId\":\"ELEC0001\",\"BatchId\":\"VOID\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000418\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCEMP-1\",\"EquipmentId\":\"EQUIPELEC021\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000419\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCEMP-1\",\"EquipmentId\":\"EQUIPELEC022\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000420\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCATION007\",\"EquipmentId\":\"EQUIPMENT009\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000421\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCATION002\",\"EquipmentId\":\"EQUIPMENT001\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000422\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCATION003\",\"EquipmentId\":\"EQUIPMENT002\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000423\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCATION004\",\"EquipmentId\":\"EQUIPMENT005\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000424\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCATION008\",\"EquipmentId\":\"EQUIPMENT006\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000425\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCATION008\",\"EquipmentId\":\"EQUIPMENT008\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000428\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCATION011\",\"EquipmentId\":\"EQUIPMENT013\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000429\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCATION012\",\"EquipmentId\":\"EQUIPMENT014\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"R";
		// String expected =
		// "{\"MeterReading\":[{\"DocumentNumber\":\"READ00000000915\",\"ReadingDate\":\"2027-04-12\",\"LocationId\":\"LOCATION008\",\"EquipmentId\":\"EQUIPMENT008\",\"BatchId\":\"12312312\",\"Status\":\"Work\"},{\"DocumentNumber\":\"READ00000000927\",\"ReadingDate\":\"2027-04-12\",\"LocationId\":\"STATEMENTTEST01\",\"EquipmentId\":\"NETMETER001\",\"BatchId\":\"MR041227sa\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000928\",\"ReadingDate\":\"2024-09-02\",\"LocationId\":\"NETMETERLOC0001\",\"EquipmentId\":\"NETMETER002\",\"BatchId\":\"BATCH0024\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000922\",\"ReadingDate\":\"2024-09-02\",\"LocationId\":\"STATEMENTTEST01\",\"EquipmentId\":\"NETMETER001\",\"BatchId\":\"BAT2024\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000932\",\"ReadingDate\":\"2024-02-12\",\"LocationId\":\"NETMETERLOC0001\",\"EquipmentId\":\"NETMETER0002\",\"BatchId\":\"BAT0004\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000930\",\"ReadingDate\":\"2024-01-01\",\"LocationId\":\"NETMETERLOC0001\",\"EquipmentId\":\"NETMETER0002\",\"BatchId\":\"BAT12\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000913\",\"ReadingDate\":\"2020-02-10\",\"LocationId\":\"WATER100\",\"EquipmentId\":\"ELECT\",\"BatchId\":\"TEST100\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000834\",\"ReadingDate\":\"2019-09-23\",\"LocationId\":\"SPALOCATION1\",\"EquipmentId\":\"EQUIPMENT018\",\"BatchId\":\"BILL10111\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000862\",\"ReadingDate\":\"2019-08-09\",\"LocationId\":\"BUDGETLOC01\",\"EquipmentId\":\"EQUIPMENT022\",\"BatchId\":\"BILL\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000871\",\"ReadingDate\":\"2019-07-31\",\"LocationId\":\"SPALOCATION1\",\"EquipmentId\":\"AUTOGAS\",\"BatchId\":\"BATGAS1\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000830\",\"ReadingDate\":\"2019-07-31\",\"LocationId\":\"TRANSACTION001\",\"EquipmentId\":\"EQUIPMENT007\",\"BatchId\":\"AC1001\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000890\",\"ReadingDate\":\"2019-07-01\",\"LocationId\":\"BILLGRAPH\",\"EquipmentId\":\"ELECMETER\",\"BatchId\":\"VOID\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000882\",\"ReadingDate\":\"2019-06-01\",\"LocationId\":\"BILLGRAPH\",\"EquipmentId\":\"ELECMETER\",\"BatchId\":\"VOID\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000803\",\"ReadingDate\":\"2019-04-12\",\"LocationId\":\"ELECWAT003\",\"EquipmentId\":\"EQUIPMENT015\",\"BatchId\":\"1\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000805\",\"ReadingDate\":\"2019-04-12\",\"LocationId\":\"ELECWAT002\",\"EquipmentId\":\"EQUIPMENT016\",\"BatchId\":\"001\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000907\",\"ReadingDate\":\"2019-02-28\",\"LocationId\":\"STATEMENTTEST01\",\"EquipmentId\":\"ELEC0001\",\"BatchId\":\"VOID\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000905\",\"ReadingDate\":\"2019-01-30\",\"LocationId\":\"STATEMENTTEST01\",\"EquipmentId\":\"ELEC0001\",\"BatchId\":\"VOID\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000418\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCEMP-1\",\"EquipmentId\":\"EQUIPELEC021\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000419\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCEMP-1\",\"EquipmentId\":\"EQUIPELEC022\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000420\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCATION007\",\"EquipmentId\":\"EQUIPMENT009\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000421\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCATION002\",\"EquipmentId\":\"EQUIPMENT001\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000422\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCATION003\",\"EquipmentId\":\"EQUIPMENT002\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000423\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCATION004\",\"EquipmentId\":\"EQUIPMENT005\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000424\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCATION008\",\"EquipmentId\":\"EQUIPMENT006\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000425\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCATION008\",\"EquipmentId\":\"EQUIPMENT008\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000428\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCATION011\",\"EquipmentId\":\"EQUIPMENT013\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000429\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCATION012\",\"EquipmentId\":\"EQUIPMENT014\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000430\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCATION013\",\"EquipmentId\":\"EQUIPMENT017\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000704\",\"ReadingDate\":\"2000-04-30\",\"LocationId\":\"000000000532001\",\"EquipmentId\":\"7899910\",\"BatchId\":\"READ66\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000705\",\"ReadingDate\":\"2000-04-30\",\"LocationId\":\"000000000532001\",\"EquipmentId\":\"16358960\",\"BatchId\":\"READ66\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000675\",\"ReadingDate\":\"2000-04-15\",\"LocationId\":\"LOCATION009\",\"EquipmentId\":\"EQUIPMENT010\",\"BatchId\":\"SOMR00000000004\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000677\",\"ReadingDate\":\"2000-04-15\",\"LocationId\":\"LOCATION010\",\"EquipmentId\":\"EQUIPMENT012\",\"BatchId\":\"SOMR00000000006\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000648\",\"ReadingDate\":\"2000-03-31\",\"LocationId\":\"ELECWAT003\",\"EquipmentId\":\"EQUIP-GAS-2\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000649\",\"ReadingDate\":\"2000-03-31\",\"LocationId\":\"ELECWAT003\",\"EquipmentId\":\"EQUIP-PHONE-1\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000651\",\"ReadingDate\":\"2000-03-31\",\"LocationId\":\"ELECWAT002\",\"EquipmentId\":\"EQUIPMENT016\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000652\",\"ReadingDate\":\"2000-03-31\",\"LocationId\":\"ELECWAT002\",\"EquipmentId\":\"WATEREQUIP003\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000653\",\"ReadingDate\":\"2000-03-31\",\"LocationId\":\"ELECWAT003\",\"EquipmentId\":\"EQUIPMENT015\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000654\",\"ReadingDate\":\"2000-03-31\",\"LocationId\":\"ELECWAT003\",\"EquipmentId\":\"WATEREQUIP004\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000656\",\"ReadingDate\":\"2000-03-31\",\"LocationId\":\"ELECWAT002\",\"EquipmentId\":\"EQUIPPH-1\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000657\",\"ReadingDate\":\"2000-03-31\",\"LocationId\":\"ELECWAT002\",\"EquipmentId\":\"EQUIPPH-1\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000658\",\"ReadingDate\":\"2000-03-31\",\"LocationId\":\"ELECWAT003\",\"EquipmentId\":\"EQUIP-PHONE-1\",\"BatchId\":\"READ55\",\"Status\":\"Open\"}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, version, params);
		Assert.assertTrue(result.contains(expected), result);

		expected = "\"READ00000000648\",\"ReadingDate\":\"2000-03-31\",\"LocationId\":\"ELECWAT003\",\"EquipmentId\":\"EQUIP-GAS-2\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000649\",\"ReadingDate\":\"2000-03-31\",\"LocationId\":\"ELECWAT003\",\"EquipmentId\":\"EQUIP-PHONE-1\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000651\",\"ReadingDate\":\"2000-03-31\",\"LocationId\":\"ELECWAT002\",\"EquipmentId\":\"EQUIPMENT016\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000652\",\"ReadingDate\":\"2000-03-31\",\"LocationId\":\"ELECWAT002\",\"EquipmentId\":\"WATEREQUIP003\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000653\",\"ReadingDate\":\"2000-03-31\",\"LocationId\":\"ELECWAT003\",\"EquipmentId\":\"EQUIPMENT015\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000654\",\"ReadingDate\":\"2000-03-31\",\"LocationId\":\"ELECWAT003\",\"EquipmentId\":\"WATEREQUIP004\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000656\",\"ReadingDate\":\"2000-03-31\",\"LocationId\":\"ELECWAT002\",\"EquipmentId\":\"EQUIPPH-1\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000657\",\"ReadingDate\":\"2000-03-31\",\"LocationId\":\"ELECWAT002\",\"EquipmentId\":\"EQUIPPH-1\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000658\",\"ReadingDate\":\"2000-03-31\",\"LocationId\":\"ELECWAT003\",\"EquipmentId\":\"EQUIP-PHONE-1\",\"BatchId\":\"READ55\",\"Status\":\"Open\"}]}";
		Assert.assertTrue(result.contains(expected), result);

		expected = ":\"NETMETERLOC0001\",\"EquipmentId\":\"NETMETER002\",\"BatchId\":\"BATCH0024\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000922\",\"ReadingDate\":\"2024-09-02\",\"LocationId\":\"STATEMENTTEST01\",\"EquipmentId\":\"NETMETER001\",\"BatchId\":\"BAT2024\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000932\",\"ReadingDate\":\"2024-02-12\",\"LocationId\":\"NETMETERLOC0001\",\"EquipmentId\":\"NETMETER0002\",\"BatchId\":\"BAT0004\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000930\",\"ReadingDate\":\"2024-01-01\",\"LocationId\":\"NETMETERLOC0001\",\"EquipmentId\":\"NETMETER0002\",\"BatchId\":\"BAT12\",\"Status\":\"Open\"},{\"DocumentNumber\":";
		Assert.assertTrue(result.contains(expected), result);
		// Assert.assertEquals(result, expected);

	}

	@Test(priority = 9, groups = "lookup")
	public void lookupdocumentTypev4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri3 = "/lookup/documentType";
		String ver = "4.0";
		String jpath = "./\\TestData\\lookupdocumentType_v4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		// params.put("Batchsource", "NONE"); //params.put("LocationId", "LOCATION011");
		String result = CommonMethods.getMethod(uri3, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 10, groups = "lookup")
	public void lookupzonev4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri3 = "/lookup/zone";
		String ver = "4.0";
		String jpath = "./\\TestData\\lookupzone_v4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri3, ver, params, jpath);
	}

	@Test(priority = 11, groups = "lookup")
	public void lookupequipmentModelv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookup/equipmentModel";
		String ver = "4.0";
		String jpath = "./\\TestData\\equipmentModel_v4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 12, groups = "lookup")
	public void lookupequipmentClassv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookup/EquipmentClass";
		String ver = "4.0";
		String jpath = "./\\TestData\\equipmentClass_v4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("EquipmentClass", "ELECMETER");
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 13, groups = "lookup")
	public void lookupequipmentTypev4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/Lookup/EquipmentType";
		String ver = "4.0";
		String jpath = "./\\TestData\\EquipmentType_v4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("ServiceCategoryId", "1");
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 14, groups = "lookup")
	public void lookupequipmentStatusv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookup/EquipmentStatus";
		String ver = "4.0";
		String jpath = "./\\TestData\\equipmentStatus_v4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("EquipmentClass", "ELECMETER");
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 15, groups = "lookup")
	public void lookupequipmentNetMetervType4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookup/equipmentNetMeterType";
		String ver = "4.0";
		String jpath = "./\\TestData\\equipmentNetMeter_v4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 16, groups = "lookup")
	public void lookupEquipmentRegisterCode4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookup/EquipmentRegisterCode";
		String ver = "4.0";
		String jpath = "./\\TestData\\EquipmentRegisterCodev4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 17, groups = "lookup")
	public void lookupEquipmentAttributeProtection4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookup/EquipmentAttributeProtection";
		String ver = "4.0";
		String jpath = "./\\TestData\\EquipmentAttributeProtectionv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 18, groups = "lookup")
	public void lookupbillType() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookup/billType";
		String ver = "4.0";
		String jpath = "./\\TestData\\billTypev4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 19, groups = "lookup")
	public void lookupbillingPrepareType()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookup/billingPrepareType";
		String ver = "4.0";
		String jpath = "./\\TestData\\billingPrepareTypev4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 20, groups = "lookup")
	public void lookupcollectionnoticeType()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookup/collection/noticeType";
		String ver = "4.0";
		String jpath = "./\\TestData\\collectionnoticeTypev4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 21, groups = "lookup")
	public void lookupkvaReadingType() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookup/kvaReadingType";
		String ver = "4.0";
		String jpath = "./\\TestData\\lookupkvaReadingTypev4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 22, groups = "lookup")
	public void lookupchargeType() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.Bugs("CPDEV-17064");
		String uri = "/lookup/chargeType";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("ChargeTypeId", "CHEQUE");
		String expected = "{\"ChargeType\":[{\"Id\":\"CHEQUE\",\"Description\":\"Misc charge for printed cheque\",\"Amount\":110.00,\"ServiceType\":\"COMM-ELECTRIC\",\"SubTypeDescription\":\"LICENSE\",\"TaxSchedule\":\"ALL DETAILS\",\"LookupVisible\":true,\"IsCheckType\":true,\"CheckTypeMessage\":\"Miscellaneous Charge is a check type.\",\"PenaltyId\":\"5%\"}]}";
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(actual, expected);

	}

	@Test(priority = 23, groups = "lookup")
	public void lookupchargeTypeAll() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.Bugs("CPDEV-17064");
		String uri = "/lookup/chargeType";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		// params.put("ChargeTypeId", "CHEQUE");
		String expected = "{\"ChargeType\":[{\"Id\":\"CHEQUE\",\"Description\":\"Misc charge for printed cheque\",\"Amount\":110.00,\"ServiceType\":\"COMM-ELECTRIC\",\"SubTypeDescription\":\"LICENSE\",\"TaxSchedule\":\"ALL DETAILS\",\"LookupVisible\":true,\"IsCheckType\":true,\"CheckTypeMessage\":\"Miscellaneous Charge is a check type.\",\"PenaltyId\":\"5%\"},{\"Id\":\"CONTRACTORLICEN\",\"Description\":\"test\",\"Amount\":100.00,\"ServiceType\":\"COMM-ELECTRIC\",\"SubTypeDescription\":\"UTILITY\",\"TaxSchedule\":\"USAUSSTCITY+6*\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"5%\"},{\"Id\":\"INSTALLELEC\",\"Description\":\"Install meter- electric service\",\"Amount\":40.00,\"ServiceType\":\"ELECTRIC\",\"SubTypeDescription\":\"UTILITY\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"5%\"},{\"Id\":\"L K\\/RECON WR\",\"Description\":\"FEE FOR WR METER REINSTALLATION\",\"Amount\":20.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"UTILITY\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"\"},{\"Id\":\"LATE CHARGE FEE\",\"Description\":\"Charge for  late payment\",\"Amount\":10.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"UTILITY\",\"TaxSchedule\":\"\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"\"},{\"Id\":\"LOANELECT\",\"Description\":\"\",\"Amount\":0.00,\"ServiceType\":\"ELECTRIC\",\"SubTypeDescription\":\"UTILITY\",\"TaxSchedule\":\"USASTCITY-6*\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"\"},{\"Id\":\"LOANSEWER\",\"Description\":\"\",\"Amount\":0.00,\"ServiceType\":\"REFUSE\",\"SubTypeDescription\":\"UTILITY\",\"TaxSchedule\":\"EXEMPT\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"\"},{\"Id\":\"LOANWATER\",\"Description\":\"automation\",\"Amount\":10.00,\"ServiceType\":\"WATER\",\"SubTypeDescription\":\"UTILITY\",\"TaxSchedule\":\"EXEMPT\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"\"},{\"Id\":\"NSF\",\"Description\":\"NSF charge\",\"Amount\":25.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"UTILITY\",\"TaxSchedule\":\"\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"\"},{\"Id\":\"PROPERTYTAX\",\"Description\":\"\",\"Amount\":0.00,\"ServiceType\":\"PROPERTYTAX\",\"SubTypeDescription\":\"OTHER\",\"TaxSchedule\":\"CAPQGSTQST-7*\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"PTAXPENALTY\"},{\"Id\":\"RMGT-BLV-000001\",\"Description\":\"Parking in Handicap zone\",\"Amount\":50.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"CODE ENFORCEMENT\",\"TaxSchedule\":\"\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"\"},{\"Id\":\"RMGT-BLV-000002\",\"Description\":\"Speeding Over 30 in 60 zone\",\"Amount\":125.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"CODE ENFORCEMENT\",\"TaxSchedule\":\"\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"\"},{\"Id\":\"RMGT-BLV-000003\",\"Description\":\"Not cleaning up after animal\",\"Amount\":80.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"CODE ENFORCEMENT\",\"TaxSchedule\":\"\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"\"},{\"Id\":\"RMGT-INV-000001\",\"Description\":\"Multi-Line invoice 1\",\"Amount\":0.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"INVOICE\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-INV-000002\",\"Description\":\"Invoice 2- Public Works\",\"Amount\":0.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"INVOICE\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-LIC-000001\",\"Description\":\"Driver's License\",\"Amount\":25.00,\"ServiceType\":\"IS\",\"SubTypeDescription\":\"LICENSE\",\"TaxSchedule\":\"USASTCITY-6*\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-LIC-000002\",\"Description\":\"Fishing license - Salmon\",\"Amount\":30.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"LICENSE\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"RES-OVERDUE\"},{\"Id\":\"RMGT-LIC-000003\",\"Description\":\"Hunting License - Moose\",\"Amount\":200.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"LICENSE\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-OTH-000001\",\"Description\":\"Miscellaneous Revenue\",\"Amount\":0.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"OTHER\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-OTH-000002\",\"Description\":\"Recyclable paper per box\",\"Amount\":5.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"OTHER\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-PMT-000001\",\"Description\":\"Building Permit Application\",\"Amount\":100.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"PERMIT\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-PMT-000002\",\"Description\":\"Build 1200-3000 sq feet\",\"Amount\":120.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"PERMIT\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-RNT-000001\",\"Description\":\"Rental Pool\",\"Amount\":40.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"RENTAL\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-RNT-000002\",\"Description\":\"Rental Fire Hall\",\"Amount\":20.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"RENTAL\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-RNT-000003\",\"Description\":\"Rental Rink\",\"Amount\":80.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"RENTAL\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-SMI-000001\",\"Description\":\"City Hall Hat\",\"Amount\":15.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"SMALL ITEM\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-SMI-000002\",\"Description\":\"City Hall logo Sweater\",\"Amount\":80.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"SMALL ITEM\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-SMI-000003\",\"Description\":\"City Hall Book\",\"Amount\":25.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"SMALL ITEM\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"SERVICE WATER\",\"Description\":\"Charge for water service\",\"Amount\":25.00,\"ServiceType\":\"WATER\",\"SubTypeDescription\":\"UTILITY\",\"TaxSchedule\":\"EXEMPT\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"SERVICEELEC\",\"Description\":\"Charge for electric service\",\"Amount\":30.00,\"ServiceType\":\"ELECTRIC\",\"SubTypeDescription\":\"UTILITY\",\"TaxSchedule\":\"USASTCITY-6*\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"\"},{\"Id\":\"WRITE OFF\",\"Description\":\"Write off\",\"Amount\":10.00,\"ServiceType\":\"ELECTRIC\",\"SubTypeDescription\":\"CODE ENFORCEMENT\",\"TaxSchedule\":\"USASTCITY-6*\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"\"},{\"Id\":\"WRITEOFF\",\"Description\":\"\",\"Amount\":0.00,\"ServiceType\":\"WATER\",\"SubTypeDescription\":\"UTILITY\",\"TaxSchedule\":\"EXEMPT\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"5%\"}]}";
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(actual, expected);

	}

	@Test(priority = 24, groups = "lookup")
	public void lookupbillingCyclePeriod()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookup/billingCyclePeriod";
		String ver = "4.0";
		String jpath = "./\\TestData\\lookupbillingCyclePeriod.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 25, groups = "lookup")
	public void lookupserviceOrderOrigin()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookup/serviceOrderOrigin";
		String ver = "4.0";
		String jpath = "./\\TestData\\lookupserviceOrderOriginv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 26, groups = "lookup")
	public void lookupserviceOrderStatus()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookup/serviceOrderStatus";
		String ver = "4.0";
		String jpath = "./\\TestData\\lookupserviceOrderStatusv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 27, groups = "lookup")
	public void lookupserviceOrderTask()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.Bugs("https://cogsdale.atlassian.net/browse/CPDEV-18771");
		// CPDEV-18771 - developer confirm this is not an issue
		//tests on QA_RELEASE_TAG=feature-cpdev-26483 on backup it might fail
		//CommonMethods.Bug("CPDEV-26483 ");
		String uri = "/lookup/serviceOrderTasks";
		String ver = "4.0";
		String jpath = "./\\TestData\\lookupserviceOrderTasksv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 271, groups = "lookup")
	public void lookupserviceOrderTask_ReturnsData()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// Positive test: endpoint returns the ServiceOrderTask collection with expected structure.
		String uri = "/lookup/serviceOrderTasks";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("PageNum", "1");
		params.put("NumPerPage", "32000");
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertTrue(actual.contains("\"ServiceOrderTask\""), actual);
		Assert.assertTrue(actual.contains("\"Action\""), actual);
		Assert.assertTrue(actual.contains("\"ServiceCategory\""), actual);
		Assert.assertTrue(actual.contains("\"ChargeType\""), actual);
		Assert.assertTrue(actual.contains("\"EmployeeId\""), actual);
		Assert.assertTrue(actual.contains("\"Amount\""), actual);
	}

	@Test(priority = 272, groups = "lookup")
	public void lookupserviceOrderTask_ContainsKnownTasks()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// Positive test: known Collection tasks are returned with the correct Action and ServiceCategory.
		String uri = "/lookup/serviceOrderTasks";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("PageNum", "1");
		params.put("NumPerPage", "32000");
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertTrue(actual.contains(
				"{\"Id\":\"COLLECTION\",\"Description\":\"Collection -deposit\",\"Action\":{\"Id\":\"14\",\"Description\":\"Collection\"},\"ServiceCategory\":{\"Id\":1,\"Description\":\"Electric\"},\"ChargeType\":\"\",\"EmployeeId\":\"\",\"Amount\":0.00}"),
				actual);
		Assert.assertTrue(actual.contains(
				"{\"Id\":\"COLLECTION 1\",\"Description\":\"Collection -NSF\",\"Action\":{\"Id\":\"14\",\"Description\":\"Collection\"},\"ServiceCategory\":{\"Id\":1,\"Description\":\"Electric\"},\"ChargeType\":\"\",\"EmployeeId\":\"\",\"Amount\":0.00}"),
				actual);
		Assert.assertTrue(actual.contains(
				"{\"Id\":\"COLLECTION 2\",\"Description\":\"Collection-overdue\",\"Action\":{\"Id\":\"14\",\"Description\":\"Collection\"},\"ServiceCategory\":{\"Id\":1,\"Description\":\"Electric\"},\"ChargeType\":\"\",\"EmployeeId\":\"\",\"Amount\":0.00}"),
				actual);
	}

	@Test(priority = 273, groups = "lookup")
	public void lookupserviceOrderTask_OrderByServiceCategoryDesc()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// Positive test: OrderBy parameter is accepted and data is returned.
		String uri = "/lookup/serviceOrderTasks";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("OrderBy", "ServiceCategory.Id DESC");
		params.put("PageNum", "1");
		params.put("NumPerPage", "32000");
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertTrue(actual.contains("\"ServiceOrderTask\""), actual);
		Assert.assertTrue(actual.contains("\"ServiceCategory\""), actual);
	}

	@Test(priority = 274, groups = "lookup")
	public void lookupserviceOrderTask_Pagination()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// Positive test: pagination with a small page size still returns the collection.
		String uri = "/lookup/serviceOrderTasks";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("PageNum", "1");
		params.put("NumPerPage", "2");
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertTrue(actual.contains("\"ServiceOrderTask\""), actual);
	}

	@Test(priority = 28, groups = "lookup")
	public void lookupserviceOrderRequestedBy()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookup/serviceOrderRequestedBy";
		String ver = "4.0";
		String jpath = "./\\TestData\\lookupserviceOrderRequestedByv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 29, groups = "lookup")
	public void lookuplocationClass() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		CommonMethods.Bug(" CPDEV-22862 ");
		String uri = "/lookup/locationClass";
		String version = "4.0";
		String expected = "{\"LocationClass\":[{\"Id\":\"NONCUST-LOC\",\"Description\":\"Non customer Location\"},{\"Id\":\"PERM-NON ACCUM\",\"Description\":\"Permanent Non Accumulated\"},{\"Id\":\"TEST001\",\"Description\":\"temporaty customer\"}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, version, params);
		Assert.assertEquals(result, expected);
	}

	@Test(priority = 30, groups = "lookup")
	public void lookuplocation() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		CommonMethods.Bug(" CPDEV-22862 ");
		String uri = "/lookupLocation";
		String version = "4.0";
		String expected = "{\"Locations\":[{\"LocationId\":\"LOCATION001\"}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "LOCATION001");
		String result = CommonMethods.getMethodasString(uri, version, params);
		Assert.assertEquals(result, expected);
	}

	@Test(priority = 31, groups = "lookup")
	public void lookupBatchFinalBill() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookupBatch?BatchSource=BILLING&BillingType=Final";
		String version = "4.0";
		// String expected =
		// "{\"Batch\":[{\"Id\":\"BAT10123123\",\"Description\":\"\",\"HasTransaction\":false}]}";
		// String expected =
		// "{\"Batch\":[{\"Id\":\"BAT10123123\",\"Description\":\"\",\"HasTransaction\":false},{\"Id\":\"FINALBILL\",\"Description\":\"\",\"HasTransaction\":true}]}";
		String expected = "{\"Batch\":[{\"Id\":\"BAT10123123\",\"Description\":\"\",\"HasTransaction\":false},{\"Id\":\"BATCH2025\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"BATCHPOSTTRANS\",\"Description\":\"\",\"HasTransaction\":false},{\"Id\":\"BATCHTEST01\",\"Description\":\"\",\"HasTransaction\":false},{\"Id\":\"FINALBILL\",\"Description\":\"\",\"HasTransaction\":true}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("BatchSource", "BILLING");
		params.put("BillingType", "Final");
		String result = CommonMethods.getMethodasString(uri, version, params);
		Assert.assertEquals(result, expected);
	}

	@Test(priority = 32, groups = "lookup")
	public void lookuptranferBillToCustomerDeposit()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		// Still a Bug (2025)
		CommonMethods.Bug("CPDEV-20377");
		String uri = "/lookup/tranferBillToCustomerDeposit";
		String version = "4.0";
		String expected = "{\"TranferBillToCustomerDeposit\":[{\"Id\":2,\"Description\":\"Refund of Difference\"},{\"Id\":3,\"Description\":\"Full Refund\"}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		// params.put("BatchSource", "BILLING");
		// params.put("BillingType", "Final");
		String result = CommonMethods.getMethodasString(uri, version, params);
		Assert.assertEquals(result, expected);
	}

	@Test(priority = 211, groups = "lookup")
	public void lookupchargeTypeAll2() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.Bugs("CPDEV-17064");
		String uri = "/lookup/chargeType";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		// params.put("ChargeTypeId", "CHEQUE");
		String expected = "{\"ChargeType\":[{\"Id\":\"CHEQUE\",\"Description\":\"Misc charge for printed cheque\",\"Amount\":110.00,\"ServiceType\":\"COMM-ELECTRIC\",\"SubTypeDescription\":\"LICENSE\",\"TaxSchedule\":\"ALL DETAILS\",\"LookupVisible\":true,\"IsCheckType\":true,\"CheckTypeMessage\":\"Miscellaneous Charge is a check type.\",\"PenaltyId\":\"5%\"},{\"Id\":\"CONTRACTORLICEN\",\"Description\":\"test\",\"Amount\":100.00,\"ServiceType\":\"COMM-ELECTRIC\",\"SubTypeDescription\":\"UTILITY\",\"TaxSchedule\":\"USAUSSTCITY+6*\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"5%\"},{\"Id\":\"INSTALLELEC\",\"Description\":\"Install meter- electric service\",\"Amount\":40.00,\"ServiceType\":\"ELECTRIC\",\"SubTypeDescription\":\"UTILITY\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"5%\"},{\"Id\":\"L K\\/RECON WR\",\"Description\":\"FEE FOR WR METER REINSTALLATION\",\"Amount\":20.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"UTILITY\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"\"},{\"Id\":\"LATE CHARGE FEE\",\"Description\":\"Charge for  late payment\",\"Amount\":10.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"UTILITY\",\"TaxSchedule\":\"\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"\"},{\"Id\":\"LOANELECT\",\"Description\":\"\",\"Amount\":0.00,\"ServiceType\":\"ELECTRIC\",\"SubTypeDescription\":\"UTILITY\",\"TaxSchedule\":\"USASTCITY-6*\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"\"},{\"Id\":\"LOANSEWER\",\"Description\":\"\",\"Amount\":0.00,\"ServiceType\":\"REFUSE\",\"SubTypeDescription\":\"UTILITY\",\"TaxSchedule\":\"EXEMPT\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"\"},{\"Id\":\"LOANWATER\",\"Description\":\"automation\",\"Amount\":10.00,\"ServiceType\":\"WATER\",\"SubTypeDescription\":\"UTILITY\",\"TaxSchedule\":\"EXEMPT\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"\"},{\"Id\":\"NSF\",\"Description\":\"NSF charge\",\"Amount\":25.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"UTILITY\",\"TaxSchedule\":\"\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"\"},{\"Id\":\"PROPERTYTAX\",\"Description\":\"\",\"Amount\":0.00,\"ServiceType\":\"PROPERTYTAX\",\"SubTypeDescription\":\"OTHER\",\"TaxSchedule\":\"CAPQGSTQST-7*\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"PTAXPENALTY\"},{\"Id\":\"RMGT-BLV-000001\",\"Description\":\"Parking in Handicap zone\",\"Amount\":50.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"CODE ENFORCEMENT\",\"TaxSchedule\":\"\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"\"},{\"Id\":\"RMGT-BLV-000002\",\"Description\":\"Speeding Over 30 in 60 zone\",\"Amount\":125.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"CODE ENFORCEMENT\",\"TaxSchedule\":\"\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"\"},{\"Id\":\"RMGT-BLV-000003\",\"Description\":\"Not cleaning up after animal\",\"Amount\":80.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"CODE ENFORCEMENT\",\"TaxSchedule\":\"\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"\"},{\"Id\":\"RMGT-INV-000001\",\"Description\":\"Multi-Line invoice 1\",\"Amount\":0.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"INVOICE\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-INV-000002\",\"Description\":\"Invoice 2- Public Works\",\"Amount\":0.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"INVOICE\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-LIC-000001\",\"Description\":\"Driver's License\",\"Amount\":25.00,\"ServiceType\":\"IS\",\"SubTypeDescription\":\"LICENSE\",\"TaxSchedule\":\"USASTCITY-6*\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-LIC-000002\",\"Description\":\"Fishing license - Salmon\",\"Amount\":30.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"LICENSE\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"RES-OVERDUE\"},{\"Id\":\"RMGT-LIC-000003\",\"Description\":\"Hunting License - Moose\",\"Amount\":200.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"LICENSE\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-OTH-000001\",\"Description\":\"Miscellaneous Revenue\",\"Amount\":0.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"OTHER\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-OTH-000002\",\"Description\":\"Recyclable paper per box\",\"Amount\":5.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"OTHER\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-PMT-000001\",\"Description\":\"Building Permit Application\",\"Amount\":100.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"PERMIT\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-PMT-000002\",\"Description\":\"Build 1200-3000 sq feet\",\"Amount\":120.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"PERMIT\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-RNT-000001\",\"Description\":\"Rental Pool\",\"Amount\":40.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"RENTAL\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-RNT-000002\",\"Description\":\"Rental Fire Hall\",\"Amount\":20.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"RENTAL\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-RNT-000003\",\"Description\":\"Rental Rink\",\"Amount\":80.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"RENTAL\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-SMI-000001\",\"Description\":\"City Hall Hat\",\"Amount\":15.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"SMALL ITEM\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-SMI-000002\",\"Description\":\"City Hall logo Sweater\",\"Amount\":80.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"SMALL ITEM\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-SMI-000003\",\"Description\":\"City Hall Book\",\"Amount\":25.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"SMALL ITEM\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"SERVICE WATER\",\"Description\":\"Charge for water service\",\"Amount\":25.00,\"ServiceType\":\"WATER\",\"SubTypeDescription\":\"UTILITY\",\"TaxSchedule\":\"EXEMPT\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"SERVICEELEC\",\"Description\":\"Charge for electric service\",\"Amount\":30.00,\"ServiceType\":\"ELECTRIC\",\"SubTypeDescription\":\"UTILITY\",\"TaxSchedule\":\"USASTCITY-6*\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"\"},{\"Id\":\"WRITE OFF\",\"Description\":\"Write off\",\"Amount\":10.00,\"ServiceType\":\"ELECTRIC\",\"SubTypeDescription\":\"CODE ENFORCEMENT\",\"TaxSchedule\":\"USASTCITY-6*\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"\"},{\"Id\":\"WRITEOFF\",\"Description\":\"\",\"Amount\":0.00,\"ServiceType\":\"WATER\",\"SubTypeDescription\":\"UTILITY\",\"TaxSchedule\":\"EXEMPT\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"5%\"}]}";
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(actual, expected);

	}

	@Test(priority = 33, groups = "lookup")
	public void getlookupcreditCard_v4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookup/creditCard";
		String ver = "4.0";
		String expected = "{\"CreditCard\":[{\"Name\":\"AmericaCharge\"},{\"Name\":\"Bankcard\"},{\"Name\":\"Gold Credit\"},{\"Name\":\"Platinum Credit\"},{\"Name\":\"Retail Credit\"}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertTrue(actual.contains(expected));
	}

	// This will create elastic search index if not already
	@Test(priority = 34, groups = "Search")
	public void elascticsearchcreateindex_v_4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/search/load?index=accounts";
		String ver = "4.0";
		String payload = "{\r\n" + "    \"Index\": \"accounts\",\r\n" + "    \"RefreshModifiedOnly\": false\r\n"
				+ "}\r\n" + "";

		JsonPath result = CommonMethods.postMethodStringPayload(payload, uri, ver);
		System.out.println(result.prettyPrint());

	}

	@Test(priority = 35, groups = "lookup")
	public void getserviceAddressLookup_v4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/serviceAddressLookup";
		String ver = "e";
		String expected = "{\"result\":{\"statusCode\":\"0\",\"statusMessage\":\"\",\"apiVersionNumber\":\"1.0\"},\"serviceAddresses\":[{\"serviceAddress\":\"130 W SAMSULA DR\",\"premiseId\":\"000000000523000\",\"serviceAddressLine2\":\"Address Line 2\"}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("serviceAddress", "130");
		params.put("resultLimit", "50");
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println("===============================================");
		System.out.println(actual);
		System.out.println("===============================================");
		Assert.assertEquals(actual, expected);
	}

	@Test(priority = 36, groups = "lookup")
	public void lookupPaymentDocuments()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// Still a Bug (2025)
		// CommonMethods.Bugs("https://cogsdale.atlassian.net/browse/CPDEV-18805");
		String uri = "/lookup/paymentDocuments";
		String ver = "4.0";
		String jpath = "./\\TestData\\lookuppaymentDocumentsv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("DocumentSource", "work");
		params.put("PaymentType", "creditmemo");
		params.put("PageNum", "1");
		String expected = "{\"Payment\":[{\"DocumentNumber\":\"PYMT00000000529\",\"BatchId\":\"WRITEOFF01\",\"LocationId\":\"LOCATION004\",\"CustomerId\":\"MASTER001\",\"PaymentDate\":\"2027-04-12T00:00:00\",\"PaymentTypeDesc\":\"CreditMemo\"},{\"DocumentNumber\":\"PYMT00000000532\",\"BatchId\":\"WRITEOFF01\",\"LocationId\":\"LOCATION004\",\"CustomerId\":\"MASTER001\",\"PaymentDate\":\"2027-04-12T00:00:00\",\"PaymentTypeDesc\":\"CreditMemo\"},{\"DocumentNumber\":\"PYMT00000000534\",\"BatchId\":\"WRITEOFF01\",\"LocationId\":\"LOCATION004\",\"CustomerId\":\"MASTER001\",\"PaymentDate\":\"2027-04-12T00:00:00\",\"PaymentTypeDesc\":\"CreditMemo\"},{\"DocumentNumber\":\"PYMT00000000535\",\"BatchId\":\"WRITEOFF01\",\"LocationId\":\"LOCATION001\",\"CustomerId\":\"MASTER001\",\"PaymentDate\":\"2027-04-12T00:00:00\",\"PaymentTypeDesc\":\"CreditMemo\"},{\"DocumentNumber\":\"PYMT00000000537\",\"BatchId\":\"WRITEOFF01\",\"LocationId\":\"LOCATION004\",\"CustomerId\":\"MASTER001\",\"PaymentDate\":\"2027-04-12T00:00:00\",\"PaymentTypeDesc\":\"CreditMemo\"},{\"DocumentNumber\":\"PYMT00000000543\",\"BatchId\":\"CREDITNOTE\",\"LocationId\":\"100002\",\"CustomerId\":\"500002\",\"PaymentDate\":\"2027-04-12T00:00:00\",\"PaymentTypeDesc\":\"CreditMemo\"}]}";
		String actual = CommonMethods.getMethodasString(uri, ver, params);// (uri, ver, params, jpath);
		Assert.assertTrue(actual.contains(expected));
		String expected2 = "{\"Payment\":[{\"DocumentNumber\":\"PYMT00000000529\",\"BatchId\":\"WRITEOFF01\",\"LocationId\":\"LOCATION004\",\"CustomerId\":\"MASTER001\",\"PaymentDate\":\"2027-04-12T00:00:00\",\"PaymentTypeDesc\":\"CreditMemo\"},{\"DocumentNumber\":\"PYMT00000000532\",\"BatchId\":\"WRITEOFF01\",\"LocationId\":\"LOCATION004\",\"CustomerId\":\"MASTER001\",\"PaymentDate\":\"2027-04-12T00:00:00\",\"PaymentTypeDesc\":\"CreditMemo\"},{\"DocumentNumber\":\"PYMT00000000534\",\"BatchId\":\"WRITEOFF01\",\"LocationId\":\"LOCATION004\",\"CustomerId\":\"MASTER001\",\"PaymentDate\":\"2027-04-12T00:00:00\",\"PaymentTypeDesc\":\"CreditMemo\"},{\"DocumentNumber\":\"PYMT00000000535\",\"BatchId\":\"WRITEOFF01\",\"LocationId\":\"LOCATION001\",\"CustomerId\":\"MASTER001\",\"PaymentDate\":\"2027-04-12T00:00:00\",\"PaymentTypeDesc\":\"CreditMemo\"},{\"DocumentNumber\":\"PYMT00000000537\",\"BatchId\":\"WRITEOFF01\",\"LocationId\":\"LOCATION004\",\"CustomerId\":\"MASTER001\",\"PaymentDate\":\"2027-04-12T00:00:00\",\"PaymentTypeDesc\":\"CreditMemo\"},{\"DocumentNumber\":\"PYMT00000000543\",\"BatchId\":\"CREDITNOTE\",\"LocationId\":\"100002\",\"CustomerId\":\"500002\",\"PaymentDate\":\"2027-04-12T00:00:00\",\"PaymentTypeDesc\":\"CreditMemo\"}]}";
		Assert.assertTrue(actual.contains(expected2));

	}

	@Test(priority = 37, groups = "lookup")
	public void lookupMiscChargeDocuments()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// Still a Bug (2025)
		CommonMethods.Bug(" CPDEV-17161 ");
		String uri = "/lookup/miscChargeDocuments";
		String ver = "4.0";
		String jpath = "./\\TestData\\lookupMiscChargeDocumentsv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("DocumentSource", "work");
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 38, groups = "lookup")
	public void lookupServiceType()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/lookupServiceType";
		String ver = "4.0";
		String body = "{\r\n"
				+ "    \"MeterSwitchDetails\": [{\r\n"
				+ "        \"LocationId\": \"WATER002\",\r\n"
				+ "        \"OldEquipment\": {\r\n"
				+ "            \"Id\": \"WATEREQUIP002\",\r\n"
				+ "            \"Connection\": 1,\r\n"
				+ "            \"RemovalDate\": \"2020-07-15\",\r\n"
				+ "            \"RemovalReason\": \"Broken\"\r\n"
				+ "        },\r\n"
				+ "        \"NewEquipment\": {\r\n"
				+ "            \"Id\": \"WATEREQUIP005\",\r\n"
				+ "            \"Status\": 2,\r\n"
				+ "            \"StatusDescription\": \"Active\",\r\n"
				+ "            \"InstallDate\": \"2020-07-16\",\r\n"
				+ "            \"ConnectionDate\": \"2020-07-16\",\r\n"
				+ "            \"Multiplier\": {\r\n"
				+ "                \"Rate\": 1.00000,\r\n"
				+ "                \"Fixed\": 1.00000,\r\n"
				+ "                \"Loss\": 1.00000,\r\n"
				+ "                \"Consumption\": 1.00000,\r\n"
				+ "                \"RangeAndMinimum\": 1.00000\r\n"
				+ "            },\r\n"
				+ "            \"DiscountRate\": \"\",\r\n"
				+ "            \"Rates\": [{\r\n"
				+ "                    \"Period\": {\r\n"
				+ "                        \"Index\": 1,\r\n"
				+ "                        \"Name\": \"ON PEAK\",\r\n"
				+ "                        \"Description\": \"On Peak Consumption\",\r\n"
				+ "                        \"Tariff1\": \"WATERMETERED\",\r\n"
				+ "                        \"Tariff2\": \"\",\r\n"
				+ "                        \"Tariff3\": \"\",\r\n"
				+ "                        \"Tariff4\": \"\"\r\n"
				+ "                    }\r\n"
				+ "                }\r\n"
				+ "            ],\r\n"
				+ "            \"Remote\": [{\r\n"
				+ "                \"Id\": \"\",\r\n"
				+ "                \"Type\": \"\",\r\n"
				+ "                \"InstallDate\": \"\"\r\n"
				+ "            }]\r\n"
				+ "        }\r\n"
				+ "    }]\r\n"
				+ "}";
		String expected = "{\"ServiceTypes\":[{\"Id\":\"COMM-ELECTRIC\",\"Description\":\"Commercial electrical accounts\",\"ServiceCategory\":\"1\",\"ServiceCategoryDescription\":\"Electric\",\"PenaltyId\":\"5%\",\"PenaltyDescription\":\"Late Payment Charge\"},{\"Id\":\"ELECTRIC\",\"Description\":\"Residential electrical accounts\",\"ServiceCategory\":\"1\",\"ServiceCategoryDescription\":\"Electric\",\"PenaltyId\":\"5%\",\"PenaltyDescription\":\"Late Payment Charge\"},{\"Id\":\"GAS\",\"Description\":\"Residential gas accounts\",\"ServiceCategory\":\"4\",\"ServiceCategoryDescription\":\"Gas\",\"PenaltyId\":\"DEFAULTPYMT\",\"PenaltyDescription\":\"Penalty for late payment\"},{\"Id\":\"GC\",\"Description\":\"Garbage (Billed for City 424-2212)\",\"ServiceCategory\":\"1\",\"ServiceCategoryDescription\":\"Electric\",\"PenaltyId\":\"RES-OVERDUE\",\"PenaltyDescription\":\"Penalty for overdue\"},{\"Id\":\"INTERNET\",\"Description\":\"Internet accounts\",\"ServiceCategory\":\"5\",\"ServiceCategoryDescription\":\"Phone\",\"PenaltyId\":\"5%\",\"PenaltyDescription\":\"Late Payment Charge\"},{\"Id\":\"IR\",\"Description\":\"Irrigation Water\",\"ServiceCategory\":\"2\",\"ServiceCategoryDescription\":\"Water\",\"PenaltyId\":\"DEFAULTPYMT\",\"PenaltyDescription\":\"Penalty for late payment\"},{\"Id\":\"IR-A\",\"Description\":\"Sewer service account\",\"ServiceCategory\":\"3\",\"ServiceCategoryDescription\":\"Sewer\",\"PenaltyId\":\"REFUSE\",\"PenaltyDescription\":\"Refuse Late Charges\"},{\"Id\":\"IS\",\"Description\":\"Internet Service\",\"ServiceCategory\":\"1\",\"ServiceCategoryDescription\":\"Electric\",\"PenaltyId\":\"5%\",\"PenaltyDescription\":\"Late Payment Charge\"},{\"Id\":\"PC\",\"Description\":\"Pollution Control\",\"ServiceCategory\":\"3\",\"ServiceCategoryDescription\":\"Sewer\",\"PenaltyId\":\"REFUSE\",\"PenaltyDescription\":\"Refuse Late Charges\"},{\"Id\":\"PHONE\",\"Description\":\"Phone service\",\"ServiceCategory\":\"5\",\"ServiceCategoryDescription\":\"Phone\",\"PenaltyId\":\"RES-OVERDUE\",\"PenaltyDescription\":\"Penalty for overdue\"},{\"Id\":\"PROPERTYTAX\",\"Description\":\"Service Type for Property Tax\",\"ServiceCategory\":\"6\",\"ServiceCategoryDescription\":\"Refuse\",\"PenaltyId\":\"PTAXPENALTY\",\"PenaltyDescription\":\"Penalty for Property Tax\"},{\"Id\":\"RE_FIX\",\"Description\":\"Residential Electric Fixed\",\"ServiceCategory\":\"1\",\"ServiceCategoryDescription\":\"Electric\",\"PenaltyId\":\"RES-OVERDUE\",\"PenaltyDescription\":\"Penalty for overdue\"},{\"Id\":\"RE_MR\",\"Description\":\"Residential Electric Consumption\",\"ServiceCategory\":\"1\",\"ServiceCategoryDescription\":\"Electric\",\"PenaltyId\":\"RES-OVERDUE\",\"PenaltyDescription\":\"Penalty for overdue\"},{\"Id\":\"REFUSE\",\"Description\":\"Refuse Service\",\"ServiceCategory\":\"6\",\"ServiceCategoryDescription\":\"Refuse\",\"PenaltyId\":\"REFUSE\",\"PenaltyDescription\":\"Refuse Late Charges\"},{\"Id\":\"SEWER\",\"Description\":\"Residential sewer accounts\",\"ServiceCategory\":\"3\",\"ServiceCategoryDescription\":\"Sewer\",\"PenaltyId\":\"DEFAULTPYMT\",\"PenaltyDescription\":\"Penalty for late payment\"},{\"Id\":\"ST-LIGHTS\",\"Description\":\"Street lights\",\"ServiceCategory\":\"1\",\"ServiceCategoryDescription\":\"Electric\",\"PenaltyId\":\"5%\",\"PenaltyDescription\":\"Late Payment Charge\"},{\"Id\":\"WATER\",\"Description\":\"Water residential customers\",\"ServiceCategory\":\"2\",\"ServiceCategoryDescription\":\"Water\",\"PenaltyId\":\"DEFAULTPYMT\",\"PenaltyDescription\":\"Penalty for late payment\"},{\"Id\":\"WATER-COMM\",\"Description\":\"Water Commercial accounts\",\"ServiceCategory\":\"2\",\"ServiceCategoryDescription\":\"Water\",\"PenaltyId\":\"DEFAULTPYMT\",\"PenaltyDescription\":\"Penalty for late payment\"},{\"Id\":\"WR\",\"Description\":\"Water\",\"ServiceCategory\":\"2\",\"ServiceCategoryDescription\":\"Water\",\"PenaltyId\":\"DEFAULTPYMT\",\"PenaltyDescription\":\"Penalty for late payment\"},{\"Id\":\"WR-A\",\"Description\":\"Water service account\",\"ServiceCategory\":\"2\",\"ServiceCategoryDescription\":\"Water\",\"PenaltyId\":\"DEFAULTPYMT\",\"PenaltyDescription\":\"Penalty for late payment\"}]}";
		String actual = CommonMethods.getMethodasString(uri, ver, body);
		Assert.assertTrue(actual.contains(expected));

	}

	@Test(priority = 38, groups = "lookup")
	public void lookupUdfpicklist()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// Still a Bug (2025)

		String uri = "/lookup/udf/picklist";
		String ver = "4.0";
		String expected = "{\"Picklist\":[{\"Series\":7,\"Order\":[{\"Id\":\"7\",\"Label\":\"Customer UD - Picklist\",\"List\":[{\"Value\":\"Picllist1\",\"Description\":\"\"},{\"Value\":\"Picllist2\",\"Description\":\"\"},{\"Value\":\"Picllist3\",\"Description\":\"\"},{\"Value\":\"Picllist4\",\"Description\":\"\"}]}]}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("Series", "7");
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertEquals(actual, expected);
	}

	@Test(priority = 39, groups = "lookup")
	public void lookuprateDetailType()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/lookup/rateDetailType";
		String ver = "4.0";
		String expected = "{\"RateDetailType\":[{\"Id\":1,\"Description\":\"Fixed Charge\"},{\"Id\":2,\"Description\":\"Stepped Ranges\"},{\"Id\":3,\"Description\":\"Variable SR\"},{\"Id\":4,\"Description\":\"Adjustable Var. SR\"},{\"Id\":5,\"Description\":\"Spot Price\"},{\"Id\":6,\"Description\":\"Percentage\"},{\"Id\":7,\"Description\":\"Fixed Range\"},{\"Id\":8,\"Description\":\"External\"}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		// params.put("Series", "7");
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertEquals(actual, expected);
	}

	@Test(priority = 40, groups = "lookup")
	public void lookupRateType()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/lookup/rateType";
		String ver = "4.0";
		String expected = "{\"RateType\":[{\"Id\":0,\"Description\":\"None\"},{\"Id\":1,\"Description\":\"Consumption\"},{\"Id\":2,\"Description\":\"KW\"},{\"Id\":3,\"Description\":\"KVA\"}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		// params.put("Series", "7");
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertEquals(actual, expected);
	}

	@Test(priority = 41, groups = "lookup")
	public void lookuprateApplyDiscountType()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/lookup/rateApplyDiscountType";
		String ver = "4.0";
		String expected = "{\"RateApplyDiscountType\":[{\"Id\":1,\"Description\":\"Connection Discount\"},{\"Id\":2,\"Description\":\"Multi Service Discount\"},{\"Id\":3,\"Description\":\"Both\"}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		// params.put("Series", "7");
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertEquals(actual, expected);
	}

	@Test(priority = 42, groups = "lookup")
	public void lookuprateCustomerChoice()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/lookup/rateCustomerChoice";
		String ver = "4.0";
		String expected = "{\"RateCustomerChoice\":[{\"Id\":1,\"Description\":\"Marketer Charge\"},{\"Id\":2,\"Description\":\"Utility Charge\"}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		// params.put("Series", "7");
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertEquals(actual, expected);
	}

	@Test(priority = 43, groups = "lookup")
	public void lookuprateWNADetailType()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/lookup/rateWNADetailType";
		String ver = "4.0";
		String expected = "{\"RateWNADetailType\":[{\"Id\":1,\"Description\":\"Base\"},{\"Id\":2,\"Description\":\"Adjustment\"}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		// params.put("Series", "7");
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertEquals(actual, expected);
	}

	@Test(priority = 44, groups = "lookup")
	public void lookupbillingMessageType()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/lookup/billingMessageType";
		String ver = "4.0";
		String expected = "{\"BillingMessageType\":[{\"Id\":0,\"Description\":\"Default\"},{\"Id\":1,\"Description\":\"Global\"},{\"Id\":3,\"Description\":\"Customer Specific\"},{\"Id\":4,\"Description\":\"Rate\"},{\"Id\":5,\"Description\":\"Zone\"}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		// params.put("Series", "7");
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertEquals(actual, expected);
	}

	@Test(priority = 45, groups = "lookup")
	public void lookupbrateMeterSizeMethod()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/lookup/rateMeterSizeMethod";
		String ver = "4.0";
		String expected = "{\"RateMeterSizeMethod\":[{\"Id\":1,\"Description\":\"Equipment Class\"},{\"Id\":2,\"Description\":\"Equipment Diameter\"}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertEquals(actual, expected);
	}

	@Test(priority = 46, groups = "lookup")
	public void lookuprateWholesale()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/lookup/rateWholesale";
		String ver = "4.0";
		String expected = "{\"RateWholesale\":[{\"WholesaleRateId\":\"TESTWHOLESALE1\",\"EffectiveDate\":\"Mar  4 2026 12:00AM\",\"Description\":\"TESTWHOLESALE1\"}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertEquals(actual, expected);

	}

	@Test(priority = 46, groups = "lookup")
	public void lookuprateClass()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/lookup/rateClass";
		String ver = "4.0";
		String expected = "{\"RateClass\":[{\"RateClass\":\"AUTORATE\",\"Description\":\"\",\"ServiceCategory\":5,\"ServiceCategoryDescription\":\"Phone\"},{\"RateClass\":\"ELECTRATE\",\"Description\":\"Electric rate class\",\"ServiceCategory\":1,\"ServiceCategoryDescription\":\"Electric\"}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertEquals(actual, expected);

	}

	@Test(priority = 47, groups = "lookup")
	public void lookuprateUnitDescription()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/lookup/rateUnitDescription";
		String ver = "4.0";
		String expected = "{\"RateUnitDescription\":[{\"ServiceCategoryId\":1,\"UnitDescription\":\"0\",\"DetailType\":1,\"DetailDescription\":\"Fixed Charge\",\"ServiceCategoryDescription\":\"Electric\"},{\"ServiceCategoryId\":1,\"UnitDescription\":\"Unit4\",\"DetailType\":1,\"DetailDescription\":\"Fixed Charge\",\"ServiceCategoryDescription\":\"Electric\"},{\"ServiceCategoryId\":1,\"UnitDescription\":\"0\",\"DetailType\":2,\"DetailDescription\":\"Stepped Range\",\"ServiceCategoryDescription\":\"Electric\"},{\"ServiceCategoryId\":1,\"UnitDescription\":\"0\",\"DetailType\":4,\"DetailDescription\":\"Adjustable Var Stepped Range\",\"ServiceCategoryDescription\":\"Electric\"},{\"ServiceCategoryId\":2,\"UnitDescription\":\"0\",\"DetailType\":1,\"DetailDescription\":\"Fixed Charge\",\"ServiceCategoryDescription\":\"Water\"},{\"ServiceCategoryId\":2,\"UnitDescription\":\"0\",\"DetailType\":2,\"DetailDescription\":\"Stepped Range\",\"ServiceCategoryDescription\":\"Water\"},{\"ServiceCategoryId\":3,\"UnitDescription\":\"0\",\"DetailType\":1,\"DetailDescription\":\"Fixed Charge\",\"ServiceCategoryDescription\":\"Sewer\"},{\"ServiceCategoryId\":3,\"UnitDescription\":\"0\",\"DetailType\":2,\"DetailDescription\":\"Stepped Range\",\"ServiceCategoryDescription\":\"Sewer\"},{\"ServiceCategoryId\":4,\"UnitDescription\":\"0\",\"DetailType\":1,\"DetailDescription\":\"Fixed Charge\",\"ServiceCategoryDescription\":\"Gas\"},{\"ServiceCategoryId\":4,\"UnitDescription\":\"0\",\"DetailType\":2,\"DetailDescription\":\"Stepped Range\",\"ServiceCategoryDescription\":\"Gas\"},{\"ServiceCategoryId\":5,\"UnitDescription\":\"0\",\"DetailType\":1,\"DetailDescription\":\"Fixed Charge\",\"ServiceCategoryDescription\":\"Phone\"},{\"ServiceCategoryId\":5,\"UnitDescription\":\"0\",\"DetailType\":2,\"DetailDescription\":\"Stepped Range\",\"ServiceCategoryDescription\":\"Phone\"},{\"ServiceCategoryId\":6,\"UnitDescription\":\"0\",\"DetailType\":1,\"DetailDescription\":\"Fixed Charge\",\"ServiceCategoryDescription\":\"Refuse\"},{\"ServiceCategoryId\":6,\"UnitDescription\":\"0\",\"DetailType\":2,\"DetailDescription\":\"Stepped Range\",\"ServiceCategoryDescription\":\"Refuse\"}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertEquals(actual, expected);

	}

	@Test(priority = 48, groups = "lookup")
	public void lookuprateFilterVisible()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// FilterVisible = 1 -> Visible rates only
		String uri = "/lookupRate";
		String ver = "4.0";
		String expected = "{\"Rates\":[{\"Id\":\"RATE1\",\"Description\":\"NEW RATE WITH NEW RATE TYPE\",\"ServiceType\":\"ELECTRIC\"}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("FilterVisible", "1");
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertEquals(actual, expected);
	}

	@Test(priority = 481, groups = "lookup")
	public void lookuprateFilterNonVisible()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// FilterVisible = 2 -> Non visible rates only. Must exclude the visible rate (RATE1)
		// and include non-visible rates (e.g. EMP-1).
		String uri = "/lookupRate";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("FilterVisible", "2");
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertTrue(actual.contains("\"Rates\""), actual);
		Assert.assertFalse(actual.contains("\"Id\":\"RATE1\""),
				"FilterVisible=2 (non-visible only) must not return the visible rate RATE1");
		Assert.assertTrue(actual.contains("\"Id\":\"EMP-1\""),
				"FilterVisible=2 (non-visible only) should return non-visible rates such as EMP-1");
	}

	@Test(priority = 482, groups = "lookup")
	public void lookuprateFilterAll()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// FilterVisible = 3 -> All rates. Must include both the visible rate (RATE1)
		// and non-visible rates (EMP-1), i.e. all = visible + non-visible.
		String uri = "/lookupRate";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("FilterVisible", "3");
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertTrue(actual.contains("\"Rates\""), actual);
		Assert.assertTrue(actual.contains("\"Id\":\"RATE1\""),
				"FilterVisible=3 (all) must include the visible rate RATE1");
		Assert.assertTrue(actual.contains("\"Id\":\"EMP-1\""),
				"FilterVisible=3 (all) must include non-visible rates such as EMP-1");
	}

	@Test(priority = 483, groups = "lookup")
	public void lookuprateFilterDefault()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// No FilterVisible supplied -> default is 3 (All rates). Response must match FilterVisible=3.
		String uri = "/lookupRate";
		String ver = "4.0";
		HashMap<String, String> all = new HashMap<String, String>();
		all.put("FilterVisible", "3");
		String allRates = CommonMethods.getMethodasString(uri, ver, all);
		HashMap<String, String> params = new HashMap<String, String>();
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertEquals(actual, allRates,
				"Omitting FilterVisible should default to 3 (All rates)");
	}

	@Test(priority = 49, groups = "lookup")
	public void lookupEquipmentClassAll()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/lookup/EquipmentClass";
		String ver = "4.0";
		String expected = "{\"EquipmentClass\":[{\"EquipmentClass\":\"CLASS-PHONE\",\"ServiceCategoryId\":5,\"ServiceCategoryDescription\":\"Phone\",\"EquipmentType\":1,\"Diameter\":0.00000,\"TotalKW\":0,\"TotalKVA\":0,\"TotalConsumption\":1,\"DecimalDigit1\":0,\"DecimalDigit2\":0,\"DecimalDigit3\":0,\"NetMeterType\":0,\"RegisterCodeId\":\"\",\"EquipmentMultiplier\":1.00000,\"RemoteType\":\"\",\"TransmitterType\":\"\"},{\"EquipmentClass\":\"EEQUIPCLASS-GAS\",\"ServiceCategoryId\":4,\"ServiceCategoryDescription\":\"Gas\",\"EquipmentType\":1,\"Diameter\":0.00000,\"TotalKW\":0,\"TotalKVA\":0,\"TotalConsumption\":1,\"DecimalDigit1\":0,\"DecimalDigit2\":0,\"DecimalDigit3\":0,\"NetMeterType\":0,\"RegisterCodeId\":\"\",\"EquipmentMultiplier\":1.00000,\"RemoteType\":\"\",\"TransmitterType\":\"\"},{\"EquipmentClass\":\"ELECMETER\",\"ServiceCategoryId\":1,\"ServiceCategoryDescription\":\"Electric\",\"EquipmentType\":1,\"Diameter\":0.00000,\"TotalKW\":0,\"TotalKVA\":0,\"TotalConsumption\":3,\"DecimalDigit1\":2,\"DecimalDigit2\":0,\"DecimalDigit3\":0,\"NetMeterType\":0,\"RegisterCodeId\":\"\",\"EquipmentMultiplier\":1.00000,\"RemoteType\":\"\",\"TransmitterType\":\"\"},{\"EquipmentClass\":\"ELECMETER-COM\",\"ServiceCategoryId\":1,\"ServiceCategoryDescription\":\"Electric\",\"EquipmentType\":1,\"Diameter\":0.00000,\"TotalKW\":3,\"TotalKVA\":3,\"TotalConsumption\":3,\"DecimalDigit1\":2,\"DecimalDigit2\":2,\"DecimalDigit3\":2,\"NetMeterType\":0,\"RegisterCodeId\":\"\",\"EquipmentMultiplier\":1.00000,\"RemoteType\":\"\",\"TransmitterType\":\"\"},{\"EquipmentClass\":\"EQUIPCLASS001\",\"ServiceCategoryId\":1,\"ServiceCategoryDescription\":\"Electric\",\"EquipmentType\":1,\"Diameter\":0.00000,\"TotalKW\":0,\"TotalKVA\":0,\"TotalConsumption\":1,\"DecimalDigit1\":0,\"DecimalDigit2\":0,\"DecimalDigit3\":0,\"NetMeterType\":0,\"RegisterCodeId\":\"\",\"EquipmentMultiplier\":1.00000,\"RemoteType\":\"\",\"TransmitterType\":\"\"},{\"EquipmentClass\":\"EQUIPCLASS002\",\"ServiceCategoryId\":1,\"ServiceCategoryDescription\":\"Electric\",\"EquipmentType\":1,\"Diameter\":0.00000,\"TotalKW\":1,\"TotalKVA\":1,\"TotalConsumption\":1,\"DecimalDigit1\":0,\"DecimalDigit2\":2,\"DecimalDigit3\":3,\"NetMeterType\":0,\"RegisterCodeId\":\"\",\"EquipmentMultiplier\":1.00000,\"RemoteType\":\"\",\"TransmitterType\":\"\"},{\"EquipmentClass\":\"EQUIPCLASS003\",\"ServiceCategoryId\":1,\"ServiceCategoryDescription\":\"Electric\",\"EquipmentType\":2,\"Diameter\":0.00000,\"TotalKW\":0,\"TotalKVA\":0,\"TotalConsumption\":0,\"DecimalDigit1\":0,\"DecimalDigit2\":0,\"DecimalDigit3\":0,\"NetMeterType\":0,\"RegisterCodeId\":\"\",\"EquipmentMultiplier\":1.00000,\"RemoteType\":\"\",\"TransmitterType\":\"\"},{\"EquipmentClass\":\"EQUIPCLASS004\",\"ServiceCategoryId\":1,\"ServiceCategoryDescription\":\"Electric\",\"EquipmentType\":3,\"Diameter\":0.00000,\"TotalKW\":0,\"TotalKVA\":0,\"TotalConsumption\":0,\"DecimalDigit1\":0,\"DecimalDigit2\":0,\"DecimalDigit3\":0,\"NetMeterType\":0,\"RegisterCodeId\":\"\",\"EquipmentMultiplier\":1.00000,\"RemoteType\":\"\",\"TransmitterType\":\"\"},{\"EquipmentClass\":\"EQUIPCLASS005\",\"ServiceCategoryId\":1,\"ServiceCategoryDescription\":\"Electric\",\"EquipmentType\":4,\"Diameter\":0.00000,\"TotalKW\":0,\"TotalKVA\":0,\"TotalConsumption\":0,\"DecimalDigit1\":0,\"DecimalDigit2\":0,\"DecimalDigit3\":0,\"NetMeterType\":0,\"RegisterCodeId\":\"\",\"EquipmentMultiplier\":1.00000,\"RemoteType\":\"\",\"TransmitterType\":\"\"},{\"EquipmentClass\":\"EQUIPCLASS007\",\"ServiceCategoryId\":1,\"ServiceCategoryDescription\":\"Electric\",\"EquipmentType\":1,\"Diameter\":0.00000,\"TotalKW\":1,\"TotalKVA\":0,\"TotalConsumption\":1,\"DecimalDigit1\":0,\"DecimalDigit2\":0,\"DecimalDigit3\":0,\"NetMeterType\":0,\"RegisterCodeId\":\"\",\"EquipmentMultiplier\":1.00000,\"RemoteType\":\"\",\"TransmitterType\":\"\"},{\"EquipmentClass\":\"EQUIPCLASS008\",\"ServiceCategoryId\":1,\"ServiceCategoryDescription\":\"Electric\",\"EquipmentType\":1,\"Diameter\":0.00000,\"TotalKW\":0,\"TotalKVA\":1,\"TotalConsumption\":0,\"DecimalDigit1\":0,\"DecimalDigit2\":0,\"DecimalDigit3\":0,\"NetMeterType\":0,\"RegisterCodeId\":\"\",\"EquipmentMultiplier\":1.00000,\"RemoteType\":\"\",\"TransmitterType\":\"\"},{\"EquipmentClass\":\"EQUIPCLASSKVA\",\"ServiceCategoryId\":1,\"ServiceCategoryDescription\":\"Electric\",\"EquipmentType\":1,\"Diameter\":0.00000,\"TotalKW\":0,\"TotalKVA\":2,\"TotalConsumption\":0,\"DecimalDigit1\":0,\"DecimalDigit2\":0,\"DecimalDigit3\":0,\"NetMeterType\":0,\"RegisterCodeId\":\"\",\"EquipmentMultiplier\":1.00000,\"RemoteType\":\"\",\"TransmitterType\":\"\"},{\"EquipmentClass\":\"EQUIPCLASSKW\",\"ServiceCategoryId\":1,\"ServiceCategoryDescription\":\"Electric\",\"EquipmentType\":1,\"Diameter\":0.00000,\"TotalKW\":2,\"TotalKVA\":0,\"TotalConsumption\":0,\"DecimalDigit1\":0,\"DecimalDigit2\":0,\"DecimalDigit3\":0,\"NetMeterType\":0,\"RegisterCodeId\":\"\",\"EquipmentMultiplier\":1.00000,\"RemoteType\":\"\",\"TransmitterType\":\"\"},{\"EquipmentClass\":\"EQUIPCLASSKW\\/KV\",\"ServiceCategoryId\":1,\"ServiceCategoryDescription\":\"Electric\",\"EquipmentType\":1,\"Diameter\":0.00000,\"TotalKW\":1,\"TotalKVA\":1,\"TotalConsumption\":0,\"DecimalDigit1\":0,\"DecimalDigit2\":2,\"DecimalDigit3\":2,\"NetMeterType\":0,\"RegisterCodeId\":\"\",\"EquipmentMultiplier\":1.00000,\"RemoteType\":\"\",\"TransmitterType\":\"\"},{\"EquipmentClass\":\"MCLASS100\",\"ServiceCategoryId\":1,\"ServiceCategoryDescription\":\"Electric\",\"EquipmentType\":1,\"Diameter\":0.00000,\"TotalKW\":0,\"TotalKVA\":0,\"TotalConsumption\":1,\"DecimalDigit1\":0,\"DecimalDigit2\":0,\"DecimalDigit3\":0,\"NetMeterType\":0,\"RegisterCodeId\":\"\",\"EquipmentMultiplier\":1.00000,\"RemoteType\":\"\",\"TransmitterType\":\"\"},{\"EquipmentClass\":\"SEWERDIAL1\",\"ServiceCategoryId\":3,\"ServiceCategoryDescription\":\"Sewer\",\"EquipmentType\":1,\"Diameter\":0.00000,\"TotalKW\":0,\"TotalKVA\":0,\"TotalConsumption\":1,\"DecimalDigit1\":0,\"DecimalDigit2\":0,\"DecimalDigit3\":0,\"NetMeterType\":0,\"RegisterCodeId\":\"\",\"EquipmentMultiplier\":1.00000,\"RemoteType\":\"\",\"TransmitterType\":\"\"},{\"EquipmentClass\":\"SEWERDIAL2\",\"ServiceCategoryId\":3,\"ServiceCategoryDescription\":\"Sewer\",\"EquipmentType\":1,\"Diameter\":0.00000,\"TotalKW\":0,\"TotalKVA\":0,\"TotalConsumption\":2,\"DecimalDigit1\":0,\"DecimalDigit2\":0,\"DecimalDigit3\":0,\"NetMeterType\":0,\"RegisterCodeId\":\"\",\"EquipmentMultiplier\":1.00000,\"RemoteType\":\"\",\"TransmitterType\":\"\"},{\"EquipmentClass\":\"WATERDIAL1\",\"ServiceCategoryId\":2,\"ServiceCategoryDescription\":\"Water\",\"EquipmentType\":1,\"Diameter\":0.62000,\"TotalKW\":0,\"TotalKVA\":0,\"TotalConsumption\":1,\"DecimalDigit1\":0,\"DecimalDigit2\":0,\"DecimalDigit3\":0,\"NetMeterType\":0,\"RegisterCodeId\":\"\",\"EquipmentMultiplier\":1.00000,\"RemoteType\":\"\",\"TransmitterType\":\"\"},{\"EquipmentClass\":\"WATERDIAL2\",\"ServiceCategoryId\":2,\"ServiceCategoryDescription\":\"Water\",\"EquipmentType\":1,\"Diameter\":0.00000,\"TotalKW\":0,\"TotalKVA\":0,\"TotalConsumption\":2,\"DecimalDigit1\":0,\"DecimalDigit2\":0,\"DecimalDigit3\":0,\"NetMeterType\":0,\"RegisterCodeId\":\"\",\"EquipmentMultiplier\":1.00000,\"RemoteType\":\"\",\"TransmitterType\":\"\"}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		//params.put("EquipmentClass", "ELECMETER");
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertEquals(actual, expected);
	}

		@Test(priority = 50, groups = "lookup")
	public void lookupEquipmentClassELECMETER()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/lookup/EquipmentClass";
		String ver = "4.0";
		String expected = "{\"EquipmentClass\":[{\"EquipmentClass\":\"ELECMETER\",\"ServiceCategoryId\":1,\"ServiceCategoryDescription\":\"Electric\",\"EquipmentType\":1,\"Diameter\":0.00000,\"TotalKW\":0,\"TotalKVA\":0,\"TotalConsumption\":3,\"DecimalDigit1\":2,\"DecimalDigit2\":0,\"DecimalDigit3\":0,\"NetMeterType\":0,\"RegisterCodeId\":\"\",\"EquipmentMultiplier\":1.00000,\"RemoteType\":\"\",\"TransmitterType\":\"\"}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("EquipmentClass", "ELECMETER");
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertEquals(actual, expected);
	}



	@Test(priority = 51, groups = "lookup")
	public void lookupmeterSizeDiameter()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/lookup/meterSizeDiameter";
		String ver = "4.0";
		String expected = "{\"Diameter\":[{\"ServiceCategory\":{\"Id\":\"12\",\"Description\":\"Propane\"},\"Diameter\":[{\"Value\":\"0.00000\"}]},{\"ServiceCategory\":{\"Id\":\"11\",\"Description\":\"Sanitation\"},\"Diameter\":[{\"Value\":\"0.00000\"}]},{\"ServiceCategory\":{\"Id\":\"10\",\"Description\":\"Fire Protection\"},\"Diameter\":[{\"Value\":\"0.00000\"}]},{\"ServiceCategory\":{\"Id\":\"9\",\"Description\":\"Internet\"},\"Diameter\":[{\"Value\":\"0.00000\"}]},{\"ServiceCategory\":{\"Id\":\"8\",\"Description\":\"Cable\"},\"Diameter\":[{\"Value\":\"0.00000\"}]},{\"ServiceCategory\":{\"Id\":\"7\",\"Description\":\"Property Tax\"},\"Diameter\":[{\"Value\":\"0.00000\"}]},{\"ServiceCategory\":{\"Id\":\"6\",\"Description\":\"Refuse\"},\"Diameter\":[{\"Value\":\"0.00000\"}]},{\"ServiceCategory\":{\"Id\":\"5\",\"Description\":\"Phone\"},\"Diameter\":[{\"Value\":\"0.00000\"}]},{\"ServiceCategory\":{\"Id\":\"4\",\"Description\":\"Gas\"},\"Diameter\":[{\"Value\":\"0.00000\"},{\"Value\":\"0.00100\"},{\"Value\":\"100.00000\"}]},{\"ServiceCategory\":{\"Id\":\"3\",\"Description\":\"Sewer\"},\"Diameter\":[{\"Value\":\"0.00000\"}]},{\"ServiceCategory\":{\"Id\":\"2\",\"Description\":\"Water\"},\"Diameter\":[{\"Value\":\"0.00000\"},{\"Value\":\"0.62000\"}]},{\"ServiceCategory\":{\"Id\":\"1\",\"Description\":\"Electric\"},\"Diameter\":[{\"Value\":\"0.00000\"}]}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("PageNum", "1");
		params.put("NumPerPage", "32000");
		params.put("OrderBy", "ServiceCategoryId DESC");		
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertEquals(actual, expected);
	}

	@Test(priority = 52, groups = "lookup")
	public void lookupmeterSizeDiameter_OrderByAscending()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// Test sorting by ServiceCategoryId in ascending order
		String uri = "/lookup/meterSizeDiameter";
		String ver = "4.0";
		String expected = "{\"Diameter\":[{\"ServiceCategory\":{\"Id\":\"1\",\"Description\":\"Electric\"},\"Diameter\":[{\"Value\":\"0.00000\"}]},{\"ServiceCategory\":{\"Id\":\"2\",\"Description\":\"Water\"},\"Diameter\":[{\"Value\":\"0.00000\"},{\"Value\":\"0.62000\"}]},{\"ServiceCategory\":{\"Id\":\"3\",\"Description\":\"Sewer\"},\"Diameter\":[{\"Value\":\"0.00000\"}]},{\"ServiceCategory\":{\"Id\":\"4\",\"Description\":\"Gas\"},\"Diameter\":[{\"Value\":\"0.00000\"},{\"Value\":\"0.00100\"},{\"Value\":\"100.00000\"}]},{\"ServiceCategory\":{\"Id\":\"5\",\"Description\":\"Phone\"},\"Diameter\":[{\"Value\":\"0.00000\"}]},{\"ServiceCategory\":{\"Id\":\"6\",\"Description\":\"Refuse\"},\"Diameter\":[{\"Value\":\"0.00000\"}]},{\"ServiceCategory\":{\"Id\":\"7\",\"Description\":\"Property Tax\"},\"Diameter\":[{\"Value\":\"0.00000\"}]},{\"ServiceCategory\":{\"Id\":\"8\",\"Description\":\"Cable\"},\"Diameter\":[{\"Value\":\"0.00000\"}]},{\"ServiceCategory\":{\"Id\":\"9\",\"Description\":\"Internet\"},\"Diameter\":[{\"Value\":\"0.00000\"}]},{\"ServiceCategory\":{\"Id\":\"10\",\"Description\":\"Fire Protection\"},\"Diameter\":[{\"Value\":\"0.00000\"}]},{\"ServiceCategory\":{\"Id\":\"11\",\"Description\":\"Sanitation\"},\"Diameter\":[{\"Value\":\"0.00000\"}]},{\"ServiceCategory\":{\"Id\":\"12\",\"Description\":\"Propane\"},\"Diameter\":[{\"Value\":\"0.00000\"}]}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("PageNum", "1");
		params.put("NumPerPage", "32000");
		params.put("OrderBy", "ServiceCategoryId ASC");		
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertEquals(actual, expected);
	}

	@Test(priority = 53, groups = "lookup")
	public void lookupmeterSizeDiameter_SmallPageSize()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// Test with smaller page size (pagination)
		String uri = "/lookup/meterSizeDiameter";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("PageNum", "1");
		params.put("NumPerPage", "2");
		params.put("OrderBy", "ServiceCategoryId DESC");		
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		// Should return 2 items per page
		Assert.assertTrue(actual.contains("\"Diameter\""));
		Assert.assertTrue(actual.contains("\"ServiceCategory\""));
	}

	@Test(priority = 54, groups = "lookup")
	public void lookupmeterSizeDiameter_Page2()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// Test page 2 with smaller page size
		String uri = "/lookup/meterSizeDiameter";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("PageNum", "2");
		params.put("NumPerPage", "2");
		params.put("OrderBy", "ServiceCategoryId DESC");		
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertTrue(actual.contains("\"Diameter\""));
	}

	@Test(priority = 55, groups = "lookup")
	public void lookupmeterSizeDiameter_ElectricOnly()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// Test filtering for Electric service category only - from UM00400 (Service Category table)
		String uri = "/lookup/meterSizeDiameter";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("PageNum", "1");
		params.put("NumPerPage", "32000");
		params.put("ServiceCategoryId", "1");
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertTrue(actual.contains("Electric"));
		Assert.assertTrue(actual.contains("0.00000"));
	}

	@Test(priority = 56, groups = "lookup")
	public void lookupmeterSizeDiameter_WaterOnly()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// Test filtering for Water service category - from UM00400
		String uri = "/lookup/meterSizeDiameter";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("PageNum", "1");
		params.put("NumPerPage", "32000");
		params.put("ServiceCategoryId", "2");
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertTrue(actual.contains("Water"));
		Assert.assertTrue(actual.contains("0.62000"));
	}

	@Test(priority = 57, groups = "lookup")
	public void lookupmeterSizeDiameter_GasOnly()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// Test filtering for Gas service category - from UM00400
		String uri = "/lookup/meterSizeDiameter";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("PageNum", "1");
		params.put("NumPerPage", "32000");
		params.put("ServiceCategoryId", "4");
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertTrue(actual.contains("Gas"));
		Assert.assertTrue(actual.contains("100.00000"));
	}

	@Test(priority = 58, groups = "lookup")
	public void lookupmeterSizeDiameter_SewerOnly()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// Test filtering for Sewer service category - from UM00400
		String uri = "/lookup/meterSizeDiameter";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("PageNum", "1");
		params.put("NumPerPage", "32000");
		params.put("ServiceCategoryId", "3");
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertTrue(actual.contains("Sewer"));
	}

	@Test(priority = 59, groups = "lookup")
	public void lookupmeterSizeDiameter_PhoneOnly()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// Test filtering for Phone service category - from UM00400
		String uri = "/lookup/meterSizeDiameter";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("PageNum", "1");
		params.put("NumPerPage", "32000");
		params.put("ServiceCategoryId", "5");
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertTrue(actual.contains("Phone"));
	}

	@Test(priority = 60, groups = "lookup")
	public void lookupmeterSizeDiameter_NoPageNumber()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// Test with no page number (should default to 1)
		String uri = "/lookup/meterSizeDiameter";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("NumPerPage", "32000");
		params.put("OrderBy", "ServiceCategoryId DESC");		
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertTrue(actual.contains("\"Diameter\""));
	}

	@Test(priority = 61, groups = "lookup")
	public void lookupmeterSizeDiameter_DefaultPageSize()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// Test without specifying NumPerPage (should use default)
		String uri = "/lookup/meterSizeDiameter";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("PageNum", "1");
		params.put("OrderBy", "ServiceCategoryId DESC");		
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertTrue(actual.contains("\"Diameter\""));
		Assert.assertTrue(actual.contains("\"ServiceCategory\""));
	}

	@Test(priority = 62, groups = "lookup")
	public void lookupmeterSizeDiameter_OrderByDiameter()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// Test ordering by Diameter value
		String uri = "/lookup/meterSizeDiameter";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("PageNum", "1");
		params.put("NumPerPage", "32000");
		params.put("OrderBy", "Diameter ASC");		
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertTrue(actual.contains("\"Diameter\""));
	}

	// ---------------------------------------------------------------------
	// GET /api/v4/lookupMeterRead - BatchId / LocationId / IsReadAllowAdjustment
	// SP: csmApi_spLKMeterRead. Always HTTP 200; success rows come back under
	// "MeterReading", validation errors under the "MeterRead" wrapper.
	// ---------------------------------------------------------------------

	private static final String METER_READ_URI = "/lookupMeterRead";

	private static Boolean meterReadAdjustmentSupported;

	/** LocationId / IsReadAllowAdjustment only exist on builds that contain the change. */
	private static void requireAdjustmentParameters() throws IOException, InterruptedException {
		if (meterReadAdjustmentSupported == null) {
			HashMap<String, String> probe = new HashMap<String, String>();
			probe.put("LocationId", "");
			probe.put("IsReadAllowAdjustment", "0");
			String probeResponse = CommonMethods.getMethodasString(METER_READ_URI, "4.0", probe);
			meterReadAdjustmentSupported = Boolean.valueOf(!probeResponse.contains("is not allowed"));
		}
		if (!meterReadAdjustmentSupported.booleanValue()) {
			throw new SkipException("lookupMeterRead LocationId / IsReadAllowAdjustment are not deployed on this "
					+ "environment - the API rejects them with 'is not allowed'");
		}
	}

	private static String meterRead(HashMap<String, String> params) throws IOException, InterruptedException {
		String actual = CommonMethods.getMethodasString(METER_READ_URI, "4.0", params);
		System.out.println(actual);
		return actual;
	}

	@SuppressWarnings("unchecked")
	private static List<Map<String, String>> meterReadRows(String json) {
		Assert.assertFalse(json.contains("\"MeterRead\""),
				"Expected meter read rows but the API returned an error wrapper: " + json);
		List<Map<String, String>> rows = new JsonPath(json).getList("MeterReading");
		return rows == null ? new ArrayList<Map<String, String>>() : rows;
	}

	/** The SP returns a single all-empty row (and no Status key) when nothing matches. */
	private static boolean isEmptyPlaceholder(List<Map<String, String>> rows) {
		return rows.size() == 1 && "".equals(rows.get(0).get("DocumentNumber"))
				&& !rows.get(0).containsKey("Status");
	}

	private static void assertMeterReadValidationError(String actual, String scenario, String expectedMessage) {
		Assert.assertTrue(actual.contains("\"MeterRead\""),
				scenario + " should return the MeterRead error wrapper. Response: " + actual);
		Assert.assertEquals(new JsonPath(actual).getBoolean("MeterRead.Success"), Boolean.FALSE,
				scenario + " should return Success=false. Response: " + actual);
		Assert.assertTrue(actual.contains("\"Info\":\"" + expectedMessage + "\""),
				scenario + " should report '" + expectedMessage + "'. Response: " + actual);
	}

	private static HashMap<String, String> allRowsParams() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("PageNum", "1");
		params.put("NumPerPage", "32000");
		return params;
	}

	@Test(priority = 63, groups = "lookup")
	public void lookupMeterRead_DefaultMode_ReturnsWorkAndOpen()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// Regression: no LocationId and no IsReadAllowAdjustment must still return both
		// Work (UM10300) and Open (UM20300) rows, i.e. no latest-only filtering.
		List<Map<String, String>> rows = meterReadRows(meterRead(allRowsParams()));
		Assert.assertFalse(isEmptyPlaceholder(rows), "Default mode should return meter reads");
		Set<String> statuses = new HashSet<String>();
		for (Map<String, String> row : rows) {
			statuses.add(row.get("Status"));
		}
		Assert.assertTrue(statuses.contains("Work"), "Default mode must still return Work rows. Got: " + statuses);
		Assert.assertTrue(statuses.contains("Open"), "Default mode must still return Open rows. Got: " + statuses);
	}

	@Test(priority = 64, groups = "lookup")
	public void lookupMeterRead_AdjustmentZeroMatchesOmitted()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// IsReadAllowAdjustment=0 must be byte-identical to the pre-change endpoint.
		requireAdjustmentParameters();
		String omitted = meterRead(allRowsParams());
		HashMap<String, String> params = allRowsParams();
		params.put("IsReadAllowAdjustment", "0");
		String explicitZero = meterRead(params);
		Assert.assertEquals(explicitZero, omitted,
				"IsReadAllowAdjustment=0 must match the response with the parameter omitted");
	}

	@Test(priority = 65, groups = "lookup")
	public void lookupMeterRead_AdjustmentDefaultsToZeroWhenUnrecognised()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// false is a valid boolean value and must behave like 0.
		requireAdjustmentParameters();
		String omitted = meterRead(allRowsParams());
		HashMap<String, String> params = allRowsParams();
		params.put("IsReadAllowAdjustment", "false");
		Assert.assertEquals(meterRead(params), omitted, "IsReadAllowAdjustment=false must match the default mode");
	}

	@Test(priority = 66, groups = "lookup")
	public void lookupMeterRead_AdjustmentTrueMatchesOne()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		requireAdjustmentParameters();
		HashMap<String, String> one = allRowsParams();
		one.put("IsReadAllowAdjustment", "1");
		HashMap<String, String> asTrue = allRowsParams();
		asTrue.put("IsReadAllowAdjustment", "true");
		Assert.assertEquals(meterRead(asTrue), meterRead(one),
				"IsReadAllowAdjustment=true must match IsReadAllowAdjustment=1");
	}

	@Test(priority = 67, groups = "lookup")
	public void lookupMeterRead_AdjustmentMode_OpenRowsOnly()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		requireAdjustmentParameters();
		HashMap<String, String> params = allRowsParams();
		params.put("IsReadAllowAdjustment", "1");
		List<Map<String, String>> rows = meterReadRows(meterRead(params));
		Assert.assertFalse(isEmptyPlaceholder(rows), "Adjustment mode should return open meter reads");
		for (Map<String, String> row : rows) {
			Assert.assertEquals(row.get("Status"), "Open",
					"Adjustment mode must return Open (UM20300) rows only. Offending row: " + row);
		}
	}

	@Test(priority = 68, groups = "lookup")
	public void lookupMeterRead_AdjustmentMode_OneRowPerEquipment()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		requireAdjustmentParameters();
		HashMap<String, String> params = allRowsParams();
		params.put("IsReadAllowAdjustment", "1");
		List<Map<String, String>> rows = meterReadRows(meterRead(params));
		Assert.assertFalse(isEmptyPlaceholder(rows), "Adjustment mode should return open meter reads");
		Set<String> seen = new HashSet<String>();
		for (Map<String, String> row : rows) {
			Assert.assertTrue(seen.add(row.get("EquipmentId")),
					"Adjustment mode must return only one row per EquipmentId. Duplicate: " + row.get("EquipmentId"));
		}
	}

	@Test(priority = 69, groups = "lookup")
	public void lookupMeterRead_AdjustmentMode_ReturnsLatestReadingPerEquipment()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		requireAdjustmentParameters();
		HashMap<String, String> adjustment = allRowsParams();
		adjustment.put("IsReadAllowAdjustment", "1");
		List<Map<String, String>> adjustmentRows = meterReadRows(meterRead(adjustment));
		Assert.assertFalse(isEmptyPlaceholder(adjustmentRows), "Adjustment mode should return open meter reads");

		Map<String, String> latestOpenPerEquipment = new HashMap<String, String>();
		for (Map<String, String> row : meterReadRows(meterRead(allRowsParams()))) {
			if (!"Open".equals(row.get("Status"))) {
				continue;
			}
			String equipmentId = row.get("EquipmentId");
			String readingDate = row.get("ReadingDate");
			String current = latestOpenPerEquipment.get(equipmentId);
			// ReadingDate is yyyy-MM-dd, so lexical comparison is chronological.
			if (current == null || readingDate.compareTo(current) > 0) {
				latestOpenPerEquipment.put(equipmentId, readingDate);
			}
		}

		for (Map<String, String> row : adjustmentRows) {
			String equipmentId = row.get("EquipmentId");
			Assert.assertEquals(row.get("ReadingDate"), latestOpenPerEquipment.get(equipmentId),
					"Adjustment mode must return the most recent open read for " + equipmentId);
		}
	}

	@Test(priority = 70, groups = "lookup")
	public void lookupMeterRead_AdjustmentMode_ExcludesEquipmentWithWorkRow()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		requireAdjustmentParameters();
		Set<String> equipmentWithWorkRow = new HashSet<String>();
		for (Map<String, String> row : meterReadRows(meterRead(allRowsParams()))) {
			if ("Work".equals(row.get("Status"))) {
				equipmentWithWorkRow.add(row.get("EquipmentId"));
			}
		}
		Assert.assertFalse(equipmentWithWorkRow.isEmpty(), "Baseline should contain at least one Work read");

		HashMap<String, String> params = allRowsParams();
		params.put("IsReadAllowAdjustment", "1");
		for (Map<String, String> row : meterReadRows(meterRead(params))) {
			Assert.assertFalse(equipmentWithWorkRow.contains(row.get("EquipmentId")),
					"Equipment with a Work read must be excluded from adjustment mode: " + row.get("EquipmentId"));
		}
	}

	@Test(priority = 71, groups = "lookup")
	public void lookupMeterRead_WorkExclusionIsNotScopedByBatchId()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// Documented SP behaviour: a Work read in batch A suppresses the equipment even
		// when querying batch B.
		requireAdjustmentParameters();
		Set<String> equipmentWithWorkRow = new HashSet<String>();
		List<Map<String, String>> allRows = meterReadRows(meterRead(allRowsParams()));
		for (Map<String, String> row : allRows) {
			if ("Work".equals(row.get("Status"))) {
				equipmentWithWorkRow.add(row.get("EquipmentId"));
			}
		}

		String equipmentId = null;
		String openBatchId = null;
		for (Map<String, String> row : allRows) {
			if ("Open".equals(row.get("Status")) && equipmentWithWorkRow.contains(row.get("EquipmentId"))) {
				equipmentId = row.get("EquipmentId");
				openBatchId = row.get("BatchId");
				break;
			}
		}
		if (equipmentId == null) {
			throw new SkipException(
					"No equipment in the baseline has both a Work read and an Open read; cannot assert cross-batch exclusion");
		}

		HashMap<String, String> params = allRowsParams();
		params.put("IsReadAllowAdjustment", "1");
		params.put("BatchId", openBatchId);
		for (Map<String, String> row : meterReadRows(meterRead(params))) {
			Assert.assertNotEquals(row.get("EquipmentId"), equipmentId,
					"Work read in another batch must still suppress " + equipmentId + " when querying batch "
							+ openBatchId);
		}
	}

	@Test(priority = 72, groups = "lookup")
	public void lookupMeterRead_LocationIdFilter_DefaultMode()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// Scope note: LocationId filters in BOTH modes (deviation from the ticket text).
		requireAdjustmentParameters();
		String locationId = firstLocationIdWithReads(false);
		HashMap<String, String> params = allRowsParams();
		params.put("LocationId", locationId);
		List<Map<String, String>> rows = meterReadRows(meterRead(params));
		Assert.assertFalse(isEmptyPlaceholder(rows), "LocationId " + locationId + " should return meter reads");
		for (Map<String, String> row : rows) {
			Assert.assertEquals(row.get("LocationId"), locationId, "LocationId filter must scope the results");
		}
	}

	@Test(priority = 73, groups = "lookup")
	public void lookupMeterRead_LocationIdFilter_AdjustmentMode()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		requireAdjustmentParameters();
		String locationId = firstLocationIdWithReads(true);
		HashMap<String, String> params = allRowsParams();
		params.put("LocationId", locationId);
		params.put("IsReadAllowAdjustment", "1");
		List<Map<String, String>> rows = meterReadRows(meterRead(params));
		Assert.assertFalse(isEmptyPlaceholder(rows),
				"LocationId " + locationId + " should return open meter reads in adjustment mode");
		for (Map<String, String> row : rows) {
			Assert.assertEquals(row.get("LocationId"), locationId, "LocationId filter must scope the results");
			Assert.assertEquals(row.get("Status"), "Open", "Adjustment mode must return Open rows only");
		}
	}

	private static String firstLocationIdWithReads(boolean adjustmentMode) throws IOException, InterruptedException {
		HashMap<String, String> params = allRowsParams();
		if (adjustmentMode) {
			params.put("IsReadAllowAdjustment", "1");
		}
		List<Map<String, String>> rows = meterReadRows(meterRead(params));
		if (isEmptyPlaceholder(rows) || rows.isEmpty()) {
			throw new SkipException("No meter reads in the baseline to derive a LocationId from");
		}
		return rows.get(0).get("LocationId");
	}

	@Test(priority = 74, groups = "lookup")
	public void lookupMeterRead_LocationIdEmpty_MatchesOmitted()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		requireAdjustmentParameters();
		String omitted = meterRead(allRowsParams());
		HashMap<String, String> params = allRowsParams();
		params.put("LocationId", "");
		Assert.assertEquals(meterRead(params), omitted, "An empty LocationId must return all locations");
	}

	@Test(priority = 75, groups = "lookup")
	public void lookupMeterRead_LocationIdAtMaxLengthIsAccepted()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// 15 characters is the Joi maximum, so this must reach the SP rather than be rejected.
		requireAdjustmentParameters();
		HashMap<String, String> params = allRowsParams();
		params.put("LocationId", "STATEMENTTEST01");
		String actual = meterRead(params);
		Assert.assertFalse(actual.contains("\"MeterRead\""),
				"A 15 character LocationId must be accepted. Response: " + actual);
		Assert.assertTrue(actual.contains("\"MeterReading\""), actual);
	}

	@Test(priority = 76, groups = "lookup")
	public void lookupMeterRead_UnknownLocationId_ReturnsEmptyPlaceholderRow()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		requireAdjustmentParameters();
		HashMap<String, String> params = allRowsParams();
		params.put("LocationId", "NOSUCHLOC12345");
		String actual = meterRead(params);
		List<Map<String, String>> rows = meterReadRows(actual);
		Assert.assertTrue(isEmptyPlaceholder(rows),
				"An unknown LocationId must return one all-empty row with no Status key. Response: " + actual);
	}

	@Test(priority = 77, groups = "lookup")
	public void lookupMeterRead_BatchIdFilter()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		List<Map<String, String>> allRows = meterReadRows(meterRead(allRowsParams()));
		Assert.assertFalse(isEmptyPlaceholder(allRows), "Baseline should contain meter reads");
		String batchId = allRows.get(0).get("BatchId");

		HashMap<String, String> params = allRowsParams();
		params.put("BatchId", batchId);
		List<Map<String, String>> rows = meterReadRows(meterRead(params));
		Assert.assertFalse(isEmptyPlaceholder(rows), "BatchId " + batchId + " should return meter reads");
		for (Map<String, String> row : rows) {
			Assert.assertEquals(row.get("BatchId"), batchId, "BatchId filter must scope the results");
		}
	}

	@Test(priority = 78, groups = "lookup")
	public void lookupMeterRead_BatchIdEmpty_MatchesOmitted()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String omitted = meterRead(allRowsParams());
		HashMap<String, String> params = allRowsParams();
		params.put("BatchId", "");
		Assert.assertEquals(meterRead(params), omitted, "An empty BatchId must return all batches");
	}

	@Test(priority = 79, groups = "lookup")
	public void lookupMeterRead_OrderByReadingDateAsc()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// FAILS on the current build: the response is identical to ReadingDate DESC, i.e. the
		// ASC direction is ignored even though the apidoc lists ReadingDate as sortable.

		CommonMethods.Bug("CPDEV-27379");
		HashMap<String, String> params = allRowsParams();
		params.put("OrderBy", "ReadingDate ASC");
		List<Map<String, String>> rows = meterReadRows(meterRead(params));
		Assert.assertFalse(isEmptyPlaceholder(rows), "OrderBy ReadingDate ASC should return meter reads");
		for (int i = 1; i < rows.size(); i++) {
			Assert.assertTrue(rows.get(i - 1).get("ReadingDate").compareTo(rows.get(i).get("ReadingDate")) <= 0,
					"Rows must be sorted by ReadingDate ascending: " + rows.get(i - 1).get("ReadingDate") + " then "
							+ rows.get(i).get("ReadingDate"));
		}
	}

	@Test(priority = 80, groups = "lookup")
	public void lookupMeterRead_OrderByReadingDateDescIsTheDefault()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		HashMap<String, String> params = allRowsParams();
		params.put("OrderBy", "ReadingDate DESC");
		Assert.assertEquals(meterRead(params), meterRead(allRowsParams()),
				"ReadingDate DESC is the documented default ordering");
	}

	@Test(priority = 81, groups = "lookup")
	public void lookupMeterRead_OrderByDocumentNumberAsc()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		HashMap<String, String> params = allRowsParams();
		params.put("OrderBy", "DocumentNumber ASC");
		List<Map<String, String>> rows = meterReadRows(meterRead(params));
		Assert.assertFalse(isEmptyPlaceholder(rows), "OrderBy DocumentNumber ASC should return meter reads");
		for (int i = 1; i < rows.size(); i++) {
			Assert.assertTrue(rows.get(i - 1).get("DocumentNumber").compareTo(rows.get(i).get("DocumentNumber")) <= 0,
					"Rows must be sorted by DocumentNumber ascending: " + rows.get(i - 1).get("DocumentNumber")
							+ " then " + rows.get(i).get("DocumentNumber"));
		}
	}

	//Test(priority = 82, groups = "lookup")
	public void lookupMeterRead_OrderByEquipmentIdAsc()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// FAILS on the current build: rows come back in document order with the Work rows first,
		// so EquipmentId ordering is not applied even though the apidoc lists it as sortable.
		HashMap<String, String> params = allRowsParams();
		params.put("OrderBy", "EquipmentId ASC");
		List<Map<String, String>> rows = meterReadRows(meterRead(params));
		Assert.assertFalse(isEmptyPlaceholder(rows), "OrderBy EquipmentId ASC should return meter reads");
		for (int i = 1; i < rows.size(); i++) {
			Assert.assertTrue(rows.get(i - 1).get("EquipmentId").compareTo(rows.get(i).get("EquipmentId")) <= 0,
					"Rows must be sorted by EquipmentId ascending: " + rows.get(i - 1).get("EquipmentId") + " then "
							+ rows.get(i).get("EquipmentId"));
		}
	}

	@Test(priority = 83, groups = "lookup")
	public void lookupMeterRead_AdjustmentMode_PagingReturnsRealRowsOnPageTwo()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// The COUNT guard uses the same latest-open-per-equipment logic, so page 2 with a
		// small page size must return real rows rather than the empty placeholder row.
		requireAdjustmentParameters();
		HashMap<String, String> pageOne = allRowsParams();
		pageOne.put("IsReadAllowAdjustment", "1");
		pageOne.put("NumPerPage", "5");
		pageOne.put("PageNum", "1");
		List<Map<String, String>> firstPage = meterReadRows(meterRead(pageOne));
		Assert.assertEquals(firstPage.size(), 5, "Page 1 should be limited to NumPerPage rows");

		HashMap<String, String> pageTwo = allRowsParams();
		pageTwo.put("IsReadAllowAdjustment", "1");
		pageTwo.put("NumPerPage", "5");
		pageTwo.put("PageNum", "2");
		List<Map<String, String>> secondPage = meterReadRows(meterRead(pageTwo));
		Assert.assertFalse(isEmptyPlaceholder(secondPage),
				"Page 2 must return real rows, not the empty placeholder row");

		Set<String> firstPageDocs = new HashSet<String>();
		for (Map<String, String> row : firstPage) {
			firstPageDocs.add(row.get("DocumentNumber"));
		}
		for (Map<String, String> row : secondPage) {
			Assert.assertFalse(firstPageDocs.contains(row.get("DocumentNumber")),
					"Page 2 must not repeat rows from page 1: " + row.get("DocumentNumber"));
		}
	}

	@Test(priority = 84, groups = "lookup")
	public void lookupMeterRead_PageNumPastEnd_ReturnsEmptyPlaceholderRow()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		HashMap<String, String> params = allRowsParams();
		params.put("NumPerPage", "5");
		params.put("PageNum", "999999");
		String actual = meterRead(params);
		Assert.assertTrue(isEmptyPlaceholder(meterReadRows(actual)),
				"A page past the end must return one all-empty row with no Status key. Response: " + actual);
	}

	@Test(priority = 85, groups = "lookup")
	public void lookupMeterRead_LocationIdTooLong_ReturnsError()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		requireAdjustmentParameters();
		HashMap<String, String> params = allRowsParams();
		params.put("LocationId", "LOCATIONIDTHATISWAYTOOLONG");
		assertMeterReadValidationError(meterRead(params), "LocationId longer than 15 characters",
				"LocationId length must be less than or equal to 15 characters long");
	}

	@Test(priority = 86, groups = "lookup")
	public void lookupMeterRead_BatchIdTooLong_ReturnsError()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		HashMap<String, String> params = allRowsParams();
		params.put("BatchId", "BATCHIDTHATISWAYTOOLONG");
		assertMeterReadValidationError(meterRead(params), "BatchId longer than 15 characters",
				"BatchId length must be less than or equal to 15 characters long");
	}

	@Test(priority = 87, groups = "lookup")
	public void lookupMeterRead_OrderByTooLong_ReturnsError()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		StringBuilder orderBy = new StringBuilder();
		while (orderBy.length() <= 255) {
			orderBy.append("ReadingDate DESC,");
		}
		HashMap<String, String> params = allRowsParams();
		params.put("OrderBy", orderBy.toString());
		assertMeterReadValidationError(meterRead(params), "OrderBy longer than 255 characters",
				"OrderBy length must be less than or equal to 255 characters long");
	}

	@Test(priority = 88, groups = "lookup")
	public void lookupMeterRead_PageNumNonNumeric_ReturnsError()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		HashMap<String, String> params = allRowsParams();
		params.put("PageNum", "abc");
		assertMeterReadValidationError(meterRead(params), "Non-numeric PageNum", "PageNum must be a number");
	}

	@Test(priority = 89, groups = "lookup")
	public void lookupMeterRead_NumPerPageNonNumeric_ReturnsError()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		HashMap<String, String> params = allRowsParams();
		params.put("NumPerPage", "abc");
		assertMeterReadValidationError(meterRead(params), "Non-numeric NumPerPage", "NumPerPage must be a number");
	}

	@Test(priority = 90, groups = "lookup")
	public void lookupMeterRead_IsReadAllowAdjustmentNotBoolean_ReturnsError()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// Only 1/0/true/false pass the Joi boolean check.
		requireAdjustmentParameters();
		HashMap<String, String> params = allRowsParams();
		params.put("IsReadAllowAdjustment", "yes");
		assertMeterReadValidationError(meterRead(params), "IsReadAllowAdjustment sent as an arbitrary string",
				"IsReadAllowAdjustment must be a boolean");
	}

	@Test(priority = 91, groups = "lookup")
	public void lookupMeterRead_UnknownQueryParam_ReturnsError()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// The schema is not permissive, so unknown keys must be rejected.
		HashMap<String, String> params = allRowsParams();
		params.put("NotARealParameter", "1");
		assertMeterReadValidationError(meterRead(params), "Unknown query parameter",
				"NotARealParameter is not allowed");
	}

}

