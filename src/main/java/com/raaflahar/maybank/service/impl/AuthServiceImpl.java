package com.raaflahar.maybank.service.impl;

import com.raaflahar.maybank.constant.enums.Role;
import com.raaflahar.maybank.dto.auth.AuthRequest;
import com.raaflahar.maybank.dto.auth.AuthResponse;
import com.raaflahar.maybank.entity.User;
import com.raaflahar.maybank.repository.UserRepository;
import com.raaflahar.maybank.security.JwtUtil;
import com.raaflahar.maybank.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public void register(AuthRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CUSTOMER) // default CUSTOMER
                .build();
        userRepository.save(user);
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtUtil.generateToken(user);
        return AuthResponse.builder().token(token).build();
    }
}
