package Views.Panels.Company;

import DataProvider.DataProviderCreateNewCompany;
import DataProvider.DataProviderCreateNewJobOffer;
import DataProvider.DataProviderCreateNewUser;
import DataProvider.DataProviderTableJobOffers;
import Helpers.TableUtils.TableUtility;
import Views.MainFrame;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class HomePageCompanyPanel extends JPanel {

    public MainFrame jFrame;
    private JButton jButtonLogOut;
    private JButton jButtonViewApplication;
    private JButton jButtonDeleteSelectedApplication;
    private JButton jButtonCreateNewJobOffer;
    private JTable jTableJobOffers;


    //--------------------------
    //========Paging==========
    private JLabel jLabelTotalData;
    private JButton jButtonLast;
    private JButton jButtonNext;
    private JButton jButtonPrevious;
    private JButton jButtonFirst;
    public JComboBox<String> jComboBoxPage;
    //-------------------------

    private JLabel jLabelStatus;


    public DataProviderTableJobOffers productTableModel;
    public static int idOfOSelectedJobOffer;
    public Integer index;

    public HomePageCompanyPanel(MainFrame jFrame) {

        this.jFrame = jFrame;


        String loginDetails = "Welcome" +
                " : " +
                DataProviderCreateNewCompany.getCurrentCompany().getCompanyName();
        jLabelStatus = new JLabel(loginDetails);
        add(jLabelStatus);

        //--------------------------------------------------------------
        //=======================Paging navigation========================

        jComboBoxPage = new JComboBox<String>();
        jComboBoxPage.addItem("10");
        jComboBoxPage.addItem("15");
        jComboBoxPage.addItem("20");
        jComboBoxPage.addItem("25");
        add(jComboBoxPage);

        jComboBoxPage.addItemListener(new ItemListener() {
            // Change data in jtable on combobox change
            public void itemStateChanged(ItemEvent e) {
                refreshTable();
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
            refreshTable();
        });
        add(jButtonLast);
        //jButtonLast.setEnabled(true);

        jButtonNext = new JButton("next");
        jButtonNext.addActionListener(e -> {
            if (jFrame.page < jFrame.totalPage) {
                jFrame.page++;
                refreshTable();
            }

        });
        add(jButtonNext);
        //jButtonNext.setEnabled(true);


        jButtonPrevious = new JButton("previous");
        jButtonPrevious.addActionListener(e -> {
            if (jFrame.page > 1) {
                jFrame.page--;
                refreshTable();
            }
        });
        add(jButtonPrevious);
        // jButtonPrevious.setEnabled(true);

        jButtonFirst = new JButton("first");
        jButtonFirst.addActionListener(e -> {
            jFrame.page = 1;//Sets page counter to first page
            refreshTable();
        });
        add(jButtonFirst);

        //------------------------------------------------------------------

        //----------------------------------------------------------------
        //========================Table====================================

        jTableJobOffers = new JTable(productTableModel);
        JScrollPane pane = new JScrollPane();
        pane.setViewportView(jTableJobOffers);
        TableUtility.autoResizeColumn(jTableJobOffers);
        add(pane);

        //-----------------------------------------------------------------

        jButtonViewApplication = new JButton("Check application");
        jButtonViewApplication.addActionListener(e -> {

            index = this.jTableJobOffers.getSelectedRow();
            idOfOSelectedJobOffer = Integer.parseInt(this.jTableJobOffers.getValueAt(index, 7).toString());
            MainFrame.router.removePanel(jFrame);
            MainFrame.router.viewJobOfersCompany(jFrame);

        });
        add(jButtonViewApplication);


        jButtonDeleteSelectedApplication = new JButton("Delete offer");
        jButtonDeleteSelectedApplication.addActionListener(e -> {

            index = this.jTableJobOffers.getSelectedRow();
            idOfOSelectedJobOffer = Integer.parseInt(this.jTableJobOffers.getValueAt(index, 7).toString());
//            //delete offer
            deleteJobOffer();

        });
        add(jButtonDeleteSelectedApplication);

        jButtonCreateNewJobOffer = new JButton("Create Offer");
        jButtonCreateNewJobOffer.addActionListener(e -> {

            MainFrame.router.removePanel(jFrame);
            MainFrame.router.createNewJobOffer(jFrame);

        });
        add(jButtonCreateNewJobOffer);


        jButtonLogOut = new JButton("Logout");
        jButtonLogOut.addActionListener(e -> {

            logout();

        });
        add(jButtonLogOut);

        refreshTable();//Refreshes table content on initialization
    }

    public void refreshTable() {
        //Refreshes table content
        DataProviderTableJobOffers.loadJobOffersInTable(jTableJobOffers, (int) DataProviderCreateNewCompany.getCurrentCompany().getId());
    }

    public void deleteJobOffer() {
        //Delete fob offer from JTable
        DataProviderCreateNewJobOffer.deleteJobOffer(idOfOSelectedJobOffer);
        //Refreshes table content aether deletion
        refreshTable();
    }

    public void logout() {
        DataProviderCreateNewCompany.removeCurrentCompany();//Removes current user on logout
        MainFrame.router.removePanel(jFrame);//Removes panel
        MainFrame.router.showHomepagePanel(jFrame);//Shows homepage panel
    }
}
