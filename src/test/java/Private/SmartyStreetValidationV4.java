package Private;

import java.sql.SQLException;

import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;
import com.NexustAPIAutomation.java.Retry;

public class SmartyStreetValidationV4 {

	@Test(priority = 1, groups = "Smarty", retryAnalyzer = Retry.class)
	public void postSmartyStreetValidationv4() throws ClassNotFoundException, SQLException, InterruptedException {

		String uri = "/customers/validateAddresses";
		String ver = "4.0";
		String payload = "{\r\n" + "   \"ValidateAddresses\":[\r\n" + "      {\r\n" + "         \"Address\":[\r\n"
				+ "            {\r\n" + "               \"AddressId\":\"\",\r\n"
				+ "               \"AddressCode\":\"Primary\",\r\n" + "               \"ContactPerson\":\"\",\r\n"
				+ "               \"AddressLabel\":{\r\n" + "                  \"Line1\":\"11985SW15thStBldg182\",\r\n"
				+ "                  \"City\":\"PembrokePines\",\r\n" + "                  \"State\":\"FL\",\r\n"
				+ "                  \"ZipCode\":\"330255785\",\r\n" + "                  \"Country\":\"USA\"\r\n"
				+ "               },\r\n" + "               \"Override\":{\r\n"
				+ "                  \"Override\":\"0\",\r\n" + "                  \"AddressCode\":\"\",\r\n"
				+ "                  \"FromDate\":\"1900-01-01\",\r\n"
				+ "                  \"ToDate\":\"1900-01-01\",\r\n" + "                  \"Recurring\":\"0\"\r\n"
				+ "               },\r\n" + "               \"PhoneNumber\":[\r\n" + "                  {\r\n"
				+ "                     \"Number\":\"9056150000\",\r\n" + "                     \"TypeId\":\"1\",\r\n"
				+ "                     \"Description\":\"Phone1\"\r\n" + "                  },\r\n"
				+ "                  {\r\n" + "                     \"Number\":\"9056150002\",\r\n"
				+ "                     \"TypeId\":\"2\",\r\n" + "                     \"Description\":\"Phone2\"\r\n"
				+ "                  },\r\n" + "                  {\r\n"
				+ "                     \"Number\":\"9056150003\",\r\n" + "                     \"TypeId\":\"3\",\r\n"
				+ "                     \"Description\":\"Phone3\"\r\n" + "                  }\r\n"
				+ "               ],\r\n" + "               \"FaxNumber\":{\r\n"
				+ "                  \"Number\":\"50688943210000\"\r\n" + "               }\r\n" + "            }\r\n"
				+ "         ],\r\n" + "         \"Acknowledge\":0,\r\n" + "         \"AddressConfirm\":0,\r\n"
				+ "         \"Success\":false,\r\n" + "         \"Messages\":[\r\n" + "            {\r\n"
				+ "               \"Enabled\":1,\r\n" + "               \"Info\":\"\",\r\n"
				+ "               \"AddressCode\":\"\"\r\n" + "            }\r\n" + "         ]\r\n" + "      }\r\n"
				+ "   ]\r\n" + "}";
		String expected = "{ \"ValidateAddresses\": [ {\"Address\":[{\"AddressId\":\"\",\"AddressCode\":\"Primary\",\"ContactPerson\":\"\",\"AddressLabel\":{\"Line1\":\"11985 Sw15thstbldg182\",\"Line2\":\"\",\"City\":\"Pembrokepines\",\"State\":\"FL\",\"ZipCode\":\"330255785\",\"Country\":\"USA\"},\"Override\":{\"Override\":\"0\",\"AddressCode\":\"\",\"FromDate\":\"1900-01-01\",\"ToDate\":\"1900-01-01\",\"Recurring\":\"0\"},\"PhoneNumber\":[{\"Number\":\"9056150000\",\"TypeId\":\"1\",\"Description\":\"Phone1\"},{\"Number\":\"9056150002\",\"TypeId\":\"2\",\"Description\":\"Phone2\"},{\"Number\":\"9056150003\",\"TypeId\":\"3\",\"Description\":\"Phone3\"}],\"FaxNumber\":{\"Number\":\"50688943210000\"},\"SmartyStreetsMailingAddressCandidate\":{\"AddressId\":\"\",\"DeliveryLine1\":\"11985SW15thStBldg182\",\"DeliveryLine2\":\"\",\"LastLine\":\"PembrokePines FL 330255785\",\"StreetNumber\":\"11985\",\"StreetNumberLng\":11985,\"StrNumberSuffix\":\"\",\"StreetName\":\"Sw15thstbldg182\",\"StreetType\":\"\",\"StreetDirectionP\":\"\",\"StreetDirection\":\"\",\"AptNumber\":\"\",\"AptDescription\":\"\",\"UnitDesignation\":\"\",\"AptNumberPlus\":\"\",\"CityName\":\"Pembrokepines\",\"State\":\"FL\",\"ZipCode\":\"330255785\",\"Country\":\"USA\",\"Success\":false,\"MailingAddressMessages\":{\"Messages\":[{\"Enabled\":1,\"Info\":\"Save the following address?</br><p style=\\\"margin-left: 40px;\\\"><b>Address 1:</b> \\\"11985 Sw15thstbldg182\\\"</br><b>Address 2:</b> \\\"\\\"</br><b>City:</b> \\\"Pembrokepines\\\",</br><b>State:</b> \\\"FL\\\",</br><b>Zip Code:</b> \\\"330255785\\\",</br><b>Country:</b> \\\"USA\\\"</p>Additional Messaging Information:\",\"AddressCode\":\"\"},{\"Enabled\":1,\"Info\":\"ZIP+4 not matched; address is invalid. (City/state/ZIP + street don't match.)\",\"AddressCode\":\"\"}]}}}],\"Acknowledge\":1,\"AddressConfirm\":0,\"Success\":false,\"Messages\":[{\"Enabled\":1,\"Info\":\"Save the following address?</br><p style=\\\"margin-left: 40px;\\\"><b>Address 1:</b> \\\"11985 Sw15thstbldg182\\\"</br><b>Address 2:</b> \\\"\\\"</br><b>City:</b> \\\"Pembrokepines\\\",</br><b>State:</b> \\\"FL\\\",</br><b>Zip Code:</b> \\\"330255785\\\",</br><b>Country:</b> \\\"USA\\\"</p>Additional Messaging Information:\",\"AddressCode\":\"\"},{\"Enabled\":1,\"Info\":\"ZIP+4 not matched; address is invalid. (City/state/ZIP + street don't match.)\",\"AddressCode\":\"\"}]}]}";
		CommonMethods.postMethodString(payload, uri, ver, expected);

	}

}