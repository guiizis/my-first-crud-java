package com.example.userapi.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.userapi.model.User;
import com.example.userapi.repository.UserRepository;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                String encodedPassword = new BCryptPasswordEncoder().encode("1234");
                userRepository.save(User.builder()
                        .username("admin")
                        .password(encodedPassword)
                        .role("ADMIN")
                        .build());
                System.out.println("Usuário admin criado com senha 1234");
            }
        };
    }
}
