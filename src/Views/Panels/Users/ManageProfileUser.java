package Views.Panels.Users;

import Helpers.ImageUtils.UtilsImages;
import Helpers.UtilityGui.GuiUtils;
import Views.MainFrame;


import javax.swing.*;
import java.awt.*;

public class ManageProfileUser extends JPanel {

    private JLabel jLabelTitle;
    private JTextField jTextFieldName;
    private JTextField jTextFieldFamilyName;
    private JPasswordField JPasswordFieldPassword;
    private JPasswordField JPasswordFieldPasswordRetypePassword;
    private JTextField jTextFieldPassphrase;
    private JTextField jTextFieldPhone;
    private JTextField jTextFieldEmail;
    private JToggleButton jButtonShowPassword;
    private JButton jButtonAddPicture;
    private JButton jButtonCreateAccount;
    private JButton jButtonBack;
    private JLabel jLabelPicture;
    public MainFrame jFrame;


    public ManageProfileUser(MainFrame jFrame) {

        this.jFrame = jFrame;
        jFrame.setSize(1300, 100);
        jLabelTitle = new JLabel("Manage user account");
        add(jLabelTitle);

        jLabelPicture = new JLabel("");
        jLabelPicture.setPreferredSize(new Dimension(60, 60));
        add(jLabelPicture);

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

        jButtonAddPicture = new JButton("Change picture");
        jButtonAddPicture.addActionListener(e -> {
            UtilsImages.addPhoto(jLabelPicture);
        });
        add(jButtonAddPicture);

        jButtonShowPassword = new JToggleButton("");
        jButtonShowPassword.setIcon(new ImageIcon(new javax.swing.ImageIcon(GuiUtils.class.getResource("/Icons/pass_off.png")).getImage().getScaledInstance(25, 30, Image.SCALE_SMOOTH)));
        jButtonShowPassword.addActionListener(e -> {

            GuiUtils.showPassword(jButtonShowPassword, JPasswordFieldPassword);
            GuiUtils.showPassword(jButtonShowPassword, JPasswordFieldPasswordRetypePassword);
        });
        add(jButtonShowPassword);

        jButtonCreateAccount = new JButton("Edit account info");
        jButtonShowPassword.addActionListener(e -> {

        });
        add(jButtonCreateAccount);

        jButtonBack = new JButton("Back");
        jButtonBack.addActionListener(e -> {



        });
        add(jButtonBack);

    }
}


