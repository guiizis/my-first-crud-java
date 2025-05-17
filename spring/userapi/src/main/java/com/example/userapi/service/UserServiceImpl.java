package com.example.userapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.userapi.dto.UserRequestDTO;
import com.example.userapi.dto.UserResponseDTO;
import com.example.userapi.exceptions.UserNotFoundException;
import com.example.userapi.mapper.UserMapper;
import com.example.userapi.model.UserTest;
import com.example.userapi.repository.UserTestRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserTestRepository UserTestRepository;

    public UserServiceImpl(UserTestRepository UserTestRepository) {
        this.UserTestRepository = UserTestRepository;
    }

    @Override
    public UserResponseDTO create(UserRequestDTO dto) {
        UserTest user = UserTestRepository.save(UserMapper.entity(dto));
        return UserMapper.toDTO(user);
    }

    @Override
    public List<UserResponseDTO> getAll() {
        return UserTestRepository
                .findAll()
                .stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO update(Long id, UserRequestDTO dto) {
        Optional<UserTest> optional = UserTestRepository.findById(id);
        if (optional.isEmpty()) {
            throw new UserNotFoundException("User with this id: " + id + " not found");
        }

        UserTest user = optional.get();
        user.setName(dto.getName());
        user.setAge(dto.getAge());

        UserTest updated = UserTestRepository.save(user);
        return UserMapper.toDTO(updated);
    }

    @Override
    public void delete(Long id) {
        Optional<UserTest> optional = UserTestRepository.findById(id);
        if (optional.isEmpty()) {
            throw new UserNotFoundException("User with this id: " + id + " not found");
        }
        UserTestRepository.deleteById(id);
    }
}
