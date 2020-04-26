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

        jButtonLogin = new JButton("Login");
        jButtonLogin.addActionListener(e -> {
            // login

            LoginUser();

        });
        add(jButtonLogin);

    }

    public void LoginUser() {

        String query = "SELECT e.`user_id`,d.`username_user`,d.`password_user`,d.`password_salt_user` \n" +
                "FROM `users` AS e \n" +
                "INNER JOIN `login_credentials_users` AS d\n" +
                "ON e.`user_id` = d.`user_id`\n" +
                "WHERE d.`username_user` = ?\n" +
                "LIMIT 1;\n;";

        jFrame.loginDataProvider.loginUser(jTextFieldUsername.getText(), String.valueOf(jPasswordField.getPassword()), query);
    }
}


