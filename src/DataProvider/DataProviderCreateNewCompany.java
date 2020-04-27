package DataProvider;

import Models.Company;
import Models.User;

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


}
