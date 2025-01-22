package gr.uom.RideOrCry.Controllers;


import gr.uom.RideOrCry.Entities.Agency;
import gr.uom.RideOrCry.Services.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agencies")
public class AgencyController {

    @Autowired
    private AgencyService agencyService;

    // Endpoint για την εμφάνιση όλων των agents
    @GetMapping("/agentlist")
    public List<Agency> getAgents() throws Exception {
        return agencyService.getAgencies();
    }

    // Endpoint για την προσθήκη νέου agent
    @PostMapping("/register")
    public Agency addAgent(@RequestBody Agency agent) throws Exception {
        return agencyService.addAgent(agent);
    }

}