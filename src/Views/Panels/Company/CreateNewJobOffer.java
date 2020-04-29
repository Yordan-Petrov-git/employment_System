package Views.Panels.Company;

import Views.MainFrame;

import javax.swing.*;

public class CreateNewJobOffer extends JPanel{


    private JTextField JTextFieldJobTitle;
    private JTextField JTextFieldCity;
    private JTextField JTextFieldPosition;
    private JTextArea JTextFieldDescription;
    private JTextField JTextFieldNetSalary;
    private JTextField JTextFieldJobType;
    private JButton jCreateOffer;
    private JButton jButtonBack;


    public MainFrame jFrame;


    public CreateNewJobOffer(MainFrame jFrame) {
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


        //TODO add jComboBox with job types full time and part time isnted of  this text field
        JTextFieldJobType = new JTextField("Job Type");
        add(JTextFieldJobType);

        jCreateOffer = new JButton("Create Offer");
        jCreateOffer.addActionListener(e -> {


        });
        add(jCreateOffer);

        jButtonBack = new JButton("Back");
        jButtonBack.addActionListener(e -> {


        });
        add(jButtonBack);
    }
}

