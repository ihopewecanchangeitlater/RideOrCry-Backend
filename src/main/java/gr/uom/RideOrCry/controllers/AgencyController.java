package gr.uom.RideOrCry.controllers;

import gr.uom.RideOrCry.entities.Agency;
import gr.uom.RideOrCry.services.AgencyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/agency")
public class AgencyController {

    private final AgencyService agencyService;
    

    public AgencyController(AgencyService agencyService) {
        this.agencyService = agencyService;
    }

    // Endpoint για την προσθήκη νέου agent
    @PostMapping(path = "/addagent")
    public Agency addAgent(@RequestBody Agency agent) throws Exception {
        return agencyService.addAgent(agent);
    }

    // Endpoint για την εμφάνιση όλων των agents
    @GetMapping(path = "/agentlist")
    public List<Agency> getAgents() throws Exception {
        return agencyService.getAgencies();
    }

    
}
