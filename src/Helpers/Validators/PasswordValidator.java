package Helpers.Validators;

public class PasswordValidator {

    public static boolean validation_Password(final String passwordArg) {
        //Regular expression to validate password returns bool
        boolean result = false;
        try {
            if (passwordArg != null) {
                //_________________________
                //Parameteres
                final String passwordMinChars = "8";
                final String passwordMaxChars = "64";
                final boolean isSpecialCharacterNeeded = true;

                //_________________________
                //Modules
                final String oneDigit = "(?=.*[0-9])";  //(?=.*[0-9]) a digit must occur at least once
                final String lowerCase = "(?=.*[a-z])";  //(?=.*[a-z]) a lower case letter must occur at least once
                final String upperCase = "(?=.*[A-Z])";  //(?=.*[A-Z]) an upper case letter must occur at least once
                final String noSpace = "(?=\\S+$)";  //(?=\\S+$) no whitespace allowed in the entire string
                //final String minChar = ".{" + passwordMinChars + ",}";  //.{8,} at least 8 characters
                final String minMaxChar = ".{" + passwordMinChars + "," + passwordMaxChars + "}";  //.{5,10} represents minimum of 8 characters and maximum of 64 characters

                final String SPECIAL_CHAR;
                if (isSpecialCharacterNeeded == true) {
                    SPECIAL_CHAR = "(?=.*[@#$%^&+=])"; //(?=.*[@#$%^&+=]) a special character must occur at least once
                } else {
                    SPECIAL_CHAR = "";
                }
                //_________________________
                //Pattern
                //String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
                final String pattern = oneDigit + lowerCase + upperCase + SPECIAL_CHAR + noSpace + minMaxChar;
                //_________________________
                result = passwordArg.matches(pattern);
                //_________________________
            }

        } catch (Exception ex) {
            result = false;
        }

        return result;
    }

}

