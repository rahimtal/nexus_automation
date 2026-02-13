package com.NexustAPIAutomation.java;

import java.io.IOException;

import org.testng.Assert;

public class QuickDBRestore {
    public static void main(String[] args) throws IOException {
        restoreDatabase();
    }

    public static void restoreDatabase() throws IOException {
        // Load properties from Project.properties file
        String serverName = ReadProjectProperties.ReadFile("serverName");
        String username = ReadProjectProperties.ReadFile("username");
        String password = ReadProjectProperties.ReadFile("password");
        String databaseName = ReadProjectProperties.ReadFile("databaseName");
        String backupFilePath = ReadProjectProperties.ReadFile("backupFilePath");

        try {
            // Check if sqlcmd is available
            Process dropDb = Runtime.getRuntime().exec(new String[] {
                    "sqlcmd",
                    "-S", serverName,
                    "-U", username,
                    "-P", password,
                    "-Q",
                    "IF EXISTS (SELECT name FROM master.dbo.sysdatabases WHERE name ='" + databaseName
                            + "') BEGIN ALTER DATABASE  " + databaseName
                            + " SET OFFLINE WITH ROLLBACK IMMEDIATE; DROP DATABASE "
                            + databaseName + "; END"
            });
            // Consume output and error streams to prevent deadlocks
            consumeStream(dropDb.getInputStream());

            dropDb.waitFor();

            // Restore database
            Process restoreDb = Runtime.getRuntime().exec(new String[] {
                    "sqlcmd",
                    "-S", serverName,
                    "-U", username,
                    "-P", password,
                    "-Q", "RESTORE DATABASE " + databaseName + " FROM DISK='" + backupFilePath + "' WITH REPLACE"
            });
            consumeStream(restoreDb.getInputStream());
            consumeStream(restoreDb.getErrorStream());
            restoreDb.waitFor();
            Thread.sleep(10000); // Wait for a few seconds to ensure the restore is complete

            System.out.println("Restore DB ==============================");
        } catch (Exception e) {
            System.out.println("Database restore task FAILED.");
            Assert.fail("Database restore task FAILED: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Database restore task completed.");
    }

    // Helper method to consume process streams
    private static void consumeStream(java.io.InputStream inputStream) {
        new Thread(() -> {
            try (java.util.Scanner s = new java.util.Scanner(inputStream)) {
                while (s.hasNextLine()) {
                    s.nextLine();
                }
            }
        }).start();
    }
}