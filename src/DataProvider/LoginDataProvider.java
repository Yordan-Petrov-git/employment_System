package DataProvider;

import Helpers.PasswordUtils.PasswordUtility;
import Views.Panels.LoginPanel;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginDataProvider extends DataProvider{



    public void loginUser(String username, String password) {
        //Method that shows menu jPanel to chose an action : make new order , add to ex
//TODO VALIDATE HASHED PASSWORD AND THE SALT GET THEM FRO SQL

        //Connection to SQL DB


        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        //SQL Query for username
        String query = "SELECT e.`user_id`,d.`username_user`,d.`password_user`,d.`password_salt_user` \n" +
                "FROM `users` AS e \n" +
                "INNER JOIN `login_credentials_users` AS d\n" +
                "ON e.`user_id` = d.`user_id`\n" +
                "WHERE d.`username_user` = ?\n" +
                "LIMIT 1;\n;";


        if (username.trim().length() == 0) {

            JOptionPane.showMessageDialog(null, "Enter Your Username", "Empty Username", 2);

        } else if (password.trim().length() == 0) {

            JOptionPane.showMessageDialog(null, "Enter Your Password", "Empty Password", 2);

        } else {

            try {
                preparedStatement = getConnection().prepareStatement(query);
                preparedStatement.setString(1, username);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    String usernameUser = resultSet.getString("username_user");
                    String key = resultSet.getString("password_user");
                    String salt = resultSet.getString("password_salt_user");
                    String userIdid = resultSet.getString("user_id");

                    if (PasswordUtility.verifyPassword(password, key, salt)) {//If Login information is correct
                        //Log in the user if credentials are correct


                        System.out.println("logged in !!!");
                        JOptionPane.showMessageDialog(null, "success", "login success", 2);
                        username = null;
                        key = null;
                        salt = null;
                        userIdid = null;
                    } else {//If Login information is not correct

                        //Error message
                        JOptionPane.showMessageDialog(null, "Invalid Username / Password", "Login Error", 2);
                        //Reset to defaut login textfield and password field


                        //TODO HANDLE THE  RESET OF USERNAME AND PASSWORD FIELDS
//                        jTextFieldUsername.setText("Username");//Resets username defaut text
//                        jPasswordField.setText("Password");//Resets password defaut text
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(LoginPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }

}
