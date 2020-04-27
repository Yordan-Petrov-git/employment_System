package DataProvider;

import Models.JobOffer;

public class DataProviderCreateNewJobOffer extends DataProvider {


    public static JobOffer currentJobOffer;

    public static JobOffer getCurrentJobOffer() {
        return currentJobOffer;
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
    }

    public static void removeCurrentJobOffer() {
        setCurrentJobOffer(null);
    }
}