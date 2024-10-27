package lab3.game;

import java.util.Comparator;

public abstract class Participant implements Cloneable, Comparable<Participant>{
    public static Comparator<Participant> ageComparator = new Comparator<>() {
        @Override
        public int compare(Participant p1, Participant p2) {
            return p1.getAge() - p2.getAge();
        }
    };

    private String name;
    private int age;

    public Participant(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public Participant clone() {
        try {
            Participant clone = (Participant) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return "Participant [name=" + name + ", age=" + age + "]";
    }

//    @Override
//    public int hashCode() {
    public int customHashCode() {
        return name.hashCode() * getAge();
    }

    public boolean equals(Participant participant) {
        return participant.toString().equals(toString());
    }

    @Override
    public int compareTo(Participant participant) {
        return name.compareTo(participant.getName());
    }
}