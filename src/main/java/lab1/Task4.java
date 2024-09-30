package lab1;

import java.util.Scanner;

public class Task4 {
    public static void main(String[] args, Scanner sc) {
        System.out.print("a: ");
        int a = sc.nextInt();
        System.out.print("b: ");
        int b = sc.nextInt();

        System.out.println("MCM for a and b: " + GetMCM(a, b));
    }

    private static int GetMCM(int a, int b) {
        while (a != 0 && b != 0) {
            if (a > b) {
                a = a % b;
            } else {
                b = b % a;
            }
        }
        return a + b;
    }
}
