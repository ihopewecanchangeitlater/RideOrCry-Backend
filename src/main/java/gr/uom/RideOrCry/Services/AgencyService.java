package gr.uom.RideOrCry.Services;

import gr.uom.RideOrCry.Entities.Agency;
import gr.uom.RideOrCry.Repositories.AgencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgencyService {

    @Autowired
    private AgencyRepository agencyRepository;

    public AgencyService(AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    // Μέθοδος προσθήκης agent στην βάση
    public Agency addAgent(Agency agent) {
        return agencyRepository.save(agent);
    }

    // Μέθοδος λήψης agent από την βάση
    public List<Agency> getAgencies() {
        return agencyRepository.findAll();
    }

    public Agency findByEmailAndPassword(String email, String password) {
        return agencyRepository.findByEmailAndPassword(email, password).orElse(null);
    }
}
