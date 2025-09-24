import org.testng.TestNG;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestExecutor {

    private static final int MAX_RETRIES = 2;
    private static final String projectRoot = System.getProperty("user.dir");
    private static final String MAIN_SUITE_PATH = projectRoot + File.separator + "testng.xml";
    private static final String FAILED_SUITE_PATH = projectRoot + File.separator + "test-output" + File.separator +
            "Failed suite (Nexus API Regression)" + File.separator + "testng-failed.xml";

    public static void main(String[] args) {
        runTestNGSuitesWithRetries(MAIN_SUITE_PATH, FAILED_SUITE_PATH, MAX_RETRIES);
    }

    public static void runTestNGSuitesWithRetries(String mainSuite, String failedSuite, int maxRetries) {
        int attempt = 0;

        while (attempt <= maxRetries) {
            System.out.println("\n==================== Attempt #" + (attempt + 1) + " ====================");

            TestNG testng = new TestNG();
            List<String> suites = new ArrayList<>();

            if (attempt == 0) {
                suites.add(mainSuite);
            } else {
                File failedXml = new File(failedSuite);

                if (!failedXml.exists()) {
                    System.out.println("❌ Failed suite file not found. No retry possible.");
                    break;
                }

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
