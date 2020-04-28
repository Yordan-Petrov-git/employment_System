package Views;

import DataProvider.DataProvider;
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

    private JButton jButtonLast;
    private JButton jButtonNext;
    private JButton jButtonPrevious;
    private JButton jButtonFirst;

    public Integer page = 1;
    public Integer rowCountPerPage = 5;
    public Integer totalPage = 1;
    public Integer totalData = 0;
    public JComboBox<String> jComboBoxPage;


    public Home(MainFrame jFrame) {
        Home.jFrame = jFrame;

        jFrame.setSize(800, 600);



        jLabelTitle = new JLabel("Homepage");
        add(jLabelTitle);


        jTableJobOffers = new JTable();
        JScrollPane pane = new JScrollPane();
        String[] columnIdentifiers = {"Company","Title","City","Salary","DateAdded"};
        MainFrame.dataProviderTableJobOffers.model = new DefaultTableModel();
        MainFrame.dataProviderTableJobOffers.model.setColumnIdentifiers(columnIdentifiers);
        jTableJobOffers.setModel(MainFrame.dataProviderTableJobOffers.model);
        pane.setViewportView(jTableJobOffers);
        TableUtility.autoResizeColumn(jTableJobOffers);
        add(pane);

        jComboBoxPage = new JComboBox<String>();
        jComboBoxPage.addItem("10");
        jComboBoxPage.addItem("20");
        jComboBoxPage.addItem("30");
        jComboBoxPage.addItem("40");
        jComboBoxPage.addItem("50");
        jComboBoxPage.addItem("60");
        jComboBoxPage.addItem("70");
        jComboBoxPage.addItem("80");
        jComboBoxPage.addItem("90");
        jComboBoxPage.addItem("100");
        add(jComboBoxPage);

        jComboBoxPage.addItemListener(new ItemListener() {
            // Change data in jtable on combobox change
            public void itemStateChanged(ItemEvent e) {
                initPagination();////Shows specific amaunt of rows in the jtable on combobox selection
            }
        });
        initPagination();//Shows first paged rows in the jtable


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
        productTableModel = new ClientsTableModel();
        //Popialte table
        productTableModel.setList(findAll(page, rowCountPerPage));
        //Set model
        jTblClients.setModel(productTableModel);
        jLabelStatusHalaman.setText("Page " + page + " for " + totalPage);//Page position count
        jLabelTotalData.setText(("Row count " + totalData));//Row count
        //Resizes jtables columns
        ResizeForJTables.autoResizeColumn(jTblClients);
    }

//    public int count() {
//        //Counts SQL table all rows
//        int counter = 0;
//        try {
//            DataProvider.setAutoCommit(false);
//            preparedStatement = connct.prepareStatement("SELECT count(id) from clientdetails");
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                counter = rs.getInt("count(id)");
//            }
//            connct.commit();
//            connct.setAutoCommit(true);
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//            return counter;
//        }
//    }


   // int index = jTableJobOffers.getSelectedRow();

}