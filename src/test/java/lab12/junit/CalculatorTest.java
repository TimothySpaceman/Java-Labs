package lab12.junit;

import static org.junit.Assert.*;

import lab12.Calculator;
import org.junit.*;
import org.junit.rules.*;

public class CalculatorTest {
    private static Calculator calculator;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Rule
    public TestName testName = new TestName();

    @Rule
    public Timeout globalTimeout = Timeout.seconds(2);

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Starting CalculatorTest");
        calculator = new Calculator();
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("Finished CalculatorTest");
        calculator = null;
    }

    @Before
    public void beforeTest() {
        System.out.println("Running Test " + testName.getMethodName());
    }

    @After
    public void afterTest() {
        System.out.println("Finished Test " + testName.getMethodName());
    }

    @Test
    public void testAdd() {
        assertEquals("Addition failed", 5, calculator.add(2, 3));
    }

    @Test
    public void testSubtract() {
        assertEquals("Subtraction failed", 1, calculator.subtract(3, 2));
    }

    @Test
    public void testDivide() {
        assertEquals("Division failed", 2, calculator.divide(6, 3));
    }

    @Test
    public void testDivideByZero() {
        expectedException.expect(IllegalArgumentException.class);
        calculator.divide(5, 0);
    }

    @Test
    public void testReverseString() {
        assertEquals("Reverse failed", "cba", calculator.reverseString("abc"));
    }

    @Test
    public void testReverseStringWithNull() {
        assertNull("Null check in reverseString failed", calculator.reverseString(null));
    }

    @Ignore("Test not implemented yet")
    @Test
    public void ignoredTest() {
        fail("This test is not implemented yet");
    }
}

