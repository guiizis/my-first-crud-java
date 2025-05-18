package com.example.userapi.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.userapi.model.AuthUser;
import com.example.userapi.repository.AuthUserRepository;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(AuthUserRepository userRepository) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                String encodedPassword = new BCryptPasswordEncoder().encode("1234");
                userRepository.save(AuthUser.builder()
                        .username("admin")
                        .password(encodedPassword)
                        .role("ADMIN")
                        .build());
                System.out.println("Usuário admin criado com senha 1234");
            }
        };
    }
}
