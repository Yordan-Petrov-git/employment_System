package Views.Panels.Company;

import Views.MainFrame;

import javax.swing.*;

public class ManageProfile extends JPanel {
    public MainFrame jFrame;
    private JButton jButtonBack;

    public ManageProfile(MainFrame jFrame) {
        this.jFrame = jFrame;




        jButtonBack = new JButton("Back");
        jButtonBack.addActionListener(e -> {


        });
        add(jButtonBack);
    }
}
