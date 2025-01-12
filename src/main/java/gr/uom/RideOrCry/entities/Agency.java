package gr.uom.RideOrCry.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Agency {

    @Id
    private String afm;
    private String name; // Ίσως να μπει αυτό ως πρωτεύον κλειδί ως username (;)
    private String owner;
    private String password;


    
    // Getters and Setters
    
	public String getAfm() {
		return afm;
	}

	public void setAfm(String afm) {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}  
}