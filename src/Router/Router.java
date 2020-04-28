package Router;

import Views.MainFrame;
import Views.Panels.Company.RegisterNewCompany;
import Views.Panels.LoginPanel;
import Views.Panels.Users.RegisterNewAccountPanel;
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
        registerNewAccount.setSize(800, 600);
        mainFrame.add(registerNewAccount);
    }

    public void showRegistrationPanelCompany(MainFrame mainFrame) {
        //Shows login panel for the bar system
        RegisterNewCompany registerNewCompany = new RegisterNewCompany(mainFrame);
        registerNewCompany.setSize(800, 600);
        mainFrame.add(registerNewCompany);
    }

    public void showManageProfilePanel(MainFrame mainFrame) {
        //Shows login panel for the bar system
        ManageProfile manageProfile = new ManageProfile(mainFrame);
        manageProfile.setSize(800, 600);
        mainFrame.add(manageProfile);
    }


}
