package lab1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task6 {
    public static List<Integer> fibs = new ArrayList<Integer>(){{
        add(1); add(1);
    }};

    public static void main(String[] args, Scanner sc) {
        System.out.print("n: ");
        int n = sc.nextInt();
        List<Integer> rsultFibs = new ArrayList<>();

        for(int i = 1; i <= n; i += 1) {
            rsultFibs.add(Fib(i));
        }

        System.out.println("Fib for n: " + Fib(n));
        System.out.println("Fibs list for n: " + rsultFibs.toString());
        System.out.println("Fibs reversed list for n: " + rsultFibs.reversed().toString());
    }

    public static int Fib(int n){
        if(fibs.size() >= n){
            return fibs.get(n - 1);
        }

        for(int i = fibs.size(); i < n; i += 1){
            fibs.add(fibs.get(i - 1) + fibs.get(i - 2));
        }

        return fibs.get(n - 1);
    }
}
