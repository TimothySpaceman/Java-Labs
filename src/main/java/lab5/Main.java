package lab5;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        task1();
    }

    public static void task1(){
        System.out.println("==========TASK 1==========");
        ArrayList<Product> products = new ArrayList<Product>();
        products.add(new Product("Ролліні з курячим жюльєном", "АТБ", 20.50));
        Product lion = new Product("Батончик LION", "Nestle", 17.60);
        products.addAll(
                new ArrayList<Product>() {
                    {
                        add(new Product("Ролліні з телятиною", "АТБ", 22.30));
                        add(new Product("Сосиска в тісті", "АТБ", 24.20));
                        add(lion);
                        add(new Product("Йогурт персиковий", "Рудь", 20.70));
                    }
                }
        );

        printArrayList("Products", products);

        System.out.println("Index of LION: " + products.indexOf(lion));
        System.out.println();

        products.sort((Product a, Product b) -> {
            return (int)(a.getPrice()*100 - b.getPrice()*100);
        });
        printArrayList("Products by price", products);

        System.out.println();
    }

    public static void printArrayList(String title, ArrayList list) {
        System.out.println("[" + title + "]:");
//        list.forEach(product->{
//            System.out.println("\t" + product);
//        });

        for(int i = 0; i < list.size(); i += 1) {
            System.out.println("\t" + list.get(i));
        }
        System.out.println();
    }

    public static void task2(){
        System.out.println("==========TASK 2==========");
        ArrayDeque products = new ArrayDeque();


        System.out.println();
    }

    public static void printArrayDeque(String title, ArrayDeque deque) {
        System.out.println("[" + title + "]:");
//        list.forEach(product->{
//            System.out.println("\t" + product);
//        });

        for(int i = 0; i < list.size(); i += 1) {
            System.out.println("\t" + list.get(i));
        }
        System.out.println();
    }
}
