package gr.uom.RideOrCry.Controllers;

import gr.uom.RideOrCry.DTO.AuthenticationRequest;
import gr.uom.RideOrCry.Entities.User;
import gr.uom.RideOrCry.Exceptions.UserAlreadyExistsException;
import gr.uom.RideOrCry.Repositories.UserRepository;
import gr.uom.RideOrCry.Services.CustomUserDetailsService;
import gr.uom.RideOrCry.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            if (userRepository.findByAfmOrEmail(user.getAfm(), user.getEmail()).isPresent())
                throw new UserAlreadyExistsException();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return ResponseEntity.ok("User registered successfully");
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
        return ResponseEntity.internalServerError().body("Error occurred");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
            return ResponseEntity.ok(jwtUtil.generateToken(userDetails));
        } catch (AuthenticationException ae) {
            System.err.println(ae.getLocalizedMessage());
            return ResponseEntity.badRequest().body("Authentication failed");
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
        return ResponseEntity.internalServerError().body("Error occurred");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logged out successfully");
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}