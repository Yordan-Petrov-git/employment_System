package Views;

import DataProvider.LoginDataProvider;
import Router.Router;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    // public JTable table;
    public LoginDataProvider loginDataProvider = new LoginDataProvider();
    public Router router = new Router();

    public MainFrame() {

        super("JobHunter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Centers the form
        setSize(800, 600);
        this.setLocationRelativeTo(null);
        setLayout(new FlowLayout());


//                 Todo  :  add table headers for table with ads
        //
        //
//        table = new JTable();
//        JScrollPane pane = new JScrollPane();
//        String[] collumnIdentifiers = {"Name", "Size", "Price"};
//        dataProvider.model = new DefaultTableModel();
//        dataProvider.model.setColumnIdentifiers(collumnIdentifiers);
//        table.setModel(dataProvider.model);
//        pane.setViewportView(table);
//        add(pane);
//
//        dataProvider.loadSandwichesFromDatabase();


      //  router.showLoginPanel(this);
       router.showRegistrationPanel(this);
       // router.showManageProfilePanel(this);
    }


}
