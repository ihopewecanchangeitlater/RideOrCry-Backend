package gr.uom.RideOrCry.DTO;


public abstract class UserRegistrationRequest {
    private String afm;
    private String email;
    private String name;
    private String password;

    public UserRegistrationRequest() {
    }

    public UserRegistrationRequest(String afm, String email, String password, String name) {
        this.afm = afm;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getAfm() {
        return afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
