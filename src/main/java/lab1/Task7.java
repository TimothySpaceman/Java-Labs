package lab1;

public class Task7 {
    public static void main(String[] args) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < alphabet.length(); i += 1) {
            int code = (int)alphabet.charAt(i);
            System.out.println(alphabet.charAt(i) + " ==> " + code);
        }
    }
}
