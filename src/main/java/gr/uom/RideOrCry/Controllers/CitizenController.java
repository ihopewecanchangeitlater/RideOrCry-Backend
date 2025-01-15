package gr.uom.RideOrCry.Controllers;

import gr.uom.RideOrCry.Entities.Citizen;
import gr.uom.RideOrCry.Services.CitizenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
