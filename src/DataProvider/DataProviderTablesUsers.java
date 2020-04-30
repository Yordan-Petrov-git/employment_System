package DataProvider;

import Helpers.TableUtils.TableUtility;
import Models.User;
import Views.MainFrame;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataProviderTablesUsers extends AbstractTableModel {

    List<User> userList = new ArrayList<User>();

    public static void initPagingUsersApplied(JTable jTable
            , JButton last
            , JButton next
            , JButton previous
            , JButton first
            , JLabel status
            , JLabel totalData
            , JComboBox pageSelect
            , DataProviderTablesUsers userTableModel,
                                       int jobOfferId) {
        //Initializes pagiantion of rows in jtable
        //Counts total rows in SQL DB
        MainFrame.totalData = countJobOffersApplication(jobOfferId);
        //Testing coutn utput
        System.out.println(MainFrame.totalData);

        MainFrame.rowCountPerPage = Integer.valueOf(pageSelect.getSelectedItem().toString());
        double totalPageD = Math.ceil(MainFrame.totalData.doubleValue() / MainFrame.rowCountPerPage.doubleValue());
        MainFrame.totalPage = (int) totalPageD;
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
        userTableModel = new DataProviderTablesUsers();
        //Popialte table
        userTableModel.setList(getListOfApplicants(MainFrame.page, MainFrame.rowCountPerPage,jobOfferId));
        //Set model
        jTable.setModel(userTableModel);
        status.setText("Page " + MainFrame.page + " for " + MainFrame.totalPage);//Page position count
        totalData.setText(("Row count " + MainFrame.totalData));//Row count
        //Resizes jtables columns
        TableUtility.autoResizeColumn(jTable);
    }

    public static int countJobOffersApplication(int offerId) {
        //Counts job offer applications  by offer id
        int counter = 0;
        try {
            PreparedStatement preparedStatement;
            //  DataProvider.getConnection().setAutoCommit(false);  //count_job_offers
            preparedStatement = DataProvider.getConnection().prepareCall("{call count_job_offers_by_offer_id(?)}");
            preparedStatement.setInt(1,offerId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                counter = rs.getInt("COUNT(`job_offer_id`)");
            }
            //  DataProvider.getConnection().commit();
            // DataProvider.getConnection().setAutoCommit(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return counter;

    }
    
    public static List<User> getListOfApplicants(int page, int pageSize, int offerId) {

        List<User> listJobOffers = new ArrayList<User>(); // list for offers

        if (countJobOffersApplication(offerId) == 0) { //counts job offers by selected job offer id
            return listJobOffers;
        }

        try {
            // DataProvider.getConnection().setAutoCommit(false);
            //SQL DB Query for table paging
            PreparedStatement preparedStatement;
            preparedStatement = DataProvider.getConnection().prepareCall("{call select_user_info_by_user_id_for_job_offers_applications(?,?,?)}");
            preparedStatement.setInt(1, offerId);
            preparedStatement.setInt(2, pageSize * (page - 1));
            preparedStatement.setInt(3, pageSize);

            ResultSet resultSet = preparedStatement.executeQuery();
            User user;

            while (resultSet.next()) {

                user = new User((resultSet.getString("first_name_user"))
                        ,(resultSet.getString("family_name_user"))
                        ,(resultSet.getString("phone_number"))
                        ,(resultSet.getString("email"))
                        ,(resultSet.getString("motivational_letter_from_user"))
                        ,(resultSet.getString("years_of_experience")));

                listJobOffers.add(user);
            }
            //DataProvider.getConnection().commit();
            // DataProvider.getConnection().setAutoCommit(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listJobOffers;

    }

    private final String[] HEADER = {"№->","Name ", "Email", "Phone Number","Motivation","Experience"};

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
                return user.getFirstName()+ " " +user.getFamilyName();

            case 2:
                return user.getEmailAddress();

            case 3:
                return user.getPhoneNumber();

            case 4:
                return user.getDescription();

            case 5:
                return user.getYearsExp();

            default:
                return null;//Defaut state null
        }
    }

    public void addRow(Object[] row) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
