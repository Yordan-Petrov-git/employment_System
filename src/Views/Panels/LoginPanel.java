package Views.Panels;

import DataProvider.LoginDataProvider;
import Helpers.UtilityGui.GuiUtils;
import Views.MainFrame;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    private JLabel jLabelLoginTop;
    private JToggleButton jButtonShowPassword;
    private JButton jButtonLogin;
    private JTextField jTextFieldUsername;
    private JPasswordField jPasswordField;
    public MainFrame jFrame;


    public LoginPanel(MainFrame jFrame) {
        this.jFrame = jFrame;

        jLabelLoginTop = new JLabel("Enter your login information");
        add(jLabelLoginTop);

        jTextFieldUsername = new JTextField("Username");
        add(jTextFieldUsername);

        jPasswordField = new JPasswordField("Password");
        add(jPasswordField);

        jButtonShowPassword = new JToggleButton("");
        jButtonShowPassword.setIcon(new ImageIcon(new javax.swing.ImageIcon(GuiUtils.class.getResource("/Icons/pass_off.png")).getImage().getScaledInstance(25, 30, Image.SCALE_SMOOTH)));
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


