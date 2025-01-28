package gr.uom.RideOrCry.DTO;

import gr.uom.RideOrCry.Entities.User;

public class Agency extends UserRegistrationRequest {
    private String owner;

    public Agency() {
    }

    public Agency(User user) {
        super(user.getAfm(), user.getEmail(), user.getPassword(), user.getName());
        this.owner = user.getCustomField();
    }

    public Agency(String afm, String email, String password, String name, String owner) {
        super(afm, email, password, name);
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}