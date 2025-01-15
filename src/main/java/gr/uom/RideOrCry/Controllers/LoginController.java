package gr.uom.RideOrCry.Controllers;

import gr.uom.RideOrCry.DTO.LoginRequest;
import gr.uom.RideOrCry.DTO.LoginResponse;
import gr.uom.RideOrCry.Entities.Agency;
import gr.uom.RideOrCry.Entities.Citizen;
import gr.uom.RideOrCry.Services.AgencyService;
import gr.uom.RideOrCry.Services.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private CitizenService citizenService;

    @Autowired
    private AgencyService agencyService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest body) {
        try {
            String email = body.getEmail();
            String password = body.getPassword();
            // Αναζήτηση στον Citizen
            Citizen citizen = citizenService.findByEmailAndPassword(email, password);
            if (citizen != null) {
                return ResponseEntity.ok(new LoginResponse(citizen, false)); // Citizen με σημαία isAgent = false
            }

            // Αν δεν βρεθεί στον Citizen, αναζήτηση στον Agent
            Agency agency = agencyService.findByEmailAndPassword(email, password);
            if (agency != null) {
                return ResponseEntity.ok(new LoginResponse(agency, true)); // Agency με σημαία isAgent = true
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // Αν δεν βρεθεί ούτε Citizen ούτε Agent
        return ResponseEntity.status(404).body(new LoginResponse("User not found. Check your credentials or register."));
    }
}
