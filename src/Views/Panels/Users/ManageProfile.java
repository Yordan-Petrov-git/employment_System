package Views.Panels.Users;

import Helpers.UtilityGui.GuiUtils;
import Views.MainFrame;


import javax.swing.*;
import java.awt.*;

public class ManageProfile extends JPanel {

    private JLabel jLabelTitle;
    private JTextField jTextFieldName;
    private JTextField jTextFieldFamilyName;
    // private JTextField jTextFieldUsername;
    private JPasswordField JPasswordFieldPassword;
    private JPasswordField JPasswordFieldPasswordRetypePassword;
    private JTextField jTextFieldPassphrase;
    private JTextField jTextFieldPhone;
    private JTextField jTextFieldEmail;
    private JToggleButton jButtonShowPassword;
    private JButton jButtonAddPicture;
    private JButton jButtonCreateAccount;

    public MainFrame jFrame;


    public ManageProfile(MainFrame jFrame) {

        this.jFrame = jFrame;
        jFrame.setSize(1300, 100);
        jLabelTitle = new JLabel("Manage user account");
        add(jLabelTitle);
        jTextFieldName = new JTextField("Name");
        add(jTextFieldName);
        jTextFieldFamilyName = new JTextField("Family name");
        add(jTextFieldFamilyName);
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
        add(jButtonShowPassword);
        jButtonAddPicture = new JButton("Change picture");
        add(jButtonAddPicture);

        jButtonShowPassword = new JToggleButton("");
        jButtonShowPassword.addActionListener(e -> {

            GuiUtils.showPassword(jButtonShowPassword, JPasswordFieldPassword);
            GuiUtils.showPassword(jButtonShowPassword, JPasswordFieldPasswordRetypePassword);
        });
        add(jButtonShowPassword);

        jButtonCreateAccount = new JButton("Edit account info");
        add(jButtonCreateAccount);


    }
}


