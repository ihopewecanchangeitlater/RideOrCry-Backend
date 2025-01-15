package gr.uom.RideOrCry.Controllers;


import gr.uom.RideOrCry.Entities.Agency;
import gr.uom.RideOrCry.Services.AgencyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/agencies")
public class AgencyController {

    private final AgencyService agencyService;


    public AgencyController(AgencyService agencyService) {
        this.agencyService = agencyService;
    }

    // Endpoint για την προσθήκη νέου agent
    @PostMapping("/register")
    public Agency addAgent(@RequestBody Agency agent) throws Exception {
        return agencyService.addAgent(agent);
    }

    // Endpoint για την εμφάνιση όλων των agents
    @GetMapping("/agentlist")
    public List<Agency> getAgents() throws Exception {
        return agencyService.getAgencies();
    }
}