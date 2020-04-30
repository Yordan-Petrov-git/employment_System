package Router;

import DataProvider.DataProviderTableJobOffers;
import Views.Home;
import Views.MainFrame;
import Views.Panels.Company.*;
import Views.Panels.LoginPanel;
import Views.Panels.Users.ApplyForOffer;
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
    }

    public void removePanel(MainFrame mainFrame) {
        //Remove all elements from the panel
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().repaint();
        mainFrame.resetPagingCounters();
    }

    public void showRegistrationPanel(MainFrame mainFrame) {
        //Registration panel user
        RegisterNewAccountPanel registerNewAccount = new RegisterNewAccountPanel(mainFrame);
        registerNewAccount.setSize(800, 600);
        mainFrame.add(registerNewAccount);
        mainFrame.getContentPane().validate();
    }

    public void showRegistrationPanelCompany(MainFrame mainFrame) {
        //Registration panel company
        RegisterNewCompany registerNewCompany = new RegisterNewCompany(mainFrame);
        registerNewCompany.setSize(800, 600);
        mainFrame.add(registerNewCompany);
        mainFrame.getContentPane().validate();
    }

    public void showManageUserProfilePanel(MainFrame mainFrame) {
        //Mange profile user
        ManageProfileUser manageUserProfile = new ManageProfileUser(mainFrame);
        manageUserProfile.setSize(800, 600);
        mainFrame.add(manageUserProfile);
        mainFrame.getContentPane().validate();
    }

    public void showManageProfilePanel(MainFrame mainFrame) {
        //Mange profile Company
        ManageProfileCompany manageCompanyProfile = new ManageProfileCompany(mainFrame);
        manageCompanyProfile.setSize(800, 600);
        mainFrame.add(manageCompanyProfile);
        mainFrame.getContentPane().validate();
    }

    public void showHomepagePanel(MainFrame mainFrame) {
        //Homepage
        Home homePage = new Home(mainFrame);
        homePage.setSize(800, 600);
        mainFrame.add(homePage);
        mainFrame.getContentPane().validate();

    }

    public void showHomepageUserPanel(MainFrame mainFrame) {
        //Homepage user
        HomePageUsersPanel homePage = new HomePageUsersPanel(mainFrame);
        homePage.setSize(800, 600);
        mainFrame.add(homePage);
        mainFrame.getContentPane().validate();
    }

    public void showHomePageCompanyPanel(MainFrame mainFrame) {
        //Homepage user
        HomePageCompanyPanel homePage = new HomePageCompanyPanel(mainFrame);
        homePage.setSize(800, 600);
        mainFrame.add(homePage);
        mainFrame.getContentPane().validate();
    }

    public void applyForOffer(MainFrame mainFrame) {
        //Homepage company
        ApplyForOffer applyForOffer = new ApplyForOffer(mainFrame);
        applyForOffer.setSize(800, 600);
        mainFrame.add(applyForOffer);
        mainFrame.getContentPane().validate();
    }

    public void createNewJobOffer(MainFrame mainFrame) {
        //Homepage company
        CreateNewJobOffer createNewJobOffer = new CreateNewJobOffer(mainFrame);
        createNewJobOffer.setSize(800, 600);
        mainFrame.add(createNewJobOffer);
        mainFrame.getContentPane().validate();
    }



    public void viewJobOfersCompany(MainFrame mainFrame) {
        //Homepage company
        ViewJobOfferDetails viewJobOfferDetails = new ViewJobOfferDetails(mainFrame);
        viewJobOfferDetails.setSize(800, 600);
        mainFrame.add(viewJobOfferDetails);
        mainFrame.getContentPane().validate();
    }

    public void editSelectedJobOffer(MainFrame mainFrame) {
        //Homepage company
        ManageJobOffer manageSelectedJobOffer = new ManageJobOffer(mainFrame);
        manageSelectedJobOffer.setSize(800, 600);
        mainFrame.add(manageSelectedJobOffer);
        mainFrame.getContentPane().validate();
    }



}
