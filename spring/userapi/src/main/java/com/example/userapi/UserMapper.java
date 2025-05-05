package com.example.userapi.mapper;

import com.example.userapi.UserTest;
import com.example.userapi.dto.UserRequestDTO;
import com.example.userapi.dto.UserResponseDTO;

public class UserMapper {

    public static UserTest entity(UserRequestDTO dto) {
        return new UserTest(dto.getName(), dto.getAge());
    }

    public static UserResponseDTO toDTO(UserTest user) {
        return new UserResponseDTO(user.getId(), user.getAge(), user.getName());
    }
}
