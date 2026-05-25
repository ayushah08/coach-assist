package com.coachassist.backend.coaching.controller;

import com.coachassist.backend.coaching.dto.request.CoachingLoginRequest;
import com.coachassist.backend.coaching.dto.request.CoachingRegisterRequest;
import com.coachassist.backend.coaching.dto.response.PaginatedCoachingResponse;
import com.coachassist.backend.coaching.service.CoachingService;
import com.coachassist.backend.dto.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coaching")
@RequiredArgsConstructor
public class CoachingController {

    private final CoachingService coachingService;

    @PostMapping("/register")
    public ApiResponse registerCoaching(@Valid
            @RequestBody CoachingRegisterRequest request
    ) {

        return coachingService.registerCoaching(request);
    }

    @PostMapping("/login")
    public Object loginCoaching(
            @RequestBody CoachingLoginRequest request
    ) {

        return coachingService.loginCoaching(request);
    }

    @GetMapping("/all")
    public PaginatedCoachingResponse getAllCoachings(

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size
    ) {

        return coachingService
                .getAllCoachings(page, size);
    }

    @DeleteMapping("/delete/{coachingCode}")
    public ApiResponse deleteCoaching(
            @PathVariable String coachingCode
    ) {

        return coachingService
                .deleteCoaching(coachingCode);
    }

    @GetMapping("/search")
    public PaginatedCoachingResponse searchCoachings(

            @RequestParam String keyword,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size
    ) {

        return coachingService.searchCoachings(
                keyword,
                page,
                size
        );
    }
}