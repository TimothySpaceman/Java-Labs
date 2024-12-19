package lab9;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        task1();
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
}
