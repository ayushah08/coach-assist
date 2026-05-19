package com.coachassist.backend.auth.service;

import com.coachassist.backend.admin.entity.Admin;
import com.coachassist.backend.admin.repository.AdminRepository;
import com.coachassist.backend.dto.request.AdminLoginRequest;
import com.coachassist.backend.dto.response.ApiResponse;
import com.coachassist.backend.security.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public ApiResponse login(AdminLoginRequest loginRequest) {


        Optional<Admin> optionalAdmin = adminRepository.findByUsername(loginRequest.getUsername());

        //Empty username To avoid optional checks
        if (optionalAdmin.isEmpty()) {
            return new ApiResponse("Admin Not Found ", false);
        }


        //if uname not empty opAdmin gets stored in admin with its data
        Admin admin = optionalAdmin.get();

        //Wrong Password
        if (passwordEncoder.matches(admin.getPassword(), loginRequest.getPassword())) {
            return new ApiResponse("Wrong Password ", false);
        }
        String token = jwtService.generateToken(admin.getUsername());

        return new ApiResponse(token, true);
    }
}

