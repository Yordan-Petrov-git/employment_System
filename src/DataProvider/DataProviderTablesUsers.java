package DataProvider;

import Models.JobOffer;
import Models.User;

import javax.swing.table.AbstractTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataProviderTablesUsers extends AbstractTableModel {

    List<User> userList = new ArrayList<User>();


    public static int countUsers(int id) {
        //Counts SQL table all rows
        int counter = 0;
        try {
            PreparedStatement preparedStatement;
            //  DataProvider.getConnection().setAutoCommit(false);  //count_job_offers
            preparedStatement = DataProvider.getConnection().prepareCall("{call count_job_offers(?)}");
            preparedStatement.setInt(1,id);
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

    public static List<JobOffer> findAll(int page, int pageSize,int id) {

        List<JobOffer> listJobOffers = new ArrayList<JobOffer>();

        if (countUsers(id) == 0) {
            return listJobOffers;
        }

        try {
            // DataProvider.getConnection().setAutoCommit(false);
            //SQL DB Query for table paging
            PreparedStatement preparedStatement;
            preparedStatement = DataProvider.getConnection().prepareCall("{call show_all_job_offers(?,?,?,?)}");
            preparedStatement.setInt(1, pageSize * (page - 1));
            preparedStatement.setInt(2, pageSize);
            ResultSet resultSet = preparedStatement.executeQuery();
            JobOffer jobOffer;

            // ---------------for later user-------------
            //resultSet.getString("date_added_offer")


            while (resultSet.next()) {
                jobOffer = new JobOffer((resultSet.getString("company_job_offer_title")),resultSet.getString("city_offer"),
                        resultSet.getString("position_job_offer"),  resultSet.getString("description_job_offer"),
                        resultSet.getString("net_salary_for_offer"),resultSet.getString("type"), resultSet.getString("name_company"),resultSet.getInt("job_offer_id") );
                listJobOffers.add(jobOffer);
            }
            //DataProvider.getConnection().commit();
            // DataProvider.getConnection().setAutoCommit(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listJobOffers;

    }



    private final String HEADER[] = {"№->", "Name", "Family", "Email", "Phone Number"};

    public void setList(List<User> listProduct) {
        this.userList = listProduct;
    }

    public void save(User product) {
        userList.add(product);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void edit(int index, User product) {
        userList.set(index, product);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index) {
        userList.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public User findOne(int index) {
        return userList.get(index);
    }

    public int getRowCount() {
        return userList.size();
    }

    public int getColumnCount() {
        return HEADER.length;
    }

    public String getColumnName(int column) {
        return HEADER[column];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = userList.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return rowIndex + 1;//Row number counter for the table

            case 1:
                return user.getFirstName();

            case 2:
                return user.getFamilyName();

            case 3:
                return user.getEmailAddress();

            case 4:
                return user.getPhoneNumber();

//            case 5:
//                return user.getId();


            default:
                return null;//Defaut state null
        }
    }

    public void addRow(Object[] row) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
