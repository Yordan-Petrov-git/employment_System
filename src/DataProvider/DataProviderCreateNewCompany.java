package DataProvider;

import Models.Company;
import Models.User;

import javax.swing.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class DataProviderCreateNewCompany extends DataProvider {

    private static Company currentCompany;

    public static Company getCurrentCompany() {
        return DataProviderCreateNewCompany.currentCompany;
    }

    public static void setCurrentCompany(Company currentUser) {
        DataProviderCreateNewCompany.currentCompany = currentUser;
    }

    public static void initializeNewCompany(String username, String password
            , String salt, String passPhrase, String companyName) {

        Company company = new Company(username, password, salt, passPhrase, companyName);

        setCurrentCompany(company);
    }

    public static void setNewCompanyForCurrentLoggedCompanyInfo(long id ,String username, String companyName) {
        //login current company

        Company company = new Company(id,companyName,username);
        setCurrentCompany(company);
    }

    public static void registerNewCompany(String companyName
            , String username
            , String key
            , String salt
            , String passphrase) {

        String query = "{ call insert_new_company(?,?,?,?,?) }";


        try (Connection conn = DataProvider.getConnection();

             CallableStatement cstmt = conn.prepareCall(query)) {

            cstmt.setString(1, companyName);
            cstmt.setString(2, username);
            cstmt.setString(3, key);
            cstmt.setString(4, salt);
            cstmt.setString(5, passphrase);
            cstmt.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void addCompanyToDataBase() {

        String companyName = getCurrentCompany().getCompanyName();
        String usernameCompany = getCurrentCompany().getUsername();
        String key = getCurrentCompany().getPassword();
        String salt = getCurrentCompany().getSalt();
        String passphrase = getCurrentCompany().getPassPhrase();


        if (!LoginDataProvider.doesUsernameCompanyExists(usernameCompany)){
            JOptionPane.showMessageDialog(null, "Company :" + companyName + "redistricted");
            registerNewCompany(companyName,usernameCompany,key,salt,passphrase);
        }else{
            JOptionPane.showMessageDialog(null, "Username is taken", "Registration Error", 2);
        }


    }

    public static void removeCurrentCompany() {
        setCurrentCompany(null);
    }
}
