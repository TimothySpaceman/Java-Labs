package lab12;

public class CalculatorWrapper {
    private Calculator calculator;

    public CalculatorWrapper(Calculator calculator) {
        this.calculator = calculator;
    }

    public int add(int a, int b) {
        return this.calculator.add(a, b);
    }

    public int subtract(int a, int b) {
        return this.calculator.subtract(a, b);
    }

    public int divide(int a, int b) {
        return this.calculator.divide(a, b);
    }

    public String reverseString(String input) {
        return this.calculator.reverseString(input);
    }
}
