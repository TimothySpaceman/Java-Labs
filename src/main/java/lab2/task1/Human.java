package lab2.task1;

public interface Human {
    void sayAge();
    void sayGender();
    void sayLocation();
    void sayName();
    default void whoIAm(){
        System.out.println("I'm a human");
    };
    String getFullInfo();
}
