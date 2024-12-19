package lab7;

public class Printer implements Runnable {
    private final MessageBuffer messageBuffer;

    public Printer(MessageBuffer messageBuffer) {
        this.messageBuffer = messageBuffer;
    }

    @Override
    public void run() {
        while (true) {
            String message = messageBuffer.readMessage();
            if (message.toLowerCase().equals("exit")) {
                break;
            }
            System.out.println("Message received: " + message);
        }
    }
}