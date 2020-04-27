package DataProvider;

import Helpers.PasswordUtils.PasswordUtility;
import Models.User;


import java.sql.*;

public class DataProviderCreateNewUser extends DataProvider{

    public static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        DataProviderCreateNewUser.currentUser = currentUser;
    }




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
                ,emailAddress);

        setCurrentUser(user);
    }


    public static void registerNewUser(String firstName
            , String familyName
            , String email
            , String phone
            , String username
            , String key
            , String salt
            , String passphrase) {

        String query = "{ call insert_new_user(?,?,?,?,?,?,?,?) }";


        try (Connection conn = DataProvider.getConnection();

             CallableStatement cstmt = conn.prepareCall(query)) {

            cstmt.setString(1, firstName);
            cstmt.setString(2, familyName);
            cstmt.setString(3, email);
            cstmt.setString(4, phone);
            cstmt.setString(5, username);
            cstmt.setString(6, key);
            cstmt.setString(7, salt);
            cstmt.setString(8, passphrase);
            cstmt.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }



    public static void addUserToDataBase() throws SQLException {

        String salt = getCurrentUser().getSalt();
        String key = getCurrentUser().getPassword();
        String firstName = getCurrentUser().getFirstName();
        String familyName = getCurrentUser().getFamilyName();
        String username = getCurrentUser().getUsername();
        String phone = getCurrentUser().getPhoneNumber();
        String email = getCurrentUser().getEmailAddress();
        String passphrase = getCurrentUser().getPassPhrase();

        registerNewUser(firstName,familyName,email,phone,username,key,salt,passphrase);

    }

}
