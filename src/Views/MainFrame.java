package Views;

import DataProvider.DataProvider;
import Views.Panels.LoginPanel;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {


   // public JTable table;
    public DataProvider dataProvider = new DataProvider();

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


        showLoginPanel();
    }
    public void showLoginPanel() {
        //Shows login panel for the bar system
        LoginPanel loginPanel = new LoginPanel(this);
        loginPanel.setSize(600,800);
        add(loginPanel);
    }

}
