package Views.Panels.Company;

import Helpers.ImageUtils.UtilsImages;
import Helpers.UtilityGui.GuiUtils;
import Views.MainFrame;

import javax.swing.*;
import java.awt.*;

public class ManageProfileCompany extends JPanel {
    public MainFrame jFrame;


    private JLabel jLabelTitle;
    private JLabel jLabelPicture;
    private JTextField jTextFieldCompanyName;
    private JTextField jTextFieldUsername;
    private JPasswordField JPasswordFieldPassword;
    private JPasswordField JPasswordFieldPasswordRetypePassword;
    private JTextField jTextFieldPassphrase;
    private JToggleButton jButtonShowPassword;
    private JButton jButtonAddPicture;
    private JButton jButtonSaveChanges;


    private JButton jButtonBack;

    public ManageProfileCompany(MainFrame jFrame) {
        this.jFrame = jFrame;



        jLabelTitle = new JLabel("Manage company profile");
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

        jButtonSaveChanges = new JButton("Save Changes");
        jButtonSaveChanges.addActionListener(e -> {


        });
        add(jButtonSaveChanges);



        jButtonBack = new JButton("Back");
        jButtonBack.addActionListener(e -> {

            MainFrame.router.removePanel(jFrame);
            MainFrame.router.showHomePageCompanyPanel(jFrame);

        });
        add(jButtonBack);
    }
}
