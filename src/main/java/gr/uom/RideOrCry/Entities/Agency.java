package gr.uom.RideOrCry.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

@Entity
public class Agency {

    @Id
    @NotNull(message = "AFM is required.")
    @Min(value = 0, message = "VAT must be at least 0")
    @Max(value = 999999999, message = "VAT must have at most 9 digits")
    private Long afm;

    @NotBlank(message = "Name is required.")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters.")
    private String name;

    @NotBlank(message = "Owner name is required.")
    @Size(max = 255, message = "Company name must be less than 255 characters")
    private String owner;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required.")
    @Size(min = 6, max = 20, message = "Password must be at least 6 characters long.")
    private String password;

    @JsonIgnore
    private boolean isAgent = true;

    public Agency() {
    }

    public Agency(Long afm, String name, String owner, String email, String password) {
        this.afm = afm;
        this.name = name;
        this.owner = owner;
        this.email = email;
        this.password = password;
    }

    public Long getAfm() {
        return afm;
    }

    public void setAfm(Long afm) {
        this.afm = afm;
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

    public boolean getIsAgent() {
        return isAgent;
    }

    public void setIsAgent(boolean agent) {
        isAgent = agent;
    }
}