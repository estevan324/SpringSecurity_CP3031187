package com.example.springsecurity.controllers;

import com.example.springsecurity.dtos.CreateUserDto;
import com.example.springsecurity.dtos.LoginUserDto;
import com.example.springsecurity.dtos.RecoveryJwtTokenDto;
import com.example.springsecurity.dtos.UserProfileDto;
import com.example.springsecurity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDto dto) {
        userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDto> login(@RequestBody LoginUserDto
                                                             dto) {
        RecoveryJwtTokenDto token = userService.authenticateUser(dto);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileDto> getCurrentUser(Authentication authentication) {
        return ResponseEntity.ok(userService.getUserInformation(authentication));
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Autenticado com sucesso!");
    }

    @GetMapping("/test/customer")
    public ResponseEntity<String> customerTest() {
        return ResponseEntity.ok("Acesso de CUSTOMER autorizado!");
    }

    @GetMapping("/test/administrator")
    public ResponseEntity<String> adminTest() {
        return ResponseEntity.ok("Acesso de ADMINISTRATOR autorizado!");
    }
}