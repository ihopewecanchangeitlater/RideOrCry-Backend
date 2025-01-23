package gr.uom.RideOrCry.Controllers;


import gr.uom.RideOrCry.Services.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/agencies")
@PreAuthorize("hasRole('AGENCY')")
public class AgencyController {

    @Autowired
    private AgencyService agencyService;

}