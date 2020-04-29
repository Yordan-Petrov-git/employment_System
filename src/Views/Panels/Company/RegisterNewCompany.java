package Views.Panels.Company;


import DataProvider.DataProviderCreateNewCompany;
import DataProvider.DataProviderCreateNewUser;
import Helpers.ImageUtils.UtilsImages;
import Helpers.PasswordUtils.PasswordUtility;
import Helpers.UtilityGui.GuiUtils;
import Views.MainFrame;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.sql.SQLException;

public class RegisterNewCompany extends JPanel {


    private JLabel jLabelTitle;
    private JLabel jLabelPicture;
    private JTextField jTextFieldCompanyName;
    private JTextField jTextFieldUsername;
    private JPasswordField JPasswordFieldPassword;
    private JPasswordField JPasswordFieldPasswordRetypePassword;
    private JTextField jTextFieldPassphrase;
    private JToggleButton jButtonShowPassword;

    private JButton jButtonAddPicture;
    private JButton jButtonCreateAccount;
    private JButton jButtonBack;

    public MainFrame jFrame;

    public RegisterNewCompany(MainFrame jFrame) {
        this.jFrame = jFrame;
        jFrame.setSize(1300, 100);


        jLabelTitle = new JLabel("Register new Company");
        add(jLabelTitle);


        jLabelPicture = new JLabel("");
        jLabelPicture.setPreferredSize(new Dimension(60, 60));
        add(jLabelPicture);


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
        jButtonShowPassword.setIcon(new ImageIcon(new javax.swing.ImageIcon(GuiUtils.class.getResource("/Icons/pass_off.png")).getImage().getScaledInstance(25, 30, Image.SCALE_SMOOTH)));
        jButtonShowPassword.addActionListener(e -> {
            GuiUtils.showPassword(jButtonShowPassword, JPasswordFieldPassword);
            GuiUtils.showPassword(jButtonShowPassword, JPasswordFieldPasswordRetypePassword);
        });
        add(jButtonShowPassword);

        jButtonAddPicture = new JButton("Add picture");
        jButtonAddPicture.addActionListener(e -> {

            UtilsImages.addPhoto(jLabelPicture);

        });
        add(jButtonAddPicture);
        ;

        jButtonCreateAccount = new JButton("Register Company");
        jButtonCreateAccount.addActionListener(e -> {
            createCompany();//create new instance of class user
            DataProviderCreateNewCompany.addCompanyToDataBase();//adds user to the database
            DataProviderCreateNewCompany.removeCurrentCompany();//deletes current user
        });
        add(jButtonCreateAccount);

        jButtonBack = new JButton("Back");
        jButtonBack.addActionListener(e -> {



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

        DataProviderCreateNewCompany.initializeNewCompany(username, key
                , salt, passPhrase, companyName);

    }


}

