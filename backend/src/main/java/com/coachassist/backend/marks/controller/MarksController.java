package com.coachassist.backend.marks.controller;

import com.coachassist.backend.dto.response.ApiResponse;
import com.coachassist.backend.marks.dto.request.MarksRequest;
import com.coachassist.backend.marks.service.MarksService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/marks")
@RequiredArgsConstructor
public class MarksController {

    private final MarksService marksService;

    @PostMapping("/add")
    public ApiResponse addMarks(
            @RequestBody MarksRequest request
    ) {

        return marksService.addStudentMarks(request);
    }

    @GetMapping("/analytics/{studentCode}")
    public Object getPerformanceAnalytics(
            @PathVariable String studentCode
    ) {

        return marksService
                .getPerformanceAnalytics(studentCode);
    }

    @GetMapping("/student/{studentCode}")
    public Object getStudentMarks(
            @PathVariable String studentCode
    ) {

        return marksService
                .getStudentMarks(studentCode);
    }
}