package Views.Panels.Company;

import DataProvider.DataProviderCreateNewCompany;
import DataProvider.DataProviderCreateNewJobOffer;
import DataProvider.DataProviderCreateNewUser;
import DataProvider.DataProviderTableJobOffers;
import Helpers.TableUtils.TableUtility;
import Views.MainFrame;

import javax.swing.*;

public class HomePageCompanyPanel extends JPanel {

    public MainFrame jFrame;
    private JButton jButtonLogOut;
    private JButton jButtonViewApplication;
    private JButton jButtonDeleteSelectedApplication;
    private JButton jButtonCreateNewJobOffer;
    private JTable jTableJobOffers;
    public JComboBox<String> jComboBoxPage;
    private JLabel jLabelStatus;


    public DataProviderTableJobOffers productTableModel;
    public static int idOfOSelectedJobOffer;
    public Integer index;

    public HomePageCompanyPanel(MainFrame jFrame) {
        this.jFrame = jFrame;

        String loginDetails = "Welcome" +
                " : " +
                DataProviderCreateNewCompany.getCurrentCompany().getCompanyName();
        jLabelStatus = new JLabel(loginDetails);
        add(jLabelStatus);

        jTableJobOffers = new JTable(productTableModel);
        JScrollPane pane = new JScrollPane();
        pane.setViewportView(jTableJobOffers);
        TableUtility.autoResizeColumn(jTableJobOffers);
        add(pane);

        jButtonViewApplication = new JButton("Check application");
        jButtonViewApplication.addActionListener(e -> {

            index = this.jTableJobOffers.getSelectedRow();
            idOfOSelectedJobOffer = Integer.parseInt(this.jTableJobOffers.getValueAt(index, 7).toString());
            MainFrame.router.removePanel(jFrame);
            MainFrame.router.viewJobOfersCompany(jFrame);

        });
        add(jButtonViewApplication);


        jButtonDeleteSelectedApplication = new JButton("Delete offer");
        jButtonDeleteSelectedApplication.addActionListener(e -> {

            index = this.jTableJobOffers.getSelectedRow();
            idOfOSelectedJobOffer = Integer.parseInt(this.jTableJobOffers.getValueAt(index, 7).toString());
//            //delete offer
            deleteJobOffer();

        });
        add(jButtonDeleteSelectedApplication);

        jButtonCreateNewJobOffer = new JButton("Create Offer");
        jButtonCreateNewJobOffer.addActionListener(e -> {

            MainFrame.router.removePanel(jFrame);
            MainFrame.router.createNewJobOffer(jFrame);

        });
        add(jButtonCreateNewJobOffer);


        jButtonLogOut = new JButton("Logout");
        jButtonLogOut.addActionListener(e -> {

            DataProviderCreateNewCompany.removeCurrentCompany();
            // DataProviderCreateNewUser.removeCurrentCompany();
            MainFrame.router.removePanel(jFrame);
            MainFrame.router.showHomepagePanel(jFrame);

        });
        add(jButtonLogOut);

        refreshTable();
    }

    public void refreshTable() {

        DataProviderTableJobOffers.loadJobOffersInTable(jTableJobOffers, (int) DataProviderCreateNewCompany.getCurrentCompany().getId());
    }

    public void deleteJobOffer() {
        DataProviderCreateNewJobOffer.deleteJobOffer(idOfOSelectedJobOffer);
        refreshTable();
    }

}
