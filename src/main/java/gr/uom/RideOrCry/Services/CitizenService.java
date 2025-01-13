package gr.uom.RideOrCry.Services;

import gr.uom.RideOrCry.Models.Citizen;
import gr.uom.RideOrCry.Repositories.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CitizenService {

    @Autowired
    CitizenRepository citizenRepository;

    public Citizen getCitizen(String ssn) {
        Optional<Citizen> citizen = citizenRepository.findById(ssn);
        return citizen.orElse(null);
    }
}
