package gr.uom.RideOrCry.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "CITIZENS")
public class Citizen {
    @Id
    @NotNull(message = "AFM is required.")
    @Size(min = 9, max = 9, message = "TRN must have 9 digits")
    @Column(length = 9)
    private String afm;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Surname is required")
    private String surname;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String password;

    private Boolean isAgent = false;

    public Citizen() {
    }

    // Ο κατασκευαστής με παραμέτρους για την καταχώρηση των πεδίων
    public Citizen(String afm, String name, String surname, String email, String password, Boolean isAgent) {
        this.afm = afm;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.isAgent = false;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAfm() {
        return afm;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAgent() {
        return isAgent;
    }

    public void setIsAgent(boolean agent) {
        isAgent = agent;
    }

}