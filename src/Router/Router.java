package Router;

import Views.Home;
import Views.MainFrame;
import Views.Panels.Company.HomePageCompanyPanel;
import Views.Panels.Company.RegisterNewCompany;
import Views.Panels.LoginPanel;
import Views.Panels.Users.HomePageUsersPanel;
import Views.Panels.Users.RegisterNewAccountPanel;
import Views.Panels.Users.ManageProfileUser;

import javax.swing.*;


public class Router extends JPanel {

    public void showLoginPanel(MainFrame mainFrame) {
       //Login panel
        LoginPanel loginPanel = new LoginPanel(mainFrame);
        loginPanel.setSize(800, 600);
        mainFrame.add(loginPanel);
        mainFrame.getContentPane().validate();
        mainFrame.resetPagingCounters();
    }

    public void removePanel(MainFrame mainFrame) {
        //Remove all elements from the panel
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().repaint();
    }

    public void showRegistrationPanel(MainFrame mainFrame) {
     //Registration panel user
        RegisterNewAccountPanel registerNewAccount = new RegisterNewAccountPanel(mainFrame);
        registerNewAccount.setSize(800, 600);
        mainFrame.add(registerNewAccount);
        mainFrame.getContentPane().validate();
        mainFrame.resetPagingCounters();
    }

    public void showRegistrationPanelCompany(MainFrame mainFrame) {
      //Registration panel company
        RegisterNewCompany registerNewCompany = new RegisterNewCompany(mainFrame);
        registerNewCompany.setSize(800, 600);
        mainFrame.add(registerNewCompany);
        mainFrame.getContentPane().validate();
        mainFrame.resetPagingCounters();
    }

    public void showManageProfilePanel(MainFrame mainFrame) {
     //Mange profile user
        ManageProfileUser manageProfile = new ManageProfileUser(mainFrame);
        manageProfile.setSize(800, 600);
        mainFrame.add(manageProfile);
        mainFrame.getContentPane().validate();
        mainFrame.resetPagingCounters();
    }


    public void showHomepagePanel(MainFrame mainFrame) {
      //Homepage
        Home homePage = new Home(mainFrame);
        homePage.setSize(800, 600);
        mainFrame.add(homePage);
        mainFrame.getContentPane().validate();
       // mainFrame.resetPagingCounters();
    }
    public void showHomepageUserPanel(MainFrame mainFrame) {
     //Homepage user
        HomePageUsersPanel homePage = new HomePageUsersPanel(mainFrame);
        homePage.setSize(800, 600);
        mainFrame.add(homePage);
        mainFrame.getContentPane().validate();
        mainFrame.resetPagingCounters();
    }

    public void showHomePageCompanyPanel(MainFrame mainFrame) {
      //Homepage company
        HomePageCompanyPanel homePageCompany = new HomePageCompanyPanel(mainFrame);
        homePageCompany.setSize(800, 600);
        mainFrame.add(homePageCompany);
        mainFrame.getContentPane().validate();
        mainFrame.resetPagingCounters();
    }

    public void showManageUserAccount(MainFrame mainFrame) {
        //Homepage company
        ManageProfileUser manageProfileUser = new ManageProfileUser(mainFrame);
        manageProfileUser.setSize(800, 600);
        mainFrame.add(manageProfileUser);
        mainFrame.getContentPane().validate();
       // mainFrame.resetPagingCounters();
    }

}
