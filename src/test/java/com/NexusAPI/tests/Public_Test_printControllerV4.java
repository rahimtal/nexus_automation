package com.NexusAPI.Tests;

import org.testng.annotations.Test; import org.testng.Assert;
import org.testng.annotations.Test; import org.testng.Assert;

public class Public_Test_printControllerV4 {

	/*
	 * @Test(priority = 300, groups = "printController" ) public void
	 * getprintreportPaymentPostEditListv3() throws Exception {
	 * 
	 * String uri = "/print/report/PaymentPostEditList"; String expected =
	 * "User Id: saPayment Edit List"; verifyPDFReports.verifyPDF(expected, uri);
	 * expected = "Batch Id: API20240219001"; verifyPDFReports.verifyPDF(expected,
	 * uri); expected = "1/15/2020PYMT00000000528";
	 * verifyPDFReports.verifyPDF(expected, uri); expected =
	 * "1/15/2020BILL00000000373 LOCATION008 : CUSTOMER009 ELECTRIC  21.52";
	 * verifyPDFReports.verifyPDF(expected, uri);
	 * 
	 * }
	 */
	@Test(priority = 1, groups = "printController" )
	public void getstatementAsPDFv3() throws Exception {

		String uri = "/print/statementAsPDF/83";
		String expected = "ELECWAT001-CUSTOMER007\r\n" + " 83\r\n" + " ACCOUNT INFORMATION\r\n" + "10/31/1997\r\n"
				+ " SPECIAL MESSAGE\r\n" + "Water Statement\r\n" + "Account Number:\r\n" + "Statement #:\r\n"
				+ "Bill Date:\r\n" + "Due Date:\r\n" + "Service Address:\r\n"
				+ "FOR QUESTIONS REGARDING YOUR BILL CALL (203) 847-7387\r\n" + "SALLY MACKENZIE\r\n"
				+ "NEW YORK, SD 56789\r\n" + "s56789s\r\n" + "12/01/1997\r\n" + "ELECWAT00\r\n" + "1-CUSTOME\r\n"
				+ "R007\r\n" + "SERVICE TYPE SERVICE DATES METER NUMBER CURRENT READ PREVIOUS READ GALLONS# OF DAYS\r\n"
				+ "EQUIPMENT011  750 86008/31/1997 - 10/31/1997equipment Class 001 -  61  110.00\r\n"
				+ " ACCOUNT ACTIVITY  INFORMATION\r\n" + "Previous Balance $ 667.00 \r\n" + "Fixed Charge $ 30.00 \r\n"
				+ "Stepped Ranges $ 330.00 \r\n" + "Tax - USASTCITY-6* $ 23.10 \r\n" + "Tax - USASTCITY-6* $ 26.40 \r\n"
				+ "Tax - USASTCITY-6* $ 2.10 \r\n" + "Tax - USASTCITY-6* $ 2.40 \r\n"
				+ "Amount Due if Paid By 12/01/1997 $ 1,081.00 \r\n"
				+ "Amount Due if Paid After 12/01/1997 $ 1,135.05 \r\n"
				+ "DETACH AND RETURN THIS PORTION OF THE BILL WITH YOUR PAYMENT\r\n"
				+ "Please include your Account Number on your check\r\n"
				+ "Make check payable to First District Water Dept.\r\n" + "Account Number:\r\n"
				+ "Service Address:\r\n" + "PREVIOUS\r\n" + "BALANCE\r\n" + "$1,081.00 \r\n"
				+ "ELECWAT001-CUSTOMER0\r\n" + "07 83\r\n" + "10/31/1997\r\n" + "Statement Number:\r\n"
				+ "Bill Date:\r\n" + "Due Date:\r\n" + "CURRENT\r\n" + "CHARGES\r\n" + "TOTAL DUE AMOUNT\r\n"
				+ "ENCLOSED\r\n" + "$667.00 $360.00 \r\n" + "Please check box if address or phone numbers have \r\n"
				+ "changed and indicate changes on back of stub. \r\n" + "SALLY MACKENZIE\r\n"
				+ "NEW YORK, SD 56789\r\n" + "s56789s\r\n" + "12/01/1997\r\n" + "ELECWAT001-CUSTOMER007\r\n" + " 83\r\n"
				+ " ACCOUNT INFORMATION\r\n" + "10/31/1997\r\n" + " SPECIAL MESSAGE\r\n" + "Water Statement\r\n"
				+ "Account Number:\r\n" + "Statement #:\r\n" + "Bill Date:\r\n" + "Due Date:\r\n"
				+ "Service Address:\r\n" + "FOR QUESTIONS REGARDING YOUR BILL CALL (203) 847-7387\r\n"
				+ "SALLY MACKENZIE\r\n" + "NEW YORK, SD 56789\r\n" + "s56789s\r\n" + "12/01/1997\r\n" + "ELECWAT00\r\n"
				+ "1-CUSTOME\r\n" + "R007\r\n" + "KEEP THIS PORTION FOR YOUR RECORDS\r\n" + " TOTAL DUE $ 1,081.00 \r\n"
				+ "DETACH AND RETURN THIS PORTION OF THE BILL WITH YOUR PAYMENT\r\n"
				+ "Please include your Account Number on your check\r\n"
				+ "Make check payable to First District Water Dept.\r\n" + "Account Number:\r\n"
				+ "Service Address:\r\n" + "PREVIOUS\r\n" + "BALANCE\r\n" + "$1,081.00 \r\n"
				+ "ELECWAT001-CUSTOMER0\r\n" + "07 83\r\n" + "10/31/1997\r\n" + "Statement Number:\r\n"
				+ "Bill Date:\r\n" + "Due Date:\r\n" + "CURRENT\r\n" + "CHARGES\r\n" + "TOTAL DUE AMOUNT\r\n"
				+ "ENCLOSED\r\n" + "$667.00 $360.00 \r\n" + "Please check box if address or phone numbers have \r\n"
				+ "changed and indicate changes on back of stub. \r\n" + "SALLY MACKENZIE\r\n"
				+ "NEW YORK, SD 56789\r\n" + "s56789s\r\n" + "12/01/1997\r\n" + "";

	

	}

}