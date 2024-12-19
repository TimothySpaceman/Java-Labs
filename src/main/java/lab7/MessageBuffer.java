package lab7;

public class MessageBuffer {
    private String message = "";
    private boolean hasMessage = false;

    public synchronized void writeMessage(String message) {
        while (hasMessage) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("An error occurred while writing the message");
                System.out.println(e.getMessage());
                return;
            }
        }
        this.message = message;
        hasMessage = true;
        notifyAll();
    }

    public synchronized String readMessage() {
        while (!hasMessage) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("An error occurred while reading the message");
                System.out.println(e.getMessage());
                return null;
            }
        }
        hasMessage = false;
        notifyAll();
        return message;
    }
}
