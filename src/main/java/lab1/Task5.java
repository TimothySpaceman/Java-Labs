package lab1;

import java.util.Scanner;

public class Task5 {
    public static void main(String[] args, Scanner sc) {
        System.out.print("n: ");
        int n = sc.nextInt();

        System.out.println("Digits sum for n: " + GetDigitsSum(n));
    }

    public static int GetDigitsSum(int a) {
        int result = 0;
        while (a > 9) {
            result += a % 10;
            a = a / 10;
        }
        return result + a;
    }
}
