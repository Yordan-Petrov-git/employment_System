package Views.Panels.Company;


import DataProvider.DataProviderCreateNewCompany;
import Helpers.PasswordUtils.PasswordUtility;
import Helpers.UtilityGui.GuiUtils;
import Helpers.Validators.Validators;
import Views.MainFrame;

import javax.swing.*;
import java.awt.*;

public class RegisterNewCompany extends JPanel {

    private JLabel jLabelTitle;
    private JTextField jTextFieldCompanyName;
    private JTextField jTextFieldUsername;
    private JPasswordField JPasswordFieldPassword;
    private JPasswordField JPasswordFieldPasswordRetypePassword;
    private JTextField jTextFieldPassphrase;
    private JToggleButton jButtonShowPassword;
    private JButton jButtonCreateAccount;
    private JButton jButtonBack;

    public MainFrame jFrame;

    public RegisterNewCompany(MainFrame jFrame) {
        this.jFrame = jFrame;
        jFrame.setSize(1300, 100);


        jLabelTitle = new JLabel("Register new Company");
        add(jLabelTitle);

        jTextFieldCompanyName = new JTextField("Company Name");
        add(jTextFieldCompanyName);
        jTextFieldUsername = new JTextField("Username");
        add(jTextFieldUsername);
        JPasswordFieldPassword = new JPasswordField("Password");
        add(JPasswordFieldPassword);
        JPasswordFieldPasswordRetypePassword = new JPasswordField("Retype Password");
        add(JPasswordFieldPasswordRetypePassword);
        jTextFieldPassphrase = new JTextField("Passphrase");
        add(jTextFieldPassphrase);


        jButtonShowPassword = new JToggleButton("");
        jButtonShowPassword.setIcon(new ImageIcon(new javax.swing.ImageIcon(GuiUtils.class.getResource("/Resources/Icons/pass_off.png")).getImage().getScaledInstance(25, 30, Image.SCALE_SMOOTH)));
        jButtonShowPassword.addActionListener(e -> {
            GuiUtils.showPassword(jButtonShowPassword, JPasswordFieldPassword);
            GuiUtils.showPassword(jButtonShowPassword, JPasswordFieldPasswordRetypePassword);
        });
        add(jButtonShowPassword);


        jButtonCreateAccount = new JButton("Register Company");
        jButtonCreateAccount.addActionListener(e -> {
            createCompany();//create new instance of class user
            DataProviderCreateNewCompany.addCompanyToDataBase();//adds user to the database
            DataProviderCreateNewCompany.removeCurrentCompany();//deletes current user
        });
        add(jButtonCreateAccount);

        jButtonBack = new JButton("Back");
        jButtonBack.addActionListener(e -> {

            MainFrame.router.removePanel(jFrame);
            MainFrame.router.showLoginPanel(jFrame);

        });
        add(jButtonBack);

    }

    public void createCompany() {



        String salt = PasswordUtility.generateSalt(512).get();//Generates secure random salt
        String password = String.valueOf(JPasswordFieldPassword.getPassword());//Gets the strign value of the first password field
        String key = PasswordUtility.hashPassword(password, salt).get();//Generates hashed key based on the passsword in the firs password field adn the generated secure random salt
        String companyName = jTextFieldCompanyName.getText();
        String username = jTextFieldUsername.getText();
        String passPhrase = jTextFieldPassphrase.getText();


        if(Validators.validatePassword(password)&&Validators.validatePassword(String.valueOf(JPasswordFieldPasswordRetypePassword.getPassword()))){

            DataProviderCreateNewCompany.initializeNewCompany(username, key
                    , salt, passPhrase, companyName);
        }else if(password.equals(String.valueOf(JPasswordFieldPasswordRetypePassword.getPassword()))){

            JOptionPane.showMessageDialog(null,"Passwords must contain at least at least 8 symbols ," +
                    "one lower case one upper case latter one digit and no whitespaces");
        }else{
            JOptionPane.showMessageDialog(null,"Passwords must match");

        }


    }


}

