package com.coachassist.backend.student.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StudentResponse {

    private String studentName;

    private String studentCode;

    private String className;

    private String parentName;
}