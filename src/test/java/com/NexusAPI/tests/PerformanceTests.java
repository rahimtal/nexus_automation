package com.NexusAPI.Tests;

import com.NexustAPIAutomation.java.*;
//import com.NexusAPI.Helpers.PerformanceTestHelper;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

public class PerformanceTests {

        // ─── Single Endpoint Response Time Tests ────────────────────────────

        @Test(groups = { "performance" }, description = "Verify account balance GET responds within 3 seconds")
        public void testGetAccountBalance_ResponseTime() {
                HashMap<String, String> params = new HashMap<>();
                params.put("LocationId", "loc@0001");
                params.put("CustomerId", "0000011111");
                params.put("UserDate", "2027-04-12");

                PerformanceMetrics metrics = PerformanceTestHelper.measureGet(
                                "/accountBalance/getAccountBalances", "2.4",
                                params, PerformanceTestHelper.THRESHOLD_NORMAL);

                Assert.assertTrue(metrics.isPassed(),
                                "Response time " + metrics.getResponseTimeMs() + "ms exceeded threshold "
                                                + metrics.getThresholdMs() + "ms");
        }

        @Test(groups = { "performance" }, description = "Verify receipt adjust POST responds within 5 seconds")
        public void testPostReceiptAdjust_ResponseTime() {
                PerformanceMetrics metrics = PerformanceTestHelper.measurePostFile(
                                "/cashiering/receipt/adjust", "2.4",
                                "./TestData/recieptAdjust.json",
                                PerformanceTestHelper.THRESHOLD_SLOW);

                Assert.assertTrue(metrics.isPassed(),
                                "Response time " + metrics.getResponseTimeMs() + "ms exceeded threshold "
                                                + metrics.getThresholdMs() + "ms");
        }

        // ─── Load Tests (Concurrent Users) ──────────────────────────────────

        @Test(groups = { "performance",
                        "load" }, description = "Load test: 10 concurrent users hitting account balance")
        public void testGetAccountBalance_Load10Users() {
                HashMap<String, String> params = new HashMap<>();
                params.put("LocationId", "loc@0001");
                params.put("CustomerId", "0000011111");
                params.put("UserDate", "2027-04-12");

                List<PerformanceMetrics> results = PerformanceTestHelper.loadTestGet(
                                "/accountBalance/getAccountBalances", "2.4",
                                params, 10, PerformanceTestHelper.THRESHOLD_SLOW);

                long failCount = results.stream().filter(m -> !m.isPassed()).count();
                Assert.assertEquals(failCount, 0,
                                failCount + " out of " + results.size() + " requests exceeded threshold");
        }

        @Test(groups = { "performance",
                        "load" }, description = "Load test: 25 concurrent users hitting account balance")
        public void testGetAccountBalance_Load25Users() {
                HashMap<String, String> params = new HashMap<>();
                params.put("LocationId", "loc@0001");
                params.put("CustomerId", "0000011111");
                params.put("UserDate", "2027-04-12");

                List<PerformanceMetrics> results = PerformanceTestHelper.loadTestGet(
                                "/accountBalance/getAccountBalances", "2.4",
                                params, 25, PerformanceTestHelper.THRESHOLD_VERY_SLOW);

                long failCount = results.stream().filter(m -> !m.isPassed()).count();
                Assert.assertEquals(failCount, 0,
                                failCount + " out of " + results.size() + " requests exceeded threshold");
        }

        // ─── Repeat Tests (Sequential Consistency) ──────────────────────────

        @Test(groups = { "performance",
                        "stability" }, description = "Repeat test: 20 sequential calls to check consistency")
        public void testGetAccountBalance_Repeat20() {
                HashMap<String, String> params = new HashMap<>();
                params.put("LocationId", "loc@0001");
                params.put("CustomerId", "0000011111");
                params.put("UserDate", "2027-04-12");

                List<PerformanceMetrics> results = PerformanceTestHelper.repeatGet(
                                "/accountBalance/getAccountBalances", "2.4",
                                params, 20, PerformanceTestHelper.THRESHOLD_NORMAL);

                long failCount = results.stream().filter(m -> !m.isPassed()).count();
                Assert.assertTrue(failCount <= 1,
                                failCount + " out of " + results.size()
                                                + " requests exceeded threshold (max 1 allowed)");
        }

        // ─── Endpoint Comparison ────────────────────────────────────────────

        @Test(groups = { "performance" }, description = "Compare v2.0 vs v2.4 response times")
        public void testVersionComparison_v2_vs_v24() {
                HashMap<String, String> params = new HashMap<>();
                params.put("LocationId", "loc@0001");
                params.put("CustomerId", "0000011111");
                params.put("UserDate", "2027-04-12");

                PerformanceMetrics v2 = PerformanceTestHelper.measureGet(
                                "/accountBalance/getAccountBalances", "2.0",
                                params, PerformanceTestHelper.THRESHOLD_SLOW);

                PerformanceMetrics v24 = PerformanceTestHelper.measureGet(
                                "/accountBalance/getAccountBalances", "2.4",
                                params, PerformanceTestHelper.THRESHOLD_SLOW);

                String comparison = "v2.0: " + v2.getResponseTimeMs() + "ms | v2.4: " + v24.getResponseTimeMs() + "ms";
                com.NexustAPIAutomation.java.ExtentReportManager.logInfo(
                                "<b>&#128200; Version Comparison:</b> " + comparison);

                Assert.assertTrue(v24.isPassed(), "v2.4 exceeded threshold: " + v24.getResponseTimeMs() + "ms");
        }
}