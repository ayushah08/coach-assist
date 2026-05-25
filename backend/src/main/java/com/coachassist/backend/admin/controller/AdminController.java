package com.coachassist.backend.admin.controller;

import com.coachassist.backend.admin.dto.request.AdminLoginRequest;
import com.coachassist.backend.admin.service.AdminService;
import com.coachassist.backend.coaching.dto.response.CoachingResponse;
import com.coachassist.backend.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/login")
    public Object loginAdmin(
            @RequestBody AdminLoginRequest request
    ) {

        return adminService.loginAdmin(request);
    }

    @PutMapping("/coaching/approve/{coachingCode}")
    public ApiResponse approveCoaching(
            @PathVariable String coachingCode
    ) {
        return adminService
                .approveCoaching(coachingCode);
    }

    @GetMapping("/coaching/pending")
    public List<CoachingResponse>
    getPendingCoachings() {

        return adminService
                .getPendingCoachings();
    }


}