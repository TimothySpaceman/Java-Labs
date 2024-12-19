package lab7;

public class MyRunnableArithmetic implements Runnable {
    private static int result = 1;

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(!printAndNext()){
                break;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println("An error occurred while trying to sleep in MyRunnableArithmetic");
                System.out.println(e.getMessage());
            }

        }
    }

    public static synchronized boolean printAndNext(){
        if(result > 100){
            return false;
        }
        System.out.print(result + " ");
        result += 1;
        return true;
    }
}
