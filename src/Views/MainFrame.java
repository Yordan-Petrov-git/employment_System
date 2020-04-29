package Views;

import DataProvider.DataProviderTableJobOffers;
import DataProvider.LoginDataProvider;
import Models.User;
import Router.Router;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public static LoginDataProvider loginDataProvider = new LoginDataProvider();
    public static DataProviderTableJobOffers dataProviderTableJobOfferss = new DataProviderTableJobOffers();
    public static Router router = new Router();
    public static LoginEnum loginAs;
    public  Integer page = 1;
    public  Integer rowCountPerPage = 10;
    public  Integer totalPage = 1;
    public  Integer totalData = 0;



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

    public  void resetPagingCounters() {
        page = 1;
        rowCountPerPage = 10;
        totalPage = 1;
        totalData = 0;
    }

}
