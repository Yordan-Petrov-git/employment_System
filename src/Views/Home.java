package Views;

import DataProvider.DataProviderCreateNewUser;
import Helpers.ImageUtils.UtilsImages;
import Helpers.PasswordUtils.PasswordUtility;
import Helpers.UtilityGui.GuiUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.sql.SQLException;

public class Home extends JPanel {


  private JLabel jLabelTitle;
  private JButton jButtonLoginAsUser;
  private JButton jButtonLoginAsCompany;
  private JTable jTableOffers;
  private JTextField jTextFieldFastSearchByKeyword;
  public MainFrame jFrame;

  private JButton jButtonAddPicture;
  private JComboBox jComboBoxAccountType;
  private JButton jButtonCreateAccount;


//  public MainFrame jFrame;
//
//
//  public Home(MainFrame jFrame) {
//    this.jFrame = jFrame;
//    jFrame.setSize(900, 1200);
//
//
//    jLabelTitle = new JLabel("Registration");
//    add(jLabelTitle);
//
//
//    jLabelPicture = new JLabel("");
//    jLabelPicture.setPreferredSize(new Dimension(60, 60));
//    add(jLabelPicture);
//
//
//    jTextFieldName = new JTextField("Name");
//    add(jTextFieldName);
//    jTextFieldFamilyName = new JTextField("Family name");
//    add(jTextFieldFamilyName);
//    jTextFieldUsername = new JTextField("Username");
//    add(jTextFieldUsername);
//    JPasswordFieldPassword = new JPasswordField("Password");
//    add(JPasswordFieldPassword);
//    JPasswordFieldPasswordRetypePassword = new JPasswordField("Retype Password");
//    add(JPasswordFieldPasswordRetypePassword);
//    jTextFieldPassphrase = new JTextField("Passphrase");
//    add(jTextFieldPassphrase);
//    jTextFieldPhone = new JTextField("Phone number");
//    add(jTextFieldPhone);
//    jTextFieldEmail = new JTextField("Email address");
//    add(jTextFieldEmail);
//
//    jButtonShowPassword = new JToggleButton("");
//    jButtonShowPassword.addActionListener(e -> {
//
//      GuiUtils.showPassword(jButtonShowPassword, JPasswordFieldPassword);
//      GuiUtils.showPassword(jButtonShowPassword, JPasswordFieldPasswordRetypePassword);
//    });
//    add(jButtonShowPassword);
//
//    jButtonAddPicture = new JButton("Add picture");
//    jButtonAddPicture.addActionListener(e -> {
//      addPhoto();
//    });
//    add(jButtonAddPicture);
//
//    String[] sizesList = {"Users", "Companies"};
//    jComboBoxAccountType = new JComboBox(sizesList);
//    jComboBoxAccountType.addActionListener(e -> {
//
//
//    });
//    add(jComboBoxAccountType);
//
//    jButtonCreateAccount = new JButton("Register User");
//    jButtonCreateAccount.addActionListener(e -> {
//      //createUser();//create new instance of class user
//      try {
//        DataProviderCreateNewUser.addUserToDataBase();//adds user to the database
//      } catch (SQLException throwables) {
//        throwables.printStackTrace();
//      }
//      DataProviderCreateNewUser.removeCurrentUser();//deletes current user
//    });
//    add(jButtonCreateAccount);
//
//  }


}