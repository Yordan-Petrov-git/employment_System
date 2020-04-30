package Views.Panels.Company;

import DataProvider.DataProviderCreateNewCompany;
import DataProvider.DataProviderCreateNewJobOffer;
import DataProvider.DataProviderTableJobOffers;
import Helpers.TableUtils.TableUtility;
import Views.MainFrame;

import javax.swing.*;

public class HomePageCompanyPanel extends JPanel {

    //TODO FIX ABSTRACT TABLE MODEL LOADING ON REFRESH

    public MainFrame jFrame;
    private JButton jButtonLogOut;
    private JButton jButtonViewApplication;
    private JButton jButtonDeleteSelectedApplication;
    private JButton jButtonCreateNewJobOffer;
    private JTable jTableJobOffers;
    public JComboBox<String> jComboBoxPage;

    public DataProviderTableJobOffers productTableModel;

    public static int id = (int) DataProviderCreateNewCompany.getCurrentCompany().getId();
    public Integer index;

    public HomePageCompanyPanel(MainFrame jFrame) {
        this.jFrame = jFrame;

        jTableJobOffers = new JTable(productTableModel);
        JScrollPane pane = new JScrollPane();
        pane.setViewportView(jTableJobOffers);
        TableUtility.autoResizeColumn(jTableJobOffers);
        add(pane);

        jButtonViewApplication = new JButton("Check application");
        jButtonViewApplication.addActionListener(e -> {

            index = this.jTableJobOffers.getSelectedRow();
            id = Integer.parseInt(this.jTableJobOffers.getValueAt(index, 7).toString());
            MainFrame.router.removePanel(jFrame);
            MainFrame.router.viewJobOfersCompany(jFrame);

        });
        add(jButtonViewApplication);


        jButtonDeleteSelectedApplication = new JButton("Delete offer");
        jButtonDeleteSelectedApplication.addActionListener(e -> {

            //TODO FIX TABLE UPDATE ON DELETE

            index = this.jTableJobOffers.getSelectedRow();
            id = Integer.parseInt(this.jTableJobOffers.getValueAt(index, 7).toString());
            //delete offer
            deleteJobOffer();

            refreshTable();


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

        System.out.println(DataProviderCreateNewCompany.getCurrentCompany().getCompanyName());

    }

    public void refreshTable() {

        DataProviderTableJobOffers.loadJobOffersInTable(jTableJobOffers, id);
    }

    public void deleteJobOffer() {
        DataProviderCreateNewJobOffer.deleteJobOffer(id);

        MainFrame.dataProviderTableJobOffers.delete(index);//ATTEMPT TO REFRESH TABLE
    }

}
