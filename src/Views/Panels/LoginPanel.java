package Views.Panels;

import Helpers.UtilityGui.GuiUtils;
import Views.MainFrame;

import javax.swing.*;

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
        jButtonShowPassword.addActionListener(e -> {
            // reveal password
            GuiUtils.showPassword(jButtonShowPassword, jPasswordField);

        });
        add(jButtonShowPassword);

        jButtonLogin = new JButton("Sign in");
        jButtonLogin.addActionListener(e -> {
            // login
            jFrame.loginDataProvider.loginUser(jTextFieldUsername.getText(), String.valueOf(jPasswordField.getPassword()));
        });
        add(jButtonLogin);

    }


}


