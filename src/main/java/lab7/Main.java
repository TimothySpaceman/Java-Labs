package lab7;

public class Main {
    public static void main(String[] args) {
        task1();
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
        }

        Thread m = Thread.currentThread();
        System.out.println("Main thread name: " + m.getName());
        System.out.println("Main thread priority: " + m.getPriority());
        System.out.println();
    }
}
