package com.coachassist.backend.coaching.service;

import com.coachassist.backend.coaching.dto.request.CoachingLoginRequest;
import com.coachassist.backend.coaching.dto.request.CoachingRegisterRequest;
import com.coachassist.backend.coaching.dto.response.CoachingLoginResponse;
import com.coachassist.backend.coaching.dto.response.CoachingResponse;
import com.coachassist.backend.coaching.dto.response.PaginatedCoachingResponse;
import com.coachassist.backend.coaching.entity.Coaching;
import com.coachassist.backend.coaching.enums.ApprovalStatus;
import com.coachassist.backend.coaching.repository.CoachingRepository;
import com.coachassist.backend.dto.response.ApiResponse;
import com.coachassist.backend.security.enums.Role;
import com.coachassist.backend.security.jwt.JwtService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CoachingService {

    private final CoachingRepository coachingRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public ApiResponse registerCoaching(
            CoachingRegisterRequest request
    ) {

        if (coachingRepository
                .findByEmail(request.getEmail())
                .isPresent()) {

            return new ApiResponse(
                    "Email Already Exists",
                    false
            );
        }



        Coaching coaching = new Coaching();

        coaching.setEmail(request.getEmail());

        coaching.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        coaching.setRole(Role.ROLE_COACHING);

        coaching.setCoachingName(
                request.getCoachingName()
        );



        coaching.setPhoneNumber(
                request.getPhoneNumber()
        );

        coaching.setCoachingCode(
                generateCoachingCode()

        );
        coaching.setApprovalStatus(
                ApprovalStatus.PENDING
        );

        coaching.setRole(Role.ROLE_COACHING);

        coachingRepository.save(coaching);



        return new ApiResponse(
                "Coaching Registered Succesfully",
                true
        );
    }

    public String generateCoachingCode() {

        Random random = new Random();

        int number =
                1000 + random.nextInt(9000);

        return "CA-" + number;
    }

    public Object loginCoaching(
            CoachingLoginRequest request
    ) {

        Optional<Coaching> optionalCoaching =
                coachingRepository.findByEmail(
                        request.getEmail()
                );

        if (optionalCoaching.isEmpty()) {

            return new ApiResponse(
                    "Coaching Not Found",
                    false
            );
        }


        Coaching coaching =
                optionalCoaching.get();

        boolean passwordMatch =
                passwordEncoder.matches(
                        request.getPassword(),
                        coaching.getPassword()
                );

        if (!passwordMatch) {

            return new ApiResponse(
                    "Wrong Password",
                    false
            );
        }

        if (coaching.getApprovalStatus()
                != ApprovalStatus.APPROVED) {

            return new ApiResponse(
                    "Coaching Approval Pending",
                    false
            );
        }

        String token =
                jwtService.generateToken(
                        coaching.getEmail(),
                        coaching.getRole().name()
                );



        return new CoachingLoginResponse(

                token,

                coaching.getCoachingName(),

                coaching.getCoachingCode()

        );
    }

    public PaginatedCoachingResponse getAllCoachings(

            int page,

            int size
    ) {

        Pageable pageable =
                PageRequest.of(page, size);

        Page<Coaching> coachingPage =
                coachingRepository.findAll(pageable);

        List<CoachingResponse> coachings =
                coachingPage.getContent()
                        .stream()
                        .map(coaching ->
                                new CoachingResponse(
                                        coaching.getCoachingCode(),
                                        coaching.getCoachingName(),
                                        coaching.getEmail(),
                                        coaching.getPhoneNumber()

                                )
                        )
                        .toList();

        return new PaginatedCoachingResponse(

                coachings,

                coachingPage.getNumber(),

                coachingPage.getSize(),

                coachingPage.getTotalPages(),

                coachingPage.getTotalElements()
        );
    }

    public ApiResponse deleteCoaching(
            String coachingCode
    ) {

        Optional<Coaching> optionalCoaching =
                coachingRepository.findByCoachingCode(
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

        coachingRepository.delete(coaching);

        return new ApiResponse(
                "Coaching Deleted Successfully",
                true
        );
    }

    public PaginatedCoachingResponse searchCoachings(

            String keyword,

            int page,

            int size
    ) {

        Pageable pageable =
                PageRequest.of(page, size);

        Page<Coaching> coachingPage =
                coachingRepository
                        .findByCoachingNameContainingIgnoreCase(
                                keyword,
                                pageable
                        );

        List<CoachingResponse> coachings =
                coachingPage.getContent()
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

        return new PaginatedCoachingResponse(

                coachings,

                coachingPage.getNumber(),

                coachingPage.getSize(),

                coachingPage.getTotalPages(),

                coachingPage.getTotalElements()
        );
    }
}