package lab1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("==========TASK 1==========");
        Task1.main(new String[]{});
        System.out.println();

        System.out.println("==========TASK 2==========");
        Task2.main(new String[]{}, sc);
        System.out.println();

        System.out.println("==========TASK 3==========");
        Task3.main(new String[]{"1", "2", "3", "4", "5"});
        System.out.println();

        System.out.println("==========TASK 4==========");
        Task4.main(new String[]{}, sc);
        System.out.println();

        System.out.println("==========TASK 5==========");
        Task5.main(new String[]{}, sc);
        System.out.println();

        System.out.println("==========TASK 6==========");
        Task6.main(new String[]{}, sc);
        System.out.println();

        System.out.println("==========TASK 7==========");
        Task7.main(new String[]{});
        System.out.println();

        sc.close();
    }
}
