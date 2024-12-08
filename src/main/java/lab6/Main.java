package lab6;

import lab4.Cart;
import lab4.CartItem;
import lab6.exceptions.WrongLoginException;
import lab6.exceptions.WrongPasswordException;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Locale;
import java.util.regex.*;

public class Main {
    public static void main(String[] args) {
//        task1();
//        task2();
//        task3();
        task4();
    }

    public static void task1(){
        System.out.println("==========TASK 1==========");
        String login = "abc";
        String password = "abc";
        String confirmPassword = "abc";
        Boolean result = checkCredentials(login, password, confirmPassword);
        System.out.println("Login: " + login);
        System.out.println("Password: " + password);
        System.out.println("Confirm Password: " + confirmPassword);
        System.out.println("Result: " + result);
        System.out.println();
    }

    public static boolean checkCredentials(String login, String password, String checkPassword) {
        try {
            throwIfLongerThan(login, 20, new WrongLoginException("Login must be shorter than 20 characters."));
            throwIfNoRegex(login, "^[a-zA-Z0-9_]+$", new WrongLoginException("Login must contain only latin letters, digits and underscores."));

            throwIfLongerThan(password, 20, new WrongPasswordException("Password must be shorter than 20 characters."));
            throwIfNoRegex(password, "^[a-zA-Z0-9_]+$", new WrongPasswordException("Password must contain only latin letters, digits and underscores."));

            if(password != checkPassword) {
                throw new WrongPasswordException("Password and it's confirmation must be identical.");
            }
        } catch (WrongLoginException | WrongPasswordException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    public static void throwIfLongerThan(String value, Integer maxLength, Exception exception) throws Exception {
        if(value.length() >= 20){
            throw exception;
        }
    }

    public static void throwIfNoRegex(String value, String regex, Exception exception) throws Exception {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        matcher.find();
        if(!matcher.hasMatch()){
            throw exception;
        }
    }

    public static void task2(){
        System.out.println("==========TASK 2==========");
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
        writeCartToFile(cart);
        readCartFromFile();
        System.out.println();
    }

    public static void writeCartToFile(Cart cart) {
        try {
            FileWriter fw = new FileWriter("report.cart");
            fw.write(cart.toFormattedString(new Locale("en", "US")));
            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void readCartFromFile() {
        try {
            FileReader fr = new FileReader("report.cart");
            StringBuilder builder = new StringBuilder();
            int nextChar;
            while ((nextChar = fr.read()) != -1) {
                builder.append((char) nextChar);
            }
            String output = builder.toString();
            System.out.println("DATA FROM report.cart FILE:");
            System.out.println(output);
            fr.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void task3(){
        System.out.println("==========TASK 3==========");
        CopyPaster cp = new CopyPaster("sticker.webp");
        cp.copyTo("sticker-copy.webp");
        System.out.println();
    }

    public static void task4(){
        System.out.println("==========TASK 4==========");

        try {
            RandomAccessFile r = new RandomAccessFile("report.cart", "rw");
            r.seek(0);
            r.writeBytes("START\n");
            r.seek(r.length() / 2);
            r.writeBytes("MIDDLE");
            r.seek(r.length());
            r.writeBytes("\nTHE END");
            r.close();
            System.out.println("Modified report.cart");
        } catch (IOException e) {
            e.getMessage();
        }
        System.out.println();
    }
}
