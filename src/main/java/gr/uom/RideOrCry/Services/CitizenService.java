package gr.uom.RideOrCry.Services;

import gr.uom.RideOrCry.Entities.Citizen;
import gr.uom.RideOrCry.Repositories.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitizenService {

    @Autowired
    private CitizenRepository citizenRepository;

    public Citizen registerCitizen(Citizen citizen) {
        // Έλεγχος αν υπάρχει ήδη το AFM στην βάση
        if (citizenRepository.existsById(citizen.getAfm())) {
            throw new IllegalArgumentException("Citizen with this AFM already exists.");
        }
        if (citizen.isAgent()) {
            citizen.setIsAgent(false);
        }
        return citizenRepository.save(citizen);
    }

    public List<Citizen> getCitizens() {
        return citizenRepository.findAll();
    }

    public Citizen findByEmailAndPassword(String email, String password) {
        return citizenRepository.findByEmailAndPassword(email, password);
    }

    public Citizen getCitizen(String afm) {
        Optional<Citizen> citizen = citizenRepository.findById(afm);
        return citizen.orElse(null);
    }
}
