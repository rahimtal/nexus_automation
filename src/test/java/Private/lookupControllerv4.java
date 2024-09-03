package Private;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;

public class lookupControllerv4 {

	@Test(priority = 4, groups = "lookup")
	public void getapplyByService_v4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookupBatch";
		String ver = "4.0";
		String expected = "{\"Batch\":[{\"Id\":\"10001\",\"Description\":\"\"},{\"Id\":\"1001\",\"Description\":\"\"},{\"Id\":\"100111\",\"Description\":\"\"},{\"Id\":\"10111\",\"Description\":\"\"},{\"Id\":\"109090ABC\",\"Description\":\"\"},{\"Id\":\"12312312\",\"Description\":\"\"},{\"Id\":\"12345\",\"Description\":\"\"},{\"Id\":\"ABC10001\",\"Description\":\"\"},{\"Id\":\"ABC1213\",\"Description\":\"\"},{\"Id\":\"API 20190430\",\"Description\":\"Payments from Web Service - API\"},{\"Id\":\"API 20190503\",\"Description\":\"Payments from Web Service - API\"},{\"Id\":\"API20240902001\",\"Description\":\"Payments from Nexus Api - API\"},{\"Id\":\"API932024\",\"Description\":\"\"},{\"Id\":\"auto\",\"Description\":\"API Misc Charge\"},{\"Id\":\"BAT012301203\",\"Description\":\"\"},{\"Id\":\"BAT1\",\"Description\":\"\"},{\"Id\":\"BAT10123123\",\"Description\":\"\"},{\"Id\":\"BT1231\",\"Description\":\"Api billing\"},{\"Id\":\"CHEQ1\",\"Description\":\"\"},{\"Id\":\"CHK041227sa01\",\"Description\":\"CHEQUE\"},{\"Id\":\"DPP041227sa01\",\"Description\":\"PYMT\"},{\"Id\":\"MG2024\",\"Description\":\"\"},{\"Id\":\"MG2024DM001\",\"Description\":\"MISC\"},{\"Id\":\"MISC10001\",\"Description\":\"\"},{\"Id\":\"NADMC2022093001\",\"Description\":\"API Deposit Misc Charge\"},{\"Id\":\"NAMR20240903001\",\"Description\":\"API Meter Read\"},{\"Id\":\"RM(3)120427\",\"Description\":\"\"},{\"Id\":\"Test Batch\",\"Description\":\"\"},{\"Id\":\"TEST109\",\"Description\":\"\"},{\"Id\":\"WO101619CRP001\",\"Description\":\"Write Off - sa\"}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertEquals(actual, expected);
	}

	@Test(priority = 2, groups = "lookup")
	public void getapplyByService_Paymentsv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookupBatch";
		String version = "4.0";
		String expected = "{\"Batch\":[{\"Id\":\"10001\",\"Description\":\"\"},{\"Id\":\"1001\",\"Description\":\"\"},{\"Id\":\"100111\",\"Description\":\"\"},{\"Id\":\"10111\",\"Description\":\"\"},{\"Id\":\"109090ABC\",\"Description\":\"\"},{\"Id\":\"12312312\",\"Description\":\"\"},{\"Id\":\"12345\",\"Description\":\"\"},{\"Id\":\"ABC10001\",\"Description\":\"\"},{\"Id\":\"ABC1213\",\"Description\":\"\"},{\"Id\":\"API 20190430\",\"Description\":\"Payments from Web Service - API\"},{\"Id\":\"API 20190503\",\"Description\":\"Payments from Web Service - API\"},{\"Id\":\"API20240902001\",\"Description\":\"Payments from Nexus Api - API\"},{\"Id\":\"API932024\",\"Description\":\"\"},{\"Id\":\"auto\",\"Description\":\"API Misc Charge\"},{\"Id\":\"BAT012301203\",\"Description\":\"\"},{\"Id\":\"BAT1\",\"Description\":\"\"},{\"Id\":\"BAT10123123\",\"Description\":\"\"},{\"Id\":\"BT1231\",\"Description\":\"Api billing\"},{\"Id\":\"CHEQ1\",\"Description\":\"\"},{\"Id\":\"CHK041227sa01\",\"Description\":\"CHEQUE\"},{\"Id\":\"DPP041227sa01\",\"Description\":\"PYMT\"},{\"Id\":\"MG2024\",\"Description\":\"\"},{\"Id\":\"MG2024DM001\",\"Description\":\"MISC\"},{\"Id\":\"MISC10001\",\"Description\":\"\"},{\"Id\":\"NADMC2022093001\",\"Description\":\"API Deposit Misc Charge\"},{\"Id\":\"NAMR20240903001\",\"Description\":\"API Meter Read\"},{\"Id\":\"RM(3)120427\",\"Description\":\"\"},{\"Id\":\"Test Batch\",\"Description\":\"\"},{\"Id\":\"TEST109\",\"Description\":\"\"},{\"Id\":\"WO101619CRP001\",\"Description\":\"Write Off - sa\"}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("Batchsource", "PAYMENTS");
		// params.put("LocationId", "LOCATION011");
		String actual = CommonMethods.getMethodasString(uri, version, params);
		Assert.assertEquals(actual, expected);
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

	@Test(priority = 1, groups = "lookup")
	public void lookupMetergroup4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri3 = "/lookupMeterGroup";
		String ver = "4.0";
		String jpath = "./\\TestData\\lookupMeterGroupv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		// params.put("Batchsource", "NONE"); //params.put("LocationId", "LOCATION011");
		String result = CommonMethods.getMethod(uri3, ver, params, jpath);
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
		// Bug is cancelled by Dev CommonMethods.Bug("CPDEV-17166");
		String uri = "/lookupMeterRead";
		String version = "4.0";
		String expected = "{\"MeterReading\":[{\"DocumentNumber\":\"READ00000000927\",\"ReadingDate\":\"2027-04-12\",\"LocationId\":\"STATEMENTTEST01\",\"EquipmentId\":\"NETMETER001\",\"BatchId\":\"MR041227sa\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000935\",\"ReadingDate\":\"2024-09-03\",\"LocationId\":\"LOCATION002\",\"EquipmentId\":\"EQUIPMENT001\",\"BatchId\":\"API932024\",\"Status\":\"Work\"},{\"DocumentNumber\":\"READ00000000928\",\"ReadingDate\":\"2024-09-02\",\"LocationId\":\"NETMETERLOC0001\",\"EquipmentId\":\"NETMETER002\",\"BatchId\":\"BATCH0024\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000922\",\"ReadingDate\":\"2024-09-02\",\"LocationId\":\"STATEMENTTEST01\",\"EquipmentId\":\"NETMETER001\",\"BatchId\":\"BAT2024\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000932\",\"ReadingDate\":\"2024-02-12\",\"LocationId\":\"NETMETERLOC0001\",\"EquipmentId\":\"NETMETER0002\",\"BatchId\":\"BAT0004\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000930\",\"ReadingDate\":\"2024-01-01\",\"LocationId\":\"NETMETERLOC0001\",\"EquipmentId\":\"NETMETER0002\",\"BatchId\":\"BAT12\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000934\",\"ReadingDate\":\"2022-04-14\",\"LocationId\":\"WATER003\",\"EquipmentId\":\"WATEREQUIP001\",\"BatchId\":\"NAMR20240903001\",\"Status\":\"Work\"},{\"DocumentNumber\":\"READ00000000913\",\"ReadingDate\":\"2020-02-10\",\"LocationId\":\"WATER100\",\"EquipmentId\":\"ELECT\",\"BatchId\":\"TEST100\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000834\",\"ReadingDate\":\"2019-09-23\",\"LocationId\":\"SPALOCATION1\",\"EquipmentId\":\"EQUIPMENT018\",\"BatchId\":\"BILL10111\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000862\",\"ReadingDate\":\"2019-08-09\",\"LocationId\":\"BUDGETLOC01\",\"EquipmentId\":\"EQUIPMENT022\",\"BatchId\":\"BILL\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000871\",\"ReadingDate\":\"2019-07-31\",\"LocationId\":\"SPALOCATION1\",\"EquipmentId\":\"AUTOGAS\",\"BatchId\":\"BATGAS1\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000830\",\"ReadingDate\":\"2019-07-31\",\"LocationId\":\"TRANSACTION001\",\"EquipmentId\":\"EQUIPMENT007\",\"BatchId\":\"AC1001\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000890\",\"ReadingDate\":\"2019-07-01\",\"LocationId\":\"BILLGRAPH\",\"EquipmentId\":\"ELECMETER\",\"BatchId\":\"VOID\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000882\",\"ReadingDate\":\"2019-06-01\",\"LocationId\":\"BILLGRAPH\",\"EquipmentId\":\"ELECMETER\",\"BatchId\":\"VOID\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000803\",\"ReadingDate\":\"2019-04-12\",\"LocationId\":\"ELECWAT003\",\"EquipmentId\":\"EQUIPMENT015\",\"BatchId\":\"1\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000805\",\"ReadingDate\":\"2019-04-12\",\"LocationId\":\"ELECWAT002\",\"EquipmentId\":\"EQUIPMENT016\",\"BatchId\":\"001\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000907\",\"ReadingDate\":\"2019-02-28\",\"LocationId\":\"STATEMENTTEST01\",\"EquipmentId\":\"ELEC0001\",\"BatchId\":\"VOID\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000905\",\"ReadingDate\":\"2019-01-30\",\"LocationId\":\"STATEMENTTEST01\",\"EquipmentId\":\"ELEC0001\",\"BatchId\":\"VOID\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000418\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCEMP-1\",\"EquipmentId\":\"EQUIPELEC021\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000419\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCEMP-1\",\"EquipmentId\":\"EQUIPELEC022\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000420\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCATION007\",\"EquipmentId\":\"EQUIPMENT009\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000421\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCATION002\",\"EquipmentId\":\"EQUIPMENT001\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000422\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCATION003\",\"EquipmentId\":\"EQUIPMENT002\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000423\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCATION004\",\"EquipmentId\":\"EQUIPMENT005\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000424\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCATION008\",\"EquipmentId\":\"EQUIPMENT006\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000425\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCATION008\",\"EquipmentId\":\"EQUIPMENT008\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000428\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCATION011\",\"EquipmentId\":\"EQUIPMENT013\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000429\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCATION012\",\"EquipmentId\":\"EQUIPMENT014\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000430\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCATION013\",\"EquipmentId\":\"EQUIPMENT017\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000675\",\"ReadingDate\":\"2000-04-15\",\"LocationId\":\"LOCATION009\",\"EquipmentId\":\"EQUIPMENT010\",\"BatchId\":\"SOMR00000000004\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000677\",\"ReadingDate\":\"2000-04-15\",\"LocationId\":\"LOCATION010\",\"EquipmentId\":\"EQUIPMENT012\",\"BatchId\":\"SOMR00000000006\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000648\",\"ReadingDate\":\"2000-03-31\",\"LocationId\":\"ELECWAT003\",\"EquipmentId\":\"EQUIP-GAS-2\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000649\",\"ReadingDate\":\"2000-03-31\",\"LocationId\":\"ELECWAT003\",\"EquipmentId\":\"EQUIP-PHONE-1\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000651\",\"ReadingDate\":\"2000-03-31\",\"LocationId\":\"ELECWAT002\",\"EquipmentId\":\"EQUIPMENT016\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000652\",\"ReadingDate\":\"2000-03-31\",\"LocationId\":\"ELECWAT002\",\"EquipmentId\":\"WATEREQUIP003\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000653\",\"ReadingDate\":\"2000-03-31\",\"LocationId\":\"ELECWAT003\",\"EquipmentId\":\"EQUIPMENT015\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000654\",\"ReadingDate\":\"2000-03-31\",\"LocationId\":\"ELECWAT003\",\"EquipmentId\":\"WATEREQUIP004\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000656\",\"ReadingDate\":\"2000-03-31\",\"LocationId\":\"ELECWAT002\",\"EquipmentId\":\"EQUIPPH-1\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000657\",\"ReadingDate\":\"2000-03-31\",\"LocationId\":\"ELECWAT002\",\"EquipmentId\":\"EQUIPPH-1\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000658\",\"ReadingDate\":\"2000-03-31\",\"LocationId\":\"ELECWAT003\",\"EquipmentId\":\"EQUIP-PHONE-1\",\"BatchId\":\"READ55\",\"Status\":\"Open\"}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, version, params);
		Assert.assertEquals(result, expected);

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

	@Test(priority = 13, groups = "lookup")
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

	@Test(priority = 14, groups = "lookup")
	public void lookupequipmentNetMetervType4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookup/equipmentNetMeterType";
		String ver = "4.0";
		String jpath = "./\\TestData\\equipmentNetMeter_v4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 15, groups = "lookup")
	public void lookupEquipmentRegisterCode4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookup/EquipmentRegisterCode";
		String ver = "4.0";
		String jpath = "./\\TestData\\EquipmentRegisterCodev4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 16, groups = "lookup")
	public void lookupEquipmentAttributeProtection4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookup/EquipmentAttributeProtection";
		String ver = "4.0";
		String jpath = "./\\TestData\\EquipmentAttributeProtectionv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 17, groups = "lookup")
	public void lookupbillType() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookup/billType";
		String ver = "4.0";
		String jpath = "./\\TestData\\billTypev4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 18, groups = "lookup")
	public void lookupbillingPrepareType()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookup/billingPrepareType";
		String ver = "4.0";
		String jpath = "./\\TestData\\billingPrepareTypev4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 19, groups = "lookup")
	public void lookupcollectionnoticeType()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookup/collection/noticeType";
		String ver = "4.0";
		String jpath = "./\\TestData\\collectionnoticeTypev4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 20, groups = "lookup")
	public void lookupkvaReadingType() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookup/kvaReadingType";
		String ver = "4.0";
		String jpath = "./\\TestData\\lookupkvaReadingTypev4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 21, groups = "lookup")
	public void lookupchargeType() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		CommonMethods.Bug("CPDEV-17064");
		String uri = "/lookup/chargeType";
		String ver = "4.0";
		String jpath = "./\\TestData\\lookupchargeTypev4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 22, groups = "lookup")
	public void lookupbillingCyclePeriod()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookup/billingCyclePeriod";
		String ver = "4.0";
		String jpath = "./\\TestData\\lookupbillingCyclePeriod.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 23, groups = "lookup")
	public void lookupMiscChargeDocuments()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		CommonMethods.Bug(" CPDEV-17161 ");
		String uri = "/lookup/miscChargeDocuments";
		String ver = "4.0";
		String jpath = "./\\TestData\\lookupMiscChargeDocumentsv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("DocumentSource", "work");
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 24, groups = "lookup")
	public void lookupPaymentDocuments()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		CommonMethods.Bug("https://cogsdale.atlassian.net/browse/CPDEV-18805");
		String uri = "/lookup/paymentDocuments";
		String ver = "4.0";
		String jpath = "./\\TestData\\lookuppaymentDocumentsv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("DocumentSource", "work");
		params.put("PaymentType", "creditmemo");

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
		CommonMethods.Bug("https://cogsdale.atlassian.net/browse/CPDEV-18771");
		String uri = "/lookup/serviceOrderTasks";
		String ver = "4.0";
		String jpath = "./\\TestData\\lookupserviceOrderTasksv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
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
		String uri = "/lookup/locationClass";
		String version = "4.0";
		String expected = "{\"LocationClass\":[{\"Id\":\"NONCUST-LOC\",\"Description\":\"Non customer Location\"},{\"Id\":\"PERM-NON ACCUM\",\"Description\":\"Permanent Non Accumulated\"},{\"Id\":\"TEST001\",\"Description\":\"temporaty customer\"}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, version, params);
		Assert.assertEquals(result, expected);
	}

}
