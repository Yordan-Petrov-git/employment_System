package DataProvider;

import Models.Company;
import Models.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class DataProviderCreateNewCompany extends DataProvider {

    public static Company currentCompany;

    public static Company getCurrentCompany() {
        return DataProviderCreateNewCompany.currentCompany;
    }

    public static void setCurrentCompany(Company currentUser) {
        DataProviderCreateNewCompany.currentCompany = currentUser;
    }

    public static void initializeNewCompany(String username, String password
            , String salt, String passPhrase, String companyName) {

        Company company = new Company(username, password, salt, passPhrase, companyName);

    }


    public static void removeCurrentCompany() {
        setCurrentCompany(null);
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
    public static void addCompanyToDataBase() throws SQLException {

        String salt = getCurrentCompany().getSalt();
        String key = getCurrentCompany().getPassword();
        String companyName = getCurrentCompany().getCompanyName();
        String username = getCurrentCompany().getUsername();
        String passphrase = getCurrentCompany().getPassPhrase();

        registerNewCompany(companyName,username,key,salt,passphrase);

    }
}
