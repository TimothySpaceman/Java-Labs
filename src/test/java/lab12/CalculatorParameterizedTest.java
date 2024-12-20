package lab12;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CalculatorParameterizedTest {
    private int input1;
    private int input2;
    private int expected;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { 1, 1, 2 },
                { 2, 3, 5 },
                { 6, 7, 13 }
        });
    }

    public CalculatorParameterizedTest(int input1, int input2, int expected) {
        this.input1 = input1;
        this.input2 = input2;
        this.expected = expected;
    }

    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        assertEquals("Parameterized addition failed", expected, calculator.add(input1, input2));
    }
}