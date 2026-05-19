package com.example.springsecurity.dtos;

import java.util.List;

public record UserProfileDto(
        Long id,
        String email,
        List<String> roles
) {}
