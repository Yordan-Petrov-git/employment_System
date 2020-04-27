package Views.Panels;

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

public class RegisterNewAccountPanel extends JPanel {


    private JLabel jLabelTitle;
    private JLabel jLabelPicture;
    private JTextField jTextFieldName;
    private JTextField jTextFieldFamilyName;
    private JTextField jTextFieldUsername;
    private JPasswordField JPasswordFieldPassword;
    private JPasswordField JPasswordFieldPasswordRetypePassword;
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


        jLabelPicture = new JLabel("");
        jLabelPicture.setPreferredSize(new Dimension(60, 60));
        add(jLabelPicture);


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
        jButtonShowPassword.addActionListener(e -> {

            GuiUtils.showPassword(jButtonShowPassword, JPasswordFieldPassword);
            GuiUtils.showPassword(jButtonShowPassword, JPasswordFieldPasswordRetypePassword);
        });
        add(jButtonShowPassword);

        jButtonAddPicture = new JButton("Add picture");
        jButtonAddPicture.addActionListener(e -> {
            addPhoto();
        });
        add(jButtonAddPicture);

        String[] sizesList = {"Users", "Companies"};
        jComboBoxAccountType = new JComboBox(sizesList);
        jComboBoxAccountType.addActionListener(e -> {


        });
        add(jComboBoxAccountType);

        jButtonCreateAccount = new JButton("Register User");
        jButtonCreateAccount.addActionListener(e -> {
            createUser();
            try {
                DataProviderCreateNewUser.addUserToDataBase();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        add(jButtonCreateAccount);

    }

    public void addPhoto() {
        String imagePath = null;
        JFileChooser chooser = new JFileChooser();//File choser
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));//Opens home directory in current user
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("*.images", "jpg", "png");//file extention filter
        chooser.addChoosableFileFilter(fnef);//Sets the filter
        int ans = chooser.showSaveDialog(null);
        if (ans == JFileChooser.APPROVE_OPTION) {//if image chosen closes with yes
            File selectedPhoto = chooser.getSelectedFile();
            String path = selectedPhoto.getAbsolutePath();// sets chosen file's path
            if (UtilsImages.limitImageSize(path)) {//Limit image
                jLabelPicture.setIcon(UtilsImages.resizeImage(path, null, jLabelPicture));//Resizes selected iamge to fit in jlabel
                //  imagePath = path;
            } else {//if photo is larger
                System.out.println("Photo larger than 1 mb");
                JOptionPane.showMessageDialog(null, "Photo larger than 1 mb please select different photo", "Selected photo size is too large", 2);
            }
        } else {//Image choser exited or closed
            System.out.println("Photo Not Selected ");
        }
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

        DataProviderCreateNewUser.initializeNewUserNoAccountType(username,
                key, salt, passphrase, firstName, familyName,
                phone, email);

    }


}
