package DataProvider;

import Models.JobOffer;
import Models.User;

import javax.swing.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataProviderCreateNewJobOffer extends DataProvider {


    private static JobOffer currentJobOffer;

    public static JobOffer getCurrentJobOffer() {
        return DataProviderCreateNewJobOffer.currentJobOffer;
    }

    public static void setCurrentJobOffer(JobOffer currentUser) {
        DataProviderCreateNewJobOffer.currentJobOffer = currentUser;
    }

    public static void initializeJobOffer(String jobTitle, String city, String position
            , String description, String netSalary
            , Boolean isOfferActive
            , String jobType) {

        JobOffer jobOffer = new JobOffer(jobTitle, city, position
                , description
                , netSalary
                , isOfferActive
                , jobType);

        setCurrentJobOffer(jobOffer);
    }


    public static void getJobOfferApplicationById(int idOffer) {

        String query = "{ call select_user_info_by_user_id_for_job_offers_applications(?) }";
        ResultSet resultSet;

        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall(query)) {

            stmt.setInt(1, idOffer);

            resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                String fName = resultSet.getString("first_name_user");
                String lName = resultSet.getString("family_name_user");
                String  phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                String motivationalLetterFromUser = resultSet.getString("motivational_letter_from_user");
                String yearsOfExperience = resultSet.getString("years_of_experience");
                String timeStampApplication = resultSet.getString("time_stamp_application");

            //    DataProviderTablesUsers.;

            }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }


    public static void registerJobOffer(Integer companyId, String jobTitle, String city, String position
            , String description, String netSalary
            , String jobType) {

        String query = "{ call insert_new_job_offer(?,?,?,?,?,?,?) }";


        try (Connection conn = DataProvider.getConnection();

             CallableStatement cstmt = conn.prepareCall(query)) {

            cstmt.setInt(1, companyId);
            cstmt.setString(2, jobTitle);
            cstmt.setString(3, city);
            cstmt.setString(4, position);
            cstmt.setString(5, description);
            cstmt.setString(6, netSalary);
            cstmt.setString(7, jobType);
            cstmt.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public static void applyForJobOffer(Integer offerId,Integer userId,String mLetter,String yExperience) {

        String query = "{ call insert_application_for_job_offer(?,?,?,?) }";


        try (Connection conn = DataProvider.getConnection();

             CallableStatement cstmt = conn.prepareCall(query)) {

            cstmt.setInt(1,offerId);
            cstmt.setInt(2, userId);
            cstmt.setString(3, mLetter);
            cstmt.setString(4, yExperience);

            boolean isRegistered = cstmt.execute();

            if(!isRegistered){
                JOptionPane.showMessageDialog(null,   "Application successful !");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public static void deleteJobOffer(Integer offerId) {

        String query = "{ call delete_job_offer(?) }";

        try (Connection conn = DataProvider.getConnection();

             CallableStatement cstmt = conn.prepareCall(query)) {

            cstmt.setInt(1,offerId);

            boolean isRegistered = cstmt.execute();

            if(!isRegistered){
                JOptionPane.showMessageDialog(null,   "Job offer deleted !");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public static void removeCurrentJobOffer() {
        setCurrentJobOffer(null);
    }


}