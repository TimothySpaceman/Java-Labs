package lab7;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Numbers in [0, 10000] that dividable by 10 without remainder:");
        for (int i = 0; i < 10000; i += 1) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println();
                System.out.println("Calculating finished!");
                return;
            }
            if(i % 10 == 0){
                System.out.print(i);
            }
        }
        System.out.println();
    }
}
