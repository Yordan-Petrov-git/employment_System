package Views;

import DataProvider.DataProvider;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    public JTextField nameField;
    public JTextField priceField;
    public JComboBox sizeComboBox;
    public JButton createButton;
    public JButton removeButton;
    public JTable table;
    public DataProvider dataProvider = new DataProvider();

    public MainFrame() {
        super("Advance Sandwiches");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new FlowLayout());

        nameField = new JTextField("  Name  ");
        add(nameField);

        priceField = new JTextField("  Price  ");
        add(priceField);

        String[] sizesList = {"15cm", "30cm", "45cm"};
        sizeComboBox = new JComboBox(sizesList);
        sizeComboBox.addActionListener(this);
        add(sizeComboBox);

        createButton = new JButton("Create");
        createButton.addActionListener(this);
        add(createButton);

        removeButton = new JButton("Remove");
        removeButton.addActionListener(this);
        add(removeButton);

        table = new JTable();
        JScrollPane pane = new JScrollPane();
        String[] collumnIdentifiers = {"Name", "Size", "Price"};
        dataProvider.model = new DefaultTableModel();
        dataProvider.model.setColumnIdentifiers(collumnIdentifiers);
        table.setModel(dataProvider.model);
        pane.setViewportView(table);
        add(pane);

        dataProvider.loadSandwichesFromDatabase();
    }


}
