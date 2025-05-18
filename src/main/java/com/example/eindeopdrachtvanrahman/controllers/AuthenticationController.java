package com.example.eindeopdrachtvanrahman.controllers;

import com.example.eindeopdrachtvanrahman.Services.CustomUserDetailsService;
import com.example.eindeopdrachtvanrahman.dto.AuthenticationRequestDTO;
import com.example.eindeopdrachtvanrahman.dto.AuthenticationResponseDTO;
import com.example.eindeopdrachtvanrahman.utils.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin
@RestController
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public AuthenticationController(AuthenticationManager authenticationManager, CustomUserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }
    // Deze methode geeft de principal (basic user gegevens ) terug van de ingelogde gebruiker
   @GetMapping(value = "/authenticate")
   public ResponseEntity<Object>authenticated(Authentication authentication, Principal principal){
        return ResponseEntity.ok().body(principal);
   }
   //Deze methode geeft het JWT token terug wanneer de gebruiker de juiste inloggegevens op geeft.
    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestDTO authenticationRequestDTO) throws Exception {
 String username=authenticationRequestDTO.getUsername();
 String password=authenticationRequestDTO.getPassword();
 try {
     authenticationManager.authenticate(
             new UsernamePasswordAuthenticationToken(username,password)
     );
 }catch (BadCredentialsException ex){
     throw new Exception("Incorrect username or password", ex);
 }
 final UserDetails userDetails=userDetailsService.loadUserByUsername(username);
 final String jwt=jwtUtil.generateToken(userDetails);
 return ResponseEntity.ok(new AuthenticationResponseDTO(jwt));
 }

    }
