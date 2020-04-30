package Views.Panels.Company;

import DataProvider.DataProviderCreateNewCompany;
import DataProvider.DataProviderCreateNewJobOffer;
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
    private JButton jButtonManageApplication;
    private JButton jButtonDeleteSelectedApplication;
    private JButton jButtonCreateNewJobOffer;
    private JTable jTableJobOffers;


    private JLabel jLabelStatus;
    private JLabel jLabelTotalData;

    private JButton jButtonLast;
    private JButton jButtonNext;
    private JButton jButtonPrevious;
    private JButton jButtonFirst;

    private JButton jButtonManageProfile;

    public JComboBox<String> jComboBoxPage;

    public DataProviderTableJobOffers productTableModel;

    public static  int  id = (int) DataProviderCreateNewCompany.getCurrentCompany().getId();
    public int index;

    public HomePageCompanyPanel(MainFrame jFrame) {
        this.jFrame = jFrame;

        //TODO ADD TABLE WITH JOB OFFERS FOR THE SPECIFIC LOGGED IN COMPANY BY ID OT WHTEVER IDENTIFIER


        jTableJobOffers = new JTable();
        JScrollPane pane = new JScrollPane();
       // MainFrame.dataProviderTableJobOffers.model = new DefaultTableModel();
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
        // jButtonFirst.setEnabled(true);

        jButtonViewApplication = new JButton("Check application");
        jButtonViewApplication.addActionListener(e -> {

            index = this.jTableJobOffers.getSelectedRow();
            id = Integer.parseInt(this.jTableJobOffers.getValueAt(index, 7).toString());
            MainFrame.router.removePanel(jFrame);
            MainFrame.router.viewJobOfersCompany(jFrame);

        });
        add(jButtonViewApplication);

        jButtonManageApplication = new JButton("Edit offer");
        jButtonManageApplication.addActionListener(e -> {

            MainFrame.router.removePanel(jFrame);
            MainFrame.router.editSelectedJobOffer(jFrame);

        });
        add(jButtonManageApplication);

        jButtonDeleteSelectedApplication = new JButton("Delete offer");
        jButtonDeleteSelectedApplication.addActionListener(e -> {

            index = this.jTableJobOffers.getSelectedRow();
            id = Integer.parseInt(this.jTableJobOffers.getValueAt(index, 7).toString());
            System.out.println(id);
            //delete offer
            DataProviderCreateNewJobOffer.deleteJobOffer(id);

            //TODO FIX TABLE REFRESH ON DELETION
            refreshTable();  //refresh table


        });
        add(jButtonDeleteSelectedApplication);

        jButtonCreateNewJobOffer = new JButton("Create Offer");
        jButtonCreateNewJobOffer.addActionListener(e -> {

            MainFrame.router.removePanel(jFrame);
            MainFrame.router.createNewJobOffer(jFrame);

        });
        add(jButtonCreateNewJobOffer);


        jButtonManageProfile = new JButton("Edit profile");
        jButtonManageProfile.addActionListener(e -> {

            MainFrame.router.removePanel(jFrame);
            MainFrame.router.showManageProfilePanel(jFrame);

        });
        add(jButtonManageProfile);

        jButtonLogOut = new JButton("Logout");
        jButtonLogOut.addActionListener(e -> {
            DataProviderCreateNewCompany.removeCurrentCompany();
            // DataProviderCreateNewUser.removeCurrentCompany();
            MainFrame.router.removePanel(jFrame);
            MainFrame.router.showHomepagePanel(jFrame);

        });
        add(jButtonLogOut);

        refreshTable();

        System.out.println(DataProviderCreateNewCompany.getCurrentCompany().getCompanyName());

    }

    public void refreshTable() {
        DataProviderTableJobOffers.initPaginationC(jTableJobOffers, jFrame
                , jButtonLast, jButtonNext, jButtonPrevious, jButtonFirst
                , jLabelStatus, jLabelTotalData, jComboBoxPage, productTableModel, id);
    }

}
