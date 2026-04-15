// filepath: c:\Users\Admin\Documents\GitHub\nexus_automation\src\main\java\com\NexustAPIAutomation\java\PerformanceTestHelper.java
package com.NexustAPIAutomation.java;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class PerformanceTestHelper {

    private static final ReadProjectProperties Read = new ReadProjectProperties();

    // Default thresholds (milliseconds)
    public static final long THRESHOLD_FAST = 1000; // 1 second
    public static final long THRESHOLD_NORMAL = 3000; // 3 seconds
    public static final long THRESHOLD_SLOW = 5000; // 5 seconds
    public static final long THRESHOLD_VERY_SLOW = 10000; // 10 seconds

    // ─── Single Request Performance ─────────────────────────────────────

    public static PerformanceMetrics measureGet(String uri, String version,
            HashMap<String, String> params, long thresholdMs) {
        String baseUri = resolveBaseUri(version);
        String fullUri = baseUri + uri;

        RequestSpecification request = RestAssured.given()
                .baseUri(baseUri)
                .headers("Authorization", "Bearer " + CommonMethods.getToken(),
                        "Content-Type", ContentType.JSON,
                        "Connection", "keep-alive",
                        "Accept-Encoding", "gzip, deflate, br");

        if (params != null && !params.isEmpty()) {
            request.queryParams(params);
        }

        long startTime = System.currentTimeMillis();
        Response response = request.get(fullUri);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        PerformanceMetrics metrics = new PerformanceMetrics(
                "GET", uri, version, duration, response.getStatusCode(), thresholdMs);

        logPerformanceToExtent(metrics, response.asString());
        return metrics;
    }

    public static PerformanceMetrics measurePost(String uri, String version,
            String payload, long thresholdMs) {
        String baseUri = resolveBaseUri(version);
        String fullUri = baseUri + uri;

        RequestSpecification request = RestAssured.given()
                .baseUri(baseUri)
                .headers("Authorization", "Bearer " + CommonMethods.getToken(),
                        "Content-Type", ContentType.JSON,
                        "Connection", "keep-alive",
                        "Accept-Encoding", "gzip, deflate, br")
                .body(payload);

        long startTime = System.currentTimeMillis();
        Response response = request.post(fullUri);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        PerformanceMetrics metrics = new PerformanceMetrics(
                "POST", uri, version, duration, response.getStatusCode(), thresholdMs);

        logPerformanceToExtent(metrics, response.asString());
        return metrics;
    }

    public static PerformanceMetrics measurePostFile(String uri, String version,
            String filePath, long thresholdMs) {
        String baseUri = resolveBaseUri(version);
        String fullUri = baseUri + uri;
        File payloadFile = new File(filePath);

        RequestSpecification request = RestAssured.given()
                .baseUri(baseUri)
                .headers("Authorization", "Bearer " + CommonMethods.getToken(),
                        "Content-Type", ContentType.JSON,
                        "Connection", "keep-alive",
                        "Accept-Encoding", "gzip, deflate, br")
                .body(payloadFile);

        long startTime = System.currentTimeMillis();
        Response response = request.post(fullUri);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        PerformanceMetrics metrics = new PerformanceMetrics(
                "POST", uri, version, duration, response.getStatusCode(), thresholdMs);

        logPerformanceToExtent(metrics, response.asString());
        return metrics;
    }

    public static PerformanceMetrics measurePut(String uri, String version,
            String payload, long thresholdMs) {
        String baseUri = resolveBaseUri(version);
        String fullUri = baseUri + uri;

        RequestSpecification request = RestAssured.given()
                .baseUri(baseUri)
                .headers("Authorization", "Bearer " + CommonMethods.getToken(),
                        "Content-Type", ContentType.JSON,
                        "Connection", "keep-alive",
                        "Accept-Encoding", "gzip, deflate, br")
                .body(payload);

        long startTime = System.currentTimeMillis();
        Response response = request.put(fullUri);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        PerformanceMetrics metrics = new PerformanceMetrics(
                "PUT", uri, version, duration, response.getStatusCode(), thresholdMs);

        logPerformanceToExtent(metrics, response.asString());
        return metrics;
    }

    public static PerformanceMetrics measureDelete(String uri, String version, long thresholdMs) {
        String baseUri = resolveBaseUri(version);
        String fullUri = baseUri + uri;

        RequestSpecification request = RestAssured.given()
                .baseUri(baseUri)
                .headers("Authorization", "Bearer " + CommonMethods.getToken(),
                        "Content-Type", ContentType.JSON,
                        "Connection", "keep-alive",
                        "Accept-Encoding", "gzip, deflate, br");

        long startTime = System.currentTimeMillis();
        Response response = request.delete(fullUri);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        PerformanceMetrics metrics = new PerformanceMetrics(
                "DELETE", uri, version, duration, response.getStatusCode(), thresholdMs);

        logPerformanceToExtent(metrics, response.asString());
        return metrics;
    }

    // ─── Load Test (Concurrent Requests) ────────────────────────────────

    public static List<PerformanceMetrics> loadTestGet(String uri, String version,
            HashMap<String, String> params,
            int concurrentUsers, long thresholdMs) {
        ExecutorService executor = Executors.newFixedThreadPool(concurrentUsers);
        List<Future<PerformanceMetrics>> futures = new ArrayList<>();

        for (int i = 0; i < concurrentUsers; i++) {
            futures.add(executor.submit(() -> measureGet(uri, version, params, thresholdMs)));
        }

        List<PerformanceMetrics> results = collectResults(futures);
        executor.shutdown();

        logLoadTestSummary("GET", uri, version, concurrentUsers, results);
        return results;
    }

    public static List<PerformanceMetrics> loadTestPost(String uri, String version,
            String payload,
            int concurrentUsers, long thresholdMs) {
        ExecutorService executor = Executors.newFixedThreadPool(concurrentUsers);
        List<Future<PerformanceMetrics>> futures = new ArrayList<>();

        for (int i = 0; i < concurrentUsers; i++) {
            futures.add(executor.submit(() -> measurePost(uri, version, payload, thresholdMs)));
        }

        List<PerformanceMetrics> results = collectResults(futures);
        executor.shutdown();

        logLoadTestSummary("POST", uri, version, concurrentUsers, results);
        return results;
    }

    // ─── Repeat Test (Sequential Iterations) ────────────────────────────

    public static List<PerformanceMetrics> repeatGet(String uri, String version,
            HashMap<String, String> params,
            int iterations, long thresholdMs) {
        List<PerformanceMetrics> results = new ArrayList<>();
        for (int i = 0; i < iterations; i++) {
            results.add(measureGet(uri, version, params, thresholdMs));
        }
        logRepeatSummary("GET", uri, version, iterations, results);
        return results;
    }

    public static List<PerformanceMetrics> repeatPost(String uri, String version,
            String payload,
            int iterations, long thresholdMs) {
        List<PerformanceMetrics> results = new ArrayList<>();
        for (int i = 0; i < iterations; i++) {
            results.add(measurePost(uri, version, payload, thresholdMs));
        }
        logRepeatSummary("POST", uri, version, iterations, results);
        return results;
    }

    // ─── Extent Report Logging ──────────────────────────────────────────

    private static void logPerformanceToExtent(PerformanceMetrics metrics, String responseBody) {
        String color = metrics.isPassed() ? "green" : "red";
        String icon = metrics.isPassed() ? "&#9989;" : "&#10060;";

        String html = "<div style='border-left: 4px solid " + color + "; padding: 8px; margin: 4px 0; "
                + "background-color: " + (metrics.isPassed() ? "#f0fff0" : "#fff0f0") + ";'>"
                + icon + " <b>Performance:</b> [" + metrics.getMethod() + "] " + metrics.getUri()
                + " v" + metrics.getVersion() + "<br>"
                + "<b>Response Time:</b> <span style='font-size:16px; color:" + color + ";'>"
                + metrics.getResponseTimeMs() + "ms</span>"
                + " / Threshold: " + metrics.getThresholdMs() + "ms<br>"
                + "<b>Status Code:</b> " + metrics.getStatusCode()
                + "</div>";

        if (metrics.isPassed()) {
            ExtentReportManager.logPass(html);
        } else {
            ExtentReportManager.logFail(html);
        }
    }

    private static void logLoadTestSummary(String method, String uri, String version,
            int concurrentUsers, List<PerformanceMetrics> results) {
        PerformanceSummary summary = calculateSummary(results);

        String html = buildSummaryHtml(
                "&#9889; Load Test Summary",
                method, uri, version,
                "Concurrent Users: " + concurrentUsers,
                summary);

        ExtentReportManager.logInfo(html);
    }

    private static void logRepeatSummary(String method, String uri, String version,
            int iterations, List<PerformanceMetrics> results) {
        PerformanceSummary summary = calculateSummary(results);

        String html = buildSummaryHtml(
                "&#128257; Repeat Test Summary",
                method, uri, version,
                "Iterations: " + iterations,
                summary);

        ExtentReportManager.logInfo(html);
    }

    private static String buildSummaryHtml(String title, String method, String uri,
            String version, String detail,
            PerformanceSummary summary) {
        String overallColor = summary.failCount == 0 ? "green" : "red";

        return "<div style='border: 2px solid " + overallColor + "; border-radius: 8px; "
                + "padding: 12px; margin: 8px 0; background-color: #f9f9f9;'>"
                + "<h4 style='margin:0 0 8px 0;'>" + title + "</h4>"
                + "<table style='border-collapse:collapse; width:100%;'>"
                + "<tr style='background-color:#e0e0e0;'>"
                + "<td style='padding:4px 8px;'><b>Endpoint</b></td>"
                + "<td style='padding:4px 8px;'>[" + method + "] " + uri + " v" + version + "</td></tr>"
                + "<tr><td style='padding:4px 8px;'><b>" + detail.split(":")[0] + "</b></td>"
                + "<td style='padding:4px 8px;'>" + detail.split(":")[1].trim() + "</td></tr>"
                + "<tr style='background-color:#e0e0e0;'>"
                + "<td style='padding:4px 8px;'><b>Min / Avg / Max</b></td>"
                + "<td style='padding:4px 8px;'>" + summary.minMs + "ms / "
                + summary.avgMs + "ms / " + summary.maxMs + "ms</td></tr>"
                + "<tr><td style='padding:4px 8px;'><b>P50 / P90 / P95 / P99</b></td>"
                + "<td style='padding:4px 8px;'>" + summary.p50Ms + "ms / "
                + summary.p90Ms + "ms / " + summary.p95Ms + "ms / " + summary.p99Ms + "ms</td></tr>"
                + "<tr style='background-color:#e0e0e0;'>"
                + "<td style='padding:4px 8px;'><b>Pass / Fail</b></td>"
                + "<td style='padding:4px 8px;'><span style='color:green;'>" + summary.passCount
                + " passed</span> / <span style='color:red;'>" + summary.failCount + " failed</span></td></tr>"
                + "<tr><td style='padding:4px 8px;'><b>Throughput</b></td>"
                + "<td style='padding:4px 8px;'>" + String.format("%.2f", summary.throughputPerSec)
                + " req/sec</td></tr>"
                + "</table></div>";
    }

    // ─── Statistics Calculation ──────────────────────────────────────────

    private static PerformanceSummary calculateSummary(List<PerformanceMetrics> results) {
        PerformanceSummary summary = new PerformanceSummary();

        if (results == null || results.isEmpty()) {
            return summary;
        }

        List<Long> times = results.stream()
                .map(PerformanceMetrics::getResponseTimeMs)
                .sorted()
                .collect(Collectors.toList());

        summary.totalRequests = results.size();
        summary.passCount = (int) results.stream().filter(PerformanceMetrics::isPassed).count();
        summary.failCount = summary.totalRequests - summary.passCount;
        summary.minMs = times.get(0);
        summary.maxMs = times.get(times.size() - 1);
        summary.avgMs = (long) times.stream().mapToLong(Long::longValue).average().orElse(0);
        summary.p50Ms = percentile(times, 50);
        summary.p90Ms = percentile(times, 90);
        summary.p95Ms = percentile(times, 95);
        summary.p99Ms = percentile(times, 99);

        long totalDuration = times.stream().mapToLong(Long::longValue).sum();
        summary.throughputPerSec = totalDuration > 0
                ? (summary.totalRequests * 1000.0) / totalDuration
                : 0;

        return summary;
    }

    private static long percentile(List<Long> sortedTimes, int percentile) {
        if (sortedTimes.isEmpty())
            return 0;
        int index = (int) Math.ceil((percentile / 100.0) * sortedTimes.size()) - 1;
        index = Math.max(0, Math.min(index, sortedTimes.size() - 1));
        return sortedTimes.get(index);
    }

    private static List<PerformanceMetrics> collectResults(List<Future<PerformanceMetrics>> futures) {
        List<PerformanceMetrics> results = new ArrayList<>();
        for (Future<PerformanceMetrics> future : futures) {
            try {
                results.add(future.get(60, TimeUnit.SECONDS));
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                System.out.println("[PerfTest] Request failed: " + e.getMessage());
            }
        }
        return results;
    }

    private static String resolveBaseUri(String version) {
        switch (version) {
            case "1":
            case "1.0":
                return Read.ReadFile("urlv1");
            case "2":
            case "2.0":
                return Read.ReadFile("urlv2");
            case "2.1":
                return Read.ReadFile("urlv210");
            case "2.2":
                return Read.ReadFile("urlv220");
            case "2.3":
                return Read.ReadFile("urlv230");
            case "2.3.1":
                return Read.ReadFile("urlv231");
            case "2.4":
                return Read.ReadFile("urlv240");
            case "3.0":
                return Read.ReadFile("urlv3");
            case "4.0":
            case "4":
                return Read.ReadFile("urlv4");
            case "e":
                return Read.ReadFile("urle");
            default:
                return "";
        }
    }

    // ─── Inner class for summary stats ──────────────────────────────────

    private static class PerformanceSummary {
        int totalRequests = 0;
        int passCount = 0;
        int failCount = 0;
        long minMs = 0;
        long maxMs = 0;
        long avgMs = 0;
        long p50Ms = 0;
        long p90Ms = 0;
        long p95Ms = 0;
        long p99Ms = 0;
        double throughputPerSec = 0;
    }
}