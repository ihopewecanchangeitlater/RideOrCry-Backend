package gr.uom.RideOrCry.Services;

import gr.uom.RideOrCry.DTO.Agency;
import gr.uom.RideOrCry.DTO.Citizen;
import gr.uom.RideOrCry.Entities.User;
import gr.uom.RideOrCry.Enums.UserRole;
import gr.uom.RideOrCry.Exceptions.UserAlreadyExistsException;
import gr.uom.RideOrCry.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private void checkExistence(String afm, String email) throws UserAlreadyExistsException {
        if (userRepository.findByAfmOrEmail(afm, email).isPresent())
            throw new UserAlreadyExistsException();
    }

    public void createAgency(Agency agency) throws UserAlreadyExistsException {
        checkExistence(agency.getAfm(), agency.getEmail());
        agency.setPassword(passwordEncoder.encode(agency.getPassword()));
        userRepository.save(new User(agency, Set.of(UserRole.AGENCY)));
    }

    public void createCitizen(Citizen citizen) throws UserAlreadyExistsException {
        checkExistence(citizen.getAfm(), citizen.getEmail());
        citizen.setPassword(passwordEncoder.encode(citizen.getPassword()));
        userRepository.save(new User(citizen, Set.of(UserRole.CITIZEN)));
    }

    public User getUserById(String userId) throws Exception {
        return userRepository.findById(userId).orElseThrow(() -> new Exception(""));
    }
}
