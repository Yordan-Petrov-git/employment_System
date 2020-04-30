package Views;

import DataProvider.DataProviderTableJobOffers;
import DataProvider.LoginDataProvider;
import Router.Router;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public static LoginDataProvider loginDataProvider = new LoginDataProvider();
    public static DataProviderTableJobOffers dataProviderTableJobOffers = new DataProviderTableJobOffers();
    public static Router router = new Router();

    public static LoginEnum loginAs ;

    public static Integer page = 1;
    public static Integer rowCountPerPage = 10;
    public static Integer totalPage = 1;
    public static Integer totalData = 0;

    public MainFrame() {

        super("JobHunter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        this.setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        router.showHomepagePanel(this);
    }

    public static void resetPagingCounters() {
        page = 1;
        rowCountPerPage = 10;
        totalPage = 1;
        totalData = 0;
    }

}
