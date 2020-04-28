package Models;

public class User extends SuperclassUser {

    private String firstName;
    private String familyName;
    private String phoneNumber;
    private String emailAddress;
    private String accountType;
    private String picture;


    public User(String username, String password,
                String salt, String passPhrase,
                String firstName, String familyName, String phoneNumber,
                String emailAddress, String accountType, String picture) {
        super(username, password, salt, passPhrase);
        setFirstName(firstName);
        setFamilyName(familyName);
        setPhoneNumber(phoneNumber);
        setEmailAddress(emailAddress);
        setAccountType(accountType);
        setPicture(picture);
    }

    public User(String username, String password,
                String salt, String passPhrase, String firstName,
                String familyName, String phoneNumber, String emailAddress,
                String accountType) {
        super(username, password, salt, passPhrase);
        setFirstName(firstName);
        setFamilyName(familyName);
        setPhoneNumber(phoneNumber);
        setEmailAddress(emailAddress);
        setAccountType(accountType);



    }

    public User(String username, String password,
                String salt, String passPhrase, String firstName,
                String familyName, String phoneNumber, String emailAddress) {
        super(username, password, salt, passPhrase);
        setFirstName(firstName);
        setFamilyName(familyName);
        setPhoneNumber(phoneNumber);
        setEmailAddress(emailAddress);


    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }


}
