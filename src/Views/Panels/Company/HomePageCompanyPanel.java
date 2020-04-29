package Views.Panels.Company;

import DataProvider.DataProviderCreateNewUser;
import Helpers.TableUtils.TableUtility;
import Views.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class HomePageCompanyPanel extends JPanel {
    public MainFrame jFrame;
    public JButton jButtonLogOut;
    public JButton jButtonViewApplication;
    public JButton jButtonManageApplication;
    public JButton jButtonDeleteSelectedApplication;
    public JButton jButtonCreateNewJobOffer;
    public JTable jTableJobOffers;


    public HomePageCompanyPanel(MainFrame jFrame) {
        this.jFrame = jFrame;

        //TODO ADD TABLE WITH JOB OFFERS FOR THE SPECIFIC LOGGED IN COMPANY BY ID OT WHTEVER IDENTIFIER


        jTableJobOffers = new JTable();
        JScrollPane pane = new JScrollPane();
        MainFrame.dataProviderTableJobOfferss.model = new DefaultTableModel();
        pane.setViewportView(jTableJobOffers);
        TableUtility.autoResizeColumn(jTableJobOffers);
        add(pane);

        jButtonViewApplication = new JButton("Check application");
        jButtonViewApplication.addActionListener(e -> {

            //TODO ADD  WAY TO CHECK offers ON SPECIFIC ROW OF THE TABLE
        });
        add(jButtonViewApplication);


        jButtonManageApplication = new JButton("Manage offers");
        jButtonManageApplication.addActionListener(e -> {

            //TODO ADD  WAY TO CHECK offers ON SPECIFIC ROW OF THE TABLE
        });
        add(jButtonManageApplication);

        jButtonDeleteSelectedApplication = new JButton("Delete selected offer");
        jButtonDeleteSelectedApplication.addActionListener(e -> {

            //TODO ADD  WAY TO DELETE SELECTED offers FORM THE TABLE AND THE DATABASE
            //FAST GUESS INIT PAGINATION AND DELETE OFFER BY ID with stored procedure only offer id cascade will take the rest out
        });
        add(jButtonDeleteSelectedApplication);

        jButtonCreateNewJobOffer = new JButton("Create New Job Offer");
        jButtonCreateNewJobOffer.addActionListener(e -> {

            MainFrame.router.removePanel(jFrame);
            MainFrame.router.createNewJobOffer(jFrame);

        });
        add(jButtonCreateNewJobOffer);

        jButtonLogOut = new JButton("Logout");
        jButtonLogOut.addActionListener(e -> {

            // DataProviderCreateNewUser.removeCurrentCompany();
            MainFrame.router.removePanel(jFrame);
            MainFrame.router.showHomepagePanel(jFrame);

        });
        add(jButtonLogOut);

    }


}
