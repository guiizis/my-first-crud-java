package com.example.userapi.dto;

public class UserResponseDTO {

    private Long id;
    private int age;
    private String name;

    public UserResponseDTO(Long id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public int getAge() {
        return this.age;
    }

    public String getName() {
        return this.name;
    }
}
