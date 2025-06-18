package com.raaflahar.maybank.service;

import com.raaflahar.maybank.dto.auth.AuthRequest;
import com.raaflahar.maybank.dto.auth.AuthResponse;

public interface AuthService {
    void register(AuthRequest request);
    AuthResponse login(AuthRequest request);
}