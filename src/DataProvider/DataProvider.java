package DataProvider;

import Models.Company;
import Models.JobOffer;
import Models.User;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class DataProvider {

    public ArrayList<User> userArrayList = new ArrayList<User>();
    public ArrayList<JobOffer> jobOfferArrayList = new ArrayList<JobOffer>();
    public ArrayList<Company> companyArrayList = new ArrayList<Company>();

    public DefaultTableModel tableModel;


}
