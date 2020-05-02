package DataProvider;

import Helpers.TableUtils.TableUtility;
import Models.JobOffer;
import Views.MainFrame;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataProviderTableJobOffers extends AbstractTableModel {

    private static List<JobOffer> listProduct = new ArrayList<JobOffer>();

    public static void loadJobOffersInTable(JTable jTable
            , JButton last
            , JButton next
            , JButton previous
            , JButton first
            , JLabel status
            , JLabel totalData
            , JComboBox pageSelect
            , DataProviderTableJobOffers productTableModel
            , int currentIdComp
    ) {


        MainFrame.totalData = countId(currentIdComp);
        //Testing coutn utput
        System.out.println(MainFrame.totalData);

        MainFrame.rowCountPerPage = Integer.valueOf(pageSelect.getSelectedItem().toString());
        Double totalPageD = Math.ceil(MainFrame.totalData.doubleValue() / MainFrame.rowCountPerPage.doubleValue());
        MainFrame.totalPage = totalPageD.intValue();
        //Bquttons for page navigation
        //Buttons for first page adn next page
        if (MainFrame.page.equals(1)) {
            first.setEnabled(false);
            previous.setEnabled(false);
        } else {
            first.setEnabled(true);
            previous.setEnabled(true);
        }
        //Buittons for last apge and next page
        if (MainFrame.page.equals(MainFrame.totalPage)) {
            last.setEnabled(false);
            next.setEnabled(false);
        } else {
            last.setEnabled(true);
            next.setEnabled(true);
        }

        if (MainFrame.page > MainFrame.totalPage) {
            MainFrame.page = 1;
        }
        //New instance of table for client table model
        productTableModel = new DataProviderTableJobOffers();
        //Popialte table
        productTableModel.setList(DataProviderTableJobOffers.getSpecificCompanyJobOffers(MainFrame.page, MainFrame.rowCountPerPage,currentIdComp));
        //Set model
        jTable.setModel(productTableModel);
        status.setText("Page " + MainFrame.page + " for " + MainFrame.totalPage);//Page position count
        totalData.setText(("Row count " + MainFrame.totalData));//Row count
        //Resizes jtables columns
        TableUtility.autoResizeColumn(jTable);

    }

    public static void initPagination(JTable jTable
            , JButton last
            , JButton next
            , JButton previous
            , JButton first
            , JLabel status
            , JLabel totalData
            , JComboBox pageSelect
            , DataProviderTableJobOffers productTableModel) {
        //Initializes pagiantion of rows in jtable
        //Counts total rows in SQL DB
        MainFrame.totalData = productTableModel.count();
        //Testing coutn utput
        System.out.println(MainFrame.totalData);

        MainFrame.rowCountPerPage = Integer.valueOf(pageSelect.getSelectedItem().toString());
        Double totalPageD = Math.ceil(MainFrame.totalData.doubleValue() / MainFrame.rowCountPerPage.doubleValue());
        MainFrame.totalPage = totalPageD.intValue();
        //Bquttons for page navigation
        //Buttons for first page adn next page
        if (MainFrame.page.equals(1)) {
            first.setEnabled(false);
            previous.setEnabled(false);
        } else {
            first.setEnabled(true);
            previous.setEnabled(true);
        }
        //Buittons for last apge and next page
        if (MainFrame.page.equals(MainFrame.totalPage)) {
            last.setEnabled(false);
            next.setEnabled(false);
        } else {
            last.setEnabled(true);
            next.setEnabled(true);
        }

        if (MainFrame.page > MainFrame.totalPage) {
            MainFrame.page = 1;
        }
        //New instance of table for client table model
        productTableModel = new DataProviderTableJobOffers();
        //Popialte table
        productTableModel.setList(DataProviderTableJobOffers.findAll(MainFrame.page, MainFrame.rowCountPerPage));
        //Set model
        jTable.setModel(productTableModel);
        status.setText("Page " + MainFrame.page + " for " + MainFrame.totalPage);//Page position count
        totalData.setText(("Row count " + MainFrame.totalData));//Row count
        //Resizes jtables columns
        TableUtility.autoResizeColumn(jTable);
    }

    public static void search(JTable jTable
            , String compTitle) {

        DataProviderTableJobOffers productTableModel = new DataProviderTableJobOffers();
        productTableModel.setList(DataProviderTableJobOffers.listSearchComp(compTitle));
        TableUtility.autoResizeColumn(jTable);
    }

    public static List<JobOffer> findAll(int page, int pageSize) {

        List<JobOffer> listJobOffers = new ArrayList<JobOffer>();

        if (count() == 0) {
            return listJobOffers;
        }

        try {

            PreparedStatement preparedStatement;
            preparedStatement = DataProvider.getConnection().prepareCall("{call show_all_job_offers(?,?)}");
            preparedStatement.setInt(1, pageSize * (page - 1));
            preparedStatement.setInt(2, pageSize);
            ResultSet resultSet = preparedStatement.executeQuery();
            JobOffer jobOffer;

            while (resultSet.next()) {
                jobOffer = new JobOffer((resultSet.getString("company_job_offer_title")), resultSet.getString("city_offer"),
                        resultSet.getString("position_job_offer"), resultSet.getString("description_job_offer"),
                        resultSet.getString("net_salary_for_offer"), resultSet.getString("type"), resultSet.getString("name_company"), resultSet.getInt("job_offer_id"));

                listJobOffers.add(jobOffer);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listJobOffers;
    }


    public static List<JobOffer> listSearchComp(String compTitle) {
        List<JobOffer> jobOffersById = new ArrayList<JobOffer>();

        try {
            PreparedStatement preparedStatement;
            preparedStatement = DataProvider.getConnection().prepareCall("{call select_offers_by_title(?)}");
            preparedStatement.setString(1, compTitle);
            ResultSet resultSet = preparedStatement.executeQuery();
            JobOffer jobOffer;

            while (resultSet.next()) {
                jobOffer = new JobOffer((resultSet.getString("company_job_offer_title")), resultSet.getString("city_offer"),
                        resultSet.getString("position_job_offer"), resultSet.getString("description_job_offer"),
                        resultSet.getString("net_salary_for_offer"), resultSet.getString("type"), resultSet.getString("name_company")
                        , resultSet.getInt("job_offer_id"));
                jobOffersById.add(jobOffer);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return jobOffersById;
    }


    public static List<JobOffer> getSpecificCompanyJobOffers(int page, int pageSize,int companyId) {
        List<JobOffer> jobOffersById = new ArrayList<JobOffer>();
        if (countId(companyId) == 0) {
            return jobOffersById;
        }
        try {
            PreparedStatement preparedStatement;
            preparedStatement = DataProvider.getConnection().prepareCall("{call select_all_job_offers_for_paging_by_company_id(?,?,?)}");
            preparedStatement.setInt(1, pageSize * (page - 1));
            preparedStatement.setInt(2, pageSize);
            preparedStatement.setInt(3, companyId);

            ResultSet resultSet = preparedStatement.executeQuery();
            JobOffer jobOffer;

            while (resultSet.next()) {
                jobOffer = new JobOffer((resultSet.getString("company_job_offer_title")), resultSet.getString("city_offer"),
                        resultSet.getString("position_job_offer"), resultSet.getString("description_job_offer"),
                        resultSet.getString("net_salary_for_offer"), resultSet.getString("type"), resultSet.getString("name_company")
                        , resultSet.getInt("job_offer_id"));
                jobOffersById.add(jobOffer);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return jobOffersById;
    }

    public static int countId(int companyId) {
        int counter = 0;
        try {
            PreparedStatement preparedStatement;
            preparedStatement = DataProvider.getConnection().prepareCall("{call count_job_offers_by_company_id(?)}");
            preparedStatement.setInt(1, companyId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                counter = rs.getInt("count(job_offer_id)");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return counter;
    }

    public static int count() {
        //Counts SQL table all rows
        int counter = 0;
        try {
            PreparedStatement preparedStatement;
            //  DataProvider.getConnection().setAutoCommit(false);  //count_job_offers
            preparedStatement = DataProvider.getConnection().prepareCall("{call count_job_offers()}");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                counter = rs.getInt("count(job_offer_id)");
            }
            //  DataProvider.getConnection().commit();
            // DataProvider.getConnection().setAutoCommit(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return counter;

    }



    public static List<JobOffer> getListProduct() {
        return DataProviderTableJobOffers.listProduct;
    }

    public static void setListProduct(List<JobOffer> listProduct) {
        DataProviderTableJobOffers.listProduct = listProduct;
    }


    private final String[] HEADER = {"№->", "Title", "City", "Description", "Salary", "JobType", "Company", "id"};

    public void setList(List<JobOffer> listProduct) {
        DataProviderTableJobOffers.listProduct = listProduct;
    }

    public void save(JobOffer product) {
        listProduct.add(product);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void edit(int index, JobOffer product) {
        listProduct.set(index, product);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index) {
        listProduct.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public JobOffer findOne(int index) {
        return listProduct.get(index);
    }

    public int getRowCount() {
        return listProduct.size();
    }

    public int getColumnCount() {
        return HEADER.length;
    }

    public String getColumnName(int column) {
        return HEADER[column];
    }

    public static void deleteList() {
        setListProduct(null);
    }


    public Object getValueAt(int rowIndex, int columnIndex) {
        JobOffer jobOffer = listProduct.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return rowIndex + 1;//Row number counter for the table

            case 1:
                return jobOffer.getJobTitle();

            case 2:
                return jobOffer.getCity();

            case 3:
                return jobOffer.getDescription();

            case 4:
                return jobOffer.getNetSalary();

            case 5:
                return jobOffer.getJobType();

            case 6:
                return jobOffer.getCompany();

            case 7:
                return jobOffer.getId();

            default:
                return null;//Defaut state null
        }
    }

    public void addRow(Object[] row) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
