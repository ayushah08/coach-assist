package com.coachassist.backend.dashboard.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DashboardSummaryResponse {

    private long totalStudents;

    private long presentToday;

    private long absentToday;

    private double averagePerformance;
}