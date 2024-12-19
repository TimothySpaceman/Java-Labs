package lab7;

public class Main {
    public static void main(String[] args) {
        // DO NOT RUN MULTIPLE TASKS
//        task1();
//        task2();
//        task3();
//        task4();
        task5();
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
}
