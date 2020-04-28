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


       //router.showRegistrationPanel(this);
       // router.showManageProfilePanel(this);
       // router.showRegistrationPanelCompany(this);
        router.showHomepagePanel(this);
    }


}
