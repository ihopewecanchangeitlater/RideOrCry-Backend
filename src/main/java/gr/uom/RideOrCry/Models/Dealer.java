package gr.uom.RideOrCry.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Dealer {
    @Id
    private String ssn;
    private String name;
    private String owner;
    private String password;

    public Dealer() {
    }

    public Dealer(String ssn, String name, String owner, String password) {
        this.ssn = ssn;
        this.name = name;
        this.owner = owner;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
