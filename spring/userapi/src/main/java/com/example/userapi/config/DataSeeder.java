package com.example.userapi.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.userapi.model.AuthUser;
import com.example.userapi.repository.AuthUserRepository;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(AuthUserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                String encodedPassword = passwordEncoder.encode("1234");
                userRepository.save(AuthUser.builder()
                        .username("admin")
                        .password(encodedPassword)
                        .role("ADMIN")
                        .build());
            }
        };
    }
}
