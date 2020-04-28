package DataProvider;

import Models.JobOffer;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataProviderTableJobOffers extends AbstractTableModel {

    private static PreparedStatement preparedStatement;


    public DefaultTableModel model;

    List<JobOffer> listProduct = new ArrayList<JobOffer>();

    public void updateTableData() {
        model.setRowCount(0);
//        for (Sandwich sandwitch : this.sandwiches) {
//            Object[] row = new Object[3];
//            row[0] = sandwitch.name;
//            row[1] = sandwitch.size;
//            row[2] = sandwitch.price;
//            model.addRow(row);
       }

    public static int count() {
        //Counts SQL table all rows
        int counter = 0;
        try {
          //  DataProvider.getConnection().setAutoCommit(false);
            preparedStatement = DataProvider.getConnection().prepareStatement("SELECT count(job_offer_id) from job_offers");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                counter = rs.getInt("count(job_offer_id)");
            }
          //  DataProvider.getConnection().commit();
           // DataProvider.getConnection().setAutoCommit(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return counter;
        }
    }


    public static List<JobOffer> findAll(int page, int pageSize) {
        //Table rows paging
        List<JobOffer> listJobOffers = new ArrayList<JobOffer>();

        if (count() == 0) {
            return listJobOffers;
        }

        try {
           // DataProvider.getConnection().setAutoCommit(false);
            //SQL DB Query for table paging
            preparedStatement = DataProvider.getConnection().prepareCall("{call show_all_job_offers(?,?)}");
            preparedStatement.setInt(1, pageSize * (page - 1));
            preparedStatement.setInt(2, pageSize);
            ResultSet resultSet = preparedStatement.executeQuery();
            JobOffer jobOffer;

            // ---------------for later user-------------
            //resultSet.getString("date_added_offer")
            //resultSet.getString("name_company")

            while (resultSet.next()) {
                jobOffer = new JobOffer((resultSet.getString("company_job_offer_title")),resultSet.getString("city_offer"),
                        resultSet.getString("position_job_offer"),  resultSet.getString("description_job_offer"),
                        resultSet.getString("net_salary_for_offer"),resultSet.getString("type"));
                listJobOffers.add(jobOffer);
            }
            //DataProvider.getConnection().commit();
           // DataProvider.getConnection().setAutoCommit(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            return listJobOffers;
        }

    }
        private final String HEADER[] = {"№->", "Title", "City", "Description", "Salary","JobType","Company"};

        public void setList(List<JobOffer> listProduct) {
            this.listProduct = listProduct;
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

//                case 6:
//                    return jobOffer.getCompany();


                default:
                    return null;//Defaut state null
            }
        }

        public void addRow(Object[] row) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }



}
