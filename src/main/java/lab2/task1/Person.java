package lab2.task1;

public abstract class Person implements Human {
    protected int age;
    protected String firstName;
    protected String lastName;
    protected Gender gender;
    protected Location location;

    {
        age = 0;
        firstName = "NoName";
        lastName = "NoName";
        gender = Gender.MALE;
        location = Location.Zhytomyr;
    }

    public Person() {
    }

    public Person(int age, String firstName, String lastName, Gender gender, Location location) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.location = location;
    }

    public abstract void getOccupation();

    public void sayAge() {
        System.out.println("Age: " + age);
    }

    public void sayGender() {
        System.out.println("Gender: " + gender);
    }

    public void sayLocation() {
        System.out.println("Location: " + location);
    }

    public void sayName() {
        System.out.println("Name: " + firstName + " " + lastName);
    }

    @Override
    public void whoIAm() {
        System.out.println("I'm a person");
    }

    @Override
    public String getFullInfo() {
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + ": ");
        sb.append(firstName + " " + lastName);
        sb.append(", " + gender);
        sb.append(", " + age + " y.o.");
        sb.append(", lives in " + location);
        return sb.toString();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String toString(){
        return getFullName();
    }
}
