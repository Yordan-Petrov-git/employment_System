package Router;

import Views.MainFrame;
import Views.Panels.LoginPanel;
import Views.Panels.RegisterNewAccountPanel;
import Views.Panels.Users.ManageProfile;

import javax.swing.*;



public class Router extends JPanel {

    public void showLoginPanel(MainFrame mainFrame) {
        //Shows login panel for the bar system
        LoginPanel loginPanel = new LoginPanel(mainFrame);
        loginPanel.setSize(800, 600);
        mainFrame.add(loginPanel);
    }

//    public void removePanel(MainFrame mainFrame) {
//        loginPanel.setVisible(false);
//        mainFrame.remove(loginPanel);
//    }

    public void showRegistrationPanel(MainFrame mainFrame) {
        //Shows login panel for the bar system
        RegisterNewAccountPanel registerNewAccount = new RegisterNewAccountPanel(mainFrame);
        registerNewAccount.setSize(600, 800);
        mainFrame.add(registerNewAccount);
    }



    public void showManageProfilePanel(MainFrame mainFrame) {
        //Shows login panel for the bar system
        ManageProfile manageProfile = new ManageProfile(mainFrame);
        manageProfile.setSize(600, 800);
        mainFrame.add(manageProfile);
    }
}
