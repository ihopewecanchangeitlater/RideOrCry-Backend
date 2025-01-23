package gr.uom.RideOrCry.Controllers;

import gr.uom.RideOrCry.Services.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/citizens")
@PreAuthorize("hasRole('CITIZEN')")
public class CitizenController {

    @Autowired
    private CitizenService citizenService;

}
