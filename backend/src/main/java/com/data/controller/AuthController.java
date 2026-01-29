package com.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import com.data.dto.LoginRequest;
import com.data.dto.LoginResponse;
import com.data.model.User;
import com.data.security.JwtUtil;
import com.data.service.UserServiceImpl;

import jakarta.validation.Valid;
@CrossOrigin(origins="http://localhost:5173",
				allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserServiceImpl service;
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        // 1️⃣ Authenticate credentials
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(),
                                request.getPassword()
                        )
                );

        // 2️⃣ Load user details from DB
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(request.getEmail());

        // 3️⃣ Generate JWT
        String token = jwtUtil.generateToken(userDetails);

        // 4️⃣ Extract role
        String role = userDetails.getAuthorities()
                .iterator()
                .next()
                .getAuthority(); // ROLE_ADMIN / ROLE_CUSTOMER / ROLE_VENDOR

        // 5️⃣ Send response
        return new LoginResponse(
                token,
                userDetails.getUsername(),
                role
        );
    }
    
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody User user) {
        service.registerUser(user);
        return new ResponseEntity<>("User Registered successfully",HttpStatus.OK);
    }
    
}
