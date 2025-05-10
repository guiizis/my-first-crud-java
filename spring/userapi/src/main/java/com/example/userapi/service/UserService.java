package com.example.userapi.service;

import java.util.List;

import com.example.userapi.dto.UserRequestDTO;
import com.example.userapi.dto.UserResponseDTO;

public interface UserService {
  UserResponseDTO create(UserRequestDTO dto);
  List<UserResponseDTO> getAll();
  UserResponseDTO update(Long id, UserRequestDTO dto);
  void delete(Long id);
}