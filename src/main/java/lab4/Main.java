package lab4;

public class Main {
    public static void main(String[] args) {
        System.out.println("==========TASK 1==========");
        ProcessString("I learn Java!!!");
        System.out.println();
    }

    public static void ProcessString(String str){
        System.out.println("Last char: " + str.substring(str.length() - 1));
        System.out.println("Ends with \"!!!\": " + str.endsWith("!!!"));
        System.out.println("Starts with \"I learn\": " + str.startsWith("I learn"));
        System.out.println("Contains \"Java\": " + str.contains("Java"));
        System.out.println("Index of \"Java\": " + str.indexOf("Java"));
        System.out.println("Replaced \"a\" with \"o\": " + str.replace('a', 'o'));
        System.out.println("Uppercase: " + str.toUpperCase());
        System.out.println("Lowercase: " + str.toLowerCase());
        System.out.println("Without \"Java\": " + str.replace("Java", ""));
    }
}
