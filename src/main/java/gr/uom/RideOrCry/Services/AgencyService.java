package gr.uom.RideOrCry.Services;

import gr.uom.RideOrCry.Entities.User;
import gr.uom.RideOrCry.Repositories.AgencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgencyService {

    @Autowired
    private AgencyRepository agencyRepository;

    public User findByEmailAndPassword(String email, String password) {
        return agencyRepository.findAgencyByEmailAndPassword(email, password).orElse(null);
    }
}
