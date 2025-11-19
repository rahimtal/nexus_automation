import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class RunFailedTestNG {

    private static final int MAX_RETRIES = 2;
    private static final String projectRoot = System.getProperty("user.dir");
    private static final String MAIN_SUITE_PATH = projectRoot + File.separator + "testng.xml";

    private static final String FAILED_SUITE_PATH = findFailedSuitePath();

    private static String findFailedSuitePath() {
        File testOutputDir = new File(projectRoot, "test-output");
        File[] dirs = testOutputDir.listFiles(File::isDirectory);
        if (dirs != null) {
            for (File dir : dirs) {
                File failedSuite = new File(dir, "testng-failed.xml");
                if (failedSuite.exists()) {
                    return failedSuite.getAbsolutePath();
                }
            }
        }
        // Fallback: check directly under test-output
        File failedSuite = new File(testOutputDir, "testng-failed.xml");
        if (failedSuite.exists()) {
            return failedSuite.getAbsolutePath();
        }
        // Not found, return default expected path (for first run)
        return testOutputDir + File.separator + "testng-failed.xml";
    }

    public static void main(String[] args) {
        runTestNGSuitesWithRetries(MAIN_SUITE_PATH, FAILED_SUITE_PATH, MAX_RETRIES);
    }

    public static void runTestNGSuitesWithRetries(String mainSuite, String failedSuite, int maxRetries) {
        int attempt = 0;

        while (attempt <= maxRetries) {
            System.out.println("\n==================== Attempt #" + (attempt + 1) + " ====================");

            TestNG testng = new TestNG();
            List<String> suites = new ArrayList<>();

            File failedXml = new File(failedSuite);

            if (!failedXml.exists()) {
                System.out.println("❌ Failed suite file not found. No retry possible.");
                break;
            } else {
                System.out.println("🔁 Retrying failed tests from: " + failedSuite);
                suites.add(failedXml.getAbsolutePath());
            }

            testng.setTestSuites(suites);
            testng.run();

            // Debug logs
            System.out.println("➡ hasFailure(): " + testng.hasFailure());
            System.out.println("➡ hasSkip(): " + testng.hasSkip());

            // Wait to ensure testng-failed.xml is written
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // Check if we should retry
            File failedXmlCheck = new File(failedSuite);
            if (!failedXmlCheck.exists()) {
                System.out.println("✅ All tests passed or no failed suite generated. Exiting.");
                break;
            } else {
                System.out.println("⚠️ Retrying due to failed suite detected.");
            }

            attempt++;
        }

        System.out.println("==================== Test Execution Finished ====================");
    }
}
