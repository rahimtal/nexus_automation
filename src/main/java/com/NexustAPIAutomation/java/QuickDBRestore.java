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
        String username = ReadProjectProperties.ReadFile("Dbusername");
        String password = ReadProjectProperties.ReadFile("Dbpassword");
        String databaseName = ReadProjectProperties.ReadFile("databaseName");
        String backupFilePath = ReadProjectProperties.ReadFile("backupFilePath");

        if (serverName == null || username == null || password == null || databaseName == null
                || backupFilePath == null) {
            Assert.fail("One or more required properties are missing in Project.properties file.");
            return;
        }

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
            // Log output and error streams for debugging
            logStream(dropDb.getInputStream(), "DROP DB OUTPUT");
            logStream(dropDb.getErrorStream(), "DROP DB ERROR");

            dropDb.waitFor();

            // Restore database
            Process restoreDb = Runtime.getRuntime().exec(new String[] {
                    "sqlcmd",
                    "-S", serverName,
                    "-U", username,
                    "-P", password,
                    "-Q",
                    "RESTORE DATABASE [" + databaseName + "] FROM DISK=N'" + backupFilePath + "' WITH REPLACE, RECOVERY"
            });

            // Log output and error streams for debugging
            logStream(restoreDb.getInputStream(), "RESTORE DB OUTPUT");
            logStream(restoreDb.getErrorStream(), "RESTORE DB ERROR");

            int restoreExitCode = restoreDb.waitFor();
            if (restoreExitCode != 0) {
                System.out.println("Restore database command failed with exit code: " + restoreExitCode);
                Assert.fail("Restore database command failed. Check sqlcmd availability and parameters.");
            } else {
                System.out.println("Database restored successfully.");
            }

            Thread.sleep(6000); // Wait for a few seconds to ensure the restore is complete

            System.out.println("Restore DB ==============================");
        } catch (InterruptedException | IOException e) {
            System.out.println("Database restore task INTERRUPTED or IO error occurred.");
            Assert.fail("Database restore task INTERRUPTED or IO error: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.out.println("Database restore task FAILED.");
            Assert.fail("Database restore task FAILED: " + e.getMessage());
            System.exit(1);
        }
        System.out.println("Database restore task completed.");
    }

    // Helper method to log process streams
    private static void logStream(java.io.InputStream inputStream, String streamName) {
        new Thread(() -> {
            try (java.util.Scanner s = new java.util.Scanner(inputStream)) {
                while (s.hasNextLine()) {
                    System.out.println(streamName + ": " + s.nextLine());
                }
            }
        }).start();
    }

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