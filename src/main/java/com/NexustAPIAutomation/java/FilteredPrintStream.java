package com.NexustAPIAutomation.java;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Custom PrintStream that filters out FreeMarker DEBUG logs
 * Redirects stderr/stdout to suppress verbose template loading messages
 */
public class FilteredPrintStream extends PrintStream {
    private final PrintStream original;
    private StringBuilder buffer = new StringBuilder();

    public FilteredPrintStream(PrintStream original) {
        super(original);
        this.original = original;
    }

    private boolean shouldFilter(String x) {
        if (x == null || x.isEmpty()) return false;
        
        // Filter FreeMarker DEBUG lines
        if (x.contains("DEBUG") && x.contains("freemark")) return true;
        if (x.contains("[freemark]")) return true;
        if (x.contains("TemplateLoader")) return true;
        if (x.contains("Couldn't find template")) return true;
        if (x.contains("Loading template for")) return true;
        if (x.contains("cached copy")) return true;
        if (x.contains(".ftl\"")) return true; // Catch template file paths
        
        return false;
    }

    @Override
    public void write(byte[] b) {
        write(b, 0, b.length);
    }

    @Override
    public void write(byte[] b, int off, int len) {
        String s = new String(b, off, len, StandardCharsets.UTF_8);
        if (!shouldFilter(s)) {
            original.write(b, off, len);
        }
    }

    @Override
    public void write(int b) {
        buffer.append((char) b);
        if ((char) b == '\n') {
            String line = buffer.toString();
            if (!shouldFilter(line)) {
                original.print(line);
            }
            buffer = new StringBuilder();
        }
    }

    @Override
    public void println(String x) {
        if (!shouldFilter(x)) {
            original.println(x);
        }
    }

    @Override
    public void print(String x) {
        if (!shouldFilter(x)) {
            original.print(x);
        }
    }

    @Override
    public void println(Object x) {
        String s = String.valueOf(x);
        if (!shouldFilter(s)) {
            original.println(s);
        }
    }

    @Override
    public void print(Object x) {
        String s = String.valueOf(x);
        if (!shouldFilter(s)) {
            original.print(s);
        }
    }
}
