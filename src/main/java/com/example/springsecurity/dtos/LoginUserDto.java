package com.example.springsecurity.dtos;

public record LoginUserDto(
        String email,
        String password
) {}
