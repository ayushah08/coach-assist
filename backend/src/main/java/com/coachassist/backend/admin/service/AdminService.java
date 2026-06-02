package com.coachassist.backend.admin.service;

import com.coachassist.backend.admin.dto.request.AdminLoginRequest;
import com.coachassist.backend.admin.dto.response.AdminLoginResponse;
import com.coachassist.backend.admin.entity.Admin;
import com.coachassist.backend.admin.repository.AdminRepository;
import com.coachassist.backend.coaching.dto.response.CoachingResponse;
import com.coachassist.backend.coaching.entity.Coaching;
import com.coachassist.backend.coaching.enums.ApprovalStatus;
import com.coachassist.backend.coaching.repository.CoachingRepository;
import com.coachassist.backend.dto.response.ApiResponse;
import com.coachassist.backend.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final CoachingRepository coachingRepository;
    public Object loginAdmin(
            AdminLoginRequest request
    ) {

        Optional<Admin> optionalAdmin =
                adminRepository.findByUsername(
                        request.getUsername()
                );

        if (optionalAdmin.isEmpty()) {

            return new ApiResponse(
                    "Admin Not Found",
                    false
            );
        }

        Admin admin =
                optionalAdmin.get();

        boolean passwordMatch =
                passwordEncoder.matches(
                        request.getPassword(),
                        admin.getPassword()
                );

        if (!passwordMatch) {

            return new ApiResponse(
                    "Wrong Password",
                    false
            );
        }

        String token =
                jwtService.generateToken(
                        admin.getUsername(),
                        admin.getRole().name()
                );

        return new AdminLoginResponse(
                token,
                admin.getUsername()
        );
    }

    public ApiResponse approveCoaching(
            String coachingCode
    ) {

        Optional<Coaching> optionalCoaching =
                coachingRepository
                        .findByCoachingCode(
                                coachingCode
                        );

        if (optionalCoaching.isEmpty()) {

            return new ApiResponse(
                    "Coaching Not Found",
                    false
            );
        }

        Coaching coaching =
                optionalCoaching.get();

        coaching.setApprovalStatus(
                ApprovalStatus.APPROVED
        );

        coachingRepository.save(coaching);

        return new ApiResponse(
                "Coaching Approved Successfully",
                true
        );
    }

    public List<CoachingResponse>
    getPendingCoachings() {

        return coachingRepository
                .findByApprovalStatus(
                        ApprovalStatus.PENDING
                )
                .stream()
                .map(coaching ->
                        new CoachingResponse(

                                coaching.getCoachingName(),

                                coaching.getCoachingCode(),

                                coaching.getEmail(),

                                coaching.getPhoneNumber()
                        )
                )
                .toList();
    }
}