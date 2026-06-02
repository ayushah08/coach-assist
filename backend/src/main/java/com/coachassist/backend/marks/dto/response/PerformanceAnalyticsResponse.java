package com.coachassist.backend.marks.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PerformanceAnalyticsResponse {

    private String studentName;

    private double averagePercentage;

    private int totalExams;
}