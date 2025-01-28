package gr.uom.RideOrCry.DTO;


public class LoginResponse {
    private Object user;    // Object για να υποστηρίζει τόσο Citizen όσο και Agency
    private String token;
    private String error;

    public LoginResponse() {
    }

    public LoginResponse(Object user, String token) {
        this.user = user;
        this.token = token;
    }

    public LoginResponse(String error) {
        this.error = error;
    }

    // Getter - Setter
    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return this.error;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

