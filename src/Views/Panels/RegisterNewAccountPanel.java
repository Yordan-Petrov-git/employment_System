package Views.Panels;

import Views.MainFrame;

import javax.swing.*;
import java.awt.*;

public class RegisterNewAccountPanel extends JPanel {

    private JLabel jLabelTitle;
    private JTextField jTextFieldName;
    private JTextField jTextFieldFamilyName;
    private JTextField jTextFieldUsername;
    private JPasswordField jTextFieldPassword;
    private JPasswordField jTextFieldRetypePassword;
    private JTextField jTextFieldPassphrase;
    private JTextField jTextFieldPhone;
    private JTextField jTextFieldEmail;
    private JToggleButton jButtonShowPassword;


    private JButton jButtonAddPicture;
    private JComboBox jComboBoxAccountType;
    private JButton jButtonCreateAccount;


    public MainFrame jFrame;


    public RegisterNewAccountPanel(MainFrame jFrame) {
        this.jFrame = jFrame;
        jFrame.setSize(1300, 100);


        jLabelTitle = new JLabel("Registration");
        add(jLabelTitle);

        jTextFieldName = new JTextField("Name");
        add(jTextFieldName);
        jTextFieldFamilyName = new JTextField("Family name");
        add(jTextFieldFamilyName);
        jTextFieldUsername = new JTextField("Username");
        add(jTextFieldUsername);
        jTextFieldPassword = new JPasswordField("Password");
        add(jTextFieldPassword);
        jTextFieldRetypePassword = new JPasswordField("Retype Password");
        add(jTextFieldRetypePassword);
        jTextFieldPassphrase = new JTextField("Passphrase");
        add(jTextFieldPassphrase);
        jTextFieldPhone = new JTextField("Phone number");
        add(jTextFieldPhone);
        jTextFieldEmail = new JTextField("Email address");
        add(jTextFieldEmail);


        jButtonShowPassword = new JToggleButton("");
        add(jButtonShowPassword);

        jButtonAddPicture = new JButton("Add picture");
        add(jButtonAddPicture);

        String[] sizesList = {"Users", "Companies"};
        jComboBoxAccountType = new JComboBox(sizesList);
        add(jComboBoxAccountType);

        jButtonCreateAccount = new JButton("Register User");
        add(jButtonCreateAccount);


    }
}
