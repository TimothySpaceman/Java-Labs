package lab7;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        // DO NOT RUN MULTIPLE TASKS
//        task1();
//        task2();
//        task3();
//        task4();
//        task5();
        task6();
    }

    public static void task1(){
        System.out.println("==========TASK 1==========");

        MyThread t1 = new MyThread();
        t1.printState();
        t1.start();
        t1.printState();
        System.out.println("Is Alive: " + t1.isAlive());
        System.out.println("Name: " + t1.getName());
        System.out.println("Priority: " + t1.getPriority());
        System.out.println("Is Daemon: " + t1.isDaemon());
        System.out.println();
        t1.printState();

        t1.setName("FirstThreadEver");
        t1.setPriority(2);

        System.out.println("Name: " + t1.getName());
        System.out.println("Priority: " + t1.getPriority());
        System.out.println();

        try {
            t1.join();
        } catch (InterruptedException e) {
            System.out.println("An error occurred while waiting for join");
            System.out.println(e.getMessage());
        }

        Thread m = Thread.currentThread();
        System.out.println("Main thread name: " + m.getName());
        System.out.println("Main thread priority: " + m.getPriority());

        System.out.println();
    }

    public static void task2(){
        System.out.println("==========TASK 2==========");

        Thread t1 = new Thread(new MyRunnable());
        Thread t2 = new Thread(new MyRunnable());
        Thread t3 = new Thread(new MyRunnable());

        t1.start();
        t2.start();
        t3.start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            System.out.println("An error occurred while trying to sleep");
            System.out.println(e.getMessage());
        }

        t1.interrupt();
        t2.interrupt();
        t3.interrupt();

        System.out.println();
    }

    public static void task3(){
        System.out.println("==========TASK 3==========");

        Thread t1 = new Thread(new MyRunnableArithmetic());
        Thread t2 = new Thread(new MyRunnableArithmetic());
        Thread t3 = new Thread(new MyRunnableArithmetic());

        t1.start();
        t2.start();
        t3.start();

        System.out.println();
    }

    public static void task4(){
        System.out.println("==========TASK 4==========");

        Thread t1 = new Thread(new MyRunnableArithmetic2());
        Thread t2 = new Thread(new MyRunnableArithmetic2());
        Thread t3 = new Thread(new MyRunnableArithmetic2());

        t1.start();
        t2.start();
        t3.start();

        System.out.println();
    }

    public static void task5(){
        System.out.println("==========TASK 5==========");

        MessageBuffer mb = new MessageBuffer();
        Thread r = new Thread(new Reader(mb));
        Thread p = new Thread(new Printer(mb));

        r.start();
        p.start();

        System.out.println();
    }

    public static void task6(){
        System.out.println("==========TASK 6==========");

        int[] arr = new int[1000000];
        // Change to this if single thread is fast enough on your machine:
//        int[] arr = new int[1000000000];
        Random r = new Random();

        for (int i = 0; i < arr.length; i += 1) {
            arr[i] = r.nextInt(1000);
        }

        long startTime, endTime;

        startTime = System.currentTimeMillis();
        int singleThreadSum = singleThreadSum(arr);
        endTime = System.currentTimeMillis();
        System.out.println("Single thread sum: " + singleThreadSum);
        System.out.println("Single thread time: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        int multiThreadSum = multiThreadSum(arr);
        endTime = System.currentTimeMillis();
        System.out.println("Multi thread sum: " + multiThreadSum);
        System.out.println("Multi thread time: " + (endTime - startTime) + " ms");

        System.out.println();
    }

    public static int sumOfDigits(int number) {
        int sum = 0;
        while (number != 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    public static int singleThreadSum(int[] arr){
        int sum = 0;
        for (int i = 0; i < arr.length; i += 1) {
            sum += sumOfDigits(arr[i]);
        }
        return sum;
    }

    public static int multiThreadSum(int[] arr){
        class SumCallable implements Callable<Integer> {
            private final int[] array;
            private final int start;
            private final int end;

            public SumCallable(int[] array, int start, int end) {
                this.array = array;
                this.start = start;
                this.end = end;
            }

            @Override
            public Integer call() {
                int sum = 0;
                for (int i = start; i < end; i += 1) {
                    sum += sumOfDigits(array[i]);
                }
                return sum;
            }
        }

        int threadsCount = 5;
        ExecutorService es = Executors.newFixedThreadPool(threadsCount);

        int chunkSize = arr.length / threadsCount;
        Future<Integer>[] results = new Future[threadsCount];

        for(int i = 0; i < threadsCount; i += 1){
            int start = i * chunkSize;
            int end = (i == threadsCount - 1) ? arr.length : start + chunkSize;
            results[i] = es.submit(new SumCallable(arr, start, end));
        }

        int sum = 0;
        for (int i = 0; i < results.length; i += 1) {
            try {
                sum += results[i].get();
            } catch (Exception e) {
                System.out.println("An error occurred while trying to get sum");
                System.out.println(e.getMessage());
            }
        }
        es.shutdown();
        return sum;
    }
}
