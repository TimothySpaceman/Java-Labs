package lab7;

import java.util.Scanner;

public class Reader implements Runnable {
    private final MessageBuffer messageBuffer;

    public Reader(MessageBuffer messageBuffer) {
        this.messageBuffer = messageBuffer;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter your message (or \"exit\" to quit): ");
            String input = scanner.nextLine();
            messageBuffer.writeMessage(input);
            if (input.toLowerCase().equals("exit")) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("An error occurred in Reader");
                System.out.println(e.getMessage());
                return;
            }
        }
    }
}