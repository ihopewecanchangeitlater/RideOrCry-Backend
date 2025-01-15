package gr.uom.RideOrCry.Controllers;

import gr.uom.RideOrCry.entities.Agency;
import gr.uom.RideOrCry.Services.AgencyService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/agencies")
public class AgencyController {

    @Autowired
    private AgencyService agencyService;

    @PostMapping("/register")
    public ResponseEntity<Agency> registerAgency(@Valid @RequestBody Agency agency) {
        Agency registeredAgency = agencyService.registerAgency(agency);
        return ResponseEntity.ok(registeredAgency);
    }
    
 // Endpoint για την εμφάνιση όλων των agents
    @GetMapping(path = "/agentlist")
    public List<Agency> getAgents() throws Exception {
        return agencyService.getAgencies();
    }
}
