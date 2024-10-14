package lab2.task1;

public class Teacher extends Person {
    private static int instanceCount = 0;

    public static void showInstanceCount(){
        System.out.println("Teacher instance count: " + instanceCount);
    };

    private Car car;
    private String subject;
    private String university;

    public Teacher(Car car, String subject, String university) {
        this.car = car;
        this.subject = subject;
        this.university = university;
        instanceCount += 1;
    }

    public Teacher(int age, String firstName, String lastName, Gender gender, Location location, Car car, String subject, String university) {
        super(age, firstName, lastName, gender, location);
        this.car = car;
        this.subject = subject;
        this.university = university;
        instanceCount += 1;
    }

    @Override
    public void getOccupation() {
        System.out.println("Occupation: teaching");
    }

    @Override
    public String getFullInfo(){
        StringBuilder sb = new StringBuilder(super.getFullInfo());
        sb.append(", works in " + university);
        sb.append(", teaches " + subject);
        sb.append(", drives " + car.getBrand());
        return sb.toString();
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
}
