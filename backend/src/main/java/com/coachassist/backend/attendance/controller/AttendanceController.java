package com.coachassist.backend.attendance.controller;

import com.coachassist.backend.attendance.dto.request.AttendanceRequest;
import com.coachassist.backend.attendance.dto.request.BulkAttendanceRequest;
import com.coachassist.backend.attendance.service.AttendanceService;
import com.coachassist.backend.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/mark")
    public ApiResponse markAttendance(
            @RequestBody AttendanceRequest request
    ) {

        return attendanceService.markAttendance(request);
    }

    @PostMapping("/bulk-mark")
    public ApiResponse markBulkAttendance(
            @RequestBody BulkAttendanceRequest request
    ) {

        return attendanceService
                .bulkAttendance(request);
    }

    @GetMapping("/analytics/{studentCode}")
    public Object getAttendanceAnalytics(
            @PathVariable String studentCode
    ) {

        return attendanceService
                .getAttendanceAnalytics(studentCode);
    }

    @GetMapping("/student/{studentCode}")
    public Object getStudentAttendanceHistory(
            @PathVariable String studentCode
    ) {

        return attendanceService
                .getStudentAttendanceHistory(studentCode);
    }
}