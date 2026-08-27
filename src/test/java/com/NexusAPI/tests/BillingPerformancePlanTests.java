package com.NexusAPI.Tests;

import com.NexustAPIAutomation.java.PerformanceMetrics;
import com.NexustAPIAutomation.java.PerformanceTestHelper;
import com.NexustAPIAutomation.java.QuickDBRestore;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class BillingPerformancePlanTests {

    @BeforeMethod
    public void restoreDatabaseBeforeEachTest() throws IOException {
        System.out.println("\n========== BEFORE METHOD: Restoring TWO database ==========");
        QuickDBRestore.restoreDatabase();
        System.out.println("========== BEFORE METHOD: TWO database restore COMPLETED ==========\n");
    }

    private static final String VER = "4.0";

    private static final String BILLING_CALCULATE_PAYLOAD = "{\n"
            + "  \"Billing\": {\n"
            + "    \"BatchId\": \"BT1231\",\n"
            + "    \"BillingType\": 1,\n"
            + "    \"PrepareType\": 1,\n"
            + "    \"PrepareValue\": [\"LOCATION002\"],\n"
            + "    \"PeriodStartDate\": \"2000-06-01\",\n"
            + "    \"PeriodEndDate\": \"2000-06-30\",\n"
            + "    \"ReadingDate\": \"2000-06-30\",\n"
            + "    \"BillingDate\": \"2000-07-01\",\n"
            + "    \"PowerFactor\": 0,\n"
            + "    \"BtuPgaFactorDate\": \"2000-01-01\",\n"
            + "    \"Cycle\": {\n"
            + "      \"Id\": \"\",\n"
            + "      \"BillingPeriod\": 0\n"
            + "    }\n"
            + "  }\n"
            + "}";

    private static final String EDIT_REPORT_PAYLOAD = "{\n"
            + "  \"Billing\": {\n"
            + "    \"BatchId\": \"BT1231\"\n"
            + "  }\n"
            + "}";

    private static final String CREATE_STATEMENT_PAYLOAD = "{\n"
            + "  \"Billing\": {\n"
            + "    \"BatchId\": \"BT1231\",\n"
            + "    \"Confirm\": {\n"
            + "      \"IgnoreMiscChargeOrCreditValidation\": false\n"
            + "    }\n"
            + "  }\n"
            + "}";

    private static final String PRINT_STATEMENT_PAYLOAD = "{\n"
            + "  \"Billing\": {\n"
            + "    \"ExportToCSV\": true,\n"
            + "    \"IncludeEbills\": true,\n"
            + "    \"PrintAction\": 1,\n"
            + "    \"BatchId\": \"BT1231\",\n"
            + "    \"Confirm\": {\n"
            + "      \"RefreshBillPrintData\": true\n"
            + "    }\n"
            + "  }\n"
            + "}";

    private static final String POSTING_BILL_PAYLOAD = "{\n"
            + "  \"Billing\": {\n"
            + "    \"BatchId\": \"BT1231\",\n"
            + "    \"Document\": []\n"
            + "  }\n"
            + "}";

    @Test(groups = {"performance", "billing-perf-plan", "smoke"},
            description = "Smoke: billing calculate response-time gate")
    public void smokeBillingCalculateResponseTime() {
        PerformanceMetrics m = PerformanceTestHelper.measurePost(
                "/billing/calculate", VER, BILLING_CALCULATE_PAYLOAD, 3500);
        Assert.assertEquals(m.getStatusCode(), 200, "Expected HTTP 200 for billing calculate");
        Assert.assertTrue(m.isPassed(), "billing/calculate exceeded threshold: " + m.getResponseTimeMs() + "ms");
    }

    @Test(groups = {"performance", "billing-perf-plan", "baseline"},
            description = "Baseline: billing calculate load profile")
    public void baselineBillingCalculateLoad() {
        List<PerformanceMetrics> results = PerformanceTestHelper.loadTestPost(
                "/billing/calculate", VER, BILLING_CALCULATE_PAYLOAD, 5, 3500);
        assertLoadProfile("/billing/calculate", results, 3500, 0.01);
    }

    @Test(groups = {"performance", "billing-perf-plan", "baseline"},
            description = "Baseline: generate edit report load profile")
    public void baselineGenerateEditReportLoad() {
        List<PerformanceMetrics> results = PerformanceTestHelper.loadTestPost(
                "/billing/generateEditReport", VER, EDIT_REPORT_PAYLOAD, 5, 3000);
        assertLoadProfile("/billing/generateEditReport", results, 3000, 0.01);
    }

    @Test(groups = {"performance", "billing-perf-plan", "baseline"},
            description = "Baseline: create statement load profile")
    public void baselineCreateStatementLoad() {
        List<PerformanceMetrics> results = PerformanceTestHelper.loadTestPost(
                "/billing/createStatement", VER, CREATE_STATEMENT_PAYLOAD, 5, 3000);
        assertLoadProfile("/billing/createStatement", results, 3000, 0.01);
    }

    @Test(groups = {"performance", "billing-perf-plan", "baseline"},
            description = "Baseline: print statement load profile")
    public void baselinePrintStatementLoad() {
        List<PerformanceMetrics> results = PerformanceTestHelper.loadTestPost(
                "/billing/printStatement", VER, PRINT_STATEMENT_PAYLOAD, 4, 4500);
        assertLoadProfile("/billing/printStatement", results, 4500, 0.01);
    }

    @Test(groups = {"performance", "billing-perf-plan", "baseline"},
            description = "Baseline: posting bill load profile")
    public void baselinePostingBillLoad() {
        List<PerformanceMetrics> results = PerformanceTestHelper.loadTestPost(
                "/billing/postingBill", VER, POSTING_BILL_PAYLOAD, 3, 5000);
        assertLoadProfile("/billing/postingBill", results, 5000, 0.01);
    }

    @Test(groups = {"performance", "billing-perf-plan", "baseline", "polling"},
            description = "Baseline: bill batch status polling load")
    public void baselineBillBatchStatusLoad() {
        HashMap<String, String> params = new HashMap<>();
        List<PerformanceMetrics> results = PerformanceTestHelper.loadTestGet(
                "/billing/billBatchStatus/FINALBILL", VER, params, 30, 700);
        assertLoadProfile("/billing/billBatchStatus/FINALBILL", results, 700, 0.01);
    }

    @Test(groups = {"performance", "billing-perf-plan", "baseline", "polling"},
            description = "Baseline: transfer progress polling load")
    public void baselineTransferProgressLoad() {
        HashMap<String, String> params = new HashMap<>();
        params.put("ServiceOrderNumber", "SORD00000009044");

        List<PerformanceMetrics> results = PerformanceTestHelper.loadTestGet(
                "/billing/transfer/progress", VER, params, 25, 800);
        assertLoadProfile("/billing/transfer/progress", results, 800, 0.01);
    }

    @Test(groups = {"performance", "billing-perf-plan", "stress"},
            description = "Stress: billing calculate load profile")
    public void stressBillingCalculateLoad() {
        List<PerformanceMetrics> results = PerformanceTestHelper.loadTestPost(
                "/billing/calculate", VER, BILLING_CALCULATE_PAYLOAD, 15, 5000);
        assertLoadProfile("/billing/calculate", results, 5000, 0.02);
    }

    @Test(groups = {"performance", "billing-perf-plan", "stress"},
            description = "Stress: generate edit report load profile")
    public void stressGenerateEditReportLoad() {
        List<PerformanceMetrics> results = PerformanceTestHelper.loadTestPost(
                "/billing/generateEditReport", VER, EDIT_REPORT_PAYLOAD, 12, 4500);
        assertLoadProfile("/billing/generateEditReport", results, 4500, 0.02);
    }

    @Test(groups = {"performance", "billing-perf-plan", "stress"},
            description = "Stress: create statement load profile")
    public void stressCreateStatementLoad() {
        List<PerformanceMetrics> results = PerformanceTestHelper.loadTestPost(
                "/billing/createStatement", VER, CREATE_STATEMENT_PAYLOAD, 12, 4500);
        assertLoadProfile("/billing/createStatement", results, 4500, 0.02);
    }

    @Test(groups = {"performance", "billing-perf-plan", "stress"},
            description = "Stress: print statement load profile")
    public void stressPrintStatementLoad() {
        List<PerformanceMetrics> results = PerformanceTestHelper.loadTestPost(
                "/billing/printStatement", VER, PRINT_STATEMENT_PAYLOAD, 10, 7000);
        assertLoadProfile("/billing/printStatement", results, 7000, 0.02);
    }

    @Test(groups = {"performance", "billing-perf-plan", "stress"},
            description = "Stress: posting bill load profile")
    public void stressPostingBillLoad() {
        List<PerformanceMetrics> results = PerformanceTestHelper.loadTestPost(
                "/billing/postingBill", VER, POSTING_BILL_PAYLOAD, 8, 8000);
        assertLoadProfile("/billing/postingBill", results, 8000, 0.02);
    }

    @Test(groups = {"performance", "billing-perf-plan", "stress", "polling"},
            description = "Stress: bill batch status polling load")
    public void stressBillBatchStatusLoad() {
        HashMap<String, String> params = new HashMap<>();
        List<PerformanceMetrics> results = PerformanceTestHelper.loadTestGet(
                "/billing/billBatchStatus/FINALBILL", VER, params, 100, 1100);
        assertLoadProfile("/billing/billBatchStatus/FINALBILL", results, 1100, 0.02);
    }

    @Test(groups = {"performance", "billing-perf-plan", "stress", "polling"},
            description = "Stress: transfer progress polling load")
    public void stressTransferProgressLoad() {
        HashMap<String, String> params = new HashMap<>();
        params.put("ServiceOrderNumber", "SORD00000009044");

        List<PerformanceMetrics> results = PerformanceTestHelper.loadTestGet(
                "/billing/transfer/progress", VER, params, 80, 1200);
        assertLoadProfile("/billing/transfer/progress", results, 1200, 0.02);
    }

    private void assertLoadProfile(String endpoint,
                                   List<PerformanceMetrics> results,
                                   long p95TargetMs,
                                   double maxErrorRate) {
        Assert.assertFalse(results.isEmpty(), endpoint + " returned no metrics");

        long non200 = results.stream().filter(r -> r.getStatusCode() != 200).count();
        double errorRate = (double) non200 / results.size();

        List<Long> times = results.stream()
                .map(PerformanceMetrics::getResponseTimeMs)
                .sorted()
                .toList();

        long p95 = percentile(times, 95);
        Assert.assertTrue(p95 <= p95TargetMs,
                endpoint + " p95 " + p95 + "ms exceeded target " + p95TargetMs + "ms");
        Assert.assertTrue(errorRate <= maxErrorRate,
                endpoint + " error rate " + String.format("%.2f", errorRate * 100)
                        + "% exceeded max " + (maxErrorRate * 100) + "%");
    }

    private long percentile(List<Long> sorted, int p) {
        if (sorted.isEmpty()) {
            return 0;
        }
        int idx = (int) Math.ceil((p / 100.0) * sorted.size()) - 1;
        if (idx < 0) {
            idx = 0;
        }
        if (idx >= sorted.size()) {
            idx = sorted.size() - 1;
        }
        return sorted.get(idx);
    }
}
