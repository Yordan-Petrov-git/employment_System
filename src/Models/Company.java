package Models;

public class Company extends SuperclassUser {

    private String companyName;
    private String phoneNumber;
    private String logo;
    private String address;
    private String city;
    private String eik;
    private String webPage;
    private String description;

    public Company(String username, String password, String salt, String passPhrase, String companyName, String phoneNumber, String logo, String address, String city, String eik, String description) {
        super(username, password, salt, passPhrase);
        setCompanyName(companyName);
        setCompanyName(phoneNumber);
        setCompanyName(logo);
        setCompanyName(address);
        setCompanyName(eik);
        setCompanyName(description);
    }


    public Company(String username, String password, String salt, String passPhrase, String companyName, String phoneNumber, String address, String city, String eik, String description) {
        super(username, password, salt, passPhrase);
        setCompanyName(companyName);
        setCompanyName(phoneNumber);
        setCompanyName(address);
        setCompanyName(eik);
        setCompanyName(description);
    }


    public Company(String username, String password, String salt, String passPhrase, String companyName, String phoneNumber, String logo, String address, String city, String eik, String webPage, String description) {
        super(username, password, salt, passPhrase);
        setCompanyName(companyName);
        setCompanyName(phoneNumber);
        setCompanyName(logo);
        setCompanyName(address);
        setCompanyName(eik);
        setCompanyName(webPage);
        setCompanyName(description);
    }

    public Company(String username, String password
            , String salt, String passPhrase, String companyName) {
        super(username, password, salt, passPhrase);
        setCompanyName(companyName);
    }

    public Company(String username, String companyName) {
        super(username);
        setCompanyName(companyName);
    }

    public Company(long id,String username, String companyName) {
        super(id,username);
        setCompanyName(companyName);
    }

    public Company(long id) {
        super(id);
    }


    public Company() {

    }


    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEik() {
        return this.eik;
    }

    public void setEik(String eik) {
        this.eik = eik;
    }

    public String getWebPage() {
        return this.webPage;
    }

    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
