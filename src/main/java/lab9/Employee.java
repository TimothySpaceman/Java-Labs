package lab9;

public class Employee {
    public int age;
    private String fullName;

    public Employee() {
        this.age = 21;
        this.fullName = "Empty Name";
    }

    public Employee(int age, String fullName) {
        this.age = age;
        this.fullName = fullName;
    }

    public void displayInfo() {
        System.out.println(fullName + " (" + age + ")");
    }

    public void updateAge(int age) {
        this.age = age;
    }

    private void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
