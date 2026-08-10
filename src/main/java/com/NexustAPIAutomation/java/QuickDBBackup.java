package com.NexustAPIAutomation.java;

import java.io.IOException;

public class QuickDBBackup {
    public static void main(String[] args) throws IOException {
        // Allow non-interactive runs to skip the prompt with -y / --yes / --force.
        boolean skipConfirmation = false;
        for (String arg : args) {
            if ("-y".equalsIgnoreCase(arg) || "--yes".equalsIgnoreCase(arg) || "--force".equalsIgnoreCase(arg)) {
                skipConfirmation = true;
                break;
            }
        }
        backupDatabase(skipConfirmation);
    }

    public static void backupDatabase() throws IOException {
        backupDatabase(false);
    }

    public static void backupDatabase(boolean skipConfirmation) throws IOException {
        // Load properties from Project.properties file
        String serverName = ReadProjectProperties.ReadFile("serverName");
        String username = ReadProjectProperties.ReadFile("Dbusername");
        String password = ReadProjectProperties.ReadFile("Dbpassword");
        String databaseName = ReadProjectProperties.ReadFile("databaseName");

        // Destination for the backup file. Use a dedicated property when provided so the
        // golden restore source (backupFilePath) is not overwritten by accident.
        String backupOutputFilePath = ReadProjectProperties.ReadFile("backupOutputFilePath");
        if (backupOutputFilePath == null || backupOutputFilePath.isBlank()) {
            backupOutputFilePath = ReadProjectProperties.ReadFile("backupFilePath");
        }

        if (serverName == null || username == null || password == null || databaseName == null
                || backupOutputFilePath == null) {
            throw new RuntimeException("One or more required properties are missing in Project.properties file.");
        }

        // Guard against accidental runs: require an explicit confirmation before backing up.
        if (!skipConfirmation && !confirmBackup(serverName, databaseName, backupOutputFilePath)) {
            System.out.println("Database backup cancelled by user.");
            return;
        }

        try {
            String backupQuery = buildBackupQuery(databaseName, backupOutputFilePath);

            // Backup database
            Process backupDb = Runtime.getRuntime().exec(new String[] {
                    "sqlcmd",
                    "-S", serverName,
                    "-U", username,
                    "-P", password,
                    "-Q",
                    backupQuery
            });

            // Capture output and error streams for debugging
            StringBuilder outputBuilder = new StringBuilder();
            StringBuilder errorBuilder = new StringBuilder();

            Thread outputThread = new Thread(() -> {
                try (java.util.Scanner s = new java.util.Scanner(backupDb.getInputStream())) {
                    while (s.hasNextLine()) {
                        String line = s.nextLine();
                        outputBuilder.append(line).append("\n");
                        System.out.println("BACKUP DB OUTPUT: " + line);
                    }
                }
            });

            Thread errorThread = new Thread(() -> {
                try (java.util.Scanner s = new java.util.Scanner(backupDb.getErrorStream())) {
                    while (s.hasNextLine()) {
                        String line = s.nextLine();
                        errorBuilder.append(line).append("\n");
                        System.out.println("BACKUP DB ERROR: " + line);
                    }
                }
            });

            outputThread.start();
            errorThread.start();

            int backupExitCode = backupDb.waitFor();
            outputThread.join();
            errorThread.join();

            String output = outputBuilder.toString();

            // Check for SQL errors in output (Msg xxxx indicates SQL Server error)
            if (output.contains("Msg ") || backupExitCode != 0) {
                System.out.println("Backup database command failed.");
                System.out.println("Exit Code: " + backupExitCode);
                System.out.println("Output:\n" + output);
                throw new RuntimeException(
                        "Backup database command failed. Check output path and SQL Server access. Output: " + output);
            } else {
                System.out.println("Database backed up successfully to " + backupOutputFilePath);
            }

            Thread.sleep(6000); // Wait for a few seconds to ensure the backup is complete

            System.out.println("Backup DB ==============================");
        } catch (InterruptedException | IOException e) {
            System.out.println("Database backup task INTERRUPTED or IO error occurred.");
            throw new RuntimeException("Database backup task INTERRUPTED or IO error: " + e.getMessage(), e);
        } catch (Exception e) {
            System.out.println("Database backup task FAILED.");
            throw new RuntimeException("Database backup task FAILED: " + e.getMessage(), e);
        }
        System.out.println("Database backup task completed.");
    }

    private static String buildBackupQuery(String databaseName, String backupOutputFilePath) {
        return "BACKUP DATABASE [" + databaseName + "] TO DISK=N'" + backupOutputFilePath
                + "' WITH INIT, FORMAT, COMPRESSION, STATS = 10";
    }

    private static boolean confirmBackup(String serverName, String databaseName, String backupOutputFilePath) {
        System.out.println("========================================================");
        System.out.println(" DATABASE BACKUP - please confirm before proceeding");
        System.out.println("   Server      : " + serverName);
        System.out.println("   Database    : " + databaseName);
        System.out.println("   Backup file : " + backupOutputFilePath);
        System.out.println("   (this will overwrite the backup file if it already exists)");
        System.out.println("========================================================");
        System.out.print("Type 'yes' to continue: ");

        java.io.Console console = System.console();
        String response;
        if (console != null) {
            response = console.readLine();
        } else {
            // Fallback when no console is attached (e.g. running inside an IDE).
            try (java.util.Scanner scanner = new java.util.Scanner(System.in)) {
                response = scanner.hasNextLine() ? scanner.nextLine() : "";
            }
        }

        return response != null && response.trim().equalsIgnoreCase("yes");
    }
}
