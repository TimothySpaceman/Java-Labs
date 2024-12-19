package lab8;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
//        task1();
//        task2();
//        task3_4();
        task5();
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

    public static void task3_4(){
        System.out.println("=========TASK 3,4=========");

        class Product {
            private String name;
            private String brand;
            private double price;
            private int count;

            public Product(String name, String brand, double price, int count) {
                this.name = name;
                this.brand = brand;
                this.price = price;
                this.count = count;
            }

            public String getName() {
                return name;
            }

            public String getBrand() {
                return brand;
            }

            public double getPrice() {
                return price;
            }

            public int getCount() {
                return count;
            }

            @Override
            public String toString() {
                return name + " by " + brand + " with price " + price + " (" + count + " pcs)";
            }
        }

        List<Product> products = new ArrayList<Product>() {
            {
                add(new Product("ThinkPad smth", "Lenovo", 1200, 10));
                add(new Product("Pixel", "Google", 800, 15));
                add(new Product("Surface", "Microsoft", 1500, 5));
                add(new Product("IdeaPad smth", "Lenovo", 700, 20));
                add(new Product("ChromeBook smth", "Google", 650, 50));
            }
        };

        System.out.println("All brands: ");
        products.stream()
                .map(Product::getBrand)
                .distinct()
                .forEach(System.out::println);

        System.out.println("\nProducts cheaper than 1000:");
        products.stream()
                .filter(product -> product.getPrice() < 1000)
                .limit(2)
                .forEach(System.out::println);

        int totalAmount = products.stream()
                .map(Product::getCount)
                .reduce(0, Integer::sum);
        System.out.println("\nTotal amount: " + totalAmount);

        System.out.println("\nProducts by brand:");
        products.stream()
                .collect(Collectors.groupingBy(Product::getBrand))
                .forEach((brand, productList) -> System.out.println(brand + ": " + productList));

        System.out.println("\nProducts by price:");
        products.stream()
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .forEach(System.out::println);
        System.out.println();
    }

    public static void task5(){
        System.out.println("==========TASK 5==========");

        Integer[] numbers = {4, 8, 16, 2, 37};

        String result = Arrays.stream(numbers)
                .max(Integer::compareTo)
                .map(String::valueOf)
                .orElse("No numbers provided");

        System.out.println("Max: " + result);

        System.out.println();
    }
}
