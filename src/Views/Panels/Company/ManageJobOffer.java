package Views.Panels.Company;

import Views.MainFrame;

import javax.swing.*;

public class ManageJobOffer extends JPanel {
    public MainFrame jFrame;

    public JButton jButtonBack;
    public ManageJobOffer(MainFrame jFrame) {
        this.jFrame = jFrame;

        jButtonBack = new JButton("Back");
        jButtonBack.addActionListener(e -> {


        });
        add(jButtonBack);
    }
}
