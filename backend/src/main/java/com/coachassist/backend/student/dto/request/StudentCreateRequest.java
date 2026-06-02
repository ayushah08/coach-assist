package com.coachassist.backend.student.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class StudentCreateRequest {
    private String studentName;

    private String className;

    private String parentName;

    private String parentPhone;

    private String coachingCode;
}
