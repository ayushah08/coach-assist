package com.coachassist.backend.student.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateStudentRequest {

    private String studentName;

    private String className;

    private String parentName;

    private String parentPhone;
}