package Views.Panels.Company;

import Helpers.TableUtils.TableUtility;
import Views.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class HomePageCompanyPanel extends JPanel {
    public MainFrame jFrame;
    public JButton jButtonLogOut;
    public JButton jButtonViewApplication;
    public JTable jTableJobOffers;


    public HomePageCompanyPanel(MainFrame jFrame) {
        this.jFrame = jFrame;

        jTableJobOffers = new JTable();
        JScrollPane pane = new JScrollPane();
        MainFrame.dataProviderTableJobOfferss.model = new DefaultTableModel();
        pane.setViewportView(jTableJobOffers);
        TableUtility.autoResizeColumn(jTableJobOffers);
        add(pane);

        jButtonViewApplication = new JButton("Check application");
        jButtonViewApplication.addActionListener(e -> {

        });
        add(jButtonViewApplication);

        jButtonLogOut = new JButton("Logout");
        jButtonLogOut.addActionListener(e -> {

        });
        add(jButtonLogOut);

    }


}
