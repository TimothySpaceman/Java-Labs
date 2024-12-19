package lab9;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Info {
    String author() default "Unknown";
    String date() default "Not Set";
    int version() default 1;
}

public class Main {
    @Info(author = "Timothy", date = "19.12.2024", version = 3)
    public static void main(String[] args) throws Exception {
//        task1();
//        task2();
        task3();
    }

    public static void task1(){
        System.out.println("==========TASK 1==========");

        String employeeData = "John Doe, 30 years, Software Developer, 5 years, johndoe@example.com, +380123123123\n" +
                "Jane Smith, 28 years, Database Engineer, 4 years, janesmith@example.com, +380999999999\n" +
                "Robert Brown, 35 years, Project Manager, 10 years, robertbrown@example.com, +380111111111\n" +
                "Emily White, 25 years, Designer, 3 years, emilywhite@example.com, +380987654321\n" +
                "Michael Johnson, 40 years, HR Specialist, 15 years, michaeljohnson@example.com, +380123456789";

        String phonePattern = "\\+38\\d{10}";
        Pattern phone = Pattern.compile(phonePattern);
        Matcher phoneMatcher = phone.matcher(employeeData);
        System.out.println("Phone Numbers:");
        while (phoneMatcher.find()) {
            System.out.println(phoneMatcher.group());
        }

        String emailPattern = "[a-zA-Z0-9._]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
        Pattern email = Pattern.compile(emailPattern);
        Matcher emailMatcher = email.matcher(employeeData);
        System.out.println("\nEmail Addresses:");
        while (emailMatcher.find()) {
            System.out.println(emailMatcher.group());
        }

        String datePattern = "(\\d{2})\\.(\\d{2})\\.(\\d{4})";
        String modifiedDates = employeeData.replaceAll(datePattern, "$3-$2-$1");
        System.out.println("\nModified Data (Changed Date Format):");
        System.out.println(modifiedDates);

        String modifiedTitles = modifiedDates.replaceAll("Software Developer", "Senior Software Developer")
                .replaceAll("Database Engineer", "Data Scientist");
        System.out.println("\nModified Titles:");
        System.out.println(modifiedTitles);

        System.out.println();
    }

    public static void task2() throws Exception {
        System.out.println("==========TASK 2==========");

        Class<?> employeeClass1 = Employee.class;
        Class<?> employeeClass2 = Class.forName("lab9.Employee");
        Employee employee = new Employee();
        Class<?> employeeClass3 = employee.getClass();
        System.out.println("Class from .class: " + employeeClass1.getName());
        System.out.println("Class from forName(): " + employeeClass2.getName());
        System.out.println("Class from getClass(): " + employeeClass3.getName());
        System.out.println();

        System.out.println("Fields in Employee class:");
        Field[] fields = employeeClass1.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getType() + " " + field.getName());
        }
        System.out.println();

        System.out.println("Methods in Employee class:");
        Method[] methods = employeeClass1.getDeclaredMethods();
        for (Method method : methods) {
            System.out.print(method.getReturnType() + " " + method.getName() + "(");
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (Class<?> paramType : parameterTypes) {
                System.out.print(paramType.getName() + ", ");
            }
            System.out.println(")");
        }

        System.out.println("\nConstructors in Employee class:");
        Constructor<?>[] constructors = employeeClass1.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {

            System.out.print(constructor.getName() + "(");
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            for (Class<?> paramType : parameterTypes) {
                System.out.print(paramType.getName() + ", ");
            }
            System.out.println(")");
        }

        Constructor<?> constructor = employeeClass1.getConstructor();
        Employee newEmployee = (Employee) constructor.newInstance();
        System.out.println("\nNew Employee instance created using reflection:");
        newEmployee.displayInfo();

        Method updateAgeMethod = employeeClass1.getDeclaredMethod("updateAge", int.class);
        updateAgeMethod.invoke(newEmployee, 25);
        System.out.println("\nAfter updating age:");
        newEmployee.displayInfo();

        Field fullNameField = employeeClass1.getDeclaredField("fullName");
        fullNameField.setAccessible(true);  // Access the private field
        System.out.println("\nPrivate field 'fullName' before setting:");
        System.out.println(fullNameField.get(newEmployee));

        fullNameField.set(newEmployee, "Changed Full Name");
        System.out.println("\nPrivate field 'fullName' after setting:");
        System.out.println(fullNameField.get(newEmployee));

        Method setFullNameMethod = employeeClass1.getDeclaredMethod("setFullName", String.class);
        setFullNameMethod.setAccessible(true);
        setFullNameMethod.invoke(newEmployee, "Changed Full Name by Method");
        System.out.println("\nAfter invoking private method:");
        newEmployee.displayInfo();

        System.out.println();
    }

    @Info(author = "Timothy", date = "19.12.2024", version = 1)
    public static void task3() {
        System.out.println("==========TASK 3==========");

        Arrays.stream(Main.class.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Info.class))
                .forEach(method -> {
                    Info info = method.getAnnotation(Info.class);

                    System.out.println("Method: " + method.getName());
                    System.out.println("Author: " + info.author());
                    System.out.println("Date: " + info.date());
                    System.out.println("Version: " + info.version());
                    System.out.println();
                });

        System.out.println();
    }
}
