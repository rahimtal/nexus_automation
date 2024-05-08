package Public;

import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.verifyPDFReports;

public class printControllerV4 {

	//@Test(priority = 300, groups = "printController")
	public void getprintreportPaymentPostEditListv3() throws Exception {

		String uri = "/print/report/PaymentPostEditList";
		String expected = "User Id: saPayment Edit List";
		verifyPDFReports.verifyPDF(expected, uri);
		expected = "Batch Id: API20240219001";
		verifyPDFReports.verifyPDF(expected, uri);
		expected = "1/15/2020PYMT00000000528";
		verifyPDFReports.verifyPDF(expected, uri);
		expected = "1/15/2020BILL00000000373 LOCATION008 : CUSTOMER009 ELECTRIC  21.52";
		verifyPDFReports.verifyPDF(expected, uri);

	}

}