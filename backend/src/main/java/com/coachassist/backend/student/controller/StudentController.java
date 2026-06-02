package com.coachassist.backend.student.controller;

import com.coachassist.backend.dto.response.ApiResponse;
import com.coachassist.backend.student.dto.request.StudentCreateRequest;
import com.coachassist.backend.student.dto.request.UpdateStudentRequest;
import com.coachassist.backend.student.dto.response.PaginatedStudentResponse;
import com.coachassist.backend.student.dto.response.StudentResponse;
import com.coachassist.backend.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/add")
    public Object createStudent(
            @RequestBody StudentCreateRequest request
    ) {

        return studentService.createStudent(request);
    }

    @GetMapping("/all")
    public PaginatedStudentResponse getAllStudents(

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size
    ) {

        return studentService
                .getAllStudents(page, size);
    }

    @GetMapping("/{studentCode}")
    public Object getStudentDetails(
            @PathVariable String studentCode
    ) {

        return studentService
                .getStudentDetails(studentCode);
    }

    @PutMapping("/update/{studentCode}")
    public ApiResponse updateStudent(

            @PathVariable String studentCode,

            @RequestBody UpdateStudentRequest request
    ) {

        return studentService.updateStudent(
                studentCode,
                request
        );
    }

    @DeleteMapping("/delete/{studentCode}")
    public ApiResponse deleteStudent(
            @PathVariable String studentCode
    ) {

        return studentService
                .deleteStudent(studentCode);
    }

    @GetMapping("/search")
    public PaginatedStudentResponse searchStudents(

            @RequestParam String keyword,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size
    ) {

        return studentService.searchStudents(
                keyword,
                page,
                size
        );
    }

}