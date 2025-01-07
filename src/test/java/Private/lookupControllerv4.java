package Private;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;
import com.NexustAPIAutomation.java.Retry;

public class lookupControllerv4 {

	@Test(priority = 4, groups = "lookup", retryAnalyzer = Retry.class)
	public void getapplyByService_v4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookupBatch";
		String ver = "4.0";
		String expected = "{\"Batch\":[{\"Id\":\"10001\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"1001\",\"Description\":\"\",\"HasTransaction\":true},";
		HashMap<String, String> params = new HashMap<String, String>();
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(actual);
		Assert.assertTrue(actual.contains(expected));
	}

	@Test(priority = 2, groups = "lookup", retryAnalyzer = Retry.class)
	public void getapplyByService_Paymentsv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookupBatch";
		String version = "4.0";
		String expected = "{\"Batch\":[{\"Id\":\"10001\",\"Description\":\"\",\"HasTransaction\":true},{\"Id\":\"1001\",\"Description\":\"\",\"HasTransaction\":true},";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("BatchSource", "PAYMENTS");
		// params.put("LocationId", "LOCATION011");
		String actual = CommonMethods.getMethodasString(uri, version, params);
		System.out.println(actual);
		Assert.assertTrue(actual.contains(expected));
	}

	@Test(priority = 3, groups = "lookup", retryAnalyzer = Retry.class)
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

	@Test(priority = 1, groups = "lookup", retryAnalyzer = Retry.class)
	public void lookupMetergroup4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri3 = "/lookupMeterGroup";
		String ver = "4.0";
		String jpath = "./\\TestData\\lookupMeterGroupv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		// params.put("Batchsource", "NONE"); //params.put("LocationId", "LOCATION011");
		String result = CommonMethods.getMethod(uri3, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 5, groups = "lookup", retryAnalyzer = Retry.class)
	public void lookupCheckBookv4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri3 = "/lookupCheckBook";
		String ver = "4.0";
		String jpath = "./\\TestData\\lookupCheckBookv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		// params.put("Batchsource", "NONE"); //params.put("LocationId", "LOCATION011");
		String result = CommonMethods.getMethod(uri3, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 6, groups = "lookup", retryAnalyzer = Retry.class)
	public void lookupReadingTypev4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri3 = "/lookupReadingType";
		String ver = "4.0";
		String jpath = "./\\TestData\\lookupReadingTypev4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		// params.put("Batchsource", "NONE"); //params.put("LocationId", "LOCATION011");
		String result = CommonMethods.getMethod(uri3, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 7, groups = "lookup", retryAnalyzer = Retry.class)
	public void lookupNsfReasonCodev4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri3 = "/lookupNsfReasonCode";
		String ver = "4.0";
		String jpath = "./\\TestData\\lookupNsfReasonCodev4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		// params.put("Batchsource", "NONE"); //params.put("LocationId", "LOCATION011");
		String result = CommonMethods.getMethod(uri3, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 8, groups = "lookup", retryAnalyzer = Retry.class)
	public void lookupMeterReadv4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// Bug is cancelled by Dev CommonMethods.Bug("CPDEV-17166");
		String uri = "/lookupMeterRead";
		String version = "4.0";
		String expected = "\"EquipmentId\":\"ELECT\",\"BatchId\":\"TEST100\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000834\",\"ReadingDate\":\"2019-09-23\",\"LocationId\":\"SPALOCATION1\",\"EquipmentId\":\"EQUIPMENT018\",\"BatchId\":\"BILL10111\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000862\",\"ReadingDate\":\"2019-08-09\",\"LocationId\":\"BUDGETLOC01\",\"EquipmentId\":\"EQUIPMENT022\",\"BatchId\":\"BILL\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000871\",\"ReadingDate\":\"2019-07-31\",\"LocationId\":\"SPALOCATION1\",\"EquipmentId\":\"AUTOGAS\",\"BatchId\":\"BATGAS1\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000830\",\"ReadingDate\":\"2019-07-31\",\"LocationId\":\"TRANSACTION001\",\"EquipmentId\":\"EQUIPMENT007\",\"BatchId\":\"AC1001\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000890\",\"ReadingDate\":\"2019-07-01\",\"LocationId\":\"BILLGRAPH\",\"EquipmentId\":\"ELECMETER\",\"BatchId\":\"VOID\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000882\",\"ReadingDate\":\"2019-06-01\",\"LocationId\":\"BILLGRAPH\",\"EquipmentId\":\"ELECMETER\",\"BatchId\":\"VOID\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000803\",\"ReadingDate\":\"2019-04-12\",\"LocationId\":\"ELECWAT003\",\"EquipmentId\":\"EQUIPMENT015\",\"BatchId\":\"1\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000805\",\"ReadingDate\":\"2019-04-12\",\"LocationId\":\"ELECWAT002\",\"EquipmentId\":\"EQUIPMENT016\",\"BatchId\":\"001\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000907\",\"ReadingDate\":\"2019-02-28\",\"LocationId\":\"STATEMENTTEST01\",\"EquipmentId\":\"ELEC0001\",\"BatchId\":\"VOID\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000905\",\"ReadingDate\":\"2019-01-30\",\"LocationId\":\"STATEMENTTEST01\",\"EquipmentId\":\"ELEC0001\",\"BatchId\":\"VOID\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000418\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCEMP-1\",\"EquipmentId\":\"EQUIPELEC021\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000419\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCEMP-1\",\"EquipmentId\":\"EQUIPELEC022\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000420\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCATION007\",\"EquipmentId\":\"EQUIPMENT009\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000421\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCATION002\",\"EquipmentId\":\"EQUIPMENT001\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000422\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCATION003\",\"EquipmentId\":\"EQUIPMENT002\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000423\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCATION004\",\"EquipmentId\":\"EQUIPMENT005\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000424\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCATION008\",\"EquipmentId\":\"EQUIPMENT006\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000425\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCATION008\",\"EquipmentId\":\"EQUIPMENT008\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000428\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCATION011\",\"EquipmentId\":\"EQUIPMENT013\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000429\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCATION012\",\"EquipmentId\":\"EQUIPMENT014\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000430\",\"ReadingDate\":\"2000-06-30\",\"LocationId\":\"LOCATION013\",\"EquipmentId\":\"EQUIPMENT017\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000675\",\"ReadingDate\":\"2000-04-15\",\"LocationId\":\"LOCATION009\",\"EquipmentId\":\"EQUIPMENT010\",\"BatchId\":\"SOMR00000000004\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000677\",\"ReadingDate\":\"2000-04-15\",\"LocationId\":\"LOCATION010\",\"EquipmentId\":\"EQUIPMENT012\",\"BatchId\":\"SOMR00000000006\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000648\",\"ReadingDate\":\"2000-03-31\",\"LocationId\":\"ELECWAT003\",\"EquipmentId\":\"EQUIP-GAS-2\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000649\",\"ReadingDate\":\"2000-03-31\",\"LocationId\":\"ELECWAT003\",\"EquipmentId\":\"EQUIP-PHONE-1\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000651\",\"ReadingDate\":\"2000-03-31\",\"LocationId\":\"ELECWAT002\",\"EquipmentId\":\"EQUIPMENT016\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000652\",\"ReadingDate\":\"2000-03-31\",\"LocationId\":\"ELECWAT002\",\"EquipmentId\":\"WATEREQUIP003\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000653\",\"ReadingDate\":\"2000-03-31\",\"LocationId\":\"ELECWAT003\",\"EquipmentId\":\"EQUIPMENT015\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000654\",\"ReadingDate\":\"2000-03-31\",\"LocationId\":\"ELECWAT003\",\"EquipmentId\":\"WATEREQUIP004\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000656\",\"ReadingDate\":\"2000-03-31\",\"LocationId\":\"ELECWAT002\",\"EquipmentId\":\"EQUIPPH-1\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000657\",\"ReadingDate\":\"2000-03-31\",\"LocationId\":\"ELECWAT002\",\"EquipmentId\":\"EQUIPPH-1\",\"BatchId\":\"READ55\",\"Status\":\"Open\"},{\"DocumentNumber\":\"READ00000000658\",\"ReadingDate\":\"2000-03-31\",\"LocationId\":\"ELECWAT003\",\"EquipmentId\":\"EQUIP-PHONE-1\",\"BatchId\":\"READ55\",\"Status\":\"Open\"}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, version, params);
		Assert.assertTrue(result.contains(expected));

	}

	@Test(priority = 9, groups = "lookup", retryAnalyzer = Retry.class)
	public void lookupdocumentTypev4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri3 = "/lookup/documentType";
		String ver = "4.0";
		String jpath = "./\\TestData\\lookupdocumentType_v4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		// params.put("Batchsource", "NONE"); //params.put("LocationId", "LOCATION011");
		String result = CommonMethods.getMethod(uri3, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 10, groups = "lookup", retryAnalyzer = Retry.class)
	public void lookupzonev4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri3 = "/lookup/zone";
		String ver = "4.0";
		String jpath = "./\\TestData\\lookupzone_v4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri3, ver, params, jpath);
	}

	@Test(priority = 11, groups = "lookup", retryAnalyzer = Retry.class)
	public void lookupequipmentModelv4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookup/equipmentModel";
		String ver = "4.0";
		String jpath = "./\\TestData\\equipmentModel_v4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 12, groups = "lookup", retryAnalyzer = Retry.class)
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

	@Test(priority = 13, groups = "lookup", retryAnalyzer = Retry.class)
	public void lookupequipmentTypev4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/Lookup/EquipmentType";
		String ver = "4.0";
		String jpath = "./\\TestData\\EquipmentType_v4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("ServiceCategoryId", "1");
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 13, groups = "lookup", retryAnalyzer = Retry.class)
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

	@Test(priority = 14, groups = "lookup", retryAnalyzer = Retry.class)
	public void lookupequipmentNetMetervType4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookup/equipmentNetMeterType";
		String ver = "4.0";
		String jpath = "./\\TestData\\equipmentNetMeter_v4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 15, groups = "lookup", retryAnalyzer = Retry.class)
	public void lookupEquipmentRegisterCode4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookup/EquipmentRegisterCode";
		String ver = "4.0";
		String jpath = "./\\TestData\\EquipmentRegisterCodev4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 16, groups = "lookup", retryAnalyzer = Retry.class)
	public void lookupEquipmentAttributeProtection4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookup/EquipmentAttributeProtection";
		String ver = "4.0";
		String jpath = "./\\TestData\\EquipmentAttributeProtectionv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 17, groups = "lookup", retryAnalyzer = Retry.class)
	public void lookupbillType() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookup/billType";
		String ver = "4.0";
		String jpath = "./\\TestData\\billTypev4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 18, groups = "lookup", retryAnalyzer = Retry.class)
	public void lookupbillingPrepareType()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookup/billingPrepareType";
		String ver = "4.0";
		String jpath = "./\\TestData\\billingPrepareTypev4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 19, groups = "lookup", retryAnalyzer = Retry.class)
	public void lookupcollectionnoticeType()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookup/collection/noticeType";
		String ver = "4.0";
		String jpath = "./\\TestData\\collectionnoticeTypev4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 20, groups = "lookup", retryAnalyzer = Retry.class)
	public void lookupkvaReadingType() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookup/kvaReadingType";
		String ver = "4.0";
		String jpath = "./\\TestData\\lookupkvaReadingTypev4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 21, groups = "lookup", retryAnalyzer = Retry.class)
	public void lookupchargeType() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.Bug("CPDEV-17064");
		String uri = "/lookup/chargeType";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("ChargeTypeId", "CHEQUE");
		String expected = "{\"ChargeType\":[{\"Id\":\"CHEQUE\",\"Description\":\"Misc charge for printed cheque\",\"Amount\":110.00,\"ServiceType\":\"COMM-ELECTRIC\",\"SubTypeDescription\":\"LICENSE\",\"TaxSchedule\":\"ALL DETAILS\",\"LookupVisible\":true,\"IsCheckType\":true,\"CheckTypeMessage\":\"Miscellaneous Charge is a check type.\",\"PenaltyId\":\"5%\"}]}";
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(actual, expected);

	}

	@Test(priority = 211, groups = "lookup", retryAnalyzer = Retry.class)
	public void lookupchargeTypeAll() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.Bug("CPDEV-17064");
		String uri = "/lookup/chargeType";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		// params.put("ChargeTypeId", "CHEQUE");
		String expected = "{\"ChargeType\":[{\"Id\":\"CHEQUE\",\"Description\":\"Misc charge for printed cheque\",\"Amount\":110.00,\"ServiceType\":\"COMM-ELECTRIC\",\"SubTypeDescription\":\"LICENSE\",\"TaxSchedule\":\"ALL DETAILS\",\"LookupVisible\":true,\"IsCheckType\":true,\"CheckTypeMessage\":\"Miscellaneous Charge is a check type.\",\"PenaltyId\":\"5%\"},{\"Id\":\"CONTRACTORLICEN\",\"Description\":\"test\",\"Amount\":100.00,\"ServiceType\":\"COMM-ELECTRIC\",\"SubTypeDescription\":\"UTILITY\",\"TaxSchedule\":\"USAUSSTCITY+6*\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"5%\"},{\"Id\":\"INSTALLELEC\",\"Description\":\"Install meter- electric service\",\"Amount\":40.00,\"ServiceType\":\"ELECTRIC\",\"SubTypeDescription\":\"UTILITY\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"5%\"},{\"Id\":\"L K\\/RECON WR\",\"Description\":\"FEE FOR WR METER REINSTALLATION\",\"Amount\":20.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"UTILITY\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"\"},{\"Id\":\"LATE CHARGE FEE\",\"Description\":\"Charge for  late payment\",\"Amount\":10.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"UTILITY\",\"TaxSchedule\":\"\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"\"},{\"Id\":\"LOANELECT\",\"Description\":\"\",\"Amount\":0.00,\"ServiceType\":\"ELECTRIC\",\"SubTypeDescription\":\"UTILITY\",\"TaxSchedule\":\"USASTCITY-6*\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"\"},{\"Id\":\"LOANSEWER\",\"Description\":\"\",\"Amount\":0.00,\"ServiceType\":\"REFUSE\",\"SubTypeDescription\":\"UTILITY\",\"TaxSchedule\":\"EXEMPT\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"\"},{\"Id\":\"LOANWATER\",\"Description\":\"automation\",\"Amount\":10.00,\"ServiceType\":\"WATER\",\"SubTypeDescription\":\"UTILITY\",\"TaxSchedule\":\"EXEMPT\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"\"},{\"Id\":\"NSF\",\"Description\":\"NSF charge\",\"Amount\":25.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"UTILITY\",\"TaxSchedule\":\"\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"\"},{\"Id\":\"PROPERTYTAX\",\"Description\":\"\",\"Amount\":0.00,\"ServiceType\":\"PROPERTYTAX\",\"SubTypeDescription\":\"OTHER\",\"TaxSchedule\":\"CAPQGSTQST-7*\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"PTAXPENALTY\"},{\"Id\":\"RMGT-BLV-000001\",\"Description\":\"Parking in Handicap zone\",\"Amount\":50.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"CODE ENFORCEMENT\",\"TaxSchedule\":\"\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"\"},{\"Id\":\"RMGT-BLV-000002\",\"Description\":\"Speeding Over 30 in 60 zone\",\"Amount\":125.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"CODE ENFORCEMENT\",\"TaxSchedule\":\"\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"\"},{\"Id\":\"RMGT-BLV-000003\",\"Description\":\"Not cleaning up after animal\",\"Amount\":80.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"CODE ENFORCEMENT\",\"TaxSchedule\":\"\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"\"},{\"Id\":\"RMGT-INV-000001\",\"Description\":\"Multi-Line invoice 1\",\"Amount\":0.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"INVOICE\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-INV-000002\",\"Description\":\"Invoice 2- Public Works\",\"Amount\":0.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"INVOICE\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-LIC-000001\",\"Description\":\"Driver's License\",\"Amount\":25.00,\"ServiceType\":\"IS\",\"SubTypeDescription\":\"LICENSE\",\"TaxSchedule\":\"USASTCITY-6*\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-LIC-000002\",\"Description\":\"Fishing license - Salmon\",\"Amount\":30.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"LICENSE\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"RES-OVERDUE\"},{\"Id\":\"RMGT-LIC-000003\",\"Description\":\"Hunting License - Moose\",\"Amount\":200.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"LICENSE\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-OTH-000001\",\"Description\":\"Miscellaneous Revenue\",\"Amount\":0.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"OTHER\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-OTH-000002\",\"Description\":\"Recyclable paper per box\",\"Amount\":5.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"OTHER\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-PMT-000001\",\"Description\":\"Building Permit Application\",\"Amount\":100.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"PERMIT\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-PMT-000002\",\"Description\":\"Build 1200-3000 sq feet\",\"Amount\":120.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"PERMIT\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-RNT-000001\",\"Description\":\"Rental Pool\",\"Amount\":40.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"RENTAL\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-RNT-000002\",\"Description\":\"Rental Fire Hall\",\"Amount\":20.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"RENTAL\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-RNT-000003\",\"Description\":\"Rental Rink\",\"Amount\":80.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"RENTAL\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-SMI-000001\",\"Description\":\"City Hall Hat\",\"Amount\":15.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"SMALL ITEM\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-SMI-000002\",\"Description\":\"City Hall logo Sweater\",\"Amount\":80.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"SMALL ITEM\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"RMGT-SMI-000003\",\"Description\":\"City Hall Book\",\"Amount\":25.00,\"ServiceType\":\"\",\"SubTypeDescription\":\"SMALL ITEM\",\"TaxSchedule\":\"ONT GST\\/PST\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"SERVICE WATER\",\"Description\":\"Charge for water service\",\"Amount\":25.00,\"ServiceType\":\"WATER\",\"SubTypeDescription\":\"UTILITY\",\"TaxSchedule\":\"EXEMPT\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"DEFAULTPYMT\"},{\"Id\":\"SERVICEELEC\",\"Description\":\"Charge for electric service\",\"Amount\":30.00,\"ServiceType\":\"ELECTRIC\",\"SubTypeDescription\":\"UTILITY\",\"TaxSchedule\":\"USASTCITY-6*\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"\"},{\"Id\":\"WRITE OFF\",\"Description\":\"Write off\",\"Amount\":10.00,\"ServiceType\":\"ELECTRIC\",\"SubTypeDescription\":\"CODE ENFORCEMENT\",\"TaxSchedule\":\"USASTCITY-6*\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"\"},{\"Id\":\"WRITEOFF\",\"Description\":\"\",\"Amount\":0.00,\"ServiceType\":\"WATER\",\"SubTypeDescription\":\"UTILITY\",\"TaxSchedule\":\"EXEMPT\",\"LookupVisible\":true,\"IsCheckType\":false,\"CheckTypeMessage\":\"\",\"PenaltyId\":\"5%\"}]}";
		String actual = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(actual, expected);

	}

	@Test(priority = 22, groups = "lookup", retryAnalyzer = Retry.class)
	public void lookupbillingCyclePeriod()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookup/billingCyclePeriod";
		String ver = "4.0";
		String jpath = "./\\TestData\\lookupbillingCyclePeriod.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 23, groups = "lookup", retryAnalyzer = Retry.class)
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

	@Test(priority = 24, groups = "lookup", retryAnalyzer = Retry.class)
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

	@Test(priority = 25, groups = "lookup", retryAnalyzer = Retry.class)
	public void lookupserviceOrderOrigin()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookup/serviceOrderOrigin";
		String ver = "4.0";
		String jpath = "./\\TestData\\lookupserviceOrderOriginv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 26, groups = "lookup", retryAnalyzer = Retry.class)
	public void lookupserviceOrderStatus()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookup/serviceOrderStatus";
		String ver = "4.0";
		String jpath = "./\\TestData\\lookupserviceOrderStatusv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 27, groups = "lookup", retryAnalyzer = Retry.class)
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

	@Test(priority = 28, groups = "lookup", retryAnalyzer = Retry.class)
	public void lookupserviceOrderRequestedBy()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookup/serviceOrderRequestedBy";
		String ver = "4.0";
		String jpath = "./\\TestData\\lookupserviceOrderRequestedByv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethod(uri, ver, params, jpath);
		System.out.println(result);
	}

	@Test(priority = 29, groups = "lookup", retryAnalyzer = Retry.class)
	public void lookuplocationClass() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookup/locationClass";
		String version = "4.0";
		String expected = "{\"LocationClass\":[{\"Id\":\"NONCUST-LOC\",\"Description\":\"Non customer Location\"},{\"Id\":\"PERM-NON ACCUM\",\"Description\":\"Permanent Non Accumulated\"},{\"Id\":\"TEST001\",\"Description\":\"temporaty customer\"}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, version, params);
		Assert.assertEquals(result, expected);
	}

	@Test(priority = 30, groups = "lookup", retryAnalyzer = Retry.class)
	public void lookuplocation() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookupLocation";
		String version = "4.0";
		String expected = "{\"Locations\":[{\"LocationId\":\"LOCATION001\"}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LocationId", "LOCATION001");
		String result = CommonMethods.getMethodasString(uri, version, params);
		Assert.assertEquals(result, expected);
	}

	@Test(priority = 31, groups = "lookup", retryAnalyzer = Retry.class)
	public void lookupBatch() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/lookupBatch?BatchSource=BILLING&BillingType=Final";
		String version = "4.0";
		String expected = "{\"Batch\":[{\"Id\":\"BAT10123123\",\"Description\":\"\",\"HasTransaction\":false}]}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("BatchSource", "BILLING");
		params.put("BillingType", "Final");
		String result = CommonMethods.getMethodasString(uri, version, params);
		Assert.assertEquals(result, expected);
	}

	@Test(priority = 32, groups = "lookup", retryAnalyzer = Retry.class)
	public void lookuptranferBillToCustomerDeposit()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

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

}
