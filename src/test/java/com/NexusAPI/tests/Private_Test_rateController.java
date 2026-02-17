package com.NexusAPI.Tests;

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

}