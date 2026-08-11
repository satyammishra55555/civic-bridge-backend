package com.civicbridge.service.impl;

import com.civicbridge.dto.AuthResponse;
import com.civicbridge.dto.LoginRequest;
import com.civicbridge.dto.RegisterRequest;
import com.civicbridge.entity.Role;
import com.civicbridge.entity.User;
import com.civicbridge.enums.RoleType;
import com.civicbridge.repository.RoleRepository;
import com.civicbridge.repository.UserRepository;
import com.civicbridge.security.JwtService;
import com.civicbridge.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;


    @Override
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (userRepository.existsByMobile(request.getMobile())) {
            throw new RuntimeException("Mobile already exists");
        }

        Role role = roleRepository
                .findByName(
                        RoleType.valueOf(
                                request.getRole().toUpperCase()
                        )
                )
                .orElseThrow(
                        () -> new RuntimeException("Role not found")
                );

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .mobile(request.getMobile())
                .password(
                        passwordEncoder.encode(
                                request.getPassword()
                        )
                )
                .enabled(true)
                .accountLocked(false)
                .role(role)
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user.getEmail());

        return AuthResponse.builder()
                .accessToken(token)
                .refreshToken(null)
                .tokenType("Bearer")
                .message("Registration Successful")
                .build();
    }


    @Override
    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(
                        () -> new RuntimeException("User not found")
                );

        String token = jwtService.generateToken(user.getEmail());

        return AuthResponse.builder()
                .accessToken(token)
                .refreshToken(null)
                .tokenType("Bearer")
                .message("Login Successful")
                .build();
    }
}