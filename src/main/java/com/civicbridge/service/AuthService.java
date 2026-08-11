package com.civicbridge.service;

import com.civicbridge.dto.AuthResponse;
import com.civicbridge.dto.LoginRequest;
import com.civicbridge.dto.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}