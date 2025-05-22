package com.NexustAPIAutomation.java;

import org.testng.annotations.BeforeSuite;
import java.io.File;

public class TestSetup {

    @BeforeSuite
    public void cleanTestOutput() {
        File testOutputDir = new File("test-output");
        deleteDirectory(testOutputDir);
        System.out.println("test-output directory deleted before suite run.");
    }

    private void deleteDirectory(File dir) {
        if (dir.exists()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    deleteDirectory(file);
                }
            }
            dir.delete();
        }
    }
}
