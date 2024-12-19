package lab8;

public class Main {
    public static void main(String[] args) {
        task1();
    }

    public static void task1(){
        System.out.println("==========TASK 1==========");

        Printable p = () -> System.out.println("Hello Functional Interfaces");
        p.print();

        System.out.println();
    }
}
