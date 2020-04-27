package Helpers.Validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validators {


    public static boolean validateUserName(String InputName) {
        //Validates username of the waiter
        //Username must contain only letters and numbers min 2 max 30
        final String regex = "^[a-zA-Z-0-9]{2,30}$";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(InputName);
        if (matcher.find()) {
            return true;
        }
        return false;
    }


    public static boolean validateFullName(String InputName) {
        //Validates the full name of the user
        //Name must contain only letters min 2 max 25
        //Names must start with Capital letter and each new name must start with capital letter and may contain capital letter
        //Example Ivan, Ivan Ivanov Ivanov , Ivan Ivanov Ivanov Invaov , IvAn IvaNOV , IVAN IVANOV
        final String regex = "^([A-Z][a-zA-Z]{1,25}\\s{0,25})+([A-Z][a-zA-Z]{1,25}\\s{0,25})?+([A-Z][a-zA-Z]{1,25})?$";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(InputName);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    public static boolean validateSingleName(String InputName) {
        //Validates the single name of the user
        //Name must contain only letters min 2 max 25
        //Names must start with Capital letter
        //Example Ivan, Ivannnnnnnnnnnnnnnnnnnnnnn
        final String regex = "^[A-Z][a-zA-Z]{1,25}$";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(InputName);
        if (matcher.find()) {
            return true;
        }
        return false;
    }


    public static boolean validatePhoneNumber(String number) {
        //Checks  Bulgarian phoen numbers to valdiate the correct length with regular expressions
        String numberRegex = "[0-9]{10}";
        Pattern numberPat = Pattern.compile(numberRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = numberPat.matcher(number);
        return matcher.find();
    }

    public static boolean getEmailVer(String email) {
        //99% Validation of the emails with regular expressions
        String emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[x01-x08x0bx0cx0e-x1fx21x23-x5bx5d-x7f]|[x01-x09x0bx0cx0e-x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?).){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[x01-x08x0bx0cx0e-x1fx21-x5ax53-x7f]|[x01-x09x0bx0cx0e-x7f])+)])";
        Pattern emailPat = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPat.matcher(email);
        return matcher.find();
    }

}
