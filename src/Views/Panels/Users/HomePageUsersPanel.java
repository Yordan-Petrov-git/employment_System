package Views.Panels.Users;

import DataProvider.DataProviderCreateNewCompany;
import DataProvider.DataProviderCreateNewJobOffer;
import DataProvider.DataProviderCreateNewUser;
import DataProvider.DataProviderTableJobOffers;
import Helpers.TableUtils.TableUtility;
import Models.JobOffer;
import Views.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class HomePageUsersPanel extends JPanel {

    public MainFrame jFrame;
    public JTextField jTextFieldSearch;
    public JTable jTableJobOffers;
    private JButton jButtonApply;
    private JButton jButtonSearch;
    private JLabel jLabelLoggedInAs;
    public JButton jButtonLogout;
    private JLabel jLabelStatus;
    private JLabel jLabelTotalData;
    private JButton jButtonLast;
    private JButton jButtonNext;
    private JButton jButtonPrevious;
    private JButton jButtonFirst;


    public DataProviderTableJobOffers productTableModel;

    public JComboBox<String> jComboBoxPage;

    public static int index;
    public static int id;
    public static int userID;

    private JTable tableJobOffers = new JTable();

    public HomePageUsersPanel(MainFrame jFrame) {
        this.jFrame = jFrame;

        jTableJobOffers = new JTable();
        JScrollPane pane = new JScrollPane();
        pane.setViewportView(jTableJobOffers);
        TableUtility.autoResizeColumn(jTableJobOffers);
        add(pane);

        //---------------------------------------------------------
        //===========LOGED IN AS TEXT==========================
        String loginDetails = "Welcome" +
                " : " +
                DataProviderCreateNewUser.getCurrentUser().getFirstName() +
                " " +
                DataProviderCreateNewUser.getCurrentUser().getFamilyName();
        jLabelStatus = new JLabel(String.valueOf(loginDetails));
        add(jLabelStatus);
        //---------------------------------------------------

        jTextFieldSearch = new JTextField("Search Offers");
        add(jTextFieldSearch);

        jButtonSearch = new JButton("Search");
        jButtonSearch.addActionListener(e -> {

            DataProviderTableJobOffers.search(jTableJobOffers,jTextFieldSearch.getText());

        });
        add(jButtonSearch);

        jButtonApply = new JButton("Apply for offer");
        jButtonApply.addActionListener(e -> {

            index = this.jTableJobOffers.getSelectedRow();
            id = Integer.parseInt(this.jTableJobOffers.getValueAt(index, 7).toString());
            userID = (int) DataProviderCreateNewUser.getCurrentUser().getId();

            MainFrame.router.removePanel(jFrame);
            MainFrame.router.applyForOffer(jFrame);

        });
        add(jButtonApply);



        jComboBoxPage = new JComboBox<String>();
        jComboBoxPage.addItem("10");
        jComboBoxPage.addItem("15");
        jComboBoxPage.addItem("20");
        jComboBoxPage.addItem("25");
        add(jComboBoxPage);

        jComboBoxPage.addItemListener(new ItemListener() {
            // Change data in jtable on combobox change
            public void itemStateChanged(ItemEvent e) {
                updateJobOffersTable();
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
            updateJobOffersTable();
        });
        add(jButtonLast);
        //jButtonLast.setEnabled(true);

        jButtonNext = new JButton("next");
        jButtonNext.addActionListener(e -> {
            if (jFrame.page < jFrame.totalPage) {
                jFrame.page++;
                updateJobOffersTable();
            }

        });
        add(jButtonNext);
        //jButtonNext.setEnabled(true);


        jButtonPrevious = new JButton("previous");
        jButtonPrevious.addActionListener(e -> {
            if (jFrame.page > 1) {
                jFrame.page--;
                updateJobOffersTable();
            }
        });
        add(jButtonPrevious);
        // jButtonPrevious.setEnabled(true);

        jButtonFirst = new JButton("first");
        jButtonFirst.addActionListener(e -> {
            jFrame.page = 1;//Sets page counter to first page
            updateJobOffersTable();
        });
        add(jButtonFirst);
        //------------------------------------------------------------------------------
        jButtonLogout = new JButton("Edit Account");
        jButtonLogout.addActionListener(e -> {

            MainFrame.router.removePanel(jFrame);
            MainFrame.router.showManageUserProfilePanel(jFrame);

        });
        add(jButtonLogout);

        jButtonLogout = new JButton("Logout");
        jButtonLogout.addActionListener(e -> {
            DataProviderCreateNewUser.removeCurrentUser();
            MainFrame.router.removePanel(jFrame);
            MainFrame.router.showHomepagePanel(jFrame);

        });
        add(jButtonLogout);

        updateJobOffersTable();

    }

    public void updateJobOffersTable() {

        DataProviderTableJobOffers.initPagination(jTableJobOffers
                , jButtonLast, jButtonNext, jButtonPrevious, jButtonFirst
                , jLabelStatus, jLabelTotalData, jComboBoxPage, productTableModel);

    }

}
