package lab8;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
//        task1();
        task2();
    }

    public static void task1(){
        System.out.println("==========TASK 1==========");

        Printable p = () -> System.out.println("Hello Functional Interfaces");
        p.print();

        System.out.println();
    }

    public static void task2(){
        System.out.println("==========TASK 2==========");

        Predicate<String> isNumeric = str -> {
            try {
                Double.parseDouble(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        };
        System.out.println("isNumeric(\"123\"): " + isNumeric.test("123"));
        System.out.println("isNumeric(\"123.45\"): " + isNumeric.test("123.45"));
        System.out.println("isNumeric(\"a\"): " + isNumeric.test("a"));
        Predicate<String> isInteger = isNumeric.and(str -> {
            try {
                Integer.parseInt(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        });
        System.out.println("isInteger(\"123\"): " + isInteger.test("123"));
        System.out.println("isInteger(\"123.45\"): " + isInteger.test("123.45"));
        System.out.println("isInteger(\"a\"): " + isInteger.test("a"));
        System.out.println();

        Consumer<String> sayStart = str -> System.out.println("Lecture started at " + str.split("-")[0]);
        Consumer<String> sayEnd = str -> System.out.println("Lecture ended at " + str.split("-")[1]);
        Consumer<String> saySchedule = sayStart.andThen(sayEnd);
        saySchedule.accept("8:30-9:50");
        System.out.println();

        Supplier<String> uppercaseSentence = () -> "This is a sentence in uppercase".toUpperCase();
        System.out.println(uppercaseSentence.get());
        System.out.println();

        Function<String, Integer> stringToProduct = str -> {
            String[] numbers = str.split(" ");
            int result = 1;
            for (int i = 0; i < numbers.length; i += 1) {
                result *= Integer.parseInt(numbers[i]);
            }
            return result;
        };
        System.out.println("Product: " + stringToProduct.apply("1 5 3 2 7"));

        System.out.println();
    }
}