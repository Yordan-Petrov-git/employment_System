package Views.Panels.Users;

import DataProvider.DataProviderTableJobOffers;
import Helpers.TableUtils.TableUtility;
import Views.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class HomePageUsersPanel extends JPanel {

    public MainFrame jFrame;
    public JTable jTableJobOffers;
    private JButton jButtonApply;
    private JButton jButtonSearch;

    public JButton jButtonLogout;
    private JLabel jLabelStatus;
    private JLabel jLabelTotalData;
    private JButton jButtonLast;
    private JButton jButtonNext;
    private JButton jButtonPrevious;
    private JButton jButtonFirst;
    public DataProviderTableJobOffers productTableModel;

    public JComboBox<String> jComboBoxPage;

private JTable tableJobOffers = new JTable();

    public HomePageUsersPanel(MainFrame jFrame) {
        this.jFrame = jFrame;

        jTableJobOffers = new JTable();
        JScrollPane pane = new JScrollPane();
        //  String[] columnIdentifiers = {"Company","Title","City","Salary","DateAdded"};
        MainFrame.dataProviderTableJobOfferss.model = new DefaultTableModel();
        //   MainFrame.dataProviderTableJobOfferss.model.setColumnIdentifiers(columnIdentifiers);
        // jTableJobOffers.setModel(MainFrame.dataProviderTableJobOfferss.model);
        pane.setViewportView(jTableJobOffers);
        TableUtility.autoResizeColumn(jTableJobOffers);
        add(pane);

        jButtonApply = new JButton("Apply");
        jButtonApply.addActionListener(e -> {



        });
        add(jButtonApply);

        jButtonSearch = new JButton("Search");
        jButtonSearch.addActionListener(e -> {


        });
        add(jButtonSearch);



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
                        ,jLabelStatus,jLabelTotalData,jComboBoxPage,productTableModel);
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
                    ,jLabelStatus,jLabelTotalData,jComboBoxPage,productTableModel);
        });
        add(jButtonLast);
        //jButtonLast.setEnabled(true);

        jButtonNext = new JButton("next");
        jButtonNext.addActionListener(e -> {
            if (jFrame.page < jFrame.totalPage) {
                jFrame.page++;
                TableUtility.initPagination(jTableJobOffers,jFrame
                        ,jButtonLast,jButtonNext,jButtonPrevious,jButtonFirst
                        ,jLabelStatus,jLabelTotalData,jComboBoxPage,productTableModel);
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
                        ,jLabelStatus,jLabelTotalData,jComboBoxPage,productTableModel);
            }
        });
        add(jButtonPrevious);
        // jButtonPrevious.setEnabled(true);

        jButtonFirst = new JButton("first");
        jButtonFirst.addActionListener(e -> {
            jFrame.page = 1;//Sets page counter to first page
            TableUtility.initPagination(jTableJobOffers,jFrame
                    ,jButtonLast,jButtonNext,jButtonPrevious,jButtonFirst
                    ,jLabelStatus,jLabelTotalData,jComboBoxPage,productTableModel);
        });
        add(jButtonFirst);


        jButtonLogout = new JButton("Edit Account");
        jButtonLogout.addActionListener(e -> {

            MainFrame.router.removePanel(jFrame);
            MainFrame.router.showManageUserAccount(jFrame);

        });
        add(jButtonLogout);


        jButtonLogout = new JButton("Logout");
        jButtonLogout.addActionListener(e -> {

            MainFrame.router.removePanel(jFrame);
            MainFrame.router.showHomepagePanel(jFrame);

        });
        add(jButtonLogout);


        TableUtility.initPagination(jTableJobOffers,jFrame
                ,jButtonLast,jButtonNext,jButtonPrevious,jButtonFirst
                ,jLabelStatus,jLabelTotalData,jComboBoxPage,productTableModel);
    }
}
