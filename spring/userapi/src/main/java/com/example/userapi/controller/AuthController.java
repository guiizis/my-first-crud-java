package com.example.userapi.controller;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userapi.config.JwtUtil;
import com.example.userapi.DTO.AuthUserLoginRequest;
import com.example.userapi.repository.AuthUserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.userapi.model.AuthUser;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(AuthUserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.authUserRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;


        System.out.println("teste");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthUserLoginRequest loginRequest) {
        Optional<AuthUser> userOpt = authUserRepository.findByUsername(loginRequest.getUsername());

        if(userOpt.isEmpty()) {
            return ResponseEntity.status(401).body("user not found");
        }

        AuthUser user = userOpt.get();

        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.status(403).body("invalid password");
        }

        String token = jwtUtil.generateToken(user.getPassword());
        return ResponseEntity.ok().body(token);
    }
}
