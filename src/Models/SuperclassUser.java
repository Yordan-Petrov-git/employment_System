package Models;

public class SuperclassUser {


    private String id;
    private String username;
    private String password;
    private String salt;
    private String passPhrase;

    public SuperclassUser(String id, String username, String password, String salt, String passPhrase) {
        setId(id);
        setUsername(username);
        setPassword(password);
        setSalt(salt);
        setPassPhrase(passPhrase);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return this.salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassPhrase() {
        return this.passPhrase;
    }

    public void setPassPhrase(String passPhrase) {
        this.passPhrase = passPhrase;
    }


}
