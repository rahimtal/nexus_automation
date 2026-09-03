package com.NexustAPIAutomation.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Redeploys the Nexus SQL payload (stored procedures, tables, seed data) after a
 * database restore.
 *
 * A restore from TWO.bak reverts every SQL object to whatever vintage the backup
 * was captured at, so any procedure or seed row added since then disappears.
 * Running the payload afterwards puts the database back in step with the API
 * branch that is currently checked out.
 *
 * Docker runs inside WSL on this setup, so the work is handed to a generated
 * bash script rather than invoking docker.exe directly.
 */
public class SqlPayloadDeploy {

    private static final String DEFAULT_DISTRO = "Ubuntu";
    private static final String DEFAULT_API_PATH = "/home/rahimtal/projects/nexus-api";
    private static final String DEFAULT_DOCKER_PATH = "/home/rahimtal/projects/nexus-docker";

    public static void main(String[] args) {
        deploy();
    }

    /**
     * Rebuilds the install payload from the checked-out API branch and runs
     * sqlpayload-dev against it. Controlled by {@code sqlPayloadAfterRestore} in
     * Project.Properties; when disabled this is a no-op.
     */
    public static void deploy() {
        if (!readFlag("sqlPayloadAfterRestore", true)) {
            System.out.println("SQL payload deploy skipped (sqlPayloadAfterRestore=false).");
            return;
        }

        String distro = readOrDefault("wslDistro", DEFAULT_DISTRO);
        String apiPath = readOrDefault("wslNexusApiPath", DEFAULT_API_PATH);
        String dockerPath = readOrDefault("wslNexusDockerPath", DEFAULT_DOCKER_PATH);
        boolean rebuild = readFlag("sqlPayloadRebuildInstallScripts", true);

        System.out.println("\n========== POST-RESTORE: SQL Payload Deploy ==========");
        System.out.println("WSL distro   : " + distro);
        System.out.println("nexus-api    : " + apiPath);
        System.out.println("nexus-docker : " + dockerPath);
        System.out.println("Rebuild install scripts from branch: " + rebuild);

        Path script = null;
        try {
            script = writeScript(apiPath, dockerPath, rebuild);
            int exitCode = runWsl(distro, script);

            if (exitCode != 0) {
                throw new RuntimeException(
                        "SQL payload deploy failed with exit code " + exitCode
                                + ". The database is restored but may be missing procedures or seed data.");
            }
            System.out.println("========== POST-RESTORE: SQL Payload Deploy COMPLETED ==========\n");
        } catch (IOException | InterruptedException e) {
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            throw new RuntimeException("SQL payload deploy could not be executed: " + e.getMessage(), e);
        } finally {
            if (script != null) {
                try {
                    Files.deleteIfExists(script);
                } catch (IOException ignored) {
                    // temp file cleanup is best-effort
                }
            }
        }
    }

    private static int runWsl(String distro, Path script) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("wsl", "-d", distro, "--", "bash", toWslPath(script));
        pb.redirectErrorStream(true);

        Process process = pb.start();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("SQLPAYLOAD: " + line);
            }
        }
        return process.waitFor();
    }

    private static Path writeScript(String apiPath, String dockerPath, boolean rebuild) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("#!/usr/bin/env bash\n");
        sb.append("set -euo pipefail\n");
        sb.append("API_DIR=").append(shellQuote(apiPath)).append('\n');
        sb.append("DOCKER_DIR=").append(shellQuote(dockerPath)).append('\n');
        sb.append("INSTALL_DIR=\"$DOCKER_DIR/sqlserver/install\"\n\n");

        sb.append("if [ ! -d \"$DOCKER_DIR\" ]; then\n");
        sb.append("  echo \"ERROR: nexus-docker not found at $DOCKER_DIR\" >&2\n");
        sb.append("  exit 1\n");
        sb.append("fi\n\n");

        if (rebuild) {
            // Regenerate the concatenated payload so it matches the checked-out branch.
            sb.append("SCRIPT_DIR=\"$API_DIR/src/server/db Scripts\"\n");
            sb.append("if [ ! -d \"$SCRIPT_DIR\" ]; then\n");
            sb.append("  echo \"ERROR: db Scripts not found at $SCRIPT_DIR\" >&2\n");
            sb.append("  exit 1\n");
            sb.append("fi\n");
            sb.append("echo \"Rebuilding install payload from $(git -C \"$API_DIR\" rev-parse --short HEAD 2>/dev/null || echo unknown)...\"\n");
            sb.append("cd \"$SCRIPT_DIR\"\n");
            sb.append("bash BuildInstallScript.sh\n");
            sb.append("mkdir -p \"$INSTALL_DIR\"\n");
            sb.append("for f in InstallCsmApiDatabaseComponents.sql InstallNexusApiDatabaseComponents.sql \\\n");
            sb.append("         Search_Install_Api_DatabaseComponents.sql Search_Install_CSM_DatabaseComponents.sql; do\n");
            sb.append("  if [ -f \"$f\" ]; then\n");
            sb.append("    cp -f \"$f\" \"$INSTALL_DIR/$f\"\n");
            sb.append("    echo \"  copied $f ($(wc -c < \"$f\") bytes)\"\n");
            sb.append("  else\n");
            sb.append("    echo \"  WARNING: generated file missing: $f\" >&2\n");
            sb.append("  fi\n");
            sb.append("done\n\n");
        }

        sb.append("cd \"$DOCKER_DIR\"\n");
        // SQL_INSTALL_FORCE guarantees the install step runs even if auto-deploy is off.
        sb.append("SQL_INSTALL_FORCE=true ./nexus dev run --rm --no-deps sqlpayload-dev\n");

        Path temp = Files.createTempFile("nexus-sqlpayload-", ".sh");
        Files.writeString(temp, sb.toString(), StandardCharsets.UTF_8);
        return temp;
    }

    /** Maps a Windows path onto the WSL automount, e.g. D:\a\b -> /mnt/d/a/b */
    private static String toWslPath(Path path) {
        String absolute = path.toAbsolutePath().toString();
        if (absolute.length() > 2 && absolute.charAt(1) == ':') {
            char drive = Character.toLowerCase(absolute.charAt(0));
            return "/mnt/" + drive + absolute.substring(2).replace('\\', '/');
        }
        return absolute.replace('\\', '/');
    }

    private static String shellQuote(String value) {
        return "'" + value.replace("'", "'\\''") + "'";
    }

    private static String readOrDefault(String key, String fallback) {
        String value = ReadProjectProperties.ReadFile(key);
        return (value == null || value.isBlank()) ? fallback : value.trim();
    }

    private static boolean readFlag(String key, boolean fallback) {
        String value = ReadProjectProperties.ReadFile(key);
        return (value == null || value.isBlank()) ? fallback : Boolean.parseBoolean(value.trim());
    }

    private SqlPayloadDeploy() {
        // static utility
    }
}
