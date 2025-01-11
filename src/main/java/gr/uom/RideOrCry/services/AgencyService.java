package gr.uom.RideOrCry.services;

import gr.uom.RideOrCry.entities.Car;
import gr.uom.RideOrCry.entities.Agency;
import gr.uom.RideOrCry.repositories.CarRepository;
import gr.uom.RideOrCry.repositories.AgencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    
}
