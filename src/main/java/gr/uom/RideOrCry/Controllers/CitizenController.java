package gr.uom.RideOrCry.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.uom.RideOrCry.Services.CitizenService;
import gr.uom.RideOrCry.entities.Citizen;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/citizens")
public class CitizenController {

    @Autowired
    private CitizenService citizenService;

    @PostMapping("/register")
    public ResponseEntity<Citizen> registerCitizen(@Valid @RequestBody Citizen citizen) {
        Citizen registeredCitizen = citizenService.registerCitizen(citizen);
        return ResponseEntity.ok(registeredCitizen);
    }
    
 // Endpoint για την εμφάνιση όλων των agents
    @GetMapping(path = "/citizenlist")
    public List<Citizen> getAgents() throws Exception {
        return citizenService.getCitizens();
    }
}
