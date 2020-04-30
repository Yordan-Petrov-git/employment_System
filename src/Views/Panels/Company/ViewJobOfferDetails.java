package Views.Panels.Company;

import javax.swing.*;

import DataProvider.DataProviderCreateNewCompany;
import DataProvider.DataProviderCreateNewUser;
import DataProvider.DataProviderTableJobOffers;
import Helpers.TableUtils.TableUtility;
import Views.MainFrame;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ViewJobOfferDetails extends JPanel {
    public MainFrame jFrame;
    private JButton jButtonBack;
    private JTable jTableJobOffers;


    private JLabel jLabelStatus;
    private JLabel jLabelTotalData;

    private JButton jButtonLast;
    private JButton jButtonNext;
    private JButton jButtonPrevious;
    private JButton jButtonFirst;

    public JComboBox<String> jComboBoxPage;

    public DataProviderCreateNewUser userTableModel;

    int id = (int)DataProviderCreateNewCompany.getCurrentCompany().getId();
    public  int index;

    public ViewJobOfferDetails(MainFrame jFrame) {
        this.jFrame = jFrame;

        //TODO ADD TABLE WITH JOB OFFERS FOR THE SPECIFIC LOGGED IN COMPANY BY ID OT WHTEVER IDENTIFIER


        jTableJobOffers = new JTable();
        JScrollPane pane = new JScrollPane();
       // MainFrame.dataProviderTableJobOfferss.model = new DefaultTableModel();
        pane.setViewportView(jTableJobOffers);
       // TableUtility.autoResizeColumn(jTableJobOffers);
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


        jButtonBack = new JButton("Back");
        jButtonBack.addActionListener(e -> {

            MainFrame.router.removePanel(jFrame);
            MainFrame.router.showHomePageCompanyPanel(jFrame);

        });
        add(jButtonBack);

        refreshTable();

        System.out.println(DataProviderCreateNewCompany.getCurrentCompany().getCompanyName());

    }

    public void refreshTable(){
//        TableUtility.initPaginationC(jTableJobOffers,jFrame
//                ,jButtonLast,jButtonNext,jButtonPrevious,jButtonFirst
//                ,jLabelStatus,jLabelTotalData,jComboBoxPage,productTableModel,id);
    }

}


