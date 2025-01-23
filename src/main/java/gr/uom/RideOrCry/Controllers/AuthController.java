package gr.uom.RideOrCry.Controllers;

import gr.uom.RideOrCry.DTO.Agency;
import gr.uom.RideOrCry.DTO.AuthenticationRequest;
import gr.uom.RideOrCry.DTO.Citizen;
import gr.uom.RideOrCry.Exceptions.UserAlreadyExistsException;
import gr.uom.RideOrCry.Services.CustomUserDetailsService;
import gr.uom.RideOrCry.Services.UserService;
import gr.uom.RideOrCry.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register/citizen")
    public ResponseEntity<String> registerUser(@RequestBody Citizen citizen) throws UserAlreadyExistsException {
        userService.createCitizen(citizen);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/register/agency")
    public ResponseEntity<String> registerUser(@RequestBody Agency agency) throws UserAlreadyExistsException {
        userService.createAgency(agency);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
        );
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        return ResponseEntity.ok(jwtUtil.generateToken(userDetails));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logged out successfully");
    }

}