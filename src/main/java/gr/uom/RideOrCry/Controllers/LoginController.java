package gr.uom.RideOrCry.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gr.uom.RideOrCry.Services.AgencyService;
import gr.uom.RideOrCry.Services.CitizenService;
import gr.uom.RideOrCry.dto.LoginResponse;
import gr.uom.RideOrCry.entities.Agency;
import gr.uom.RideOrCry.entities.Citizen;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private CitizenService citizenService;

    @Autowired
    private AgencyService agencyService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
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

        // Αν δεν βρεθεί ούτε Citizen ούτε Agent
        return ResponseEntity.status(404).body("User not found. Check your credentials or register.");
    }
}
