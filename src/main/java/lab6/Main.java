package lab6;

import lab6.exceptions.WrongLoginException;
import lab6.exceptions.WrongPasswordException;

import java.util.regex.*;

public class Main {
    public static void main(String[] args) {
        Boolean result = checkCredentials("abc", "a", "a");
        System.out.println(result);
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
}
