package Views;

import DataProvider.DataProviderCreateNewUser;
import Helpers.ImageUtils.UtilsImages;
import Helpers.PasswordUtils.PasswordUtility;
import Helpers.UtilityGui.GuiUtils;
import Router.Router;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.sql.SQLException;

public class Home extends JPanel {


    private JLabel jLabelTitle;
    private JButton jButtonLoginAsUser;
    private JButton jButtonLoginAsCompany;
    private JTable jTableJobOffers;
    public Router router = new Router();
    public MainFrame jFrame;

    public Home(MainFrame jFrame) {
        this.jFrame = jFrame;
        jFrame.setSize(800, 600);


        jLabelTitle = new JLabel("Homepage");
        add(jLabelTitle);


        jTableJobOffers = new JTable();
        JScrollPane pane = new JScrollPane();
        String[] columnIdentifiers = {"", "", "","", "", ""};

//        dataProvider.model = new DefaultTableModel();
//        dataProvider.model.setColumnIdentifiers(columnIdentifiers);
//
//        jTableJobOffers.setModel(dataProvider.model);
        pane.setViewportView(jTableJobOffers);

        add(pane);


        jButtonLoginAsUser = new JButton("Login as User");
        jButtonLoginAsUser.addActionListener(e -> {

            router.removePanel(jFrame);
            router.showLoginPanel(jFrame);


        });
        add(jButtonLoginAsUser);

        jButtonLoginAsCompany = new JButton("Login as Company");
        jButtonLoginAsCompany.addActionListener(e -> {

            router.removePanel(jFrame);
            //   router.showLoginPanel(jFrame);

        });
        add(jButtonLoginAsCompany);

    }


}