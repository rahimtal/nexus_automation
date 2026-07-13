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
		Assert.assertEquals(result.replace("\"MeterSizeMinimum\":null", "\"MeterSizeMinimum\":[]")
				.replaceAll(",\"CreatedDateTime\":\"[^\"]*\",\"UserId\":\"[^\"]*\"", ""), expected);

	}

	@Test(priority = 4, groups = "rate")
	public void getrateID2()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/EOL-1HPS100WOB";
		String ver = "4.0";
		String expected = "{\"Rate\":{\"Success\":true,\"Data\":{\"RateId\":\"EOL-1HPS100WOB\",\"Description\":\"Outdoor Lighting-High Pressure Sodium 100Watt Open Bottom\",\"Type\":{\"Id\":1,\"Description\":\"Consumption\"},\"ServiceType\":\"ST-LIGHTS\",\"RateClassId\":\"\",\"BillingMessageExist\":false,\"Active\":true,\"UseLatestRateEffectivePeriod\":false,\"ConsecutiveEstimatesAllowed\":0,\"BillInAdvance\":false,\"LookupVisible\":false,\"SpecialCondition\":false,\"TimeOfUse\":false,\"ExcludeFromBd\":false,\"RatchetDemand\":false,\"KvarFactor\":0.00000,\"RateInUse\":true,\"EffectiveDate\":[{\"EffectiveStartDate\":\"1999-01-01\",\"MinimumAmount\":0.00,\"MaximumAmount\":0.00,\"ProrateMinimum\":{\"First\":false,\"Regular\":false,\"Last\":false},\"ProrateMaximum\":{\"First\":false,\"Regular\":false,\"Last\":false}}],\"Detail\":[{\"DetailIndex\":1,\"Detail\":{\"Type\":1,\"Description\":\"Fixed Charge\"},\"EffectiveStartDate\":\"1999-01-01\",\"EffectiveEndDate\":\"1900-01-01\",\"DetailDescription\":\"Monthly Charge-street lights\",\"TaxSchedule\":\"USALLEXMPT-0\",\"ServiceType\":\"ST-LIGHTS\",\"BillingFrequency\":30,\"ProrateDetail\":{\"First\":true,\"Regular\":false,\"Last\":true},\"ProrateMinimum\":{\"First\":false,\"Regular\":false,\"Last\":false},\"MinimumCharge\":0.00,\"UnitDescription\":\"\",\"FixedCharge\":16.00000,\"Consumption\":{\"BillingDemandMinimum\":0.00,\"ConsumptionTolerance\":0.00000,\"UseActualDays\":false,\"Reporting\":{\"IncludeUnits\":true,\"IncludeRevenue\":true},\"ExportDetail\":false,\"ApplyDiscountPercentage\":0,\"WinterNormalizationAdjustment\":{\"Type\":0,\"TypeDetail\":0},\"CustomerChoice\":0,\"OldestEstimateUpdateDate\":\"1900-01-01\"},\"RevenueAccount\":{\"Index\":524,\"Number\":\"900-4614-00\",\"Description\":\"Metered Sales to Public Auth. - Electric\"},\"ReceivableAccount\":{\"Index\":506,\"Number\":\"900-1410-00\",\"Description\":\"Customer Accounts Receivable - Electric\"},\"DetailSequence\":null,\"MeterSizeMinimum\":[],\"AutomaticEstimates\":null}]},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result.replace("\"MeterSizeMinimum\":null", "\"MeterSizeMinimum\":[]")
				.replaceAll(",\"CreatedDateTime\":\"[^\"]*\",\"UserId\":\"[^\"]*\"", ""), expected);

	}

	@Test(priority = 5, groups = "rate")
	public void getrateID3()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/EPCA-1";
		String ver = "4.0";
		String expected = "{\"Rate\":{\"Success\":true,\"Data\":{\"RateId\":\"EPCA-1\",\"Description\":\"Electric Power Cost Adjustment\",\"Type\":{\"Id\":1,\"Description\":\"Consumption\"},\"ServiceType\":\"ELECTRIC\",\"RateClassId\":\"\",\"BillingMessageExist\":false,\"Active\":true,\"UseLatestRateEffectivePeriod\":false,\"ConsecutiveEstimatesAllowed\":0,\"BillInAdvance\":false,\"LookupVisible\":false,\"SpecialCondition\":false,\"TimeOfUse\":false,\"ExcludeFromBd\":false,\"RatchetDemand\":true,\"KvarFactor\":0.00000,\"RateInUse\":true,\"EffectiveDate\":[{\"EffectiveStartDate\":\"1998-01-01\",\"MinimumAmount\":0.00,\"MaximumAmount\":0.00,\"ProrateMinimum\":{\"First\":false,\"Regular\":false,\"Last\":false},\"ProrateMaximum\":{\"First\":false,\"Regular\":false,\"Last\":false}}],\"Detail\":[{\"DetailIndex\":1,\"Detail\":{\"Type\":2,\"Description\":\"Stepped Range\"},\"EffectiveStartDate\":\"1998-01-01\",\"EffectiveEndDate\":\"1900-01-01\",\"DetailDescription\":\"PCA for Electric Service\",\"TaxSchedule\":\"USASTCITY-6*\",\"ServiceType\":\"ELECTRIC\",\"BillingFrequency\":90,\"ProrateDetail\":{\"First\":false,\"Regular\":false,\"Last\":false},\"ProrateMinimum\":{\"First\":false,\"Regular\":false,\"Last\":false},\"MinimumCharge\":0.00,\"UnitDescription\":\"\",\"FixedCharge\":0.00000,\"Consumption\":{\"BillingDemandMinimum\":0.00,\"ConsumptionTolerance\":0.00000,\"UseActualDays\":false,\"Reporting\":{\"IncludeUnits\":true,\"IncludeRevenue\":true},\"ExportDetail\":false,\"ApplyDiscountPercentage\":0,\"WinterNormalizationAdjustment\":{\"Type\":0,\"TypeDetail\":0},\"CustomerChoice\":0,\"OldestEstimateUpdateDate\":\"1900-01-01\"},\"RevenueAccount\":{\"Index\":515,\"Number\":\"900-4611-00\",\"Description\":\"Metered Sales to Residential - Electric\"},\"ReceivableAccount\":{\"Index\":506,\"Number\":\"900-1410-00\",\"Description\":\"Customer Accounts Receivable - Electric\"},\"DetailSequence\":[{\"DetailIndexSequence\":1,\"UnitRate\":0.53000,\"VolumeLowerLimit\":0,\"VolumeUpperLimit\":999999999,\"VolumeLowerLimitString\":\"\",\"VolumeUpperLimitString\":\"\"}],\"MeterSizeMinimum\":[],\"AutomaticEstimates\":null}]},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result.replace("\"MeterSizeMinimum\":null", "\"MeterSizeMinimum\":[]")
				.replaceAll(",\"CreatedDateTime\":\"[^\"]*\",\"UserId\":\"[^\"]*\"", ""), expected);

	}

	@Test(priority = 6, groups = "rate")
	public void getrateID4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/INTERNETFIX";
		String ver = "4.0";
		String expected = "{\"Rate\":{\"Success\":true,\"Data\":{\"RateId\":\"INTERNETFIX\",\"Description\":\"\",\"Type\":{\"Id\":0,\"Description\":\"\"},\"ServiceType\":\"INTERNET\",\"RateClassId\":\"\",\"BillingMessageExist\":false,\"Active\":true,\"UseLatestRateEffectivePeriod\":false,\"ConsecutiveEstimatesAllowed\":0,\"BillInAdvance\":false,\"LookupVisible\":false,\"SpecialCondition\":false,\"TimeOfUse\":false,\"ExcludeFromBd\":false,\"RatchetDemand\":false,\"KvarFactor\":0.00000,\"RateInUse\":true,\"EffectiveDate\":[{\"EffectiveStartDate\":\"2019-06-01\",\"MinimumAmount\":0.00,\"MaximumAmount\":0.00,\"ProrateMinimum\":{\"First\":false,\"Regular\":false,\"Last\":false},\"ProrateMaximum\":{\"First\":false,\"Regular\":false,\"Last\":false}}],\"Detail\":[{\"DetailIndex\":1,\"Detail\":{\"Type\":1,\"Description\":\"Fixed Charge\"},\"EffectiveStartDate\":\"2019-06-01\",\"EffectiveEndDate\":\"1900-01-01\",\"DetailDescription\":\"\",\"TaxSchedule\":\"USAUSSTCITY+6*\",\"ServiceType\":\"INTERNET\",\"BillingFrequency\":30,\"ProrateDetail\":{\"First\":false,\"Regular\":false,\"Last\":false},\"ProrateMinimum\":{\"First\":false,\"Regular\":false,\"Last\":false},\"MinimumCharge\":0.00,\"UnitDescription\":\"\",\"FixedCharge\":10.00000,\"Consumption\":{\"BillingDemandMinimum\":0.00,\"ConsumptionTolerance\":0.00000,\"UseActualDays\":false,\"Reporting\":{\"IncludeUnits\":false,\"IncludeRevenue\":false},\"ExportDetail\":false,\"ApplyDiscountPercentage\":0,\"WinterNormalizationAdjustment\":{\"Type\":0,\"TypeDetail\":0},\"CustomerChoice\":0,\"OldestEstimateUpdateDate\":\"1900-01-01\"},\"RevenueAccount\":{\"Index\":624,\"Number\":\"900-4616-00\",\"Description\":\"Internet Service\"},\"ReceivableAccount\":{\"Index\":623,\"Number\":\"900-1414-00\",\"Description\":\"Customer Account Receivable-Internet service\"},\"DetailSequence\":null,\"MeterSizeMinimum\":[],\"AutomaticEstimates\":null}]},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result.replace("\"MeterSizeMinimum\":null", "\"MeterSizeMinimum\":[]")
				.replaceAll(",\"CreatedDateTime\":\"[^\"]*\",\"UserId\":\"[^\"]*\"", ""), expected);

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
		Assert.assertEquals(result.replace("\"MeterSizeMinimum\":null", "\"MeterSizeMinimum\":[]")
				.replaceAll(",\"CreatedDateTime\":\"[^\"]*\",\"UserId\":\"[^\"]*\"", ""), expected);

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
		String expected = "{\"RateMeterSize\":{\"Success\":false,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Rate INVALIDRATE does not exist.\",\"Level\":3}]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result, expected);

	}


	// ==========================================================================
	// Rate Flip Inquiry - CPDEV-26651
	// Endpoints:
	//   GET /rate/flip/:RateId                        (rate flip inquiry data)
	//   GET /rate/generateRateFlipReport/:RateClassId (rate flip SSRS report)
	// Source tables: UMRFHDR (header), UM41320, csmApi_vwReportRateFlip,
	//                csmApi_ReportHeader (ReportName = 'RateFlipReport')
	// ==========================================================================

	// Verify the rate flip inquiry returns the correct response and the
	// expanded period / step tables when the rate class exists.
	@Test(priority = 63, groups = "rate")
	public void getRateFlip_v4() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/rate/flip/ELECTRATE";
		String ver = "4.0";
		String expected = "{\"Rate\":{\"Success\":true,\"Data\":{\"RateClassID\":\"ELECTRATE\",\"ServiceCategory\":{\"Id\":1,\"Description\":\"Electric\"},\"RateType\":{\"Id\":1,\"Description\":\"Consumption\"},\"NumberOfMonths\":6,\"RateFlip\":[{\"PeriodID\":\"ON PEAK\",\"PeriodIndex\":1,\"Steps\":[{\"SequenceNumber\":1,\"VolumeLowerLimit\":111,\"VolumeUpperLimit\":999999999,\"TariffID\":\"EPCA-1\",\"KWRate\":\"RATE1\",\"KVARate\":\"RATE1\"}]},{\"PeriodID\":\"OFFPEAK\",\"PeriodIndex\":2,\"Steps\":[{\"SequenceNumber\":1,\"VolumeLowerLimit\":10000,\"VolumeUpperLimit\":23123134,\"TariffID\":\"GS-PK ENERGY\",\"KWRate\":\"RATE1\",\"KVARate\":\"RATE1\"}]}]},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(result);
		Assert.assertEquals(result, expected);
	}

	// Verify the response when the requested rate class does not exist.
	@Test(priority = 64, groups = "rate")
	public void getRateFlip_InvalidRate_v4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/rate/flip/INVALIDRATE";
		String ver = "4.0";
		String expected = "{\"Rate\":{\"Success\":true,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Rate Flip for INVALIDRATE does not exist.\",\"Level\":2}]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(result);
		Assert.assertEquals(result, expected);
	}

	// Generate Rate Flip report metadata for an existing rate class. The report
	// header ('RateFlipReport' in csmApi_ReportHeader) is seeded in the current
	// backup, so the endpoint returns the ReportList success payload.
	@Test(priority = 65, groups = "rate")
	public void getGenerateRateFlipReport_v4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/rate/generateRateFlipReport/ELECTRATE";
		String ver = "4.0";
		String expected = "{\"RateFlipReport\":{\"Success\":true,\"Data\":{\"ReportList\":[{\"Name\":\"RateFlipReport\",\"DisplayName\":\"Rate Flip Report\",\"PrintOrder\":1}]},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(result);
		Assert.assertEquals(result, expected);
	}

	// Verify the report response when the rate class does not exist.
	@Test(priority = 66, groups = "rate")
	public void getGenerateRateFlipReport_InvalidRateClass_v4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/rate/generateRateFlipReport/INVALIDRATE";
		String ver = "4.0";
		String expected = "{\"RateFlipReport\":{\"Success\":false,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Invalid Rate Class Id INVALIDRATE.\",\"Level\":3}]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(result);
		Assert.assertEquals(result, expected);
	}

	// Verify generateRateMeterSizeReport returns the report metadata for an
	// existing rate / detail index (RateId = gas1, DetailIndex = 1).
	@Test(priority = 67, groups = "rate")
	public void getGenerateRateMeterSizeReport_Gas1_v4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/rate/generateRateMeterSizeReport/gas1/1";
		String ver = "4.0";
		String expected = "{\"RateMeterSize\":{\"Success\":true,\"Data\":{\"ReportList\":[{\"Name\":\"RateMeterSize\",\"DisplayName\":\"Rate Meter Size\",\"PrintOrder\":1}]},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(result);
		Assert.assertEquals(result, expected);
	}

	// Verify the report response when the requested rate does not exist.
	@Test(priority = 68, groups = "rate")
	public void getGenerateRateMeterSizeReport_InvalidRate_v4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/rate/generateRateMeterSizeReport/INVALIDRATE/1";
		String ver = "4.0";
		String expected = "{\"RateMeterSize\":{\"Success\":false,\"Data\":null,\"Messages\":[{\"Enabled\":1,\"Info\":\"Rate INVALIDRATE does not exist.\",\"Level\":3}]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(result);
		Assert.assertEquals(result, expected);
	}

	// ==========================================================================
	
	// Verify the Get Rate Information response returns the CreatedDateTime and
	// UserId audit fields inside the EffectiveDate element.
	@Test(priority = 69, groups = "rate")
	public void getRateInformation_AuditFields_v4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/rate/EPCA-1";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(result);

		JsonPath jp = new JsonPath(result);
		Assert.assertTrue(jp.getBoolean("Rate.Success"),
				"Rate.Success should be true. Actual: " + result);
		Assert.assertEquals(jp.getString("Rate.Data.RateId"), "EPCA-1",
				"Rate.Data.RateId should be EPCA-1. Actual: " + result);

		// Audit fields present on the first EffectiveDate element.
		String createdDateTime = jp.getString("Rate.Data.EffectiveDate[0].CreatedDateTime");
		String userId = jp.getString("Rate.Data.EffectiveDate[0].UserId");
		Assert.assertNotNull(createdDateTime,
				"CreatedDateTime audit field should be present. Actual: " + result);
		Assert.assertFalse(createdDateTime.trim().isEmpty(),
				"CreatedDateTime audit field should not be empty. Actual: " + result);
		Assert.assertNotNull(userId,
				"UserId audit field should be present (may be empty or a login). Actual: " + result);
	}

	// Verify the audit fields are returned when the request is scoped by an
	// EffectiveDate query parameter. The screenshot used EffectiveDate=2001-01-01,
	// but EPCA-1's effective period in the restored DB starts 1998-01-01, so we
	// scope by that actual start date to keep the test independent of extra data.
	@Test(priority = 70, groups = "rate")
	public void getRateInformation_AuditFields_WithEffectiveDate_v4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/rate/EPCA-1";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("EffectiveDate", "1998-01-01");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(result);

		JsonPath jp = new JsonPath(result);
		Assert.assertTrue(jp.getBoolean("Rate.Success"),
				"Rate.Success should be true for the effective period. Actual: " + result);

		String createdDateTime = jp.getString("Rate.Data.EffectiveDate[0].CreatedDateTime");
		String userId = jp.getString("Rate.Data.EffectiveDate[0].UserId");
		Assert.assertNotNull(createdDateTime,
				"CreatedDateTime audit field should be present. Actual: " + result);
		Assert.assertFalse(createdDateTime.trim().isEmpty(),
				"CreatedDateTime audit field should not be empty. Actual: " + result);
		Assert.assertNotNull(userId,
				"UserId audit field should be present (may be empty or a login). Actual: " + result);
	}

	// ==========================================================================
	// Rate validation - getRate (getRateValidation) and postRate
	// (postRateValidation + csmApi_spRateCreateValidation) negative scenarios.
	// Reference: src/server/validations/Rate.ts and the rateController SPs.
	// These assert Success:false and the specific validation message. Payloads
	// start from a schema-valid Type-1 (Fixed Charge) base and mutate one field.
	// ==========================================================================

	// Compact, schema-valid Type-1 (Fixed Charge) create payload used as the base
	// for postRate validation tests. Individual tests mutate a single field to
	// trigger a specific validation error.
	private static String validRateCreatePayload(String rateId) {
		return "{\"RateId\":\"" + rateId + "\",\"Description\":\"Validation base rate\",\"Type\":{\"Id\":1},"
				+ "\"ServiceType\":\"ELECTRIC\",\"RateClassId\":\"\",\"Active\":1,\"UseLatestRateEffectivePeriod\":false,"
				+ "\"ConsecutiveEstimatesAllowed\":0,\"BillInAdvance\":false,\"LookupVisible\":false,\"SpecialCondition\":false,"
				+ "\"TimeOfUse\":false,\"ExcludeFromBd\":false,\"RatchetDemand\":false,\"KvarFactor\":\"0.00000\","
				+ "\"EffectiveDate\":[{\"EffectiveStartDate\":\"2026-01-01\",\"MinimumAmount\":\"0.00\",\"MaximumAmount\":\"0.00\","
				+ "\"ProrateMinimum\":{\"First\":false,\"Regular\":false,\"Last\":false},"
				+ "\"ProrateMaximum\":{\"First\":false,\"Regular\":false,\"Last\":false}}],"
				+ "\"Detail\":[{\"DetailIndex\":1,\"Detail\":{\"Type\":1},\"EffectiveStartDate\":\"2026-01-01\","
				+ "\"DetailDescription\":\"Fixed\",\"TaxSchedule\":\"USASTCITY-6*\",\"ServiceType\":\"ELECTRIC\",\"BillingFrequency\":30,"
				+ "\"ProrateDetail\":{\"First\":false,\"Regular\":false,\"Last\":false},"
				+ "\"ProrateMinimum\":{\"First\":false,\"Regular\":false,\"Last\":false},\"MinimumCharge\":\"0.00\","
				+ "\"UnitDescription\":\"\",\"FixedCharge\":\"10.00000\","
				+ "\"Consumption\":{\"BillingDemandMinimum\":\"0.00\",\"ConsumptionTolerance\":\"0.00000\",\"UseActualDays\":false,"
				+ "\"Reporting\":{\"IncludeUnits\":true,\"IncludeRevenue\":true},\"ExportDetail\":false,\"ApplyDiscountPercentage\":0,"
				+ "\"WinterNormalizationAdjustment\":{\"Type\":0,\"TypeDetail\":0},\"CustomerChoice\":0},"
				+ "\"RevenueAccount\":{\"Index\":515},\"ReceivableAccount\":{\"Index\":506},"
				+ "\"DetailSequence\":[],\"MeterSizeMinimum\":null,\"AutomaticEstimates\":null}]}";
	}

	// -------- getRate validation --------

	// RateId longer than 15 characters is rejected by getRateValidation.
	@Test(priority = 71, groups = "rate")
	public void getRateValidation_RateIdTooLong()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/rate/THISRATEIDTOOLONG16";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(result);
		Assert.assertTrue(result.contains("\"Success\":false"),
				"Expected Success:false for an over-length RateId. Actual: " + result);
		Assert.assertTrue(result.contains("RateId length must be less than or equal to 15 characters long"),
				"Expected max-length message. Actual: " + result);
	}

	// A well-formed RateId that does not exist returns the rate-not-found message.
	@Test(priority = 72, groups = "rate")
	public void getRateValidation_RateNotFound()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/rate/NOSUCHRATE";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(result);
		Assert.assertTrue(result.contains("\"Success\":false"),
				"Expected Success:false for a non-existent rate. Actual: " + result);
		Assert.assertTrue(result.contains("does not exist") || result.contains("not found"),
				"Expected rate-not-found message. Actual: " + result);
	}

	// Requesting an effective date the rate has no period for returns not found.
	@Test(priority = 73, groups = "rate")
	public void getRateValidation_EffectiveDateNotFound()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String uri = "/rate/EPCA-1";
		String ver = "4.0";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("EffectiveDate", "1901-01-01");
		String result = CommonMethods.getMethodasString(uri, ver, params);
		System.out.println(result);
		Assert.assertTrue(result.contains("\"Success\":false"),
				"Expected Success:false for a missing effective date. Actual: " + result);
		Assert.assertTrue(result.contains("Effective Date") && result.contains("not found"),
				"Expected effective-date-not-found message. Actual: " + result);
	}

	// -------- postRate validation --------

	// Creating a rate whose RateId already exists is rejected (use update).
	@Test(priority = 74, groups = "rate")
	public void postRateValidation_DuplicateRateId()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String payload = validRateCreatePayload("EMP-1");
		String response = CommonMethods.postMethodStringPayloadString(payload, "/rate", "4.0");
		System.out.println(response);
		Assert.assertTrue(response.contains("\"Success\":false"),
				"Expected Success:false for a duplicate RateId. Actual: " + response);
		Assert.assertTrue(response.contains("already exist"),
				"Expected 'already exists' message. Actual: " + response);
	}

	// RateId longer than 15 characters is rejected by postRateValidation schema.
	@Test(priority = 75, groups = "rate")
	public void postRateValidation_RateIdTooLong()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String payload = validRateCreatePayload("THISIDTOOLONG16X");
		String response = CommonMethods.postMethodStringPayloadString(payload, "/rate", "4.0");
		System.out.println(response);
		Assert.assertTrue(response.contains("\"Success\":false"),
				"Expected Success:false for an over-length RateId. Actual: " + response);
		Assert.assertTrue(response.contains("length must be less than or equal to 15"),
				"Expected RateId max-length message. Actual: " + response);
	}

	// Invalid header ServiceType is rejected before detail validations.
	@Test(priority = 76, groups = "rate")
	public void postRateValidation_InvalidHeaderServiceType()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String payload = validRateCreatePayload("RATEVAL1")
				.replace("\"ServiceType\":\"ELECTRIC\",\"RateClassId\"", "\"ServiceType\":\"INVALIDST\",\"RateClassId\"");
		String response = CommonMethods.postMethodStringPayloadString(payload, "/rate", "4.0");
		System.out.println(response);
		Assert.assertTrue(response.contains("\"Success\":false"),
				"Expected Success:false for an invalid header service type. Actual: " + response);
		Assert.assertTrue(response.contains("Invalid Service Type") || response.contains("service type"),
				"Expected invalid service type message. Actual: " + response);
	}

	// Invalid TaxSchedule on a detail item is rejected.
	@Test(priority = 77, groups = "rate")
	public void postRateValidation_InvalidTaxSchedule()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String payload = validRateCreatePayload("RATEVAL2")
				.replace("\"TaxSchedule\":\"USASTCITY-6*\"", "\"TaxSchedule\":\"NOSUCHTAX\"");
		String response = CommonMethods.postMethodStringPayloadString(payload, "/rate", "4.0");
		System.out.println(response);
		Assert.assertTrue(response.contains("\"Success\":false"),
				"Expected Success:false for an invalid tax schedule. Actual: " + response);
		Assert.assertTrue(response.contains("tax schedule"),
				"Expected invalid tax schedule message. Actual: " + response);
	}

	// Invalid detail ServiceType (header valid) is rejected.
	@Test(priority = 78, groups = "rate")
	public void postRateValidation_InvalidDetailServiceType()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String payload = validRateCreatePayload("RATEVAL3")
				.replace("\"ServiceType\":\"ELECTRIC\",\"BillingFrequency\"", "\"ServiceType\":\"INVALIDST\",\"BillingFrequency\"");
		String response = CommonMethods.postMethodStringPayloadString(payload, "/rate", "4.0");
		System.out.println(response);
		Assert.assertTrue(response.contains("\"Success\":false"),
				"Expected Success:false for an invalid detail service type. Actual: " + response);
		Assert.assertTrue(response.contains("Service Type") || response.contains("service type"),
				"Expected invalid service type message. Actual: " + response);
	}

	// Invalid (non-existent) unit description on a detail item is rejected.
	@Test(priority = 79, groups = "rate")
	public void postRateValidation_InvalidUnitDescription()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String payload = validRateCreatePayload("RATEVAL4")
				.replace("\"UnitDescription\":\"\"", "\"UnitDescription\":\"NoSuchUnit\"");
		String response = CommonMethods.postMethodStringPayloadString(payload, "/rate", "4.0");
		System.out.println(response);
		Assert.assertTrue(response.contains("\"Success\":false"),
				"Expected Success:false for an invalid unit description. Actual: " + response);
		Assert.assertTrue(response.contains("Invalid Unit Description"),
				"Expected invalid unit description message. Actual: " + response);
	}

	// A RevenueAccount.Index that does not exist in GL is rejected.
	@Test(priority = 80, groups = "rate")
	public void postRateValidation_InvalidRevenueAccountIndex()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String payload = validRateCreatePayload("RATEVAL5")
				.replace("\"RevenueAccount\":{\"Index\":515}", "\"RevenueAccount\":{\"Index\":987654}");
		String response = CommonMethods.postMethodStringPayloadString(payload, "/rate", "4.0");
		System.out.println(response);
		Assert.assertTrue(response.contains("\"Success\":false"),
				"Expected Success:false for an invalid account index. Actual: " + response);
		Assert.assertTrue(response.contains("account index") || response.contains("account"),
				"Expected invalid account index message. Actual: " + response);
	}

	// EffectiveStartDate of 1900-01-01 is rejected by the schema.
	@Test(priority = 81, groups = "rate")
	public void postRateValidation_EffectiveStartDate1900()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String payload = validRateCreatePayload("RATEVAL6").replace("2026-01-01", "1900-01-01");
		String response = CommonMethods.postMethodStringPayloadString(payload, "/rate", "4.0");
		System.out.println(response);
		Assert.assertTrue(response.contains("\"Success\":false"),
				"Expected Success:false for an 1900-01-01 effective start date. Actual: " + response);
		Assert.assertTrue(response.contains("1900-01-01") || response.contains("EffectiveStartDate"),
				"Expected 1900-01-01 rejection message. Actual: " + response);
	}

	// DetailSequence must be empty when Detail.Type is 1 (Fixed Charge).
	@Test(priority = 82, groups = "rate")
	public void postRateValidation_DetailSequenceNotEmptyForFixedCharge()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String payload = validRateCreatePayload("RATEVAL7").replace("\"DetailSequence\":[]",
				"\"DetailSequence\":[{\"DetailIndexSequence\":1,\"UnitRate\":\"1.00000\",\"VolumeLowerLimit\":\"0\","
						+ "\"VolumeUpperLimit\":\"100\",\"VolumeLowerLimitString\":\"0\",\"VolumeUpperLimitString\":\"999999999\"}]");
		String response = CommonMethods.postMethodStringPayloadString(payload, "/rate", "4.0");
		System.out.println(response);
		Assert.assertTrue(response.contains("\"Success\":false"),
				"Expected Success:false for a non-empty DetailSequence on a fixed charge. Actual: " + response);
		Assert.assertTrue(response.contains("DetailSequence must be empty"),
				"Expected DetailSequence-must-be-empty message. Actual: " + response);
	}

	// WNA TypeDetail "Base" (=1) is only valid for stepped-range (Type 2) details.
	@Test(priority = 83, groups = "rate")
	public void postRateValidation_WNATypeDetailBaseOnFixedCharge()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String payload = validRateCreatePayload("RATEVAL8")
				.replace("\"WinterNormalizationAdjustment\":{\"Type\":0,\"TypeDetail\":0}",
						"\"WinterNormalizationAdjustment\":{\"Type\":2,\"TypeDetail\":1}");
		String response = CommonMethods.postMethodStringPayloadString(payload, "/rate", "4.0");
		System.out.println(response);
		Assert.assertTrue(response.contains("\"Success\":false"),
				"Expected Success:false for WNA Base on a fixed charge. Actual: " + response);
		Assert.assertTrue(response.contains("only allowed for stepped range"),
				"Expected WNA-Base-stepped-range message. Actual: " + response);
	}

	// All Detail.EffectiveStartDate values must equal EffectiveDate[0].EffectiveStartDate.
	@Test(priority = 84, groups = "rate")
	public void postRateValidation_DetailEffectiveDateMismatch()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		String payload = validRateCreatePayload("RATEVAL9").replace(
				"\"Detail\":[{\"DetailIndex\":1,\"Detail\":{\"Type\":1},\"EffectiveStartDate\":\"2026-01-01\"",
				"\"Detail\":[{\"DetailIndex\":1,\"Detail\":{\"Type\":1},\"EffectiveStartDate\":\"2027-01-01\"");
		String response = CommonMethods.postMethodStringPayloadString(payload, "/rate", "4.0");
		System.out.println(response);
		Assert.assertTrue(response.contains("\"Success\":false"),
				"Expected Success:false when detail effective date does not match header. Actual: " + response);
		Assert.assertTrue(response.contains("must be the same as EffectiveDate"),
				"Expected detail-effective-date-mismatch message. Actual: " + response);
	}

}