package com.NexustAPIAutomation.java;

import java.io.IOException;

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
            throw new RuntimeException("One or more required properties are missing in Project.properties file.");
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
                    "RESTORE DATABASE [" + databaseName + "] FROM DISK=N'" + backupFilePath + "' WITH REPLACE, RECOVERY, MOVE 'GPSTWODat.mdf' TO '/var/opt/mssql/data/TWODat.mdf', MOVE 'GPSTWOLog.ldf' TO '/var/opt/mssql/data/TWOLog.ldf'"
            });

            // Capture output and error streams for debugging
            StringBuilder outputBuilder = new StringBuilder();
            StringBuilder errorBuilder = new StringBuilder();
            
            Thread outputThread = new Thread(() -> {
                try (java.util.Scanner s = new java.util.Scanner(restoreDb.getInputStream())) {
                    while (s.hasNextLine()) {
                        String line = s.nextLine();
                        outputBuilder.append(line).append("\n");
                        System.out.println("RESTORE DB OUTPUT: " + line);
                    }
                }
            });
            
            Thread errorThread = new Thread(() -> {
                try (java.util.Scanner s = new java.util.Scanner(restoreDb.getErrorStream())) {
                    while (s.hasNextLine()) {
                        String line = s.nextLine();
                        errorBuilder.append(line).append("\n");
                        System.out.println("RESTORE DB ERROR: " + line);
                    }
                }
            });
            
            outputThread.start();
            errorThread.start();

            int restoreExitCode = restoreDb.waitFor();
            outputThread.join();
            errorThread.join();
            
            String output = outputBuilder.toString();
            
            // Check for SQL errors in output (Msg xxxx indicates SQL Server error)
            if (output.contains("Msg ") || restoreExitCode != 0) {
                System.out.println("Restore database command failed.");
                System.out.println("Exit Code: " + restoreExitCode);
                System.out.println("Output:\n" + output);
                throw new RuntimeException("Restore database command failed. Check backup file path and SQL Server access. Output: " + output);
            } else {
                System.out.println("Database restored successfully.");
            }

            Thread.sleep(6000); // Wait for a few seconds to ensure the restore is complete

            System.out.println("Restore DB ==============================");
        } catch (InterruptedException | IOException e) {
            System.out.println("Database restore task INTERRUPTED or IO error occurred.");
            throw new RuntimeException("Database restore task INTERRUPTED or IO error: " + e.getMessage(), e);
        } catch (Exception e) {
            System.out.println("Database restore task FAILED.");
            throw new RuntimeException("Database restore task FAILED: " + e.getMessage(), e);
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