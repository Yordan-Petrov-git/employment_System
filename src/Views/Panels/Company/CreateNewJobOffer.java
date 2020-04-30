package Views.Panels.Company;

import DataProvider.DataProviderCreateNewCompany;
import DataProvider.DataProviderCreateNewJobOffer;
import Views.MainFrame;

import javax.swing.*;

public class CreateNewJobOffer extends JPanel {


    private JTextField JTextFieldJobTitle;
    private JTextField JTextFieldCity;
    private JTextField JTextFieldPosition;
    private JTextArea JTextFieldDescription;
    private JTextField JTextFieldNetSalary;
    private JButton jCreateOffer;
    private JButton jButtonBack;
    private  JComboBox <String> jComboBoxPage;

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


        jComboBoxPage = new JComboBox<String>();
        jComboBoxPage.addItem("Full-time job");
        jComboBoxPage.addItem("Temporary position");
        add(jComboBoxPage);

        jCreateOffer = new JButton("Create Offer");
        jCreateOffer.addActionListener(e -> {

            String title = JTextFieldJobTitle.getText();
            String city = JTextFieldCity.getText();
            String position = JTextFieldPosition.getText();
            String description = JTextFieldDescription.getText();
            String salary = JTextFieldNetSalary.getText();
            String type = jComboBoxPage.getItemAt(jComboBoxPage.getSelectedIndex());

            createOffer(title, city, position, description, salary, type);

        });
        add(jCreateOffer);

        jButtonBack = new JButton("Back");
        jButtonBack.addActionListener(e -> {

            MainFrame.router.removePanel(jFrame);
            MainFrame.router.showHomePageCompanyPanel(jFrame);

        });
        add(jButtonBack);
    }

    public static void createOffer(String title, String city, String position
            , String desription, String salary, String type) {


        int id = (int) DataProviderCreateNewCompany.getCurrentCompany().getId();
        DataProviderCreateNewJobOffer.registerJobOffer(id, title, city, position, desription, salary, type);

    }
}

