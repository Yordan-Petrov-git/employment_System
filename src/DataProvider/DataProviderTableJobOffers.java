package DataProvider;

import Models.JobOffer;

import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataProviderTableJobOffers extends DataProvider{

    private PreparedStatement preparedStatement;

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

    public int count() {
        //Counts SQL table all rows
        int counter = 0;
        try {
            getConnection().setAutoCommit(false);
            preparedStatement = getConnection().prepareStatement("SELECT count(job_offer_id) from job_offers");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                counter = rs.getInt("count(job_offer_id)");
            }
            getConnection().commit();
            getConnection().setAutoCommit(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return counter;
        }
    }


    public List<JobOffer> findAll(int page, int pageSize) {
        //Table rows paging
        List<JobOffer> listJobOffers = new ArrayList<JobOffer>();

        if (count() == 0) {
            return listJobOffers;
        }

        try {
            getConnection().setAutoCommit(false);
            //SQL DB Query for table paging
            preparedStatement = getConnection().prepareCall("{ call show_all_job_offers() }");
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
            getConnection().commit();
            getConnection().setAutoCommit(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        //finally {
            return listJobOffers;
       // }

    }

}
