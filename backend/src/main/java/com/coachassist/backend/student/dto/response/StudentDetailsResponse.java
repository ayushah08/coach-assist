package com.coachassist.backend.student.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StudentDetailsResponse {

    private String studentName;

    private String studentCode;

    private String className;

    private String parentName;

    private String parentPhone;

    private String coachingName;
}