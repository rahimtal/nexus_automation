package com.NexusAPI.Tests;

import com.NexustAPIAutomation.java.QuickDBRestore;

import org.testng.annotations.Test;

/**
 * Tail-of-suite helper that restores the SQL Server "TWO" database from the
 * configured backup before the final group of tests runs.
 *
 * Wired in testng.xml as the first class of the "Post-Restore Final Tests"
 * block so any tests that require a clean baseline run against a freshly
 * restored database.
 */
public class DatabaseRestoreSetup {

    @Test(priority = 1)
    public void restoreDatabaseBeforeFinalTests() throws Exception {
        System.out.println("==============================================");
        System.out.println(" Restoring database before final test block...");
        System.out.println("==============================================");
        QuickDBRestore.restoreDatabase();
        // Give SQL Server a moment to settle after RESTORE WITH RECOVERY.
        Thread.sleep(5000);
        System.out.println(" Database restore complete. Proceeding with final tests.");
    }
}
