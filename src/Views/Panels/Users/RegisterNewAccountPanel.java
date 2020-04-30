package Views.Panels.Users;

import DataProvider.DataProviderCreateNewUser;
import Helpers.PasswordUtils.PasswordUtility;
import Helpers.UtilityGui.GuiUtils;
import Helpers.Validators.Validators;
import Views.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class RegisterNewAccountPanel extends JPanel {

    private JLabel jLabelTitle;
    private JTextField jTextFieldName;
    private JTextField jTextFieldFamilyName;
    private JTextField jTextFieldUsername;
    private JPasswordField JPasswordFieldPassword;
    private JPasswordField JPasswordFieldPasswordRetypePassword;
    private JTextField jTextFieldPassphrase;
    private JTextField jTextFieldPhone;
    private JTextField jTextFieldEmail;
    private JToggleButton jButtonShowPassword;
    private JButton jButtonCreateAccount;
    private JButton jButtonBack;
    public MainFrame jFrame;


    public RegisterNewAccountPanel(MainFrame jFrame) {
        this.jFrame = jFrame;
        jFrame.setSize(1300, 100);
        jFrame.setLocationRelativeTo(null);

        jLabelTitle = new JLabel("Registration");
        add(jLabelTitle);


        jTextFieldName = new JTextField("Name");
        add(jTextFieldName);

        jTextFieldFamilyName = new JTextField("Family name");
        add(jTextFieldFamilyName);

        jTextFieldUsername = new JTextField("Username");
        add(jTextFieldUsername);

        JPasswordFieldPassword = new JPasswordField("Password");
        add(JPasswordFieldPassword);

        JPasswordFieldPasswordRetypePassword = new JPasswordField("Retype Password");
        add(JPasswordFieldPasswordRetypePassword);

        jTextFieldPassphrase = new JTextField("Passphrase");
        add(jTextFieldPassphrase);

        jTextFieldPhone = new JTextField("Phone number");
        add(jTextFieldPhone);

        jTextFieldEmail = new JTextField("Email address");
        add(jTextFieldEmail);

        jButtonShowPassword = new JToggleButton("");
        jButtonShowPassword.setIcon(new ImageIcon(new javax.swing.ImageIcon(GuiUtils.class.getResource("/Icons/pass_off.png")).getImage().getScaledInstance(25, 30, Image.SCALE_SMOOTH)));
        jButtonShowPassword.addActionListener(e -> {

            GuiUtils.showPassword(jButtonShowPassword, JPasswordFieldPassword);
            GuiUtils.showPassword(jButtonShowPassword, JPasswordFieldPasswordRetypePassword);
        });
        add(jButtonShowPassword);


        jButtonCreateAccount = new JButton("Register User");
        jButtonCreateAccount.addActionListener(e -> {

            createUser();//create new instance of class user

            try {

                DataProviderCreateNewUser.addUserToDataBase();//adds user to the database

            } catch (SQLException throwables) {

                throwables.printStackTrace();

            }

            DataProviderCreateNewUser.removeCurrentUser();//deletes current user
        });
        add(jButtonCreateAccount);


        jButtonBack = new JButton("Back");
        jButtonBack.addActionListener(e -> {

            MainFrame.router.removePanel(jFrame);
            MainFrame.router.showLoginPanel(jFrame);

        });
        add(jButtonBack);

    }

    public void createUser() {

        String salt = PasswordUtility.generateSalt(512).get();//Generates secure random salt
        String password = String.valueOf(JPasswordFieldPassword.getPassword());//Gets the strign value of the first password field
        String key = PasswordUtility.hashPassword(password, salt).get();//Generates hashed key based on the passsword in the firs password field adn the generated secure random salt
        String firstName = jTextFieldName.getText();
        String familyName = jTextFieldFamilyName.getText();
        String username = jTextFieldUsername.getText();
        String phone = jTextFieldPhone.getText();
        String email = jTextFieldEmail.getText();
        String passphrase = jTextFieldPassphrase.getText();


        if (Validators.validatePassword(password) && Validators.validatePassword(String.valueOf(JPasswordFieldPasswordRetypePassword.getPassword()))) {


            if (Validators.getEmailVer(email)) {

                if (Validators.validatePhoneNumber(phone)) {
                    DataProviderCreateNewUser.initializeNewUserNoAccountType(username,
                            key, salt, passphrase, firstName, familyName,
                            phone, email);
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong phone number");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid email address");
            }


        } else if (password.equals(String.valueOf(JPasswordFieldPasswordRetypePassword.getPassword()))) {

            JOptionPane.showMessageDialog(null, "Passwords must contain at least at least 8 symbols ," +
                    "one lower case one upper case latter one digit and no whitespaces");
        } else {
            JOptionPane.showMessageDialog(null, "Passwords must match");

        }


    }

}
