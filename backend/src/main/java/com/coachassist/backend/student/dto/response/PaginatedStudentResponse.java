package com.coachassist.backend.student.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PaginatedStudentResponse {

    private List<StudentResponse> students;

    private int currentPage;

    private int pageSize;

    private int totalPages;

    private long totalStudents;
}