package DataProvider;

import Models.JobOffer;

public class DataProviderCreateNewJobOffer extends DataProvider{


    public static JobOffer currentJobOffer;

    public static JobOffer getCurrentUser() {
        return currentJobOffer;
    }

    public static void setCurrentUser(JobOffer currentUser) {
        DataProviderCreateNewJobOffer.currentJobOffer = currentUser;
    }

}