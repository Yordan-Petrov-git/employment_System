package Views;

import DataProvider.DataProviderTableJobOffers;
import DataProvider.LoginDataProvider;
import Router.Router;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public static LoginDataProvider loginDataProvider = new LoginDataProvider();
    public static DataProviderTableJobOffers dataProviderTableJobOfferss = new DataProviderTableJobOffers();
    public static Router router = new Router();
    public static LoginEnum loginAs;


    public MainFrame() {

        super("JobHunter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        this.setLocationRelativeTo(null);
        setLayout(new FlowLayout());

       //router.showManageProfilePanel(this);
       //router.showRegistrationPanelCompany(this);
        router.showHomepagePanel(this);
    }

}
