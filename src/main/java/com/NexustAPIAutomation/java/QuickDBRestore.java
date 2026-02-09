package com.NexustAPIAutomation.java;

import java.io.IOException;

public class QuickDBRestore {
    public static void main(String[] args) throws IOException {
        restoreDatabase();
    }

    public static void restoreDatabase() throws IOException {
        java.util.Properties properties = new java.util.Properties();
        try (java.io.InputStream input = QuickDBRestore.class.getClassLoader()
                .getResourceAsStream("Project.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find Project.properties");
                return;
            }
            properties.load(input);
        }

        String serverName = properties.getProperty("serverName", "DESKTOP-QU86F3Q");
        String username = properties.getProperty("username", "sa");
        String password = properties.getProperty("password", "cogs");
        String databaseName = properties.getProperty("databaseName", "TWO");
        String backupFilePath = properties.getProperty("backupFilePath", "B:\\DatabaseBackup\\TEST_CSM_58");

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
            consumeStream(dropDb.getErrorStream());
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