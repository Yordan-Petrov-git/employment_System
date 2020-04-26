package Views;



import DataProvider.LoginDataProvider;
import Views.Panels.LoginPanel;
import Views.Panels.RegisterNewAccountPanel;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {


   // public JTable table;
   public LoginDataProvider loginDataProvider = new LoginDataProvider();
   // public Router router = new Router();
    public MainFrame() {
        super("JobHunter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Centers the form
        this.setLocationRelativeTo(null);
        setSize(800, 600);
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


       // router.showLoginPanel();
       showLoginPanel();
       // showRegistrationPanel();
    }

    public void showLoginPanel() {
        //Shows login panel for the bar system
        LoginPanel loginPanel = new LoginPanel(this);
        loginPanel.setSize(600,800);
        add(loginPanel);
    }

    public void showRegistrationPanel() {
        //Shows login panel for the bar system
        RegisterNewAccountPanel registerNewAccount = new RegisterNewAccountPanel(this);
        registerNewAccount.setSize(600,800);
        add(registerNewAccount);
    }
}
