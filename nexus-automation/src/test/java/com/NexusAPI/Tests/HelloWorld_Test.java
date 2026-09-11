import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HelloWorld_Test {

    @BeforeClass
    public void setUp() {
        // Setup code here
    }

    @Test
    public void testHelloWorld() {
        System.out.println("Hello, World!");
    }
}