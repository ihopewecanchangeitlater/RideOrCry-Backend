package gr.uom.RideOrCry.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Citizen {

    @Id
    private String ssn;
    private String name;
    private String surname;
    private String email;
    private String password;

    public Citizen() {
    }

    public Citizen(String ssn, String name, String surname, String email, String password) {
        this.ssn = ssn;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
}
