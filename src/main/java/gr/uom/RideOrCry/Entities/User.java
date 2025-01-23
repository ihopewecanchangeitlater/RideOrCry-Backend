package gr.uom.RideOrCry.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gr.uom.RideOrCry.DTO.Agency;
import gr.uom.RideOrCry.DTO.Citizen;
import gr.uom.RideOrCry.DTO.UserRegistrationRequest;
import gr.uom.RideOrCry.Enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Cascade;

import java.util.HashSet;
import java.util.Set;

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

    @JsonIgnore
    @NotBlank(message = "Password is required.")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters long.")
    private String password;

    @NotBlank(message = "Name is required.")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters.")
    private String name;

    @Column(name = "custom_field")
    @NotBlank(message = "Owner name is required.")
    @Size(max = 50, message = "Company name must be less than 50 characters")
    private String customField;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_afm"))
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private Set<UserRole> roles;

    public User() {
    }

    public User(UserRegistrationRequest registrationRequest) {
        this.afm = registrationRequest.getAfm();
        this.email = registrationRequest.getEmail();
        this.password = registrationRequest.getPassword();
        this.name = registrationRequest.getName();
        this.roles = new HashSet<>();
        this.customField = (registrationRequest instanceof Agency) ? ((Agency) registrationRequest).getOwner() : ((Citizen) registrationRequest).getSurname();
    }

    public User(UserRegistrationRequest registrationRequest, Set<UserRole> roles) {
        this.afm = registrationRequest.getAfm();
        this.email = registrationRequest.getEmail();
        this.password = registrationRequest.getPassword();
        this.name = registrationRequest.getName();
        this.roles = roles;
        this.customField = (registrationRequest instanceof Agency) ? ((Agency) registrationRequest).getOwner() : ((Citizen) registrationRequest).getSurname();
    }

    public User(String afm, String email, String password, String name, String customField) {
        this.afm = afm;
        this.email = email;
        this.password = password;
        this.name = name;
        this.customField = customField;
        this.roles = new HashSet<>();
    }

    public User(String afm, String email, String password, String name, String customField, Set<UserRole> roles) {
        this.afm = afm;
        this.email = email;
        this.password = password;
        this.name = name;
        this.customField = customField;
        this.roles = roles;
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

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomField() {
        return customField;
    }

    public void setCustomField(String customField) {
        this.customField = customField;
    }
}
