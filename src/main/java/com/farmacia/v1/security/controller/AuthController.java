package com.farmacia.v1.security.controller;


import com.farmacia.v1.security.JwtService;

import com.farmacia.v1.security.dto.AuthRequest;
import com.farmacia.v1.security.dto.AuthResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
//@CrossOrigin("*")

public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest req) {
        // Aquí el principal es el EMAIL
        Authentication auth = new UsernamePasswordAuthenticationToken(req.email(), req.password());
        authenticationManager.authenticate(auth);

        UserDetails user = userDetailsService.loadUserByUsername(req.email());
        String token = jwtService.generateToken(user); // sub = email

        return ResponseEntity.ok(new AuthResponse(token));
    }
}