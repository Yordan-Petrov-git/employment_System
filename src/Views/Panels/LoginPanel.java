package Views.Panels;

import DataProvider.LoginDataProvider;
import Helpers.UtilityGui.GuiUtils;
import Views.LoginEnum;
import Views.MainFrame;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    private JLabel jLabelLoginTop;
    private JToggleButton jButtonShowPassword;
    private JButton jButtonLogin;
    private JTextField jTextFieldUsername;
    private JPasswordField jPasswordField;
    private JButton jButtonRegister;
    private JButton jButtonBack;
    public MainFrame jFrame;


    String registerButtonState;

    public LoginPanel(MainFrame jFrame) {
        this.jFrame = jFrame;

        jLabelLoginTop = new JLabel("Login");
        add(jLabelLoginTop);

        jTextFieldUsername = new JTextField("Username");
        add(jTextFieldUsername);

        jPasswordField = new JPasswordField("Password");
        add(jPasswordField);

        jButtonShowPassword = new JToggleButton("");
        jButtonShowPassword.setIcon(new ImageIcon(new javax.swing.ImageIcon(GuiUtils.class.getResource("/Icons/pass_off.png")).getImage().getScaledInstance(25, 30, Image.SCALE_SMOOTH)));
        jButtonShowPassword.addActionListener(e -> {
            // reveal password

            GuiUtils.showPassword(jButtonShowPassword, jPasswordField);

        });
        add(jButtonShowPassword);

        jButtonLogin = new JButton("Login");
        jButtonLogin.addActionListener(e -> {
            // login
            if (MainFrame.loginAs == LoginEnum.LOGIN_AS_USER) {
                LoginUser();
            } else if (MainFrame.loginAs == LoginEnum.LOGIN_AS_COMPANY) {
                LoginCompany();
            }
        });
        add(jButtonLogin);


        if (MainFrame.loginAs == LoginEnum.LOGIN_AS_USER) {
            registerButtonState = "Register new User";
        } else if (MainFrame.loginAs == LoginEnum.LOGIN_AS_COMPANY) {
            registerButtonState = "Register new Company";
        }

        jButtonRegister = new JButton(registerButtonState);
        jButtonRegister.addActionListener(e -> {

            if (MainFrame.loginAs == LoginEnum.LOGIN_AS_USER) {
                registerNewUser();
            } else if (MainFrame.loginAs == LoginEnum.LOGIN_AS_COMPANY) {
                registerNewCompany();
            }

        });
        add(jButtonRegister);

        jButtonBack = new JButton("Back");
        jButtonBack.addActionListener(e -> {

            MainFrame.router.removePanel(jFrame);
            MainFrame.router.showHomepagePanel(jFrame);

        });
        add(jButtonBack);

    }

    private void LoginUser() {

        String username = jTextFieldUsername.getText();
        String password = String.valueOf(jPasswordField.getPassword());

        if (LoginDataProvider.logonUser(username, password)) {

            LoginDataProvider.getUserForCurrentLoggedInUserInfo(username);//gets current user details by username
            System.out.println();
            MainFrame.router.removePanel(jFrame);
            MainFrame.router.showHomepageUserPanel(jFrame);

        }

    }

    private void LoginCompany() {

        String companyUsername = jTextFieldUsername.getText();
        String password = String.valueOf(jPasswordField.getPassword());

        if (LoginDataProvider.logonCompany(companyUsername, password)) {

            LoginDataProvider.getCompanyForCurrentLoggedInCompanyInfo(companyUsername);

            MainFrame.router.removePanel(jFrame);
            MainFrame.router.showHomePageCompanyPanel(jFrame);

        }

    }


    private void registerNewUser() {

        MainFrame.router.removePanel(jFrame);
        MainFrame.router.showRegistrationPanel(jFrame);


    }

    private void registerNewCompany() {


        MainFrame.router.removePanel(jFrame);
        MainFrame.router.showRegistrationPanelCompany(jFrame);


    }


}


