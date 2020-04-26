package Views.Panels;


import Helpers.DatabaseConnection.DataBaseConnection;
import Helpers.Validators.PasswordValidator;
import Helpers.Validators.Validators;
import Views.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {

    private JLabel jLabelLoginTop;
    private JButton jButtonLogin;
    private JTextField jTextFieldUsername;
    private JTextField jTextFieldPassword;
    public MainFrame jFrame;


    public LoginPanel(MainFrame jFrame) {
        this.jFrame = jFrame;

        jLabelLoginTop = new JLabel("Enter your credentials");
        add(jLabelLoginTop);

        jTextFieldUsername = new JTextField("Username");
        add(jTextFieldUsername);

        jTextFieldPassword = new JTextField("Password");
        add(jTextFieldPassword);

        jButtonLogin = new JButton("Sign in");
        jButtonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = jTextFieldUsername.getText().trim();
                String password = jTextFieldPassword.getText().trim();
                login(username, password);
            }
        });

        add(jButtonLogin);

    }

    public void login(String username, String password) {
        //Method that shows menu jPanel to chose an action : make new order , add to ex
//TODO VALIDATE HASHED PASSWORD AND THE SALT GET THEM FRO SQL
        DataBaseConnection.getConnection();
        if (true) {

//            for (Waiter waiter : JFrameMain.waiter) {
//                if (username.equals(waiter.getWaiterUsername()) && password.equals(waiter.getWaiterPassword())) {
//                    JFrameMain.currentWaiter = waiter;
//                    jFrame.showMenuPanel();
//                    break;
//                } else {
//                    JOptionPane.showMessageDialog(
//                            null
//                            , "Invalid login credentials"
//                            , "Error"
//                            , JOptionPane.ERROR_MESSAGE);
//                }
//            }

        } else {
            JOptionPane.showMessageDialog(
                    null
                    , "Invalid input"
                    , "Error"
                    , JOptionPane.ERROR_MESSAGE);
        }

    }
}


