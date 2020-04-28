package Views;
import Helpers.TableUtils.TableUtility;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class Home extends JPanel {


    private static MainFrame jFrame;
    private JLabel jLabelTitle;
    private JButton jButtonLoginAsUser;
    private JButton jButtonLoginAsCompany;
    private JTable jTableJobOffers;

    private JButton jButtonLast;
    private JButton jButtonNext;
    private JButton jButtonPrevious;
    private JButton jButtonFirst;



    public Home(MainFrame jFrame) {
        this.jFrame = jFrame;
        jFrame.setSize(800, 600);


        jLabelTitle = new JLabel("Homepage");
        add(jLabelTitle);


        jTableJobOffers = new JTable();
        JScrollPane pane = new JScrollPane();
        String[] columnIdentifiers = {"Company","Title","City","Salary","DateAdded"};
        MainFrame.dataProviderTableJobOffers.model = new DefaultTableModel();
        MainFrame.dataProviderTableJobOffers.model.setColumnIdentifiers(columnIdentifiers);
        jTableJobOffers.setModel(MainFrame.dataProviderTableJobOffers.model);
        pane.setViewportView(jTableJobOffers);
        TableUtility.autoResizeColumn(jTableJobOffers);
        add(pane);

        jButtonLoginAsUser = new JButton("Login as User");
        jButtonLoginAsUser.addActionListener(e -> {


            MainFrame.loginAs = LoginEnum.LOGIN_AS_USER;
            showLogin();


        });
        add(jButtonLoginAsUser);

        jButtonLoginAsCompany = new JButton("Login as Company");
        jButtonLoginAsCompany.addActionListener(e -> {

            MainFrame.loginAs = LoginEnum.LOGIN_AS_COMPANY;
            showLogin();

        });
        add(jButtonLoginAsCompany);

    }


    public static void showLogin() {

        MainFrame.router.removePanel(jFrame);
        MainFrame.router.showLoginPanel(jFrame);

    }





   // int index = jTableJobOffers.getSelectedRow();

}