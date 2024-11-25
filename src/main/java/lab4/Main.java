package lab4;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        System.out.println("==========TASK 1==========");
        ProcessString("I learn Java!!!");
        System.out.println();

        System.out.println("==========TASK 2==========");
        BuildString(4, 36);
        System.out.println();

        System.out.println("==========TASK 3==========");
        Cart cart = new Cart(new Date());
        cart.add(new CartItem("Zoom Call Background of a Clean Room", "Home Office Essentials", 299.99))
                .add(new CartItem("Coffee IV Drip", "Stay Awake Gear", 799.49))
                .add(new CartItem("Pet Translator for Video Calls", "Pet Management", 499.90))
                .add(new CartItem("Noise-Canceling Headphones for Kids' Screams", "Noise Control", 1099.75))
                .add(new CartItem("Professional Pajama Set", "Work-From-Bed Apparel", 89.99))
                .add(new CartItem("Snack Delivery Robot", "Desk Snacks", 4999.00))
                .add(new CartItem("Auto-Mute Button for Embarrassing Sounds", "Meeting Savers", 199.99))
                .add(new CartItem("Desk Plant that Never Dies", "Mood Boosters", 149.50))
                .add(new CartItem("Motivational Poster: 'You're Still on Mute!'", "Office Decor", 19.99))
                .add(new CartItem("Virtual High-Five Machine", "Team Bonding", 99.49));
        System.out.println(cart.toFormattedString(new Locale("uk", "UA")));
        System.out.println();
        System.out.println(cart.toFormattedString(new Locale("en", "US")));
        System.out.println();
        System.out.println(cart.toFormattedString(new Locale("es", "ES")));
        System.out.println();

        System.out.println("==========TASK 2==========");
        PlayWithDates();
        System.out.println();
    }

    public static void ProcessString(String str) {
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

    public static void BuildString(int a, int b) {
        StringBuilder sb = new StringBuilder();

        sb.append(a).append(" + ").append(b).append(" = ").append(a + b).append("\n");
        sb.append(a).append(" - ").append(b).append(" = ").append(a - b).append("\n");
        sb.append(a).append(" * ").append(b).append(" = ").append(a * b);
        System.out.println(sb.toString() + "\n");

        sb.deleteCharAt(7).insert(7, "equals");
        System.out.println(sb.toString() + "\n");

        sb.replace(24, 25, "equals");
        System.out.println(sb.toString() + "\n");

        sb.reverse();
        System.out.println(sb.toString() + "\n");

        System.out.println("Length: " + sb.length());
        System.out.println("Capacity: " + sb.capacity());
    }

    public static void PlayWithDates(){
        GregorianCalendar calendar = new GregorianCalendar(2024, Calendar.NOVEMBER, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 45);
        System.out.println("Lab Start");
        System.out.println("Day of week: " + calendar.get(Calendar.DAY_OF_WEEK));
        System.out.println("Day of year: " + calendar.get(Calendar.DAY_OF_YEAR));
        System.out.println("Month: " + calendar.get(Calendar.MONTH) + 1);
        System.out.println("Year: " + calendar.get(Calendar.YEAR));
        System.out.println("Hours: " + calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println("Minutes: " + calendar.get(Calendar.MINUTE));
        System.out.println("Seconds: " + calendar.get(Calendar.SECOND));
        System.out.println("Leap year: " + calendar.isLeapYear(calendar.get(Calendar.YEAR)));
        System.out.println();

        System.out.println("Current Moment");
        LocalDate now = LocalDate.ofInstant((new Date()).toInstant(), ZoneId.systemDefault());
        LocalDate labStart = LocalDate.ofInstant((new Date(calendar.getTimeInMillis())).toInstant(), ZoneId.systemDefault());
        System.out.println("Before: " + now.isBefore(labStart));
        System.out.println("After: " + now.isAfter(labStart));
    }
}
