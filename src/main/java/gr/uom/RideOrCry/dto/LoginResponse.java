package gr.uom.RideOrCry.dto;


public class LoginResponse {
    private Object user;    // Object για να υποστηρίζει τόσο Citizen όσο και Agency
    private boolean isAgent;

    // Constructor
    public LoginResponse(Object user, boolean isAgent) {
        this.user = user;
        this.isAgent = isAgent;
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
}

