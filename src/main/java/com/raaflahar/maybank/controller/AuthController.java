package com.raaflahar.maybank.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raaflahar.maybank.constant.ApiEndpoint;
import com.raaflahar.maybank.dto.auth.AuthRequest;
import com.raaflahar.maybank.dto.auth.AuthResponse;
import com.raaflahar.maybank.payload.response.CommonResponse;
import com.raaflahar.maybank.service.AuthService;
import com.raaflahar.maybank.util.ResponseUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiEndpoint.Auth.AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public CommonResponse<String> register(@RequestBody AuthRequest request) {
        authService.register(request);
        return ResponseUtil.success("User registered successfully");
    }

    @PostMapping("/login")
    public CommonResponse<AuthResponse> login(@RequestBody AuthRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseUtil.success(response);
    }
}
