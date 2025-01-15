package gr.uom.RideOrCry.DTO;


public class LoginResponse {
    private Object user;    // Object για να υποστηρίζει τόσο Citizen όσο και Agency
    private boolean isAgent;
    private String error;

    public LoginResponse() {
    }

    // Constructor
    public LoginResponse(Object user, boolean isAgent) {
        this.user = user;
        this.isAgent = isAgent;
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

    public boolean isAgent() {
        return isAgent;
    }

    public void setAgent(boolean isAgent) {
        this.isAgent = isAgent;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return this.error;
    }
}

