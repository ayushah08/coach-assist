package com.coachassist.backend.marks.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MarksRequest {

    private String studentCode;

    private String subject;

    private String examName;

    private double marksObtained;

    private double totalMarks;
}