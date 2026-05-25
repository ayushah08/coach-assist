package com.coachassist.backend.attendance.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AttendanceAnalyticsResponse {

    private String studentName;

    private long presentDays;

    private long absentDays;

    private double attendancePercentage;
}