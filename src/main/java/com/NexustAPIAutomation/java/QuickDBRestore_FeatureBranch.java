package com.NexustAPIAutomation.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuickDBRestore_FeatureBranch {
    public static void main(String[] args) throws IOException {
        restoreDatabase();
    }

    public static void restoreDatabase() throws IOException {
        // Load properties from Project.properties file
        String serverName = ReadProjectProperties.ReadFile("serverName");
        String username = ReadProjectProperties.ReadFile("Dbusername");
        String password = ReadProjectProperties.ReadFile("Dbpassword");
        String databaseName = ReadProjectProperties.ReadFile("databaseName");
        String backupFilePath = ReadProjectProperties.ReadFile("backupFilePath_FeatureBranch");

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

                String defaultDataPath = querySingleValue(serverName, username, password,
                    "SET NOCOUNT ON; SELECT CAST(SERVERPROPERTY('InstanceDefaultDataPath') AS NVARCHAR(4000))");
                String defaultLogPath = querySingleValue(serverName, username, password,
                    "SET NOCOUNT ON; SELECT CAST(SERVERPROPERTY('InstanceDefaultLogPath') AS NVARCHAR(4000))");

                if (defaultDataPath == null || defaultDataPath.isBlank() || defaultLogPath == null
                    || defaultLogPath.isBlank()) {
                throw new RuntimeException("Could not determine SQL Server default data/log paths.");
                }

                List<BackupFileEntry> backupFiles = queryBackupFiles(serverName, username, password, backupFilePath);
                String restoreQuery = buildRestoreQuery(databaseName, backupFilePath, defaultDataPath, defaultLogPath,
                    backupFiles);

            // Restore database
            Process restoreDb = Runtime.getRuntime().exec(new String[] {
                    "sqlcmd",
                    "-S", serverName,
                    "-U", username,
                    "-P", password,
                    "-Q",
                    restoreQuery
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

    private static String querySingleValue(String serverName, String username, String password, String query)
            throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(new String[] {
                "sqlcmd",
                "-S", serverName,
                "-U", username,
                "-P", password,
                "-W",
                "-h", "-1",
                "-Q", query
        });

        StringBuilder output = new StringBuilder();
        StringBuilder error = new StringBuilder();
        readProcessStreams(process, output, error, false);
        int exitCode = process.waitFor();

        if (exitCode != 0) {
            throw new RuntimeException("sqlcmd query failed: " + error);
        }

        for (String line : output.toString().split("\\R")) {
            String trimmed = line.trim();
            if (!trimmed.isEmpty() && !trimmed.matches("-+")) {
                return trimmed;
            }
        }

        return null;
    }

    private static List<BackupFileEntry> queryBackupFiles(String serverName, String username, String password,
            String backupFilePath) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(new String[] {
                "sqlcmd",
                "-S", serverName,
                "-U", username,
                "-P", password,
                "-W",
                "-h", "-1",
                "-s", "|",
                "-Q", "RESTORE FILELISTONLY FROM DISK=N'" + backupFilePath + "'"
        });

        StringBuilder output = new StringBuilder();
        StringBuilder error = new StringBuilder();
        readProcessStreams(process, output, error, false);
        int exitCode = process.waitFor();

        if (exitCode != 0) {
            throw new RuntimeException("RESTORE FILELISTONLY failed: " + error);
        }

        List<BackupFileEntry> entries = new ArrayList<>();
        for (String line : output.toString().split("\\R")) {
            String trimmed = line.trim();
            if (trimmed.isEmpty() || trimmed.matches("-+") || trimmed.startsWith("The backup set")
                    || trimmed.startsWith("Processed")) {
                continue;
            }

            String[] columns = trimmed.split("\\|");
            if (columns.length < 3) {
                continue;
            }

            String logicalName = columns[0].trim();
            String physicalName = columns[1].trim();
            String type = columns[2].trim();
            if (!logicalName.isEmpty() && !type.isEmpty()) {
                entries.add(new BackupFileEntry(logicalName, physicalName, type));
            }
        }

        if (entries.isEmpty()) {
            throw new RuntimeException("No file entries returned from RESTORE FILELISTONLY.");
        }

        return entries;
    }

    private static String buildRestoreQuery(String databaseName, String backupFilePath, String defaultDataPath,
            String defaultLogPath, List<BackupFileEntry> backupFiles) {
        String normalizedDataPath = ensureTrailingBackslash(defaultDataPath);
        String normalizedLogPath = ensureTrailingBackslash(defaultLogPath);
        StringBuilder restore = new StringBuilder();
        restore.append("RESTORE DATABASE [")
                .append(databaseName)
                .append("] FROM DISK=N'")
                .append(backupFilePath)
                .append("' WITH REPLACE, RECOVERY");

        for (BackupFileEntry entry : backupFiles) {
            String targetDirectory = "L".equalsIgnoreCase(entry.type) ? normalizedLogPath : normalizedDataPath;
            String targetFileName = buildTargetFileName(databaseName, entry);
            restore.append(", MOVE N'")
                    .append(entry.logicalName)
                    .append("' TO N'")
                    .append(targetDirectory)
                    .append(targetFileName)
                    .append("'");
        }

        return restore.toString();
    }

    private static String buildTargetFileName(String databaseName, BackupFileEntry entry) {
        String extension = extractExtension(entry.physicalName);
        if ("L".equalsIgnoreCase(entry.type)) {
            return databaseName + "_log" + extension;
        }

        return databaseName + extension;
    }

    private static String extractExtension(String physicalName) {
        int dotIndex = physicalName.lastIndexOf('.');
        if (dotIndex >= 0) {
            return physicalName.substring(dotIndex);
        }
        return ".mdf";
    }

    private static String ensureTrailingBackslash(String path) {
        return path.endsWith("\\") ? path : path + "\\";
    }

    private static void readProcessStreams(Process process, StringBuilder outputBuilder, StringBuilder errorBuilder,
            boolean echoOutput) throws InterruptedException {
        Thread outputThread = new Thread(() -> {
            try (java.util.Scanner s = new java.util.Scanner(process.getInputStream())) {
                while (s.hasNextLine()) {
                    String line = s.nextLine();
                    outputBuilder.append(line).append("\n");
                    if (echoOutput) {
                        System.out.println("RESTORE DB OUTPUT: " + line);
                    }
                }
            }
        });

        Thread errorThread = new Thread(() -> {
            try (java.util.Scanner s = new java.util.Scanner(process.getErrorStream())) {
                while (s.hasNextLine()) {
                    String line = s.nextLine();
                    errorBuilder.append(line).append("\n");
                    if (echoOutput) {
                        System.out.println("RESTORE DB ERROR: " + line);
                    }
                }
            }
        });

        outputThread.start();
        errorThread.start();
        outputThread.join();
        errorThread.join();
    }

    private static final class BackupFileEntry {
        private final String logicalName;
        private final String physicalName;
        private final String type;

        private BackupFileEntry(String logicalName, String physicalName, String type) {
            this.logicalName = logicalName;
            this.physicalName = physicalName;
            this.type = type;
        }
    }
}