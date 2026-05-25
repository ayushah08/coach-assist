package com.coachassist.backend.dashboard.controller;

import com.coachassist.backend.dashboard.dto.response.DashboardSummaryResponse;
import com.coachassist.backend.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/summary")
    public DashboardSummaryResponse getDashboardSummary() {

        return dashboardService.getDashboardSummary();
    }
}