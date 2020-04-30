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
                voidUpdateTable();

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
            voidUpdateTable();

        });
        add(jButtonLast);
        //jButtonLast.setEnabled(true);

        jButtonNext = new JButton("next");
        jButtonNext.addActionListener(e -> {
            if (jFrame.page < jFrame.totalPage) {
                jFrame.page++;
                voidUpdateTable();

            }



        });
        add(jButtonNext);
        //jButtonNext.setEnabled(true);


        jButtonPrevious = new JButton("previous");
        jButtonPrevious.addActionListener(e -> {
            if (jFrame.page > 1) {
                jFrame.page--;
                voidUpdateTable();

            }
        });
        add(jButtonPrevious);
       // jButtonPrevious.setEnabled(true);

        jButtonFirst = new JButton("first");
        jButtonFirst.addActionListener(e -> {
            jFrame.page = 1;//Sets page counter to first page
            voidUpdateTable();

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

        voidUpdateTable();

    }

    public void voidUpdateTable(){

        DataProviderTableJobOffers.initPagination(jTableJobOffers,jFrame
                ,jButtonLast,jButtonNext,jButtonPrevious,jButtonFirst
                ,jLabelStatus,jLabelTotalData,jComboBoxPage,productTableModel);
    }

    public static void showLogin() {

        MainFrame.router.removePanel(jFrame);
        MainFrame.router.showLoginPanel(jFrame);

    }


}