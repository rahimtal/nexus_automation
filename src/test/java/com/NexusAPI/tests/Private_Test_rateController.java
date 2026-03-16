package com.NexusAPI.Tests;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.NexustAPIAutomation.java.CommonMethods;

public class Private_Test_rateController extends BaseClass {

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
		String expected = "{\"Rate\":{\"Success\":true,\"Data\":{\"RateId\":\"EMP-1\",\"Description\":\"Electric Medium Power (13)\",\"Type\":{\"Id\":1,\"Description\":\"Consumption\"},\"ServiceType\":\"ELECTRIC\",\"RateClassId\":\"\",\"BillingMessageExist\":false,\"Active\":true,\"UseLatestRateEffectivePeriod\":false,\"ConsecutiveEstimatesAllowed\":0,\"BillInAdvance\":false,\"LookupVisible\":false,\"SpecialCondition\":false,\"TimeOfUse\":false,\"ExcludeFromBd\":false,\"RatchetDemand\":true,\"KvarFactor\":0.00000,\"EffectiveDate\":[{\"EffectiveStartDate\":\"1998-01-01\",\"MinimumAmount\":0.00,\"MaximumAmount\":0.00,\"ProrateMinimum\":{\"First\":false,\"Regular\":false,\"Last\":false},\"ProrateMaximum\":{\"First\":false,\"Regular\":false,\"Last\":false}}],\"Detail\":[{\"DetailIndex\":1,\"Detail\":{\"Type\":1,\"Description\":\"Fixed Charge\"},\"EffectiveStartDate\":\"1998-01-01\",\"EffectiveEndDate\":\"1900-01-01\",\"DetailDescription\":\"Electric Monhtly Charge\",\"TaxSchedule\":\"USASTCITY-6*\",\"ServiceType\":\"ELECTRIC\",\"BillingFrequency\":90,\"ProrateDetail\":{\"First\":true,\"Regular\":false,\"Last\":true},\"ProrateMinimum\":{\"First\":false,\"Regular\":false,\"Last\":false},\"MinimumCharge\":0.00,\"UnitDescription\":\"\",\"FixedCharge\":16.42000,\"Consumption\":{\"BillingDemandMinimum\":0.00,\"ConsumptionTolerance\":0.00000,\"UseActualDays\":false,\"Reporting\":{\"IncludeUnits\":true,\"IncludeRevenue\":true},\"ExportDetail\":false,\"ApplyDiscountPercentage\":0,\"WinterNormalizationAdjustment\":{\"Type\":0,\"TypeDetail\":0},\"CustomerChoice\":0,\"OldestEstimateUpdateDate\":\"1900-01-01\"},\"RevenueAccount\":{\"Index\":515,\"Number\":\"900-4611-00\",\"Description\":\"Metered Sales to Residential - Electric\"},\"ReceivableAccount\":{\"Index\":506,\"Number\":\"900-1410-00\",\"Description\":\"Customer Accounts Receivable - Electric\"},\"DetailSequence\":null,\"MeterSizeMinimum\":[],\"AutomaticEstimates\":null},{\"DetailIndex\":2,\"Detail\":{\"Type\":4,\"Description\":\"Adjustable Var Stepped Range\"},\"EffectiveStartDate\":\"1998-01-01\",\"EffectiveEndDate\":\"1900-01-01\",\"DetailDescription\":\"Electric Energy Charge MP-1\",\"TaxSchedule\":\"USASTCITY-6*\",\"ServiceType\":\"ELECTRIC\",\"BillingFrequency\":90,\"ProrateDetail\":{\"First\":false,\"Regular\":false,\"Last\":false},\"ProrateMinimum\":{\"First\":false,\"Regular\":false,\"Last\":false},\"MinimumCharge\":0.00,\"UnitDescription\":\"\",\"FixedCharge\":0.00000,\"Consumption\":{\"BillingDemandMinimum\":0.00,\"ConsumptionTolerance\":0.00000,\"UseActualDays\":false,\"Reporting\":{\"IncludeUnits\":true,\"IncludeRevenue\":true},\"ExportDetail\":false,\"ApplyDiscountPercentage\":0,\"WinterNormalizationAdjustment\":{\"Type\":0,\"TypeDetail\":0},\"CustomerChoice\":0,\"OldestEstimateUpdateDate\":\"1900-01-01\"},\"RevenueAccount\":{\"Index\":515,\"Number\":\"900-4611-00\",\"Description\":\"Metered Sales to Residential - Electric\"},\"ReceivableAccount\":{\"Index\":506,\"Number\":\"900-1410-00\",\"Description\":\"Customer Accounts Receivable - Electric\"},\"DetailSequence\":[{\"DetailIndexSequence\":1,\"UnitRate\":0.10689,\"VolumeLowerLimit\":0,\"VolumeUpperLimit\":0,\"VolumeLowerLimitString\":\"0\",\"VolumeUpperLimitString\":\"3000\"},{\"DetailIndexSequence\":2,\"UnitRate\":0.09789,\"VolumeLowerLimit\":0,\"VolumeUpperLimit\":0,\"VolumeLowerLimitString\":\"3000+1\",\"VolumeUpperLimitString\":\"10000\"},{\"DetailIndexSequence\":3,\"UnitRate\":0.08560,\"VolumeLowerLimit\":0,\"VolumeUpperLimit\":0,\"VolumeLowerLimitString\":\"10000+1\",\"VolumeUpperLimitString\":\"200000\"},{\"DetailIndexSequence\":4,\"UnitRate\":0.01089,\"VolumeLowerLimit\":0,\"VolumeUpperLimit\":0,\"VolumeLowerLimitString\":\"200000+1\",\"VolumeUpperLimitString\":\"BD*200\"},{\"DetailIndexSequence\":5,\"UnitRate\":0.01078,\"VolumeLowerLimit\":0,\"VolumeUpperLimit\":0,\"VolumeLowerLimitString\":\"BD*200+1\",\"VolumeUpperLimitString\":\"BD*400\"},{\"DetailIndexSequence\":6,\"UnitRate\":0.00989,\"VolumeLowerLimit\":0,\"VolumeUpperLimit\":0,\"VolumeLowerLimitString\":\"BD*400+1\",\"VolumeUpperLimitString\":\"BD*600\"},{\"DetailIndexSequence\":7,\"UnitRate\":0.00787,\"VolumeLowerLimit\":0,\"VolumeUpperLimit\":0,\"VolumeLowerLimitString\":\"BD*600+1\",\"VolumeUpperLimitString\":\"999999999\"}],\"MeterSizeMinimum\":[],\"AutomaticEstimates\":null}]},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result, expected);

	}

	@Test(priority = 4, groups = "rate")
	public void getrateID2()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/EOL-1HPS100WOB";
		String ver = "4.0";
		String expected = "{\"Rate\":{\"Success\":true,\"Data\":{\"RateId\":\"EOL-1HPS100WOB\",\"Description\":\"Outdoor Lighting-High Pressure Sodium 100Watt Open Bottom\",\"Type\":{\"Id\":1,\"Description\":\"Consumption\"},\"ServiceType\":\"ST-LIGHTS\",\"RateClassId\":\"\",\"BillingMessageExist\":false,\"Active\":true,\"UseLatestRateEffectivePeriod\":false,\"ConsecutiveEstimatesAllowed\":0,\"BillInAdvance\":false,\"LookupVisible\":false,\"SpecialCondition\":false,\"TimeOfUse\":false,\"ExcludeFromBd\":false,\"RatchetDemand\":false,\"KvarFactor\":0.00000,\"EffectiveDate\":[{\"EffectiveStartDate\":\"1999-01-01\",\"MinimumAmount\":0.00,\"MaximumAmount\":0.00,\"ProrateMinimum\":{\"First\":false,\"Regular\":false,\"Last\":false},\"ProrateMaximum\":{\"First\":false,\"Regular\":false,\"Last\":false}}],\"Detail\":[{\"DetailIndex\":1,\"Detail\":{\"Type\":1,\"Description\":\"Fixed Charge\"},\"EffectiveStartDate\":\"1999-01-01\",\"EffectiveEndDate\":\"1900-01-01\",\"DetailDescription\":\"Monthly Charge-street lights\",\"TaxSchedule\":\"USALLEXMPT-0\",\"ServiceType\":\"ST-LIGHTS\",\"BillingFrequency\":30,\"ProrateDetail\":{\"First\":true,\"Regular\":false,\"Last\":true},\"ProrateMinimum\":{\"First\":false,\"Regular\":false,\"Last\":false},\"MinimumCharge\":0.00,\"UnitDescription\":\"\",\"FixedCharge\":16.00000,\"Consumption\":{\"BillingDemandMinimum\":0.00,\"ConsumptionTolerance\":0.00000,\"UseActualDays\":false,\"Reporting\":{\"IncludeUnits\":true,\"IncludeRevenue\":true},\"ExportDetail\":false,\"ApplyDiscountPercentage\":0,\"WinterNormalizationAdjustment\":{\"Type\":0,\"TypeDetail\":0},\"CustomerChoice\":0,\"OldestEstimateUpdateDate\":\"1900-01-01\"},\"RevenueAccount\":{\"Index\":524,\"Number\":\"900-4614-00\",\"Description\":\"Metered Sales to Public Auth. - Electric\"},\"ReceivableAccount\":{\"Index\":506,\"Number\":\"900-1410-00\",\"Description\":\"Customer Accounts Receivable - Electric\"},\"DetailSequence\":null,\"MeterSizeMinimum\":[],\"AutomaticEstimates\":null}]},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result, expected);

	}

	@Test(priority = 5, groups = "rate")
	public void getrateID3()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/EPCA-1";
		String ver = "4.0";
		String expected = "{\"Rate\":{\"Success\":true,\"Data\":{\"RateId\":\"EPCA-1\",\"Description\":\"Electric Power Cost Adjustment\",\"Type\":{\"Id\":1,\"Description\":\"Consumption\"},\"ServiceType\":\"ELECTRIC\",\"RateClassId\":\"\",\"BillingMessageExist\":false,\"Active\":true,\"UseLatestRateEffectivePeriod\":false,\"ConsecutiveEstimatesAllowed\":0,\"BillInAdvance\":false,\"LookupVisible\":false,\"SpecialCondition\":false,\"TimeOfUse\":false,\"ExcludeFromBd\":false,\"RatchetDemand\":true,\"KvarFactor\":0.00000,\"EffectiveDate\":[{\"EffectiveStartDate\":\"1998-01-01\",\"MinimumAmount\":0.00,\"MaximumAmount\":0.00,\"ProrateMinimum\":{\"First\":false,\"Regular\":false,\"Last\":false},\"ProrateMaximum\":{\"First\":false,\"Regular\":false,\"Last\":false}}],\"Detail\":[{\"DetailIndex\":1,\"Detail\":{\"Type\":2,\"Description\":\"Stepped Range\"},\"EffectiveStartDate\":\"1998-01-01\",\"EffectiveEndDate\":\"1900-01-01\",\"DetailDescription\":\"PCA for Electric Service\",\"TaxSchedule\":\"USASTCITY-6*\",\"ServiceType\":\"ELECTRIC\",\"BillingFrequency\":90,\"ProrateDetail\":{\"First\":false,\"Regular\":false,\"Last\":false},\"ProrateMinimum\":{\"First\":false,\"Regular\":false,\"Last\":false},\"MinimumCharge\":0.00,\"UnitDescription\":\"\",\"FixedCharge\":0.00000,\"Consumption\":{\"BillingDemandMinimum\":0.00,\"ConsumptionTolerance\":0.00000,\"UseActualDays\":false,\"Reporting\":{\"IncludeUnits\":true,\"IncludeRevenue\":true},\"ExportDetail\":false,\"ApplyDiscountPercentage\":0,\"WinterNormalizationAdjustment\":{\"Type\":0,\"TypeDetail\":0},\"CustomerChoice\":0,\"OldestEstimateUpdateDate\":\"1900-01-01\"},\"RevenueAccount\":{\"Index\":515,\"Number\":\"900-4611-00\",\"Description\":\"Metered Sales to Residential - Electric\"},\"ReceivableAccount\":{\"Index\":506,\"Number\":\"900-1410-00\",\"Description\":\"Customer Accounts Receivable - Electric\"},\"DetailSequence\":[{\"DetailIndexSequence\":1,\"UnitRate\":0.53000,\"VolumeLowerLimit\":0,\"VolumeUpperLimit\":999999999,\"VolumeLowerLimitString\":\"\",\"VolumeUpperLimitString\":\"\"}],\"MeterSizeMinimum\":[],\"AutomaticEstimates\":null}]},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result, expected);

	}

	@Test(priority = 6, groups = "rate")
	public void getrateID4()
			throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		String uri = "/rate/INTERNETFIX";
		String ver = "4.0";
		String expected = "{\"Rate\":{\"Success\":true,\"Data\":{\"RateId\":\"INTERNETFIX\",\"Description\":\"\",\"Type\":{\"Id\":0,\"Description\":\"\"},\"ServiceType\":\"INTERNET\",\"RateClassId\":\"\",\"BillingMessageExist\":false,\"Active\":true,\"UseLatestRateEffectivePeriod\":false,\"ConsecutiveEstimatesAllowed\":0,\"BillInAdvance\":false,\"LookupVisible\":false,\"SpecialCondition\":false,\"TimeOfUse\":false,\"ExcludeFromBd\":false,\"RatchetDemand\":false,\"KvarFactor\":0.00000,\"EffectiveDate\":[{\"EffectiveStartDate\":\"2019-06-01\",\"MinimumAmount\":0.00,\"MaximumAmount\":0.00,\"ProrateMinimum\":{\"First\":false,\"Regular\":false,\"Last\":false},\"ProrateMaximum\":{\"First\":false,\"Regular\":false,\"Last\":false}}],\"Detail\":[{\"DetailIndex\":1,\"Detail\":{\"Type\":1,\"Description\":\"Fixed Charge\"},\"EffectiveStartDate\":\"2019-06-01\",\"EffectiveEndDate\":\"1900-01-01\",\"DetailDescription\":\"\",\"TaxSchedule\":\"USAUSSTCITY+6*\",\"ServiceType\":\"INTERNET\",\"BillingFrequency\":30,\"ProrateDetail\":{\"First\":false,\"Regular\":false,\"Last\":false},\"ProrateMinimum\":{\"First\":false,\"Regular\":false,\"Last\":false},\"MinimumCharge\":0.00,\"UnitDescription\":\"\",\"FixedCharge\":10.00000,\"Consumption\":{\"BillingDemandMinimum\":0.00,\"ConsumptionTolerance\":0.00000,\"UseActualDays\":false,\"Reporting\":{\"IncludeUnits\":false,\"IncludeRevenue\":false},\"ExportDetail\":false,\"ApplyDiscountPercentage\":0,\"WinterNormalizationAdjustment\":{\"Type\":0,\"TypeDetail\":0},\"CustomerChoice\":0,\"OldestEstimateUpdateDate\":\"1900-01-01\"},\"RevenueAccount\":{\"Index\":624,\"Number\":\"900-4616-00\",\"Description\":\"Internet Service\"},\"ReceivableAccount\":{\"Index\":623,\"Number\":\"900-1414-00\",\"Description\":\"Customer Account Receivable-Internet service\"},\"DetailSequence\":null,\"MeterSizeMinimum\":[],\"AutomaticEstimates\":null}]},\"Messages\":[]}}";
		HashMap<String, String> params = new HashMap<String, String>();
		String result = CommonMethods.getMethodasString(uri, ver, params);
		Assert.assertEquals(result, expected);

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
		String expected = "{\"Rate\":{\"Success\":true,\"Data\":{\"RateClassID\":\"ELECTRATE\",\"RateFlip\":[{\"PeriodID\":\"ON PEAK\",\"PeriodIndex\":1,\"Steps\":[{\"SequenceNumber\":1,\"VolumeLowerLimit\":111,\"VolumeUpperLimit\":999999999,\"TariffID\":\"EPCA-1\",\"KWRate\":\"RATE1\",\"KVARate\":\"RATE1\"}]},{\"PeriodID\":\"OFFPEAK\",\"PeriodIndex\":2,\"Steps\":[{\"SequenceNumber\":1,\"VolumeLowerLimit\":10000,\"VolumeUpperLimit\":23123134,\"TariffID\":\"GS-PK ENERGY\",\"KWRate\":\"RATE1\",\"KVARate\":\"RATE1\"}]}]},\"Messages\":[]}}";
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

}