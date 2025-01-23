package gr.uom.RideOrCry.DTO;

import gr.uom.RideOrCry.Entities.User;

public class Citizen extends UserRegistrationRequest {
    private String surname;

    public Citizen() {
    }

    public Citizen(User citizen) {
        super(citizen.getAfm(), citizen.getEmail(), citizen.getPassword(), citizen.getName());
        this.surname = citizen.getCustomField();
    }

    public Citizen(String afm, String email, String password, String name, String surname) {
        super(afm, email, password, name);
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}