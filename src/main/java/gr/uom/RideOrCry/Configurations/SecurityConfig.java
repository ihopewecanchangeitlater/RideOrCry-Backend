package gr.uom.RideOrCry.Configurations;

import gr.uom.RideOrCry.Enums.UserRole;
import gr.uom.RideOrCry.Services.CustomUserDetailsService;
import gr.uom.RideOrCry.Utils.JwtAuthenticationEntryPoint;
import gr.uom.RideOrCry.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers( "/**",
                                "/api-ui/**",
                                "/api-docs/**",
                                "/v3/api-docs/**",       // Fallback for default OpenAPI JSON path
                                "/swagger-ui/**",        // Fallback for default Swagger UI path
                                "/webjars/**",
                                "/swagger-resources/**")
                        .permitAll()
//                        .requestMatchers(
//                                "/api/auth/register/**",
//                                "/api/auth/login",
//                                "api/auth/logout")
//                        .permitAll()
//                        .requestMatchers("/api/agencies/**")
//                        .hasAuthority(UserRole.AGENCY.getAuthority())
//                        .requestMatchers("/api/citizens/**")
//                        .hasAuthority(UserRole.CITIZEN.getAuthority())
//                        .requestMatchers(
//                                "/api/cars/**",
//                                "/api/reservations/**")
//                        .hasAnyAuthority(
//                                UserRole.AGENCY.getAuthority(),
//                                UserRole.CITIZEN.getAuthority())
                        .anyRequest().authenticated())
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        http.addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public JwtRequestFilter jwtRequestFilter() {
        return new JwtRequestFilter();
    }
}