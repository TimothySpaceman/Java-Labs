package lab3;

import lab3.game.Participant;
import lab3.game.Schoolar;
import lab3.game.Student;
import lab3.game.Team;

import java.util.Collections;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Student student1 = new Student("Student 1", 20);
        Student student2 = new Student("Student 2", 18);
        Student student3 = new Student("Student 3", 19);
        Student student4 = new Student("Student 4", 19);
        Team<Student> studentTeam1 = new Team<>("Student team 1");
        Team<Student> studentTeam2 = new Team<>("Student team 2");
        Schoolar schoolar1 = new Schoolar("Schoolar 1", 15);
        Schoolar schoolar2 = new Schoolar("Schoolar 2", 16);
        Schoolar schoolar3 = new Schoolar("Schoolar 3", 14);
        Schoolar schoolar4 = new Schoolar("Schoolar 4", 15);
        Team<Schoolar> schoolarTeam1 = new Team<>("Schoolar team 1");
        Team<Schoolar> schoolarTeam2 = new Team<>("Schoolar team 2");

        System.out.println("==========TASK 1==========");
        studentTeam1.addNewParticipant(student1);
        studentTeam1.addNewParticipant(student2);
        studentTeam2.addNewParticipant(student3);
        studentTeam2.addNewParticipant(student4);
        schoolarTeam1.addNewParticipant(schoolar1);
        schoolarTeam1.addNewParticipant(schoolar2);
        schoolarTeam2.addNewParticipant(schoolar3);
        schoolarTeam2.addNewParticipant(schoolar4);
        // studentTeam1.addNewParticipant(schoolar1); - Will show type error

        studentTeam1.playWith(studentTeam2);
        // studentTeam1.playWith(schoolarTeam1); - Will show type error
        System.out.println();

        System.out.println("==========TASK 2==========");
        Student student1Clone = (Student)student1.clone();
        System.out.println(student1);
        System.out.println(student1Clone);
        System.out.println(student1.hashCode());
        System.out.println(student1Clone.hashCode());
        System.out.println(student1.customHashCode());
        System.out.println(student1Clone.customHashCode());
        System.out.println(student1.equals(student1Clone));
        System.out.println();

        Team<Student> studentTeam1Clone = new Team<>(studentTeam1);
        System.out.println(studentTeam1);
        System.out.println(studentTeam1Clone);
        System.out.println(studentTeam1.getName());
        System.out.println(studentTeam1Clone.getName());
        System.out.println(studentTeam1.getParticipants());
        System.out.println(studentTeam1Clone.getParticipants());
        System.out.println(studentTeam1.getParticipants().getFirst().hashCode());
        System.out.println(studentTeam1Clone.getParticipants().getFirst().hashCode());
        System.out.println(studentTeam1.getParticipants().getFirst().customHashCode());
        System.out.println(studentTeam1Clone.getParticipants().getFirst().customHashCode());
        studentTeam1Clone.getParticipants().getFirst().setName("Cloned with Team!");
        System.out.println(studentTeam1.getParticipants());
        System.out.println(studentTeam1Clone.getParticipants());
        System.out.println(studentTeam1.getParticipants().getFirst().customHashCode());
        System.out.println(studentTeam1Clone.getParticipants().getFirst().customHashCode());

        System.out.println();

        System.out.println("==========TASK 3==========");
        studentTeam1.addNewParticipant(student4);
        studentTeam1.addNewParticipant(student3);
        System.out.println(studentTeam1.getParticipants());
        Collections.sort(studentTeam1.getParticipants());
        System.out.println(studentTeam1.getParticipants());
        studentTeam1.getParticipants().sort(Participant.ageComparator);
        System.out.println(studentTeam1.getParticipants());
        studentTeam1.getParticipants().sort(Comparator.comparing(Participant::getName).thenComparing(Participant::getAge));
        System.out.println(studentTeam1.getParticipants());
    }
}
