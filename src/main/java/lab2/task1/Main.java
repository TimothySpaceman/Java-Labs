package lab2.task1;

public class Main {
    public static void main(String[] args) {
        Teacher t = new Teacher(new Car("Nissan"), "John", "Doe");
        System.out.println(t.getFullInfo());
    }
}
