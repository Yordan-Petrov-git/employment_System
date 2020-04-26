package DataProvider;

import Models.User;

public class DataProviderCreateNewUser {

    public static void initializeNewUser(String username, String password, String salt
            , String passPhrase, String firstName, String familyName, String phoneNumber
            , String emailAddress, String accountType, String picture) {

        User user = new User(username, password, salt, passPhrase, firstName, familyName, phoneNumber
                , emailAddress, accountType, picture);
    }

    public static void initializeNewUserNoPicture(String username, String password, String salt
            , String passPhrase, String firstName, String familyName, String phoneNumber
            , String emailAddress, String accountType) {

        User user = new User(username, password, salt, passPhrase, firstName, familyName, phoneNumber
                , emailAddress, accountType);
    }

    public static void initializeNewUserNoAccountType(String username, String password, String salt
            , String passPhrase, String firstName, String familyName, String phoneNumber
            , String emailAddress) {

        User user = new User(username, password, salt, passPhrase, firstName, familyName, phoneNumber
                , emailAddress);
    }
}
