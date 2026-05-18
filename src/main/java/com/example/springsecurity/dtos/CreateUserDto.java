package com.example.springsecurity.dtos;

import com.example.springsecurity.enums.RoleName;

public record CreateUserDto(
        String email,
        String password,
        RoleName role
) {}
