package lab2.task2;

public class Main {
    public static void main(String[] args) {
        double[] values = new double[]{78, 52, 7, 59, 10, 82, 16, 70, 87, 40};

        System.out.println("Addition: " + Operation.addition(values));
        System.out.println("Subtraction: " + Operation.subtraction(values));
        System.out.println("Multiplication: " + Operation.multiplication(values));
        System.out.println("Division: " + Operation.division(values));
        System.out.println("Average: " + Operation.average(values));
        System.out.println("Minimum: " + Operation.minimum(values));
        System.out.println("Maximum: " + Operation.maximum(values));
    }
}
