package lab2.task1;

import java.util.Arrays;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        Bank mono = new Bank("Mono");

        Car AM5203AA = new Car("VolksWagen");
        Teacher mrWise = new Teacher(
                37,
                "John",
                "Wise",
                Gender.MALE,
                Location.Zhytomyr,
                AM5203AA,
                "Docker",
                "ZTU"
        );
        mono.openAccount(mrWise);

        Student max = new Student(
                20,
                "Max",
                "Adamson",
                Gender.MALE,
                Location.Zhytomyr,
                3,
                "Software Engineering",
                "ZTU"
        );
        mono.openAccount(max);
        Student emily = new Student(
                19,
                "Emily",
                "Roberts",
                Gender.FEMALE,
                Location.Zhytomyr,
                3,
                "Software Engineering",
                "ZTU"
        );
        mono.openAccount(emily);
        Student sam = new Student(
                19,
                "Samuel",
                "Price",
                Gender.MALE,
                Location.Zhytomyr,
                3,
                "Software Engineering",
                "ZTU"
        );
        mono.openAccount(sam);

        Car AM8639AB = new Car("Volvo");
        Employee employedDude = new Employee(
                27,
                "Dude",
                "Dudson",
                Gender.MALE,
                Location.Zhytomyr,
                AM8639AB,
                "Employed Dudes inc.",
                "Worker"
        );
        mono.openAccount(employedDude);
        mono.deposit(employedDude, 25350.00);

        System.out.println("Society:");
        System.out.println(mrWise.getFullInfo());
        mrWise.getOccupation();
        System.out.println(max.getFullInfo());
        max.getOccupation();
        System.out.println(emily.getFullInfo());
        emily.getOccupation();
        System.out.println(sam.getFullInfo());
        sam.getOccupation();
        System.out.println(employedDude.getFullInfo());
        employedDude.getOccupation();
        System.out.println("");

        mono.transfer(employedDude, sam, 180.00);
        System.out.println("");
        checkInstance(mrWise, Teacher.class);
        checkInstance(mrWise, Person.class);
        checkInstance(mrWise, Human.class);
        checkInstance(mrWise, Student.class);

        System.out.println("");
        System.out.println("Available locations: " + Arrays.toString(Location.values()).replace("[", "").replace("]", ""));
    }

    public static void checkInstance(Object instance, Class<?> of)
    {
        String result = isInstanceOf(instance, of) ? "is" : "is not";
        System.out.println(instance + " " + result + " instance of " + of.getSimpleName());
    }

    public static boolean isInstanceOf(Object instance, Class<?> of){
        return of.isInstance(instance);
    }
}
