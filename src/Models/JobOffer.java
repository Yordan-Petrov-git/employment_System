package Models;

public class JobOffer {

    private long id;
    private String jobTitle;
    private String city;
    private String position;
    private String description;
    private String netSalary;
    private Boolean isOfferActive;
    private String jobType;
    private String logo;

    public JobOffer( String jobTitle, String city, String position
            , String description, String netSalary
            , Boolean isOfferActive
            , String jobType
            , String logo) {


        setJobTitle(jobTitle);
        setCity(city);
        setPosition(position);
        setDescription(description);
        setNetSalary(netSalary);
        setOfferActive(isOfferActive);
        setJobType(jobType);
        setLogo(logo);
    }

    public JobOffer( String jobTitle, String city, String position
            , String description, String netSalary
            , Boolean isOfferActive
            , String jobType) {
//without logo
        setJobTitle(jobTitle);
        setCity(city);
        setPosition(position);
        setDescription(description);
        setNetSalary(netSalary);
        setOfferActive(isOfferActive);
        setJobType(jobType);
    }


    public JobOffer( String jobTitle, String city, String position
            , String description, String netSalary
            , String jobType) {
//without logo and status
        setJobTitle(jobTitle);
        setCity(city);
        setPosition(position);
        setDescription(description);
        setNetSalary(netSalary);
        setJobType(jobType);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return this.jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNetSalary() {
        return this.netSalary;
    }

    public void setNetSalary(String netSalary) {
        this.netSalary = netSalary;
    }

    public Boolean getOfferActive() {
        return this.isOfferActive;
    }

    public void setOfferActive(Boolean offerActive) {
        isOfferActive = offerActive;
    }

    public String getJobType() {
        return this.jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }


}
