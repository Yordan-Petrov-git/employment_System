package Views.Panels.Company;

import Views.MainFrame;

import javax.swing.*;

public class ManageProfileCompany extends JPanel {
    public MainFrame jFrame;
    private JButton jButtonBack;

    public ManageProfileCompany(MainFrame jFrame) {
        this.jFrame = jFrame;


        jButtonBack = new JButton("Back");
        jButtonBack.addActionListener(e -> {


        });
        add(jButtonBack);
    }
}
