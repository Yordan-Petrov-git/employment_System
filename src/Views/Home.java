package Views;

import DataProvider.DataProviderTableJobOffers;
import Helpers.TableUtils.TableUtility;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Home extends JPanel {

    private static MainFrame jFrame;
    private JLabel jLabelTitle;
    private JButton jButtonLoginAsUser;
    private JButton jButtonLoginAsCompany;
    private JTable jTableJobOffers;

    private JLabel jLabelStatus;
    private JLabel jLabelTotalData;

    private JButton jButtonLast;
    private JButton jButtonNext;
    private JButton jButtonPrevious;
    private JButton jButtonFirst;


    public JComboBox<String> jComboBoxPage;

    public DataProviderTableJobOffers productTableModel;

    public Home(MainFrame jFrame) {

        Home.jFrame = jFrame;
        jFrame.setSize(1300, 500);

        jLabelTitle = new JLabel("Homepage");
        add(jLabelTitle);

        jTableJobOffers = new JTable();
        JScrollPane pane = new JScrollPane();
        //  String[] columnIdentifiers = {"Company","Title","City","Salary","DateAdded"};
        MainFrame.dataProviderTableJobOfferss.model = new DefaultTableModel();
        //   MainFrame.dataProviderTableJobOfferss.model.setColumnIdentifiers(columnIdentifiers);
        // jTableJobOffers.setModel(MainFrame.dataProviderTableJobOfferss.model);
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
                initPagination(jTableJobOffers,jFrame
                        , jButtonLast,jButtonNext,jButtonPrevious,jButtonFirst
                        ,jLabelStatus,jLabelTotalData,jComboBoxPage);
            }
        });
        //Shows first paged rows in the jtable


        jLabelStatus = new JLabel("");
        add(jLabelStatus);

        jLabelTotalData = new JLabel("");
        add(jLabelTotalData);


        jButtonLast = new JButton("last");
        jButtonLast.addActionListener(e -> {
            MainFrame.page = MainFrame.totalPage;
            initPagination(jTableJobOffers,jFrame
                    , jButtonLast,jButtonNext,jButtonPrevious,jButtonFirst
                    ,jLabelStatus,jLabelTotalData,jComboBoxPage);
        });
        add(jButtonLast);
        //jButtonLast.setEnabled(true);

        jButtonNext = new JButton("next");
        jButtonNext.addActionListener(e -> {
            if (MainFrame.page < MainFrame.totalPage) {
                MainFrame.page++;
                initPagination(jTableJobOffers,jFrame
                        , jButtonLast,jButtonNext,jButtonPrevious,jButtonFirst
                        ,jLabelStatus,jLabelTotalData,jComboBoxPage);
            }



        });
        add(jButtonNext);
        //jButtonNext.setEnabled(true);


        jButtonPrevious = new JButton("previous");
        jButtonPrevious.addActionListener(e -> {
            if (MainFrame.page > 1) {
                MainFrame.page--;
                initPagination(jTableJobOffers,jFrame
                        , jButtonLast,jButtonNext,jButtonPrevious,jButtonFirst
                        ,jLabelStatus,jLabelTotalData,jComboBoxPage);
            }
        });
        add(jButtonPrevious);
       // jButtonPrevious.setEnabled(true);

        jButtonFirst = new JButton("first");
        jButtonFirst.addActionListener(e -> {
            MainFrame.page = 1;//Sets page counter to first page
            initPagination(jTableJobOffers,jFrame
                    , jButtonLast,jButtonNext,jButtonPrevious,jButtonFirst
                    ,jLabelStatus,jLabelTotalData,jComboBoxPage);
        });
        add(jButtonFirst);
       // jButtonFirst.setEnabled(true);


        jButtonLoginAsUser = new JButton("Login as User");
        jButtonLoginAsUser.addActionListener(e -> {


            MainFrame.loginAs = LoginEnum.LOGIN_AS_USER;
            showLogin();


        });
        add(jButtonLoginAsUser);

        jButtonLoginAsCompany = new JButton("Login as Company");
        jButtonLoginAsCompany.addActionListener(e -> {

            MainFrame.loginAs = LoginEnum.LOGIN_AS_COMPANY;
            showLogin();

        });
        add(jButtonLoginAsCompany);

        initPagination(jTableJobOffers,jFrame
                ,jButtonLast,jButtonNext,jButtonPrevious,jButtonFirst
                ,jLabelStatus,jLabelTotalData,jComboBoxPage);
    }


    public static void showLogin() {

        MainFrame.router.removePanel(jFrame);
        MainFrame.router.showLoginPanel(jFrame);

    }

    private void initPagination(JTable jTable
            ,MainFrame mainFrame
            ,JButton last
            ,JButton next
            ,JButton previous
            ,JButton first
            ,JLabel status
            ,JLabel totalData
            ,JComboBox pageSelect) {
        //Initializes pagiantion of rows in jtable
        //Counts total rows in SQL DB
        mainFrame.totalData = DataProviderTableJobOffers.count();
        //Testing coutn utput
        System.out.println(mainFrame.totalData);

        mainFrame.rowCountPerPage = Integer.valueOf(pageSelect.getSelectedItem().toString());
        Double totalPageD = Math.ceil(mainFrame.totalData.doubleValue() / mainFrame.rowCountPerPage.doubleValue());
        mainFrame.totalPage = totalPageD.intValue();
        //Bquttons for page navigation
        //Buttons for first page adn next page
        if (mainFrame.page.equals(1)) {
            first.setEnabled(false);
            previous.setEnabled(false);
        } else {
            first.setEnabled(true);
            previous.setEnabled(true);
        }
        //Buittons for last apge and next page
        if (mainFrame.page.equals(mainFrame.totalPage)) {
            last.setEnabled(false);
            next.setEnabled(false);
        } else {
            last.setEnabled(true);
            next.setEnabled(true);
        }

        if (mainFrame.page > mainFrame.totalPage) {
            mainFrame.page = 1;
        }
        //New instance of table for client table model
        productTableModel = new DataProviderTableJobOffers();
        //Popialte table
        productTableModel.setList(DataProviderTableJobOffers.findAll(mainFrame.page, mainFrame.rowCountPerPage));
        //Set model
        jTable.setModel(productTableModel);
        status.setText("Page " + mainFrame.page + " for " + mainFrame.totalPage);//Page position count
        totalData.setText(("Row count " + mainFrame.totalData));//Row count
        //Resizes jtables columns
        TableUtility.autoResizeColumn(jTable);
    }

    // int index = jTableJobOffers.getSelectedRow();

}