package lab7;

public class MyRunnableArithmetic implements Runnable {
    private static int result = 1;

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            next();
            System.out.print(result + " ");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println("An error occurred while trying to sleep in MyRunnableArithmetic");
                System.out.println(e.getMessage());
            }
            if(result >= 100){
                break;
            }
        }
    }

    public static synchronized void next(){
        result += 1;
    }
}
