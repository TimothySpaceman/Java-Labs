package lab1;

import java.util.Scanner;

public class Task2 {
    public static void main(String[] args, Scanner sc) {
        System.out.print("a: ");
        int a = sc.nextInt();
        System.out.print("b: ");
        int b = sc.nextInt();

        System.out.println("a + b = " + Sum(a, b));
    }

    public static int Sum(int a, int b) {
        return a + b;
    }
}
