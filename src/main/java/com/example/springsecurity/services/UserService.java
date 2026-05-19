package com.example.springsecurity.services;

import com.example.springsecurity.dtos.CreateUserDto;
import com.example.springsecurity.dtos.LoginUserDto;
import com.example.springsecurity.dtos.RecoveryJwtTokenDto;
import com.example.springsecurity.entities.Role;
import com.example.springsecurity.entities.User;
import com.example.springsecurity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public RecoveryJwtTokenDto authenticateUser(LoginUserDto loginDto) {
        var authToken = new UsernamePasswordAuthenticationToken(loginDto.email(),
                loginDto.password());
        var authentication =
                authenticationManager.authenticate(authToken);
        UserDetailsImpl userDetails = (UserDetailsImpl)
                authentication.getPrincipal();
        String token = jwtTokenService.generateToken(userDetails);
        return new RecoveryJwtTokenDto(token);
    }
    public void createUser(CreateUserDto createDto) {
        User newUser = User.builder()
                .email(createDto.email())
                .password(passwordEncoder.encode(createDto.password()))
                .roles(List.of(Role.builder().name(createDto.role()).build()))
                .build();
        userRepository.save(newUser);
    }
}
