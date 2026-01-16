package com.NexustAPIAutomation.java;

import java.io.File;

public class RunSpecificTestNG {
    public static void main(String[] args) {
        try {
            // Define the Maven command explicitly as separate arguments
            String[] command = {
                    "cmd.exe", "/c", "mvn", "test", "-Dsurefire.suiteXmlFiles=target/test-output/testng-failed.xml"
            };
            System.out.println(
                    "Executing command: mvn test -Dsurefire.suiteXmlFiles=target/test-output/testng-failed.xml");

            // Create and start the ProcessBuilder
            ProcessBuilder failedTestsBuilder = new ProcessBuilder(command);

            // Set the working directory to the Maven project root
            failedTestsBuilder.directory(new File("c:\\Users\\Admin\\Documents\\GitHub\\nexus_automation"));

            failedTestsBuilder.inheritIO();
            Process process = failedTestsBuilder.start();

            // Wait for the process to complete and capture the exit code
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Tests executed successfully.");
            } else {
                System.err.println("Tests failed with exit code: " + exitCode);
            }
        } catch (Exception e) {
            System.err.println("An error occurred while running Maven: " + e.getMessage());
            e.printStackTrace();
        }
    }
}