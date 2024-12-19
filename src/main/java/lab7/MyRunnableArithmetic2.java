package lab7;

public class MyRunnableArithmetic2 implements Runnable {
    private static int result = 1;

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            synchronized (MyRunnableArithmetic2.class) {
                if(result > 100){
                    break;
                }
                System.out.print(result + " ");
                result += 1;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println("An error occurred while trying to sleep in MyRunnableArithmetic2");
                System.out.println(e.getMessage());
            }
        }
    }
}
