package lab12.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ CalculatorTest.class, CalculatorParameterizedTest.class })
public class CalculatorTestSuite {

}
