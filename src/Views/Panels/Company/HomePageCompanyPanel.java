package Views.Panels.Company;

import DataProvider.DataProviderCreateNewCompany;
import DataProvider.DataProviderCreateNewJobOffer;
import DataProvider.DataProviderTableJobOffers;
import Helpers.TableUtils.TableUtility;
import Views.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class HomePageCompanyPanel extends JPanel {
    public MainFrame jFrame;
    private JButton jButtonLogOut;
    private JButton jButtonViewApplication;
    private JButton jButtonManageApplication;
    private JButton jButtonDeleteSelectedApplication;
    private JButton jButtonCreateNewJobOffer;
    private JTable jTableJobOffers;
    public JComboBox<String> jComboBoxPage;

    public DataProviderTableJobOffers productTableModel;

    public static  int  id = (int) DataProviderCreateNewCompany.getCurrentCompany().getId();
    public int index;

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

        jButtonManageApplication = new JButton("Edit offer");
        jButtonManageApplication.addActionListener(e -> {

            MainFrame.router.removePanel(jFrame);
            MainFrame.router.editSelectedJobOffer(jFrame);

        });
        add(jButtonManageApplication);

        jButtonDeleteSelectedApplication = new JButton("Delete offer");
        jButtonDeleteSelectedApplication.addActionListener(e -> {

            index = this.jTableJobOffers.getSelectedRow();
            id = Integer.parseInt(this.jTableJobOffers.getValueAt(index, 7).toString());
            System.out.println(id);
            //delete offer
            DataProviderCreateNewJobOffer.deleteJobOffer(id);
            MainFrame.dataProviderTableJobOffers.delete(index);
           // refreshTable();  //refresh table

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
        DataProviderTableJobOffers.loadJobOffersInTable(jTableJobOffers,id);
    }





}
