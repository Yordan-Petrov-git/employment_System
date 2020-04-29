package DataProvider;

import Helpers.PasswordUtils.PasswordUtility;
import Models.User;
import Views.MainFrame;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.sql.*;

public class LoginDataProvider extends DataProvider {

    public User loggedInUser;

    public static Boolean logonUser(String username, String password) {
        //
        String query = "{ call select_user_by_username(?) }";
        ResultSet resultSet;

        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall(query)) {

            stmt.setString(1, username);

            resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                String usernameUser = resultSet.getString("username_user");
                String key = resultSet.getString("password_user");
                String salt = resultSet.getString("password_salt_user");
               // String compId = resultSet.getString("user_id");

                if (PasswordUtility.verifyPassword(password, key, salt) && username.equals(usernameUser)) {//If Login information is correct
                    //Log in the user if credentials are correct

                    JOptionPane.showMessageDialog(null, "success", "login success", 2);

                    username = null;
                    key = null;
                    salt = null;

                    return true;

                } else {//If Login information is not correct

                    //Error message
                    JOptionPane.showMessageDialog(null, "Invalid Username / Password", "Login Error", 2);
                   return false;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }


    public static Boolean logonCompany(String username, String password) {
        //
        String query = "{ call select_company_by_username(?) }";
        ResultSet resultSet;

        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall(query)) {

            stmt.setString(1, username);

            resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                String usernameCompany = resultSet.getString("username_company");
                String key = resultSet.getString("password_company");
                String salt = resultSet.getString("password_salt_company");
               //  String compId = resultSet.getString("company_id");

                if (PasswordUtility.verifyPassword(password, key, salt) && username.equals(usernameCompany)) {//If Login information is correct
                    //Log in the user if credentials are correct


                    JOptionPane.showMessageDialog(null, "success", "login success", 2);

                    username = null;
                    key = null;
                    salt = null;
                    // userId = null;
                    return  true;
                } else {//If Login information is not correct
                    //Error message
                    JOptionPane.showMessageDialog(null, "Invalid Username / Password", "Login Error", 2);
                    return false;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }



    public static void getUserInfo(String username) {
        //
        String query = "{ call select_all_user_info_by_usernamr(?) }";
        ResultSet resultSet;

        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall(query)) {

            stmt.setString(1, username);

            resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                String compId = resultSet.getString("user_id");
                String usernameUser = resultSet.getString("username_user");
                String fName = resultSet.getString("first_name_user");
                String lName = resultSet.getString("family_name_user");
                String phone = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static boolean doesUsernameUserExists(String username) {
        //
        String query = "{ call select_all_user_info_by_usernamr(?) }";
        ResultSet resultSet;

        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall(query)) {

            stmt.setString(1, username);

            resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                //if username is found
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        //if username is not found
      return false;
    }



    public static boolean doesUsernameCompanyExists(String usernameCompany) {
        //
        String query = "{ call select_serach_company_username_for_existing(?) }";
        ResultSet resultSet;

        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall(query)) {

            stmt.setString(1, usernameCompany);

            resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                //if usernameCompany is found
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        //if usernameCompany is not found
        return false;
    }


}
