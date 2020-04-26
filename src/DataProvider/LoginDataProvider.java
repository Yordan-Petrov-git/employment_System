package DataProvider;

import Helpers.PasswordUtils.PasswordUtility;

import Views.Panels.LoginPanel;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginDataProvider extends DataProvider {


    public void loginUser(String username, String password, String query) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

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
                    String userId = resultSet.getString("user_id");

                    if (PasswordUtility.verifyPassword(password, key, salt) && username.equals(usernameUser)) {//If Login information is correct
                        //Log in the user if credentials are correct

                        System.out.println("logged in !!!");
                        JOptionPane.showMessageDialog(null, "success", "login success", 2);
                        username = null;
                        key = null;
                        salt = null;
                        userId = null;
                    } else {//If Login information is not correct

                        //Error message
                        JOptionPane.showMessageDialog(null, "Invalid Username / Password", "Login Error", 2);

                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(LoginPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }

}
