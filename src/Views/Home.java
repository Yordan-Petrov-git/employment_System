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

    private JLabel jLabelStatusHalaman;
    private JLabel jLabelTotalData;

    private JButton jButtonLast;
    private JButton jButtonNext;
    private JButton jButtonPrevious;
    private JButton jButtonFirst;

    public Integer page = 1;
    public Integer rowCountPerPage = 10;
    public Integer totalPage = 1;
    public Integer totalData = 0;
    public JComboBox<String> jComboBoxPage;

    public  DataProviderTableJobOffers productTableModel;

    public Home(MainFrame jFrame) {
        Home.jFrame = jFrame;

        jFrame.setSize(800, 600);

        jLabelTitle = new JLabel("Homepage");
        add(jLabelTitle);


        jTableJobOffers = new JTable();
        JScrollPane pane = new JScrollPane();
        String[] columnIdentifiers = {"Company","Title","City","Salary","DateAdded"};
        MainFrame.dataProviderTableJobOfferss.model = new DefaultTableModel();
        MainFrame.dataProviderTableJobOfferss.model.setColumnIdentifiers(columnIdentifiers);
        jTableJobOffers.setModel(MainFrame.dataProviderTableJobOfferss.model);
        pane.setViewportView(jTableJobOffers);
        TableUtility.autoResizeColumn(jTableJobOffers);
        add(pane);

        jComboBoxPage = new JComboBox<String>();
        jComboBoxPage.addItem("10");
        jComboBoxPage.addItem("20");
        jComboBoxPage.addItem("30");
        jComboBoxPage.addItem("40");
        jComboBoxPage.addItem("50");
        add(jComboBoxPage);

        jComboBoxPage.addItemListener(new ItemListener() {
            // Change data in jtable on combobox change
            public void itemStateChanged(ItemEvent e) {
                initPagination();////Shows specific amaunt of rows in the jtable on combobox selection
            }
        });
       //Shows first paged rows in the jtable


        jLabelStatusHalaman = new JLabel("");
        add(jLabelStatusHalaman);

        jLabelTotalData = new JLabel("");
        add(jLabelTotalData);


        jButtonLast = new JButton("last");
        jButtonLast.addActionListener(e -> {
            page = totalPage;
            initPagination();
        });
        add(jButtonLast);
        jButtonLast.setEnabled(true);

        jButtonNext = new JButton("next");
        jButtonNext.addActionListener(e -> {
            if (page < totalPage) {
                page++;//Page counter increment
                initPagination();//Update table rows with paging
            }
        });
        add(jButtonNext);
        jButtonNext.setEnabled(true);

        jButtonPrevious= new JButton("previous");
        jButtonPrevious.addActionListener(e -> {
            if (page > 1) {
                page--;//Page counter decrement
                initPagination();//Update table rows with paging
            }
        });
        add(jButtonPrevious);
        jButtonPrevious.setEnabled(true);

        jButtonFirst= new JButton("first");
        jButtonFirst.addActionListener(e -> {
            page = 1;//Sets page counter to first page
            initPagination();//Update table rows with paging
        });
        add(jButtonFirst);
        jButtonFirst.setEnabled(true);



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
        initPagination();
    }


    public static void showLogin() {

        MainFrame.router.removePanel(jFrame);
        MainFrame.router.showLoginPanel(jFrame);

    }


    private void initPagination() {
        //Initializes pagiantion of rows in jtable
        //Counts total rows in SQL DB
        totalData = DataProviderTableJobOffers.count();
        //Testing coutn utput
        System.out.println(totalData);

        rowCountPerPage = Integer.valueOf(jComboBoxPage.getSelectedItem().toString());
        Double totalPageD = Math.ceil(totalData.doubleValue() / rowCountPerPage.doubleValue());
        totalPage = totalPageD.intValue();
        //Bquttons for page navigation
        //Buttons for first page adn next page
        if (page.equals(1)) {
            jButtonFirst.setEnabled(false);
            jButtonPrevious.setEnabled(false);
        } else {
            jButtonFirst.setEnabled(true);
            jButtonPrevious.setEnabled(true);
        }
        //Buittons for last apge and next page
        if (page.equals(totalPage)) {
            jButtonLast.setEnabled(false);
            jButtonNext.setEnabled(false);
        } else {
            jButtonLast.setEnabled(true);
            jButtonNext.setEnabled(true);
        }

        if (page > totalPage) {
            page = 1;
        }
        //New instance of table for client table model
        productTableModel = new DataProviderTableJobOffers();
        //Popialte table
        productTableModel.setList(DataProviderTableJobOffers.findAll(page, rowCountPerPage));
        //Set model
        jTableJobOffers.setModel(productTableModel);
       // jLabelStatusHalaman.setText("Page " + page + " for " + totalPage);//Page position count
       // jLabelTotalData.setText(("Row count " + totalData));//Row count
        //Resizes jtables columns
        TableUtility.autoResizeColumn(jTableJobOffers);
    }

   // int index = jTableJobOffers.getSelectedRow();

}