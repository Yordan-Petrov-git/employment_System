package Views.Panels.Company;

import Views.MainFrame;

import javax.swing.*;

public class ManageJobOffer extends JPanel {

    private JTextField JTextFieldJobTitle;
    private JTextField JTextFieldCity;
    private JTextField JTextFieldPosition;
    private JTextArea JTextFieldDescription;
    private JTextField JTextFieldNetSalary;
    private JTextField JTextFieldJobType;
    private JButton jButtonBack;
    private JButton jButtonSaveOffer;
    public MainFrame jFrame;

    public ManageJobOffer(MainFrame jFrame) {
        this.jFrame = jFrame;


        JTextFieldJobTitle = new JTextField("Job Title");
        add(JTextFieldJobTitle);

        JTextFieldCity = new JTextField("City");
        add(JTextFieldCity);

        JTextFieldPosition = new JTextField("Position");
        add(JTextFieldPosition);

        JTextFieldDescription = new JTextArea("Description");
        add(JTextFieldDescription);

        JTextFieldNetSalary = new JTextField("Salary");
        add(JTextFieldNetSalary);


        JTextFieldJobType = new JTextField("Job Type");
        add(JTextFieldJobType);

        jButtonSaveOffer = new JButton("Save");
        jButtonSaveOffer.addActionListener(e -> {


        });
        add(jButtonSaveOffer);



        jButtonBack = new JButton("Back");
        jButtonBack.addActionListener(e -> {

            MainFrame.router.removePanel(jFrame);
            MainFrame.router.showHomePageCompanyPanel(jFrame);

        });
        add(jButtonBack);
    }
}
