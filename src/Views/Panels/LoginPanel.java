package Views.Panels;

import DataProvider.LoginDataProvider;
import Helpers.UtilityGui.GuiUtils;
import Models.User;
import Views.MainFrame;

import javax.swing.*;

public class LoginPanel extends JPanel {

    private JLabel jLabelLoginTop;
    private JToggleButton jButtonShowPassword;
    private JButton jButtonLogin;
    private JTextField jTextFieldUsername;
    private JPasswordField jPasswordField;
    public MainFrame jFrame;


    private User currentUser;

    public  User getCurrentUser() {
        return this.currentUser;
    }

    public  void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }


    public LoginPanel(MainFrame jFrame) {
        this.jFrame = jFrame;

        jLabelLoginTop = new JLabel("Enter your login information");
        add(jLabelLoginTop);

        jTextFieldUsername = new JTextField("Username");
        add(jTextFieldUsername);

        jPasswordField = new JPasswordField("Password");
        add(jPasswordField);

        jButtonShowPassword = new JToggleButton("");
        jButtonShowPassword.addActionListener(e -> {
            // reveal password
            GuiUtils.showPassword(jButtonShowPassword, jPasswordField);

        });
        add(jButtonShowPassword);

        jButtonLogin = new JButton("Login");
        jButtonLogin.addActionListener(e -> {
            // login

            LoginUser();

        });
        add(jButtonLogin);

    }

    private void LoginUser() {

        LoginDataProvider.logonUser(jTextFieldUsername.getText(), String.valueOf(jPasswordField.getPassword()));

    }
}


