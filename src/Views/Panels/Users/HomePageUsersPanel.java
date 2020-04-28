package Views.Panels.Users;

import Helpers.TableUtils.TableUtility;
import Views.MainFrame;
import com.mysql.cj.xdevapi.Table;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class HomePageUsersPanel extends JPanel {

    public MainFrame jFrame;
    public JTable jTableJobOffers;
    public JButton jButtonLogout;

private JTable tableJobOffers = new JTable();

    public HomePageUsersPanel(MainFrame jFrame) {
        this.jFrame = jFrame;

        jTableJobOffers = new JTable();
        JScrollPane pane = new JScrollPane();
        String[] columnIdentifiers = {"Company","Title","City","Salary","DateAdded"};
        MainFrame.dataProviderTableJobOffers.model = new DefaultTableModel();
        MainFrame.dataProviderTableJobOffers.model.setColumnIdentifiers(columnIdentifiers);
        jTableJobOffers.setModel(MainFrame.dataProviderTableJobOffers.model);
        pane.setViewportView(jTableJobOffers);
        TableUtility.autoResizeColumn(jTableJobOffers);
        add(pane);

        jButtonLogout = new JButton("Logout");
        jButtonLogout.addActionListener(e -> {

            MainFrame.router.removePanel(jFrame);
            MainFrame.router.showHomepagePanel(jFrame);

        });
        add(jButtonLogout);
    }
}
