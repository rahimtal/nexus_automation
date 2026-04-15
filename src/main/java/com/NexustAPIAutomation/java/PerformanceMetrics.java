// filepath: c:\Users\Admin\Documents\GitHub\nexus_automation\src\main\java\com\NexustAPIAutomation\java\PerformanceMetrics.java
package com.NexustAPIAutomation.java;

public class PerformanceMetrics {

    private String method;
    private String uri;
    private String version;
    private long responseTimeMs;
    private int statusCode;
    private long thresholdMs;
    private boolean passed;

    public PerformanceMetrics(String method, String uri, String version,
                              long responseTimeMs, int statusCode, long thresholdMs) {
        this.method = method;
        this.uri = uri;
        this.version = version;
        this.responseTimeMs = responseTimeMs;
        this.statusCode = statusCode;
        this.thresholdMs = thresholdMs;
        this.passed = responseTimeMs <= thresholdMs;
    }

    public String getMethod() { return method; }
    public String getUri() { return uri; }
    public String getVersion() { return version; }
    public long getResponseTimeMs() { return responseTimeMs; }
    public int getStatusCode() { return statusCode; }
    public long getThresholdMs() { return thresholdMs; }
    public boolean isPassed() { return passed; }

    @Override
    public String toString() {
        return method + " " + uri + " v" + version + " — " + responseTimeMs + "ms"
                + (passed ? " [PASS]" : " [FAIL: exceeded " + thresholdMs + "ms]");
    }
}