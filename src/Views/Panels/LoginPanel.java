package Views.Panels;

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
            if (jButtonShowPassword.isSelected()) {//If button is presed
                //jButtonShowPassword.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Pass onn.png")).getImage().getScaledInstance(25, 30, Image.SCALE_SMOOTH)));
                jPasswordField.setEchoChar((char) 0);//Sets cahrecter tovisible
            } else {//If button is presed again
                jPasswordField.setEchoChar('*');//Sets cahrecter to invisible defaut
              //  jButtonShowPassword.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/pass off.png")).getImage().getScaledInstance(25, 30, Image.SCALE_SMOOTH)));
            }
        });
        add(jButtonShowPassword);

        jButtonLogin = new JButton("Sign in");
        jButtonLogin.addActionListener(e -> {
            // get the username & password
            jFrame.dataProvider.login(jTextFieldUsername.getText(), String.valueOf(jPasswordField.getPassword()));
        });
        add(jButtonLogin);

    }


}


