package Views.Panels.Company;

import DataProvider.DataProviderCreateNewUser;
import DataProvider.DataProviderTableJobOffers;
import Helpers.TableUtils.TableUtility;
import Views.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class HomePageCompanyPanel extends JPanel {
    public MainFrame jFrame;
    public JButton jButtonLogOut;
    public JButton jButtonViewApplication;
    public JButton jButtonManageApplication;
    public JButton jButtonDeleteSelectedApplication;
    public JButton jButtonCreateNewJobOffer;
    public JTable jTableJobOffers;


    private JLabel jLabelStatus;
    private JLabel jLabelTotalData;

    private JButton jButtonLast;
    private JButton jButtonNext;
    private JButton jButtonPrevious;
    private JButton jButtonFirst;

    private JButton jButtonManageProfile;

    public JComboBox<String> jComboBoxPage;

    public DataProviderTableJobOffers productTableModel;

    public HomePageCompanyPanel(MainFrame jFrame) {
        this.jFrame = jFrame;

        //TODO ADD TABLE WITH JOB OFFERS FOR THE SPECIFIC LOGGED IN COMPANY BY ID OT WHTEVER IDENTIFIER


        jTableJobOffers = new JTable();
        JScrollPane pane = new JScrollPane();
        MainFrame.dataProviderTableJobOfferss.model = new DefaultTableModel();
        pane.setViewportView(jTableJobOffers);
        TableUtility.autoResizeColumn(jTableJobOffers);
        add(pane);

        jComboBoxPage = new JComboBox<String>();
        jComboBoxPage.addItem("10");
        jComboBoxPage.addItem("15");
        jComboBoxPage.addItem("20");
        jComboBoxPage.addItem("25");
        add(jComboBoxPage);

        jComboBoxPage.addItemListener(new ItemListener() {
            // Change data in jtable on combobox change
            public void itemStateChanged(ItemEvent e) {
                TableUtility.initPagination(jTableJobOffers,jFrame
                        ,jButtonLast,jButtonNext,jButtonPrevious,jButtonFirst
                        ,jLabelStatus,jLabelTotalData,jComboBoxPage,productTableModel,1);
            }
        });
        //Shows first paged rows in the jtable


        jLabelStatus = new JLabel("");
        add(jLabelStatus);

        jLabelTotalData = new JLabel("");
        add(jLabelTotalData);


        jButtonLast = new JButton("last");
        jButtonLast.addActionListener(e -> {
            jFrame.page = jFrame.totalPage;
            TableUtility.initPagination(jTableJobOffers,jFrame
                    ,jButtonLast,jButtonNext,jButtonPrevious,jButtonFirst
                    ,jLabelStatus,jLabelTotalData,jComboBoxPage,productTableModel,1);
        });
        add(jButtonLast);
        //jButtonLast.setEnabled(true);

        jButtonNext = new JButton("next");
        jButtonNext.addActionListener(e -> {
            if (jFrame.page < jFrame.totalPage) {
                jFrame.page++;
                TableUtility.initPagination(jTableJobOffers,jFrame
                        ,jButtonLast,jButtonNext,jButtonPrevious,jButtonFirst
                        ,jLabelStatus,jLabelTotalData,jComboBoxPage,productTableModel,1);
            }



        });
        add(jButtonNext);
        //jButtonNext.setEnabled(true);


        jButtonPrevious = new JButton("previous");
        jButtonPrevious.addActionListener(e -> {
            if (jFrame.page > 1) {
                jFrame.page--;
                TableUtility.initPagination(jTableJobOffers,jFrame
                        ,jButtonLast,jButtonNext,jButtonPrevious,jButtonFirst
                        ,jLabelStatus,jLabelTotalData,jComboBoxPage,productTableModel,1);
            }
        });
        add(jButtonPrevious);
        // jButtonPrevious.setEnabled(true);

        jButtonFirst = new JButton("first");
        jButtonFirst.addActionListener(e -> {
            jFrame.page = 1;//Sets page counter to first page
            TableUtility.initPagination(jTableJobOffers,jFrame
                    ,jButtonLast,jButtonNext,jButtonPrevious,jButtonFirst
                    ,jLabelStatus,jLabelTotalData,jComboBoxPage,productTableModel,1);
        });
        add(jButtonFirst);
        // jButtonFirst.setEnabled(true);

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

        jButtonDeleteSelectedApplication = new JButton("Delete offer");
        jButtonDeleteSelectedApplication.addActionListener(e -> {

            //TODO ADD  WAY TO DELETE SELECTED offers FORM THE TABLE AND THE DATABASE
            //FAST GUESS INIT PAGINATION AND DELETE OFFER BY ID with stored procedure only offer id cascade will take the rest out
        });
        add(jButtonDeleteSelectedApplication);

        jButtonCreateNewJobOffer = new JButton("Create Offer");
        jButtonCreateNewJobOffer.addActionListener(e -> {

            MainFrame.router.removePanel(jFrame);
            MainFrame.router.createNewJobOffer(jFrame);

        });
        add(jButtonCreateNewJobOffer);




        jButtonManageProfile  = new JButton("Edit profile");
        jButtonManageProfile .addActionListener(e -> {

            MainFrame.router.removePanel(jFrame);
            MainFrame.router.showManageProfilePanel(jFrame);

        });
        add(jButtonManageProfile);

        jButtonLogOut = new JButton("Logout");
        jButtonLogOut.addActionListener(e -> {

            // DataProviderCreateNewUser.removeCurrentCompany();
            MainFrame.router.removePanel(jFrame);
            MainFrame.router.showHomepagePanel(jFrame);

        });
        add(jButtonLogOut);

        TableUtility.initPagination(jTableJobOffers,jFrame
                ,jButtonLast,jButtonNext,jButtonPrevious,jButtonFirst
                ,jLabelStatus,jLabelTotalData,jComboBoxPage,productTableModel,1);


    }


}
