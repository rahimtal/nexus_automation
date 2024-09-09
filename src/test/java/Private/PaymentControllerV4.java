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

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PaymentControllerV4 {

	@Test(priority = 1, groups = "Payment")
	public void gettPaymentNextv4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/payment/next";
		String ver = "4.0";
		String expected = "./\\TestData\\gettPaymentNextv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("PrevInWork", "true");
		String result = CommonMethods.getMethodContains(uri, ver, params, expected);
		System.out.println(result);

	}

	@Test(priority = 2, groups = "Payment")
	public void postPaymentSimulatev4() throws ClassNotFoundException, SQLException, InterruptedException {

		CommonMethods.Bug("CPDEV-17140");
		String uri = "/payment/simulate";
		String ver = "4.0";
		String payload = "./\\TestData\\paymentsimulatev4.json";
		String exptected = "{\"Payment\":{\"Success\":true,\"Data\":{\"LocationId\":\"000000000523000\",\"CustomerId\":\"0012200\",\"CreditNoteId\":\"RMGT-OTH-000002\",\"TaxScheduleId\":\"ONT GST\\/PST\",\"Amount\":5.00,\"TotalTax\":0.75,\"TotalAmount\":5.75,\"UnappliedAmount\":5.75,\"DistributionDetail\":[{\"TypeId\":3,\"Type\":\"RECV\",\"Description\":\"Accounts Receivable\",\"Index\":6,\"Number\":\"000-1200-00\",\"DebitAmount\":0.00,\"CreditAmount\":5.75},{\"TypeId\":9,\"Type\":\"SALES\",\"Description\":\"Other Electric Revenues\",\"Index\":554,\"Number\":\"900-4740-00\",\"DebitAmount\":5.00,\"CreditAmount\":0.00},{\"TypeId\":13,\"Type\":\"TAXES\",\"Description\":\"Federal Income Tax Payable\",\"Index\":96,\"Number\":\"000-2600-00\",\"DebitAmount\":0.75,\"CreditAmount\":0.00}],\"DistributionTotal\":{\"DebitAmount\":5.75,\"CreditAmount\":5.75},\"TaxDetail\":[{\"TaxDetailId\":\"GST\",\"TransactionAmount\":5.00,\"TaxAmount\":0.35,\"TaxAccountIndex\":96,\"ServiceType\":\"\",\"ServiceTypeReceivableAccountIndex\":0},{\"TaxDetailId\":\"PST\",\"TransactionAmount\":5.00,\"TaxAmount\":0.40,\"TaxAccountIndex\":96,\"ServiceType\":\"\",\"ServiceTypeReceivableAccountIndex\":0}],\"Document\":[{\"Number\":\"BUDG00000002604\",\"LocationId\":\"000000000523000\",\"LocationAddress\":\"130 W SAMSULA DR\",\"StatementNumber\":0,\"Type\":[{\"Id\":7,\"Description\":\"Budget Document\"}],\"ServiceType\":[{\"Id\":\"\",\"Description\":\"\"}],\"Date\":\"2000-05-22\",\"DueDate\":\"2000-05-22\",\"Amount\":167.00,\"OutstandingAmount\":167.00,\"ApplyAmount\":0.00,\"Sequence\":1,\"ReferenceDocumentNumber\":\"BUDG00000002600\",\"ReferenceDocumentDate\":\"2000-01-01\",\"ServiceCategory\":[{\"Id\":0,\"Description\":\"\"}],\"Attribute\":[{\"ChargeType\":\"\",\"ChargeDescription\":\"\",\"PaymentOrigin\":\"\",\"SupportAuthorization\":0,\"Service\":[{\"Category\":0,\"Description\":\"\",\"Type\":\"\",\"Amount\":0.00,\"OutstandingAmount\":0.00,\"ApplyAmount\":0.00}]}]},{\"Number\":\"BUDG00000002605\",\"LocationId\":\"000000000523000\",\"LocationAddress\":\"130 W SAMSULA DR\",\"StatementNumber\":0,\"Type\":[{\"Id\":7,\"Description\":\"Budget Document\"}],\"ServiceType\":[{\"Id\":\"\",\"Description\":\"\"}],\"Date\":\"2000-06-21\",\"DueDate\":\"2000-06-21\",\"Amount\":167.00,\"OutstandingAmount\":167.00,\"ApplyAmount\":0.00,\"Sequence\":2,\"ReferenceDocumentNumber\":\"BUDG00000002600\",\"ReferenceDocumentDate\":\"2000-01-01\",\"ServiceCategory\":[{\"Id\":0,\"Description\":\"\"}],\"Attribute\":[{\"ChargeType\":\"\",\"ChargeDescription\":\"\",\"PaymentOrigin\":\"\",\"SupportAuthorization\":0,\"Service\":[{\"Category\":0,\"Description\":\"\",\"Type\":\"\",\"Amount\":0.00,\"OutstandingAmount\":0.00,\"ApplyAmount\":0.00}]}]},{\"Number\":\"BUDG00000002606\",\"LocationId\":\"000000000523000\",\"LocationAddress\":\"130 W SAMSULA DR\",\"StatementNumber\":0,\"Type\":[{\"Id\":7,\"Description\":\"Budget Document\"}],\"ServiceType\":[{\"Id\":\"\",\"Description\":\"\"}],\"Date\":\"2000-07-21\",\"DueDate\":\"2000-07-21\",\"Amount\":167.00,\"OutstandingAmount\":167.00,\"ApplyAmount\":0.00,\"Sequence\":3,\"ReferenceDocumentNumber\":\"BUDG00000002600\",\"ReferenceDocumentDate\":\"2000-01-01\",\"ServiceCategory\":[{\"Id\":0,\"Description\":\"\"}],\"Attribute\":[{\"ChargeType\":\"\",\"ChargeDescription\":\"\",\"PaymentOrigin\":\"\",\"SupportAuthorization\":0,\"Service\":[{\"Category\":0,\"Description\":\"\",\"Type\":\"\",\"Amount\":0.00,\"OutstandingAmount\":0.00,\"ApplyAmount\":0.00}]}]},{\"Number\":\"BUDG00000002607\",\"LocationId\":\"000000000523000\",\"LocationAddress\":\"130 W SAMSULA DR\",\"StatementNumber\":0,\"Type\":[{\"Id\":7,\"Description\":\"Budget Document\"}],\"ServiceType\":[{\"Id\":\"\",\"Description\":\"\"}],\"Date\":\"2000-08-21\",\"DueDate\":\"2000-08-21\",\"Amount\":167.00,\"OutstandingAmount\":167.00,\"ApplyAmount\":0.00,\"Sequence\":4,\"ReferenceDocumentNumber\":\"BUDG00000002600\",\"ReferenceDocumentDate\":\"2000-01-01\",\"ServiceCategory\":[{\"Id\":0,\"Description\":\"\"}],\"Attribute\":[{\"ChargeType\":\"\",\"ChargeDescription\":\"\",\"PaymentOrigin\":\"\",\"SupportAuthorization\":0,\"Service\":[{\"Category\":0,\"Description\":\"\",\"Type\":\"\",\"Amount\":0.00,\"OutstandingAmount\":0.00,\"ApplyAmount\":0.00}]}]},{\"Number\":\"BUDG00000002608\",\"LocationId\":\"000000000523000\",\"LocationAddress\":\"130 W SAMSULA DR\",\"StatementNumber\":0,\"Type\":[{\"Id\":7,\"Description\":\"Budget Document\"}],\"ServiceType\":[{\"Id\":\"\",\"Description\":\"\"}],\"Date\":\"2000-09-21\",\"DueDate\":\"2000-09-21\",\"Amount\":167.00,\"OutstandingAmount\":167.00,\"ApplyAmount\":0.00,\"Sequence\":5,\"ReferenceDocumentNumber\":\"BUDG00000002600\",\"ReferenceDocumentDate\":\"2000-01-01\",\"ServiceCategory\":[{\"Id\":0,\"Description\":\"\"}],\"Attribute\":[{\"ChargeType\":\"\",\"ChargeDescription\":\"\",\"PaymentOrigin\":\"\",\"SupportAuthorization\":0,\"Service\":[{\"Category\":0,\"Description\":\"\",\"Type\":\"\",\"Amount\":0.00,\"OutstandingAmount\":0.00,\"ApplyAmount\":0.00}]}]},{\"Number\":\"BUDG00000002609\",\"LocationId\":\"000000000523000\",\"LocationAddress\":\"130 W SAMSULA DR\",\"StatementNumber\":0,\"Type\":[{\"Id\":7,\"Description\":\"Budget Document\"}],\"ServiceType\":[{\"Id\":\"\",\"Description\":\"\"}],\"Date\":\"2000-10-23\",\"DueDate\":\"2000-10-23\",\"Amount\":167.00,\"OutstandingAmount\":167.00,\"ApplyAmount\":0.00,\"Sequence\":6,\"ReferenceDocumentNumber\":\"BUDG00000002600\",\"ReferenceDocumentDate\":\"2000-01-01\",\"ServiceCategory\":[{\"Id\":0,\"Description\":\"\"}],\"Attribute\":[{\"ChargeType\":\"\",\"ChargeDescription\":\"\",\"PaymentOrigin\":\"\",\"SupportAuthorization\":0,\"Service\":[{\"Category\":0,\"Description\":\"\",\"Type\":\"\",\"Amount\":0.00,\"OutstandingAmount\":0.00,\"ApplyAmount\":0.00}]}]},{\"Number\":\"BUDG00000002610\",\"LocationId\":\"000000000523000\",\"LocationAddress\":\"130 W SAMSULA DR\",\"StatementNumber\":0,\"Type\":[{\"Id\":7,\"Description\":\"Budget Document\"}],\"ServiceType\":[{\"Id\":\"\",\"Description\":\"\"}],\"Date\":\"2000-11-21\",\"DueDate\":\"2000-11-21\",\"Amount\":167.00,\"OutstandingAmount\":167.00,\"ApplyAmount\":0.00,\"Sequence\":7,\"ReferenceDocumentNumber\":\"BUDG00000002600\",\"ReferenceDocumentDate\":\"2000-01-01\",\"ServiceCategory\":[{\"Id\":0,\"Description\":\"\"}],\"Attribute\":[{\"ChargeType\":\"\",\"ChargeDescription\":\"\",\"PaymentOrigin\":\"\",\"SupportAuthorization\":0,\"Service\":[{\"Category\":0,\"Description\":\"\",\"Type\":\"\",\"Amount\":0.00,\"OutstandingAmount\":0.00,\"ApplyAmount\":0.00}]}]},{\"Number\":\"BUDG00000002611\",\"LocationId\":\"000000000523000\",\"LocationAddress\":\"130 W SAMSULA DR\",\"StatementNumber\":0,\"Type\":[{\"Id\":7,\"Description\":\"Budget Document\"}],\"ServiceType\":[{\"Id\":\"\",\"Description\":\"\"}],\"Date\":\"2000-12-21\",\"DueDate\":\"2000-12-21\",\"Amount\":167.00,\"OutstandingAmount\":167.00,\"ApplyAmount\":0.00,\"Sequence\":8,\"ReferenceDocumentNumber\":\"BUDG00000002600\",\"ReferenceDocumentDate\":\"2000-01-01\",\"ServiceCategory\":[{\"Id\":0,\"Description\":\"\"}],\"Attribute\":[{\"ChargeType\":\"\",\"ChargeDescription\":\"\",\"PaymentOrigin\":\"\",\"SupportAuthorization\":0,\"Service\":[{\"Category\":0,\"Description\":\"\",\"Type\":\"\",\"Amount\":0.00,\"OutstandingAmount\":0.00,\"ApplyAmount\":0.00}]}]},{\"Number\":\"BUDG00000002612\",\"LocationId\":\"000000000523000\",\"LocationAddress\":\"130 W SAMSULA DR\",\"StatementNumber\":0,\"Type\":[{\"Id\":7,\"Description\":\"Budget Document\"}],\"ServiceType\":[{\"Id\":\"\",\"Description\":\"\"}],\"Date\":\"2001-01-21\",\"DueDate\":\"2001-01-21\",\"Amount\":167.00,\"OutstandingAmount\":167.00,\"ApplyAmount\":0.00,\"Sequence\":9,\"ReferenceDocumentNumber\":\"BUDG00000002600\",\"ReferenceDocumentDate\":\"2000-01-01\",\"ServiceCategory\":[{\"Id\":0,\"Description\":\"\"}],\"Attribute\":[{\"ChargeType\":\"\",\"ChargeDescription\":\"\",\"PaymentOrigin\":\"\",\"SupportAuthorization\":0,\"Service\":[{\"Category\":0,\"Description\":\"\",\"Type\":\"\",\"Amount\":0.00,\"OutstandingAmount\":0.00,\"ApplyAmount\":0.00}]}]},{\"Number\":\"BILL00000000582\",\"LocationId\":\"000000000523000\",\"LocationAddress\":\"130 W SAMSULA DR\",\"StatementNumber\":384,\"Type\":[{\"Id\":5,\"Description\":\"Regular Bill\"}],\"ServiceType\":[{\"Id\":\"\",\"Description\":\"\"}],\"Date\":\"2019-09-01\",\"DueDate\":\"2019-09-22\",\"Amount\":133.45,\"OutstandingAmount\":133.45,\"ApplyAmount\":0.00,\"Sequence\":10,\"ReferenceDocumentNumber\":\"\",\"ReferenceDocumentDate\":\"\",\"ServiceCategory\":[{\"Id\":0,\"Description\":\"\"}],\"Attribute\":[{\"ChargeType\":\"\",\"ChargeDescription\":\"\",\"PaymentOrigin\":\"\",\"SupportAuthorization\":0,\"Service\":[{\"Category\":2,\"Description\":\"Water\",\"Type\":\"IR\",\"Amount\":51.55,\"OutstandingAmount\":51.55,\"ApplyAmount\":0.00},{\"Category\":3,\"Description\":\"Sewer\",\"Type\":\"PC\",\"Amount\":37.20,\"OutstandingAmount\":37.20,\"ApplyAmount\":0.00},{\"Category\":2,\"Description\":\"Water\",\"Type\":\"WR\",\"Amount\":44.70,\"OutstandingAmount\":44.70,\"ApplyAmount\":0.00}]}]},{\"Number\":\"BILL00000000583\",\"LocationId\":\"000000000523000\",\"LocationAddress\":\"130 W SAMSULA DR\",\"StatementNumber\":384,\"Type\":[{\"Id\":5,\"Description\":\"Regular Bill\"}],\"ServiceType\":[{\"Id\":\"\",\"Description\":\"\"}],\"Date\":\"2019-09-01\",\"DueDate\":\"2019-09-22\",\"Amount\":31.36,\"OutstandingAmount\":31.36,\"ApplyAmount\":0.00,\"Sequence\":11,\"ReferenceDocumentNumber\":\"\",\"ReferenceDocumentDate\":\"\",\"ServiceCategory\":[{\"Id\":1,\"Description\":\"Electric\"}],\"Attribute\":[{\"ChargeType\":\"\",\"ChargeDescription\":\"\",\"PaymentOrigin\":\"\",\"SupportAuthorization\":0,\"Service\":[{\"Category\":1,\"Description\":\"Electric\",\"Type\":\"GC\",\"Amount\":14.79,\"OutstandingAmount\":14.79,\"ApplyAmount\":0.00},{\"Category\":1,\"Description\":\"Electric\",\"Type\":\"RE_FIX\",\"Amount\":5.65,\"OutstandingAmount\":5.65,\"ApplyAmount\":0.00},{\"Category\":1,\"Description\":\"Electric\",\"Type\":\"RE_MR\",\"Amount\":10.92,\"OutstandingAmount\":10.92,\"ApplyAmount\":0.00}]}]},{\"Number\":\"MISC00000000305\",\"LocationId\":\"000000000523000\",\"LocationAddress\":\"130 W SAMSULA DR\",\"StatementNumber\":384,\"Type\":[{\"Id\":2,\"Description\":\"Misc Charge\"}],\"ServiceType\":[{\"Id\":\"\",\"Description\":\"\"}],\"Date\":\"2000-04-20\",\"DueDate\":\"2019-09-22\",\"Amount\":20.00,\"OutstandingAmount\":20.00,\"ApplyAmount\":0.00,\"Sequence\":12,\"ReferenceDocumentNumber\":\"\",\"ReferenceDocumentDate\":\"\",\"ServiceCategory\":[{\"Id\":0,\"Description\":\"\"}],\"Attribute\":[{\"ChargeType\":\"L K\\/RECON WR\",\"ChargeDescription\":\"FEE FOR WR METER REINSTALLATION\",\"PaymentOrigin\":\"\",\"SupportAuthorization\":0,\"Service\":[{\"Category\":0,\"Description\":\"\",\"Type\":\"\",\"Amount\":0.00,\"OutstandingAmount\":20.00,\"ApplyAmount\":0.00}]}]},{\"Number\":\"MISC00000000332\",\"LocationId\":\"000000000523000\",\"LocationAddress\":\"130 W SAMSULA DR\",\"StatementNumber\":384,\"Type\":[{\"Id\":2,\"Description\":\"Misc Charge\"}],\"ServiceType\":[{\"Id\":\"\",\"Description\":\"\"}],\"Date\":\"2019-07-31\",\"DueDate\":\"2019-09-22\",\"Amount\":500.00,\"OutstandingAmount\":500.00,\"ApplyAmount\":0.00,\"Sequence\":13,\"ReferenceDocumentNumber\":\"\",\"ReferenceDocumentDate\":\"\",\"ServiceCategory\":[{\"Id\":0,\"Description\":\"\"}],\"Attribute\":[{\"ChargeType\":\"CHEQUE\",\"ChargeDescription\":\"Misc charge for printed cheque\",\"PaymentOrigin\":\"\",\"SupportAuthorization\":0,\"Service\":[{\"Category\":0,\"Description\":\"\",\"Type\":\"\",\"Amount\":500.00,\"OutstandingAmount\":500.00,\"ApplyAmount\":0.00}]}]}]},\"Messages\":[]}}";
		Response result = CommonMethods.postMethodResponseasString(payload, uri, ver);
		String actualResult = result.print();
		AssertJUnit.assertEquals(actualResult, exptected);

	}

	@Test(priority = 3, groups = "Payment")
	public static void delPaymentv4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/payment/PYMT00000000431";
		String ver = "4.0";
		String jpath = "./\\TestData\\delPaymentV4.json";
		String result = CommonMethods.deleteMethod(uri, ver, jpath);
		System.out.println(result.toString());

	}

	@Test(priority = 4, groups = "Payment")
	public static void delPaymentv4Err()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/payment/PYMT00000000431";
		String ver = "4.0";
		String jpath = "./\\TestData\\delPaymentErrV4.json";
		String result = CommonMethods.deleteMethod(uri, ver, jpath);
		System.out.println(result.toString());

	}

	@Test(priority = 5, groups = "Payment")
	public static void delPaymentv4CreditNote()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/payment/PYMT00000000505";
		String ver = "4.0";
		String jpath = "./\\TestData\\delPaymentCreditNoteV4.json";
		String result = CommonMethods.deleteMethod(uri, ver, jpath);
		System.out.println(result.toString());

	}

	@Test(priority = 6, groups = "Payment")
	public void gettPaymentNextOpenv4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/payment/next";
		String ver = "4.0";
		String expected = "./\\TestData\\gettPaymentNextOpenv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("PrevInOpen", "true");
		String result = CommonMethods.getMethodContains(uri, ver, params, expected);
		System.out.println(result);

	}

	@Test(priority = 7, groups = "Payment")
	public void gettPaymentNextHistv4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/payment/next";
		String ver = "4.0";
		String expected = "./\\TestData\\gettPaymentNextHistv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("PrevInHistory", "true");
		String result = CommonMethods.getMethodContains(uri, ver, params, expected);
		expected = "./\\TestData\\getpaymentNextHistv4_1.json";
		result = CommonMethods.getMethodContains(uri, ver, params, expected);

		System.out.println(result);

	}

	@Test(priority = 8, groups = "Payment")
	public void gettPaymentNextAllv4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// CommonMethods.CompanyDBRestore();
		String uri = "/payment/next";
		String ver = "4.0";
		String expected = "./\\TestData\\gettPaymentNextAllv4.json";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("PaymentType", "All");
		String result = CommonMethods.getMethodContains(uri, ver, params, expected);
		System.out.println(result);

	}

	@Test(priority = 9, groups = "Payment")
	public void putputPaymentV4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		CommonMethods.Bug("You've created CPDEV-18776 issue");
		String uri = "/Payment";
		String ver = "4.0";
		String jpath = "./\\TestData\\putPaymentV4.json";
		String params = new String(Files.readAllBytes(Paths.get(jpath)));
		String expected = "./\\TestData\\putPaymentV4expected_v4.json";
		Response result = CommonMethods.putMethod(uri, ver, params, expected);

	}

	@Test(priority = 10, groups = "Payment")
	public void postPaymentSimulateExt() throws ClassNotFoundException, SQLException, InterruptedException {

		CommonMethods.Bug("CPDEV-17140");
		String uri = "/payment/simulate";
		String version = "4.0";
		String payload = "./\\TestData\\paymentsimulatev4.json";
		String expected = "{\"Payment\":{\"Success\":true,\"Data\":{\"LocationId\":\"000000000523000\",\"CustomerId\":\"0012200\",\"CreditNoteId\":\"RMGT-OTH-000002\",\"TaxScheduleId\":\"ONT GST\\/PST\",\"Amount\":5.00,\"TotalTax\":0.75,\"TotalAmount\":5.75,\"UnappliedAmount\":5.75,\"DistributionDetail\":[{\"TypeId\":3,\"Type\":\"RECV\",\"Description\":\"Accounts Receivable\",\"Index\":6,\"Number\":\"000-1200-00\",\"DebitAmount\":0.00,\"CreditAmount\":5.75},{\"TypeId\":9,\"Type\":\"SALES\",\"Description\":\"Other Electric Revenues\",\"Index\":554,\"Number\":\"900-4740-00\",\"DebitAmount\":5.00,\"CreditAmount\":0.00},{\"TypeId\":13,\"Type\":\"TAXES\",\"Description\":\"Federal Income Tax Payable\",\"Index\":96,\"Number\":\"000-2600-00\",\"DebitAmount\":0.75,\"CreditAmount\":0.00}],\"DistributionTotal\":{\"DebitAmount\":5.75,\"CreditAmount\":5.75},\"TaxDetail\":[{\"TaxDetailId\":\"GST\",\"TransactionAmount\":5.00,\"TaxAmount\":0.35,\"TaxAccountIndex\":96,\"ServiceType\":\"\",\"ServiceTypeReceivableAccountIndex\":0},{\"TaxDetailId\":\"PST\",\"TransactionAmount\":5.00,\"TaxAmount\":0.40,\"TaxAccountIndex\":96,\"ServiceType\":\"\",\"ServiceTypeReceivableAccountIndex\":0}],\"Document\":[{\"Number\":\"BUDG00000002604\",\"LocationId\":\"000000000523000\",\"LocationAddress\":\"130 W SAMSULA DR\",\"StatementNumber\":0,\"Type\":[{\"Id\":7,\"Description\":\"Budget Document\"}],\"ServiceType\":[{\"Id\":\"\",\"Description\":\"\"}],\"Date\":\"2000-05-22\",\"DueDate\":\"2000-05-22\",\"Amount\":167.00,\"OutstandingAmount\":167.00,\"ApplyAmount\":0.00,\"Sequence\":1,\"ReferenceDocumentNumber\":\"BUDG00000002600\",\"ReferenceDocumentDate\":\"2000-01-01\",\"ServiceCategory\":[{\"Id\":0,\"Description\":\"\"}],\"Attribute\":[{\"ChargeType\":\"\",\"ChargeDescription\":\"\",\"PaymentOrigin\":\"\",\"SupportAuthorization\":0,\"Service\":[{\"Category\":0,\"Description\":\"\",\"Type\":\"\",\"Amount\":0.00,\"OutstandingAmount\":0.00,\"ApplyAmount\":0.00}]}]},{\"Number\":\"BUDG00000002605\",\"LocationId\":\"000000000523000\",\"LocationAddress\":\"130 W SAMSULA DR\",\"StatementNumber\":0,\"Type\":[{\"Id\":7,\"Description\":\"Budget Document\"}],\"ServiceType\":[{\"Id\":\"\",\"Description\":\"\"}],\"Date\":\"2000-06-21\",\"DueDate\":\"2000-06-21\",\"Amount\":167.00,\"OutstandingAmount\":167.00,\"ApplyAmount\":0.00,\"Sequence\":2,\"ReferenceDocumentNumber\":\"BUDG00000002600\",\"ReferenceDocumentDate\":\"2000-01-01\",\"ServiceCategory\":[{\"Id\":0,\"Description\":\"\"}],\"Attribute\":[{\"ChargeType\":\"\",\"ChargeDescription\":\"\",\"PaymentOrigin\":\"\",\"SupportAuthorization\":0,\"Service\":[{\"Category\":0,\"Description\":\"\",\"Type\":\"\",\"Amount\":0.00,\"OutstandingAmount\":0.00,\"ApplyAmount\":0.00}]}]},{\"Number\":\"BUDG00000002606\",\"LocationId\":\"000000000523000\",\"LocationAddress\":\"130 W SAMSULA DR\",\"StatementNumber\":0,\"Type\":[{\"Id\":7,\"Description\":\"Budget Document\"}],\"ServiceType\":[{\"Id\":\"\",\"Description\":\"\"}],\"Date\":\"2000-07-21\",\"DueDate\":\"2000-07-21\",\"Amount\":167.00,\"OutstandingAmount\":167.00,\"ApplyAmount\":0.00,\"Sequence\":3,\"ReferenceDocumentNumber\":\"BUDG00000002600\",\"ReferenceDocumentDate\":\"2000-01-01\",\"ServiceCategory\":[{\"Id\":0,\"Description\":\"\"}],\"Attribute\":[{\"ChargeType\":\"\",\"ChargeDescription\":\"\",\"PaymentOrigin\":\"\",\"SupportAuthorization\":0,\"Service\":[{\"Category\":0,\"Description\":\"\",\"Type\":\"\",\"Amount\":0.00,\"OutstandingAmount\":0.00,\"ApplyAmount\":0.00}]}]},{\"Number\":\"BUDG00000002607\",\"LocationId\":\"000000000523000\",\"LocationAddress\":\"130 W SAMSULA DR\",\"StatementNumber\":0,\"Type\":[{\"Id\":7,\"Description\":\"Budget Document\"}],\"ServiceType\":[{\"Id\":\"\",\"Description\":\"\"}],\"Date\":\"2000-08-21\",\"DueDate\":\"2000-08-21\",\"Amount\":167.00,\"OutstandingAmount\":167.00,\"ApplyAmount\":0.00,\"Sequence\":4,\"ReferenceDocumentNumber\":\"BUDG00000002600\",\"ReferenceDocumentDate\":\"2000-01-01\",\"ServiceCategory\":[{\"Id\":0,\"Description\":\"\"}],\"Attribute\":[{\"ChargeType\":\"\",\"ChargeDescription\":\"\",\"PaymentOrigin\":\"\",\"SupportAuthorization\":0,\"Service\":[{\"Category\":0,\"Description\":\"\",\"Type\":\"\",\"Amount\":0.00,\"OutstandingAmount\":0.00,\"ApplyAmount\":0.00}]}]},{\"Number\":\"BUDG00000002608\",\"LocationId\":\"000000000523000\",\"LocationAddress\":\"130 W SAMSULA DR\",\"StatementNumber\":0,\"Type\":[{\"Id\":7,\"Description\":\"Budget Document\"}],\"ServiceType\":[{\"Id\":\"\",\"Description\":\"\"}],\"Date\":\"2000-09-21\",\"DueDate\":\"2000-09-21\",\"Amount\":167.00,\"OutstandingAmount\":167.00,\"ApplyAmount\":0.00,\"Sequence\":5,\"ReferenceDocumentNumber\":\"BUDG00000002600\",\"ReferenceDocumentDate\":\"2000-01-01\",\"ServiceCategory\":[{\"Id\":0,\"Description\":\"\"}],\"Attribute\":[{\"ChargeType\":\"\",\"ChargeDescription\":\"\",\"PaymentOrigin\":\"\",\"SupportAuthorization\":0,\"Service\":[{\"Category\":0,\"Description\":\"\",\"Type\":\"\",\"Amount\":0.00,\"OutstandingAmount\":0.00,\"ApplyAmount\":0.00}]}]},{\"Number\":\"BUDG00000002609\",\"LocationId\":\"000000000523000\",\"LocationAddress\":\"130 W SAMSULA DR\",\"StatementNumber\":0,\"Type\":[{\"Id\":7,\"Description\":\"Budget Document\"}],\"ServiceType\":[{\"Id\":\"\",\"Description\":\"\"}],\"Date\":\"2000-10-23\",\"DueDate\":\"2000-10-23\",\"Amount\":167.00,\"OutstandingAmount\":167.00,\"ApplyAmount\":0.00,\"Sequence\":6,\"ReferenceDocumentNumber\":\"BUDG00000002600\",\"ReferenceDocumentDate\":\"2000-01-01\",\"ServiceCategory\":[{\"Id\":0,\"Description\":\"\"}],\"Attribute\":[{\"ChargeType\":\"\",\"ChargeDescription\":\"\",\"PaymentOrigin\":\"\",\"SupportAuthorization\":0,\"Service\":[{\"Category\":0,\"Description\":\"\",\"Type\":\"\",\"Amount\":0.00,\"OutstandingAmount\":0.00,\"ApplyAmount\":0.00}]}]},{\"Number\":\"BUDG00000002610\",\"LocationId\":\"000000000523000\",\"LocationAddress\":\"130 W SAMSULA DR\",\"StatementNumber\":0,\"Type\":[{\"Id\":7,\"Description\":\"Budget Document\"}],\"ServiceType\":[{\"Id\":\"\",\"Description\":\"\"}],\"Date\":\"2000-11-21\",\"DueDate\":\"2000-11-21\",\"Amount\":167.00,\"OutstandingAmount\":167.00,\"ApplyAmount\":0.00,\"Sequence\":7,\"ReferenceDocumentNumber\":\"BUDG00000002600\",\"ReferenceDocumentDate\":\"2000-01-01\",\"ServiceCategory\":[{\"Id\":0,\"Description\":\"\"}],\"Attribute\":[{\"ChargeType\":\"\",\"ChargeDescription\":\"\",\"PaymentOrigin\":\"\",\"SupportAuthorization\":0,\"Service\":[{\"Category\":0,\"Description\":\"\",\"Type\":\"\",\"Amount\":0.00,\"OutstandingAmount\":0.00,\"ApplyAmount\":0.00}]}]},{\"Number\":\"BUDG00000002611\",\"LocationId\":\"000000000523000\",\"LocationAddress\":\"130 W SAMSULA DR\",\"StatementNumber\":0,\"Type\":[{\"Id\":7,\"Description\":\"Budget Document\"}],\"ServiceType\":[{\"Id\":\"\",\"Description\":\"\"}],\"Date\":\"2000-12-21\",\"DueDate\":\"2000-12-21\",\"Amount\":167.00,\"OutstandingAmount\":167.00,\"ApplyAmount\":0.00,\"Sequence\":8,\"ReferenceDocumentNumber\":\"BUDG00000002600\",\"ReferenceDocumentDate\":\"2000-01-01\",\"ServiceCategory\":[{\"Id\":0,\"Description\":\"\"}],\"Attribute\":[{\"ChargeType\":\"\",\"ChargeDescription\":\"\",\"PaymentOrigin\":\"\",\"SupportAuthorization\":0,\"Service\":[{\"Category\":0,\"Description\":\"\",\"Type\":\"\",\"Amount\":0.00,\"OutstandingAmount\":0.00,\"ApplyAmount\":0.00}]}]},{\"Number\":\"BUDG00000002612\",\"LocationId\":\"000000000523000\",\"LocationAddress\":\"130 W SAMSULA DR\",\"StatementNumber\":0,\"Type\":[{\"Id\":7,\"Description\":\"Budget Document\"}],\"ServiceType\":[{\"Id\":\"\",\"Description\":\"\"}],\"Date\":\"2001-01-21\",\"DueDate\":\"2001-01-21\",\"Amount\":167.00,\"OutstandingAmount\":167.00,\"ApplyAmount\":0.00,\"Sequence\":9,\"ReferenceDocumentNumber\":\"BUDG00000002600\",\"ReferenceDocumentDate\":\"2000-01-01\",\"ServiceCategory\":[{\"Id\":0,\"Description\":\"\"}],\"Attribute\":[{\"ChargeType\":\"\",\"ChargeDescription\":\"\",\"PaymentOrigin\":\"\",\"SupportAuthorization\":0,\"Service\":[{\"Category\":0,\"Description\":\"\",\"Type\":\"\",\"Amount\":0.00,\"OutstandingAmount\":0.00,\"ApplyAmount\":0.00}]}]},{\"Number\":\"BILL00000000582\",\"LocationId\":\"000000000523000\",\"LocationAddress\":\"130 W SAMSULA DR\",\"StatementNumber\":384,\"Type\":[{\"Id\":5,\"Description\":\"Regular Bill\"}],\"ServiceType\":[{\"Id\":\"\",\"Description\":\"\"}],\"Date\":\"2019-09-01\",\"DueDate\":\"2019-09-22\",\"Amount\":133.45,\"OutstandingAmount\":133.45,\"ApplyAmount\":0.00,\"Sequence\":10,\"ReferenceDocumentNumber\":\"\",\"ReferenceDocumentDate\":\"\",\"ServiceCategory\":[{\"Id\":0,\"Description\":\"\"}],\"Attribute\":[{\"ChargeType\":\"\",\"ChargeDescription\":\"\",\"PaymentOrigin\":\"\",\"SupportAuthorization\":0,\"Service\":[{\"Category\":2,\"Description\":\"Water\",\"Type\":\"IR\",\"Amount\":51.55,\"OutstandingAmount\":51.55,\"ApplyAmount\":0.00},{\"Category\":3,\"Description\":\"Sewer\",\"Type\":\"PC\",\"Amount\":37.20,\"OutstandingAmount\":37.20,\"ApplyAmount\":0.00},{\"Category\":2,\"Description\":\"Water\",\"Type\":\"WR\",\"Amount\":44.70,\"OutstandingAmount\":44.70,\"ApplyAmount\":0.00}]}]},{\"Number\":\"BILL00000000583\",\"LocationId\":\"000000000523000\",\"LocationAddress\":\"130 W SAMSULA DR\",\"StatementNumber\":384,\"Type\":[{\"Id\":5,\"Description\":\"Regular Bill\"}],\"ServiceType\":[{\"Id\":\"\",\"Description\":\"\"}],\"Date\":\"2019-09-01\",\"DueDate\":\"2019-09-22\",\"Amount\":31.36,\"OutstandingAmount\":31.36,\"ApplyAmount\":0.00,\"Sequence\":11,\"ReferenceDocumentNumber\":\"\",\"ReferenceDocumentDate\":\"\",\"ServiceCategory\":[{\"Id\":1,\"Description\":\"Electric\"}],\"Attribute\":[{\"ChargeType\":\"\",\"ChargeDescription\":\"\",\"PaymentOrigin\":\"\",\"SupportAuthorization\":0,\"Service\":[{\"Category\":1,\"Description\":\"Electric\",\"Type\":\"GC\",\"Amount\":14.79,\"OutstandingAmount\":14.79,\"ApplyAmount\":0.00},{\"Category\":1,\"Description\":\"Electric\",\"Type\":\"RE_FIX\",\"Amount\":5.65,\"OutstandingAmount\":5.65,\"ApplyAmount\":0.00},{\"Category\":1,\"Description\":\"Electric\",\"Type\":\"RE_MR\",\"Amount\":10.92,\"OutstandingAmount\":10.92,\"ApplyAmount\":0.00}]}]},{\"Number\":\"MISC00000000305\",\"LocationId\":\"000000000523000\",\"LocationAddress\":\"130 W SAMSULA DR\",\"StatementNumber\":384,\"Type\":[{\"Id\":2,\"Description\":\"Misc Charge\"}],\"ServiceType\":[{\"Id\":\"\",\"Description\":\"\"}],\"Date\":\"2000-04-20\",\"DueDate\":\"2019-09-22\",\"Amount\":20.00,\"OutstandingAmount\":20.00,\"ApplyAmount\":0.00,\"Sequence\":12,\"ReferenceDocumentNumber\":\"\",\"ReferenceDocumentDate\":\"\",\"ServiceCategory\":[{\"Id\":0,\"Description\":\"\"}],\"Attribute\":[{\"ChargeType\":\"L K\\/RECON WR\",\"ChargeDescription\":\"FEE FOR WR METER REINSTALLATION\",\"PaymentOrigin\":\"\",\"SupportAuthorization\":0,\"Service\":[{\"Category\":0,\"Description\":\"\",\"Type\":\"\",\"Amount\":0.00,\"OutstandingAmount\":20.00,\"ApplyAmount\":0.00}]}]},{\"Number\":\"MISC00000000332\",\"LocationId\":\"000000000523000\",\"LocationAddress\":\"130 W SAMSULA DR\",\"StatementNumber\":384,\"Type\":[{\"Id\":2,\"Description\":\"Misc Charge\"}],\"ServiceType\":[{\"Id\":\"\",\"Description\":\"\"}],\"Date\":\"2019-07-31\",\"DueDate\":\"2019-09-22\",\"Amount\":500.00,\"OutstandingAmount\":500.00,\"ApplyAmount\":0.00,\"Sequence\":13,\"ReferenceDocumentNumber\":\"\",\"ReferenceDocumentDate\":\"\",\"ServiceCategory\":[{\"Id\":0,\"Description\":\"\"}],\"Attribute\":[{\"ChargeType\":\"CHEQUE\",\"ChargeDescription\":\"Misc charge for printed cheque\",\"PaymentOrigin\":\"\",\"SupportAuthorization\":0,\"Service\":[{\"Category\":0,\"Description\":\"\",\"Type\":\"\",\"Amount\":500.00,\"OutstandingAmount\":500.00,\"ApplyAmount\":0.00}]}]}]},\"Messages\":[]}}";
		CommonMethods.postMethodString(payload, uri, version, expected);

	}
	
	
	@Test(priority = 11, groups = "Payment")
	public void postPaymentDepositv4() throws ClassNotFoundException, SQLException, InterruptedException {

		
		String uri = "/payment";
		String ver = "4.0";
		String payload = "{\r\n" + 
				"    \"BatchId\":\"dep1\",\r\n" + 
				"    \"LocationId\":\"ELECWAT001\",\r\n" + 
				"    \"CustomerId\":\"CUSTOMER007\",\r\n" + 
				"    \"Type\":0,\r\n" + 
				"    \"PaymentDate\":\"2024-09-04\",\r\n" + 
				"    \"PaymentAmount\":10.00,\r\n" + 
				"    \"OriginId\":\"\",\r\n" + 
				"    \"PaidBy\":1,\r\n" + 
				"    \"PaidById\":\"\",\r\n" + 
				"    \"AuthorizationCode\":\"\",\r\n" + 
				"    \"AutoApply\":false,\r\n" + 
				"    \"ApplyBy\":0,\r\n" + 
				"    \"Comment\":\"Deposit\",\r\n" + 
				"    \"DistributionDetail\":\r\n" + 
				"    [\r\n" + 
				"        {\"TypeId\":3,\r\n" + 
				"        \"Index\":508,\r\n" + 
				"        \"DebitAmount\":0.00,\r\n" + 
				"        \"CreditAmount\":10.00\r\n" + 
				"        },\r\n" + 
				"    {\"TypeId\":9,\r\n" + 
				"    \"Index\":568,\r\n" + 
				"    \"DebitAmount\":10.00,\r\n" + 
				"    \"CreditAmount\":0.00\r\n" + 
				"    }\r\n" + 
				"    ],\r\n" + 
				"    \"DocumentToApply\":\r\n" + 
				"    [\r\n" + 
				"        {\r\n" + 
				"            \"Number\":\"MISC00000000387\",\r\n" + 
				"            \"ApplyAmount\":10.00\r\n" + 
				"            	}\r\n" + 
				"                ]\r\n" + 
				"    ,\r\n" + 
				"    \"ServiceOrder\":{\r\n" + 
				"         \"Id\": \"SORD00000009019\",\r\n" + 
				"        \"Task\":{\r\n" + 
				"           \"Sequence\":1400,\r\n" + 
				"            \"EmployeeId\":\"\"}\r\n" + 
				"            }\r\n" + 
				"	}";
		String expected = "{\"Payment\":{\"Success\":true,\"Data\":{\"BatchId\":\"dep1\",\"DocumentNumber\":\"PYMT00000000529\"},\"Messages\":[{\"Enabled\":1,\"Info\":\"Service order number and\\/or task id does not exist\",\"Level\":2}]}}";
		CommonMethods.postMethodString(payload, uri, ver, expected);
		

	}

}
