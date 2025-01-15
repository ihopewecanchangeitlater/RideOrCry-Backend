package gr.uom.RideOrCry.Services;


import gr.uom.RideOrCry.entities.Agency;
import gr.uom.RideOrCry.Repositories.AgencyRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgencyService {

    @Autowired
    private AgencyRepository agencyRepository;

    public Agency registerAgency(Agency agency) {
    	// Έλεγχος αν υπάρχει ήδη το AFM στην βάση
        if (agencyRepository.existsById(agency.getAfm())) {
            throw new IllegalArgumentException("Agency with this AFM already exists.");
        }
        return agencyRepository.save(agency);
    }

    // Μέθοδος λήψης agent από την βάση
    public List<Agency> getAgencies() {
        return agencyRepository.findAll();
    }
    
    public Agency findByEmailAndPassword(String email, String password) {
        return agencyRepository.findByEmailAndPassword(email, password);
    }
    
}
