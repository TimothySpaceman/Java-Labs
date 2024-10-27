package lab3.game;

public class Schoolar extends Participant{
    public Schoolar(String name, int age) {
        super(name, age);
    }

    @Override
    public String toString() {
        return "Schoolar [name=" + getName() + ", age=" + getAge() + "]";
    }
}
