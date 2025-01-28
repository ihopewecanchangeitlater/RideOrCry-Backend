package gr.uom.RideOrCry.Services;

import gr.uom.RideOrCry.Entities.User;
import gr.uom.RideOrCry.Repositories.CitizenRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CitizenService {

    @Autowired
    private CitizenRepository citizenRepository;

    public User findByEmailAndPassword(String email, String password) {
        return citizenRepository.findCitizenByEmailAndPassword(email, password).orElseThrow(EntityNotFoundException::new);
    }

    public User getCitizen(String afm) {
        return citizenRepository.findById(afm).orElseThrow(EntityNotFoundException::new);
    }
}
