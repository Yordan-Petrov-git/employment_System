package Router;

import Views.Home;
import Views.MainFrame;
import Views.Panels.Company.HomePageCompanyPanel;
import Views.Panels.Company.RegisterNewCompany;
import Views.Panels.LoginPanel;
import Views.Panels.Users.HomePageUsersPanel;
import Views.Panels.Users.RegisterNewAccountPanel;
import Views.Panels.Users.ManageProfile;

import javax.swing.*;


public class Router extends JPanel {

    public void showLoginPanel(MainFrame mainFrame) {
        //Shows login panel for the bar system
        LoginPanel loginPanel = new LoginPanel(mainFrame);
        loginPanel.setSize(800, 600);
        mainFrame.add(loginPanel);
        mainFrame.getContentPane().validate();
    }

    public void removePanel(MainFrame mainFrame) {
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().repaint();
    }

    public void showRegistrationPanel(MainFrame mainFrame) {
        //Shows login panel for the bar system
        RegisterNewAccountPanel registerNewAccount = new RegisterNewAccountPanel(mainFrame);
        registerNewAccount.setSize(800, 600);
        mainFrame.add(registerNewAccount);
        mainFrame.getContentPane().validate();
    }

    public void showRegistrationPanelCompany(MainFrame mainFrame) {
        //Shows login panel for the bar system
        RegisterNewCompany registerNewCompany = new RegisterNewCompany(mainFrame);
        registerNewCompany.setSize(800, 600);
        mainFrame.add(registerNewCompany);
        mainFrame.getContentPane().validate();
    }

    public void showManageProfilePanel(MainFrame mainFrame) {
        //Shows login panel for the bar system
        ManageProfile manageProfile = new ManageProfile(mainFrame);
        manageProfile.setSize(800, 600);
        mainFrame.add(manageProfile);
        mainFrame.getContentPane().validate();
    }


    public void showHomepagePanel(MainFrame mainFrame) {
        //Shows login panel for the bar system
        Home homePage = new Home(mainFrame);
        homePage.setSize(800, 600);
        mainFrame.add(homePage);
        mainFrame.getContentPane().validate();
    }
    public void showHomepageUserPanel(MainFrame mainFrame) {
        //Shows login panel for the bar system
        HomePageUsersPanel homePage = new HomePageUsersPanel(mainFrame);
        homePage.setSize(800, 600);
        mainFrame.add(homePage);
        mainFrame.getContentPane().validate();
    }

    public void showHomePageCompanyPanel(MainFrame mainFrame) {
        //Shows login panel for the bar system
        HomePageCompanyPanel homePage = new HomePageCompanyPanel(mainFrame);
        homePage.setSize(800, 600);
        mainFrame.add(homePage);
        mainFrame.getContentPane().validate();
    }

}
