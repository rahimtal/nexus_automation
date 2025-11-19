import java.util.Collections;

import org.testng.TestNG;

public class RunFailedTestNG {
    public static void main(String[] args) {
        TestNG testng = new TestNG();
        testng.setTestSuites(Collections.singletonList("test-output/Nexus API Regression/testng-failed.xml"));
        testng.run();
    }
}