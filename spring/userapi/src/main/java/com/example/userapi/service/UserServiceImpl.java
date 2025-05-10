package com.example.userapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.userapi.dto.UserRequestDTO;
import com.example.userapi.dto.UserResponseDTO;
import com.example.userapi.mapper.UserMapper;
import com.example.userapi.model.UserTest;
import com.example.userapi.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDTO create(UserRequestDTO dto) {
        UserTest user = userRepository.save(UserMapper.entity(dto));
        return UserMapper.toDTO(user);
    }

    @Override
    public List<UserResponseDTO> getAll() {
        return userRepository
                .findAll()
                .stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO update(Long id, UserRequestDTO dto) {
        Optional<UserTest> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            throw new RuntimeException("User with this id: " + id + " not found");
        }

        UserTest user = optional.get();
        user.setName(dto.getName());
        user.setAge(dto.getAge());

        UserTest updated = userRepository.save(user);
        return UserMapper.toDTO(updated);
    }

}
