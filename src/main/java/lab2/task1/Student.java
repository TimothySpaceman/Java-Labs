package lab2.task1;

public class Student extends Person {
    private static int instanceCount = 0;

    public static void showInstanceCount(){
        System.out.println("Teacher instance count: " + instanceCount);
    };

    private int course;
    private String specialty;
    private String university;

    public Student(int course, String specialty, String university) {
        this.course = course;
        this.specialty = specialty;
        this.university = university;
        instanceCount += 1;
    }

    public Student(int age, String firstName, String lastName, Gender gender, Location location, int course, String specialty, String university) {
        super(age, firstName, lastName, gender, location);
        this.course = course;
        this.specialty = specialty;
        this.university = university;
        instanceCount += 1;
    }

    @Override
    public void getOccupation() {
        System.out.println("Occupation: studying");
    }

    @Override
    public String getFullInfo(){
        StringBuilder sb = new StringBuilder(super.getFullInfo());
        sb.append(", studying at " + university);
        sb.append(", on " + specialty + " speciality");
        sb.append(", " + course + " course");
        return sb.toString();
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
}
