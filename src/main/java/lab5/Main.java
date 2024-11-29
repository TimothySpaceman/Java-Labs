package lab5;

import java.util.*;

public class Main {
    public static void main(String[] args) {
//        task1();
//        task2();
//        task3();
//        task4();
        task5();
    }

    public static void task1() {
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

        printList("Products", products);

        System.out.println("Index of LION: " + products.indexOf(lion));
        System.out.println();

        products.sort((Product a, Product b) -> {
            return (int) (a.getPrice() * 100 - b.getPrice() * 100);
        });
        printList("Products by price", products);

        System.out.println();
    }

    public static void printList(String title, List list) {
        System.out.println("[" + title + "]:");
//        list.forEach(product->{
//            System.out.println("\t" + product);
//        });

        for (int i = 0; i < list.size(); i += 1) {
            System.out.println("\t" + list.get(i));
        }
        System.out.println();
    }

    public static void task2() {
        System.out.println("==========TASK 2==========");
        ArrayDeque products = new ArrayDeque();

        products.add(new Product("Ролліні з курячим жюльєном", "АТБ", 20.50));
        products.add(new Product("Ролліні з телятиною", "АТБ", 22.30));
        products.add(new Product("Сосиска в тісті", "АТБ", 24.20));
        products.add(new Product("Йогурт персиковий", "Рудь", 20.70));

        printArrayDeque("Products", products);
        System.out.println("First product: " + products.peek());

        System.out.println();
    }

    public static void printArrayDeque(String title, ArrayDeque source) {
        System.out.println("[" + title + "]:");
        ArrayDeque deque = source.clone();

        while (!deque.isEmpty()) {
            System.out.println("\t" + deque.pop());
        }
        System.out.println();
    }

    public static void task3() {
        System.out.println("==========TASK 3==========");
        Product lion = new Product("Батончик LION", "Nestle", 17.60);

        TreeSet<Product> products = new TreeSet<Product>() {{
            add(new Product("Ролліні з курячим жюльєном", "АТБ", 20.50));
            add(new Product("Ролліні з телятиною", "АТБ", 22.30));
            add(lion);
            add(new Product("Сосиска в тісті", "АТБ", 24.20));
            add(new Product("Сирок", "Своя Лінія", 9.60));
            add(new Product("Йогурт персиковий", "Рудь", 20.70));
        }};

        printSet("Products", products);
        System.out.println("First (cheapest) product: " + products.first());
        printSet("Products cheaper than LION", products.headSet(lion));
        printSet("Products more expensive than LION", products.tailSet(lion));
        printSet("Products reversed", products.descendingSet());

        System.out.println();
    }

    public static void printSet(String title, Set source) {
        System.out.println("[" + title + "]:");

        Iterator iterator = source.iterator();
        while (iterator.hasNext()) {
            System.out.println("\t" + iterator.next());
        }

        System.out.println();
    }

    public static void task4() {
        System.out.println("==========TASK 4==========");
        Product lion = new Product("LION", "Nestle", 17.60);

        ArrayList<Product> productArrayList = new ArrayList<Product>() {{
            add(new Product("Ролліні з курячим жюльєном", "АТБ", 20.50));
            add(new Product("Ролліні з телятиною", "АТБ", 22.30));
            add(new Product("Сосиска в тісті", "АТБ", 24.20));
            add(new Product("Сирок", "Своя Лінія", 9.60));
            add(new Product("Йогурт персиковий", "Рудь", 20.70));
        }};

        HashMap<String, Product> products = new HashMap<String, Product>();

        productArrayList.forEach(p -> {
            products.put(p.getTitle(), p);
        });

        products.putIfAbsent(lion.getTitle(), lion);
        products.putIfAbsent(lion.getTitle(), new Product("NOT LION", "", 0));
        printMap("Products", products);
        System.out.println("LION: " + products.get(lion.getTitle()));
        System.out.println("Contains сирок: " + (products.containsKey("Сирок") ? "Yes" : "No"));
        System.out.println("Contains банан: " + (products.containsValue(new Product("Банан", "Бананова республіка", 72.30)) ? "Yes" : "No"));
        printSet("Keys", products.keySet());

        System.out.println("EntrySet: ");
        Set<Map.Entry<String, Product>> entrySet = products.entrySet();
        entrySet.forEach(e -> {
            System.out.println("\t" + e.getKey() + " === " + e.getValue());
        });

        System.out.println();
    }

    public static void printMap(String title, Map source) {
        System.out.println("[" + title + "]:");

        source.forEach((k, v) -> {
            System.out.println("\t{" + k + "}: " + v);
        });

        System.out.println();
    }

    public static void task5() {
        System.out.println("==========TASK 5==========");
        Product lion = new Product("LION", "Nestle", 17.60);

        List<Product> products = new ArrayList<Product>(Arrays.asList(lion,
                new Product("Ролліні з курячим жюльєном", "АТБ", 20.50),
                new Product("Ролліні з телятиною", "АТБ", 22.30),
                new Product("Сосиска в тісті", "АТБ", 24.20),
                new Product("Сирок", "Своя Лінія", 9.60),
                new Product("Йогурт персиковий", "Рудь", 20.70)
        ));
        printList("Products", products);

        products.sort(Comparator.comparing(Product::getManufacturer));
        printList("Products by manufacturer", products);

        System.out.println("LION index: " + Collections.binarySearch(products, lion));

        printList("Products reversed", products.reversed());

        Collections.shuffle(products);
        printList("Products shuffled", products);

        Collections.rotate(products, 3);
        printList("Products rotated by 3", products);

        System.out.println("Cheapest: " + Collections.min(products));
        System.out.println("Most expensive: " + Collections.max(products));

        List<Product> productsCopy = new ArrayList<Product>(products);
        Collections.copy(productsCopy, products);
        Collections.fill(productsCopy, new Product("Fill Product", "Java", 5.00));
        printList("Products filled", productsCopy);

        printList("Products checked", new ArrayList(Collections.checkedCollection(products, Product.class)));

        System.out.println("LION frequency: " + Collections.frequency(products, lion));
        products.add(lion);
        System.out.println("LION frequency after adding another one: " + Collections.frequency(products, lion));


        System.out.println();
    }

}
