package DataProvider;

import Models.Company;

public class DataProviderCreateNewCompany extends DataProvider{

    public static Company currentCompany;

    public static Company getCurrentUser() {
        return currentCompany;
    }

    public static void setCurrentUser(Company currentUser) {
        DataProviderCreateNewCompany.currentCompany = currentUser;
    }



}
