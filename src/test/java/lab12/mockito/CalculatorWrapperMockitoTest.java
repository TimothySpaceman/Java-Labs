package lab12.mockito;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import lab12.Calculator;
import lab12.CalculatorWrapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorWrapperMockitoTest {
    @Mock
    private CalculatorWrapper calculatorWrapper;

    @InjectMocks
    private Calculator injectedCalculator;

    @Spy
    private Calculator spyCalculator;

    @Mock
    private Calculator mockedCalculator;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAdd() {
        when(calculatorWrapper.add(2, 3)).thenReturn(5);

        int result = calculatorWrapper.add(2, 3);
//        int result2 = calculatorWrapper.add(2, 3);

        assertEquals("Addition failed", 5, result);

        verify(calculatorWrapper, times(1)).add(2, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDivideByZero() {
        when(calculatorWrapper.divide(5, 0)).thenThrow(new IllegalArgumentException("Cannot divide by zero"));
        calculatorWrapper.divide(5, 0);
    }

    @Test
    public void testSpy() {
        when(spyCalculator.add(2, 3)).thenReturn(10);

        int result = spyCalculator.add(2, 3);
        assertEquals("Spy method failed", 10, result);

        result = spyCalculator.subtract(10, 5);
        assertEquals("Spy method failed", 5, result);

        verify(spyCalculator, times(1)).add(anyInt(), anyInt());
        verify(spyCalculator, times(1)).subtract(anyInt(), anyInt());
    }

    @Captor
    ArgumentCaptor<Integer> captor;

    @Test
    public void testArgumentCaptor() {
        calculatorWrapper.add(2, 3);
        verify(calculatorWrapper).add(captor.capture(), captor.capture());

        assertEquals(Integer.valueOf(2), captor.getAllValues().get(0));
        assertEquals(Integer.valueOf(3), captor.getAllValues().get(1));
    }
}