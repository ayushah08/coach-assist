package com.coachassist.backend.auth.controller;

import com.coachassist.backend.auth.service.AuthService;
import com.coachassist.backend.dto.request.AdminLoginRequest;
import com.coachassist.backend.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/admin/login")
    public ApiResponse adminLogin(@RequestBody AdminLoginRequest adminLoginRequest) {
        return authService.login(adminLoginRequest);
    }
}

