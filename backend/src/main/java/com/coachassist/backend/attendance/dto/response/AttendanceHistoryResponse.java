package com.coachassist.backend.attendance.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AttendanceHistoryResponse {

    private String attendanceDate;

    private String status;
}