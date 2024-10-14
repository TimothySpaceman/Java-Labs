package lab2.task1;

public class Employee extends Person {
    private static int instanceCount = 0;

    public static void showInstanceCount(){
        System.out.println("Teacher instance count: " + instanceCount);
    };

    private Car car;
    private String company;
    private String position;

    public Employee(Car car, String company, String position) {
        this.car = car;
        this.company = company;
        this.position = position;
        instanceCount += 1;
    }

    public Employee(int age, String firstName, String lastName, Gender gender, Location location, Car car, String company, String position) {
        super(age, firstName, lastName, gender, location);
        this.car = car;
        this.company = company;
        this.position = position;
        instanceCount += 1;
    }

    @Override
    public void getOccupation() {
        System.out.println("Occupation: working");
    }

    @Override
    public String getFullInfo(){
        StringBuilder sb = new StringBuilder(super.getFullInfo());
        sb.append(", works in " + company + " as " + position);
        sb.append(", drives " + car.getBrand());
        return sb.toString();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
