package com.coachassist.backend.marks.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MarksResponse {

    private String subject;

    private String examName;

    private double marksObtained;

    private double totalMarks;

    private double percentage;
}