package lab7;

public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("I love coding!");
        for (int i = 0; i < 100; i += 1) {
            System.out.print(i % 10);
        }
        System.out.println();
    }

    public void printState(){
        System.out.println("Thread " + this.getName() + " state: " + this.getState());
    }
}
