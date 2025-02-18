package Private;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;
import com.NexustAPIAutomation.java.Retry;

import freemarker.core.BugException;

public class SmartyStreetValidationV4 {

	@Test(priority = 1, groups = "Smarty")
	public void postSmartyStreetValidationv4() throws ClassNotFoundException, SQLException, InterruptedException {

		CommonMethods.Bug("CPDEV-20909");
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

	@Test(priority = 1, groups = "Smarty")
	public void postvalidateAddressesandPostLocation()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/customers/validateAddresses";
		String ver = "4.0";
		String payload = "{\r\n" + 
				"    \"Address\": [\r\n" + 
				"        {\r\n" + 
				"            \"AddressId\": \"\",\r\n" + 
				"            \"AddressCode\": \"PRIMARY\",\r\n" + 
				"            \"ContactPerson\": \"\",\r\n" + 
				"            \"AddressLabel\":{\r\n" + 
				"                \"Line1\":\"1119 skylino DR\",\r\n" + 
				"                \"Line2\":\"\",\r\n" + 
				"                \"City\":\"Griffin\",\r\n" + 
				"                \"State\":\"GA\",\r\n" + 
				"                \"ZipCode\":\"302824\",\r\n" + 
				"                \"Country\":\"USA\",\r\n" + 
				"                \"IsDirty\":false\r\n" + 
				"            },\r\n" + 
				"            \"BillToAddress\": false,\r\n" + 
				"            \"Override\": {\r\n" + 
				"                \"Override\": false,\r\n" + 
				"                \"AddressCode\": \"\",\r\n" + 
				"                \"FromDate\": \"1900-01-01\",\r\n" + 
				"                \"ToDate\": \"1900-01-01\",\r\n" + 
				"                \"Recurring\": false\r\n" + 
				"            },\r\n" + 
				"            \"PhoneNumber\": [\r\n" + 
				"                {\r\n" + 
				"                    \"Number\": \"\",\r\n" + 
				"                    \"TypeId\": 0,\r\n" + 
				"                    \"Description\": \"empty\",\r\n" + 
				"                    \"IsDirty\": false\r\n" + 
				"                }\r\n" + 
				"            ],\r\n" + 
				"            \"FaxNumber\": {\r\n" + 
				"                \"Number\": \"\",\r\n" + 
				"                \"IsDirty\": false\r\n" + 
				"            }\r\n" + 
				"        }\r\n" + 
				"    ]\r\n" + 
				"}\r\n" + 
				"";
		String expected = "{ \"ValidateAddresses\": {\"Success\":true,\"Data\":{\"AddressId\":\"\",\"AddressCode\":\"PRIMARY\",\"ContactPerson\":\"\",\"AddressLabel\":{\"Line1\":\"1119 skylino DR\",\"Line2\":\"\",\"City\":\"Griffin\",\"State\":\"GA\",\"ZipCode\":\"302824\",\"Country\":\"USA\",\"IsDirty\":false},\"BillToAddress\":false,\"Override\":{\"Override\":false,\"AddressCode\":\"\",\"FromDate\":\"1900-01-01\",\"ToDate\":\"1900-01-01\",\"Recurring\":false},\"PhoneNumber\":[{\"Number\":\"\",\"TypeId\":0,\"Description\":\"empty\",\"IsDirty\":false}],\"FaxNumber\":{\"Number\":\"\",\"IsDirty\":false},\"SmartyStreetsMailingAddressCandidate\":{\"AddressId\":\"\",\"DeliveryLine1\":\"1119 Skyline Dr\",\"DeliveryLine2\":\"\",\"LastLine\":\"Griffin GA 30224-4958\",\"StreetNumber\":\"1119\",\"StreetNumberLng\":1119,\"StrNumberSuffix\":\"\",\"StreetName\":\"Skyline\",\"StreetType\":\"Dr\",\"StreetDirectionP\":\"\",\"StreetDirection\":\"\",\"AptNumber\":\"\",\"AptDescription\":\"\",\"UnitDesignation\":\"\",\"AptNumberPlus\":\"\",\"CityName\":\"Griffin\",\"State\":\"GA\",\"ZipCode\":\"30224\",\"Plus4Code\":\"4958\",\"Country\":\"USA\",\"Success\":true,\"MailingAddressMessages\":{\"Messages\":[{\"Enabled\":1,\"Info\":\"Confirmed; entire address was Delivery Point Validation confirmed deliverable.\",\"AddressCode\":\"\"},{\"Enabled\":1,\"Info\":\"City/state/ZIP + street are all valid.\",\"AddressCode\":\"\"},{\"Enabled\":1,\"Info\":\"ZIP+4 matched; confirmed entire address; address is valid.\",\"AddressCode\":\"\"}]}}},\"Messages\":[]}}";
		CommonMethods.postMethodString(payload, uri, ver, expected);
		
		uri = "/location";
		 ver = "4.0";
		 payload = "{\r\n" + 
		 		"    \"LocationId\":\"2026\",\r\n" + 
		 		"    \"Description\":\"\",\r\n" + 
		 		"    \"LocationClassId\":\"\",\r\n" + 
		 		"    \"BillToCustomerId\":\"CUSTOMER013\",\r\n" + 
		 		"    \"ZoneId\":\"\",\r\n" + 
		 		"    \"Address\":\r\n" + 
		 		"    {\r\n" + 
		 		"        \"AddressId\": \"\",\r\n" + 
		 		"        \"AddressCode\":\"PRIMARY\",\r\n" + 
		 		"        \"AddressLabel\":{\r\n" + 
		 		"                \"Line1\":\"22 Degroat Rd\",\r\n" + 
		 		"                \"Line2\":\"Building 15000\",\r\n" + 
		 		"                \"City\":\"\",\r\n" + 
		 		"                \"State\":\"NJ\",\r\n" + 
		 		"                \"ZipCode\":\"07827\",\r\n" + 
		 		"                \"Country\":\"USA\",\r\n" + 
		 		"                \"IsDirty\":true\r\n" + 
		 		"        },\r\n" + 
		 		"              \"SmartyStreetsMailingAddressCandidate\": {\r\n" + 
		 		"                \"AddressId\": \"\",\r\n" + 
		 		"                \"DeliveryLine1\": \"1119 Skyline Dr\",\r\n" + 
		 		"                \"DeliveryLine2\": \"\",\r\n" + 
		 		"                \"LastLine\": \"Griffin GA 30224-4958\",\r\n" + 
		 		"                \"StreetNumber\": \"1119\",\r\n" + 
		 		"                \"StreetNumberLng\": 1119,\r\n" + 
		 		"                \"StrNumberSuffix\": \"\",\r\n" + 
		 		"                \"StreetName\": \"Skyline\",\r\n" + 
		 		"                \"StreetType\": \"Dr\",\r\n" + 
		 		"                \"StreetDirectionP\": \"\",\r\n" + 
		 		"                \"StreetDirection\": \"\",\r\n" + 
		 		"                \"AptNumber\": \"\",\r\n" + 
		 		"                \"AptDescription\": \"\",\r\n" + 
		 		"                \"UnitDesignation\": \"\",\r\n" + 
		 		"                \"AptNumberPlus\": \"\",\r\n" + 
		 		"                \"CityName\": \"Griffin\",\r\n" + 
		 		"                \"State\": \"GA\",\r\n" + 
		 		"                \"ZipCode\": \"30224\",\r\n" + 
		 		"                \"Plus4Code\": \"4958\",\r\n" + 
		 		"                \"Country\": \"USA\",\r\n" + 
		 		"                \"Success\": true\r\n" + 
		 		"\r\n" + 
		 		"                }\r\n" + 
		 		"        },\r\n" + 
		 		"    \"Billing\":{\r\n" + 
		 		"        \"AccumulatedBilling\":{\r\n" + 
		 		"            \"Type\":1,\r\n" + 
		 		"            \"MasterId\":\"\"\r\n" + 
		 		"        },\r\n" + 
		 		"        \"Transfer\":{\r\n" + 
		 		"            \"AllowTransferWithoutFinalBill\":true,\r\n" + 
		 		"            \"MoveBalanceOnTransfer\":0\r\n" + 
		 		"        },\r\n" + 
		 		"        \"DefaultRouteId\":\"R123\",\r\n" + 
		 		"        \"FinalReadRouteId\":\"FR123\",\r\n" + 
		 		"        \"BillingCycle\":{\r\n" + 
		 		"            \"Override\":true,\r\n" + 
		 		"            \"Id\":\"MONTHLY\"\r\n" + 
		 		"        },\r\n" + 
		 		"        \"LocationPaymentTermId\":\"PT123\",\r\n" + 
		 		"        \"PaymentTermOverrideId\":\"PTO123\",\r\n" + 
		 		"        \"HDDZoneId\":\"HDD123\",\r\n" + 
		 		"        \"AllowMinimumBill\":true,\r\n" + 
		 		"        \"AllowMaximumBill\":true,\r\n" + 
		 		"        \"ItemizedStatement\":true,\r\n" + 
		 		"        \"NumberOfBillCopies\":1,\r\n" + 
		 		"        \"AccumulateAccessEnergy\":true,\r\n" + 
		 		"        \"EligibleForRecall\":true\r\n" + 
		 		"    },\r\n" + 
		 		"    \"CustomerOptions\":{\r\n" + 
		 		"        \"Customer1Id\":\"\",\r\n" + 
		 		"        \"Customer2Id\":\"\",\r\n" + 
		 		"        \"Customer3Id\":\"\",\r\n" + 
		 		"        \"DefaultMoveIn\":{\r\n" + 
		 		"            \"Enabled\":true,\r\n" + 
		 		"            \"CustomerMoveInId\": 1,\r\n" + 
		 		"            \"TaxScheduleId\": \"\"\r\n" + 
		 		"        }\r\n" + 
		 		"    },\r\n" + 
		 		"    \"LocationOptions\":{\r\n" + 
		 		"        \"AssessorId\":\"\",\r\n" + 
		 		"        \"PlanNumber\":\"\",\r\n" + 
		 		"        \"KeyNumber\":\"\",\r\n" + 
		 		"        \"SICCode\":\"S123\",\r\n" + 
		 		"        \"LotNumber\":\"L123\",\r\n" + 
		 		"        \"NumberOfResidentialUnits\":10,\r\n" + 
		 		"        \"RevenueDistrictId\":\"RD123\",\r\n" + 
		 		"        \"ReceiptPoint\":0,\r\n" + 
		 		"        \"GISCoordinates\":{\r\n" + 
		 		"            \"X\":\"X123\",\r\n" + 
		 		"            \"Y\":\"Y123\"\r\n" + 
		 		"        },\r\n" + 
		 		"        \"ExcludeFromArchiving\":true,\r\n" + 
		 		"        \"PrepayStatus\":true\r\n" + 
		 		"    },\r\n" + 
		 		"    \"CollectionsOptions\":{\r\n" + 
		 		"        \"CurrentCustomerCollectionType\":\"CURRENT\",\r\n" + 
		 		"        \"FormerCustomerCollectionType\":\"FORMER\",\r\n" + 
		 		"        \"BudgetCustomerCollectionType\":\"BUDGET\",\r\n" + 
		 		"        \"SpaCustomerCollectionType\":\"\",\r\n" + 
		 		"        \"DisconnectExempt\":{\r\n" + 
		 		"            \"ExemptType\":\"SPECIAL STATUS\",\r\n" + 
		 		"            \"EffectiveStartDate\":\"2023-01-01\",\r\n" + 
		 		"            \"EffectiveEndDate\":\"2023-12-31\",\r\n" + 
		 		"            \"Reoccurring\":true\r\n" + 
		 		"        },\r\n" + 
		 		"         \"PenaltyExempt\":{\r\n" + 
		 		"            \"Enabled\": false,\r\n" + 
		 		"            \"ExemptType\":\"\",\r\n" + 
		 		"            \"EffectiveStartDate\":\"1900-01-01\",\r\n" + 
		 		"            \"EffectiveEndDate\":\"1900-12-31\"\r\n" + 
		 		"        },\r\n" + 
		 		"        \"NoExtension\":true\r\n" + 
		 		"    },\r\n" + 
		 		"    \"ServiceOptions\":{\r\n" + 
		 		"        \"Service\":[\r\n" + 
		 		"            {\r\n" + 
		 		"                \"CategoryId\":1,\r\n" + 
		 		"                \"Estimate\":true,\r\n" + 
		 		"                \"PenaltyId\":\"5%\",\r\n" + 
		 		"                \"AccountReceivableIndex\":1,\r\n" + 
		 		"                \"TaxScheduleId\":\"\"\r\n" + 
		 		"            },\r\n" + 
		 		"            {\r\n" + 
		 		"                \"CategoryId\":2,\r\n" + 
		 		"                \"Estimate\":true,\r\n" + 
		 		"                \"PenaltyId\":\"5%\",\r\n" + 
		 		"                \"AccountReceivableIndex\":1,\r\n" + 
		 		"                \"TaxScheduleId\":\"\"\r\n" + 
		 		"            },\r\n" + 
		 		"            {\r\n" + 
		 		"                \"CategoryId\":3,\r\n" + 
		 		"                \"Estimate\":false,\r\n" + 
		 		"                \"PenaltyId\":\"5%\",\r\n" + 
		 		"                \"AccountReceivableIndex\":1,\r\n" + 
		 		"                \"TaxScheduleId\":\"\"\r\n" + 
		 		"            },\r\n" + 
		 		"                {\r\n" + 
		 		"                \"CategoryId\":4,\r\n" + 
		 		"                \"Estimate\":true,\r\n" + 
		 		"                \"PenaltyId\":\"5%\",\r\n" + 
		 		"                \"AccountReceivableIndex\":1,\r\n" + 
		 		"                \"TaxScheduleId\":\"\"\r\n" + 
		 		"            },\r\n" + 
		 		"            {\r\n" + 
		 		"                \"CategoryId\":5,\r\n" + 
		 		"                \"Estimate\":true,\r\n" + 
		 		"                \"PenaltyId\":\"5%\",\r\n" + 
		 		"                \"AccountReceivableIndex\":1,\r\n" + 
		 		"                \"TaxScheduleId\":\"\"\r\n" + 
		 		"            },\r\n" + 
		 		"            {\r\n" + 
		 		"                \"CategoryId\":6,\r\n" + 
		 		"                \"Estimate\":false,\r\n" + 
		 		"                \"PenaltyId\":\"5%\",\r\n" + 
		 		"                \"AccountReceivableIndex\":1,\r\n" + 
		 		"                \"TaxScheduleId\":\"\"\r\n" + 
		 		"            }\r\n" + 
		 		"        ]\r\n" + 
		 		"    },\r\n" + 
		 		"    \"AddressConfirm\":true,\r\n" + 
		 		"    \"Confirm\":true\r\n" + 
		 		"}";
		 expected = "{\"Location\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Location created successfully\",\"Level\":1}]}}";
		CommonMethods.postMethodString(payload, uri, ver, expected);
		
		
		uri = "/location";
		 ver = "4.0";
		 payload = "{\r\n" + 
		 		"    \"LocationId\":\"2026\",\r\n" + 
		 		"    \"Description\":\"\",\r\n" + 
		 		"    \"LocationClassId\":\"\",\r\n" + 
		 		"    \"BillToCustomerId\":\"CUSTOMER013\",\r\n" + 
		 		"    \"ZoneId\":\"\",\r\n" + 
		 		"    \"Address\":\r\n" + 
		 		"    {\r\n" + 
		 		"        \"AddressId\": \"265\",\r\n" + 
		 		"        \"AddressCode\":\"PRIMARY\",\r\n" + 
		 		"        \"AddressLabel\":{\r\n" + 
		 		"                \"Line1\":\"22 Degroat Rd\",\r\n" + 
		 		"                \"Line2\":\"Building 15000\",\r\n" + 
		 		"                \"City\":\"\",\r\n" + 
		 		"                \"State\":\"NJ\",\r\n" + 
		 		"                \"ZipCode\":\"07822\",\r\n" + 
		 		"                \"Country\":\"USA\",\r\n" + 
		 		"                \"IsDirty\":true\r\n" + 
		 		"        },\r\n" + 
		 		"              \"SmartyStreetsMailingAddressCandidate\": {\r\n" + 
		 		"                \"AddressId\": \"124\",\r\n" + 
		 		"                \"DeliveryLine1\": \"1119 Skyline Dr\",\r\n" + 
		 		"                \"DeliveryLine2\": \"\",\r\n" + 
		 		"                \"LastLine\": \"Griffin GA 30224-4958\",\r\n" + 
		 		"                \"StreetNumber\": \"1119\",\r\n" + 
		 		"                \"StreetNumberLng\": 1119,\r\n" + 
		 		"                \"StrNumberSuffix\": \"\",\r\n" + 
		 		"                \"StreetName\": \"Skyline\",\r\n" + 
		 		"                \"StreetType\": \"Dr\",\r\n" + 
		 		"                \"StreetDirectionP\": \"\",\r\n" + 
		 		"                \"StreetDirection\": \"\",\r\n" + 
		 		"                \"AptNumber\": \"\",\r\n" + 
		 		"                \"AptDescription\": \"\",\r\n" + 
		 		"                \"UnitDesignation\": \"\",\r\n" + 
		 		"                \"AptNumberPlus\": \"\",\r\n" + 
		 		"                \"CityName\": \"Griffin\",\r\n" + 
		 		"                \"State\": \"GA\",\r\n" + 
		 		"                \"ZipCode\": \"30224\",\r\n" + 
		 		"                \"Plus4Code\": \"4958\",\r\n" + 
		 		"                \"Country\": \"USA\",\r\n" + 
		 		"                \"Success\": true\r\n" + 
		 		"\r\n" + 
		 		"                }\r\n" + 
		 		"        },\r\n" + 
		 		"    \"Billing\":{\r\n" + 
		 		"        \"AccumulatedBilling\":{\r\n" + 
		 		"            \"Type\":1,\r\n" + 
		 		"            \"MasterId\":\"\"\r\n" + 
		 		"        },\r\n" + 
		 		"        \"Transfer\":{\r\n" + 
		 		"            \"AllowTransferWithoutFinalBill\":true,\r\n" + 
		 		"            \"MoveBalanceOnTransfer\":0\r\n" + 
		 		"        },\r\n" + 
		 		"        \"DefaultRouteId\":\"R123\",\r\n" + 
		 		"        \"FinalReadRouteId\":\"FR123\",\r\n" + 
		 		"        \"BillingCycle\":{\r\n" + 
		 		"            \"Override\":true,\r\n" + 
		 		"            \"Id\":\"MONTHLY\"\r\n" + 
		 		"        },\r\n" + 
		 		"        \"LocationPaymentTermId\":\"PT123\",\r\n" + 
		 		"        \"PaymentTermOverrideId\":\"PTO123\",\r\n" + 
		 		"        \"HDDZoneId\":\"HDD123\",\r\n" + 
		 		"        \"AllowMinimumBill\":true,\r\n" + 
		 		"        \"AllowMaximumBill\":true,\r\n" + 
		 		"        \"ItemizedStatement\":true,\r\n" + 
		 		"        \"NumberOfBillCopies\":1,\r\n" + 
		 		"        \"AccumulateAccessEnergy\":true,\r\n" + 
		 		"        \"EligibleForRecall\":true\r\n" + 
		 		"    },\r\n" + 
		 		"    \"CustomerOptions\":{\r\n" + 
		 		"        \"Customer1Id\":\"\",\r\n" + 
		 		"        \"Customer2Id\":\"\",\r\n" + 
		 		"        \"Customer3Id\":\"\",\r\n" + 
		 		"        \"DefaultMoveIn\":{\r\n" + 
		 		"            \"Enabled\":true,\r\n" + 
		 		"            \"CustomerMoveInId\": 1,\r\n" + 
		 		"            \"TaxScheduleId\": \"\"\r\n" + 
		 		"        }\r\n" + 
		 		"    },\r\n" + 
		 		"    \"LocationOptions\":{\r\n" + 
		 		"        \"AssessorId\":\"\",\r\n" + 
		 		"        \"PlanNumber\":\"\",\r\n" + 
		 		"        \"KeyNumber\":\"\",\r\n" + 
		 		"        \"SICCode\":\"S123\",\r\n" + 
		 		"        \"LotNumber\":\"L123\",\r\n" + 
		 		"        \"NumberOfResidentialUnits\":10,\r\n" + 
		 		"        \"RevenueDistrictId\":\"RD123\",\r\n" + 
		 		"        \"ReceiptPoint\":0,\r\n" + 
		 		"        \"GISCoordinates\":{\r\n" + 
		 		"            \"X\":\"X123\",\r\n" + 
		 		"            \"Y\":\"Y123\"\r\n" + 
		 		"        },\r\n" + 
		 		"        \"ExcludeFromArchiving\":true,\r\n" + 
		 		"        \"PrepayStatus\":true\r\n" + 
		 		"    },\r\n" + 
		 		"    \"CollectionsOptions\":{\r\n" + 
		 		"        \"CurrentCustomerCollectionType\":\"CURRENT\",\r\n" + 
		 		"        \"FormerCustomerCollectionType\":\"FORMER\",\r\n" + 
		 		"        \"BudgetCustomerCollectionType\":\"BUDGET\",\r\n" + 
		 		"        \"SpaCustomerCollectionType\":\"\",\r\n" + 
		 		"        \"DisconnectExempt\":{\r\n" + 
		 		"            \"ExemptType\":\"SPECIAL STATUS\",\r\n" + 
		 		"            \"EffectiveStartDate\":\"2023-01-01\",\r\n" + 
		 		"            \"EffectiveEndDate\":\"2023-12-31\",\r\n" + 
		 		"            \"Reoccurring\":true\r\n" + 
		 		"        },\r\n" + 
		 		"         \"PenaltyExempt\":{\r\n" + 
		 		"            \"Enabled\": false,\r\n" + 
		 		"            \"ExemptType\":\"\",\r\n" + 
		 		"            \"EffectiveStartDate\":\"1900-01-01\",\r\n" + 
		 		"            \"EffectiveEndDate\":\"1900-12-31\"\r\n" + 
		 		"        },\r\n" + 
		 		"        \"NoExtension\":true\r\n" + 
		 		"    },\r\n" + 
		 		"    \"ServiceOptions\":{\r\n" + 
		 		"        \"Service\":[\r\n" + 
		 		"            {\r\n" + 
		 		"                \"CategoryId\":1,\r\n" + 
		 		"                \"Estimate\":true,\r\n" + 
		 		"                \"PenaltyId\":\"5%\",\r\n" + 
		 		"                \"AccountReceivableIndex\":1,\r\n" + 
		 		"                \"TaxScheduleId\":\"\"\r\n" + 
		 		"            },\r\n" + 
		 		"            {\r\n" + 
		 		"                \"CategoryId\":2,\r\n" + 
		 		"                \"Estimate\":true,\r\n" + 
		 		"                \"PenaltyId\":\"5%\",\r\n" + 
		 		"                \"AccountReceivableIndex\":1,\r\n" + 
		 		"                \"TaxScheduleId\":\"\"\r\n" + 
		 		"            },\r\n" + 
		 		"            {\r\n" + 
		 		"                \"CategoryId\":3,\r\n" + 
		 		"                \"Estimate\":false,\r\n" + 
		 		"                \"PenaltyId\":\"5%\",\r\n" + 
		 		"                \"AccountReceivableIndex\":1,\r\n" + 
		 		"                \"TaxScheduleId\":\"\"\r\n" + 
		 		"            },\r\n" + 
		 		"                {\r\n" + 
		 		"                \"CategoryId\":4,\r\n" + 
		 		"                \"Estimate\":true,\r\n" + 
		 		"                \"PenaltyId\":\"5%\",\r\n" + 
		 		"                \"AccountReceivableIndex\":1,\r\n" + 
		 		"                \"TaxScheduleId\":\"\"\r\n" + 
		 		"            },\r\n" + 
		 		"            {\r\n" + 
		 		"                \"CategoryId\":5,\r\n" + 
		 		"                \"Estimate\":true,\r\n" + 
		 		"                \"PenaltyId\":\"5%\",\r\n" + 
		 		"                \"AccountReceivableIndex\":1,\r\n" + 
		 		"                \"TaxScheduleId\":\"\"\r\n" + 
		 		"            },\r\n" + 
		 		"            {\r\n" + 
		 		"                \"CategoryId\":6,\r\n" + 
		 		"                \"Estimate\":false,\r\n" + 
		 		"                \"PenaltyId\":\"5%\",\r\n" + 
		 		"                \"AccountReceivableIndex\":1,\r\n" + 
		 		"                \"TaxScheduleId\":\"\"\r\n" + 
		 		"            }\r\n" + 
		 		"        ]\r\n" + 
		 		"    },\r\n" + 
		 		"    \"AddressConfirm\":true,\r\n" + 
		 		"    \"Confirm\":true\r\n" + 
		 		"}";
		 expected = "{\"Location\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Location updated successfully\",\"Level\":1}]}}";
		 CommonMethods.putMethodstring(uri, ver, payload, expected);
		

	}

}