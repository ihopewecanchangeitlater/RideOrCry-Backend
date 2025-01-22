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
@Table(name = "USERS")
public class User {

    @Id
    @NotNull(message = "AFM is required.")
    @Size(min = 9, max = 9, message = "TRN must have 9 digits")
    @Column(length = 9)
    private String afm;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required.")
    @Size(min = 6, max = 20, message = "Password must be at least 6 characters long.")
    private String password;

    public User() {
    }

    public User(String afm, String email, String password) {
        this.afm = afm;
        this.email = email;
        this.password = password;
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
}
