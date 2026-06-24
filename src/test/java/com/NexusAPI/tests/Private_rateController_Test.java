package com.NexusAPI.Tests;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.xmlbeans.impl.soap.Detail;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;

import io.restassured.path.json.JsonPath;

public class Private_rateController_Test extends BaseClass {

	@Test(priority = 1, groups = "rate")
	public void geteffectiveDates() throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/EMP-1/effectiveDates";
		String ver = "4.0";
		String expected = "{\"Rate\":{\"Success\":true,\"Data\":{\"EffectiveDate\":[{\"StartDate\":\"1998-01-01\",\"EndDate\":\"1900-01-01\"}]},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result, expected);

	}

	@Test(priority = 2, groups = "rate")
	public void geteffectiveDatesInvalidURI()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/INVALID/effectiveDates";
		String ver = "4.0";
		String expected = "{\"Rate\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Rate INVALID does not exist.\",\"Level\":2}]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result, expected);

	}

	@Test(priority = 3, groups = "rate")
	public void getrateID()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/EMP-1";
		String ver = "4.0";
		String expected = "{\"Rate\":{\"Success\":true,\"Data\":{\"RateId\":\"EMP-1\",\"Description\":\"Electric Medium Power (13)\",\"Type\":{\"Id\":1,\"Description\":\"Consumption\"},\"ServiceType\":\"ELECTRIC\",\"RateClassId\":\"\",\"BillingMessageExist\":true,\"Active\":true,\"UseLatestRateEffectivePeriod\":false,\"ConsecutiveEstimatesAllowed\":0,\"BillInAdvance\":false,\"LookupVisible\":false,\"SpecialCondition\":false,\"TimeOfUse\":false,\"ExcludeFromBd\":false,\"RatchetDemand\":true,\"KvarFactor\":0.00000,\"RateInUse\":true,\"EffectiveDate\":[{\"EffectiveStartDate\":\"1998-01-01\",\"MinimumAmount\":0.00,\"MaximumAmount\":0.00,\"ProrateMinimum\":{\"First\":false,\"Regular\":false,\"Last\":false},\"ProrateMaximum\":{\"First\":false,\"Regular\":false,\"Last\":false}}],\"Detail\":[{\"DetailIndex\":1,\"Detail\":{\"Type\":1,\"Description\":\"Fixed Charge\"},\"EffectiveStartDate\":\"1998-01-01\",\"EffectiveEndDate\":\"1900-01-01\",\"DetailDescription\":\"Electric Monhtly Charge\",\"TaxSchedule\":\"USASTCITY-6*\",\"ServiceType\":\"ELECTRIC\",\"BillingFrequency\":90,\"ProrateDetail\":{\"First\":true,\"Regular\":false,\"Last\":true},\"ProrateMinimum\":{\"First\":false,\"Regular\":false,\"Last\":false},\"MinimumCharge\":0.00,\"UnitDescription\":\"\",\"FixedCharge\":16.42000,\"Consumption\":{\"BillingDemandMinimum\":0.00,\"ConsumptionTolerance\":0.00000,\"UseActualDays\":false,\"Reporting\":{\"IncludeUnits\":true,\"IncludeRevenue\":true},\"ExportDetail\":false,\"ApplyDiscountPercentage\":0,\"WinterNormalizationAdjustment\":{\"Type\":0,\"TypeDetail\":0},\"CustomerChoice\":0,\"OldestEstimateUpdateDate\":\"1900-01-01\"},\"RevenueAccount\":{\"Index\":515,\"Number\":\"900-4611-00\",\"Description\":\"Metered Sales to Residential - Electric\"},\"ReceivableAccount\":{\"Index\":506,\"Number\":\"900-1410-00\",\"Description\":\"Customer Accounts Receivable - Electric\"},\"DetailSequence\":null,\"MeterSizeMinimum\":[],\"AutomaticEstimates\":null},{\"DetailIndex\":2,\"Detail\":{\"Type\":4,\"Description\":\"Adjustable Var Stepped Range\"},\"EffectiveStartDate\":\"1998-01-01\",\"EffectiveEndDate\":\"1900-01-01\",\"DetailDescription\":\"Electric Energy Charge MP-1\",\"TaxSchedule\":\"USASTCITY-6*\",\"ServiceType\":\"ELECTRIC\",\"BillingFrequency\":90,\"ProrateDetail\":{\"First\":false,\"Regular\":false,\"Last\":false},\"ProrateMinimum\":{\"First\":false,\"Regular\":false,\"Last\":false},\"MinimumCharge\":0.00,\"UnitDescription\":\"\",\"FixedCharge\":0.00000,\"Consumption\":{\"BillingDemandMinimum\":0.00,\"ConsumptionTolerance\":0.00000,\"UseActualDays\":false,\"Reporting\":{\"IncludeUnits\":true,\"IncludeRevenue\":true},\"ExportDetail\":false,\"ApplyDiscountPercentage\":0,\"WinterNormalizationAdjustment\":{\"Type\":0,\"TypeDetail\":0},\"CustomerChoice\":0,\"OldestEstimateUpdateDate\":\"1900-01-01\"},\"RevenueAccount\":{\"Index\":515,\"Number\":\"900-4611-00\",\"Description\":\"Metered Sales to Residential - Electric\"},\"ReceivableAccount\":{\"Index\":506,\"Number\":\"900-1410-00\",\"Description\":\"Customer Accounts Receivable - Electric\"},\"DetailSequence\":[{\"DetailIndexSequence\":1,\"UnitRate\":0.10689,\"VolumeLowerLimit\":0,\"VolumeUpperLimit\":0,\"VolumeLowerLimitString\":\"0\",\"VolumeUpperLimitString\":\"3000\"},{\"DetailIndexSequence\":2,\"UnitRate\":0.09789,\"VolumeLowerLimit\":0,\"VolumeUpperLimit\":0,\"VolumeLowerLimitString\":\"3000+1\",\"VolumeUpperLimitString\":\"10000\"},{\"DetailIndexSequence\":3,\"UnitRate\":0.08560,\"VolumeLowerLimit\":0,\"VolumeUpperLimit\":0,\"VolumeLowerLimitString\":\"10000+1\",\"VolumeUpperLimitString\":\"200000\"},{\"DetailIndexSequence\":4,\"UnitRate\":0.01089,\"VolumeLowerLimit\":0,\"VolumeUpperLimit\":0,\"VolumeLowerLimitString\":\"200000+1\",\"VolumeUpperLimitString\":\"BD*200\"},{\"DetailIndexSequence\":5,\"UnitRate\":0.01078,\"VolumeLowerLimit\":0,\"VolumeUpperLimit\":0,\"VolumeLowerLimitString\":\"BD*200+1\",\"VolumeUpperLimitString\":\"BD*400\"},{\"DetailIndexSequence\":6,\"UnitRate\":0.00989,\"VolumeLowerLimit\":0,\"VolumeUpperLimit\":0,\"VolumeLowerLimitString\":\"BD*400+1\",\"VolumeUpperLimitString\":\"BD*600\"},{\"DetailIndexSequence\":7,\"UnitRate\":0.00787,\"VolumeLowerLimit\":0,\"VolumeUpperLimit\":0,\"VolumeLowerLimitString\":\"BD*600+1\",\"VolumeUpperLimitString\":\"999999999\"}],\"MeterSizeMinimum\":[],\"AutomaticEstimates\":null}]},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result.replace("\"MeterSizeMinimum\":null", "\"MeterSizeMinimum\":[]"), expected);

	}

	@Test(priority = 4, groups = "rate")
	public void getrateID2()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/EOL-1HPS100WOB";
		String ver = "4.0";
		String expected = "{\"Rate\":{\"Success\":true,\"Data\":{\"RateId\":\"EOL-1HPS100WOB\",\"Description\":\"Outdoor Lighting-High Pressure Sodium 100Watt Open Bottom\",\"Type\":{\"Id\":1,\"Description\":\"Consumption\"},\"ServiceType\":\"ST-LIGHTS\",\"RateClassId\":\"\",\"BillingMessageExist\":false,\"Active\":true,\"UseLatestRateEffectivePeriod\":false,\"ConsecutiveEstimatesAllowed\":0,\"BillInAdvance\":false,\"LookupVisible\":false,\"SpecialCondition\":false,\"TimeOfUse\":false,\"ExcludeFromBd\":false,\"RatchetDemand\":false,\"KvarFactor\":0.00000,\"RateInUse\":true,\"EffectiveDate\":[{\"EffectiveStartDate\":\"1999-01-01\",\"MinimumAmount\":0.00,\"MaximumAmount\":0.00,\"ProrateMinimum\":{\"First\":false,\"Regular\":false,\"Last\":false},\"ProrateMaximum\":{\"First\":false,\"Regular\":false,\"Last\":false}}],\"Detail\":[{\"DetailIndex\":1,\"Detail\":{\"Type\":1,\"Description\":\"Fixed Charge\"},\"EffectiveStartDate\":\"1999-01-01\",\"EffectiveEndDate\":\"1900-01-01\",\"DetailDescription\":\"Monthly Charge-street lights\",\"TaxSchedule\":\"USALLEXMPT-0\",\"ServiceType\":\"ST-LIGHTS\",\"BillingFrequency\":30,\"ProrateDetail\":{\"First\":true,\"Regular\":false,\"Last\":true},\"ProrateMinimum\":{\"First\":false,\"Regular\":false,\"Last\":false},\"MinimumCharge\":0.00,\"UnitDescription\":\"\",\"FixedCharge\":16.00000,\"Consumption\":{\"BillingDemandMinimum\":0.00,\"ConsumptionTolerance\":0.00000,\"UseActualDays\":false,\"Reporting\":{\"IncludeUnits\":true,\"IncludeRevenue\":true},\"ExportDetail\":false,\"ApplyDiscountPercentage\":0,\"WinterNormalizationAdjustment\":{\"Type\":0,\"TypeDetail\":0},\"CustomerChoice\":0,\"OldestEstimateUpdateDate\":\"1900-01-01\"},\"RevenueAccount\":{\"Index\":524,\"Number\":\"900-4614-00\",\"Description\":\"Metered Sales to Public Auth. - Electric\"},\"ReceivableAccount\":{\"Index\":506,\"Number\":\"900-1410-00\",\"Description\":\"Customer Accounts Receivable - Electric\"},\"DetailSequence\":null,\"MeterSizeMinimum\":[],\"AutomaticEstimates\":null}]},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result.replace("\"MeterSizeMinimum\":null", "\"MeterSizeMinimum\":[]"), expected);

	}

	@Test(priority = 5, groups = "rate")
	public void getrateID3()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/EPCA-1";
		String ver = "4.0";
		String expected = "{\"Rate\":{\"Success\":true,\"Data\":{\"RateId\":\"EPCA-1\",\"Description\":\"Electric Power Cost Adjustment\",\"Type\":{\"Id\":1,\"Description\":\"Consumption\"},\"ServiceType\":\"ELECTRIC\",\"RateClassId\":\"\",\"BillingMessageExist\":false,\"Active\":true,\"UseLatestRateEffectivePeriod\":false,\"ConsecutiveEstimatesAllowed\":0,\"BillInAdvance\":false,\"LookupVisible\":false,\"SpecialCondition\":false,\"TimeOfUse\":false,\"ExcludeFromBd\":false,\"RatchetDemand\":true,\"KvarFactor\":0.00000,\"RateInUse\":true,\"EffectiveDate\":[{\"EffectiveStartDate\":\"1998-01-01\",\"MinimumAmount\":0.00,\"MaximumAmount\":0.00,\"ProrateMinimum\":{\"First\":false,\"Regular\":false,\"Last\":false},\"ProrateMaximum\":{\"First\":false,\"Regular\":false,\"Last\":false}}],\"Detail\":[{\"DetailIndex\":1,\"Detail\":{\"Type\":2,\"Description\":\"Stepped Range\"},\"EffectiveStartDate\":\"1998-01-01\",\"EffectiveEndDate\":\"1900-01-01\",\"DetailDescription\":\"PCA for Electric Service\",\"TaxSchedule\":\"USASTCITY-6*\",\"ServiceType\":\"ELECTRIC\",\"BillingFrequency\":90,\"ProrateDetail\":{\"First\":false,\"Regular\":false,\"Last\":false},\"ProrateMinimum\":{\"First\":false,\"Regular\":false,\"Last\":false},\"MinimumCharge\":0.00,\"UnitDescription\":\"\",\"FixedCharge\":0.00000,\"Consumption\":{\"BillingDemandMinimum\":0.00,\"ConsumptionTolerance\":0.00000,\"UseActualDays\":false,\"Reporting\":{\"IncludeUnits\":true,\"IncludeRevenue\":true},\"ExportDetail\":false,\"ApplyDiscountPercentage\":0,\"WinterNormalizationAdjustment\":{\"Type\":0,\"TypeDetail\":0},\"CustomerChoice\":0,\"OldestEstimateUpdateDate\":\"1900-01-01\"},\"RevenueAccount\":{\"Index\":515,\"Number\":\"900-4611-00\",\"Description\":\"Metered Sales to Residential - Electric\"},\"ReceivableAccount\":{\"Index\":506,\"Number\":\"900-1410-00\",\"Description\":\"Customer Accounts Receivable - Electric\"},\"DetailSequence\":[{\"DetailIndexSequence\":1,\"UnitRate\":0.53000,\"VolumeLowerLimit\":0,\"VolumeUpperLimit\":999999999,\"VolumeLowerLimitString\":\"\",\"VolumeUpperLimitString\":\"\"}],\"MeterSizeMinimum\":[],\"AutomaticEstimates\":null}]},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result.replace("\"MeterSizeMinimum\":null", "\"MeterSizeMinimum\":[]"), expected);

	}

	@Test(priority = 6, groups = "rate")
	public void getrateID4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/INTERNETFIX";
		String ver = "4.0";
		String expected = "{\"Rate\":{\"Success\":true,\"Data\":{\"RateId\":\"INTERNETFIX\",\"Description\":\"\",\"Type\":{\"Id\":0,\"Description\":\"\"},\"ServiceType\":\"INTERNET\",\"RateClassId\":\"\",\"BillingMessageExist\":false,\"Active\":true,\"UseLatestRateEffectivePeriod\":false,\"ConsecutiveEstimatesAllowed\":0,\"BillInAdvance\":false,\"LookupVisible\":false,\"SpecialCondition\":false,\"TimeOfUse\":false,\"ExcludeFromBd\":false,\"RatchetDemand\":false,\"KvarFactor\":0.00000,\"RateInUse\":true,\"EffectiveDate\":[{\"EffectiveStartDate\":\"2019-06-01\",\"MinimumAmount\":0.00,\"MaximumAmount\":0.00,\"ProrateMinimum\":{\"First\":false,\"Regular\":false,\"Last\":false},\"ProrateMaximum\":{\"First\":false,\"Regular\":false,\"Last\":false}}],\"Detail\":[{\"DetailIndex\":1,\"Detail\":{\"Type\":1,\"Description\":\"Fixed Charge\"},\"EffectiveStartDate\":\"2019-06-01\",\"EffectiveEndDate\":\"1900-01-01\",\"DetailDescription\":\"\",\"TaxSchedule\":\"USAUSSTCITY+6*\",\"ServiceType\":\"INTERNET\",\"BillingFrequency\":30,\"ProrateDetail\":{\"First\":false,\"Regular\":false,\"Last\":false},\"ProrateMinimum\":{\"First\":false,\"Regular\":false,\"Last\":false},\"MinimumCharge\":0.00,\"UnitDescription\":\"\",\"FixedCharge\":10.00000,\"Consumption\":{\"BillingDemandMinimum\":0.00,\"ConsumptionTolerance\":0.00000,\"UseActualDays\":false,\"Reporting\":{\"IncludeUnits\":false,\"IncludeRevenue\":false},\"ExportDetail\":false,\"ApplyDiscountPercentage\":0,\"WinterNormalizationAdjustment\":{\"Type\":0,\"TypeDetail\":0},\"CustomerChoice\":0,\"OldestEstimateUpdateDate\":\"1900-01-01\"},\"RevenueAccount\":{\"Index\":624,\"Number\":\"900-4616-00\",\"Description\":\"Internet Service\"},\"ReceivableAccount\":{\"Index\":623,\"Number\":\"900-1414-00\",\"Description\":\"Customer Account Receivable-Internet service\"},\"DetailSequence\":null,\"MeterSizeMinimum\":[],\"AutomaticEstimates\":null}]},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result.replace("\"MeterSizeMinimum\":null", "\"MeterSizeMinimum\":[]"), expected);

	}

	@Test(priority = 7, groups = "rate")
	public void postCreateRate()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate";
		String version = "4.0";
		String payload = "{\r\n" + //
				"    \"RateId\": \"RATE2\",\r\n" + //
				"    \"Description\": \"NEW RATE WITH NEW RATE TYPE\",\r\n" + //
				"    \"Type\": {\r\n" + //
				"        \"Id\": 2\r\n" + //
				"    },\r\n" + //
				"    \"ServiceType\": \"ELECTRIC\",\r\n" + //
				"    \"RateClassId\": \"\",\r\n" + //
				"    \"Active\": 1,\r\n" + //
				"    \"UseLatestRateEffectivePeriod\": true,\r\n" + //
				"    \"ConsecutiveEstimatesAllowed\": -99,\r\n" + //
				"    \"BillInAdvance\": false,\r\n" + //
				"    \"LookupVisible\": true,\r\n" + //
				"    \"SpecialCondition\": true,\r\n" + //
				"    \"TimeOfUse\": true,\r\n" + //
				"    \"ExcludeFromBd\": true,\r\n" + //
				"    \"RatchetDemand\": false,\r\n" + //
				"    \"KvarFactor\": \"-19292202020.02111\",\r\n" + //
				"    \"EffectiveDate\": [\r\n" + //
				"        {\r\n" + //
				"            \"EffectiveStartDate\": \"2026-01-01\",\r\n" + //
				"            \"MinimumAmount\": \"0.11\",\r\n" + //
				"            \"MaximumAmount\": \"99999999.99\",\r\n" + //
				"            \"ProrateMinimum\": {\r\n" + //
				"                \"First\": true,\r\n" + //
				"                \"Regular\": false,\r\n" + //
				"                \"Last\": false\r\n" + //
				"            },\r\n" + //
				"            \"ProrateMaximum\": {\r\n" + //
				"                \"First\": true,\r\n" + //
				"                \"Regular\": false,\r\n" + //
				"                \"Last\": true\r\n" + //
				"            }\r\n" + //
				"        }\r\n" + //
				"    ],\r\n" + //
				"    \"Detail\": [\r\n" + //
				"        {\r\n" + //
				"            \"DetailIndex\": 1,\r\n" + //
				"            \"Detail\": {\r\n" + //
				"                \"Type\": 4\r\n" + //
				"            },\r\n" + //
				"            \"EffectiveStartDate\": \"2026-01-01\",\r\n" + //
				"            \"DetailDescription\": \"\",\r\n" + //
				"            \"TaxSchedule\": \"USASTCITY-6*\",\r\n" + //
				"            \"ServiceType\": \"ELECTRIC\",\r\n" + //
				"            \"BillingFrequency\": 9999,\r\n" + //
				"            \"ProrateDetail\": {\r\n" + //
				"                \"First\": true,\r\n" + //
				"                \"Regular\": false,\r\n" + //
				"                \"Last\": true\r\n" + //
				"            },\r\n" + //
				"            \"ProrateMinimum\": {\r\n" + //
				"                \"First\": false,\r\n" + //
				"                \"Regular\": false,\r\n" + //
				"                \"Last\": false\r\n" + //
				"            },\r\n" + //
				"            \"MinimumCharge\": \"9999999999.99\",\r\n" + //
				"            \"UnitDescription\": \"0\",\r\n" + //
				"            \"FixedCharge\": \"12.46444\",\r\n" + //
				"            \"Consumption\": {\r\n" + //
				"                \"BillingDemandMinimum\": \"999999999.99\",\r\n" + //
				"                \"ConsumptionTolerance\": \"132.03333\",\r\n" + //
				"                \"UseActualDays\": false,\r\n" + //
				"                \"Reporting\": {\r\n" + //
				"                    \"IncludeUnits\": true,\r\n" + //
				"                    \"IncludeRevenue\": true\r\n" + //
				"                },\r\n" + //
				"                \"ExportDetail\": false,\r\n" + //
				"                \"ApplyDiscountPercentage\": 1,\r\n" + //
				"                \"WinterNormalizationAdjustment\": {\r\n" + //
				"                    \"Type\": 2,\r\n" + //
				"                    \"TypeDetail\": 2\r\n" + //
				"                },\r\n" + //
				"                \"CustomerChoice\": 1\r\n" + //
				"            },\r\n" + //
				"            \"RevenueAccount\": {\r\n" + //
				"                \"Index\": 3\r\n" + //
				"            },\r\n" + //
				"            \"ReceivableAccount\": {\r\n" + //
				"                \"Index\": 1\r\n" + //
				"            },\r\n" + //
				"            \"DetailSequence\": [\r\n" + //
				"                {\r\n" + //
				"                    \"DetailIndexSequence\": \"1\",\r\n" + //
				"                    \"UnitRate\": \"-16.42000\",\r\n" + //
				"                    \"VolumeLowerLimit\": \"0\",\r\n" + //
				"                    \"VolumeUpperLimit\": \"0\",\r\n" + //
				"                    \"VolumeLowerLimitString\": \"0\",\r\n" + //
				"                    \"VolumeUpperLimitString\": \"BD*100\"\r\n" + //
				"                },\r\n" + //
				"                {\r\n" + //
				"                    \"DetailIndexSequence\": \"2\",\r\n" + //
				"                    \"UnitRate\": \"999999999.9999\",\r\n" + //
				"                    \"VolumeLowerLimit\": \"0\",\r\n" + //
				"                    \"VolumeUpperLimit\": \"999999999\",\r\n" + //
				"                    \"VolumeLowerLimitString\": \"BD*100+1\",\r\n" + //
				"                    \"VolumeUpperLimitString\": \"999999999\"\r\n" + //
				"                }\r\n" + //
				"            ],\r\n" + //
				"            \"MeterSizeMinimum\": null,\r\n" + //
				"            \"AutomaticEstimates\": {\r\n" + //
				"                \"January\": {\r\n" + //
				"                    \"Consumption\": \"2.50000\",\r\n" + //
				"                    \"Date\": \"2026-02-15\"\r\n" + //
				"                },\r\n" + //
				"                \"February\": {\r\n" + //
				"                    \"Consumption\": \"2.00000\",\r\n" + //
				"                    \"Date\": \"2026-02-15\"\r\n" + //
				"                },\r\n" + //
				"                \"March\": {\r\n" + //
				"                    \"Consumption\": \"2.50000\",\r\n" + //
				"                    \"Date\": \"2026-02-15\"\r\n" + //
				"                },\r\n" + //
				"                \"April\": {\r\n" + //
				"                    \"Consumption\": \"2.88888\",\r\n" + //
				"                    \"Date\": \"2026-02-15\"\r\n" + //
				"                },\r\n" + //
				"                \"May\": {\r\n" + //
				"                    \"Consumption\": \"3.00000\",\r\n" + //
				"                    \"Date\": \"2026-02-15\"\r\n" + //
				"                },\r\n" + //
				"                \"June\": {\r\n" + //
				"                    \"Consumption\": \"5.00000\",\r\n" + //
				"                    \"Date\": \"2026-02-15\"\r\n" + //
				"                },\r\n" + //
				"                \"July\": {\r\n" + //
				"                    \"Consumption\": \"6.00000\",\r\n" + //
				"                    \"Date\": \"2026-02-15\"\r\n" + //
				"                },\r\n" + //
				"                \"August\": {\r\n" + //
				"                    \"Consumption\": \"7.70000\",\r\n" + //
				"                    \"Date\": \"2026-02-15\"\r\n" + //
				"                },\r\n" + //
				"                \"September\": {\r\n" + //
				"                    \"Consumption\": \"8.00000\",\r\n" + //
				"                    \"Date\": \"2026-02-15\"\r\n" + //
				"                },\r\n" + //
				"                \"October\": {\r\n" + //
				"                    \"Consumption\": \"8.80000\",\r\n" + //
				"                    \"Date\": \"2026-02-17\"\r\n" + //
				"                },\r\n" + //
				"                \"November\": {\r\n" + //
				"                    \"Consumption\": \"9.00000\",\r\n" + //
				"                    \"Date\": \"2026-02-15\"\r\n" + //
				"                },\r\n" + //
				"                \"December\": {\r\n" + //
				"                    \"Consumption\": \"9.7\",\r\n" + //
				"                    \"Date\": \"2026-02-15\"\r\n" + //
				"                }\r\n" + //
				"            }\r\n" + //
				"        },\r\n" + //
				"        {\r\n" + //
				"            \"DetailIndex\": 2,\r\n" + //
				"            \"Detail\": {\r\n" + //
				"                \"Type\": 2\r\n" + //
				"            },\r\n" + //
				"            \"EffectiveStartDate\": \"2026-01-01\",\r\n" + //
				"            \"DetailDescription\": \"\",\r\n" + //
				"            \"TaxSchedule\": \"USASTCITY-6*\",\r\n" + //
				"            \"ServiceType\": \"INTERNET\",\r\n" + //
				"            \"BillingFrequency\": 9999,\r\n" + //
				"            \"ProrateDetail\": {\r\n" + //
				"                \"First\": true,\r\n" + //
				"                \"Regular\": false,\r\n" + //
				"                \"Last\": true\r\n" + //
				"            },\r\n" + //
				"            \"ProrateMinimum\": {\r\n" + //
				"                \"First\": false,\r\n" + //
				"                \"Regular\": false,\r\n" + //
				"                \"Last\": false\r\n" + //
				"            },\r\n" + //
				"            \"MinimumCharge\": \"9999999999.99\",\r\n" + //
				"            \"UnitDescription\": \"0\",\r\n" + //
				"            \"FixedCharge\": \"12.46444\",\r\n" + //
				"            \"Consumption\": {\r\n" + //
				"                \"BillingDemandMinimum\": \"299.21\",\r\n" + //
				"                \"ConsumptionTolerance\": \"132.03333\",\r\n" + //
				"                \"UseActualDays\": false,\r\n" + //
				"                \"Reporting\": {\r\n" + //
				"                    \"IncludeUnits\": true,\r\n" + //
				"                    \"IncludeRevenue\": true\r\n" + //
				"                },\r\n" + //
				"                \"ExportDetail\": false,\r\n" + //
				"                \"ApplyDiscountPercentage\": 1,\r\n" + //
				"                \"WinterNormalizationAdjustment\": {\r\n" + //
				"                    \"Type\": 2,\r\n" + //
				"                    \"TypeDetail\": 1\r\n" + //
				"                },\r\n" + //
				"                \"CustomerChoice\": 1\r\n" + //
				"            },\r\n" + //
				"            \"RevenueAccount\": {\r\n" + //
				"                \"Index\": 1\r\n" + //
				"            },\r\n" + //
				"            \"ReceivableAccount\": {\r\n" + //
				"                \"Index\": 2\r\n" + //
				"            },\r\n" + //
				"            \"DetailSequence\": [\r\n" + //
				"                {\r\n" + //
				"                    \"DetailIndexSequence\": 1,\r\n" + //
				"                    \"UnitRate\": \"999999991.91\",\r\n" + //
				"                    \"VolumeLowerLimit\": \"0\",\r\n" + //
				"                    \"VolumeUpperLimit\": \"500\",\r\n" + //
				"                    \"VolumeLowerLimitString\": \"\",\r\n" + //
				"                    \"VolumeUpperLimitString\": \"\"\r\n" + //
				"                },\r\n" + //
				"                {\r\n" + //
				"                    \"DetailIndexSequence\": 2,\r\n" + //
				"                    \"UnitRate\": \"-999999999.99991\",\r\n" + //
				"                    \"VolumeLowerLimit\": \"501\",\r\n" + //
				"                    \"VolumeUpperLimit\": \"6000\",\r\n" + //
				"                    \"VolumeLowerLimitString\": \"\",\r\n" + //
				"                    \"VolumeUpperLimitString\": \"\"\r\n" + //
				"                }\r\n" + //
				"            ],\r\n" + //
				"            \"MeterSizeMinimum\": {\r\n" + //
				"                \"Method\": {\r\n" + //
				"                    \"Id\": 1\r\n" + //
				"                },\r\n" + //
				"                \"Type\": {\r\n" + //
				"                    \"Id\": 1\r\n" + //
				"                },\r\n" + //
				"                \"MeterSizes\": [\r\n" + //
				"                    {\r\n" + //
				"                        \"EquipmentClass\": \"\",\r\n" + //
				"                        \"Diameter\": \"999999999999.99999\",\r\n" + //
				"                        \"Amount\": \"102020099999.99\",\r\n" + //
				"                        \"UnitRate\": \"-191919999.99999\",\r\n" + //
				"                        \"VolumeUpperLimit\": \"276447231\"\r\n" + //
				"                    },\r\n" + //
				"                    {\r\n" + //
				"                        \"EquipmentClass\": \"CLASS-PHONE\",\r\n" + //
				"                        \"Diameter\": \"2.00000\",\r\n" + //
				"                        \"Amount\": \"1.23\",\r\n" + //
				"                        \"UnitRate\": \"1.23000\",\r\n" + //
				"                        \"VolumeUpperLimit\": \"12\"\r\n" + //
				"                    }\r\n" + //
				"                ]\r\n" + //
				"            },\r\n" + //
				"            \"AutomaticEstimates\": null\r\n" + //
				"        }\r\n" + //
				"    ]\r\n" + //
				"}";
		String exResponse = "{\"Rate\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Rate (RATE2) successfully saved.\",\"Level\":1}]}}";
		String response = CommonMethods.postMethodStringPayloadString(payload, uri, version);
		Assert.assertEquals(exResponse, response);
	}

	@Test(priority = 8, groups = "rate")
	public static void putUpdateRatev4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<>();

		String payload = "{\r\n" + //
				"    \"RateId\": \"RATE2\",\r\n" + //
				"    \"Description\": \"Updating COMM Rate Setup\",\r\n" + //
				"    \"Type\": {\r\n" + //
				"        \"Id\": 1\r\n" + //
				"    },\r\n" + //
				"    \"ServiceType\": \"INTERNET\",\r\n" + //
				"    \"RateClassId\": \"AUTORATE\",\r\n" + //
				"    \"Active\": 1,\r\n" + //
				"    \"UseLatestRateEffectivePeriod\": true,\r\n" + //
				"    \"ConsecutiveEstimatesAllowed\": 99,\r\n" + //
				"    \"BillInAdvance\": false,\r\n" + //
				"    \"LookupVisible\": true,\r\n" + //
				"    \"SpecialCondition\": true,\r\n" + //
				"    \"TimeOfUse\": true,\r\n" + //
				"    \"ExcludeFromBd\": true,\r\n" + //
				"    \"RatchetDemand\": true,\r\n" + //
				"    \"KvarFactor\": \"99999999999999.99999\",\r\n" + //
				"    \"EffectiveDate\": [\r\n" + //
				"        {\r\n" + //
				"            \"EffectiveStartDate\": \"2027-02-15\",                  \r\n" + //
				"            \"MinimumAmount\": \"999999999999.99\",\r\n" + //
				"            \"MaximumAmount\": \"999999999999.99\",\r\n" + //
				"            \"ProrateMinimum\": {\r\n" + //
				"                \"First\": true,\r\n" + //
				"                \"Regular\": true,\r\n" + //
				"                \"Last\": true\r\n" + //
				"            },\r\n" + //
				"            \"ProrateMaximum\": {\r\n" + //
				"                \"First\": true,\r\n" + //
				"                \"Regular\": true,\r\n" + //
				"                \"Last\": true\r\n" + //
				"            }\r\n" + //
				"        }\r\n" + //
				"    ],\r\n" + //
				"    \"Detail\": [\r\n" + //
				"        {\r\n" + //
				"            \"DetailIndex\": 1,\r\n" + //
				"            \"Detail\": {\r\n" + //
				"                \"Type\": 4\r\n" + //
				"            },\r\n" + //
				"            \"EffectiveStartDate\": \"2027-02-15\",            \r\n" + //
				"            \"DetailDescription\": \"Electric Monhtly Charge\",\r\n" + //
				"            \"TaxSchedule\": \"USASTCITY-6*\",\r\n" + //
				"            \"ServiceType\": \"INTERNET\",\r\n" + //
				"            \"BillingFrequency\": 90,\r\n" + //
				"            \"ProrateDetail\": {\r\n" + //
				"                \"First\": true,\r\n" + //
				"                \"Regular\": false,\r\n" + //
				"                \"Last\": true\r\n" + //
				"            },\r\n" + //
				"            \"ProrateMinimum\": {\r\n" + //
				"                \"First\": false,\r\n" + //
				"                \"Regular\": false,\r\n" + //
				"                \"Last\": false\r\n" + //
				"            },\r\n" + //
				"            \"MinimumCharge\": \"0.00\",\r\n" + //
				"            \"UnitDescription\": \"0\",\r\n" + //
				"            \"FixedCharge\": \"0.00\",\r\n" + //
				"            \"Consumption\": {\r\n" + //
				"                \"BillingDemandMinimum\": \"999999999.99\",\r\n" + //
				"                \"ConsumptionTolerance\": \"123.12345\",\r\n" + //
				"                \"UseActualDays\": false,\r\n" + //
				"                \"Reporting\": {\r\n" + //
				"                    \"IncludeUnits\": true,\r\n" + //
				"                    \"IncludeRevenue\": true\r\n" + //
				"                },\r\n" + //
				"                \"ExportDetail\": false,\r\n" + //
				"                \"ApplyDiscountPercentage\": 0,\r\n" + //
				"                \"WinterNormalizationAdjustment\": {\r\n" + //
				"                    \"Type\": 2,\r\n" + //
				"                    \"TypeDetail\": 2\r\n" + //
				"                },\r\n" + //
				"                \"CustomerChoice\": 0\r\n" + //
				"            },\r\n" + //
				"            \"RevenueAccount\": {\r\n" + //
				"                \"Index\": 515\r\n" + //
				"            },\r\n" + //
				"            \"ReceivableAccount\": {\r\n" + //
				"                \"Index\": 506\r\n" + //
				"            },\r\n" + //
				"            \"DetailSequence\": [\r\n" + //
				"                {\r\n" + //
				"                    \"DetailIndexSequence\": \"1\",\r\n" + //
				"                    \"UnitRate\": \"-16.42000\",\r\n" + //
				"                    \"VolumeLowerLimit\": \"0\",\r\n" + //
				"                    \"VolumeUpperLimit\": \"0\",\r\n" + //
				"                    \"VolumeLowerLimitString\": \"0\",\r\n" + //
				"                    \"VolumeUpperLimitString\": \"20000\"\r\n" + //
				"                },\r\n" + //
				"                {\r\n" + //
				"                    \"DetailIndexSequence\": \"2\",\r\n" + //
				"                    \"UnitRate\": \"999999999.9999\",\r\n" + //
				"                    \"VolumeLowerLimit\": \"0\",\r\n" + //
				"                    \"VolumeUpperLimit\": \"0\",\r\n" + //
				"                    \"VolumeLowerLimitString\": \"20000+1\",\r\n" + //
				"                    \"VolumeUpperLimitString\": \"BD*200\"\r\n" + //
				"                },\r\n" + //
				"                {\r\n" + //
				"                    \"DetailIndexSequence\": \"3\",\r\n" + //
				"                    \"UnitRate\": \"999999999.9999\",\r\n" + //
				"                    \"VolumeLowerLimit\": \"0\",\r\n" + //
				"                    \"VolumeUpperLimit\": \"0\",\r\n" + //
				"                    \"VolumeLowerLimitString\": \"BD*200+1\",\r\n" + //
				"                    \"VolumeUpperLimitString\": \"999999999\"\r\n" + //
				"                }\r\n" + //
				"            ],\r\n" + //
				"            \"MeterSizeMinimum\": {\r\n" + //
				"                 \"Method\": {\r\n" + //
				"                    \"Id\": 1\r\n" + //
				"                },\r\n" + //
				"                \"Type\": {\r\n" + //
				"                    \"Id\": 2\r\n" + //
				"                },\r\n" + //
				"                \"MeterSizes\": [\r\n" + //
				"                {\r\n" + //
				"                        \"EquipmentClass\": \"\",\r\n" + //
				"                        \"Diameter\": \"999999999999.99999\",\r\n" + //
				"                        \"Amount\": \"999999999999.99\",\r\n" + //
				"                        \"UnitRate\": \"999999999.99999\",\r\n" + //
				"                        \"VolumeUpperLimit\": \"276447231\"\r\n" + //
				"                    },\r\n" + //
				"                    {\r\n" + //
				"                        \"EquipmentClass\": \"CLASS-PHONE\",\r\n" + //
				"                        \"Diameter\": \"0.00000\",\r\n" + //
				"                        \"Amount\": \"1.23\",\r\n" + //
				"                        \"UnitRate\": \"1.23000\",\r\n" + //
				"                        \"VolumeUpperLimit\": \"12\"\r\n" + //
				"                    }\r\n" + //
				"                ]\r\n" + //
				"            },\r\n" + //
				"            \"AutomaticEstimates\": {\r\n" + //
				"                \"January\": {\r\n" + //
				"                    \"Consumption\": \"2.50000\",\r\n" + //
				"                    \"Date\": \"2026-02-25\"\r\n" + //
				"                },\r\n" + //
				"                \"February\": {\r\n" + //
				"                    \"Consumption\": \"2.00000\",\r\n" + //
				"                    \"Date\": \"2026-02-25\"\r\n" + //
				"                },\r\n" + //
				"                \"March\": {\r\n" + //
				"                    \"Consumption\": \"2.50000\",\r\n" + //
				"                    \"Date\": \"2026-02-25\"\r\n" + //
				"                },\r\n" + //
				"                \"April\": {\r\n" + //
				"                    \"Consumption\": \"2.88888\",\r\n" + //
				"                    \"Date\": \"2026-02-25\"\r\n" + //
				"                },\r\n" + //
				"                \"May\": {\r\n" + //
				"                    \"Consumption\": \"3.00000\",\r\n" + //
				"                    \"Date\": \"2026-02-25\"\r\n" + //
				"                },\r\n" + //
				"                \"June\": {\r\n" + //
				"                    \"Consumption\": \"5.00000\",\r\n" + //
				"                    \"Date\": \"2026-02-25\"\r\n" + //
				"                },\r\n" + //
				"                \"July\": {\r\n" + //
				"                    \"Consumption\": \"5.00000\",\r\n" + //
				"                    \"Date\": \"2026-02-25\"\r\n" + //
				"                },\r\n" + //
				"                \"August\": {\r\n" + //
				"                    \"Consumption\": \"5.00000\",\r\n" + //
				"                    \"Date\": \"2026-02-25\"\r\n" + //
				"                },\r\n" + //
				"                \"September\": {\r\n" + //
				"                    \"Consumption\": \"5.00000\",\r\n" + //
				"                    \"Date\": \"2026-02-25\"\r\n" + //
				"                },\r\n" + //
				"                \"October\": {\r\n" + //
				"                    \"Consumption\": \"5.00000\",\r\n" + //
				"                    \"Date\": \"2026-02-25\"\r\n" + //
				"                },\r\n" + //
				"                \"November\": {\r\n" + //
				"                    \"Consumption\": \"5.00000\",\r\n" + //
				"                    \"Date\": \"2026-02-25\"\r\n" + //
				"                },\r\n" + //
				"                \"December\": {\r\n" + //
				"                    \"Consumption\": \"5.00000\",\r\n" + //
				"                    \"Date\": \"2026-02-25\"\r\n" + //
				"                }\r\n" + //
				"            }\r\n" + //
				"        },\r\n" + //
				"        {\r\n" + //
				"            \"DetailIndex\": 3,\r\n" + //
				"            \"Detail\": {\r\n" + //
				"                \"Type\": 2\r\n" + //
				"            },\r\n" + //
				"            \"EffectiveStartDate\": \"2027-02-15\",            \r\n" + //
				"            \"DetailDescription\": \"Electric Monhtly Charge\",\r\n" + //
				"            \"TaxSchedule\": \"USASTCITY-6*\",\r\n" + //
				"            \"ServiceType\": \"INTERNET\",\r\n" + //
				"            \"BillingFrequency\": 90,\r\n" + //
				"            \"ProrateDetail\": {\r\n" + //
				"                \"First\": true,\r\n" + //
				"                \"Regular\": false,\r\n" + //
				"                \"Last\": true\r\n" + //
				"            },\r\n" + //
				"            \"ProrateMinimum\": {\r\n" + //
				"                \"First\": false,\r\n" + //
				"                \"Regular\": false,\r\n" + //
				"                \"Last\": false\r\n" + //
				"            },\r\n" + //
				"            \"MinimumCharge\": \"0.00\",\r\n" + //
				"            \"UnitDescription\": \"0\",\r\n" + //
				"            \"FixedCharge\": \"99999.991\",\r\n" + //
				"            \"Consumption\": {\r\n" + //
				"                \"BillingDemandMinimum\": \"999999999.99\",\r\n" + //
				"                \"ConsumptionTolerance\": \"123.12345\",\r\n" + //
				"                \"UseActualDays\": false,\r\n" + //
				"                \"Reporting\": {\r\n" + //
				"                    \"IncludeUnits\": true,\r\n" + //
				"                    \"IncludeRevenue\": true\r\n" + //
				"                },\r\n" + //
				"                \"ExportDetail\": false,\r\n" + //
				"                \"ApplyDiscountPercentage\": 0,\r\n" + //
				"                \"WinterNormalizationAdjustment\": {\r\n" + //
				"                    \"Type\": 2,\r\n" + //
				"                    \"TypeDetail\": 2\r\n" + //
				"                },\r\n" + //
				"                \"CustomerChoice\": 0\r\n" + //
				"            },\r\n" + //
				"            \"RevenueAccount\": {\r\n" + //
				"                \"Index\": 1\r\n" + //
				"            },\r\n" + //
				"            \"ReceivableAccount\": {\r\n" + //
				"                \"Index\": 2\r\n" + //
				"            },\r\n" + //
				"            \"DetailSequence\": [],\r\n" + //
				"            \"MeterSizeMinimum\": {\r\n" + //
				"                 \"Method\": {\r\n" + //
				"                    \"Id\": 1\r\n" + //
				"                },\r\n" + //
				"                \"Type\": {\r\n" + //
				"                    \"Id\": 1\r\n" + //
				"                },\r\n" + //
				"                \"MeterSizes\": [\r\n" + //
				"                {\r\n" + //
				"                        \"EquipmentClass\": \"\",\r\n" + //
				"                        \"Diameter\": \"999999999999.99999\",\r\n" + //
				"                        \"Amount\": \"999999999999.99\",\r\n" + //
				"                        \"UnitRate\": \"999999999.99999\",\r\n" + //
				"                        \"VolumeUpperLimit\": \"276447231\"\r\n" + //
				"                    },\r\n" + //
				"                    {\r\n" + //
				"                        \"EquipmentClass\": \"CLASS-PHONE\",\r\n" + //
				"                        \"Diameter\": \"0.00000\",\r\n" + //
				"                        \"Amount\": \"1.23\",\r\n" + //
				"                        \"UnitRate\": \"1.23000\",\r\n" + //
				"                        \"VolumeUpperLimit\": \"12\"\r\n" + //
				"                    }\r\n" + //
				"                ]\r\n" + //
				"            },\r\n" + //
				"            \"AutomaticEstimates\": null\r\n" + //
				"        }\r\n" + //
				"    ]\r\n" + //
				"}\r\n" + //
				"";
		String expected = "{\"Rate\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Rate (RATE2) successfully saved.\",\"Level\":1}]}}";
		String result = CommonMethods.putMethodString(uri, ver, params, payload, expected);

	}

	@Test(priority = 9, groups = "rate")
	public void getrateFlip()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/flip/ELECTRATE";
		String ver = "4.0";
		String expected = "{\"Rate\":{\"Success\":true,\"Data\":{\"RateClassID\":\"ELECTRATE\",\"ServiceCategory\":{\"Id\":1,\"Description\":\"Electric\"},\"RateType\":{\"Id\":1,\"Description\":\"Consumption\"},\"NumberOfMonths\":6,\"RateFlip\":[{\"PeriodID\":\"ON PEAK\",\"PeriodIndex\":1,\"Steps\":[{\"SequenceNumber\":1,\"VolumeLowerLimit\":111,\"VolumeUpperLimit\":999999999,\"TariffID\":\"EPCA-1\",\"KWRate\":\"RATE1\",\"KVARate\":\"RATE1\"}]},{\"PeriodID\":\"OFFPEAK\",\"PeriodIndex\":2,\"Steps\":[{\"SequenceNumber\":1,\"VolumeLowerLimit\":10000,\"VolumeUpperLimit\":23123134,\"TariffID\":\"GS-PK ENERGY\",\"KWRate\":\"RATE1\",\"KVARate\":\"RATE1\"}]}]},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		// params.put("RateId", "ELECTRATE");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result, expected);

	}

	@Test(priority = 10, groups = "rate")
	public void deleteRate()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/RATE1";
		String ver = "4.0";
		String expected = "{\"Rate\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Rate (RATE1) successfully deleted.\",\"Level\":1}]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		// params.put("RateId", "ELECTRATE");
		String result = CommonMethods.deleteMethodasString(uri, ver);
		Assert.assertEquals(result, expected);

	}

	@Test(priority = 11, groups = "rate")
	public static void putUpdateRatev4_DeleteDetailindex()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<>();

		String payload = "{  \r\n" + //
				" \"RateId\": \"RATE4\",  \r\n" + //
				" \"Description\": \"Update Rate Id\",  \r\n" + //
				" \"Type\": {  \r\n" + //
				" \"Id\": 1  \r\n" + //
				" },  \r\n" + //
				" \"ServiceType\": \"GAS\",  \r\n" + //
				" \"RateClassId\": \"\",  \r\n" + //
				" \"Active\": true,  \r\n" + //
				" \"UseLatestRateEffectivePeriod\": false,  \r\n" + //
				" \"ConsecutiveEstimatesAllowed\": 0,  \r\n" + //
				" \"BillInAdvance\": false,  \r\n" + //
				" \"LookupVisible\": false,  \r\n" + //
				" \"SpecialCondition\": false,  \r\n" + //
				" \"TimeOfUse\": false,  \r\n" + //
				" \"ExcludeFromBd\": false,  \r\n" + //
				" \"RatchetDemand\": false,  \r\n" + //
				" \"KvarFactor\": \"0.00000\",  \r\n" + //
				" \"EffectiveDate\": [  \r\n" + //
				" {  \r\n" + //
				" \"EffectiveStartDate\": \"2026-01-01\",  \r\n" + //
				" \"MinimumAmount\": \"0.00\",  \r\n" + //
				" \"MaximumAmount\": \"0.00\",  \r\n" + //
				" \"ProrateMinimum\": {  \r\n" + //
				" \"First\": false,\r\n" + //
				" \"Regular\": false,  \r\n" + //
				" \"Last\": false  \r\n" + //
				" },  \r\n" + //
				" \"ProrateMaximum\": {  \r\n" + //
				" \"First\": false,  \r\n" + //
				" \"Regular\": false,  \r\n" + //
				" \"Last\": false  \r\n" + //
				" }  \r\n" + //
				" }  \r\n" + //
				" ],  \r\n" + //
				" \"Detail\": [  \r\n" + //
				" ]  \r\n" + //
				"}  ";
		String expected = "{\"Rate\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Rate (RATE4) successfully saved.\",\"Level\":1}]}}";
		String result = CommonMethods.putMethodString(uri, ver, params, payload, expected);

	}

	@Test(priority = 12, groups = "rate")
	public static void putUpdateRatev4_removeDetailSequence()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<>();

		String payload = "{\r\n" + //
				"  \"RateId\": \"RATE4\",\r\n" + //
				"  \"Description\": \"Update Rate Id\",\r\n" + //
				"  \"Type\": {\r\n" + //
				"    \"Id\": 1\r\n" + //
				"  },\r\n" + //
				"  \"ServiceType\": \"GAS\",\r\n" + //
				"  \"RateClassId\": \"\",\r\n" + //
				"  \"Active\": true,\r\n" + //
				"  \"UseLatestRateEffectivePeriod\": false,\r\n" + //
				"  \"ConsecutiveEstimatesAllowed\": 0,\r\n" + //
				"  \"BillInAdvance\": false,\r\n" + //
				"  \"LookupVisible\": false,\r\n" + //
				"  \"SpecialCondition\": false,\r\n" + //
				"  \"TimeOfUse\": false,\r\n" + //
				"  \"ExcludeFromBd\": false,\r\n" + //
				"  \"RatchetDemand\": false,\r\n" + //
				"  \"KvarFactor\": \"0.00000\",\r\n" + //
				"  \"EffectiveDate\": [\r\n" + //
				"    {\r\n" + //
				"      \"EffectiveStartDate\": \"2026-01-01\",\r\n" + //
				"      \"MinimumAmount\": \"0.00\",\r\n" + //
				"      \"MaximumAmount\": \"0.00\",\r\n" + //
				"      \"ProrateMinimum\": {\r\n" + //
				"        \"First\": false,\r\n" + //
				"        \"Regular\": false,\r\n" + //
				"        \"Last\": false\r\n" + //
				"      },\r\n" + //
				"      \"ProrateMaximum\": {\r\n" + //
				"        \"First\": false,\r\n" + //
				"        \"Regular\": false,\r\n" + //
				"        \"Last\": false\r\n" + //
				"      }\r\n" + //
				"    }\r\n" + //
				"  ],\r\n" + //
				"  \"Detail\": [\r\n" + //
				"    {\r\n" + //
				"      \"DetailIndex\": 1,\r\n" + //
				"      \"Detail\": {\r\n" + //
				"        \"Type\": 4\r\n" + //
				"      },\r\n" + //
				"      \"EffectiveStartDate\": \"2026-01-01\",\r\n" + //
				"      \"DetailDescription\": \"Gas Monhtly Charge\",\r\n" + //
				"      \"TaxSchedule\": \"USASTCITY-6*\",\r\n" + //
				"      \"ServiceType\": \"GAS\",\r\n" + //
				"      \"BillingFrequency\": 90,\r\n" + //
				"      \"ProrateDetail\": {\r\n" + //
				"        \"First\": true,\r\n" + //
				"        \"Regular\": false,\r\n" + //
				"        \"Last\": true\r\n" + //
				"      },\r\n" + //
				"      \"ProrateMinimum\": {\r\n" + //
				"        \"First\": false,\r\n" + //
				"        \"Regular\": false,\r\n" + //
				"        \"Last\": false\r\n" + //
				"      },\r\n" + //
				"      \"MinimumCharge\": \"0.00\",\r\n" + //
				"      \"UnitDescription\": \"\",\r\n" + //
				"      \"FixedCharge\": \"0.00\",\r\n" + //
				"      \"Consumption\": {\r\n" + //
				"        \"BillingDemandMinimum\": \"999999999.99\",\r\n" + //
				"        \"ConsumptionTolerance\": \"123.12345\",\r\n" + //
				"        \"UseActualDays\": false,\r\n" + //
				"        \"Reporting\": {\r\n" + //
				"          \"IncludeUnits\": true,\r\n" + //
				"          \"IncludeRevenue\": true\r\n" + //
				"        },\r\n" + //
				"        \"ExportDetail\": false,\r\n" + //
				"        \"ApplyDiscountPercentage\": 0,\r\n" + //
				"        \"WinterNormalizationAdjustment\": {\r\n" + //
				"          \"Type\": 2,\r\n" + //
				"          \"TypeDetail\": 2\r\n" + //
				"        },\r\n" + //
				"        \"CustomerChoice\": 0\r\n" + //
				"      },\r\n" + //
				"      \"RevenueAccount\": {\r\n" + //
				"        \"Index\": 515\r\n" + //
				"      },\r\n" + //
				"      \"ReceivableAccount\": {\r\n" + //
				"        \"Index\": 506\r\n" + //
				"      },\r\n" + //
				"      \"DetailSequence\": [\r\n" + //
				"        {\r\n" + //
				"          \"DetailIndexSequence\": \"1\",\r\n" + //
				"          \"UnitRate\": \"-16.42000\",\r\n" + //
				"          \"VolumeLowerLimit\": \"0\",\r\n" + //
				"          \"VolumeUpperLimit\": \"0\",\r\n" + //
				"          \"VolumeLowerLimitString\": \"0\",\r\n" + //
				"          \"VolumeUpperLimitString\": \"20000\"\r\n" + //
				"        },\r\n" + //
				"        {\r\n" + //
				"          \"DetailIndexSequence\": \"2\",\r\n" + //
				"          \"UnitRate\": \"999999999.9999\",\r\n" + //
				"          \"VolumeLowerLimit\": \"0\",\r\n" + //
				"          \"VolumeUpperLimit\": \"0\",\r\n" + //
				"          \"VolumeLowerLimitString\": \"20000+1\",\r\n" + //
				"          \"VolumeUpperLimitString\": \"BD*200\"\r\n" + //
				"        },\r\n" + //
				"        {\r\n" + //
				"          \"DetailIndexSequence\": \"3\",\r\n" + //
				"          \"UnitRate\": \"999999999.9999\",\r\n" + //
				"          \"VolumeLowerLimit\": \"0\",\r\n" + //
				"          \"VolumeUpperLimit\": \"0\",\r\n" + //
				"          \"VolumeLowerLimitString\": \"BD*200+1\",\r\n" + //
				"          \"VolumeUpperLimitString\": \"999999999\"\r\n" + //
				"        }\r\n" + //
				"      ],\r\n" + //
				"      \"MeterSizeMinimum\": {\r\n" + //
				"        \"Method\": {\r\n" + //
				"          \"Id\": 1\r\n" + //
				"        },\r\n" + //
				"        \"Type\": {\r\n" + //
				"          \"Id\": 2\r\n" + //
				"        },\r\n" + //
				"        \"MeterSizes\": [\r\n" + //
				"          {\r\n" + //
				"            \"EquipmentClass\": \"\",\r\n" + //
				"            \"Diameter\": \"999999999999.99999\",\r\n" + //
				"            \"Amount\": \"999999999999.99\",\r\n" + //
				"            \"UnitRate\": \"999999999.99999\",\r\n" + //
				"            \"VolumeUpperLimit\": \"276447231\"\r\n" + //
				"          },\r\n" + //
				"          {\r\n" + //
				"            \"EquipmentClass\": \"\",\r\n" + //
				"            \"Diameter\": \"0.00000\",\r\n" + //
				"            \"Amount\": \"1.23\",\r\n" + //
				"            \"UnitRate\": \"1.23000\",\r\n" + //
				"            \"VolumeUpperLimit\": \"12\"\r\n" + //
				"          }\r\n" + //
				"        ]\r\n" + //
				"      },\r\n" + //
				"      \"AutomaticEstimates\": {\r\n" + //
				"        \"January\": {\r\n" + //
				"          \"Consumption\": \"2.50000\",\r\n" + //
				"          \"Date\": \"2026-02-25\"\r\n" + //
				"        },\r\n" + //
				"        \"February\": {\r\n" + //
				"          \"Consumption\": \"2.00000\",\r\n" + //
				"          \"Date\": \"2026-02-25\"\r\n" + //
				"        },\r\n" + //
				"        \"March\": {\r\n" + //
				"          \"Consumption\": \"2.50000\",\r\n" + //
				"          \"Date\": \"2026-02-25\"\r\n" + //
				"        },\r\n" + //
				"        \"April\": {\r\n" + //
				"          \"Consumption\": \"2.88888\",\r\n" + //
				"          \"Date\": \"2026-02-25\"\r\n" + //
				"        },\r\n" + //
				"        \"May\": {\r\n" + //
				"          \"Consumption\": \"3.00000\",\r\n" + //
				"          \"Date\": \"2026-02-25\"\r\n" + //
				"        },\r\n" + //
				"        \"June\": {\r\n" + //
				"          \"Consumption\": \"5.00000\",\r\n" + //
				"          \"Date\": \"2026-02-25\"\r\n" + //
				"        },\r\n" + //
				"        \"July\": {\r\n" + //
				"          \"Consumption\": \"5.00000\",\r\n" + //
				"          \"Date\": \"2026-02-25\"\r\n" + //
				"        },\r\n" + //
				"        \"August\": {\r\n" + //
				"          \"Consumption\": \"5.00000\",\r\n" + //
				"          \"Date\": \"2026-02-25\"\r\n" + //
				"        },\r\n" + //
				"        \"September\": {\r\n" + //
				"          \"Consumption\": \"5.00000\",\r\n" + //
				"          \"Date\": \"2026-02-25\"\r\n" + //
				"        },\r\n" + //
				"        \"October\": {\r\n" + //
				"          \"Consumption\": \"5.00000\",\r\n" + //
				"          \"Date\": \"2026-02-25\"\r\n" + //
				"        },\r\n" + //
				"        \"November\": {\r\n" + //
				"          \"Consumption\": \"5.00000\",\r\n" + //
				"          \"Date\": \"2026-02-25\"\r\n" + //
				"        },\r\n" + //
				"        \"December\": {\r\n" + //
				"          \"Consumption\": \"5.00000\",\r\n" + //
				"          \"Date\": \"2026-02-25\"\r\n" + //
				"        }\r\n" + //
				"      }\r\n" + //
				"    }\r\n" + //
				"  ]\r\n" + //
				"}";
		String expected = "{\"Rate\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Rate (RATE4) successfully saved.\",\"Level\":1}]}}";
		String result = CommonMethods.putMethodString(uri, ver, params, payload, expected);

	}

	@Test(priority = 13, groups = "rate")
	public void getrateId()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/EMP-1";
		String ver = "4.0";
		String expected = "{\"Rate\":{\"Success\":true,\"Data\":{\"RateId\":\"EMP-1\",\"Description\":\"Electric Medium Power (13)\",\"Type\":{\"Id\":1,\"Description\":\"Consumption\"},\"ServiceType\":\"ELECTRIC\",\"RateClassId\":\"\",\"BillingMessageExist\":true,\"Active\":true,\"UseLatestRateEffectivePeriod\":false,\"ConsecutiveEstimatesAllowed\":0,\"BillInAdvance\":false,\"LookupVisible\":false,\"SpecialCondition\":false,\"TimeOfUse\":false,\"ExcludeFromBd\":false,\"RatchetDemand\":true,\"KvarFactor\":0.00000,\"RateInUse\":true,\"EffectiveDate\":[{\"EffectiveStartDate\":\"1998-01-01\",\"MinimumAmount\":0.00,\"MaximumAmount\":0.00,\"ProrateMinimum\":{\"First\":false,\"Regular\":false,\"Last\":false},\"ProrateMaximum\":{\"First\":false,\"Regular\":false,\"Last\":false}}],\"Detail\":[{\"DetailIndex\":1,\"Detail\":{\"Type\":1,\"Description\":\"Fixed Charge\"},\"EffectiveStartDate\":\"1998-01-01\",\"EffectiveEndDate\":\"1900-01-01\",\"DetailDescription\":\"Electric Monhtly Charge\",\"TaxSchedule\":\"USASTCITY-6*\",\"ServiceType\":\"ELECTRIC\",\"BillingFrequency\":90,\"ProrateDetail\":{\"First\":true,\"Regular\":false,\"Last\":true},\"ProrateMinimum\":{\"First\":false,\"Regular\":false,\"Last\":false},\"MinimumCharge\":0.00,\"UnitDescription\":\"\",\"FixedCharge\":16.42000,\"Consumption\":{\"BillingDemandMinimum\":0.00,\"ConsumptionTolerance\":0.00000,\"UseActualDays\":false,\"Reporting\":{\"IncludeUnits\":true,\"IncludeRevenue\":true},\"ExportDetail\":false,\"ApplyDiscountPercentage\":0,\"WinterNormalizationAdjustment\":{\"Type\":0,\"TypeDetail\":0},\"CustomerChoice\":0,\"OldestEstimateUpdateDate\":\"1900-01-01\"},\"RevenueAccount\":{\"Index\":515,\"Number\":\"900-4611-00\",\"Description\":\"Metered Sales to Residential - Electric\"},\"ReceivableAccount\":{\"Index\":506,\"Number\":\"900-1410-00\",\"Description\":\"Customer Accounts Receivable - Electric\"},\"DetailSequence\":null,\"MeterSizeMinimum\":[],\"AutomaticEstimates\":null},{\"DetailIndex\":2,\"Detail\":{\"Type\":4,\"Description\":\"Adjustable Var Stepped Range\"},\"EffectiveStartDate\":\"1998-01-01\",\"EffectiveEndDate\":\"1900-01-01\",\"DetailDescription\":\"Electric Energy Charge MP-1\",\"TaxSchedule\":\"USASTCITY-6*\",\"ServiceType\":\"ELECTRIC\",\"BillingFrequency\":90,\"ProrateDetail\":{\"First\":false,\"Regular\":false,\"Last\":false},\"ProrateMinimum\":{\"First\":false,\"Regular\":false,\"Last\":false},\"MinimumCharge\":0.00,\"UnitDescription\":\"\",\"FixedCharge\":0.00000,\"Consumption\":{\"BillingDemandMinimum\":0.00,\"ConsumptionTolerance\":0.00000,\"UseActualDays\":false,\"Reporting\":{\"IncludeUnits\":true,\"IncludeRevenue\":true},\"ExportDetail\":false,\"ApplyDiscountPercentage\":0,\"WinterNormalizationAdjustment\":{\"Type\":0,\"TypeDetail\":0},\"CustomerChoice\":0,\"OldestEstimateUpdateDate\":\"1900-01-01\"},\"RevenueAccount\":{\"Index\":515,\"Number\":\"900-4611-00\",\"Description\":\"Metered Sales to Residential - Electric\"},\"ReceivableAccount\":{\"Index\":506,\"Number\":\"900-1410-00\",\"Description\":\"Customer Accounts Receivable - Electric\"},\"DetailSequence\":[{\"DetailIndexSequence\":1,\"UnitRate\":0.10689,\"VolumeLowerLimit\":0,\"VolumeUpperLimit\":0,\"VolumeLowerLimitString\":\"0\",\"VolumeUpperLimitString\":\"3000\"},{\"DetailIndexSequence\":2,\"UnitRate\":0.09789,\"VolumeLowerLimit\":0,\"VolumeUpperLimit\":0,\"VolumeLowerLimitString\":\"3000+1\",\"VolumeUpperLimitString\":\"10000\"},{\"DetailIndexSequence\":3,\"UnitRate\":0.08560,\"VolumeLowerLimit\":0,\"VolumeUpperLimit\":0,\"VolumeLowerLimitString\":\"10000+1\",\"VolumeUpperLimitString\":\"200000\"},{\"DetailIndexSequence\":4,\"UnitRate\":0.01089,\"VolumeLowerLimit\":0,\"VolumeUpperLimit\":0,\"VolumeLowerLimitString\":\"200000+1\",\"VolumeUpperLimitString\":\"BD*200\"},{\"DetailIndexSequence\":5,\"UnitRate\":0.01078,\"VolumeLowerLimit\":0,\"VolumeUpperLimit\":0,\"VolumeLowerLimitString\":\"BD*200+1\",\"VolumeUpperLimitString\":\"BD*400\"},{\"DetailIndexSequence\":6,\"UnitRate\":0.00989,\"VolumeLowerLimit\":0,\"VolumeUpperLimit\":0,\"VolumeLowerLimitString\":\"BD*400+1\",\"VolumeUpperLimitString\":\"BD*600\"},{\"DetailIndexSequence\":7,\"UnitRate\":0.00787,\"VolumeLowerLimit\":0,\"VolumeUpperLimit\":0,\"VolumeLowerLimitString\":\"BD*600+1\",\"VolumeUpperLimitString\":\"999999999\"}],\"MeterSizeMinimum\":[],\"AutomaticEstimates\":null}]},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		// params.put("RateId", "ELECTRATE");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result.replace("\"MeterSizeMinimum\":null", "\"MeterSizeMinimum\":[]"), expected);

	}

	@Test(priority = 14, groups = "rate")
	public void getrateIdNegative()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/EMP-1";
		String ver = "4.0";
		String expected = "{\"Rate\":{\"Success\":false,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Rate (EMP-1) with Effective Date (2001-01-01) not found.\",\"Level\":3}]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("EffectiveDate", "2001-01-01");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result, expected);

	}

	@Test(priority = 15, groups = "rate")
	public void getrateIserviceTypeSetup()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/serviceTypeSetup/GAS";
		String ver = "4.0";
		String expected = "{\"ServiceType\":{\"Success\":true,\"Data\":{\"ServiceType\":\"GAS\",\"Description\":\"Residential gas accounts\",\"Category\":{\"Id\":4,\"Description\":\"Gas\"},\"ReceivablesAccount\":{\"Index\":560,\"Number\":\"930-1410-00\",\"Description\":\"Customer Accounts Receivable - Gas\"},\"RevenueAccount\":{\"Index\":569,\"Number\":\"930-4611-00\",\"Description\":\"Metered Sales to Residential - Gas\"},\"WriteOffAccount\":{\"Index\":569,\"Number\":\"930-4611-00\",\"Description\":\"Metered Sales to Residential - Gas\"},\"CollectionAgencyAccount\":{\"Index\":0,\"Number\":\"\",\"Description\":\"\"},\"DiscountContraRevenueAccount\":{\"Index\":0,\"Number\":\"\",\"Description\":\"\"},\"DefaultTaxSchedule\":{\"Id\":\"USAUSSTCITY+6*\",\"Description\":\"US State\\/City Tax-Purchases\"},\"Penalty\":{\"Id\":\"DEFAULTPYMT\",\"Description\":\"Penalty for late payment\"},\"MinimumWriteOffAmountToAddMessage\":0.00,\"DiscountAllowed\":false,\"DiscountId\":\"\",\"IncludeThirdPartyCollections\":false,\"MarketerCharge\":false},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		//params.put("ServiceType", "GAS");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result, expected);

	}

	@Test(priority = 16, groups = "rate")
	public void getrateIserviceTypeSetupElectric()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/serviceTypeSetup/ELECTRIC";
		String ver = "4.0";
		String expected = "{\"ServiceType\":{\"Success\":true,\"Data\":{\"ServiceType\":\"ELECTRIC\",\"Description\":\"Residential electrical accounts\",\"Category\":{\"Id\":1,\"Description\":\"Electric\"},\"ReceivablesAccount\":{\"Index\":506,\"Number\":\"900-1410-00\",\"Description\":\"Customer Accounts Receivable - Electric\"},\"RevenueAccount\":{\"Index\":515,\"Number\":\"900-4611-00\",\"Description\":\"Metered Sales to Residential - Electric\"},\"WriteOffAccount\":{\"Index\":515,\"Number\":\"900-4611-00\",\"Description\":\"Metered Sales to Residential - Electric\"},\"CollectionAgencyAccount\":{\"Index\":0,\"Number\":\"\",\"Description\":\"\"},\"DiscountContraRevenueAccount\":{\"Index\":0,\"Number\":\"\",\"Description\":\"\"},\"DefaultTaxSchedule\":{\"Id\":\"USASTCITY-6*\",\"Description\":\"State Tax-USA\"},\"Penalty\":{\"Id\":\"5%\",\"Description\":\"Late Payment Charge\"},\"MinimumWriteOffAmountToAddMessage\":0.00,\"DiscountAllowed\":false,\"DiscountId\":\"\",\"IncludeThirdPartyCollections\":false,\"MarketerCharge\":false},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result, expected);

	}

	@Test(priority = 17, groups = "rate")
	public void getrateIserviceTypeSetupWater()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/serviceTypeSetup/WATER";
		String ver = "4.0";
		String expected = "{\"ServiceType\":{\"Success\":true,\"Data\":{\"ServiceType\":\"WATER\",\"Description\":\"Water residential customers\",\"Category\":{\"Id\":2,\"Description\":\"Water\"},\"ReceivablesAccount\":{\"Index\":508,\"Number\":\"920-1410-00\",\"Description\":\"Customer Accounts Receivable - Water\"},\"RevenueAccount\":{\"Index\":520,\"Number\":\"920-4612-00\",\"Description\":\"Metered Sales to Commercial - Water\"},\"WriteOffAccount\":{\"Index\":520,\"Number\":\"920-4612-00\",\"Description\":\"Metered Sales to Commercial - Water\"},\"CollectionAgencyAccount\":{\"Index\":0,\"Number\":\"\",\"Description\":\"\"},\"DiscountContraRevenueAccount\":{\"Index\":0,\"Number\":\"\",\"Description\":\"\"},\"DefaultTaxSchedule\":{\"Id\":\"EXEMPT\",\"Description\":\"No tax included\"},\"Penalty\":{\"Id\":\"DEFAULTPYMT\",\"Description\":\"Penalty for late payment\"},\"MinimumWriteOffAmountToAddMessage\":0.00,\"DiscountAllowed\":false,\"DiscountId\":\"\",\"IncludeThirdPartyCollections\":false,\"MarketerCharge\":false},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result, expected);

	}

	@Test(priority = 18, groups = "rate")
	public void getrateIserviceTypeSetupSteamNegative()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/serviceTypeSetup/STEAM";
		String ver = "4.0";
		String expected = "{\"ServiceType\":{\"Success\":false,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Invalid Service Type STEAM.\",\"Level\":3}]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result, expected);

	}

	@Test(priority = 19, groups = "rate")
	public void getrateIserviceTypeSetupChilledNegative()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/serviceTypeSetup/CHILLED";
		String ver = "4.0";
		String expected = "{\"ServiceType\":{\"Success\":false,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Invalid Service Type CHILLED.\",\"Level\":3}]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result, expected);

	}

	// =========================================================================
	// POST /rate/unitDescription  (Create Unit Description) - multiple scenarios
	// Bruno reference: nexus-bruno/.../rateController/Create Unit Description.yml
	// Body: { "UnitDescription": "...", "ServiceCategory": "...", "DetailType": <int> }
	// =========================================================================

	@Test(priority = 18, groups = "rate")
	public void postCreateUnitDescription_Success()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/unitDescription";
		String version = "4.0";
		String payload = "{\r\n" +
				"    \"UnitDescription\": \"UnitNewA\",\r\n" +
				"    \"ServiceCategory\": \"ELECTRIC\",\r\n" +
				"    \"DetailType\": 1\r\n" +
				"}";
		String exResponse = "{\"UnitDescription\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Unit Description saved successfully.\",\"Level\":1}]}}";
		String response = CommonMethods.postMethodStringPayloadString(payload, uri, version);
		Assert.assertEquals(response, exResponse);
	}

	@Test(priority = 18, groups = "rate", dependsOnMethods = "postCreateUnitDescription_Success")
	public void postCreateUnitDescription_Duplicate()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/unitDescription";
		String version = "4.0";
		String payload = "{\r\n" +
				"    \"UnitDescription\": \"UnitNewA\",\r\n" +
				"    \"ServiceCategory\": \"ELECTRIC\",\r\n" +
				"    \"DetailType\": 1\r\n" +
				"}";
		String exResponse = "{\"UnitDescription\":{\"Success\":false,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"The record already exists.\",\"Level\":3}]}}";
		String response = CommonMethods.postMethodStringPayloadString(payload, uri, version);
		Assert.assertEquals(response, exResponse);
	}

	@Test(priority = 18, groups = "rate")
	public void postCreateUnitDescription_InvalidServiceCategory()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/unitDescription";
		String version = "4.0";
		String payload = "{\r\n" +
				"    \"UnitDescription\": \"UnitX\",\r\n" +
				"    \"ServiceCategory\": \"INVALID\",\r\n" +
				"    \"DetailType\": 1\r\n" +
				"}";
		String exResponse = "{\"UnitDescription\":{\"Success\":false,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Invalid service category INVALID.\",\"Level\":3}]}}";
		String response = CommonMethods.postMethodStringPayloadString(payload, uri, version);
		Assert.assertEquals(response, exResponse);
	}

	@Test(priority = 18, groups = "rate")
	public void postCreateUnitDescription_InvalidDetailType()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/unitDescription";
		String version = "4.0";
		String payload = "{\r\n" +
				"    \"UnitDescription\": \"UnitY\",\r\n" +
				"    \"ServiceCategory\": \"ELECTRIC\",\r\n" +
				"    \"DetailType\": 99\r\n" +
				"}";
		String exResponse = "{\"UnitDescription\":{\"Success\":false,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"DetailType must be one of [1, 2, 3, 4, 5, 6, 7, 8]\",\"Level\":3}]}}";
		String response = CommonMethods.postMethodStringPayloadString(payload, uri, version);
		Assert.assertEquals(response, exResponse);
	}

	@Test(priority = 18, groups = "rate")
	public void postCreateUnitDescription_EmptyUnitDescription()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/unitDescription";
		String version = "4.0";
		String payload = "{\r\n" +
				"    \"UnitDescription\": \"\",\r\n" +
				"    \"ServiceCategory\": \"ELECTRIC\",\r\n" +
				"    \"DetailType\": 1\r\n" +
				"}";
		String exResponse = "{\"UnitDescription\":{\"Success\":false,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"UnitDescription is not allowed to be empty\",\"Level\":3}]}}";
		String response = CommonMethods.postMethodStringPayloadString(payload, uri, version);
		Assert.assertEquals(response, exResponse);
	}

	@Test(priority = 18, groups = "rate")
	public void postCreateUnitDescription_DifferentServiceAndType()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/unitDescription";
		String version = "4.0";
		String payload = "{\r\n" +
				"    \"UnitDescription\": \"UnitGasNewB\",\r\n" +
				"    \"ServiceCategory\": \"GAS\",\r\n" +
				"    \"DetailType\": 2\r\n" +
				"}";
		String exResponse = "{\"UnitDescription\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Unit Description saved successfully.\",\"Level\":1}]}}";
		String response = CommonMethods.postMethodStringPayloadString(payload, uri, version);
		Assert.assertEquals(response, exResponse);
	}

	
	@Test(priority = 19, groups = "rate")
	public void getrateunitDescription()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/unitDescription/Unit4/ELECTRIC/1";
		String ver = "4.0";
		String expected = "{\"UnitDescription\":{\"Success\":true,\"Data\":{\"UnitDescription\":\"Unit4\",\"ServiceCategory\":{\"Id\":1,\"Description\":\"ELECTRIC\"},\"DetailType\":{\"Id\":1,\"Description\":\"Fixed Charge\"},\"IsInUse\":false},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Thread.sleep(12000);
		Assert.assertEquals(result, expected);

	}

	@Test(priority = 20, groups = "rate", dependsOnMethods = "getrateunitDescription")
	public void deleterateunitDescription()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/unitDescription/Unit4/ELECTRIC/1";
		String ver = "4.0";
		String expected = "{\"UnitDescription\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Unit Description deleted successfully.\",\"Level\":1}]}}";
		//HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.deleteMethodasString(uri, ver);
		Assert.assertEquals(result, expected);

	}

		
	@Test(priority = 21, groups = "rate")
	public void getrateCalculateAutomaticEstimates()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/calculateAutomaticEstimates/SEWERMETERED?UserDate=2026-04-30";
		String ver = "4.0";
		String expected = "{\"AutomaticEstimates\":{\"Success\":true,\"Data\":{\"RateId\":\"SEWERMETERED\",\"Description\":\"Metered rates for sewrwe connection\",\"January\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"February\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"March\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"April\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"May\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"June\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"July\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"August\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"September\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"October\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"November\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"December\":{\"AverageConsumption\":0,\"NoOfBills\":0}},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Thread.sleep(12000);
		Assert.assertEquals(result, expected);

	}


	@Test(priority = 21, groups = "rate")
          	public void getrateCalculateAutomaticEstimates2()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/calculateAutomaticEstimates/WATERMETERED?UserDate=2026-04-30";
		String ver = "4.0";
		String expected = "{\"AutomaticEstimates\":{\"Success\":true,\"Data\":{\"RateId\":\"WATERMETERED\",\"Description\":\"Metered connection for water\",\"January\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"February\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"March\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"April\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"May\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"June\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"July\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"August\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"September\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"October\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"November\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"December\":{\"AverageConsumption\":0,\"NoOfBills\":0}},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Thread.sleep(12000);
		Assert.assertEquals(result, expected);

	}

	@Test(priority = 22, groups = "rate")
	public void getrateCalculateAutomaticEstimates_ElectricRate()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/calculateAutomaticEstimates/EMP-1?UserDate=2026-04-30";
		String ver = "4.0";
		String expected = "{\"AutomaticEstimates\":{\"Success\":true,\"Data\":{\"RateId\":\"EMP-1\",\"Description\":\"Electric Medium Power (13)\",\"January\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"February\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"March\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"April\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"May\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"June\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"July\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"August\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"September\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"October\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"November\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"December\":{\"AverageConsumption\":0,\"NoOfBills\":0}},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Thread.sleep(12000);
		Assert.assertEquals(result, expected);

	}



	@Test(priority = 24, groups = "rate")
	public void getrateCalculateAutomaticEstimates_JanuaryDate()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/calculateAutomaticEstimates/WATERMETERED?UserDate=2026-01-31";
		String ver = "4.0";
		String expected = "{\"AutomaticEstimates\":{\"Success\":true,\"Data\":{\"RateId\":\"WATERMETERED\",\"Description\":\"Metered connection for water\",\"January\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"February\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"March\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"April\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"May\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"June\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"July\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"August\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"September\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"October\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"November\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"December\":{\"AverageConsumption\":0,\"NoOfBills\":0}},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Thread.sleep(12000);
		Assert.assertEquals(result, expected);

	}

	@Test(priority = 25, groups = "rate")
	public void getrateCalculateAutomaticEstimates_DecemberDate()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/calculateAutomaticEstimates/WATERMETERED?UserDate=2026-12-31";
		String ver = "4.0";
		String expected = "{\"AutomaticEstimates\":{\"Success\":true,\"Data\":{\"RateId\":\"WATERMETERED\",\"Description\":\"Metered connection for water\",\"January\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"February\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"March\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"April\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"May\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"June\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"July\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"August\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"September\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"October\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"November\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"December\":{\"AverageConsumption\":0,\"NoOfBills\":0}},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Thread.sleep(12000);
		Assert.assertEquals(result, expected);

	}

	@Test(priority = 26, groups = "rate")
	public void getrateCalculateAutomaticEstimates_InvalidRate()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/calculateAutomaticEstimates/INVALID_RATE_12345?UserDate=2026-04-30";
		String ver = "4.0";
		String expected = "{\"AutomaticEstimates\":{\"Success\":false,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"RateId length must be less than or equal to 15 characters long\",\"Level\":3}]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Thread.sleep(12000);
		Assert.assertEquals(result, expected);

	}



	@Test(priority = 28, groups = "rate")
	public void getrateCalculateAutomaticEstimates_FutureDate()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/calculateAutomaticEstimates/WATERMETERED?UserDate=2030-12-31";
		String ver = "4.0";
		String expected = "{\"AutomaticEstimates\":{\"Success\":true,\"Data\":{\"RateId\":\"WATERMETERED\",\"Description\":\"Metered connection for water\",\"January\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"February\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"March\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"April\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"May\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"June\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"July\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"August\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"September\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"October\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"November\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"December\":{\"AverageConsumption\":0,\"NoOfBills\":0}},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Thread.sleep(12000);
		Assert.assertEquals(result, expected);

	}

	@Test(priority = 29, groups = "rate")
	public void getrateCalculateAutomaticEstimates_PastDate()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/calculateAutomaticEstimates/WATERMETERED?UserDate=2015-06-15";
		String ver = "4.0";
		String expected = "{\"AutomaticEstimates\":{\"Success\":true,\"Data\":{\"RateId\":\"WATERMETERED\",\"Description\":\"Metered connection for water\",\"January\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"February\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"March\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"April\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"May\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"June\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"July\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"August\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"September\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"October\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"November\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"December\":{\"AverageConsumption\":0,\"NoOfBills\":0}},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Thread.sleep(12000);
		Assert.assertEquals(result, expected);

	}

	@Test(priority = 30, groups = "rate")
	public void getrateCalculateAutomaticEstimates_EmptyRateId()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/calculateAutomaticEstimates/?UserDate=2026-04-30";
		String ver = "4.0";
		String expected = "{\"Rate\":{\"Success\":false,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"RateId length must be less than or equal to 15 characters long\",\"Level\":3}]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Thread.sleep(12000);
		Assert.assertEquals(result, expected);

	}

	@Test(priority = 31, groups = "rate")
	public void getrateCalculateAutomaticEstimates_MissingDateParameter()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/calculateAutomaticEstimates/WATERMETERED";
		String ver = "4.0";
		String expected = "{\"AutomaticEstimates\":{\"Success\":true,\"Data\":{\"RateId\":\"WATERMETERED\",\"Description\":\"Metered connection for water\",\"January\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"February\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"March\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"April\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"May\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"June\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"July\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"August\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"September\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"October\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"November\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"December\":{\"AverageConsumption\":0,\"NoOfBills\":0}},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Thread.sleep(12000);
		Assert.assertEquals(result, expected);

	}

	
	@Test(priority = 32, groups = "rate")
	public void posttrateCalculateAutomaticEstimates_MissingDateParameter()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/calculateAutomaticEstimates/WATERMETERED";
		String ver = "4.0";
		String expected = "{\"AutomaticEstimates\":{\"Success\":true,\"Data\":{\"RateId\":\"WATERMETERED\",\"Description\":\"Metered connection for water\",\"January\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"February\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"March\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"April\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"May\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"June\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"July\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"August\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"September\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"October\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"November\":{\"AverageConsumption\":0,\"NoOfBills\":0},\"December\":{\"AverageConsumption\":0,\"NoOfBills\":0}},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Thread.sleep(12000);
		Assert.assertEquals(result, expected);

	}

	@Test(priority = 9, groups = "rate")
	public void getGenerateRateMeterSizeReport()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/generateRateMeterSizeReport/RATE1/2";
		String ver = "4.0";
		String expected = "{\"RateMeterSize\":{\"Success\":true,\"Data\":{\"ReportList\":[{\"Name\":\"RateMeterSize\",\"DisplayName\":\"Rate Meter Size\",\"PrintOrder\":1}]},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result, expected);

	}

	@Test(priority = 9, groups = "rate")
	public void getGenerateRateMeterSizeReport_InvalidRate()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/generateRateMeterSizeReport/INVALIDRATE/3";
		String ver = "4.0";
		String expected = "{\"result\":{\"Success\":false,\"Message\":\"Not Found\"}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result, expected);

	}


	   

}